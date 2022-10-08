package store.model.checkout

class StartMachine(selfCheckout: SelfCheckout) extends State {

  override def displayString(): String = {
    "welcome to my store"
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
      this.selfCheckout.Item_barcode = ""
      this.selfCheckout.state = new Main(selfCheckout)
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
