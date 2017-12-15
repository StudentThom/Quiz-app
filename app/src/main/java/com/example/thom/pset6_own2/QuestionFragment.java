package com.example.thom.pset6_own2;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class QuestionFragment extends ListFragment {


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /*
 Volley things, using ref:
 https://apps.mprog.nl/android/volley
 https://stackoverflow.com/questions/17037340/converting-jsonarray-to-arraylist
*/
// Instantiate the RequestQueue.

        Log.d(" oncreate", "test");

        RequestQueue queue = Volley.newRequestQueue(getActivity().getApplicationContext());
        String url ="https://opentdb.com/api.php?amount=1&type=boolean";

        final List<String> myArray = new ArrayList<>();

        final ArrayAdapter<String> adapter =
                new ArrayAdapter<String>(
                        getActivity(),
                        android.R.layout.simple_list_item_1,
                        myArray);

        final String[] correctAnswer = {""};



// Request a string response from the provided URL.
        JsonObjectRequest jsObjectRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                    //private ArrayAdapter<String> adapter;

                    //public void setAdapter(ArrayAdapter<String> adapter) {
//                        this.adapter = adapter;
//                    }

                    @Override
                    public void onResponse(JSONObject response) {
                        // Display the first 500 characters of the response string.
                        JSONArray jsonArray = new JSONArray();
                        JSONObject question = new JSONObject();
                        try {
                            jsonArray = response.getJSONArray("results");

                            for (int i = 0; i < jsonArray.length(); i++) {
                                question = jsonArray.getJSONObject(i);
                                Log.d("question i", question.toString());
                                myArray.add(question.getString("question"));
                                myArray.add("True");
                                myArray.add("False");
                                correctAnswer[0] = question.getString("correct answer");
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                        Log.d("correct answers", correctAnswer[0]);


                        Log.d("string", "net voor if");
                        if (jsonArray != null) {
                            Log.d("string2", "net na if");
                            for (int i = 0; i < jsonArray.length(); i++) {
                                try {
                                    myArray.add(jsonArray.getJSONArray(i).toString());
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        }

                        if (myArray != null){
                            Log.d("myarray inhoud", myArray.toString());
                        }

                        Log.d("myarray", myArray.toString());


                        setListAdapter(adapter);




                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        //mTextView.setText("That didn't work!");
                    }
                });

        queue.add(jsObjectRequest);

    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);

        if (id == 1) {

        }

           }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // inflater
        final View inflaterCategories = inflater.inflate(R.layout.fragment_question, container, false);

        // Inflate the layout for this fragment
        return inflaterCategories;
    }

}