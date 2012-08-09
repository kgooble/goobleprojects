package com.gooble.logic.app.entity;

public class Relation extends Entity{

   private String name;
   private Integer bidirectional;
   private Integer uniquevalue;
   private Integer variableid;
   public String getName() {
      return name;
   }
   public void setName(String name) {
      this.name = name;
   }
   public Integer getBidirectional() {
      return bidirectional;
   }
   public void setBidirectional(Integer bidirectional) {
      this.bidirectional = bidirectional;
   }
   public Integer getUniquevalue() {
      return uniquevalue;
   }
   public void setUniquevalue(Integer uniquevalue) {
      this.uniquevalue = uniquevalue;
   }
   public Integer getVariableid() {
      return variableid;
   }
   public void setVariableid(Integer variableid) {
      this.variableid = variableid;
   }
}
