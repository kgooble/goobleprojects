package com.gooble.logic.app.entity;

public class Relationterm extends Entity {

   private Long relationid;
   private Long termid;
   private Integer relationorder;

   public Long getRelationid() {
      return relationid;
   }

   public void setRelationid(Long relationid) {
      this.relationid = relationid;
   }

   public Long getTermid() {
      return termid;
   }

   public void setTermid(Long termid) {
      this.termid = termid;
   }

   public Integer getRelationorder() {
      return relationorder;
   }

   public void setRelationorder(Integer relationorder) {
      this.relationorder = relationorder;
   }
}
