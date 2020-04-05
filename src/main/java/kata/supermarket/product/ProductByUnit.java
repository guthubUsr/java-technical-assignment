package kata.supermarket.product;

import java.math.BigDecimal;
import java.util.Objects;
import kata.supermarket.basket.Item;
import kata.supermarket.basket.ItemByUnit;

public class ProductByUnit implements Product {

  private final Long id;
  private BigDecimal pricePerUnit;

  public ProductByUnit(Long id) {
    this.id = id;
  }

  public ProductByUnit(Long id, BigDecimal pricePerUnit) {
    this.id = id;
    this.pricePerUnit = pricePerUnit;
  }

  public BigDecimal pricePerUnit() {
    return pricePerUnit;
  }

  public Item oneOf() {
    return new ItemByUnit(this);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ProductByUnit that = (ProductByUnit) o;
    return id.equals(that.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id);
  }
}
