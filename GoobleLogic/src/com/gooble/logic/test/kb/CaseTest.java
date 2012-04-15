package com.gooble.logic.test.kb;

import static com.gooble.logic.kb.KBEncoding.replacement;
import static com.gooble.logic.kb.KBEncoding.rule;
import static com.gooble.logic.kb.KBEncoding.solution;
import static com.gooble.logic.kb.KBEncoding.statement;
import static junit.framework.Assert.assertFalse;
import static org.junit.Assert.assertEquals;

import java.util.Arrays;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.gooble.logic.Logger;
import com.gooble.logic.kb.KnowledgeBase;

public class CaseTest {
   
   private KnowledgeBase kb;

   @Before
   public void setup() throws Exception{
      kb = new KnowledgeBase();      
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
      
      kb.add(rule("eastHouse(X) ^ eastHouse(Y) ^ oneOfThemIsHouseFour(X, Y) => nextTo(X, Y)"));
      kb.add(rule("westHouse(X) ^ westHouse(Y) => nextTo(X, Y)"));
      
      kb.add(rule("house(X) ^ house(Y) ^ X > Y => northOf(X, Y)"));
      kb.add(rule("house(X) ^ house(Y) ^ X < Y => southOf(X, Y)"));      
      
      // All hair colours
      kb.add(statement("hairColour(black)"));
      kb.add(statement("hairColour(blonde)"));
      kb.add(statement("hairColour(brown)"));
      kb.add(statement("hairColour(chestnut)"));
      kb.add(statement("hairColour(grey)"));
   }
   
   @Test
   public void a_few_different_queries() throws Exception{
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
      assertEquals(Arrays.asList(solution(replacement("H", 4)), solution(replacement("H", 5))), kb.findSolutions(statement("northOf(H, 3)")).getSolutions());
      assertFalse(kb.query(statement("nextTo(1, 2)")));
   }
   
   @After
   public void close(){
      Logger.close();
   }
   
}
