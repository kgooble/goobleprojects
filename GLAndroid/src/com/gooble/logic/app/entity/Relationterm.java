package com.gooble.logic.app.entity;

public class Relationterm extends Entity{

   private int relationid;
   private int termid;
   private int relationorder;
   public int getRelationid() {
      return relationid;
   }
   public void setRelationid(int relationid) {
      this.relationid = relationid;
   }
   public int getTermid() {
      return termid;
   }
   public void setTermid(int termid) {
      this.termid = termid;
   }
   public int getRelationorder() {
      return relationorder;
   }
   public void setRelationorder(int relationorder) {
      this.relationorder = relationorder;
   }
}
