package store.model.items

class LoyaltySale(sale_percentage: Double) extends Modifier {

  override def updatePrice(price: Double): Double = {
    price
  }

  override def computeTax(tax_price: Double): Double ={
    0.0
  }

  override def loyalPrice(price: Double): Double = {
    val final_price = price - (this.sale_percentage/100)*price
    final_price
  }

}
