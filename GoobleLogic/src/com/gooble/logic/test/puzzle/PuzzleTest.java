package com.gooble.logic.test.puzzle;

import static com.gooble.logic.kb.encoding.KBEncoding.statement;
import static com.gooble.logic.kb.encoding.TermEncoding.constant;
import static com.gooble.logic.kb.encoding.TermEncoding.variable;
import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;


import org.junit.After;
import org.junit.Test;

import com.gooble.logic.Logger;
import com.gooble.logic.puzzle.HintDefinition;
import com.gooble.logic.puzzle.Puzzle;
import com.gooble.logic.puzzle.RelationDefinition;
import com.gooble.logic.puzzle.Result;
import com.gooble.logic.puzzle.SolutionDefinition;
import com.gooble.logic.puzzle.VariableDefinition;

public class PuzzleTest {
   
   @Test
   public void contradictory_puzzle_has_no_solutions() throws Exception {
      VariableDefinition varDefStub = new VariableDefinition();
      varDefStub.setMain("person");
      varDefStub.add("person", "alice", "bob", "carl");
      varDefStub.add("shoes", "red", "blue", "green");
      varDefStub.add("age", 17, 18, 19);
      RelationDefinition relDefStub = new RelationDefinition("age");
      relDefStub.addNonUnique("olderThan", variable("X"), variable("Y"), statement("Y > X"));
      HintDefinition hintDefStub = new HintDefinition(varDefStub);
      hintDefStub.about("person", "alice").property("age", "X").about("age", "X").relation("olderThan", "Y").about("age", "Y").property("person", "bob").end();
      hintDefStub.about("person", "bob").property("age", "X").about("age", "X").relation("olderThan", "Y").about("age", "Y").property("person", "alice").end();
      SolutionDefinition solnDefStub = new SolutionDefinition("person", "shoes", "age");
      
      Puzzle puzzle = new Puzzle(varDefStub, hintDefStub, solnDefStub, relDefStub);
      Result result = puzzle.solve();
      assertFalse(result.isPuzzleSolvable());
   }
   
   @Test
   public void augment_then_hints_then_solutions_then_error_check() throws Exception {
      VariableDefinition varDefStub = createVariableDefinition();
      RelationDefinition relDefStub = createRelationDefinition();
      HintDefinition hintDefStub = createHintDefinition(varDefStub);
      SolutionDefinition solnDefStub = createSolutionDefinition();
      
      Result result = new Puzzle(varDefStub, hintDefStub, solnDefStub, relDefStub).solve();
      assertTrue(result.isPuzzleSolvable());
      // We need to check whether the solution to the puzzle is the same as that
      // encoded by the solution definition.
   }
   @After
   public void closeLogger(){
      Logger.close();
   }
   private VariableDefinition createVariableDefinition() {
      VariableDefinition varDef = new VariableDefinition();
      varDef.add("woman", "adele", "jane", "laura", "molly", "sarah");
      varDef.add("house", 1, 2, 3, 4, 5);
      varDef.add("hair", "black", "blonde", "brown", "chestnut", "grey");
      varDef.setMain("woman");
      return varDef;
   }
   private RelationDefinition createRelationDefinition() {
      RelationDefinition relDef = new RelationDefinition("house");
      relDef.addBidirectionalNonUnique("nextTo", constant(1), constant(3));
      relDef.addBidirectionalNonUnique("nextTo", constant(2), constant(4));
      relDef.addBidirectionalNonUnique("nextTo", constant(4), constant(5));
      relDef.add("directlyEastOf", constant(1), constant(2));
      relDef.add("directlyEastOf", constant(3), constant(4));
      relDef.add("northOf", variable("X"), variable("Y"), statement("X > Y"));
      relDef.add("southOf", variable("X"), variable("Y"), statement("X < Y"));
      return relDef;
   }
   private HintDefinition createHintDefinition(VariableDefinition varDefStub) {
      HintDefinition hintDef = new HintDefinition(varDefStub);
      hintDef.about("woman", "W1").property("hair", "blonde").property("house", "H1")
               .about("house", "H1").relation("nextTo", "H2").relation("southOf", "H2")
                  .about("house", "H2").property("woman", "adele").relation("directlyEastOf", "H3")
                     .about("house", "H3").property("woman", "laura").end();
      
      hintDef.about("house", "H1").property("woman", "sarah").relation("nextTo", "H2").relation("southOf", "H2")
               .about("house", "H2").property("woman", "molly").end();
      
      hintDef.about("house", "H1").property("woman", "W1").relation("directlyEastOf", "H2")
               .about("house", "H2").property("woman", "jane").relation("northOf", "H3")
                  .about("house", "H3").property("woman", "W2")
                     .about("woman", "W2").property("hair", "grey").end();
      hintDef.about("woman", "W1").property("hair", "chestnut").property("house", "H1")
               .about("house", "H1").relation("nextTo", "H2").relation("northOf", "H2")
                  .about("house", "H2").property("woman", "W2")
                     .about("woman", "W2").property("hair", "black").end();
      return hintDef;
   }
   private SolutionDefinition createSolutionDefinition() {
      SolutionDefinition solnDef = new SolutionDefinition("woman", "house", "hair");
      solnDef.add("adele", 2, "brown");
      solnDef.add("jane", 3, "black");
      solnDef.add("laura", 1, "chestnut");
      solnDef.add("molly", 4, "blonde");
      solnDef.add("sarah", 5, "grey");
      return solnDef;
   }
}
