package com.gooble.logic.app.entity;

public class Condition extends Entity{

   private int relationid;
   private String operator;
   public int getRelationid() {
      return relationid;
   }
   public void setRelationid(int relationid) {
      this.relationid = relationid;
   }
   public String getOperator() {
      return operator;
   }
   public void setOperator(String operator) {
      this.operator = operator;
   }
   
}
