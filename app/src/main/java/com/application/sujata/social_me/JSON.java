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

    protected void parseJSONForMyPosts() {


        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject(json);
            list = jsonObject.getJSONArray(Config.TAG_JSON_ARRAY);


            for(int i=0;i< list.length();i++){
                JSONObject jo = list.getJSONObject(i);

                HashMap<String,String> data = new HashMap<>();
                data.put(Config.KEY_ENAME,jo.getString(Config.KEY_ENAME));
                data.put(Config.KEY_ETIME,jo.getString(Config.KEY_ETIME));
                data.put(Config.KEY_CATEGORY,jo.getString(Config.KEY_CATEGORY));
                data.put(Config.KEY_GROUPNAME,jo.getString(Config.KEY_GROUPNAME));
                data.put(Config.KEY_EDESC, jo.getString(Config.KEY_EDESC));


                SentPost post = new SentPost(data,jo.getInt("members"));
                JSONArray going=jo.getJSONArray("going");
                for(int j=0;j<going.length();j++){
                    JSONObject mem = going.getJSONObject(j);
                    post.addToGoingList(new MemberInfo(mem.getString("name"),mem.getString("mobile")));
                }

                JSONArray maybe=jo.getJSONArray("maybe");
                for(int j=0;j<maybe.length();j++){
                    JSONObject mem = maybe.getJSONObject(j);
                    post.addMayBeList(new MemberInfo(mem.getString("name"),mem.getString("mobile")));
                }

                JSONArray not=jo.getJSONArray("not");
                for(int j=0;j<not.length();j++){
                    JSONObject mem = not.getJSONObject(j);
                    post.addToNotList(new MemberInfo(mem.getString("name"),mem.getString("mobile")));
                }
                MyPosts.addToPosts(post);

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

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
