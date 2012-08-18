package com.gooble.logic.app.db;

import android.database.sqlite.SQLiteDatabase;

import com.gooble.logic.app.db.sql.CreateTableSql;
import com.gooble.logic.app.db.sql.DropTableSql;
import com.gooble.logic.app.entity.Condition;
import com.gooble.logic.app.entity.Conditionset;
import com.gooble.logic.app.entity.Conditionterm;
import com.gooble.logic.app.entity.Entity;
import com.gooble.logic.app.entity.Hint;
import com.gooble.logic.app.entity.Hintrelation;
import com.gooble.logic.app.entity.Hintrelationterm;
import com.gooble.logic.app.entity.Hintvariable;
import com.gooble.logic.app.entity.Property;
import com.gooble.logic.app.entity.Propertyterm;
import com.gooble.logic.app.entity.Puzzle;
import com.gooble.logic.app.entity.Relation;
import com.gooble.logic.app.entity.Relationterm;
import com.gooble.logic.app.entity.Term;
import com.gooble.logic.app.entity.Variable;
import com.gooble.logic.app.entity.Variablevalue;

public class AllTables {

   @SuppressWarnings("unchecked")
   private static final Class<Entity>[] ALL_TABLES = new Class[] {
         Conditionset.class, Condition.class, Conditionterm.class, Hint.class, Hintrelation.class,
         Hintrelationterm.class, Hintvariable.class, Property.class,
         Propertyterm.class, Puzzle.class, Relation.class, Relationterm.class,
         Term.class, Variable.class, Variablevalue.class };

   public void create(SQLiteDatabase db) {
      CreateTableSql sqlCreator = new CreateTableSql();
      for (Class<Entity> entityClass : ALL_TABLES){
         db.execSQL(sqlCreator.generateFor(entityClass));
      }
   }

   public void drop(SQLiteDatabase db) {
      DropTableSql sqlCreator = new DropTableSql();
      for (Class<Entity> entityClass : ALL_TABLES){
         db.execSQL(sqlCreator.generateFor(entityClass));
      }
   }

}