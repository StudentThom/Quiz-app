package com.example.thom.pset6_own2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoggedInActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener authListenerTest;
    private static final String TAG = "Firebase_test";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logged_in);

        mAuth = FirebaseAuth.getInstance();
        //setListener();
        FirebaseUser currentUser = mAuth.getCurrentUser();

        if (currentUser == null){
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }


    }

    @Override
    protected void onStart() {
        super.onStart();

        FirebaseUser currentUser = mAuth.getCurrentUser();

        if (currentUser == null){
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
        }
    }

    public void startQuiz(View view) {
        Intent intent = new Intent(this, StartQuizActivity.class);
        String userscore = "0";
        String numberOfQuestions = "0";
        intent.putExtra("userscore", userscore);
        intent.putExtra("numberOfQuestions", numberOfQuestions);
        startActivity(intent);
    }

    public void goToViewScores(View view) {

        Intent intent = new Intent(this, ViewScoresActivity.class);
        startActivity(intent);
        finish();
    }

    public void goToMain() {

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
