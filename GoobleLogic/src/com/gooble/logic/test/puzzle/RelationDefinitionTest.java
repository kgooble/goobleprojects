package com.gooble.logic.test.puzzle;

import static com.gooble.logic.kb.encoding.KBEncoding.rule;
import static com.gooble.logic.kb.encoding.KBEncoding.statement;
import static com.gooble.logic.kb.encoding.TermEncoding.constant;
import static com.gooble.logic.kb.encoding.TermEncoding.variable;
import static com.gooble.logic.test.TestUtilities.set;
import static com.gooble.logic.test.TestUtilities.setFromList;
import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import com.gooble.logic.kb.Rule;
import com.gooble.logic.kb.stmts.Statement;
import com.gooble.logic.puzzle.RelationDefinition;


public class RelationDefinitionTest {
   private KBStub kb;
   private RelationDefinition relationDef;
   @Before
   public void init() throws Exception{
      kb = new KBStub();
      relationDef = new RelationDefinition("house");
   }
   @Test
   public void add_rule_relation_for_variable_type() throws Exception {
      relationDef.add("northOf", variable("X"), variable("Y"), statement("X < Y"));
      relationDef.augment(kb);
      assertNoKBStatements();
      assertKBRulesExactlyEqualTo(rule("house(X) ^ house(Y) ^ X < Y => northOf(X, Y)"));
   }
   @Test
   public void add_non_unique_bi_directional_statement_adds_to_kb_twice_and_unique_list() throws Exception {
      relationDef.addBidirectionalNonUnique("nextTo", constant(5), constant(7));
      relationDef.augment(kb);
      assertNonUniquesExactlyEqualTo(statement("nextTo(X, Y)"));
      assertKBStatementsExactlyEqualTo(statement("nextTo(5, 7)"), statement("nextTo(7, 5)"));
   }
   @Test
   public void add_non_unique_statement_adds_to_non_unique_list_that_statement() throws Exception {
      relationDef.addNonUnique("nextTo", constant(5), constant(7));
      relationDef.augment(kb);
      assertNonUniquesExactlyEqualTo(statement("nextTo(X, Y)"));
      assertKBStatementsExactlyEqualTo(statement("nextTo(5, 7)"));
   }
   @Test
   public void add_bidirectional_statement_adds_both_variations_to_kb() throws Exception {
      relationDef.addBidirectional("nextTo", constant(5), constant(7));
      relationDef.augment(kb);
      assertKBStatementsExactlyEqualTo(statement("nextTo(5, 7)"), statement("nextTo(7, 5)"));
   }
   @Test
   public void add_simple_statement_like_relation() throws Exception {
      relationDef.add("nextTo", constant(5), constant(7));
      relationDef.augment(kb);
      assertKBStatementsExactlyEqualTo(statement("nextTo(5, 7)"));
   }
   private void assertKBStatementsExactlyEqualTo(Statement... stmts) {
      assertEquals(set(stmts), setFromList(kb.stmts));
   }
   private void assertKBRulesExactlyEqualTo(Rule rule) {
      assertEquals(set(rule), setFromList(kb.rules));
   }
   private void assertNonUniquesExactlyEqualTo(Statement statement) {
      assertEquals(set(statement), relationDef.getNonUniqueStatements());
   }
   private void assertNoKBStatements() {
      assertTrue(kb.stmts.isEmpty());
   }
}
