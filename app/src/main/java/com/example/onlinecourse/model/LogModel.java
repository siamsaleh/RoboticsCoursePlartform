package com.example.onlinecourse.model;

import androidx.annotation.NonNull;

import com.example.onlinecourse.contoller.LogController;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LogModel {
    FirebaseAuth mAuth = FirebaseAuth.getInstance();
    LogController logController;

    public LogModel(LogController logController) {
        this.logController = logController;
    }

    public void logIn(String email, String password){
        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    logController.loginReturn(0);
                }
                else{
                    logController.loginReturn(1);
                }
            }
        });
    }
}
