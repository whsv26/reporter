package org.whsv26.reporter

object Compiler {
  import Ast.Predicate.*
  import Ast.Aggregate.*
  import Ast.Arithmetic.*
  import Ast.*

  def compile(formula: Formula): String = {
    formula match {
      case Value(v) => v match {
        case str: String => s"'$str'"
        case default => default.toString
      }
      case Field(fld) => fld
      case Eq(fld, v) => "%s = %s" format (compile(fld), compile(v))
      case In(fld, v) => "%s IN %s" format (compile(fld), v.map(compile).mkString("(", ",", ")"))
      case Sum(fld) => "sum(%s)" format compile(fld)
      case SumIf(fld, p) => "sumIf(%s, %s)" format (compile(fld), compile(p))
      case Plus(lhs, rhs) => "(%s) + (%s)" format (compile(lhs), compile(rhs))
      case Minus(lhs, rhs) => "(%s) - (%s)" format (compile(lhs), compile(rhs))
      case Mul(lhs, rhs) => "(%s) * (%s)" format (compile(lhs), compile(rhs))
      case Div(lhs, rhs) => "(%s) / (%s)" format (compile(lhs), compile(rhs))
    }
  }
}
