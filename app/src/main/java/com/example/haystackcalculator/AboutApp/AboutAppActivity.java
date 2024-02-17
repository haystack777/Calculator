package com.example.haystackcalculator.AboutApp;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.haystackcalculator.R;

public class AboutAppActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        AboutAppActivityWidgetHolder widgetHolder = new AboutAppActivityWidgetHolder(this);

        AboutAppActivityLogicHolder logicHolder = new AboutAppActivityLogicHolder( this);

        widgetHolder.getMoveToGitHubButton().
                setOnClickListener(logicHolder::moveToGitHub);

        widgetHolder.getWriteToDevloperButton().
                setOnClickListener(logicHolder::writeToDeveloper);

    }

}