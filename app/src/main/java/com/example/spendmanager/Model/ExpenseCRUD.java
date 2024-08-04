package com.example.spendmanager.Model;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class ExpenseCRUD {
    private DatabaseHelper dbHelper;

    public ExpenseCRUD(Context context) {
        dbHelper = new DatabaseHelper(context);
    }

    // Add expense
    public boolean addExpense(String expenseDate, double amount, String description, int userId) {
        double currentLimit = getExpenseLimit(userId);

        if (amount > currentLimit) {
            // Transaction exceeds limit, return false or handle it as needed
            return false;
        }

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.COLUMN_EXPENSE_USER_ID, userId);
        values.put(DatabaseHelper.COLUMN_EXPENSE_DATE, expenseDate);
        values.put(DatabaseHelper.COLUMN_EXPENSE_AMOUNT, amount);
        values.put(DatabaseHelper.COLUMN_EXPENSE_DESCRIPTION, description);
        long result = db.insert(DatabaseHelper.TABLE_EXPENSE, null, values);
        return result != -1;
    }

    // Update expense
    public boolean updateExpense(int id, double amount, String description) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.COLUMN_EXPENSE_AMOUNT, amount);
        values.put(DatabaseHelper.COLUMN_EXPENSE_DESCRIPTION, description);
        int result = db.update(DatabaseHelper.TABLE_EXPENSE, values, DatabaseHelper.COLUMN_EXPENSE_ID + " = ?", new String[]{String.valueOf(id)});
        return result > 0;
    }

    // Delete expense
    public boolean deleteExpense(int id) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        int result = db.delete(DatabaseHelper.TABLE_EXPENSE, DatabaseHelper.COLUMN_EXPENSE_ID + " = ?", new String[]{String.valueOf(id)});
        return result > 0;
    }

    // Get all expense records for a user
    public List<Expense> getAllExpenses(int userId) {
        List<Expense> expenseList = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String[] columns = {DatabaseHelper.COLUMN_EXPENSE_ID, DatabaseHelper.COLUMN_EXPENSE_DATE, DatabaseHelper.COLUMN_EXPENSE_AMOUNT, DatabaseHelper.COLUMN_EXPENSE_DESCRIPTION};
        String selection = DatabaseHelper.COLUMN_EXPENSE_USER_ID + "=?";
        String[] selectionArgs = {String.valueOf(userId)};

        Cursor cursor = db.query(DatabaseHelper.TABLE_EXPENSE, columns, selection, selectionArgs, null, null, DatabaseHelper.COLUMN_EXPENSE_AMOUNT + " ASC");

        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_EXPENSE_ID));
                String date = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_EXPENSE_DATE));
                double amount = cursor.getDouble(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_EXPENSE_AMOUNT));
                String description = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_EXPENSE_DESCRIPTION));

                Expense expense = new Expense(id, date, amount,description);
                expenseList.add(expense);
            } while (cursor.moveToNext());
        }

        cursor.close();
        return expenseList;
    }

    // Set expense limit
    public boolean setExpenseLimit(int userId, double limit) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.COLUMN_LIMIT_USER_ID, userId);
        values.put(DatabaseHelper.COLUMN_EXPENSE_LIMIT, limit);
        long result = db.replace(DatabaseHelper.TABLE_TRANSACTION_LIMITS, null, values);
        return result != -1;
    }

    // Get expense limit
    public double getExpenseLimit(int userId) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.query(DatabaseHelper.TABLE_TRANSACTION_LIMITS,
                new String[]{DatabaseHelper.COLUMN_EXPENSE_LIMIT},
                DatabaseHelper.COLUMN_LIMIT_USER_ID + "=?",
                new String[]{String.valueOf(userId)},
                null, null, null);

        if (cursor != null && cursor.moveToFirst()) {
            double limit = cursor.getDouble(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_EXPENSE_LIMIT));
            cursor.close();
            return limit;
        }

        return Double.MAX_VALUE; // No limit if not found
    }
}
