package ru.samsung.case2022.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import org.tensorflow.lite.DataType;
import org.tensorflow.lite.Interpreter;
import org.tensorflow.lite.support.common.FileUtil;
import org.tensorflow.lite.support.common.TensorOperator;
import org.tensorflow.lite.support.common.TensorProcessor;
import org.tensorflow.lite.support.common.ops.NormalizeOp;
import org.tensorflow.lite.support.image.ImageProcessor;
import org.tensorflow.lite.support.image.TensorImage;
import org.tensorflow.lite.support.image.ops.ResizeOp;
import org.tensorflow.lite.support.image.ops.ResizeWithCropOrPadOp;
import org.tensorflow.lite.support.label.TensorLabel;
import org.tensorflow.lite.support.tensorbuffer.TensorBuffer;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import ru.samsung.case2022.R;
import ru.samsung.case2022.StateViewModel;
import ru.samsung.case2022.ui.main.Classifiers;

public class PhotoViewerActivity extends AppCompatActivity {

    // for check
    private static final int NORM = 1;
    private static final int REQUEST_TAKE_PHOTO = 1;

    // image
    private ImageView iv;
    private StateViewModel mViewModel;
    // Button
    private Button bt;
    //ml
    Button buclassify;
    TextView classitext;
    protected Interpreter tflite;
    private MappedByteBuffer tfliteModel;
    private TensorImage inputImageBuffer;
    private int imageSizeX;
    private int imageSizeY;
    private TensorBuffer outputProbabilityBuffer;
    private TensorProcessor probabilityProcessor;
    private static final float IMAGE_MEAN = 0.0f;
    private static final float IMAGE_STD = 1.0f;
    private static final float PROBABILITY_MEAN = 0.0f;
    private static final float PROBABILITY_STD = 255.0f;
    private Classifiers recognition = new Classifiers();
    private Bitmap bitmap;
    private List<String> labels;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_viewer);
        setPhoto();
        buclassify = findViewById(R.id.recognize);
        classitext = findViewById(R.id.classify);
        bt = findViewById(R.id.cancel);

        bt.setOnClickListener(v -> {
            cancelPhoto();
        });
        buclassify.setOnClickListener(view -> {
            //iv.setImageDrawable(getResources().getDrawable(R.drawable.photo1)); for check
            //iv.setImageBitmap(getPhoto()); for check
            recognizePhoto();
        });
    }


    // after doing photo
    /*
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        iv = (ImageView) findViewById(R.id.preview);
        if (requestCode == NORM){Bundle extras = data.getExtras();
            Bitmap thumbnailBitmap = (Bitmap) extras.get("data");
            iv.setImageBitmap(thumbnailBitmap);
            iv.setDrawingCacheEnabled(true);
            bitmap = iv.getDrawingCache();
            MediaStore.Images.Media.insertImage(this.getContentResolver(), bitmap, "a", "h");
        }
    }
    */

    public void setPhoto() {
        Bundle extras = getIntent().getExtras();
        byte[] byteArray = extras.getByteArray("picture");
        Bitmap bmp = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
        iv = (ImageView) findViewById(R.id.preview);
        iv.setImageBitmap(bmp);
        bitmap = bmp;
    }

    public Bitmap getPhoto() {
        ImageView iv = (ImageView) findViewById(R.id.preview);
        iv.buildDrawingCache();
        //Bitmap bitmap = iv1.getDrawingCache();
        BitmapDrawable drawable = (BitmapDrawable) iv.getDrawable();
        Bitmap bitmap = drawable.getBitmap();

        Bitmap bt = Bitmap.createBitmap(bitmap, 0, (int) (bitmap.getHeight() * 0.35), (int) (bitmap.getWidth() * 0.926), (int) (bitmap.getHeight() * 0.156));
        return bt;
    }

    public void cancelPhoto() {
        // delete photo
        iv.setImageDrawable(null);
        Intent intent = new Intent(PhotoViewerActivity.this, RootActivity.class);
        startActivity(intent);
    }

    public void recognizePhoto() {
        bitmap = getPhoto();
        recognition.recognize(this);
        recognition.classifyImage(bitmap);
        recognition.deleteProduct();
        mViewModel.setStateUpdateLiveData("Update");
        finish();
    }

    private TensorImage loadImage(final Bitmap bitmap) {
        // Loads bitmap into a TensorImage.
        inputImageBuffer.load(bitmap);

        // Creates processor for the TensorImage.
        int cropSize = Math.min(bitmap.getWidth(), bitmap.getHeight());
        // TODO(b/143564309): Fuse ops inside ImageProcessor.
        ImageProcessor imageProcessor =
                new ImageProcessor.Builder()
                        .add(new ResizeWithCropOrPadOp(cropSize, cropSize))
                        .add(new ResizeOp(imageSizeX, imageSizeY, ResizeOp.ResizeMethod.NEAREST_NEIGHBOR))
                        .add(getPreprocessNormalizeOp())
                        .build();
        return imageProcessor.process(inputImageBuffer);
    }

    private MappedByteBuffer loadmodelfile(Activity activity) throws IOException {
        AssetFileDescriptor fileDescriptor = activity.getAssets().openFd("imageClassifier.tflite");
        FileInputStream inputStream = new FileInputStream(fileDescriptor.getFileDescriptor());
        FileChannel fileChannel = inputStream.getChannel();
        long startoffset = fileDescriptor.getStartOffset();
        long declaredLength = fileDescriptor.getDeclaredLength();
        return fileChannel.map(FileChannel.MapMode.READ_ONLY, startoffset, declaredLength);
    }

    private TensorOperator getPreprocessNormalizeOp() {
        return new NormalizeOp(IMAGE_MEAN, IMAGE_STD);
    }

    private TensorOperator getPostprocessNormalizeOp() {
        return new NormalizeOp(PROBABILITY_MEAN, PROBABILITY_STD);
    }

    private void showresult() {

        try {
            labels = FileUtil.loadLabels(this, "newdict.txt");
        } catch (Exception e) {
            e.printStackTrace();
        }
        Map<String, Float> labeledProbability =
                new TensorLabel(labels, probabilityProcessor.process(outputProbabilityBuffer))
                        .getMapWithFloatValue();
        float maxValueInMap = (Collections.max(labeledProbability.values()));

        for (Map.Entry<String, Float> entry : labeledProbability.entrySet()) {
            if (entry.getValue() == maxValueInMap) {
                classitext.setText(entry.getKey());
            }
        }
    }
}
