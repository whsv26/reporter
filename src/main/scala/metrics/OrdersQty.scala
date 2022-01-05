package org.whsv26.reporter
package metrics

import Aggregate.*
import MetricName.*
import ImplicitConversions.given
import scala.language.implicitConversions

given OrderSourceMetric[OrdersQty.type] with {
  def formula: Formula = {
    count(field.OrderId)
  }
}

given EventSourceMetric[OrdersQty.type] with {
  def formula: Formula = {
    countDistinct(field.OrderId)
  }
}
