package com.example.kibernetika.zibatcanteenapp2;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.RatingBar;
import android.widget.TextView;
import java.util.List;
import android.util.Log;
/**
 * Created by kibernetika on 19.04.2017.
 */

public class DishListItemAdapter  extends ArrayAdapter<Dish> {



    private int resource;



    public DishListItemAdapter(Context context, int resource, List<Dish> object) {
        super(context, resource,object);
        this.resource = resource;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        Dish dishes = getItem(position);
        String title = dishes.getTitle();
        String description = dishes.getDescription();
        LinearLayout dishView;

        if (convertView == null) {
            dishView = new LinearLayout(getContext());
            String inflater = Context.LAYOUT_INFLATER_SERVICE;
            LayoutInflater li = (LayoutInflater) getContext().getSystemService(inflater);
            li.inflate(resource, dishView, true);


        } else {
            dishView = (LinearLayout) convertView;
        }

        //ListView dishListView = (ListView) dishView.findViewById(R.id.boklist_View);

        TextView titleView = (TextView) dishView.findViewById(R.id.dishlist_item_title);
        TextView descView = (TextView) dishView.findViewById(R.id.dishlist_item_description);
        titleView.setText(title);
        descView.setText(description);
        return dishView;
    }




    }

