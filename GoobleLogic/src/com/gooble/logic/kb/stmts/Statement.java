package com.gooble.logic.kb.stmts;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.gooble.logic.kb.Replacement;
import com.gooble.logic.kb.terms.Term;
import com.gooble.logic.kb.terms.Variable;

public class Statement implements Comparable<Statement>{

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

   public List<Replacement> unifyWith(Statement other) {
      return new VariableReplacement(this).unifyWith(other);
   }
   
   public Statement applyReplacements(List<Replacement> replacements) {
      return new VariableReplacement(this).applyReplacements(replacements);
   }
   
   public String getName(){
      return name;
   }
   
   public Term<?>[] getTerms(){
      return terms;
   }

   public Statement createCopyWithTerms(Term<?>[] constants) {
      return new Statement(name, constants);
   }

   public boolean evaluate() {
      throw new UnsupportedOperationException("this should not be called; override in operator implementation");
   }

   public boolean isToBeEvaluated() {
      return false;
   }

   public Set<Variable> getVariables() {
      Set<Variable> myVars = new HashSet<Variable>();
      for (Term<?> t : terms){
         if (t.isVariable())
            myVars.add((Variable) t);
      }
      return myVars;
   }
   public boolean containsVariables() {
      return !getVariables().isEmpty();
   }
   
   @Override
   public boolean equals(Object obj){
      if (!(obj instanceof Statement)) 
         return false;
      Statement other = (Statement) obj;
      return (this.name.equals(other.name)) && Arrays.equals(this.terms, other.terms);
   }
   @Override
   public int hashCode(){
      return this.name.hashCode() + this.terms.hashCode();
   }
   
   @Override
   public String toString(){
      return name + Arrays.asList(terms).toString();
   }

   @Override
   public int compareTo(Statement other) {
      if (this.name.compareTo(other.name) == 0){
         if (terms.length > other.terms.length)
            return 1;
         if (terms.length < other.terms.length)
            return -1;
         for (int i = 0; i < terms.length; i++){
            int comparison = terms[i].compareTo(other.terms[i]);
            if (comparison != 0){
               return comparison;
            }
         }
      }
      return this.name.compareTo(other.name);
   }

}
