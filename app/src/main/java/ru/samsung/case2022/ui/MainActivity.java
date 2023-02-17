package ru.samsung.case2022.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.List;

import ru.samsung.case2022.R;
import ru.samsung.case2022.data.DataBaseHandler;
import ru.samsung.case2022.model.Products;

public class NewProduct extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_product);
        EditText t = findViewById(R.id.editProductName);
        Button butAdd = findViewById(R.id.save);
        butAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DataBaseHandler dataBaseHandler = new DataBaseHandler(NewProduct.this);
                dataBaseHandler.addProd(new Products(t.getText().toString(),"Dairy"));
                Intent intent=new Intent(NewProduct.this, RecycleShow.class);
                startActivity(intent);
                //
            }
        });
    }
}