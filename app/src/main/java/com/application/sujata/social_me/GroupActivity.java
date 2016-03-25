package com.application.sujata.social_me;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class GroupActivity extends AppCompatActivity {


    ListView list;
    DBUtil db;
    List<String> names;
    EditText tGroupName;
    Contacts contacts;
    Cache cache;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                redirectToMain();
            }
        });


        cache = new Cache(getApplicationContext().getSharedPreferences("MyPref", MODE_PRIVATE));

        tGroupName = (EditText) findViewById(R.id.tGroupName);
        list = (ListView) findViewById(R.id.list);
        list.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        list.requestFocus();

        contacts =new Contacts(this);

        db = new DBUtil(this);
        db.loadListData(Config.URL_GET_REGISTERED_MOBILE_NOS,Config.KEY_MOBILE);


    }
    public void loadListContacts(ArrayAdapter<String> v){

        list.setAdapter(v);
    }
    public void setGroupListView(List<String> list){
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_checked,list);
        loadListContacts(adapter);
    }

    public void redirectToMain(){
        Intent t =  new Intent(this,MainActivity.class);
        startActivity(t);
        finish();
    }

    //registered:- all contacts registered on server may on user hava in contact list
    public void setContactsRegistered(List<String> registered){

        registered.retainAll(contacts.getMobileNos());

        names = contacts.getMatchedNames(registered);

        setGroupListView(names);
    }

    public void saveGroup(View v){
        SparseBooleanArray array = list.getCheckedItemPositions();
        String nos = "";
        if(array.size() >= 1) {

            for(int i=0;i< names.size();i++) {
                if(array.get(i)){
                    if(i==0)
                        nos+=contacts.getMobileNo(names.get(i));
                    else
                        nos+=","+contacts.getMobileNo(names.get(i));
                }
            }

            Toast.makeText(this,"numbers:"+nos,Toast.LENGTH_LONG).show();
            db.addGroupListIntoDB(cache.getValue("uid"), tGroupName.getText().toString(), nos);
        }
        else{
            Toast.makeText(this,"Please select contacts",Toast.LENGTH_LONG).show();
        }

    }

    public void afterAddingGroup(String resp){

        new AlertDialog.Builder(this)
                .setTitle("Result")
                .setMessage(resp)
                .setPositiveButton("OK", new DialogInterface.OnClickListener(){

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        redirectToMain();
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_info)
                .show();

    }

}
