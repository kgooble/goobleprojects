package com.gooble.logic.app.db.entity;

import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;

public abstract class RowDeleteListener implements OnClickListener {

   public void onClick(View v) {
      View row = (View) v.getParent();
      ViewGroup container = (ViewGroup) row.getParent();
      container.removeView(row);
      domainDelete(container, row);
   }

   abstract public void domainDelete(ViewGroup container, View row);

}