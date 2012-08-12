package com.gooble.logic.app.variables;

import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

import com.gooble.logic.api.VariablevalueDomain;
import com.gooble.logic.api.VariablevalueFacade;
import com.gooble.logic.app.R;
import com.gooble.logic.app.db.Tables;
import com.gooble.logic.app.db.entity.EntityListAdapter;
import com.gooble.logic.app.db.entity.RowDeleteListener;
import com.gooble.logic.app.db.entity.VariableAdapter;
import com.gooble.logic.app.db.entity.VariablevalueAdapter;
import com.gooble.logic.app.entity.EntityList;
import com.gooble.logic.app.entity.Variable;
import com.gooble.logic.app.entity.Variablevalue;
import com.gooble.logic.app.entity.domain.VariableType;

public class VariableValuesActivity extends Activity {

   private VariablevalueFacade variablevalueFacade;

   public VariableValuesActivity() {
      this.variablevalueFacade = new VariablevalueDomain();
   }
   
   @Override
   public void onCreate(Bundle bundle){
      super.onCreate(bundle);
      setContentView(R.layout.variable_values);
      
      final Long variableId = (Long) getIntent().getExtras().get("variableid");
      
      VariableAdapter vars = new VariableAdapter(this);
      Variable myVar = vars.getById(variableId);
      
      CheckBox isMainVar = (CheckBox) findViewById(R.id.mainvariable_cbx);
      isMainVar.setChecked(myVar.isMainVariable(this));
      
      TextView nameLabel = (TextView) findViewById(R.id.variable_name_label);
      nameLabel.setText(myVar.getName());
      
      Spinner variableType = (Spinner) findViewById(R.id.variable_type);
      SpinnerAdapter typeAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, 
            VariableType.ALL_TYPES);
      variableType.setAdapter(typeAdapter);
      variableType.setSelection(VariableType.getPosition(myVar.getType()));

      EntityListAdapter adapter = loadVariableValues(variableId);
      setAddEntityHandler(adapter);
      setSaveHandler(variableId, variableType, adapter);
   }

   private EntityListAdapter loadVariableValues(final Long variableId) {
      final VariablevalueAdapter valueAdapter = new VariablevalueAdapter(this);
      EntityList<Variablevalue> values = valueAdapter.getForVariable(variableId);
      final EntityListAdapter adapter = new EntityListAdapter(this, values, R.id.value_container, R.layout.value_row, 
            new int[]{R.id.delete_value_button}, 
            new OnClickListener[]{new RowDeleteListener(valueAdapter)});
      adapter.registerContainer(
            new String[]{Tables.Variablevalue.VALUE}, 
            new int[]{R.id.variable_value});
      return adapter;
   }

   private void setAddEntityHandler(final EntityListAdapter adapter) {
      Button addValueButton = (Button) findViewById(R.id.add_value_button);
      addValueButton.setOnClickListener(new OnClickListener() {
         public void onClick(View v) {
            adapter.addNewEntity();
         }
      });
   }

   private void setSaveHandler(final Long variableId, final Spinner variableType, final EntityListAdapter adapter) {
      final Activity activity = this;
      Button saveValuesButton = (Button) findViewById(R.id.save_values_button);
      saveValuesButton.setOnClickListener(new OnClickListener() {
         public void onClick(View v) {
            List<Long> ids = adapter.getIds();
            List<String> values = adapter.getStringsFromField(R.id.variable_value);
            boolean isMainVar = ((CheckBox) activity.findViewById(R.id.mainvariable_cbx)).isChecked();
            String variableTypeValue = VariableType.getValue(variableType.getSelectedItemPosition());
            variablevalueFacade.save(activity, variableId, variableTypeValue, isMainVar, ids, values);
            activity.finish();
         }
      });
   }
}
