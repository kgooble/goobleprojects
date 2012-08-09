package com.gooble.logic.test.app.entity;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class EntityUTest {

   @Test
   public void set_field() throws Exception{
      Testentity e = new Testentity();
      e.setField("name", "Boom");
      e.setField("money", 6.33);
      e.setField("number", 7);
      assertEquals("Boom", e.getName());
      assertEquals(6.33, e.getMoney(), 0.00001);
      assertEquals(Integer.valueOf(7), e.getNumber());
   }
   
}
