package com.gooble.logic.app.util;

import com.gooble.logic.app.R;

import android.content.Context;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TableRow.LayoutParams;
import android.widget.TextView;

public class TableLayoutRow {

   private static final LayoutParams WIDE_VIEW_LAYOUT = new TableRow.LayoutParams(
         TableRow.LayoutParams.FILL_PARENT, 
         TableRow.LayoutParams.WRAP_CONTENT);
   
   private final TableLayout layout;
   private final Context context;

   public TableLayoutRow(TableLayout layout, Context context) {
      this.layout = layout;
      this.context = context;
   }
   
   public void addRowLabeledWithTextField(String label){
      TableRow.LayoutParams lparams = WIDE_VIEW_LAYOUT;
      
      TableRow nameValueRow = new TableRow(context);
      nameValueRow.setLayoutParams(lparams);
      
      TextView name = new TextView(context);
      name.setText(label);
      name.setLayoutParams(lparams);
      
      EditText edit = new EditText(context);
      edit.setLayoutParams(lparams);
      
      nameValueRow.addView(name, WIDE_VIEW_LAYOUT);
      nameValueRow.addView(edit, new TableRow.LayoutParams(
            TableRow.LayoutParams.FILL_PARENT, 
            TableRow.LayoutParams.WRAP_CONTENT, 1.0f));
      
      layout.addView(nameValueRow, WIDE_VIEW_LAYOUT);
   }
   
}
