package com.example.haystackcalculator.MainActivity;

import com.example.haystackcalculator.R;

import java.util.HashMap;

public class ButtonsHashMap {
    // Создание мапы кнопок и соответствующих числовых значений
    final static HashMap<Integer, String> buttonIdsMap = new HashMap<Integer, String>() {
        {
            put(R.id.button0, "0");
            put(R.id.button1, "1");
            put(R.id.button2, "2");
            put(R.id.button3, "3");
            put(R.id.button4, "4");
            put(R.id.button5, "5");
            put(R.id.button6, "6");
            put(R.id.button7, "7");
            put(R.id.button8, "8");
            put(R.id.button9, "9");
        }
    };
}
