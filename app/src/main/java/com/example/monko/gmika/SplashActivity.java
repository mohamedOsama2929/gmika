package com.example.monko.gmika;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ProgressBar;

public class SplashActivity extends AppCompatActivity {

    ProgressBar progressBar;
    Intent i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

      progressBar=(ProgressBar)findViewById(R.id.progressBar3);

        new Thread(new Runnable() {

            int prograssState=0;

            @Override
            public void run() {
                while (prograssState<100){

                    prograssState+=20;

                    try {
                        Thread.sleep(1000);

                    }catch (Exception e){

                      e.printStackTrace();
                    }
                    progressBar.post(new Runnable() {
                        @Override
                        public void run() {
                            progressBar.setProgress(prograssState);
                        }
                    });

                }
                if (prograssState>=100){
                    i=new Intent(SplashActivity.this,MainActivity.class);
                    startActivity(i);
                    finish();

                }



            }
        }).start();
    }
}
