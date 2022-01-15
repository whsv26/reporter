package org.whsv26.reporter
package infrastructure.metrics

import domain.*
import infrastructure.datasource.{EventSourceMetric, OrderSourceMetric}
import infrastructure.computation.*
import infrastructure.computation.ImplicitConversions.given
import scala.language.implicitConversions

given OrderSourceMetric[OrdersQty.type] with
  def formula: Formula = 
    count(ctx.OrderId)

given EventSourceMetric[OrdersQty.type] with
  def formula: Formula =
    countDistinct(ctx.OrderId)
