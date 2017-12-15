package com.example.thom.pset6_own2;

import android.app.DownloadManager;
import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.common.api.Response;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class StartQuizActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_quiz);

        TextView userscoreTextView = findViewById(R.id.userscoreTextView);
        TextView numberOfQuestionsTextView = findViewById(R.id.numberOfQuestionsTextView);

        // get intent info
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        String userscore = extras.getString("userscore");
        String numberOfQuestions = extras.getString("numberOfQuestions");

        // put intent info in textviews
        userscoreTextView.setText(userscore);
        numberOfQuestionsTextView.setText(numberOfQuestions);



        RequestQueue queue = Volley.newRequestQueue(this.getApplicationContext());
        String url = "https://opentdb.com/api.php?amount=1&type=boolean";

        final TextView questionTextView = findViewById(R.id.questionTextView);
        final TextView answerTextView = findViewById(R.id.answerTextView);

// Request a string response from the provided URL.
        JsonObjectRequest jsObjectRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new com.android.volley.Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {

                        JSONArray jsonArray = new JSONArray();
                        String question = "";
                        String answer = "";
                        try {
                            jsonArray = response.getJSONArray("results");
                            question = jsonArray.getJSONObject(0).getString("question");
                            answer = jsonArray.getJSONObject(0).getString("correct_answer");
                            Log.d("answer", answer);
                            questionTextView.setText(question);
                            answerTextView.setText(answer);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new com.android.volley.Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });
//      Add the request to the RequestQueue.
        queue.add(jsObjectRequest);

    }

    public void trueAnswered(View view) {

        // variables
        int totalQuestions = 5;

        // get textviews
        TextView answerTextView = findViewById(R.id.answerTextView);
        TextView userscoreTextView = findViewById(R.id.userscoreTextView);
        TextView numberOfQuestionsTextView = findViewById(R.id.numberOfQuestionsTextView);

        // get strings from textviews
        String userscoreString = userscoreTextView.getText().toString();
        String numberOfQuestionsString = numberOfQuestionsTextView.toString();

        // convert strings to int
        int userscoreInt = Integer.valueOf(userscoreString);
        int numberOfQuestions = Integer.valueOf(numberOfQuestionsTextView.getText().toString());

        // check if true was indeed the good answer
        if (answerTextView.getText().equals("True")) {
            // user earns a point
            userscoreInt = userscoreInt + 1;
        }

        numberOfQuestions = numberOfQuestions + 1;

        // back to strings
        userscoreString = Integer.toString(userscoreInt);
        numberOfQuestionsString = Integer.toString(numberOfQuestions);

        // check if this was last question
        if (numberOfQuestions == totalQuestions){
            Intent intent = new Intent(this, FinishActivity.class);
            intent.putExtra("userscore", userscoreString);
            startActivity(intent);
            finish();
        }
        else {
            // new question
            Intent intent = new Intent(this, StartQuizActivity.class);
            Bundle extras = new Bundle();
            extras.putString("userscore", userscoreString);
            extras.putString("numberOfQuestions", numberOfQuestionsString);
            intent.putExtras(extras);
            startActivity(intent);
            finish();
        }

    }


    public void falseAnswered(View view) {

        // variables
        int totalQuestions = 5;

        // get textviews
        TextView answerTextView = findViewById(R.id.answerTextView);
        TextView userscoreTextView = findViewById(R.id.userscoreTextView);
        TextView numberOfQuestionsTextView = findViewById(R.id.numberOfQuestionsTextView);

        // get strings from textviews
        String userscoreString = userscoreTextView.getText().toString();
        String numberOfQuestionsString = numberOfQuestionsTextView.toString();

        // convert strings to int
        int userscoreInt = Integer.valueOf(userscoreString);
        int numberOfQuestions = Integer.valueOf(numberOfQuestionsTextView.getText().toString());

        // check if true was indeed the good answer
        if (answerTextView.getText().equals("False")) {
            // user earns a point
            userscoreInt = userscoreInt + 1;
        }

        numberOfQuestions = numberOfQuestions + 1;

        // back to strings
        userscoreString = Integer.toString(userscoreInt);
        numberOfQuestionsString = Integer.toString(numberOfQuestions);

        // check if this was last question
        if (numberOfQuestions == totalQuestions){
            Intent intent = new Intent(this, FinishActivity.class);
            intent.putExtra("userscore", userscoreString);
            startActivity(intent);
            finish();
        }
        else {
            // new question
            Intent intent = new Intent(this, StartQuizActivity.class);
            Bundle extras = new Bundle();
            extras.putString("userscore", userscoreString);
            extras.putString("numberOfQuestions", numberOfQuestionsString);
            intent.putExtras(extras);
            startActivity(intent);
            finish();
        }

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        TextView questionTextView = findViewById(R.id.questionTextView);
        String question = questionTextView.getText().toString();
        outState.putSerializable("question",question);
    }
    @Override
    protected  void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        String question = (String) savedInstanceState.getSerializable("question");
        TextView questionTextView = findViewById(R.id.questionTextView);
        questionTextView.setText(question);
    }
}
