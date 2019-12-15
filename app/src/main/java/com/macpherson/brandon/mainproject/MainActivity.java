package com.macpherson.brandon.mainproject;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public RecyclerView recyclerView;
    public RecyclerView.LayoutManager layoutManager;
    public RecyclerView.Adapter adapter;
    public List<model> datalist = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        createExampleList();
        buildRecyclerView();
    }

    /*
    Not Working.  It is supposed to take info from NewPostActivity to add new datalist item
    */
    public void onInsert(View theButton) {
        Intent newPost = new Intent(this, NewPostActivity.class);

        startActivity(newPost);

        SharedPreferences pref = getSharedPreferences("PREFERENCES", Context.MODE_PRIVATE);
        String item = pref.getString("item", null);
        String info = pref.getString("info", null);
        String address = pref.getString("address", null);
        String city = pref.getString("city", null);
        String state = pref.getString("state", null);
        String location = pref.getString("location", null);
        int photo = pref.getInt("item", 0);

        datalist.add(0,new model(item, info, address, city, state, location, photo));
        adapter.notifyItemInserted(0);
    }

    /*
    creates an example list of items to show on main page
    */
    public void createExampleList() {
        datalist.add(new model("meal0","purple", "1234 red rd", "Idaho Falls", "Idaho",R.drawable.food));
        datalist.add(new model("meal1", "pink", "1234 red rd", "rexburg", "Idaho",R.drawable.food));
        datalist.add(new model("meal2", "red", "1234 red rd", "rexburg", "Idaho",R.drawable.food));
        datalist.add(new model("meal3","blue", "1234 red rd", "rexburg", "Idaho",R.drawable.food));
        datalist.add(new model("meal4","not much left", "1234 red rd", "rexburg", "Idaho",R.drawable.food));
        datalist.add(new model("meal5","all I've got", "1234 red rd", "rexburg", "Idaho",R.drawable.food));
    }

    public void buildRecyclerView() {
        recyclerView = findViewById(R.id.recycler);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        adapter = new Adapter(datalist);
        recyclerView.setAdapter(adapter);

    }

}
