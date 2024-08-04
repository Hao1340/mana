package com.example.spendmanager;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {

    private Button addRevenueButton;
    private Button addExpenseButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addRevenueButton = findViewById(R.id.addRevenueButtonm);
        addExpenseButton = findViewById(R.id.addExpenseButtonm);


    }
}
