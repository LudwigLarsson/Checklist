package com.example.mcvfttfuvcwec;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ChangeProduct extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_product);
        EditText t = findViewById(R.id.editOldProductName);
        Button butRename = findViewById(R.id.saveProduct);
        Button butDel = findViewById(R.id.remove);
        DataBaseHandler dataBaseHandler = new DataBaseHandler(this);
        t.setText(dataBaseHandler.getProd(Util.changeI).getName());
        butRename.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DataBaseHandler dataBaseHandler = new DataBaseHandler(ChangeProduct.this);

                dataBaseHandler.updateProd(new Products(t.getText().toString()), Util.changeI);
                Intent intent=new Intent(ChangeProduct.this, RecycleShow.class);
                startActivity(intent);

                //
            }
        });
        butDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DataBaseHandler dataBaseHandler = new DataBaseHandler(ChangeProduct.this);

                dataBaseHandler.deleteProd(Util.changeI);

                Intent intent=new Intent(ChangeProduct.this, RecycleShow.class);
                startActivity(intent);
                //
            }
        });


    }
}