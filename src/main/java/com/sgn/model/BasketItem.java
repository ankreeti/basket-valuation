package com.sgn.model;

/**
 * Class representing an item in basket
 */
public class BasketItem {

    private Product product;

    private int quantity;

    public BasketItem(Product product, int quantity) {

        if(null == product){
            throw new IllegalStateException("Cannot add a product which is not in inventory");
        }

        if(quantity < 0){
            throw new IllegalStateException("Quantity of your product should be greater than zero");
        }
        this.product = product;
        this.quantity = quantity;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
