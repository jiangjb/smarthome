package com.smarthome.imcp.dao;

import com.smarthome.imcp.common.Page;

import java.io.FileNotFoundException;
import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.LockMode;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.type.Type;

public abstract interface CommonsDaoIface<T, PK extends Serializable>
{
  public abstract Class<T> getType();

  public abstract Session getCurrentSession();

  public abstract T findById(PK paramPK);
  //new
//  public abstract T findByPhone(String userPhone);

  public abstract T findByIdWithLock(PK paramPK, LockMode paramLockMode);

  public abstract T load(PK paramPK);

  public abstract T loadWithLock(PK paramPK, LockMode paramLockMode);

  public abstract void update(T paramT);

  public abstract void batchUpdate(T paramT, int paramInt);

  public abstract void save(T paramT);

  public abstract void batchSave(T paramT, int paramInt);

  public abstract T merge(T paramT);

  public abstract void saveOrUpdate(T paramT);

  public abstract void delete(T paramT);

  public abstract void deleteByKey(PK paramPK);

  public abstract void deleteAll(Collection<T> paramCollection);

  public abstract int deleteLogicByKey(String paramString, PK paramPK);

  public abstract int deleteLogicByKey(PK paramPK);

  public abstract String getPrimaryKey(Class paramClass);

  public abstract int doPositionByKey(PK paramPK, Integer paramInteger);

  public abstract int bulkUpdate(String paramString);

  public abstract int bulkUpdate(String paramString, Object paramObject);

  public abstract int bulkUpdate(String paramString, Object[] paramArrayOfObject);

  public abstract List find(String paramString);

  public abstract List find(String paramString, Object[] paramArrayOfObject);

  public abstract List findByNamedParam(String paramString, String[] paramArrayOfString, Object[] paramArrayOfObject);

  public abstract List findByNamedParam(String paramString, String[] paramArrayOfString, Object[] paramArrayOfObject, Page paramPage);

  public abstract List findByNamedQuery(String paramString);

  public abstract List findByNamedQuery(String paramString, Object paramObject);

  public abstract List findByNamedQuery(String paramString, Object[] paramArrayOfObject);

  public abstract List findByNamedQueryAndNamedParam(String paramString, String[] paramArrayOfString, Object[] paramArrayOfObject);

  public abstract List findByNamedQueryAndNamedParam(String paramString, String[] paramArrayOfString, Object[] paramArrayOfObject, Page paramPage);

  public abstract List findByNamedQueryAndType(String paramString, Type[] paramArrayOfType, Object[] paramArrayOfObject);

  public abstract List findByNamedQueryAndType(String paramString, Type[] paramArrayOfType, Object[] paramArrayOfObject, Page paramPage);

  public abstract List findByNamedQuery(String paramString, Page paramPage);

  public abstract List findByNamedQuery(String paramString, Object paramObject, Page paramPage);

  public abstract List findByNamedQuery(String paramString, Object[] paramArrayOfObject, Page paramPage);

  public abstract List findByNamedQuery(String paramString, String[] paramArrayOfString, Object[] paramArrayOfObject, Page paramPage);

  public abstract Object findByNamedExecute(String paramString);

  public abstract Object findByNamedExecute(String paramString, Object paramObject);

  public abstract Object findByNamedExecute(String paramString, Object[] paramArrayOfObject);

  public abstract Object findByNamedExecute(String paramString, String[] paramArrayOfString, Object[] paramArrayOfObject);

  public abstract List<T> findByCriteria(DetachedCriteria paramDetachedCriteria);

  public abstract List<T> findByCriteria(DetachedCriteria paramDetachedCriteria, int paramInt1, int paramInt2);

  public abstract List<T> findByCriteria(DetachedCriteria paramDetachedCriteria, Page paramPage);

  public abstract List<T> findEqualByEntity(T paramT, String[] paramArrayOfString);

  public abstract List<T> findLikeByEntity(T paramT, String[] paramArrayOfString);

  public abstract Integer getRowCount(DetachedCriteria paramDetachedCriteria);

  public abstract Object getMaxValue(DetachedCriteria paramDetachedCriteria, String paramString);

  public abstract Object getMinValue(DetachedCriteria paramDetachedCriteria, String paramString);

  public abstract Object getAvgValue(DetachedCriteria paramDetachedCriteria, String paramString);

  public abstract Object getSumValue(DetachedCriteria paramDetachedCriteria, String paramString);

  public abstract Integer getCountValue(DetachedCriteria paramDetachedCriteria, String paramString);

  public abstract Integer getDistinctCountValue(DetachedCriteria paramDetachedCriteria, String paramString);

  public abstract List findByHQL(String paramString);

  public abstract List findByHQL(String paramString, Page paramPage);

  public abstract List findByHQL(String paramString, Object paramObject);

  public abstract Integer getCount(String paramString, Object paramObject);

  public abstract Integer getCount(String paramString, Object[] paramArrayOfObject);

  public abstract List findByHQL(String paramString, Object paramObject, Page paramPage);

  public abstract List findByHQL(String paramString, Object[] paramArrayOfObject);

  public abstract List findByHQL(String paramString, Object[] paramArrayOfObject, Page paramPage);

  public abstract List findByHQLCache(String paramString);

  public abstract List findByHQLCache(String paramString, Page paramPage);

  public abstract List findByNSQL(String paramString);

  public abstract List findByNSQL(String paramString, Page paramPage);

  public abstract List findByNSQL(String paramString, boolean paramBoolean, Page paramPage);

  public abstract int executeByNSQL(String paramString);

  public abstract void lock(T paramT, LockMode paramLockMode);

  public abstract void flush();
  
  //从Excel导入 
  public abstract String saveExcel(int choiceTo,String filepath) throws  Exception;
  //导出到Excel
  public abstract String toExcel(int num,List<T> list);
}

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.dao.CommonsDaoIface
 * JD-Core Version:    0.6.2
 */