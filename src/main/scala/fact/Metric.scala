package org.whsv26.reporter
package fact

sealed trait Metric

case object OrdersQty extends Metric
case object OrdersInApprovedStatusQty extends Metric
case object OrdersInApprovedStatusPercent extends Metric
case object OrdersInSpamStatusQty extends Metric
case object OrdersInSpamStatusPercent extends Metric
case object OrdersInCanceledStatusQty extends Metric
case object OrdersInCanceledStatusPercent extends Metric
