import scala.io.Source

trait SolutionInput {

  def getInputList(filename: String) =
    for {
      filename <- Option(getClass.getClassLoader.getResource(filename).getFile)
      list = Source.fromFile(filename).getLines()
    } yield list

}
