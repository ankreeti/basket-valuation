package com.sgn.model;

import java.math.BigDecimal;

/**
 * A class holding product name and price.
 *
 */
public class Product {

    private String name;

    private BigDecimal price;

    public Product(String name, BigDecimal price) {

        if(name == null || name.trim().equals("")){
            throw new IllegalStateException("Please specify name for your product");
        }
        if(null == price || price.doubleValue() < 0){
            throw new IllegalStateException("Price of product should be greater than zero");
        }

        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
