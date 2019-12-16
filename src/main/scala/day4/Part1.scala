package day4

object Part1 {

  /*
	It is a six-digit number. ALWAYS TRUE
	The value is within the range given in your puzzle input. ALWAYS TRUE
	Two adjacent digits are the same (like 22 in 122345).
	Going from left to right, the digits never decrease; they only ever increase or stay the same (like 111123 or 135679).
	 */

  lazy val digitRange: Seq[Int] = 234208 to 765869 by 1

  def toDigitList(digits: Int): Seq[Int] = digits.toString.split("").map(_.toInt).toList

  def twoDigitsAreTheSame(digits: Int): Boolean = {
    case class Accumalator(numberBefore: Int, isTheSame: Boolean)
    val STARTING_NUMBER = 99999

    toDigitList(digits)
      .foldLeft(Accumalator(STARTING_NUMBER, false)) {
        case (Accumalator(accNum, _), currNum) if accNum == currNum => Accumalator(currNum, true)
        case (Accumalator(_, isTheSame), currNum)                   => Accumalator(currNum, isTheSame)
      }
      .isTheSame
  }

  def inAscendingOrder(digits: Int): Boolean = {
    val sortedList = toDigitList(digits).sorted.map(_.toString).mkString.toInt
    sortedList == digits
  }

  println(
    "Amount of password combinations = " + digitRange
      .count(digit => twoDigitsAreTheSame(digit) && inAscendingOrder(digit)))

}
