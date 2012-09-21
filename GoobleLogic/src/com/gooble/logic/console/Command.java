package com.gooble.logic.console;

import com.gooble.logic.kb.KnowledgeBaseFacade;

public interface Command {

   boolean match(String command);
   CommandResult process(String command, KnowledgeBaseFacade kb);
   
}
