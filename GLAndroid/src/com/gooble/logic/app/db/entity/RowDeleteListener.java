package com.gooble.logic.app.db.entity;

import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;

public class RowDeleteListener implements OnClickListener {

   private final EntityAdapter<?> adapter;

   public RowDeleteListener(EntityAdapter<?> adapter) {
      this.adapter = adapter;
   }
   
   public void onClick(View v) {
      View row = (View) v.getParent();
      ViewGroup container = (ViewGroup) row.getParent();
      container.removeView(row);
      adapter.delete((long) row.getId());
   }

}