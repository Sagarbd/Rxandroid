package com.millionrmaker.interfaceexample;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.TextView;

import java.util.List;

public class Listadapter extends BaseAdapter {

    Context context;
    List<USER> list;
    LayoutInflater inflater;
int selecteditem=0;

    public Listadapter(Context context, List<USER> list) {
        this.list = list;
        this.context = context;
        inflater = LayoutInflater.from(this.context);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        final MyViewHolder mViewHolder;

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.custom_layoyt, parent, false);
            mViewHolder = new MyViewHolder(convertView);
            convertView.setTag(mViewHolder);
        } else {
            mViewHolder = (MyViewHolder) convertView.getTag();
        }

/*if(selecteditem == position)
{
    convertView.setBackgroundColor(selecteditem == position ? Color.GREEN : Color.TRANSPARENT);
}*/
        mViewHolder.name.setText(list.get(position).getName());
        mViewHolder.email.setText(list.get(position).getEmail());
        mViewHolder.city.setText(list.get(position).getStudentCity());
       /* mViewHolder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               selecteditem =position;
               notifyDataSetChanged();
            }
        });
        if(position==selecteditem){
            convertView.setBackgroundColor(selecteditem == position ? Color.GREEN : Color.TRANSPARENT);
        }*/
        return convertView;
    }

    public void sendvalue(int position) {
        this.selecteditem = position;
    }

    private class MyViewHolder {
        TextView name, email,city;
        LinearLayout linearLayout;

        public MyViewHolder(View item) {
            name = (TextView)item.findViewById(R.id.name);
            email = (TextView)item.findViewById(R.id.email);
            city = (TextView)item.findViewById(R.id.city);
            linearLayout =(LinearLayout)item.findViewById(R.id.linearcustome);

        }
    }

    }

