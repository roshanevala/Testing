package com.mobile.test.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.mobile.test.R;
import com.mobile.test.databinding.ActivityHomeBinding;

public class HomeActivity extends AppCompatActivity {
    private ActivityHomeBinding binding;
    private ClickHandlers handlers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_home);
        handlers = new ClickHandlers(this);
        binding.setHandlers(handlers);
    }


    public class ClickHandlers {
        Context context;

        public ClickHandlers(Context context) {
            this.context = context;
        }

        public void onUserListClicked(View view) {
            startActivity(new Intent(HomeActivity.this, UsersActivity.class));
        }

        public void onVotingAppClicked(View view) {
            startActivity(new Intent(HomeActivity.this, VotersActivity.class));
        }
    }
}