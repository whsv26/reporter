package org.whsv26.reporter
package infrastructure.metrics

import domain.*
import infrastructure.datasource.{EventSourceMetric, OrderSourceMetric}
import infrastructure.computation.*
import infrastructure.computation.ImplicitConversions.given
import scala.language.implicitConversions

given OrderSourceMetric[OrdersInApprovedStatusPercent.type] with
  def formula: Formula =
    OrdersInApprovedStatusQty % OrdersQty

given EventSourceMetric[OrdersInApprovedStatusPercent.type] with
  def formula: Formula =
    OrdersInApprovedStatusQty * 100 / OrdersQty