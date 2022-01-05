package org.whsv26.reporter
package metrics

import Aggregate.*
import MetricName.*
import ImplicitConversions.given
import scala.language.implicitConversions
import OrderStatus.*

given OrderSourceMetric[OrdersInApprovedStatusQty.type] with {
  def formula: Formula = {
    countIf(field.OrderId, field.Status === Approved)
  }
}

given EventSourceMetric[OrdersInApprovedStatusQty.type] with {
  def formula: Formula = {
    countDistinctIf(field.OrderId, field.StatusTo === Approved)
  }
}
