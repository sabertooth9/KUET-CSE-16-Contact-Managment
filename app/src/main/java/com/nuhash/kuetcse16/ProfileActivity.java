package com.nuhash.kuetcse16;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;
import com.mikhaellopez.circularimageview.CircularImageView;

import java.util.Objects;

public class ProfileActivity extends AppCompatActivity {
    student sx;
    TextInputEditText name,phone,college,blood,birthdate,hometown,roll;
    FloatingActionButton fab;
    CircularImageView propic;
    ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
                Intent intent=new Intent(ProfileActivity.this,request_edit.class);
                startActivity(intent);
            }
        });
        Intent intent=getIntent();
        if(intent!=null){
            if(intent.hasExtra("DEV")){
                if(Objects.requireNonNull(intent.getExtras()).getBoolean("DEV")){
                   //propic.setImageResource(R.drawable.nuhash_bhai);
                   propic.setVisibility(View.VISIBLE);
                   imageView.setVisibility(View.VISIBLE);
                }
            }
        }

    }
    void init(){
        sx=Adapter.stt1;
        imageView=findViewById(R.id.image_view_profile_cover);
        roll=findViewById(R.id.text_view_profile_roll);
        birthdate=findViewById(R.id.text_view_profile_BD);
        blood=findViewById(R.id.text_view_profile_blood);
        college=findViewById(R.id.text_view_profile_college);
        hometown=findViewById(R.id.text_view_profile_Hometown);
        phone=findViewById(R.id.text_view_profile_phone);
        name=findViewById(R.id.text_view_profile_name);
        fab=findViewById(R.id.fab);
        propic=findViewById(R.id.image_view_profile_profile);
        //fab.setVisibility(View.GONE);
    }
}
