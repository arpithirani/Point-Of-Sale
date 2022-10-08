package store.model.checkout

import store.model.items.{Item, LoyaltySale, Sale}

class SelfCheckout {

  var state: State = new StartMachine(this)
  var Item_in_inventory: Map[String,Item] = Map()
  var Item_barcode: String = ""
  var returned_list: List[ReceiptLine] = List()
  var Sub_Total: Double = 0.0
  var Tax: Double = 0.0
  var Loyalty_Item_in_inventory: Map[String,String] = Map()

  def addItem(barcode: String, item: Item): Unit = {
    Item_in_inventory += (barcode -> item)
    Loyalty_Item_in_inventory += (item.description() -> barcode)
  }

  def numberPressed(number: Int): Unit = {
    this.state.numberPressed(number)
  }

  def clearPressed(): Unit = {
    this.state.clearPressed()
  }

  def enterPressed(): Unit = {
    this.state.enterPressed()
  }

  def checkoutPressed(): Unit = {
    this.state.checkoutPressed()
  }

  def cashPressed(): Unit = {
    this.state.cashPressed()
  }

  def creditPressed(): Unit = {
    this.state.creditPressed()
  }

  def loyaltyCardPressed(): Unit = {
    this.state.loyaltycardPressed()
  }

  def displayString(): String = {
    this.state.displayString()
  }

  def receiptLines(): List[ReceiptLine] = {
    returned_list
  }

  def prepareStore(): Unit = {
    // Similar to openMap in the Pale Blue Dot assignment, this method is not required and is
    // meant to help you run manual tests.
    //
    // This method is called by the GUI during setup. Use this method to prepare your
    // items and call addItem to add their barcodes. Also add any sales/tax/etc to your
    // items.
    //
    // This method will not be called during testing and you should not call it in your tests.
    // Each test must setup its own items to ensure compatibility in AutoLab. However, you can
    // write a similar method in your Test Suite classes.
    val Item1: Item = new Item("Air Jordan 1", 170.0)
    val Item2: Item = new Item("Air Jordan 3", 200.0)
    val Item3: Item = new Item("Air Max 90", 190.0)
    val Item4: Item = new Item("Air Force 1", 90.0)
    addItem("123",Item1)
    addItem("456",Item2)
    addItem("789",Item3)
    addItem("100",Item4)
    val Sale1 = new Sale(10.0)
    Item1.addModifier(new LoyaltySale(0.1))
    Item2.addModifier(new LoyaltySale(90))
    Item1.addModifier(Sale1)
  }

}
