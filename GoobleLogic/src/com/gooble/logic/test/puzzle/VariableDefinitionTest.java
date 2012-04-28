package com.gooble.logic.test.puzzle;

import static com.gooble.logic.kb.encoding.KBEncoding.rule;
import static com.gooble.logic.kb.encoding.KBEncoding.statement;
import static com.gooble.logic.test.puzzle.TestUtilities.set;
import static com.gooble.logic.test.puzzle.TestUtilities.setFromList;
import static junit.framework.Assert.assertEquals;

import org.junit.Test;

import com.gooble.logic.puzzle.VariableDefinition;

public class VariableDefinitionTest{
   @Test
   public void encode_variables_into_knowledge_base() throws Exception {
      KBStub kb = new KBStub();
      VariableDefinition varEnc = new VariableDefinition();
      varEnc.add("name", "alice", "bob", "carl");
      varEnc.add("age", 17, 18, 19);
      varEnc.add("height", 5.5, 6.0, 5.1);
      varEnc.setMain("name");
      varEnc.augment(kb);
      assertEquals(
            set(statement("name(alice)"), statement("name(bob)"), statement("name(carl)"),
                statement("age(17)"), statement("age(18)"), statement("age(19)"),
                statement("height(5.5)"), statement("height(6.0)"), statement("height(5.1)")), 
            setFromList(kb.stmts));
      assertEquals(set(
            rule("name(X) ^ age(Y) => ageOf(X, Y)"),
            rule("name(X) ^ height(Y) => heightOf(X, Y)")), setFromList(kb.rules));
   }
}
