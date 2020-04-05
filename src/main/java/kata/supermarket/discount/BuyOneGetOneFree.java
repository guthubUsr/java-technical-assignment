package kata.supermarket.discount;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import kata.supermarket.basket.Item;
import kata.supermarket.product.ProductByUnit;

public class BuyOneGetOneFree implements Discount {

  private final ProductByUnit productByUnit;

  public BuyOneGetOneFree(ProductByUnit productByUnit) {
    this.productByUnit = productByUnit;
  }

  @Override
  public void apply(Collection<Item> items) {
    List<Item> itemsToBeDiscounted = items.stream()
        .filter(item -> item.product().equals(productByUnit)).filter(item -> !item.isDiscounted())
        .collect(
            Collectors.toList());
    if (itemsToBeDiscounted.size() >= 2) {
      Item item0 = itemsToBeDiscounted.get(0);
      Item item1 = itemsToBeDiscounted.get(1);

      item0.setDiscounted();
      item1.setDiscounted();

      item1.setPrice(new BigDecimal("0.0"));
    }
  }
}
