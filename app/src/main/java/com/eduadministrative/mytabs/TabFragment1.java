package com.eduadministrative.mytabs;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;

import com.ServiceHandler.ServiceHandler;
import com.adapter.AllListAdapter;
import com.arrayClass.AllListClass;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class TabFragment1 extends Fragment implements View.OnClickListener {

    View view;
    private ProgressDialog pDialog;
    private Spinner spnBrnch;
    private ListView lstCnfrmNotic;
    private ProgressBar prgCnfrm;
    private LinearLayout noPostLayout;

    ArrayList<AllListClass> allListClass=new ArrayList<AllListClass>();
    AllListAdapter boxAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.tab_fragment1, container, false);
        spnBrnch = (Spinner) view.findViewById(R.id.spnBrnch);
        prgCnfrm = (ProgressBar) view.findViewById(R.id.prgCnfrm);
        prgCnfrm.setVisibility(View.GONE);
        lstCnfrmNotic = (ListView) view.findViewById(R.id.lstCnfrmNotic);
        noPostLayout = (LinearLayout) view.findViewById(R.id.noPostLayout);
        noPostLayout.setVisibility(View.GONE);

        spnBrnch.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //new FillList().execute();

                String myBrnchNm=spnBrnch.getSelectedItem().toString().replace(" ","_");
                getJSON_List("http://eduadministrative.com/Get_CurrNoticEvnt.aspx?schId=9&brnchNm="+myBrnchNm+"&hd=M&typ=notic");
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        return view;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);

    }

    @Override
    public void onResume() {
        super.onResume();
        getJSON_Brnch("http://eduadministrative.com/Get_Branch.aspx?schId=9&logId=152");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){

        }
    }

    private void getJSON_Brnch(final String urlWebService) {

        class GetJSON extends AsyncTask<Void, Void, String> {

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                pDialog = new ProgressDialog(getActivity());
                pDialog.setMessage("Loading Branch...");
                pDialog.setIndeterminate(false);
                pDialog.setCancelable(false);
                pDialog.show();
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                //Toast.makeText(getApplicationContext(), s, Toast.LENGTH_LONG).show();
                try {
                    loadIntoSpnBrnch(s);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                pDialog.dismiss();
            }

            @Override
            protected String doInBackground(Void... voids) {
                try {
                    URL url = new URL(urlWebService);
                    HttpURLConnection con = (HttpURLConnection) url.openConnection();
                    StringBuilder sb = new StringBuilder();
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(con.getInputStream()));
                    String json;
                    while ((json = bufferedReader.readLine()) != null) {
                        sb.append(json + "\n");
                    }
                    return sb.toString().trim();
                } catch (Exception e) {
                    return null;
                }
            }
        }
        GetJSON getJSON = new GetJSON();
        getJSON.execute();
    }

    private void loadIntoSpnBrnch(String json) throws JSONException {
        JSONArray jsonArray = new JSONArray(json);
        String[] brnch = new String[jsonArray.length()];
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject obj = jsonArray.getJSONObject(i);
            brnch[i] = obj.getString("brnchNm");
        }
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, brnch);
        spnBrnch.setAdapter(arrayAdapter);
    }

    private void getJSON_List(final String urlWebService) {

        class GetJSON extends AsyncTask<Void, Void, String> {

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                prgCnfrm.setVisibility(View.VISIBLE);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                //Toast.makeText(getApplicationContext(), s, Toast.LENGTH_LONG).show();
                try {
                    loadIntoListView(s);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                prgCnfrm.setVisibility(View.GONE);
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
            allListClass.add(new AllListClass(obj.getString("senderNm"), obj.getString("typTxt"), obj.getString("dtTm"),
                    obj.getString("cls"), obj.getString("cnfrmBy"), obj.getString("cnfrmDtTm"), "#ffffff"));
        }
        if (i==0){
            lstCnfrmNotic.setVisibility(View.GONE);
            noPostLayout.setVisibility(View.VISIBLE);
        }else{
            lstCnfrmNotic.setVisibility(View.VISIBLE);
            noPostLayout.setVisibility(View.GONE);
        }
        boxAdapter = new AllListAdapter(getActivity(), allListClass);
        lstCnfrmNotic.setAdapter(boxAdapter);
    }
}