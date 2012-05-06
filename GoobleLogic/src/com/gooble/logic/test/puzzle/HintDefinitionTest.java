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
import com.gooble.logic.puzzle.VariableDefinition;

public class HintDefinitionTest {
   private KBStub kb;
   private HintDefinition hintDef;
   @Before
   public void init() throws Exception{
      kb = new KBStub();
      hintDef = new HintDefinition();
   }
   @Test
   public void hint_definition_interface_creates_hints() throws Exception {
      hintDef.about("person", "bob").relation("olderThan", "X")
                .about("person", "X").property("shoes", "red").end();
      assertEquals(Arrays.asList(new Hint(
            new Relation("olderThan", "X", "bob"), 
            new Property("shoes", "X", "red"))), 
            hintDef.getHints());
   }
   @Test
   public void create_hint_with_multiple_variables() throws Exception {
      hintDef
         .about("person", "X").property("shoes", "blue").relation("youngerThan", "Y")
            .about("person", "Y").property("shoes", "red").end();
      assertEquals(Arrays.asList(new Hint(
            new Property("shoes", "X", "blue"),
            new Relation("youngerThan", "Y", "X"), 
            new Property("shoes", "Y", "red"))), 
            hintDef.getHints());
   }
   @Test
   public void after_creating_hints_augment_kb_creates_correct_rules_with_suffixed_solution_consequences() throws Exception {
      hintDef
      .about("person", "bob").relation("olderThan", "X")
         .about("person", "X").property("shoes", "red").end()
      .about("person", "X").property("shoes", "blue").relation("youngerThan", "Y")
         .about("person", "Y").property("shoes", "red").end();
      hintDef.augment(kb);
      assertEquals(Arrays.asList(
            rule("olderThan(X, bob) ^ shoesOf(X, red) => hint1(X)"),
            rule("shoesOf(X, blue) ^ youngerThan(Y, X) ^ shoesOf(Y, red) => hint2(Y, X)")
            ), kb.rules);
   }
   
   @Test
   public void adding_property_of_a_non_main_variable_makes_it_a_property_of_a_main_variable() throws Exception {
      VariableDefinition varDef = new VariableDefinition();
      varDef.add("house", 1, 2, 3, 4, 5);
      varDef.add("woman", "adele", "sarah", "molly", "laura", "jane");
      varDef.add("hair", "blonde", "black", "grey", "brown", "chestnut");
      varDef.setMain("woman");
      
      hintDef = new HintDefinition(varDef);
      hintDef.about("woman", "W1").property("hair", "blonde").property("house", "H1")
               .about("house", "H1").relation("nextTo", "H2").relation("southOf", "H2")
                  .about("house", "H2").property("woman", "adele").relation("directlyEastOf", "H3")
                     .about("house", "H3").property("woman", "laura").end();
      hintDef.augment(kb);
      assertEquals(Arrays.asList(
            rule("hairOf(W1, blonde) ^ houseOf(W1, H1) ^ nextTo(H2, H1) ^ southOf(H2, H1) ^ " +
                  "houseOf(adele, H2) ^ directlyEastOf(H3, H2) ^ houseOf(laura, H3) " +
            		"=> hint1(H3, W1, H2, H1)")
            ), kb.rules);
   }
}
