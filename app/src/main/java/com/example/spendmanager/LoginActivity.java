package com.example.spendmanager;



import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.spendmanager.Controller.AddRevenueActivity;
import com.example.spendmanager.Database.DatabaseHelper;
import com.example.spendmanager.MainActivity;
import com.example.spendmanager.R;
import com.example.spendmanager.register;


public class LoginActivity extends AppCompatActivity {
    private EditText usernameInput;
    private EditText passwordInput;
    private Button loginButton;
    private Button registerButton;
    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       /* EdgeToEdge.enable(this);*/
        setContentView(R.layout.activity_login);

        usernameInput = findViewById(R.id.edittext_username);
        passwordInput = findViewById(R.id.edittext_password);
        loginButton = findViewById(R.id.button_Login);
        registerButton = findViewById(R.id.buttonToRegis);

        dbHelper = new DatabaseHelper(this);
        // set event for button login
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = usernameInput.getText().toString();
                String password = passwordInput.getText().toString();
                if(dbHelper.register(username,password)){
                    Intent intent = new Intent(com.example.spendmanager.LoginActivity.this, AddRevenueActivity.class);
                    startActivity(intent);
                    finish();
                }else {
                    Toast.makeText(com.example.spendmanager.LoginActivity.this, "Invalid username or password",Toast.LENGTH_SHORT);
                }
            }

        });
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(com.example.spendmanager.LoginActivity.this, register.class);
                startActivity(intent);
            }
        });
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}
