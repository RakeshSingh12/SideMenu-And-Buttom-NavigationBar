package com.techugo.sidemenu;


import android.annotation.TargetApi;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.snackbar.Snackbar;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;


/**
 * Created by vikash on 8/9/17.
 */

public class BaseActivity extends AppCompatActivity {
    public BaseActivity activity;
    public AppPrefence appPrefence;
    private Locale mCurrentLocale;
    private BaseFragment currentFragment;
    private String fragmentTag;
    private ProgressDialog progressDialog;
    private String selectedDate;
    /*  protected SocketHelper socketHelper;*/

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // disableAutofill();
        activity = this;
        appPrefence = AppPrefence.INSTANCE;
        appPrefence.initAppPreferences(this);
       /* if (!SocketHelper.getInstance(this).isConnected()) {
            SocketHelper.getInstance(this).startConnection();
        }*/
        changeLocale();
        //getFcmToken();
    }


    protected boolean getEnterPriseAdminRole(){
        if(appPrefence.getInt(
                        AppPrefence.SharedPreferncesKeys.ENTERPRISE_ADMIN.toString(),
                0
        )==1 ){
            return true;
        }else{
          return false;
        }
    }

    protected boolean getAppSchedulerRole(){
        if(appPrefence.getInt(
                AppPrefence.SharedPreferncesKeys.SCHEDULER.toString(),
                0
        )==2 ){
            return true;
        }else{
            return false;
        }
    }

    protected boolean getAppSupervisorRole(){
        if(appPrefence.getInt(
                AppPrefence.SharedPreferncesKeys.SUPERVISIOR.toString(),
                0
        )==2 ){
            return true;
        }else{
            return false;
        }
    }

    protected boolean getAppSupervisorEvaluatorRole(){
        if(appPrefence.getInt(
                AppPrefence.SharedPreferncesKeys.SUPER_EVALUATOR.toString(),
                0
        )==3){
            return true;
        }else{
            return false;
        }
    }

    protected boolean getAppRecruiterRole(){
        if(appPrefence.getInt(
                AppPrefence.SharedPreferncesKeys.RECRUITER.toString(),
                0
        )==4 ){
            return true;
        }else{
            return false;
        }
    }


    // get device Token
//    private void getFcmToken() {
//        FirebaseInstanceId.getInstance().getInstanceId()
//                .addOnCompleteListener(new OnCompleteListener<InstanceIdResult>() {
//                    @Override
//                    public void onComplete(@NonNull Task<InstanceIdResult> task) {
//                        if (!task.isSuccessful()) {
//                            Log.w("FCM_MESSAGE", "getInstanceId failed", task.getException());
//                            return;
//                        }
//
//                        // Get new Instance ID token
//                        String token = task.getResult().getToken();
//                        appPrefence.setString(AppPrefence.SharedPreferncesKeys.deviceToken.toString(), token);
//                        Log.d("FCM_MESSAGE", appPrefence.getString(AppPrefence.SharedPreferncesKeys.deviceToken.toString()));
//                    }
//                });
//    }

    @TargetApi(Build.VERSION_CODES.O)
    private void disableAutofill() {
        getWindow().getDecorView().setImportantForAutofill(View.IMPORTANT_FOR_AUTOFILL_NO_EXCLUDE_DESCENDANTS);
    }

//    public PlacesClient InitilisePlacesKey(String key, Activity activity) {
//        Places.initialize(getApplicationContext(), "AIzaSyC-kBLYO2qpSUj7AloQQ9Z9r4dETFZeDZ4");
//        PlacesClient placesClient = Places.createClient(activity);
//        return placesClient;
//    }

    @Override
    protected void onStart() {
        super.onStart();
        mCurrentLocale = getResources().getConfiguration().locale;
        //startAnimation();
    }

    @Override
    protected void onResume() {
        super.onResume();

    }


    public String getStringInStringWithNumber(String s) {
        return s.replaceAll("[0-9]", "");
    }

