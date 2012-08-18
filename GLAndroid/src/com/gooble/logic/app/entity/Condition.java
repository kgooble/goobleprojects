package com.gooble.logic.app.entity;

public class Condition extends Entity{

   private Long conditionsetid;
   private String operator;
   public Long getConditionsetid() {
      return conditionsetid;
   }
   public void setConditionsetid(Long conditionosetid) {
      this.conditionsetid = conditionosetid;
   }
   public String getOperator() {
      return operator;
   }
   public void setOperator(String operator) {
      this.operator = operator;
   }
   
}
