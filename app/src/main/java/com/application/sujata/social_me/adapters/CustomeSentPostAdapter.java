package com.application.sujata.social_me.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.application.sujata.social_me.R;
import com.application.sujata.social_me.activities.SentPostDetails;
import com.application.sujata.social_me.beans.SentPost;

import java.util.List;

/**
 * Created by sujata on 27/3/16.
 */
public class CustomeSentPostAdapter extends ArrayAdapter<SentPost> {
    public CustomeSentPostAdapter(Context context,List<SentPost> posts) {
        super(context, R.layout.sent_post,posts);

    }
    @Override
    public View getView(final int position, View convertView, final ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(getContext());

        SentPost post = getItem(position);

        int resource = R.layout.sent_post;

        View custom_row = inflater.inflate(resource, parent, false);

        TextView eventName= (TextView) custom_row.findViewById(R.id.eventName);
        TextView eventTime = (TextView) custom_row.findViewById(R.id.time);
        TextView response = (TextView) custom_row.findViewById(R.id.responses);

        response.setText("Responses:\nGoing :"+ post.getGoingCount()+" May Be:"+post.getMayBeCount()+" Not:"+post.getNotCount());
        custom_row.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent itent = new Intent(getContext(),SentPostDetails.class);
                    itent.putExtra("position",position);
                    getContext().startActivity(itent);
                }
            });

        eventName.setText("Event Name:" + post.getEventName());
        eventTime.setText("Event Time:" + post.getEDatetime());
        return custom_row;
    }
}
