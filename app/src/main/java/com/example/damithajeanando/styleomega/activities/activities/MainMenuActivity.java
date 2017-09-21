package com.example.damithajeanando.styleomega.activities.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.damithajeanando.styleomega.R;
import com.example.damithajeanando.styleomega.activities.adapters.ProductAdapter;
import com.example.damithajeanando.styleomega.activities.database.dbHelper;
import com.example.damithajeanando.styleomega.activities.model.Product;

import java.util.ArrayList;
import java.util.List;

public class MainMenuActivity extends AppCompatActivity {


    ListView listAllProducts;
    List<Product> products;
    dbHelper db;
    ProductAdapter customProductAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        listAllProducts = (ListView) findViewById(R.id.listAllProducts);
        products = new ArrayList<>();
        db = new dbHelper(this);

        listPopulate();
    }

    private void listPopulate() {

        products = db.getAllProducts();

        customProductAdapter = new ProductAdapter(this, products);
        listAllProducts.setAdapter(customProductAdapter);
        listAllProducts.setClickable(true);

        listAllProducts.setOnItemClickListener(new AdapterView.OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id){
                Product pr = (Product)listAllProducts.getItemAtPosition(position);
                String pname = pr.getName();
                Intent in = new Intent(MainMenuActivity.this, DisplayProductActivity.class);
                in.putExtra("name", pname);
                startActivity(in);

            }

        });
    }
}
