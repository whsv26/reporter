package org.whsv26.reporter

import collection.mutable.Stack
import org.scalatest.*
import flatspec.*
import matchers.*
import org.whsv26.reporter.Aggregate.*
import org.whsv26.reporter.MetricName.*
import scala.language.implicitConversions
import ImplicitConversions.given
import metrics.given

object F {
  def _sum: Formula = {
    sum("order_id")
  }
  def _sumIf: Formula = {
    sumIf("order_id", "order_id" === 1)
  }
  def _sumIfWithOrderField: Formula = {
    sumIf(OrderField.OrderId, OrderField.OrderId === 1)
  }
  def _sumIfWithEventField: Formula = {
    sumIf(EventField.EventId, EventField.OrderId === 1)
  }
  def _dependent: Formula = {
    OrdersInApprovedStatusAvg.formula(OrdersSource)
  }
}

class CompilerTest extends AnyFlatSpec with should.Matchers {
  "Compiler" should "produce correct output" in {
    Compiler.compile(F._sum) should be ("sum(order_id)")
    Compiler.compile(F._sumIf) should be ("sumIf(order_id, order_id = 1)")
  }

  "Compiler" should "convert contextual field to snake case" in {
    Compiler.compile(F._sumIfWithOrderField) should be ("sumIf(order_id, order_id = 1)")
    Compiler.compile(F._sumIfWithEventField) should be ("sumIf(event_id, order_id = 1)")
  }

  "Compiler" should "handle dependent metrics" in {
    Compiler.compile(F._dependent) should be ("((countIf(order_id, status = 'APPROVED')) * (100)) / (count(order_id))")
  }
}
