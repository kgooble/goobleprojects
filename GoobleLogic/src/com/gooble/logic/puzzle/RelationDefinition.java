package com.gooble.logic.puzzle;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.gooble.logic.kb.KnowledgeBaseFacade;
import com.gooble.logic.kb.stmts.Statement;
import com.gooble.logic.kb.terms.Term;
import com.gooble.logic.kb.terms.Variable;

public class RelationDefinition implements Encoding{

   private final List<Statement> relations;
   private final Set<Statement> nonUniques;
   public RelationDefinition(){
      this.relations = new ArrayList<Statement>();
      this.nonUniques = new HashSet<Statement>();
   }
   
   @Override
   public void augment(KnowledgeBaseFacade kb) {
      for (Statement stmt : relations){
         kb.add(stmt);
      }
   }

   public void add(Term<?> term1, Term<?> term2, String nameOfRelation, Statement... antecedents) {
      relations.add(new Statement(nameOfRelation, term1, term2));
   }

   public void addBidirectional(Term<?> term1, Term<?> term2, String nameOfRelation, Statement... antecedents) {
      add(term1, term2, nameOfRelation, antecedents);
      add(term2, term1, nameOfRelation, antecedents);
   }

   public void addNonUnique(Term<?> term1, Term<?> term2, String nameOfRelation, Statement... antecedents) {
      add(term1, term2, nameOfRelation, antecedents);
      addGenericNonUnique(nameOfRelation);
   }

   public Set<Statement> getNonUniqueStatements() {
      return nonUniques;
   }

   public void addBidirectionalNonUnique(Term<?> term1, Term<?> term2, String nameOfRelation, Statement... antecedents) {
      addBidirectional(term1, term2, nameOfRelation, antecedents);
      addGenericNonUnique(nameOfRelation);
   }

   private void addGenericNonUnique(String nameOfRelation) {
      nonUniques.add(new Statement(nameOfRelation, new Variable("X"), new Variable("Y")));
   }
   
}