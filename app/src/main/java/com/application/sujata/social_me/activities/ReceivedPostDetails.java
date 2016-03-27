package com.application.sujata.social_me.activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.application.sujata.social_me.utils.Config;
import com.application.sujata.social_me.networking.DBUtil;
import com.application.sujata.social_me.utils.MessageBox;
import com.application.sujata.social_me.R;
import com.application.sujata.social_me.activities.lists.MainActivity;
import com.application.sujata.social_me.beans.NotificationList;

import com.application.sujata.social_me.beans.ReceivedPost;

import java.util.HashMap;
import java.util.List;

public class ReceivedPostDetails extends AppCompatActivity {

    TextView tEventName,tSender,tEventTime,tEventDescr,tMobile;

    DBUtil db;
    RadioGroup group;
    ReceivedPost post;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_received_post_details);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                redirectToMain();
            }
        });
        initilize();


        Bundle data = getIntent().getExtras();
        if(data == null)
            return;
        else{

            List<ReceivedPost> posts = NotificationList.getInstance().getNotifications();
            post = posts.get(data.getInt("position"));
            setAllDetails(post);
        }
    }

    private void initilize() {

        db = new DBUtil(this);
        tEventName = (TextView) findViewById(R.id.eventName);
        tEventDescr = (TextView) findViewById(R.id.tEventDesc);
        tEventTime = (TextView) findViewById(R.id.eventDateTime);
        tSender = (TextView) findViewById(R.id.sender);
        tMobile = (TextView) findViewById(R.id.mobile);
        group = (RadioGroup) findViewById(R.id.responseGroup);
    }

    public void redirectToMain(){
        Intent t =  new Intent(this,MainActivity.class);
        startActivity(t);
        finish();
    }
    public void setAllDetails(ReceivedPost p){
        tEventName.setText("Event Name: "+p.getEventName());
        tEventDescr.setText("Event Description:\n"+p.getEdesc());
        tEventTime.setText("Event Time: "+p.getEDatetime());
        tSender.setText("Sender: " + p.getSender());
        tMobile.setText("Sender Mobile No: " + p.getMobile());
    }

    public void sendResponse(View v){
        HashMap<String,String> params = new HashMap<>();
        params.put(Config.KEY_ENAME, post.getEventName());
        params.put(Config.KEY_UID,Config.UID);
        int response=0;
        switch (group.getCheckedRadioButtonId()){
            case R.id.going :
                                response = Config.TAG_GOING;
                                break;

            case R.id.maybe :
                                response = Config.TAG_MAYBE;
                                break;

            case R.id.not :
                                response = Config.TAG_NOT;
                                break;

        }
        params.put(Config.KEY_RESPONSE, "" + response);
        Toast.makeText(this,response+"",Toast.LENGTH_LONG).show();
        db.saveDataIntoDB(Config.URL_ADD_RESPONSE, params);
        post.setResponse(response);

    }
    public void afterTryingToSaveNotification(String response){
        if(response.contains("Saved")){
            new MessageBox(this, response, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    redirectToMain();
                }
            });
        }
    }
}
