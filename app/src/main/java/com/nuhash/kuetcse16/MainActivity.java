package com.nuhash.kuetcse16;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import com.google.android.material.button.MaterialButton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    String maindata;
    data_handler dataHandler;
    ArrayList<student> arr;
    LinearLayoutManager layoutManager;
    RecyclerView recyclerView;
    Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    void init() {
        arr = new ArrayList<>();
        dataHandler = new data_handler();
        maindata = dataHandler.Give_data();
        process_JSON();
        adapter = new Adapter(arr, MainActivity.this);
        recyclerView = findViewById(R.id.recycler_view_name);
        layoutManager = new LinearLayoutManager(MainActivity.this, RecyclerView.VERTICAL, false);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(layoutManager);
    }

    void process_JSON() {
        student st;
        try {
            JSONArray jsonArray = new JSONArray(maindata);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                st = new student();
                st.bd = jsonObject.getString("Birthdate");
                if (st.bd.isEmpty()) st.bd = "None";
                st.blood = jsonObject.getString("Blood Group");
                if (st.blood.isEmpty()) st.blood = "None";
                st.college = jsonObject.getString("College");
                if (st.college.isEmpty()) st.college = "None";
                st.hometown = jsonObject.getString("Hometown");
                if (st.hometown.isEmpty()) st.hometown = "None";
                st.name = jsonObject.getString("Name");
                if (st.name.isEmpty()) st.name = "None";
                st.nickname = jsonObject.getString("Nick Name");
                if (st.nickname.isEmpty()) st.nickname = "None";
                st.phone = "0" + String.valueOf(jsonObject.getInt("Phone"));
                if (st.phone.isEmpty()) st.phone = "None";
                st.roll = String.valueOf(jsonObject.getInt("Roll"));
                if (st.roll.isEmpty()) st.roll = "None";
                arr.add(st);
            }
        } catch (JSONException e) {
            e.printStackTrace();
            Log.e("ERROR", e.getMessage());
        }
    }
}
