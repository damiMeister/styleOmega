package com.example.damithajeanando.styleomega.activities.validateHelper;

import android.content.Context;

import com.example.damithajeanando.styleomega.activities.activities.RegisterActivity;

/**
 * Created by Damitha Jeanando on 9/19/2017.
 */

public class InputValidate {

    private Context context;
    public InputValidate(Context context){
        this.context = context;
    }

    public  boolean emailFieldEmpty(String email){
        boolean flag = false;

        if(email.equals("")){
            flag = true;
        }
        return flag;
    }
    public  boolean nameFieldEmpty(String name){
        boolean flag = false;

        if(name.equals("")){
            flag = true;
        }
        return flag;
    } public  boolean passwordFieldEmpty(String pass){
        boolean flag = false;

        if(pass.equals("")){
            flag = true;
        }
        return flag;
    }
}
