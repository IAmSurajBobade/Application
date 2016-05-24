package com.application.sujata.social_me.activities.lists;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.application.sujata.social_me.R;
import com.application.sujata.social_me.beans.MemberInfo;
import com.application.sujata.social_me.beans.MyPosts;
import com.application.sujata.social_me.beans.SentPost;
import com.application.sujata.social_me.utils.Config;

import java.util.ArrayList;
import java.util.List;

public class ResponseList extends AppCompatActivity {

    ListView list;
    int postNo;
    TextView status, count;
    int option;
    SentPost post;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_response_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        status = (TextView)findViewById(R.id.status);
        count = (TextView)findViewById(R.id.count);
        list=(ListView) findViewById(R.id.responseList);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_SUBJECT, "Social Me");
                String sAux = "\n\n\n";
                sAux = sAux + "https://play.google.com/store/apps/details?id=Orion.Soft \n\n";
                intent.putExtra(Intent.EXTRA_TEXT, sAux);
                startActivity(Intent.createChooser(intent, "choose one"));
            }
        });
        Bundle data = getIntent().getExtras();
        if(data == null)
            return;
        else{

            List<SentPost> posts= MyPosts.getPosts();
            postNo = data.getInt("position");
            post = posts.get(postNo);

            option = data.getInt("option");
            setAllDetails();
        }


    }
    public void setAllDetails(){

        List<MemberInfo> info=null;
        String s="";
        switch(option){
            case Config.TAG_GOING :
                            info= post.getGoingList();
                            s="Coming People";
                            break;
            case Config.TAG_MAYBE :
                            info = post.getMayBeList();
                            s="Confused People";
                            break;
            case Config.TAG_NOT :
                            info = post.getNotList();
                            s="People Not Coming";
                            break;
        }
        status.setText(s);
        s="\nCount: "+info.size();
        count.setText(s);
        setListView(getList(info));

    }
    public List<String> getList(List<MemberInfo> list){

        List<String> listFinal =new ArrayList<>();
        for(MemberInfo s:list){
            listFinal.add(s.toString());
        }
        return listFinal;
    }

    public void loadListContacts(ArrayAdapter<String> v){
        list.setAdapter(v);
    }

    public void setListView(List<String> list){
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,list);
        loadListContacts(adapter);
    }
}
