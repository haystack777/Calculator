package com.example.haystackcalculator;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.DecimalFormat;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView HistoryDisplay, MainDisplay;
    Button buttonAllClean, buttonDel, buttonSign;
    Button buttonDivision, buttonMultiplication, buttonMinus,
            buttonPlus, buttonEquals, buttonDot;
    Button button1, button2, button3, button4, button5, button6,
            button7, button8, button9, button0;

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

        // находим элементы
        HistoryDisplay = (TextView) findViewById(R.id.history);
        MainDisplay = (TextView) findViewById(R.id.result);

        buttonAllClean = (Button) findViewById(R.id.buttonAC);
        buttonDel = (Button) findViewById(R.id.buttonDel);
        buttonSign = (Button) findViewById(R.id.buttonSign);

        buttonDivision = (Button) findViewById(R.id.buttonDivision);
        buttonMultiplication = (Button) findViewById(R.id.buttonMultiplication);
        buttonMinus = (Button) findViewById(R.id.buttonMinus);
        buttonPlus = (Button) findViewById(R.id.buttonPlus);
        buttonEquals = (Button) findViewById(R.id.buttonEquals);
        buttonDot = (Button) findViewById(R.id.buttonDot);

        button1 = (Button) findViewById(R.id.button1);
        button2 = (Button) findViewById(R.id.button2);
        button3 = (Button) findViewById(R.id.button3);
        button4 = (Button) findViewById(R.id.button4);
        button5 = (Button) findViewById(R.id.button5);
        button6 = (Button) findViewById(R.id.button6);
        button7 = (Button) findViewById(R.id.button7);
        button8 = (Button) findViewById(R.id.button8);
        button9 = (Button) findViewById(R.id.button9);
        button0 = (Button) findViewById(R.id.button0);

        MainDisplay.setText(textMainDisplay);
        setHistoryDisplay();

        // Для поворота экрана
        if (savedInstanceState != null) {
            textMainDisplay = savedInstanceState.getString(KEY_COUNT1);
            result = savedInstanceState.getString(KEY_COUNT2);
            pressPlus = savedInstanceState.getBoolean(KEY_COUNT3);
            MainDisplay.setText(textMainDisplay);
        } else {
            textMainDisplay = "0";
            MainDisplay.setText(textMainDisplay);
        }

        buttonAllClean.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                num1 = "";
                num2 = "";
                if (!textMainDisplay.equals("0")) {
                    MainDisplay.setText(textMainDisplay = "0");
                    num1 = "";
                    num2 = "";
                    result = "";
                    num12 = 0;
                    num22 = 0;
                    result2 = 0;
                    //  HistoryDisplay.setText(textMainDisplay);
                    //  HistoryDisplay.setText("num1=" + num1 + " num2=" + num2);
                }
                setHistoryDisplay();
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


        buttonDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!textMainDisplay.equals("0") & textMainDisplay.length() > 0) {
                    MainDisplay.setText(textMainDisplay = textMainDisplay.substring(0, textMainDisplay.length() - 1));
                }
                if (textMainDisplay.equals("")) {
                    MainDisplay.setText(textMainDisplay = "0");
                }

                HistoryDisplay.setText(textMainDisplay);
                setHistoryDisplay();
            }
        });

        buttonSign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                char s = '-';
                int a;
                if (textMainDisplay.equals("0")) {

                }

                if (!textMainDisplay.contains(String.valueOf(s)) & !textMainDisplay.equals("0")) {
                    textMainDisplay = "-" + textMainDisplay;
                    MainDisplay.setText(textMainDisplay);
                } else {
                    a = Integer.parseInt(textMainDisplay);
                    a = a * (-1);
                    textMainDisplay = String.valueOf(a);
                    MainDisplay.setText(textMainDisplay);
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
                setHistoryDisplay();
            }
        });

        buttonDivision.setOnClickListener(new View.OnClickListener() {
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
                    MainDisplay.setText(textMainDisplay);
                }
                if (num2.equals("0")) {
                    MainDisplay.setText("ОШИБКА");
                }
                textMainDisplay = ""; //после вывода результата,новое число выводится на новом экране

                // условие - результат вычисляется с новым введённым числом
                if (!result.equals("")) {
                    num1 = result;
                    num2 = textMainDisplay;
                }
                pressDivision = true;

                setHistoryDisplay();
            }
        });

        buttonMultiplication.setOnClickListener(new View.OnClickListener() {
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
                    MainDisplay.setText(textMainDisplay);
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
                setHistoryDisplay();
            }
        });

        buttonMinus.setOnClickListener(new View.OnClickListener() {
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
                    MainDisplay.setText(textMainDisplay);
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
                setHistoryDisplay();
            }
        });


        buttonPlus.setOnClickListener(new View.OnClickListener() {
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
                    MainDisplay.setText(textMainDisplay);
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
                setHistoryDisplay();
            }

        });

        buttonEquals.setOnClickListener(new View.OnClickListener() {
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
                    MainDisplay.setText(result);
                }

                if (pressMultiplication & !num1.equals("") & !num2.equals("")) {
                    num12 = Double.parseDouble(num1);
                    num22 = Double.parseDouble(num2);
                    result2 = num12 * num22;
                    result = String.valueOf(result2);
                    formatFloatPoint();
                    textMainDisplay = result;
                    MainDisplay.setText(result);
                }
                if (pressMinus & !num1.equals("") & !num2.equals("")) {
                    num12 = Double.parseDouble(num1);
                    num22 = Double.parseDouble(num2);
                    result2 = num12 - num22;
                    result = String.valueOf(result2);
                    formatFloatPoint();
                    textMainDisplay = result;
                    MainDisplay.setText(result);
                }
                if (pressPlus & !num1.equals("") & !num2.equals("")) {
                    num12 = Double.parseDouble(num1);
                    num22 = Double.parseDouble(num2);
                    result2 = num12 + num22;
                    result = String.valueOf(result2);
                    formatFloatPoint();
                    textMainDisplay = result;
                    MainDisplay.setText(result);
                }
                // Пробел после нажатого знака вычисления
                if (pressEquals & !result.equals("")) {
                    textMainDisplay = "";
                }


                pressEquals = true;
                setHistoryDisplay();
            }
        });


        buttonDot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textMainDisplay = textMainDisplay + ".";
                MainDisplay.setText(textMainDisplay);
                HistoryDisplay.setText(textMainDisplay);
                setHistoryDisplay();
            }
        });

