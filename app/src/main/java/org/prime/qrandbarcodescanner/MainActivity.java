package org.prime.qrandbarcodescanner;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.prime.qrandbarcodescanner.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    public static TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        textView = findViewById(R.id.resultText);

        binding.scanButton.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), ScannerActivity.class);
            startActivity(intent);

        });

    }
}