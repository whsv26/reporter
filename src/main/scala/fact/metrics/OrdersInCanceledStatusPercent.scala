package org.whsv26.reporter
package fact.metrics

import AggregateFunction.*
import OrderStatus.*
import datasource.*
import fact.*
import ImplicitConversions.given
import scala.language.implicitConversions

given OrderSourceMetric[OrdersInCanceledStatusPercent.type] with
  def formula: Formula =
    OrdersInCanceledStatusQty % OrdersQty

given EventSourceMetric[OrdersInCanceledStatusPercent.type] with
  def formula: Formula =
    OrdersInCanceledStatusQty * 100 / OrdersQty
