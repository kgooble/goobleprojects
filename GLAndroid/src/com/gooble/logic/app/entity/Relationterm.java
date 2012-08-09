package com.gooble.logic.app.entity;

public class Relationterm extends Entity{

   private Integer relationid;
   private Integer termid;
   private Integer relationorder;
   public Integer getRelationid() {
      return relationid;
   }
   public void setRelationid(Integer relationid) {
      this.relationid = relationid;
   }
   public Integer getTermid() {
      return termid;
   }
   public void setTermid(Integer termid) {
      this.termid = termid;
   }
   public Integer getRelationorder() {
      return relationorder;
   }
   public void setRelationorder(Integer relationorder) {
      this.relationorder = relationorder;
   }
}
