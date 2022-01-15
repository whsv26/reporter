package org.whsv26.reporter
package infrastructure.computation

import infrastructure.computation.Formulas.*
import infrastructure.computation.ImplicitConversions.given

trait AggregateFunctionFormulas {
  def sum(fld: Field): Formula = Sum(fld)
  def count(fld: Field): Formula = Count(fld)
  def countIf(fld: Field, p: Predicate): Formula = CountIf(fld, p)
  def countDistinct(fld: Field): Formula = CountDistinct(fld)
  def countDistinctIf(fld: Field, p: Predicate): Formula = CountDistinctIf(fld, p)
  def sumIf(fld: Field, p: Predicate): Formula = SumIf(fld, p)
}
