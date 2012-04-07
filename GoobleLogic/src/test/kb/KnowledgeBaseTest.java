package test.kb;

import static junit.framework.Assert.assertTrue;
import static main.kb.KBEncoding.rule;
import static main.kb.KBEncoding.stmt;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.util.Arrays;
import java.util.Collections;

import main.kb.Constant;
import main.kb.KnowledgeBase;
import main.kb.Solution;
import main.kb.Statement;
import main.kb.Variable;
import main.kb.stmts.GreaterThan;

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
      kb.add(stmt("smart(bob)"));
      assertTrue(kb.query(stmt("smart(bob)")));
      assertFalse(kb.query(stmt("smart(joe)")));
   }
   
   @Test
   public void rule_and_statement_in_kb_query_provable_stmt_is_true() throws Exception{
      kb.add(stmt("smart(bob)"));
      kb.add(rule("smart(X) => goodLooking(X)"));
      
      assertTrue(query("goodLooking(bob)"));
      assertFalse(query("goodLooking(joe)"));
   }

   @Test
   public void prove_statement_with_multiple_variables_and_use_of_numbers() throws Exception{
      // you're an adult if you're 18+
      kb.add(stmt("age(bob, 17)"));
      kb.add(stmt("age(joe, 18)"));
//      kb.add(rule("adult(X)"));
      kb.addRule(new Statement("adult", new Variable("X")), new Statement("age", new Variable("X"), new Variable("Y")), new GreaterThan(new Constant<Number>(17), new Variable("Y")));
      
      assertFalse(query("adult(bob)"));
      assertTrue(query("adult(joe)"));
   }
   
   @Test
   public void find_all_values_which_make_statement_true() throws Exception{
      kb.add(stmt("age(bob, 17)"));
      
      Solution solution = new Solution();
      solution.addVariableReplacement(new Variable("X"), new Constant<Number>(17));
      assertEquals(Arrays.asList(solution), kb.findSolutions(stmt("age(bob, X)")));
      assertEquals(Collections.<Solution>emptyList(), kb.findSolutions(new Statement("age", new Constant<String>("mary"), new Variable("X"))));
   }
   
   @Test
   public void chain_provable_query() throws Exception{
      kb.add(stmt("q(a)"));
      kb.add(stmt("z(a)"));
      
      kb.add(rule("h(X) => p(X)"));
      kb.add(rule("g(X) ^ b(X) => p(X)"));
      kb.add(rule("q(X) ^ y(X) => h(X)"));
      kb.add(rule("z(X) => y(X)"));
      
      assertTrue(query("p(a)"));
      assertFalse(query("p(b)"));
   }
   
   @Test
   public void logic_puzzle_1_from_book_case_test() throws Exception{
      System.out.println(rule("p(X, Y) ^ q(X) => g(X, Y)"));
   }
   
   private boolean query(String stmtEncoding) {
      return kb.query(stmt(stmtEncoding));
   }

}
