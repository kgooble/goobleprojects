package com.gooble.logic.app.entity;

import com.gooble.logic.app.db.entity.VariableAdapter;

import android.content.Context;

public class Puzzle extends Entity{

   private String name;
   private Long mainvariableid;
   
   public String getName() {
      return name;
   }
   public void setName(String name) {
      this.name = name;
   }
   public Long getMainvariableid() {
      return mainvariableid;
   }
   public void setMainvariableid(Long mainvariableid) {
      this.mainvariableid = mainvariableid;
   }
   public EntityList<Variable> getVariables(Context context) {
      return new VariableAdapter(context).getVariablesForPuzzle(getId());
   }
   
}
