package org.prime.qrandbarcodescanner.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import org.prime.qrandbarcodescanner.adapter.HistoryAdapter;
import org.prime.qrandbarcodescanner.data.model.HistoryModel;
import org.prime.qrandbarcodescanner.databinding.ActivityMainBinding;
import org.prime.qrandbarcodescanner.listener.HistoryListeners;
import org.prime.qrandbarcodescanner.viewModel.HistoryViewModel;

public class MainActivity extends AppCompatActivity implements HistoryListeners {
    ActivityMainBinding binding;
    HistoryViewModel historyViewModel;
    HistoryAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);

        historyViewModel = new ViewModelProvider(this).get(HistoryViewModel.class);

        historyViewModel.getAllHistory.observe(this, histories -> {
            if (!histories.isEmpty()){
                binding.recyclerView.setVisibility(View.VISIBLE);
                binding.emptyLayout.getRoot().setVisibility(View.GONE);

                binding.recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                adapter = new HistoryAdapter(getApplicationContext(), histories, this);
                binding.recyclerView.setAdapter(adapter);
            } else {
                binding.recyclerView.setVisibility(View.GONE);
                binding.emptyLayout.getRoot().setVisibility(View.VISIBLE);
            }
        });

        binding.btScan.setOnClickListener(view -> startActivity(new Intent(MainActivity.this,
                QRScanActivity.class)));
    }


    @Override
    public void onDeleteClicked(HistoryModel historyModel, int position) {
        new MaterialAlertDialogBuilder(this)
                .setTitle("Delete")
                .setMessage("Are you sure to delete this item?")
                .setNegativeButton("No", (dialogInterface, i) ->
                        dialogInterface.dismiss())
                .setPositiveButton("Yes", (dialogInterface, i) -> {
                    dialogInterface.dismiss();
                    historyViewModel.delete(historyModel);
                    adapter.notifyItemRemoved(position);
                }).show();


    }

}