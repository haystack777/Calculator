package com.example.haystackcalculator;

import android.annotation.SuppressLint;
import android.view.View;

import java.text.DecimalFormat;

public class LogicHolder {
    protected double numberDouble1, numberDouble2, resultDouble, was;
    protected boolean isPressDivision, isPressMultiplication, isPressMinus, isPressPlus, isPressEquals;
    protected WidgetHolder widgetHolder;

    public void choosePressedId(View v) {
        if (Strings.textMainDisplay.equals("0")) {
            Strings.textMainDisplay = "";
        }
        Strings.textMainDisplay += ButtonsHashMap.buttonIdsMap.get(v.getId());
        setMainDisplay();
    }

    public void cleaning() {
        Strings.number1 = "";
        Strings.number2 = "";
        if (!Strings.textMainDisplay.equals("0")) {
            widgetHolder.getMainDisplayTextView().setText(Strings.textMainDisplay = "0");
            Strings.number1 = "";
            Strings.number2 = "";
            Strings.result = "";
            numberDouble1 = 0;
            numberDouble2 = 0;
            resultDouble = 0;
        }
    }

    public void deletion() {
        if (!Strings.textMainDisplay.equals("0") & Strings.textMainDisplay.length() > 0) {
            widgetHolder.getMainDisplayTextView().setText(Strings.textMainDisplay = Strings.textMainDisplay.substring(0, Strings.textMainDisplay.length() - 1));
        }
        if (Strings.textMainDisplay.equals("")) {
            widgetHolder.getMainDisplayTextView().setText(Strings.textMainDisplay = "0");
        }
    }

    public void changeSign() {
        char s = '-';
        int a;
        if (!Strings.textMainDisplay.contains(String.valueOf(s)) & !Strings.textMainDisplay.equals("0")) {
            Strings.textMainDisplay = "-" + Strings.textMainDisplay;
            setMainDisplay();
        } else {
            a = Integer.parseInt(Strings.textMainDisplay);
            a = a * (-1);
            Strings.textMainDisplay = String.valueOf(a);
            setMainDisplay();
        }
        if (Strings.textMainDisplay.contains(String.valueOf(s)) & !Strings.result.equals("")) {
            Strings.result = "-" + Strings.result;
        } else if (!Strings.textMainDisplay.contains(String.valueOf(s)) & Strings.result.contains(String.valueOf(s))) {
            Strings.result = Strings.result.substring(1);
            resultDouble -= resultDouble;

        }
    }

    public void division(View v) {
        getNumbers();

        // условие 6/2/ → 3/ → 3 3 3 3 3
        // основное вычисление
        if (!Strings.number2.equals("") & !Strings.number2.equals("0")) {

            resultDouble = convertStringToDouble(Strings.number1) / convertStringToDouble(Strings.number2);
            //     getResultCalculation(v);
            convertDoubleToString();
            formatFloatPoint();
            double x2 = getResultCalculation(v);
            setResultMainDisplay(x2);
        }
        if (Strings.number2.equals("0")) {
            widgetHolder.getMainDisplayTextView().setText("ОШИБКА");
        }
        Strings.textMainDisplay = ""; //после вывода результата,новое число выводится на новом экране

        // условие - результат вычисляется с новым введённым числом
        if (!Strings.result.equals("")) {
            Strings.number1 = Strings.result;
            Strings.number2 = Strings.textMainDisplay;
        }
        isPressDivision = true;

    }

    public void multiplication(View v) {
        getNumbers();

        // условие 1*2* → 2* → 2 2 2 2 2
        if (isPressMultiplication & Strings.number1.equals(Strings.number2)) {
            Strings.number1 = "";
            Strings.textMainDisplay = Strings.result;
        }

        // основное вычисление
        if (!Strings.number1.equals("") & !Strings.number2.equals("")) {
            numberDouble1 = convertStringToDouble(Strings.number1);
            numberDouble2 = convertStringToDouble(Strings.number2);
            double x2 = getResultCalculation(v);
            setResultMainDisplay(x2);
        }

        // условие 1*2= → 2*3 = → 6 = → 18
        if (isPressEquals & isPressMultiplication & !Strings.result.equals("")) {
            Strings.number1 = Strings.textMainDisplay;
        }

        // условие 2*3* → 6 = → 36 → 216
        if (isPressPlus & !Strings.number2.equals("")) {
            Strings.number2 = Strings.result;
        }


        // условие - результат вычисляется с новым введённым числом
        if (!Strings.result.equals("")) {
            Strings.number1 = Strings.result;
            Strings.number2 = Strings.textMainDisplay;
        }

        isPressMultiplication = true;
        Strings.textMainDisplay = ""; //после вывода результата,новое число выводится на новом экране

    }

