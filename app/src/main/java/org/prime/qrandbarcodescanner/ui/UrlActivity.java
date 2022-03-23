package org.prime.qrandbarcodescanner.ui;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.prime.qrandbarcodescanner.R;

public class UrlActivity extends AppCompatActivity {

    public TextView urlTextView;
    Button copyButton;
    ClipboardManager clipboard;
    ClipData myClip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_url);

        urlTextView = findViewById(R.id.textUrlView);
        copyButton = findViewById(R.id.copyButton);
        clipboard = (ClipboardManager)getSystemService(CLIPBOARD_SERVICE);
        urlTextView.setText(getIntent().getStringExtra("url")); ;

        copyButton.setOnClickListener(view -> {
            String text = urlTextView.getText().toString();
            myClip = ClipData.newPlainText("text", text);
            clipboard.setPrimaryClip(myClip);
            Toast.makeText(getApplicationContext(), "URL Copied",
                    Toast.LENGTH_SHORT).show();

        });

    }
}