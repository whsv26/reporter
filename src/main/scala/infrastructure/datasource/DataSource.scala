package org.whsv26.reporter
package infrastructure.datasource

import infrastructure.computation.*
import domain.{Metric, Grouping}

trait ContextualField

trait ContextualMetric[M <: Metric, S <: DataSource] extends
  FunctionFormulas,
  AggregateFunctionFormulas {

  type ContextType
  def ctx: ContextType
  def formula: Formula
}

trait ContextualGrouping[G <: Grouping, S <: DataSource] extends
  FunctionFormulas {

  type ContextType
  def ctx: ContextType
  def formula: Formula
}

trait DataSource {
  def sql: String
}
