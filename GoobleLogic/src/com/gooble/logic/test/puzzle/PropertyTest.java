package com.gooble.logic.test.puzzle;

import static com.gooble.logic.kb.encoding.KBEncoding.statement;
import static junit.framework.Assert.assertEquals;

import org.junit.Test;

import com.gooble.logic.puzzle.Property;

public class PropertyTest {
   
   @Test
   public void transform_property_to_statement() throws Exception {
      assertEquals(statement("shoesOf(X, red)"), new Property("shoes", "X", "red").toStatement());
   }
}
