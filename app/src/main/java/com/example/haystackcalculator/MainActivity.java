package com.example.haystackcalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);

        WidgetHolder widgetHolder = new WidgetHolder(this);
        LogicHolder logicHolder = new LogicHolder();

        logicHolder.setMainDisplay();
        WidgetHolder.setHistoryDisplayTextView();

        widgetHolder.getAllCleanButton().setOnClickListener(v -> {
            logicHolder.clear();
        });

        widgetHolder.getDelButton().setOnClickListener(v -> {
            logicHolder.delete();
        });
        widgetHolder.getChangeSignButton().setOnClickListener(v -> {
            logicHolder.changeSign();
        });
        widgetHolder.getDivisionButton().setOnClickListener(logicHolder::divide);

        widgetHolder.getMultiplicationButton().setOnClickListener(logicHolder::multiply);

        widgetHolder.getMinusButton().setOnClickListener(logicHolder::subtract);

        widgetHolder.getPlusButton().setOnClickListener(logicHolder::summarize);

        widgetHolder.getEqualsButton().setOnClickListener(v -> {
            logicHolder.equal();
        });

        widgetHolder.getDotButton().setOnClickListener(v -> {
            logicHolder.putDot();
        });

        for (Button button : widgetHolder.getButtons()) {
            button.setOnClickListener(logicHolder::choosePressedId);
        }
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
