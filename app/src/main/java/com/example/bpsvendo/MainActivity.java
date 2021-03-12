package com.example.bpsvendo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    EditText merchantname, merchantcode, amount, product;
    Button btnproceed;
    FirebaseDatabase database;
    DatabaseReference myRef;
    DatabaseReference dbRef;
    String merchantName, merchantCode, pro, amounts;
    ImageButton btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        merchantname = findViewById(R.id.etMerchantName);
        merchantcode = findViewById(R.id.etMerchantCode);
        amount = findViewById(R.id.etAmount);
        product = findViewById(R.id.etProduct);
        btnproceed = findViewById(R.id.btnProceed);
        btnBack = findViewById(R.id.btnBack);
        btnproceed.setOnClickListener(this);
        btnBack.setOnClickListener(this);
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("product");
        dbRef = database.getReference("qr");

        init();
    }

    private void init() {
        Intent intent = getIntent();
        merchantName = intent.getExtras().getString("esewaid");
        merchantCode = getIntent().getExtras().getString("name");
        pro = getIntent().getExtras().getString("product");
        amounts = getIntent().getExtras().getString("amount");
        merchantcode.setText(merchantCode);
        merchantname.setText(merchantName);
        amount.setText(amounts);
        product.setText(pro);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnProceed:
                myRef.setValue(pro);
                Toast.makeText(this, pro, Toast.LENGTH_SHORT).show();
                dbRef.setValue(true);
                break;
            case R.id.btnBack:
                finish();
                break;
        }
    }
}