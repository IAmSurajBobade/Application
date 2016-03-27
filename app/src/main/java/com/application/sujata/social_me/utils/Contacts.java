package com.application.sujata.social_me.utils;

import android.app.Activity;
import android.database.Cursor;
import android.provider.ContactsContract;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by sujata on 25/3/16.
 */
public class Contacts {
    Map<String,String> contactsNameMap;
    Map<String,String> contactsNoMap;

    Contacts(Activity activity){

        contactsNameMap = new HashMap<>();
        contactsNoMap = new HashMap<>();
        Cursor phones = activity.getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null,null,null, null);
        while (phones.moveToNext())
        {
            try{
                String name=phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
                String phoneNumber = phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                contactsNameMap.put(name,phoneNumber);
                contactsNoMap.put(phoneNumber,name);
            }
            catch(Exception e){
                e.printStackTrace();
            }

        }
        phones.close();
    }
    public List<String> getNames(){
        List<String> names = new ArrayList<String>();

        for (String item : contactsNoMap.values()) {
            names.add(item);
        }
        Collections.sort(names);
        return names;
    }
    public List<String> getMobileNos(){
        List<String> nos = new ArrayList<String>();

        for (String item : contactsNameMap.values()) {
            nos.add(item);
        }

        return nos;
    }
    public String getMobileNo(String name){
        return contactsNameMap.get(name);
    }
    public String getName(String no){
        return contactsNoMap.get(no);
    }
    public List<String> getMatchedNames(List<String> contacts){

        List<String> list = new ArrayList<>();
        for(String contact:contacts){
            list.add(getName(contact));
        }
        return list;
    }
}
