package kata.supermarket.basket;

import java.math.BigDecimal;
import kata.supermarket.product.Product;
import kata.supermarket.product.ProductByUnit;

public class ItemByUnit implements Item {

  private final ProductByUnit productByUnit;
  private BigDecimal price;
  private Boolean discounted;

  public ItemByUnit(final ProductByUnit productByUnit) {
    this.productByUnit = productByUnit;
    this.price = productByUnit.pricePerUnit();
    this.discounted = Boolean.FALSE;
  }

  public BigDecimal price() {
    return price;
  }

  @Override
  public void setPrice(BigDecimal newPrice) {
    this.price = newPrice;
  }

  @Override
  public boolean isDiscounted() {
    return false;
  }

  @Override
  public void setDiscounted() {
    this.discounted = Boolean.TRUE;
  }

  @Override
  public Product product() {
    return productByUnit;
  }
}
