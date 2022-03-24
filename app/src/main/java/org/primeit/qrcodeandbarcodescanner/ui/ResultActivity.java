package org.primeit.qrcodeandbarcodescanner.ui;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.primeit.qrcodeandbarcodescanner.R;
import org.primeit.qrcodeandbarcodescanner.databinding.ActivityResultBinding;

import java.util.Objects;

public class ResultActivity extends AppCompatActivity {

    ActivityResultBinding binding;

    public TextView urlTextView;
    Button copyButton;
    ClipboardManager clipboard;
    ClipData myClip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityResultBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);
        Objects.requireNonNull(getSupportActionBar()).setTitle("About");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        urlTextView = findViewById(R.id.textUrlView);
        copyButton = findViewById(R.id.copyButton);
        clipboard = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
        urlTextView.setText(getIntent().getStringExtra("result"));

        copyButton.setOnClickListener(view -> {
            String text = urlTextView.getText().toString();
            myClip = ClipData.newPlainText("text", text);
            clipboard.setPrimaryClip(myClip);
            Toast.makeText(getApplicationContext(), "Copied",
                    Toast.LENGTH_SHORT).show();

        });

    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}