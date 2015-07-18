import com.sgn.model.BasketItem;
import com.sgn.util.CommonUtil;
import com.sgn.util.FileUtils;

import java.util.List;
import java.util.function.Predicate;

/**
 * Basket Valuation class.
 */
public class Basket {

    private List<BasketItem> items;

    public static void main(String... args) {

        if (args.length < 2) {
            System.out.println("Need path to products and basket items file. Please refer to readme.txt on how to create these files.");
            System.out.println("Usage: <Path to Products file>  <Path to Basket items>");
            System.exit(0);
        }

        Basket basket = new Basket();
        List<BasketItem> items = basket.createBasketItemsFromFiles(args[0], args[1]);
        basket.setItems(items);

        basket.compute();

    }

    public List<BasketItem> createBasketItemsFromFiles(String priceFeed, String basketFile) {
        List<String> products = FileUtils.readFile(priceFeed);
        List<String> basketItems = FileUtils.readFile(basketFile);

        return CommonUtil.loadBasket(basketItems, CommonUtil.loadProducts(products));
    }

    public double compute() {

        printProductDetails();

        double basketValue = this.getItems().stream()
                .filter(validateProduct().and(validateQuantity()))
                .map(item -> item.getQuantity() * item.getProduct().getPrice().doubleValue())
                .reduce(0.0, (d1, d2) -> d1 + d2);

        System.out.println("Total value of basket = " + basketValue);

        return basketValue;
    }

    private void printProductDetails() {
        this.getItems().stream()
                .filter(validateProduct().and(validateQuantity()))
                .forEach(item -> System.out.println(String.format("Product=%s, Price=%s, Quantity=%s, Cost=%s",
                        item.getProduct().getName(),
                        item.getProduct().getPrice(),
                        item.getQuantity(),
                        item.getProduct().getPrice().doubleValue() * item.getQuantity())));
    }

    private Predicate<BasketItem> validateProduct() {
        Predicate<BasketItem> validProduct = item -> null != item.getProduct()
                && null != item.getProduct().getName()
                && !item.getProduct().getName().trim().equals("")
                && null != item.getProduct().getPrice()
                && item.getProduct().getPrice().doubleValue() > 0;
        return validProduct;
    }

    private Predicate<BasketItem> validateQuantity() {
        Predicate<BasketItem> validQuantity = item -> item.getQuantity() > 0;

        return validQuantity;
    }

    public List<BasketItem> getItems() {
        return items;
    }

    public void setItems(List<BasketItem> items) {
        this.items = items;
    }
}
