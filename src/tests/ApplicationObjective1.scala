package tests

import org.scalatest.FunSuite
import store.model.checkout.SelfCheckout
import store.model.items.{BottleDeposit, Item, LoyaltySale, Modifier, Sale, SalesTax}

class ApplicationObjective1 extends FunSuite{

  val epsilon:Double = 0.0001
  def compareDoubles(d1:Double, d2:Double):Boolean = {
    Math.abs(d1-d2) < epsilon
  }

  test("test1 AO1") {

    val test1 : SelfCheckout = new SelfCheckout()
    val test2 : SelfCheckout = new SelfCheckout()
    val test3 : SelfCheckout = new SelfCheckout()
    val Item1: Item = new Item("Air Jordan 1", 170.0)
    val Item2: Item = new Item("Air Jordan 3", 200.0)
    val Item3: Item = new Item("Air Max 90", 190.0)
    val Item4: Item = new Item("Air Force 1", 90.0)

    val sale = new Sale(20)
    val saletax = new SalesTax(6)
    val loyaltySale = new LoyaltySale(10)

    Item2.addModifier(sale)

    Item1.addModifier(saletax)
    Item2.addModifier(saletax)
    Item3.addModifier(saletax)
    Item4.addModifier(saletax)

    Item1.addModifier(loyaltySale)
    Item2.addModifier(loyaltySale)
    Item3.addModifier(loyaltySale)
    Item4.addModifier(loyaltySale)

    test1.addItem("123", Item1)
    test1.addItem("456", Item2)
    test1.addItem("789", Item3)
    test1.addItem("100", Item4)


    test2.addItem("123", Item1)
    test2.addItem("456", Item2)
    test2.addItem("789", Item3)
    test2.addItem("100", Item4)

    test3.addItem("123", Item1)
    test3.addItem("456", Item2)
    test3.addItem("789", Item3)
    test3.addItem("100", Item4)

    test1.numberPressed(1)
    test1.numberPressed(2)
    test1.numberPressed(3)
    test1.enterPressed()
    assert(test1.receiptLines().head.description.contentEquals("Air Jordan 1"))
    assert(compareDoubles(test1.receiptLines().head.amount, 170))

    test1.numberPressed(4)
    test1.numberPressed(5)
    test1.numberPressed(6)
    test1.enterPressed()
    test1.enterPressed()
    assert(test1.receiptLines()(1).description.contentEquals("Air Jordan 3"))
    assert(compareDoubles(test1.receiptLines()(1).amount, 160))
    assert(test1.receiptLines()(2).description == test1.receiptLines()(1).description)
    assert(compareDoubles(test1.receiptLines()(2).amount, test1.receiptLines()(1).amount))

    test1.loyaltyCardPressed()

    test1.checkoutPressed()

    assert(test1.receiptLines().head.description.contentEquals("Air Jordan 1"))
    assert(compareDoubles(test1.receiptLines().head.amount, 153))
    assert(test1.receiptLines()(1).description.contentEquals("Air Jordan 3"))
    assert(compareDoubles(test1.receiptLines()(1).amount, 144))
    assert(test1.receiptLines()(2).description == test1.receiptLines()(1).description)
    assert(test1.receiptLines()(3).description.contentEquals("subtotal"))
    assert(compareDoubles(test1.receiptLines()(3).amount, 441))
    assert(test1.receiptLines()(4).description.contentEquals("tax"))
    assert(compareDoubles(test1.receiptLines()(4).amount, 26.46))
    assert(test1.receiptLines()(5).description.contentEquals("total"))
    assert(compareDoubles(test1.receiptLines()(5).amount, 467.46))

    test1.cashPressed()

    assert(test1.receiptLines() == List())

    test2.loyaltyCardPressed()

    test2.numberPressed(1)
    test2.numberPressed(2)
    test2.numberPressed(3)
    test2.enterPressed()
    assert(test2.receiptLines().head.description.contentEquals("Air Jordan 1"))
    assert(compareDoubles(test2.receiptLines().head.amount, 153))

    test2.numberPressed(4)
    test2.numberPressed(5)
    test2.numberPressed(6)
    test2.enterPressed()
    test2.enterPressed()
    assert(test2.receiptLines()(1).description.contentEquals("Air Jordan 3"))
    assert(compareDoubles(test2.receiptLines()(1).amount, 144))
    assert(test2.receiptLines()(2).description == test2.receiptLines()(1).description)
    assert(compareDoubles(test2.receiptLines()(2).amount, test2.receiptLines()(1).amount))

    test2.numberPressed(7)
    test2.numberPressed(8)
    test2.numberPressed(9)
    test2.enterPressed()
    assert(test2.receiptLines()(3).description.contentEquals("Air Max 90"))
    assert(compareDoubles(test2.receiptLines()(3).amount, 171))

    test2.checkoutPressed()

    assert(test2.receiptLines()(4).description.contentEquals("subtotal"))
    assert(compareDoubles(test2.receiptLines()(4).amount, 612))
    assert(test2.receiptLines()(5).description.contentEquals("tax"))
    assert(compareDoubles(test2.receiptLines()(5).amount, 36.72))
    assert(test2.receiptLines()(6).description.contentEquals("total"))
    assert(compareDoubles(test2.receiptLines()(6).amount, 648.72))

    test2.cashPressed()

    assert(test2.receiptLines() == List())

    test3.numberPressed(1)
    test3.numberPressed(2)
    test3.numberPressed(3)
    test3.enterPressed()
    assert(test3.receiptLines().head.description.contentEquals("Air Jordan 1"))
    assert(compareDoubles(test3.receiptLines().head.amount, 170))

    test3.numberPressed(7)
    test3.numberPressed(8)
    test3.numberPressed(9)
    test3.enterPressed()
    assert(test3.receiptLines()(1).description.contentEquals("Air Max 90"))
    assert(compareDoubles(test3.receiptLines()(1).amount, 190))

    test3.checkoutPressed()

    test3.loyaltyCardPressed()

    assert(test3.receiptLines().head.description.contentEquals("Air Jordan 1"))
    assert(compareDoubles(test3.receiptLines().head.amount, 153))
    assert(test3.receiptLines()(1).description.contentEquals("Air Max 90"))
    assert(compareDoubles(test3.receiptLines()(1).amount, 171))
    assert(test3.receiptLines()(2).description.contentEquals("subtotal"))
    assert(compareDoubles(test3.receiptLines()(2).amount, 324))
    assert(test3.receiptLines()(3).description.contentEquals("tax"))
    assert(compareDoubles(test3.receiptLines()(3).amount, 19.4399998))
    assert(test3.receiptLines()(4).description.contentEquals("total"))
    assert(compareDoubles(test3.receiptLines()(4).amount, 343.44))

    test3.cashPressed()

    assert(test3.receiptLines() == List())

  }

}
