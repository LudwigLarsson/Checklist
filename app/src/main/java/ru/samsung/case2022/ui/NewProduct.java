package com.example.mcvfttfuvcwec;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

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