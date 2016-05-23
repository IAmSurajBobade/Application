package com.application.sujata.social_me.beans;

import java.util.HashMap;


public class ReceivedPost extends Post{

    private String sender,mobile;

    public int getResponse() {
        return response;
    }

    public void setResponse(int response) {
        this.response = response;
    }

    private int response;
    public ReceivedPost(HashMap<String, String> postDetails, String sender,String mobile){
        super(postDetails);
        this.sender = sender;
        this.mobile = mobile;
    }

    public String getSender(){
        return sender;
    }
    public String getMobile(){
        return mobile;
    }



}
