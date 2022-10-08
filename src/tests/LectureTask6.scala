package tests

import org.scalatest.FunSuite
import store.model.checkout.SelfCheckout
import store.model.items.{Item, SalesTax, Sale, Modifier}


class LectureTask6 extends FunSuite{

  val epsilon:Double = 0.0001
  def compareDoubles(d1:Double, d2:Double):Boolean = {
    Math.abs(d1-d2) < epsilon
  }

  test("check LT6") {

    val test1: SelfCheckout = new SelfCheckout

    val Item1: Item = new Item("Air Jordan 1", 170.0)
    val Item2: Item = new Item("Air Jordan 3", 200.0)
    val Item3: Item = new Item("Air Max 90", 190.0)
    val Item4: Item = new Item("Air Force 1", 90.0)

    val saleVal:Sale = new Sale(50)
    val taxPrice:Modifier = new SalesTax(25)

    Item1.addModifier(saleVal)
    Item2.addModifier(saleVal)
    Item3.addModifier(saleVal)
    Item4.addModifier(saleVal)

    Item1.addModifier(taxPrice)
    Item2.addModifier(taxPrice)
    Item3.addModifier(taxPrice)
    Item4.addModifier(taxPrice)

    test1.addItem("123", Item1)
    test1.addItem("456", Item2)
    test1.addItem("789", Item3)
    test1.addItem("100", Item4)


    assert(test1.displayString().contains("welcome"))

    test1.numberPressed(1)
    test1.numberPressed(2)
    test1.numberPressed(3)
    assert(test1.displayString() == "123")
    test1.enterPressed()
    assert(test1.receiptLines().head.description == "Air Jordan 1")
    assert(compareDoubles(test1.receiptLines().head.amount,85.0))

    test1.enterPressed()
    test1.enterPressed()
    test1.clearPressed()
    test1.enterPressed()
    assert(test1.receiptLines().last.description == "error")
    assert(compareDoubles(test1.receiptLines().last.amount,0.0))

    test1.numberPressed(0)
    test1.numberPressed(1)
    test1.numberPressed(0)
    test1.numberPressed(0)
    test1.enterPressed()
    assert(test1.receiptLines().last.description == "error")
    assert(compareDoubles(test1.receiptLines().last.amount,0.0))

    test1.checkoutPressed()
    assert(test1.displayString() == "cash or credit")


    test1.cashPressed()
    assert(test1.displayString().contains("thank you"))

    assert(test1.receiptLines() == List())
    test1.numberPressed(1)
    test1.numberPressed(2)
    test1.numberPressed(3)
    assert(test1.displayString() == "123")
    test1.enterPressed()
    assert(test1.receiptLines().head.description == "Air Jordan 1")
    assert(compareDoubles(test1.receiptLines().head.amount,85.0))

  }
}
