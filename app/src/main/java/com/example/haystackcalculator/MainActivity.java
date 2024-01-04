package com.example.haystackcalculator;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;



public class MainActivity extends AppCompatActivity {
    //   private double numberDouble1, numberDouble2, resultDouble, was;
    private WidgetHolder widgetHolder;
    private LogicHolder logicHolder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);

        widgetHolder = new WidgetHolder(this);
      //  logicHolder = new LogicHolder();
        logicHolder.setMainDisplay();
        setHistoryDisplayTextView();

        // Для поворота экрана
        if (savedInstanceState != null) {
            Strings.textMainDisplay = savedInstanceState.getString(Strings.KEY_COUNT1);
            Strings.result = savedInstanceState.getString(Strings.KEY_COUNT2);
            logicHolder.isPressPlus = savedInstanceState.getBoolean(Strings.KEY_COUNT3);
            logicHolder.setMainDisplay();
        } else {
            Strings.textMainDisplay = "0";
            logicHolder.setMainDisplay();
        }

        widgetHolder.getAllCleanButton().setOnClickListener(v -> {
            logicHolder.cleaning();
            setHistoryDisplayTextView();
        });

        widgetHolder.getDelButton().setOnClickListener(v -> {
            logicHolder.deletion();
            setHistoryDisplayTextView();
        });

        widgetHolder.getChangeSignButton().setOnClickListener(v -> {
            logicHolder.changeSign();
            setHistoryDisplayTextView();
        });
        widgetHolder.getDivisionButton().setOnClickListener(v -> {
            logicHolder.division(v);
            setHistoryDisplayTextView();
        });

        widgetHolder.getMultiplicationButton().setOnClickListener(v -> {
            logicHolder.multiplication(v);
            setHistoryDisplayTextView();
        });

        widgetHolder.getMinusButton().setOnClickListener(v -> {
            logicHolder.subtraction(v);
            setHistoryDisplayTextView();
        });

        widgetHolder.getPlusButton().setOnClickListener(v -> {
            logicHolder.summarize(v);
            setHistoryDisplayTextView();
        });

        widgetHolder.getEqualsButton().setOnClickListener(v -> {
            logicHolder.calculate();
            setHistoryDisplayTextView();
        });

        widgetHolder.getDotButton().setOnClickListener(v -> {
            logicHolder.putDot();
            setHistoryDisplayTextView();
        });


        for (Button button : widgetHolder.getButtons()) {
            button.setOnClickListener(v -> {
                logicHolder.choosePressedId(v);
                setHistoryDisplayTextView();
            });
        }
    }


    // Для поворота экрана
    @Override
    protected final void onSaveInstanceState(@NonNull Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putString(Strings.KEY_COUNT1, Strings.textMainDisplay);
        savedInstanceState.putString(Strings.KEY_COUNT2, Strings.result);
        savedInstanceState.putBoolean(Strings.KEY_COUNT3, logicHolder.isPressPlus);// Plus исправить
    }

    private void setHistoryDisplayTextView() {
        widgetHolder.getHistoryDisplayTextView().setText("number1=" + Strings.number1 + " number2=" +
                Strings.number2 + " result=" + Strings.result + " resultDouble=" +
                logicHolder.resultDouble + " numberDouble1=" + logicHolder.numberDouble1 +
                " numberDouble2=" + logicHolder.numberDouble2 + " textMainDisplay=" + Strings.textMainDisplay);
    }


    //  LastOperation lastPressOperation = LastOperation.PRESSEDEQUALS;

    /*switch(lastPressOperation){
        case PRESSEDDEVISION:
            return was = 1;
        break;
        case PRESSEDMULTIPLICATION:
            return numberDouble1 * numberDouble2;
        break;
        case PRESSEDMINUS:
            return numberDouble1 - numberDouble2;
        break;
        case PRESSEDPLUS:
            return numberDouble1 + numberDouble2;
        break;
        case PRESSEDEQUALS:
            was = 5;
            break;
    }*/


   /* public void lastPressOperation(LastOperation lastOperation) {
        switch (lastOperation) {
            case PRESSEDDEVISION:
                was = numberDouble1 / numberDouble2;
                ;
                break;
            case PRESSEDMULTIPLICATION:
                was = 2;
                break;
            case PRESSEDMINUS:
                was = 3;
                break;
            case PRESSEDPLUS:
                was = 4;
                break;
            case PRESSEDEQUALS:
                was = 5;
                break;
            default:
        }
    }*/

 /*   public Double lastPressOperation (LastOperation lastOperation) {
        switch (lastOperation) {
            case PRESSEDDEVISION:
                return numberDouble1 / numberDouble2;
                break;
            case PRESSEDMULTIPLICATION:
                return numberDouble1 * numberDouble2;
                break;
            case PRESSEDMINUS:
                return numberDouble1 - numberDouble2;
                break;
            case PRESSEDPLUS:
                return numberDouble1 + numberDouble2;
                break;
            case PRESSEDEQUALS:
                was = 5;
                break;
            default:
        }*/


}

