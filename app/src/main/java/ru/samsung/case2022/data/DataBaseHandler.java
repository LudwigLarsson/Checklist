package ru.samsung.case2022.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import ru.samsung.case2022.model.Products;
import ru.samsung.case2022.utils.Util;


public class DataBaseHandler extends SQLiteOpenHelper {

    public DataBaseHandler(Context context) {
        super(context, Util.DATABASE_NAME, null, Util.DATABASE_VERSION);
    }

    //Создание БД
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String CREATE_PRODUCTS_TABLE = "CREATE TABLE " + Util.TABLE_NAME + " ("
                + Util.KEY_ID + " INTEGER PRIMARY KEY, "
                + Util.KEY_NAME + " TEXT, "
                + Util.KEY_CATEGORY + " TEXT" + " );";

        sqLiteDatabase.execSQL(CREATE_PRODUCTS_TABLE);
    }

    //Без понятия, но звучит классно
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + Util.TABLE_NAME + ";");
        onCreate(sqLiteDatabase);
    }
    //Удаление всех данных
    public void deleteAll(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(Util.TABLE_NAME, null, null);
        db.close();
    }

    //Добавление продукта
    public void addProd(Products products) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(Util.KEY_NAME, products.getName());
        contentValues.put(Util.KEY_CATEGORY, products.getCategory());

        db.insert(Util.TABLE_NAME, null, contentValues);
        db.close();
    }

    //Возвращает продукт нам
    public Products getProd(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(Util.TABLE_NAME, new String[]{Util.KEY_ID, Util.KEY_NAME, Util.KEY_CATEGORY},
                Util.KEY_ID + "=?", new String[]{String.valueOf(id)}, null, null, null, null);
        /*
        if (cursor != null){
            cursor.moveToFirst();
        }
        */
        while (cursor != null && cursor.moveToNext()) {
            Products products = new Products(Integer.parseInt(cursor.getString(0)),
                    cursor.getString(1), cursor.getString(2));
            cursor.close();
            return products;
        }
        if (cursor != null) {
            cursor.close();
        }
        return null;
    }

    //Возвращает все продукты
    public List<Products> getAllProd() {

        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<Products> prodList = new ArrayList<>();
        String selectAllProd = "Select * from " + Util.TABLE_NAME + ";";
        Cursor cursor = db.rawQuery(selectAllProd, null);
        if (cursor.moveToFirst()) {

            do {
                Products products = new Products();
                products.setId(Integer.parseInt(cursor.getString(0)));
                products.setName(cursor.getString(1));
                products.setCategory(cursor.getString(2));

                prodList.add(products);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return prodList;
    }

    //Обновляет информацию о продукте
    public int updateProd(Products products, long id) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Util.KEY_NAME, products.getName());
        contentValues.put(Util.KEY_CATEGORY, products.getCategory());

        return db.update(Util.TABLE_NAME, contentValues, Util.KEY_ID + "=?", new String[]{String.valueOf(id)});
    }

    //Удаляет продукт
    public void deleteProd(long id) {
        SQLiteDatabase db = this.getWritableDatabase();

        db.delete(Util.TABLE_NAME, Util.KEY_ID + "=?", new String[]{String.valueOf(id)});
        db.close();
    }

}
