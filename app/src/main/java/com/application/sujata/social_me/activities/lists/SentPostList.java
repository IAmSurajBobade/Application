package com.application.sujata.social_me.activities.lists;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ListView;

import com.application.sujata.social_me.utils.Config;
import com.application.sujata.social_me.networking.DBUtil;
import com.application.sujata.social_me.R;
import com.application.sujata.social_me.adapters.CustomeSentPostAdapter;
import com.application.sujata.social_me.beans.MyPosts;

public class SentPostList extends AppCompatActivity {

    DBUtil db;
    ListView list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sent_post_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        list = (ListView) findViewById(R.id.listView);
        db = new DBUtil(this);
        MyPosts.clearPosts();
        db.loadMyPosts(Config.URL_EVENt_REPORT+Config.UID);
    }
    public void setList(){

        CustomeSentPostAdapter adapter = new CustomeSentPostAdapter(this, MyPosts.getPosts());
        list.setAdapter(adapter);
    }
}
