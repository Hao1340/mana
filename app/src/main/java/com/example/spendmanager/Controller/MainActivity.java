package com.example.spendmanager.Controller;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.spendmanager.R;

public class MainActivity extends Activity {

    private Button addRevenueButton;
    private Button addExpenseButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addRevenueButton = findViewById(R.id.addRevenueButtonm);
        addExpenseButton = findViewById(R.id.addExpenseButtonm);

        // Xử lý sự kiện khi người dùng nhấn nút "Add Revenue"
        addRevenueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Tạo Intent để chuyển sang AddRevenueActivity
                Intent intent = new Intent(MainActivity.this, AddRevenueActivity.class);
                startActivity(intent);
            }
        });
    }

}
