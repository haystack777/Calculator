package com.example.haystackcalculator.AboutApp;

import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.haystackcalculator.R;

class AboutAppActivityWidgetHolder {

    private final Button moveToGitHubButton, writeToDeveloperButton;


    public AboutAppActivityWidgetHolder(AppCompatActivity context) {
        moveToGitHubButton = context.findViewById(R.id.codeGit);
        writeToDeveloperButton = context.findViewById(R.id.writeDeveloper);
    }

    public Button getMoveToGitHubButton() {
        return moveToGitHubButton;
    }

    public Button getWriteToDevloperButton() {
        return writeToDeveloperButton;
    }

}
