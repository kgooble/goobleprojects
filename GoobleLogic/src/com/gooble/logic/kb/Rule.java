package com.gooble.logic.kb;

import java.util.Arrays;

import com.gooble.logic.kb.stmts.Statement;

public class Rule {

   private final Statement consequence;
   private final Statement[] antecedents;

   public Rule(Statement consequence, Statement... antecedents) {
      this.consequence = consequence;
      this.antecedents = antecedents;
   }

   public boolean consequenceMatches(Statement statement) {
      return consequence.match(statement);
   }

   public Statement getConsequence() {
      return consequence;
   }

   public Statement[] getAntecedents() {
      return antecedents;
   }
   
   @Override
   public boolean equals(Object obj){
      if (!(obj instanceof Rule))
         return false;
      Rule other = (Rule) obj;
      return this.consequence.equals(other.consequence) && Arrays.equals(this.antecedents, other.antecedents); 
   }
   
   @Override
   public String toString(){
      return Arrays.asList(antecedents).toString() + " => " + consequence.toString();
   }

}
