package com.example.kibernetika.zibatcanteenapp2;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import android.widget.RatingBar;
public class MenuDetailsActivity extends AppCompatActivity {
    private Dish dishes;
    private EditText fatView, carbsView, alcoholView, energyView, priceView;
    RatingBar rb;
    TextView value;
    SharedPreferences wmbPreference1;
    SharedPreferences.Editor editor;
    int ratingNum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_details);
        //((RatingBar )findViewById(R.id.ratingBar)).setOnRatingBarChangeListener((RatingBar.OnRatingBarChangeListener) this);
        //wmbPreference1 = PreferenceManager.getDefaultSharedPreferences(this);


        Intent intent = getIntent();
        dishes = (Dish) intent.getSerializableExtra("DISH");

        fatView = (EditText) findViewById(R.id.fat_edittext);
        fatView.setText(dishes.getFat() + " ");

        carbsView = (EditText) findViewById(R.id.carbohydrates_edittext);
        carbsView.setText(dishes.getCarbohydrates() + " ");

        alcoholView = (EditText) findViewById(R.id.alcohol_EditText);
        alcoholView.setText(dishes.getAlcohol() + " ");

        energyView = (EditText) findViewById(R.id.energy_EditText);
        energyView.setText(dishes.getEnergy() + " ");

        priceView = (EditText) findViewById(R.id.price_EditText);
        priceView.setText(dishes.getPrice() + " ");


        rb = (RatingBar) findViewById(R.id.ratingBar);

    }

    public void submitRating(View view) {
        Log.d("Mine", "step0");
        TextView messageView = (TextView) findViewById(R.id.value);
        try {
            JSONObject jsonObject = new JSONObject();
            Log.d("Mine", "step1");
            jsonObject.put("DishId", dishes.getId());
            Log.d("dishId", dishes.getId() + "");
            jsonObject.put("CustomerId", 8);
            RatingBar ratingBar2 = (RatingBar) findViewById(R.id.ratingBar);
            ratingNum = Math.round(ratingBar2.getRating());

            jsonObject.put("Rate", ratingNum);
            Log.e("Ratingnr",ratingNum+"");

            String jsonDocument = jsonObject.toString();
            Log.d("JSON", jsonDocument);
            messageView.setText(jsonDocument);
            PostDishTask task = new PostDishTask();
            task.execute("http://anbo-canteen.azurewebsites.net/Service1.svc/ratings",jsonDocument);

        } catch (JSONException ex) {
            messageView.setText(ex.getMessage());

        }
        finish();
    }

    public void OrderNow(View view) {
        TextView messageView = (TextView) findViewById(R.id.orderTakeAway);

        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("DishId", dishes.getId());
            Log.d("DISHID-ORDER", dishes.getId() + "");
            jsonObject.put("CustomerId", 1);
            jsonObject.put("Howmany", 1);
            Log.d("DISHID-qaua", dishes + "");
            long pickup =  new Date().getTime()+4567875433L;
            Object value = "/Date(" + pickup + ")/";
            jsonObject.put("PickupDateTime", value);
            Log.d("pickit", value + "");

            String jsonDocument = jsonObject.toString();
            Log.d("JSON-ORDER", jsonDocument);
            messageView.setText(jsonDocument);
            PostDishTask task = new PostDishTask();
            Log.d("MyStep","stepPOST");
            task.execute("http://anbo-canteen.azurewebsites.net/Service1.svc/takeaways", jsonDocument);
        } catch (JSONException ex) {
            messageView.setText(ex.getMessage());
        }
        finish();
    }

    private class PostDishTask extends AsyncTask<String, Void, CharSequence> {

        @Override
        protected CharSequence doInBackground(String... params) {
            String urlString = params[0];
            String jsonDocumet = params[1];
            try {
                URL url = new URL(urlString);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("POST");
                connection.setDoOutput(true);
                connection.setRequestProperty("Content-Type", "application/json");
                connection.setRequestProperty("Accept", "application/json");
                OutputStreamWriter osw = new OutputStreamWriter(connection.getOutputStream());
                osw.write(jsonDocumet);
                osw.flush();
                osw.close();
                int responseCode = connection.getResponseCode();
                if (responseCode / 100 != 2) {
                    String responseMessage = connection.getResponseMessage();
                    throw new IOException("HTTP response code: " + responseCode + " " + responseMessage);
                }
                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(connection.getInputStream()));
                String line = reader.readLine();
                return line;
            } catch (MalformedURLException ex) {
                cancel(true);
                String message = ex.getMessage() + " " + urlString;
                Log.e("DISH", message);
                return message;
            } catch (IOException ex) {
                cancel(true);
                Log.e("DISH", ex.getMessage());
                return ex.getMessage();
            }
        }
    }
//        private static ArrayList<Dish> dishess = new ArrayList<>();
//
//        public  void caloriesList(View view) {
//            if(dishes.isEmpty()) {
//
//                dishes.add(0, dishes);
//
//                Log.d("listnew", dishes + "");
//            }
//            else if(dishes.contains(dishes)) {
//                dishes.add(0, dishes);
//                //dishes.clear();
//                Log.d("listnew2", dishes + "");
//            }
//
//            else if (dishes.contains(dishes)) {
//                dishes.add(0, dishes);
//                Log.d("listnew3", dishes + "");
//            }
//            else if(dishes.contains(dishes)) {
//                dishes.add(0, dishes);
//                Log.d("listnew4", dishes + "");
//            }
//            else if(dishes.size() == 5)
//                dishes.clear();
//            Log.d("listnewclean", dishes + "");
//        }
//
//        public static ArrayList<Dish> getArrayList()
//        {
//            return dishess;
//        }
//
//
//        public void onCleanDiaryClick(View view) {
//            dishes.clear();
//            Log.d("listnewcleanx", dishes + "");
//        }



}


