package com.example.haystackcalculator;

import android.view.View;

import java.text.DecimalFormat;

public class LogicHolder {
    private String textMainDisplay= "0";
    public String number1 = "", number2 = "", result = "";
    public double numberDouble1, numberDouble2, resultDouble, was;
    protected boolean isPressDivision, isPressMultiplication, isPressMinus, isPressPlus, isPressEquals;
   public Operation operation = Operation.NONE;
    public WidgetHolder widgetHolder;

    public LogicHolder (WidgetHolder widgetHolder){
       this.widgetHolder = widgetHolder;
    }



    public void choosePressedId (View ignoredV){
        if (textMainDisplay.equals("0")) {
            textMainDisplay = "";
        }
        textMainDisplay += ButtonsHashMap.getButtonIdsMap();
        setMainDisplay();
    }

        public void clear () {

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
        }

        public void delete () {
            if (!textMainDisplay.equals("0") & textMainDisplay.length() > 0) {
                widgetHolder.getMainDisplayTextView().setText(textMainDisplay = textMainDisplay.substring(0, textMainDisplay.length() - 1));
            }
            if (textMainDisplay.equals("")) {
                widgetHolder.getMainDisplayTextView().setText(textMainDisplay = "0");
            }
           setHistoryDisplayTextView();
        }

