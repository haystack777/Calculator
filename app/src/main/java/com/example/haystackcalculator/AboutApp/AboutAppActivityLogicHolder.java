package com.example.haystackcalculator.AboutApp;

import static androidx.core.content.ContextCompat.startActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.view.View;


public class AboutAppActivityLogicHolder {
    public AboutAppActivityWidgetHolder aboutAppActivityWidgetHolder;

    public AboutAppActivityLogicHolder(AboutAppActivityWidgetHolder aboutAppActivityWidgetHolder) {
        this.aboutAppActivityWidgetHolder = aboutAppActivityWidgetHolder;
    }

    public void moveToGitHub(View ignoredView){
        Intent intent = new Intent(Intent.ACTION_VIEW,
                Uri.parse("https://github.com/haystack777/Calculator"));
       startActivity(intent);
    }


    @SuppressLint("IntentReset")
    public void writeToDeveloper(View view)  {

        // List of recipients
        String[] recipients=new String[]{"haystack_git@mail.ru"};

        String subject="Hi, do you like HaystackCalculator?";

        String content ="Hello, Anton!";

        Intent intentEmail = new Intent(Intent.ACTION_SEND, Uri.parse("mailto:"));
        intentEmail.putExtra(Intent.EXTRA_EMAIL, recipients);
        intentEmail.putExtra(Intent.EXTRA_SUBJECT, subject);
        intentEmail.putExtra(Intent.EXTRA_TEXT, content);

        intentEmail.setType("text/plain");

        startActivity(Intent.createChooser(intentEmail, "Choose an email client from..."));
    }


}
