package org.whsv26.reporter
package infrastructure.groupings

import infrastructure.computation.*
import infrastructure.computation.ImplicitConversions.given
import infrastructure.datasource.{EventSourceGrouping, OrderSourceGrouping}
import domain.*
import scala.language.implicitConversions

given OrderSourceGrouping[ByYear.type] with
  def formula: Formula =
    toStartOfYear(ctx.CreatedAt)

given EventSourceGrouping[ByYear.type] with
  def formula: Formula =
    toStartOfYear(ctx.OrderCreatedAt)
