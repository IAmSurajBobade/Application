package com.application.sujata.social_me;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sujata on 24/3/16.
 */
public class CategoryJSON {



    private JSONArray categoryList = null;

    private String json;

    public CategoryJSON(String json){
        this.json = json;
    }

    protected List<String> parseJSON() {


        List<String> categories = new ArrayList<>();
        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject(json);
            categoryList = jsonObject.getJSONArray(Config.TAG_JSON_ARRAY);


            for(int i=0;i<categoryList.length();i++){
                JSONObject jo = categoryList.getJSONObject(i);
                String category = jo.getString(Config.KEY_CATEGORY);
                categories.add(category);

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return categories;
    }
}
