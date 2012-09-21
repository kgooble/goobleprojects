package com.gooble.logic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import com.gooble.logic.console.*;
import com.gooble.logic.kb.KnowledgeBase;

public class LogicMain {
   
   private final static List<Command> COMMANDS;
   static{
      COMMANDS = new ArrayList<Command>();
      COMMANDS.add(new QuitCommand());
      COMMANDS.add(new AddRuleCommand());
      COMMANDS.add(new AddFactCommand());
      COMMANDS.add(new QueryCommand());
   }

   public static void main(String[] args) {
      KnowledgeBase kb = new KnowledgeBase();
      
      System.out.println("Type QUIT to quit");
      System.out.println("Rule format: 'p(X) ^ g(X) => h(X).' (^ symbolizes logical AND)");
      System.out.println("Fact format: 'p(X).', 'g(a).'");
      System.out.println("Names in all upper-case count as Variables (e.g X, COW, K_1)");
      System.out.println("Names not in all upper-case are ground terms e.g. bob, joe, cow");
      System.out.println("To do a query, end your statement in '?' instead of '.' . Queries may not be in rule format");
      
      BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
      boolean exiting = false;
      while(true){
         try {
            String text = reader.readLine();
            for (Command cmd : COMMANDS){
               if (cmd.match(text)){
                  CommandResult result = cmd.process(text, kb);
                  if (result == CommandResult.QUIT)
                     exiting = true;
                  System.out.println(result.getMessage());
                  break;
               }
            }
         } catch (IOException e) {
            e.printStackTrace();
         }
         if (exiting)
            break;
      }
   }
   
}
