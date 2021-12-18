package com.techugo.sidemenu;


import android.content.Context;
import android.content.SharedPreferences;


/* this prefrence use the enum for dynamic and standard use
 * */

public enum AppPrefence {
    INSTANCE;

    private static final String SHARED_PREFERENCE_NAME = "parttimeappenterprise";
    private SharedPreferences mPreferences;
    private SharedPreferences.Editor mEditor;

    public boolean isFirstTimeLaunch() {
        return mPreferences.getBoolean(SharedPreferncesKeys.IS_FIRST_TIME_LAUNCH.toString(), true);
    }

    public void setFirstTimeLaunch(boolean isFirstTime) {
        mEditor.putBoolean(SharedPreferncesKeys.IS_FIRST_TIME_LAUNCH.toString(), isFirstTime);
        mEditor.commit();
    }

    /**
     * private constructor for singleton class
     *
     * @param context
     */
    public void initAppPreferences(Context context) {
        mPreferences = context.getSharedPreferences(SHARED_PREFERENCE_NAME, Context.MODE_PRIVATE);
        mEditor = mPreferences.edit();
    }

    public int getAppVersion() {
        return mPreferences.getInt(SharedPreferncesKeys.appVersion.toString(), 0);
    }

    /* getapp version
     * */

    /*
     * set App version
     * */
    public void setAppVersion(int value) {
        mEditor.putInt(SharedPreferncesKeys.appVersion.toString(), value);
        mEditor.commit();
    }

    public void setString(String key, String value) {
        mEditor.putString(key, value);
        mEditor.commit();
    }

    /*
     *  set prefenceValue
     * */

    /*
     *  get prefence Value
     * */
    public void setLong(String key, long value) {
        mEditor.putLong(key, value);
        mEditor.commit();
    }

    public void setInt(String key, int value) {
        mEditor.putInt(key, value);
        mEditor.commit();
    }

    public void setBoolean(String key, boolean value) {
        mEditor.putBoolean(key, value);
        mEditor.commit();
    }

    public String getString(String key) {
        return mPreferences.getString(key, null);
    }

   /* public void setAppdata(String key, AppData appData) {
        if (appData != null) {
            mPreferences.edit().putString(key, GsonUtils.getJson(appData)).commit();
        } else {
            mPreferences.edit().putString(key, "").commit();
        }
    }
*/
/*
    public void setLoginData(String key, LoggedInCustomerDetails customerDetails) {
        if (customerDetails != null) {
            mPreferences.edit().putString(key, GsonUtils.getJson(customerDetails)).commit();
        } else {
            mPreferences.edit().putString(key, "").commit();
        }
    }

    public void setReservationList(ArrayList<MyReservationList> reservationData) {
        if (reservationData != null) {
            mPreferences.edit().putString(AppConstant.RESERVATIONLIST, GsonUtils.getJson(reservationData)).commit();
        } else {
            mPreferences.edit().putString(AppConstant.RESERVATIONLIST, "").commit();
        }

    }

    public ArrayList<MyReservationList> getReservationList() {
        Type type = new TypeToken<ArrayList<MyReservationList>>() {}.getType();
        String mPreferencesString = mPreferences.getString(AppConstant.RESERVATIONLIST, "");
        return GsonUtils.parseJson(mPreferencesString, type);

    }

    public void setLoginLmsData(String key, LoggedInLmsDetails lmsDetails) {
        if (lmsDetails != null) {
            mPreferences.edit().putString(key, GsonUtils.getJson(lmsDetails)).commit();
        } else {
            mPreferences.edit().putString(key, "").commit();
        }
    }


    public LoggedInCustomerDetails getLoginData(String key) {
        Type type = new TypeToken<LoggedInCustomerDetails>() {
        }.getType();
        return GsonUtils.parseJson(mPreferences.getString(key, ""), type);
    }

    public LoggedInLmsDetails getLoginLmsData(String key) {
        Type type = new TypeToken<LoggedInLmsDetails>() {
        }.getType();
        return GsonUtils.parseJson(mPreferences.getString(key, ""), type);
    }

    public AppData getAppData(String key) {
        Type type = new TypeToken<AppData>() {
        }.getType();
        return GsonUtils.parseJson(mPreferences.getString(key, ""), type);
    }
*/

    public String getString(String key, String defaultValue) {
        return mPreferences.getString(key, defaultValue);
    }

    public int getInt(String key) {
        return mPreferences.getInt(key, Integer.MIN_VALUE);
    }

    public int getInt(String key, int defaultValue) {
        return mPreferences.getInt(key, defaultValue);
    }

    public long getLong(String key) {
        return mPreferences.getLong(key, Long.MIN_VALUE);
    }

    public long getLong(String key, long defaultValue) {
        return mPreferences.getLong(key, defaultValue);
    }

    public boolean getBoolean(String key) {
        return mPreferences.getBoolean(key, false);
    }

    public boolean getBoolean(String key, boolean defaultValue) {
        return mPreferences.getBoolean(key, defaultValue);
    }

    /**
     * Used to clear all the values stored in preferences
     *
     * @return void
     */
    public void clearPreferences() {
        mEditor.clear();
        mEditor.commit();
    }

/*
    public void setReservationData(ReservationData reservationData) {
        if (reservationData != null) {
            mPreferences.edit().putString(AppConstant.PASSENGERLIST, GsonUtils.getJson(reservationData)).commit();
        } else {
            mPreferences.edit().putString(AppConstant.PASSENGERLIST, "").commit();
        }

    }

    public ReservationData getReservatIonData() {
        Type type = new TypeToken<ReservationData>() {
        }.getType();
        return GsonUtils.parseJson(mPreferences.getString(AppConstant.PASSENGERLIST, ""), type);

    }
*/

    /**
     * Enum for shared preferences keys to store various values
     *
     * @author vikash
     */
    public enum SharedPreferncesKeys {
        appVersion, IS_FIRST_TIME_LAUNCH, name,
        email,
        apiToken,
        enterpriseId,
        socialProvider,
        deviceToken,
        deviceType,
        LANGUAGE_KEY,
        LANGUAGE,
        LANGUAGE_SCREEN,
        FILTER,
        hourlyRateSetting,
        enrollWorkerSetting,
        notificationONOFF,
        USER_ID,
        ENTERPRISE_ADMIN,
        SUPERVISIOR,
        SCHEDULER,
        SUPER_EVALUATOR,
        RECRUITER
    }
}