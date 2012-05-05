package com.gooble.logic.puzzle;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Hint{
   private final List<HintPart> parts;

   public Hint(HintPart... parts) {
      this.parts = new ArrayList<HintPart>();
      this.parts.addAll(Arrays.asList(parts));
   }

   public void add(HintPart part) {
      this.parts.add(part);
   }
   
   @Override
   public boolean equals(Object other){
      if (!(other instanceof Hint))
         return false;
      return this.parts.equals(((Hint)other).parts);
   }
   @Override
   public String toString(){
      return parts.toString(); 
   }
}