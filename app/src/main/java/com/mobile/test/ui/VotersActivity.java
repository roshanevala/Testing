package com.mobile.test.ui;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.util.Log;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.mobile.test.R;
import com.mobile.test.adapter.VotersAdapter;
import com.mobile.test.databinding.ActivityVotersBinding;
import com.mobile.test.model.Voter;
import com.mobile.test.util.CommonUtils;

import java.util.ArrayList;
import java.util.List;

public class VotersActivity extends AppCompatActivity {
    private ActivityVotersBinding binding;
    private List<Voter> voterList;
    private VotersAdapter adapter;
    private DatabaseReference voteRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_voters);
        getSupportActionBar().setTitle("List View");

        voterList = new ArrayList<>();
        binding.recyclerview.setLayoutManager(new LinearLayoutManager(this));
        adapter = new VotersAdapter(voterList, this);
        binding.recyclerview.setAdapter(adapter);
        // Write a message to the database
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference dbRef = database.getReference();
        voteRef = dbRef.child("Voters");
    }

    @Override
    protected void onResume() {
        super.onResume();
        voterList.clear();
        voteRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                String name = snapshot.getKey();
                long value = snapshot.getValue(Long.class);
                Voter voter = new Voter();
                voter.setName(name);
                voter.setVotes(value);
                voterList.add(voter);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}