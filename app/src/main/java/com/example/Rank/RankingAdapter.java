package com.example.Rank;

import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mars1.R;

import java.util.ArrayList;

public class RankingAdapter extends RecyclerView.Adapter<RankingAdapter.ItemViewHolder> {

    private ArrayList<Rank> listData;

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.ranking_item, parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        holder.onBind(listData.get(position));
    }

    @Override
    public int getItemCount() {
        if (listData != null) {
            return listData.size();
        }
        return 0;
    }

    public void setRankList(ArrayList<Rank> list){
        this.listData = list;
        notifyDataSetChanged();
    }

    class ItemViewHolder extends RecyclerView.ViewHolder {

        TextView rank;
        TextView name;
        TextView date;
        TextView score;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);

            rank = itemView.findViewById(R.id.rank);
            name = itemView.findViewById(R.id.name);
            date = itemView.findViewById(R.id.date);
            score = itemView.findViewById(R.id.score);
        }

        void onBind(Rank data) {
            rank.setText(data.getRanking());
            name.setText(data.getName());
            date.setText(data.getPlay_at());
            score.setText(data.getScore());
        }
    }
}

