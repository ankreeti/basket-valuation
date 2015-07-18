package com.sgn.model;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.math.BigDecimal;

/**
 * Product instantiation validation
 */
public class ProductTest {

    private String name;

    private BigDecimal price;

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Before
    public void setUp(){
        name="Gherkin";
        price = new BigDecimal("1.23");
    }


    @Test
    public void createProductWithNullName(){
        exception.expect(IllegalStateException.class);
        exception.expectMessage("Please specify name for your product");

        new Product(null,price);
    }

    @Test
    public void createProductWithWhitespaceName(){
        exception.expect(IllegalStateException.class);
        exception.expectMessage("Please specify name for your product");

        new Product("    ",price);
    }

    @Test
    public void createProductWithoutPrice(){
        exception.expect(IllegalStateException.class);
        exception.expectMessage("Price of product should be greater than zero");

        new Product(name,null);
    }

    @Test
    public void createProductWithNegativePrice(){
        exception.expect(IllegalStateException.class);
        exception.expectMessage("Price of product should be greater than zero");

        new Product(name,new BigDecimal("-1222.987"));
    }
}
