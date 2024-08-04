package com.example.spendmanager.Model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import  com.example.spendmanager.Controller.AddRevenueActivity;

import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "finance_manager.db";
    private static final int DATABASE_VERSION = 1;

    public static final String TABLE_USERS = "users";
    public static final String COLUMN_USER_ID = "user_id";
    public static final String COLUMN_USER_NAME = "user_name";
    public static final String COLUMN_USER_PASSWORD = "user_password";

    public static final String TABLE_REVENUE = "revenue";
    public static final String COLUMN_REVENUE_ID = "revenue_id";
    public static final String COLUMN_REVENUE_USER_ID = "user_id";
    private static final String COLUMN_REVENUE_DATE = "revenue_date";
    /*public static final String COLUMN_REVENUE_ICON = "revenue_icon";*/
    public static final String COLUMN_REVENUE_AMOUNT = "revenue_amount";
    public static final String COLUMN_REVENUE_DESCRIPTION = "revenue_description";

    public static final String TABLE_EXPENSE = "expense";
    public static final String COLUMN_EXPENSE_ID = "expense_id";
    public static final String COLUMN_EXPENSE_USER_ID = "user_id";
    private static final String COLUMN_EXPENSE_DATE = "expense_date";
    /*public static final String COLUMN_EXPENSE_ICON = "expense_icon";*/
    public static final String COLUMN_EXPENSE_AMOUNT = "expense_amount";
    public static final String COLUMN_EXPENSE_DESCRIPTION = "expense_description";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createUserTable = "CREATE TABLE " + TABLE_USERS + " (" +
                COLUMN_USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_USER_NAME + " TEXT, " +
                COLUMN_USER_PASSWORD + " TEXT)";

        String createRevenueTable = "CREATE TABLE " + TABLE_REVENUE + " (" +
                COLUMN_REVENUE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_REVENUE_USER_ID + " INTEGER, " +
                 COLUMN_REVENUE_DATE + " TEXT," +
               /* COLUMN_REVENUE_ICON + " TEXT, " +*/
                COLUMN_REVENUE_AMOUNT + " REAL, " +
                COLUMN_REVENUE_DESCRIPTION + " TEXT, " +
                "FOREIGN KEY(" + COLUMN_REVENUE_USER_ID + ") REFERENCES " + TABLE_USERS + "(" + COLUMN_USER_ID + "))";

        String createExpenseTable = "CREATE TABLE " + TABLE_EXPENSE + " (" +
                COLUMN_EXPENSE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_EXPENSE_USER_ID + " INTEGER, " +
                /*COLUMN_EXPENSE_ICON + " TEXT, " +*/
                COLUMN_EXPENSE_DATE + "TEXT,"+
                COLUMN_EXPENSE_AMOUNT + " REAL, " +
                COLUMN_EXPENSE_DESCRIPTION + " TEXT, " +
                "FOREIGN KEY(" + COLUMN_EXPENSE_USER_ID + ") REFERENCES " + TABLE_USERS + "(" + COLUMN_USER_ID + "))";

        db.execSQL(createUserTable);
        db.execSQL(createRevenueTable);
        db.execSQL(createExpenseTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_REVENUE);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_EXPENSE);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
        onCreate(db);
    }
    //user register
    public boolean register (String username,String password){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_USER_NAME, username);
        values.put(COLUMN_USER_PASSWORD, password);
        long result = db.insert(TABLE_USERS, null, values);
        return result!= -1; // If result is -1, there was an error
    }
    public boolean login (String username, String password){
        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = {COLUMN_USER_ID};
        String selection = COLUMN_USER_NAME + " = ? AND " + COLUMN_USER_PASSWORD + " = ?";
        String[] selectionArgs = {username, password};
        Cursor cursor = db.query(TABLE_USERS, columns, selection, selectionArgs, null, null, null);
        int count = cursor.getCount();
        cursor.close();
        return count > 0;

    }
    public boolean addRevenue(String revenueday,double amount, String description, int userId) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_REVENUE_USER_ID, userId);
        /*values.put(COLUMN_REVENUE_ICON, icon);*/
        values.put(COLUMN_REVENUE_DATE, revenueday);
        values.put(COLUMN_REVENUE_AMOUNT, amount);
        values.put(COLUMN_REVENUE_DESCRIPTION, description);
        long result = db.insert(TABLE_REVENUE, null, values);
        return result != -1;
    }

    public boolean updateRevenue(int id, double amount, String description) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(COLUMN_REVENUE_AMOUNT, amount);
        values.put(COLUMN_REVENUE_DESCRIPTION, description);
        int result = db.update(TABLE_REVENUE, values, COLUMN_REVENUE_ID + " = ?", new String[]{String.valueOf(id)});
        return result > 0;
    }

    public boolean deleteRevenue(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        int result = db.delete(TABLE_REVENUE, COLUMN_REVENUE_ID + " = ?", new String[]{String.valueOf(id)});
        return result > 0;
    }
    public Cursor getRevenue(int userId){
        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = {COLUMN_REVENUE_ID,  COLUMN_REVENUE_DATE, COLUMN_REVENUE_AMOUNT, COLUMN_REVENUE_DESCRIPTION};
        String selection = COLUMN_REVENUE_ID + "=?";
        String[] selectionArgs = {String.valueOf(userId)};

        return  db.query(TABLE_REVENUE, columns, selection, selectionArgs, null, null, COLUMN_REVENUE_AMOUNT+"ASC");
    }


}

