package com.gooble.logic.test.kb.encoding;

import static com.gooble.logic.kb.encoding.KBEncoding.replacement;
import static com.gooble.logic.kb.encoding.KBEncoding.rule;
import static com.gooble.logic.kb.encoding.KBEncoding.solution;
import static com.gooble.logic.kb.encoding.KBEncoding.statement;
import static junit.framework.Assert.assertEquals;

import org.junit.Test;

import com.gooble.logic.kb.Replacement;
import com.gooble.logic.kb.Rule;
import com.gooble.logic.kb.solutions.Solution;
import com.gooble.logic.kb.stmts.GreaterThan;
import com.gooble.logic.kb.stmts.LessThan;
import com.gooble.logic.kb.stmts.Statement;
import com.gooble.logic.kb.terms.Constant;
import com.gooble.logic.kb.terms.Variable;

public class KBEncodingTest {
   
   @Test
   public void encode_statement() throws Exception{
      assertEquals(new Statement("p", new Constant<String>("a")), statement("p(a)"));
      assertEquals(new Statement("p", new Variable("X")), statement("p(X)"));
      assertEquals(new Statement("p", new Variable("X"), new Variable("Y")), statement("p(X, Y)"));
      assertEquals(new Statement("p", new Variable("X"), new Variable("Y")), statement("p(X,Y)"));
      assertEquals(new Statement("p", new Constant<String>("a"), new Variable("Y")), statement("p(a, Y)"));
      assertEquals(new GreaterThan(new Constant<Number>(17), new Variable("X")), statement("X > 17"));
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
      solution.addVariableReplacement(new Replacement(new Variable("T"), new Variable("Z")));
      assertEquals(solution, solution(replacement("X", 5), replacement("Y", "sixty"), replacement("T", "Z")));
   }
}
