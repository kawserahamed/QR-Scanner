package org.prime.qrandbarcodescanner.ui;

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

import org.prime.qrandbarcodescanner.data.model.HistoryModel;
import org.prime.qrandbarcodescanner.viewModel.HistoryViewModel;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class QRScanActivity extends AppCompatActivity implements ZXingScannerView.ResultHandler {

    ZXingScannerView scannerView;
    HistoryViewModel historyViewModel;
    Calendar calendar = Calendar.getInstance();
    SimpleDateFormat sdf = new SimpleDateFormat("dd-MMMM-yyyy", Locale.getDefault());
    String date = sdf.format(calendar.getTime());

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
        Intent intent = new Intent(getApplicationContext(), UrlActivity.class);
        intent.putExtra("url", result.getText());
        startActivity(intent);
        String codeType = result.getBarcodeFormat().toString();
        String textUrl = result.getText();
        String strDate = date;
        createHistory(textUrl, strDate, codeType);
    }

    private void createHistory(String textUrl, String strDate, String codeType) {
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