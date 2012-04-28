package com.gooble.logic.test.puzzle;

import static com.gooble.logic.kb.encoding.KBEncoding.statement;
import static com.gooble.logic.kb.encoding.TermEncoding.constant;
import static com.gooble.logic.test.TestUtilities.set;
import static com.gooble.logic.test.TestUtilities.setFromList;
import static junit.framework.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import com.gooble.logic.kb.stmts.Statement;
import com.gooble.logic.puzzle.RelationDefinition;


public class RelationDefinitionTest {
   private KBStub kb;
   private RelationDefinition relationEncoding;
   @Before
   public void init() throws Exception{
      kb = new KBStub();
      relationEncoding = new RelationDefinition();
   }
   
   @Test
   public void add_non_unique_bi_directional_statement_adds_to_kb_twice_and_unique_list() throws Exception {
      relationEncoding.addBidirectionalNonUnique(constant(5), constant(7), "nextTo");
      relationEncoding.augment(kb);
      assertNonUniquesExactlyEqualTo(statement("nextTo(X, Y)"));
      assertKBStatementsExactlyEqualTo(statement("nextTo(5, 7)"), statement("nextTo(7, 5)"));
   }
   @Test
   public void add_non_unique_statement_adds_to_non_unique_list_that_statement() throws Exception {
      relationEncoding.addNonUnique(constant(5), constant(7), "nextTo");
      relationEncoding.augment(kb);
      assertNonUniquesExactlyEqualTo(statement("nextTo(X, Y)"));
      assertKBStatementsExactlyEqualTo(statement("nextTo(5, 7)"));
   }
   private void assertNonUniquesExactlyEqualTo(Statement statement) {
      assertEquals(set(statement), relationEncoding.getNonUniqueStatements());
   }

   @Test
   public void add_bidirectional_statement_adds_both_variations_to_kb() throws Exception {
      relationEncoding.addBidirectional(constant(5), constant(7), "nextTo");
      relationEncoding.augment(kb);
      assertKBStatementsExactlyEqualTo(statement("nextTo(5, 7)"), statement("nextTo(7, 5)"));
   }
   @Test
   public void add_simple_statement_like_relation() throws Exception {
      relationEncoding.add(constant(5), constant(7), "nextTo");
      relationEncoding.augment(kb);
      assertKBStatementsExactlyEqualTo(statement("nextTo(5, 7)"));
   }
   private void assertKBStatementsExactlyEqualTo(Statement... stmts) {
      assertEquals(set(stmts), setFromList(kb.stmts));
   }
}
