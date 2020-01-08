package com.github.grimpy.usbhidg;

import android.view.KeyEvent;

import java.util.HashMap;
import java.util.Map;


public class KeyCodes {
    public static Map<Integer, Integer> US_QWERTY;
    public static Map<Integer, Integer> US_QWERTY_SHIFTED;
    static {
            US_QWERTY = new HashMap<Integer, Integer>();
            US_QWERTY.put(KeyEvent.KEYCODE_A, 4);
            US_QWERTY.put(KeyEvent.KEYCODE_B, 5);
            US_QWERTY.put(KeyEvent.KEYCODE_C, 6);
            US_QWERTY.put(KeyEvent.KEYCODE_D, 7);
            US_QWERTY.put(KeyEvent.KEYCODE_E, 8);
            US_QWERTY.put(KeyEvent.KEYCODE_F, 9);
            US_QWERTY.put(KeyEvent.KEYCODE_G, 10);
            US_QWERTY.put(KeyEvent.KEYCODE_H, 11);
            US_QWERTY.put(KeyEvent.KEYCODE_I, 12);
            US_QWERTY.put(KeyEvent.KEYCODE_J, 13);
            US_QWERTY.put(KeyEvent.KEYCODE_K, 14);
            US_QWERTY.put(KeyEvent.KEYCODE_L, 15);
            US_QWERTY.put(KeyEvent.KEYCODE_M, 16);
            US_QWERTY.put(KeyEvent.KEYCODE_N, 17);
            US_QWERTY.put(KeyEvent.KEYCODE_O, 18);
            US_QWERTY.put(KeyEvent.KEYCODE_P, 19);
            US_QWERTY.put(KeyEvent.KEYCODE_Q, 20);
            US_QWERTY.put(KeyEvent.KEYCODE_R, 21);
            US_QWERTY.put(KeyEvent.KEYCODE_S, 22);
            US_QWERTY.put(KeyEvent.KEYCODE_T, 23);
            US_QWERTY.put(KeyEvent.KEYCODE_U, 24);
            US_QWERTY.put(KeyEvent.KEYCODE_V, 25);
            US_QWERTY.put(KeyEvent.KEYCODE_W, 26);
            US_QWERTY.put(KeyEvent.KEYCODE_X, 27);
            US_QWERTY.put(KeyEvent.KEYCODE_Y, 28);
            US_QWERTY.put(KeyEvent.KEYCODE_Z, 29);
            US_QWERTY.put(KeyEvent.KEYCODE_1, 30);
            US_QWERTY.put(KeyEvent.KEYCODE_2, 31);
            US_QWERTY.put(KeyEvent.KEYCODE_3, 32);
            US_QWERTY.put(KeyEvent.KEYCODE_4, 33);
            US_QWERTY.put(KeyEvent.KEYCODE_5, 34);
            US_QWERTY.put(KeyEvent.KEYCODE_6, 35);
            US_QWERTY.put(KeyEvent.KEYCODE_7, 36);
            US_QWERTY.put(KeyEvent.KEYCODE_8, 37);
            US_QWERTY.put(KeyEvent.KEYCODE_9, 38);
            US_QWERTY.put(KeyEvent.KEYCODE_0, 39);
            US_QWERTY.put(KeyEvent.KEYCODE_ENTER, 40);
            US_QWERTY.put(KeyEvent.KEYCODE_ESCAPE, 41);
            US_QWERTY.put(KeyEvent.KEYCODE_DEL, 42);
            US_QWERTY.put(KeyEvent.KEYCODE_TAB, 43);
            US_QWERTY.put(KeyEvent.KEYCODE_SPACE, 44);
            US_QWERTY.put(KeyEvent.KEYCODE_MINUS, 45);
            US_QWERTY.put(KeyEvent.KEYCODE_EQUALS, 46);
            US_QWERTY.put(KeyEvent.KEYCODE_LEFT_BRACKET, 47);
            US_QWERTY.put(KeyEvent.KEYCODE_RIGHT_BRACKET, 48);
            US_QWERTY.put(KeyEvent.KEYCODE_BACKSLASH, 49);
            US_QWERTY.put(KeyEvent.KEYCODE_SEMICOLON, 51);
            US_QWERTY.put(KeyEvent.KEYCODE_APOSTROPHE, 52);
            US_QWERTY.put(KeyEvent.KEYCODE_GRAVE, 53);
            US_QWERTY.put(KeyEvent.KEYCODE_COMMA, 54);
            US_QWERTY.put(KeyEvent.KEYCODE_PERIOD, 55);
            US_QWERTY.put(KeyEvent.KEYCODE_SLASH, 56);
            US_QWERTY.put(KeyEvent.KEYCODE_DPAD_RIGHT, 79);
            US_QWERTY.put(KeyEvent.KEYCODE_DPAD_LEFT, 80);
            US_QWERTY.put(KeyEvent.KEYCODE_DPAD_DOWN, 81);
            US_QWERTY.put(KeyEvent.KEYCODE_DPAD_UP, 82);
            US_QWERTY.put(KeyEvent.KEYCODE_SHIFT_LEFT, 255);


            US_QWERTY_SHIFTED = new HashMap<Integer, Integer>();
            US_QWERTY_SHIFTED.put(KeyEvent.KEYCODE_AT, 31);
            US_QWERTY_SHIFTED.put(KeyEvent.KEYCODE_POUND, 32);
            US_QWERTY_SHIFTED.put(KeyEvent.KEYCODE_STAR, 37);
            US_QWERTY_SHIFTED.put(KeyEvent.KEYCODE_PLUS, 46);
            US_QWERTY_SHIFTED.put(KeyEvent.KEYCODE_ESCAPE, 89);
    }

}
