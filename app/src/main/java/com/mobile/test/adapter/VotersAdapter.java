package com.mobile.test.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.mobile.test.databinding.VotesBinding;
import com.mobile.test.listner.Presenter;
import com.mobile.test.model.Voter;
import com.mobile.test.ui.AddVoteActivity;
import com.mobile.test.ui.HomeActivity;
import com.mobile.test.ui.VotersActivity;

import java.util.List;

public class VotersAdapter extends RecyclerView.Adapter<VotersAdapter.VoteView> {

    List<Voter> voterList;
    private LayoutInflater layoutInflater;
    private Context context;

    public VotersAdapter(List<Voter> voters, Context context) {
        this.voterList = voters;
        this.context = context;
    }

    @NonNull
    @Override
    public VoteView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (layoutInflater == null) {
            layoutInflater = LayoutInflater.from(parent.getContext());
        }
        final VotesBinding votesBinding = VotesBinding.inflate(layoutInflater, parent, false);
        votesBinding.setPresenter(new Presenter() {
            @Override
            public void onClick() {
                Voter voter = votesBinding.getVoteView();
                String voterJson = new Gson().toJson(voter);

                Intent i = new Intent(context, AddVoteActivity.class);
                i.putExtra("VOTER", voterJson);
                context.startActivity(i);
            }
        });
        return new VoteView(votesBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull VoteView holder, int position) {
        Voter voter = voterList.get(position);
        holder.bind(voter);
        holder.votesBinding.textName.setText(voter.getName());
        holder.votesBinding.textVotes.setText("" + voter.getVotes());
    }

    @Override
    public int getItemCount() {
        if (voterList == null)
            return 0;
        else
            return voterList.size();
    }

    public class VoteView extends RecyclerView.ViewHolder {
        private VotesBinding votesBinding;

        public VoteView(VotesBinding votesBinding) {
            super(votesBinding.getRoot());

            this.votesBinding = votesBinding;
        }

        public void bind(Voter voter) {
            this.votesBinding.setVoteView(voter);
        }

    }

}