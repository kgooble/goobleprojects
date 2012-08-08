package com.gooble.logic.app.db.sql;

import com.gooble.logic.app.entity.Entity;

public class DropTableSql {

   public String generateFor(Class<? extends Entity> entityClass) {
      return "DROP TABLE IF EXISTS " + entityClass.getSimpleName().toLowerCase() + ";";
   }

}
