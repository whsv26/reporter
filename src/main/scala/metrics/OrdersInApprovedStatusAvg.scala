package org.whsv26.reporter
package metrics

import Aggregate.*
import ImplicitConversions.given
import MetricName.*
import OrderStatus.*
import scala.language.implicitConversions

given OrderSourceMetric[OrdersInApprovedStatusAvg.type] with {
  given DataSource[OrderField.type] = OrderSource
  def formula: Formula = {
    OrdersInApprovedStatusQty * 100 / OrdersQty
  }
}

given EventSourceMetric[OrdersInApprovedStatusAvg.type] with {
  given DataSource[EventField.type] = EventSource
  def formula: Formula = {
    OrdersInApprovedStatusQty * 100 / OrdersQty
  }
}
