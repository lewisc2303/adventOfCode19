package day1

import cats.effect.IO
import scala.io.Source

object Part1 extends App {

  val path: IO[String] = IO(getClass.getClassLoader.getResource("day1Input.txt").getFile)

  def calculateFuel(mass: Int) = math.floor(mass / 3).toInt - 2

  def fuelFromMass(filepath: IO[String]): IO[Int] =
    for {
      fileString <- filepath
      lines      <- IO(Source.fromFile(fileString).getLines())
      fuel       <- IO(lines.map(line => calculateFuel(line.toInt)))
    } yield fuel.sum

  fuelFromMass(path)
    .flatMap(x => IO(println("Part 1: Total Fuel = " + x)))
    .unsafeRunAsyncAndForget()

}
