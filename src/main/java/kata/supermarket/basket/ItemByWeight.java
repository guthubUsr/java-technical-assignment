package kata.supermarket.basket;

import java.math.BigDecimal;
import java.math.RoundingMode;
import kata.supermarket.product.Product;
import kata.supermarket.product.ProductByWeight;

public class ItemByWeight implements Item {

  private final ProductByWeight productByWeight;
  private final BigDecimal weightInKilos;
  private BigDecimal pricePerKilo;

  public ItemByWeight(final ProductByWeight productByWeight, final BigDecimal weightInKilos) {
    this.productByWeight = productByWeight;
    this.weightInKilos = weightInKilos;
    this.pricePerKilo = productByWeight.pricePerKilo();
  }

  public BigDecimal price() {
    return pricePerKilo.multiply(weightInKilos).setScale(2, RoundingMode.HALF_UP);
  }

  @Override
  public void setPrice(BigDecimal newPrice) {
    this.pricePerKilo = newPrice;
  }

  @Override
  public boolean isDiscounted() {
    return false;
  }

  @Override
  public void setDiscounted() {
    throw new RuntimeException("Not supported functionality");
  }

  @Override
  public Product product() {
    return productByWeight;
  }
}
