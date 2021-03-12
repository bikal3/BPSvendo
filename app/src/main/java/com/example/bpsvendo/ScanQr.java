package com.example.bpsvendo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.budiyev.android.codescanner.CodeScanner;
import com.budiyev.android.codescanner.CodeScannerView;
import com.budiyev.android.codescanner.DecodeCallback;
import com.google.gson.Gson;
import com.google.zxing.Result;

import org.json.JSONArray;

public class ScanQr extends AppCompatActivity {
    private CodeScanner mCodeScanner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan_qr);
        CodeScannerView scannerView = findViewById(R.id.scanner_view);
        mCodeScanner = new CodeScanner(this, scannerView);
        mCodeScanner.setDecodeCallback(new DecodeCallback() {
            @Override
            public void onDecoded(@NonNull Result result) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(ScanQr.this, result.getText(), Toast.LENGTH_SHORT).show();
                        Gson gson = new Gson();
                        String s = result.getText();
                        EsewaModel esewaModel = gson.fromJson(s, EsewaModel.class);
                        Intent intent = new Intent(ScanQr.this, MainActivity.class);
                        intent.putExtra("esewaid", esewaModel.eSewa_id);
                        intent.putExtra("name", esewaModel.name);
                        intent.putExtra("amount", esewaModel.amount);
                        intent.putExtra("product", esewaModel.product);
                        startActivity(intent);
                    }
                });
            }
        });
        scannerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCodeScanner.startPreview();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        mCodeScanner.startPreview();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mCodeScanner.releaseResources();
    }
}