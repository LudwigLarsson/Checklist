package ru.samsung.case2022.model;

public class Products {
    private int id;
    private String name;
    private String category;

    //START Добавляем конструкторы в класс
    public Products() {
    }
    public Products(int id, String name, String category) {
        this.id = id;
        this.name = name;
        this.category = category;
    }
    public Products(String name, String category) {
        this.name = name;
        this.category = category;
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
