package com.example.haystackcalculator.AboutApp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.haystackcalculator.Strings;


public class AboutAppActivityLogicHolder {
    public AboutAppActivityWidgetHolder aboutAppActivityWidgetHolder;
public AppCompatActivity aboutContext;
    public AboutAppActivityLogicHolder(AppCompatActivity context) {
        aboutContext = context;

    }

    public void moveToGitHub(View ignoredView){
        Intent intent = new Intent(Intent.ACTION_VIEW,
                Uri.parse("https://github.com/haystack777/Calculator"));
       aboutContext.startActivity(intent);
    }


    @SuppressLint("IntentReset")
    public void writeToDeveloper(View ignoredView)  {



        Intent intentEmail = new Intent(Intent.ACTION_SEND, Uri.parse("mailto:"));
        intentEmail.putExtra(Intent.EXTRA_EMAIL, Strings.recipients);
        intentEmail.putExtra(Intent.EXTRA_SUBJECT, Strings.subject);
        intentEmail.putExtra(Intent.EXTRA_TEXT, Strings.content);

        intentEmail.setType("text/plain");
        aboutContext.startActivity(Intent.createChooser(intentEmail, "Choose an email client from..."));
    }


}
