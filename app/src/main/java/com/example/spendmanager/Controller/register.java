package com.example.spendmanager.Controller;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.spendmanager.Model.DatabaseHelper;
import com.example.spendmanager.R;

public class register extends Activity {
    private EditText user;
    private EditText pass;
    private Button btn_register;
    public DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.register);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;


        });
        user = (EditText) findViewById(R.id.edittext_username);
        String username = user.getText().toString();
        pass = (EditText) findViewById(R.id.edittext_password);
        String password = pass.getText().toString();
        //setonclick on button btn_register
        btn_register = findViewById(R.id.btn_AddRegis);
        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (dbHelper.register(username, password)) {
                    Toast.makeText(register.this, "You registed successfully!",Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(register.this, "Registration failed", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}
