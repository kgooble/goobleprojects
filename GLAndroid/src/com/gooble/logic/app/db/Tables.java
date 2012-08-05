package com.gooble.logic.app.db;

import android.provider.BaseColumns;

public class Tables {

   public class Puzzle implements BaseColumns{
      public static final String NAME = "name";
      public static final String MAINVARIABLEID = "mainvariableid";
   }
   
   public class Hint implements BaseColumns{
      public static final String PUZZLEID = "puzzleid";
   }

   public class Variable implements BaseColumns{
      public static final String NAME = "name";
      public static final String PUZZLEID = "puzzleid";
   }
   public class Variablevalue implements BaseColumns{
      public static final String VALUE = "value";
      public static final String VARIABLEID = "variableid";
   }
   public class Term implements BaseColumns{
      public static final String VALUE = "value";
      public static final String VARIABLE = "variable"; // whether or not this term is a variable
      public static final String VARIABLEID = "variableid"; 
   }
   
   /*
    * relation definitions, not for hints 
    */
   public class Relation implements BaseColumns{
      public static final String NAME = "name";
      public static final String BIDIRECTIONAL = "bidirectional"; // if a relation is bidirectional, it means that if rel(X, Y) is true, rel(Y, X) is true
      public static final String UNIQUEVALUE = "uniquevalue"; // if a relation is unique, it means if rel(X, Y) is true, rel(X, Z) cannot be true for any Z s.t Z != Y 
      public static final String VARIABLEID = "variableid";
   }
   public class Relationterm implements BaseColumns{
      public static final String RELATIONID = "relationid";
      public static final String TERMID = "termid";
      public static final String RELATIONORDER = "relationorder"; // the order that this variable comes in in the relation. eg for rel(X, Y) , RELATIONORDER(X) must be less than RELATIONORDER(Y)
   }

   public class Condition implements BaseColumns{
      public static final String RELATIONID = "relationid";
      public static final String OPERATOR = "operator";
   }
   public class Conditionterm implements BaseColumns{
      public static final String CONDITIONID = "conditionid";
      public static final String TERMID = "termid";
   }
   
   public class Property implements BaseColumns{
      public static final String HINTID = "hintid";
   }
   public class Propertyterm implements BaseColumns{
      public static final String PROPERTYID = "propertyid";
      public static final String TERMID = "termid";
   }
   
}
 