package org.whsv26.reporter
package infrastructure.compiling

import domain.*
import infrastructure.*
import infrastructure.computation.*
import infrastructure.computation.Formulas.*
import util.*

object FormulaCompiler:
  def compile(formula: Formula): String = formula match
    case Eq(fld, v) => "%s = %s" format(compile(fld), compile(v))
    case In(fld, v) => "%s IN %s" format(compile(fld), v.map(compile).mkString("(", ",", ")"))
    case Sum(fld) => "sum(%s)" format compile(fld)
    case Count(fld) => "count(%s)" format compile(fld)
    case CountDistinct(fld) => "countDistinct(%s)" format compile(fld)
    case CountDistinctIf(fld, p) => "countDistinctIf(%s, %s)" format(compile(fld), compile(p))
    case CountIf(fld, p) => "countIf(%s, %s)" format(compile(fld), compile(p))
    case SumIf(fld, p) => "sumIf(%s, %s)" format(compile(fld), compile(p))
    case ToStartOfYear(fld) => "toStartOfYear(%s)" format compile(fld)
    case ToStartOfMonth(fld) => "toStartOfMonth(%s)" format compile(fld)
    case ToStartOfWeek(fld) => "toStartOfWeek(%s, 3)" format compile(fld)
    case ToStartOfDay(fld) => "toStartOfDay(%s)" format compile(fld)
    case ToStartOfHour(fld) => "toStartOfHour(%s)" format compile(fld)
    case Plus(lhs, rhs) => "%s + %s" format(compile(lhs), compile(rhs))
    case Minus(lhs, rhs) => "%s - %s" format(compile(lhs), compile(rhs))
    case Mul(lhs, rhs) => "%s * %s" format(compile(lhs), compile(rhs))
    case Div(lhs, rhs) => "%s / %s" format(compile(lhs), compile(rhs))
    case Field(fld) => "`%s`" format fld.toSnakeCase
    case Value(v) => v match
      case status: OrderStatus => s"'${status.toString.toScreamingSnakeCase}'"
      case str: String => s"'$str'"
      case default => default.toString
