package com.example.onlinecourse.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.onlinecourse.R;
import com.example.onlinecourse.contoller.SignController;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


public class SignUpActivity extends AppCompatActivity {

    public EditText userEmail, userPass, userConPass, userName;
    private TextView loginActivity;
    private Button createAccount;
    private FirebaseAuth mAuth;
    public ProgressDialog loadingBar;

    SignController signController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        createAccount = findViewById(R.id.btSignUp);
        userEmail = findViewById(R.id.etEmail);
        userName = findViewById(R.id.etName);
        userPass = findViewById(R.id.etPass);
        userConPass = findViewById(R.id.etConPass);
        loginActivity = findViewById(R.id.txt_login);
        loadingBar = new ProgressDialog(this);
        loadingBar.setTitle("Sign Up Account");
        loadingBar.setMessage("Please Wait");
        loadingBar.setCanceledOnTouchOutside(true);

        mAuth = FirebaseAuth.getInstance();

        signController = new SignController(this);

        createAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signController.signUp();
            }
        });
    }

    private void createAccount() {
        String email = userEmail.getText().toString().trim();
        String name = userName.getText().toString().trim();
        String password = userPass.getText().toString().trim();
        String conPass = userConPass.getText().toString().trim();

        if(TextUtils.isEmpty(email) || TextUtils.isEmpty(password) || TextUtils.isEmpty(conPass)){
            Toast.makeText(this, "Input All", Toast.LENGTH_SHORT).show();
        }
        else if (!password.equals(conPass)){
            Toast.makeText(this, "Password not matched", Toast.LENGTH_SHORT).show();
        }
        else {
            loadingBar.setTitle("Creating New Account");
            loadingBar.setMessage("Please Wait");
            loadingBar.show();
            loadingBar.setCanceledOnTouchOutside(true);

            mAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()){
                                startActivity(new Intent(getApplicationContext(), HomeActivity.class)
                                        .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK));
                                finish();
//                                Toast.makeText(RegisterActivity.this, "Done", Toast.LENGTH_SHORT).show();
                                loadingBar.dismiss();
                            }
                            else {
                                Toast.makeText(SignUpActivity.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                loadingBar.dismiss();
                            }
                        }
                    });
        }
    }
}