package store.model.items

abstract class Modifier {

  def updatePrice(price: Double): Double

  def computeTax(tax_price: Double): Double

  def loyalPrice(price: Double): Double

}
