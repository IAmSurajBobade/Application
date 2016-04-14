package com.application.sujata.social_me.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.application.sujata.social_me.utils.Cache;
import com.application.sujata.social_me.utils.Config;
import com.application.sujata.social_me.R;
import com.application.sujata.social_me.activities.lists.MainActivity_nav;
import com.application.sujata.social_me.networking.DBUtil;

import java.util.HashMap;

public class RegisterActivity extends AppCompatActivity {

    EditText tfname,tlname,tmobile,tDOB,temail;
    HashMap<String,String> userDetails;

    Cache cache;

    DBUtil db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        intilize();
    }

    private void intilize() {
        cache = new Cache(getApplicationContext().getSharedPreferences("MyPref", MODE_PRIVATE));
        //cache.clearCache();
        db = new DBUtil(this);

        String registered = cache.getValue("registered");

        if(registered==null||!registered.equals("yes")) {

            setContentView(R.layout.activity_register);
            userDetails = new HashMap<>();
            setReferncesToWidgets();

        }
        else{
            redirectToMain();
        }
    }

    private void setReferncesToWidgets() {
        tfname = (EditText) findViewById(R.id.tfname);
        tlname = (EditText) findViewById(R.id.tlname);
        temail = (EditText) findViewById(R.id.temail);
        tDOB = (EditText) findViewById(R.id.tDOB);
        tmobile = (EditText) findViewById(R.id.tmobile);
    }


    public void register(View v){

        userDetails.put(Config.KEY_FNAME, tfname.getText().toString());
        userDetails.put(Config.KEY_LNAME,tlname.getText().toString());
        userDetails.put(Config.KEY_EMAIL,temail.getText().toString());
        userDetails.put(Config.KEY_MOBILE,tmobile.getText().toString());
        userDetails.put(Config.KEY_DOB,tDOB.getText().toString());

        db.saveDataIntoDB(Config.URL_REGISTER,userDetails);

    }

    public void afterTryingToRegister(String response) {

        if (!response.equals("network error")) {

            cache.putData(userDetails);
            cache.putData("registered", "yes");
            db.getUID(userDetails.get(Config.KEY_EMAIL));

        }
    }
    public void redirectToMain() {
        Intent t =  new Intent(this,MainActivity_nav.class);
        startActivity(t);
        finish();
    }
    public void saveUID(String uid){
        cache.putData("uid",uid);
    }
}
