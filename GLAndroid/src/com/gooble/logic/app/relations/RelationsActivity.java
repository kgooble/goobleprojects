package com.gooble.logic.app.relations;

import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.gooble.logic.api.RelationDomain;
import com.gooble.logic.api.RelationFacade;
import com.gooble.logic.app.R;
import com.gooble.logic.app.db.Tables;
import com.gooble.logic.app.db.entity.EntityListAdapter;
import com.gooble.logic.app.db.entity.RelationAdapter;
import com.gooble.logic.app.db.entity.RowDeleteListener;
import com.gooble.logic.app.entity.EntityList;
import com.gooble.logic.app.entity.Relation;
import com.gooble.logic.app.util.ContainerColumns;

public class RelationsActivity extends Activity {

   private RelationFacade relationFacade;

   public RelationsActivity() {
      relationFacade = new RelationDomain();
   }
   
   @Override
   public void onCreate(Bundle bundle){
      super.onCreate(bundle);
      setContentView(R.layout.relations);
      
      final Long puzzleId = (Long) getIntent().getExtras().get("puzzleid");
      final EntityListAdapter adapter = loadRelations(puzzleId);
      
      // TODO: Maybe pass the add button id in to the entity list adapter because it always does the same thing.
      Button addRelationButton = (Button) findViewById(R.id.add_relation_button);
      addRelationButton.setOnClickListener(new OnClickListener() {
         public void onClick(View v) {
            adapter.addNewEntity();
         }
      });
      
      Button saveRelationsButton = (Button) findViewById(R.id.save_relations_button);
      saveRelationsButton.setOnClickListener(new OnClickListener() {
         public void onClick(View v) {
            save(puzzleId);
         }
      });
   }

   private EntityListAdapter loadRelations(final Long puzzleId) {
      final Activity activity = this;
      RelationAdapter relHelper = new RelationAdapter(activity);
      EntityList<Relation> relations = relHelper.getRelationsForPuzzle(puzzleId);
      
      EntityListAdapter adapter = new EntityListAdapter(this, relations, R.id.relation_container, R.layout.relation_row, 
            new int[]{R.id.edit_relation_button, R.id.delete_relation_button}, 
            new OnClickListener[]{
            //TODO: extract pattern. save, get id, then launch new activity with id set as extra
               new OnClickListener() {
                  public void onClick(View v) {
                     save(puzzleId);
                     
                     View relationRow = (View) v.getParent();
                     Intent intent = new Intent(activity, EditRelationActivity.class);
                     intent.putExtra("relationid", (long) relationRow.getId());
                     activity.startActivity(intent);
                  }
               },
               new RowDeleteListener(relHelper)
      });
      adapter.registerContainer(new String[]{Tables.Relation.NAME}, new int[]{R.id.relation_name});
      return adapter;
   }
   
   //TODO: extract pattern. get Ids, get some field values, save & get new ids, update ids...
   private void save(Long puzzleId){
      List<Long> ids = new ContainerColumns(this, R.id.relation_container).getIds();
      List<String> names = new ContainerColumns(this, R.id.relation_container).getStringsFromField(R.id.relation_name);
      List<Long> newIds = relationFacade.save(this, puzzleId, ids, names);
      new ContainerColumns(this, R.id.relation_container).updateIds(newIds);
   }
   
}
