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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);
        EditText t = (EditText) findViewById(R.id.editProductName);
        Button butAdd = findViewById(R.id.save);
        butAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DataBaseHandler dataBaseHandler = new DataBaseHandler(AddProductActivity.this);
                dataBaseHandler.addProd(new Products(t.getText().toString(),"Dairy"));
                Intent intent=new Intent(AddProductActivity.this, RootActivity.class);
                startActivity(intent);
                finish();
                //
            }
        });
    }
}