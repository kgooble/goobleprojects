package com.gooble.logic.app.variables;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.gooble.logic.api.VariableDomain;
import com.gooble.logic.api.VariableFacade;
import com.gooble.logic.app.R;
import com.gooble.logic.app.db.entity.VariableAdapter;
import com.gooble.logic.app.entity.EntityList;
import com.gooble.logic.app.entity.Variable;

public class VariablesActivity extends Activity {
   
   private VariableFacade variableFacade;

   public VariablesActivity() {
      this.variableFacade = new VariableDomain();
   }
   
   @Override
   public void onCreate(Bundle icicle) {
      super.onCreate(icicle);
      setContentView(R.layout.variables);
      final Long puzzleId = (Long) getIntent().getExtras().get("puzzleid");
      final VariableAdapter helper = new VariableAdapter(this);
      
      EntityList<Variable> variables = helper.getVariablesForPuzzle(puzzleId);
      for (Variable var : variables){
         addVariable(var.getName()).setId((int) var.getId());
      }
      
      /*
       * TODO: Load all existing variable names with Edit and Delete buttons next to them
       * TODO: need radio set which states which one is the main variable--- 
       *       or maybe this should go on the variable screen.
       */
      
      Button addVariableButton = (Button) findViewById(R.id.add_variable_button);
      Button saveVariablesButton = (Button) findViewById(R.id.save_variables_button);
      saveVariablesButton.setOnClickListener(new OnClickListener() {
         public void onClick(View v) {
            variableFacade.save();
            LinearLayout variableContainer = (LinearLayout) findViewById(R.id.variable_container);
            int numVars = variableContainer.getChildCount();
            for (int i = 0; i < numVars; i++){
               View vari = variableContainer.getChildAt(i);
               EditText varName = (EditText) vari.findViewById(R.id.variable_name);
               Variable var = helper.getById((long) vari.getId());
               if (var == null){
                  var = helper.create();
                  var.setPuzzleid(puzzleId);
                  var.setName(varName.getText().toString());
               }else{
                  var.setName(varName.getText().toString());
               }
               helper.store(var);
            }
         }
      });
      
      addVariableButton.setOnClickListener(new OnClickListener() {
         public void onClick(View v) {
            addVariable("");
         }
      });
   }
   
   private View addVariable(String name){
      final Activity activity = this;
      final LinearLayout variableContainer = (LinearLayout) findViewById(R.id.variable_container);
      
      LayoutInflater inflater = getLayoutInflater();
      final View newVariable = inflater.inflate(R.layout.variable_row, variableContainer, false);
      
      EditText variableName = (EditText) newVariable.findViewById(R.id.variable_name);
      variableName.setText(name);
      
      Button addVariableValuesButton = (Button) newVariable.findViewById(R.id.edit_variable_button);
      addVariableValuesButton.setOnClickListener(new OnClickListener() {
         public void onClick(View v) {
            variableFacade.save();
            Intent intent = new Intent(activity, VariableValuesActivity.class);
            String name = ((EditText)newVariable.findViewById(R.id.variable_name)).getText().toString();
            intent.putExtra("name", name);
            activity.startActivity(intent);
         }
      });
      
      Button deleteButton = (Button) newVariable.findViewById(R.id.delete_variable_button);
      deleteButton.setOnClickListener(new OnClickListener() {
         public void onClick(View v) {
            variableContainer.removeView(newVariable);
         }
      });
      variableContainer.addView(newVariable);
      return newVariable;
   }
}

