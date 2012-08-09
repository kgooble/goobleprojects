package com.gooble.logic.app;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import com.gooble.logic.app.db.Tables;
import com.gooble.logic.app.db.entity.PuzzleHelper;

public class ViewPuzzlesActivity extends Activity {
   @Override
   public void onCreate(Bundle icicle) {
      super.onCreate(icicle);
      setContentView(R.layout.view_puzzles);
      
      // load puzzles into list
      SimpleCursorAdapter adapter = new SimpleCursorAdapter(this, android.R.layout.simple_list_item_1, getPuzzles(), new String[]{Tables.Puzzle.NAME}, new int[]{android.R.id.text1});
      
      ListView list = (ListView) findViewById(R.id.puzzle_list);
      list.setAdapter(adapter);
      // after clicking one, set it in the intent and launch the puzzle activity.
   }

   private Cursor getPuzzles() {
      return new PuzzleHelper(this).getAll();
   }
}