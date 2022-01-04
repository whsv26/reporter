package org.whsv26.reporter

import collection.mutable.Stack
import org.scalatest.*
import flatspec.*
import matchers.*
import org.whsv26.reporter.Aggregate.*
import scala.language.implicitConversions
import Conversions.given

object F {
  def _sum: Formula = sum("order_id")
  def _sumIf: Formula = sumIf("order_id", "order_id" === 1)
}

class CompilerTest extends AnyFlatSpec with should.Matchers {
  "Compiler" should "produce correct output" in {
    Compiler.compile(F._sum) should be ("sum(order_id)")
    Compiler.compile(F._sumIf) should be ("sumIf(order_id, order_id = 1)")
  }
}
