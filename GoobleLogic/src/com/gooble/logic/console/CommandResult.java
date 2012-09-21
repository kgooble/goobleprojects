package com.gooble.logic.console;

public class CommandResult{
   public final static CommandResult QUIT = new CommandResult("Quit");
   public final static CommandResult ADDED_FACT = new CommandResult("Added Fact");
   public final static CommandResult ADDED_RULE = new CommandResult("Added Rule");
   
   private final String message;
   
   public CommandResult(String message) {
      this.message = message;
   }

   public String getMessage() {
      return message;
   }
}