/*
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (textMainDisplay.equals("0")) {
                    textMainDisplay = "";
                }
                textMainDisplay = textMainDisplay + 1;
                MainDisplay.setText(textMainDisplay);
                //HistoryDisplay.setText(textMainDisplay);

                setHistoryDisplay();
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
                HistoryDisplay.setText(textMainDisplay);
                setHistoryDisplay();
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
                HistoryDisplay.setText(textMainDisplay);

                setHistoryDisplay();
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
                HistoryDisplay.setText(textMainDisplay);

                // условие 1+2= → 3+4 = → 7 = → 11
              *//*  if (!result.equals(textMainDisplay) & !pressPlus) {
                    num1 = textMainDisplay;
                }*//*

                setHistoryDisplay();
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
                setHistoryDisplay();
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
                setHistoryDisplay();
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
                setHistoryDisplay();
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
                setHistoryDisplay();
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
                setHistoryDisplay();
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
                setHistoryDisplay();
            }
        });
        */
        //Обработка одним ClickListener
        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);
        button4.setOnClickListener(this);
        button5.setOnClickListener(this);
        button6.setOnClickListener(this);
        button7.setOnClickListener(this);
        button8.setOnClickListener(this);
        button9.setOnClickListener(this);
        button0.setOnClickListener(this);

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
        MainDisplay.setText(textMainDisplay);

        setHistoryDisplay();

    }


    public void setHistoryDisplay() {
        HistoryDisplay.setText("num1=" + num1 + " num2=" + num2 + " result=" + result + " result2=" +
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

