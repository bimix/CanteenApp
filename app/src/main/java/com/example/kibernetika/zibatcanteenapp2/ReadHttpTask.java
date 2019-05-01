package com.example.kibernetika.zibatcanteenapp2;

import android.os.AsyncTask;
import android.util.Log;

import java.io.IOException;

/**
 * Created by kibernetika on 19.04.2017.
 */


public class ReadHttpTask extends AsyncTask<String, Void, CharSequence> {
    protected CharSequence doInBackground(String... urls) {
        String urlString = urls[0];
        try {
            CharSequence result = HttpHelper.GetHttpResponse(urlString);
            return result;
        } catch (IOException ex) {
            cancel(true);
            String errorMessage = ex.getMessage() + "\n" + urlString;
            Log.e("DISH", errorMessage);
            return errorMessage;
        }
    }
}