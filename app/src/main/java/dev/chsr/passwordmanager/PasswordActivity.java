package dev.chsr.passwordmanager;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import java.util.ArrayList;
import java.util.List;

public class PasswordActivity extends AppCompatActivity {
    CardView keyCard;
    EditText keyEditText;
    EditText emailEditText;
    EditText passwordEditText;
    EditText notesEditText;
    Button saveButton;
    Button deleteButton;
    Button copyButton;

    List<String> getPasswordKeys(List<PasswordItem> passwordList) {
        List<String> keys = new ArrayList<>();
        for (PasswordItem item: passwordList) {
            keys.add(item.getKey());
        }
        return keys;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acivity_password);

        keyCard = findViewById(R.id.key_card);
        keyEditText = findViewById(R.id.password_edit_key);
        emailEditText = findViewById(R.id.password_edit_email);
        passwordEditText = findViewById(R.id.password_edit_password);
        notesEditText = findViewById(R.id.password_edit_notes);
        saveButton = findViewById(R.id.save_password_btn);
        deleteButton = findViewById(R.id.delete_password_btn);
        copyButton = findViewById(R.id.copy_password_btn);

        List<PasswordItem> passwordList = PrefsManager.getPasswordList(getApplicationContext());

        Intent intent = getIntent();
        int pos = intent.getIntExtra("position", -1);
        keyEditText.setEnabled(true);

        if (pos != -1) {
            PasswordItem passwordItem = passwordList.get(pos);
            keyEditText.setText(passwordItem.getKey());
            emailEditText.setText(passwordItem.getEmail());
            passwordEditText.setText(passwordItem.getPassword());
            notesEditText.setText(passwordItem.getNotes());
            keyEditText.setEnabled(false);
        }

        saveButton.setOnClickListener(view -> {
            if (pos == -1) {
                if (!getPasswordKeys(passwordList).contains(keyEditText.getText().toString())) {
                    PasswordItem passwordItem = new PasswordItem(
                            keyEditText.getText().toString(),
                            emailEditText.getText().toString(),
                            passwordEditText.getText().toString(),
                            notesEditText.getText().toString()
                    );
                    passwordList.add(passwordItem);
                    PrefsManager.savePasswordList(getApplicationContext(), passwordList);
                    startActivity(new Intent(this, MainActivity.class));
                } else {
//                    todo - normal animation
                    keyCard.animate().setDuration(250);
                    keyCard.animate().alphaBy(1);
                    keyCard.animate().alpha(.5f);
                    keyCard.animate().withEndAction(() -> {
                        keyCard.animate().setDuration(250);
                        keyCard.animate().alphaBy(.5f);
                        keyCard.animate().alpha(1);
                        keyCard.animate().start();
                    });
                    keyCard.animate().start();

                }
            } else {
                PasswordItem passwordItem = passwordList.get(pos);
                passwordItem.setEmail(emailEditText.getText().toString());
                passwordItem.setPassword(passwordEditText.getText().toString());
                passwordItem.setNotes(notesEditText.getText().toString());
                PrefsManager.savePasswordList(getApplicationContext(), passwordList);
                startActivity(new Intent(this, MainActivity.class));
            }
        });

        deleteButton.setOnClickListener(view -> {
            if (pos != -1) {
                passwordList.remove(pos);
                PrefsManager.savePasswordList(getApplicationContext(), passwordList);
            }
            startActivity(new Intent(this, MainActivity.class));
        });

        copyButton.setOnClickListener(view -> {
            if (pos != -1) {
                ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData clip = ClipData.newPlainText("label", passwordList.get(pos).getPassword());
                clipboard.setPrimaryClip(clip);
            }
            startActivity(new Intent(this, MainActivity.class));
        });
    }
}