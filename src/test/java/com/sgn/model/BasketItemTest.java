package com.sgn.model;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.math.BigDecimal;

/**
 * Basket Items instantiation validation
 */
public class BasketItemTest {
    @Rule
    public  ExpectedException exception = ExpectedException.none();

    @Test
    public void createBasketItemWithNullProduct(){
        exception.expect(IllegalStateException.class);
        exception.expectMessage("Cannot add a product which is not in inventory");
        new BasketItem(null, 12);
    }

    @Test
    public void createBasketItemWithNegativeQuantity(){
        exception.expect(IllegalStateException.class);
        exception.expectMessage("Quantity of your product should be greater than zero");
        new BasketItem(new Product("Mango", new BigDecimal("100.00")), -12);
    }
}
