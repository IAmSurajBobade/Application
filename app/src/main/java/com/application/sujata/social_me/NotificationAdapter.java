package com.application.sujata.social_me;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

/**
 * Created by sujata on 27/3/16.
 */
public class NotificationAdapter extends ArrayAdapter<ReceivedPost> {

    public NotificationAdapter(Context context,List<ReceivedPost> posts) {
        super(context,R.layout.sent_post,posts);

    }

    @Override
    public View getView(final int position, View convertView, final ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(getContext());

        ReceivedPost post = getItem(position);

        int resource = R.layout.received_post;

        View custom_row = inflater.inflate(resource, parent, false);

        TextView eventName= (TextView) custom_row.findViewById(R.id.eventName);
        TextView eventTime = (TextView) custom_row.findViewById(R.id.time);


        TextView sender = (TextView) custom_row.findViewById(R.id.sender);
        sender.setText("Sender:" + post.getSender());
        custom_row.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent itent = new Intent(getContext(), ReceivedPostDetails.class);
                itent.putExtra("position", position);
                getContext().startActivity(itent);
            }
        });

        eventName.setText("Event Name:"+post.getEventName());
        eventTime.setText("Event Time:"+post.getEDatetime());

        if(post.getResponse()!=0){
            LinearLayout layout = (LinearLayout) custom_row.findViewById(R.id.backLayout);
            layout.setBackgroundColor(Color.YELLOW);
        }
        return custom_row;
    }
}
