package com.TabExample3;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.eduadministrative.mytabs.R;

public class Fragment_Call extends Fragment {

    View v;

    public Fragment_Call(){}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        v=inflater.inflate(R.layout.call_fragment, container, false);

        return v;
    }
}