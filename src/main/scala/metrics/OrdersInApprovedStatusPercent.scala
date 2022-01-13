package org.whsv26.reporter
package metrics

import Aggregate.*
import ImplicitConversions.given
import OrderStatus.*
import scala.language.implicitConversions

given OrderSourceMetric[OrdersInApprovedStatusPercent.type] with
  def formula: Formula =
    OrdersInApprovedStatusQty % OrdersQty

given EventSourceMetric[OrdersInApprovedStatusPercent.type] with
  def formula: Formula =
    OrdersInApprovedStatusQty * 100 / OrdersQty