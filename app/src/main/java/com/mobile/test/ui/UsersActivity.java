package com.mobile.test.ui;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.mobile.test.R;
import com.mobile.test.adapter.UsersAdapter;
import com.mobile.test.databinding.ActivityUsersBinding;
import com.mobile.test.model.User;
import com.mobile.test.util.CommonUtils;
import com.mobile.test.viewmodel.UsersViewModel;

import java.util.List;

public class UsersActivity extends AppCompatActivity {
    private ActivityUsersBinding binding;
    private UsersViewModel viewModel;
    private UsersAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_users);
        getSupportActionBar().setTitle("List View");

        initViewModel();

    }


    /**
     * Creating Users list
     *
     * @param users
     */
    private void initRecyclerView(List<User> users) {
        binding.recyclerview.setLayoutManager(new LinearLayoutManager(this));
        adapter = new UsersAdapter(users, this);
        binding.recyclerview.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    /**
     * Initialize ViewModel
     */
    private void initViewModel() {
        viewModel = new ViewModelProvider(this).get(UsersViewModel.class);
        viewModel.getLiveData().observe(this, new Observer<List<User>>() {
            @Override
            public void onChanged(List<User> hotels) {
                if (hotels != null) {
                    initRecyclerView(hotels);
                } else {
                    CommonUtils.getInstance().showSnackMessage(binding.getRoot(), R.string.msg_error_in_getting_data);
                }
            }
        });

        viewModel.getHotelsAPICall();
    }
}