package com.application.sujata.social_me;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by sujata on 27/3/16.
 */
public class GoingFragment extends Fragment {
    @Override

    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.layout_going, container, false);
        return rootView;

    }

}
