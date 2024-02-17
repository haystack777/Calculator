package com.example.haystackcalculator.AboutApp;

import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.haystackcalculator.R;

class AboutAppActivityWidgetHolder {

    private final Button moveToGitHub, writeToDeveloper;


    public AboutAppActivityWidgetHolder(AppCompatActivity context) {
        moveToGitHub = context.findViewById(R.id.codeGit);
        writeToDeveloper = context.findViewById(R.id.writeDeveloper);
    }

    public Button getMoveToGitHub() {
        return moveToGitHub;
    }

    public Button getWriteToDevloperButton() {
        return writeToDeveloper;
    }


}
