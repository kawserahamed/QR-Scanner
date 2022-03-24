package org.primeit.qrcodeandbarcodescanner.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import org.primeit.qrcodeandbarcodescanner.R;
import org.primeit.qrcodeandbarcodescanner.adapter.HistoryAdapter;
import org.primeit.qrcodeandbarcodescanner.data.model.HistoryModel;
import org.primeit.qrcodeandbarcodescanner.databinding.ActivityMainBinding;
import org.primeit.qrcodeandbarcodescanner.listener.HistoryListeners;
import org.primeit.qrcodeandbarcodescanner.viewModel.HistoryViewModel;

public class MainActivity extends AppCompatActivity implements HistoryListeners {
    ActivityMainBinding binding;
    HistoryViewModel historyViewModel;
    HistoryAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setTheme(R.style.Theme_QRAndBarcodeScanner);
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);

        historyViewModel = new ViewModelProvider(this).get(HistoryViewModel.class);

        historyViewModel.getAllHistory.observe(this, histories -> {
            if (!histories.isEmpty()) {
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.top_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {

            case R.id.about:
                Intent intent = new Intent(getApplicationContext(), AboutActivity.class);
                startActivity(intent);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}