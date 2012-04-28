package com.gooble.logic.test.kb;

import static com.gooble.logic.kb.encoding.KBEncoding.rule;
import static junit.framework.Assert.assertEquals;

import org.junit.Test;

import com.gooble.logic.kb.Rules;

public class RulesTest {

   @Test
   public void merge() throws Exception{
      assertEquals(rule("p(X1) ^ p(X2) ^ h(X2) => gq(X1, X2)"), 
            new Rules(rule("p(X) => g(X)"), rule("p(X) ^ h(X) => q(X)")).merge());
   }
   
}
