package com.example.haystackcalculator.AboutApp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.haystackcalculator.R;

public class AboutAppActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
    }

    public void moveToCalculator(View view) {
        finish();
    }

    public void moveToGitHub(View view){
        Intent intent = new Intent(Intent.ACTION_VIEW,
                Uri.parse("https://github.com/haystack777/Calculator"));
        startActivity(intent);
    }



    // The method is called when the user clicks on "Send Email" button.
    @SuppressLint("IntentReset")
    public void sendEmail(View view)  {

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