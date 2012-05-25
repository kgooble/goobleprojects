package com.gooble.logic.test;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class TestUtilities{
   public static <T> Set<T> set(T... stuff){
      return setFromList(Arrays.asList(stuff));
   }
   public static <T> Set<T> setFromList(List<T> stuff){
      return new TreeSet<T>(stuff);
   }
}
