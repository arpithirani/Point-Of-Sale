package store.model.checkout

abstract class State {

  def displayString(): String

  def numberPressed(number: Int): Unit

  def clearPressed(): Unit

  def enterPressed(): Unit

  def checkoutPressed(): Unit

  def cashPressed(): Unit

  def creditPressed(): Unit

  def loyaltycardPressed(): Unit

}
