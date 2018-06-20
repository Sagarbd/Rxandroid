package com.millionrmaker.interfaceexample;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.RuntimeExceptionDao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;

public class DBhelper extends OrmLiteSqliteOpenHelper {
    public static final String TAG = "DBhelper";
    public static final String DATABASE_NAME="student";
    public static final int DATABSE_VERSION=1;

    public DBhelper(Context context) {
        super(context, DATABASE_NAME, null, DATABSE_VERSION);
    }

    RuntimeExceptionDao<USER,Integer> runtimeExceptionDao =null;
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase, ConnectionSource connectionSource) {
        try {
            TableUtils.createTable(connectionSource,USER.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, ConnectionSource connectionSource, int i, int i1) {
        try {
            TableUtils.dropTable(connectionSource,USER.class,true);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    RuntimeExceptionDao<USER,Integer> getuserORMLite(){
        if(runtimeExceptionDao==null)
        {
            runtimeExceptionDao = getRuntimeExceptionDao(USER.class);
        }
        return runtimeExceptionDao;
    }


 }
