package com.gooble.logic.puzzle;

import java.util.ArrayList;
import java.util.List;

import com.gooble.logic.kb.KnowledgeBaseFacade;
import com.gooble.logic.kb.Rule;
import com.gooble.logic.kb.Suffix;
import com.gooble.logic.kb.stmts.Statement;

public class HintDefinition implements Definition{
   private final List<Hint> hints;
   private String currentValue;
   private Hint currentHint;

   public HintDefinition() {
      this.hints = new ArrayList<Hint>();
   }

   @Override
   public void augment(KnowledgeBaseFacade kb) {
      Integer i = 1;
      for (Hint hint : hints){
         Rule rule = hint.toRule();
         Statement suffixedConsequence = Suffix.statement(rule.getConsequence(), i.toString());
         kb.add(new Rule(suffixedConsequence, rule.getAntecedents()));
         i++;
      }
   }

   public HintDefinition about(String value) {
      currentValue = value;
      return this;
   }

   public HintDefinition relation(String relName, String secondValue) {
      currentHint().add(new Relation(relName, secondValue, currentValue));
      return this;
   }

   public HintDefinition property(String variable, String valueOfProperty) {
      currentHint().add(new Property(variable, currentValue, valueOfProperty));
      return this;
   }

   public List<Hint> getHints() {
      return hints;
   }

   public HintDefinition end() {
      hints.add(currentHint);
      currentHint = null;
      return this;
   }

   private Hint currentHint() {
      if (currentHint == null)
         currentHint = new Hint();
      return currentHint;
   }
}