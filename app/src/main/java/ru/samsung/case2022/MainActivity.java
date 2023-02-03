package ru.samsung.case2022;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button bt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bt = (Button) findViewById(R.id.scan);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toScanner();
            }
        });
    }
    public void toScanner() {
        // вызов камеры непонятно как
        // допустим, сюда передаётся фото
        // надо его кешировать и передать ссылку/id в PhotoViewerActivity
        Intent intent = new Intent(MainActivity.this, PhotoViewerActivity.class);
        startActivity(intent);
    }
//    public void addProduct() {
//        Intent intent = new Intent(MainActivity.this, AddProductActivity.class);
//        startActivityForResult(intent, 1);
//        public void onActivityResult(int requestCode, int resultCode, Intent data) {    //data - ?
//            switch (resultCode) {
//                case RESULT_OK:
//                    productName.setText(data.getStringExtra("et"));
//                    break;
//            }
//        }
//    }
    public void editProduct() {
        Intent intent = new Intent(MainActivity.this, EditProductActivity.class);
        startActivity(intent);
    }
}