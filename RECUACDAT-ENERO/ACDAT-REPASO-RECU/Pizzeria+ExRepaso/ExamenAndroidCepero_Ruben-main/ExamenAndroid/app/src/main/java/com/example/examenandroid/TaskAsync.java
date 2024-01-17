package com.example.examenandroid;

import android.os.AsyncTask;
import android.widget.TextView;

public class TaskAsync extends AsyncTask<Void, Void, String> {

    private TextView resultTextView;

    public TaskAsync() {
    }

    @Override
    protected void onPreExecute() {

    }

    @Override
    protected String doInBackground(Void... params) {

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return "true";
    }

    @Override
    protected void onPostExecute(String result) {
        // This method runs on the UI thread after the background task is finished.
        // Update the UI with the result.

        resultTextView.setText(result);
    }
}