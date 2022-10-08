package store.model.checkout

class Checkout(selfCheckout: SelfCheckout) extends State {


  selfCheckout.returned_list = selfCheckout.returned_list ::: List(new ReceiptLine("subtotal",selfCheckout.Sub_Total))
  selfCheckout.returned_list = selfCheckout.returned_list ::: List(new ReceiptLine("tax",selfCheckout.Tax))
  selfCheckout.returned_list = selfCheckout.returned_list ::: List(new ReceiptLine("total",selfCheckout.Sub_Total+selfCheckout.Tax))

  override def displayString(): String = {
    "cash or credit"
  }

  override def numberPressed(number: Int): Unit = {

  }

  override def clearPressed(): Unit = {}

  override def enterPressed(): Unit = {}

  override def checkoutPressed(): Unit = {}

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

  override def loyaltycardPressed(): Unit = {
    this.selfCheckout.returned_list = this.selfCheckout.returned_list.dropRight(3)
    this.selfCheckout.state = new Loyalty_Card_Checkout(selfCheckout)
  }

}
