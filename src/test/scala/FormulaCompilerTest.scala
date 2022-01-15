package org.whsv26.reporter

import AggregateFunctions.*
import compiling.FormulaCompiler
import datasource.*
import fact.*
import org.scalatest.*
import org.scalatest.flatspec.*
import org.scalatest.matchers.*
import scala.collection.mutable.Stack

class FormulaCompilerTest extends AnyFlatSpec with should.Matchers {
  behavior of ("Formula compiler")

  it should "produce SQL for aggregate functions" in {
    FormulaCompiler.compile(Cases._sum) should be {
      "sum(`order_id`)"
    }
    FormulaCompiler.compile(Cases._sumIf) should be {
      "sumIf(`order_id`, `order_id` = 1)"
    }
  }

  it should "convert contextual field to snake case" in {
    FormulaCompiler.compile(Cases._sumIfWithOrderField) should be {
      "sumIf(`order_id`, `order_id` = 1)"
    }
    FormulaCompiler.compile(Cases._sumIfWithEventField) should be {
      "sumIf(`event_id`, `order_id` = 1)"
    }
  }

  object Cases {
    import scala.language.implicitConversions
    import ImplicitConversions.given

    def _sum: Formula =
      sum(OrderField.OrderId)

    def _sumIf: Formula =
      sumIf(OrderField.OrderId, OrderField.OrderId === 1)

    def _sumIfWithOrderField: Formula =
      sumIf(OrderField.OrderId, OrderField.OrderId === 1)

    def _sumIfWithEventField: Formula =
      sumIf(EventField.EventId, EventField.OrderId === 1)
  }
}
