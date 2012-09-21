package com.gooble.logic.test.console;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import com.gooble.logic.console.CommandResult;
import com.gooble.logic.console.QuitCommand;

public class QuitCommandTest {

   private QuitCommand command;

   @Before
   public void make_command() {
      command = new QuitCommand();
   }
   
   @Test
   public void matches_string() throws Exception{
      assertTrue(command.match("quit"));
      assertTrue(command.match("QUIT"));
      assertFalse(command.match("q"));
   }
   
   @Test
   public void return_quit_result() throws Exception{
      assertEquals(CommandResult.QUIT, command.process("quit", null));
   }
   
}
