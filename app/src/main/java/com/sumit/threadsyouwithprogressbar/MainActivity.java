package com.sumit.threadsyouwithprogressbar;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private Button btnthread;
    private ProgressBar progressBar;
    private WorkerThread workerThread;
    private TextView precent, thankyou;
    private Handler handler;
    private int count = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        workerThread = new WorkerThread();
        handler = new Handler();
        workerThread.start();
        intitview();
    }

    private void intitview() {
        btnthread = findViewById(R.id.BtnThread);
        progressBar = findViewById(R.id.progressbar);
        precent = findViewById(R.id.percent);
        thankyou = findViewById(R.id.tvthankyou);

        btnthread.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                thankyou.setVisibility(View.INVISIBLE);
                workerThread.addTaskToMessageQueue(tasknumber);
                progressBar.setVisibility(View.VISIBLE);
            }
        });
    }
    private  Runnable tasknumber = new Runnable() {
        @Override
        public void run() {
            for (int i =0; i<=10; i++){
                count = i;

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                   progressBar.setProgress(count);
                   if(count==10){
                       precent.setText("DownLoad Complete");
                       progressBar.setVisibility(View.INVISIBLE);
                       thankyou.setVisibility(View.VISIBLE);
                   }else{
                       precent.setText((count) + " % " + " loading ");
                   }
                    }
                });
            }
        }
    };
}