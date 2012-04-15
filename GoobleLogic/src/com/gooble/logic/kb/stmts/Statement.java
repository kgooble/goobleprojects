package com.gooble.logic.kb.stmts;

import java.util.Arrays;
import java.util.List;

import com.gooble.logic.kb.Replacement;
import com.gooble.logic.kb.Term;

public class Statement{

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

   public boolean containsVariables() {
      for (Term<?> t : terms){
         if (t.isVariable())
            return true;
      }
      return false;
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

}
