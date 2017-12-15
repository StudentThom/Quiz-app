package com.example.thom.pset6_own2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ViewScoresActivity extends AppCompatActivity {

    private DatabaseReference mDatabase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_scores);


        final List<String> myArray = new ArrayList<>();
        final ArrayAdapter<String> itemsAdapter =
                new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, myArray);

        mDatabase = FirebaseDatabase.getInstance().getReference();
        mDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                UserScores userScores = dataSnapshot.child("scores").child("Thom").getValue(UserScores.class);
                String test = dataSnapshot.child("scores").toString();

                DataSnapshot dataSnapshot1 = dataSnapshot.child("scores");
                dataSnapshot1.getChildren();

                for (DataSnapshot child : dataSnapshot1.getChildren()) {
                    UserScores userscore = child.getValue(UserScores.class);
                    Log.d("userscore loop", userscore.userMail);
                    myArray.add(userscore.userMail + ": " + userscore.userScore.toString());

                }


                ListView listView = findViewById(R.id.list);
                listView.setAdapter(itemsAdapter);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Getting Post failed, log a message
                Log.w("TAG", "loadPost:onCancelled", databaseError.toException());
                // ...
                TextView textViewData = findViewById(R.id.dataTextView);
                textViewData.setText("error");

            }
        });


    }


    public void logOut(View view) {
        FirebaseAuth.getInstance().signOut();
        startActivity(new Intent(this, MainActivity.class));
    }
}