        public void changeSign () {
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

        public void divide (View ignoredV){
            getNumbers();

            // условие 6/2/ → 3/ → 3 3 3 3 3
            // основное вычисление
            if (!number2.equals("") & !number2.equals("0")) {

                resultDouble = convertStringToDouble(number1) / convertStringToDouble(number2);
                //     getResultCalculation(v);
                convertDoubleToString();
                formatFloatPoint();
                double x2 = calculate(operation);
                setResultMainDisplay(x2);
            }
            if (number2.equals("0")) {
                widgetHolder.getMainDisplayTextView().setText(Strings.error);
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

        public void multiply (View ignoredV){
            getNumbers();

            // условие 1*2* → 2* → 2 2 2 2 2
            if (isPressMultiplication & number1.equals(number2)) {
                number1 = "";
                textMainDisplay = result;
            }

            // основное вычисление
            if (!number1.equals("") & !number2.equals("")) {
                numberDouble1 = convertStringToDouble(number1);
                numberDouble2 = convertStringToDouble(number2);
                double x2 = calculate(operation);
                setResultMainDisplay(x2);
            }

            // условие 1*2= → 2*3 = → 6 = → 18
            if (isPressEquals & isPressMultiplication & !result.equals("")) {
                number1 = textMainDisplay;
            }

            // условие 2*3* → 6 = → 36 → 216
            if (isPressPlus & !number2.equals("")) {
                number2 = result;
            }


            // условие - результат вычисляется с новым введённым числом
            if (!result.equals("")) {
                number1 = result;
                number2 = textMainDisplay;
            }

            isPressMultiplication = true;
            textMainDisplay = ""; //после вывода результата,новое число выводится на новом экране

            setHistoryDisplayTextView();
        }

        public void subtract (View ignoredV){
            getNumbers();
            // условие 5-2- → 3 → 3 3 3 3 3
            if (isPressPlus & number1.equals(number2)) {
                number1 = "";
                textMainDisplay = result;
            }
            // основное вычисление
            if (!number2.equals("")) {
                numberDouble1 = convertStringToDouble(number1);
                numberDouble2 = convertStringToDouble(number2);

                double x2 = calculate(operation);
                setResultMainDisplay(x2);
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

        public void summarize (View ignoredV){
            getNumbers();
            // основное сложение
            if (!number1.equals("") & !number2.equals("")) {
                numberDouble1 = convertStringToDouble(number1);
                numberDouble2 = convertStringToDouble(number2);
                setResultMainDisplay(calculate(operation));
            }

         /*   // условие 1+2+ → 3+ → 3 3 3 3 3
            if (isPressPlus & number1.equals(number2)) {
                number1 = "";
                textMainDisplay = result;
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
*/
            isPressPlus = true;
            //operation = isPressPlus (Operation.SUMMATION);


            setHistoryDisplayTextView();
        }

        public void equal () {
            //          getNumbers();// add
            if (isPressPlus & !number1.equals("") & !number2.equals("")) {
                numberDouble1 = convertStringToDouble(number1);
                numberDouble2 = convertStringToDouble(number2);
                resultDouble = numberDouble1 + numberDouble2;
                setResultMainDisplay(resultDouble);
            }


            // условие 1+2= → 3 = → 5 = → 7 (ОСНОВНОЕ СУММИРОВАНИЕ)
            // условие - запись в первое Число если оно пусто
         /*   //& !result.equals(number1) можно убрать???
            if (!number2.equals("") & !result.equals(number1)) {
              *//*  textMainDisplay = result; // дописал
                number1 = textMainDisplay;*//*
                number1 = result;
            }*/
           /* if (!number2.equals("") & !result.equals(number1)) {
                number2 = textMainDisplay;
            }*/

            // условие 1+2= → 3+4 = → 7 = → 11
            if (isPressPlus & !result.equals("") & number2.equals(result)) {
                number1 = number2;
                number2 = textMainDisplay;
            }
            // условие 1*2= → 2*3 = → 6 = → 18
            if (isPressMultiplication & !result.equals("") & number2.equals(result)) {
                number1 = number2;
                number2 = textMainDisplay;
            }

            getSecondNumber();

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
            }*/

//                if (isPressMultiplication & !number1.equals("") & !number2.equals("")) {
//                    numberDouble1 = convertStringToDouble(number1);
//                    numberDouble2 = convertStringToDouble(number2);
//
//                    double x2 = getResultCalculation(v);
//                    setResultMainDisplay(x2);
//                }
//                if (isPressMinus & !number1.equals("") & !number2.equals("")) {
//                    numberDouble1 = convertStringToDouble(number1);
//                    numberDouble2 = convertStringToDouble(number2);
//
//                    double x2 = getResultCalculation(v);
//                    setResultMainDisplay(x2);
//                }

            // Пробел после нажатого знака вычисления
            if (isPressEquals & !result.equals("")) {
                textMainDisplay = "";
            }
            isPressEquals = true;

            setHistoryDisplayTextView();
        }




        public void putDot () {
            textMainDisplay = textMainDisplay + ".";
            setMainDisplay();
        }


        private void getNumbers () {
            // условие - запись в первое число если оно пусто
            if (number1.equals("")) {
                number1 = textMainDisplay;
                textMainDisplay = "";
            }
            if (number2.equals("")) {
                number2 = textMainDisplay;
            }
        }

        private void getSecondNumber () {
            // условие - запись во второе число если оно пусто
            if (number2.equals("")) {
                number2 = textMainDisplay;
            }
        }

    /*private void convertStringToDouble1() {
        numberDouble1 = Double.parseDouble(number1);
        numberDouble2 = Double.parseDouble(number2);
    }*/

        public double convertStringToDouble (String num){
            return Double.parseDouble(num);
        }


        public Double calculate (Operation operation){
            switch (operation) {
                case DIVISION:
                    return numberDouble1 / numberDouble2;
                case MULTIPLICATION:
                    return numberDouble1 * numberDouble2;
                case SUBTRACTION:
                    return numberDouble1 - numberDouble2;
                case SUMMATION:
                    return numberDouble1 + numberDouble2;
                default:
                    return (double) 0;
            }
        }


        private void setResultMainDisplay ( double x){

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


        protected void setMainDisplay () {
            widgetHolder.getMainDisplayTextView().setText(textMainDisplay);
        }

        private String getNumber1 () {
            return number1;
        }

        public void setHistoryDisplayTextView() {
            widgetHolder.getHistoryDisplayTextView().setText("number1=" + number1 + " number2=" +
                    number2 + " result=" + result + " resultDouble=" +
                    resultDouble + " numberDouble1=" + numberDouble1 +
                    " numberDouble2=" + numberDouble2 + " textMainDisplay=" + textMainDisplay);
        }


        private void convertDoubleToString () {
            result = String.valueOf(resultDouble);
        }

        private void formatFloatPoint () {
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



    }

