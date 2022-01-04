package org.whsv26.reporter

import MetricEnum.*
import org.whsv26.reporter.Aggregate.{count, sum, sumIf}
import scala.language.implicitConversions
import Conversions.given

type Scalar = Boolean|String|Int|Float

object Conversions {
  import Ast.*
  given Conversion[MetricEnum, Metric] = Metric(_)
  given Conversion[ContextualField, Field] = (cf: ContextualField) => Field(cf.snake())
  given Conversion[String, Field] = Field(_) // TODO drop
  given Conversion[Scalar, Value] = Value(_)
}

enum MetricEnum {
  case OrdersQty
  case OrdersInApprovedStatusQty
  case OrdersInApprovedStatusAvg
  case OrdersInSpamStatusQty
  case OrdersInSpamStatusAvg
  case OrdersInCanceledStatusQty
  case OrdersInCanceledStatusAvg

  def formula[C <: FieldContext, S <: DataSource[C]](src: S)(using CM: ContextualMetric[this.type, C]): Formula = {
    CM.formula(src.context())
  }
}

trait ContextualMetric[M <: MetricEnum, C <: FieldContext] {
  def formula(ctx: C): Formula
}

object MetricImplementations {
  given ContextualMetric[OrdersQty.type, OrderFieldContext] with {
    def formula(ctx: OrderFieldContext): Formula = {
      count(ctx.OrderId.toString)
    }
  }

  given ContextualMetric[OrdersQty.type, EventFieldsContext] with {
    def formula(ctx: EventFieldsContext): Formula = {
      count(ctx.OrderId.toString)
    }
  }
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
    case class SumIf(fld: Field, p: Predicate) extends Aggregate
  }

  object Predicate {
    case class Eq(lhs: Field, rhs: Value) extends Predicate
    case class In(lhs: Field, rhs: Seq[Value]) extends Predicate
  }

  case class Metric(m: MetricEnum) extends Formula
  case class Value(v: Scalar) extends Formula
  case class Field(fld: String) extends Formula {
    def ===(rhs: Value): Predicate = Eq(this, rhs)
    def in(rhs: Seq[Value]): Predicate = In(this, rhs)
  }
}

object TestFormulas {
  def test1: Formula = {
    OrdersInApprovedStatusQty * 100 / OrdersQty
  }
  def test2: Formula = {
    OrdersInApprovedStatusQty % OrdersQty
  }
}
