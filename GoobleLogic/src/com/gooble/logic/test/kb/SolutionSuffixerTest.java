package com.gooble.logic.test.kb;

import static com.gooble.logic.kb.KBEncoding.replacement;
import static com.gooble.logic.kb.KBEncoding.solution;
import static junit.framework.Assert.assertEquals;

import java.util.Arrays;

import org.junit.Test;

import com.gooble.logic.kb.SolutionSuffixer;

public class SolutionSuffixerTest {
   
   @Test
   public void suffix() throws Exception{
      assertEquals(Arrays.asList(solution(replacement("X1", "bob"))), 
            SolutionSuffixer.suffix(Arrays.asList(solution(replacement("X", "bob"))), "1"));
   }

}
