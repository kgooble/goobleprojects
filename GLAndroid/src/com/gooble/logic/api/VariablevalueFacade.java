package com.gooble.logic.api;

import java.util.List;

import android.content.Context;

public interface VariablevalueFacade {

   List<Long> save(Context context, Long variableId, String variableType, boolean isMainVar, List<Long> ids, List<String> values);

}
