package org.whsv26.reporter
package dimension

sealed trait Grouping

case object ByYear extends Grouping
case object ByMonth extends Grouping
case object ByWeek extends Grouping
case object ByDay extends Grouping
case object ByHour extends Grouping

