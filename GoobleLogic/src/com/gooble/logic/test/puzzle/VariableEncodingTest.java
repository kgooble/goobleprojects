package com.gooble.logic.test.puzzle;

import static com.gooble.logic.kb.KBEncoding.rule;
import static com.gooble.logic.kb.KBEncoding.statement;
import static junit.framework.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

import org.junit.Test;

import com.gooble.logic.puzzle.VariableEncoding;

public class VariableEncodingTest {
   private <T> SortedSet<T> set(T... stuff){
      return setFromList(Arrays.asList(stuff));
   }
   private <T> SortedSet<T> setFromList(List<T> stuff){
      return new TreeSet<T>(stuff);
   }
   @Test
   public void encode_variables_into_knowledge_base() throws Exception {
      KBStub kb = new KBStub();
      VariableEncoding varEnc = new VariableEncoding();
      varEnc.add("name", "alice", "bob", "carl");
      varEnc.add("age", 17, 18, 19);
      varEnc.add("height", 5.5, 6.0, 5.1);
      varEnc.setMain("name");
      varEnc.augment(kb);
      assertEquals(
            set(statement("name(alice)"), statement("name(bob)"), statement("name(carl)"),
                statement("age(17)"), statement("age(18)"), statement("age(19)"),
                statement("height(5.5)"), statement("height(6.0)"), statement("height(5.1)")), 
            setFromList(kb.stmts));
      assertEquals(set(
            rule("name(X) ^ age(Y) => ageOf(X, Y)"),
            rule("name(X) ^ height(Y) => heightOf(X, Y)")), setFromList(kb.rules));
   }
}
