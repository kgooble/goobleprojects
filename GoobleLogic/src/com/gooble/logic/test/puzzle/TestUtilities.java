package com.gooble.logic.test.puzzle;

import java.util.Arrays;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

public class TestUtilities{
   public static <T> SortedSet<T> set(T... stuff){
      return setFromList(Arrays.asList(stuff));
   }
   public static <T> SortedSet<T> setFromList(List<T> stuff){
      return new TreeSet<T>(stuff);
   }
}
