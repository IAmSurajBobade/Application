package com.application.sujata.social_me;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Cache cache;
    DBUtil db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cache = new Cache(getApplicationContext().getSharedPreferences("MyPref", MODE_PRIVATE));

        db = new DBUtil(cache);
        String uid = cache.getValue("uid");
        Toast.makeText(MainActivity.this, uid, Toast.LENGTH_LONG).show();

    }
}
