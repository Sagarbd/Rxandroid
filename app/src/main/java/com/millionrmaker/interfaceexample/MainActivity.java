package com.millionrmaker.interfaceexample;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.graphics.Color;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.RuntimeExceptionDao;
import com.j256.ormlite.stmt.DeleteBuilder;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements OnitemClickListener {

    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;
    OnitemClickListener onitemClickListener;
    ListView listView;
    public static final String TAG = "MainActivity";
    DBhelper dBhelper;
    List<USER> list = new ArrayList<>();
    SwipeRefreshLayout swipeRefreshLayout;
    RuntimeExceptionDao<USER, Integer> runtimeExceptionDao;
    Listadapter listadapter;
    int selecteditem = 0;
    Fragment2 abc;
    int postion = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        StartFragment startFragment = new StartFragment();
        startFragment.setOnitemClickListener(this);
        fragmentManager = getFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.stratfragment_container, startFragment);
        fragmentTransaction.commit();
        abc = new Fragment2();
        init();
        ORMLite();

        Log.i("ViewPagerLLLLLL", Integer.toString(postion));
        //selecteditem = abc.getdata();
        list = runtimeExceptionDao.queryForAll();
        if (list != null) {
            listadapter = new Listadapter(MainActivity.this, list);
            listView.setAdapter(listadapter);
        }
        Log.i("ViewPagerLLLLLL", Integer.toString(selecteditem));

        listView.setOnItemClickListener((parent, view, position, id) -> {
            fragmentManager = getFragmentManager();
            Fragment fragment1 = fragmentManager.findFragmentById(R.id.fragment_container);
            if (fragment1 != null) {
                fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.remove(fragment1);
                fragmentTransaction.commit();

            }
            addFragment2(position);
            selecteditem = position;
            listadapter.notifyDataSetChanged();
            for (int i = 0; i < listView.getChildCount(); i++) {
                if (position == i) {
                    listView.getChildAt(i).setBackgroundColor(Color.BLUE);
                } else {
                    listView.getChildAt(i).setBackgroundColor(Color.TRANSPARENT);
                }
            }
        });
        listView.setOnItemLongClickListener((parent, view, position, id) -> {
            DeleteBuilder<USER, Integer> deleteBuilder = runtimeExceptionDao.deleteBuilder();

            try {
                deleteBuilder.where().eq("name", list.get(position).getName());
                runtimeExceptionDao.delete(deleteBuilder.prepare());
                // recycleAdapter.notifyDataSetChanged();
                list.clear();
                list = runtimeExceptionDao.queryForAll();
                if (list != null) {
                    listadapter = new Listadapter(MainActivity.this, list);
                    listView.setAdapter(listadapter);
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
            return true;

        });
        swipeRefreshLayout.setOnRefreshListener(() -> {

            DeleteBuilder<USER, Integer> deleteBuilder = runtimeExceptionDao.deleteBuilder();
            try {
                runtimeExceptionDao.delete(deleteBuilder.prepare());
            } catch (SQLException e) {
                e.printStackTrace();
            }

            if (postion == 1) {

                //  LoadJsonUsingRetroFit();

                listView.setOnItemClickListener((parent, view, position, id) -> {
                    fragmentManager = getFragmentManager();
                    Fragment fragment1 = fragmentManager.findFragmentById(R.id.fragment_container);
                    if (fragment1 != null) {
                        fragmentTransaction = fragmentManager.beginTransaction();
                        fragmentTransaction.remove(fragment1);
                        fragmentTransaction.commit();

                    }
                    addFragment2(position);
                    selecteditem = position;
                    listadapter.notifyDataSetChanged();
                    for (int i = 0; i < listView.getChildCount(); i++) {
                        if (position == i) {
                            listView.getChildAt(i).setBackgroundColor(Color.BLUE);
                        } else {
                            listView.getChildAt(i).setBackgroundColor(Color.TRANSPARENT);
                        }
                    }

                    //listadapter.sendvalue(position);
                });
                listView.setOnItemLongClickListener((parent, view, position, id) -> {
                    DeleteBuilder<USER, Integer> deleteBuilder12 = runtimeExceptionDao.deleteBuilder();

                    try {
                        deleteBuilder12.where().eq("name", list.get(position).getName());
                        runtimeExceptionDao.delete(deleteBuilder12.prepare());
                        // recycleAdapter.notifyDataSetChanged();
                        list.clear();
                        list = runtimeExceptionDao.queryForAll();
                        if (list != null) {
                            listadapter = new Listadapter(MainActivity.this, list);
                            listView.setAdapter(listadapter);
                        }

                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    return true;

                });
            } else if (postion == 2) {
                //       LoadJson();
                listView.setOnItemClickListener((parent, view, position, id) -> {
                    fragmentManager = getFragmentManager();
                    Fragment fragment1 = fragmentManager.findFragmentById(R.id.fragment_container);
                    if (fragment1 != null) {
                        fragmentTransaction = fragmentManager.beginTransaction();
                        fragmentTransaction.remove(fragment1);
                        fragmentTransaction.commit();

                    }
                    addFragment2(position);
                    selecteditem = position;
                    listadapter.notifyDataSetChanged();
                    for (int i = 0; i < listView.getChildCount(); i++) {
                        if (position == i) {
                            listView.getChildAt(i).setBackgroundColor(Color.BLUE);
                        } else {
                            listView.getChildAt(i).setBackgroundColor(Color.TRANSPARENT);
                        }
                    }

                    //listadapter.sendvalue(position);
                });
                listView.setOnItemLongClickListener((parent, view, position, id) -> {
                    DeleteBuilder<USER, Integer> deleteBuilder1 = runtimeExceptionDao.deleteBuilder();

                    try {
                        deleteBuilder1.where().eq("name", list.get(position).getName());
                        runtimeExceptionDao.delete(deleteBuilder1.prepare());
                        // recycleAdapter.notifyDataSetChanged();
                        list.clear();
                        list = runtimeExceptionDao.queryForAll();
                        if (list != null) {
                            listadapter = new Listadapter(MainActivity.this, list);
                            listView.setAdapter(listadapter);
                        }

                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    return true;

                });
            }


        });


    }

    /**
     *get data from server using RxAndroid and RetroFit Api
     */
    private void LoadJson() {
        Retrofit builder = new Retrofit.Builder().baseUrl(ApiRxJava.base_url)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        ApiRxJava apiRxJava = builder.create(ApiRxJava.class);
        Observable<List<USER>> observable = apiRxJava.getUser().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

        observable.subscribe(new Observer<List<USER>>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(List<USER> users) {
                list = users;
                for (int i = 0; i < users.size(); i++) {
                    runtimeExceptionDao.create(new USER(users.get(i).getName(), users.get(i).getEmail(), users.get(i).getAddress().getCity()));
                    Log.i(TAG, users.get(i).getName() + " " + users.get(i).getEmail() + " " + users.get(i).getAddress().getCity());
                }
                list = runtimeExceptionDao.queryForAll();
                listadapter = new Listadapter(MainActivity.this, list);
                listView.setAdapter(listadapter);
                swipeRefreshLayout.setRefreshing(false);
            }

            @Override
            public void onError(Throwable e) {
                Log.i("Data From Link", e.toString());
            }

            @Override
            public void onComplete() {

            }
        });

    }


    private void ORMLite() {
        dBhelper = (DBhelper) OpenHelperManager.getHelper(this, DBhelper.class);
        runtimeExceptionDao = dBhelper.getuserORMLite();
    }

    private void init() {

        listView = findViewById(R.id.listview);
        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe);
    }

    private void addFragment2(int position) {
        Fragment fragment = fragmentManager.findFragmentById(R.id.fragment_container);
        if (fragment != null) {
            abc.getdata();
            fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.remove(fragment);
            fragmentTransaction.commit();
        } else {
            fragmentManager = getFragmentManager();
            fragmentTransaction = fragmentManager.beginTransaction();
            Bundle bundle = new Bundle();
            bundle.putInt("position", position);
            Log.d("osition", Integer.toString(position));
            abc.setArguments(bundle);
            abc.setOnitemClickListener(this);
            fragmentTransaction.replace(R.id.fragment_container, abc);
            fragmentTransaction.commit();
        }
    }

    /**
     * Interface for getting value rom start Fragment Class
     */
    @Override
    public void onitemclickListner(int country) {
        Log.i("onitme", Integer.toString(country));
        this.postion = country;
        Log.i("oniasfastme", Integer.toString(postion));
        removefragment(country);

    }

    /**
     * Remove First Fragment
     */
    private void removefragment(int country) {

        DeleteBuilder<USER, Integer> deleteBuilder = runtimeExceptionDao.deleteBuilder();
        try {
            runtimeExceptionDao.delete(deleteBuilder.prepare());
        } catch (SQLException e) {
            e.printStackTrace();
        }

        Fragment fragment = fragmentManager.findFragmentById(R.id.stratfragment_container);
        if (fragment != null) {
            fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.remove(fragment);
            fragmentTransaction.commit();
            FrameLayout frameLayout = findViewById(R.id.stratfragment_container);
            frameLayout.setVisibility(View.GONE);
        }

        if (postion == 1) {
            LoadJsonUsingRetroFit();

        } else if (postion == 2) {
            LoadJson();


        }

    }


    /**
     * get data from server using RetroFit Api
     */
    private void LoadJsonUsingRetroFit() {


        Retrofit builder = new Retrofit.Builder()
                .baseUrl(Api.base_url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        Api api = builder.create(Api.class);
        Call<List<USER>> call = api.getUser();
        call.enqueue(new Callback<List<USER>>() {
            @Override
            public void onResponse(Call<List<USER>> call, Response<List<USER>> response) {
                List<USER> list = response.body();
                //       userdataFromJson = new String[list.size()];
                for (int i = 0; i < list.size(); i++) {
                    runtimeExceptionDao.create(new USER(list.get(i).getName(), list.get(i).getEmail(), list.get(i).getAddress().getCity()));
                    Log.i(TAG, list.get(i).getName() + " " + list.get(i).getEmail() + " " + list.get(i).getAddress().getCity());
                }
                list = runtimeExceptionDao.queryForAll();
                listadapter = new Listadapter(MainActivity.this, list);
                listView.setAdapter(listadapter);
                swipeRefreshLayout.setRefreshing(false);
            }


            @Override
            public void onFailure(Call<List<USER>> call, Throwable t) {

            }
        });
    }

    @Override
    public void onBackPressed() {
        Fragment fragment = fragmentManager.findFragmentById(R.id.fragment_container);
        if (fragment != null) {
            abc.getdata();
            fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.remove(fragment);
            fragmentTransaction.commit();

            for (int i = 0; i < listView.getChildCount(); i++) {
                if (i == abc.getdata()) {
                    listView.getChildAt(i).setBackgroundColor(Color.BLUE);
                } else {
                    listView.getChildAt(i).setBackgroundColor(Color.TRANSPARENT);
                }
            }
        } else {
            super.onBackPressed();
        }
    }
}
