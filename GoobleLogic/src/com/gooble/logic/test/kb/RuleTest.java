package com.gooble.logic.test.kb;

import static com.gooble.logic.kb.encoding.KBEncoding.rule;
import static com.gooble.logic.test.TestUtilities.set;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Set;

import org.junit.Test;

import com.gooble.logic.kb.Rule;

public class RuleTest {

   @Test
   public void rule_does_not_equal_other_rule_with_different_variable_values() {
      Rule rule1 = rule("p(Y) => g(Y)");
      Rule rule2 = rule("p(X) => g(X)");
      assertFalse(rule2.equals(rule1));
      assertFalse(rule1.equals(rule2));
   }
   @Test
   public void rule_does_not_equal_other_rule_with_different_names() {
      Rule rule2 = rule("p(X) => h(X)");
      Rule rule1 = rule("p(X) => g(X)");
      assertFalse(rule1.equals(rule2));
      assertFalse(rule2.equals(rule1));
   }
   @Test
   public void rule_equals_rule_with_same_name_and_Variables() throws Exception {
      Rule rule2 = rule("p(X) => h(X)");
      Rule rule1 = rule("p(X) => h(X)");
      assertTrue(rule1.equals(rule2));
      assertTrue(rule2.equals(rule1));
   }
   
   @Test
   public void rule_with_multiple_antecedents_not_equal_to_similar_rule_with_diff_vars() throws Exception {
      Rule rule2 = rule("p(X) ^ g(X) => h(X)");
      Rule rule1 = rule("p(Y) ^ g(Y) => h(Y)");
      assertFalse(rule1.equals(rule2));
      assertFalse(rule2.equals(rule1));
   }
   
   @Test
   public void inequality_of_multiple_rules() throws Exception {
      Set<Rule> set1 = set(
            rule("ageOf(alice, V1) ^ heightOf(alice, V2) => solution1(V1, V2)"), 
            rule("ageOf(bob, V1) ^ heightOf(bob, V2) => solution2(V1, V2)"),
            rule("ageOf(carl, V1) ^ heightOf(carl, V2) => solution3(V1, V2)"));
      Set<Rule> set2 = set(
            rule("ageOf(alice, V1) ^ heightOfNot(alice, V2) => solution1(V1, V2)"), 
            rule("ageOf(bob, V1) ^ heightOf(bob, V2) => solution2(V1, V2)"),
            rule("ageOf(carl, V1) ^ heightOf(carl, V2) => solution3(V1, V2)"));
      assertFalse(set1.equals(set2));
   }

}
