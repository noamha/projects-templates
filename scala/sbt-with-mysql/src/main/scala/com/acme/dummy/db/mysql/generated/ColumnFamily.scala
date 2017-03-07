package com.acme.dummy.db.mysql.generated

import scalikejdbc._
import org.joda.time.{DateTime}

case class ColumnFamily(
  columnFamilyId: Int,
  name: String,
  keyspaceId: Int,
  status: Int,
  cqlSchemaDef: Option[String] = None,
  lastUpdate: Option[DateTime] = None) {

  def save()(implicit session: DBSession = ColumnFamily.autoSession): ColumnFamily = ColumnFamily.save(this)(session)

  def destroy()(implicit session: DBSession = ColumnFamily.autoSession): Int = ColumnFamily.destroy(this)(session)

}


object ColumnFamily extends SQLSyntaxSupport[ColumnFamily] {

  override val tableName = "column_family"

  override val columns = Seq("column_family_id", "name", "keyspace_id", "status", "cql_schema_def", "last_update")

  def apply(cf: SyntaxProvider[ColumnFamily])(rs: WrappedResultSet): ColumnFamily = apply(cf.resultName)(rs)
  def apply(cf: ResultName[ColumnFamily])(rs: WrappedResultSet): ColumnFamily = new ColumnFamily(
    columnFamilyId = rs.get(cf.columnFamilyId),
    name = rs.get(cf.name),
    keyspaceId = rs.get(cf.keyspaceId),
    status = rs.get(cf.status),
    cqlSchemaDef = rs.get(cf.cqlSchemaDef),
    lastUpdate = rs.get(cf.lastUpdate)
  )

  val cf = ColumnFamily.syntax("cf")

  override val autoSession = AutoSession

  def find(columnFamilyId: Int)(implicit session: DBSession = autoSession): Option[ColumnFamily] = {
    withSQL {
      select.from(ColumnFamily as cf).where.eq(cf.columnFamilyId, columnFamilyId)
    }.map(ColumnFamily(cf.resultName)).single.apply()
  }

  def findAll()(implicit session: DBSession = autoSession): List[ColumnFamily] = {
    withSQL(select.from(ColumnFamily as cf)).map(ColumnFamily(cf.resultName)).list.apply()
  }

  def countAll()(implicit session: DBSession = autoSession): Long = {
    withSQL(select(sqls.count).from(ColumnFamily as cf)).map(rs => rs.long(1)).single.apply().get
  }

  def findBy(where: SQLSyntax)(implicit session: DBSession = autoSession): Option[ColumnFamily] = {
    withSQL {
      select.from(ColumnFamily as cf).where.append(where)
    }.map(ColumnFamily(cf.resultName)).single.apply()
  }

  def findAllBy(where: SQLSyntax)(implicit session: DBSession = autoSession): List[ColumnFamily] = {
    withSQL {
      select.from(ColumnFamily as cf).where.append(where)
    }.map(ColumnFamily(cf.resultName)).list.apply()
  }

  def countBy(where: SQLSyntax)(implicit session: DBSession = autoSession): Long = {
    withSQL {
      select(sqls.count).from(ColumnFamily as cf).where.append(where)
    }.map(_.long(1)).single.apply().get
  }

  def create(
    name: String,
    keyspaceId: Int,
    status: Int,
    cqlSchemaDef: Option[String] = None,
    lastUpdate: Option[DateTime] = None)(implicit session: DBSession = autoSession): ColumnFamily = {
    val generatedKey = withSQL {
      insert.into(ColumnFamily).namedValues(
        column.name -> name,
        column.keyspaceId -> keyspaceId,
        column.status -> status,
        column.cqlSchemaDef -> cqlSchemaDef,
        column.lastUpdate -> lastUpdate
      )
    }.updateAndReturnGeneratedKey.apply()

    ColumnFamily(
      columnFamilyId = generatedKey.toInt,
      name = name,
      keyspaceId = keyspaceId,
      status = status,
      cqlSchemaDef = cqlSchemaDef,
      lastUpdate = lastUpdate)
  }

  def batchInsert(entities: Seq[ColumnFamily])(implicit session: DBSession = autoSession): List[Int] = {
    val params: Seq[Seq[(Symbol, Any)]] = entities.map(entity =>
      Seq(
        'name -> entity.name,
        'keyspaceId -> entity.keyspaceId,
        'status -> entity.status,
        'cqlSchemaDef -> entity.cqlSchemaDef,
        'lastUpdate -> entity.lastUpdate))
        SQL("""insert into column_family(
        name,
        keyspace_id,
        status,
        cql_schema_def,
        last_update
      ) values (
        {name},
        {keyspaceId},
        {status},
        {cqlSchemaDef},
        {lastUpdate}
      )""").batchByName(params: _*).apply[List]()
    }

  def save(entity: ColumnFamily)(implicit session: DBSession = autoSession): ColumnFamily = {
    withSQL {
      update(ColumnFamily).set(
        column.columnFamilyId -> entity.columnFamilyId,
        column.name -> entity.name,
        column.keyspaceId -> entity.keyspaceId,
        column.status -> entity.status,
        column.cqlSchemaDef -> entity.cqlSchemaDef,
        column.lastUpdate -> entity.lastUpdate
      ).where.eq(column.columnFamilyId, entity.columnFamilyId)
    }.update.apply()
    entity
  }

  def destroy(entity: ColumnFamily)(implicit session: DBSession = autoSession): Int = {
    withSQL { delete.from(ColumnFamily).where.eq(column.columnFamilyId, entity.columnFamilyId) }.update.apply()
  }

}
