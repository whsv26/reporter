package org.whsv26.reporter

import ImplicitConversions.given

sealed trait Arithmetic {
  import Formulas.*
  def %(lhs: Formula, rhs: Formula): Formula = Div(Mul(Value(100), lhs), rhs)
  def +(lhs: Formula, rhs: Formula): Formula = Plus(lhs, rhs)
  def -(lhs: Formula, rhs: Formula): Formula = Minus(lhs, rhs)
  def *(lhs: Formula, rhs: Formula): Formula = Mul(lhs, rhs)
  def /(lhs: Formula, rhs: Formula): Formula = Div(lhs, rhs)
}

sealed trait Formula extends Arithmetic {
  import Formulas.*
  def %(rhs: Formula): Formula = this.%(this, rhs)
  def +(rhs: Formula): Formula = this.+(this, rhs)
  def -(rhs: Formula): Formula = this.-(this, rhs)
  def *(rhs: Formula): Formula = this.*(this, rhs)
  def /(rhs: Formula): Formula = this./(this, rhs)
}
sealed trait Predicate extends Formula
sealed trait AggregateFunctions extends Formula

object AggregateFunctions {
  import Formulas.*
  def sum(fld: Field): Formula = Sum(fld)
  def count(fld: Field): Formula = Count(fld)
  def countIf(fld: Field, p: Predicate): Formula = CountIf(fld, p)
  def countDistinct(fld: Field): Formula = CountDistinct(fld)
  def countDistinctIf(fld: Field, p: Predicate): Formula = CountDistinctIf(fld, p)
  def sumIf(fld: Field, p: Predicate): Formula = SumIf(fld, p)
}

object Functions {
  import Formulas.*
  def toStartOfYear(fld: Field): Formula = ToStartOfYear(fld)
  def toStartOfMonth(fld: Field): Formula = ToStartOfMonth(fld)
  def toStartOfWeek(fld: Field): Formula = ToStartOfWeek(fld)
  def toStartOfDay(fld: Field): Formula = ToStartOfDay(fld)
  def toStartOfHour(fld: Field): Formula = ToStartOfHour(fld)
}

object Formulas {
  export Arithmetics.*
  export AggregateFunctions.*
  export Functions.*
  export Predicates.*

  object Arithmetics {
    case class Plus(lhs: Formula, rhs: Formula) extends Formula
    case class Minus(lhs: Formula, rhs: Formula) extends Formula
    case class Mul(lhs: Formula, rhs: Formula) extends Formula
    case class Div(lhs: Formula, rhs: Formula) extends Formula
  }

  object AggregateFunctions {
    case class Sum(fld: Field) extends AggregateFunctions
    case class Count(fld: Field) extends AggregateFunctions
    case class CountIf(fld: Field, p: Predicate) extends AggregateFunctions
    case class CountDistinct(fld: Field) extends AggregateFunctions
    case class CountDistinctIf(fld: Field, p: Predicate) extends AggregateFunctions
    case class SumIf(fld: Field, p: Predicate) extends AggregateFunctions
  }

  object Functions {
    case class ToStartOfYear(fld: Field) extends AggregateFunctions
    case class ToStartOfMonth(fld: Field) extends AggregateFunctions
    case class ToStartOfWeek(fld: Field) extends AggregateFunctions
    case class ToStartOfDay(fld: Field) extends AggregateFunctions
    case class ToStartOfHour(fld: Field) extends AggregateFunctions
  }

  object Predicates {
    case class Eq(lhs: Field, rhs: Value) extends Predicate
    case class In(lhs: Field, rhs: Seq[Value]) extends Predicate
  }

  case class Value(v: Boolean|String|Int|Float|OrderStatus) extends Formula
  case class Field(fld: String) extends Formula {
    def ===(rhs: Value): Predicate = Eq(this, rhs)
    def in(rhs: Seq[Value]): Predicate = In(this, rhs)
  }
}

