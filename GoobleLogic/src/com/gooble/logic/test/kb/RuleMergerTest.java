package com.gooble.logic.test.kb;

import static com.gooble.logic.kb.KBEncoding.rule;
import static junit.framework.Assert.assertEquals;

import org.junit.Test;

import com.gooble.logic.kb.RuleMerger;

public class RuleMergerTest {

   @Test
   public void merge() throws Exception{
      assertEquals(rule("p(X1) ^ p(X2) ^ h(X2) => gq(X1, X2)"), 
            RuleMerger.merge(rule("p(X) => g(X)"), rule("p(X) ^ h(X) => q(X)")));
   }
   
}
