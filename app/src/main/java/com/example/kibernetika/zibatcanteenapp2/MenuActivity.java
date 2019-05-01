package com.example.kibernetika.zibatcanteenapp2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        ReadTask task = new ReadTask();
        task.execute("http://anbo-canteen.azurewebsites.net/Service1.svc/dishes");

    }


    private class ReadTask extends ReadHttpTask {

        protected void onPostExecute(CharSequence charSequence) {
            super.onPostExecute(charSequence);

            //TextView messageTextView = (TextView) findViewById(R.id.main_message_TextView);


            final List<Dish> articles = new ArrayList<>();
            try {
                // JSONObject object = new JSONObject(charSequence.toString());
                JSONArray array = new JSONArray(charSequence.toString());
                for (int i = 0; i < array.length(); i++) {
                    JSONObject obj = array.getJSONObject(i);
                    String Description = obj.getString("Description");
                    String PictureUrl = obj.getString("PictureUrl");
                    String Title = obj.getString("Title");
                    int Alcohol = obj.getInt("Alcohol");
                    int Carbohydrates = obj.getInt("Carbohydrates");
                    int Energy =obj.getInt("Energy");
                    int Fat =obj.getInt("Fat");
                    int Id =obj.getInt("Id");
                    int Price =obj.getInt("Price");
                    //int Protein =obj.getInt("Protein");
                    //int Weight =obj.getInt("Weight");

                    Dish article = new Dish(Description, PictureUrl, Title, Alcohol, Carbohydrates, Energy, Fat, Price, Id);
                    articles.add(article);
                }

                ListView listView = (ListView) findViewById(R.id.dishlist_View);
                //  ArrayAdapter<Article> adapter = new ArrayAdapter<>(getBaseContext(), android.R.layout.simple_list_item_1, articles);
                final DishListItemAdapter adapter = new DishListItemAdapter(getBaseContext(), R.layout.adapter_layout, articles);
                listView.setAdapter(adapter);

                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                    //                    @Override
//                    public void onClick(View view) {
//                        Intent intent = new Intent(getBaseContext(),MenuDetailsActivity.class);
//                        //int articleId=(int) id+1;
//                        //Article theArticle= articles.get(articleId);
//                      //  intent.putExtra(ARTICLE,theArticle);
//                        startActivity(intent);
//                    }
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                        Intent intent = new Intent(getBaseContext(), MenuDetailsActivity.class);
                        intent.putExtra("DISH", articles.get((int) id));
                        startActivity(intent);
                    }
                });

            } catch (JSONException ex) {
                //messageTextView.setText(ex.getMessage());
                Log.e("Articles", ex.getMessage());
            }
        }



    }
}


