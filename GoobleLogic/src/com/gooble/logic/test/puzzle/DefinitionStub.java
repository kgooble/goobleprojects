package com.gooble.logic.test.puzzle;

import com.gooble.logic.kb.KnowledgeBaseFacade;
import com.gooble.logic.kb.Rule;
import com.gooble.logic.puzzle.Definition;

public class DefinitionStub implements Definition{
   private final Rule fakeRule;
   public DefinitionStub(Rule fakeRule) {
      this.fakeRule = fakeRule;
   }
   @Override
   public void augment(KnowledgeBaseFacade kb) {
      kb.add(fakeRule);
   }
}
