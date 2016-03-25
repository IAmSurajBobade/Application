package com.application.sujata.social_me;


import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.List;

/**
 * Created by sujata on 22/3/16.
 */
public class DBUtil {


   Cache cache;
    Activity activity;

    DBUtil(Activity activity){
        this.activity = activity;
    }
    DBUtil(Activity activity ,Cache cache)
    {
        this.activity = activity;
        this.cache = cache;
    }


    public void register(final String fname,final String lname,final String mobile,final String email,final String DOB){
        class Registration extends AsyncTask<Void,Void,String> {

            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(activity, "Registering...", "Wait...", false, false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(activity, s, Toast.LENGTH_LONG).show();
                if(!s.equals("network error")){

                    String keys[]={Config.KEY_FNAME,Config.KEY_LNAME,Config.KEY_EMAIL,Config.KEY_EMAIL,Config.KEY_DOB};
                    String values[]={fname,lname,email,mobile,DOB};

                    cache.putData(keys,values);
                    cache.putData("registered","yes");
                    getUID(email);
                }
            }

            @Override
            protected String doInBackground(Void... v) {
                HashMap<String, String> params = new HashMap<>();
                params.put(Config.KEY_FNAME, fname);
                params.put(Config.KEY_LNAME, lname);
                params.put(Config.KEY_DOB, DOB);
                params.put(Config.KEY_EMAIL, email);
                params.put(Config.KEY_MOBILE, mobile);

                RequestHandler rh = new RequestHandler();
                String res = rh.sendPostRequest(Config.URL_REGISTER, params);

                return res;
            }
        }

        Registration ae = new Registration();
        ae.execute();



    }
    public void getUID(final String email){

        class User extends AsyncTask<Void,Void,String> {

            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(activity, "Loading...", "Wait...", false, false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                cache.putData("uid", s);
                try{
                    ((Register) activity).redirectToMain();
                }catch(ClassCastException e){
                    e.printStackTrace();
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
                        Toast.makeText(activity, error.getMessage().toString(), Toast.LENGTH_LONG).show();
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
                ((EventActivity) activity).setCategoryList(list);

        }
        else if(activity instanceof GroupActivity){
            ((GroupActivity) activity).setContactsRegistered(list);
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

}
