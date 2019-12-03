package day1

import cats.effect.IO
import day1.Part1.{calculateFuel, fuelFromMass}

import scala.annotation.tailrec

object Part2 {

  val path: IO[String] = IO(getClass.getClassLoader.getResource("input.txt").getFile)

  @tailrec
  def fuelOfFuel(mass: Int, totalFuel: Int): Int = {
    calculateFuel(mass) match {
      case newFuel if newFuel <= 0 => totalFuel
      case newFuel                 => fuelOfFuel(newFuel, newFuel + totalFuel)
    }
  }

  val printTotalFuel = for {
    fuelMass  <- fuelFromMass(path)
    totalFuel <- IO(fuelOfFuel(fuelMass, fuelMass))
    _         <- IO(println("Part 2: Total Fuel = " + totalFuel))
  } yield ()

  printTotalFuel.unsafeRunSync()

}
