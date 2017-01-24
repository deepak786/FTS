package com.deepak.fts.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.deepak.fts.model.User;

import java.util.ArrayList;

/**
 * Created by Deepak Goyal on 24/1/17.
 * Author: Deepak Goyal
 */
public class ConnectDB extends SQLiteOpenHelper {
    private static final String DB_NAME = "fts_test.db";
    private static final int DB_VERSION = 1;

    // FTS 3
    private static final String TABLE_NAME_FTS_3 = "fts3_test";
    private static final String COL_NAME_FTS_3 = "name";
    private static final String COL_EMAIL_FTS_3 = "email";
    private static final String COL_DESC_FTS_3 = "desc";

    // FTS 4
    private static final String TABLE_NAME_FTS_4 = "fts4_test";
    private static final String COL_DESC_FTS_4 = "desc";

    private static ConnectDB connectDB;

    // singleton class
    public static ConnectDB getInstance(Context context) {
        if (null == connectDB)
            connectDB = new ConnectDB(context);
        return connectDB;
    }

    private ConnectDB(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // FTS 3
        // create the FTS_3 table
        db.execSQL("CREATE VIRTUAL TABLE " + TABLE_NAME_FTS_3 + " USING fts3 ( " + COL_NAME_FTS_3 + "," + COL_EMAIL_FTS_3 + ", " + COL_DESC_FTS_3 + " )");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // no need to consider right now
    }

    /**
     * function to insert the dummy data for FTS 3 table
     */
    public void insertFTS3Data(ArrayList<User> mList) {
        int rowsInserted = -1;

        SQLiteDatabase database = getWritableDatabase();
        database.beginTransaction(); // START Transaction

        try {
            for (User user : mList) {
                ContentValues values = new ContentValues();
                values.put(COL_NAME_FTS_3, user.getName());
                values.put(COL_EMAIL_FTS_3, user.getEmail());
                values.put(COL_DESC_FTS_3, user.getDesc());

                database.insert(TABLE_NAME_FTS_3, null, values);
            }
            database.setTransactionSuccessful(); //TRANSACTION SUCCESSFUL

            rowsInserted = mList.size();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println(">>>>ROWS INSERTED = " + rowsInserted);
            database.endTransaction(); // END Transaction
        }
    }

    /**
     * delete the dummy data of FTS 3 table
     */
    public void deleteFTS3Data() {
        SQLiteDatabase database = getWritableDatabase();
        database.delete(TABLE_NAME_FTS_3, null, null);
        database.close();
    }

    /**
     * search the users using FTS3
     */
    public ArrayList<User> searchFTS3(String s) {
        ArrayList<User> users = new ArrayList<>();

        SQLiteDatabase database = getReadableDatabase();
        Cursor cursor = database.query(TABLE_NAME_FTS_3, new String[]{"rowid", COL_NAME_FTS_3, COL_EMAIL_FTS_3, COL_DESC_FTS_3}, TABLE_NAME_FTS_3 + " MATCH ?", new String[]{s + "*"}, null, null, null, null);
        if (cursor != null && cursor.getCount() > 0) {
            cursor.moveToFirst();
            for (int i = 0; i < cursor.getCount(); i++) {
                User user = new User();
                user.setId(cursor.getString(cursor.getColumnIndex("rowid")));
                user.setName(cursor.getString(cursor.getColumnIndex(COL_NAME_FTS_3)));
                user.setEmail(cursor.getString(cursor.getColumnIndex(COL_EMAIL_FTS_3)));
                user.setDesc(cursor.getString(cursor.getColumnIndex(COL_DESC_FTS_3)));
                users.add(user);
                cursor.moveToNext();
            }
        }
        if (cursor != null) {
            cursor.close();
        }
        return users;
    }
}
