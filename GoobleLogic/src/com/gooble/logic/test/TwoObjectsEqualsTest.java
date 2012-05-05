package com.gooble.logic.test;

import static junit.framework.Assert.assertTrue;
import static org.junit.Assert.*;


import org.junit.Test;

import com.gooble.logic.TwoObjectsEqual;

public class TwoObjectsEqualsTest {
   class TestObject{
      private final String value;
      public TestObject(String value) {
         this.value = value;
      }
      public String getValue(){
         return value;
      }
   }
   class TestObjectChild extends TestObject{
      public TestObjectChild(String value){
         super(value);
      }
   }
   @Test
   public void class_with_same_field_values_and_same_type_is_equal_to_other() throws Exception {
      TestObject obj1 = new TestObject("cow");
      TestObject obj2 = new TestObject("cow");
      TestObject obj3 = new TestObject("monkey");
      assertTrue(TwoObjectsEqual.byReflection(obj1, obj2));
      assertFalse(TwoObjectsEqual.byReflection(obj1, obj3));
   }
   @Test
   public void subclass_with_same_values_not_equal_to_super_class() throws Exception {
      TestObject obj1 = new TestObject("cow");
      TestObjectChild obj2 = new TestObjectChild("cow");
      assertFalse(TwoObjectsEqual.byReflection(obj1, obj2));
   }
}
