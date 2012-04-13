package com.gooble.logic.kb;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Statement {

   protected final String name;
   protected final Term<?>[] terms;

   public Statement(String name, Term<?>... terms) {
      this.name = name;
      this.terms = terms;
   }

   public boolean match(Statement other) {
      if (other.terms.length != this.terms.length)
         return false;
      
      boolean allConstantsMatch = true;
      for (int i = 0; i < terms.length; i++){
         allConstantsMatch &= (terms[i].match(other.terms[i]) || other.terms[i].match(terms[i])); 
      }
      return other.name.equals(this.name) && allConstantsMatch;
   }
   
   public <T> Statement replaceVariableWithValue(Variable variableToReplace, Term<T> newValue) {
      List<Term<?>> newConstants = new ArrayList<Term<?>>();
      for (Term<?> c : terms){
         if (c.equals(variableToReplace)){
            newConstants.add(newValue);
         }else{
            newConstants.add(c);
         }
      }
      
      return createStatement(newConstants.toArray(new Term<?>[terms.length]));
   }
   
   @Override
   public boolean equals(Object obj){
      if (!(obj instanceof Statement)) 
         return false;
      Statement other = (Statement) obj;
      return (this.name.equals(other.name)) && Arrays.equals(this.terms, other.terms);
   }
   
   @Override
   public String toString(){
      return name + Arrays.asList(terms).toString();
   }

   public List<Replacement> unifyWith(Statement other) {
      List<Replacement> replacements = new ArrayList<Replacement>();
      
      if (!this.match(other)) 
         return replacements;
      
      Statement workingStatement = createStatement(Arrays.copyOf(terms, terms.length));
      
      for (int i = 0; i < terms.length; i++){
         Term<?> myConstant = workingStatement.terms[i];
         Term<?> otherConstant = other.terms[i];
         
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
      Statement newStmt = createStatement(Arrays.copyOf(terms, terms.length));
      for (Replacement r: replacements){
         newStmt = newStmt.replaceVariableWithValue(r.getVariable(), r.getValue());
      }
      return newStmt;
   }

   protected Statement createStatement(Term<?>[] constants) {
      return new Statement(name, constants);
   }

   public boolean evaluate() {
      throw new UnsupportedOperationException("this should not be called; override in operator");
   }

   public boolean isToBeEvaluated() {
      return false;
   }

   public boolean containsVariables() {
      for (Term<?> t : terms){
         if (t.isVariable())
            return true;
      }
      return false;
   }

}
