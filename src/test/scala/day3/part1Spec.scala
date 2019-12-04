package day3

import day3.Part1.{Command, DOWN, LEFT, RIGHT, UP}
import org.scalatest.{FreeSpec, Matchers}

class part1Spec extends FreeSpec with Matchers {

  "pathToCoordinates" - {
    "should produce a set of all Coordinates along a path from A to B going RIGHT on the x-axis" in {
      val command                 = Command(RIGHT, 20)
      val output: Set[(Int, Int)] = (0 to 20).map(x => (x, 0)).toSet
      Part1.pathToCoordinates((0, 0), Set.empty, command) shouldBe (output, (20, 0))
    }
    "should produce a set of all Coordinates along a path from A to B going LEFT on the x-axis" in {
      val command                 = Command(LEFT, 20)
      val output: Set[(Int, Int)] = (0 to -20).map(x => (x, 0)).toSet
      Part1.pathToCoordinates((0, 0), Set.empty, command) shouldBe (output, (-20, 0))
    }
    "should produce a set of all Coordinates along a path from A to B going UP on the y-axis" in {
      val command                 = Command(UP, 30)
      val output: Set[(Int, Int)] = (0 to 30).map(x => (0, x)).toSet
      Part1.pathToCoordinates((0, 0), Set.empty, command) shouldBe (output, (0, 30))
    }
    "should produce a set of all Coordinates along a path from A to B going DOWN on the y-axis" in {
      val command                 = Command(DOWN, 5)
      val output: Set[(Int, Int)] = (0 to -5).map(x => (0, x)).toSet
      Part1.pathToCoordinates((0, 0), Set.empty, command) shouldBe (output, (0, -5))
    }
  }
  "rawDataToCommand" - {
    "should convert a rawData with RIGHT to a Command Type of RIGHT" in {
      val command = Command(RIGHT, 36)
      Part1.rawDataToCommand("R36") shouldBe command
    }
    "should convert a rawData with UP to a Command Type of UP" in {
      val command = Command(UP, 100)
      Part1.rawDataToCommand("U100") shouldBe command
    }
  }
}
