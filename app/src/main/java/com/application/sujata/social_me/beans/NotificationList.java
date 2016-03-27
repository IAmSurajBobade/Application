package com.application.sujata.social_me.beans;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sujata on 27/3/16.
 */

//SingleTon Class
public class NotificationList {
    private static List<ReceivedPost> notifications;
    private static NotificationList list;
    private NotificationList(){
        notifications=new ArrayList<>();
    }
    public static NotificationList getInstance(){
        if(list==null)
            list=new NotificationList();
        return list;
    }
    public void setNotifications(List<ReceivedPost> posts){
        notifications = posts;
    }
    public List<ReceivedPost> getNotifications(){
        return notifications;
    }
    public void addToNotification(ReceivedPost notification){
        notifications.add(notification);
    }
    public void clearNotifications(){
        notifications.clear();
    }
}
