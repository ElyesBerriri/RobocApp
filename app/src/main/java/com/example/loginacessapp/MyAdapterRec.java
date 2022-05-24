package com.example.loginacessapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapterRec extends RecyclerView.Adapter<MyAdapterRec.MyViewHolder> {

    ArrayList<Team> mList;
    Context context;

    public MyAdapterRec(Context context, ArrayList<Team> mList){
        this.mList = mList;
        this.context = context;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        View v = LayoutInflater.from(context).inflate(R.layout.rec_item,parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder , int position){
    Team model = mList.get(position);
    holder.name.setText(model.getTeam_id());
    }

    @Override
    public int getItemCount(){
        return mList.size();
    }
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView name;
        public MyViewHolder(@NonNull View itemView){
            super(itemView);
            name = itemView.findViewById(R.id.list_name_rec);
        }
    }
}
