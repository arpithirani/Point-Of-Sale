package tests
import store.model.checkout.ReceiptLine
import store.model.checkout.SelfCheckout
import org.scalatest.FunSuite
import store.model.items.Item


class LectureTask5 extends FunSuite{

  test("check SelfCheckout") {

    var test1: SelfCheckout = new SelfCheckout

    val Item1: Item = new Item("Air Jordan 1", 170.0)
    val Item2: Item = new Item("Air Jordan 3", 200.0)
    val Item3: Item = new Item("Air Max 90", 190.0)
    val Item4: Item = new Item("Air Force 1", 90.0)

    test1.addItem("123", Item1)
    test1.addItem("456", Item2)
    test1.addItem("789", Item3)
    test1.addItem("100", Item4)

    test1.numberPressed(1)
    test1.numberPressed(2)
    test1.numberPressed(3)
    assert(test1.displayString() == "123", "Correct press")
    test1.enterPressed()

    test1.numberPressed(1)
    test1.numberPressed(2)
    test1.numberPressed(3)
    test1.enterPressed()

    test1.numberPressed(0)
    test1.numberPressed(1)
    test1.numberPressed(0)
    test1.numberPressed(0)
    assert(test1.displayString() == "0100", "Correct press")
    test1.enterPressed()

    test1.numberPressed(8900)
    assert(test1.displayString() == "8900", "Correct press")
    test1.enterPressed()

    test1.numberPressed(0)
    test1.clearPressed()
    assert(test1.displayString() == "", "Correct press")


    assert(test1.receiptLines().head.description == "Air Jordan 1")
    assert(test1.receiptLines().head.amount == 170.0)

  }
}
