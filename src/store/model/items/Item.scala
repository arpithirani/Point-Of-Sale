package store.model.items
import store.model.items.Modifier

class Item(val item_description: String, val item_price: Double) {

  var answer: Int = 0
  var method_list: List[Modifier] = List()

  def price(): Double = {
    var discounted_price: Double = this.item_price
    for (method <- method_list){
      discounted_price = method.updatePrice(discounted_price)
    }
    discounted_price
  }

  def loyalty_price(): Double = {
    var discounted_price: Double = price()
    for (method <- method_list){
      discounted_price = method.loyalPrice(discounted_price)
    }
    discounted_price
  }

  def description(): String = {
    this.item_description
  }

  def scanned(): Unit = {
    answer += 1
  }

  def timesScanned(): Int = {
    answer
  }

  def addModifier(modifier: Modifier): Unit = {
    method_list = method_list :+ modifier
  }

  def tax(): Double = {
    var applied_tax_price: Double = 0.0
    val final_price: Double= price()
    for (func <- method_list){
      applied_tax_price += func.computeTax(final_price)
    }
    applied_tax_price
  }

  def loyalty_tax(): Double = {
    var applied_tax_price: Double = 0.0
    val final_price: Double= loyalty_price()
    for (func <- method_list){
      applied_tax_price += func.computeTax(final_price)
    }
    applied_tax_price
  }

}

