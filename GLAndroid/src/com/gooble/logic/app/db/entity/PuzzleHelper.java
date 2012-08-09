package com.gooble.logic.app.db.entity;

import android.content.Context;

import com.gooble.logic.app.entity.Puzzle;
import com.gooble.logic.app.entity.PuzzleFactory;

public class PuzzleHelper extends DatabaseHelper<Puzzle> {

   public PuzzleHelper(Context context) {
      super(context, new PuzzleFactory());
   }

}
