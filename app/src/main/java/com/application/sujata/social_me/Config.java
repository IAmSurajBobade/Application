package com.application.sujata.social_me;

/**
 * Created by sujata on 22/3/16.
 */
public class Config {

    //Address of our scripts of the CRUD
    public static final String URL_REGISTER="https://social-me-regotiss.c9users.io/application/AddUser.php";
    public static final String URL_GET_UID = "https://social-me-regotiss.c9users.io/application/finduid.php?email=";
    public static final String URL_GET_CATEGORIES = "https://social-me-regotiss.c9users.io/application/GetCategories.php";
    public static final String URL_UPDATE_EMP = "http://192.168.94.1/Android/CRUD/updateEmp.php";
    public static final String URL_DELETE_EMP = "http://192.168.94.1/Android/CRUD/deleteEmp.php?id=";

    //Keys that will be used to send the request to php scripts
    public static final String KEY_FNAME = "fname";
    public static final String KEY_LNAME = "lname";
    public static final String KEY_MOBILE = "mobile";
    public static final String KEY_EMAIL = "email";
    public static final String KEY_DOB = "DOB";
    public static final String KEY_CATEGORY = "category_name";

    //JSON Tags
    public static final String TAG_JSON_ARRAY="result";
    public static final String TAG_ID = "id";
    public static final String TAG_NAME = "name";
    public static final String TAG_DESG = "desg";
    public static final String TAG_SAL = "salary";

    //employee id to pass with intent
    public static final String EMP_ID = "emp_id";

}
