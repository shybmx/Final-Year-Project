package com.example.shahzademambaccus.finalyearprojectuser;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

public class Logout {

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    public Logout(Context context){
        sharedPreferences = context.getSharedPreferences("loginRef", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        editor.putBoolean("saveLogin", false);
        editor.putString("Username", null);
        editor.commit();
    }

}
