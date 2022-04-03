package com.mobile.test.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mobile.test.databinding.UserBinding;
import com.mobile.test.model.User;

import java.util.List;

public class UsersAdapter extends RecyclerView.Adapter<UsersAdapter.UserView> {

    List<User> userList;
    private LayoutInflater layoutInflater;
    private Context context;

    public UsersAdapter(List<User> users, Context context) {
        this.userList = users;
        this.context = context;
    }

    @NonNull
    @Override
    public UserView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (layoutInflater == null) {
            layoutInflater = LayoutInflater.from(parent.getContext());
        }
        final UserBinding userBinding = UserBinding.inflate(layoutInflater, parent, false);

        return new UserView(userBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull UserView holder, int position) {
        User user = userList.get(position);
        holder.bind(user);
        holder.userBinding.textName.setText(user.getName());
        holder.userBinding.textEmail.setText(user.getEmail());
        holder.userBinding.textPhone.setText(user.getPhone());
        holder.userBinding.textAddress.setText(String.format("%s\n%s\n%s", user.getAddress().getStreet(), user.getAddress().getSuite(), user.getAddress().getCity()));

    }

    @Override
    public int getItemCount() {
        if (userList == null)
            return 0;
        else
            return userList.size();
    }

    public class UserView extends RecyclerView.ViewHolder {
        private UserBinding userBinding;

        public UserView(UserBinding userBinding) {
            super(userBinding.getRoot());

            this.userBinding = userBinding;
        }

        public void bind(User user) {
            this.userBinding.setUserView(user);
        }

    }

}