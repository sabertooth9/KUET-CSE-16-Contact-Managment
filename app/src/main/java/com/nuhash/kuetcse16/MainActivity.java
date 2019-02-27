package com.nuhash.kuetcse16;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.SearchView;

import com.google.android.material.button.MaterialButton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    String maindata;
    data_handler dataHandler;
    static ArrayList<student> arr;
    LinearLayoutManager layoutManager;
    RecyclerView recyclerView;
    Adapter adapter;
    SearchManager searchManager;
    SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.search_menu, menu);

        searchManager=(SearchManager)getSystemService(Context.SEARCH_SERVICE);
        searchView=(SearchView)menu.findItem(R.id.search).getActionView();
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setMaxWidth(Integer.MAX_VALUE);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                adapter.getFilter().filter(query);
                return false;
            }
            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);
                return false;
            }
        });
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id=item.getItemId();
        if(id==R.id.search)return  true;
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        if(!searchView.isIconified()){
            searchView.setIconified(true);
            return;
        }
        super.onBackPressed();
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
                st.blood = jsonObject.getString("Blood Group");
                st.college = jsonObject.getString("College");
                st.hometown = jsonObject.getString("Hometown");
                st.name = jsonObject.getString("Name");
                st.nickname = jsonObject.getString("Nick Name");
                st.phone = "0" + String.valueOf(jsonObject.getInt("Phone"));
                st.roll = String.valueOf(jsonObject.getInt("Roll"));

                if (st.bd.isEmpty()) st.bd = "None";
                if (st.blood.isEmpty()) st.blood = "None";
                if (st.college.isEmpty()) st.college = "None";
                if (st.hometown.isEmpty()) st.hometown = "None";
                if (st.name.isEmpty()) st.name = "None";
                if (st.nickname.isEmpty()) st.nickname = "None";
                if (st.phone.isEmpty()) st.phone = "None";
                if (st.roll.isEmpty()) st.roll = "None";

                st.hometown=st.hometown.toUpperCase();
                st.college=st.college.toUpperCase();
                st.name=st.name.toUpperCase();
                st.nickname=st.nickname.toUpperCase();
                if(st.blood.length()==2)
                st.blood=st.blood.substring(0,1).toUpperCase()+st.blood.substring(1);
                else st.blood=st.blood.substring(0,2).toUpperCase()+st.blood.substring(2);
                arr.add(st);
            }
            Log.e("DEBUG",arr.size()+"");
        } catch (JSONException e) {
            e.printStackTrace();
            Log.e("ERROR", e.getMessage());
        }
    }
}
