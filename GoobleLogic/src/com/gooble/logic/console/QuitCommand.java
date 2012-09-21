package com.gooble.logic.console;

import com.gooble.logic.kb.KnowledgeBaseFacade;

public class QuitCommand implements Command{

   @Override
   public boolean match(String command) {
      return "quit".equalsIgnoreCase(command);
   }

   @Override
   public CommandResult process(String command, KnowledgeBaseFacade kb) {
      return CommandResult.QUIT;
   }

}
