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
        Part1.twoDigitsAreTheSame(123456) shouldBe false
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

}
