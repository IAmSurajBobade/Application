package com.application.sujata.social_me.utils;

import java.util.List;


public class Config {

    //Address of our scripts of the CRUD
    public static final String URL_REGISTER="https://social-me-regotiss.c9users.io/application/AddUser.php";
    public static final String URL_GET_UID = "https://social-me-regotiss.c9users.io/application/finduid.php?email=";
    public static final String URL_GET_CATEGORIES = "https://social-me-regotiss.c9users.io/application/GetCategories.php";
    public static final String URL_GET_REGISTERED_MOBILE_NOS = "https://social-me-regotiss.c9users.io/application/GetRegisteredContacts.php";
    public static final String URL_ADD_GROUP = "https://social-me-regotiss.c9users.io/application/AddGroup.php";
    public static final String URL_GET_GROUPS = "https://social-me-regotiss.c9users.io/application/GetGroups.php?uid=";
    public static final String URL_SAVE_EVENT = "https://social-me-regotiss.c9users.io/application/AddEvent.php";
    public static final String URL_NOTIFICATIONS = "https://social-me-regotiss.c9users.io/application/Notification.php?uid=";
    public static final String URL_ADD_RESPONSE = "https://social-me-regotiss.c9users.io/application/AddResponse.php";
    public static final String URL_EVENt_REPORT = "https://social-me-regotiss.c9users.io/application/EventReport.php?uid=";
    public static final String URL_ALL_CONTACTS = "https://social-me-regotiss.c9users.io/application/GetAllContacts.php?uid=";
    public static final String URL_RESPONSE_LIST= "https://social-me-regotiss.c9users.io/application/GetResponseList.php?uid=,response=";

    //Keys that will be used to send the request to php scripts : table User
    public static final String KEY_FNAME = "fname";
    public static final String KEY_LNAME = "lname";
    public static final String KEY_MOBILE = "mobile";
    public static final String KEY_EMAIL = "email";
    public static final String KEY_DOB = "DOB";
    public static final String KEY_UID = "uid";

    //Keys of table Category
    public static final String KEY_CATEGORY = "category_name";

    public static final String KEY_RESPONSE = "response";

    //keys of table Group
    public static final String KEY_GROUPNAME = "gname";

    //keys of table Event
    public static final String KEY_ENAME = "ename";
    public static final String KEY_EDESC = "edescription";
    public static final String KEY_ETIME = "etime";
    public static final String KEY_EGENERATED = "egenerated";
    public static final String KEY_SENDER = "sender";

    //JSON Tagsd
    public static final String TAG_JSON_ARRAY="result";


    public static final int TAG_GOING=1;
    public static final int TAG_MAYBE=2;
    public static final int TAG_NOT=3;



    //employee id to pass with intent
    public static final String EMP_ID = "emp_id";


    //userid of current user
    public static String UID;


}
