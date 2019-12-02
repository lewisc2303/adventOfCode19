package day1

import cats.effect.IO
import scala.io.Source

object Day1 extends App {

  val path: IO[String] = IO(getClass.getClassLoader.getResource("input.txt").getFile)

  def calculateFuel(mass: Int) = math.floor(mass / 3).toInt - 2

  val fuels: IO[Unit] =
    for {
      fileString <- path
      lines      <- IO(Source.fromFile(fileString).getLines())
      fuel       <- IO(lines.map(line => calculateFuel(line.toInt)))
    } yield println(fuel.sum)

  fuels.unsafeRunSync()

}
