package wordhunter

import java.io._

/**
 * Allows parsing Wordnet formatted files to the internal format
 */
object DictionaryParser {

  def parse(dictionaryName: String, dir: String) = {
    def parsed(str: String): Stream[String] = parseFile(new BufferedReader(new FileReader(str)))

    storeFile(dictionaryName, getDataFiles(dir).map(parsed).foldLeft(Stream[String]())(_ ++ _))
  }

  def getDataFiles(dir: String): List[String] = {
    new File(dir).listFiles(new FilenameFilter {
      override def accept(dir: File, name: String): Boolean = name.startsWith("data.")
    }).toList.map(_.getAbsolutePath)
  }

  def parseFile(in: BufferedReader): Stream[String] = {
    parseFile(Stream.continually(in.readLine()).takeWhile(_ != null))
  }

  def parseFile(stream: Stream[String]): Stream[String] = stream.filter(_.charAt(0) != ' ').map(getWordFromLine).map(_.replace("_", " "))

  def getWordFromLine(str:String): String = {
    str.split(" ")(4)
  }

  def storeFile(dictionaryName: String, stream: Stream[String]) = {
    val newDictionary = new File(dictionaryName)
    if(newDictionary.exists())
      throw new IllegalArgumentException("File already exists")

    val out = new BufferedWriter(new FileWriter(newDictionary))
    stream.map(_+"\n").foreach(out.write)
    out.close()
  }
}
