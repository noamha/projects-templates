package com.acme.dummy.db.mysql.generated

import scalikejdbc.specs2.mutable.AutoRollback
import org.specs2.mutable._
import scalikejdbc._
import org.joda.time.{DateTime}


class ColumnFamilySpec extends Specification {

  "ColumnFamily" should {

    val cf = ColumnFamily.syntax("cf")

    "find by primary keys" in new AutoRollback {
      val maybeFound = ColumnFamily.find(123)
      maybeFound.isDefined should beTrue
    }
    "find by where clauses" in new AutoRollback {
      val maybeFound = ColumnFamily.findBy(sqls.eq(cf.columnFamilyId, 123))
      maybeFound.isDefined should beTrue
    }
    "find all records" in new AutoRollback {
      val allResults = ColumnFamily.findAll()
      allResults.size should be_>(0)
    }
    "count all records" in new AutoRollback {
      val count = ColumnFamily.countAll()
      count should be_>(0L)
    }
    "find all by where clauses" in new AutoRollback {
      val results = ColumnFamily.findAllBy(sqls.eq(cf.columnFamilyId, 123))
      results.size should be_>(0)
    }
    "count by where clauses" in new AutoRollback {
      val count = ColumnFamily.countBy(sqls.eq(cf.columnFamilyId, 123))
      count should be_>(0L)
    }
    "create new record" in new AutoRollback {
      val created = ColumnFamily.create(name = "MyString", keyspaceId = 123, status = 123)
      created should not beNull
    }
    "save a record" in new AutoRollback {
      val entity = ColumnFamily.findAll().head
      // TODO modify something
      val modified = entity
      val updated = ColumnFamily.save(modified)
      updated should not equalTo(entity)
    }
    "destroy a record" in new AutoRollback {
      val entity = ColumnFamily.findAll().head
      val deleted = ColumnFamily.destroy(entity) == 1
      deleted should beTrue
      val shouldBeNone = ColumnFamily.find(123)
      shouldBeNone.isDefined should beFalse
    }
    "perform batch insert" in new AutoRollback {
      val entities = ColumnFamily.findAll()
      entities.foreach(e => ColumnFamily.destroy(e))
      val batchInserted = ColumnFamily.batchInsert(entities)
      batchInserted.size should be_>(0)
    }
  }

}
