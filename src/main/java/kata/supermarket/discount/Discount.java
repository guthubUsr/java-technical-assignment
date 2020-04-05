package kata.supermarket.discount;

import java.util.Collection;
import kata.supermarket.basket.Item;

public interface Discount {

  void apply(Collection<Item> items);
}
