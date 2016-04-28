package com.application.sujata.social_me.beans;

import com.application.sujata.social_me.beans.SentPost;

import java.util.ArrayList;
import java.util.List;

public class MyPosts extends SentPost{

    private static List<SentPost> myPosts;

    static {
        myPosts = new ArrayList<>();
    }

    public static void setPosts(List<SentPost> posts){
        myPosts = posts;
    }
    
    public static List<SentPost> getPosts(){
        return myPosts;
    }
    public static void addToPosts(SentPost post){
        myPosts.add(post);
    }
    public static void clearPosts(){
        myPosts.clear();
    }
}
