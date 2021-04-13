package com.acervera.performance

import better.files._
import org.scalatest.matchers.must.Matchers
import org.scalatest.matchers.should.Matchers._
import org.scalatest.wordspec.AnyWordSpecLike

class ParserSpec extends AnyWordSpecLike with Matchers {

  "Parser" should {
    "parse line correctly" in {
      val jsonLine = Parser.parser(File("src/test/resources/ex_1.json").contentAsString)
      jsonLine.tags.size shouldBe 3
      jsonLine.nodes.size shouldBe 4
      jsonLine.nodes(0).tags.size shouldBe 2
      jsonLine.nodes(1).tags.size shouldBe 0
    }
  }
}
