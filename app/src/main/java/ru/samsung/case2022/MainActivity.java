package ru.samsung.case2022;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void toScanner() {
        // вызов камеры непонятно как
        // допустим, сюда передаётся фото
        // надо его кешировать и передать ссылку/id в PhotoViewerActivity
    }
    public void addProduct() {
        Intent intent = new Intent(MainActivity.this, AddProductActivity.class);
        startActivityForResult(intent, 1);
        public void onActivityResult(int requestCode, int resultCode, Intent data) {    //data - ?
            switch (resultCode) {
                case RESULT_OK:
                    productName.setText(data.getStringExtra("et"));
                    break;
            }
        }
    }
    public void editProduct() {
        Intent intent = new Intent(MainActivity.this, EditProductActivity.class);
        startActivity(intent);
    }
}