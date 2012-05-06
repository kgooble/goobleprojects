package com.gooble.logic.test.puzzle;

import static com.gooble.logic.kb.encoding.KBEncoding.rule;
import static junit.framework.Assert.assertEquals;

import org.junit.Test;

import com.gooble.logic.kb.Rule;
import com.gooble.logic.puzzle.Hint;
import com.gooble.logic.puzzle.Property;
import com.gooble.logic.puzzle.Relation;

public class HintTest {
   @Test
   public void convert_hint_to_rule_generates_rule_from_statements() throws Exception {
      Hint hint = new Hint(new Relation("olderThan", "X", "bob"), new Property("shoes", "X", "red"));
      Rule expectedRule = rule("olderThan(X, bob) ^ shoesOf(X, red) => hint(X)");
      Rule actualRule = hint.toRule();
      assertEquals(expectedRule, actualRule);
   }
}
