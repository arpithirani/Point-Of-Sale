package store.model.items
import store.model.items.Modifier

class BottleDeposit(var deposit_charged: Double) extends Modifier {

  override def updatePrice(price: Double): Double = {
    price
  }

  override def computeTax(tax_price: Double): Double = {
    this.deposit_charged
  }

  override def loyalPrice(price: Double): Double = {
    price
  }

}
