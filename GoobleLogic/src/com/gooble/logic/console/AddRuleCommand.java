package com.gooble.logic.console;

import static com.gooble.logic.kb.encoding.KBEncoding.rule;

import java.util.regex.Pattern;

import com.gooble.logic.kb.KnowledgeBaseFacade;

public class AddRuleCommand implements Command {
   
   private final static Pattern ADD_RULE = Pattern.compile("^.*=>.*\\.$");
   
   @Override
   public boolean match(String command) {
      return ADD_RULE.matcher(command).matches();
   }

   @Override
   public CommandResult process(String command, KnowledgeBaseFacade kb) {
      kb.add(rule(command.substring(0, command.length() - 1)));
      return CommandResult.ADDED_RULE;
   }

}
