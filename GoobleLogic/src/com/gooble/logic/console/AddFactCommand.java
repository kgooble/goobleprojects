package com.gooble.logic.console;

import static com.gooble.logic.kb.encoding.KBEncoding.statement;

import java.util.regex.Pattern;

import com.gooble.logic.kb.KnowledgeBaseFacade;

public class AddFactCommand implements Command{

   private final static Pattern ADD_FACT = Pattern.compile("^.*\\.$");
   
   public boolean match(String command) {
      return ADD_FACT.matcher(command).matches();
   }

   public CommandResult process(String command, KnowledgeBaseFacade kb) {
      kb.add(statement(command.substring(0, command.length() - 1)));
      return CommandResult.ADDED_FACT;
   }

}
