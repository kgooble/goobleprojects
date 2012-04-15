package com.gooble.logic.test.kb;

import static com.gooble.logic.kb.KBEncoding.replacement;
import static com.gooble.logic.kb.KBEncoding.rule;
import static com.gooble.logic.kb.KBEncoding.solution;
import static com.gooble.logic.kb.KBEncoding.statement;
import static junit.framework.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import com.gooble.logic.kb.ContradictingSolutions;
import com.gooble.logic.kb.solutions.Solution;

public class ContradictingSolutionsTest {

   @Test
   public void remove_contradicting_solutions_for_unique_variable_value_domains() throws Exception{
      List<Solution> newSolutions = new ContradictingSolutions(
            rule("ageOf(bob, X) ^ ageOf(G, Y) ^ X < Y ^ wears(blue, G) => solution1(X, Y, G)"),
            statement("solution1(BOBAGE, BLUESHOESAGE, BLUESHOESGUY)"),
            Arrays.asList(
            solution(replacement("BOBAGE", 22), replacement("BLUESHOESAGE", 33), replacement("BLUESHOESGUY", "bob")),
            solution(replacement("BOBAGE", 22), replacement("BLUESHOESAGE", 33), replacement("BLUESHOESGUY", "alex")),
            solution(replacement("BOBAGE", 22), replacement("BLUESHOESAGE", 33), replacement("BLUESHOESGUY", "john")))).remove();
      
      assertEquals(Arrays.asList(
            solution(replacement("BOBAGE", 22), replacement("BLUESHOESAGE", 33), replacement("BLUESHOESGUY", "alex")),
            solution(replacement("BOBAGE", 22), replacement("BLUESHOESAGE", 33), replacement("BLUESHOESGUY", "john"))), newSolutions);
   }
   
   @Test
   public void merged_solution_with_subset() throws Exception{
      List<Solution> newSolutions = new ContradictingSolutions(
            rule("ageOf(bob, X) ^ ageOf(G, Y) ^ X < Y ^ wears(blue, G) ^ ageOf(G1, A1) ^ ageOf(G2, A2) ^ A1 < A2 ^ wears(green, G2) ^ wears(red, G1) => solution1(X, Y, G, G1, G2, A1, A2)"),
            statement("solution1(BOBAGE, BLUESHOESAGE, BLUESHOESGUY, REDSHOESYOUNGGUY, GREENSHOESOLDGUY, REDSHOESAGE, GREENSHOESAGE)"),
            Arrays.asList(
            solution(replacement("BOBAGE", 22), replacement("BLUESHOESAGE", 33), replacement("BLUESHOESGUY", "bob"), replacement("REDSHOESAGE", 17), replacement("REDSHOESYOUNGGUY", "john"), replacement("GREENSHOESAGE", 33), replacement("GREENSHOESOLDGUY", "bob")),
            solution(replacement("BOBAGE", 22), replacement("BLUESHOESAGE", 33), replacement("BLUESHOESGUY", "alex"), replacement("REDSHOESAGE", 17), replacement("REDSHOESYOUNGGUY", "john"), replacement("GREENSHOESAGE", 33), replacement("GREENSHOESOLDGUY", "bob")),
            solution(replacement("BOBAGE", 22), replacement("BLUESHOESAGE", 33), replacement("BLUESHOESGUY", "john"), replacement("REDSHOESAGE", 17), replacement("REDSHOESYOUNGGUY", "john"), replacement("GREENSHOESAGE", 33), replacement("GREENSHOESOLDGUY", "bob")),
            solution(replacement("BOBAGE", 22), replacement("BLUESHOESAGE", 33), replacement("BLUESHOESGUY", "bob"), replacement("REDSHOESAGE", 17), replacement("REDSHOESYOUNGGUY", "john"), replacement("GREENSHOESAGE", 33), replacement("GREENSHOESOLDGUY", "alex")),
            solution(replacement("BOBAGE", 22), replacement("BLUESHOESAGE", 33), replacement("BLUESHOESGUY", "alex"), replacement("REDSHOESAGE", 17), replacement("REDSHOESYOUNGGUY", "john"), replacement("GREENSHOESAGE", 33), replacement("GREENSHOESOLDGUY", "alex")),
            solution(replacement("BOBAGE", 22), replacement("BLUESHOESAGE", 33), replacement("BLUESHOESGUY", "john"), replacement("REDSHOESAGE", 17), replacement("REDSHOESYOUNGGUY", "john"), replacement("GREENSHOESAGE", 33), replacement("GREENSHOESOLDGUY", "alex")),
            solution(replacement("BOBAGE", 22), replacement("BLUESHOESAGE", 33), replacement("BLUESHOESGUY", "bob"), replacement("REDSHOESAGE", 17), replacement("REDSHOESYOUNGGUY", "john"), replacement("GREENSHOESAGE", 22), replacement("GREENSHOESOLDGUY", "bob")),
            solution(replacement("BOBAGE", 22), replacement("BLUESHOESAGE", 33), replacement("BLUESHOESGUY", "alex"), replacement("REDSHOESAGE", 17), replacement("REDSHOESYOUNGGUY", "john"), replacement("GREENSHOESAGE", 22), replacement("GREENSHOESOLDGUY", "bob")),
            solution(replacement("BOBAGE", 22), replacement("BLUESHOESAGE", 33), replacement("BLUESHOESGUY", "john"), replacement("REDSHOESAGE", 17), replacement("REDSHOESYOUNGGUY", "john"), replacement("GREENSHOESAGE", 22), replacement("GREENSHOESOLDGUY", "bob")))).remove();
      assertEquals(Arrays.asList(solution(replacement("BOBAGE", 22), replacement("BLUESHOESAGE", 33), replacement("BLUESHOESGUY", "alex"), replacement("REDSHOESAGE", 17), replacement("REDSHOESYOUNGGUY", "john"), replacement("GREENSHOESAGE", 22), replacement("GREENSHOESOLDGUY", "bob"))), 
            newSolutions);
   }
   
