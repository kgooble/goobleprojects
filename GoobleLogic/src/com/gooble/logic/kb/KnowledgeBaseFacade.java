package com.gooble.logic.kb;

import com.gooble.logic.kb.stmts.Statement;

public interface KnowledgeBaseFacade {

   void add(Rule rule);
   void add(Statement statement);
   
}
