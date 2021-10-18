package com.usman.book_list_1_qh;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;


public class DatabaseHelper extends SQLiteOpenHelper {

    // The Android's default system path
    // of your application database.
    private static String DB_PATH = "";
    private static final String DB_NAME = "test1.db";
    private SQLiteDatabase myDataBase;
    private final Context myContext;

    // Table name in the database.
    public static final String Book_List = "booklist";
    public static final String Book_Content = "bookcontent";

    /**
     * Constructor
     * Takes and keeps a reference of
     * the passed context in order
     * to access the application assets and resources.
     */
    public DatabaseHelper(Context context) {

        super(context, DB_NAME, null, 1);
        this.myContext = context;
        DB_PATH = myContext.getDatabasePath(DB_NAME)
                .toString();
    }

    // Creates an empty database
    // on the system and rewrites it
    // with your own database.
    public void createDataBase() {

        boolean dbExist = checkDataBase();

        // do nothing - database already exist
        if (dbExist) {
            Context context = null;
            Toast.makeText(context, "database already Exist", Toast.LENGTH_LONG).show();
        } else {
            // By calling this method and
            // the empty database will be
            // created into the default system
            // path of your application
            // so we are gonna be able
            // to overwrite that database
            // with our database.
            this.getWritableDatabase();
            try {
                copyDataBase();
            } catch (IOException e) {
                throw new Error(
                        "Error copying database");
            }
        }
    }

    // Check if the database already exist
    // to avoid re-copying the file each
    // time you open the application
    // return true if it exists
    // false if it doesn't.
    private boolean checkDataBase() {
        SQLiteDatabase checkDB = null;
        try {
            String myPath = DB_PATH;
            checkDB
                    = SQLiteDatabase
                    .openDatabase(
                            myPath, null,
                            SQLiteDatabase.OPEN_READONLY);
        } catch (SQLiteException e) {

            // database doesn't exist yet.
            Log.e("message", "" + e);
        }
        if (checkDB != null) {
            checkDB.close();
        }
        return checkDB != null;
    }

    /**
     * Copies your database from your
     * local assets-folder to the just
     * created empty database in the
     * system folder, from where it
     * can be accessed and handled.
     * This is done by transferring bytestream.
     */
    private void copyDataBase()
            throws IOException {
        // Open your local db as the input stream
        InputStream myInput
                = myContext.getAssets()
                .open(DB_NAME);

        // Path to the just created empty db
        String outFileName = DB_PATH;

        // Open the empty db as the output stream
        OutputStream myOutput
                = new FileOutputStream(outFileName);

        // transfer bytes from the
        // inputfile to the outputfile
        byte[] buffer = new byte[1024];
        int length;
        while ((length = myInput.read(buffer)) > -1) {
            myOutput.write(buffer, 0, length);
        }

        // Close the streams
        myOutput.flush();
        myOutput.close();
        myInput.close();
    }

    public void openDataBase() {
        // Open the database
        String myPath = DB_PATH;
        myDataBase = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);
    }

    @Override
    public synchronized void close() {
        // close the database.
        if (myDataBase != null)
            myDataBase.close();
        super.close();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // It is an abstract method
        // but we define our own method here.
    }

    @Override
    public void onUpgrade(SQLiteDatabase db,
                          int oldVersion,
                          int newVersion) {
        // It is an abstract method which is
        // used to perform different task
        // based on the version of database.
    }

    // This method is used to get the
    // algorithm topics from the database.
    public ArrayList<Model> getBookList(Activity activity) {
        SQLiteOpenHelper sqLiteOpenHelper = new DatabaseHelper(activity);
        SQLiteDatabase db = sqLiteOpenHelper.getWritableDatabase();
        ArrayList<Model> booklist = new ArrayList<>();

        // query help us to return all data
        // the present in the getBookList table.
        String query = "SELECT * FROM " + Book_List;
        @SuppressLint("Recycle") Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            do {
                //list.add(cursor.getString(1));
                Model obj=new Model(cursor.getString(0),cursor.getString(1),cursor.getString(2));
                booklist.add(obj);
            } while (cursor.moveToNext());
        }
        return booklist;
    }

    // get data for chapters
    public Cursor readalldata(int id)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        String qry="SELECT * FROM " + Book_Content + " where contentid = "+ id;
        Cursor cursor=db.rawQuery(qry,null);
        return  cursor;
    }


}

