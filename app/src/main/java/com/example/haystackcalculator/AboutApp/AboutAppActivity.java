package com.example.haystackcalculator.AboutApp;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.haystackcalculator.R;

public class AboutAppActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        AboutAppActivityWidgetHolder aboutAppActivityWidgetHolder = new AboutAppActivityWidgetHolder(this);
        AboutAppActivityLogicHolder aboutAppActivityLogicHolder =
                new AboutAppActivityLogicHolder(aboutAppActivityWidgetHolder);

        aboutAppActivityWidgetHolder.getMoveToGitHub().
                setOnClickListener(aboutAppActivityLogicHolder::moveToGitHub);

        aboutAppActivityWidgetHolder.getWriteToDevloperButton().
                setOnClickListener(aboutAppActivityLogicHolder::writeToDeveloper);

    }

}