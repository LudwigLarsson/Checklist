package ru.samsung.case2022.utils;

import java.util.HashMap;
import java.util.Map;

public class RussianDictionary {
    public String putWord(String englishName) {
        Map<String,String> dictionary = new HashMap<String,String>(10);
        dictionary.put("biscuits1", "Печенье");
        dictionary.put("broccoli1", "Брокколи");
        dictionary.put("cheese1", "Сыр");
        dictionary.put("coffee1", "Кофе");
        dictionary.put("curd1", "Творог");
        dictionary.put("dough1", "Тесто");
        dictionary.put("milk1", "Иолоко");
        dictionary.put("pancakes1", "Блинчики");
        dictionary.put("sourcream1", "Сметана");
        dictionary.put("tea1", "Чай");

        return dictionary.get(englishName);
    }
}
