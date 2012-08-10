package com.gooble.logic.app;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import com.gooble.logic.app.db.Tables;
import com.gooble.logic.app.db.entity.PuzzleAdapter;

public class ViewPuzzlesActivity extends Activity {

   @Override
   public void onCreate(Bundle bundle) {
      super.onCreate(bundle);
      setContentView(R.layout.view_puzzles);
      
      SimpleCursorAdapter adapter = new SimpleCursorAdapter(this, android.R.layout.simple_list_item_1, getPuzzles(), new String[]{Tables.Puzzle.NAME}, new int[]{android.R.id.text1});
      
      ListView list = (ListView) findViewById(R.id.puzzle_list);
      
      list.setAdapter(adapter);
      final Activity activity = this;
      
      list.setOnItemClickListener(new OnItemClickListener() {
         public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Intent intent = new Intent(activity, PuzzleActivity.class);
            intent.setAction(Intent.ACTION_EDIT);
            intent.putExtra("puzzleid", id);
            startActivity(intent);
         }
      });
   }
   
   private Cursor getPuzzles() {
      return new PuzzleAdapter(this).getAll();
   }
}