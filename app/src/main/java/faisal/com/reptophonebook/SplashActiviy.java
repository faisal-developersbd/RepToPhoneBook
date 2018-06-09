package faisal.com.reptophonebook;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ProgressBar;

public class SplashActiviy extends AppCompatActivity {
    Handler handler;
    class MyThread extends Thread{
        int i=0;
        public void run()
        {
            while(i<=100){
                try {
                    sleep(100);
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            progressBar.setProgress(i);
                            i++;
                            if(i==100)
                            {
                                Intent intent=new Intent(SplashActiviy.this,RegistrationLogin.class);
                                startActivity(intent);
                            }
                        }
                    });
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    ProgressBar progressBar;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        progressBar=findViewById(R.id.progressBar);
        handler=new Handler();
        Thread thread=new MyThread();
        thread.start();
    }
}
