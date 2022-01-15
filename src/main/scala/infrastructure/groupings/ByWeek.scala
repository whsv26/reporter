package org.whsv26.reporter
package infrastructure.groupings

import infrastructure.computation.*
import infrastructure.computation.ImplicitConversions.given
import infrastructure.datasource.{EventSourceGrouping, OrderSourceGrouping}
import domain.*
import scala.language.implicitConversions

given OrderSourceGrouping[ByWeek.type] with
  def formula: Formula =
    toStartOfWeek(ctx.CreatedAt)

given EventSourceGrouping[ByWeek.type] with
  def formula: Formula =
    toStartOfWeek(ctx.OrderCreatedAt)
