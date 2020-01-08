package com.github.grimpy.usbhidg;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    protected TextView text;
    private static final String TAG = "usbhid";
    private boolean echo = true;
    private HIDGHandler hidhandler = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG, "Creatingg app");
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        this.text = findViewById(R.id.textview);
        this.text.setText("Start typing just here");
        this.text.setCursorVisible(true);
        this.hidhandler = new HIDGHandler();
        setSupportActionBar(toolbar);
    }

    @Override
    protected void onStart() {
        super.onStart();
        showKeyboard();
    }

    public MainActivity() {
    }

    public void showKeyboard() {
        InputMethodManager imm = (InputMethodManager) getSystemService(this.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(InputMethodManager.SHOW_IMPLICIT, 0);
    }

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        if (event.getKeyCode() == KeyEvent.KEYCODE_SHIFT_LEFT) {
            return true;
        }
        if (this.echo) {
            if (event.getKeyCode() == KeyEvent.KEYCODE_DEL) {
                String currenttext = this.text.getText().toString();
                this.text.setText(currenttext.substring(0, currenttext.length() -1));

            } else {
                String key = String.valueOf(event.getDisplayLabel());
                if (!event.isCapsLockOn() && !event.isShiftPressed()) {
                    key = key.toLowerCase();
                }
                this.text.append(key);

            }
            hidhandler.sendEvent(event);

        }
        Log.d(TAG, event.toString());
        return super.onKeyUp(keyCode, event);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_keyboard) {
            showKeyboard();
            return true;
        } else if (id == R.id.action_echo   ) {
            this.echo = !this.echo;
            return true;

        } else if (id == R.id.action_settings   ) {
            this.text.setText(this.hidhandler.test());

        }

        return super.onOptionsItemSelected(item);
    }
}
