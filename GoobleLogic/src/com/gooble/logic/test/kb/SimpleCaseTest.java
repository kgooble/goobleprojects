package com.gooble.logic.test.kb;

import static com.gooble.logic.kb.KBEncoding.replacement;
import static com.gooble.logic.kb.KBEncoding.rule;
import static com.gooble.logic.kb.KBEncoding.solution;
import static com.gooble.logic.kb.KBEncoding.statement;
import static junit.framework.Assert.assertEquals;

import java.util.Arrays;

import org.junit.After;
import org.junit.Test;

import com.gooble.logic.Logger;
import com.gooble.logic.kb.KnowledgeBase;

public class SimpleCaseTest {

   @Test
   public void encoding_a_simple_problem() throws Exception{
      KnowledgeBase kb = new KnowledgeBase();
      kb.add(statement("age(17)"));
      kb.add(statement("age(22)"));
      kb.add(statement("age(33)"));
      
      kb.add(statement("man(bob)"));
      kb.add(statement("man(alex)"));
      kb.add(statement("man(john)"));
      
      kb.add(statement("shoes(blue)"));
      kb.add(statement("shoes(green)"));
      kb.add(statement("shoes(red)"));
      
      kb.add(rule("age(X) ^ man(Y) => ageOf(Y, X)"));
      kb.add(rule("shoes(X) ^ man(Y) => wears(X, Y)"));
      
      kb.add(rule("ageOf(bob, X) ^ ageOf(G, Y) ^ X < Y ^ wears(blue, G) => solution(X, Y, G)"));
      
      assertEquals(Arrays.asList(
            solution(replacement("AGEBOB", 22), replacement("AGEOTHER", 33), replacement("OTHER", "bob")),
            solution(replacement("AGEBOB", 22), replacement("AGEOTHER", 33), replacement("OTHER", "alex")),
            solution(replacement("AGEBOB", 22), replacement("AGEOTHER", 33), replacement("OTHER", "john")), 
            solution(replacement("AGEBOB", 17), replacement("AGEOTHER", 33), replacement("OTHER", "bob")),
            solution(replacement("AGEBOB", 17), replacement("AGEOTHER", 33), replacement("OTHER", "alex")),
            solution(replacement("AGEBOB", 17), replacement("AGEOTHER", 33), replacement("OTHER", "john")),
            solution(replacement("AGEBOB", 17), replacement("AGEOTHER", 22), replacement("OTHER", "bob")),
            solution(replacement("AGEBOB", 17), replacement("AGEOTHER", 22), replacement("OTHER", "alex")),
            solution(replacement("AGEBOB", 17), replacement("AGEOTHER", 22), replacement("OTHER", "john"))),
            kb.findSolutions(statement("solution(AGEBOB, AGEOTHER, OTHER)")).getSolutions());
   }
   
   @After
   public void after() throws Exception{
      Logger.close();
   }
   
}
