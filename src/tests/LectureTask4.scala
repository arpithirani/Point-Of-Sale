package tests
import store.model.items.{Item, Modifier, Sale, SalesTax}
import org.scalatest.FunSuite

class LectureTask4 extends FunSuite {

  val Item1 : Item = new Item("Nike Air Force 1", 90.0)
  val Item2 : Item = new Item("Air Jordan 1", 170.0)

  val Sale1 : Sale = new Sale(20.0)
  val Sale2 : Sale = new Sale(3.0)
  val Sale3 : Sale = new Sale(10.0)

  val Tax1 : SalesTax = new SalesTax(8.0)
  val Tax2 : SalesTax = new SalesTax(0.5)
  val Tax3 : SalesTax = new SalesTax(3.0)

  test("check price and tax") {
    Item1.addModifier(Sale1)
    assert(Math.abs(Item1.price() - 72.0) < 0.0001, "Sale applied")
    Item1.addModifier(Sale2)
    Item1.addModifier(Sale3)
    assert(Math.abs(Item1.price() - 62.856) < 0.01 , Item1.price())
    Item1.addModifier(Tax1)
    assert(Math.abs(Item1.tax() - 5.028) < 0.01, "tax 1 applied")
    Item1.addModifier(Tax2)
    Item1.addModifier(Tax3)
    assert(Math.abs(Item1.tax() - 7.2285) < 0.01, "combined tax applied")
  }


}
