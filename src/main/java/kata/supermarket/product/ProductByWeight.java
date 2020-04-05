package kata.supermarket.product;

import java.math.BigDecimal;
import java.util.Objects;
import kata.supermarket.basket.Item;
import kata.supermarket.basket.ItemByWeight;

public class ProductByWeight implements Product {

  private final Long id;
  private final BigDecimal pricePerKilo;

  public ProductByWeight(Long id, final BigDecimal pricePerKilo) {
    this.id = id;
    this.pricePerKilo = pricePerKilo;
  }

  public BigDecimal pricePerKilo() {
    return pricePerKilo;
  }

  public Item weighing(final BigDecimal kilos) {
    return new ItemByWeight(this, kilos);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ProductByWeight that = (ProductByWeight) o;
    return id.equals(that.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id);
  }
}
