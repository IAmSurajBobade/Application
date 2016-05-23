package com.application.sujata.social_me.beans;


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

    private String name, mobile;
    public MemberInfo(String name,String mobile){
        this.name = name;
        this.mobile = mobile;
    }

    public String toString(){
        return name+" "+mobile;
    }
}
