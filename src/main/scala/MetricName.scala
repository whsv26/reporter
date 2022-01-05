package org.whsv26.reporter

enum MetricName {
  case OrdersQty
  case OrdersInApprovedStatusQty
  case OrdersInApprovedStatusPercent
  case OrdersInSpamStatusQty
  case OrdersInSpamStatusAvg
  case OrdersInCanceledStatusQty
  case OrdersInCanceledStatusAvg

  def formula[C <: FieldContext, S <: DataSource[C]](source: S)(using cm: ContextualMetric[this.type, C]): Formula = {
    cm.formula
  }
}
