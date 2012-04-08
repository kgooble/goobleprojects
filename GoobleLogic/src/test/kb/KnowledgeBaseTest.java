package test.kb;

import static junit.framework.Assert.assertTrue;
import static main.kb.KBEncoding.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.util.Arrays;
import java.util.Collections;

import main.kb.KnowledgeBase;
import main.kb.Solution;

import org.junit.Before;
import org.junit.Test;

public class KnowledgeBaseTest {

   private KnowledgeBase kb;

   @Before
   public void setup() throws Exception{
      kb = new KnowledgeBase();      
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
   public void logic_puzzle_2_from_book_case_test() throws Exception{
      // All women
      kb.add(statement("woman(adele)"));
      kb.add(statement("woman(jane)"));
      kb.add(statement("woman(laura)"));
      kb.add(statement("woman(molly)"));
      kb.add(statement("woman(sarah)"));
      
      
      // All houses
      kb.add(statement("westHouse(1)"));
      kb.add(statement("westHouse(3)"));
      kb.add(statement("eastHouse(2)"));
      kb.add(statement("eastHouse(4)"));
      kb.add(statement("eastHouse(5)"));
      
      kb.add(rule("eastHouse(X) => house(X)"));
      kb.add(rule("westHouse(X) => house(X)"));
      kb.add(statement("directlyEastOf(1, 2)"));
      kb.add(statement("directlyEastOf(3, 4)"));
      kb.add(statement("oneOfThemIsHouseFour(4, Y)"));
      kb.add(statement("oneOfThemIsHouseFour(X, 4)"));
      kb.add(rule("house(X), house(Y), X > Y => northOf(X, Y)"));
      kb.add(rule("house(X), house(Y), X < Y => southOf(X, Y)"));      
      
      // All hair colours
      kb.add(statement("hairColour(black)"));
      kb.add(statement("hairColour(blonde)"));
      kb.add(statement("hairColour(brown)"));
      kb.add(statement("hairColour(chestnut)"));
      kb.add(statement("hairColour(grey)"));
      
      kb.add(rule("house(X) ^ woman(Y) => livesAtHouse(X, Y)"));
      kb.add(rule("hairColour(C) ^ woman(W) => hairOf(W, C)"));
      kb.add(rule("woman(A) ^ livesAtHouse(B, A) ^ hairOf(A, C) => everythingAbout(A, B, C)"));
      
      assertEquals(Arrays.asList(solution(replacement("C", "black")),
            solution(replacement("C", "blonde")),
            solution(replacement("C", "brown")),
            solution(replacement("C", "chestnut")),
            solution(replacement("C", "grey"))),
            kb.findSolutions(statement("hairOf(molly, C)")).getSolutions());
      assertEquals(Arrays.asList(solution(replacement("H", 2)),
            solution(replacement("H", 4)),
            solution(replacement("H", 5)),
            solution(replacement("H", 1)),
            solution(replacement("H", 3))),
            kb.findSolutions(statement("livesAtHouse(H, molly)")).getSolutions());
      assertEquals(25, kb.findSolutions(statement("everythingAbout(molly, X, Y)")).getSolutions().size());
      System.out.println(kb.findSolutions(statement("everythingAbout(molly, 3, Y)")));
   }
   
   
   @Test
   public void solutions_do_not_contradict_each_other() throws Exception{
      kb.add(statement("p(a)"));
      kb.add(statement("p(c)"));
      kb.add(statement("h(c)"));
      
      kb.add(rule("p(X) ^ h(X) => g(X)"));
      
      assertEquals(Arrays.asList(solution(replacement("X", "c"))), kb.findSolutions(statement("g(X)")).getSolutions());
   }
   
   private boolean query(String stmtEncoding) {
      return kb.query(statement(stmtEncoding));
   }

}
