package com.gooble.logic.test.app.entity;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;

import org.junit.Test;

import com.gooble.logic.app.entity.EntityList;

public class EntityListUTest {

   @Test
   public void get_column() {
      EntityList<Testentity> list = new EntityList<Testentity>();
      Testentity e1 = entityWithName("A");
      Testentity e2 = entityWithName("B");
      Testentity e3 = entityWithName("C");
      list.add(e1);
      list.add(e2);
      list.add(e3);
      
      assertEquals(Arrays.asList("A", "B", "C"), list.getColumn("name"));
   }

   private Testentity entityWithName(String name) {
      Testentity e1 = new Testentity();
      e1.setName(name);
      return e1;
   }
   
}
