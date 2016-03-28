package com.application.sujata.social_me.activities.lists;

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

import com.application.sujata.social_me.about_us;
import com.application.sujata.social_me.beans.NotificationList;
import com.application.sujata.social_me.utils.Config;
import com.application.sujata.social_me.networking.DBUtil;
import com.application.sujata.social_me.adapters.NotificationAdapter;
import com.application.sujata.social_me.R;
import com.application.sujata.social_me.activities.EventActivity;
import com.application.sujata.social_me.activities.GroupActivity;
import com.application.sujata.social_me.beans.Post;

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
        String uid = pref.getString("uid", null);

        //Cache ch = new Cache(pref);
        //ch.putData("uid",17+"");
        if(Config.UID==null)
            Config.UID = uid;
        Toast.makeText(this, "TOAST",Toast.LENGTH_LONG).show();

        list = (ListView) findViewById(R.id.log_list);
        db = new DBUtil(this);


        NotificationList.getInstance().clearNotifications();
        db.loadNotifications(Config.URL_NOTIFICATIONS+uid);

    }
    public void getLog(){

        NotificationAdapter adapter = new NotificationAdapter(this, NotificationList.getInstance().getNotifications());
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
            redirectToMyPosts();
        }
        else if(id == R.id.title_activity_about_us){
            redirectToAboutUs();
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
    public void redirectToMyPosts(){
        Intent t =  new Intent(this,SentPostList.class);
        startActivity(t);
        finish();
    }
    public void redirectToAboutUs(){
        Intent t =  new Intent(this,about_us.class);
        startActivity(t);
        finish();
    }

}
