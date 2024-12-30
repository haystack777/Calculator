package com.example.haystackcalculator.AboutApp.Fragments;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.view.View;

import com.example.haystackcalculator.Strings;

public class AboutAppFragmentLogicHolder {
    //public AboutAppFragmentWidgetHolder aboutAppFragmentWidgetHolder;
    public AboutAppFragment aboutContext;

    // Конструктор принимает контекст и приводит его к AppCompatActivity
    public AboutAppFragmentLogicHolder(AboutAppFragment context) {
        aboutContext = context;
    }

    public void moveToGitHub(View ignoredView) {
        Intent intent = new Intent(Intent.ACTION_VIEW,
                Uri.parse("https://github.com/haystack777/Calculator"));
        aboutContext.startActivity(intent);
    }

    @SuppressLint("IntentReset")
    public void writeToDeveloper(View ignoredView) {
        Intent intentEmail = new Intent(Intent.ACTION_SENDTO, Uri.parse("mailto:"));
        intentEmail.putExtra(Intent.EXTRA_EMAIL, Strings.recipients);
        intentEmail.putExtra(Intent.EXTRA_SUBJECT, Strings.subject);
        intentEmail.putExtra(Intent.EXTRA_TEXT, Strings.content);
        aboutContext.startActivity(Intent.createChooser(intentEmail, "Choose an email client from..."));
    }
}
