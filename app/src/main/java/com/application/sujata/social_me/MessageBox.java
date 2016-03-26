package com.application.sujata.social_me;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

/**
 * Created by sujata on 26/3/16.
 */
public class MessageBox extends AlertDialog.Builder{

    MessageBox(Context context,String message,DialogInterface.OnClickListener listener){
        super(context);
        setTitle("Result");
        setMessage(message);
        setPositiveButton("OK", listener);
        setIcon(android.R.drawable.ic_dialog_info);
        show();
    }
}
