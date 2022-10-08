package tests

import org.scalatest.FunSuite
import scalafx.scene.input.KeyCombination.Modifier
import store.model.items.Modifier
import store.model.items.Sale
import store.model.items.SalesTax
import store.model.items.BottleDeposit

class LectureTask3 extends FunSuite {

  val deposit1 : BottleDeposit = new BottleDeposit(0.9)
  val percentage1 : SalesTax = new SalesTax(8.5)
  val percentage2 : Sale = new Sale(50.0)

  test("check BottleDeposit") {
    assert(deposit1.updatePrice(90.0) == 90.0, "price returned")
    assert(deposit1.computeTax(8.5) == 0.9, "deposit charged")
  }

  test("check SalesTax") {
    assert(percentage1.updatePrice(45.0) == 45.0, "price returned")
    assert(percentage1.computeTax(90.0) == 7.65, "tax charged")
  }

  test("check Sale") {
    assert(percentage2.updatePrice(60.0) == 30.0, "discount applied")
    assert(percentage2.computeTax(9.0) == 0.0)
  }

}
