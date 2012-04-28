package com.gooble.logic.puzzle;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.gooble.logic.kb.KnowledgeBaseFacade;
import com.gooble.logic.kb.Rule;
import com.gooble.logic.kb.stmts.Statement;
import com.gooble.logic.kb.terms.Term;
import com.gooble.logic.kb.terms.Variable;

public class RelationDefinition implements Encoding{

   private final List<Rule> ruleRelations;
   private final List<Statement> relations;
   private final Set<Statement> nonUniques;
   private final String varName;
   public RelationDefinition(String varName) {
      this.ruleRelations = new ArrayList<Rule>();
      this.relations = new ArrayList<Statement>();
      this.nonUniques = new HashSet<Statement>();
      this.varName = varName;
   }

   @Override
   public void augment(KnowledgeBaseFacade kb) {
      for (Statement stmt : relations){
         kb.add(stmt);
      }
      for (Rule rule : ruleRelations){
         kb.add(rule);
      }
   }

   public void add(String nameOfRelation, Term<?> term1, Term<?> term2, Statement... antecedents) {
      Statement consequence = new Statement(nameOfRelation, term1, term2);
      if (antecedents.length == 0) {
         relations.add(consequence);
      } else{
         List<Statement> allAntes = new ArrayList<Statement>();
         allAntes.add(new Statement(varName, term1));
         allAntes.add(new Statement(varName, term2));
         allAntes.addAll(Arrays.asList(antecedents));
         ruleRelations.add(new Rule(consequence, allAntes.toArray(new Statement[allAntes.size()])));
      }
   }

   public void addBidirectional(String nameOfRelation, Term<?> term1, Term<?> term2) {
      add(nameOfRelation, term1, term2);
      add(nameOfRelation, term2, term1);
   }

   public void addNonUnique(String nameOfRelation, Term<?> term1, Term<?> term2) {
      add(nameOfRelation, term1, term2);
      addGenericNonUnique(nameOfRelation);
   }

   public void addBidirectionalNonUnique(String nameOfRelation, Term<?> term1, Term<?> term2) {
      addBidirectional(nameOfRelation, term1, term2);
      addGenericNonUnique(nameOfRelation);
   }
   
   public Set<Statement> getNonUniqueStatements() {
      return nonUniques;
   }

   private void addGenericNonUnique(String nameOfRelation) {
      nonUniques.add(new Statement(nameOfRelation, new Variable("X"), new Variable("Y")));
   }
   
}