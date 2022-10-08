package store.model.items

class SaleTestingItem(val item_description: String, var item_price: Double) {
  var new_sale: List[Sale] = List()

  def addSale(sale: Sale): Unit = {
    this.new_sale = this.new_sale :+ sale
  }

  def price(): Double = {
    var final_price: Double = this.item_price
    for (obj <- this.new_sale) {
      final_price = obj.updatePrice(final_price)
    }
    final_price
  }

}


