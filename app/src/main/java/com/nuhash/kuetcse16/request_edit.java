package com.nuhash.kuetcse16;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

public class request_edit extends AppCompatActivity {
   // DatabaseReference reference;
    TextInputEditText name,roll,phone,home,college,blood;
    MaterialButton button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_edit);
    }
    void init(){
       // reference= FirebaseDatabase.getInstance().getReference();
        name=findViewById(R.id.text_edit_profile_name);
        roll=findViewById(R.id.text_edit_profile_roll);
        phone=findViewById(R.id.text_edit_profile_phone);
    }
}
