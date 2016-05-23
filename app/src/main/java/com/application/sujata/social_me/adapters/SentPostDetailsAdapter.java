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
import com.application.sujata.social_me.beans.MyPosts;
import com.application.sujata.social_me.beans.SentPost;

import java.util.List;

/**
 * Created by Suraj on 04/15/2016.
 */
public class SentPostDetailsAdapter extends ArrayAdapter<MyPosts> {
    public SentPostDetailsAdapter(Context context,List<MyPosts> posts) {
        super(context, R.layout.content_sent_post_details,posts);

    }
    @Override
    public View getView(final int position, View convertView, final ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(getContext());

        MyPosts post = getItem(position);

        int resource = R.layout.content_sent_post_details;

        View custom_row = inflater.inflate(resource, parent, false);

        TextView eventName= (TextView) custom_row.findViewById(R.id.eventName);
        TextView eventTime = (TextView) custom_row.findViewById(R.id.time);
        TextView response_going = (TextView) custom_row.findViewById(R.id.going);
        TextView response_maybe = (TextView) custom_row.findViewById(R.id.maybe);
        TextView response_not = (TextView) custom_row.findViewById(R.id.not);
        TextView category = (TextView) custom_row.findViewById(R.id.category);
        TextView discription = (TextView) custom_row.findViewById(R.id.discription);


        eventName.setText(post.getEventName());
        eventTime.setText(post.getEDatetime());
        response_going.setText(post.getGoingCount());
        response_maybe.setText(post.getMayBeCount());
        response_not.setText(post.getNotCount());
        category.setText(post.getCategory());
        discription.setText(post.getEdesc());
        return custom_row;
    }
}

