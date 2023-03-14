package ru.samsung.case2022.ui.main;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.camera.core.ImageProxy;

import org.tensorflow.lite.task.vision.classifier.Classifications;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import ru.samsung.case2022.data.DataBaseHandler;
import ru.samsung.case2022.model.Products;
import ru.samsung.case2022.ui.EditProductActivity;
import ru.samsung.case2022.utils.ImageClassifierHelper;
import ru.samsung.case2022.utils.ProductDeleteListener;
import ru.samsung.case2022.utils.Util;
import ru.samsung.case2022.utils.VotingClassifier;

public class Classifiers {
    private ImageClassifierHelper imageClassifierHelper1;
    private ImageClassifierHelper imageClassifierHelper2;
    private ImageClassifierHelper imageClassifierHelper3;
    private String label = "";

    private ProductDeleteListener productDeleteListener;

    private volatile int counter;
    private final VotingClassifier votingClassifier = new VotingClassifier(3);
    public ArrayList<HashMap<String, Float>> arrayList1 = new ArrayList<>();

    public void recognize(Context context){
        arrayList1.add(new HashMap<>());
        arrayList1.add(new HashMap<>());
        arrayList1.add(new HashMap<>());
        productDeleteListener = productLabel -> {
            Log.d("UNIQUE DELETE", productLabel);
            DataBaseHandler dataBaseHandler = new DataBaseHandler(context);
            dataBaseHandler.deleteProduct(productLabel);
        };
        ImageClassifierHelper.ClassifierListener classifierListener1 = new ImageClassifierHelper.ClassifierListener() {
            @Override
            public void onError(String error) {

            }
            @Override
            public void onResults(List<Classifications> result, long inferenceTime) {
                setter(counter);
                //classificationResultsAdapter.updateResults(result.get(0).getCategories());
                //fragmentCameraBinding.bottomSheetLayout.inferenceTimeVal
                //.setText(String.format(Locale.US, "%d ms", inferenceTime));
                if (result.get(0).getCategories().size()>0){
                    Log.d("UNIQUE1",result.get(0).getCategories().get(0).getLabel() + " ");
                    HashMap<String, Float> pan = new HashMap<>();
                    for (int i = 0; i<result.get(0).getCategories().size(); i++){
                        pan.put(result.get(0).getCategories().get(i).getLabel(),result.get(0).getCategories().get(i).getScore() );
                    }
                    set(0,pan);//null(3)
                }
                checkReadyToVoteAndSummarize();
            }
        };
        ImageClassifierHelper.ClassifierListener classifierListener2 = new ImageClassifierHelper.ClassifierListener() {
            @Override
            public void onError(String error) {

            }
            @Override
            public void onResults(List<Classifications> result, long inferenceTime) {
                setter(counter);
                if (result.get(0).getCategories().size()>0){
                    Log.d("UNIQUE2",result.get(0).getCategories().get(0).getLabel() + " ");
                    HashMap<String, Float> pan = new HashMap<>();
                    for (int i = 0; i<result.get(0).getCategories().size(); i++){
                        pan.put(result.get(0).getCategories().get(i).getLabel(),result.get(0).getCategories().get(i).getScore() );
                    }
                    set(1,pan);//null(3)
                }
                checkReadyToVoteAndSummarize();
            }
        };
        ImageClassifierHelper.ClassifierListener classifierListener3 = new ImageClassifierHelper.ClassifierListener() {
            @Override
            public void onError(String error) {

            }
            @Override
            public void onResults(List<Classifications> result, long inferenceTime) {
                setter(counter);
                if (result.get(0).getCategories().size()>0){
                    Log.d("UNIQUE3",result.get(0).getCategories().get(0).getLabel() + " ");
                    HashMap<String, Float> pan = new HashMap<>();
                    for (int i = 0; i<result.get(0).getCategories().size(); i++){
                        pan.put(result.get(0).getCategories().get(i).getLabel(),result.get(0).getCategories().get(i).getScore() );
                    }
                    set(2,pan);//null(3)
                }
                checkReadyToVoteAndSummarize();
            }
        };


        Log.d("UNIQUE","ImageClassifier was created");
        imageClassifierHelper1 = ImageClassifierHelper.create(context, classifierListener1, 0);
        imageClassifierHelper2 = ImageClassifierHelper.create(context, classifierListener2,1);
        imageClassifierHelper3 = ImageClassifierHelper.create(context, classifierListener3,2);
        //imageClassifierHelper1.setCurrentModel(0);
        //imageClassifierHelper2.setCurrentModel(1);
        //imageClassifierHelper3.setCurrentModel(2);
        imageClassifierHelper1.setThreshold(0.05f);
        imageClassifierHelper2.setThreshold(0.05f);
        imageClassifierHelper3.setThreshold(0.05f);
    }
    public void classifyImage(@NonNull Bitmap bitmapBuffer) {
        // Copy out RGB bits to the shared bitmap buffer
        //bitmapBuffer.copyPixelsFromBuffer(image.getPlanes()[0].getBuffer());

        //todo Определить, сколько градусов должен передаваться в переменную imageRotation
        //int imageRotation = image.getImageInfo().getRotationDegrees();
        //image.close();
        imageClassifierHelper1.classify(bitmapBuffer, 0);
        imageClassifierHelper2.classify(bitmapBuffer, 0);
        imageClassifierHelper3.classify(bitmapBuffer, 0);
    }
    public void set(int index, HashMap<String, Float> array){
        arrayList1.set(index,array);
    }
    public void setter(int counter){
        this.counter++;
    }
    public void checkReadyToVoteAndSummarize(){
        if (this.counter==3){
            this.counter = 0;
            label = votingClassifier.summarizeFloats(arrayList1);
        }
    }

    public boolean deleteProduct() {
        if (productDeleteListener != null) {
            productDeleteListener.requireDelete(label);
            return true;
        }
        return false;
    }
}

