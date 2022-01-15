package org.whsv26.reporter
package datasource

trait ContextualField

trait ContextualMetric[M <: Metric, S <: DataSource] {
  type ContextType
  def ctx: ContextType
  def formula: Formula
}

trait DataSource {
  def sql: String
}
