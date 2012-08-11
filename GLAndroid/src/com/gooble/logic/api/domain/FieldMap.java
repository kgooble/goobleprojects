package com.gooble.logic.api.domain;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class FieldMap {
   private final Map<String, List<?>> fieldValues;
   private final Map<String, Object> staticFieldValues;

   public FieldMap() {
      fieldValues = new HashMap<String, List<?>>();
      staticFieldValues = new HashMap<String, Object>();
   }

   public void put(String key, List<?> value) {
      fieldValues.put(key, value);
   }

   public void putStaticField(String key, Object value) {
      staticFieldValues.put(key, value);
   }

   public Object get(String key, int index) {
      if (staticFieldValues.containsKey(key))
         return staticFieldValues.get(key);
      return fieldValues.get(key).get(index);
   }

   public Set<String> keySet() {
      HashSet<String> keys = new HashSet<String>();
      keys.addAll(fieldValues.keySet());
      keys.addAll(staticFieldValues.keySet());
      return keys;
   }
}