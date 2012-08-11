package com.gooble.logic.test.app.db.sql;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.gooble.logic.app.db.Tables;
import com.gooble.logic.app.db.sql.CreateTableSql;
import com.gooble.logic.test.app.entity.Testentity;

public class CreateTableSqlUTest {

   @Test
   public void generate_sql_for_entity() {
      assertEquals("CREATE TABLE testentity (" +
            Tables._ID + " INTEGER PRIMARY KEY, " +
      		"name TEXT, " +
      		"number INTEGER, " +
      		"longnumber INTEGER, " +
      		"money REAL, " +
      		"cool INTEGER" +
      		");", new CreateTableSql().generateFor(Testentity.class));
   }
   
}
