package com.nuhash.kuetcse16;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.app.ProgressDialog;
import android.app.SearchManager;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.SearchView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {
    String maindata;
    data_handler dataHandler;
    static ArrayList<student> arr;
    LinearLayoutManager layoutManager;
    RecyclerView recyclerView;
    Adapter adapter;
    SearchManager searchManager;
    SearchView searchView;
    FloatingActionButton fabx;
    Toolbar toolbar;
    CoordinatorLayout coordinatorLayout;
    SwipeRefreshLayout swipeRefreshLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        setSupportActionBar(toolbar);
        Snackbar.make(coordinatorLayout,"Swipe Down to Update Data",Snackbar.LENGTH_LONG).show();
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
    void set_view(){
        process_JSON();
        adapter = new Adapter(arr, MainActivity.this);
        layoutManager = new LinearLayoutManager(MainActivity.this, RecyclerView.VERTICAL, false);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(layoutManager);
    }
    void init() {
        coordinatorLayout=findViewById(R.id.coordinate_layout);
        toolbar=findViewById(R.id.toolbar);
        swipeRefreshLayout=findViewById(R.id.swipeRefresh);
        swipeRefreshLayout.setOnRefreshListener(this);
        swipeRefreshLayout.setColorSchemeColors(
                getResources().getColor(R.color.colorPrimary),
                getResources().getColor(android.R.color.holo_green_dark),getResources().getColor(android.R.color.holo_green_light),
                getResources().getColor(android.R.color.holo_orange_dark),getResources().getColor(android.R.color.holo_orange_light),
                getResources().getColor(android.R.color.holo_blue_dark),getResources().getColor(android.R.color.holo_blue_light),getResources().getColor(android.R.color.holo_blue_bright));
        /*swipeRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                swipeRefreshLayout.setRefreshing(true);
                GETDATA getdata=new GETDATA();
                getdata.execute();
                set_view();
            }
        });*/
        //fabx=findViewById(R.id.fab1);
        arr = new ArrayList<>();
        dataHandler = new data_handler();
        maindata = dataHandler.Give_data();
        recyclerView = findViewById(R.id.recycler_view_name);
        set_view();
    }

    @Override
    public void onRefresh() {
        GETDATA getdata=new GETDATA();
        getdata.execute();
        set_view();
    }
    private class GETDATA extends AsyncTask<Void, Void, Void> {
        ProgressDialog progressDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = new ProgressDialog(MainActivity.this);
            progressDialog.setMessage("Updating");
            progressDialog.show();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            HttpHandler sh = new HttpHandler();
            String jsnStr = sh.makeServiceCall(dataHandler.Give_API());
            try {
                Log.e("JSON", jsnStr);
            } catch (Exception e) {
                Log.e("JSONERROR", e.getMessage());
            }
            if (jsnStr != null) {
                dataHandler.setJSONstr(jsnStr);
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            progressDialog.dismiss();
            swipeRefreshLayout.setRefreshing(false);
            Toast.makeText(MainActivity.this, "DONE", Toast.LENGTH_SHORT).show();
            set_view();
        }
    }
    void process_JSON() {
        maindata=dataHandler.Give_data();
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
