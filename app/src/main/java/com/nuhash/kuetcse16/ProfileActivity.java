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
    TextInputEditText name, phone, college, blood, birthdate, hometown, roll;
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
                Intent intent = new Intent(ProfileActivity.this, request_edit.class);
                startActivity(intent);
            }
        });
        if (sx.getRoll().equals("1607031")) {
            propic.setVisibility(View.VISIBLE);
            propic.setImageDrawable(getDrawable(R.drawable.noman));
        } else if (sx.getRoll().equals("1607035")) {
            propic.setVisibility(View.VISIBLE);
            propic.setImageDrawable(getDrawable(R.drawable.rahi));
        } else if (sx.getRoll().equals("1607053")) {
            propic.setVisibility(View.VISIBLE);
            propic.setImageDrawable(getDrawable(R.drawable.nowshin));
        } else if (sx.getRoll().equals("1607007")) {
            propic.setVisibility(View.VISIBLE);
            propic.setImageDrawable(getDrawable(R.drawable.paul));
        } else if (sx.getRoll().equals("1607048")) {
            propic.setVisibility(View.VISIBLE);
            propic.setImageDrawable(getDrawable(R.drawable.faisal));
        } else if (sx.getRoll().equals("1607058")) {
            propic.setVisibility(View.VISIBLE);
            propic.setImageDrawable(getDrawable(R.drawable.sakib));
        } else if (sx.getRoll().equals("1607022")) {
            propic.setVisibility(View.VISIBLE);
            propic.setImageDrawable(getDrawable(R.drawable.sas));
        } else if (sx.getRoll().equals("1607102")) {
            propic.setVisibility(View.VISIBLE);
            propic.setImageDrawable(getDrawable(R.drawable.yosuf));
        } else if (sx.getRoll().equals("1607093")) {
            propic.setVisibility(View.VISIBLE);
            propic.setImageDrawable(getDrawable(R.drawable.rohan));
        } else if (sx.getRoll().equals("1607087")) {
            propic.setVisibility(View.VISIBLE);
            propic.setImageDrawable(getDrawable(R.drawable.osim));
        } else if (sx.getRoll().equals("1607078")) {
            propic.setVisibility(View.VISIBLE);
            propic.setImageDrawable(getDrawable(R.drawable.anika));
        } else if (sx.getRoll().equals("1607014")) {
            propic.setVisibility(View.VISIBLE);
            propic.setImageDrawable(getDrawable(R.drawable.sourav));
        } else if (sx.getRoll().equals("1607099")) {
            propic.setVisibility(View.VISIBLE);
            propic.setImageDrawable(getDrawable(R.drawable.joy));
        } else if (sx.getRoll().equals("1607044")) {
            propic.setVisibility(View.VISIBLE);
            propic.setImageDrawable(getDrawable(R.drawable.dipto));
        } else if (sx.getRoll().equals("1607079")) {
            propic.setVisibility(View.VISIBLE);
            propic.setImageDrawable(getDrawable(R.drawable.awal));
        }else if (sx.getRoll().equals("1607040")) {
            propic.setVisibility(View.VISIBLE);
            propic.setImageDrawable(getDrawable(R.drawable.nuhash_bhai));
        }else if (sx.getRoll().equals("1607045")) {
            propic.setVisibility(View.VISIBLE);
            propic.setImageDrawable(getDrawable(R.drawable.saikan));
        }else if (sx.getRoll().equals("1607030")) {
            propic.setVisibility(View.VISIBLE);
            propic.setImageDrawable(getDrawable(R.drawable.sajal));
        }else if (sx.getRoll().equals("1607032")) {
            propic.setVisibility(View.VISIBLE);
            propic.setImageDrawable(getDrawable(R.drawable.alif));
        }else if (sx.getRoll().equals("1607033")) {
            propic.setVisibility(View.VISIBLE);
            propic.setImageDrawable(getDrawable(R.drawable.diptomagi));
        }else if (sx.getRoll().equals("1607046")) {
            propic.setVisibility(View.VISIBLE);
            propic.setImageDrawable(getDrawable(R.drawable.nuhashmota));
        }else if (sx.getRoll().equals("1607070")) {
            propic.setVisibility(View.VISIBLE);
            propic.setImageDrawable(getDrawable(R.drawable.rudra));
        }
        else if (sx.getRoll().equals("1607006")) {
            propic.setVisibility(View.VISIBLE);
            propic.setImageDrawable(getDrawable(R.drawable.sajid));
        }else if (sx.getRoll().equals("1607023")) {
            propic.setVisibility(View.VISIBLE);
            propic.setImageDrawable(getDrawable(R.drawable.shah));
        }else if (sx.getRoll().equals("1607084")) {
            propic.setVisibility(View.VISIBLE);
            propic.setImageDrawable(getDrawable(R.drawable.jisan));
        }else if (sx.getRoll().equals("1607049")) {
            propic.setVisibility(View.VISIBLE);
            propic.setImageDrawable(getDrawable(R.drawable.limon));
        }else if (sx.getRoll().equals("1607038")) {
            propic.setVisibility(View.VISIBLE);
            propic.setImageDrawable(getDrawable(R.drawable.niloy));
        }else if (sx.getRoll().equals("1607071")) {
            propic.setVisibility(View.VISIBLE);
            propic.setImageDrawable(getDrawable(R.drawable.pias));
        }else if (sx.getRoll().equals("1607041")) {
            propic.setVisibility(View.VISIBLE);
            propic.setImageDrawable(getDrawable(R.drawable.proja));
        }else if (sx.getRoll().equals("1607034")) {
            propic.setVisibility(View.VISIBLE);
            propic.setImageDrawable(getDrawable(R.drawable.sakha));
        }
    }

    void init() {
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
        //fab.setVisibility(View.GONE);
    }
}
