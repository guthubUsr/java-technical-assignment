package kata.supermarket.basket;

import java.math.BigDecimal;
import kata.supermarket.product.Product;

public interface Item {

  BigDecimal price();

  void setPrice(BigDecimal newPrice);

  boolean isDiscounted();

  void setDiscounted();

  Product product();
}
