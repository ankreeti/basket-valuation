package com.sgn.util;

import com.sgn.model.BasketItem;
import com.sgn.model.Product;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Utility class to read data from file and convert into Product and Basket Item Objects
 */
public class CommonUtil {

    /**
     * Convert a comma separated list of Name, Price into Product object
     * @param productPrices
     * @return List<Product>
     */
    public static List<Product> loadProducts(List<String> productPrices){
        List<Product> products = new ArrayList<>();
        productPrices.stream()
                .forEach(item -> {
                    String[] items = item.split(",");
                    String productName = items[0];
                    BigDecimal price = new BigDecimal(items[1]);
                    products.add(new Product(productName, price));
                });

        return products;
    }

    /**
     * Convert a comma separated list of Product Name, Quantity in to BasketItem object.
     * @param fileItems
     * @param products
     * @return List<BasketItem>
     */
    public static List<BasketItem> loadBasket(List<String> fileItems, List<Product> products){

        List<BasketItem> items = new ArrayList<>();
        fileItems.stream()
                .forEach(item -> {
                    String[] basketItems = item.split(",");
                    String productName = basketItems[0];
                    try {
                        int quantity = Integer.parseInt(basketItems[1]);
                        items.add(new BasketItem(findProduct(products, productName), quantity));
                    } catch (NumberFormatException e) {
                        System.out.println("Price is not valid for product = " + productName);
                    }
                });

        return items;
    }

    /**
     * Method to find product in a list of products.
     * @param products
     * @param product
     * @return Product
     */
    public static Product findProduct(List<Product> products, String product){
        for (Product product1 : products) {
            if(product1.getName().equals(product)){
                return product1;
            }
        }
        return null;
    }
}
