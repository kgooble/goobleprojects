package com.gooble.logic.test.puzzle;

import static com.gooble.logic.kb.encoding.KBEncoding.replacement;
import static com.gooble.logic.kb.encoding.KBEncoding.rule;
import static com.gooble.logic.kb.encoding.KBEncoding.solution;
import static com.gooble.logic.kb.encoding.KBEncoding.solutions;
import static junit.framework.Assert.assertTrue;
import static org.junit.Assert.assertFalse;


import org.junit.Test;

import com.gooble.logic.kb.Rule;
import com.gooble.logic.kb.solutions.SolutionSet;
import com.gooble.logic.puzzle.PuzzleResolver;
import com.gooble.logic.puzzle.ResolveResult;
import com.gooble.logic.puzzle.SolutionDefinition;

public class PuzzleResolverTest {
   @Test
   public void given_found_solutions_and_actual_solutions_puzzle_solvable_if_all_matches() throws Exception{
      SolutionDefinition solnDef = new SolutionDefinition("person", "age", "shoes");
      solnDef.add("alan", 16, "green");
      solnDef.add("bob", 17, "red");
      solnDef.add("carol", 18, "blue");
      
      Rule rule = rule("ageOf(alan, AGEALAN) ^ shoesOf(alan, SHOESALAN) " +
      		"^ ageOf(bob, AGEBOB) ^ shoesOf(bob, SHOESBOB) " +
      		"^ ageOf(carol, AGECAROL) ^ shoesOf(carol, SHOESCAROL) => " +
      		"solution1solution2solution3(AGEALAN, SHOESALAN, AGEBOB, SHOESBOB, AGECAROL, SHOESCAROL)");
      SolutionSet solnSet = solutions(true, solution(replacement("AGEALAN", 16), replacement("SHOESALAN", "green"),
            replacement("AGEBOB", 17), replacement("SHOESBOB", "red"), 
            replacement("AGECAROL", 18), replacement("SHOESCAROL", "blue")));
      
      ResolveResult result = new PuzzleResolver(solnDef).resolveSolutions(rule, solnSet);
      
      assertTrue(result.isPuzzleConsistent());
   }
   @Test
   public void if_there_is_solution_defined_but_not_found_then_puzzle_is_inconsistent() throws Exception{
      SolutionDefinition solnDef = new SolutionDefinition("person", "age", "shoes");
      solnDef.add("alan", 16, "green");
      solnDef.add("bob", 18, "red");
      solnDef.add("carol", 17, "blue");
      
      Rule rule = rule("ageOf(alan, AGEALAN) ^ shoesOf(alan, SHOESALAN) " +
            "^ ageOf(bob, AGEBOB) ^ shoesOf(bob, SHOESBOB) " +
            "^ ageOf(carol, AGECAROL) ^ shoesOf(carol, SHOESCAROL) => " +
            "solution1solution2solution3(AGEALAN, SHOESALAN, AGEBOB, SHOESBOB, AGECAROL, SHOESCAROL)");
      SolutionSet solnSet = solutions(true, solution(replacement("AGEALAN", 16), replacement("SHOESALAN", "green"),
            replacement("AGEBOB", 17), replacement("SHOESBOB", "red"), 
            replacement("AGECAROL", 18), replacement("SHOESCAROL", "blue")));
      
      ResolveResult result = new PuzzleResolver(solnDef).resolveSolutions(rule, solnSet);

      assertFalse(result.isPuzzleConsistent());
   }
   
}
