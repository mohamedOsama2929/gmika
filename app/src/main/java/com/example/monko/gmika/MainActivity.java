package com.example.monko.gmika;

import android.app.Application;
import android.content.ClipData;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;
import java.util.logging.Handler;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity implements MediaPlayer.OnCompletionListener {

    TextView time;
    int hour=0;
    int minute=0;
   public int second=0;
    String ho,min,sec;
    android.os.Handler handler;
    Runnable run;
    MediaPlayer mplay;

    SeekBar seekBar;
    boolean seekdo=false;
    boolean seekdone=false;
    Button button;
    boolean clickbutton3=true;
    boolean clicked3=false;
    boolean clicked4=false;
    boolean clickbutton4=true;

    void timer(){
        handler = new android.os.Handler();
        run = new Runnable() {
            @Override
            public void run() {
                executed=true;

                second++;
                hour=(int) minute/60;



                ho=Integer.toString(hour);
                min=Integer.toString(minute);
                sec=Integer.toString(second);

                if(second==60){
                    minute++;
                    second=1;
                }
                if(minute==60){
                    hour++;
                    minute=1;

                }

                //the show of number in the screen

                if(second<=9){
                    sec="0"+second;

                }
                if(minute<=9){
                    min="0"+minute;

                }
                if(hour<=9){
                    ho="0"+hour;

                }
                if((q<=questions.length-1)&&(second == 1 || second == 10 || second == 20 || second == 30 || second == 40 || second == 50 || second == 60)){

                    questionText.setText(questions[q]+"");
                    q++;


                }

                time.setText(ho+" : "+min+" : "+sec);
               Log.i("log000",Integer.toString(second));

                //display seekbar after minute
                if(minute==1&&!seekdone){

                    seekmetod();

                }
                //the button on seekbar do not drag to new position at 10 second
                if(seekdo==false&&minute==1&&second==10){

                   // System.exit(0);
                    android.os.Process.killProcess(android.os.Process.myPid());

                }
                //after 3 minute display the button again
                if(minute==3&&clickbutton3){
                    button.setVisibility(View.VISIBLE);
                    clickbutton3=false;

                }
                //if not click at 3 minute
                if(minute==3&&clicked3==false&&second==10){
                    System.exit(1);

                }


                //after 4 minute display the button again
                if(minute==4&&clickbutton4){
                    button.setVisibility(View.VISIBLE);
                    clickbutton4=false;

                }
                //if not click at 4 minute
                if(minute==4&&clicked4==false&&second==10){
                    System.exit(1);

                }



                handler.postDelayed(this, 1000);

            }
        };

    }
    void seekmetod(){
        seekdone=true;

        seekBar.setVisibility(View.VISIBLE);

        seekBar.setMax(100);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                if(progress!=100){
                    seekdo=false;

                }else{
                    seekdo=true;
                    //do thing
                    Log.i("seekbar","done");

                }

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                seekBar.setProgress(0);

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                if(seekdo){

                    seekBar.setVisibility(View.GONE);


                    seekBar.animate().alphaBy(0.0f).setDuration(2000);


                }else{
                    seekBar.setProgress(0);
                    seekdo=false;

                }

            }
        });

    }


    boolean executed=false;
    String[]questions={"The secret of getting ahead is getting started","Only I can change my life. No one can do it for me","Only I can change my life. No one can do it for me","In order to succeed, we must first believe that we can","Keep your eyes on the stars, and your feet on the ground","Getting tired?","Don't Stop Believing","Failure will never overtake me if my determination to succeed is strong enough","Accept the challenges so that you can feel the exhilaration of victory","You can't cross the sea merely by standing and staring at the water","A creative man is motivated by the desire to achieve, not by the desire to beat others","It’s just a button man. A button can’t break you","We should not give up and we should not allow the problem to defeat us","No bird soars too high if he soars with his own wings","Set your goals high, and don't stop till you get there","They can conquer who believe they can","Be the Michael Jordan of the button world!","Expect problems and eat them for breakfast","Never retreat. Never explain. Get it done and let them howl","Change your life today. Don't gamble on the future, act now, without delay","Do something wonderful, people may imitate it","YOU ARE THE KING.","YOU ARE THE CHAMPION","THE UNIVERSE IS IN MOTION BECAUSE YOU WERE BORN","PYRAMIDS WERE BUILT TO WELCOME YOU INTO THE WORLD","VIVA LA VIDA!","YOU’RE THE VIPER!"};

    int q=0;
    TextView questionText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        Log.i("secondteeeeeen",Integer.toString(second));


        time = (TextView) findViewById(R.id.timertext);
        timer();

        questionText=(TextView)findViewById(R.id.questointext);
        //switch questions






        //background and alpha with 4 sec
       RelativeLayout  act=(RelativeLayout)findViewById(R.id.act);
        act.animate().alpha(1.0f).setDuration(4000);


        //the music at start the activity

        mplay=MediaPlayer.create(this,R.raw.coldplay);

        mplay.start();

        mplay.setOnCompletionListener( this);



        seekBar=(SeekBar)findViewById(R.id.seekBar);
        seekBar.setVisibility(View.GONE);

        //button
        button=(Button)findViewById(R.id.button);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id= item.getItemId();
        if(id==android.R.id.home){
            Toast.makeText(getApplicationContext(),"home",Toast.LENGTH_LONG).show();


        }

        return super.onOptionsItemSelected(item);

    }

    //when the game pause
    @Override
    protected void onPause() {
        super.onPause();

        System.exit(0);

    }

 // When song is ended then media player automatically called onCompletion method.
    public void onCompletion(MediaPlayer arg0)
    {
        System.exit(0);
        // Write your code
    }



    void on(View view){
        if(!executed){

            handler.post(run);
        }

            button.setVisibility(View.INVISIBLE);
        button.setText("Again");


        if(minute==3){
            clicked3=true;



        }

        if(minute==4){
            clicked4=true;



        }







    }

}
