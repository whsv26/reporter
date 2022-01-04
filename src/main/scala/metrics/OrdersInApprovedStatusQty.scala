package org.whsv26.reporter
package metrics

import Aggregate.*
import MetricName.*
import ImplicitConversions.given
import scala.language.implicitConversions
import OrderStatus.*

given ContextualMetric[OrdersInApprovedStatusQty.type, OrderField.type] with {
  def formula(c: OrderField.type): Formula = {
    countIf(c.OrderId, c.Status === Approved)
  }
}

given ContextualMetric[OrdersInApprovedStatusQty.type, EventField.type] with {
  def formula(c: EventField.type): Formula = {
    countDistinctIf(c.OrderId, c.StatusTo === Approved)
  }
}
