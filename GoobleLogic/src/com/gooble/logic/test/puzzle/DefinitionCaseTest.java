package com.gooble.logic.test.puzzle;

import static com.gooble.logic.kb.encoding.KBEncoding.statement;
import static com.gooble.logic.kb.encoding.TermEncoding.constant;
import static com.gooble.logic.kb.encoding.TermEncoding.variable;

import org.junit.Test;

import com.gooble.logic.kb.KnowledgeBase;
import com.gooble.logic.puzzle.HintDefinition;
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

      RelationDefinition relDef = new RelationDefinition("house");
      relDef.addBidirectionalNonUnique("nextTo", constant(1), constant(3));
      relDef.addBidirectionalNonUnique("nextTo", constant(2), constant(4));
      relDef.addBidirectionalNonUnique("nextTo", constant(4), constant(5));
      relDef.add("directlyEastOf", constant(1), constant(2));
      relDef.add("directlyEastOf", constant(3), constant(4));
      relDef.add("northOf", variable("X"), variable("Y"), statement("X > Y"));
      relDef.add("southOf", variable("X"), variable("Y"), statement("X < Y"));
      relDef.augment(kb);
      
      HintDefinition hintDef = new HintDefinition();
      
      hintDef.about("woman", "W1").property("hair", "blonde").property("house", "H1")
               .about("house", "H1").relation("nextTo", "H2").relation("southOf", "H2")
                  .about("house", "H2").property("woman", "adele").relation("directlyEastOf", "H3")
                     .about("house", "H3").property("woman", "laura").end();
      hintDef.augment(kb);
      
      System.out.println(kb.findSolutions(statement("hint1(X, Y, Z, W)")));
   }
}
