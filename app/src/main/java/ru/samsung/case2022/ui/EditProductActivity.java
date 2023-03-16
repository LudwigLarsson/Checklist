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


public class EditProductActivity extends AppCompatActivity {
    // активность по изменению продукта
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
                // изменяем продукт в бд
                DataBaseHandler dataBaseHandler = new DataBaseHandler(EditProductActivity.this);
                dataBaseHandler.updateProd(new Products(t.getText().toString(), dataBaseHandler.getProd(Util.changeI).getCount()), Util.changeI);
                // переходим обратно в ресайкл
                Intent intent=new Intent(EditProductActivity.this, RootActivity.class);
                startActivity(intent);

            }
        });
        butDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DataBaseHandler dataBaseHandler = new DataBaseHandler(EditProductActivity.this);

                dataBaseHandler.deleteProd(Util.changeI);

                Intent intent=new Intent(EditProductActivity.this, RootActivity.class);
                startActivity(intent);
                //
            }
        });


    }
}