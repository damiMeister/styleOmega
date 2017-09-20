package com.example.damithajeanando.styleomega.activities.model;

/**
 * Created by Damitha Jeanando on 9/19/2017.
 */

public class Product {

    private int productId;
    private String name;
    private String category;
    private String description;
    private String price;
    private String qty;
    private String img;

    public Product() {
        this.productId = productId;
        this.name = name;
        this.category = category;
        this.description = description;
        this.price = price;
        this.qty = qty;
        this.img = img;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getQty() {
        return qty;
    }

    public void setQty(String qty) {
        this.qty = qty;
    }

    public String getImg() {
        return img;
    }

    public void setImage(String img) {
        this.img = img;
    }
}
