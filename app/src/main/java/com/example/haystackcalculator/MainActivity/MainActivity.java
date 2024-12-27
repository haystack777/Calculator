package com.example.haystackcalculator.MainActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.haystackcalculator.AboutApp.Fragments.AboutAppFragment;
import com.example.haystackcalculator.CalculationGame.CalculationGameFragment;
import com.example.haystackcalculator.OperationsHistory.OperationsHistoryFragment;
import com.example.haystackcalculator.R;


public class MainActivity extends AppCompatActivity {

    // @SuppressLint("MissingInflatedId")
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
        return true;
    }



    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Fragment selectedFragment = null;
        if (item.getItemId() == R.id.about_app) {
            selectedFragment = new AboutAppFragment();
        } else if (item.getItemId() == R.id.calculation_game) {
            selectedFragment = new CalculationGameFragment();
        } else if (item.getItemId() == R.id.operations_history) {
            selectedFragment = new OperationsHistoryFragment();
        }

        if (selectedFragment != null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.menu_frame, selectedFragment)
                    .addToBackStack(null)
                    .commit();
            //findViewById(R.id.root_view).onFinishTemporaryDetach();
        }
        return super.onOptionsItemSelected(item);
    }
   /* @Override
   protected void onStop() {
        super.onStop();
        finish();
    }*/
}

