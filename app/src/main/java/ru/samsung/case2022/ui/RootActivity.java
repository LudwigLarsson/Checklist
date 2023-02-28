package ru.samsung.case2022.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;

import java.io.ByteArrayOutputStream;
import java.util.List;

import ru.samsung.case2022.R;
import ru.samsung.case2022.adapters.ProductRecyclerAdapter;
import ru.samsung.case2022.data.DataBaseHandler;
import ru.samsung.case2022.model.Products;

public class RootActivity extends AppCompatActivity {

    private static final int NORM = 1;
    private static final int REQUEST_TAKE_PHOTO = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recycle_view);
        MaterialButton buttAdd= (MaterialButton) findViewById(R.id.add);
        MaterialButton buttScan= (MaterialButton) findViewById(R.id.scan);
        MaterialButton buttClear= (MaterialButton) findViewById(R.id.clear);
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


        buttScan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getPhoto();
            }
        });
        buttAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addProduct();
            }
        });
        buttClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dataBaseHandler.deleteAll();
                adapter.notifyDataSetChanged();
            }
        });

    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == NORM){Bundle extras = data.getExtras();
//            Bitmap thumbnailBitmap = (Bitmap) extras.get("data");
//            Intent intent = new Intent(this, PhotoViewerActivity.class);
//            intent.putExtra("BitmapImage", thumbnailBitmap);
//            startActivity(intent);

            Bitmap thumbnailBitmap = (Bitmap) extras.get("data");
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            thumbnailBitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
            byte[] byteArray = stream.toByteArray();
            Intent intent = new Intent(this, PhotoViewerActivity.class);
            intent.putExtra("picture", byteArray);
            startActivity(intent);
        }
    }

    public void toScanner() {
        // вызов камеры непонятно как
        // допустим, сюда передаётся фото
        // надо его кешировать и передать ссылку/id в PhotoViewerActivity
        Intent intent = new Intent(RootActivity.this, PhotoViewerActivity.class);
        startActivity(intent);
    }
    public void clear(){

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

    public void getPhoto() {
        // получает фото
        Intent takePhotoIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        try {
            startActivityForResult(takePhotoIntent, REQUEST_TAKE_PHOTO);
        } catch (ActivityNotFoundException e) {
            e.printStackTrace();
        }
    }
}
