package com.example.haystackcalculator.MainActivity;

public class MainActivityCalculator {

    public Double calculate(MainActivityOperation mainActivityOperation, double num1, double num2) {
        switch (mainActivityOperation) {
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


