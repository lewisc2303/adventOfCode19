package day2

import scala.annotation.tailrec
import scala.io.Source
import scala.util.{Success, Try}

object Part1 extends App {

  lazy val path = Try(getClass.getClassLoader.getResource("day2Input.txt").getFile)

  val ADD       = 1
  val MULTIPLY  = 2
  val TERMINATE = 99

  val initialListUpdate = getInputList(path).toList.updated(1, 12).updated(2, 2)

  def intCodeComputer(input: List[Int]): List[Int] = {

    @tailrec
    def go(input: List[Int], toDrop: Int, memory: List[Int]): List[Int] = {
      input match {
        case ADD :: x2 :: x3 :: x4 :: _ =>
          val newMemory = memory.updated(x4, memory(x2) + memory(x3))
          println(s"[+ Updated $x4 with ${memory(x2) + memory(x3)} ] " + memory)
          go(newMemory.drop(toDrop), toDrop + 4, newMemory)
        case MULTIPLY :: x2 :: x3 :: x4 :: _ =>
          val newMemory = memory.updated(x4, memory(x2) * memory(x3))
          println(s"[* Updated $x4 with ${memory(x2) + memory(x3)}] " + memory)
          go(newMemory.drop(toDrop), toDrop + 4, newMemory)
        case TERMINATE :: _ =>
          println("[Terminated result] " + memory)
          memory
        case x =>
          println(s"WOOOOPS! YOU GONE AND MESSED UP for input: $x")
          List.empty
      }
    }
    go(input, 0, input)
  }

  def getInputList(path: Try[String]) = path match {
    case Success(foundPath) =>
      for {
        file    <- Source.fromFile(foundPath).getLines()
        newList <- file.split(",")
      } yield newList.toInt
  }
  println("initial state = " + initialListUpdate)

  intCodeComputer(initialListUpdate)
}
