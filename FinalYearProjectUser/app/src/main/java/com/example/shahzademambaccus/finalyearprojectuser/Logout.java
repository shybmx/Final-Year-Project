package com.example.shahzademambaccus.finalyearprojectuser;

import android.content.Context;
import android.content.SharedPreferences;

public class Logout {

    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private static final String LOGIN_REF_LABEL = "loginRef";
    private static final String LOGIN_STATUS_LABEL = "saveLogin";
    private static final String USERNAME_LABEL = "Username";

    public Logout(Context context){
        sharedPreferences = context.getSharedPreferences(LOGIN_REF_LABEL, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        editor.putBoolean(LOGIN_STATUS_LABEL, false);
        editor.putString(USERNAME_LABEL, null);
        editor.commit();
    }

}
