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

        MainActivityWidgetHolder mainActivityWidgetHolder = new MainActivityWidgetHolder(this);
        MainActivityLogicHolder mainActivityLogicHolder = new MainActivityLogicHolder(mainActivityWidgetHolder);

        mainActivityLogicHolder.setMainDisplay();
        mainActivityLogicHolder.setHistoryDisplayTextView();

        mainActivityWidgetHolder.getAllCleanButton().setOnClickListener(v -> {
            mainActivityLogicHolder.clear();
        });

        mainActivityWidgetHolder.getDelButton().setOnClickListener(v -> {
            mainActivityLogicHolder.delete();
        });
        mainActivityWidgetHolder.getChangeSignButton().setOnClickListener(v -> {
            mainActivityLogicHolder.changeSign();
        });
        mainActivityWidgetHolder.getDivisionButton().setOnClickListener(mainActivityLogicHolder::divide);

        mainActivityWidgetHolder.getMultiplicationButton().setOnClickListener(mainActivityLogicHolder::multiply);

        mainActivityWidgetHolder.getMinusButton().setOnClickListener(mainActivityLogicHolder::subtract);

        mainActivityWidgetHolder.getPlusButton().setOnClickListener(mainActivityLogicHolder::summarize);

        mainActivityWidgetHolder.getEqualsButton().setOnClickListener(v -> {
            mainActivityLogicHolder.equal();
        });

        mainActivityWidgetHolder.getDotButton().setOnClickListener(v -> {
            mainActivityLogicHolder.putDot();
        });

        for (Button button : mainActivityWidgetHolder.getButtons()) {
            button.setOnClickListener(mainActivityLogicHolder::choosePressedId);
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
