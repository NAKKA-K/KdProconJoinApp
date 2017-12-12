package com.example.snakka.kdproconjoinapp;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class SharedPreferenceAccessor {
    private static final String DEPARTMENT_KEY = "department_key";
    private static final String SCHOOL_YEAR_KEY = "school_year_key";
    private static final String CLASS_NO_KEY = "class_no_key";
    private static final String FIRST_NAME_KEY = "first_name_key";
    private static final String FAMILY_NAME_KEY = "family_name_key";

    public static Boolean isRegisteredUser(Context context){
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);

        if(preferences.getString(DEPARTMENT_KEY, null) == null
                || preferences.getString(SCHOOL_YEAR_KEY, null) == null
                || preferences.getString(CLASS_NO_KEY, null) == null
                || preferences.getString(FIRST_NAME_KEY, null) == null
                || preferences.getString(FAMILY_NAME_KEY, null) == null) {
            return false;
        }
        return true;
    }
}
