package com.application.sujata.social_me.networking;


import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.application.sujata.social_me.utils.Config;
import com.application.sujata.social_me.activities.RegisterActivity;
import com.application.sujata.social_me.activities.EventActivity;
import com.application.sujata.social_me.activities.GroupActivity;
import com.application.sujata.social_me.activities.ReceivedPostDetails;
import com.application.sujata.social_me.activities.lists.MainActivity_nav;
import com.application.sujata.social_me.activities.lists.SentPostList;

import java.util.HashMap;
import java.util.List;


public class DBUtil {



    Activity activity;

    public DBUtil(Activity activity){
        this.activity = activity;
    }

    public void saveDataIntoDB(final String url,final HashMap<String,String> data){
        class Save extends  AsyncTask<Void,Void,String>{
            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(activity, "Saving...", "Wait...", false, false);
            }
            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(activity, s, Toast.LENGTH_LONG).show();
                Log.i("MyActivity", "sql: " + s);

                if(activity instanceof RegisterActivity)
                    ((RegisterActivity) activity).afterTryingToRegister(s);
                else if(activity instanceof EventActivity)
                    ((EventActivity) activity).afterTryingToSaveEvent(s);
                else if(activity instanceof ReceivedPostDetails)
                    ((ReceivedPostDetails)activity).afterTryingToSaveNotification(s);

            }
            @Override
            protected String doInBackground(Void... v) {

                RequestHandler rh = new RequestHandler();
                String res = rh.sendPostRequest(url, data);
                return res;
            }
        }

        Save s = new Save();
        s.execute();
    }


    public void getUID(final String email){

        class User extends AsyncTask<Void,Void,String> {

            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(activity, "Loading...", "Wait...", false, false);
            }
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();

                if(activity instanceof RegisterActivity){
                    ((RegisterActivity) activity).redirectToMain();
                    ((RegisterActivity) activity).saveUID(s);
                }
            }

            @Override
            protected String doInBackground(Void... v) {
                RequestHandler rh = new RequestHandler();
                String s = rh.sendGetRequestParam(Config.URL_GET_UID, email);
                return s;
            }
        }

        User ae = new User();

        ae.execute();

    }
    public void loadListData(String url,final String attr){

        final ProgressDialog loading = ProgressDialog.show(activity, "Please wait...", "Fetching...", false, false);

        //String url = Config.DATA_URL+editTextId.getText().toString().trim();

        StringRequest stringRequest = new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                loading.dismiss();
                setDataIntoList(response, attr);
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
//                        Toast.makeText(activity, error.getMessage().toString(), Toast.LENGTH_LONG).show();
                    }
                });

        RequestQueue requestQueue = Volley.newRequestQueue(activity);
        requestQueue.add(stringRequest);
    }
    public void setDataIntoList(String r,String attr){

        JSON pj = new JSON(r);
        List<String> list=pj.parseJSON(attr);

        if( activity instanceof EventActivity){
            if(attr.equals(Config.KEY_CATEGORY))
                ((EventActivity) activity).setCategoryList(list);
            else
                ((EventActivity) activity).setGroupList(list);

        }
        else if(activity instanceof GroupActivity){
            ((GroupActivity) activity).setGroupListView(list);
        }

    }
    public void addGroupListIntoDB(final String uid,final String gname,final String nos){
        class Group extends AsyncTask<Void,Void,String> {

            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(activity, "Saving...", "Wait...", false, false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(activity, s, Toast.LENGTH_LONG).show();

                ((GroupActivity) activity).afterAddingGroup(s);
            }

            @Override
            protected String doInBackground(Void... v) {
                HashMap<String, String> params = new HashMap<>();
                params.put(Config.KEY_UID, uid);
                params.put(Config.KEY_GROUPNAME, gname);
                params.put(Config.KEY_MOBILE, nos);

                RequestHandler rh = new RequestHandler();
                String res = rh.sendPostRequest(Config.URL_ADD_GROUP, params);

                return res;
            }
        }

        Group ae = new Group();
        ae.execute();

    }

    public void loadNotifications(final String url){

        final ProgressDialog loading = ProgressDialog.show(activity, "Please wait...", "Fetching...", false, false);

        //String url = Config.DATA_URL+editTextId.getText().toString().trim();

        StringRequest stringRequest = new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                loading.dismiss();
                setNotifications(response);
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
//                        Toast.makeText(activity, error.getMessage().toString(), Toast.LENGTH_LONG).show();
                        Log.d("Error",""+url+" "+error.getMessage().toString());
                    }
                });

        RequestQueue requestQueue = Volley.newRequestQueue(activity);
        requestQueue.add(stringRequest);
    }
    public void setNotifications(String r){

        JSON pj = new JSON(r);
        pj.parseJSONForNotifications();

        if(activity instanceof MainActivity_nav){
            ((MainActivity_nav) activity).getLog();
        }

    }


    public void loadMyPosts(String url){

        final ProgressDialog loading = ProgressDialog.show(activity, "Please wait...", "Fetching...", false, false);

        //String url = Config.DATA_URL+editTextId.getText().toString().trim();

        StringRequest stringRequest = new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                loading.dismiss();
                setMyPosts(response);
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
//                        Toast.makeText(activity, error.getMessage().toString(), Toast.LENGTH_LONG).show();
                    }
                });

        RequestQueue requestQueue = Volley.newRequestQueue(activity);
        requestQueue.add(stringRequest);
    }
    public void setMyPosts(String r){

        JSON pj = new JSON(r);
        pj.parseJSONForMyPosts();

        if(activity instanceof SentPostList){
            ((SentPostList) activity).setList();
        }


    }

}
