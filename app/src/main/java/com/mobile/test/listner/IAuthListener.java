package com.mobile.test.listner;

public interface IAuthListener {

    void onSuccess(int code);
    void onFail(int code);
    void onException(int code);
}
