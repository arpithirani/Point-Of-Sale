package store.model.checkout

class Main(selfCheckout: SelfCheckout) extends State {

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
    this.selfCheckout.state = new Enter(selfCheckout)
  }

  override def checkoutPressed(): Unit = ???

  override def cashPressed(): Unit = {
    this.selfCheckout.state = new Developer_Mode_Cash(selfCheckout)
  }

  override def creditPressed(): Unit = ???

  override def loyaltycardPressed(): Unit = {
    this.selfCheckout.state = new Loyalty_Card(selfCheckout)
  }

}
