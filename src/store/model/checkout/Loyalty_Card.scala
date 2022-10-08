package store.model.checkout

import store.model.items.Item

class Loyalty_Card(selfCheckout: SelfCheckout) extends State {

  this.selfCheckout.Sub_Total = 0.0
  this.selfCheckout.Tax = 0.0

  for(item <- selfCheckout.returned_list){
    val Item_Barcode = this.selfCheckout.Loyalty_Item_in_inventory.getOrElse(item.description,"error")
    val Item = this.selfCheckout.Item_in_inventory.getOrElse(Item_Barcode, new Item("error", 0.0))
    item.amount = Item.loyalty_price()
    this.selfCheckout.Sub_Total += item.amount
    this.selfCheckout.Tax += Item.loyalty_tax()
  }

  override def displayString(): String = {
    this.selfCheckout.Item_barcode
  }

  override def numberPressed(number: Int): Unit = {
    this.selfCheckout.Item_barcode += number.toString
  }

  override def clearPressed(): Unit = {
    this.selfCheckout.Item_barcode = ""
  }

  override def enterPressed(): Unit = {
    this.selfCheckout.state = new Loyalty_Card_Enter(selfCheckout)
  }

  override def checkoutPressed(): Unit = {
    this.selfCheckout.state = new Checkout(selfCheckout)
  }

  override def cashPressed(): Unit = ???

  override def creditPressed(): Unit = ???

  override def loyaltycardPressed(): Unit = {

  }

}
