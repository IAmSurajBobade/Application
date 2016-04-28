package com.application.sujata.social_me.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.application.sujata.social_me.beans.MyPosts;
import com.application.sujata.social_me.beans.NotificationList;
import com.application.sujata.social_me.beans.ReceivedPost;
import com.application.sujata.social_me.beans.SentPost;
import com.application.sujata.social_me.networking.DBUtil;
import com.application.sujata.social_me.R;
import com.application.sujata.social_me.activities.lists.MainActivity_nav;

import java.util.List;

public class SentPostDetails extends AppCompatActivity {

    DBUtil db;
    SentPost post;
    TextView teventnm,tdatetime,tgoing,tnot,tmaybe,tdescr,tcateg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sent_post_details);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                redirectToMain();
            }
        });

        Bundle data = getIntent().getExtras();
        if(data == null)
            return;
        else{

            List<SentPost> posts=MyPosts.getPosts();
            post = posts.get(data.getInt("position"));
            setAllDetails();
        }

    }

    private void setAllDetails() {
        teventnm = (TextView)findViewById(R.id.eventName);
        tdatetime = (TextView)findViewById(R.id.eventDateTime);
        tgoing = (TextView)findViewById(R.id.going);
        tmaybe = (TextView)findViewById(R.id.maybe);
        tnot = (TextView)findViewById(R.id.not);
        tdescr = (TextView)findViewById(R.id.discription);
        tcateg = (TextView)findViewById(R.id.category);

        teventnm.setText(post.getEventName());
        tdatetime.setText(post.getEDatetime());
        tgoing.setText(""+post.getGoingCount());
        tmaybe.setText(""+post.getMayBeCount());
        tnot.setText(""+post.getNotCount());
        tcateg.setText(""+post.getCategory());
        try {
            Log.d("msg", "" + post.getGoingCount());
        }
        catch(Exception e){
            Log.d("msg1",""+e);
        }
        tdescr.setText(post.getEdesc());
    }

    public void redirectToMain(){
        Intent t =  new Intent(this,MainActivity_nav.class);
        startActivity(t);
        finish();
    }

}
