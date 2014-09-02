package wordhunter

/**
 * Defines the restrictions to make a word search
 */
class WordRegex(private val regex: String) {
  def filter(word: String): Boolean = word.matches(regex)
}

object WordRegex {
  def apply(str: String) = new WordRegex("^"+str.replace("*",".*").replace("?",".?").replace("_",".{1}")+"$")
}
