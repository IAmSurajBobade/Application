package com.application.sujata.social_me;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class MainActivity extends AppCompatActivity {

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
