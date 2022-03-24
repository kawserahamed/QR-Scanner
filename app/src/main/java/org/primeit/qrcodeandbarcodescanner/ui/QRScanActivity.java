package org.primeit.qrcodeandbarcodescanner.ui;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.google.zxing.Result;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

import org.primeit.qrcodeandbarcodescanner.data.model.HistoryModel;
import org.primeit.qrcodeandbarcodescanner.viewModel.HistoryViewModel;

import java.util.Calendar;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class QRScanActivity extends AppCompatActivity implements ZXingScannerView.ResultHandler {

    ZXingScannerView scannerView;
    HistoryViewModel historyViewModel;
    Calendar calendar = Calendar.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        scannerView = new ZXingScannerView(this);
        setContentView(scannerView);


        historyViewModel = new ViewModelProvider(this).get(HistoryViewModel.class);
        Dexter.withContext(getApplicationContext())
                .withPermission(Manifest.permission.CAMERA)
                .withListener(new PermissionListener() {
                    @Override
                    public void onPermissionGranted(PermissionGrantedResponse permissionGrantedResponse) {
                        scannerView.startCamera();
                    }

                    @Override
                    public void onPermissionDenied(PermissionDeniedResponse permissionDeniedResponse) {

                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(PermissionRequest permissionRequest, PermissionToken permissionToken) {
                        permissionToken.continuePermissionRequest();
                    }
                }).check();

    }

    @Override
    public void handleResult(Result result) {
        String codeType = result.getBarcodeFormat().toString();
        String textUrl = result.getText();
        createHistory(textUrl, System.currentTimeMillis(), codeType);

        Intent intent = new Intent(getApplicationContext(), ResultActivity.class);
        intent.putExtra("result", result.getText());
        startActivity(intent);
    }

    private void createHistory(String textUrl, Long strDate, String codeType) {
        HistoryModel history = new HistoryModel(textUrl, strDate, codeType);
        historyViewModel.insert(history);
    }


    @Override
    protected void onPause() {
        super.onPause();
        scannerView.stopCamera();
    }

    @Override
    protected void onResume() {
        super.onResume();
        scannerView.setResultHandler(this);
        scannerView.startCamera();
    }
}