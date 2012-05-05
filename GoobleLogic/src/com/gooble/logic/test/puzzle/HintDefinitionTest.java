package com.gooble.logic.test.puzzle;

import static junit.framework.Assert.assertEquals;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.gooble.logic.puzzle.Hint;
import com.gooble.logic.puzzle.HintDefinition;
import com.gooble.logic.puzzle.Property;
import com.gooble.logic.puzzle.Relation;
import com.gooble.logic.puzzle.VariableDefinition;

public class HintDefinitionTest {
   private KBStub kb;
   private HintDefinition hintDef;
   @Before
   public void init() throws Exception{
      kb = new KBStub();
   }
   @Test
   public void add_hint_definition_keeps_track_of_solutions_to_query() throws Exception {
      VariableDefinition varDefStub = new VariableDefinition();
      hintDef = new HintDefinition(varDefStub);
      hintDef.about("bob").relation("olderThan", "X")
             .about("X").type("person").property("shoes", "red").end();
      List<Hint> hints = hintDef.getHints();
      assertEquals(1, hints.size());
      assertEquals(new Hint(new Relation("olderThan", "X", "bob"), new Property("shoes", "X", "red")), hints.get(0));
      // create hint containing relations olderThan(X, bob) and shoesOf(X, red)
   }
   
   /*
    * hint definitions.
    * 
    * see case test for tons of examples of hints.
    * the question is, how do you add a hint using this interface?
    * 
    * what's the point of adding a hint through this?
    * -- it keeps track of the solutions you need to query afterwards.
    * -- it generates the consequent for you, instead of writing out a solutionN(X1, X2, ... XK) and remembering to query for it later
    * -- but, in the end, you still have to list out the little details, don't you.
    * -- we don't want the user to be exposed to how it works underneath, though, in the KB I Mean
    * 
    */
   
   /* bob is older than the person who wears red shoes
    * hintDef.add(olderThan(X, bob), wears(red, X)) --
    * instead of directly providing the variable "X" should there be an abstraction
    * layer which states that olderThan(_, bob) and wears(red, _) are "linked" through _?
    * i.e that _ in olderThan is == _ in wears ?
    * 
    * 
    * 
    * -- add a fact: bob is older than someone.
    * -- add facts about "someone" : this "someone" wears red shoes. 
    *                                this "someone" is older than kary.
    * an indented system?
    *  var("bob").is("olderThan").varWhich("wears", "red").varWhich("olderThan", "kary")
    *  --> only works for names?
    *  var("blonde").is??????
    */
   
   /*
    * bob is older than the person who wears red shoes.
    * 
    * -- need a variable definition & a relation definition (?) 
    * 
    * start()
    * .about(bob).relation(olderThan, X).
    *   about(X).type(person).property(shoes, red)
    * .end()
    * 
    * State Variable X, which we are talking about now:
    * what is true about X?
    *  Well, X has blonde hair.
    *  Well, X lives in a house Y.
    *  State Variable Y, which we are talking about now.
    *   Well, Y is next to Z.
    *   State Variable Z, which we are talking about now.
    *    Well, adele lives at house Z.
    *    Well, Z is directly east of W.
    *    State variable W.
    *     Well, laura lives at house W.
    *     
    * End hint. (Sort of changing scopes as we go.)]
    * 
    * start()
    * .about(X).type(woman).property(hair, blonde).property(house, Y)
    *   .about(Y).type(house).relation(nextTo, Z)
    *     .about(Z).type(house).property(woman, adele).relation(directlyEastOf, W)
    *       .about(W).type(house).property(woman, laura)
    * .end()
    *                         
    *                       
    */
}
