package com.example.haystackcalculator.MainActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.haystackcalculator.AboutApp.AboutAppFragment;
import com.example.haystackcalculator.CalculationGame.CalculationGameFragment;
import com.example.haystackcalculator.OperationsHistory.OperationsHistoryFragment;
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



        Button aboutAppButton, calculationGameButton, operationHistoryButton;

        aboutAppButton = findViewById(R.id.about_program);
        calculationGameButton = findViewById(R.id.calculation_game);
        operationHistoryButton = findViewById(R.id.operations_history);

        aboutAppButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AboutAppFragment aboutAppFragment = new AboutAppFragment();
                setNewFragment(aboutAppFragment);
            }
        });
        calculationGameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CalculationGameFragment calculationGameFragment = new CalculationGameFragment();
                setNewFragment(calculationGameFragment);
            }
        });
        operationHistoryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OperationsHistoryFragment operationsHistoryFragment = new OperationsHistoryFragment();
                setNewFragment(operationsHistoryFragment);
            }
        });
    }

    private void setNewFragment(Fragment fragment) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.menu_frame, fragment);
        ft.commit();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        MenuItem menuItem = menu.findItem(R.id.about_program);
        menuItem.setOnMenuItemClickListener(item -> {
            // Выполните код для вызова новой активити
            Intent intent = new Intent(MainActivity.this, AboutAppFragment.class);
            startActivity(intent);
            return true;
        });
        return true;
    }

    public void moveToAboutApp(View view) {
        Intent intent = new Intent(MainActivity.this, AboutAppFragment.class);
        startActivity(intent);
    }

    /*    @Override
    public boolean onCreateOptionsMenu (Menu menu){
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        TextView headerView = findViewById(R.id.activity.menu);
        switch(id){
            case R.id.activity_menu :
                headerView.setText("Настройки");
                return true;
            case R.id.Program_to_Git:
                headerView.setText("Открыть");
                return true;
            case R.id.Write_to_developer:
                headerView.setText("Сохранить");
                return true;
        }
        //headerView.setText(item.getTitle());
        return super.onOptionsItemSelected(item);
    }


    public void moveToAboutApp(View view) {
        Intent intent = new Intent(MainActivity.this, AboutAppActivity.class);
        startActivity(intent);
    }*/


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
