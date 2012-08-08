package com.gooble.logic.test.app.db.sql;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.gooble.logic.app.db.Tables;
import com.gooble.logic.app.db.sql.CreateTableSql;
import com.gooble.logic.app.entity.Entity;

public class CreateTableSqlUTest {

   @SuppressWarnings("unused")
   class Testentity extends Entity{
      private String name;
      private int number;
      private double money;
   }
   
   @Test
   public void generate_sql_for_entity() {
      Testentity testentity = new Testentity();
      assertEquals("CREATE TABLE testentity (" +
            Tables._ID + " INTEGER PRIMARY KEY, " +
      		"name TEXT, " +
      		"number INTEGER, " +
      		"money REAL" +
      		");", new CreateTableSql().generateFor(testentity));
   }
   
}
