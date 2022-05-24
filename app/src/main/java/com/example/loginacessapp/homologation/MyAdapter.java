package com.example.loginacessapp.homologation;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.loginacessapp.R;
import com.example.loginacessapp.Team;
import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    ArrayList<Team> mList;
    Context context;

    public MyAdapter(Context context, ArrayList<Team> mList){
        this.mList = mList;
        this.context = context;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        View v = LayoutInflater.from(context).inflate(R.layout.item_of_list,parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder , int position){
    Team model = mList.get(position);
    holder.name.setText(model.getTeam_id());
    holder.score.setText(model.getScore_homologation()+"");
    }

    @Override
    public int getItemCount(){
        return mList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView name,score;

        public MyViewHolder(@NonNull View itemView){
            super(itemView);
            name = itemView.findViewById(R.id.list_name);
            score = itemView.findViewById(R.id.list_score);
        }
    }
}
