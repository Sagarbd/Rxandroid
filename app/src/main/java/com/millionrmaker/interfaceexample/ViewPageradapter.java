package com.millionrmaker.interfaceexample;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

class ViewPageradapter extends PagerAdapter {

    List<USER> list;
    Context context;
    TextView name, email, city;
    Button edit;

    public ViewPageradapter (Context context ,List<USER> list){
        this.list=list;
        this.context = context;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {

        return view==object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, final int position) {
       View view = LayoutInflater.from(context).inflate(R.layout.custom_viewpager_layout,container,false);
        name = (TextView)view.findViewById(R.id.name);
        email = (TextView)view.findViewById(R.id.email);
        city = (TextView)view.findViewById(R.id.city);
        edit=view.findViewById(R.id.edit_potriat);
        edit.setOnClickListener(v -> {
            Intent i = new Intent(context, EditActivity.class);
            i.putExtra("INDEX",position);
            context.startActivity(i);
        });
        name.setText(list.get(position).getName());
        email.setText(list.get(position).getEmail());
        city.setText(list.get(position).getStudentCity());
        container.addView(view);
        return view;
    }


    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
       container.removeView((LinearLayout)object);
    }
}
