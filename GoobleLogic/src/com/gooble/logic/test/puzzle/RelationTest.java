package com.gooble.logic.test.puzzle;

import static com.gooble.logic.kb.encoding.KBEncoding.statement;
import static junit.framework.Assert.assertEquals;

import org.junit.Test;

import com.gooble.logic.puzzle.Relation;

public class RelationTest {
   @Test
   public void transform_relation_to_statement() throws Exception {
      assertEquals(statement("olderThan(X, bob)"), new Relation("olderThan", "X", "bob").toStatement());
   }
}
