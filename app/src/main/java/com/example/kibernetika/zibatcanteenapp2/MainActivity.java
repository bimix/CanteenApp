package com.example.kibernetika.zibatcanteenapp2;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
public class MainActivity extends AppCompatActivity {
private GestureDetectorCompat gestureObject;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
gestureObject = new GestureDetectorCompat(this,new LearnGesture());
      //  MediaPlayer sound = MediaPlayer.create(MainActivity.this,R.raw.on_gan);
    }
    @Override
    protected void onStart() {
        super.onStart();

    }
    public void dishListButton(View view){
        Intent intent = new Intent(this,MenuActivity.class);
        startActivity(intent);
    }


    public void LoginButton(View view) {
        Intent intent2= new Intent(this,LoginActivity.class);
        startActivity(intent2);
    }
    @Override
    public boolean onTouchEvent (MotionEvent event){
        this.gestureObject.onTouchEvent(event);
        return super.onTouchEvent(event);
    }
class LearnGesture extends GestureDetector.SimpleOnGestureListener{


    @Override
    public boolean onFling(MotionEvent event1,MotionEvent event2, float velocityX, float velocityY){
        if(event2.getX()>event1.getX()){
Intent intent = new Intent(MainActivity.this,MenuActivity.class);
            finish();
            startActivity(intent);
        }
        else
            if (event2.getX()<event1.getX()){
Intent intent2 = new Intent(MainActivity.this,SignupActivity.class);
                finish();
                startActivity(intent2);
            }
            return true;
    }

}
}
