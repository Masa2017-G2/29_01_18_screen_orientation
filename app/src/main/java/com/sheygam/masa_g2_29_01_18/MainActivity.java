package com.sheygam.masa_g2_29_01_18;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button myBtn;
    private TextView resultTxt;
    private MyTask myTask;
    public static final String TAG = "MAIN_ACT";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate: " + savedInstanceState);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myBtn = findViewById(R.id.my_btn);
        resultTxt = findViewById(R.id.result_txt);
        myBtn.setOnClickListener(this);
        if(savedInstanceState != null){
            resultTxt.setText(savedInstanceState.getString("DATA","empty"));
        }

        myTask = (MyTask) getLastCustomNonConfigurationInstance();
        Log.d(TAG, "onCreate: " + myTask);
        if (myTask == null){
            myTask = new MyTask();
            myTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
        }
        myTask.onAttach(this);
    }

    @Override
    protected void onStart() {
        Log.d(TAG, "onStart: ");
        super.onStart();
    }

    @Override
    protected void onResume() {
        Log.d(TAG, "onResume: ");
        super.onResume();
    }

    @Override
    public Object onRetainCustomNonConfigurationInstance() {
        Log.d(TAG, "onRetainCustomNonConfigurationInstance: ");
        if(myTask!= null){
            myTask.onDetach();
        }
        return myTask;
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        Log.d(TAG, "onSaveInstanceState: ");
        outState.putString("DATA", String.valueOf(resultTxt.getText()));
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        Log.d(TAG, "onRestoreInstanceState: ");
        super.onRestoreInstanceState(savedInstanceState);
    }

    @Override
    protected void onPause() {
        Log.d(TAG, "onPause: ");
        super.onPause();
    }

    @Override
    protected void onStop() {
        Log.d(TAG, "onStop: ");
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        Log.d(TAG, "onDestroy: ");
        super.onDestroy();
        myTask = null;
    }



    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.my_btn){
            resultTxt.setText("New msg");
        }
    }

    class MyTask extends AsyncTask<Void,String,Void>{
        MainActivity activity;
        public static final String ASYNC_TAG = "MAIN_ACT";
        private int id;

        public MyTask() {
            Random rnd = new Random();
            id = rnd.nextInt(1000);
        }

        public void onAttach(MainActivity activity){
            this.activity = activity;
        }

        public void onDetach(){
            activity = null;
        }

        @Override
        protected void onProgressUpdate(String... values) {
            if(activity != null) {
                activity.resultTxt.setText(values[0]);
            }
        }

        @Override
        protected Void doInBackground(Void... voids) {
            for (int i = 0; i < 10; i++) {
                Log.d(TAG, "MyTask id: " + id + " doInBackground: " + i);
                publishProgress(String.valueOf(i));
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }
    }
}
