package org.whsv26.reporter

import AggregateFunctions.*
import ImplicitConversions.given
import compiling.FormulaCompiler
import datasource.*
import fact.OrdersInApprovedStatusPercent
import org.scalatest.*
import org.scalatest.flatspec.*
import org.scalatest.matchers.*
import scala.collection.mutable.Stack
import scala.language.implicitConversions

class CompilerTest extends AnyFlatSpec with should.Matchers {
  behavior of ("Compiler")

  it should "handle dependent metrics for different contexts" in {
    compiling.Compiler.compile(OrdersInApprovedStatusPercent, OrderSource()) should be {
      "100 * countIf(`order_id`, `status` = 'APPROVED') / count(`order_id`)"
    }
    compiling.Compiler.compile(OrdersInApprovedStatusPercent, EventSource()) should be {
      "countDistinctIf(`order_id`, `status_to` = 'APPROVED') * 100 / countDistinct(`order_id`)"
    }
  }
}
