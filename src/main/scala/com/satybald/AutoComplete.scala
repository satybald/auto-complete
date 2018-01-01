package com.satybald

/**
  * A trait that all implementations of auto-suggest should inherit.
  */
trait AutoComplete {

  /**
    * Accepts an user input char, and oututs auto suggestion.
    *
    * @param char input parameter
    * @return List of auto suggestions, words are lexiographically sorted.
    */
  def search(char: Char): List[String]
}
