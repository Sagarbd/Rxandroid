package com.millionrmaker.interfaceexample;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class StartFragment extends Fragment {
Button usingRetrofit,usingRetrofitAndRxAndroid;
OnitemClickListener onitemClickListener;
    public void setOnitemClickListener(OnitemClickListener onitemClickListener) {
        this.onitemClickListener = onitemClickListener;
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.startfragment,container,false);
        usingRetrofit = view.findViewById(R.id.retrofit);
        usingRetrofitAndRxAndroid = view.findViewById(R.id.rxAndroid);
        usingRetrofit.setOnClickListener(v -> onitemClickListener.onitemclickListner(1));
        usingRetrofitAndRxAndroid.setOnClickListener(v -> onitemClickListener.onitemclickListner(2));

        return view;
    }
}
