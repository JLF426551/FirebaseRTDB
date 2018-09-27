package com.androidorange.example.firebasertdb;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class PlayerAdapter extends RecyclerView.Adapter<PlayerAdapter.mViewHolder> {

    //Variable sets to 0 by default. Updated by setList method.
    private int totalPlayers = 0;

    private ArrayList<Player> playerList;

    public PlayerAdapter(ArrayList<Player> list) {
        playerList = list;
        totalPlayers = list.size();
    }

    @NonNull
    @Override
    public mViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        final boolean attachToParent = false;

        View viewToProcess = inflater.inflate(R.layout.single_player, parent, attachToParent);

        return new mViewHolder(viewToProcess);
    }

    @Override
    public void onBindViewHolder(@NonNull mViewHolder holder, int position) {
        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        return totalPlayers;
    }


    public class mViewHolder extends RecyclerView.ViewHolder {

        TextView playerView;
        Context context;

        mViewHolder(View viewParam) {
            super(viewParam);
            context = viewParam.getContext();
            playerView = viewParam.findViewById(R.id.player_view);
        }

        void bind(int currentItemPosition) {
            playerView.setText(playerList.get(currentItemPosition).getSummary());
        }

    }

}