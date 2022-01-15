package org.whsv26.reporter
package infrastructure.groupings

import infrastructure.computation.*
import infrastructure.computation.ImplicitConversions.given
import infrastructure.datasource.*
import domain.*
import scala.language.implicitConversions

given OrderSourceGrouping[ByDay.type] with
  def formula: Formula =
    toStartOfDay(ctx.CreatedAt)

given EventSourceGrouping[ByDay.type] with
  def formula: Formula =
    toStartOfDay(ctx.OrderCreatedAt)
