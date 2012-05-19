package com.gooble.logic.puzzle;

import java.util.Collection;

import com.gooble.logic.kb.Rule;
import com.gooble.logic.kb.solutions.SolutionSet;
import com.gooble.logic.kb.stmts.Statement;

public interface MergerFacade{

   void mergeWith(Rule rule, SolutionSet solutions, Collection<Statement> ignoreList);
   Rule getMergedRule();
   SolutionSet getMergedSolutions();
   
}