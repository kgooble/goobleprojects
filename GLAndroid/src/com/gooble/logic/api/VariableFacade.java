package com.gooble.logic.api;

import java.util.List;

import android.content.Context;

public interface VariableFacade {

   List<Long> save(Context context, Long puzzleId, List<Long> ids, List<String> names);

}
