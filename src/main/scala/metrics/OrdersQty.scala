package org.whsv26.reporter
package metrics

import AggregateFunction.*
import ImplicitConversions.given
import scala.language.implicitConversions

given OrderSourceMetric[OrdersQty.type] with
  def formula: Formula = 
    count(ctx.OrderId)

given EventSourceMetric[OrdersQty.type] with
  def formula: Formula =
    countDistinct(ctx.OrderId)
