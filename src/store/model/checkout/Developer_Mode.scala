package store.model.checkout

class Developer_Mode(selfCheckout: SelfCheckout) extends State {

  for(item <- selfCheckout.returned_list){
    item.amount = 0.0
  }
  this.selfCheckout.Sub_Total = 0.0
  this.selfCheckout.Tax = 0.0

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
    this.selfCheckout.state = new Developer_Mode_Enter(selfCheckout)
  }

  override def checkoutPressed(): Unit = {
    this.selfCheckout.state = new Checkout(selfCheckout)
  }

  override def cashPressed(): Unit = {
    this.selfCheckout.state = new Developer_Mode_Cash(selfCheckout)
  }

  override def creditPressed(): Unit = {

  }

  override def loyaltycardPressed(): Unit = {

  }
}
