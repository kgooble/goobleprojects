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
      
      // bob is younger than the guy wearing blue shoes
      kb.add(rule("ageOf(bob, X) ^ ageOf(G, Y) ^ X < Y ^ wears(blue, G) => solution1(X, Y, G)"));

      // the guy wearing green shoes is older than the guy wearing red shoes
      kb.add(rule("ageOf(G1, A1) ^ ageOf(G2, A2) ^ A1 < A2 ^ wears(green, G2) ^ wears(red, G1) => solution2(G1, G2, A1, A2)"));
      
      // john wears red shoes
      kb.add(statement("wears(red, john)"));
      
      // Solution: alex, 33, blue; john, 17, red; bob, 22, green
      
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
            kb.findSolutions(statement("solution1(AGEBOB, AGEOTHER, OTHER)")).getSolutions());
      
      assertEquals(Arrays.asList(
            // ageOf(john, 17) ^ ageOf(bob, 33) ^ wears(green, john) ^ wears(red, bob)   
            solution(replacement("YOUNGAGE", 17), replacement("YOUNGGUY", "john"), replacement("OLDAGE", 33), replacement("OLDGUY", "bob")),
            solution(replacement("YOUNGAGE", 17), replacement("YOUNGGUY", "john"), replacement("OLDAGE", 33), replacement("OLDGUY", "alex")),
            solution(replacement("YOUNGAGE", 17), replacement("YOUNGGUY", "john"), replacement("OLDAGE", 33), replacement("OLDGUY", "john")),
            solution(replacement("YOUNGAGE", 17), replacement("YOUNGGUY", "john"), replacement("OLDAGE", 22), replacement("OLDGUY", "bob")),
            solution(replacement("YOUNGAGE", 17), replacement("YOUNGGUY", "john"), replacement("OLDAGE", 22), replacement("OLDGUY", "alex")),
            solution(replacement("YOUNGAGE", 17), replacement("YOUNGGUY", "john"), replacement("OLDAGE", 22), replacement("OLDGUY", "john")),
            solution(replacement("YOUNGAGE", 17), replacement("YOUNGGUY", "alex"), replacement("OLDAGE", 33), replacement("OLDGUY", "bob")),
            solution(replacement("YOUNGAGE", 17), replacement("YOUNGGUY", "alex"), replacement("OLDAGE", 33), replacement("OLDGUY", "alex")),
            solution(replacement("YOUNGAGE", 17), replacement("YOUNGGUY", "alex"), replacement("OLDAGE", 33), replacement("OLDGUY", "john")),
            solution(replacement("YOUNGAGE", 17), replacement("YOUNGGUY", "alex"), replacement("OLDAGE", 22), replacement("OLDGUY", "bob")),
            solution(replacement("YOUNGAGE", 17), replacement("YOUNGGUY", "alex"), replacement("OLDAGE", 22), replacement("OLDGUY", "alex")),
            solution(replacement("YOUNGAGE", 17), replacement("YOUNGGUY", "alex"), replacement("OLDAGE", 22), replacement("OLDGUY", "john")),
            solution(replacement("YOUNGAGE", 17), replacement("YOUNGGUY", "bob"), replacement("OLDAGE", 33), replacement("OLDGUY", "bob")),
            solution(replacement("YOUNGAGE", 17), replacement("YOUNGGUY", "bob"), replacement("OLDAGE", 33), replacement("OLDGUY", "alex")),
            solution(replacement("YOUNGAGE", 17), replacement("YOUNGGUY", "bob"), replacement("OLDAGE", 33), replacement("OLDGUY", "john")),
            solution(replacement("YOUNGAGE", 17), replacement("YOUNGGUY", "bob"), replacement("OLDAGE", 22), replacement("OLDGUY", "bob")),
            solution(replacement("YOUNGAGE", 17), replacement("YOUNGGUY", "bob"), replacement("OLDAGE", 22), replacement("OLDGUY", "alex")),
            solution(replacement("YOUNGAGE", 17), replacement("YOUNGGUY", "bob"), replacement("OLDAGE", 22), replacement("OLDGUY", "john")),
            solution(replacement("YOUNGAGE", 22), replacement("YOUNGGUY", "john"), replacement("OLDAGE", 33), replacement("OLDGUY", "bob")),
            solution(replacement("YOUNGAGE", 22), replacement("YOUNGGUY", "john"), replacement("OLDAGE", 33), replacement("OLDGUY", "alex")),
            solution(replacement("YOUNGAGE", 22), replacement("YOUNGGUY", "john"), replacement("OLDAGE", 33), replacement("OLDGUY", "john")),
            solution(replacement("YOUNGAGE", 22), replacement("YOUNGGUY", "alex"), replacement("OLDAGE", 33), replacement("OLDGUY", "bob")),
            solution(replacement("YOUNGAGE", 22), replacement("YOUNGGUY", "alex"), replacement("OLDAGE", 33), replacement("OLDGUY", "alex")),
            solution(replacement("YOUNGAGE", 22), replacement("YOUNGGUY", "alex"), replacement("OLDAGE", 33), replacement("OLDGUY", "john")),
            solution(replacement("YOUNGAGE", 22), replacement("YOUNGGUY", "bob"), replacement("OLDAGE", 33), replacement("OLDGUY", "bob")),
            solution(replacement("YOUNGAGE", 22), replacement("YOUNGGUY", "bob"), replacement("OLDAGE", 33), replacement("OLDGUY", "alex")), 
            solution(replacement("YOUNGAGE", 22), replacement("YOUNGGUY", "bob"), replacement("OLDAGE", 33), replacement("OLDGUY", "john"))
            ), 
            kb.findSolutions(statement("solution2(YOUNGGUY, OLDGUY, YOUNGAGE, OLDAGE)")).getSolutions());
   }
   
   @After
   public void after() throws Exception{
      Logger.close();
   }
   
}
