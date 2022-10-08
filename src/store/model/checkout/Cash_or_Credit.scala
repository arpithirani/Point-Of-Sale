package store.model.checkout

class Cash_or_Credit(selfCheckout: SelfCheckout) extends State {

  this.selfCheckout.Item_barcode = ""

  override def displayString(): String = {
    "thank you"
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
    this.selfCheckout.state = new Main(selfCheckout)
  }

  override def checkoutPressed(): Unit = {}

  override def cashPressed(): Unit = {}

  override def creditPressed(): Unit = {}

  override def loyaltycardPressed(): Unit = {
    this.selfCheckout.state = new Loyalty_Card(selfCheckout)
  }

}
