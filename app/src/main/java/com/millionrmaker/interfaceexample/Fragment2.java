package com.millionrmaker.interfaceexample;

import android.app.Fragment;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.RuntimeExceptionDao;

import java.util.List;

public class Fragment2 extends Fragment implements ViewPager.OnPageChangeListener {
    TextView textView;
    int details;
    int getDetails = 0;
    ViewPager viewPager;
    List<USER> list;
    OnitemClickListener onitemClickListener;

    public void setOnitemClickListener(OnitemClickListener onitemClickListener) {
        this.onitemClickListener = onitemClickListener;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        Bundle bundle = getArguments();
        details = bundle.getInt("position", 0);
        Log.d("osition", Integer.toString(details));
        bundle.clear();
        bundle.putAll(bundle);

        DBhelper dBhelper = (DBhelper) OpenHelperManager.getHelper(getActivity(), DBhelper.class);
        RuntimeExceptionDao<USER, Integer> runtimeExceptionDao = dBhelper.getuserORMLite();
        list = runtimeExceptionDao.queryForAll();
        ViewPageradapter viewPagerAdapder = new ViewPageradapter(getActivity(), list);
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            TextView name, email, city;
            Button edit;
            View view1 = inflater.inflate(R.layout.custom_viewpager_layout, container, false);
            name = (TextView) view1.findViewById(R.id.name);
            email = (TextView) view1.findViewById(R.id.email);
            city = (TextView) view1.findViewById(R.id.city);
            edit = view1.findViewById(R.id.edit_potriat);
            name.setText(list.get(details).getName());
            email.setText(list.get(details).getEmail());
            city.setText(list.get(details).getStudentCity());
            edit.setOnClickListener(v -> {
                Intent i = new Intent(getActivity(), EditActivity.class);
                i.putExtra("INDEX", details);
                startActivity(i);
            });
            return view1;
        } else {

            View view = inflater.inflate(R.layout.fragment2, container, false);
            viewPager = view.findViewById(R.id.viewpager);
            viewPager = (ViewPager) view.findViewById(R.id.viewpager);
            viewPager.setAdapter(viewPagerAdapder);
            viewPager.setCurrentItem(details);
            final int position = viewPager.getCurrentItem();
            Log.i("ViewPager", Integer.toString(position));
           /* Bundle bundle1=new Bundle();
            bundle1.putInt("viewPagerPosition",position);
            getActivity().setArguments(bundle);*/
            viewPager.setOnPageChangeListener(this);
            //   onitemClickListener.onitemclickListner(position);
            return view;
        }


    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (savedInstanceState != null) {
            onitemClickListener = (MainActivity) getActivity();
        }

    }

    int getdata() {
        Log.i("oGETDATA", Integer.toString(getDetails));
        return getDetails;
    }


    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
       this.getDetails = position;
        Log.i("onPageScrolled", Integer.toString(getDetails));
    }

    @Override
    public void onPageSelected(int position) {
        Log.i("onPageSelected", Integer.toString(getDetails));
    }

    @Override
    public void onPageScrollStateChanged(int state) {
    }


}

