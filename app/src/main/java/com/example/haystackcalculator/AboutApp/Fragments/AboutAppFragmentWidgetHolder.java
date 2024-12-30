package com.example.haystackcalculator.AboutApp.Fragments;

import android.view.View;
import android.widget.Button;

import com.example.haystackcalculator.R;

public class AboutAppFragmentWidgetHolder {
    private Button moveToGitHubButton;
    private Button writeToDeveloperButton;


    public AboutAppFragmentWidgetHolder(View context) {
        moveToGitHubButton = context.findViewById(R.id.codeGit);
        writeToDeveloperButton = context.findViewById(R.id.writeDeveloper);
    }

    public AboutAppFragmentWidgetHolder(AboutAppFragment aboutAppFragment) {

    }

    public Button getMoveToGitHubButton() {
        return moveToGitHubButton;
    }

    public Button getWriteToDevloperButton() {
        return writeToDeveloperButton;
    }
}
