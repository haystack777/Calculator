package com.example.haystackcalculator;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;


public class MainActivity extends AppCompatActivity {
    private String number1 = "", number2 = "", result = "";
    private double numberDouble1, numberDouble2, resultDouble, was;
    private boolean isPressDivision, isPressMultiplication, isPressMinus, isPressPlus, isPressEquals;
    private String textMainDisplay = "0";
    private static final String KEY_COUNT1 = "COUNT1";// для поворота экрана
    private static final String KEY_COUNT2 = "COUNT2";
    private static final String KEY_COUNT3 = "COUNT3";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);

        WidgetHolder widgetHolder = new WidgetHolder(this);
        widgetHolder.getMainDisplayTextView().setText(textMainDisplay);
        setHistoryDisplayTextView();

        // Для поворота экрана
        if (savedInstanceState != null) {
            textMainDisplay = savedInstanceState.getString(KEY_COUNT1);
            result = savedInstanceState.getString(KEY_COUNT2);
            isPressPlus = savedInstanceState.getBoolean(KEY_COUNT3);
            widgetHolder.getMainDisplayTextView().setText(textMainDisplay);
        } else {
            textMainDisplay = "0";
            widgetHolder.getMainDisplayTextView().setText(textMainDisplay);
        }

        widgetHolder.getAllCleanButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
                    //widgetHolder.historyDisplayTextView.setText(textMainDisplay);
                }
                setHistoryDisplayTextView();
            }
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
                    widgetHolder.getMainDisplayTextView().setText(textMainDisplay);
                } else {
                    a = Integer.parseInt(textMainDisplay);
                    a = a * (-1);
                    textMainDisplay = String.valueOf(a);
                    widgetHolder.getMainDisplayTextView().setText(textMainDisplay);
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

        widgetHolder.getDivisionButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // условие - запись в первое число если оно пусто

                if (number1.equals("")) {
                    number1 = textMainDisplay;
                    textMainDisplay = "";
                }
                // условие - запись во второе число если оно пусто
                if (number2.equals("")) {
                    number2 = textMainDisplay;
                }

                // условие 6/2/ → 3/ → 3 3 3 3 3
                // основное вычисление
                if (!number2.equals("") & !number2.equals("0")) {
                    numberDouble1 = Double.parseDouble(number1);
                    numberDouble2 = Double.parseDouble(number2);
                    resultDouble = numberDouble1 / numberDouble2;
                    result = String.valueOf(resultDouble);
                    formatFloatPoint();
                    textMainDisplay = result;
                    widgetHolder.getMainDisplayTextView().setText(textMainDisplay);
                }
                if (number2.equals("0")) {
                    widgetHolder.getMainDisplayTextView().setText("ОШИБКА");
                }
                textMainDisplay = ""; //после вывода результата,новое число выводится на новом экране

                // условие - результат вычисляется с новым введённым числом
                if (!result.equals("")) {
                    number1 = result;
                    number2 = textMainDisplay;
                }
                isPressDivision = true;

                setHistoryDisplayTextView();
            }
        });

        widgetHolder.getMultiplicationButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // условие - запись в первое число если оно пусто
                if (number1.equals("")) {
                    number1 = textMainDisplay;
                    textMainDisplay = "";
                }
                // условие - запись во второе число если оно пусто
                if (number2.equals("")) {
                    number2 = textMainDisplay;
                }

                // условие 1*2* → 2* → 2 2 2 2 2
                if (isPressMultiplication & number1.equals(number2)) {
                    number1 = "";
                    textMainDisplay = result;
                }

                // основное вычисление
                if (!number1.equals("") & !number2.equals("")) {
                    numberDouble1 = Double.parseDouble(number1);
                    numberDouble2 = Double.parseDouble(number2);
                    resultDouble = numberDouble1 * numberDouble2;
                    formatFloatPoint();
                    textMainDisplay = result;
                    widgetHolder.getMainDisplayTextView().setText(textMainDisplay);
                }
                // условие 1*2= → 2*3 = → 6 = → 18
                if (isPressEquals & isPressMultiplication & !result.equals("")) {
                    number1 = textMainDisplay;
                }

                // условие 2*3* → 6 = → 36 → 216
                if (isPressPlus & !number2.equals("")) {
                    number2 = result;
                }

               /*
                // условие - результат вычисляется с новым введённым числом
                if (!result.equals("")) {
                    number1 = result;
                    number2 = textMainDisplay;
                }*/

                isPressMultiplication = true;
                textMainDisplay = ""; //после вывода результата,новое число выводится на новом экране
                setHistoryDisplayTextView();
            }
        });

        widgetHolder.getMinusButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // условие - запись в первое число если оно пусто
                if (number1.equals("")) {
                    number1 = textMainDisplay;
                    textMainDisplay = "";
                }
                // условие - запись во второе число если оно пусто
                if (number2.equals("")) {
                    number2 = textMainDisplay;
                }

                // условие 5-2- → 3 → 3 3 3 3 3
                if (isPressPlus & number1.equals(number2)) {
                    number1 = "";
                    textMainDisplay = result;
                }
                // основное вычисление
                if (!number2.equals("")) {
                    numberDouble1 = Double.parseDouble(number1);
                    numberDouble2 = Double.parseDouble(number2);
                    resultDouble = numberDouble1 - numberDouble2;
                    result = String.valueOf(resultDouble);
                    formatFloatPoint();
                    textMainDisplay = result;
                    widgetHolder.getMainDisplayTextView().setText(textMainDisplay);
                }
                textMainDisplay = ""; //после вывода результата,новое число выводится на новом экране

                // условие 5-2= → 3-2 = → 1 = → -1 → -3
                if (isPressEquals & isPressMinus & !result.equals("")) {
                    number1 = textMainDisplay;
                }
                // условие 1+2+ → 3 = → 6 → 9 Вроде не нужно, но плюс без него не работает
                if (isPressMinus & !number2.equals("")) {
                    number2 = result;
                }

                // условие - результат вычисляется с новым введённым числом ЗАЧЕМ????
                if (!result.equals("")) {
                    number1 = result;
                    number2 = textMainDisplay;
                }
                isPressMinus = true;
                setHistoryDisplayTextView();
            }
        });

        widgetHolder.getPlusButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // условие - запись в первое слагаемое если оно пусто
                if (number1.equals("")) {
                    number1 = textMainDisplay;
                    textMainDisplay = "";
                }
                // условие - запись во второе слагаемое если оно пусто
                if (number2.equals("")) {
                    number2 = textMainDisplay;
                }

                // условие 1+2+ → 3+ → 3 3 3 3 3
                if (isPressPlus & number1.equals(number2)) {
                    number1 = "";
                    textMainDisplay = result;
                }

                // основное сложение
                if (!number1.equals("") & !number2.equals("")) {
                    numberDouble1 = Double.parseDouble(number1);
                    numberDouble2 = Double.parseDouble(number2);
                    resultDouble = numberDouble1 + numberDouble2;
                    result = String.valueOf(resultDouble);
                    formatFloatPoint();
                    textMainDisplay = result;
                    widgetHolder.getMainDisplayTextView().setText(textMainDisplay);
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
                    number1 = textMainDisplay;
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


                // условие - запись во второе Число если оно пусто
                if (number2.equals("")) {
                    number2 = textMainDisplay;
                }

                // условие 1+ = → 2 = → 3 = → 4
                if (!number1.equals("") & number2.equals("")) {
                    number2 = number1;
                }

                textMainDisplay = ""; //после вывода равенства,новое число выводится на новом экране

                // условие начальное нажатие =
                if (isPressDivision & !number1.equals("") & !number2.equals("")) {
                    numberDouble1 = Double.parseDouble(number1);
                    numberDouble2 = Double.parseDouble(number2);
                    resultDouble = numberDouble1 / numberDouble2;
                    result = String.valueOf(resultDouble);
                    formatFloatPoint();
                    textMainDisplay = result;
                    widgetHolder.getMainDisplayTextView().setText(textMainDisplay);
                }

                if (isPressMultiplication & !number1.equals("") & !number2.equals("")) {
                    numberDouble1 = Double.parseDouble(number1);
                    numberDouble2 = Double.parseDouble(number2);
                    resultDouble = numberDouble1 * numberDouble2;
                    result = String.valueOf(resultDouble);
                    formatFloatPoint();
                    textMainDisplay = result;
                    widgetHolder.getMainDisplayTextView().setText(textMainDisplay);
                }
                if (isPressMinus & !number1.equals("") & !number2.equals("")) {
                    numberDouble1 = Double.parseDouble(number1);
                    numberDouble2 = Double.parseDouble(number2);
                    resultDouble = numberDouble1 - numberDouble2;
                    result = String.valueOf(resultDouble);
                    formatFloatPoint();
                    textMainDisplay = result;
                    widgetHolder.getMainDisplayTextView().setText(textMainDisplay);
                }
                if (isPressPlus & !number1.equals("") & !number2.equals("")) {
                    numberDouble1 = Double.parseDouble(number1);
                    numberDouble2 = Double.parseDouble(number2);
                    resultDouble = numberDouble1 + numberDouble2;
                    result = String.valueOf(resultDouble);
                    formatFloatPoint();
                    textMainDisplay = result;
                    widgetHolder.getMainDisplayTextView().setText(textMainDisplay);
                }
                // Пробел после нажатого знака вычисления
                if (isPressEquals & !result.equals("")) {
                    textMainDisplay = "";
                }

                isPressEquals = true;
                setHistoryDisplayTextView();
            }
        });

        widgetHolder.getDotButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textMainDisplay = textMainDisplay + ".";
                widgetHolder.getMainDisplayTextView().setText(textMainDisplay);
                setHistoryDisplayTextView();
            }
        });

        ArrayList<Button> buttons = new ArrayList<>();
        Button[] buttonArray = {
                widgetHolder.getButton1(),
                widgetHolder.getButton2(),
                widgetHolder.getButton3(),
                widgetHolder.getButton4(),
                widgetHolder.getButton5(),
                widgetHolder.getButton6(),
                widgetHolder.getButton7(),
                widgetHolder.getButton8(),
                widgetHolder.getButton9(),
                widgetHolder.getButton0()
        };
        Collections.addAll(buttons, buttonArray);

        for (Button button : buttons) {
            button.setOnClickListener(
                    new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (textMainDisplay.equals("0")) {
                                textMainDisplay = "";
                            }
                            int id = v.getId();

                            // Создание массива кнопок и соответствующих числовых значений
                            int[] buttonIds = {R.id.button1, R.id.button2, R.id.button3,
                                    R.id.button4, R.id.button5,
                                    R.id.button6, R.id.button7, R.id.button8,
                                    R.id.button9, R.id.button0};
                            int[] buttonValues = {1, 2, 3, 4, 5, 6, 7, 8, 9, 0};

                            // Поиск соответствующего числового значения для данного id кнопки
                            for (int i = 0; i < buttonIds.length; i++) {
                                if (id == buttonIds[i]) {
                                    textMainDisplay += buttonValues[i];
                                    break;
                                }
                            }
                            widgetHolder.getMainDisplayTextView().setText(textMainDisplay);
                            setHistoryDisplayTextView();
                        }
                    }
            );
        }

    }

    // Для поворота экрана
    @Override
    protected final void onSaveInstanceState(@NonNull Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putString(KEY_COUNT1, textMainDisplay);
        savedInstanceState.putString(KEY_COUNT2, result);
        savedInstanceState.putBoolean(KEY_COUNT3, isPressPlus);
    }


    private void setHistoryDisplayTextView() {
        WidgetHolder widgetHolder = new WidgetHolder(this);
        widgetHolder.getHistoryDisplayTextView().setText("number1=" + number1 + " number2=" +
                number2 + " result=" + result + " resultDouble=" +
                resultDouble + " numberDouble1=" + numberDouble1 +
                " numberDouble2=" + numberDouble2 + " textMainDisplay=" + textMainDisplay);
    }

    private void formatFloatPoint() {
        // Форматироание плавающей точки
        if (resultDouble == (int) resultDouble) {
                     /*   result = String.format("%.0f",resultDouble);
                        System.out.printf("целое" + result);*/
            DecimalFormat dF = new DecimalFormat("#.#");
            result = dF.format(resultDouble);
            System.out.printf("целое" + result);

        } else {
            //result = String.format("%s",resultDouble);

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

