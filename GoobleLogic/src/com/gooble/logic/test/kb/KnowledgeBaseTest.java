package com.gooble.logic.test.kb;

import static com.gooble.logic.kb.KBEncoding.*;
import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;
import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Collections;


import org.junit.Before;
import org.junit.Test;

import com.gooble.logic.kb.KnowledgeBase;
import com.gooble.logic.kb.solutions.Solution;
import com.gooble.logic.kb.solutions.SolutionSet;

public class KnowledgeBaseTest {

   private KnowledgeBase kb;

   @Before
   public void setup() throws Exception{
      kb = new KnowledgeBase();      
   }
   
   @Test
   public void simplest_statement_true() throws Exception{
      kb.add(statement("p(a)"));
      SolutionSet solns = kb.findSolutions(statement("p(a)"));
      assertTrue(solns.isQueryTrue());
      assertFalse(solns.hasSolutions());
   }
   
   @Test
   public void any_variable_statement_always_true() throws Exception{
      kb.add(statement("p(X)"));
      SolutionSet solns = kb.findSolutions(statement("p(a)"));
      assertTrue(solns.isQueryTrue());
      assertFalse(solns.hasSolutions());
      solns = kb.findSolutions(statement("p(X)"));
      assertTrue(solns.isQueryTrue());
      assertEquals(Arrays.asList(solution(replacement("X", "X"))), solns.getSolutions());
   }

   @Test
   public void multiple_variable_function() throws Exception{
      kb.add(statement("p(X, a, Z)"));
      kb.add(statement("g(X, a, a)"));
      kb.add(statement("h(b, a, a)"));
      
      SolutionSet solns = kb.findSolutions(statement("p(X, Z, Y)"));
      assertTrue(solns.isQueryTrue());
      assertEquals(Arrays.asList(solution(replacement("X", "X"), replacement("Z", "a"), replacement("Y", "Z"))), solns.getSolutions());
      
      solns = kb.findSolutions(statement("p(X, a, Y)"));
      assertTrue(solns.isQueryTrue());
      assertEquals(Arrays.asList(solution(replacement("X", "X"), replacement("Y", "Z"))), solns.getSolutions());
      
      solns = kb.findSolutions(statement("p(X, b, Y)"));
      assertFalse(solns.isQueryTrue());
      
      solns = kb.findSolutions(statement("g(X, Y, Y)"));
      assertTrue(solns.isQueryTrue());
      assertEquals(Arrays.asList(solution(replacement("X", "X"), replacement("Y", "a"))), solns.getSolutions());
      
      solns = kb.findSolutions(statement("g(X, Y, Z)"));
      assertTrue(solns.isQueryTrue());
      assertEquals(Arrays.asList(solution(replacement("X", "X"), replacement("Y", "a"), replacement("Z", "a"))), solns.getSolutions());
      
      solns = kb.findSolutions(statement("h(X, Y, Z)"));
      assertTrue(solns.isQueryTrue());
      assertEquals(Arrays.asList(solution(replacement("X", "b"), replacement("Y", "a"), replacement("Z", "a"))), solns.getSolutions());
      
      solns = kb.findSolutions(statement("h(X, X, Z)"));
      assertFalse(solns.isQueryTrue());
      
      solns = kb.findSolutions(statement("h(X, X, X)"));
      assertFalse(solns.isQueryTrue());
      
      solns = kb.findSolutions(statement("h(Y, X, X)"));
      assertTrue(solns.isQueryTrue());
      assertEquals(Arrays.asList(solution(replacement("Y", "b"), replacement("X", "a"))), solns.getSolutions());
   }
   
   @Test
   public void evaluating_operators() throws Exception{
      SolutionSet solns = kb.findSolutions(statement("17 > 18"));
      assertFalse(solns.isQueryTrue());
      solns = kb.findSolutions(statement("17 < 18"));
      assertTrue(solns.isQueryTrue());
   }
   
   
   @Test
   public void statement_added_to_kb_queried_is_true_but_statement_not_in_kb_false() throws Exception{
      kb.add(statement("smart(bob)"));
      assertTrue(kb.query(statement("smart(bob)")));
      assertFalse(kb.query(statement("smart(joe)")));
   }
   
   @Test
   public void rule_and_statement_in_kb_query_provable_stmt_is_true() throws Exception{
      kb.add(statement("smart(bob)"));
      kb.add(rule("smart(X) => goodLooking(X)"));
      
      assertTrue(query("goodLooking(bob)"));
      assertFalse(query("goodLooking(joe)"));
   }

   @Test
   public void prove_statement_with_multiple_variables_and_use_of_numbers() throws Exception{
      kb.add(statement("age(bob, 17)"));
      kb.add(statement("age(joe, 18)"));
      kb.add(rule("age(X, Y) ^ Y > 17 => adult(X)"));
      
      assertFalse(query("adult(bob)"));
      assertTrue(query("adult(joe)"));
   }
   
   @Test
   public void find_all_values_which_make_statement_true() throws Exception{
      kb.add(statement("age(bob, 17)"));
      assertEquals(Arrays.asList(solution(replacement("X", 17))), kb.findSolutions(statement("age(bob, X)")).getSolutions());
      assertEquals(Collections.<Solution>emptyList(), kb.findSolutions(statement("age(mary, X)")).getSolutions());
   }
   
   @Test
   public void chain_provable_query() throws Exception{
      kb.add(statement("q(a)"));
      kb.add(statement("z(a)"));
      
      kb.add(rule("h(X) => p(X)"));
      kb.add(rule("g(X) ^ b(X) => p(X)"));
      kb.add(rule("q(X) ^ y(X) => h(X)"));
      kb.add(rule("z(X) => y(X)"));
      
      assertTrue(query("p(a)"));
      assertFalse(query("p(b)"));
   }
   
   @Test
   public void solutions_do_not_contradict_each_other() throws Exception{
      kb.add(statement("p(a)"));
      kb.add(statement("p(c)"));
      kb.add(statement("h(c)"));
      
      kb.add(rule("p(X) ^ h(X) => g(X)"));
      
      assertEquals(Arrays.asList(solution(replacement("X", "c"))), kb.findSolutions(statement("g(X)")).getSolutions());
   }
   
   @Test
   public void get_all_solutions() throws Exception{
      kb.add(rule("orphan(X) ^ parentOf(X, Y) => dead(Y)"));
      kb.add(statement("parentOf(bob, alfred)"));
      kb.add(statement("parentOf(bob, betty)"));
      kb.add(statement("orphan(bob)"));
      
      assertEquals(Arrays.asList(solution(replacement("X", "alfred")), solution(replacement("X", "betty"))), kb.findSolutions(statement("dead(X)")).getSolutions());
   }
   
   private boolean query(String stmtEncoding) {
      return kb.query(statement(stmtEncoding));
   }

}
