package org.prime.qrandbarcodescanner;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import org.prime.qrandbarcodescanner.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.scanButton.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), ScannerActivity.class);
            startActivity(intent);

        });

    }
}