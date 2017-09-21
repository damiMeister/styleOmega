package com.example.damithajeanando.styleomega.activities.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.damithajeanando.styleomega.R;
import com.example.damithajeanando.styleomega.activities.adapters.CartAdapter;
import com.example.damithajeanando.styleomega.activities.model.Cart;
import com.example.damithajeanando.styleomega.activities.database.dbHelper;

import java.util.ArrayList;
import java.util.List;

public class AddToCartActivity extends AppCompatActivity implements View.OnClickListener {

    ListView viewCartList;
    TextView viewTotal;
    Button checkout;
    Cart cart;
    List<Cart> cartList;
    dbHelper db;
    CartAdapter cartAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_to_cart);

        viewCartList = (ListView) findViewById(R.id.cartlist);
        viewTotal = (TextView) findViewById(R.id.viewtotal);
        checkout = (Button) findViewById(R.id.checkout);
        db = new dbHelper(this);
        cartList = new ArrayList<>();
        cartList = db.getCartProducts();
        cart = new Cart();

        listPopulate();
        totalDisplay();
    }

    public void totalDisplay(){

        int total = 0;
        int price = 0;
        for (int b = 0; b < cartList.size(); b++){
            cart = cartList.get(b);
            price = Integer.parseInt(cart.getCartPrice());
            total = total + price;
        }

        viewTotal.setText(Integer.toString(total));
    }

    public void listPopulate(){

        cartAdapter = new CartAdapter(this, cartList);
        viewCartList.setAdapter(cartAdapter);
        viewCartList.setClickable(true);

    }

    @Override
    public void onClick(View v){
        if(v.getId() == checkout.getId()){

        }
    }

}
