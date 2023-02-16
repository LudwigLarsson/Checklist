package ru.samsung.case2022.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import ru.samsung.case2022.R;

public class NewProduct extends AppCompatActivity {

        @Override
        protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.activity_new_product);
                Button butAdd = findViewById(R.id.save);
                butAdd.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                                Intent intent=new Intent(NewProduct.this, RecycleShow.class);
                                startActivity(intent);
                        }
                });
        }
}