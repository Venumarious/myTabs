package com.TabExample3;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.eduadministrative.mytabs.R;

public class Fragment_Contact extends Fragment {

    View v;

    public Fragment_Contact() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.contact_fragment, container, false);
        Toast.makeText(getContext(), "Changed this toast just to see that it changes on GitHub", Toast.LENGTH_LONG).show();
        return v;
    }
}
