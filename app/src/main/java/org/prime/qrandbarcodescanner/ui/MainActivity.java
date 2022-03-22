package org.prime.qrandbarcodescanner.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import org.prime.qrandbarcodescanner.databinding.ActivityMainBinding;
import org.prime.qrandbarcodescanner.adapter.HistoryAdapter;
import org.prime.qrandbarcodescanner.viewModel.HistoryViewModel;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    HistoryViewModel historyViewModel;
    HistoryAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        historyViewModel = new ViewModelProvider(this).get(HistoryViewModel.class);

        historyViewModel.getAllHistory.observe(this, histories -> {
            binding.recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
            adapter = new HistoryAdapter(getApplicationContext(), histories);
            binding.recyclerView.setAdapter(adapter);

        });

        binding.btScan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, ScannerActivity.class));
            }
        });
    }
}