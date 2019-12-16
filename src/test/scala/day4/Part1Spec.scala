package day4

import org.scalatest.{FreeSpec, Matchers}

class Part1Spec extends FreeSpec with Matchers {

  "twoDigitsAreTheSame" - {
    "SHOULD return true for" - {
      "113456" in {
        Part1.twoDigitsAreTheSame(113456) shouldBe true
      }
      "153446" in {
        Part1.twoDigitsAreTheSame(153446) shouldBe true
      }
      "155346" in {
        Part1.twoDigitsAreTheSame(155346) shouldBe true
      }
      "135346" in {
        Part1.twoDigitsAreTheSame(135346) shouldBe false
      }
      "123456" in {
        Part1.twoDigitsAreTheSame(123456) shouldBe false
      }
      "1" in {
        Part1.twoDigitsAreTheSame(1) shouldBe false
      }
      "44" in {
        Part1.twoDigitsAreTheSame(44) shouldBe true
      }
    }
  }

  "inAscendingOrder" - {
    "123456" in {
      Part1.inAscendingOrder(123456) shouldBe true
    }
    "121456" in {
      Part1.inAscendingOrder(121456) shouldBe false
    }
    "111111" in {
      Part1.inAscendingOrder(111111) shouldBe true
    }
    "111123" in {
      Part1.inAscendingOrder(111123) shouldBe true
    }
    "135679" in {
      Part1.inAscendingOrder(135679) shouldBe true
    }
    "935679" in {
      Part1.inAscendingOrder(935679) shouldBe false
    }
  }

  "whichDigitsAreTheSame" - {
    "for 113456 should return 11" in {
      Part2.whichOnlyTwoDigitsAreTheSame(113456) shouldBe Right(1)
    }
    "153446 should return 44" in {
      Part2.whichOnlyTwoDigitsAreTheSame(153446) shouldBe Right(4)
    }
    "155346 should return 55" in {
      Part2.whichOnlyTwoDigitsAreTheSame(155346) shouldBe Right(5)
    }
    "135346 should return a Left" in {
      Part2.whichOnlyTwoDigitsAreTheSame(135346) shouldBe Left("No Double digits exist")
    }
    "123456 should return a Left" in {
      Part2.whichOnlyTwoDigitsAreTheSame(123456) shouldBe Left("No Double digits exist")
    }
    "1 should return a Left" in {
      Part2.whichOnlyTwoDigitsAreTheSame(1) shouldBe Left("No Double digits exist")
    }
    "111345 should return a Left" in {
      Part2.whichOnlyTwoDigitsAreTheSame(111345) shouldBe Left(1)
    }
    "111225 should return a Left" in {
      Part2.whichOnlyTwoDigitsAreTheSame(111122) shouldBe Left(1)
    }
    "44 should return a Right" in {
      Part2.whichOnlyTwoDigitsAreTheSame(44) shouldBe Right(4)
    }
    "334444 should return a Right" in {
      Part2.whichOnlyTwoDigitsAreTheSame(334444) shouldBe Right(3)
    }
  }

  "evaluate" - {
    "should contain 333344" in {
      Part2.evaluate(Part1.digitRange) should contain(333344)
    }
    "should contain 334444" in {
      Part2.evaluate(Part1.digitRange) should contain(334444)
    }
  }

}
