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
/*    @Override
    public boolean onCreateOptionsMenu (Menu menu){
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        TextView headerView = findViewById(R.id.activity.menu);
        switch(id){
            case R.id.activity_menu :
                headerView.setText("Настройки");
                return true;
            case R.id.Program_to_Git:
                headerView.setText("Открыть");
                return true;
            case R.id.Write_to_developer:
                headerView.setText("Сохранить");
                return true;
        }
        //headerView.setText(item.getTitle());
        return super.onOptionsItemSelected(item);
    }


    public void moveToAboutApp(View view) {
        Intent intent = new Intent(MainActivity.this, AboutAppActivity.class);
        startActivity(intent);
    }*/

}
