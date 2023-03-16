package ru.samsung.case2022.utils;

import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class VotingClassifier {
    int n;
    /*
    //public float[] summarize(List<List<Float>> array, ProductDeleteListener listener){
        public void summarize(List<HashMap<String, Float>> products, ProductDeleteListener listener){
            //HashMap<String, Float> products;
        // Map.Entry<> entry = products.get(0).entrySet();
        //int count = array.size();
        //int value = array.get(count-1).size();
        //float[] result = new float [value];
            // { getScore(): getScore() > 0.05f }
            // {"biscuits":0.5, "sourcream":0.25}, {"sourcream": 0.25, "milk": 0.5}, {}
            HashMap<String, Float> probs = new HashMap<>();
            String maxLabel = "";
            float maxScore = -1;
        for (int i = 0;i<3;i++){
            /*for (int j=0;j<count;j++){
                result[i] = result[i] + array.get(j).get(i);
            }
            for (var e : products.get(0).entrySet()) {
                if (products.get(1)!= null&& products.get(1).containsKey(e.getKey())) {

                    float tempScore  = e.getValue() + products.get(1).get(e.getKey());
                    if (maxScore < tempScore) {
                        maxLabel = e.getKey();
                        maxScore = tempScore;
                    }
                    Log.d("UNIQUE", ""+tempScore);
                    probs.put(e.getKey(), tempScore);
                } else {
                    float tempScore  = e.getValue();
                    if (maxScore < tempScore) {
                        maxLabel = e.getKey();
                        maxScore = tempScore;
                    }
                    Log.d("UNIQUE", ""+tempScore);
                    probs.put(e.getKey(), e.getValue());
                }
            }
        }
        String mostPopular= maxLabel;
        listener.requireDelete(mostPopular);
        //return result;
    }
    */
    public String summarizeFloats(ArrayList<HashMap<String, Float>> hash) {
        HashMap<String, Float> map1 = hash.get(0);
        HashMap<String, Float> map2 = hash.get(1);
        HashMap<String, Float> map3 = hash.get(2);
        HashMap<String, Float> newMap = new HashMap<>();
        String maxKey = " ";
        float maxValue = 0;

        // Summarize float values into newMap
        for(Map.Entry<String, Float> entry : map1.entrySet()) {
            String key = entry.getKey();
            Float value = entry.getValue();
            if(newMap.containsKey(key)) {
                newMap.put(key, newMap.get(key) + value);
            } else {
                newMap.put(key, value);
            }
        }

        for(Map.Entry<String, Float> entry : map2.entrySet()) {
            String key = entry.getKey();
            Float value = entry.getValue();
            if(newMap.containsKey(key)) {
                newMap.put(key, newMap.get(key) + value);
            } else {
                newMap.put(key, value);
            }
        }

        for(Map.Entry<String, Float> entry : map3.entrySet()) {
            String key = entry.getKey();
            Float value = entry.getValue();
            if(newMap.containsKey(key)) {
                newMap.put(key, newMap.get(key) + value);
            } else {
                newMap.put(key, value);
            }
        }

        // Find key with maximum value
        for(Map.Entry<String, Float> entry : newMap.entrySet()) {
            String key = entry.getKey();
            Float value = entry.getValue();
            if(value > maxValue) {
                maxKey = key;
                maxValue = value;
            }
        }
        Log.d("MAXKEYS",maxKey);
        return maxKey;
    }
    public VotingClassifier(int n) {
        this.n = n;
    }

}
