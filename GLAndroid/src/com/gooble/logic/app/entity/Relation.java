package com.gooble.logic.app.entity;

public class Relation extends Entity {

   private String name;
   private Boolean bidirectional;
   private Boolean uniquevalue;
   private Long variableid;
   private Long puzzleid;

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public Boolean getBidirectional() {
      return bidirectional;
   }

   public void setBidirectional(Boolean bidirectional) {
      this.bidirectional = bidirectional;
   }

   public Boolean getUniquevalue() {
      return uniquevalue;
   }

   public void setUniquevalue(Boolean uniquevalue) {
      this.uniquevalue = uniquevalue;
   }

   public Long getVariableid() {
      return variableid;
   }

   public void setVariableid(Long variableid) {
      this.variableid = variableid;
   }

   public Long getPuzzleid() {
      return puzzleid;
   }

   public void setPuzzleid(Long puzzleid) {
      this.puzzleid = puzzleid;
   }
}
