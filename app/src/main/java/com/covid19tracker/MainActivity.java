package com.covid19tracker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.covid19tracker.R;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private List<Model> data = new ArrayList<>();
    private RecyclerView recyclerView;
    private Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new Adapter(MainActivity.this,data);
        recyclerView.setAdapter(adapter);
        retrieveData();

        Button searchBtn = findViewById(R.id.search);
        EditText tv = findViewById(R.id.search_box);

        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String stateName = tv.getText().toString();
                tv.setText("");
                filterState(stateName);
            }
        });
    }
    private void retrieveData(){
        final String URL = "https://data.covid19india.org/state_district_wise.json";
        RequestQueue requestQueue = Volley.newRequestQueue(MainActivity.this);
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, URL,null,new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    Toast.makeText(MainActivity.this, "Inside on response", Toast.LENGTH_SHORT).show();
                    Iterator<String> keys = response.keys();
                    keys.next();

                    while (keys.hasNext()) {

                        String stateName = keys.next();
                        JSONObject object = response.getJSONObject(stateName);
                        JSONObject cityData = object.getJSONObject("districtData");
                        Iterator<String> cities = cityData.keys();

                        while (cities.hasNext()) {

                            String cityName = cities.next();
                            JSONObject city_data = cityData.getJSONObject(cityName);
                            long active = city_data.getLong("active");
                            long recovered = city_data.getLong("recovered");
                            long deceased = city_data.getLong("deceased");
                            long confirmed = city_data.getLong("confirmed");

                            data.add(new Model(Long.toString(confirmed), Long.toString(active), Long.toString(recovered), Long.toString(deceased), stateName, cityName));
                        }

                        adapter.notifyDataSetChanged();
                    }

                } catch (Exception e) {
                    Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, error.toString(), Toast.LENGTH_SHORT).show();
            }
        });
        requestQueue.add(request);
    }
    private void filterState(String state){
        ArrayList<Model> filteredList = new ArrayList<>();

        for(Model item:data){
            if(item.getState().toLowerCase().contains(state.toLowerCase())){
                filteredList.add(item);
            }
        }

        if(filteredList.isEmpty()) Toast.makeText(this, "No state found by this name.", Toast.LENGTH_SHORT).show();
        else{

            Dialog dialogBox = new Dialog(this);
            dialogBox.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            dialogBox.setContentView(R.layout.dialogue_box_layout);

            RecyclerView dialog_recycler = dialogBox.getWindow().findViewById(R.id.dialog_recycler);
            Adapter newAdapter = new Adapter(this,filteredList);
            dialog_recycler.setLayoutManager(new LinearLayoutManager(this));
            dialog_recycler.setAdapter(newAdapter);
            dialogBox.show();
        }
    }
}