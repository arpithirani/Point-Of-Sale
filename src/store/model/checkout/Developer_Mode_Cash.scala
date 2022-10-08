package store.model.checkout

class Developer_Mode_Cash(selfCheckout: SelfCheckout) extends State {

  override def displayString(): String = ???

  override def numberPressed(number: Int): Unit = ???

  override def clearPressed(): Unit = ???

  override def enterPressed(): Unit = ???

  override def checkoutPressed(): Unit = ???

  override def cashPressed(): Unit = ???

  override def creditPressed(): Unit = {
    this.selfCheckout.state = new Developer_Mode_Credit(selfCheckout)
  }

  override def loyaltycardPressed(): Unit = ???

}
