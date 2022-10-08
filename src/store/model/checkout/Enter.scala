package store.model.checkout

import store.model.items.Item

class Enter(selfCheckout: SelfCheckout) extends State {

  val check_inventory: Item = selfCheckout.Item_in_inventory.getOrElse(selfCheckout.Item_barcode, new Item("error",0.0))
  selfCheckout.returned_list = selfCheckout.returned_list ::: List(new ReceiptLine(check_inventory.description(),check_inventory.price()))
  selfCheckout.Item_barcode = ""

  this.selfCheckout.Sub_Total += check_inventory.price()
  this.selfCheckout.Tax += check_inventory.tax()

  override def displayString(): String = {
    ""
  }

  override def numberPressed(number: Int): Unit = {
    this.selfCheckout.Item_barcode += number.toString
    this.selfCheckout.state = new Main(selfCheckout)
  }

  override def clearPressed(): Unit = {
    this.selfCheckout.Item_barcode = ""
    this.selfCheckout.state = new Main(selfCheckout)
  }

  override def enterPressed(): Unit = {
    this.selfCheckout.returned_list = selfCheckout.returned_list ::: List(new ReceiptLine(check_inventory.description(),check_inventory.price()))
    this.selfCheckout.Sub_Total += check_inventory.price()
    this.selfCheckout.Tax += check_inventory.tax()
  }

  override def checkoutPressed(): Unit = {
    this.selfCheckout.state = new Checkout(selfCheckout)
  }

  override def cashPressed(): Unit = {
    this.selfCheckout.state = new Developer_Mode_Cash(selfCheckout)
  }

  override def creditPressed(): Unit = ???

  override def loyaltycardPressed(): Unit = {
    this.selfCheckout.state = new Loyalty_Card(selfCheckout)
  }

}
