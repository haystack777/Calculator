package com.example.haystackcalculator.MainActivity;

import android.annotation.SuppressLint;
import android.view.View;

import com.example.haystackcalculator.Calculator;
import com.example.haystackcalculator.Operation;

import java.text.DecimalFormat;

public class LogicHolder {
    private String textMainDisplay = "0";
    public String var1 = "", result1 = "";
    public String number1 = "", number2 = "", result = "";
    public double numberDouble1, numberDouble2, resultDouble, was;
    protected boolean isPressDivision, isPressMultiplication, isPressMinus, isPressPlus, isPressEquals;
    public Operation operation = Operation.NONE;
    public WidgetHolder widgetHolder;

    public LogicHolder(WidgetHolder widgetHolder) {
        this.widgetHolder = widgetHolder;
    }

    public Calculator calculator = new Calculator();


    public void choosePressedId(View v) {
        if (textMainDisplay.equals("0")) {
            textMainDisplay = "";
        }
        textMainDisplay += ButtonsHashMap.buttonIdsMap.get(v.getId());
        setMainDisplay();
    }

    public void clear() {

        number1 = "";
        number2 = "";
        if (!textMainDisplay.equals("0")) {
            widgetHolder.getMainDisplayTextView().setText(textMainDisplay = "0");
            number1 = "";
            number2 = "";
            result = "";
            numberDouble1 = 0;
            numberDouble2 = 0;
            resultDouble = 0;
            was = 0;
        }
        setHistoryDisplayTextView();
    }

    public void delete() {
        if (!textMainDisplay.equals("0") & textMainDisplay.length() > 0) {
            widgetHolder.getMainDisplayTextView().setText(textMainDisplay = textMainDisplay.substring(0, textMainDisplay.length() - 1));
        }
        if (textMainDisplay.equals("")) {
            widgetHolder.getMainDisplayTextView().setText(textMainDisplay = "0");
        }
        setHistoryDisplayTextView();
    }

    public void changeSign() {
        char s = '-';
        int a;
        if (!textMainDisplay.contains(String.valueOf(s)) & !textMainDisplay.equals("0")) {
            textMainDisplay = "-" + textMainDisplay;
            setMainDisplay();
        } else {
            a = Integer.parseInt(textMainDisplay);
            a = a * (-1);
            textMainDisplay = String.valueOf(a);
            setMainDisplay();
        }
        if (textMainDisplay.contains(String.valueOf(s)) & !result.equals("")) {
            result = "-" + result;
        } else if (!textMainDisplay.contains(String.valueOf(s)) & result.contains(String.valueOf(s))) {
            result = result.substring(1);
            resultDouble -= resultDouble;

        }
        setHistoryDisplayTextView();
    }

    public void divide(View ignoredV) {
        getNumbers();

        // условие 6/2/ → 3/ → 3 3 3 3 3
        // основное вычисление
        if (!number2.equals("") & !number2.equals("0")) {
            shet(Operation.SUBTRACTION);
        }

        if (number2.equals("0")) {
            widgetHolder.getMainDisplayTextView().setText(Strings.error);
        }

        // условие - результат вычисляется с новым введённым числом
        if (!result.equals("")) {
            number1 = result;
            number2 = textMainDisplay;
        }

        if (isPressDivision & !number1.equals("") & !number2.equals("") &
                !number1.equals(result) & textMainDisplay.equals("")) {
            shet(Operation.DIVISION);
        }
        if (was == 1 & !number1.equals("") & !number2.equals("")) {
            textMainDisplay = "0";
            shet(Operation.SUMMATION);
        }
        if (was == 2 & !number1.equals("") & !number2.equals("")) {
            shet(Operation.SUBTRACTION);
        }
        if (was == 3 & !number1.equals("") & !number2.equals("")) {
            shet(Operation.MULTIPLICATION);
        }
        // условие 1+2= → 3+4 = → 7 = → 11
        if (/*isPressPlus & */!result.equals(number2)) {
            number2 = textMainDisplay;
        }

        textMainDisplay = ""; //после вывода результата,новое число выводится на новом экране
        isPressDivision = true;
        lastPressOperation(Operation.PRESSDEVISION);
        setHistoryDisplayTextView();
    }

