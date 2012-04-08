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
      assertEquals(Arrays.asList(solution(replacement("X", 17))), kb.findSolutions(statement("age(bob, X)")));
      assertEquals(Collections.<Solution>emptyList(), kb.findSolutions(statement("age(mary, X)")));
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
   
//   @Test
//   public void logic_puzzle_2_from_book_case_test() throws Exception{
//      kb.add(statement("livesDirectlyEastOf(laura, adele)"));
//      kb.add(statement("livesNextTo(molly, sarah)"));
//      kb.add(statement("livesSouthOf(molly, sarah)"));
//      
//      kb.add(statement("westHouse(1)"));
//      kb.add(statement("westHouse(3)"));
//      
//      kb.add(statement("eastHouse(2)"));
//      kb.add(statement("eastHouse(4)"));
//      kb.add(statement("eastHouse(5)"));
//      
//      kb.add(statement("hairColour(black)"));
//      kb.add(statement("hairColour(blonde)"));
//      kb.add(statement("hairColour(brown)"));
//      kb.add(statement("hairColour(chestnut)"));
//      kb.add(statement("hairColour(grey)"));
//      
//      kb.add(rule("eastHouse(X) => house(X)"));
//      kb.add(rule("westHouse(X) => house(X)"));
//      
//      kb.add(statement("directlyEastOf(1, 2)"));
//      kb.add(statement("directlyEastOf(3, 4)"));
//      
//      kb.add(statement("oneOfThemIsHouseFour(4, Y)"));
//      kb.add(statement("oneOfThemIsHouseFour(X, 4)"));
//
//      kb.add(rule("house(X), house(Y), X < Y => northOf(X, Y)"));
//      kb.add(rule("house(X), house(Y), X > Y => southOf(X, Y)"));
//      
//      kb.add(rule("livesAtHouse(A, X), livesAtHouse(B, Y), westHouse(A), westHouse(B) => livesNextTo(X, Y)"));
//      kb.add(rule("livesAtHouse(A, X), livesAtHouse(B, Y), eastHouse(A), eastHouse(B), oneOfThemIsHouseFour(A, B) => livesNextTo(X, Y)"));
//      
//      
//      kb.add(rule("hairOf(X, blonde) => livesNextTo(adele, X)"));
//      kb.add(rule("livesNextTo(adele, X) => hairOf(X, blonde)"));
//      
//      kb.add(rule("woman(A), livesAtHouse(A, B), house(B), hairOf(A, C), hairColour(C) => everythingAbout(A, B, C)"));
//      
//      assertTrue(query("oneOfThemIsHouseFour(4, 6)"));
//   }
   
   private boolean query(String stmtEncoding) {
      return kb.query(statement(stmtEncoding));
   }

}
