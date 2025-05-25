package com.example.user_sharedpreferences;



import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText nameEditText, lastNameEditText;
    private TextView profileTextView;
    private Button saveButton;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nameEditText = findViewById(R.id.nameEditText);
        lastNameEditText = findViewById(R.id.lastNameEditText);
        profileTextView = findViewById(R.id.profileTextView);
        saveButton = findViewById(R.id.saveButton);

        // مقداردهی اولیه SharedPreferences
        sharedPreferences = getSharedPreferences("UserProfile", MODE_PRIVATE);

        // نمایش داده‌های ذخیره‌شده در TextView
        loadProfileData();

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveProfileData();
            }
        });
    }

    private void saveProfileData() {
        String name = nameEditText.getText().toString().trim();
        String lastName = lastNameEditText.getText().toString().trim();

        if (!name.isEmpty() && !lastName.isEmpty()) {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("name", name);
            editor.putString("lastName", lastName);
            editor.apply();

            Toast.makeText(MainActivity.this, "پروفایل ذخیره شد!", Toast.LENGTH_SHORT).show();
            loadProfileData();
        } else {
            Toast.makeText(MainActivity.this, "لطفاً تمامی فیلد‌ها را پر کنید.", Toast.LENGTH_SHORT).show();
        }
    }

    private void loadProfileData() {
        String name = sharedPreferences.getString("name", "نام وارد نشده");
        String lastName = sharedPreferences.getString("lastName", "نام خانوادگی وارد نشده");
        profileTextView.setText("پروفایل:\n" + name + " " + lastName);
    }
}
