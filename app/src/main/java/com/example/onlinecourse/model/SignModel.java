package com.example.onlinecourse.model;

import androidx.annotation.NonNull;

import com.example.onlinecourse.contoller.SignController;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignModel {

    SignController signController;
    FirebaseAuth mAuth = FirebaseAuth.getInstance();

    public SignModel(SignController signController) {
        this.signController = signController;
    }

    public void signUp(String email, String password, String name) {
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            signController.signUpResult(0);
                        }
                        else {
                            signController.signUpResult(1);
                        }
                    }
                });
    }
}
