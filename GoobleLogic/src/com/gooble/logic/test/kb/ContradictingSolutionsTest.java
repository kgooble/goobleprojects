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
            statement("solution1(AGEBOB, AGEOTHER, OTHER)"),
            Arrays.asList(
            solution(replacement("AGEBOB", 22), replacement("AGEOTHER", 33), replacement("OTHER", "bob")),
            solution(replacement("AGEBOB", 22), replacement("AGEOTHER", 33), replacement("OTHER", "alex")),
            solution(replacement("AGEBOB", 22), replacement("AGEOTHER", 33), replacement("OTHER", "john")))).remove();
      
      assertEquals(Arrays.asList(
            solution(replacement("AGEBOB", 22), replacement("AGEOTHER", 33), replacement("OTHER", "alex")),
            solution(replacement("AGEBOB", 22), replacement("AGEOTHER", 33), replacement("OTHER", "john"))), newSolutions);
   }
   
}
