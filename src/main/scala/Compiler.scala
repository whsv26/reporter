package org.whsv26.reporter

import metrics.given
import MetricName.*
import Ast.Predicate.*
import Ast.Aggregate.*
import Ast.Arithmetic.*
import Ast.*

object Compiler {
  def compile(formula: Formula): String = {
    formula match {
      case Eq(fld, v) => "%s = %s" format(compile(fld), compile(v))
      case In(fld, v) => "%s IN %s" format(compile(fld), v.map(compile).mkString("(", ",", ")"))
      case Sum(fld) => "sum(%s)" format compile(fld)
      case Count(fld) => "count(%s)" format compile(fld)
      case CountDistinct(fld) => "countDistinct(%s)" format compile(fld)
      case CountDistinctIf(fld, p) => "countDistinctIf(%s, %s)" format(compile(fld), compile(p))
      case CountIf(fld, p) => "countIf(%s, %s)" format(compile(fld), compile(p))
      case SumIf(fld, p) => "sumIf(%s, %s)" format(compile(fld), compile(p))
      case Plus(lhs, rhs) => "(%s) + (%s)" format(compile(lhs), compile(rhs))
      case Minus(lhs, rhs) => "(%s) - (%s)" format(compile(lhs), compile(rhs))
      case Mul(lhs, rhs) => "(%s) * (%s)" format(compile(lhs), compile(rhs))
      case Div(lhs, rhs) => "(%s) / (%s)" format(compile(lhs), compile(rhs))
      case Field(fld) => fld.toSnakeCase
      case Value(v) => v match {
        case s: OrderStatus => s"'${s.toString.toScreamingSnakeCase}'"
        case str: String => s"'$str'"
        case default => default.toString
      }
    }
  }
}
