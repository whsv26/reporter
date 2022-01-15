package org.whsv26.reporter
package infrastructure.groupings

import infrastructure.computation.*
import infrastructure.computation.ImplicitConversions.given
import infrastructure.datasource.{EventSourceGrouping, OrderSourceGrouping}
import domain.*
import scala.language.implicitConversions

given OrderSourceGrouping[ByMonth.type] with
  def formula: Formula =
    toStartOfMonth(ctx.CreatedAt)

given EventSourceGrouping[ByMonth.type] with
  def formula: Formula =
    toStartOfMonth(ctx.OrderCreatedAt)
