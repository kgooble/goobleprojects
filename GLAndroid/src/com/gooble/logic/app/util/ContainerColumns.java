package com.gooble.logic.app.util;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class ContainerColumns {

   private final int containerId;
   private final Activity activity;

   public ContainerColumns(Activity activity, int containerId) {
      this.activity = activity;
      this.containerId = containerId;
   }
   
   public List<Long> getIds(){
      ViewGroup container = (ViewGroup) activity.findViewById(containerId);
      List<Long> ids = new ArrayList<Long>();
      int numVars = container.getChildCount();
      for (int i = 0; i < numVars; i++){
         View vari = container.getChildAt(i);
         ids.add((long) vari.getId());
      }
      return ids;
   }
   
   public List<String> getStringsFromField(int fieldId){
      ViewGroup container = (ViewGroup) activity.findViewById(containerId);
      List<String> values = new ArrayList<String>();
      int numRows = container.getChildCount();
      for (int i = 0; i < numRows; i++){
         View row = container.getChildAt(i);
         TextView field = (TextView) row.findViewById(fieldId);
         values.add(field.getText().toString());
      }
      return values;
   }

   public void updateIds(List<Long> newIds) {
      ViewGroup container = (ViewGroup) activity.findViewById(containerId);
      int numVars = container.getChildCount();
      for (int i = 0; i < numVars; i++){
         View vari = container.getChildAt(i);
         vari.setId(newIds.get(i).intValue());
      }
   }
   
}
