package com.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.arrayClass.Confirm_ListClass;
import com.eduadministrative.mytabs.R;

import java.util.ArrayList;

/**
 * Created by ISA on 07/02/2018.
 */

public class Confirm_ListAdapter extends BaseAdapter {
    String query;

    Context ctx;
    LayoutInflater lInflater;
    ArrayList<Confirm_ListClass> objects;

    public Confirm_ListAdapter(Context context, ArrayList<Confirm_ListClass> allLstClass) {
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
            view = lInflater.inflate(R.layout.confirm_lsttemplate, parent, false);
        }

        Confirm_ListClass p = getProduct(position);

        ((TextView) view.findViewById(R.id.txtBrnch)).setText(p.brnch);
        ((TextView) view.findViewById(R.id.txtSec)).setText(p.sec);
        ((TextView) view.findViewById(R.id.txtCls)).setText(p.cls);
        ((TextView) view.findViewById(R.id.txtvsmId)).setText(p.vsmId);
        ((TextView) view.findViewById(R.id.txtvsmId)).setVisibility(View.GONE);
        ((TextView) view.findViewById(R.id.txtFrm)).setText(p.frm);
        ((TextView) view.findViewById(R.id.txtText)).setText(p.lstText);
        ((TextView) view.findViewById(R.id.txtDt)).setText(p.dt);
        ((TextView) view.findViewById(R.id.txtSub)).setText(p.sub);

        ((LinearLayout) view.findViewById(R.id.llClr)).setBackgroundColor(Color.parseColor(p.clr) );

        final String rmrkTxt=((TextView) view.findViewById(R.id.txtText)).getText().toString();
        final String rmrkDt=((TextView) view.findViewById(R.id.txtDt)).getText().toString();
        final String rmrkTm=((TextView) view.findViewById(R.id.txtSub)).getText().toString();

        return view;
    }

    Confirm_ListClass getProduct(int position) {
        return ((Confirm_ListClass) getItem(position));
    }

}
