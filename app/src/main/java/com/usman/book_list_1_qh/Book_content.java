package com.usman.book_list_1_qh;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;

import java.util.List;

public class Book_content extends AppCompatActivity {

    TextView bname,bauthor,bid;

    @SuppressLint("Range")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_content);
        getSupportActionBar().setTitle("Content");
        //Back Button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        // this event will enable the back
        // function to the button on press






        bid = findViewById(R.id.idinc);
        bname = findViewById(R.id.bookinc);
        bauthor = findViewById(R.id.authorinc);
        Intent intent=getIntent();


        String diid = intent.getStringExtra("Rbookid");
        int iiid = Integer.parseInt(diid);

        Log.i("Index", "Id is : "+ iiid);

        Cursor cursor=new DatabaseHelper(this).readalldata(iiid);

        if (cursor.moveToFirst()){

            bid.setText(iiid +" . "+cursor.getString(cursor.getColumnIndex("title")));
            bname.setText(cursor.getString(cursor.getColumnIndex("content")));
            bauthor.setText(cursor.getString(cursor.getColumnIndex("end")));
        }
        else {
            bname.setText("error doing this");
        }
        cursor.close();

//        bid.setText( content.get(0).toString());
//        bname.setText(content.get(1).toString());
//        bauthor.setText(content.get(2).toString());


    }
}