package com.application.sujata.social_me.beans;

/**
 * Created by sujata on 27/3/16.
 */
public class MemberInfo {
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    private String name,mobile;
    MemberInfo(String name,String mobile){
        this.name = name;
        this.mobile = mobile;
    }
}
