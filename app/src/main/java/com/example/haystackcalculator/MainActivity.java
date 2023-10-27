package com.example.haystackcalculator;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import java.text.DecimalFormat;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    public String number1 = "", number2 = "", result = "";
    double numberDouble1, numberDouble2, resultDouble, was;
    boolean isPressDivision, isPressMultiplication, isPressMinus, isPressPlus, isPressEquals;
    private String textMainDisplay = "0";

    private static final String KEY_COUNT1 = "COUNT1";// для поворота экрана
    private static final String KEY_COUNT2 = "COUNT2";
    private static final String KEY_COUNT3 = "COUNT3";


    @Override
    protected final void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);
        WidgetHolder widgetHolder = new WidgetHolder(this);
        
      /*  // находим элементы
        widgetHolder.historyDisplayTextViewTextView = (TextView) findViewById(R.id.history);
        MainDisplay = (TextView) findViewById(R.id.result);

        allCleanButton = (Button) findViewById(R.id.buttonAC);
        delButton = (Button) findViewById(R.id.buttonDel);
        changeSignButton = (Button) findViewById(R.id.buttonSign);

        widgetHolder.divisionButton = (Button) findViewById(R.id.buttonDivision);
        widgetHolder.multiplicationButton = (Button) findViewById(R.id.buttonMultiplication);
        buttonMinus = (Button) findViewById(R.id.buttonMinus);
        widgetHolder.plusButton = (Button) findViewById(R.id.buttonPlus);
        widgetHolder.equalsButton = (Button) findViewById(R.id.buttonEquals);
        widgetHolder.widgetHolder.dotButton  = (Button) findViewById(R.id.buttonDot);

        button1 = (Button) findViewById(R.id.button1);
        button2 = (Button) findViewById(R.id.button2);
        button3 = (Button) findViewById(R.id.button3);
        button4 = (Button) findViewById(R.id.button4);
        button5 = (Button) findViewById(R.id.button5);
        button6 = (Button) findViewById(R.id.button6);
        button7 = (Button) findViewById(R.id.button7);
        button8 = (Button) findViewById(R.id.button8);
        button9 = (Button) findViewById(R.id.button9);
        button0 = (Button) findViewById(R.id.button0);*/

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

        widgetHolder.allCleanButton.setOnClickListener(new View.OnClickListener() {
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
       /* public void wasLastPress() {
            switch (v.getId()) {
                case R.id.buttonAC:
                    oper = "0";
                    break;
                case R.id.buttonDel:
                    oper = "0";
                    break;
                case R.id.buttonPlus:
                    oper = "+";
                    result = number1;
                    break;
                case R.id.buttonMinus:
                    oper = "-";
                    //     result = number1 - number2;
                    break;

                case R.id.button1:
                    oper = "1";
                    //      result = number1 * number2;
                    break;
                case R.id.button2:
                    oper = "2";
                    //      result = number1 / number2;
                    break;//
                default:
                    break;
            }
        }*/


        widgetHolder.delButton.setOnClickListener(new View.OnClickListener() {
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

        widgetHolder.changeSignButton.setOnClickListener(new View.OnClickListener() {
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

        widgetHolder.divisionButton.setOnClickListener(new View.OnClickListener() {
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

        widgetHolder.multiplicationButton.setOnClickListener(new View.OnClickListener() {
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

        widgetHolder.minusButton.setOnClickListener(new View.OnClickListener() {
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

        widgetHolder.plusButton.setOnClickListener(new View.OnClickListener() {
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

        widgetHolder.equalsButton.setOnClickListener(new View.OnClickListener() {
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


        widgetHolder.dotButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textMainDisplay = textMainDisplay + ".";
                widgetHolder.getMainDisplayTextView().setText(textMainDisplay);
                setHistoryDisplayTextView();
            }
        });

        widgetHolder.button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (textMainDisplay.equals("0")) {
                    textMainDisplay = "";
                }
                textMainDisplay = textMainDisplay + "1";
                widgetHolder.getMainDisplayTextView().setText(textMainDisplay);
                setHistoryDisplayTextView();
            }
        });



/*
        widgetHolder.button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (textMainDisplay.equals("0")) {
                    textMainDisplay = "";
                }
                textMainDisplay = textMainDisplay + 1;
               widgetHolder.getMainDisplayTextView().setText(textMainDisplay);
               setHistoryDisplayTextView();
            }
       {
                MainDisplay.setText(MainDisplay.getText() + "1");
            }*//*

        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (textMainDisplay.equals("0")) {
                    textMainDisplay = "";
                }
                textMainDisplay = textMainDisplay + 2;
                MainDisplay.setText(textMainDisplay);
                widgetHolder.historyDisplayTextView.setText(textMainDisplay);
                setHistoryDisplayTextView();
            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (textMainDisplay.equals("0")) {
                    textMainDisplay = "";
                }
                textMainDisplay = textMainDisplay + 3;
                MainDisplay.setText(textMainDisplay);
                widgetHolder.historyDisplayTextView.setText(textMainDisplay);

                setHistoryDisplayTextView();
            }
        });

        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Пробел после нажатого знака вычисления
                *//*if (isPressPlus & pressEquals) {
                    textMainDisplay = "";
                }*//*
                // Пробел после 0
                if (textMainDisplay.equals("0")) {
                    textMainDisplay = "";
                }

                textMainDisplay = textMainDisplay + 4;

                MainDisplay.setText(textMainDisplay);
                widgetHolder.historyDisplayTextView.setText(textMainDisplay);

                // условие 1+2= → 3+4 = → 7 = → 11
              *//*  if (!result.equals(textMainDisplay) & !isPressPlus) {
                    number1 = textMainDisplay;
                }*//*

                setHistoryDisplayTextView();
            }

        });

        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (textMainDisplay.equals("0")) {
                    textMainDisplay = "";
                }
                textMainDisplay = textMainDisplay + 5;
                MainDisplay.setText(textMainDisplay);
                HistoryDisplay.setText(textMainDisplay);
                setHistoryDisplayTextView();
            }
        });

        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (textMainDisplay.equals("0")) {
                    textMainDisplay = "";
                }
                textMainDisplay = textMainDisplay + 6;
                MainDisplay.setText(textMainDisplay);
                HistoryDisplay.setText(textMainDisplay);
                setHistoryDisplayTextView();
            }
        });

        button7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (textMainDisplay.equals("0")) {
                    textMainDisplay = "";
                }
                textMainDisplay = textMainDisplay + 7;
                MainDisplay.setText(textMainDisplay);
                HistoryDisplay.setText(textMainDisplay);
                setHistoryDisplayTextView();
            }
        });

        button8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (textMainDisplay.equals("0")) {
                    textMainDisplay = "";
                }
                textMainDisplay = textMainDisplay + 8;
                MainDisplay.setText(textMainDisplay);
                HistoryDisplay.setText(textMainDisplay);
                setHistoryDisplayTextView();
            }
        });

        button9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (textMainDisplay.equals("0")) {
                    textMainDisplay = "";
                }
                textMainDisplay = textMainDisplay + 9;
                MainDisplay.setText(textMainDisplay);
                HistoryDisplay.setText(textMainDisplay);
                setHistoryDisplayTextView();
            }
        });

        button0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (textMainDisplay.equals("0")) {
                    textMainDisplay = "";
                }
                textMainDisplay = textMainDisplay + 0;
                MainDisplay.setText(textMainDisplay);
                HistoryDisplay.setText(textMainDisplay);
                setHistoryDisplayTextView();
            }
        });
        */
        //Обработка одним ClickListener
  //    widgetHolder.button1.setOnClickListener(this);
        widgetHolder.button2.setOnClickListener(this);
        widgetHolder.button3.setOnClickListener(this);
        widgetHolder.button4.setOnClickListener(this);
        widgetHolder.button5.setOnClickListener(this);
        widgetHolder.button6.setOnClickListener(this);
        widgetHolder.button7.setOnClickListener(this);
        widgetHolder.button8.setOnClickListener(this);
        widgetHolder.button9.setOnClickListener(this);
        widgetHolder.button0.setOnClickListener(this);

    }

    // Для поворота экрана
    @Override
    protected final void onSaveInstanceState(@NonNull Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putString(KEY_COUNT1, textMainDisplay);
        savedInstanceState.putString(KEY_COUNT2, result);
        savedInstanceState.putBoolean(KEY_COUNT3, isPressPlus);
    }

    public final void onClick(View v) {
        if (textMainDisplay.equals("0")) {
            textMainDisplay = "";
        }
        int id = v.getId();
        if /*(id == R.id.button1) {
            textMainDisplay = textMainDisplay + 1;
        } else if*/ (id == R.id.button2) {
            textMainDisplay = textMainDisplay + 2;
        } else if (id == R.id.button3) {
            textMainDisplay = textMainDisplay + 3;
        } else if (id == R.id.button4) {
            textMainDisplay = textMainDisplay + 4;
        } else if (id == R.id.button5) {
            textMainDisplay = textMainDisplay + 5;
        } else if (id == R.id.button6) {
            textMainDisplay = textMainDisplay + 6;
        } else if (id == R.id.button7) {
            textMainDisplay = textMainDisplay + 7;
        } else if (id == R.id.button8) {
            textMainDisplay = textMainDisplay + 8;
        } else if (id == R.id.button9) {
            textMainDisplay = textMainDisplay + 9;
        } else if (id == R.id.button0) {
            textMainDisplay = textMainDisplay + 0;
        }
        WidgetHolder widgetHolder = new WidgetHolder(this);
        widgetHolder.getMainDisplayTextView().setText(textMainDisplay);
        setHistoryDisplayTextView();

    }


    private void setHistoryDisplayTextView() {
        WidgetHolder widgetHolder = new WidgetHolder(this);
        widgetHolder.getHistoryDisplayTextView().setText("number1=" + number1 + " number2=" + number2 + " result=" + result + " resultDouble=" +
                resultDouble + " numberDouble1=" + numberDouble1 + " numberDouble2=" + numberDouble2 + " textMainDisplay=" + textMainDisplay);

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





    /*public void changeColor(View v) {
        switch (v.getId()) {
            case R.id.button1:
                button1.setBackgroundColor(Color.parseColor("#ff0000"));
                button2.setBackgroundColor(Color.parseColor("#0000ff"));
                button3.setBackgroundColor(Color.parseColor("#0000ff"));
                break;
            case R.id.button2:
                button1.setBackgroundColor(Color.parseColor("#0000ff"));
                button2.setBackgroundColor(Color.parseColor("#ff0000"));
                button3.setBackgroundColor(Color.parseColor("#0000ff"));
                break;
            case R.id.button3:
                button1.setBackgroundColor(Color.parseColor("#0000ff"));
                button2.setBackgroundColor(Color.parseColor("#0000ff"));
                button3.setBackgroundColor(Color.parseColor("#ff0000"));
                break;
        }
    }*/




/*
        @Override
        public void onClick(View v){
            double number1 = 0;
            double number2 = 0;
            double result = 0;
            // определяем нажатую кнопку и выполняем соответствующую операцию
            // в oper пишем операцию, потом будем использовать в выводе
            switch (v.getId()) {
               *//* case R.id.buttonAllClean:
                    oper = "0";
                    break;*//*
                case R.id.buttonDel:
                    oper = "0";
                    break;
                case R.id.buttonPlus:
                    oper = "+";
                    result = number1;
                    break;
                case R.id.buttonMinus:
                    oper = "-";
                    //     result = number1 - number2;
                    break;
                case R.id.button1:
                    oper = "1";
                    //      result = number1 * number2;
                    break;
                case R.id.button2:
                    oper = "2";
                    //      result = number1 / number2;
                    break;//
                default:
                    break;
            }

          //  number1 = Double.parseDouble(MainDisplay.getText().toString());


            HistoryDisplay.setText(oper);
            MainDisplay.setText(oper);
        }*/


    /* *//* // сохранение состояния
        @Override
        protected void onSaveInstanceState(Bundle outState) {
            outState.putString("OPERATION", lastOperation);
            if(operand!=null)
                outState.putDouble("OPERAND", operand);
            super.onSaveInstanceState(outState);
        }
        // получение ранее сохраненного состояния
        @Override
        protected void onRestoreInstanceState(Bundle savedInstanceState) {
            super.onRestoreInstanceState(savedInstanceState);
            lastOperation = savedInstanceState.getString("OPERATION");
            operand= savedInstanceState.getDouble("OPERAND");
            resultField.setText(operand.toString());
            operationField.setText(lastOperation);
        }*//*
        // обработка нажатия на числовую кнопку
        public void onNumberClick(View view){

            Button button = (Button)view;
            MainDisplay.append(button.getText());

            if(lastOperation.equals("=") && operand!=null){
                operand = null;
            }
        }
        // обработка нажатия на кнопку операции
        public void onOperationClick(View view){

            Button button = (Button)view;
            String op = button.getText().toString();
            String textMainDisplay = numberField.getText().toString();
            // если введенно что-нибудь
            if(textMainDisplay.length()>0){
                textMainDisplay = textMainDisplay.replace(',', '.');
                try{
                    performOperation(Double.valueOf(textMainDisplay), op);
                }catch (NumberFormatException ex){
                    numberField.setText("");
                }
            }
            lastOperation = op;
            operationField.setText(lastOperation);
        }

        private void performOperation(Double textMainDisplay, String operation){

            // если операнд ранее не был установлен (при вводе самой первой операции)
            if(operand ==null){
                operand = textMainDisplay;
            }
            else{
                if(lastOperation.equals("=")){
                    lastOperation = operation;
                }
                switch(lastOperation){
                    case "=":
                        operand =textMainDisplay;
                        break;
                    case "/":
                        if(textMainDisplay==0){
                            operand =0.0;
                        }
                        else{
                            operand /=textMainDisplay;
                        }
                        break;
                    case "*":
                        operand *=textMainDisplay;
                        break;
                    case "+":
                        operand +=textMainDisplay;
                        break;
                    case "-":
                        operand -=textMainDisplay;
                        break;
                }
            }
            resultField.setText(operand.toString().replace('.', ','));
            numberField.setText("");
        }
    }


    }*/


}

