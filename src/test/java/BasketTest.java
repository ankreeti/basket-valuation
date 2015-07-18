import com.sgn.model.BasketItem;
import com.sgn.model.Product;
import com.sgn.util.CommonUtil;
import com.sgn.util.FileUtils;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.List;

import static com.sgn.util.CommonUtil.findProduct;
import static org.junit.Assert.assertEquals;

/**
 * Unit tests to validate basket items and its value.
 */
public class BasketTest {

    private Basket basket;

    private List<Product> products;

    private List<BasketItem> items;

    @Before
    public void setUp() {
        basket = new Basket();
        products = CommonUtil.loadProducts(FileUtils.readFile(getClass().getResourceAsStream("PriceFeed.txt")));
        items = CommonUtil.loadBasket(FileUtils.readFile(getClass().getResourceAsStream("BasketItems")), products);
        basket.setItems(items);
    }

    @Test
    public void computeBasketPrice() {
        //Given
        double expected = 76.009;

        //when
        double actual = basket.compute();

        //Then
        assertEquals("Computation do not match ", expected, actual, 0.001);
    }

    @Test
    public void computeForEmptyBasket() {
        //Given
        double expected = 0.0;

        //when
        items.clear();
        double actual = basket.compute();

        //Then
        assertEquals("Basket should be empty ", expected, actual, 0.0);

    }

    @Test
    public void computeWhenPriceOfAllItemsIsNull() {
        //Given
        double expected = 0.0;

        //when
        for (Product product : products) {
            product.setPrice(null);
        }
        double actual = basket.compute();

        //Then
        assertEquals("Basket should be empty ", expected, actual, 0.0);

    }

    @Test
    public void computeWhenPriceOfSomeItemsIsNull() {
        //Given
        double expected = 48.0;
        //Set price  of apples and peaches to null
        Product apple = findProduct(products, "Apple");
        Product peaches = findProduct(products, "Peaches");
        apple.setPrice(null);
        peaches.setPrice(null);

        //when
        double actual = basket.compute();

        //Then
        assertEquals("Basket value should ignore Apple and peaches ", expected, actual, 0.3);
    }

    @Test
    public void computeWhenPriceIsLessThanZero() {
        //Given
        double expected = 48.0;
        //Set price  of apples and peaches LESS THAN ZERO
        Product apple = findProduct(products, "Apple");
        Product peaches = findProduct(products, "Peaches");
        apple.setPrice(new BigDecimal("-12.0"));
        peaches.setPrice(new BigDecimal("-122.0"));

        //when
        double actual = basket.compute();

        //Then
        assertEquals("Basket value should ignore Apple and peaches ", expected, actual, 0.3);
    }

    @Test
    public void computeWhenQuantityIsLessThanZero() {
        //Given
        double expected = 48.0;

        //Set Quantity of apples =0 and peaches LESS THAN ZERO
        Product apple = findProduct(products, "Apple");
        Product peaches = findProduct(products, "Peaches");

        for (BasketItem item : items) {
            if (item.getProduct().equals(apple)) {
                item.setQuantity(0);
            }
            if (item.getProduct().equals(peaches)) {
                item.setQuantity(-90);
            }
        }

        //when
        double actual = basket.compute();

        //Then
        assertEquals("Basket value should ignore Apple and peaches ", expected, actual, 0.3);
    }

}
