package com.eduadministrative.mytabs;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.ServiceHandler.ServiceHandler;
import com.adapter.Confirm_ListAdapter;
import com.arrayClass.Confirm_ListClass;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class TabFragment2 extends Fragment {

    View view;
    private ListView lstView;
    private ProgressBar prgPndng;
    private LinearLayout noPostLayout;

    ArrayList<Confirm_ListClass> confirm_ListClass=new ArrayList<Confirm_ListClass>();
    Confirm_ListAdapter boxAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.tab_fragment2, container, false);
        lstView = (ListView) view.findViewById(R.id.lstView);
        prgPndng = (ProgressBar) view.findViewById(R.id.prgPndng);
        prgPndng.setVisibility(View.GONE);
        noPostLayout = (LinearLayout) view.findViewById(R.id.noPostLayout);
        noPostLayout.setVisibility(View.GONE);

        return view;
    }


    @Override
    public void onResume() {
        super.onResume();
        getJSON_List("http://eduadministrative.com/get_confirmList.aspx?schid=9&typ=notic");
    }

    private void getJSON_List(final String urlWebService) {

        class GetJSON extends AsyncTask<Void, Void, String> {

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                prgPndng.setVisibility(View.VISIBLE);

            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                try {
                    loadIntoListView(s);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                prgPndng.setVisibility(View.GONE);
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
        confirm_ListClass.clear();
        int i=0;
        JSONArray jsonArray = new JSONArray(json);
        for (i = 0; i < jsonArray.length(); i++) {
            JSONObject obj = jsonArray.getJSONObject(i);
            confirm_ListClass.add(new Confirm_ListClass(obj.getString("brnchNm"), obj.getString("secNm"), obj.getString("cls"),
                    obj.getString("vsmTypId"), obj.getString("senderNm"), obj.getString("typTxt"),
                    obj.getString("dtTm"), obj.getString("subNm"), "#ffffff"));
        }
        if (i==0){
            lstView.setVisibility(View.GONE);
            noPostLayout.setVisibility(View.VISIBLE);
        }else{
            lstView.setVisibility(View.VISIBLE);
            noPostLayout.setVisibility(View.GONE);
        }
        boxAdapter = new Confirm_ListAdapter(getActivity(), confirm_ListClass);
        lstView.setAdapter(boxAdapter);
    }
}