package com.eduadministrative.mytabs;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.ServiceHandler.ServiceHandler;
import com.adapter.AllListAdapter;
import com.adapter.Confirm_ListAdapter;
import com.arrayClass.AllListClass;
import com.arrayClass.Confirm_ListClass;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class TabFragment3 extends Fragment implements View.OnClickListener {

    View view;
    private ListView lstView;
    private ProgressBar prgRjctd;
    private LinearLayout noPostLayout;

    ArrayList<AllListClass> allListClass=new ArrayList<AllListClass>();
    AllListAdapter boxAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        view=inflater.inflate(R.layout.tab_fragment3, container, false);
        lstView = (ListView) view.findViewById(R.id.lstView);
        prgRjctd = (ProgressBar) view.findViewById(R.id.prgRjctd);
        prgRjctd.setVisibility(View.GONE);
        noPostLayout = (LinearLayout) view.findViewById(R.id.noPostLayout);
        noPostLayout.setVisibility(View.GONE);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        getJSON_List("http://eduadministrative.com/Get_Rejected.aspx?schId=9&hd=M&typ=notic");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){

        }
    }

    private void getJSON_List(final String urlWebService) {

        class GetJSON extends AsyncTask<Void, Void, String> {

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                prgRjctd.setVisibility(View.VISIBLE);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                try {
                    loadIntoListView(s);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                prgRjctd.setVisibility(View.GONE);
            }

            @Override
            protected String doInBackground(Void... voids) {
                try {
                    ServiceHandler serviceClient = new ServiceHandler();
                    String json = serviceClient.makeServiceCall(urlWebService);
                    return json;
                } catch (Exception e) {
                    return null;
                }
            }
        }
        GetJSON getJSON = new GetJSON();
        getJSON.execute();
    }

    private void loadIntoListView(String json) throws JSONException {
        allListClass.clear();
        int i=0;
        JSONArray jsonArray = new JSONArray(json);
        for (i = 0; i < jsonArray.length(); i++) {
            JSONObject obj = jsonArray.getJSONObject(i);
            allListClass.add(new AllListClass(obj.getString("senderNm"), obj.getString("typTxt"), obj.getString("dtTm")
                    ,obj.getString("cls"),obj.getString("rjctdBy"),obj.getString("rjctdDtTm"),"#ffffff"));
        }
        if (i==0){
            lstView.setVisibility(View.GONE);
            noPostLayout.setVisibility(View.VISIBLE);
        }else{
            lstView.setVisibility(View.VISIBLE);
            noPostLayout.setVisibility(View.GONE);
        }
        boxAdapter = new AllListAdapter(getActivity(), allListClass);
        lstView.setAdapter(boxAdapter);
    }
}