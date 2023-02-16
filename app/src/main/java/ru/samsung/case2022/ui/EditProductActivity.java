package ru.samsung.case2022.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import ru.samsung.case2022.R;

public class EditProductActivity extends AppCompatActivity {
    private Button bt;
    private Button bt1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_product);
        bt = (Button) findViewById(R.id.save);
        bt1 = (Button) findViewById(R.id.remove);
        bt.setOnClickListener(v -> {
            saveChanges();
        });
        bt1.setOnClickListener(v -> {
            deleteProduct();
        });
    }
    public void deleteProduct() {
        Intent switcher = new Intent(EditProductActivity.this, MainActivity.class);
        startActivity(switcher);
        // todo Database
    }
    public void saveChanges() {
        Intent switcher = new Intent(EditProductActivity.this, MainActivity.class);
        startActivity(switcher);
        // todo Database
    }
}