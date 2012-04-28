package com.gooble.logic.test.kb;

import static com.gooble.logic.kb.encoding.KBEncoding.replacement;
import static com.gooble.logic.kb.encoding.KBEncoding.solution;
import static com.gooble.logic.kb.encoding.TermEncoding.constant;
import static com.gooble.logic.kb.encoding.TermEncoding.variable;
import static junit.framework.Assert.assertEquals;

import java.util.Arrays;

import org.junit.Test;

import com.gooble.logic.kb.Suffix;
import com.gooble.logic.kb.terms.Term;

public class SuffixTest {
   
   @Test
   public void suffix_solutions() throws Exception{
      assertEquals(Arrays.asList(solution(replacement("X1", "bob"))), 
            Suffix.solutions(Arrays.asList(solution(replacement("X", "bob"))), "1"));
   }
   @Test
   public void suffix_terms() throws Exception{
      Term<?>[] oldTerms = new Term<?>[]{ constant("bob"), variable("Y"), constant(49)};
      Term<?>[] expectedTerms = new Term<?>[]{ constant("bob"), variable("Y1"), constant(49)};
      assertEquals(Arrays.asList(expectedTerms), Arrays.asList(Suffix.terms(oldTerms, "1")));
   }

}
