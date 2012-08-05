package com.gooble.logic.app.entity;

public class Relation extends Entity{

   private String name;
   private int bidirectional;
   private int uniquevalue;
   private int variableid;
   public String getName() {
      return name;
   }
   public void setName(String name) {
      this.name = name;
   }
   public int getBidirectional() {
      return bidirectional;
   }
   public void setBidirectional(int bidirectional) {
      this.bidirectional = bidirectional;
   }
   public int getUniquevalue() {
      return uniquevalue;
   }
   public void setUniquevalue(int uniquevalue) {
      this.uniquevalue = uniquevalue;
   }
   public int getVariableid() {
      return variableid;
   }
   public void setVariableid(int variableid) {
      this.variableid = variableid;
   }
}