    public void subtraction(View v) {
        getNumbers();
        // условие 5-2- → 3 → 3 3 3 3 3
        if (isPressPlus & Strings.number1.equals(Strings.number2)) {
            Strings.number1 = "";
            Strings.textMainDisplay = Strings.result;
        }
        // основное вычисление
        if (!Strings.number2.equals("")) {
            numberDouble1 = convertStringToDouble(Strings.number1);
            numberDouble2 = convertStringToDouble(Strings.number2);

            double x2 = getResultCalculation(v);
            setResultMainDisplay(x2);
        }
        Strings.textMainDisplay = ""; //после вывода результата,новое число выводится на новом экране

        // условие 5-2= → 3-2 = → 1 = → -1 → -3
        if (isPressEquals & isPressMinus & !Strings.result.equals("")) {
            Strings.number1 = Strings.textMainDisplay;
        }

        // условие 1+2+ → 3 = → 6 → 9 Вроде не нужно, но плюс без него не работает
        if (isPressMinus & !Strings.number2.equals("")) {
            Strings.number2 = Strings.result;
        }

        // условие - результат вычисляется с новым введённым числом ЗАЧЕМ????
        if (!Strings.result.equals("")) {
            Strings.number1 = Strings.result;
            Strings.number2 = Strings.textMainDisplay;
        }

        isPressMinus = true;

    }

    public void summarize(View v) {
        getNumbers();
        // основное сложение
        if (!Strings.number1.equals("") & !Strings.number2.equals("")) {
            numberDouble1 = convertStringToDouble(Strings.number1);
            numberDouble2 = convertStringToDouble(Strings.number2);

            //double x2 = getResultCalculation(v);
            setResultMainDisplay(getResultCalculation(v));
        }

         /*   // условие 1+2+ → 3+ → 3 3 3 3 3
            if (isPressPlus & Strings.number1.equals(Strings.number2)) {
                Strings.number1 = "";
                Strings.textMainDisplay = Strings.result;
            }

            // условие 1+2= → 3+4 = → 7 = → 11
            if (isPressEquals & isPressPlus & !Strings.result.equals("")) {
                Strings.number1 = Strings.textMainDisplay;
            }

            // условие 1+2+ → 3 = → 6 → 9
            if (isPressPlus & !Strings.number2.equals("")) {
                Strings.number2 = Strings.result;
            }

            // Пробел после нажатого знака вычисления
            if (isPressPlus & !Strings.result.equals("")) {
                Strings.textMainDisplay = "";
            }

            //   Strings.textMainDisplay = ""; //после вывода результата,новое число выводится на новом экране
*/
        isPressPlus = true;

    }

