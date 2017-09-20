package com.example.damithajeanando.styleomega.activities.database;


import android.content.ClipData;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.damithajeanando.styleomega.activities.model.Product;
import com.example.damithajeanando.styleomega.activities.model.User;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Damitha Jeanando on 9/18/2017.
 */

public class dbHelper extends SQLiteOpenHelper {


    private static  final int DB_VERSION = 6;
    private static  final String DB_NAME = "styleOmega.db";

    private static final String TABLE_PRODUCT = "product";
    private static final String COLUMN_PRODUCT_ID = "id";
    private static final String COLUMN_PRODUCT_CATEGORY = "category";
    private static final String COLUMN_PRODUCT_DESCRIPTION = "description";
    private static final String COLUMN_PRODUCT_NAME = "name";
    private static final String COLUMN_PRODUCT_PRICE = "price";
    private static final String COLUMN_PRODUCT_QTY = "qty";
    private static final String COLUMN_PRODUCT_IMG = "img";

    private static final String TABLE_USER = "user";
    private static final String COLUMN_USER_NAME = "name";
    private static final String COLUMN_USER_ID = "id";
    private static final String COLUMN_USER_EMAIL = "email";
    private static final String COLUMN_USER_PASSWORD = "password";

    private String CREATE_TABLE_USER = "CREATE TABLE " + TABLE_USER + "("
            + COLUMN_USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + COLUMN_USER_NAME + " TEXT,"
            + COLUMN_USER_EMAIL + " TEXT," + COLUMN_USER_PASSWORD + " TEXT" + ")";

    private String CREATE_TABLE_PRODUCT = "CREATE TABLE " + TABLE_PRODUCT + "("
            + COLUMN_PRODUCT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + COLUMN_PRODUCT_NAME + " TEXT," + COLUMN_PRODUCT_DESCRIPTION  + " TEXT," + COLUMN_PRODUCT_CATEGORY + " TEXT,"
            + COLUMN_PRODUCT_PRICE + " TEXT," + COLUMN_PRODUCT_QTY + " TEXT," + COLUMN_PRODUCT_IMG + " TEXT" + ")";

   /* private String CREATE_TABLE_CART = "CREATE TABLE " + TABLE_CART + "("
            + COLUMN_CART_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + COLUMN_CART_NAME + " TEXT," + COLUMN_CART_PRICE + " INTEGER,"
            + COLUMN_CART_QTY + " TEXT," + COLUMN_CART_IMG + " TEXT" + ")";*/
    //Drop table sql query
    private String DROP_USER_TABLE = "DROP TABLE IF EXISTS " + TABLE_USER;
    private String DROP_PRODUCT_TABLE = "DROP TABLE IF EXISTS " + TABLE_PRODUCT;

    List<Product> ProductList;



    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_TABLE_PRODUCT);
        sqLiteDatabase.execSQL(CREATE_TABLE_USER);
    }

    public List<Product> getAllProducts(){
      String[] columns = {
                COLUMN_PRODUCT_NAME,
                COLUMN_PRODUCT_PRICE,
                COLUMN_PRODUCT_QTY,
                COLUMN_PRODUCT_IMG
      };

      String sortOrder = COLUMN_PRODUCT_CATEGORY + " ASC";
        ProductList = new ArrayList<Product>();

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_PRODUCT, columns, null, null, null, null, sortOrder);

        if(cursor.moveToFirst()){
            do {
                Product product = new Product();
                product.setName(cursor.getString(cursor.getColumnIndex(COLUMN_PRODUCT_NAME)));
                product.setPrice(cursor.getString(cursor.getColumnIndex(COLUMN_PRODUCT_PRICE)));
                product.setImage(cursor.getString(cursor.getColumnIndex(COLUMN_PRODUCT_IMG)));
                ProductList.add(product);
            }
            while(cursor.moveToNext());
            }

        cursor.close();
        db.close();

        return ProductList;
        }

    public void addProduct(Product product){

        SQLiteDatabase db;

        db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_PRODUCT_NAME, product.getName());
        values.put(COLUMN_PRODUCT_PRICE, product.getPrice());
        values.put(COLUMN_PRODUCT_QTY, product.getQty());
        values.put(COLUMN_PRODUCT_IMG, product.getImg());

        db.insert(TABLE_PRODUCT, null, values);
        db.close();

    }

    public Product getProduct(String name){

        SQLiteDatabase db = getReadableDatabase();
        Product product = null;

        String selectProduct = "SELECT * FROM " + TABLE_PRODUCT + " WHERE " + COLUMN_PRODUCT_NAME + "='" + name + "'";

        Cursor cursor = db.rawQuery(selectProduct,null);
        if(cursor.moveToFirst()){
            product = new Product();
            product.setProductId(cursor.getInt(cursor.getColumnIndex(COLUMN_PRODUCT_ID)));
            product.setName(cursor.getString(cursor.getColumnIndex(COLUMN_PRODUCT_NAME)));
            product.setPrice(cursor.getString(cursor.getColumnIndex(COLUMN_PRODUCT_PRICE)));
            product.setDescription(cursor.getString(cursor.getColumnIndex(COLUMN_PRODUCT_DESCRIPTION)));
            product.setImage(cursor.getString(cursor.getColumnIndex(COLUMN_PRODUCT_IMG)));
        }

        return product;
    }

    public void userReg(User u){

        SQLiteDatabase db;
        db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_USER_NAME, u.getName());
        values.put(COLUMN_USER_EMAIL, u.getEmail());
        values.put(COLUMN_USER_PASSWORD, u.getPassword());

        db.insert(TABLE_USER, null, values);
        db.close();
    }

    public void userUpdate(User user){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(COLUMN_USER_NAME, user.getName());
        values.put(COLUMN_USER_PASSWORD, user.getPassword());

        db.update(TABLE_USER, values, COLUMN_USER_EMAIL + " =?", new String[]{String.valueOf(user.getId())});

        db.close();
    }

    public boolean userCheck(String email, String password){

        String[] columns = {
                COLUMN_USER_EMAIL
        };

        SQLiteDatabase db = this.getReadableDatabase();

        String selection = COLUMN_USER_EMAIL + " =?" + " AND " + COLUMN_USER_PASSWORD + " = ?";

        String [] selectionArgs = {email, password};

        Cursor cursor = db.query(TABLE_USER, columns, selection, selectionArgs, null, null, null);

        int cursorCount = cursor.getCount();

        cursor.close();
        db.close();
        if(cursorCount > 0){
            return true;
        }
        return false;
    }

    public boolean userCheck(String email){

        String[] columns = {
                COLUMN_USER_EMAIL
        };

        SQLiteDatabase db = this.getReadableDatabase();
        String selection = COLUMN_USER_EMAIL + " =?";

        String [] selectionArgs = {email};

        Cursor cursor = db.query(TABLE_USER, columns, selection, selectionArgs, null, null, null);

        int cursorCount = cursor.getCount();
        cursor.close();
        db.close();

        if (cursorCount > 0){
            return true;
        }

        return false;
    }


    public dbHelper(Context context){
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
       // db.execSQL(DROP_USER_TABLE);
       // db.execSQL(DROP_PRODUCT_TABLE);
        sqLiteDatabase.execSQL(CREATE_TABLE_USER);
        sqLiteDatabase.execSQL(CREATE_TABLE_PRODUCT);
       // onCreate(db);

    }
}
