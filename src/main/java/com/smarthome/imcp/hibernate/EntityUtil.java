/*     */ package com.smarthome.imcp.hibernate;
/*     */ 
/*     */ import java.util.Iterator;
/*     */ import javax.annotation.Resource;
/*     */ import org.hibernate.SessionFactory;
/*     */ import org.hibernate.cfg.Configuration;
/*     */ import org.hibernate.mapping.Column;
/*     */ import org.hibernate.mapping.PersistentClass;
/*     */ import org.hibernate.mapping.PrimaryKey;
/*     */ import org.hibernate.mapping.Property;
/*     */ import org.hibernate.mapping.Table;
/*     */ 
/*     */ public class EntityUtil
/*     */ {
/*     */   private static Configuration hibernateConf;
/*     */   protected SessionFactory sessionFactory;
/*     */ 
/*     */   @Resource(name="sessionFactory")
/*     */   public void setSessionFactory(SessionFactory sessionFactory)
/*     */   {
/*  31 */     this.sessionFactory = sessionFactory;
/*     */   }
/*     */ 
/*     */   private static Configuration getHibernateConf() {
/*  35 */     if (hibernateConf == null) {
/*  36 */       hibernateConf = new Configuration().configure();
/*     */     }
/*     */ 
/*  40 */     return hibernateConf;
/*     */   }
/*     */ 
/*     */   private static PersistentClass getPersistentClass(Class<?> clazz) {
/*  44 */     synchronized (EntityUtil.class) {
/*  45 */       PersistentClass pc = getHibernateConf().getClassMapping(
/*  46 */         clazz.getName());
/*  47 */       if (pc == null) {
/*  48 */         hibernateConf = getHibernateConf().addClass(clazz);
/*  49 */         pc = getHibernateConf().getClassMapping(clazz.getName());
/*     */       }
/*  51 */       return pc;
/*     */     }
/*     */   }
/*     */ 
/*     */   public static String getTableName(Class<?> clazz)
/*     */   {
/*  63 */     return getPersistentClass(clazz).getTable().getName();
/*     */   }
/*     */ 
/*     */   public static String getPrimaryKey(Class<?> clazz)
/*     */   {
/*  74 */     return getPrimaryKeys(clazz).getColumn(0).getName();
/*     */   }
/*     */ 
/*     */   public static PrimaryKey getPrimaryKeys(Class<?> clazz)
/*     */   {
/*  85 */     return getPersistentClass(clazz).getTable().getPrimaryKey();
/*     */   }
/*     */ 
/*     */   public static String getColumnName(Class<?> clazz, String propertyName)
/*     */   {
/*  98 */     PersistentClass persistentClass = getPersistentClass(clazz);
/*  99 */     Property property = persistentClass.getProperty(propertyName);
/* 100 */     Iterator it = property.getColumnIterator();
/* 101 */     if (it.hasNext()) {
/* 102 */       Column column = (Column)it.next();
/* 103 */       return column.getName();
/*     */     }
/* 105 */     return null;
/*     */   }
/*     */ 
/*     */   public static Column getColumn(Class<?> clazz, String propertyName)
/*     */   {
/* 116 */     PersistentClass persistentClass = getPersistentClass(clazz);
/* 117 */     Property property = persistentClass.getProperty(propertyName);
/* 118 */     Iterator it = property.getColumnIterator();
/* 119 */     if (it.hasNext()) {
/* 120 */       return (Column)it.next();
/*     */     }
/* 122 */     return null;
/*     */   }
/*     */ }

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.hibernate.EntityUtil
 * JD-Core Version:    0.6.2
 */