package com.danielacedo.logintextinputlayout;

import android.content.Context;
import android.text.TextUtils;

import com.danielacedo.logintextinputlayout.model.User;

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

        if(TextUtils.isEmpty(user)) {
            view.setMessageError(((Context) view).getResources().getString(com.danielacedo.logintextinputlayout.R.string.err_emptyData), com.danielacedo.logintextinputlayout.R.id.edt_User);
        }
        else if(TextUtils.isEmpty(pass)){
            view.setMessageError(((Context) view).getResources().getString(com.danielacedo.logintextinputlayout.R.string.err_emptyData), com.danielacedo.logintextinputlayout.R.id.edt_Pass);
        }
        else if(!Pattern.matches(".*[0-9].*", pass)){
            view.setMessageError(((Context)view).getResources().getString(com.danielacedo.logintextinputlayout.R.string.err_Password_Digit), com.danielacedo.logintextinputlayout.R.id.edt_Pass);
        }
        else if(!Pattern.matches(".*[a-z].*",pass) || !Pattern.matches(".*[A-Z].*",pass)){
            view.setMessageError(((Context)view).getResources().getString(com.danielacedo.logintextinputlayout.R.string.err_Password_UpperLowerCase), com.danielacedo.logintextinputlayout.R.id.edt_Pass);
        }
        else if(pass.length()<8){
            view.setMessageError(((Context)view).getResources().getString(com.danielacedo.logintextinputlayout.R.string.err_Password_Length), com.danielacedo.logintextinputlayout.R.id.edt_Pass);
        }else{
            //Save the user in the Application class
            ((Login_Application)((Context)view).getApplicationContext()).setUser(new User(user, pass));
        }


    }
}
