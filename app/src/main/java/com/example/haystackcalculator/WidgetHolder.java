package com.example.haystackcalculator;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.BreakIterator;

public class WidgetHolder {

    public TextView HistoryDisplay, MainDisplay;
    public View button1, button2, button3, button4,
            button5, button6, button7, button8, button9, button0;
    public View allCleanButton, delButton, divisionButton, changeSignButton, multiplicationButton,
            minusButton, plusButton, equalsButton, dotButton;

    private Context context;
    public WidgetHolder(Context context) {
        this.context = context;
    }
    public WidgetHolder() {

    }

    // находим элементы
    public void findTextView() {
        //   TextView textView = ((Activity) context).findViewById(R.id.textView);
        // Дальнейшая работа с textView
        TextView HistoryDisplay = ((Activity) context).findViewById(R.id.history);
        TextView MainDisplay = ((Activity) context).findViewById(R.id.result);
        Button allCleanButton = ((Activity) context).findViewById(R.id.buttonAC);
        Button delButton = ((Activity) context).findViewById(R.id.buttonDel);
        Button changeSignButton = ((Activity) context).findViewById(R.id.buttonSign);
        Button divisionButton = ((Activity) context).findViewById(R.id.buttonDivision);
        Button multiplicationButton = ((Activity) context).findViewById(R.id.buttonMultiplication);
        Button minusButton = ((Activity) context).findViewById(R.id.buttonMinus);
        Button plusButton = ((Activity) context).findViewById(R.id.buttonPlus);
        Button equalsButton = ((Activity) context).findViewById(R.id.buttonEquals);
        Button dotButton = ((Activity) context).findViewById(R.id.buttonDot);
        Button button1 = ((Activity) context).findViewById(R.id.button1);
        Button button2 = ((Activity) context).findViewById(R.id.button2);
        Button button3 = ((Activity) context).findViewById(R.id.button3);
        Button button4 = ((Activity) context).findViewById(R.id.button4);
        Button button5 = ((Activity) context).findViewById(R.id.button5);
        Button button6 = ((Activity) context).findViewById(R.id.button6);
        Button button7 = ((Activity) context).findViewById(R.id.button7);
        Button button8 = ((Activity) context).findViewById(R.id.button8);
        Button button9 = ((Activity) context).findViewById(R.id.button9);
        Button button0 = ((Activity) context).findViewById(R.id.button0);


    }


}
