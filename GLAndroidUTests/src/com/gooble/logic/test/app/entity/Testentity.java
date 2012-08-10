package com.gooble.logic.test.app.entity;

import com.gooble.logic.app.entity.Entity;

public class Testentity extends Entity{
   private String name;
   private Integer number;
   private Long longnumber;
   private Double money;
   private Boolean cool;
   
   public String getName() {
      return name;
   }
   public void setName(String name) {
      this.name = name;
   }
   public Integer getNumber() {
      return number;
   }
   public void setNumber(Integer number) {
      this.number = number;
   }
   public Double getMoney() {
      return money;
   }
   public void setMoney(Double money) {
      this.money = money;
   }
   public Boolean getCool() {
      return cool;
   }
   public void setCool(Boolean cool) {
      this.cool = cool;
   }
   public Long getLongnumber() {
      return longnumber;
   }
   public void setLongnumber(Long longnumber) {
      this.longnumber = longnumber;
   }
}