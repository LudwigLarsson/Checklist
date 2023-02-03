package ru.samsung.case2022;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class PhotoViewerActivity extends AppCompatActivity {

    // for check
    private static final int NORM = 1;
    private static final int REQUEST_TAKE_PHOTO = 1;

    // image
    private ImageView iv;
    // Button
    private Button bt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_viewer);
        getPhoto();
        bt = findViewById(R.id.cancel);
        bt.setOnClickListener(v -> {
            cancelPhoto();
        });

    }
    // after doing photo
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        iv = (ImageView) findViewById(R.id.preview);
        if (requestCode == NORM){Bundle extras = data.getExtras();
            Bitmap thumbnailBitmap = (Bitmap) extras.get("data");
            iv.setImageBitmap(thumbnailBitmap);

        }
    }
    public void getPhoto() {

        Intent takePhotoIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        try{
            startActivityForResult(takePhotoIntent, REQUEST_TAKE_PHOTO);
        }catch (ActivityNotFoundException e){
            e.printStackTrace();
        }



    }
    public void cancelPhoto() {
        // delete photo
        iv.setImageDrawable(null);
        Intent intent = new Intent(PhotoViewerActivity.this, MainActivity.class);
        startActivity(intent);

    }
    public void recognizePhoto() {
        // todo Next Activity (ML)

    }
}