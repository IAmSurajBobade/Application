package com.application.sujata.social_me;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Register extends AppCompatActivity {

    EditText tfname,tlname,tmobile,tDOB,temail;
    Cache cache;

    DBUtil db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        cache = new Cache(getApplicationContext().getSharedPreferences("MyPref", MODE_PRIVATE));

        //cache.clearCache();
        db = new DBUtil(this,cache);

        String registered = cache.getValue("registered");

        if(registered==null||!registered.equals("yes")) {

            setContentView(R.layout.activity_register);

        }
        else{

          redirectToMain();

        }
    }


    public void register(View v){
        tfname = (EditText) findViewById(R.id.tfname);
        tlname = (EditText) findViewById(R.id.tlname);
        temail = (EditText) findViewById(R.id.temail);
        tDOB = (EditText) findViewById(R.id.tDOB);
        tmobile = (EditText) findViewById(R.id.tmobile);

        String fname = tfname.getText().toString();
        String lname = tlname.getText().toString();
        String email = temail.getText().toString();
        String mobile = tmobile.getText().toString();
        String DOB = tDOB.getText().toString();


        db.register(fname, lname, mobile, email, DOB);

    }

    public void redirectToMain() {
        Intent t =  new Intent(this,MainActivity.class);
        startActivity(t);
        finish();
    }
}
