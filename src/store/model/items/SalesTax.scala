package store.model.items
import store.model.items.Modifier

class SalesTax(var tax_percentage: Double) extends Modifier() {

  override def updatePrice(price: Double): Double = {
    price
  }

  override def computeTax(tax_price: Double): Double = {
    val final_price = (this.tax_percentage/100) * tax_price
    final_price
  }

  override def loyalPrice(price: Double): Double = {
    price
  }

}
