package com.example.damithajeanando.styleomega.activities.database;


import android.content.ClipData;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.damithajeanando.styleomega.activities.model.Cart;
import com.example.damithajeanando.styleomega.activities.model.Product;
import com.example.damithajeanando.styleomega.activities.model.User;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Damitha Jeanando on 9/18/2017.
 */

public class dbHelper extends SQLiteOpenHelper {


    private static  final int DB_VERSION = 10;
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

    private static final String TABLE_CART = "cart";
    private static final String COLUMN_CART_ID = "cartId";
    private static final String COLUMN_CART_NAME = "cartName";
    private static final String COLUMN_CART_PRICE = "cartPrice";
    private static final String COLUMN_CART_QTY = "cartQty";
    private static final String COLUMN_CART_IMG = "cartImg";


    private String CREATE_TABLE_USER = "CREATE TABLE " + TABLE_USER + "("
            + COLUMN_USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + COLUMN_USER_NAME + " TEXT,"
            + COLUMN_USER_EMAIL + " TEXT," + COLUMN_USER_PASSWORD + " TEXT" + ")";

    private String CREATE_TABLE_PRODUCT = "CREATE TABLE " + TABLE_PRODUCT + "("
            + COLUMN_PRODUCT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + COLUMN_PRODUCT_NAME + " TEXT," + COLUMN_PRODUCT_DESCRIPTION  + " TEXT," + COLUMN_PRODUCT_CATEGORY + " TEXT,"
            + COLUMN_PRODUCT_PRICE + " TEXT," + COLUMN_PRODUCT_QTY + " TEXT," + COLUMN_PRODUCT_IMG + " TEXT" + ")";

    private String CREATE_TABLE_CART = "CREATE TABLE " + TABLE_CART + "("
            + COLUMN_CART_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + COLUMN_CART_NAME + " TEXT," + COLUMN_CART_PRICE + " TEXT,"
            + COLUMN_CART_QTY + " TEXT," + COLUMN_CART_IMG + " TEXT" + ")";

    //Drop table sql query
    private String DROP_USER_TABLE = "DROP TABLE IF EXISTS " + TABLE_USER;
    private String DROP_PRODUCT_TABLE = "DROP TABLE IF EXISTS " + TABLE_PRODUCT;
    private String DROP_CART_TABLE = "DROP TABLE IF EXISTS " + TABLE_CART;

