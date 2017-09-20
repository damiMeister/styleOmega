package com.example.damithajeanando.styleomega.activities.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.damithajeanando.styleomega.R;
import com.example.damithajeanando.styleomega.activities.activities.MainMenuActivity;
import com.example.damithajeanando.styleomega.activities.model.Product;


import java.util.List;

/**
 * Created by Damitha Jeanando on 9/19/2017.
 */

public class ProductAdapter extends ArrayAdapter<Product> implements View.OnClickListener {

    Context context;
    List<Product> productList = null;


    public ProductAdapter(Context context, List<Product> productList) {
        super(context, R.layout.custom_list, productList);
        this.context = context;
        this.productList = productList;
    }

    @Override
    public View getView(int position, final View convertView, ViewGroup parent){

        LayoutInflater productInflater = LayoutInflater.from(getContext());
        final View customView = productInflater.inflate(R.layout.custom_list, parent, false);

        Product product = getItem(position);
        TextView productName = (TextView) customView.findViewById(R.id.productName);
        TextView productPrice = (TextView) customView.findViewById(R.id.productPrice);
        ImageView productImage = (ImageView) customView.findViewById(R.id.productImg);

        productName.setText(product.getName());
        productPrice.setText(product.getPrice());

        int imgId = context.getResources().getIdentifier(product.getImg(), "drawable", context.getPackageName());
        productImage.setImageResource(imgId);

        return customView;
    }


    @Override
    public void onClick(View view) {

    }
}
