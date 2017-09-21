package com.example.damithajeanando.styleomega.activities.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.damithajeanando.styleomega.R;
import com.example.damithajeanando.styleomega.activities.activities.AddToCartActivity;
import com.example.damithajeanando.styleomega.activities.database.dbHelper;
import com.example.damithajeanando.styleomega.activities.model.Cart;

import java.util.List;

/**
 * Created by Damitha Jeanando on 9/20/2017.
 */

public class CartAdapter extends ArrayAdapter<Cart> implements View.OnClickListener {

    Context context;
    List<Cart> CartList = null;
    dbHelper db;
    Cart cart;

    public CartAdapter(Context context, List<Cart> CartList){
        super(context, R.layout.cart_list, CartList);
        this.context = context;
        this.CartList = CartList;
    }

    @Override
    public View getView(int position, final View convertView, ViewGroup parent){

        LayoutInflater ItemInflater = LayoutInflater.from(getContext());
        final View customView = ItemInflater.inflate(R.layout.cart_list, parent, false);

        cart = getItem(position);
        TextView cartname = (TextView) customView.findViewById(R.id.cartListName);
        TextView cartprice = (TextView) customView.findViewById(R.id.cartListPrice);
        ImageView cartimg = (ImageView) customView.findViewById(R.id.cartListImg);

        cartname.setText(cart.getCartName());
        cartprice.setText(cart.getCartPrice());

        int imgId = context.getResources().getIdentifier(cart.getCartImg(), "drawable", context.getPackageName());
        cartimg.setImageResource(imgId);

        return customView;
    }

    @Override
    public boolean isEnabled(int position){
        return true;
    }

    @Override
    public void onClick(View view) {

    }
}
