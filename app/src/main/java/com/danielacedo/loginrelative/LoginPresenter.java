package com.danielacedo.loginrelative;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.text.TextUtils;
import android.widget.Toast;

import com.danielacedo.loginrelative.model.User;

import java.util.regex.Pattern;

/**
 * Created by Daniel on 6/10/16.
 */

/**
 * Presenter for the login Activity. It handles all the communication with the model and updates the view when necessary
 * @author Daniel Acedo Calderón
 */
public class LoginPresenter implements ILoginMvp.Presenter {

    private ILoginMvp.View view;

    public LoginPresenter(ILoginMvp.View view){
        this.view = view;
    }

    @Override
    public void validateCredentials(String user, String pass) {

        if(TextUtils.isEmpty(user) || TextUtils.isEmpty(pass)) {
            view.setMessageError(((Context) view).getResources().getString(R.string.err_emptyData));
        }
        else if(!Pattern.matches(".*[0-9].*", pass)){
            view.setMessageError(((Context)view).getResources().getString(R.string.err_Password_Digit));
        }
        else if(!Pattern.matches(".*[a-z].*",pass) || !Pattern.matches(".*[A-Z].*",pass)){
            view.setMessageError(((Context)view).getResources().getString(R.string.err_Password_UpperLowerCase));
        }
        else if(pass.length()<8){
            view.setMessageError(((Context)view).getResources().getString(R.string.err_Password_Length));
        }else{
            //Save the user in the Application class
            ((Login_Application)((Context)view).getApplicationContext()).setUser(new User(user, pass));
        }


    }
}
