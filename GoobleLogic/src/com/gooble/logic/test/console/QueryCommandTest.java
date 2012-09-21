package com.gooble.logic.test.console;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import com.gooble.logic.console.CommandResult;
import com.gooble.logic.console.QueryCommand;
import com.gooble.logic.test.puzzle.stubs.KBStub;

public class QueryCommandTest {

   private QueryCommand command;
   @Before
   public void make_command() {
      command = new QueryCommand();
   }
   
   @Test
   public void match() {
      assertTrue(command.match("p(X)?"));
      assertFalse(command.match("p(X)."));
   }
   
   @Test
   public void parse_command_then_query_kb() {
      KBStub kb = new KBStub();
      CommandResult result = command.process("p(X)?", kb);
      assertTrue(result.getMessage().contains("QUERY "));
   }
   
}
