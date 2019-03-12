package com.TabExample3;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.eduadministrative.mytabs.R;

public class Fragment_Fav extends Fragment {

    View v;

    public Fragment_Fav() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fav_fragment, container, false);

        return v;
    }
}