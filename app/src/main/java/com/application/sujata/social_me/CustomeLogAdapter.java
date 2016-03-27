package com.application.sujata.social_me;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by sujata on 26/3/16.
 */
public class CustomeLogAdapter extends ArrayAdapter<Post>{


    public CustomeLogAdapter(Context context,List<Post> posts) {
        super(context,R.layout.sent_post,posts);

    }

    @Override
    public View getView(final int position, View convertView, final ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(getContext());

        Post post = getItem(position);

        int resource;
        if(post instanceof SentPost)
            resource = R.layout.sent_post;
        else
            resource = R.layout.received_post;

        View custom_row = inflater.inflate(resource, parent, false);

        TextView eventName= (TextView) custom_row.findViewById(R.id.eventName);
        TextView eventTime = (TextView) custom_row.findViewById(R.id.time);

        if(post instanceof  SentPost) {
            TextView response = (TextView) custom_row.findViewById(R.id.responses);
            response.setText("Responses:Going :"+((SentPost) post).getGoingCount()+" May Be:"+
                    ((SentPost) post).getMayBeCount()+" Not:"+((SentPost) post).getNotCount());
            custom_row.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent itent = new Intent(getContext(),SentPostDetails.class);
                    itent.putExtra("position",position);
                    getContext().startActivity(itent);
                }
            });
        }
        else
        {
            TextView sender = (TextView) custom_row.findViewById(R.id.sender);
            sender.setText("Sender:" + ((ReceivedPost) post).getSender());
            custom_row.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent itent = new Intent(getContext(), ReceivedPostDetails.class);
                    itent.putExtra("position", position);
                    getContext().startActivity(itent);
                }
            });
        }
        eventName.setText("Event Name:"+post.getEventName());
        eventTime.setText("Event Time:"+post.getEDatetime());
        return custom_row;
    }
}
