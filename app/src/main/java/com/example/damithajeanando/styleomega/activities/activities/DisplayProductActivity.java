package com.example.damithajeanando.styleomega.activities.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.damithajeanando.styleomega.R;
import com.example.damithajeanando.styleomega.activities.model.Product;
import com.example.damithajeanando.styleomega.activities.database.dbHelper;

/**
 * Created by Damitha Jeanando on 9/19/2017.
 */

public class DisplayProductActivity extends AppCompatActivity implements View.OnClickListener {

    TextView viewName;
    TextView viewDetails;
    TextView viewPrice;
    TextView viewQty;
    ImageView viewProductImg;
    ImageButton addCart;
    Product product;
    dbHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_display);

        db = new dbHelper(this);
        viewName = (TextView) findViewById(R.id.viewname);
        viewDetails = (TextView) findViewById(R.id.viewdetails);
        viewPrice = (TextView) findViewById(R.id.viewprice);
        viewQty = (TextView) findViewById(R.id.viewqty);
        viewProductImg = (ImageView) findViewById(R.id.viewimg);
        addCart = (ImageButton) findViewById(R.id.addtocart);

        addCart.setOnClickListener(this);
        Intent intent = getIntent();

        String name = intent.getStringExtra("name");
        product = db.getProduct(name);

        viewName.setText(product.getName());
        viewDetails.setText(product.getDescription());
        viewPrice.setText(product.getPrice());
        viewQty.setText(product.getQty());
        viewProductImg.setImageResource(this.getResources().getIdentifier(product.getImg(), "drawable", this.getPackageName()));
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == addCart.getId()){

            db.addProduct(product);
            Toast.makeText(getApplicationContext(), "Product added to the cart successfully!", Toast.LENGTH_SHORT).show();

        }
    }
    }

