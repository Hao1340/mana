package com.example.spendmanager.Model;

import android.content.Context;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class RevenueCRUD {
    private DatabaseHelper dbHelper;
    public RevenueCRUD (Context context) {
        dbHelper =  new DatabaseHelper(context);
    }
    // Add revenue
    public boolean addRevenue(String revenueDate, double amount, String description, int userId) {
        double currentLimit = getRevenueLimit(userId);

        if (amount > currentLimit) {
            // Transaction exceeds limit, return false or handle it as needed
            return false;
        }

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.COLUMN_REVENUE_USER_ID, userId);
        values.put(DatabaseHelper.COLUMN_REVENUE_DATE, revenueDate);
        values.put(DatabaseHelper.COLUMN_REVENUE_AMOUNT, amount);
        values.put(DatabaseHelper.COLUMN_REVENUE_DESCRIPTION, description);
        long result = db.insert(DatabaseHelper.TABLE_REVENUE, null, values);
        return result != -1;
    }
    // Update revenue
    public boolean updateRevenue(int id, double amount, String description) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.COLUMN_REVENUE_AMOUNT, amount);
        values.put(DatabaseHelper.COLUMN_REVENUE_DESCRIPTION, description);
        int result = db.update(DatabaseHelper.TABLE_REVENUE, values, DatabaseHelper.COLUMN_REVENUE_ID + " = ?", new String[]{String.valueOf(id)});
        return result > 0;
    }
    // Delete revenue
    public boolean deleteRevenue(int id) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        int result = db.delete(DatabaseHelper.TABLE_REVENUE, DatabaseHelper.COLUMN_REVENUE_ID + " = ?", new String[]{String.valueOf(id)});
        return result > 0;
    }
    // Get all revenue records for a user
    public List<Revenue> getAllRevenues(int userId) {
        List<Revenue> revenueList = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String[] columns = {DatabaseHelper.COLUMN_REVENUE_ID, DatabaseHelper.COLUMN_REVENUE_DATE, DatabaseHelper.COLUMN_REVENUE_AMOUNT, DatabaseHelper.COLUMN_REVENUE_DESCRIPTION};
        String selection = DatabaseHelper.COLUMN_REVENUE_USER_ID + "=?";
        String[] selectionArgs = {String.valueOf(userId)};

        Cursor cursor = db.query(DatabaseHelper.TABLE_REVENUE, columns, selection, selectionArgs, null, null, DatabaseHelper.COLUMN_REVENUE_AMOUNT + " ASC");

        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_REVENUE_ID));
                String date = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_REVENUE_DATE));
                double amount = cursor.getDouble(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_REVENUE_AMOUNT));
                String description = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_REVENUE_DESCRIPTION));

                Revenue revenue = new Revenue(id, date, amount, description);
                revenueList.add(revenue);
            } while (cursor.moveToNext());
        }

        cursor.close();
        return revenueList;
    }
    // Set revenue limit
    public boolean setRevenueLimit(int userId, double limit) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.COLUMN_LIMIT_USER_ID, userId);
        values.put(DatabaseHelper.COLUMN_REVENUE_LIMIT, limit);
        long result = db.replace(DatabaseHelper.TABLE_TRANSACTION_LIMITS, null, values);
        return result != -1;
    }
    // Get revenue limit
    public double getRevenueLimit(int userId) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.query(DatabaseHelper.TABLE_TRANSACTION_LIMITS,
                new String[]{DatabaseHelper.COLUMN_REVENUE_LIMIT},
                DatabaseHelper.COLUMN_LIMIT_USER_ID + "=?",
                new String[]{String.valueOf(userId)},
                null, null, null);

        if (cursor != null && cursor.moveToFirst()) {
            double limit = cursor.getDouble(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_REVENUE_LIMIT));
            cursor.close();
            return limit;
        }

        return Double.MAX_VALUE; // No limit if not found
    }

}
