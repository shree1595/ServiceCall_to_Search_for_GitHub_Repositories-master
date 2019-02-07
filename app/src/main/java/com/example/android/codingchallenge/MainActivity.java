package com.example.android.codingchallenge;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;


import org.json.JSONObject;

import Adapters.SearchResultAdapter;
import Models.SearchResult;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText queryEditText;
    private Button searchButton;
    private RecyclerView resultRecyclerView;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
    }

    /*
     * Initalizing the view.
     */
    private void initViews() {
        queryEditText = (EditText) findViewById(R.id.search_edit_text);
        searchButton = (Button) findViewById(R.id.search_button);
        progressBar = (ProgressBar) findViewById(R.id.progressbar);
        resultRecyclerView = (RecyclerView) findViewById(R.id.result_recyclerview);

        searchButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.search_button :
                progressBar.setVisibility(View.VISIBLE);
                resultRecyclerView.setVisibility(View.GONE);
                requestGitApiCall();
                break;
        }
    }


    private void requestGitApiCall() {
        String url = getResources().getString(R.string.url, queryEditText.getText().toString());
        JsonRequest<JSONObject> jsonObjectJsonRequest = new JsonObjectRequest(url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        SearchResult results =  new Gson().fromJson(response.toString(), SearchResult.class);
                        resultRecyclerView.setAdapter(new SearchResultAdapter(results.getItems()));
                        resultRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                        progressBar.setVisibility(View.GONE);
                        resultRecyclerView.setVisibility(View.VISIBLE);
                    }
                },
                new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("error ", error.toString());
            }
        }
        );
        Volley.newRequestQueue(getApplicationContext()).add(jsonObjectJsonRequest); //calling the request.
    }


}
