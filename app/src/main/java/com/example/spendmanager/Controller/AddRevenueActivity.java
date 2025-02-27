package com.example.spendmanager.Controller;

import static android.content.ContentValues.TAG;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.spendmanager.Model.DatabaseHelper;
import com.example.spendmanager.R;

public class AddRevenueActivity extends AppCompatActivity {

    private static final int ICON_REQUEST_CODE = 1;
    private EditText amountEditText,dayEditText, descriptionEditText;
   /* private ImageView iconImageView;*/
    private ListView revenueListView;
    private Button saveButton, editButton;
    private String selectedIcon = "expense";
    int userId, revenueId =-1;
    private DatabaseHelper dbHelper;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_revenue);
        dayEditText = findViewById(R.id.date);
        amountEditText = findViewById(R.id.amountEditText);
        descriptionEditText = findViewById(R.id.descriptionEditText);
       /* iconImageView.setImageResource(R.drawable.money);*/

        saveButton = findViewById(R.id.saveButton);
        dbHelper = new DatabaseHelper(this);
        userId = getIntent().getIntExtra("USER_ID", -1);
        revenueId = getIntent().getIntExtra("REVENUE_ID", -1);

        if(revenueId !=-1){
            // // Populate fields with existing budget data for editing
            Cursor cursor = dbHelper.getRevenue(userId);
            if (cursor.moveToNext()){
                amountEditText.setText(cursor.getString(cursor.getColumnIndexOrThrow("amountEditText")));
                descriptionEditText.setText(cursor.getString(cursor.getColumnIndexOrThrow("descriptionEditText")));
                dayEditText.setText(cursor.getString(cursor.getColumnIndexOrThrow("date")));
                // Lấy giá trị của biểu tượng và hiển thị trong ImageView
               /* String iconName = cursor.getString(cursor.getColumnIndexOrThrow("icon"));*/

            }
            cursor.close();
        }

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*double amount = Double.parseDouble(amountEditText.getText().toString().trim());
                String description = descriptionEditText.getText().toString().trim();
                String day = dayEditText.getText().toString().trim();

                boolean success;
                if(revenueId!=-1){
                    success = dbHelper.addRevenue(day,amount, description,  userId);
                }else {
                    success = dbHelper.updateRevenue(revenueId, amount, description);
                }
                if (success){
                    Toast.makeText(AddRevenueActivity.this,"Revenue saving success!", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(AddRevenueActivity.this, "Error saving revenue!", Toast.LENGTH_SHORT).show();
                }

            }
        });*/


                try {
                    double amount = Double.parseDouble(amountEditText.getText().toString().trim());
                    String description = descriptionEditText.getText().toString().trim();
                    String day = dayEditText.getText().toString().trim();

                    boolean success;
                    if (revenueId != -1) {
                        success = dbHelper.updateRevenue(revenueId, amount, description);
                    } else {
                        success = dbHelper.addRevenue(day, amount, description, userId);
                    }

                    if (success) {
                        Toast.makeText(AddRevenueActivity.this, "Revenue saving success!", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(AddRevenueActivity.this, "Error saving revenue!", Toast.LENGTH_SHORT).show();
                    }
                } catch (NumberFormatException e) {
                    Log.e(TAG, "Invalid number format", e);
                    Toast.makeText(AddRevenueActivity.this, "Invalid number format", Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    Log.e(TAG, "Error saving revenue", e);
                    Toast.makeText(AddRevenueActivity.this, "Error saving revenue", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


}
