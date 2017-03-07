package com.acme.dummy.infra

trait H2DB {
  val schema: String = "dummy" + scala.util.Random.alphanumeric.take(5).mkString
  val h2ConnectionProps: String = s"jdbc:h2:mem:$schema;" +
    s"INIT=CREATE SCHEMA IF NOT EXISTS $schema \\;" +
    s"SET SCHEMA $schema ;" +
    s"DATABASE_TO_UPPER=false;" +
    s"IGNORECASE=TRUE;" +
    s"MODE=MYSQL"
}
