package com.sheygam.masa_g2_29_01_18;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by gregorysheygam on 29/01/2018.
 */

public class MyFragment extends Fragment {
    public static final String TAG = "MY_TAG";
    private String tmp = "Temp";
    private TextView countTxt;
    private MyTask myTask;
    private View view;

    public MyFragment() {
        setRetainInstance(true);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        Log.d(TAG, "onCreate: temp = " + tmp);
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onAttach(Activity activity) {
        Log.d(TAG, "onAttach: temp = " + tmp);
        super.onAttach(activity);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView: ");
        if(view == null){
            view = inflater.inflate(R.layout.my_fragment,container,false);
            countTxt = view.findViewById(R.id.count_txt);
        }
        ViewGroup root  = (ViewGroup) view.getParent();
        if(root != null){
            root.removeAllViews();
        }
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if(savedInstanceState == null){
            myTask = new MyTask();
            myTask.execute();
        }
        view.findViewById(R.id.set_btn)
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        tmp = "Btn Clicked";
                    }
                });
    }

    @Override
    public void onDestroyView() {
        Log.d(TAG, "onDestroyView: ");
        super.onDestroyView();
    }

    @Override
    public void onDetach() {
        Log.d(TAG, "onDetach: ");
        super.onDetach();
    }

    class MyTask extends AsyncTask<Void,String,Void>{

        @Override
        protected void onProgressUpdate(String... values) {
            countTxt.setText(values[0]);
        }

        @Override
        protected Void doInBackground(Void... voids) {
            for (int i = 0; i < 10; i++) {
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
