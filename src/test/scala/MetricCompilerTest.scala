package org.whsv26.reporter

import AggregateFunctions.*
import compiling.FormulaCompiler
import datasource.*
import dimension.*
import fact.*
import org.scalatest.*
import org.scalatest.flatspec.*
import org.scalatest.matchers.*
import scala.collection.mutable.Stack

class MetricCompilerTest extends AnyFlatSpec with should.Matchers {
  behavior of ("Metric compiler")

  it should "compile dependent metrics" in {
    compiling.Compiler.compile(OrdersInApprovedStatusPercent, OrderSource()) should be {
      "100 * countIf(`order_id`, `status` = 'APPROVED') / count(`order_id`)"
    }
    compiling.Compiler.compile(OrdersInApprovedStatusPercent, EventSource()) should be {
      "countDistinctIf(`order_id`, `status_to` = 'APPROVED') * 100 / countDistinct(`order_id`)"
    }
  }
}
