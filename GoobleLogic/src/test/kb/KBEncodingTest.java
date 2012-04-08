package test.kb;

import main.kb.Constant;
import main.kb.Replacement;
import main.kb.Rule;
import main.kb.Solution;
import main.kb.Statement;
import main.kb.Variable;
import main.kb.stmts.GreaterThan;
import main.kb.stmts.LessThan;

import org.junit.Test;

import static junit.framework.Assert.assertEquals;
import static main.kb.KBEncoding.*;

public class KBEncodingTest {
   
   @Test
   public void encode_statement() throws Exception{
      assertEquals(new Statement("p", new Variable("X")), statement("p(X)"));
      assertEquals(new Statement("p", new Variable("X"), new Variable("Y")), statement("p(X, Y)"));
      assertEquals(new Statement("p", new Constant<String>("a"), new Variable("Y")), statement("p(a, Y)"));
   }
   
   @Test
   public void encode_rule() throws Exception{
      assertEquals(new Rule(new Statement("p", new Variable("X")), new Statement("g", new Variable("X"))), rule("g(X) => p(X)"));
      assertEquals(new Rule(new Statement("p", new Variable("X")), new Statement("g", new Variable("X")), new Statement("h", new Variable("X"))), rule("g(X) ^ h(X) => p(X)"));
   }
   
   @Test
   public void encode_rule_with_operator() throws Exception{
      assertEquals(new Rule(new Statement("p", new Variable("X")), new GreaterThan(new Constant<Number>(17), new Variable("X"))), rule("X > 17 => p(X)"));
      assertEquals(new Rule(new Statement("p", new Variable("X")), new LessThan(new Constant<Number>(17), new Variable("X"))), rule("X < 17 => p(X)"));
   }
   
   @Test
   public void encode_solution_with_replacements() throws Exception{
      Solution solution = new Solution();
      solution.addVariableReplacement(new Replacement(new Variable("X"), new Constant<Number>(5)));
      solution.addVariableReplacement(new Replacement(new Variable("Y"), new Constant<String>("sixty")));
      assertEquals(solution, solution(replacement("X", 5), replacement("Y", "sixty")));
   }
}
