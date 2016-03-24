package com.application.sujata.social_me;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.List;


public class MainActivity extends AppCompatActivity {

    Cache cache;
    DBUtil db;

    Spinner categoriesSpinner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cache = new Cache(getApplicationContext().getSharedPreferences("MyPref", MODE_PRIVATE));

        db = new DBUtil(this,cache);
        String uid = cache.getValue("uid");
        Toast.makeText(MainActivity.this, uid, Toast.LENGTH_LONG).show();

        categoriesSpinner = (Spinner) findViewById(R.id.category_spinner);

        db.getCategories();

    }

    public void setCategorySpinner(ArrayAdapter<String> adapter){
        categoriesSpinner.setAdapter(adapter);
    }

}
