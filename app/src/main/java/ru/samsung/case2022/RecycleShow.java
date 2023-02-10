package ru.samsung.case2022;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

public class RecycleShow extends AppCompatActivity {
    private RecyclerView rvProduct;


    private ProductRecyclerAdapter productAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recycle_view);
        getSupportActionBar().hide();
        Button butAdd = findViewById(R.id.add);
        butAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(RecycleShow.this, NewProduct.class);
                startActivity(intent);
            }
        });

        Button butScan = findViewById(R.id.scan);
        butScan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //scan text
            }
        });
        rvProduct = findViewById(R.id.recycle);
        //Log.e("j1", "OK1");
        try {
            productAdapter = new ProductRecyclerAdapter(this);
            rvProduct.setAdapter(productAdapter);
        }catch (Exception e)
        {
            rvProduct.setAdapter(null);
        }

    }
    // желтую хрень мб нужно убрать
    @SuppressLint("NotifyDataSetChanged")
    public void updateAdapter()
    {
        productAdapter.notifyDataSetChanged();
    }
}