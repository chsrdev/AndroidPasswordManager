package dev.chsr.passwordmanager;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MainActivity extends AppCompatActivity {
    RecyclerView passwordListView;
    PasswordAdapter passwordAdapter;
    Button addButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addButton = findViewById(R.id.add_password_btn);
        passwordListView = findViewById(R.id.password_list);

        addButton.setOnClickListener(view -> {
            Intent intent = new Intent(this, PasswordActivity.class);
            intent.putExtra("position", -1);
            startActivity(intent);
        });

        passwordAdapter = new PasswordAdapter(PrefsManager.getPasswordList(getApplicationContext()));
        passwordListView.setLayoutManager(new LinearLayoutManager(this));
        passwordListView.setAdapter(passwordAdapter);
    }
}