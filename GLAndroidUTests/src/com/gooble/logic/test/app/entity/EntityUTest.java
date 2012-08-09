package com.gooble.logic.test.app.entity;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Test;

public class EntityUTest {

   @Test
   public void set_field() throws Exception{
      Testentity e = new Testentity();
      assertNull(e.getName());
      assertNull(e.getMoney());
      assertNull(e.getNumber());
      assertNull(e.getCool());
      
      e.setField("name", "Boom");
      e.setField("money", 6.33);
      e.setField("number", 7);
      e.setField("cool", false);
      
      assertEquals("Boom", e.getName());
      assertEquals(6.33, e.getMoney(), 0.00001);
      assertEquals(Integer.valueOf(7), e.getNumber());
      assertEquals(Boolean.FALSE, e.getCool());
   }
   
}
