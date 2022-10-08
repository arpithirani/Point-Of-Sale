package store.model.items
import store.model.items.Modifier

class Sale(var percentOff: Double) extends Modifier() {

  override def updatePrice(price: Double): Double = {
    val discount_price: Double = price - (this.percentOff/100.0)*price
    discount_price
  }

  override def computeTax(tax_price: Double): Double = {
    0.0
  }

  override def loyalPrice(price: Double): Double = {
    price
  }

}