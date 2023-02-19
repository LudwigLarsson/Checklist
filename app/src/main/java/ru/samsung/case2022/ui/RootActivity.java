package ru.samsung.case2022.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.List;

import ru.samsung.case2022.R;
import ru.samsung.case2022.adapters.ProductRecyclerAdapter;
import ru.samsung.case2022.data.DataBaseHandler;
import ru.samsung.case2022.model.Products;

public class RootActivity extends AppCompatActivity {
    private Button bt;
    private Button bt1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DataBaseHandler dataBaseHandler = new DataBaseHandler(this);
        RecyclerView rv = (RecyclerView) findViewById(R.id.recycler);
        ProductRecyclerAdapter adapter = new ProductRecyclerAdapter(this);
        rv.setAdapter(adapter);
        rv.setLayoutManager(new LinearLayoutManager(this));
        adapter.notifyDataSetChanged();

        //Добавляем продукты в базу данных

//        dataBaseHandler.addProd(new Products("Milk","Dairy"));
//        dataBaseHandler.addProd(new Products("Apple","Fruits"));
//        dataBaseHandler.addProd(new Products("Onion","Vegetables"));

        //Список всех продуктов за раз

        List<Products> productsList = dataBaseHandler.getAllProd();

        //Удаляем какой-либо продукт по его id
        //Products deleteProd = dataBaseHandler.getProd();
        //dataBaseHandler.deleteProd(deleteProd);

        //Вывод списка продуктов в консоль
//        for (Products products : productsList){
//            Log.d("Products info: ", "ID" + products.getId() + " , Name - " + products.getName() + ", Category - " + products.getCategory());
//        }

        //Смена каких-либо характеристик продукта
        //Products products = dataBaseHandler.getProd(2);
        //products.setName("Cucumber");
        //products.setCategory("Vegetables");
        //dataBaseHandler.updateProd(products);
        //Log.d("Products info: ", "ID" + products.getId() + " , Name - " + products.getName() + ", Category - " + products.getCategory());




        bt = (Button) findViewById(R.id.scan);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toScanner();
            }
        });
        bt1 = (Button) findViewById(R.id.add);
        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addProduct();
            }
        });
    }
    public void toScanner() {
        // вызов камеры непонятно как
        // допустим, сюда передаётся фото
        // надо его кешировать и передать ссылку/id в PhotoViewerActivity
        Intent intent = new Intent(RootActivity.this, PhotoViewerActivity.class);
        startActivity(intent);
    }
    public void addProduct() {
        Intent intent = new Intent(RootActivity.this, AddProductActivity.class);
        startActivity(intent);
//        public void onActivityResult(int requestCode, int resultCode, Intent data) {    //data - ?
//            switch (resultCode) {
//                case RESULT_OK:
//                    productName.setText(data.getStringExtra("et"));
//                    break;
//            }
//        }
    }
    public void editProduct() {
        Intent intent = new Intent(RootActivity.this, EditProduct.class);
        startActivity(intent);
    }
}