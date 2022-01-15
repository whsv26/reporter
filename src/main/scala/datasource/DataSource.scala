package org.whsv26.reporter
package datasource

import fact.Metric
import dimension.Grouping

trait ContextualField

trait ContextualMetric[M <: Metric, S <: DataSource] {
  type ContextType
  def ctx: ContextType
  def formula: Formula
}

trait ContextualGrouping[G <: Grouping, S <: DataSource] {
  type ContextType
  def ctx: ContextType
  def formula: Formula
}

trait DataSource {
  def sql: String
}
