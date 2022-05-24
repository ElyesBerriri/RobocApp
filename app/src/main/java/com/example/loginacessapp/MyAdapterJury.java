package com.example.loginacessapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.Collection;

public class MyAdapterJury extends RecyclerView.Adapter<MyAdapterJury.MyViewHolder> {

    ArrayList<Team> mList;
    Context context;

    public MyAdapterJury(Context context, ArrayList<Team> mList){
        this.mList = mList;
        this.context = context;
    }

    @NonNull
    @Override
    public MyAdapterJury.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        View v = LayoutInflater.from(context).inflate(R.layout.jury_item,parent, false);
        return new MyAdapterJury.MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyAdapterJury.MyViewHolder holder , int position){
        Team model = mList.get(position);
        holder.name.setText(model.getTeam_id());
        holder.score.setText(model.getScore_jury()+"");
    }

    @Override
    public int getItemCount(){
        return mList.size();
    }
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView name,score;
        public MyViewHolder(@NonNull View itemView){
            super(itemView);
            name = itemView.findViewById(R.id.list_name_jury);
            score = itemView.findViewById(R.id.list_score_jury);
        }
    }
}
