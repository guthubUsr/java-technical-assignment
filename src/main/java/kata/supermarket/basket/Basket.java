package kata.supermarket.basket;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import kata.supermarket.discount.Discount;

public class Basket {

  private final List<Item> items;
  private final List<Discount> discountList;

  public Basket(List<Discount> discountList) {
    this.discountList = discountList;

    this.items = new ArrayList<>();
  }

  public void add(final Item item) {
    this.items.add(item);
    discountList.forEach(discount -> discount.apply(items));
  }

  List<Item> items() {
    return Collections.unmodifiableList(items);
  }

  public BigDecimal total() {
    return new TotalCalculator().calculate();
  }

  private class TotalCalculator {

    private final List<Item> items;

    TotalCalculator() {
      this.items = items();
    }

    private BigDecimal subtotal() {
      return items.stream().map(Item::price)
          .reduce(BigDecimal::add)
          .orElse(BigDecimal.ZERO)
          .setScale(2, RoundingMode.HALF_UP);
    }

    /**
     * TODO: This could be a good place to apply the results of the discount calculations. It is not
     * likely to be the best place to do those calculations. Think about how Basket could interact
     * with something which provides that functionality.
     */
    private BigDecimal discounts() {
      return BigDecimal.ZERO;
    }

    private BigDecimal calculate() {
      return subtotal().subtract(discounts());
    }
  }
}
