package com.gooble.logic.console;

import static com.gooble.logic.kb.encoding.KBEncoding.statement;

import com.gooble.logic.kb.KnowledgeBaseFacade;

public class QueryCommand implements Command{

   @Override
   public boolean match(String command) {
      return command.endsWith("?");
   }

   @Override
   public CommandResult process(String command, KnowledgeBaseFacade kb) {
      return new CommandResult(kb.findSolutions(statement(command.substring(0, command.length() - 1))).toString());
   }

}
