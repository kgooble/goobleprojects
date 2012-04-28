package com.gooble.logic.test.puzzle;

import static com.gooble.logic.kb.encoding.KBEncoding.statement;
import static com.gooble.logic.kb.encoding.TermEncoding.constant;
import static com.gooble.logic.test.TestUtilities.set;
import static com.gooble.logic.test.TestUtilities.setFromList;
import static junit.framework.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.gooble.logic.kb.KnowledgeBaseFacade;
import com.gooble.logic.kb.stmts.Statement;
import com.gooble.logic.kb.terms.Term;
import com.gooble.logic.puzzle.Encoding;

public class RelationDefinitionTest {
   class RelationEncoding implements Encoding{

      private final List<Statement> relations;
      public RelationEncoding(){
         this.relations = new ArrayList<Statement>();
      }
      
      @Override
      public void augment(KnowledgeBaseFacade kb) {
         for (Statement stmt : relations){
            kb.add(stmt);
         }
      }

      public void add(Term<?> term1, Term<?> term2, String nameOfRelation, Statement... antecedents) {
         relations.add(new Statement(nameOfRelation, term1, term2));
      }
      
   }
   
   @Test
   public void add_simple_statement_like_relation() throws Exception {
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
      relationEncoding.add(constant(5), constant(7), "nextTo"); //"X is nextTo Y if X = 5 and Y = 7"
      relationEncoding.augment(kb);
      

      assertEquals(set(statement("nextTo(5, 7)")), setFromList(kb.stmts));
   }
   
}
