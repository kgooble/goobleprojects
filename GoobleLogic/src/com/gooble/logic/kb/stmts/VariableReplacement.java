package com.gooble.logic.kb.stmts;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.gooble.logic.kb.Replacement;
import com.gooble.logic.kb.Term;
import com.gooble.logic.kb.Variable;

public class VariableReplacement {

   private Statement stmt;

   public VariableReplacement(Statement stmt){
      this.stmt = stmt;
   }
   
   public Statement applyReplacements(List<Replacement> replacements){
      Term<?>[] terms = stmt.getTerms();
      stmt = stmt.createCopyWithTerms(Arrays.copyOf(terms, terms.length));
      List<Integer> replacedIndices = new ArrayList<Integer>();
      for (Replacement r: replacements){
         stmt =  replaceVariableWithValue(r.getVariable(), r.getValue(), replacedIndices);
      }
      return stmt;
   }
   
   public List<Replacement> unifyWith(Statement second){
      List<Replacement> replacements = new ArrayList<Replacement>();
      if (!stmt.match(second)) 
         return replacements;
      stmt = stmt.createCopyWithTerms(Arrays.copyOf(stmt.getTerms(), stmt.getTerms().length));
      for (int i = 0; i < stmt.getTerms().length; i++){
         List<Integer> replacedIndices = new ArrayList<Integer>();
         Term<?> myConstant = stmt.getTerms()[i];
         Term<?> otherConstant = second.getTerms()[i];
         
         if (myConstant.isVariable()){
            stmt = replaceVariableWithValue((Variable)myConstant, otherConstant, replacedIndices);
            replacements.add(new Replacement((Variable)myConstant, otherConstant));
         }
      }
      
      if (!second.match(stmt))
         return new ArrayList<Replacement>();
      return replacements;
   }
   
   private <T> Statement replaceVariableWithValue(Variable variableToReplace, Term<T> newValue, List<Integer> alreadyReplaced) {
      List<Term<?>> newConstants = new ArrayList<Term<?>>();
      Term<?>[] terms = stmt.getTerms();
      for (int i = 0; i < terms.length; i++){
         Term<?> c = terms[i];
         if (!alreadyReplaced.contains(i) && c.equals(variableToReplace)){
            newConstants.add(newValue);
            alreadyReplaced.add(i);
         }else{
            newConstants.add(c);
         }
      }
      return stmt.createCopyWithTerms(newConstants.toArray(new Term<?>[terms.length]));
   }
   
}
