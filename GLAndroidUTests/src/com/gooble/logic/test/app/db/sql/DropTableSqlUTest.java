package com.gooble.logic.test.app.db.sql;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.gooble.logic.app.db.sql.DropTableSql;
import com.gooble.logic.test.app.entity.Testentity;

public class DropTableSqlUTest {

   @Test
   public void drop_table_sql() {
      assertEquals("DROP TABLE IF EXISTS testentity;", new DropTableSql().generateFor(Testentity.class));
   }
   
}
