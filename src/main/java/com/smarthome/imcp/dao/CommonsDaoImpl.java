/*     */ package com.smarthome.imcp.dao;
/*     */ 
/*     */ import com.smarthome.imcp.common.Page;
		  import com.smarthome.imcp.dao.model.bo.BoDevice;
		  import com.smarthome.imcp.dao.model.bo.BoInfraredPart;
		  import com.smarthome.imcp.dao.model.bo.BoUsersValidation;
/*     */ import java.io.Serializable;
/*     */ import java.util.Collection;
/*     */ import java.util.List;
/*     */ import javax.annotation.Resource;
/*     */ import org.apache.commons.beanutils.PropertyUtils;
/*     */ import org.hibernate.CacheMode;
/*     */ import org.hibernate.Criteria;
/*     */ import org.hibernate.LockMode;
/*     */ import org.hibernate.Query;
/*     */ import org.hibernate.SQLQuery;
/*     */ import org.hibernate.Session;
/*     */ import org.hibernate.SessionFactory;
/*     */ import org.hibernate.criterion.DetachedCriteria;
/*     */ import org.hibernate.criterion.Example;
/*     */ import org.hibernate.criterion.MatchMode;
/*     */ import org.hibernate.criterion.Order;
/*     */ import org.hibernate.criterion.Projections;
/*     */ import org.hibernate.criterion.Restrictions;
/*     */ import org.hibernate.metadata.ClassMetadata;
/*     */ import org.hibernate.transform.Transformers;
/*     */ import org.hibernate.type.Type;
      //Excel操作
          //导入
		  import java.io.File;
		  import jxl.*; 
		  //导出
		  import java.io.FileOutputStream;
		  import java.util.ArrayList;
		  import org.apache.poi.hssf.usermodel.HSSFCell;
		  import org.apache.poi.hssf.usermodel.HSSFCellStyle;
		  import org.apache.poi.hssf.usermodel.HSSFRow;
		  import org.apache.poi.hssf.usermodel.HSSFSheet;
		  import org.apache.poi.hssf.usermodel.HSSFWorkbook;
		  
