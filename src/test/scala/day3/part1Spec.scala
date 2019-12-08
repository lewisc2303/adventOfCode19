package day3

import day3.Part1.{Command, DOWN, LEFT, RIGHT, UP}
import org.scalatest.{FreeSpec, Matchers}

class part1Spec extends FreeSpec with Matchers {

  "pathToCoordinates" - {
    "should produce a set of all Coordinates along a path from A to B going RIGHT on the x-axis" in {
      val command                  = Command(RIGHT, 20)
      val output: List[(Int, Int)] = (0 to 20).map(x => (x, 0)).toList
      Part1.pathToCoordinates((0, 0), List.empty, command) shouldBe (output, (20, 0))
    }
    "should produce a set of all Coordinates along a path from A to B going LEFT on the x-axis" in {
      val command                  = Command(LEFT, 20)
      val output: List[(Int, Int)] = (-20 to 0).reverse.map(x => (x, 0)).toList
      Part1.pathToCoordinates((0, 0), List.empty, command) shouldBe (output, (-20, 0))
    }
    "should produce a set of all Coordinates along a path from A to B going UP on the y-axis" in {
      val command                  = Command(UP, 30)
      val output: List[(Int, Int)] = (0 to 30).map(x => (0, x)).toList
      Part1.pathToCoordinates((0, 0), List.empty, command) shouldBe (output, (0, 30))
    }
    "should produce a set of all Coordinates along a path from A to B going DOWN on the y-axis" in {
      val command                  = Command(DOWN, 5)
      val output: List[(Int, Int)] = (-5 to 0).reverse.map(x => (0, x)).toList
      Part1.pathToCoordinates((0, 0), List.empty, command) shouldBe (output, (0, -5))
    }
    "should produce a set of all Coordinates along a path from A to B going DOWN on the y-axis from non (0,0)" in {
      val command                  = Command(DOWN, 15)
      val output: List[(Int, Int)] = (-15 to 0).toList.map(x => (6, 10 + x)).reverse
      Part1.pathToCoordinates((6, 10), List.empty, command) shouldBe (output, (6, -5))
    }
    "should produce a set of all Coordinates along a path from A to B going UP on the y-axis from non (0,0)" in {
      val command                  = Command(UP, 1)
      val output: List[(Int, Int)] = List((4, -2), (4, -1))
      Part1.pathToCoordinates((4, -2), List.empty, command) shouldBe (output, (4, -1))
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
  "getCoordinatesVisited" - {
    "should create a set of all the coordinates visited example 1" in {
      val wireOne = List("R2", "U1", "L3", "D1")
      val visited = List((0, 0), (1, 0), (2, 0), (2, 1), (1, 1), (0, 1), (-1, 1), (-1, 0))
      Part1.getCoordinatedVisited(wireOne).toSet should contain theSameElementsAs visited
    }

    "should create a set of all the coordinates visited example 2" in {
      val wireOne = List("D2", "L1", "L3", "U1")
      val visited = List((0, 0), (0, -1), (0, -2), (-1, -2), (-2, -2), (-3, -2), (-4, -2), (-4, -1))
      Part1.getCoordinatedVisited(wireOne).toSet should contain theSameElementsAs visited
    }
  }
  "findMinimumDistance" - {
    "should find the minimum distance to the intersection of 2 wires" in {
      val wireOne   = List("R8", "U5", "L5", "D3")
      val wireTwo   = List("U7", "R6", "D4", "L4")
      val distances = Part1.findMinimumDistance(wireOne, wireTwo)
      println("distances = " + distances)
      distances shouldBe 6
    }
  }
}
