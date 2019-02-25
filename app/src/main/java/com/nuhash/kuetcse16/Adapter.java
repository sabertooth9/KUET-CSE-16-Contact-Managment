package com.nuhash.kuetcse16;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.card.MaterialCardView;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

class view_holder extends RecyclerView.ViewHolder {
    ImageButton call,message;
    MaterialCardView materialCardView;
    TextView roll,name,phone,blood;
    view_holder(@NonNull View itemView) {
        super(itemView);
        materialCardView=itemView.findViewById(R.id.card_view_adapter);
        blood=itemView.findViewById(R.id.text_view_blood_adapter);
        call=itemView.findViewById(R.id.image_button_call_adapter);
        message=itemView.findViewById(R.id.image_button_message_adapter);
        roll=itemView.findViewById(R.id.text_view_name_adapter);
        name=itemView.findViewById(R.id.text_view_roll_adapter);
        phone=itemView.findViewById(R.id.text_view_phone_adapter);
    }
}
public class Adapter extends RecyclerView.Adapter<view_holder> {
    ArrayList<student>ar;
    Context ctx;

    public Adapter(ArrayList<student> ar, Context ctx) {
        this.ar = ar;
        this.ctx = ctx;
    }

    @NonNull
    @Override
    public view_holder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View inflat = LayoutInflater.from(ctx).inflate(R.layout.adapter_item, viewGroup, false);
        return new view_holder(inflat);
    }

    @Override
    public void onBindViewHolder(@NonNull view_holder holder, int position) {
        student stt = ar.get(position);
        try {
            holder.blood.setText(stt.blood);
            holder.phone.setText(stt.phone);
            holder.name.setText(stt.nickname);
            holder.roll.setText(stt.roll);
            holder.call.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(ctx, "CALL BUTTON", Toast.LENGTH_LONG).show();
                }
            });
            holder.message.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(ctx, "MESSEGE BUTTON", Toast.LENGTH_LONG).show();
                }
            });

            holder.materialCardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(ctx, ProfileActivity.class);
                    ctx.startActivity(intent);
                }
            });

        }catch (Exception e){
            Log.e("DEBUG",e.getMessage());
        }
    }

    @Override
    public int getItemCount() {
        return ar.size();
    }
}
