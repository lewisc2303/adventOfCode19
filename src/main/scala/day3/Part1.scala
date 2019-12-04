package day3

object Part1 {

  type Coordinate = (Int, Int)

  sealed trait Direction
  case object UP    extends Direction
  case object DOWN  extends Direction
  case object LEFT  extends Direction
  case object RIGHT extends Direction

  case class Command(direction: Direction, distance: Int)

  private def toTypeDirection(rawData: Char): Option[Direction] =
    rawData match {
      case 'R' => Some(RIGHT)
      case 'L' => Some(LEFT)
      case 'U' => Some(UP)
      case 'D' => Some(DOWN)
      case _   => None
    }

  def rawDataToCommand(rawData: String): Command = {
    Command(toTypeDirection(rawData.head).get, rawData.drop(1).toInt)
  }

  def pathToCoordinates(position: Coordinate,
                        visited: Set[Coordinate],
                        command: Command): (Set[Coordinate], Coordinate) = {
    command.direction match {
      case RIGHT =>
        val newXCoordinate = command.distance - position._1
        ((position._1 to newXCoordinate).map(x => (x, position._2)).toSet ++ visited,
         (newXCoordinate, position._2))
      case LEFT =>
        val newXCoordinate = position._1 - command.distance
        ((position._1 to newXCoordinate).map(x => (x, position._2)).toSet ++ visited,
         (newXCoordinate, position._2))
      case UP =>
        val newYCoordinate = command.distance - position._2
        ((position._2 to newYCoordinate).map(y => (position._1, y)).toSet ++ visited,
         (position._1, newYCoordinate))
      case DOWN =>
        val newYCoordinate = position._2 - command.distance
        ((position._2 to newYCoordinate).map(y => (position._1, y)).toSet ++ visited,
         (position._1, newYCoordinate))
    }
  }

}
