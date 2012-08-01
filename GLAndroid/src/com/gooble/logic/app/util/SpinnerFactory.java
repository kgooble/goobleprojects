package com.gooble.logic.app.util;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TableRow;

public class SpinnerFactory {
   
   private final Context context;

   public SpinnerFactory(Context context) {
      this.context = context;
   }
   
   public Spinner createRowSpinnerWithValues(String... values){
      return createSpinnerWithLayoutAndValues(new TableRow.LayoutParams(
            TableRow.LayoutParams.WRAP_CONTENT,
            TableRow.LayoutParams.WRAP_CONTENT
            ), values);
   }
   
   public Spinner createSpinnerWithValues(String... values){
      return createSpinnerWithLayoutAndValues(new RelativeLayout.LayoutParams(
            RelativeLayout.LayoutParams.WRAP_CONTENT,
            RelativeLayout.LayoutParams.WRAP_CONTENT
            ), values);
   }
   
   private Spinner createSpinnerWithLayoutAndValues(ViewGroup.LayoutParams params, String... values){
      Spinner operatorSpinner = new Spinner(context);
      SpinnerAdapter operatorAdapter = new ArrayAdapter<String>(context, android.R.layout.simple_list_item_1, values); 
      operatorSpinner.setAdapter(operatorAdapter);
      operatorSpinner.setLayoutParams(params);
      return operatorSpinner;
   }
}
