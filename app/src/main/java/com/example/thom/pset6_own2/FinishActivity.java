package com.example.thom.pset6_own2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class FinishActivity extends AppCompatActivity {

    private FirebaseAuth authTest;
    private FirebaseAuth.AuthStateListener authListenerTest;
    private static final String TAG = "firebase_test";
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finish);


        // get intent info
        Intent intent = getIntent();
        String userscore = intent.getStringExtra("userscore");

        TextView finalScoreTextView = findViewById(R.id.finalScoreTextView);
        finalScoreTextView.setText(userscore);

        mDatabase = FirebaseDatabase.getInstance().getReference();

    }

    public void addToDb(View view){

        EditText nameEditText = findViewById(R.id.nameEditText);
        String username = nameEditText.getText().toString();

        TextView finalScoreTextView = findViewById(R.id.finalScoreTextView);
        Integer finalScore = Integer.valueOf(finalScoreTextView.getText().toString());



        UserScores aUserscore = new UserScores(username, finalScore);

        mDatabase.child("scores").child(username).setValue(aUserscore);

    }

    public void goToViewScores(View view) {

        Intent intent = new Intent(this, ViewScoresActivity.class);
        startActivity(intent);
        finish();
    }
}
