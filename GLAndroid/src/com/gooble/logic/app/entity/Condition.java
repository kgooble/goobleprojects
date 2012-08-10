package com.gooble.logic.app.entity;

public class Condition extends Entity{

   private Long relationid;
   private String operator;
   public Long getRelationid() {
      return relationid;
   }
   public void setRelationid(Long relationid) {
      this.relationid = relationid;
   }
   public String getOperator() {
      return operator;
   }
   public void setOperator(String operator) {
      this.operator = operator;
   }
   
}
