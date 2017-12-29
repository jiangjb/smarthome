/*    */ package com.smarthome.imcp.dao.model.bo;
/*    */ 
/*    */ public class BoMetadata
/*    */ {
/*    */   private int id;
/*    */   private String keyName;
/*    */   private String description;
/*    */   private String value;
/*    */   private char deleted;
/*    */ 
/*    */   public BoMetadata()
/*    */   {
/* 11 */     this.deleted = 'N';
/*    */   }
/*    */ 
/*    */   public int getId()
/*    */   {
/* 18 */     return this.id;
/*    */   }
/*    */ 
/*    */   public void setId(int id)
/*    */   {
/* 24 */     this.id = id;
/*    */   }
/*    */ 
/*    */   public String getDescription()
/*    */   {
/* 30 */     return this.description;
/*    */   }
/*    */ 
/*    */   public void setDescription(String description)
/*    */   {
/* 36 */     this.description = description;
/*    */   }
/*    */ 
/*    */   public String getValue()
/*    */   {
/* 42 */     return this.value;
/*    */   }
/*    */ 
/*    */   public void setValue(String value)
/*    */   {
/* 48 */     this.value = value;
/*    */   }
/*    */ 
/*    */   public char getDeleted()
/*    */   {
/* 54 */     return this.deleted;
/*    */   }
/*    */ 
/*    */   public void setDeleted(char deleted)
/*    */   {
/* 60 */     this.deleted = deleted;
/*    */   }
/*    */ 
/*    */   public String getKeyName()
/*    */   {
/* 67 */     return this.keyName;
/*    */   }
/*    */ 
/*    */   public void setKeyName(String keyName)
/*    */   {
/* 74 */     this.keyName = keyName;
/*    */   }
/*    */ }

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.dao.model.bo.BoMetadata
 * JD-Core Version:    0.6.2
 */