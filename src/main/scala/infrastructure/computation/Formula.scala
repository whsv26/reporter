package org.whsv26.reporter
package infrastructure.computation

import domain.OrderStatus
import infrastructure.computation.ImplicitConversions.given

sealed trait Formula:
  import Formulas.*
  def %(rhs: Formula): Formula = Div(Mul(Value(100), this), rhs)
  def +(rhs: Formula): Formula = Plus(this, rhs)
  def -(rhs: Formula): Formula = Minus(this, rhs)
  def *(rhs: Formula): Formula = Mul(this, rhs)
  def /(rhs: Formula): Formula = Div(this, rhs)

sealed trait Predicate extends Formula
sealed trait Function extends Formula
sealed trait AggregateFunction extends Function

object Formulas:
  export Arithmetics.*
  export Functions.*
  export Predicates.*
  export Leafs.*

  object Arithmetics:
    case class Plus(lhs: Formula, rhs: Formula) extends Formula
    case class Minus(lhs: Formula, rhs: Formula) extends Formula
    case class Mul(lhs: Formula, rhs: Formula) extends Formula
    case class Div(lhs: Formula, rhs: Formula) extends Formula

  object Functions:
    import Leafs.*
    case class ToStartOfYear(fld: Field) extends Function
    case class ToStartOfMonth(fld: Field) extends Function
    case class ToStartOfWeek(fld: Field) extends Function
    case class ToStartOfDay(fld: Field) extends Function
    case class ToStartOfHour(fld: Field) extends Function
    case class Sum(fld: Field) extends AggregateFunction
    case class Count(fld: Field) extends AggregateFunction
    case class CountIf(fld: Field, p: Predicate) extends AggregateFunction
    case class CountDistinct(fld: Field) extends AggregateFunction
    case class CountDistinctIf(fld: Field, p: Predicate) extends AggregateFunction
    case class SumIf(fld: Field, p: Predicate) extends AggregateFunction

  object Predicates:
    import Leafs.*
    case class Eq(lhs: Field, rhs: Value) extends Predicate
    case class In(lhs: Field, rhs: Seq[Value]) extends Predicate

  object Leafs:
    case class Value(v: Boolean|String|Int|Float|OrderStatus) extends Formula
    case class Field(fld: String) extends Formula:
      def ===(rhs: Value): Predicate = Eq(this, rhs)
      def in(rhs: Seq[Value]): Predicate = In(this, rhs)
