package com.gooble.logic.app.db.entity;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.Button;
import android.widget.TextView;

import com.gooble.logic.app.entity.Entity;
import com.gooble.logic.app.entity.EntityList;

public class EntityListAdapter {

   private final Activity activity;
   private final EntityList<?> list;
   private final int layoutId;
   private final int rowLayoutId;
   private final OnClickListener[] buttonListeners;
   private final int[] buttonIds;

   public EntityListAdapter(Activity activity, EntityList<?> list, int layoutId, int rowLayoutId, int[] buttonIds, OnClickListener[] buttonListeners) {
      this.activity = activity;
      this.list = list;
      this.layoutId = layoutId;
      this.rowLayoutId = rowLayoutId;
      this.buttonIds = buttonIds;
      this.buttonListeners = buttonListeners;
   }
   
   public void registerContainer(String[] entityColumns, int[] rowFields){
      if (entityColumns.length != rowFields.length)
         throw new RuntimeException("Cannot assign " + entityColumns.length + " columns to " + rowFields.length + " fields on the row");
      if (buttonIds.length != buttonListeners.length)
         throw new RuntimeException("Cannot assign " + buttonListeners.length + " actions to " + buttonIds.length + " buttons");
      
      ViewGroup container = (ViewGroup) activity.findViewById(layoutId);
      LayoutInflater inflater = activity.getLayoutInflater();
      
      View newRow = inflater.inflate(rowLayoutId, container, false);
      
      for (Entity e : list){
         newRow.setId((int) e.getId());
         for (int i = 0; i < entityColumns.length; i++){
            String column = entityColumns[i];
            int rowField = rowFields[i];
            TextView view = (TextView) newRow.findViewById(rowField);
            view.setText(String.valueOf(e.getField(column)));
         }
         addButtonHandlers(newRow);
         container.addView(newRow);
      }
   }

   public void addNewEntity() {
      ViewGroup container = (ViewGroup) activity.findViewById(layoutId);
      LayoutInflater inflater = activity.getLayoutInflater();
      
      View newRow = inflater.inflate(rowLayoutId, container, false);
      addButtonHandlers(newRow);
      container.addView(newRow);
   }

   private void addButtonHandlers(View newRow) {
      for (int i = 0; i < buttonIds.length; i++){
         int buttonId = buttonIds[i];
         OnClickListener action = buttonListeners[i];
         Button button = (Button) newRow.findViewById(buttonId);
         button.setOnClickListener(action);
      }
   }
   
}
