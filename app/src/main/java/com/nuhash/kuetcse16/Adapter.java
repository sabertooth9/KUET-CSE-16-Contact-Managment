package com.nuhash.kuetcse16;

import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.transition.Transition;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;


import com.google.android.material.button.MaterialButton;
import com.google.android.material.card.MaterialCardView;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

class view_holder extends RecyclerView.ViewHolder {
    MaterialButton call,message;
    CardView materialCardView;
    TextView roll,name,phone,blood;
    view_holder(@NonNull View itemView) {
        super(itemView);
        materialCardView=itemView.findViewById(R.id.card_view_adapter);
        blood=itemView.findViewById(R.id.text_view_blood_adapter);
        call=itemView.findViewById(R.id.button_call_adapter);
        message=itemView.findViewById(R.id.button_text_adapter);
        roll=itemView.findViewById(R.id.text_view_roll_adapter);
        name=itemView.findViewById(R.id.text_view_name_adapter);
        phone=itemView.findViewById(R.id.text_view_phone_adapter);
    }
}
public class Adapter extends RecyclerView.Adapter<view_holder> implements Filterable {
    static student stt1;
    student stt;
    ArrayList<student>arx,ar;
    Context ctx;
    Transition transition;

    public Adapter(ArrayList<student> ar, Context ctx) {
        this.arx = ar;
        this.ctx = ctx;
        this.ar=ar;
    }

    @NonNull
    @Override
    public view_holder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View inflat = LayoutInflater.from(ctx).inflate(R.layout.adapter_item, viewGroup, false);
        return new view_holder(inflat);
    }

    @Override
    public void onBindViewHolder(@NonNull view_holder holder, final int position) {
        stt = ar.get(position);
        try {
            holder.blood.setText(stt.blood);
            holder.phone.setText(stt.phone);
            holder.name.setText(stt.nickname);
            holder.roll.setText(stt.roll);
            holder.call.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent(Intent.ACTION_DIAL);
                    intent.setData(Uri.parse("tel:"+ar.get(position).getPhone()));
                    ctx.startActivity(intent);
                    //Toast.makeText(ctx, "CALL BUTTON", Toast.LENGTH_LONG).show();
                }
            });
            holder.message.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent(Intent.ACTION_VIEW);
                    intent.setData(Uri.fromParts("sms",ar.get(position).getPhone(),null));
                    ctx.startActivity(intent);
                    //Toast.makeText(ctx, "MESSEGE BUTTON", Toast.LENGTH_LONG).show();
                }
            });

            holder.materialCardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    stt1=ar.get(position);
                    Intent intent = new Intent(ctx, ProfileActivity.class);
                    intent.putExtra("position",position);
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

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                String charString = constraint.toString();
                ArrayList<student>xx=new ArrayList<>();
                if (charString.isEmpty()) {
                    xx = arx;
                } else {
                    student tmp;
                    for (int i = 0; i < arx.size(); i++) {
                        tmp = arx.get(i);
                        if (tmp.contains(charString)) {
                            xx.add(tmp);
                        }
                    }
                }
                FilterResults filterResults=new FilterResults();
                filterResults.values=xx;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                try {
                    ar = (ArrayList<student>) results.values;
                    Log.e("DEBUG RES", ar.size() + "");
                    notifyDataSetChanged();
                }catch (Exception e){
                    Log.e("DEBUG",e.getMessage());
                }
            }
        };
    }
}