    public void multiply(View ignoredV) {
        getNumbers();
        /* 5-4=1 +6 =7 но + не получ. 7,а 6 6 6*/

        // основное сложение
        // условие 1+2+ → 3+ → 3 3 3 3 3
        // условие 1+2+ → 3 = → 6 → 9

        if (isPressMultiplication & !number1.equals("") & !number2.equals("") &
                !number1.equals(result) & textMainDisplay.equals("")) {
            shet(Operation.MULTIPLICATION);
        }
        if (was == 1 & !number1.equals("") & !number2.equals("")) {
            textMainDisplay = "0";
            shet(Operation.SUMMATION);
        }
        if (was == 2 & !number1.equals("") & !number2.equals("")) {
            shet(Operation.SUBTRACTION);
        }
        if (was == 4 & !number1.equals("") & !number2.equals("")) {
            shet(Operation.DIVISION);
        }

        // условие 1+2= → 3+4 = → 7 = → 11
        if (/*isPressPlus & */!result.equals(number2)) {
            number2 = textMainDisplay;
        }
        textMainDisplay = ""; //после вывода результата,новое число выводится на новом экране
        isPressMultiplication = true;
        lastPressOperation(Operation.PRESSMULTIPLICATION);
        setHistoryDisplayTextView();
    }


    public void subtract(View ignoredV) {
        getNumbers();
        /* 5-4=1 +6 =7 но + не получ. 7,а 6 6 6*/

        // основное сложение
        // условие 1+2+ → 3+ → 3 3 3 3 3
        // условие 1+2+ → 3 = → 6 → 9

        if (isPressMinus & !number1.equals("") & !number2.equals("") &
                !number1.equals(result) & textMainDisplay.equals("")) {
            shet(Operation.SUBTRACTION);
        }
        if (was == 1 & !number1.equals("") & !number2.equals("")) {
            textMainDisplay = "0";
            shet(Operation.SUMMATION);
        }
        if (was == 3 & !number1.equals("") & !number2.equals("")) {
            shet(Operation.DIVISION);
        }
        if (was == 4 & !number1.equals("") & !number2.equals("")) {
            shet(Operation.MULTIPLICATION);
        }
        // условие 1+2= → 3+4 = → 7 = → 11
        if (/*isPressPlus & */!result.equals(number2)) {
            number2 = textMainDisplay;
        }
        textMainDisplay = ""; //после вывода результата,новое число выводится на новом экране
        isPressMinus = true;
        lastPressOperation(Operation.PRESSMINUS);
        setHistoryDisplayTextView();
    }

    public void summarize(View ignoredV) {
        getNumbers();
        /*   5-4=1 +6 =7 но + не получ. 7,а 6 6 6*/

        // основное сложение
        // условие 1+2+ → 3+ → 3 3 3 3 3
        // условие 1+2+ → 3 = → 6 → 9

        if (isPressPlus & !number1.equals("") & !number2.equals("") &
                !number1.equals(result) & !textMainDisplay.equals("")) {
            shet(Operation.SUMMATION);
        }
        if (was == 2 & !number1.equals("") & !number2.equals("")) {
            textMainDisplay = "0";
            shet(Operation.SUBTRACTION);
        }
        if (was == 3 & !number1.equals("") & !number2.equals("")) {
            shet(Operation.DIVISION);
        }
        if (was == 4 & !number1.equals("") & !number2.equals("")) {
            shet(Operation.MULTIPLICATION);
        }
        // условие 1+2= → 3+4 = → 7 = → 11
        if (isPressPlus & !result.equals(number2)) {
            number2 = textMainDisplay;
        }
        textMainDisplay = ""; //после вывода результата,новое число выводится на новом экране
        isPressPlus = true;
        // operation = isPressPlus (Operation.SUMMATION);
        lastPressOperation(Operation.PRESSPLUS);
        setHistoryDisplayTextView();
    }

 /*   public void summarize(View ignoredV) {
        getNumbers();

        if (isPressPlus && checkNumbers(number1, number2) && !number1.equals(result) && !textMainDisplay.isEmpty()) {
            shet(Operation.SUMMATION);
        }

        if (was == 2 && checkNumbers(number1, number2)) {
            textMainDisplay = "0";
            shet(Operation.SUBTRACTION);
        }

        if (was == 4 && checkNumbers(number1, number2)) {
            shet(Operation.MULTIPLICATION);
        }

        if (!result.equals(number2)) {
            number2 = textMainDisplay;
        }

        textMainDisplay = "";
        isPressPlus = true;
        lastPressOperation(Operation.PRESSPLUS);
        setHistoryDisplayTextView();
    }

    private boolean checkNumbers(String number1, String number2) {
        return !number1.isEmpty() && !number2.isEmpty();
    }*/


