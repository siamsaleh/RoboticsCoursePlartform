package com.example.onlinecourse.contoller;

import android.content.Intent;
import android.text.TextUtils;
import android.widget.Toast;

import com.example.onlinecourse.model.SignModel;
import com.example.onlinecourse.view.HomeActivity;
import com.example.onlinecourse.view.SignUpActivity;

public class SignController {
    SignModel signModel;
    SignUpActivity signUpActivity;

    public SignController(SignUpActivity signUpActivity) {
        this.signModel = new SignModel(this);
        this.signUpActivity = signUpActivity;
    }

    public void signUp(){
        String email = signUpActivity.userEmail.getText().toString().trim();
        String name = signUpActivity.userName.getText().toString().trim();
        String pass = signUpActivity.userPass.getText().toString().trim();
        String conPass = signUpActivity.userConPass.getText().toString().trim();

        if(TextUtils.isEmpty(email) || TextUtils.isEmpty(pass) || TextUtils.isEmpty(conPass) || TextUtils.isEmpty(conPass)){
            Toast.makeText(signUpActivity, "Input All", Toast.LENGTH_SHORT).show();
        }
        else if (!pass.equals(conPass)){
            Toast.makeText(signUpActivity, "Password not matched", Toast.LENGTH_SHORT).show();
        }else{
            signUpActivity.loadingBar.show();
            signModel.signUp(email, pass, name);
        }
    }

    public void signUpResult(int result){
        if (result == 0){
            //SignUp Successful
            this.signUpActivity.loadingBar.dismiss();
            signUpActivity.startActivity(new Intent(signUpActivity, HomeActivity.class)
                    .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK));
            signUpActivity.finish();
        } else if (result == 1) {
            //Not Successful
            this.signUpActivity.loadingBar.dismiss();
        }
    }
}
