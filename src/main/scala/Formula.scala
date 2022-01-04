package org.whsv26.reporter

import MetricName.*
import org.whsv26.reporter.Aggregate.{count, sum, sumIf}
import ImplicitConversions.given

type Values = Boolean|String|Int|Float|OrderStatus

object ImplicitConversions {
  import Ast.*
  given Conversion[ContextualField, Field] = (cf: ContextualField) => Field(cf.toString)
  given Conversion[String, Field] = Field(_) // TODO drop
  given Conversion[Values, Value] = Value(_)
  given [M <: MetricName, C <: FieldContext, S <: DataSource[C]] (using source: S, CM: ContextualMetric[M, C]): Conversion[M, Formula] with {
    override def apply(name: M): Formula = CM.formula(source.context())
  }
}

enum MetricName {
  case OrdersQty
  case OrdersInApprovedStatusQty
  case OrdersInApprovedStatusAvg
  case OrdersInSpamStatusQty
  case OrdersInSpamStatusAvg
  case OrdersInCanceledStatusQty
  case OrdersInCanceledStatusAvg

  def formula[C <: FieldContext, S <: DataSource[C]](source: S)(using CM: ContextualMetric[this.type, C]): Formula = {
    CM.formula(source.context())
  }
}

trait ContextualMetric[M <: MetricName, C <: FieldContext] {
  def formula(ctx: C): Formula
}

sealed trait Formula {
  import Ast.*
  def %(rhs: Formula): Formula = Div(Mul(Value(100), this), rhs)
  def +(rhs: Formula): Formula = Plus(this, rhs)
  def -(rhs: Formula): Formula = Minus(this, rhs)
  def *(rhs: Formula): Formula = Mul(this, rhs)
  def /(rhs: Formula): Formula = Div(this, rhs)
}
sealed trait Predicate extends Formula

sealed trait Aggregate extends Formula
object Aggregate {
  import Ast.*
  def sum(fld: Field): Formula = Sum(fld)
  def count(fld: Field): Formula = Count(fld)
  def countIf(fld: Field, p: Predicate): Formula = CountIf(fld, p)
  def countDistinct(fld: Field): Formula = CountDistinct(fld)
  def countDistinctIf(fld: Field, p: Predicate): Formula = CountDistinctIf(fld, p)
  def sumIf(fld: Field, p: Predicate): Formula = SumIf(fld, p)
}

object Ast {
  export Arithmetic.*
  export Aggregate.*
  export Predicate.*

  object Arithmetic {
    case class Plus(lhs: Formula, rhs: Formula) extends Formula
    case class Minus(lhs: Formula, rhs: Formula) extends Formula
    case class Mul(lhs: Formula, rhs: Formula) extends Formula
    case class Div(lhs: Formula, rhs: Formula) extends Formula
  }

  object Aggregate {
    case class Sum(fld: Field) extends Aggregate
    case class Count(fld: Field) extends Aggregate
    case class CountIf(fld: Field, p: Predicate) extends Aggregate
    case class CountDistinct(fld: Field) extends Aggregate
    case class CountDistinctIf(fld: Field, p: Predicate) extends Aggregate
    case class SumIf(fld: Field, p: Predicate) extends Aggregate
  }

  object Predicate {
    case class Eq(lhs: Field, rhs: Value) extends Predicate
    case class In(lhs: Field, rhs: Seq[Value]) extends Predicate
  }

  case class Value(v: Values) extends Formula
  case class Field(fld: String) extends Formula {
    def ===(rhs: Value): Predicate = Eq(this, rhs)
    def in(rhs: Seq[Value]): Predicate = In(this, rhs)
  }
}