/*     */ public class CommonsDaoImpl<T, PK extends Serializable>
/*     */   implements CommonsDaoIface<T, PK>
/*     */ {
/*     */   protected SessionFactory sessionFactory;
/*     */   private Class persistentClass;
/*     */ 
/*     */   public SessionFactory getCurrentSessionFactory()
/*     */   {
/*  35 */     return this.sessionFactory;
/*     */   }
/*     */ 
/*     */   @Resource(name="sessionFactory")
/*     */   public void setSessionFactory(SessionFactory sessionFactory)
/*     */   {
/*  41 */     this.sessionFactory = sessionFactory;
/*     */   }
/*     */ 
/*     */   public Session getCurrentSession()
/*     */   {
/*  46 */     return this.sessionFactory.getCurrentSession();
/*     */   }
/*     */ 
/*     */   public CommonsDaoImpl(Class persistentClass)
/*     */   {
/*  53 */     this.persistentClass = persistentClass;
/*     */   }
			public CommonsDaoImpl() {
	// TODO Auto-generated constructor stub
			}
/*     */ 
/*     */   public Class<T> getType() {
/*  57 */     return this.persistentClass;
/*     */   }
/*     */ 
/*     */   public DetachedCriteria createDetachedCriteria()
/*     */   {
/*  64 */     return DetachedCriteria.forClass(this.persistentClass);
/*     */   }
/*     */ 
/*     */   public Criteria createCriteria()
/*     */   {
/*  69 */     return createDetachedCriteria().getExecutableCriteria(getCurrentSession());
/*     */   }
/*     */ 
/*     */   public T findById(PK id)
/*     */   {
/*  76 */     return (T) getCurrentSession().get(this.persistentClass, id);
/*     */   }

			//new
//			public T findByPhone(String userPhone)
///*     */   {
///*  76 */     return (T) getCurrentSession().get(this.persistentClass, userPhone);
///*     */   }
/*     */ 
/*     */   public T findByIdWithLock(PK id, LockMode lock)
/*     */   {
/*  81 */     Object t = getCurrentSession().get(this.persistentClass, id, lock);
/*  82 */     if (t != null) {
/*  83 */       flush();
/*     */     }
/*  85 */     return (T) t;
/*     */   }
/*     */ 
/*     */   public T load(PK id)
/*     */   {
/*  90 */     return (T) getCurrentSession().load(this.persistentClass, id);
/*     */   }
/*     */ 
/*     */   public T loadWithLock(PK id, LockMode lock)
/*     */   {
/*  95 */     Object t = getCurrentSession().load(this.persistentClass, id, lock);
/*  96 */     if (t != null) {
/*  97 */       flush();
/*     */     }
/*  99 */     return (T) t;
/*     */   }
/*     */ 
/*     */   public void update(T entity)
/*     */   {
/* 104 */     getCurrentSession().update(entity);
/*     */   }
/*     */ 
/*     */   public void batchUpdate(T entity, int batchNumber)
/*     */   {
/* 109 */     for (int i = 1; i <= batchNumber; i++) {
/* 110 */       getCurrentSession().update(entity);
/* 111 */       if (i % batchNumber == 0) {
/* 112 */         getCurrentSession().flush();
/* 113 */         getCurrentSession().clear();
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   public void save(T entity)
/*     */   {
/* 120 */     getCurrentSession().save(entity);
/* 121 */     getCurrentSession().flush();
/*     */   }
            //这两个方法是从excel导入数据时用的
//			public void save(BoDevice entity)
///*     */   {
///* 120 */     getCurrentSession().save(entity);
///* 121 */     getCurrentSession().flush();
///*     */   }
//			public void save(BoHostDevice entity)
//			{
//			  getCurrentSession().save(entity);
//			  getCurrentSession().flush();
//			}
/*     */ 
/*     */   public void batchSave(T entity, int batchNumber)
/*     */   {
/* 126 */     for (int i = 1; i <= batchNumber; i++) {
/* 127 */       getCurrentSession().save(entity);
/* 128 */       if (i % batchNumber == 0) {
/* 129 */         getCurrentSession().flush();
/* 130 */         getCurrentSession().clear();
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   public T merge(T entity)
/*     */   {
/* 137 */     return (T) getCurrentSession().merge(entity);
/*     */   }
/*     */ 
/*     */   public void saveOrUpdate(T entity)
/*     */   {
/* 144 */     getCurrentSession().saveOrUpdate(entity);
/*     */   }
/*     */ 
/*     */   public void saveOrUpdateAll(Collection<T> entities)
/*     */   {
/*     */   }
/*     */ 
/*     */   public void delete(T entity)
/*     */   {
/* 158 */     getCurrentSession().delete(entity);
/*     */   }
/*     */ 
/*     */   public void deleteByKey(PK id)
/*     */   {
/* 163 */     delete(load(id));
/*     */   }
/*     */ 
/*     */   public void deleteAll(Collection<T> entities)
/*     */   {
/* 172 */     getCurrentSession().delete(entities);
/*     */   }
/*     */ 
/*     */   public int deleteLogicByKey(String field, PK id) {
/* 176 */     StringBuffer sb = new StringBuffer();
/* 177 */     sb.append("update ");
/* 178 */     sb.append(this.persistentClass.getName());
/* 179 */     sb.append(" set ");
/* 180 */     sb.append(field);
/* 181 */     sb.append("='Y' WHERE ");
/* 182 */     sb.append(getPrimaryKey(this.persistentClass));
/* 183 */     sb.append(" = ");
/* 184 */     sb.append("'");
/* 185 */     sb.append(id);
/* 186 */     sb.append("'");
/* 187 */     return getCurrentSession().createQuery(sb.toString())
/* 188 */       .executeUpdate();
/*     */   }
/*     */ 
/*     */   public int deleteLogicByKey(PK id)
/*     */   {
/* 193 */     return deleteLogicByKey("mntDelete", id);
/*     */   }
/*     */ 
/*     */   public int doPositionByKey(PK id, Integer mntPosition)
/*     */   {
/* 203 */     StringBuffer sb = new StringBuffer();
/* 204 */     sb.append("update ");
/* 205 */     sb.append(this.persistentClass.getName());
/* 206 */     sb.append(" set mntPosition=");
/* 207 */     sb.append(mntPosition);
/* 208 */     sb.append(" WHERE ");
/* 209 */     sb.append(getPrimaryKey(this.persistentClass));
/* 210 */     sb.append(" = ");
/* 211 */     sb.append("'");
/* 212 */     sb.append(id);
/* 213 */     sb.append("'");
/* 214 */     return getCurrentSession().createQuery(sb.toString())
/* 215 */       .executeUpdate();
/*     */   }
/*     */ 
/*     */   public String getPrimaryKey(Class entityClass) {
/* 219 */     ClassMetadata classMetadata = this.sessionFactory.getClassMetadata(entityClass);
/* 220 */     return classMetadata.getIdentifierPropertyName();
/*     */   }
/*     */ 
/*     */   public int bulkUpdate(String queryString)
/*     */   {
/* 227 */     Query query = getCurrentSession().createQuery(queryString);
/* 228 */     return query.executeUpdate();
/*     */   }
/*     */ 
/*     */   public int bulkUpdate(String queryString, Object value)
/*     */   {
/* 233 */     Query query = getCurrentSession().createQuery(queryString);
/* 234 */     query.setParameter(0, value);
/* 235 */     return query.executeUpdate();
/*     */   }
/*     */ 
/*     */   public int bulkUpdate(String queryString, Object[] values)
/*     */   {
/* 240 */     Query query = getCurrentSession().createQuery(queryString);
/* 241 */     for (int i = 0; i < values.length; i++) {
/* 242 */       query.setParameter(i, values[i]);
/*     */     }
/* 244 */     return query.executeUpdate();//executeUpdate 的返回值是一个整数，指示受影响的行数（即更新计数）。对于 CREATE TABLE 或 DROP TABLE 等不操作行的语句，executeUpdate 的返回值总为零。返回值为-1代表更新不成功。
/*     */   }
/*     */ 
/*     */   public List find(String queryString) {
/* 248 */     Query query = getCurrentSession().createQuery(queryString);
/* 249 */     return query.list();
/*     */   }
/*     */ 
/*     */   public List find(String queryString, Object[] values) {
/* 253 */     Query query = getCurrentSession().createQuery(queryString);
/* 254 */     for (int i = 0; i < values.length; i++) {
/* 255 */       query.setParameter(i, values[i]);
/*     */     }
/* 257 */     return query.list();
/*     */   }
/*     */ 
/*     */   public List findByNamedParam(String queryString, String[] paramNames, Object[] values)
/*     */   {
/* 262 */     Query query = getCurrentSession().createQuery(queryString);
/* 263 */     for (int i = 0; i < values.length; i++) {
/* 264 */       query.setParameter(paramNames[i], values[i]);
/*     */     }
/* 266 */     return query.list();
/*     */   }
/*     */ 
/*     */   public List findByNamedParam(String queryString, String[] paramNames, Object[] values, Page page)
/*     */   {
/* 271 */     Query q = getCurrentSession().createQuery(queryString);
/* 272 */     if (page != null) {
/* 273 */       q.setFirstResult(page.getStartRow());
/* 274 */       q.setMaxResults(page.getPageSize());
/*     */     }
/* 276 */     if ((paramNames != null) && (values != null)) {
/* 277 */       for (int i = 0; i < values.length; i++)
/* 278 */         q.setParameter(paramNames[i], values[i]);
/*     */     }
/* 280 */     List result = q.list();
/* 281 */     if (page != null) {
/* 282 */       page.setEndRow(page.getStartRow() + result.size());
/*     */     }
/* 284 */     return result;
/*     */   }
/*     */ 
/*     */   public List findByNamedQuery(String queryName) {
/* 288 */     Query query = getCurrentSession().getNamedQuery(queryName);
/* 289 */     return query.list();
/*     */   }
/*     */ 
/*     */   public List findByNamedQuery(String queryName, Object value) {
/* 293 */     Query query = getCurrentSession().getNamedQuery(queryName);
/* 294 */     query.setParameter(0, value);
/* 295 */     List result = query.list();
/*     */ 
/* 297 */     return result;
/*     */   }
/*     */ 
/*     */   public List findByNamedQuery(String queryName, Object[] values) {
/* 301 */     Query query = getCurrentSession().getNamedQuery(queryName);
/*     */ 
/* 303 */     int i = 0; for (int size = values.length; i < size; i++) {
/* 304 */       query.setParameter(i, values[i]);
/*     */     }
/* 306 */     return query.list();
/*     */   }
/*     */ 
/*     */   public Object findByNamedExecute(String queryName) {
/* 310 */     Query query = getCurrentSession().getNamedQuery(queryName);
/* 311 */     return Integer.valueOf(query.executeUpdate());
/*     */   }
/*     */ 
/*     */   public Object findByNamedExecute(String queryName, Object value) {
/* 315 */     Query query = getCurrentSession().getNamedQuery(queryName);
/* 316 */     if (value != null)
/* 317 */       query.setParameter(0, value);
/* 318 */     return Integer.valueOf(query.executeUpdate());
/*     */   }
/*     */ 
/*     */   public Object findByNamedExecute(String queryName, Object[] values)
/*     */   {
/* 323 */     Query query = getCurrentSession().getNamedQuery(queryName);
/* 324 */     if ((values != null) && (queryName != null)) {
/* 325 */       for (int i = 0; i < values.length; i++)
/* 326 */         query.setParameter(i, values[i]);
/*     */     }
/* 328 */     return Integer.valueOf(query.executeUpdate());
/*     */   }
/*     */ 
/*     */   public Object findByNamedExecute(String queryName, String[] params, Object[] values)
/*     */   {
/* 333 */     Query query = getCurrentSession().getNamedQuery(queryName);
/* 334 */     if ((values != null) && (queryName != null)) {
/* 335 */       for (int i = 0; i < values.length; i++) {
/* 336 */         if ((values[i] instanceof Object[]))
/* 337 */           query.setParameterList(params[i], 
/* 338 */             (Object[])values[i]);
/*     */         else
/* 340 */           query.setParameter(params[i], values[i]);
/*     */       }
/*     */     }
/* 343 */     return Integer.valueOf(query.executeUpdate());
/*     */   }
/*     */ 
/*     */   public List findByNamedQueryAndNamedParam(String queryName, String[] paramNames, Object[] values)
/*     */   {
/* 349 */     Query query = getCurrentSession().getNamedQuery(queryName);
/* 350 */     int i = 0; for (int size = paramNames.length; i < size; i++) {
/* 351 */       query.setParameter(paramNames[i], values[i]);
/*     */     }
/* 353 */     return query.list();
/*     */   }
/*     */ 
/*     */   public List findByNamedQueryAndNamedParam(String queryName, String[] paramNames, Object[] values, Page page)
/*     */   {
/* 358 */     if (page != null) {
/* 359 */       page.setTotalCount(getCountByNamedQuery(queryName + "Count", paramNames, values).intValue());
/*     */     }
/* 361 */     Query query = getCurrentSession().getNamedQuery(queryName);
/*     */ 
/* 363 */     int i = 0; for (int size = paramNames.length; i < size; i++) {
/* 364 */       query.setParameter(paramNames[i], values[i]);
/*     */     }
/*     */ 
/* 367 */     if (page != null) {
/* 368 */       query.setFirstResult(page.getStartRow());
/* 369 */       query.setMaxResults(page.getPageSize());
/*     */     }
/*     */     
/* 372 */     List result = query.list();
/* 373 */     if (page != null) {
/* 374 */       page.setEndRow(page.getStartRow() + result.size());
/*     */     }
/* 376 */     return result;
/*     */   }
/*     */ 
/*     */   public List findByNamedQueryAndType(String queryName, Type[] paramTypes, Object[] values)
/*     */   {
/* 381 */     Query query = getCurrentSession().getNamedQuery(queryName);
/* 382 */     if ((values != null) && (queryName != null))
/*     */     {
/* 384 */       query.setParameters(values, paramTypes);
/*     */     }
/* 386 */     return query.list();
/*     */   }
/*     */ 
/*     */   public List findByNamedQueryAndType(String queryName, Type[] paramTypes, Object[] values, Page page)
/*     */   {
/* 391 */     if (page != null) {
/* 392 */       page.setTotalCount(getCountByNamedQuery(queryName + "Count", paramTypes, values).intValue());
/*     */     }
/* 394 */     Query query = getCurrentSession().getNamedQuery(queryName);
/* 395 */     if ((values != null) && (queryName != null))
/*     */     {
/* 397 */       query.setParameters(values, paramTypes);
/*     */     }
/*     */ 
/* 400 */     if (page != null) {
/* 401 */       query.setFirstResult(page.getStartRow());
/* 402 */       query.setMaxResults(page.getPageSize());
/*     */     }
/*     */ 
/* 405 */     List result = query.list();
/* 406 */     if (page != null) {
/* 407 */       page.setEndRow(page.getStartRow() + result.size());
/*     */     }
/* 409 */     return result;
/*     */   }
/*     */ 
/*     */   public List findByNamedQuery(String queryName, Page page) {
/* 413 */     if (page != null) {
/* 414 */       page.setTotalCount(getCountByNamedQuery(queryName + "Count").intValue());
/*     */     }
/* 416 */     Query query = getCurrentSession().getNamedQuery(queryName);
/*     */ 
/* 418 */     if (page != null) {
/* 419 */       query.setFirstResult(page.getStartRow());
/* 420 */       query.setMaxResults(page.getPageSize());
/*     */     }
/* 422 */     List result = query.list();
/* 423 */     if (page != null) {
/* 424 */       page.setEndRow(page.getStartRow() + result.size());
/*     */     }
/* 426 */     return result;
/*     */   }
/*     */ 
/*     */   public List findByNamedQuery(String queryName, Page page, boolean toBean)
/*     */   {
/* 431 */     if (page != null) {
/* 432 */       page.setTotalCount(getCountByNamedQuery(queryName + "Count").intValue());
/*     */     }
/* 434 */     Query query = getCurrentSession().getNamedQuery(queryName);
/*     */ 
/* 436 */     if (toBean) {
/* 437 */       query.setResultTransformer(
/* 438 */         Transformers.aliasToBean(this.persistentClass));
/*     */     }
/*     */ 
/* 441 */     if (page != null) {
/* 442 */       query.setFirstResult(page.getStartRow());
/* 443 */       query.setMaxResults(page.getPageSize());
/*     */     }
/* 445 */     List result = query.list();
/* 446 */     if (page != null) {
/* 447 */       page.setEndRow(page.getStartRow() + result.size());
/*     */     }
/* 449 */     return result;
/*     */   }
/*     */ 
/*     */   public List findByNamedQuery(String queryName, Object value, Page page)
/*     */   {
/* 454 */     if (page != null) {
/* 455 */       page.setTotalCount(getCountByNamedQuery(queryName + "Count", 
/* 456 */         value).intValue());
/*     */     }
/*     */ 
/* 458 */     Query q = getCurrentSession().getNamedQuery(queryName);
/* 459 */     if (page != null) {
/* 460 */       q.setFirstResult(page.getStartRow());
/* 461 */       q.setMaxResults(page.getPageSize());
/*     */     }
/* 463 */     if ((value != null) && (queryName != null))
/* 464 */       q.setParameter(0, value);
/* 465 */     List result = q.list();
/* 466 */     if (page != null) {
/* 467 */       page.setEndRow(page.getStartRow() + result.size());
/*     */     }
/* 469 */     return result;
/*     */   }
/*     */ 
/*     */   public List findByNamedQuery(String queryName, Object[] values, Page page)
/*     */   {
/* 474 */     if (page != null) {
/* 475 */       page.setTotalCount(getCountByNamedQuery(queryName + "Count", 
/* 476 */         values).intValue());
/*     */     }
/*     */ 
/* 478 */     Query q = getCurrentSession().getNamedQuery(queryName);
/* 479 */     if (page != null) {
/* 480 */       q.setFirstResult(page.getStartRow());
/* 481 */       q.setMaxResults(page.getPageSize());
/*     */     }
/* 483 */     if ((values != null) && (queryName != null)) {
/* 484 */       for (int i = 0; i < values.length; i++)
/* 485 */         q.setParameter(i, values[i]);
/*     */     }
/* 487 */     List result = q.list();
/* 488 */     if (page != null) {
/* 489 */       page.setEndRow(page.getStartRow() + result.size());
/*     */     }
/* 491 */     return result;
/*     */   }
/*     */ 
/*     */   public List findByNamedQuery(String queryName, String[] params, Object[] values, Page page)
/*     */   {
/* 496 */     if (page != null) {
/* 497 */       page.setTotalCount(getCountByNamedQuery(queryName + "Count", 
/* 498 */         params, values).intValue());
/*     */     }
/*     */ 
/* 500 */     Query q = getCurrentSession().getNamedQuery(queryName);
/* 501 */     if (page != null) {
/* 502 */       q.setFirstResult(page.getStartRow());
/* 503 */       q.setMaxResults(page.getPageSize());
/*     */     }
/* 505 */     if ((values != null) && (queryName != null)) {
/* 506 */       for (int i = 0; i < values.length; i++) {
/* 507 */         if ((values[i] instanceof Object[]))
/* 508 */           q.setParameterList(params[i], (Object[])values[i]);
/*     */         else
/* 510 */           q.setParameter(params[i], values[i]);
/*     */       }
/*     */     }
/* 513 */     List result = q.list();
/* 514 */     if (page != null) {
/* 515 */       page.setEndRow(page.getStartRow() + result.size());
/*     */     }
/* 517 */     return result;
/*     */   }
/*     */ 
/*     */   public Integer getCountByNamedQuery(String queryName) {
/* 521 */     Query query = getCurrentSession().getNamedQuery(queryName);
/* 522 */     Object obj = query.list().get(0);
/* 523 */     return Integer.valueOf(((Number)obj).intValue());
/*     */   }
/*     */ 
/*     */   public Integer getCountByNamedQuery(String queryName, Object value)
/*     */   {
/* 528 */     Query query = getCurrentSession().getNamedQuery(queryName);
/* 529 */     if (value != null)
/* 530 */       query.setParameter(0, value);
/* 531 */     Object obj = query.list().get(0);
/* 532 */     return Integer.valueOf(((Number)obj).intValue());
/*     */   }
/*     */ 
/*     */   public Integer getCountByNamedQuery(String queryName, Object[] values)
/*     */   {
/* 537 */     Query query = getCurrentSession().getNamedQuery(queryName);
/* 538 */     if (values != null) {
/* 539 */       for (int i = 0; i < values.length; i++)
/* 540 */         query.setParameter(i, values[i]);
/*     */     }
/* 542 */     Object obj = query.list().get(0);
/* 543 */     return Integer.valueOf(((Number)obj).intValue());
/*     */   }
/*     */ 
/*     */   public Integer getCountByNamedQuery(String queryName, String[] params, Object[] values)
/*     */   {
/* 549 */     Query query = getCurrentSession().getNamedQuery(queryName);
/* 550 */     if (values != null) {
/* 551 */       for (int i = 0; i < values.length; i++) {
/* 552 */         if ((values[i] instanceof Object[]))
/* 553 */           query.setParameterList(params[i], 
/* 554 */             (Object[])values[i]);
/*     */         else
/* 556 */           query.setParameter(params[i], values[i]);
/*     */       }
/*     */     }
/* 559 */     Object obj = query.list().get(0);
/* 560 */     return Integer.valueOf(((Number)obj).intValue());
/*     */   }
/*     */ 
/*     */   public Integer getCountByNamedQuery(String queryName, Type[] paramTypes, Object[] values)
/*     */   {
/* 566 */     Query query = getCurrentSession().getNamedQuery(queryName);
/* 567 */     query.setParameters(values, paramTypes);
/* 568 */     Object obj = query.list().get(0);
/* 569 */     return Integer.valueOf(((Number)obj).intValue());
/*     */   }
/*     */ 
/*     */   public List<T> findByCriteria(DetachedCriteria detachedCriteria)
/*     */   {
//	          System.out.println("CommonsDaoImpl.java:detachedCriteria="+detachedCriteria);//CommonsDaoImpl.java:detachedCriteria=DetachableCriteria(CriteriaImpl(com.smarthome.imcp.dao.model.system.SysUser:this[][mntDelete=N, loginName=admin, loginPwd=21232F297A57A5A743894A0E4A801FC3]))
/* 576 */     Criteria criteria = detachedCriteria.getExecutableCriteria(getCurrentSession());
/* 577 */       
//			  System.out.println("CommonsDaoImpl.java:criteria="+criteria);//criteria=CriteriaImpl(com.smarthome.imcp.dao.model.system.SysUser:this[][mntDelete=N, loginName=admin, loginPwd=21232F297A57A5A743894A0E4A801FC3])
/* 578 */     
			  criteria.setCacheMode(CacheMode.NORMAL);
//              System.out.println("criteria.list()="+criteria.list());//criteria.list()=[]
/* 579 */     return criteria.list();
/*     */   }
/*     */ 
/*     */   public List<T> findByCriteria(DetachedCriteria detachedCriteria, int firstResult, int maxResults)
/*     */   {
/* 586 */     Criteria criteria = detachedCriteria.getExecutableCriteria(
/* 587 */       getCurrentSession());
/*     */ 
/* 589 */     List result = criteria.setFirstResult(firstResult)
/* 590 */       .setMaxResults(maxResults).list();
/*     */ 
/* 592 */     return result;
/*     */   }
/*     */ 
/*     */   public List<T> findByCriteria(DetachedCriteria detachedCriteria, Page page)
/*     */   {
/* 604 */     Criteria criteria = detachedCriteria.getExecutableCriteria(
/* 605 */       getCurrentSession());
/* 606 */     List result = criteria.setFirstResult(page.getStartRow())
/* 607 */       .setMaxResults(page.getPageSize()).list();
/* 608 */     page.setEndRow(page.getStartRow() + result.size());
/* 609 */     criteria.setFirstResult(0);
/* 610 */     Object total = criteria.setProjection(Projections.rowCount())
/* 611 */       .uniqueResult();
/* 612 */     int totalCount = 0;
/* 613 */     if (total != null) {
/* 614 */       totalCount = ((Long)total).intValue();
/*     */     }
/* 616 */     page.setTotalCount(totalCount);
/* 617 */     return result;
/*     */   }
/*     */ 
/*     */   public List<T> findEqualByEntity(T entity, String[] propertyNames)
/*     */   {
/* 622 */     Criteria criteria = getCurrentSession().createCriteria(
/* 623 */       entity.getClass());
/* 624 */     criteria.setCacheable(true);
/* 625 */     Example exam = Example.create(entity);
/* 626 */     exam.excludeZeroes();
/* 627 */     String[] defPropertys = getCurrentSessionFactory().getClassMetadata(
/* 628 */       this.persistentClass).getPropertyNames();
/* 629 */     for (String defProperty : defPropertys) {
/* 630 */       int ii = 0;
/* 631 */       for (ii = 0; ii < propertyNames.length; ii++) {
/* 632 */         if (defProperty.equals(propertyNames[ii])) {
/* 633 */           criteria.addOrder(Order.asc(defProperty));
/* 634 */           break;
/*     */         }
/*     */       }
/* 637 */       if (ii == propertyNames.length) {
/* 638 */         exam.excludeProperty(defProperty);
/*     */       }
/*     */     }
/* 641 */     criteria.add(exam);
/* 642 */     return criteria.list();
/*     */   }
/*     */ 
/*     */   public List<T> findLikeByEntity(T entity, String[] propertyNames)
/*     */   {
/* 647 */     Criteria criteria = getCurrentSession().createCriteria(
/* 648 */       entity.getClass());
/* 649 */     criteria.setCacheable(true);
/* 650 */     for (String property : propertyNames)
/*     */       try {
/* 652 */         Object value = PropertyUtils.getProperty(entity, property);
/* 653 */         if ((value instanceof String)) {
/* 654 */           criteria.add(Restrictions.like(property, (String)value, 
/* 655 */             MatchMode.ANYWHERE));
/* 656 */           criteria.addOrder(Order.asc(property));
/*     */         } else {
/* 658 */           criteria.add(Restrictions.eq(property, value));
/* 659 */           criteria.addOrder(Order.asc(property));
/*     */         }
/*     */       }
/*     */       catch (Exception localException)
/*     */       {
/*     */       }
/* 665 */     return criteria.list();
/*     */   }
/*     */ 
/*     */   public Integer countEqualByEntity(T entity) {
/* 669 */     Criteria criteria = getCurrentSession().createCriteria(
/* 670 */       entity.getClass());
/* 671 */     criteria.setCacheable(true);
/* 672 */     criteria.add(Example.create(entity));
/* 673 */     criteria.setProjection(Projections.rowCount());
/* 674 */     List results = criteria.list();
/* 675 */     return Integer.valueOf(Integer.parseInt(results.get(0).toString()));
/*     */   }
/*     */ 
/*     */   public Integer getRowCount(DetachedCriteria criteria)
/*     */   {
/* 680 */     Criteria crit = criteria
/* 681 */       .getExecutableCriteria(getCurrentSession());
/* 682 */     crit.setCacheable(true);
/* 683 */     crit.setProjection(Projections.rowCount());
/* 684 */     return Integer.valueOf(((Long)crit.uniqueResult()).intValue());
/*     */   }
/*     */ 
/*     */   private Object getStatValue(DetachedCriteria criteria, String propertyName, String StatName)
/*     */   {
/* 703 */     if (StatName.toLowerCase().equals("max"))
/* 704 */       criteria.setProjection(Projections.max(propertyName));
/* 705 */     else if (StatName.toLowerCase().equals("min"))
/* 706 */       criteria.setProjection(Projections.min(propertyName));
/* 707 */     else if (StatName.toLowerCase().equals("avg"))
/* 708 */       criteria.setProjection(Projections.avg(propertyName));
/* 709 */     else if (StatName.toLowerCase().equals("sum"))
/* 710 */       criteria.setProjection(Projections.sum(propertyName));
/*     */     else
/* 712 */       return null;
/* 713 */     List list = findByCriteria(criteria, 0, 1);
/* 714 */     return list.get(0);
/*     */   }
/*     */ 
/*     */   public Object getMaxValue(DetachedCriteria criteria, String propertyName)
/*     */   {
/* 719 */     Criteria crit = criteria
/* 720 */       .getExecutableCriteria(getCurrentSession());
/* 721 */     crit.setProjection(Projections.max(propertyName));
/* 722 */     return crit.uniqueResult();
/*     */   }
/*     */ 
/*     */   public Object getMinValue(DetachedCriteria criteria, String propertyName)
/*     */   {
/* 727 */     Criteria crit = criteria
/* 728 */       .getExecutableCriteria(getCurrentSession());
/* 729 */     crit.setProjection(Projections.min(propertyName));
/* 730 */     return crit.uniqueResult();
/*     */   }
/*     */ 
/*     */   public Object getAvgValue(DetachedCriteria criteria, String propertyName)
/*     */   {
/* 735 */     Criteria crit = criteria
/* 736 */       .getExecutableCriteria(getCurrentSession());
/* 737 */     crit.setProjection(Projections.avg(propertyName));
/* 738 */     return crit.uniqueResult();
/*     */   }
/*     */ 
/*     */   public Object getSumValue(DetachedCriteria criteria, String propertyName)
/*     */   {
/* 743 */     Criteria crit = criteria
/* 744 */       .getExecutableCriteria(getCurrentSession());
/* 745 */     crit.setProjection(Projections.sum(propertyName));
/* 746 */     return crit.uniqueResult();
/*     */   }
/*     */ 
/*     */   public Integer getCountValue(DetachedCriteria criteria, String propertyName)
/*     */   {
/* 751 */     Criteria crit = criteria
/* 752 */       .getExecutableCriteria(getCurrentSession());
/* 753 */     crit.setProjection(Projections.count(propertyName));
/* 754 */     return Integer.valueOf(((Long)crit.uniqueResult()).intValue());
/*     */   }
/*     */ 
/*     */   public Integer getDistinctCountValue(DetachedCriteria criteria, String propertyName)
/*     */   {
/* 760 */     Criteria crit = criteria
/* 761 */       .getExecutableCriteria(getCurrentSession());
/* 762 */     crit.setProjection(Projections.countDistinct(propertyName));
/* 763 */     return Integer.valueOf(((Long)crit.uniqueResult()).intValue());
/*     */   }
/*     */ 
/*     */   public List<T> findByHQL(String HQL)
/*     */   {
/* 769 */     return findByHQL(HQL, null, null);
/*     */   }
/*     */ 
/*     */   public List<T> findByHQL(String HQL, Object value) {
/* 773 */     return findByHQL(HQL, value, null);
/*     */   }
/*     */ 
/*     */   public List<T> findByHQL(String HQL, Object[] values) {
	          System.out.println("findByHQL...");
/* 777 */     return findByHQL(HQL, values, null);
/*     */   }
/*     */ 
/*     */   public List<T> findByHQL(String HQL, Page page) {
/* 781 */     return findByHQL(HQL, null, page);
/*     */   }
/*     */ 
/*     */   public List<T> findByHQL(String HQL, Object value, Page page)
/*     */   {
/* 786 */     if (page != null) {
/* 787 */       page.setTotalCount(getCount(HQL, value).intValue());
/*     */     }
/* 789 */     Query query = getCurrentSession().createQuery(HQL);
/* 790 */     if (page != null) {
/* 791 */       query.setFirstResult(page.getStartRow());
/* 792 */       query.setMaxResults(page.getPageSize());
/*     */     }
/* 794 */     if (value != null)
/* 795 */       query.setParameter(0, value);
			  query.executeUpdate();
/* 796 */     List result = query.list();
/* 797 */     if (page != null) {
/* 798 */       page.setEndRow(page.getStartRow() + result.size());
/*     */     }
/* 800 */     return result;
/*     */   }
/*     */ 
/*     */   public List findByHQL(String HQL, Object[] values, Page page)
/*     */   {
/* 805 */     if (page != null) {
/* 806 */       page.setTotalCount(getCount(HQL, values).intValue());
/*     */     }
/* 808 */     Query query = getCurrentSession().createQuery(HQL);
//              System.out.println("query==="+query);
/* 809 */     if (page != null) {
/* 810 */       query.setFirstResult(page.getStartRow());
/* 811 */       query.setMaxResults(page.getPageSize());
/*     */     }
/* 813 */     if (values != null) {
/* 814 */       for (int i = 0; i < values.length; i++) {
//	                System.out.println("value["+i+"]="+values[i].getClass().toString());没有问题
					query.setParameter(i, values[i]);
				}
/* 815 */         
/*     */     }
//              System.out.println("query.list="+query.list());//query.list=[1]
/* 817 */     List result = query.list();//问题出现在这
//              System.out.println("result==="+result);//result===[1]
/* 818 */     if (page != null) {
/* 819 */       page.setEndRow(page.getStartRow() + result.size());
/*     */     }
/* 821 */     return result;
/*     */   }
/*     */ 
/*     */   public List findByHQLCache(String HQL) {
/* 825 */     return findByHQLCache(HQL, null);
/*     */   }
/*     */ 
/*     */   public List findByHQLCache(String HQL, Page page) {
/* 829 */     if (page != null) {
/* 830 */       page.setTotalCount(getCount(HQL, null).intValue());
/*     */     }
/* 832 */     Query query = getCurrentSession().createQuery(HQL);
/* 833 */     if (page != null) {
/* 834 */       query.setFirstResult(page.getStartRow());
/* 835 */       query.setMaxResults(page.getPageSize());
/*     */     }
/* 837 */     List result = query.setCacheable(true).list();
/* 838 */     if (page != null) {
/* 839 */       page.setEndRow(page.getStartRow() + result.size());
/*     */     }
/* 841 */     return result;
/*     */   }
/*     */ 
/*     */   public List findByNSQL(String SQL)
/*     */   {
/* 846 */     return findByNSQL(SQL, null);
/*     */   }
/*     */ 
/*     */   public List findByNSQL(String SQL, Page page) {
/* 850 */     return findByNSQL(SQL, false, page);
/*     */   }
/*     */ 
/*     */   public List findByNSQL(String SQL, boolean toBean, Page page)
/*     */   {
/* 855 */     if (page != null) {
/* 856 */       page.setTotalCount(getCountNSQL(SQL).intValue());
/*     */     }
/*     */ 
/* 859 */     SQLQuery query = getCurrentSession().createSQLQuery(SQL);
/*     */ 
/* 861 */     if (toBean) {
/* 862 */       query.setResultTransformer(
/* 863 */         Transformers.aliasToBean(this.persistentClass));
/*     */     }
/* 865 */     if (page != null) {
/* 866 */       query.setFirstResult(page.getStartRow());
/* 867 */       query.setMaxResults(page.getPageSize());
/*     */     }
/*     */ 
/* 870 */     List result = query.list();
/* 871 */     if (page != null) {
/* 872 */       page.setEndRow(page.getStartRow() + result.size());
/*     */     }
/*     */ 
/* 875 */     return result;
/*     */   }
/*     */ 
/*     */   public Integer getCountNSQL(String queryString) {
/* 879 */     String filter = queryString.substring(queryString.toLowerCase()
/* 880 */       .indexOf("from"));
/* 881 */     Query query = getCurrentSession().createSQLQuery(
/* 882 */       "select count(*) " + filter);
/* 883 */     Object obj = query.list().get(0);
/* 884 */     return Integer.valueOf(((Number)obj).intValue());
/*     */   }
/*     */ 
/*     */   public int executeByNSQL(String SQL) {
/* 888 */     SQLQuery query = getCurrentSession().createSQLQuery(SQL);
/* 889 */     return query.executeUpdate();
/*     */   }
/*     */ 
/*     */   public void lock(T entity, LockMode lock)
/*     */   {
/* 894 */     getCurrentSession().lock(entity, lock);
/*     */   }
/*     */ 
/*     */   public void flush()
/*     */   {
/* 899 */     getCurrentSession().flush();
/*     */   }
/*     */ 
/*     */   public Integer getCount(String queryString, Object param) {
/* 903 */     String filter = queryString.substring(queryString.toLowerCase()
/* 904 */       .indexOf("from"));
/*     */ 
/* 906 */     Query query = getCurrentSession().createQuery(
/* 907 */       "select count(*) " + filter);
/* 908 */     if (param != null) {
/* 909 */       query.setParameter(0, param);
/*     */     }
/* 911 */     Object obj = query.list().get(0);
/*     */ 
/* 913 */     return Integer.valueOf(((Number)obj).intValue());
/*     */   }
/*     */ 
/*     */   public Integer getCount(String queryString, Object[] params) {
/* 917 */     String filter = queryString.substring(queryString.toLowerCase()
/* 918 */       .indexOf("from"));
/*     */ 
/* 920 */     Query query = getCurrentSession().createQuery(
/* 921 */       "select count(*) " + filter);
/* 922 */     if (params != null) {
/* 923 */       for (int i = 0; i < params.length; i++) {
/* 924 */         query.setParameter(i, params[i]);
/*     */       }
/*     */     }
/* 927 */     Object obj = query.list().get(0);
/* 928 */     return Integer.valueOf(((Number)obj).intValue());
/*     */   }
          //导入Excel
			@Override
			public String saveExcel(int choiceTo,String filepath) throws Exception {
				String result="fail";
				List<BoDevice> list=new ArrayList<BoDevice>();
		        try {
		            Workbook rwb=Workbook.getWorkbook(new File(filepath));
		            Sheet rs=rwb.getSheet(0);//或者rwb.getSheet(0)
		            int clos=rs.getColumns();//得到所有的列
		            int rows=rs.getRows();//得到所有的行
		            
		            System.out.println(clos+" rows:"+rows);
		            for (int i = 1; i < rows; i++) {
		                for (int j = 0; j < clos; j++) {
		                	switch(choiceTo) {
		                	  case 0:
	                		  	//创建BoDevice实体类
								BoDevice info=new BoDevice();
			                    //第一个是列数，第二个是行数
			                    String deviceCode=rs.getCell(j++, i).getContents();//默认最左边编号也算一列 所以这里得j++
			                    info.setDeviceCode(deviceCode);
			                    String type=rs.getCell(j++, i).getContents();
			                    info.setType(type);
								info.setStatus(0);
								save(info);
								result="success";
//				                    System.out.println("deviceCode:"+deviceCode+" type:"+type);  
								break;
		                	  case 1:
		                		  System.out.println("case l");
		                		//创建实体类
		                		BoInfraredPart info1=new BoInfraredPart();
								//取出当前行第1个单元格数据，并封装在info实体stuName属性上
								String deviceAddress=rs.getCell(j++, i).getContents();//默认最左边编号也算一列 所以这里得j++
								info1.setDeviceAddress(deviceAddress);
								String validationCode=rs.getCell(j++, i).getContents();//默认最左边编号也算一列 所以这里得j++
								info1.setValidationCode(validationCode);
								save(info1);
								result="success";
								break;
							default:
								System.out.println("导入Excel出错");  
		                	}
		                	
		                }
		            }
		        } catch (Exception e) {
		            e.printStackTrace();
		        } 
				return result;
			}
           

			//导入excel新建的方法
			private void save(BoInfraredPart info1) {
				getCurrentSession().save(info1);
				getCurrentSession().flush();
				
			}
			private void save(BoDevice info) {
				getCurrentSession().save(info);
				getCurrentSession().flush();
			
		}
			//导出到Excel
			@Override
			public String toExcel(int num,List<T> list) {
				String fileName="";
				// 第一步，创建一个webbook，对应一个Excel文件
				HSSFWorkbook wb = new HSSFWorkbook();
				
				switch(num) {
				 case 0://登录验证码导出到Excel
				    // 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet
					HSSFSheet sheet = wb.createSheet("登录验证码");
					// 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short
					HSSFRow row = sheet.createRow((int) 0);
					// 第四步，创建单元格，并设置值表头 设置表头居中
					HSSFCellStyle style = wb.createCellStyle();
					style.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 创建一个居中格式
					HSSFCell cell = row.createCell((short) 0);
					cell.setCellValue("手机号");
					cell.setCellStyle(style);
					cell = row.createCell((short) 1);
					cell.setCellValue("验证码");
					cell.setCellStyle(style);
					
					// 第五步，写入实体数据 实际应用中这些数据从数据库得到，
					for (int i = 0; i < list.size(); i++)
					{
						row = sheet.createRow((int) i + 1);
						BoUsersValidation validation = (BoUsersValidation) list.get(i);
						// 第四步，创建单元格，并设置值
						row.createCell((short) 0).setCellValue(validation.getUserPhone());
						row.createCell((short) 1).setCellValue(validation.getVerificationCode());
					}
					 fileName="C:/ZNHOME/usersValidation.xls";
//					 map.put("userPhone", userPhone);
//					 map.put("verificationCode", verificationCode);
					break;
				 default : 
					 System.out.println("无法正常导出Excel");
				     break; 
				}
				// 第六步，将文件存到指定位置
				try
				{
					if(fileName != "") {
						FileOutputStream fout = new FileOutputStream(fileName);
						wb.write(fout);
						fout.close();
					}else {
						return "fail";
					}	
				}
				catch (Exception e)
				{
					e.printStackTrace();
				}
				return "success";
			}
/*     */
		 }

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.dao.CommonsDaoImpl
 * JD-Core Version:    0.6.2
 */