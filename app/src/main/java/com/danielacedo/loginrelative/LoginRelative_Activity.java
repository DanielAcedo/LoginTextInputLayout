package com.danielacedo.loginrelative;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.danielacedo.loginrelative.model.User;

/**
 * Example activity that acts as a login with a RelativeLayout as root.
 */
public class LoginRelative_Activity extends AppCompatActivity implements ILoginMvp.View{
    private ILoginMvp.Presenter loginMvp;
    private EditText edt_User, edt_Pass;
    private Button btn_Ok, btn_Cancel;

    private final String TAG = "loginrelative";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_);
        loginMvp = new LoginPresenter(this); // Presenter has a reference to the view
        edt_User = (EditText)findViewById(R.id.edt_User);
        edt_Pass = (EditText)findViewById(R.id.edt_Pass);
        btn_Ok = (Button)findViewById(R.id.btn_Ok);
        btn_Ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginMvp.validateCredentials(edt_User.getText().toString(), edt_Pass.getText().toString());
            }
        });

        btn_Cancel = (Button)findViewById(R.id.btn_Cancel);
        btn_Cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetValues();
            }
        });

        //Check user persistence
        User user = ((Login_Application)getApplicationContext()).getUser();
        if(user != null){
            Log.d(TAG, user.getUser());
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "Finished Activity");
    }

    @Override
    protected void onStart(){
        super.onStart();
        //Check user persistence
        User user = ((Login_Application)getApplicationContext()).getUser();
        if(user != null){
            Log.d(TAG, user.getUser());
        }
    }

    /**
     * Sends an order to the view to display a message error
     * @param messageError The message to be displayed
     * @author Daniel Acedo Calderón
     */
    @Override
    public void setMessageError(String messageError, int view) {
        //Toast.makeText(this, messageError, Toast.LENGTH_SHORT).show();

        switch (view){
            case R.id.edt_User:
                edt_User.setError(messageError);
                break;
            case R.id.edt_Pass:
                edt_Pass.setError(messageError);
                break;
        }
    }

    /**
     * Reset the input fiels for the login
     * @author Daniel Acedo Calderón
     */
    private void resetValues() {
        edt_User.setText("");
        edt_Pass.setText("");
    }
}
