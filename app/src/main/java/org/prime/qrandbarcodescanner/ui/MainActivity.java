package org.prime.qrandbarcodescanner.ui;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import org.prime.qrandbarcodescanner.R;
import org.prime.qrandbarcodescanner.adapter.HistoryAdapter;
import org.prime.qrandbarcodescanner.data.model.HistoryModel;
import org.prime.qrandbarcodescanner.databinding.ActivityMainBinding;
import org.prime.qrandbarcodescanner.lesteners.HistoryLesteners;
import org.prime.qrandbarcodescanner.viewModel.HistoryViewModel;

public class MainActivity extends AppCompatActivity implements HistoryLesteners {
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
            adapter = new HistoryAdapter(getApplicationContext(), histories, this);
            binding.recyclerView.setAdapter(adapter);

        });

        binding.btScan.setOnClickListener(view -> startActivity(new Intent(MainActivity.this,
                QRScanActivity.class)));
    }

    @Override
    public void onHistoryClicked(HistoryModel historyModel, int historyClickedPosition) {

        new AlertDialog.Builder(this)
                .setTitle("Delete item")
                .setMessage("Are you sure you want to delete?")
                .setPositiveButton(
                        "OK"
                        , new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                historyViewModel.delete(historyModel);
                                adapter.notifyDataSetChanged();
                            }
                        })
                .setNegativeButton(
                        "CANCEL", null).show();

    }
}