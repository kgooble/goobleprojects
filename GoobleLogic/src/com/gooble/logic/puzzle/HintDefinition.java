package com.gooble.logic.puzzle;

import java.util.ArrayList;
import java.util.List;

import com.gooble.logic.kb.KnowledgeBaseFacade;

public class HintDefinition implements Definition{
   private final VariableDefinition varDef;
   private final List<Hint> hints;
   private String currentValue;
   private Hint currentHint;

   public HintDefinition(VariableDefinition varDef) {
      this.varDef = varDef;
      this.hints = new ArrayList<Hint>();
   }

   @Override
   public void augment(KnowledgeBaseFacade kb) {
   }

   public HintDefinition about(String value) {
      currentValue = value;
      return this;
   }

   public HintDefinition relation(String relName, String secondValue) {
      currentHint().add(new Relation(relName, secondValue, currentValue));
      return this;
   }

   private Hint currentHint() {
      if (currentHint == null)
         currentHint = new Hint();
      return currentHint;
   }

   public HintDefinition type(String variable) {
      return this;
   }

   public HintDefinition property(String variable, String valueOfProperty) {
      currentHint().add(new Property(variable, currentValue, valueOfProperty));
      return this;
   }

   public List<Hint> getHints() {
      return hints;
   }

   public void end() {
      hints.add(currentHint);
      currentHint = null;
   }
}