    List<Product> ProductList;
    List<Cart> CartList;
    User user;



    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_TABLE_PRODUCT);
        sqLiteDatabase.execSQL(CREATE_TABLE_USER);

    }

    public List<Cart> getCartProducts(){

        String[] columns = {
                COLUMN_CART_NAME,
                COLUMN_CART_ID,
                COLUMN_CART_QTY,
                COLUMN_CART_PRICE,
                COLUMN_CART_IMG
        };

        String sortOrder = COLUMN_CART_ID + " ASC";
        CartList = new ArrayList<Cart>();

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_CART, columns, null, null, null, null, sortOrder);

        if (cursor.moveToFirst()){
            do {
               Cart cart = new Cart();
                cart.setCartId(cursor.getInt(cursor.getColumnIndex(COLUMN_CART_ID)));
                cart.setCartName(cursor.getString(cursor.getColumnIndex(COLUMN_CART_NAME)));
                cart.setCartPrice(cursor.getString(cursor.getColumnIndex(COLUMN_CART_PRICE)));
                cart.setCartQty(cursor.getString(cursor.getColumnIndex(COLUMN_CART_QTY)));
                cart.setCartImg(cursor.getString(cursor.getColumnIndex(COLUMN_CART_IMG)));

                CartList.add(cart);
            }while (cursor.moveToNext());
        }
        cursor.close();
        db.close();

        return CartList;
    }

    public List<Product> getAllProducts(){
      String[] columns = {
                COLUMN_PRODUCT_NAME,
                COLUMN_PRODUCT_PRICE,
                COLUMN_PRODUCT_QTY,
                COLUMN_PRODUCT_CATEGORY,
                COLUMN_PRODUCT_IMG
      };

      String sortOrder = COLUMN_PRODUCT_NAME + " ASC";
        ProductList = new ArrayList<Product>();

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_PRODUCT, columns, null, null, null, null, sortOrder);

        if(cursor.moveToFirst()){
            do {
                Product product = new Product();
                product.setName(cursor.getString(cursor.getColumnIndex(COLUMN_PRODUCT_NAME)));
                product.setPrice(cursor.getString(cursor.getColumnIndex(COLUMN_PRODUCT_PRICE)));
                product.setQty(cursor.getString(cursor.getColumnIndex(COLUMN_PRODUCT_QTY)));
                product.setCategory(cursor.getString(cursor.getColumnIndex(COLUMN_PRODUCT_CATEGORY)));
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
        values.put(COLUMN_CART_NAME, product.getName());
        values.put(COLUMN_CART_PRICE, product.getPrice());
        values.put(COLUMN_CART_QTY, product.getQty());
        values.put(COLUMN_CART_IMG, product.getImg());

        db.insert(TABLE_CART, null, values);
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

    public List<Product> getCategory(String category, boolean l){


        if(l) {

            SQLiteDatabase db = getReadableDatabase();
            Product product = null;


            String selectItem = "SELECT * FROM " + TABLE_PRODUCT + " WHERE " + COLUMN_PRODUCT_CATEGORY + "='" + category + "'";

            ProductList = new ArrayList<>();

            Cursor c = db.rawQuery(selectItem, null);
            if (c.moveToFirst()) {
                do {
                    product = new Product();
                    product.setProductId(c.getInt(c.getColumnIndex(COLUMN_PRODUCT_ID)));
                    product.setName(c.getString(c.getColumnIndex(COLUMN_PRODUCT_NAME)));
                    product.setPrice(c.getString(c.getColumnIndex(COLUMN_PRODUCT_PRICE)));
                    product.setCategory(c.getString(c.getColumnIndex(COLUMN_PRODUCT_CATEGORY)));
                    product.setDescription(c.getString(c.getColumnIndex(COLUMN_PRODUCT_DESCRIPTION)));
                    product.setQty(c.getString(c.getColumnIndex(COLUMN_PRODUCT_QTY)));
                    product.setImage(c.getString(c.getColumnIndex(COLUMN_PRODUCT_IMG)));

                    ProductList.add(product);
                } while (c.moveToNext());

            }
            c.close();
            db.close();

            return ProductList;
        }
        return getAllProducts();
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

    /*public String userUpdate(String email, int q, String changer, String password){
        SQLiteDatabase db = this.getWritableDatabase();

        String userSelect = "SELECT * FROM " + TABLE_USER + " WHERE " + COLUMN_USER_EMAIL + "='" + email + "'";
        Cursor c = db.rawQuery(userSelect, null);
        if (c.moveToFirst()) {
            user = new User();
            user.setId(c.getInt(c.getColumnIndex(COLUMN_USER_ID)));
            user.setEmail(c.getString(c.getColumnIndex(COLUMN_USER_EMAIL)));
            user.setName(c.getString(c.getColumnIndex(COLUMN_USER_NAME)));
            user.setPassword(c.getString(c.getColumnIndex(COLUMN_USER_PASSWORD)));
        }
        ContentValues values = new ContentValues();
        if(q == 1){
            values.put(COLUMN_USER_NAME, changer);
            db.update(TABLE_USER, values, COLUMN_USER_ID + " ="+ user.getId(), null);
            return "Your name changed successfully";
        }
        if (q == 2){
            String t = user.getPassword();

            if(t.trim().equals(password)){
                values.put(COLUMN_USER_PASSWORD, changer);
                db.update(TABLE_USER, values, COLUMN_USER_ID + " =" + user.getId(), null);
                return "Your password has changed successfully.";
            }
            else{
                return "Password invalid!!";
            }

        }

        c.close();
        db.close();
        return "Error!";
    }*/

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
        //sqLiteDatabase.execSQL(CREATE_TABLE_USER);
        //sqLiteDatabase.execSQL(CREATE_TABLE_PRODUCT);
       // sqLiteDatabase.execSQL(CREATE_TABLE_CART);
       // onCreate(db);

    }
}
