package com.example.haystackcalculator;

import android.graphics.Path;
import android.view.View;

public class Calculator {

    public Double calculate(Operation operation, double num1, double num2) {
        switch (operation) {
            case DIVISION:
                return num1 / num2;
            case MULTIPLICATION:
                return num1 * num2;
            case SUBTRACTION:
                return num1 - num2;
            case SUMMATION:
                return num1 + num2;
            case NONE:
            default:
                return (double) 0;
        }
    }
}


