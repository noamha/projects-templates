package com.acme.dummy.infra

import liquibase.Liquibase
import liquibase.database.jvm.JdbcConnection
import liquibase.resource.ClassLoaderResourceAccessor
import org.scalatest.{BeforeAndAfterAll, Matchers, fixture}
import scalikejdbc._
import scalikejdbc.scalatest.AutoRollback


trait MemoryDatabase extends BeforeAndAfterAll with H2DB with AutoRollback with Matchers {
  this: fixture.TestSuite =>
  MemoryDatabase
  private lazy val liquibase: Liquibase = {
    ConnectionPool.singleton(h2ConnectionProps, "user", "pwd")
    val conn: java.sql.Connection = ConnectionPool.borrow()
    val liquibase = new Liquibase("migrations/changelog.xml", new ClassLoaderResourceAccessor(), new JdbcConnection(conn))
    liquibase.getDatabase.setLiquibaseCatalogName(schema)
    liquibase
  }


  override def beforeAll() {
    liquibase.update("")
  }


  override def afterAll(): Unit = {
    ConnectionPool.close()
  }
}

object MemoryDatabase {
    GlobalSettings.loggingSQLAndTime = LoggingSQLAndTimeSettings(
      enabled = true,
      singleLineMode = true,
      printUnprocessedStackTrace = false,
      stackTraceDepth = 15,
      logLevel = 'debug,
      warningEnabled = true,
      warningThresholdMillis = 3000L,
      warningLogLevel = 'warn
    )
}
