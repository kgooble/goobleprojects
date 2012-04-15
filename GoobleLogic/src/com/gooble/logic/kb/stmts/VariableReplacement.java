package com.gooble.logic.kb.stmts;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.gooble.logic.kb.Replacement;
import com.gooble.logic.kb.Term;
import com.gooble.logic.kb.Variable;

public class VariableReplacement {

   public Statement applyReplacementsTo(Statement stmt, List<Replacement> replacements){
      Term<?>[] terms = stmt.getTerms();
      Statement newStmt = stmt.createCopyWithTerms(Arrays.copyOf(terms, terms.length));
      List<Integer> replacedIndices = new ArrayList<Integer>();
      for (Replacement r: replacements){
         newStmt =  replaceVariableWithValue(newStmt, r.getVariable(), r.getValue(), replacedIndices);
      }
      return newStmt;
   }
   
   public List<Replacement> unifyStatements(Statement first, Statement second){
      List<Replacement> replacements = new ArrayList<Replacement>();
      if (!first.match(second)) 
         return replacements;
      Statement workingStatement = first.createCopyWithTerms(Arrays.copyOf(first.getTerms(), first.getTerms().length));
      for (int i = 0; i < first.getTerms().length; i++){
         List<Integer> replacedIndices = new ArrayList<Integer>();
         Term<?> myConstant = workingStatement.getTerms()[i];
         Term<?> otherConstant = second.getTerms()[i];
         
         if (myConstant.isVariable()){
            workingStatement = replaceVariableWithValue(workingStatement, (Variable)myConstant, otherConstant, replacedIndices);
            replacements.add(new Replacement((Variable)myConstant, otherConstant));
         } 
      }
      
      if (!second.match(workingStatement))
         return new ArrayList<Replacement>();
      return replacements;
   }
   
   private <T> Statement replaceVariableWithValue(Statement stmt, Variable variableToReplace, Term<T> newValue, List<Integer> alreadyReplaced) {
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
