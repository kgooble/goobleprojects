package com.gooble.logic.app.db.entity;

import android.content.Context;
import android.database.Cursor;

import com.gooble.logic.app.entity.Puzzle;
import com.gooble.logic.app.entity.PuzzleFactory;

public class PuzzleAdapter extends EntityAdapter<Puzzle> {

   public PuzzleAdapter(Context context) {
      super(context, new PuzzleFactory());
   }

   public Puzzle getOrCreate(Long puzzleId) {
      if (puzzleId == null)
         return factory.createNew();
      Cursor cursor = helper.getById(puzzleId, factory.getTableName());
      if (cursor.getCount() == 0)
         return factory.createNew();
      cursor.moveToFirst();
      return loadEntity(puzzleId, cursor);
   }

}
