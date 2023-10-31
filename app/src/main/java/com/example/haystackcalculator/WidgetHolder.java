package com.example.haystackcalculator;

import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

class WidgetHolder {

    private final TextView historyDisplayTextView, mainDisplayTextView;
    private final Button button1, button2, button3, button4,
            button5, button6, button7, button8, button9, button0,
            allCleanButton, delButton, divisionButton, changeSignButton, multiplicationButton,
            minusButton, plusButton, equalsButton, dotButton;

    public WidgetHolder(AppCompatActivity context) {

        // находим элементы
        historyDisplayTextView = context.findViewById(R.id.history);
        mainDisplayTextView = context.findViewById(R.id.result);
        allCleanButton = context.findViewById(R.id.buttonAC);
        delButton = context.findViewById(R.id.buttonDel);
        changeSignButton = context.findViewById(R.id.buttonSign);
        divisionButton = context.findViewById(R.id.buttonDivision);
        multiplicationButton = context.findViewById(R.id.buttonMultiplication);
        minusButton = context.findViewById(R.id.buttonMinus);
        plusButton = context.findViewById(R.id.buttonPlus);
        equalsButton = context.findViewById(R.id.buttonEquals);
        dotButton = context.findViewById(R.id.buttonDot);
        button1 = context.findViewById(R.id.button1);
        button2 = context.findViewById(R.id.button2);
        button3 = context.findViewById(R.id.button3);
        button4 = context.findViewById(R.id.button4);
        button5 = context.findViewById(R.id.button5);
        button6 = context.findViewById(R.id.button6);
        button7 = context.findViewById(R.id.button7);
        button8 = context.findViewById(R.id.button8);
        button9 = context.findViewById(R.id.button9);
        button0 = context.findViewById(R.id.button0);
    }

    public TextView getHistoryDisplayTextView() {
        return historyDisplayTextView;
    }

    public TextView getMainDisplayTextView() {
        return mainDisplayTextView;
    }

    public Button getButton1() {
        return button1;
    }

    public Button getButton2() {
        return button2;
    }

    public Button getButton3() {
        return button3;
    }

    public Button getButton4() {
        return button4;
    }

    public Button getButton5() {
        return button5;
    }

    public Button getButton6() {
        return button6;
    }

    public Button getButton7() {
        return button7;
    }

    public Button getButton8() {
        return button8;
    }

    public Button getButton9() {
        return button9;
    }

    public Button getButton0() {
        return button0;
    }

    public Button getAllCleanButton() {
        return allCleanButton;
    }

    public Button getDelButton() {
        return delButton;
    }

    public Button getChangeSignButton() {
        return changeSignButton;
    }

    public Button getDivisionButton() {
        return divisionButton;
    }

    public Button getMultiplicationButton() { return multiplicationButton;}

    public Button getMinusButton() {
        return minusButton;
    }

    public Button getPlusButton() {
        return plusButton;
    }

    public Button getEqualsButton() {
        return equalsButton;
    }

    public Button getDotButton() {
        return dotButton;
    }
}
