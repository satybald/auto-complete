package com.satybald

import java.util
import scala.collection.JavaConverters._
import scala.collection.mutable.{Map => MMap}

/**
  * Implementation of Auto suggestion based on Radix(Prefix) Tree. https://en.wikipedia.org/wiki/Trie
  * @param dictionary to build a trie on
  */
class TrieAutoComplete(dictionary: List[String]) extends AutoComplete {
  private val root = buildTrie(dictionary)
  private var current = root

  override def search(char: Char): List[String] = char match {
    case '#' => //End of line
      val result = current.getSuggestions()
      current = root
      result
    case _ =>
      current = current.next(char.toLower)
      current.getSuggestions()
  }

  /**
    * Add a dictionary to the trie.
    * @param dictionary
    * @return a root of the trie
    */
  private def buildTrie(dictionary: List[String]): TrieNode = {
    val node = new TrieNode()
    dictionary.foreach { word =>
      var current = node
      for (char <- word) {
        current = current.next(char)
        current.addWord(word)
      }
    }
    node
  }
}

object TrieAutoComplete {
  val defaultDictionary = List(
    "Pandora",
    "Pinterest",
    "Paypal",
    "Pg&e",
    "Project free tv Priceline",
    "Press democrat",
    "Progressive",
    "Project runway",
    "Proactive",
    "Programming",
    "Progeria",
    "Progesterone",
    "Progenex",
    "Procurable",
    "Processor",
    "Proud",
    "Print",
    "Prank",
    "Bowl",
    "Owl",
    "River"
  )

  def apply(dictionary: List[String] = defaultDictionary) =
    new TrieAutoComplete(dictionary.map(_.toLowerCase))
}

/**
  * Internal Trie node to maintains a pointers to the neighboring nodes
  */
class TrieNode {
  private val nodes = MMap.empty[Char, TrieNode]
  private val suggestion = new util.TreeSet[String]()

  /**
    * Finds auto suggestion for the given prefix
    */
  def getSuggestions(): List[String] = suggestion.asScala.toList

  /**
    * Returns the next node given a current symbol
    */
  def next(char: Char): TrieNode = {
    nodes.getOrElseUpdate(char, new TrieNode())
  }

  /**
    * Add word to the data structure
    */
  def addWord(word: String): Unit = {
    suggestion.add(word)
    trimSize()
  }

  /**
    * Removes unnesary words from suggestion
    */
  private def trimSize(n: Int = 4) = {
    while (suggestion.size() > n) {
      suggestion.pollLast()
    }
  }
}
