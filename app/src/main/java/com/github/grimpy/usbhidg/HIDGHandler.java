package com.github.grimpy.usbhidg;

import android.util.Log;
import android.view.KeyEvent;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;


public class HIDGHandler {
    private File hidg;
    private FileWriter hidgw;
    private RootHandler roothandler;
    public static final String DEVICE = "/dev/hidg0";
    private static final String TAG = "usbhid.handler";
    private static final String HIDDESCRIPTION = "\\x05\\x01\\x09\\x06\\xa1\\x01\\x05\\x07\\x19\\xe0\\x29\\xe7\\x15\\x00\\x25\\x01"+
                                                 "\\x75\\x01\\x95\\x08\\x81\\x02\\x95\\x01\\x75\\x08\\x81\\x03\\x95\\x05\\x75\\x01"+
                                                 "\\x05\\x08\\x19\\x01\\x29\\x05\\x91\\x02\\x95\\x01\\x75\\x03\\x91\\x03\\x95\\x06"+
                                                 "\\x75\\x08\\x15\\x00\\x25\\x65\\x05\\x07\\x19\\x00\\x29\\x65\\x81\\x00\\xc0\\x00";

    public HIDGHandler() {
        roothandler = new RootHandler();
        openHID();
    }

    public boolean openHID() {
        this.hidg = new File(DEVICE);
        if (!this.hidg.exists()) {
            this.createHID();
            if (!this.hidg.exists()) {
                Log.d(TAG, "Device does not exists");
                return false;
            }
        }
        try {
            this.hidgw = new FileWriter(DEVICE);
        } catch (IOException e) {
            fixPermissions();
            try {
                this.hidgw = new FileWriter(DEVICE);
            } catch (IOException ex) {
                Log.d(TAG, "Could not open hidg device for writing");
                return false;
            }
        }
        return true;
}

    public String test() {
        return roothandler.exec("ls /").output;
    }

    public void sendEvent(KeyEvent event) {
        int modifier = 0;
        if (event.isCtrlPressed()) {
            modifier |= 1;
        }
        if (event.isShiftPressed()) {
            Log.d(TAG, "Shift was pressed");
            modifier |= 2;
        }
        if (event.isAltPressed()) {
            modifier |= 4;
        }
        if (event.isMetaPressed()) {
            modifier |= 8;
        }
        Integer key = KeyCodes.US_QWERTY.get(event.getKeyCode());
        if (key == null) {
            key = KeyCodes.US_QWERTY_SHIFTED.get(event.getKeyCode());
            if (key == null) {
                Log.d(TAG, String.format("No mapping for keyevent %s", String.valueOf(event.getKeyCode())));
                return;
            }
            Log.d(TAG, "Shift is needed");
            modifier |= 2;
        }
        writeHID(modifier, key);
        writeHID(0, 0);

    }

    private void writeHID(int modifiers, int key) {
        try {
            hidgw.write(modifiers); // modifiers;
            hidgw.write(0); // unused field;
            hidgw.write(key); // letter A
            hidgw.write(0); // 2nd key
            hidgw.write(0); // 3rd key
            hidgw.write(0); // 4th key
            hidgw.write(0); // 5th key
            hidgw.write(0); // 6th key
            hidgw.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    public boolean createHID() {
        String udc = roothandler.exec("ls /sys/class/udc").output.trim();
        CommandResult result = roothandler.exec("cd /config/usb_gadget/g1");
        if (result.exit_code != 0){
            Log.d(TAG, "Could not access configfs");
            return false;
        }
        roothandler.writeFile("UDC", "");
        roothandler.makeDir("configs/b.1");
        roothandler.makeDir("functions/hid.usb0");
        roothandler.writeFile("functions/hid.usb0/protocol", "1");
        roothandler.writeFile("functions/hid.usb0/subclass", "1");
        roothandler.writeFile("functions/hid.usb0/report_length", "8");
        roothandler.writeFile("functions/hid.usb0/report_desc", HIDDESCRIPTION, false);
        roothandler.writeFile("configs/b.1/MaxPower", "120");
        roothandler.exec("ln -s functions/hid.usb0 configs/b.1");
        roothandler.writeFile("UDC", udc);
        fixPermissions();
        return true;
    }

    private void fixPermissions() {
        roothandler.exec("chmod 777 /dev/hidg0");

    }

}
