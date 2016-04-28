package com.application.sujata.social_me.activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.application.sujata.social_me.utils.Cache;
import com.application.sujata.social_me.utils.Config;
import com.application.sujata.social_me.networking.DBUtil;
import com.application.sujata.social_me.utils.MessageBox;
import com.application.sujata.social_me.R;
import com.application.sujata.social_me.activities.lists.MainActivity_nav;

import java.util.HashMap;
import java.util.List;


public class EventActivity extends AppCompatActivity {

    Cache cache;
    DBUtil db;

    Spinner categoriesSpinner;
    Spinner groupsSpinner;
    EditText tEname,tEdescr,tEdate,tEtime;
    HashMap<String,String> eventDetails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);
        initlize();
    }

    private void initlize() {
        cache = new Cache(getApplicationContext().getSharedPreferences("MyPref", MODE_PRIVATE));

        db = new DBUtil(this);
        String uid = cache.getValue("uid");
        Toast.makeText(EventActivity.this, "**"+ Config.URL_GET_GROUPS+uid+"**", Toast.LENGTH_LONG).show();

        //getting references for widgets
        categoriesSpinner = (Spinner) findViewById(R.id.category_spinner);
        groupsSpinner = (Spinner) findViewById(R.id.group_spinner);
        tEname = (EditText) findViewById(R.id.tEventName);
        tEdescr = (EditText) findViewById(R.id.tEventDescription);
        tEdate = (EditText) findViewById(R.id.tEventDate);
        tEtime = (EditText) findViewById(R.id.tEventTime);

        eventDetails = new HashMap<>();

        db.loadListData(Config.URL_GET_GROUPS,Config.KEY_GROUPNAME);
        db.loadListData(Config.URL_GET_CATEGORIES,Config.KEY_CATEGORY);

    }

    public void setCategorySpinner(ArrayAdapter<String> adapter){
        categoriesSpinner.setAdapter(adapter);
    }
    public void setGroupsSpinner(ArrayAdapter<String> adapter){
        groupsSpinner.setAdapter(adapter);
    }
    public void setCategoryList(List<String> categories){

        // Creating adapter for categories
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, categories);
        setCategorySpinner(dataAdapter);

    }
    public void setGroupList(List<String> groups){

        // Creating adapter for groups
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, groups);
        setGroupsSpinner(dataAdapter);
    }

    public void postEvent(View v){
        savePost();
    }

    private void savePost() {
        eventDetails.put(Config.KEY_ENAME,tEname.getText().toString());
        eventDetails.put(Config.KEY_CATEGORY,categoriesSpinner.getSelectedItem().toString());
        eventDetails.put(Config.KEY_EDESC,tEdescr.getText().toString());
        eventDetails.put(Config.KEY_ETIME,tEdate.getText()+" "+tEtime.getText());
        eventDetails.put(Config.KEY_GROUPNAME,groupsSpinner.getSelectedItem().toString());
        eventDetails.put(Config.KEY_UID,cache.getValue("uid"));

        db.saveDataIntoDB(Config.URL_SAVE_EVENT, eventDetails);
    }

    public void afterTryingToSaveEvent(String response){
        if(response.contains("Saved")){
            new MessageBox(this, response, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    redirectToMain();
                }
            });
        }
    }
    public void redirectToMain(){
        Intent t =  new Intent(this,MainActivity_nav.class);
        startActivity(t);
        finish();
    }

}
