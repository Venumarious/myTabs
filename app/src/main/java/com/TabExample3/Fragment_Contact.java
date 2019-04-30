package com.TabExample3;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.adapter.ContactAdapter;
import com.arrayClass.Contact;
import com.eduadministrative.mytabs.R;

import java.util.ArrayList;
import java.util.List;

public class Fragment_Contact extends Fragment {

    View v;
    private RecyclerView rvCntct;
    private List<Contact> lstContact;

    public Fragment_Contact() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.contact_fragment, container, false);
        rvCntct=(RecyclerView)v.findViewById(R.id.rvCntct);
        ContactAdapter contactAdapter=new ContactAdapter(getContext(), lstContact);
        rvCntct.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvCntct.setAdapter(contactAdapter);

        return v;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        lstContact=new ArrayList<>();
        lstContact.add(new Contact("John Doe", "1234567890" , R.drawable.icon_profile));
        lstContact.add(new Contact("Sayed Isa", "1234567890" , R.drawable.icon_profile));
        lstContact.add(new Contact("Zaheer Khan", "1234567890" , R.drawable.icon_profile));
        lstContact.add(new Contact("Shakeel Shaikh", "1234567890" , R.drawable.icon_profile));
        lstContact.add(new Contact("Junaid Shaikh", "1234567890" , R.drawable.icon_profile));
        lstContact.add(new Contact("Wasiullah Khan", "1234567890" , R.drawable.icon_profile));
        lstContact.add(new Contact("Rajesh Dhumal", "1234567890" , R.drawable.icon_profile));
    }
}
