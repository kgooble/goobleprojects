package com.gooble.logic.test.app.entity;

import com.gooble.logic.app.entity.Entity;

public class Testentity extends Entity{
   private String name;
   private Integer number;
   private Double money;
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
}