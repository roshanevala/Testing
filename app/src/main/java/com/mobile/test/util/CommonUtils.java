package com.mobile.test.util;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;

import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.mobile.test.app.MyApplication;
import com.mobile.test.ui.HomeActivity;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;

/**
 * Common Utils class for Practical
 * <p>
 * Created by roshane on 4/3/22.
 */
public class CommonUtils {
    private static CommonUtils commonUtils;
    private ProgressDialog mProgressDialog;

    /**
     * Singleton method for CommonUtils
     *
     * @return CommonUtils
     */
    public static CommonUtils getInstance() {
        if (commonUtils == null) {
            commonUtils = new CommonUtils();
        }
        return commonUtils;
    }


    public DateFormat getDateFormat() {
        return new SimpleDateFormat("hh:mm a", Locale.US);
    }

    public DateFormat getDayFormat() {
        return new SimpleDateFormat("dd MMM hh:mm a", Locale.US);
    }


    /**
     * Display Toast Message in Application
     *
     * @param message Display Toast message
     */
    public void showToastMessage(int message) {
        Toast.makeText(MyApplication.getAppContext(),
                MyApplication.getAppContext().getResources().getString(message),
                Toast.LENGTH_SHORT).show();
    }

    /**
     * Display Snack Message in Application
     *
     * @param view    Display view
     * @param message Display Snack message
     */
    public void showSnackMessage(View view, int message) {
        Snackbar.make(view, MyApplication.getAppContext().getResources().getString(message), Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();
    }

    /**
     * Display Snack Message in Application
     *
     * @param view    Display view
     * @param message Display Snack message
     */
    public void showSnackMessage(View view, String message) {
        Snackbar.make(view, message, Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();
    }

    /**
     * Check Internet Connection is Available or not
     *
     * @return isInternetAvailable
     */
    public boolean isInternetAvailable() {
        ConnectivityManager cm =
                (ConnectivityManager) MyApplication.getAppContext()
                        .getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();


        return activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();
    }

    /**
     * A 64-bit number (as a hex string) that is randomly generated
     * when the user first sets up the device and
     * should remain constant for the lifetime of the user's device.
     *
     * @return deviceId - Android device ID
     */
    @SuppressLint("HardwareIds")
    public String getDeviceID() {

        return Settings.Secure.getString(MyApplication.getAppContext().getContentResolver()
                , Settings.Secure.ANDROID_ID);

    }

    /**
     * @return device name
     */
    public String getDeviceName() {
        return Build.MODEL;
    }

    /**
     * @return device type
     */
    public String getDeviceType() {
        return Build.TYPE;
    }

    /**
     * This provides a convenient way to create an intent that is
     * intended to execute and navigate a new class
     *
     * @param packageContext A Context of the application package implementing
     *                       this class.
     * @param cls            The component class that is to be used for the intent.
     */
    public void navigateTo(Context packageContext, Class<?> cls) {
        navigateTo(packageContext, cls, null);
    }

    /**
     * This provides a convenient way to create an intent that is
     * intended to execute and navigate a new class
     *
     * @param packageContext A Context of the application package implementing
     *                       this class.
     * @param cls            The component class that is to be used for the intent.
     * @param bundle         Bundle Value
     */
    public void navigateTo(Context packageContext, Class<?> cls, Bundle bundle) {
        Intent intent = new Intent(packageContext, cls);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        packageContext.startActivity(intent);
    }

    /**
     * Show Soft Keyboard in UI
     *
     * @param view current activity
     */
    public void showKeyBoard(View view) {
        InputMethodManager imm = (InputMethodManager) MyApplication.getAppContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(view, InputMethodManager.SHOW_IMPLICIT);
    }

    /**
     * Hide Soft Keyboard in UI
     *
     * @param view current activity
     */
    public void hideKeyboard(View view) {
        if (view != null) {
            InputMethodManager inputManager = (InputMethodManager) MyApplication.getAppContext().getSystemService(Context.INPUT_METHOD_SERVICE);
            inputManager.hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }

    /**
     * Display Progress Dialog, while doing any AsyncTask work
     *
     * @param activity current activity
     * @param message  description
     */
    public void showProgressDialog(Activity activity, int message) {
        // If already progress dialog is loading
        // dismiss the dialog
        hideProgressDialog();

        // Create a new progress dialog
        mProgressDialog = new ProgressDialog(activity);
        mProgressDialog.setMessage(MyApplication.getAppContext().getString(message));
        mProgressDialog.setCancelable(false);
        mProgressDialog.show();
    }

    /**
     * Hide Progress Dialog, After finish the AsyncTAsk Work
     */
    public void hideProgressDialog() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.dismiss();
        }
    }

    /**
     * Show Logout Alert
     */
    public void logoutAlert(Context context) {

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Sign out");
        builder.setMessage("Are you sure you want to logout?");
        builder.setPositiveButton("Yes", (dialog, which) -> {
            FirebaseAuth.getInstance().signOut();
            Intent intent = new Intent(context, HomeActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
            ((Activity) context).finish();
        });
        builder.setNegativeButton("No", (dialog, which) -> dialog.cancel());
        builder.show();
    }

    /**
     * Validate Email
     *
     * @param email email to validate
     * @return verification
     */
    public boolean isValidMail(String email) {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    /**
     * Validate Phone
     *
     * @param phone number to check
     * @return verification
     */
    public boolean isValidMobile(String phone) {
        return android.util.Patterns.PHONE.matcher(phone).matches();
    }

}
