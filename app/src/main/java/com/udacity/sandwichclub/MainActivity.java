package com.udacity.sandwichclub;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.udacity.sandwichclub.model.Sandwich;
import com.udacity.sandwichclub.utils.JsonUtils;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<Sandwich> sandwichList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setupToolbar();

        RecyclerView recyclerViewSandwich = findViewById(R.id.recycler_view_sandwich_list);

        sandwichList = new ArrayList<>();

        // Set sandwich adapter
        SandwichAdapter sandwichAdapter = new SandwichAdapter(MainActivity.this, sandwichList);
        recyclerViewSandwich.setHasFixedSize(true);
        recyclerViewSandwich.setAdapter(sandwichAdapter);
        sandwichAdapter.notifyDataSetChanged();
        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MainActivity.this,
                LinearLayoutManager.VERTICAL, false);
        recyclerViewSandwich.setLayoutManager(linearLayoutManager);

        loadSandwiches();
    }

    // Get sandwiches
    private void loadSandwiches(){

        // Get sandwich object from string resource
        String[] sandwiches = getResources().getStringArray(R.array.sandwich_details);
        // Iterate through string array with for loop, parse json object, and add it to sandwichList
        for (String sandwichString : sandwiches){
            Sandwich sandwich;
            sandwich = JsonUtils.parseSandwichJson(sandwichString);
            sandwichList.add(sandwich);
        }
    }

    // Set toolbar
    private void setupToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null){
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        }
    }
}
