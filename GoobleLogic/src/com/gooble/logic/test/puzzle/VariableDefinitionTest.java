package com.gooble.logic.test.puzzle;

import static com.gooble.logic.kb.encoding.KBEncoding.rule;
import static com.gooble.logic.kb.encoding.KBEncoding.statement;
import static com.gooble.logic.test.TestUtilities.set;
import static com.gooble.logic.test.TestUtilities.setFromList;
import static junit.framework.Assert.assertEquals;

import org.junit.Test;

import com.gooble.logic.puzzle.VariableDefinition;
import com.gooble.logic.test.puzzle.stubs.KBStub;

public class VariableDefinitionTest{
   @Test
   public void encode_variables_rules_and_solution_rules_into_knowledge_base() throws Exception {
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
                statement("height(5.5)"), statement("height(6.0)"), statement("height(5.1)"),
                statement("ageOf(alice, 17)"),
                statement("ageOf(alice, 18)"),
                statement("ageOf(alice, 19)"),
                statement("ageOf(bob, 17)"),
                statement("ageOf(bob, 18)"),
                statement("ageOf(bob, 19)"),
                statement("ageOf(carl, 17)"),
                statement("ageOf(carl, 18)"),
                statement("ageOf(carl, 19)"),
                statement("heightOf(alice, 5.5)"),
                statement("heightOf(alice, 6.0)"),
                statement("heightOf(alice, 5.1)"),
                statement("heightOf(bob, 5.5)"),
                statement("heightOf(bob, 6.0)"),
                statement("heightOf(bob, 5.1)"),
                statement("heightOf(carl, 5.5)"),
                statement("heightOf(carl, 6.0)"),
                statement("heightOf(carl, 5.1)")
                  ),
            setFromList(kb.stmts));
      assertEquals(set(
            rule("ageOf(alice, V1) ^ heightOf(alice, V2) => solution1(V1, V2)"), 
            rule("ageOf(bob, V1) ^ heightOf(bob, V2) => solution2(V1, V2)"),
            rule("ageOf(carl, V1) ^ heightOf(carl, V2) => solution3(V1, V2)")),
            setFromList(kb.rules));
   }
}
