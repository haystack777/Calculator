package com.example.haystackcalculator;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.text.DecimalFormat;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    public String num1 = "", num2 = "", result = "";
    double result2;
    double num12, num22, was;
    boolean pressDivision, pressMultiplication, pressMinus, pressPlus, pressEquals;
    String textMainDisplay = "0";

    private static final String KEY_COUNT1 = "COUNT1";// для поворота экрана
    private static final String KEY_COUNT2 = "COUNT2";
    private static final String KEY_COUNT3 = "COUNT3";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);
        WidgetHolder widgetHolder = new WidgetHolder(this);

       // Context context = getContext
     //   WidgetHolder widgetHolder1 = new WidgetHolder(this);



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

        widgetHolder.mainDisplayTextView.setText(textMainDisplay);
       // widgetHolder.historyDisplayTextView.setText(textMainDisplay);
        setHistoryDisplayTextView();
        // Для поворота экрана
        if (savedInstanceState != null) {
            textMainDisplay = savedInstanceState.getString(KEY_COUNT1);
            result = savedInstanceState.getString(KEY_COUNT2);
            pressPlus = savedInstanceState.getBoolean(KEY_COUNT3);
            widgetHolder.mainDisplayTextView.setText(textMainDisplay);
        } else {
            textMainDisplay = "0";
            widgetHolder.mainDisplayTextView.setText(textMainDisplay);
        }

        widgetHolder.allCleanButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                num1 = "";
                num2 = "";
                if (!textMainDisplay.equals("0")) {
                    widgetHolder.mainDisplayTextView.setText(textMainDisplay = "0");
                    num1 = "";
                    num2 = "";
                    result = "";
                    num12 = 0;
                    num22 = 0;
                    result2 = 0;
                    //  widgetHolder.historyDisplayTextView.setText(textMainDisplay);
                    //  widgetHolder.historyDisplayTextView.setText("num1=" + num1 + " num2=" + num2);
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
                    result = num1;
                    break;
                case R.id.buttonMinus:
                    oper = "-";
                    //     result = num1 - num2;
                    break;

                case R.id.button1:
                    oper = "1";
                    //      result = num1 * num2;
                    break;
                case R.id.button2:
                    oper = "2";
                    //      result = num1 / num2;
                    break;//
                default:
                    break;
            }
        }*/


        widgetHolder.delButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!textMainDisplay.equals("0") & textMainDisplay.length() > 0) {
                    widgetHolder.mainDisplayTextView.setText(textMainDisplay = textMainDisplay.substring(0, textMainDisplay.length() - 1));
                }
                if (textMainDisplay.equals("")) {
                    widgetHolder.mainDisplayTextView.setText(textMainDisplay = "0");
                }

                widgetHolder.historyDisplayTextView.setText(textMainDisplay);
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
                    widgetHolder.mainDisplayTextView.setText(textMainDisplay);
                } else {
                    a = Integer.parseInt(textMainDisplay);
                    a = a * (-1);
                    textMainDisplay = String.valueOf(a);
                    widgetHolder.mainDisplayTextView.setText(textMainDisplay);
                }
                if (textMainDisplay.contains(String.valueOf(s)) & !result.equals("")) {
                    result = "-" + result;
                } else if (!textMainDisplay.contains(String.valueOf(s)) & result.contains(String.valueOf(s))) {
                    result = result.substring(1);
                    result2 -= result2;
                }

             /*   if (result.contains(String.valueOf(s))){

                    a = Integer.parseInt(result);
                    a = a * (-1);
                    result = String.valueOf(a);
                    result2 = result2*2;
                }*/


                /*else {
                    a = Integer.parseInt(result);
                    a = a * (-1);
                    result = String.valueOf(a);
                }*/
                setHistoryDisplayTextView();
            }
        });

        widgetHolder.divisionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // условие - запись в первое число если оно пусто

                if (num1.equals("")) {
                    num1 = textMainDisplay;
                    textMainDisplay = "";
                }
                // условие - запись во второе число если оно пусто
                if (num2.equals("")) {
                    num2 = textMainDisplay;
                }

                // условие 6/2/ → 3/ → 3 3 3 3 3
                // основное вычисление
                if (!num2.equals("") & !num2.equals("0")) {
                    num12 = Double.parseDouble(num1);
                    num22 = Double.parseDouble(num2);
                    result2 = num12 / num22;
                    result = String.valueOf(result2);
                    formatFloatPoint();
                    textMainDisplay = result;
                    widgetHolder.mainDisplayTextView.setText(textMainDisplay);
                }
                if (num2.equals("0")) {
                    widgetHolder.mainDisplayTextView.setText("ОШИБКА");
                }
                textMainDisplay = ""; //после вывода результата,новое число выводится на новом экране

                // условие - результат вычисляется с новым введённым числом
                if (!result.equals("")) {
                    num1 = result;
                    num2 = textMainDisplay;
                }
                pressDivision = true;

                setHistoryDisplayTextView();
            }
        });

        widgetHolder.multiplicationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // условие - запись в первое число если оно пусто
                if (num1.equals("")) {
                    num1 = textMainDisplay;
                    textMainDisplay = "";
                }
                // условие - запись во второе число если оно пусто
                if (num2.equals("")) {
                    num2 = textMainDisplay;
                }

                // условие 1*2* → 2* → 2 2 2 2 2
                if (pressMultiplication & num1.equals(num2)) {
                    num1 = "";
                    textMainDisplay = result;
                }

                // основное вычисление
                if (!num1.equals("") & !num2.equals("")) {
                    num12 = Double.parseDouble(num1);
                    num22 = Double.parseDouble(num2);
                    result2 = num12 * num22;
                    formatFloatPoint();
                    textMainDisplay = result;
                    widgetHolder.mainDisplayTextView.setText(textMainDisplay);
                }
                // условие 1*2= → 2*3 = → 6 = → 18
                if (pressEquals & pressMultiplication & !result.equals("")) {
                    num1 = textMainDisplay;
                }

                // условие 2*3* → 6 = → 36 → 216
                if (pressPlus & !num2.equals("")) {
                    num2 = result;
                }

               /*
                // условие - результат вычисляется с новым введённым числом
                if (!result.equals("")) {
                    num1 = result;
                    num2 = textMainDisplay;
                }*/

                pressMultiplication = true;
                textMainDisplay = ""; //после вывода результата,новое число выводится на новом экране
                setHistoryDisplayTextView();
            }
        });

        widgetHolder.minusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // условие - запись в первое число если оно пусто
                if (num1.equals("")) {
                    num1 = textMainDisplay;
                    textMainDisplay = "";
                }
                // условие - запись во второе число если оно пусто
                if (num2.equals("")) {
                    num2 = textMainDisplay;
                }

                // условие 5-2- → 3 → 3 3 3 3 3
                if (pressPlus & num1.equals(num2)) {
                    num1 = "";
                    textMainDisplay = result;
                }
                // основное вычисление
                if (!num2.equals("")) {
                    num12 = Double.parseDouble(num1);
                    num22 = Double.parseDouble(num2);
                    result2 = num12 - num22;
                    result = String.valueOf(result2);
                    formatFloatPoint();
                    textMainDisplay = result;
                    widgetHolder.mainDisplayTextView.setText(textMainDisplay);
                }
                textMainDisplay = ""; //после вывода результата,новое число выводится на новом экране

                // условие 5-2= → 3-2 = → 1 = → -1 → -3
                if (pressEquals & pressMinus & !result.equals("")) {
                    num1 = textMainDisplay;
                }
                // условие 1+2+ → 3 = → 6 → 9 Вроде не нужно, но плюс без него не работает
                if (pressMinus & !num2.equals("")) {
                    num2 = result;
                }

                // условие - результат вычисляется с новым введённым числом ЗАЧЕМ????
                if (!result.equals("")) {
                    num1 = result;
                    num2 = textMainDisplay;
                }
                pressMinus = true;
                setHistoryDisplayTextView();
            }
        });


        widgetHolder.plusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // условие - запись в первое слагаемое если оно пусто
                if (num1.equals("")) {
                    num1 = textMainDisplay;
                    textMainDisplay = "";
                }
                // условие - запись во второе слагаемое если оно пусто
                if (num2.equals("")) {
                    num2 = textMainDisplay;
                }

                // условие 1+2+ → 3+ → 3 3 3 3 3
                if (pressPlus & num1.equals(num2)) {
                    num1 = "";
                    textMainDisplay = result;
                }

                // основное сложение
                if (!num1.equals("") & !num2.equals("")) {
                    num12 = Double.parseDouble(num1);
                    num22 = Double.parseDouble(num2);
                    result2 = num12 + num22;
                    result = String.valueOf(result2);
                    formatFloatPoint();
                    textMainDisplay = result;
                    widgetHolder.mainDisplayTextView.setText(textMainDisplay);
                }

                // условие 1+2= → 3+4 = → 7 = → 11
                if (pressEquals & pressPlus & !result.equals("")) {
                    num1 = textMainDisplay;
                }

                // условие 1+2+ → 3 = → 6 → 9
                if (pressPlus & !num2.equals("")) {
                    num2 = result;
                }

                // Пробел после нажатого знака вычисления
                if (pressPlus & !result.equals("")) {
                    textMainDisplay = "";
                }

                // textMainDisplay = ""; //после вывода результата,новое число выводится на новом экране

                pressPlus = true;
                setHistoryDisplayTextView();
            }

        });

        widgetHolder.equalsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // условие 1+2= → 3 = → 5 = → 7 (ОСНОВНОЕ СУММИРОВАНИЕ)
                // условие - запись в первое Число если оно пусто
                //& !result.equals(num1) можно убрать???
                if (!num2.equals("") & !result.equals(num1)) {
                    num1 = textMainDisplay;
                }
               /* if (!num2.equals("") & !result.equals(num1)) {
                    num2 = textMainDisplay;
                }*/

                // условие 1+2= → 3+4 = → 7 = → 11
                if (pressPlus & !result.equals("") & num2.equals(result)) {
                    num1 = num2;
                    num2 = textMainDisplay;
                }
                // условие 1+2= → 3+4 = → 7 = → 11
                if (pressMultiplication & !result.equals("") & num2.equals(result)) {
                    num1 = num2;
                    num2 = textMainDisplay;
                }


                // условие - запись во второе Число если оно пусто
                if (num2.equals("")) {
                    num2 = textMainDisplay;
                }

                // условие 1+ = → 2 = → 3 = → 4
                if (!num1.equals("") & num2.equals("")) {
                    num2 = num1;
                }

                textMainDisplay = ""; //после вывода равенства,новое число выводится на новом экране

                // условие начальное нажатие =
                if (pressDivision & !num1.equals("") & !num2.equals("")) {
                    num12 = Double.parseDouble(num1);
                    num22 = Double.parseDouble(num2);
                    result2 = num12 / num22;
                    result = String.valueOf(result2);
                    formatFloatPoint();
                    textMainDisplay = result;
                    widgetHolder.mainDisplayTextView.setText(result);
                }

                if (pressMultiplication & !num1.equals("") & !num2.equals("")) {
                    num12 = Double.parseDouble(num1);
                    num22 = Double.parseDouble(num2);
                    result2 = num12 * num22;
                    result = String.valueOf(result2);
                    formatFloatPoint();
                    textMainDisplay = result;
                    widgetHolder.mainDisplayTextView.setText(result);
                }
                if (pressMinus & !num1.equals("") & !num2.equals("")) {
                    num12 = Double.parseDouble(num1);
                    num22 = Double.parseDouble(num2);
                    result2 = num12 - num22;
                    result = String.valueOf(result2);
                    formatFloatPoint();
                    textMainDisplay = result;
                    widgetHolder.mainDisplayTextView.setText(result);
                }
                if (pressPlus & !num1.equals("") & !num2.equals("")) {
                    num12 = Double.parseDouble(num1);
                    num22 = Double.parseDouble(num2);
                    result2 = num12 + num22;
                    result = String.valueOf(result2);
                    formatFloatPoint();
                    textMainDisplay = result;
                    widgetHolder.mainDisplayTextView.setText(result);
                }
                // Пробел после нажатого знака вычисления
                if (pressEquals & !result.equals("")) {
                    textMainDisplay = "";
                }


                pressEquals = true;
                setHistoryDisplayTextView();
            }
        });


        widgetHolder.dotButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textMainDisplay = textMainDisplay + ".";
                widgetHolder.mainDisplayTextView.setText(textMainDisplay);
                widgetHolder.historyDisplayTextView.setText(textMainDisplay);
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
                widgetHolder.mainDisplayTextView.setText(textMainDisplay);
                //widgetHolder.historyDisplayTextView.setText(textMainDisplay);

                setHistoryDisplayTextView();
            }
     *//*   {
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
                *//*if (pressPlus & pressEquals) {
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
              *//*  if (!result.equals(textMainDisplay) & !pressPlus) {
                    num1 = textMainDisplay;
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
        widgetHolder.button1.setOnClickListener(this);
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
    protected void onSaveInstanceState(@NonNull Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putString(KEY_COUNT1, textMainDisplay);
        savedInstanceState.putString(KEY_COUNT2, result);
        savedInstanceState.putBoolean(KEY_COUNT3, pressPlus);
    }

    public void onClick(View v) {
        if (textMainDisplay.equals("0")) {
            textMainDisplay = "";
        }
        int id = v.getId();
        if (id == R.id.button1) {
            textMainDisplay = textMainDisplay + 1;
        } else if (id == R.id.button2) {
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
        widgetHolder.mainDisplayTextView.setText(textMainDisplay);

        setHistoryDisplayTextView();

    }


    public void setHistoryDisplayTextView() {
        WidgetHolder widgetHolder = new WidgetHolder(this);
        widgetHolder.historyDisplayTextView.setText("num1=" + num1 + " num2=" + num2 + " result=" + result + " result2=" +
                result2 + " num12=" + num12 + " num22=" + num22 + " textMainDisplay=" + textMainDisplay);
        
    }

    public void formatFloatPoint() {
        // Форматироание плавающей точки
        if (result2 == (int) result2) {
                     /*   result = String.format("%.0f",result2);
                        System.out.printf("целое" + result);*/
            DecimalFormat dF = new DecimalFormat("#.#");
            result = dF.format(result2);
            System.out.printf("целое" + result);

        } else {
            //result = String.format("%s",result2);

            DecimalFormat dF = new DecimalFormat("#.##########");
            result = dF.format(result2);
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
            double num1 = 0;
            double num2 = 0;
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
                    result = num1;
                    break;
                case R.id.buttonMinus:
                    oper = "-";
                    //     result = num1 - num2;
                    break;
                case R.id.button1:
                    oper = "1";
                    //      result = num1 * num2;
                    break;
                case R.id.button2:
                    oper = "2";
                    //      result = num1 / num2;
                    break;//
                default:
                    break;
            }

          //  num1 = Double.parseDouble(MainDisplay.getText().toString());


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

