package com.gooble.logic.app.util;

import com.gooble.logic.app.R;

import android.content.Context;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TableRow.LayoutParams;
import android.widget.TextView;

public class TableLayoutRow {

   private static final LayoutParams NARROW_VIEW_LAYOUT = new TableRow.LayoutParams(
         TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT);

   private final TableLayout layout;
   private final Context context;

   public TableLayoutRow(TableLayout layout, Context context) {
      this.layout = layout;
      this.context = context;
   }

   public void addRowLabeledWithTextField(String label) {
      TableRow nameValueRow = new TableRow(context);
      nameValueRow.setLayoutParams(new TableRow.LayoutParams(
            TableRow.LayoutParams.FILL_PARENT,
            TableRow.LayoutParams.WRAP_CONTENT));

      TextView name = new TextView(context);
      name.setText(label);
      name.setLayoutParams(NARROW_VIEW_LAYOUT);

      EditText value = new EditText(context);
      value.setLayoutParams(new TableRow.LayoutParams(
            TableRow.LayoutParams.FILL_PARENT,
            TableRow.LayoutParams.WRAP_CONTENT, 1.0f));

      Button delete = new Button(context);
      delete.setText(context.getString(R.string.delete));
      delete.setLayoutParams(NARROW_VIEW_LAYOUT);

      nameValueRow.addView(name);
      nameValueRow.addView(value);
      nameValueRow.addView(delete);

      layout.addView(nameValueRow);
   }

}
