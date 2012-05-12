package com.gooble.logic.test.puzzle.stubs;

import static com.gooble.logic.kb.encoding.KBEncoding.replacement;
import static com.gooble.logic.kb.encoding.KBEncoding.solution;
import static com.gooble.logic.kb.encoding.KBEncoding.solutions;

import java.util.ArrayList;
import java.util.List;

import com.gooble.logic.kb.KnowledgeBaseFacade;
import com.gooble.logic.kb.Rule;
import com.gooble.logic.kb.solutions.SolutionSet;
import com.gooble.logic.kb.stmts.Statement;

public class KBStub implements KnowledgeBaseFacade {

   public final List<Rule> rules = new ArrayList<Rule>();
   public final List<Statement> stmts = new ArrayList<Statement>();

   @Override
   public void add(Rule rule) {
      rules.add(rule);
   }

   @Override
   public void add(Statement stmt) {
      stmts.add(stmt);
   }

   @Override
   public SolutionSet findSolutions(Statement statement) {
      return solutions(true, solution(replacement(statement.toString(), 1)));
   }

}