   @Test
   public void remove_contradictory_solutions_case_2() throws Exception{
      List<Solution> newSolutions = new ContradictingSolutions(
            rule("ageOf(G1, A1) ^ ageOf(G2, A2) ^ A1 < A2 ^ wears(green, G2) ^ wears(red, G1) => solution2(G1, G2, A1, A2)"), 
            statement("solution2(REDSHOESYOUNGGUY, GREENSHOESOLDGUY, REDSHOESAGE, GREENSHOESAGE)"), Arrays.asList(
            solution(replacement("REDSHOESAGE", 17), replacement("REDSHOESYOUNGGUY", "john"), replacement("GREENSHOESAGE", 33), replacement("GREENSHOESOLDGUY", "bob")),
            solution(replacement("REDSHOESAGE", 17), replacement("REDSHOESYOUNGGUY", "john"), replacement("GREENSHOESAGE", 33), replacement("GREENSHOESOLDGUY", "alex")),
            solution(replacement("REDSHOESAGE", 17), replacement("REDSHOESYOUNGGUY", "john"), replacement("GREENSHOESAGE", 33), replacement("GREENSHOESOLDGUY", "john")),
            solution(replacement("REDSHOESAGE", 17), replacement("REDSHOESYOUNGGUY", "john"), replacement("GREENSHOESAGE", 22), replacement("GREENSHOESOLDGUY", "bob")),
            solution(replacement("REDSHOESAGE", 17), replacement("REDSHOESYOUNGGUY", "john"), replacement("GREENSHOESAGE", 22), replacement("GREENSHOESOLDGUY", "alex")),
            solution(replacement("REDSHOESAGE", 17), replacement("REDSHOESYOUNGGUY", "john"), replacement("GREENSHOESAGE", 22), replacement("GREENSHOESOLDGUY", "john")),
            solution(replacement("REDSHOESAGE", 17), replacement("REDSHOESYOUNGGUY", "alex"), replacement("GREENSHOESAGE", 33), replacement("GREENSHOESOLDGUY", "bob")),
            solution(replacement("REDSHOESAGE", 17), replacement("REDSHOESYOUNGGUY", "alex"), replacement("GREENSHOESAGE", 33), replacement("GREENSHOESOLDGUY", "alex")),
            solution(replacement("REDSHOESAGE", 17), replacement("REDSHOESYOUNGGUY", "alex"), replacement("GREENSHOESAGE", 33), replacement("GREENSHOESOLDGUY", "john")),
            solution(replacement("REDSHOESAGE", 17), replacement("REDSHOESYOUNGGUY", "alex"), replacement("GREENSHOESAGE", 22), replacement("GREENSHOESOLDGUY", "bob")),
            solution(replacement("REDSHOESAGE", 17), replacement("REDSHOESYOUNGGUY", "alex"), replacement("GREENSHOESAGE", 22), replacement("GREENSHOESOLDGUY", "alex")),
            solution(replacement("REDSHOESAGE", 17), replacement("REDSHOESYOUNGGUY", "alex"), replacement("GREENSHOESAGE", 22), replacement("GREENSHOESOLDGUY", "john")),
            solution(replacement("REDSHOESAGE", 17), replacement("REDSHOESYOUNGGUY", "bob"), replacement("GREENSHOESAGE", 33), replacement("GREENSHOESOLDGUY", "bob")),
            solution(replacement("REDSHOESAGE", 17), replacement("REDSHOESYOUNGGUY", "bob"), replacement("GREENSHOESAGE", 33), replacement("GREENSHOESOLDGUY", "alex")),
            solution(replacement("REDSHOESAGE", 17), replacement("REDSHOESYOUNGGUY", "bob"), replacement("GREENSHOESAGE", 33), replacement("GREENSHOESOLDGUY", "john")),
            solution(replacement("REDSHOESAGE", 17), replacement("REDSHOESYOUNGGUY", "bob"), replacement("GREENSHOESAGE", 22), replacement("GREENSHOESOLDGUY", "bob")),
            solution(replacement("REDSHOESAGE", 17), replacement("REDSHOESYOUNGGUY", "bob"), replacement("GREENSHOESAGE", 22), replacement("GREENSHOESOLDGUY", "alex")),
            solution(replacement("REDSHOESAGE", 17), replacement("REDSHOESYOUNGGUY", "bob"), replacement("GREENSHOESAGE", 22), replacement("GREENSHOESOLDGUY", "john")),
            solution(replacement("REDSHOESAGE", 22), replacement("REDSHOESYOUNGGUY", "john"), replacement("GREENSHOESAGE", 33), replacement("GREENSHOESOLDGUY", "bob")),
            solution(replacement("REDSHOESAGE", 22), replacement("REDSHOESYOUNGGUY", "john"), replacement("GREENSHOESAGE", 33), replacement("GREENSHOESOLDGUY", "alex")),
            solution(replacement("REDSHOESAGE", 22), replacement("REDSHOESYOUNGGUY", "john"), replacement("GREENSHOESAGE", 33), replacement("GREENSHOESOLDGUY", "john")),
            solution(replacement("REDSHOESAGE", 22), replacement("REDSHOESYOUNGGUY", "alex"), replacement("GREENSHOESAGE", 33), replacement("GREENSHOESOLDGUY", "bob")),
            solution(replacement("REDSHOESAGE", 22), replacement("REDSHOESYOUNGGUY", "alex"), replacement("GREENSHOESAGE", 33), replacement("GREENSHOESOLDGUY", "alex")),
            solution(replacement("REDSHOESAGE", 22), replacement("REDSHOESYOUNGGUY", "alex"), replacement("GREENSHOESAGE", 33), replacement("GREENSHOESOLDGUY", "john")),
            solution(replacement("REDSHOESAGE", 22), replacement("REDSHOESYOUNGGUY", "bob"), replacement("GREENSHOESAGE", 33), replacement("GREENSHOESOLDGUY", "bob")),
            solution(replacement("REDSHOESAGE", 22), replacement("REDSHOESYOUNGGUY", "bob"), replacement("GREENSHOESAGE", 33), replacement("GREENSHOESOLDGUY", "alex")), 
            solution(replacement("REDSHOESAGE", 22), replacement("REDSHOESYOUNGGUY", "bob"), replacement("GREENSHOESAGE", 33), replacement("GREENSHOESOLDGUY", "john"))
            )).remove();
      assertEquals(Arrays.asList(
            solution(replacement("REDSHOESAGE", 17), replacement("REDSHOESYOUNGGUY", "john"), replacement("GREENSHOESAGE", 33), replacement("GREENSHOESOLDGUY", "bob")),
            solution(replacement("REDSHOESAGE", 17), replacement("REDSHOESYOUNGGUY", "john"), replacement("GREENSHOESAGE", 33), replacement("GREENSHOESOLDGUY", "alex")),
            solution(replacement("REDSHOESAGE", 17), replacement("REDSHOESYOUNGGUY", "john"), replacement("GREENSHOESAGE", 22), replacement("GREENSHOESOLDGUY", "bob")),
            solution(replacement("REDSHOESAGE", 17), replacement("REDSHOESYOUNGGUY", "john"), replacement("GREENSHOESAGE", 22), replacement("GREENSHOESOLDGUY", "alex")),
            solution(replacement("REDSHOESAGE", 17), replacement("REDSHOESYOUNGGUY", "alex"), replacement("GREENSHOESAGE", 33), replacement("GREENSHOESOLDGUY", "bob")),
            solution(replacement("REDSHOESAGE", 17), replacement("REDSHOESYOUNGGUY", "alex"), replacement("GREENSHOESAGE", 33), replacement("GREENSHOESOLDGUY", "john")),
            solution(replacement("REDSHOESAGE", 17), replacement("REDSHOESYOUNGGUY", "alex"), replacement("GREENSHOESAGE", 22), replacement("GREENSHOESOLDGUY", "bob")),
            solution(replacement("REDSHOESAGE", 17), replacement("REDSHOESYOUNGGUY", "alex"), replacement("GREENSHOESAGE", 22), replacement("GREENSHOESOLDGUY", "john")),
            solution(replacement("REDSHOESAGE", 17), replacement("REDSHOESYOUNGGUY", "bob"), replacement("GREENSHOESAGE", 33), replacement("GREENSHOESOLDGUY", "alex")),
            solution(replacement("REDSHOESAGE", 17), replacement("REDSHOESYOUNGGUY", "bob"), replacement("GREENSHOESAGE", 33), replacement("GREENSHOESOLDGUY", "john")),
            solution(replacement("REDSHOESAGE", 17), replacement("REDSHOESYOUNGGUY", "bob"), replacement("GREENSHOESAGE", 22), replacement("GREENSHOESOLDGUY", "alex")),
            solution(replacement("REDSHOESAGE", 17), replacement("REDSHOESYOUNGGUY", "bob"), replacement("GREENSHOESAGE", 22), replacement("GREENSHOESOLDGUY", "john")),
            solution(replacement("REDSHOESAGE", 22), replacement("REDSHOESYOUNGGUY", "john"), replacement("GREENSHOESAGE", 33), replacement("GREENSHOESOLDGUY", "bob")),
            solution(replacement("REDSHOESAGE", 22), replacement("REDSHOESYOUNGGUY", "john"), replacement("GREENSHOESAGE", 33), replacement("GREENSHOESOLDGUY", "alex")),
            solution(replacement("REDSHOESAGE", 22), replacement("REDSHOESYOUNGGUY", "alex"), replacement("GREENSHOESAGE", 33), replacement("GREENSHOESOLDGUY", "bob")),
            solution(replacement("REDSHOESAGE", 22), replacement("REDSHOESYOUNGGUY", "alex"), replacement("GREENSHOESAGE", 33), replacement("GREENSHOESOLDGUY", "john")),
            solution(replacement("REDSHOESAGE", 22), replacement("REDSHOESYOUNGGUY", "bob"), replacement("GREENSHOESAGE", 33), replacement("GREENSHOESOLDGUY", "alex")), 
            solution(replacement("REDSHOESAGE", 22), replacement("REDSHOESYOUNGGUY", "bob"), replacement("GREENSHOESAGE", 33), replacement("GREENSHOESOLDGUY", "john"))
            ), newSolutions);
   }
   
}
