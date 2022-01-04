package org.whsv26.reporter
package metrics

import Aggregate.*
import ImplicitConversions.given
import MetricName.*
import OrderStatus.*
import scala.language.implicitConversions

given ContextualMetric[OrdersInApprovedStatusAvg.type, OrderField.type] with {
  given DataSource[OrderField.type] = OrdersSource
  def formula(c: OrderField.type): Formula = {
    OrdersInApprovedStatusQty * 100 / OrdersQty
  }
}

given ContextualMetric[OrdersInApprovedStatusAvg.type, EventField.type] with {
  given DataSource[EventField.type] = EventsSource
  def formula(c: EventField.type): Formula = {
    OrdersInApprovedStatusQty * 100 / OrdersQty
  }
}
