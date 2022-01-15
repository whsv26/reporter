package org.whsv26.reporter

import Formulas.Predicates.*
import Formulas.AggregateFunctions.*
import Formulas.Arithmetics.*
import Formulas.*
import metrics.given

object Compiler {
  def formula[M <: MetricName, S <: DataSource](
    metric: M,
    source: S
  )(using
    cm: ContextualMetric[M, S]
  ): Formula = cm.formula

  def compile(name: MetricName, source: DataSource): String = (name, source) match {
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

  def compile(formula: Formula): String = formula match {
    case Eq(fld, v) => "%s = %s" format(compile(fld), compile(v))
    case In(fld, v) => "%s IN %s" format(compile(fld), v.map(compile).mkString("(", ",", ")"))
    case Sum(fld) => "sum(%s)" format compile(fld)
    case Count(fld) => "count(%s)" format compile(fld)
    case CountDistinct(fld) => "countDistinct(%s)" format compile(fld)
    case CountDistinctIf(fld, p) => "countDistinctIf(%s, %s)" format(compile(fld), compile(p))
    case CountIf(fld, p) => "countIf(%s, %s)" format(compile(fld), compile(p))
    case SumIf(fld, p) => "sumIf(%s, %s)" format(compile(fld), compile(p))
    case Plus(lhs, rhs) => "%s + %s" format(compile(lhs), compile(rhs))
    case Minus(lhs, rhs) => "%s - %s" format(compile(lhs), compile(rhs))
    case Mul(lhs, rhs) => "%s * %s" format(compile(lhs), compile(rhs))
    case Div(lhs, rhs) => "%s / %s" format(compile(lhs), compile(rhs))
    case Field(fld) => "`%s`" format fld.toSnakeCase
    case Value(v) => v match {
      case status: OrderStatus => s"'${status.toString.toScreamingSnakeCase}'"
      case str: String => s"'$str'"
      case default => default.toString
    }
  }
}
