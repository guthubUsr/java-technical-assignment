package kata.supermarket.product;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
import java.util.stream.Stream;
import kata.supermarket.basket.Item;
import kata.supermarket.product.ProductByWeight;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class ProductByWeightByUnitTest {

  private static Long WEIGHED_PRODUCT_ID = 888L;

  static Stream<Arguments> itemFromWeighedProductHasExpectedUnitPrice() {
    return Stream.of(
        Arguments.of("100.00", "1.00", "100.00"),
        Arguments.of("100.00", "0.33333", "33.33"),
        Arguments.of("100.00", "0.33335", "33.34"),
        Arguments.of("100.00", "0", "0.00")
    );
  }

  @ParameterizedTest
  @MethodSource
  void itemFromWeighedProductHasExpectedUnitPrice(String pricePerKilo, String weightInKilos,
      String expectedPrice) {
    final ProductByWeight productByWeight = new ProductByWeight(WEIGHED_PRODUCT_ID,
        new BigDecimal(pricePerKilo));
    final Item weighedItem = productByWeight.weighing(new BigDecimal(weightInKilos));
    assertEquals(new BigDecimal(expectedPrice), weighedItem.price());
  }

}