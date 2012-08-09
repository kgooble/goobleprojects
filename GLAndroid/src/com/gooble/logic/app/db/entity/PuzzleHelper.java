package com.gooble.logic.app.db.entity;

import android.content.Context;

import com.gooble.logic.app.entity.Puzzle;
import com.gooble.logic.app.entity.PuzzleFactory;

public class PuzzleHelper extends DatabaseHelper<Puzzle> {

   public PuzzleHelper(Context context) {
      super(context, new PuzzleFactory());
   }

   public Puzzle getOrCreate(Long puzzleId) {
      if (puzzleId == null)
         return create();
      Puzzle puzzle = get(puzzleId);
      if (puzzle == null){
         return create();
      }
      return puzzle;
   }

}
