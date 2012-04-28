package com.gooble.logic.test.puzzle;

import static com.gooble.logic.kb.encoding.KBEncoding.statement;
import static com.gooble.logic.kb.encoding.TermEncoding.variable;
import static com.gooble.logic.test.puzzle.TestUtilities.set;
import static junit.framework.Assert.assertEquals;

import java.util.regex.Pattern;

import org.junit.Ignore;
import org.junit.Test;

import com.gooble.logic.kb.KnowledgeBaseFacade;
import com.gooble.logic.kb.stmts.Statement;
import com.gooble.logic.kb.terms.Variable;
import com.gooble.logic.puzzle.Encoding;

public class RelationDefinitionTest {
   class RelationEncoding implements Encoding{

      private final Pattern ENCODING_REGEX = Pattern.compile("([A-Z]+)\\s+([a-zA-Z]+)\\s+([A-Z]+)\\s+if\\s+");
      
      @Override
      public void augment(KnowledgeBaseFacade kb) {
         
      }

      public void add(Variable variable1, Variable variable2, String nameOfRelation, Statement... antecedents) {
         
      }
      
   }
   
   @Ignore("WIP")
   @Test
   public void if_statements_are_equals_statements_replace_variables_with_values_when_augmenting() throws Exception {
      // Relation: _ is ______ _ if _____________
      // e.g       X is nextTo Y if X = 5 and Y = 7
      
      // relation for HOUSE 
      // Bi-directional? (Default NO) -> for nextTo(x, y) nextTo(y, x) is also true
      // Unique? (Default YES) -> houseOf(bob, x) and houseOf(bob, y) are both true if and only if x = y
      // - provide list of HOUSE values, or VARIABLE1 -
      // - provide list of HOUSE values, or VARIABLE2 -
      // --> becomes something like 5 is nextTo 4
      // or X is nextTo Y if X > Y (and) ... (and) ...
      // -> two TERMS. one NAME for the relation. Varargs of ANTECEDENT STATEMENTS.
      // -> two BOOLEANS, denoting UNIQUENESS, and BIDIRECTONALISM.
      // -> addBidirectionalUnique?  DEFAULT ADD has BIDIRECTIONAL=FALSE, UNIQUE=TRUE
      
      KBStub kb = new KBStub();
      RelationEncoding relationEncoding = new RelationEncoding();
      relationEncoding.add(variable("X"), variable("Y"), "nextTo", statement("X = 5"), statement("Y = 7")); //"X is nextTo Y if X = 5 and Y = 7"
      relationEncoding.augment(kb);
      

      assertEquals(set(statement("nextTo(5, 7)")), kb.stmts);
   }
   
}
