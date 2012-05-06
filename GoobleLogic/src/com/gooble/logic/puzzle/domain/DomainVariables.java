package com.gooble.logic.puzzle.domain;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class DomainVariables {

   private Map<String, List<?>> variables;
   private String main;

   public DomainVariables() {
      this.variables = new HashMap<String, List<?>>();
   }
   
   public void put(String type, Object... domainValues) {
      variables.put(type, Arrays.asList(domainValues));
   }

   public Set<String> types() {
      return variables.keySet();
   }
   public Set<String> secondaryTypes(){
      Set<String> secondaryTypes = new HashSet<String>(variables.keySet());
      secondaryTypes.remove(main);
      return secondaryTypes;
   }
   
   public int numberOfVariables(){
      return types().size();
   }
   public int numberOfSecondaryVariables(){
      return types().size() - 1;
   }
   
   public String getMain(){
      return main;
   }
   public void setMain(String type) {
      main = type;
   }

   public List<?> mainValues() {
      return values(main);
   }
   public List<?> values(String type) {
      return variables.get(type);
   }

}
