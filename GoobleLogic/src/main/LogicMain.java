package main;

import static main.kb.KBEncoding.rule;
import static main.kb.KBEncoding.statement;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import main.kb.KnowledgeBase;

public class LogicMain {

   public static void main(String[] args) {
      KnowledgeBase kb = new KnowledgeBase();
      
      System.out.println("Hello Saman");
      System.out.println("Type QUIT to quit");
      System.out.println("Rule format: 'p(X) ^ g(X) => h(X).' (^ symbolizes logical AND)");
      System.out.println("Fact format: 'p(X).', 'g(a).'");
      System.out.println("Names in all upper-case count as Variables (e.g X, COW, K_1)");
      System.out.println("Names not in all upper-case are ground terms e.g. bob, joe, cow");
      System.out.println("To do a query, end your statement in '?' instead of '.' . Queries may not be in rule format");
      
      BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
      
      while (true){
         try {
            String text = reader.readLine();
            if (text.endsWith(".")){
               if (text.indexOf("=>") == -1){
                  System.out.println("You added a fact");
                  kb.add(statement(text.substring(0, text.length() - 1)));
               } else{
                  System.out.println("You added a rule");
                  kb.add(rule(text.substring(0, text.length() - 1)));
               }
            }else{
               if ("QUIT".equalsIgnoreCase(text)){
                  break;
               } else if (text.endsWith("?")) {
                  System.out.println(kb.findSolutions(statement(text.substring(0, text.length() - 1))));
               } else{
                  System.out.println("weird formatted text: " + text + ", length: " + text.length());
               }
            }
            
         } catch (Exception e) {
            System.out.println("ERROR OF SOME SORT!");
            e.printStackTrace();
         }
      }
   }
   
}