//    public HashMap<String, String> getHashMapFromSharePrepence(String string, Gson gson) {
//        java.lang.reflect.Type type = new TypeToken<HashMap<String, String>>() {
//        }.getType();
//        HashMap<String, String> testHashMap2 = gson.fromJson(string, type);
//        return testHashMap2;
//    }

//    private void startAnimation() {
//        if (appPrefence.getString(AppPrefence.SharedPreferncesKeys.LANGUAGE_KEY.toString(), "").equals("ar")) {
//            overridePendingTransition(R.anim.left_to_right_animation, R.anim.right_to_left_animation);
//        } else {
//            overridePendingTransition(R.anim.right_to_left_animation, R.anim.left_to_right_animation);
//        }
//    }
//
//    private void endAnimation() {
//        if (appPrefence.getString(AppPrefence.SharedPreferncesKeys.LANGUAGE_KEY.toString(), "").equals("ar")) {
//            overridePendingTransition(R.anim.left_to_right_animation, R.anim.right_to_left_animation);
//        } else {
//            overridePendingTransition(R.anim.right_to_left_animation, R.anim.left_to_right_animation);
//        }
//    }

    @Override
    public void finish() {
        super.finish();
        //endAnimation();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //endAnimation();
    }

    public void launchActivity(Class<? extends BaseActivity> activityClass) {
        if (activityClass != null) {
            launchActivity(activityClass, null);
        }
    }

    public void launchActivity(Class<? extends BaseActivity> activityClass, Bundle bundle) {
        Intent intent = new Intent(this, activityClass);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
    }

    public void launchActivityMain(Class<? extends BaseActivity> activityClass) {
        Intent intent = new Intent(this, activityClass);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

    public void replaceFragment(int containerId, BaseFragment fragment) {
        replaceFragment(containerId, fragment, null);
    }

    public void replaceFragment(int containerId, BaseFragment fragment, Bundle bundle) {
        replaceFragment(containerId, fragment, false, bundle);
    }

    public void replaceFragment(int containerId, BaseFragment fragment, boolean isNextFragmentNeedsTobeAdded) {
        replaceFragment(containerId, fragment, isNextFragmentNeedsTobeAdded, null);
    }

    public void replaceFragment(int containerId, BaseFragment fragment, boolean isNextFragmentNeedsTobeAdded, Bundle bundle) {
        replaceFragment(containerId, fragment, 0, 0, 0, 0, isNextFragmentNeedsTobeAdded, bundle);
    }

    public void replaceFragment(int containerId, BaseFragment fragment, int enter, int exit, int enterPop, int exitPop, boolean isNextFragmentNeedsTobeAdded, Bundle bundle) {
        FragmentManager manager = getSupportFragmentManager();
        if (manager != null) {
            FragmentTransaction fragmentTransaction = manager.beginTransaction();
            if (fragment == null) {
                return;
            }

            fragmentTransaction.setCustomAnimations(enter, exit, enterPop, exitPop);

            fragmentTag = fragment.getClass().getSimpleName();

            if (isNextFragmentNeedsTobeAdded) {
                fragmentTransaction.addToBackStack(fragmentTag);
            }
            if (bundle != null) {
                fragment.setArguments(bundle);
            }

            if (fragment.isAdded()) {
                return;
            }

            currentFragment = fragment;

            fragmentTransaction.replace(containerId, fragment, fragmentTag);
            fragmentTransaction.commit();

        }
    }

    public void addFragment(int containerId, BaseFragment fragment, int enter, int exit, int enterPop, int exitPop, boolean isNextFragmentNeedsTobeAdded, Bundle bundle) {
        FragmentManager manager = getSupportFragmentManager();
        if (manager != null) {
            FragmentTransaction fragmentTransaction = manager.beginTransaction();
            if (fragment == null) {
                return;
            }

           /* if(enterPop == 0 && exitPop ==0)
                fragmentTransaction.setCustomAnimations(enter,exit);
            else*/
            fragmentTransaction.setCustomAnimations(enter, exit, enterPop, exitPop);

            fragmentTag = fragment.getClass().getSimpleName();

            if (isNextFragmentNeedsTobeAdded) {
                fragmentTransaction.addToBackStack(fragmentTag);
            }

            if (bundle != null) {
                fragment.setArguments(bundle);
            }

            if (fragment.isAdded()) {
                return;
            }

            currentFragment = fragment;

            fragmentTransaction.add(containerId, fragment, fragmentTag);
            fragmentTransaction.commit();

        }
    }

  /*  public void showMessage(Context context, View v, String tag, int color) {
        if (v != null) {
            Snackbar snack = Snackbar.make(v, tag, Snackbar.LENGTH_LONG);
            View view = snack.getView();//android.support.design.R.id.snackbar_text
            TextView tv = (TextView) view.findViewById();
            tv.setTextColor(color);
            snack.setDuration(3000);
            snack.show();

        }
    }*/

    public void showProgressBarNew() {
        progressDialog = ProgressDialog.show(BaseActivity.this, "", "", true);
        progressDialog.setCanceledOnTouchOutside(false);
        //progressDialog.setContentView(R.layout.progress_layout);
        progressDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
    }

    public void hideProgressBarNew() {
        if (progressDialog != null) {
            progressDialog.dismiss();
            progressDialog=null;
        }
    }

    public void showMessage(Context context, View v, String tag) {
        if (v != null) {
            Snackbar snack = Snackbar.make(v, tag, Snackbar.LENGTH_LONG);
            snack.setDuration(3000);
            snack.setTextColor(context.getResources().getColor(R.color.white));
            snack.show();

        }
    }

    public String convertDateInToTime(String date1) {
        String DATE_FORMAT_I = "yyyy-MM-dd'T'HH:mm:ss";
        String DATE_FORMAT_O = "HH:mm";
        SimpleDateFormat formatInput = new SimpleDateFormat(DATE_FORMAT_I);
        SimpleDateFormat formatOutput = new SimpleDateFormat(DATE_FORMAT_O);
        Date date = null;
        try {
            date = formatInput.parse(date1);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return formatOutput.format(date);
    }

    public String convertDateInToTime2(String date1) {
        String DATE_FORMAT_I = "yyyy-MM-dd HH:mm:ss";
        String DATE_FORMAT_O = "HH:mm";
        SimpleDateFormat formatInput = new SimpleDateFormat(DATE_FORMAT_I);
        SimpleDateFormat formatOutput = new SimpleDateFormat(DATE_FORMAT_O);
        Date date = null;
        try {
            date = formatInput.parse(date1);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return formatOutput.format(date);
    }

    public String convertDateInToDate2(String date1) {
        String DATE_FORMAT_I = "yyyy-MM-dd HH:mm:ss";
        String DATE_FORMAT_O = "EEE dd MMM yyyy";
        SimpleDateFormat formatInput = new SimpleDateFormat(DATE_FORMAT_I);
        SimpleDateFormat formatOutput = new SimpleDateFormat(DATE_FORMAT_O);
        Date date = null;
        try {
            date = formatInput.parse(date1);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return formatOutput.format(date);
    }

    public String convertDateInToCheckinComplete(String date1) {
        String DATE_FORMAT_I = "yyyy-MM-dd";
        String DATE_FORMAT_O = "EEE dd MMM yyyy";
        SimpleDateFormat formatInput = new SimpleDateFormat(DATE_FORMAT_I);
        SimpleDateFormat formatOutput = new SimpleDateFormat(DATE_FORMAT_O);
        Date date = null;
        try {
            date = formatInput.parse(date1);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return formatOutput.format(date);
    }

    public String convertTimeInToCheckinComplete(String date1) {
        String DATE_FORMAT_I = "hh:mm:ss";
        String DATE_FORMAT_O = "hh:mm a";
        SimpleDateFormat formatInput = new SimpleDateFormat(DATE_FORMAT_I);
        SimpleDateFormat formatOutput = new SimpleDateFormat(DATE_FORMAT_O);
        Date date = null;
        try {
            date = formatInput.parse(date1);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return formatOutput.format(date);
    }

    public String convertDateInToDate3(String date1) {
        String DATE_FORMAT_I = "yyyy-MM-dd'T'HH:mm:ss";
        String DATE_FORMAT_O = "EEE dd MMM yyyy";
        SimpleDateFormat formatInput = new SimpleDateFormat(DATE_FORMAT_I);
        SimpleDateFormat formatOutput = new SimpleDateFormat(DATE_FORMAT_O);
        Date date = null;
        try {
            date = formatInput.parse(date1);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return formatOutput.format(date);
    }

    public String convertDateInToHeaderDate(String date1) {
        String DATE_FORMAT_I = "yyyy-MM-dd'T'HH:mm:ss";
        String DATE_FORMAT_O = "EEE dd MMM ";
        SimpleDateFormat formatInput = new SimpleDateFormat(DATE_FORMAT_I);
        SimpleDateFormat formatOutput = new SimpleDateFormat(DATE_FORMAT_O);
        Date date = null;
        try {
            date = formatInput.parse(date1);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return formatOutput.format(date);
    }

    public String convertDateInToDate(String date1) {
        String DATE_FORMAT_I = "yyyy-MM-dd";
        String DATE_FORMAT_O = "EEE dd MMM";
        SimpleDateFormat formatInput = new SimpleDateFormat(DATE_FORMAT_I);
        SimpleDateFormat formatOutput = new SimpleDateFormat(DATE_FORMAT_O);
        Date date = null;
        try {
            date = formatInput.parse(date1);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return formatOutput.format(date);
    }

    public String convertDateInToServerDate(String date1) {
        if (!TextUtils.isEmpty(date1)) {
            String DATE_FORMAT_I = "dd/MM/yyyy";
            String DATE_FORMAT_O = "yyyy-MM-dd'T'HH:mm:ss";
            SimpleDateFormat formatInput = new SimpleDateFormat(DATE_FORMAT_I);
            SimpleDateFormat formatOutput = new SimpleDateFormat(DATE_FORMAT_O);
            Date date = null;
            try {
                date = formatInput.parse(date1);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            return formatOutput.format(date);
        } else {
            return "";
        }
    }

    public String convertServerDateInToDate(String date1) {
        String DATE_FORMAT_I = "yyyy-MM-dd'T'HH:mm:ss";
        String DATE_FORMAT_O = "dd/MM/yyyy";
        SimpleDateFormat formatInput = new SimpleDateFormat(DATE_FORMAT_I);
        SimpleDateFormat formatOutput = new SimpleDateFormat(DATE_FORMAT_O);
        Date date = null;
        try {
            date = formatInput.parse(date1);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return formatOutput.format(date);
    }

    public String convertDateToDate(String date1) {
        String DATE_FORMAT_I = "dd MMM yyyy hh:mm";
        String DATE_FORMAT_O = "dd MMM yyyy";
        SimpleDateFormat formatInput = new SimpleDateFormat(DATE_FORMAT_I);
        SimpleDateFormat formatOutput = new SimpleDateFormat(DATE_FORMAT_O);
        Date date = null;
        try {
            date = formatInput.parse(date1);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return formatOutput.format(date);
    }

    public String convertDateToTime(String date1) {
        String DATE_FORMAT_I = "dd MMM yyyy hh:mm";
        String DATE_FORMAT_O = "hh:mm   ";
        SimpleDateFormat formatInput = new SimpleDateFormat(DATE_FORMAT_I);
        SimpleDateFormat formatOutput = new SimpleDateFormat(DATE_FORMAT_O);
        Date date = null;
        try {
            date = formatInput.parse(date1);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return formatOutput.format(date);
    }


    public String convertCardDateInToServerDate(String date1) {
        String DATE_FORMAT_I = "MM/yy";
        String DATE_FORMAT_O = "yyyy-MM-dd'T'HH:mm:ss";
        SimpleDateFormat formatInput = new SimpleDateFormat(DATE_FORMAT_I);
        SimpleDateFormat formatOutput = new SimpleDateFormat(DATE_FORMAT_O);
        Date date = null;
        try {
            date = formatInput.parse(date1);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return formatOutput.format(date);
    }

    public String getDayAndMonth(String date1) {
        String DATE_FORMAT_I = "yyyy-MM-dd'T'HH:mm:ss";
        String DATE_FORMAT_O = "dd-MM";
        SimpleDateFormat formatInput = new SimpleDateFormat(DATE_FORMAT_I);
        SimpleDateFormat formatOutput = new SimpleDateFormat(DATE_FORMAT_O);
        Date date = null;
        try {
            date = formatInput.parse(date1);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return formatOutput.format(date);
    }

    public String getConfirmationDate(String date1) {
        String DATE_FORMAT_I = "yyyy-MM-dd'T'HH:mm:ss";
        String DATE_FORMAT_O = "dd-MM-yyyy";
        SimpleDateFormat formatInput = new SimpleDateFormat(DATE_FORMAT_I);
        SimpleDateFormat formatOutput = new SimpleDateFormat(DATE_FORMAT_O);
        Date date = null;
        try {
            date = formatInput.parse(date1);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return formatOutput.format(date);
    }

    public void showToast(String msg) {
        Toast toast = Toast.makeText(BaseActivity.this, msg, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }


    public void showProgressBar() {
        progressDialog = ProgressDialog.show(BaseActivity.this, "", "", true);
        progressDialog.setCanceledOnTouchOutside(false);
        //progressDialog.setContentView(R.layout.progress_layout);
        progressDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
    }

    public void hideProgressBar() {
        if (progressDialog != null) {
            progressDialog.dismiss();
        }
    }


    // date picker
   /* fun datePicker(textView: TextView) {
        val mYear: Int
        val mMonth: Int
        val mDay: Int
        // Get Current Date
        val c = Calendar.getInstance()
        mYear = c.get(Calendar.YEAR)
        mMonth = c.get(Calendar.MONTH)
        mDay = c.get(Calendar.DAY_OF_MONTH)
        val datePickerDialog = DatePickerDialog(
                this,
                DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
                textView.text = dayOfMonth.toString() + "-" + (monthOfYear + 1) + "-" + year
        }, mYear, mMonth, mDay
        )
        datePickerDialog.show()

    }*/

    /* Language Setting PAge*/


    public void changeLocale() {
        Locale locale = new Locale("ar", "SA");
        Locale.setDefault(locale);
        android.content.res.Configuration config = new android.content.res.Configuration();

        config.locale = locale;
        config.setLocale(locale);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            config.setLayoutDirection(config.locale);
        }
        getBaseContext().getResources().updateConfiguration(config,
                getBaseContext().getResources().getDisplayMetrics());


        String language = appPrefence.getString(AppPrefence.SharedPreferncesKeys.LANGUAGE.toString(), "English");
        if (language.equals("Arabic")) {
            Locale locale1 = new Locale("ar", "SA");
            Locale.setDefault(locale1);
            android.content.res.Configuration config1 = new android.content.res.Configuration();

            config1.locale = locale1;
            config1.setLocale(locale1);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                config1.setLayoutDirection(config1.locale);
            }

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                config.setLocale(locale);
                this.getApplicationContext().createConfigurationContext(config);
            }
            getBaseContext().getResources().updateConfiguration(config1,
                    getBaseContext().getResources().getDisplayMetrics());
            Log.e("Appplanguaiiii", Locale.getDefault().getLanguage());

        }
        if (language.equals("English")) {
            String languageToLoad = "en_us";
            Locale locale1 = new Locale(languageToLoad);
            Locale.setDefault(locale1);
            android.content.res.Configuration config1 = new android.content.res.Configuration();

            config1.locale = locale1;
            config1.setLocale(locale1);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                config1.setLayoutDirection(config1.locale);
            }
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                config.setLocale(locale);
                this.getApplicationContext().createConfigurationContext(config);
            }
            getBaseContext().getResources().updateConfiguration(config1,
                    getBaseContext().getResources().getDisplayMetrics());
            Log.e("Appplanguaiiii", Locale.getDefault().getLanguage());


        }


    }

}
