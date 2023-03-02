package ru.samsung.case2022.model;

public class Products {
    private int id;
    private String name;
    private String category;

    private int count;

    //START Добавляем конструкторы в класс
    public Products() {
    }
    public Products(String name, int count){
        this.name = name;
        this.count = count;
    }
    public Products(int id, String name, String category, int count) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.count = count;
    }
    public Products(String name, String category, int count) {
        this.name = name;
        this.category = category;
        this.count = count;
    }
    //END Добавляем конструкторы в класс

    //START Добавляем геттеры и сеттеры
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }

    public int getCount(){return count;}

    public void setCount(int count){this.count = count;}
    public void setName(String name) {
        this.name = name;
    }
    public String getCategory() {
        return category;
    }
    public void setCategory(String category) {
        this.category = category;
    }
    //END Добавляем геттеры и сеттеры
}
