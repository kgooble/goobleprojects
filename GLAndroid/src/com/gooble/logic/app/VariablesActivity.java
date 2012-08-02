package com.gooble.logic.app;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.gooble.logic.app.api.VariableDomain;
import com.gooble.logic.app.api.VariableFacade;

public class VariablesActivity extends Activity {
   
   private VariableFacade variableFacade;

   public VariablesActivity() {
      this.variableFacade = new VariableDomain();
   }
   
   public class Variable{

      private final String name;

      public Variable(String name) {
         this.name = name;
      }

      public String getName() {
         return name;
      }
      
   }
   public class VariableHolder{

      public EditText name;
      
   }
   
   public class VariableAdapter extends ArrayAdapter<Variable>{

      private final int layoutResourceId;

      public VariableAdapter(Context context, int layoutResourceId, List<Variable> variables) {
         super(context, layoutResourceId, variables);
         this.layoutResourceId = layoutResourceId;
      }
      
      @Override
      public View getView(final int position, View convertView, ViewGroup parent){
         View row = convertView;
         
         VariableHolder holder = null;
         if (row == null){
            LayoutInflater inflater = ((Activity)getContext()).getLayoutInflater();
            row = inflater.inflate(layoutResourceId, parent, false);
            
            holder = new VariableHolder();
            holder.name = (EditText) row.findViewById(R.id.variable_name);
            Button addButton = (Button) row.findViewById(R.id.add_values_button);
            addButton.setOnClickListener(new OnClickListener() {
               public void onClick(View v) {
                  variableFacade.save();
                  startActivity(new Intent(getContext(), VariableValuesActivity.class));
               }
            });
            Button deleteButton = (Button) row.findViewById(R.id.delete_variable_button);
            deleteButton.setOnClickListener(new OnClickListener() {
               public void onClick(View v) {
                  remove(getItem(position));
               }
            });
            row.setTag(holder);
            
         }else{
            holder = (VariableHolder) row.getTag();
         }
         
         Variable variable = getItem(position);
         holder.name.setText(variable.getName());
         
         return row;
      }
      
   }
   
   @Override
   public void onCreate(Bundle icicle) {
      super.onCreate(icicle);
      setContentView(R.layout.variables);
      
      /*
       * TODO: Load all existing variable names with Edit and Delete buttons next to them
       * TODO: need radio set which states which one is the main variable--- 
       *       or maybe this should go on the variable screen.
       */
      
      final ListView variableList = (ListView)findViewById(R.id.variable_list);
      final List<Variable> variables = new ArrayList<Variable>();
      variables.add(new Variable("X"));
      variables.add(new Variable("Y"));
      final VariableAdapter adapter = new VariableAdapter(this, R.layout.variable_row, variables);
      variableList.setAdapter(adapter);
      
      Button addVariableButton = (Button) findViewById(R.id.add_variable_button);
      addVariableButton.setOnClickListener(new OnClickListener() {
         public void onClick(View v) {
            variables.add(new Variable(""));
            adapter.notifyDataSetChanged();
         }
      });
      
      Button saveVariablesButton = (Button) findViewById(R.id.save_variables_button);
      saveVariablesButton.setOnClickListener(new OnClickListener() {
         public void onClick(View v) {
            variableFacade.save(); //TODO: need to pass in data
            // TODO: maybe go back to previous activity here? Finish?
         }
      });
      
   }
}
