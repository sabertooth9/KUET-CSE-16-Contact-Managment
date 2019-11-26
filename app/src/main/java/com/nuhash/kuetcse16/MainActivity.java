package com.nuhash.kuetcse16;

import android.Manifest;
import android.app.ProgressDialog;
import android.app.SearchManager;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.bumptech.glide.Glide;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case 1: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                } else {
                    Toast.makeText(MainActivity.this, "Permission denied to read your External storage", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        }
    }

    String maindata;
    Display mDisplay;
    data_handler dataHandler;
    static ArrayList<student> arr;
    LinearLayoutManager layoutManager;
    RecyclerView recyclerView;
    Adapter adapter;
    SearchManager searchManager;
    SearchView searchView;
    FloatingActionButton fabx;
    ImageView cover;
    Toolbar toolbar;
    CoordinatorLayout coordinatorLayout;
    SwipeRefreshLayout swipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE},
                1);
        init();
        setSupportActionBar(toolbar);
        Log.e("DEBUG WIDTH", mDisplay.getWidth() + "");
        Log.e("DEBUG HIGHT", mDisplay.getHeight() + "");
        Glide.with(MainActivity.this).load(R.drawable.coverpic).override(858, 480).into(cover);
        Snackbar.make(coordinatorLayout, "Swipe Down to Update Data", Snackbar.LENGTH_LONG).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.search_menu, menu);

        searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        searchView = (SearchView) menu.findItem(R.id.search).getActionView();
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setMaxWidth(Integer.MAX_VALUE);
        searchView.setQueryHint("Search Roll,Name,Blood,Hometown,College..");
        searchView.setBackgroundColor(Color.BLACK);
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
        int id = item.getItemId();
        if (id == R.id.search) return true;
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        if (!searchView.isIconified()) {
            searchView.setIconified(true);
            return;
        }
        super.onBackPressed();
    }

    void set_view() {
        arr.clear();
        process_JSON();
        adapter = new Adapter(arr, MainActivity.this);
        layoutManager = new LinearLayoutManager(MainActivity.this, RecyclerView.VERTICAL, false);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(layoutManager);
    }

    void init() {
        mDisplay = getWindowManager().getDefaultDisplay();
        cover = findViewById(R.id.backdrop);
        coordinatorLayout = findViewById(R.id.coordinate_layout);
        toolbar = findViewById(R.id.toolbar);
        swipeRefreshLayout = findViewById(R.id.swipeRefresh);
        swipeRefreshLayout.setOnRefreshListener(this);
        swipeRefreshLayout.setColorSchemeColors(
                getResources().getColor(R.color.colorPrimary),
                getResources().getColor(android.R.color.holo_green_dark), getResources().getColor(android.R.color.holo_green_light),
                getResources().getColor(android.R.color.holo_orange_dark), getResources().getColor(android.R.color.holo_orange_light),
                getResources().getColor(android.R.color.holo_blue_dark), getResources().getColor(android.R.color.holo_blue_light), getResources().getColor(android.R.color.holo_blue_bright));
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
        GETDATA getdata = new GETDATA();
        getdata.execute();
        set_view();
    }

    private class GETDATA extends AsyncTask<Void, Void, Void> {
        ProgressDialog progressDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = new ProgressDialog(MainActivity.this);
            progressDialog.setMessage("Updating..");
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
            Snackbar.make(coordinatorLayout, "Done..!", Snackbar.LENGTH_LONG).show();
            set_view();
        }
    }

    void process_JSON() {
        maindata = dataHandler.Give_data();
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
                st.phone = "0" + jsonObject.getInt("Phone");
                st.roll = String.valueOf(jsonObject.getInt("Roll"));

                if (st.bd.isEmpty()) st.bd = "None";
                if (st.blood.isEmpty()) st.blood = "None";
                if (st.college.isEmpty()) st.college = "None";
                if (st.hometown.isEmpty()) st.hometown = "None";
                if (st.name.isEmpty()) st.name = "None";
                if (st.nickname.isEmpty()) st.nickname = "None";
                if (st.phone.isEmpty()) st.phone = "None";
                if (st.roll.isEmpty()) st.roll = "None";

                st.hometown = st.hometown.toUpperCase();
                st.college = st.college.toUpperCase();
                st.name = st.name.toUpperCase();
                st.nickname = st.nickname.toUpperCase();
                if (st.blood.length() == 2)
                    st.blood = st.blood.substring(0, 1).toUpperCase() + st.blood.substring(1);
                else st.blood = st.blood.substring(0, 2).toUpperCase() + st.blood.substring(2);
                arr.add(st);
            }
            Log.e("DEBUG", arr.size() + "");
        } catch (JSONException e) {
            e.printStackTrace();
            Log.e("ERROR", e.getMessage());
        }
    }
}
