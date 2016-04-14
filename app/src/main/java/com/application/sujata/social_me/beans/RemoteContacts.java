package com.application.sujata.social_me.beans;

import java.util.ArrayList;
import java.util.List;


public class RemoteContacts {
    private static List<MemberInfo> contacts;

    static {
        contacts = new ArrayList<>();
    }
    public static void setContacts(List<MemberInfo> contcts){
        contacts = contcts;
    }
    public static List<MemberInfo> getContacts(){
        return contacts;
    }
    public static void addToContacts(MemberInfo post){
        contacts.add(post);
    }
    public static void clearContacts(){
        contacts.clear();
    }
}
