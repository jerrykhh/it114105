package com.game.tictacteo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.game.tictacteo.model.UserRanking;

import java.util.ArrayList;

public class UserRankingAdapter extends RecyclerView.Adapter<UserRankingAdapter.Viewholder> {

    private Context context;
    private ArrayList<UserRanking> users;

    // Constructor
    public UserRankingAdapter(Context context, ArrayList<UserRanking> users) {
        this.context = context;
        this.users = users;
    }

    @NonNull
    @Override
    public UserRankingAdapter.Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_ranking_card_row, parent, false);
        return new Viewholder(view);
    }


    // Set Text for each row
    @Override
    public void onBindViewHolder(@NonNull UserRankingAdapter.Viewholder holder, int position) {
        UserRanking user = users.get(position);
        holder.tvRankingNo.setText(String.valueOf(position+1));
        holder.tvRankingName.setText("Name: " + user.getName());
        holder.tvRankingDuration.setText("Duration: " + String.valueOf(user.getDuration()));
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    // Init find Element ID;
    public class Viewholder extends RecyclerView.ViewHolder {
        private TextView tvRankingNo, tvRankingName, tvRankingDuration;

        public Viewholder(@NonNull View itemView) {
            super(itemView);
            tvRankingNo = itemView.findViewById(R.id.tvRecordDateTime);
            tvRankingName = itemView.findViewById(R.id.tvRecordState);
            tvRankingDuration = itemView.findViewById(R.id.tvRecordDuration);
        }
    }
}
