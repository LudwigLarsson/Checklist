package ru.samsung.case2022;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class AddProductActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);
    }
    public void onSend() {
        Intent intent = new Intent();
        intent.putExtra("et", "inforamation to send comeback to first activity");
        setResult(RESULT_OK, intent);
        finish();
    }
}