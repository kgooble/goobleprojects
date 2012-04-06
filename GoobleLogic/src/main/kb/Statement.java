package main.kb;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class Statement {

   protected final String name;
   protected final Constant<?>[] constants;

   public Statement(String name, Constant<?>... constants) {
      this.name = name;
      this.constants = constants;
   }

   public boolean match(Statement other) {
      if (other.constants.length != this.constants.length)
         return false;
      
      boolean allConstantsMatch = true;
      for (int i = 0; i < constants.length; i++){
         allConstantsMatch &= (constants[i].match(other.constants[i]) || other.constants[i].match(constants[i])); 
      }
      return other.name == this.name && allConstantsMatch;
   }
   
   @Override
   public String toString(){
      return name + Arrays.asList(constants).toString();
   }

   public String getValue() {
      return (String) constants[0].getValue();
   }

   public Statement replaceVariableWithValue(String value) {
      return new Statement(this.name, new Constant<String>(value));
   }

   public <T> Statement replaceVariableWithValue(Variable variableToReplace, Constant<T> newValue) {
      List<Constant<?>> newConstants = new ArrayList<Constant<?>>();
      for (Constant<?> c : constants){
         if (c.match(variableToReplace)){
            newConstants.add(newValue);
         }else{
            newConstants.add(c);
         }
      }
      
      return new Statement(name, newConstants.toArray(new Constant<?>[constants.length]));
   }
   
   @Override
   public boolean equals(Object obj){
      if (!(obj instanceof Statement)) 
         return false;
      Statement other = (Statement) obj;
      return (this.name == other.name) && Arrays.equals(this.constants, other.constants);
   }

   public List<Replacement> unifyWith(Statement other) {
      List<Replacement> replacements = new ArrayList<Replacement>();
      
      if (!other.match(this)) 
         return replacements;
      
      for (int i = 0; i < constants.length; i++){
         Constant<?> myConstant = constants[i];
         Constant<?> otherConstant = other.constants[i];
         
         if (myConstant.isVariable()){
            replacements.add(new Replacement((Variable)myConstant, otherConstant));
         }
      }
      
      return replacements;
   }

}
