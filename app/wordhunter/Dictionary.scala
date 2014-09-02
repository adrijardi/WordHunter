package wordhunter

import java.io
import java.io.{File, IOException, FileReader, BufferedReader}


class Dictionary(private val data: List[String]) {

  def find(reg: WordRegex) = {
    println("Size: "+data.size)
    data.filter(reg.filter).sorted.distinct
  }
}

object Dictionary {

  def load(name: String): Option[Dictionary] = {
    try {
      println("Reading dictionary: "+new File(s"dictionaries/$name").getAbsolutePath)
      val in = new BufferedReader(new FileReader(s"dictionaries/$name"))
      Some(new Dictionary(Stream.continually(in.readLine()).takeWhile(_ != null).toList))
    } catch {
      case _: IOException => None
    }
  }
}
