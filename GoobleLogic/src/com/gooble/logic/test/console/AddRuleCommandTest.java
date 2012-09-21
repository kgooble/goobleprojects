package com.gooble.logic.test.console;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import com.gooble.logic.console.AddRuleCommand;
import com.gooble.logic.console.CommandResult;
import com.gooble.logic.test.puzzle.stubs.KBStub;

public class AddRuleCommandTest {
   private AddRuleCommand command;

   @Before
   public void make_command() {
      command = new AddRuleCommand();
   }
   
   @Test
   public void match() {
      assertTrue(command.match("p(x) => g(x)."));
      assertFalse(command.match("p(x)."));
      assertFalse(command.match("p(X)?"));
   }
   
   @Test
   public void parse_then_add_fact_to_kb() {
      KBStub kb = new KBStub();
      assertEquals(0, kb.rules.size());
      assertEquals(CommandResult.ADDED_RULE, command.process("p(x) => g(x).", kb));
      assertEquals(1, kb.rules.size());
   }
}
