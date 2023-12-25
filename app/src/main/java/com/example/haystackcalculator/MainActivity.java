package com.example.haystackcalculator;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.text.DecimalFormat;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {
    private String number1 = "", number2 = "", result = "";
    private double numberDouble1, numberDouble2, resultDouble, was;
    private boolean isPressDivision, isPressMultiplication, isPressMinus, isPressPlus, isPressEquals;
    private String textMainDisplay = "0";
    private WidgetHolder widgetHolder;
    //private ButtonsHashMap buttonsHashMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);

        widgetHolder = new WidgetHolder(this);
        setMainDisplay();
        setHistoryDisplayTextView();

        // Для поворота экрана
        if (savedInstanceState != null) {
            textMainDisplay = savedInstanceState.getString(Strings.KEY_COUNT1);
            result = savedInstanceState.getString(Strings.KEY_COUNT2);
            isPressPlus = savedInstanceState.getBoolean(Strings.KEY_COUNT3);
            setMainDisplay();
        } else {
            textMainDisplay = "0";
            setMainDisplay();
        }

        widgetHolder.getAllCleanButton().setOnClickListener(v -> {
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
            }
            setHistoryDisplayTextView();
        });

        widgetHolder.getDelButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!textMainDisplay.equals("0") & textMainDisplay.length() > 0) {
                    widgetHolder.getMainDisplayTextView().setText(textMainDisplay = textMainDisplay.substring(0, textMainDisplay.length() - 1));
                }
                if (textMainDisplay.equals("")) {
                    widgetHolder.getMainDisplayTextView().setText(textMainDisplay = "0");
                }
                setHistoryDisplayTextView();
            }
        });
        widgetHolder.getChangeSignButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                char s = '-';
                int a;
                if (textMainDisplay.equals("0")) {
                }
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
        });
