package com.game.tictacteo;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.game.tictacteo.model.GameLog;
import com.game.tictacteo.model.UserRanking;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class UserRecordAdapter extends RecyclerView.Adapter<UserRecordAdapter.Viewholder> {

    private Context context;
    private ArrayList<GameLog> logs;

    public UserRecordAdapter(Context context, ArrayList<GameLog> logs) {
        this.context = context;
        this.logs = logs;
    }

    @NonNull
    @Override
    public UserRecordAdapter.Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_record_card_row, parent, false);
        return new Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserRecordAdapter.Viewholder holder, int position) {
        GameLog log = logs.get(position);

        SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat timeFormat = new SimpleDateFormat("hh:mm aa");

        holder.tvRecordDateTime.setText(dateFormat.format(log.getPlayDate()) + " " + timeFormat.format(log.getPlayTime()));
        holder.tvRecordState.setText("WinState: " + log.winStateToString());
        if(log.getWinningStatus() == 0)
            holder.tvRecordState.setTextColor(Color.RED);
        else if (log.getWinningStatus() == 1)
            holder.tvRecordState.setTextColor(Color.GREEN);
        else
            holder.tvRecordState.setTextColor(Color.BLACK);

        holder.tvRecordDuration.setText("Duration: " + String.valueOf(log.getDuration()) + " sec");
    }

    @Override
    public int getItemCount() {
        return logs.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder {
        private TextView tvRecordDateTime, tvRecordState, tvRecordDuration;

        public Viewholder(@NonNull View itemView) {
            super(itemView);
            tvRecordDateTime = itemView.findViewById(R.id.tvRecordDateTime);
            tvRecordState = itemView.findViewById(R.id.tvRecordState);
            tvRecordDuration = itemView.findViewById(R.id.tvRecordDuration);
        }
    }
}
