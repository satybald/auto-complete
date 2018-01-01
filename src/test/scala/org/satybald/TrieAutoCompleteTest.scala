package org.satybald

import com.satybald.TrieAutoComplete
import org.scalatest.{FlatSpec, Matchers}

class TrieAutoCompleteTest extends FlatSpec with Matchers {
  val testDictionary = List("pp", "a", "aa", "bb", "c",
    "prppp", "praaaa", "probbbb", "proaaaa",  "progressive",
    "pandora", "paypal",
    "pg&e", "pinterest")

  "Empty Trie" should "return empty match" in {
    val emptyTrie = TrieAutoComplete(List())
    emptyTrie.search('A') should be(List())
    emptyTrie.search('\n') should be(List())
  }

  "Trie" should "match symbols according to dictionalry" in {
    val trie = TrieAutoComplete(testDictionary)
    trie.search('p') should be(List("pandora", "paypal", "pg&e", "pinterest"))
    trie.search('r') should be (List("praaaa", "proaaaa", "probbbb", "progressive"))
    trie.search('o') should be (List("proaaaa", "probbbb", "progressive"))
  }

}
