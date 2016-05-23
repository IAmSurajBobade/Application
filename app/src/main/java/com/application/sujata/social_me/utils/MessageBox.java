package com.application.sujata.social_me.utils;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;


public class MessageBox extends AlertDialog.Builder{

    public MessageBox(Context context,String message,DialogInterface.OnClickListener listener){
        super(context);
        setTitle("Result");
        setMessage(message);
        setPositiveButton("OK", listener);
        setIcon(android.R.drawable.ic_dialog_info);
        show();
    }
}
