package com.example.haystackcalculator.MainActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.haystackcalculator.AboutApp.AboutAppActivity;
import com.example.haystackcalculator.R;


public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        WidgetHolder widgetHolder = new WidgetHolder(this);
        LogicHolder logicHolder = new LogicHolder(widgetHolder);

        logicHolder.setMainDisplay();
        logicHolder.setHistoryDisplayTextView();

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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        MenuItem menuItem = menu.findItem(R.id.About_program);
        menuItem.setOnMenuItemClickListener(item -> {
            // Выполните код для вызова новой активити
            Intent intent = new Intent(MainActivity.this, AboutAppActivity.class);
            startActivity(intent);
            return true;
        });



        return true;
    }
    public void moveToAboutApp(View view){
        Intent intent = new Intent(MainActivity.this, AboutAppActivity.class);
        startActivity(intent);
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
