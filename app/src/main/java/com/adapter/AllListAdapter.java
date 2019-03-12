package com.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.arrayClass.AllListClass;
import com.eduadministrative.mytabs.R;

import java.util.ArrayList;

/**
 * Created by ISA on 22/11/2017.
 */

public class AllListAdapter extends BaseAdapter {
    String query;

    Context ctx;
    LayoutInflater lInflater;
    ArrayList<AllListClass> objects;

    public AllListAdapter(Context context, ArrayList<AllListClass> allLstClass) {
        ctx = context;
        objects = allLstClass;
        lInflater = (LayoutInflater) ctx
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }


    @Override
    public int getCount() {
        return objects.size();
    }

    @Override
    public Object getItem(int position) {
        return objects.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            view = lInflater.inflate(R.layout.card_lsttemplate, parent, false);
        }

        AllListClass p = getProduct(position);

        ((TextView) view.findViewById(R.id.txtFrm)).setText(p.frm);
        ((TextView) view.findViewById(R.id.txtText)).setText(p.lstText);
        ((TextView) view.findViewById(R.id.txtDt)).setText(p.dt);
        ((TextView) view.findViewById(R.id.txtSub)).setText(p.sub);
        String rmrkTxt=((TextView) view.findViewById(R.id.txtText)).getText().toString();
        String rmrkDt=((TextView) view.findViewById(R.id.txtDt)).getText().toString();
        String rmrkTm=((TextView) view.findViewById(R.id.txtSub)).getText().toString();

        LinearLayout llCnfrm =view.findViewById(R.id.llCnfrm);
        if (p.cnfrmBy.equals("")){
            llCnfrm.setVisibility(View.GONE);
        }else{
            ((TextView) view.findViewById(R.id.txtCnfrmBy)).setText(p.cnfrmBy);
            ((TextView) view.findViewById(R.id.txtCnfrmDt)).setText(p.cnfrmDt);
        }

        LinearLayout llClr =view.findViewById(R.id.llClr);
        if (p.clr.equals("#ffffff")){
            //llClr.setVisibility(View.GONE);
            llClr.setBackgroundColor(Color.parseColor(p.clr) );
        }else{
            llClr.setBackgroundColor(Color.parseColor(p.clr) );
        }

        return view;
    }

    AllListClass getProduct(int position) {
        return ((AllListClass) getItem(position));
    }
}