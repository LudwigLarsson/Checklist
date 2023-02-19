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
import ru.samsung.case2022.utils.Util;


public class EditProduct extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_product);
        EditText t = findViewById(R.id.editProductName);
        Button butRename = findViewById(R.id.save);
        Button butDel = findViewById(R.id.remove);
        DataBaseHandler dataBaseHandler = new DataBaseHandler(this);
        t.setText(dataBaseHandler.getProd(Util.changeI).getName());
        butRename.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DataBaseHandler dataBaseHandler = new DataBaseHandler(EditProduct.this);

                dataBaseHandler.updateProd(new Products(t.getText().toString()), Util.changeI);
                Intent intent=new Intent(EditProduct.this, RecycleShow.class);
                startActivity(intent);

                //
            }
        });
        butDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DataBaseHandler dataBaseHandler = new DataBaseHandler(EditProduct.this);

                dataBaseHandler.deleteProd(Util.changeI);

                Intent intent=new Intent(EditProduct.this, RecycleShow.class);
                startActivity(intent);
                //
            }
        });


    }
}