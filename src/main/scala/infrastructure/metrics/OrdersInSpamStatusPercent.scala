package org.whsv26.reporter
package infrastructure.metrics

import domain.*
import infrastructure.datasource.{EventSourceMetric, OrderSourceMetric}
import infrastructure.computation.*
import infrastructure.computation.ImplicitConversions.given
import scala.language.implicitConversions

given OrderSourceMetric[OrdersInSpamStatusPercent.type] with
  def formula: Formula =
    OrdersInSpamStatusQty % OrdersQty

given EventSourceMetric[OrdersInSpamStatusPercent.type] with
  def formula: Formula =
    OrdersInSpamStatusQty * 100 / OrdersQty
