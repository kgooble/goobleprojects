package com.gooble.logic.app.entity;

public class EntityFactory<E extends Entity> {

   private final Class<E> entityClass;

   public EntityFactory(Class<E> entityClass){
      this.entityClass = entityClass;
   }
   
   public Class<E> getEntityClass() {
      return entityClass;
   }

   public String getTableName() {
      return entityClass.getSimpleName().toLowerCase();
   }

   public E createNew() {
      try {
         E entity = entityClass.getConstructor().newInstance();
         entity.setNew(true);
         return entity;
      } catch (Exception e) {
         throw new RuntimeException(e);
      }      
   }

   public Iterable<String> getFields() {
      return new EntityFields(entityClass).getFields();
   }
   
   public Class<?> getFieldType(String field){
      return new EntityFields(entityClass).getFieldType(field);
   }

}
