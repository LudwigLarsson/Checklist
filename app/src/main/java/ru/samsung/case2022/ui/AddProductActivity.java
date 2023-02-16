package ru.samsung.case2022.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import ru.samsung.case2022.R;

public class AddProductActivity extends AppCompatActivity {
    private Button bt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);
        bt = (Button) findViewById(R.id.save);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                send();
            }
        });

    }

    protected void send(){
        Intent intent = new Intent(AddProductActivity.this, MainActivity.class);
        intent.putExtra("et", "inforamation to send comeback to first activity");
        setResult(RESULT_OK, intent);
        finish();
    }

}