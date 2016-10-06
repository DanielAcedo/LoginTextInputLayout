package com.danielacedo.loginrelative;

import android.app.Application;

import com.danielacedo.loginrelative.model.User;

/**
 * Created by Daniel on 6/10/16.
 */

/**
 * Singleton application class where we place our objects available to all classes
 * @author Daniel Acedo Calderón
 */
public class Login_Application extends Application {

    private User user;


    @Override
    public void onCreate() {
        super.onCreate();
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
