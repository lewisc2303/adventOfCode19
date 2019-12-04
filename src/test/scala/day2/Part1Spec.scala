package day2

import day2.Part1.intCodeComputer
import org.scalatest.{FreeSpec, Matchers}

class Part1Spec extends FreeSpec with Matchers {
  /*
1,0,0,0,99 becomes 2,0,0,0,99 (1 + 1 = 2).
2,3,0,3,99 becomes 2,3,0,6,99 (3 * 2 = 6).
2,4,4,5,99,0 becomes 2,4,4,5,99,9801 (99 * 99 = 9801).
1,1,1,4,99,5,6,0,99 becomes 30,1,1,4,2,5,6,0,99.
   */

  //test does not work as it cannot deal with cases which terminate on the last element
  "intCodeComputer should solve according to examples given from brief" - {
    "1,0,0,0,99 => 2,0,0,0,99" in {
      val input  = List(1, 0, 0, 0, 99)
      val output = List(2, 0, 0, 0, 99)
      intCodeComputer(input) shouldBe output
    }

  }

}
