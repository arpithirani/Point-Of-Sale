package tests

import org.scalatest.FunSuite
import store.model.checkout.SelfCheckout
import store.model.items.{BottleDeposit, Item, LoyaltySale, Modifier, Sale, SalesTax}

class ApplicationObjective3 extends FunSuite{

  val epsilon:Double = 0.0001
  def compareDoubles(d1:Double, d2:Double):Boolean = {
    Math.abs(d1-d2) < epsilon
  }

  test("test1 AO3") {

    val test1 : SelfCheckout = new SelfCheckout()
    val test2 : SelfCheckout = new SelfCheckout()
    val Item1: Item = new Item("Air Jordan 1", 170.0)
    val Item2: Item = new Item("Air Jordan 3", 200.0)
    val Item3: Item = new Item("Air Max 90", 190.0)
    val Item4: Item = new Item("Air Force 1", 90.0)

    val sale = new Sale(20)
    val saletax = new SalesTax(6)

    Item2.addModifier(sale)
    Item1.addModifier(saletax)
    Item2.addModifier(saletax)
    Item3.addModifier(saletax)
    Item4.addModifier(saletax)
    test1.addItem("123", Item1)
    test1.addItem("456", Item2)
    test1.addItem("789", Item3)
    test1.addItem("100", Item4)


    test2.addItem("123", Item1)
    test2.addItem("456", Item2)
    test2.addItem("789", Item3)
    test2.addItem("100", Item4)

    test1.cashPressed()
    test1.creditPressed()
    test1.enterPressed()

    test1.numberPressed(1)
    test1.numberPressed(2)
    test1.numberPressed(3)
    test1.enterPressed()
    var x = test1.receiptLines()
    assert(x.head.description.contentEquals("Air Jordan 1"))
    assert(compareDoubles(x.head.amount, 0))

    test1.numberPressed(4)
    test1.numberPressed(5)
    test1.numberPressed(6)
    test1.enterPressed()
    test1.enterPressed()
    x = test1.receiptLines()
    assert(x(1).description.contentEquals("Air Jordan 3"))
    assert(compareDoubles(x(1).amount, 0))
    assert(x(2).description == x(1).description)
    assert(compareDoubles(x(2).amount, x(1).amount))

    test1.checkoutPressed()

    assert(x.head.description.contentEquals("Air Jordan 1"))
    assert(compareDoubles(x.head.amount, 0))
    assert(x(1).description.contentEquals("Air Jordan 3"))
    assert(compareDoubles(x(1).amount, 0))
    assert(x(2).description == x(1).description)
    assert(compareDoubles(x(2).amount, x(1).amount))
    x = test1.receiptLines()
    assert(x(3).description.contentEquals("subtotal"))
    assert(compareDoubles(x(3).amount, 0))
    assert(x(4).description.contentEquals("tax"))
    assert(compareDoubles(x(4).amount, 0))
    assert(x(5).description.contentEquals("total"))
    assert(compareDoubles(x(5).amount, 0))

    test1.cashPressed()

    assert(test1.receiptLines() == List())

    test2.numberPressed(1)
    test2.numberPressed(2)
    test2.numberPressed(3)
    test2.enterPressed()
    var x2 = test2.receiptLines()
    assert(x2.head.description.contentEquals("Air Jordan 1"))
    assert(compareDoubles(x2.head.amount, 170))

    test2.cashPressed()
    test2.creditPressed()
    test2.enterPressed()

    test2.numberPressed(4)
    test2.numberPressed(5)
    test2.numberPressed(6)
    test2.enterPressed()
    test2.enterPressed()
    x2 = test2.receiptLines()
    assert(x2(1).description.contentEquals("Air Jordan 3"))
    assert(compareDoubles(x2(1).amount, 0))
    assert(x2(2).description == x2(1).description)
    assert(compareDoubles(x2(2).amount, x2(1).amount))

    test2.checkoutPressed()

    assert(x2.head.description.contentEquals("Air Jordan 1"))
    assert(compareDoubles(x2.head.amount, 0))
    assert(x2(1).description.contentEquals("Air Jordan 3"))
    assert(compareDoubles(x2(1).amount, 0))
    assert(x2(2).description == x2(1).description)
    assert(compareDoubles(x2(2).amount, x2(1).amount))
    x2 = test2.receiptLines()
    assert(x2(3).description.contentEquals("subtotal"))
    assert(compareDoubles(x2(3).amount, 0))
    assert(x2(4).description.contentEquals("tax"))
    assert(compareDoubles(x2(4).amount, 0))
    assert(x2(5).description.contentEquals("total"))
    assert(compareDoubles(x2(5).amount, 0))

    test2.cashPressed()


  }

}
