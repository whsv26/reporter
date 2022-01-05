package org.whsv26.reporter

enum MetricName {
  case OrdersQty
  case OrdersInApprovedStatusQty
  case OrdersInApprovedStatusPercent
  case OrdersInSpamStatusQty
  case OrdersInSpamStatusAvg
  case OrdersInCanceledStatusQty
  case OrdersInCanceledStatusAvg
  def formula[S <: DataSource](using cm: ContextualMetric[this.type, S]): Formula = {
    cm.formula
  }
}