    public void calculate() {
        //          getNumbers();// add
        if (isPressPlus & !Strings.number1.equals("") & !Strings.number2.equals("")) {
            numberDouble1 = convertStringToDouble(Strings.number1);
            numberDouble2 = convertStringToDouble(Strings.number2);
            resultDouble = numberDouble1 + numberDouble2;
            setResultMainDisplay(resultDouble);
        }


        // условие 1+2= → 3 = → 5 = → 7 (ОСНОВНОЕ СУММИРОВАНИЕ)
        // условие - запись в первое Число если оно пусто
         /*   //& !Strings.result.equals(Strings.number1) можно убрать???
            if (!Strings.number2.equals("") & !Strings.result.equals(Strings.number1)) {
              *//*  Strings.textMainDisplay = Strings.result; // дописал
                Strings.number1 = Strings.textMainDisplay;*//*
                Strings.number1 = Strings.result;
            }*/
           /* if (!Strings.number2.equals("") & !Strings.result.equals(Strings.number1)) {
                Strings.number2 = Strings.textMainDisplay;
            }*/

        // условие 1+2= → 3+4 = → 7 = → 11
        if (isPressPlus & !Strings.result.equals("") & Strings.number2.equals(Strings.result)) {
            Strings.number1 = Strings.number2;
            Strings.number2 = Strings.textMainDisplay;
        }
        // условие 1*2= → 2*3 = → 6 = → 18
        if (isPressMultiplication & !Strings.result.equals("") & Strings.number2.equals(Strings.result)) {
            Strings.number1 = Strings.number2;
            Strings.number2 = Strings.textMainDisplay;
        }

        getSecondNumber();

        // условие 1+ = → 2 = → 3 = → 4
        if (!Strings.number1.equals("") & Strings.number2.equals("")) {
            Strings.number2 = Strings.number1;
        }

        Strings.textMainDisplay = ""; //после вывода равенства,новое число выводится на новом экране
/*
            // условие начальное нажатие =
            if (isPressDivision & !Strings.number1.equals("") & !Strings.number2.equals("")) {
                convertStringToDouble();
                resultDouble = numberDouble1 / numberDouble2;
                conversionNumberDoubleToSting();
                formatFloatPoint();
                setResultMainDisplay();
            }*/

//                if (isPressMultiplication & !Strings.number1.equals("") & !Strings.number2.equals("")) {
//                    numberDouble1 = convertStringToDouble(Strings.number1);
//                    numberDouble2 = convertStringToDouble(Strings.number2);
//
//                    double x2 = getResultCalculation(v);
//                    setResultMainDisplay(x2);
//                }
//                if (isPressMinus & !Strings.number1.equals("") & !Strings.number2.equals("")) {
//                    numberDouble1 = convertStringToDouble(Strings.number1);
//                    numberDouble2 = convertStringToDouble(Strings.number2);
//
//                    double x2 = getResultCalculation(v);
//                    setResultMainDisplay(x2);
//                }

        // Пробел после нажатого знака вычисления
        if (isPressEquals & !Strings.result.equals("")) {
            Strings.textMainDisplay = "";
        }
        isPressEquals = true;
    }

    public void putDot() {
        Strings.textMainDisplay = Strings.textMainDisplay + ".";
        setMainDisplay();
    }


    private void getNumbers() {
        // условие - запись в первое число если оно пусто
        if (Strings.number1.equals("")) {
            Strings.number1 = Strings.textMainDisplay;
            Strings.textMainDisplay = "";
        }
        if (Strings.number2.equals("")) {
            Strings.number2 = Strings.textMainDisplay;
        }
    }

    private void getSecondNumber() {
        // условие - запись во второе число если оно пусто
        if (Strings.number2.equals("")) {
            Strings.number2 = Strings.textMainDisplay;
        }
    }

    /*private void convertStringToDouble1() {
        numberDouble1 = Double.parseDouble(Strings.number1);
        numberDouble2 = Double.parseDouble(Strings.number2);
    }*/

    public double convertStringToDouble(String num) {
        return Double.parseDouble(num);
    }


    public Double getResultCalculation(View v) {
        switch (v.getId()) {
            case R.id.buttonDivision:
                return numberDouble1 / numberDouble2;
            case R.id.buttonMultiplication:
                return numberDouble1 * numberDouble2;
            case R.id.buttonMinus:
                return numberDouble1 - numberDouble2;
            case R.id.buttonPlus:
                return numberDouble1 + numberDouble2;
            default:
                return (double) 0;
        }
    }


    private void setResultMainDisplay(double x) {

        Strings.result = String.valueOf(x);//convertDoubleToString

        // Форматироание плавающей точки
        if (x == (int) x) {
            DecimalFormat dF = new DecimalFormat("#.#");
            Strings.result = dF.format(x);
            System.out.printf("целое" + Strings.result);

        } else {
            DecimalFormat dF = new DecimalFormat("#.##########");
            Strings.result = dF.format(x);
        }

        Strings.textMainDisplay = Strings.result;
        setMainDisplay();
    }


    protected void setMainDisplay() {
        widgetHolder.getMainDisplayTextView().setText(Strings.textMainDisplay);
    }

    private String getNumber1() {
        return Strings.number1;
    }


    private void convertDoubleToString() {
        Strings.result = String.valueOf(resultDouble);
    }

    private void formatFloatPoint() {
        // Форматироание плавающей точки
        if (resultDouble == (int) resultDouble) {
            DecimalFormat dF = new DecimalFormat("#.#");
            Strings.result = dF.format(resultDouble);
            System.out.printf("целое" + Strings.result);

        } else {
            DecimalFormat dF = new DecimalFormat("#.##########");
            Strings.result = dF.format(resultDouble);
        }
    }
}
