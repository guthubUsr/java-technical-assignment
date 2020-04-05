package kata.supermarket.product;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
import kata.supermarket.product.ProductByUnit;
import org.junit.jupiter.api.Test;

class ProductByUnitTest {

  private static Long PRODUCT_ID = 888L;

  @Test
  void singleItemHasExpectedUnitPriceFromProduct() {
    final BigDecimal price = new BigDecimal("2.49");
    assertEquals(price, new ProductByUnit(PRODUCT_ID, price).oneOf().price());
  }
}