package tests

import org.scalatest.FunSuite
import store.model.items.Item

class LectureTask1 extends FunSuite {

  test("check scanned item") {
    val item1: Item = new Item("PS4 Dualshock", 64.99)
    val item2: Item = new Item("Dell Monitor 27 inch", 130.0)

    assert(item1.description() == "PS4 Dualshock")
    assert(item2.description() == "Dell Monitor 27 inch")

    assert(item2.price() == 130)
    assert(Math.abs(item2.price() - 130.0) < 0.0001)

    assert((item1.timesScanned() == 0))
    item1.scanned()
    assert(item1.timesScanned() == 1)
    item2.scanned()
    item2.scanned()
    assert(item2.timesScanned() == 2)
  }

}
