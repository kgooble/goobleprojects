package main.kb;

import java.util.Arrays;

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

   public boolean antecedentMatches(Statement statement) {
      return antecedents[0].match(statement);
   }
   

   @Override
   public String toString(){
      return Arrays.asList(antecedents).toString() + " => " + consequence.toString();
   }

   public Statement getAntecedent() {
      return antecedents[0];
   }
   
   public Statement getConsequence() {
      return consequence;
   }

   public Statement[] getAntecedents() {
      return antecedents;
   }

}
