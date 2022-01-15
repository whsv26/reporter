package org.whsv26.reporter
package compiling

import Formulas.*
import Formulas.AggregateFunctions.*
import Formulas.Arithmetics.*
import Formulas.Predicates.*
import datasource.*
import fact.*
import fact.metrics.given

object MetricCompiler {
  def compile(metric: Metric, source: DataSource): String = (metric, source) match {
    case (m: OrdersQty.type, s: OrderSource) => compile(formula(m, s))
    case (m: OrdersQty.type, s: EventSource) => compile(formula(m, s))
    case (m: OrdersInApprovedStatusQty.type, s: OrderSource) => compile(formula(m, s))
    case (m: OrdersInApprovedStatusQty.type, s: EventSource) => compile(formula(m, s))
    case (m: OrdersInApprovedStatusPercent.type, s: OrderSource) => compile(formula(m, s))
    case (m: OrdersInApprovedStatusPercent.type, s: EventSource) => compile(formula(m, s))
    case (m: OrdersInSpamStatusQty.type, s: OrderSource) => compile(formula(m, s))
    case (m: OrdersInSpamStatusQty.type, s: EventSource) => compile(formula(m, s))
    case (m: OrdersInSpamStatusPercent.type, s: OrderSource) => compile(formula(m, s))
    case (m: OrdersInSpamStatusPercent.type, s: EventSource) => compile(formula(m, s))
    case (m: OrdersInCanceledStatusQty.type, s: OrderSource) => compile(formula(m, s))
    case (m: OrdersInCanceledStatusQty.type, s: EventSource) => compile(formula(m, s))
    case (m: OrdersInCanceledStatusPercent.type, s: OrderSource) => compile(formula(m, s))
    case (m: OrdersInCanceledStatusPercent.type, s: EventSource) => compile(formula(m, s))
  }

  private def formula[M <: Metric, S <: DataSource](
    metric: M,
    source: S
  )(using
    cm: ContextualMetric[M, S]
  ): Formula = cm.formula

  private def compile(formula: Formula): String = FormulaCompiler.compile(formula)
}
