package com.gooble.logic.test.puzzle;

import static com.gooble.logic.kb.encoding.TermEncoding.constant;

import org.junit.Test;

import com.gooble.logic.kb.KnowledgeBase;
import com.gooble.logic.puzzle.RelationDefinition;
import com.gooble.logic.puzzle.VariableDefinition;

public class DefinitionCaseTest {
   @Test
   public void case_test_using_definitions() throws Exception {
      KnowledgeBase kb = new KnowledgeBase();
      
      VariableDefinition varDef = new VariableDefinition();
      varDef.add("woman", "adele", "jane", "laura", "molly", "sarah");
      varDef.add("house", 1, 2, 3, 4, 5);
      varDef.add("hairColour", "black", "blonde", "brown", "chestnut", "grey");
      varDef.setMain("woman");
      varDef.augment(kb);

      RelationDefinition relDef = new RelationDefinition();
      relDef.addBidirectionalNonUnique(constant(1), constant(3), "nextTo");
      relDef.addBidirectionalNonUnique(constant(2), constant(4), "nextTo");
      relDef.addBidirectionalNonUnique(constant(4), constant(5), "nextTo");
      relDef.augment(kb);
      
      System.out.println();
   }
}
