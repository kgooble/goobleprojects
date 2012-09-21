package com.gooble.logic.app.entity;

import java.util.Arrays;
import java.util.List;

import android.content.Context;

import com.gooble.logic.app.db.entity.PuzzleAdapter;
import com.gooble.logic.app.entity.domain.VariableType;

public class Variable extends Entity {

   private String name;
   private String type;
   private Long puzzleid;

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public Long getPuzzleid() {
      return puzzleid;
   }

   public void setPuzzleid(Long puzzleid) {
      this.puzzleid = puzzleid;
   }

   public String getType() {
      return type;
   }

   public void setType(String type) {
      this.type = type;
   }

   public boolean isMainVariable(Context context) {
      PuzzleAdapter helper = new PuzzleAdapter(context);
      Puzzle myPuzzle = helper.getById(getPuzzleid());
      return getId() == myPuzzle.getMainvariableid();
   }

   public List<String> getOperators() {
      if (VariableType.NUMBER.equals(getType())){
         return Arrays.asList("<", ">", "=");
      }
      return Arrays.asList("=");
   }

}
