package com.example.snakka.kdproconjoinapp;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.widget.Toast;

import java.util.HashMap;

public class SharedPreferenceAccessor {
    public static final String DEPARTMENT_KEY = "department_key";
    public static final String SCHOOL_YEAR_KEY = "school_year_key";
    public static final String CLASS_NO_KEY = "class_no_key";
    public static final String FIRST_NAME_KEY = "first_name_key";
    public static final String FAMILY_NAME_KEY = "family_name_key";
    static final String[] REFERENCE_KEYS = {DEPARTMENT_KEY, SCHOOL_YEAR_KEY, CLASS_NO_KEY, FIRST_NAME_KEY, FAMILY_NAME_KEY};


    public static Boolean isRegisteredUser(){
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(AppContextMgr.getContext());

        if(preferences.getString(DEPARTMENT_KEY, null) == null
                || preferences.getString(SCHOOL_YEAR_KEY, null) == null
                || preferences.getString(CLASS_NO_KEY, null) == null
                || preferences.getString(FIRST_NAME_KEY, null) == null
                || preferences.getString(FAMILY_NAME_KEY, null) == null) {
            Toast.makeText(AppContextMgr.getContext(), "ユーザー情報が未設定です", Toast.LENGTH_LONG).show();
            return false;
        }
        return true;
    }

    public static Boolean commitUserStatusStr(HashMap<String, String> userStatus){
        if(userStatus.isEmpty())
            return false;

        SharedPreferences.Editor editor =
                PreferenceManager.getDefaultSharedPreferences(AppContextMgr.getContext()).edit();

        for(String key:REFERENCE_KEYS){
            if(userStatus.containsKey(key)){
                editor.putString(key, userStatus.get(key));
            }
        }
        editor.commit();

        return true;
    }

    public static String getUserOfJson(){
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(AppContextMgr.getContext());

        return "{" +
                "\"department\":\"" + preferences.getString(DEPARTMENT_KEY, null) + "\"," +
                "\"schoolYear\":" + Integer.parseInt(preferences.getString(SCHOOL_YEAR_KEY, "0")) + "," +
                "\"classno\":" + Integer.parseInt(preferences.getString(CLASS_NO_KEY, "0")) + "," +
                "\"familyName\":\"" + preferences.getString(FAMILY_NAME_KEY, null) + "\"," +
                "\"firstName\":\"" + preferences.getString(FIRST_NAME_KEY, null) + "\"" +
                "}";
    }

    public static HashMap<String, String> getUserStatus(){
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(AppContextMgr.getContext());

        HashMap<String, String> userStatus = new HashMap<>();
        for(String key:REFERENCE_KEYS){
            if(preferences.contains(key))
                userStatus.put(key, preferences.getString(key, null));
        }

        return userStatus;
    }

}
