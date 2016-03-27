package com.application.sujata.social_me.utils;

import android.content.SharedPreferences;

import java.util.Map;
import java.util.Set;

/**
 * Created by sujata on 23/3/16.
 */
public class Cache {

    SharedPreferences pref;
    SharedPreferences.Editor editor;

    public Cache(SharedPreferences pref){
        this.pref = pref;
        editor = pref.edit();
    }


    public void setSharedPreferences(SharedPreferences pref){
        this.pref = pref;
        editor = pref.edit();
    }
    public SharedPreferences getCache(){
        return pref;
    }

    public void putData(String keys[],String values[]) {

        if(keys.length!=values.length)
            return;

        for(int i=0;i<keys.length;i++){
            editor.putString(keys[i],values[i]);
        }
        editor.commit();

    }

    public void putData(String key ,String value){
        editor.putString(key,value);
        editor.commit();
    }

    public String getValue(String key){

        return  pref.getString(key,null);
    }
    public void clearCache(){
        editor.clear();
        editor.commit();
    }
    public void putData(Map<String,String> data){

        Set<String> keys = data.keySet();
        for(String key:keys){
            putData(key,data.get(key));
        }

    }
}
