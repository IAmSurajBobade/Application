package com.application.sujata.social_me.post.eventdetails;

import com.application.sujata.social_me.Config;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.application.sujata.social_me.Post;
import com.application.sujata.social_me.ReceivedPost;
import com.application.sujata.social_me.SentPost;

/**
 * Created by sujata on 26/3/16.
 */
public class PostData {
    public static final List<Post> POSTS = new ArrayList<Post>();

    public static final Map<String, Post> POST_MAP = new HashMap<String, Post>();

    static HashMap<String,String> eventDetails=new HashMap<>();
    static {
        eventDetails.put(Config.KEY_ENAME,"Josh");
        eventDetails.put(Config.KEY_CATEGORY,"Movie");
        eventDetails.put(Config.KEY_EDESC,"Greate Moview...some other views");
        eventDetails.put(Config.KEY_ETIME,"2016-4-1 12:00:00");
        eventDetails.put(Config.KEY_GROUPNAME,"friends");

        addPost(createReceivedPost());
        addPost(createReceivedPost());
        addPost(createReceivedPost());
        addPost(createReceivedPost());
        addPost(createReceivedPost());

    }
    public static void addPost(Post p){
        POSTS.add(p);
        POST_MAP.put(p.getEventName(), p);
    }
    public static Post createSendPost(){
        return new SentPost(eventDetails,10);
    }
    public static Post createReceivedPost(){
        return new ReceivedPost(eventDetails,"Sujata","768768768");
    }
}
