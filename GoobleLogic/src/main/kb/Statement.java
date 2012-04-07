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
   
   public <T> Statement replaceVariableWithValue(Variable variableToReplace, Constant<T> newValue) {
      List<Constant<?>> newConstants = new ArrayList<Constant<?>>();
      for (Constant<?> c : constants){
         if (c.match(variableToReplace)){
            newConstants.add(newValue);
         }else{
            newConstants.add(c);
         }
      }
      
      return createStatement(newConstants.toArray(new Constant<?>[constants.length]));
   }
   
   @Override
   public boolean equals(Object obj){
      if (!(obj instanceof Statement)) 
         return false;
      Statement other = (Statement) obj;
      return (this.name == other.name) && Arrays.equals(this.constants, other.constants);
   }
   
   @Override
   public String toString(){
      return name + Arrays.asList(constants).toString();
   }

   public List<Replacement> unifyWith(Statement other) {
      List<Replacement> replacements = new ArrayList<Replacement>();
      
      if (!other.match(this)) 
         return replacements;
      
      Statement workingStatement = createStatement(Arrays.copyOf(constants, constants.length));
      
      for (int i = 0; i < constants.length; i++){
         Constant<?> myConstant = workingStatement.constants[i];
         Constant<?> otherConstant = other.constants[i];
         
         if (myConstant.isVariable()){
            workingStatement = workingStatement.replaceVariableWithValue((Variable)myConstant, otherConstant);
            replacements.add(new Replacement((Variable)myConstant, otherConstant));
         } 
      }
      
      if (!other.match(workingStatement))
         return new ArrayList<Replacement>();
      return replacements;
   }
   
   public Statement applyReplacements(List<Replacement> replacements) {
      Statement newStmt = createStatement(Arrays.copyOf(constants, constants.length));
      for (Replacement r: replacements){
         newStmt = newStmt.replaceVariableWithValue(r.getVariable(), r.getValue());
      }
      return newStmt;
   }

   protected Statement createStatement(Constant<?>[] constants) {
      return new Statement(name, constants);
   }

   public boolean evaluate() {
      return false;
   }

   public boolean isToBeEvaluated() {
      return false;
   }

}
