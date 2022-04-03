package com.mobile.test.ui;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.gson.Gson;
import com.mobile.test.R;
import com.mobile.test.databinding.ActivityAddVoteBinding;
import com.mobile.test.model.Voter;

public class AddVoteActivity extends AppCompatActivity {
    private ActivityAddVoteBinding binding;
    private ClickHandlers handlers;

    private Gson gson;
    private Voter voter;
    private DatabaseReference voteRef;
    private long voteCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_add_vote);
        handlers = new ClickHandlers(this);
        binding.setHandlers(handlers);

        gson = new Gson();
        voter = gson.fromJson(getIntent().getStringExtra("VOTER"), Voter.class);
        binding.textName.setText(voter.getName());
        voteCount = voter.getVotes();
        binding.textVotes.setText("" + voteCount);

        // Write a message to the database
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference dbRef = database.getReference();
        voteRef = dbRef.child("Voters").child(voter.getName());
    }


    public class ClickHandlers {
        Context context;

        public ClickHandlers(Context context) {
            this.context = context;
        }

        public void onAddClicked(View view) {
            voteCount = voteCount + 1;
            voteRef.setValue(voteCount);
            binding.textVotes.setText("" + voteCount);
        }

        public void onRemoveClicked(View view) {
            voteCount = voteCount - 1;
            voteRef.setValue(voteCount);
            binding.textVotes.setText("" + voteCount);
        }
    }
}