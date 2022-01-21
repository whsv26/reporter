package org.whsv26.reporter
package infrastructure.computation

import infrastructure.computation.Formulas.*
import infrastructure.computation.ImplicitConversions.given

trait FunctionFormulas:
  def toStartOfYear(fld: Field): Formula = ToStartOfYear(fld)
  def toStartOfMonth(fld: Field): Formula = ToStartOfMonth(fld)
  def toStartOfWeek(fld: Field): Formula = ToStartOfWeek(fld)
  def toStartOfDay(fld: Field): Formula = ToStartOfDay(fld)
  def toStartOfHour(fld: Field): Formula = ToStartOfHour(fld)
