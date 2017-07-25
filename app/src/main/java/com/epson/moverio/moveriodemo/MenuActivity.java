package com.epson.moverio.moveriodemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MenuActivity extends AppCompatActivity {

    private final int REQUEST_EXIT = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_menu);

        final Button btnHome = (Button) findViewById(R.id.btn_home);

        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchHomeScreen();
            }
        });

    }

    private void launchHomeScreen() {
        Intent intent = new Intent(this, IntroActivity.class);
        startActivity(intent);
        finish();
    }

    public void twoDButtonClick (View view){
        Intent intent = new Intent(this, TwoDActivity.class);
        startActivityForResult(intent, REQUEST_EXIT);
    }

    public void djiButtonClick (View view){
        Intent intent = new Intent(this, DJIActivity.class);
        startActivityForResult(intent, REQUEST_EXIT);
    }

    public void viewerDButtonClick (View view){
        Intent intent = new Intent(this, ViewerActivity.class);
        startActivityForResult(intent,REQUEST_EXIT);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_EXIT) {
            if (resultCode == RESULT_OK) {
                this.finish();
            }
        }
    }

}
