package com.mobile.test.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.mobile.test.model.User;
import com.mobile.test.network.RetroRepository;

import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class UsersViewModel extends ViewModel {
    MutableLiveData<List<User>> liveData;


    @Inject
    public UsersViewModel() {
        liveData = new MutableLiveData<>();
    }

    public MutableLiveData<List<User>> getLiveData() {
        return liveData;
    }

    public void getHotelsAPICall() {
        RetroRepository retroRepository = new RetroRepository();
        retroRepository.getUsersAPICall(liveData);
    }
}
