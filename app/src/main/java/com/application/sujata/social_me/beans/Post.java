package com.application.sujata.social_me.beans;

import com.application.sujata.social_me.utils.Config;

import java.util.HashMap;


public class Post {


    private HashMap<String,String> eventDetails;

    Post(){}

    Post(HashMap<String,String> eventDetails){
        this.eventDetails = eventDetails;
    }

    public String getEventName(){
        return eventDetails.get(Config.KEY_ENAME).trim();
    }
    public String getEdesc() {
        return eventDetails.get(Config.KEY_EDESC).trim();
    }
    public String getCategory() {
        return eventDetails.get(Config.KEY_CATEGORY).trim();
    }
    public String getGname() {
        return eventDetails.get(Config.KEY_GROUPNAME).trim();
    }
    public String getEDatetime() {
        return eventDetails.get(Config.KEY_ETIME).trim();
    }
    public String getGeneratedDateTime(){
        return eventDetails.get(Config.KEY_EGENERATED).trim();
    }
}
