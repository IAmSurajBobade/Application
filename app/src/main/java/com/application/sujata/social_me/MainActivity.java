package com.application.sujata.social_me;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.application.sujata.social_me.post.eventdetails.PostData;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    DBUtil db;
    ListView list;
    List<Post> posts;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                redirectToAddEvent();
            }
        });


        SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", MODE_PRIVATE);
        String uid = pref.getString("uid",null);

        if(Config.UID==null)
            Config.UID = uid;
        Toast.makeText(this, Config.URL_NOTIFICATIONS+uid,Toast.LENGTH_LONG).show();

        list = (ListView) findViewById(R.id.log_list);
        db = new DBUtil(this);


        db.loadNotifications(Config.URL_NOTIFICATIONS+uid);

    }
    public void getLog(){

        NotificationAdapter adapter = new NotificationAdapter(this, Notification.getNotifications());
        list.setAdapter(adapter);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        if(item.isChecked())
            item.setCheckable(false);
        else
            item.setChecked(true);


        if(id==R.id.add_group){
            redirectToAddGroup();
        }
        else if(id==R.id.myposts){

        }
        return true;
    }

    public void redirectToAddGroup() {
        Intent t =  new Intent(this,GroupActivity.class);
        startActivity(t);
        finish();
    }
    public void redirectToAddEvent(){
        Intent t =  new Intent(this,EventActivity.class);
        startActivity(t);
        finish();
    }
}
