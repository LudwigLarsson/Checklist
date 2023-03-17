package ru.samsung.case2022.ui;

import java.util.HashMap;
import java.util.Map;

public class RussianDictionary {
    public String putWord(String englishName) {
        Map<String,String> dictionary = new HashMap<String,String>(10);
        dictionary.put("biscuits1", "печенье");
        dictionary.put("broccoli1", "брокколи");
        dictionary.put("cheese1", "сыр");
        dictionary.put("coffee1", "кофе");
        dictionary.put("curd1", "творог");
        dictionary.put("dough1", "тесто");
        dictionary.put("milk1", "молоко");
        dictionary.put("pancakes1", "блинчики");
        dictionary.put("sourcream1", "сметана");
        dictionary.put("tea1", "чай");

        return dictionary.get(englishName);
    }
}
