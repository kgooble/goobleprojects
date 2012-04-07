package test;

import static junit.framework.Assert.assertTrue;
import static org.junit.Assert.*;

import java.util.Arrays;

import main.kb.Constant;
import main.kb.KnowledgeBase;
import main.kb.Solution;
import main.kb.Statement;
import main.kb.Variable;

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
      kb.addStatement(new Statement("smart", new Constant<String>("bob")));
      assertTrue(kb.query(new Statement("smart", new Constant<String>("bob"))));
      assertFalse(kb.query(new Statement("smart", new Constant<String>("joe"))));
   }
   
   @Test
   public void rule_and_statement_in_kb_query_provable_stmt_is_true() throws Exception{
      kb.addStatement(new Statement("smart", new Constant<String>("bob")));
      kb.addRule(new Statement("goodLooking", new Variable("X")), new Statement("smart", new Variable("X")));
      assertTrue(kb.query(new Statement("goodLooking", new Constant<String>("bob"))));
      assertFalse(kb.query(new Statement("goodLooking", new Constant<String>("joe"))));
   }
   
   @Test
   public void prove_statement_with_multiple_variables_and_use_of_numbers() throws Exception{
      // you're an adult if you're 18+
      kb.addStatement(new Statement("age", new Constant<String>("bob"), new Constant<Number>(17)));
      kb.addStatement(new Statement("age", new Constant<String>("joe"), new Constant<Number>(18)));
      kb.addRule(new Statement("adult", new Variable("X")), new Statement("age", new Variable("X"), new Variable("Y")), new GreaterThan(new Constant<Number>(17), new Variable("Y")));
      assertFalse(kb.query(new Statement("adult", new Constant<String>("bob"))));
      assertTrue(kb.query(new Statement("adult", new Constant<String>("joe"))));
   }
   
   @Test
   public void find_all_values_which_make_statement_true() throws Exception{
      kb.addStatement(new Statement("age", new Constant<String>("bob"), new Constant<Number>(17)));
      Solution solution = new Solution();
      solution.addVariableReplacement(new Variable("X"), new Constant<Number>(17));
      assertEquals(Arrays.asList(solution), kb.findSolutions(new Statement("age", new Constant<String>("bob"), new Variable("X"))));
   }
   
   class GreaterThan extends Statement{

      private Constant<?> constant;
      private Constant<Number> limit;

      public GreaterThan(Constant<Number> limit, Constant<?> constant) {
         super("greaterThan", limit, constant);
         this.limit = limit;
         this.constant = constant;
      }
      
      @Override
      public String toString(){
         return "(" + limit + " > " + constant + ")"; // constant > limit
      }
      
      @Override
      public boolean match(Statement other){
         return super.match(other);
      }
      
      @Override
      public boolean isToBeEvaluated() {
         return true;
      }
      
      @Override
      public boolean evaluate(){
         if (!(constant.getValue() instanceof Number))
            return false;
         else
            return ((Number) constant.getValue()).floatValue() > limit.getValue().floatValue(); 
      }
      
      @Override
      protected Statement createStatement(Constant<?>[] constants){
         return new GreaterThan((Constant<Number>) constants[0], constants[1]);
      }
      
   }
   
}
