package ru.samsung.case2022.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import ru.samsung.case2022.R;
import ru.samsung.case2022.data.DataBaseHandler;
import ru.samsung.case2022.model.Products;

public class AddProductActivity extends AppCompatActivity {
    // активность по добавления продуктов
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);
        EditText t = (EditText) findViewById(R.id.editProductName);
        EditText t2 = (EditText)findViewById(R.id.editProductCount);
        Button butAdd = findViewById(R.id.save);
        butAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(t.getText().length() != 0 && t2.getText().length() != 0) {
                    if(Integer.parseInt(t2.getText().toString()) != 0) {
                        // добавляем продукт в бд
                        DataBaseHandler dataBaseHandler = new DataBaseHandler(AddProductActivity.this);
                        dataBaseHandler.addProd(new Products(t.getText().toString(), "Dairy", Integer.parseInt(t2.getText().toString())));
                        // переходим обратно в ресайкл
                        Intent intent = new Intent(AddProductActivity.this, RootActivity.class);
                        startActivity(intent);
                        finish();
                    }
                }
            }
        });
    }
}