package day1

import cats.effect.IO
import day1.Part1.calculateFuel

import scala.annotation.tailrec
import scala.io.Source

object Part2 extends App {

  val path: IO[String] = IO(getClass.getClassLoader.getResource("day1Input.txt").getFile)

  @tailrec
  def fuelOfFuel(mass: Int, totalFuel: Int): Int = {
    calculateFuel(mass) match {
      case newFuel if newFuel <= 0 => totalFuel
      case newFuel                 => fuelOfFuel(newFuel, newFuel + totalFuel)
    }
  }

  def fuelFromMass(filepath: IO[String]) =
    for {
      fileString    <- filepath
      lines         <- IO(Source.fromFile(fileString).getLines())
      fuelPerModule <- IO(lines.map(line => calculateFuel(line.toInt)))
      totalFuel     <- IO(fuelPerModule.map(fuel => fuelOfFuel(fuel, fuel)))
      _             <- IO(println(totalFuel.sum))
    } yield ()

  fuelFromMass(path).unsafeRunSync()

}
