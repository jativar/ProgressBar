package com.jativa.ramiro.progressbar;

import android.support.v7.app.AppCompatActivity;
import android.widget.ProgressBar;
import android.os.Handler;
import android.os.Bundle;
import android.widget.TextView;

/*
    In this App we've created a basic android ProgressBar.
    Source: https://www.journaldev.com/9629/android-progressbar-example
    The code updates the ProgressBar after every 200 milliseconds and
    the progress bar updates are set using setProgress().
    A handler is used to run the background thread.
    The thread runs until the progressStatus value reaches 100.

    Note: The next application of the progressBar is the use of a
    progress Dialog.  Where we determine when the progress bar will
    initiate.  The URL below is a reference to the example.
    https://www.journaldev.com/9652/android-progressdialog-example

 */

public class MainActivity extends AppCompatActivity {

    private ProgressBar progressBar;
    private int progressStatus = 0;
    private TextView textView;
    private Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        textView = (TextView) findViewById(R.id.textView);

        // Start long running operation in a background threat

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (progressStatus < 100) {
                    progressStatus += 1;

                    // Update the progress bar and display the
                    // current value in the text view
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            progressBar.setProgress(progressStatus);
                            textView.setText(progressStatus + "/" + progressBar.getMax());
                        }
                    });
                    try {
                        // Sleep for 200 milliseconds
                        Thread.sleep(200);

                    } catch (InterruptedException e) {
                        e.getStackTrace();
                    }
                }
            }
        }).start();



    }



}
