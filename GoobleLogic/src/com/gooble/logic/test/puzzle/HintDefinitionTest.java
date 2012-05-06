package com.gooble.logic.test.puzzle;

import static com.gooble.logic.kb.encoding.KBEncoding.rule;
import static junit.framework.Assert.assertEquals;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;

import com.gooble.logic.puzzle.Hint;
import com.gooble.logic.puzzle.HintDefinition;
import com.gooble.logic.puzzle.Property;
import com.gooble.logic.puzzle.Relation;

public class HintDefinitionTest {
   private KBStub kb;
   private HintDefinition hintDef;
   @Before
   public void init() throws Exception{
      kb = new KBStub();
   }
   @Test
   public void hint_definition_interface_creates_hints() throws Exception {
      hintDef = new HintDefinition();
      hintDef.about("bob").relation("olderThan", "X")
                .about("X").property("shoes", "red").end();
      assertEquals(Arrays.asList(new Hint(
            new Relation("olderThan", "X", "bob"), 
            new Property("shoes", "X", "red"))), 
            hintDef.getHints());
   }
   @Test
   public void create_hint_with_multiple_variables() throws Exception {
      hintDef = new HintDefinition();
      hintDef
         .about("X").property("shoes", "blue").relation("youngerThan", "Y")
            .about("Y").property("shoes", "red").end();
      assertEquals(Arrays.asList(new Hint(
            new Property("shoes", "X", "blue"),
            new Relation("youngerThan", "Y", "X"), 
            new Property("shoes", "Y", "red"))), 
            hintDef.getHints());
   }
   @Test
   public void after_creating_hints_augment_kb_creates_correct_rules_with_suffixed_solution_consequences() throws Exception {
      hintDef = new HintDefinition();
      hintDef
      .about("bob").relation("olderThan", "X")
         .about("X").property("shoes", "red").end()
      .about("X").property("shoes", "blue").relation("youngerThan", "Y")
         .about("Y").property("shoes", "red").end();
      hintDef.augment(kb);
      assertEquals(Arrays.asList(
            rule("olderThan(X, bob) ^ shoesOf(X, red) => hint1(X)"),
            rule("shoesOf(X, blue) ^ youngerThan(Y, X) ^ shoesOf(Y, red) => hint2(Y, X)")
            ), kb.rules);
   }
}
