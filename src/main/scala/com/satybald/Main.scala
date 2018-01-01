package com.satybald

import scala.io.StdIn

/**
  * Main class to run in console TrieAutoComplete class.
  */
object Main extends App {
  println(
    s"""
      |
      | █████╗ ██╗   ██╗████████╗ ██████╗      ██████╗ ██████╗ ███╗   ███╗██████╗ ██╗     ███████╗████████╗███████╗
      |██╔══██╗██║   ██║╚══██╔══╝██╔═══██╗    ██╔════╝██╔═══██╗████╗ ████║██╔══██╗██║     ██╔════╝╚══██╔══╝██╔════╝
      |███████║██║   ██║   ██║   ██║   ██║    ██║     ██║   ██║██╔████╔██║██████╔╝██║     █████╗     ██║   █████╗
      |██╔══██║██║   ██║   ██║   ██║   ██║    ██║     ██║   ██║██║╚██╔╝██║██╔═══╝ ██║     ██╔══╝     ██║   ██╔══╝
      |██║  ██║╚██████╔╝   ██║   ╚██████╔╝    ╚██████╗╚██████╔╝██║ ╚═╝ ██║██║     ███████╗███████╗   ██║   ███████╗
      |╚═╝  ╚═╝ ╚═════╝    ╚═╝    ╚═════╝      ╚═════╝ ╚═════╝ ╚═╝     ╚═╝╚═╝     ╚══════╝╚══════╝   ╚═╝   ╚══════╝
      |
      |Default dictionary:
      |${TrieAutoComplete.defaultDictionary.mkString(", ")}
      |
      |Please enter char. If you want to start from the beginning please press #. Empty string for exit
    """.stripMargin)

    val autoComplete = TrieAutoComplete()
    Iterator.continually(StdIn.readLine())
    .takeWhile(line => line.nonEmpty)
    .foreach{line =>
      val last = line.last
      println(s"Your input: ${last}")
      println(autoComplete.search(last))
    }

}
