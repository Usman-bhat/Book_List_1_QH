package com.usman.book_list_1_qh;



import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    private static List<Model> data;
    Activity activity;

    public MyAdapter(Activity activity,ArrayList<Model> data) {
        this.data = data;
        this.activity = activity;


    }

    // This method is used to attach
    // custom layout to the recycler view
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater LI = activity.getLayoutInflater();
        View vw = LI.inflate(R.layout.custom_layout, null);
        return new ViewHolder(vw);
    }

    // This method is used to set the action
    // to the widgets of our custom layout.
    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, @SuppressLint("RecyclerView") final int position) {

        holder.bookid.setText(data.get(position).getBookid());
        holder.bookname.setText(data.get(position).getBookname());
        holder.bookauthore.setText(data.get(position).getBookauthore());

        holder.bookname.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int posit = holder.getBindingAdapterPosition();
                Model book = data.get(position);
                String bookid = book.getBookid();
                String bookname = book.getBookname();
                String author = book.getBookauthore();
                Toast.makeText(activity, "The position is " + String.valueOf(position) +
                        " Book Name: " + bookname + ", Authore :" + author, Toast.LENGTH_SHORT).show();


                Intent intent = new Intent(activity, Book_content.class);
                intent.putExtra("Rbookid", bookid);
                intent.putExtra("Rbookname", bookname);
                intent.putExtra("Rauthor", author);
                activity.startActivity(intent);


                //Toast.makeText(activity, "you Clicked on "+ posit, Toast.LENGTH_SHORT).show();
            }
        });


    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView bookid,bookname,bookauthore;

        public ViewHolder(View itemView) {
            super(itemView);
            this.bookid = itemView.findViewById(R.id.bookid);
            this.bookname = itemView.findViewById(R.id.bookname);
            this.bookauthore = itemView.findViewById(R.id.authore);
        }
    }
//for seaching in recview
public void filterList(ArrayList<Model> filteredList) {
    data = filteredList;
    notifyDataSetChanged();
}
}


