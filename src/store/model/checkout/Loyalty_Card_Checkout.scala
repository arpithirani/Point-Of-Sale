package store.model.checkout

import store.model.items.Item

class Loyalty_Card_Checkout(selfCheckout: SelfCheckout) extends State {

  this.selfCheckout.Sub_Total = 0.0
  this.selfCheckout.Tax = 0.0

  for(item <- selfCheckout.returned_list){
    val Item_Barcode = this.selfCheckout.Loyalty_Item_in_inventory.getOrElse(item.description,"error")
    val Item = this.selfCheckout.Item_in_inventory.getOrElse(Item_Barcode, new Item("error", 0.0))
    item.amount = Item.loyalty_price()
    this.selfCheckout.Sub_Total += item.amount
    this.selfCheckout.Tax += Item.loyalty_tax()
  }

  selfCheckout.returned_list = selfCheckout.returned_list ::: List(new ReceiptLine("subtotal",selfCheckout.Sub_Total))
  selfCheckout.returned_list = selfCheckout.returned_list ::: List(new ReceiptLine("tax",selfCheckout.Tax))
  selfCheckout.returned_list = selfCheckout.returned_list ::: List(new ReceiptLine("total",selfCheckout.Sub_Total+selfCheckout.Tax))

  override def displayString(): String = {
    "cash or credit"
  }

  override def numberPressed(number: Int): Unit = ???

  override def clearPressed(): Unit = ???

  override def enterPressed(): Unit = ???

  override def checkoutPressed(): Unit = ???

  override def cashPressed(): Unit = {
    this.selfCheckout.Sub_Total = 0.0
    this.selfCheckout.Tax = 0.0
    this.selfCheckout.returned_list = List()
    this.selfCheckout.state = new Cash_or_Credit(selfCheckout)
  }

  override def creditPressed(): Unit = {
    this.selfCheckout.Sub_Total = 0.0
    this.selfCheckout.Tax = 0.0
    this.selfCheckout.returned_list = List()
    this.selfCheckout.state = new Cash_or_Credit(selfCheckout)
  }

  override def loyaltycardPressed(): Unit = ???

}
