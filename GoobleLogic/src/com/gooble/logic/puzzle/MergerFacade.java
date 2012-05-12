package com.gooble.logic.puzzle;

import java.util.List;

import com.gooble.logic.kb.Rule;
import com.gooble.logic.kb.solutions.SolutionSet;
import com.gooble.logic.kb.stmts.Statement;

public interface MergerFacade{

   void mergeWith(Rule rule, SolutionSet solutions, List<Statement> ignoreList);
   Rule getMergedRule();
   SolutionSet getMergedSolutions();
   
}