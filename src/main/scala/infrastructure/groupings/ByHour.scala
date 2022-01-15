package org.whsv26.reporter
package infrastructure.groupings

import infrastructure.computation.*
import infrastructure.computation.ImplicitConversions.given
import infrastructure.datasource.{EventSourceGrouping, OrderSourceGrouping}
import domain.*
import scala.language.implicitConversions

given OrderSourceGrouping[ByHour.type] with
  def formula: Formula =
    toStartOfHour(ctx.CreatedAt)

given EventSourceGrouping[ByHour.type] with
  def formula: Formula =
    toStartOfHour(ctx.OrderCreatedAt)
