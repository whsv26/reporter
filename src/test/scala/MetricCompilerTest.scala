package org.whsv26.reporter

import infrastructure.datasource.*
import infrastructure.computation.*
import infrastructure.compiling.*
import domain.*
import org.scalatest.*
import org.scalatest.flatspec.*
import org.scalatest.matchers.*
import scala.collection.mutable.Stack

class MetricCompilerTest extends AnyFlatSpec with should.Matchers {
  behavior of ("Metric compiler")

  it should "compile dependent metrics" in {
    MetricCompiler.compile(OrdersInApprovedStatusPercent, OrderSource()) should be {
      "100 * countIf(`order_id`, `status` = 'APPROVED') / count(`order_id`)"
    }
    MetricCompiler.compile(OrdersInApprovedStatusPercent, EventSource()) should be {
      "countDistinctIf(`order_id`, `status_to` = 'APPROVED') * 100 / countDistinct(`order_id`)"
    }
  }
}
