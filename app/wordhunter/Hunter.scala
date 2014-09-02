package wordhunter

/**
 * Manages dictionaries and dispatches lookup calls
 */
object Hunter {
  
  private var dictionaries: Map[String, Dictionary] = Map()

  def findDictionary(name: String): Option[Dictionary] = {
    val d = dictionaries.get(name) match {
      case None => {
        val dictionary = Dictionary.load(name)
        dictionary.map{ dict =>
          dictionaries = dictionaries + (name -> dict)
        }
        dictionary
      }
      case d => d
    }
    println("Dictionary: "+d)
    d
  }

  def lookup(dictionary: String, regex: WordRegex) = {
    findDictionary(dictionary).map(_.find(regex))
  }
}
