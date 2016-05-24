package com.application.sujata.social_me.activities.lists;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import com.application.sujata.social_me.R;
import com.application.sujata.social_me.SettingsActivity;
import com.application.sujata.social_me.activities.EventActivity;
import com.application.sujata.social_me.activities.GroupActivity;
import com.application.sujata.social_me.activities.about_us;
import com.application.sujata.social_me.adapters.NotificationAdapter;
import com.application.sujata.social_me.beans.NotificationList;
import com.application.sujata.social_me.beans.Post;
import com.application.sujata.social_me.networking.DBUtil;
import com.application.sujata.social_me.utils.Config;

import java.util.List;

public class MainActivity_nav extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    DBUtil db;
    ListView list;
    List<Post> posts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_activity_nav);
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


        if(Config.UID==null)
            Config.UID = uid;
//        Toast.makeText(this, ""+ uid, Toast.LENGTH_LONG).show();
        try{
            list = (ListView) findViewById(R.id.log_list);
            db = new DBUtil(this);

            NotificationList.getInstance().clearNotifications();
            db.loadNotifications(Config.URL_NOTIFICATIONS + uid);

            DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
            ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                    this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
            drawer.setDrawerListener(toggle);
            toggle.syncState();
        }catch (Exception e){
            Toast.makeText(this, "Error while connection", Toast.LENGTH_LONG).show();
        }

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }


//Manually Defined Methods
    public void getLog(){
        NotificationAdapter adapter = new NotificationAdapter(this, NotificationList.getInstance().getNotifications());
        list.setAdapter(adapter);
    }

    public void redirectToAddGroup() {
        Intent t =  new Intent(this,GroupActivity.class);
        startActivity(t);
    }

    public void redirectToAddEvent(){
        Intent t =  new Intent(this,EventActivity.class);
        startActivity(t);
    }
    public void redirectToMyPosts(){
        Intent t =  new Intent(this,SentPostList.class);
        startActivity(t);
    }
    public void redirectToAboutUs(){
        Intent t =  new Intent(this,about_us.class);
        startActivity(t);
    }
    public void redirectToSettings(){
        Intent t =  new Intent(this,SettingsActivity.class);
        startActivity(t);
    }
    public void redirectToShare(){
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_SUBJECT, "Social Me");
        String sAux = "\nLet me recommend you this application\n\n";
        sAux = sAux + "https://play.google.com/store/apps/details?id=Orion.Soft \n\n";
        intent.putExtra(Intent.EXTRA_TEXT, sAux);
        startActivity(Intent.createChooser(intent, "choose one"));
    }
    public void redirectToFeedback(){
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setType("message/rfc822");
  //      intent.putExtra(Intent.EXTRA_EMAIL, "suraj.bobade@outlook.com");
        intent.setData(Uri.parse("mailto:suraj.bobade@outlook.com"));
        intent.putExtra(Intent.EXTRA_SUBJECT, "Feedback for social Me");
        intent.putExtra(Intent.EXTRA_TEXT, "/n/n/n-Feedback to social Me.");
        startActivity(Intent.createChooser(intent, "Send Email"));
    }

    //Predefined Methods
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_activity_nav, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            redirectToSettings();
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_add_group) {
            redirectToAddGroup();

        } else if (id == R.id.nav_my_posts){
            redirectToMyPosts();

        } else if (id == R.id.nav_aboutus) {
            redirectToAboutUs();

        } else if (id == R.id.nav_manage) {
                redirectToSettings();

        } else if (id == R.id.nav_share) {
            redirectToShare();

        } else if (id == R.id.nav_feedback) {
            redirectToFeedback();
        }

    DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}