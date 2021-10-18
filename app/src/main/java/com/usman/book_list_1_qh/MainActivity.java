package com.usman.book_list_1_qh;



import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.SearchView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity
        extends AppCompatActivity {

    //private static RecyclerView.Adapter adapter;
    private MyAdapter mAdapter;
    private static RecyclerView recyclerView;
    public static ArrayList<Model> data;
    DatabaseHelper db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        Back Button
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

//        // this event will enable the back
//        // function to the button on press
//        @Override
//        public boolean onOptionsItemSelected(@NonNull MenuItem ) {
//            MenuItem item = new MenuItem();
//            switch (item.getItemId()) {
//                case android.R.id.home:
//                    this.finish();
//                    return true;
//            }
//            return super.onOptionsItemSelected(item);
//        }





        recyclerView = findViewById(R.id.my_recycler_view);
        db = new DatabaseHelper(this);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,2);
        recyclerView.setLayoutManager(gridLayoutManager);
        /* recyclerView.setLayoutManager(new LinearLayoutManager(this)); */

        data = new ArrayList<Model>();
        fetchData();




        //        search field
        EditText search = findViewById(R.id.search);

        search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                filter(s.toString());
            }
        });

    }


    //search method
private void filter(String text) {
    ArrayList<Model> filteredList = new ArrayList<>();

    for (Model item : data) {
        if (item.getBookname().toLowerCase().contains(text.toLowerCase())) {
            filteredList.add(item);
        }
    }

    mAdapter.filterList(filteredList);
}

    public void fetchData() {
        // Before fetching the data
        // directly from the database.
        // first we have to creates an empty
        // database on the system and
        // rewrites it with your own database.
        // Then we have to open the
        // database to fetch the data from it.
        db = new DatabaseHelper(this);
        try {
            db.createDataBase();
            db.openDataBase();
        } catch (Exception e) {
            e.printStackTrace();
        }
//changr mAdapter To adapter if find error
        data = new ArrayList<>();
        data = db.getBookList(this);
        mAdapter = new MyAdapter(this, (ArrayList<Model>) data);
        recyclerView.setAdapter(mAdapter);
    }
}
