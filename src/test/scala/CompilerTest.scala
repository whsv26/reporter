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

object F {
  def _sum: Formula = {
    sum(OrderField.OrderId)
  }
  def _sumIf: Formula = {
    sumIf(OrderField.OrderId, OrderField.OrderId === 1)
  }
  def _sumIfWithOrderField: Formula = {
    sumIf(OrderField.OrderId, OrderField.OrderId === 1)
  }
  def _sumIfWithEventField: Formula = {
    sumIf(EventField.EventId, EventField.OrderId === 1)
  }
}

class CompilerTest extends AnyFlatSpec with should.Matchers {
  "Compiler" should "produce correct output" in {
    FormulaCompiler.compile(F._sum) should be ("sum(`order_id`)")
    FormulaCompiler.compile(F._sumIf) should be ("sumIf(`order_id`, `order_id` = 1)")
  }

  "Compiler" should "convert contextual field to snake case" in {
    FormulaCompiler.compile(F._sumIfWithOrderField) should be ("sumIf(`order_id`, `order_id` = 1)")
    FormulaCompiler.compile(F._sumIfWithEventField) should be ("sumIf(`event_id`, `order_id` = 1)")
  }

  "Compiler" should "handle dependent metrics" in {
    compiling.Compiler.compile(OrdersInApprovedStatusPercent, OrderSource()) should be {
      "100 * countIf(`order_id`, `status` = 'APPROVED') / count(`order_id`)"
    }
    compiling.Compiler.compile(OrdersInApprovedStatusPercent, EventSource()) should be {
      "countDistinctIf(`order_id`, `status_to` = 'APPROVED') * 100 / countDistinct(`order_id`)"
    }
  }
}
