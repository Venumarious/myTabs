package com.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.arrayClass.Contact;
import com.eduadministrative.mytabs.R;

import java.util.List;

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.myViewHolder> {

    Context mContext;
    List<Contact> mData;

    public ContactAdapter(Context mContext, List<Contact> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @Override
    public myViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v;
        v= LayoutInflater.from(mContext).inflate(R.layout.item_contact, parent, false);
        myViewHolder viewHolder=new myViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(myViewHolder holder, int position) {
        holder.tv_name.setText(mData.get(position).getName());
        holder.tv_phone.setText(mData.get(position).getPhone());
        holder.img.setImageResource(mData.get(position).getPhoto());
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class myViewHolder extends RecyclerView.ViewHolder{

        private TextView tv_name, tv_phone;
        private ImageView img;

        public myViewHolder(View itemView) {
            super(itemView);

            tv_name=(TextView)itemView.findViewById(R.id.name_contact);
            tv_phone=(TextView)itemView.findViewById(R.id.phone_contact);
            img=(ImageView) itemView.findViewById(R.id.imgCntct);
        }
    }
}