    public void equal() {
        // условие - запись во второе Число если оно пусто
        if (number2.equals("")) {
            number2 = textMainDisplay;
        }
////////
        // условие 1+2+ → 3 = → 6 → 9
        if (/*isPressPlus & */!result.equals("") & number2.equals(result)) {
            number1 = number2;
            number2 = textMainDisplay;
        }
        // условие 1+ = → 2 = → 3 = → 4
        // условие 1+2=3
        if (!number1.equals("") & number2.equals("")) {
            number2 = number1;
        }
/////////
        if (was == 1 & !number1.equals("") & !number2.equals("")) {
            numberDouble1 = convertStringToDouble(number1);
            numberDouble2 = convertStringToDouble(number2);
            double x2 = calculator.calculate(Operation.SUMMATION, numberDouble1, numberDouble2);
            setResultMainDisplay(x2);
        }
        if (was == 2 & !number1.equals("") & !number2.equals("")) {
            numberDouble1 = convertStringToDouble(number1);
            numberDouble2 = convertStringToDouble(number2);
            double x2 = calculator.calculate(Operation.SUBTRACTION, numberDouble1, numberDouble2);
            setResultMainDisplay(x2);
        }
        if (was == 3 & !number1.equals("") & !number2.equals("")) {
            numberDouble1 = convertStringToDouble(number1);
            numberDouble2 = convertStringToDouble(number2);
            double x2 = calculator.calculate(Operation.MULTIPLICATION, numberDouble1, numberDouble2);
            setResultMainDisplay(x2);
        }
        if (was == 4 & !number1.equals("") & !number2.equals("")) {
            numberDouble1 = convertStringToDouble(number1);
            numberDouble2 = convertStringToDouble(number2);
            double x2 = calculator.calculate(Operation.DIVISION, numberDouble1, numberDouble2);
            setResultMainDisplay(x2);
        }
        //Должно стоять здесь
        // условие 1+2= → 3 = → 5 = → 7
        if (!number2.equals("")) {
            number1 = result;
        }
        textMainDisplay = ""; //после вывода равенства,новое число выводится на новом экране
        isPressEquals = true;
        lastPressOperation(Operation.PRESSEQUALS);
        setHistoryDisplayTextView();
    }


    public void putDot() {
        textMainDisplay = textMainDisplay + ".";
        setMainDisplay();
    }

    public void lastPressOperation(Operation lastOperation) {
        switch (lastOperation) {
            case PRESSPLUS:
                was = 1;
                break;
            case PRESSMINUS:
                was = 2;
                break;
            case PRESSMULTIPLICATION:
                was = 3;
                break;
            case PRESSDEVISION:
                was = 4;
                break;
            /*case PRESSEQUALS:
                was = 5;
                break;*/
            default:
        }
    }


    private void getNumbers() {
        // условие - запись в первое число если оно пусто
        if (number1.equals("")) {
            number1 = textMainDisplay;
            textMainDisplay = "";
        }
        // условие - запись во второе число если оно пусто
        if (number2.equals("")) {
            number2 = textMainDisplay;
        }
    }

    private void getSecondNumber() {
        // условие - запись во второе число если оно пусто
        if (number2.equals("")) {
            number2 = textMainDisplay;
        }
    }

    /*private void convertStringToDouble1() {
        numberDouble1 = Double.parseDouble(number1);
        numberDouble2 = Double.parseDouble(number2);
    }*/

    public double convertStringToDouble(String num) {
        return Double.parseDouble(num);
    }


    private void setResultMainDisplay(double x) {

        result = String.valueOf(x);//convertDoubleToString

        // Форматироание плавающей точки
        if (x == (int) x) {
            DecimalFormat dF = new DecimalFormat("#.#");
            result = dF.format(x);
            System.out.printf("целое" + result);

        } else {
            DecimalFormat dF = new DecimalFormat("#.##########");
            result = dF.format(x);
        }

        textMainDisplay = result;
        setMainDisplay();
    }


    protected void setMainDisplay() {
        widgetHolder.getMainDisplayTextView().setText(textMainDisplay);
    }

    private String getNumber1() {
        return number1;
    }

    @SuppressLint("SetTextI18n")
    public void setHistoryDisplayTextView() {
        widgetHolder.getHistoryDisplayTextView().setText(/*"result1=" + result1 + " var1=" + var1 +
         */ "was=" + was + " number1=" + number1 + " number2=" +
                number2 + " result=" + result + " resultDouble=" +
                resultDouble + " numberDouble1=" + numberDouble1 +
                " numberDouble2=" + numberDouble2 + " textMainDisplay=" + textMainDisplay);
    }


    private void convertDoubleToString() {
        result = String.valueOf(resultDouble);
    }

    private void formatFloatPoint() {
        // Форматироание плавающей точки
        if (resultDouble == (int) resultDouble) {
            DecimalFormat dF = new DecimalFormat("#.#");
            result = dF.format(resultDouble);
            System.out.printf("целое" + result);

        } else {
            DecimalFormat dF = new DecimalFormat("#.##########");
            result = dF.format(resultDouble);
        }
    }

    private void shet(Operation x) {
        numberDouble1 = convertStringToDouble(number1);
        numberDouble2 = convertStringToDouble(number2);
        double x2 = calculator.calculate(x, numberDouble1, numberDouble2);
        setResultMainDisplay(x2);
    }


}