//        widgetHolder.getDivisionButton().setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                setFirstNumberWhenEmpty();
//                setSecondNumberWhenEmpty();
//
//                // условие 6/2/ → 3/ → 3 3 3 3 3
//                // основное вычисление
//                if (!number2.equals("") & !number2.equals("0")) {
//
//                   resultDouble = convertStringToDouble(number1) / convertStringToDouble(number2);
//               //     getResultCalculation(v);
//                    convertDoubleToString();
//                    formatFloatPoint();
//                    setResultMainDisplay();
//                }
//                if (number2.equals("0")) {
//                    widgetHolder.getMainDisplayTextView().setText("ОШИБКА");
//                }
//                textMainDisplay = ""; //после вывода результата,новое число выводится на новом экране
//
//                // условие - результат вычисляется с новым введённым числом
//                if (!result.equals("")) {
//                    number1 = result;
//                    number2 = textMainDisplay;
//                }
//                isPressDivision = true;
//
//                setHistoryDisplayTextView();
//            }
//        });
//
//        widgetHolder.getMultiplicationButton().setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                setFirstNumberWhenEmpty();
//                setSecondNumberWhenEmpty();
//
//                // условие 1*2* → 2* → 2 2 2 2 2
//                if (isPressMultiplication & number1.equals(number2)) {
//                    number1 = "";
//                    textMainDisplay = result;
//                }
//
//                // основное вычисление
//                if (!number1.equals("") & !number2.equals("")) {
//                    convertStringToDouble(number1);
//                    convertStringToDouble(number2);
//                    getResultCalculation(v);
//                    formatFloatPoint();
//                    setResultMainDisplay();
//                }
//
//                // условие 1*2= → 2*3 = → 6 = → 18
//                if (isPressEquals & isPressMultiplication & !result.equals("")) {
//                    number1 = textMainDisplay;
//                }
//
//                // условие 2*3* → 6 = → 36 → 216
//                if (isPressPlus & !number2.equals("")) {
//                    number2 = result;
//                }
//
//
//                // условие - результат вычисляется с новым введённым числом
//                if (!result.equals("")) {
//                    number1 = result;
//                    number2 = textMainDisplay;
//                }
//
//                isPressMultiplication = true;
//                textMainDisplay = ""; //после вывода результата,новое число выводится на новом экране
//                setHistoryDisplayTextView();
//            }
//        });
//
//        widgetHolder.getMinusButton().setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                setFirstNumberWhenEmpty();
//                setSecondNumberWhenEmpty();
//
//                // условие 5-2- → 3 → 3 3 3 3 3
//                if (isPressPlus & number1.equals(number2)) {
//                    number1 = "";
//                    textMainDisplay = result;
//                }
//                // основное вычисление
//                if (!number2.equals("")) {
//                    convertStringToDouble(number1);
//                    convertStringToDouble(number2);
//                   // resultDouble = numberDouble1 - numberDouble2;
//                    getResultCalculation(v);
//                    convertDoubleToString();
//                    formatFloatPoint();
//                    setResultMainDisplay();
//                }
//                textMainDisplay = ""; //после вывода результата,новое число выводится на новом экране
//
//                // условие 5-2= → 3-2 = → 1 = → -1 → -3
//                if (isPressEquals & isPressMinus & !result.equals("")) {
//                    number1 = textMainDisplay;
//                }
//                // условие 1+2+ → 3 = → 6 → 9 Вроде не нужно, но плюс без него не работает
//                if (isPressMinus & !number2.equals("")) {
//                    number2 = result;
//                }
//
//                // условие - результат вычисляется с новым введённым числом ЗАЧЕМ????
//                if (!result.equals("")) {
//                    number1 = result;
//                    number2 = textMainDisplay;
//                }
//                isPressMinus = true;
//                setHistoryDisplayTextView();
//            }
//        });

        widgetHolder.getPlusButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setFirstNumberWhenEmpty();
                setSecondNumberWhenEmpty();

                // условие 1+2+ → 3+ → 3 3 3 3 3
                if (isPressPlus & number1.equals(number2)) {
                    number1 = "";
                    textMainDisplay = result;
                }
                /* if (!number1.equals("")& !number2.equals("")) {
                    convertStringToDouble(number1);
                    convertStringToDouble(number2);
                    resultDouble = numberDouble1 + numberDouble2;
                    System.out.println("v = " + resultDouble+"ttt");
                } */


                // основное сложение
                if (!number1.equals("") & !number2.equals("")) {
                    /*convertStringToDouble(number1);
                    convertStringToDouble(number2);*/
                    convertStringToDouble();
                    double d = getResultCalculation(v);

                    //   resultDouble = numberDouble1 + numberDouble2;

                    convertDoubleToString();
                    formatFloatPoint();
                    setResultMainDisplay();
                }

                // условие 1+2= → 3+4 = → 7 = → 11
                if (isPressEquals & isPressPlus & !result.equals("")) {
                    number1 = textMainDisplay;
                }

                // условие 1+2+ → 3 = → 6 → 9
                if (isPressPlus & !number2.equals("")) {
                    number2 = result;
                }

                // Пробел после нажатого знака вычисления
                if (isPressPlus & !result.equals("")) {
                    textMainDisplay = "";
                }

                //   textMainDisplay = ""; //после вывода результата,новое число выводится на новом экране

                isPressPlus = true;
                setHistoryDisplayTextView();
            }

        });

        widgetHolder.getEqualsButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // условие 1+2= → 3 = → 5 = → 7 (ОСНОВНОЕ СУММИРОВАНИЕ)
                // условие - запись в первое Число если оно пусто
                //& !result.equals(number1) можно убрать???
                if (!number2.equals("") & !result.equals(number1)) {
                  /*  textMainDisplay = result; // дописал
                    number1 = textMainDisplay;*/
                    number1 = result;
                }
               /* if (!number2.equals("") & !result.equals(number1)) {
                    number2 = textMainDisplay;
                }*/

                // условие 1+2= → 3+4 = → 7 = → 11
                if (isPressPlus & !result.equals("") & number2.equals(result)) {
                    number1 = number2;
                    number2 = textMainDisplay;
                }
                // условие 1+2= → 3+4 = → 7 = → 11
                if (isPressMultiplication & !result.equals("") & number2.equals(result)) {
                    number1 = number2;
                    number2 = textMainDisplay;
                }

                setSecondNumberWhenEmpty();

                // условие 1+ = → 2 = → 3 = → 4
                if (!number1.equals("") & number2.equals("")) {
                    number2 = number1;
                }

                textMainDisplay = ""; //после вывода равенства,новое число выводится на новом экране
