package day4

import day4.Part1.toDigitList

object Part2 extends App {

  /*
	An Elf just remembered one more important detail: the two adjacent matching digits are not part of a larger group of matching digits.

Given this additional criterion, but still ignoring the range rule, the following are now true:

112233 meets these criteria because the digits never decrease and all repeated digits are exactly two digits long.
123444 no longer meets the criteria (the repeated 44 is part of a larger group of 444).
111122 meets the criteria (even though 1 is repeated more than twice, it still contains a double 22).
How many different passwords within
	 */

  lazy val digitRange: Seq[Int] = 234208 to 765869 by 1

  def whichOnlyTwoDigitsAreTheSame(digits: Int): Either[Any, Int] = {
    case class Accumalator(multipleNumber: Int, numberBefore: Int, isTheSame: Boolean)
    val STARTING_NUMBER = 99999

    val result = toDigitList(digits)
      .foldLeft(Accumalator(STARTING_NUMBER, STARTING_NUMBER, false)) {
        case (Accumalator(multipleNumber, accNum, _), currNum)
            if accNum == currNum && multipleNumber != STARTING_NUMBER =>
          Accumalator(multipleNumber, currNum, false)
        case (Accumalator(multipleNumber, accNum, _), currNum) if accNum == currNum =>
          Accumalator(currNum, currNum, true)
        case (Accumalator(multipleNumber, _, isTheSame), currNum) =>
          Accumalator(multipleNumber, currNum, isTheSame)
      }

    result match {
      case Accumalator(multiple, _, true)         => Right(multiple)
      case Accumalator(STARTING_NUMBER, _, false) => Left("No Double digits exist")
      case Accumalator(multiple, _, false)        => Left(multiple) //for more than two occurence
    }
  }

  def filterOutMoreThanTwoOccurances(digits: Int, digitFilter: Int) = {
    toDigitList(digits).filterNot(_ == digitFilter).mkString.toIntOption.getOrElse(digits)
  }

  def combine(digit: Int) = {
    whichOnlyTwoDigitsAreTheSame(digit) match {
      case Right(x)        => true
      case Left(x: String) => false
      case Left(x: Int) =>
        whichOnlyTwoDigitsAreTheSame(filterOutMoreThanTwoOccurances(digit, x)).isRight
    }
  }

  def evaluate(digitRange: Seq[Int]): Seq[Int] = {
    digitRange.filter(digit => Part1.inAscendingOrder(digit) && combine(digit))
  }

  println(evaluate(digitRange))

}
