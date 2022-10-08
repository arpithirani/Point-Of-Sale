package tests

import org.scalatest.FunSuite
import store.model.items.Sale
import store.model.items.SaleTestingItem

class LectureTask2 extends FunSuite {

  val Item1 : SaleTestingItem = new SaleTestingItem("Nike Air Force 1", 90.0)
  val Item2 : SaleTestingItem = new SaleTestingItem("Air Jordan 1", 170.0)
  val Item3 : SaleTestingItem = new SaleTestingItem("iPhone 12 Pro", 999.0)
  val Item4 : SaleTestingItem = new SaleTestingItem("charger", 20.0)

  val Sale1 : Sale = new Sale(20.0)
  val Sale2 : Sale = new Sale(3.0)
  val Sale3 : Sale = new Sale(50.0)
  val Sale4 : Sale = new Sale(10.0)
  val Sale5 : Sale = new Sale(0.0)

  test("check SaleTestingItem") {

    Item1.addSale(Sale1)
    Item1.addSale(Sale2)
    assert(Math.abs(Item1.price() - 69.84) < 0.01, "discount applied")

    Item1.addSale(Sale3)
    assert(Math.abs(Item1.price() - 34.92) < 0.01, "Sale applied")

    Item1.addSale(Sale4)
    Item1.addSale(Sale5)
    assert(Math.abs(Item1.price() - 31.428) < 0.01, "Incorrect Sale")

    Item3.addSale(Sale3)
    assert(Math.abs(Item3.price() - 499.5) < 0.01, "Sale applied")

    Item2.addSale(Sale4)
    Sale4.percentOff = 5.0
    assert(Math.abs(Item2.price() - 161.5) < 0.01, "Test not passed")

  }

}
