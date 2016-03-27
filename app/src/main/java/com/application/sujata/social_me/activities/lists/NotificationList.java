package com.application.sujata.social_me.activities.lists;

import com.application.sujata.social_me.beans.ReceivedPost;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sujata on 27/3/16.
 */
public class NotificationList {
    private static List<ReceivedPost> notifications;

    static {
        notifications = new ArrayList<>();
    }
    public static void setNotifications(List<ReceivedPost> posts){
        notifications = posts;
    }
    public static List<ReceivedPost> getNotifications(){
        return notifications;
    }
    public static void addToNotification(ReceivedPost notification){
        notifications.add(notification);
    }
    public static void clearNotifications(){
        notifications.clear();
    }
}
