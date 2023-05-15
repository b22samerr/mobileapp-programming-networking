package com.example.networking;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.*;
import com.google.gson.reflect.*;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SuppressWarnings("FieldCanBeLocal")
public class MainActivity extends AppCompatActivity implements JsonTask.JsonTaskListener {

    private final String JSON_URL = "https://mobprog.webug.se/json-api?login=brom";
    private final String JSON_FILE = "mountains.json";

    private ArrayList<RecyclerViewItem> mountains = new ArrayList<>();
    private RecyclerViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d("json_output", "hello from onCreate");



        //ArrayList<RecyclerViewItem> items = new ArrayList<>(Arrays.asList(
        //        new RecyclerViewItem("Matterhorn"),
        //        new RecyclerViewItem("Mont Blanc"),
        //        new RecyclerViewItem("Denali")
        //));

        Log.d("onCreateTAG", "" + mountains.size());

        adapter = new RecyclerViewAdapter(this, mountains, new RecyclerViewAdapter.OnClickListener() {
            @Override
            public void onClick(RecyclerViewItem item) {
                Toast.makeText(MainActivity.this, item.getTitle(), Toast.LENGTH_SHORT).show();
            }
        });

        new JsonFile(this, this).execute(JSON_FILE);

        RecyclerView view = findViewById(R.id.recycler_view);
        view.setLayoutManager(new LinearLayoutManager(this));
        view.setAdapter(adapter);

        
    }

    @Override
    public void onPostExecute(String json) {

        Log.d("json_output", "hello from onPostExecute");
        Gson gson = new Gson();

        // Unmarshall JSON -> list of objects
        Type type = new TypeToken<List<Mountain>>() {}.getType();
        List<Mountain> listOfMountains = gson.fromJson(json, type);

        for(Mountain mountain : listOfMountains){
            Log.d("json_output_loop", mountain.getName());
            mountains.add(new RecyclerViewItem(mountain.getName()));
        }

        //Type hamed = new TypeToken<ArrayList<RecyclerViewItem>>(){}.getType();

        //ArrayList<RecyclerViewItem> data  = gson.fromJson(json, name);
        //mountain.addAll(data);

        adapter.notifyDataSetChanged();

        Log.d("json_output", "" + json);
    }

}
//