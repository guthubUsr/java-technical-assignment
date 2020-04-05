package kata.supermarket.basket;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;
import kata.supermarket.discount.BuyOneGetOneFree;
import kata.supermarket.product.ProductByUnit;
import kata.supermarket.product.ProductByWeight;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class BasketTest {

  private static Long MILK_ID = 1000L;
  private static Long DIGESTIVE_ID = 1001L;
  private static Long AMERICAN_SWEETS_ID = 1001L;
  private static Long PICK_AND_MIX_ID = 1001L;

  static Stream<Arguments> basketProvidesTotalValue() {
    return Stream.of(
        noItems(),
        aSingleItemPricedPerUnit(),
        multipleItemsPricedPerUnit(),
        aSingleItemPricedByWeight(),
        multipleItemsPricedByWeight(),
        buyOneGetOneFreeByUnit(),
        buyThreeGetOneFreeByUnit(),
        buyFourGetTwoFreeByUnit()
    );
  }

  private static Arguments aSingleItemPricedByWeight() {
    return Arguments.of("a single weighed item", "1.25",
        Collections.singleton(twoFiftyGramsOfAmericanSweets()));
  }

  private static Arguments multipleItemsPricedByWeight() {
    return Arguments.of("multiple weighed items", "1.85",
        Arrays.asList(twoFiftyGramsOfAmericanSweets(), twoHundredGramsOfPickAndMix())
    );
  }

  private static Arguments multipleItemsPricedPerUnit() {
    return Arguments.of("multiple items priced per unit", "2.04",
        Arrays.asList(aPackOfDigestives(), aPintOfMilk()));
  }

  private static Arguments buyOneGetOneFreeByUnit() {
    return Arguments.of("buy 1 get 1 free items per unit", "1.55",
        Arrays.asList(aPackOfDigestives(), aPackOfDigestives()));
  }

  private static Arguments buyThreeGetOneFreeByUnit() {
    return Arguments.of("buy 3 get 1 free items per unit", "3.10",
        Arrays.asList(aPackOfDigestives(), aPackOfDigestives(), aPackOfDigestives()));
  }

  private static Arguments buyFourGetTwoFreeByUnit() {
    return Arguments.of("buy 4 get 2 free items per unit", "3.10",
        Arrays.asList(aPackOfDigestives(), aPackOfDigestives(), aPackOfDigestives()));
  }

  private static Arguments aSingleItemPricedPerUnit() {
    return Arguments
        .of("a single item priced per unit", "0.49", Collections.singleton(aPintOfMilk()));
  }

  private static Arguments noItems() {
    return Arguments.of("no items", "0.00", Collections.emptyList());
  }

  private static Item aPintOfMilk() {
    return new ProductByUnit(MILK_ID, new BigDecimal("0.49")).oneOf();
  }

  private static Item aPackOfDigestives() {
    return new ProductByUnit(DIGESTIVE_ID, new BigDecimal("1.55")).oneOf();
  }

  private static ProductByWeight aKiloOfAmericanSweets() {
    return new ProductByWeight(AMERICAN_SWEETS_ID, new BigDecimal("4.99"));
  }

  private static Item twoFiftyGramsOfAmericanSweets() {
    return aKiloOfAmericanSweets().weighing(new BigDecimal(".25"));
  }

  private static ProductByWeight aKiloOfPickAndMix() {
    return new ProductByWeight(PICK_AND_MIX_ID, new BigDecimal("2.99"));
  }

  private static Item twoHundredGramsOfPickAndMix() {
    return aKiloOfPickAndMix().weighing(new BigDecimal(".2"));
  }

  @DisplayName("basket provides its total value when containing...")
  @MethodSource
  @ParameterizedTest(name = "{0}")
  void basketProvidesTotalValue(String description, String expectedTotal, Iterable<Item> items) {
    final Basket basket = new Basket(
        List.of(new BuyOneGetOneFree(new ProductByUnit(DIGESTIVE_ID))));
    items.forEach(basket::add);
    assertEquals(new BigDecimal(expectedTotal), basket.total());
  }
}