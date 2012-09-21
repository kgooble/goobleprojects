package com.gooble.logic.test.console;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import com.gooble.logic.console.AddFactCommand;
import com.gooble.logic.console.CommandResult;
import com.gooble.logic.test.puzzle.stubs.KBStub;

public class AddFactCommandTest {
   private AddFactCommand command;

   @Before
   public void make_command() {
      command = new AddFactCommand();
   }
   
   @Test
   public void match() {
      assertTrue(command.match("p(x)."));
      assertFalse(command.match("p(X)?"));
   }
   
   @Test
   public void parse_then_add_fact_to_kb() {
      KBStub kb = new KBStub();
      assertEquals(0, kb.stmts.size());
      assertEquals(CommandResult.ADDED_FACT, command.process("p(x).", kb));
      assertEquals(1, kb.stmts.size());
   }

}
