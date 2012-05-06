package com.gooble.logic.puzzle;

import java.util.ArrayList;
import java.util.List;

import com.gooble.logic.kb.KnowledgeBaseFacade;
import com.gooble.logic.kb.Rule;
import com.gooble.logic.kb.Suffix;
import com.gooble.logic.kb.stmts.Statement;

public class HintDefinition implements Definition{
   private final List<Hint> hints;
   private final VariableDefinition varDef;
   private String currentValue;
   private Hint currentHint;
   private String currentType;

   public HintDefinition() {
      this(new VariableDefinition());
   }

   public HintDefinition(VariableDefinition varDef) {
      this.varDef = varDef;
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

   public HintDefinition about(String type, String value) {
      currentType = type;
      currentValue = value;
      return this;
   }

   public HintDefinition relation(String relName, String secondValue) {
      currentHint().add(new Relation(relName, secondValue, currentValue));
      return this;
   }

   public HintDefinition property(String type, String value) {
      if (varDef.isMain(type)){
         currentHint().add(new Property(currentType, value, currentValue));
      }else{
         currentHint().add(new Property(type, currentValue, value));
      }
      return this;
   }

   public List<Hint> getHints() {
      return hints;
   }

   public HintDefinition end() {
      hints.add(currentHint);
      currentHint = null;
      currentType = null;
      return this;
   }

   private Hint currentHint() {
      if (currentHint == null)
         currentHint = new Hint();
      return currentHint;
   }
}