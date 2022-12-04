package com.example.onlinecourse.contoller;

import android.content.Intent;
import android.text.TextUtils;
import android.widget.Toast;

import com.example.onlinecourse.model.LogModel;
import com.example.onlinecourse.view.HomeActivity;
import com.example.onlinecourse.view.LoginActivity;
import com.example.onlinecourse.view.SignUpActivity;

public class LogController {
    LogModel logModel;
    LoginActivity loginActivity;

    public LogController(LoginActivity loginActivity) {
        this.logModel = new LogModel(this);
        this.loginActivity = loginActivity;
    }

    public void LogIn(){
        this.loginActivity.loadingBar.show();

        // Store email password
        String email = loginActivity.userEmail.getText().toString().trim();
        String password = loginActivity.userPass.getText().toString().trim();

        //Checking User input all data or not
        if (TextUtils.isEmpty(email) || TextUtils.isEmpty(password)){
            Toast.makeText(loginActivity, "Input Email Password", Toast.LENGTH_SHORT).show();
        }else {
            logModel.logIn(email, password);
        }
    }

    public void loginReturn(int loginReturn){
        if (loginReturn == 0){
            //Login Successful
            this.loginActivity.loadingBar.dismiss();
            loginActivity.startActivity(new Intent(loginActivity, HomeActivity.class)
                    .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK));
            loginActivity.finish();
        } else if (loginReturn == 1) {
            //Not Successful
            this.loginActivity.loadingBar.dismiss();
        }
    }

    public void SignUp() {
        loginActivity.startActivity(new Intent(loginActivity, SignUpActivity.class));
    }
}
