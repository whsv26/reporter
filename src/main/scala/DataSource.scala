package org.whsv26.reporter

trait DataSource {
  def sql(): String
}

object OrdersSource extends DataSource {
  override def sql(): String =
    """
      |SELECT
      |    o.order_id,
      |    o.status
      |FROM orders o
      |""".stripMargin
}

object EventsSource extends DataSource {
  override def sql(): String =
    """
      |SELECT
      |    e.event_id,
      |    e.order_id,
      |    e.status_from,
      |    e.status_to
      |FROM events e
      |""".stripMargin
}
