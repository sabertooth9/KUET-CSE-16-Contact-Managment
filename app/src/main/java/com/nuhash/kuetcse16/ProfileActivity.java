package com.nuhash.kuetcse16;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;
import com.mikhaellopez.circularimageview.CircularImageView;

public class ProfileActivity extends AppCompatActivity {
    student sx;
    TextInputEditText name, phone, college, blood, birthdate, hometown, roll;
    FloatingActionButton fab;
    CircularImageView propic;
    ImageView imageView;
    Display mDisplay;

    @Override
    protected void onPause() {
        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
        super.onPause();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
        setContentView(R.layout.activity_profile);
        init();
        name.setText(sx.getName());
        blood.setText(sx.getBlood());
        college.setText(sx.getCollege());
        hometown.setText(sx.getHometown());
        birthdate.setText(sx.getBd());
        phone.setText(sx.getPhone());
        roll.setText(sx.getRoll());
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProfileActivity.this, request_edit.class);
                startActivity(intent);
            }
        });
        set_image();
    }

    void set_image() {
        try {
            String xx = 'a' + String.valueOf(Integer.valueOf(sx.getRoll().substring(4, 7)));
            Log.e("DEBUG", xx);
            int id = getResources().getIdentifier(xx, "drawable", getPackageName());
            if (id != 0x00000000) {
                Glide.with(ProfileActivity.this).load(id).override(250, 250).into(propic);
                propic.setVisibility(View.VISIBLE);
            }
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    void init() {
        mDisplay = getWindowManager().getDefaultDisplay();
        sx = Adapter.stt1;
        imageView = findViewById(R.id.image_view_profile_cover);
        roll = findViewById(R.id.text_view_profile_roll);
        birthdate = findViewById(R.id.text_view_profile_BD);
        blood = findViewById(R.id.text_view_profile_blood);
        college = findViewById(R.id.text_view_profile_college);
        hometown = findViewById(R.id.text_view_profile_Hometown);
        phone = findViewById(R.id.text_view_profile_phone);
        name = findViewById(R.id.text_view_profile_name);
        fab = findViewById(R.id.fab);
        propic = findViewById(R.id.image_view_profile_profile);
        Glide.with(ProfileActivity.this).load(R.drawable.coverpic).override(858, 480).into(imageView);
        //fab.setVisibility(View.GONE);
    }
}
