package com.gooble.logic.puzzle;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import com.gooble.logic.kb.Rule;
import com.gooble.logic.kb.stmts.Statement;
import com.gooble.logic.kb.terms.Variable;

public class Hint{
   private final List<HintPart> parts;

   public Hint(HintPart... parts) {
      this.parts = new ArrayList<HintPart>();
      this.parts.addAll(Arrays.asList(parts));
   }

   public void add(HintPart part) {
      this.parts.add(part);
   }

   public Rule toRule() {
      List<Statement> antecedentsList = new ArrayList<Statement>();
      for (HintPart part : parts){
         antecedentsList.add(part.toStatement());
      }
      Set<Variable> variables = new StatementVariables(antecedentsList).find();
      Statement consequence = new Statement("hint", variables.toArray(new Variable[variables.size()]));
      Statement[] antecedents = antecedentsList.toArray(new Statement[antecedentsList.size()]);
      return new Rule(consequence, antecedents);
   }
   
   @Override
   public boolean equals(Object other){
      if (!(other instanceof Hint))
         return false;
      return this.parts.equals(((Hint)other).parts);
   }
   @Override
   public int hashCode(){
      return parts.hashCode();
   }
   @Override
   public String toString(){
      return parts.toString(); 
   }
}