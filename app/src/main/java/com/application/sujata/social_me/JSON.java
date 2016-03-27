package com.application.sujata.social_me;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

/**
 * Created by sujata on 24/3/16.
 */
public class JSON {



    private JSONArray list = null;

    private String json;

    public JSON(String json){
        this.json = json;
    }

    protected List<String> parseJSON(String attr) {


        List<String> categories = new ArrayList<>();
        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject(json);
            list = jsonObject.getJSONArray(Config.TAG_JSON_ARRAY);


            for(int i=0;i< list.length();i++){
                JSONObject jo = list.getJSONObject(i);
                String category = jo.getString(attr);
                categories.add(category);

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return categories;
    }

    protected void parseJSONForNotifications() {


        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject(json);
            list = jsonObject.getJSONArray(Config.TAG_JSON_ARRAY);


            for(int i=0;i< list.length();i++){
                JSONObject jo = list.getJSONObject(i);

                HashMap<String,String> data = new HashMap<>();
                data.put(Config.KEY_ENAME,jo.getString(Config.KEY_ENAME));
                data.put(Config.KEY_ETIME,jo.getString(Config.KEY_ETIME));

                data.put(Config.KEY_EDESC, jo.getString(Config.KEY_EDESC));

                ReceivedPost post = new ReceivedPost(data,jo.getString(Config.KEY_SENDER),jo.getString(Config.KEY_MOBILE));
                Notification.addToNotification(post);

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}
