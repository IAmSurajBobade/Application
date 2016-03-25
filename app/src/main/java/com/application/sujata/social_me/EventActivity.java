package com.application.sujata.social_me;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.List;


public class EventActivity extends AppCompatActivity {

    Cache cache;
    DBUtil db;

    Spinner categoriesSpinner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);

        cache = new Cache(getApplicationContext().getSharedPreferences("MyPref", MODE_PRIVATE));

        db = new DBUtil(this,cache);
        String uid = cache.getValue("uid");
        Toast.makeText(EventActivity.this, uid, Toast.LENGTH_LONG).show();

        categoriesSpinner = (Spinner) findViewById(R.id.category_spinner);

        db.loadListData(Config.URL_GET_CATEGORIES,Config.KEY_CATEGORY);
        db.loadListData(Config.URL_GET_GROUPS,Config.KEY_UID);

    }

    public void setCategorySpinner(ArrayAdapter<String> adapter){
        categoriesSpinner.setAdapter(adapter);
    }
    public void setCategoryList(List<String> categories){

        // Creating adapter for categories
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories);
        setCategorySpinner(dataAdapter);

    }
    public void setGroupList(List<String> groups){

        // Creating adapter for groups
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, groups);
        setCategorySpinner(dataAdapter);
    }

    public void postEvent(View v){

    }
}
