package org.whsv26.reporter
package infrastructure.metrics

import domain.*
import infrastructure.datasource.{EventSourceMetric, OrderSourceMetric}
import infrastructure.computation.*
import infrastructure.computation.ImplicitConversions.given
import scala.language.implicitConversions

given OrderSourceMetric[OrdersInCanceledStatusPercent.type] with
  def formula: Formula =
    OrdersInCanceledStatusQty % OrdersQty

given EventSourceMetric[OrdersInCanceledStatusPercent.type] with
  def formula: Formula =
    OrdersInCanceledStatusQty * 100 / OrdersQty
