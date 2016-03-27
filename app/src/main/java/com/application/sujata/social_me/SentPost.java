package com.application.sujata.social_me;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by sujata on 26/3/16.
 */
public class SentPost extends Post{

    private int noresponse;
    private List<String> goingList,mayBeList,notList;

    public SentPost(HashMap<String, String> postDetails, int noresponse){
        super(postDetails);

        this.noresponse = noresponse;
        goingList = new ArrayList<>();
        mayBeList = new ArrayList<>();
        notList = new ArrayList<>();

    }

    public void addToGoingList(String member){

    }
    public int getGoingCount(){
        return goingList.size();
    }
    public int getMayBeCount(){
        return mayBeList.size();
    }
    public int getNotCount(){
        return notList.size();
    }
    public int getNoResponseCount(){
        return noresponse;
    }

}
