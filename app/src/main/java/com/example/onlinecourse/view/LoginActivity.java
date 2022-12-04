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
import com.example.onlinecourse.contoller.LogController;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {

    private Button loginBT;
    public EditText userEmail, userPass;
    private TextView noAcc;
    public ProgressDialog loadingBar;
    LogController logController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        noAcc = findViewById(R.id.txt_signup);
        loginBT = findViewById(R.id.btLogin);
        userEmail = findViewById(R.id.etEmail);
        userPass = findViewById(R.id.etPass);
        loadingBar = new ProgressDialog(this);
        loadingBar.setTitle("Log In Account");
        loadingBar.setMessage("Please Wait");
        loadingBar.setCanceledOnTouchOutside(true);

        // Log Controller
        logController = new LogController(this);

        loginBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                logController.LogIn();
            }
        });

        noAcc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                logController.SignUp();
            }
        });

    }

    /*@Override
    protected void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();

        if (currentUser != null){
            Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            finish();
        }
    }*/

    @Override
    protected void onStart() {
        super.onStart();



    }
}