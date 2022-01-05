package org.whsv26.reporter
package metrics

import Aggregate.*
import ImplicitConversions.given
import MetricName.*
import OrderStatus.*

import scala.language.implicitConversions

given OrderSourceMetric[OrdersInCanceledStatusPercent.type] with {
  def formula: Formula = {
    OrdersInCanceledStatusQty % OrdersQty
  }
}

given EventSourceMetric[OrdersInCanceledStatusPercent.type] with {
  def formula: Formula = {
    OrdersInCanceledStatusQty * 100 / OrdersQty
  }
}
