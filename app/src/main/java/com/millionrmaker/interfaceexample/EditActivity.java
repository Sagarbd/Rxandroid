package com.millionrmaker.interfaceexample;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.RuntimeExceptionDao;
import com.j256.ormlite.stmt.UpdateBuilder;

import java.sql.SQLException;
import java.util.List;

public class EditActivity extends AppCompatActivity {
    DBhelper dBhelper;
    List<USER> list;
    String name, email, city, afterName, afterCity, afterEmail;
    EditText editName, editEmail, editCity;
    Button save;
    UpdateBuilder<USER, Integer> updateBuilder;
    int index;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        Bundle b = getIntent().getExtras();
        index = b.getInt("INDEX");
        dBhelper = (DBhelper) OpenHelperManager.getHelper(this, DBhelper.class);
        final RuntimeExceptionDao<USER, Integer> runtimeExceptionDao = dBhelper.getuserORMLite();
        updateBuilder = runtimeExceptionDao.updateBuilder();
        list = runtimeExceptionDao.queryForAll();
        name = list.get(index).getName();
        email = list.get(index).getEmail();
        city = list.get(index).getStudentCity();

        editName = findViewById(R.id.edit_name);
        editEmail = findViewById(R.id.edit_email);
        editCity = findViewById(R.id.edit_city);
        save = findViewById(R.id.Save);
        editName.setHint(name);
        editEmail.setHint(email);
        editCity.setHint(city);
        save.setOnClickListener(v -> {
            if (!editName.getText().toString().isEmpty()) {
                afterName = editName.getText().toString();
            } else {
                afterName = name;
            }
            if (!editEmail.getText().toString().isEmpty()) {
                afterEmail = editEmail.getText().toString();
            } else {
                afterEmail = email;
            }
            if (!editCity.getText().toString().isEmpty()) {
                afterCity = editCity.getText().toString();
            } else {
                afterCity = city;
                Log.d("AD---------->",afterCity);
            }
            try {
                updateBuilder.where().eq("name", list.get(index).getName());
                updateBuilder.updateColumnValue("name", afterName);
                updateBuilder.update();
                updateBuilder.where().eq("email", list.get(index).getEmail());
                updateBuilder.updateColumnValue("email", afterEmail);
                updateBuilder.update();
                updateBuilder.where().eq("studentCity", list.get(index).getStudentCity());
                updateBuilder.updateColumnValue("studentCity", afterCity);
                updateBuilder.update();
                Intent i = new Intent(EditActivity.this, MainActivity.class);
                i.putExtra("index", index);
                startActivity(i);
            } catch (SQLException e) {
                e.printStackTrace();
                Log.d("AD---------->",e.toString());

            }


        });

    }
}