/*
                // условие начальное нажатие =
                if (isPressDivision & !number1.equals("") & !number2.equals("")) {
                    convertStringToDouble();
                    resultDouble = numberDouble1 / numberDouble2;
                    conversionNumberDoubleToSting();
                    formatFloatPoint();
                    setResultMainDisplay();
                }

                if (isPressMultiplication & !number1.equals("") & !number2.equals("")) {
                    convertStringToDouble();
                    resultDouble = numberDouble1 * numberDouble2;
                    conversionNumberDoubleToSting();
                    formatFloatPoint();
                    setResultMainDisplay();
                }
                if (isPressMinus & !number1.equals("") & !number2.equals("")) {
                    convertStringToDouble();
                    resultDouble = numberDouble1 - numberDouble2;
                    conversionNumberDoubleToSting();
                    formatFloatPoint();
                    setResultMainDisplay();
                }*/
                if (isPressPlus & !number1.equals("") & !number2.equals("")) {
                    convertStringToDouble();
                    //getResultCalculation(v);
                    resultDouble = numberDouble1 + numberDouble2;
                    convertDoubleToString();
                    formatFloatPoint();
                    setResultMainDisplay();
                }
                // Пробел после нажатого знака вычисления
                if (isPressEquals & !result.equals("")) {
                    textMainDisplay = "";
                }

                isPressEquals = true;
                setHistoryDisplayTextView();
            }
        });

        widgetHolder.getDotButton().setOnClickListener(v -> {
            textMainDisplay = textMainDisplay + ".";
            setMainDisplay();
            setHistoryDisplayTextView();
        });


        for (Button button : widgetHolder.getButtons()) {
            button.setOnClickListener(
                    new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (textMainDisplay.equals("0")) {
                                textMainDisplay = "";
                            }
                            textMainDisplay += ButtonsHashMap.buttonIdsMap.get(v.getId());
                            setMainDisplay();
                            setHistoryDisplayTextView();
                        }
                    });
        }
    }

    private void convertStringToDouble() {
        numberDouble1 = Double.parseDouble(number1);
        numberDouble2 = Double.parseDouble(number2);
    }
   /* public double convertStringToDouble(String num) {
        return Double.parseDouble(num);
    }*/


    private void convertDoubleToString() {
        result = String.valueOf(resultDouble);
    }

    private void setResultMainDisplay() {
        textMainDisplay = result;
        setMainDisplay();
    }

    private void setFirstNumberWhenEmpty() {
        // условие - запись в первое число если оно пусто
        if (number1.equals("")) {
            number1 = textMainDisplay;
            textMainDisplay = "";
        }
    }

    private void setSecondNumberWhenEmpty() {
        // условие - запись во второе число если оно пусто
        if (number2.equals("")) {
            number2 = textMainDisplay;
        }
    }

    private void setMainDisplay() {
        widgetHolder.getMainDisplayTextView().setText(textMainDisplay);
    }


    @SuppressLint("NonConstantResourceId")
    public Double getResultCalculation(View view) {
        double vb = 0;
        switch (view.getId()) {
            case R.id.buttonDivision:
                vb = numberDouble1 / numberDouble2;
                break;
            case R.id.buttonMultiplication:
                resultDouble = numberDouble1 * numberDouble2;
                break;

            case R.id.buttonMinus:
                resultDouble = numberDouble1 - numberDouble2;
                break;

            case R.id.buttonPlus:
                resultDouble = numberDouble1 + numberDouble2;
                break;
        }

        return vb;
    }


    // Для поворота экрана
    @Override
    protected final void onSaveInstanceState(@NonNull Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putString(Strings.KEY_COUNT1, textMainDisplay);
        savedInstanceState.putString(Strings.KEY_COUNT2, result);
        savedInstanceState.putBoolean(Strings.KEY_COUNT3, isPressPlus);
    }

    private void setHistoryDisplayTextView() {
        widgetHolder.getHistoryDisplayTextView().setText("number1=" + number1 + " number2=" +
                number2 + " result=" + result + " resultDouble=" +
                resultDouble + " numberDouble1=" + numberDouble1 +
                " numberDouble2=" + numberDouble2 + " textMainDisplay=" + textMainDisplay);
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

    LastOperation lastPressOperation = LastOperation.PRESSPLUS;

    public void lastPressOperation(LastOperation lastOperation) {
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
            default:
        }
    }

}

