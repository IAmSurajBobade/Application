package com.application.sujata.social_me.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.application.sujata.social_me.networking.DBUtil;
import com.application.sujata.social_me.R;
import com.application.sujata.social_me.activities.lists.MainActivity_nav;

public class SentPostDetails extends AppCompatActivity {

    DBUtil db;
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

        setDetails();
    }

    private void setDetails() {

    }

    public void redirectToMain(){
        Intent t =  new Intent(this,MainActivity_nav.class);
        startActivity(t);
        finish();
    }

}
