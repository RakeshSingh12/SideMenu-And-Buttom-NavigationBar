package com.techugo.sidemenu;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

/**
 * Created by vikash on 8/9/17.
 */

public class BaseFragment extends Fragment {
    public BaseActivity activity;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        activity = (BaseActivity) context;
    }

    protected void launchActivityForResult(int reqCode, Bundle bundle, Class<? extends BaseActivity> activityClass) {
        Intent intent = new Intent(activity, activityClass);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivityForResult(intent, reqCode);
    }


    protected boolean getEnterPriseAdminRole(){
        if(activity.appPrefence.getInt(
                AppPrefence.SharedPreferncesKeys.ENTERPRISE_ADMIN.toString(),
                0
        )==1){

            return true;
        }else{
            return false;
        }
    }

    protected boolean getAppSchedulerRole(){
        if(activity.appPrefence.getInt(
                AppPrefence.SharedPreferncesKeys.SCHEDULER.toString(),
                0
        )==2 ){
            return true;
        }else{
            return false;
        }
    }

    protected boolean getAppSupervisorRole(){
        if(activity.appPrefence.getInt(
                AppPrefence.SharedPreferncesKeys.SUPERVISIOR.toString(),
                0
        )==2 ){
            return true;
        }else{
            return false;
        }
    }

    protected boolean getAppSupervisorEvaluatorRole(){
        if(activity.appPrefence.getInt(
                AppPrefence.SharedPreferncesKeys.SUPER_EVALUATOR.toString(),
                0
        )==3 ){
            return true;
        }else{
            return false;
        }
    }

    protected boolean getAppRecruiterRole(){
        if(activity.appPrefence.getInt(
                AppPrefence.SharedPreferncesKeys.RECRUITER.toString(),
                0
        )==4 ){
            return true;
        }else{
            return false;
        }
    }
}
