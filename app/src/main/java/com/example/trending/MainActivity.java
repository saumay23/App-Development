package com.example.trending;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    List<ModelClass> Userlist;
    Adapter adapter;
    LinearLayout error;
    SwipeRefreshLayout swipeRefreshLayout;
    LinearLayout loading_effect;
    Button retry;
    @SuppressLint("WrongConstant")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recyclerview);
        Userlist = new ArrayList<>();
        loading_effect=findViewById(R.id.loading_activity);
        error = findViewById(R.id.error_main);
        extractdata();
        swipeRefreshLayout=findViewById(R.id.refresh);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh()
            {
                loading_effect.setVisibility(View.VISIBLE);
                recyclerView.setVisibility(View.GONE);
                error.setVisibility(View.GONE);
                extractdata();
                adapter.notifyDataSetChanged();
                swipeRefreshLayout.setRefreshing(false);
            }
        });
    }

    private <JsonArray> void extractdata()
    {
        loading_effect.setVisibility(View.VISIBLE);
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "https://gh-trending-api.herokuapp.com/repositories";
        JsonArrayRequest JsonArray = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject user = response.getJSONObject(i);
                        ModelClass data = new ModelClass();
                        data.setDesc(user.getString("repositoryName"));
                        data.setContent(user.getString("description"));
                        data.setForkcount(user.getString("forks"));
                        data.setStarcount(user.getString("totalStars"));
                        data.setLangcolor(user.getString("languageColor"));
                        data.setLanguage(user.getString("language"));
                        data.setExpanded(false);
                        JSONArray user_parse = user.getJSONArray("builtBy");
                        user=user_parse.getJSONObject(1);
                        data.setName(user.getString("username"));
                        data.setDisplay_pic(user.getString("avatar"));
                        Userlist.add(data);


                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                loading_effect.setVisibility(View.GONE);
                recyclerView.setVisibility(View.VISIBLE);
                error.setVisibility(View.GONE);
                recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                adapter = new Adapter(Userlist);
                recyclerView.setAdapter(adapter);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError e) {

                loading_effect.setVisibility(View.GONE);
                recyclerView.setVisibility(View.GONE);
                error.setVisibility(View.VISIBLE);
                retry=findViewById(R.id.retry);
                retry.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Log.d("TAG", "onClick: retry " );
                        loading_effect.setVisibility(View.VISIBLE);
                        recyclerView.setVisibility(View.GONE);
                        error.setVisibility(View.GONE);
                        adapter = new Adapter(Userlist);
                        recyclerView.setAdapter(adapter);
                        extractdata();
                        adapter.notifyDataSetChanged();

                    }
                });

            }
        });
        queue.add(JsonArray);
    }

}