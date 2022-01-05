package org.whsv26.reporter

import metrics.given
import MetricName.*
import Ast.Predicate.*
import Ast.Aggregate.*
import Ast.Arithmetic.*
import Ast.*

object Compiler {
  def compile(name: MetricName, src: DataSource): String = {
    def isOrderSource(src: DataSource) = src.isInstanceOf[OrderSource]
    def isEventSource(src: DataSource) = src.isInstanceOf[EventSource]

    name match {
      case OrdersQty if isOrderSource(src) => compile[OrdersQty.type, OrderSource]
      case OrdersQty if isEventSource(src) => compile[OrdersQty.type, EventSource]
      case OrdersInApprovedStatusQty if isOrderSource(src) => compile[OrdersInApprovedStatusQty.type, OrderSource]
      case OrdersInApprovedStatusQty if isEventSource(src) => compile[OrdersInApprovedStatusQty.type, EventSource]
      case OrdersInApprovedStatusPercent if isOrderSource(src) => compile[OrdersInApprovedStatusPercent.type, OrderSource]
      case OrdersInApprovedStatusPercent if isEventSource(src) => compile[OrdersInApprovedStatusPercent.type, EventSource]
      case OrdersInSpamStatusQty if isOrderSource(src) => compile[OrdersInSpamStatusQty.type, OrderSource]
      case OrdersInSpamStatusQty if isEventSource(src) => compile[OrdersInSpamStatusQty.type, EventSource]
      case OrdersInSpamStatusPercent if isOrderSource(src) => compile[OrdersInSpamStatusPercent.type, OrderSource]
      case OrdersInSpamStatusPercent if isEventSource(src) => compile[OrdersInSpamStatusPercent.type, EventSource]
      case OrdersInCanceledStatusQty if isOrderSource(src) => compile[OrdersInCanceledStatusQty.type, OrderSource]
      case OrdersInCanceledStatusQty if isEventSource(src) => compile[OrdersInCanceledStatusQty.type, EventSource]
      case OrdersInCanceledStatusPercent if isOrderSource(src) => compile[OrdersInCanceledStatusPercent.type, OrderSource]
      case OrdersInCanceledStatusPercent if isEventSource(src) => compile[OrdersInCanceledStatusPercent.type, EventSource]
    }
  }

  def compile[M <: MetricName, S <: DataSource](using cm: ContextualMetric[M, S]): String = {
    compile(cm.formula)
  }

  def compile(formula: Formula): String = {
    formula match {
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
      case Field(fld) => fld.toSnakeCase
      case Value(v) => v match {
        case status: OrderStatus => s"'${status.toString.toScreamingSnakeCase}'"
        case str: String => s"'$str'"
        case default => default.toString
      }
    }
  }
}
