/*    */ package com.smarthome.imcp.dao.impl.bo;
/*    */ 
/*    */ import com.smarthome.imcp.common.GlobalMethod;
/*    */ import com.smarthome.imcp.common.Page;
/*    */ import com.smarthome.imcp.dao.CommonsDaoImpl;
/*    */ import com.smarthome.imcp.dao.bo.BoGoodsDaoIface;
/*    */ import com.smarthome.imcp.dao.criteria.smarthome.SearchCriteriaGoods;
/*    */ import com.smarthome.imcp.dao.model.bo.BoGoods;
/*    */ import java.io.Serializable;
/*    */ import java.util.List;
/*    */ import org.apache.commons.lang.StringUtils;
/*    */ import org.hibernate.criterion.DetachedCriteria;
/*    */ import org.hibernate.criterion.MatchMode;
/*    */ import org.hibernate.criterion.Restrictions;
/*    */ import org.springframework.stereotype.Service;
/*    */ 
/*    */ @Service("boGoodsDao")
/*    */ public class BoGoodsDaoImpl extends CommonsDaoImpl<BoGoods, Serializable>
/*    */   implements BoGoodsDaoIface<BoGoods, Serializable>
/*    */ {
/*    */   public BoGoodsDaoImpl()
/*    */   {
/* 25 */     super(BoGoods.class);
/*    */   }
/*    */ 
/*    */   public List<BoGoods> getList(SearchCriteriaGoods searchCriteriaUser)
/*    */   {
/* 31 */     DetachedCriteria criteria = DetachedCriteria.forClass(BoGoods.class);
/* 32 */     GlobalMethod.isNullorEmpty(searchCriteriaUser);
/*    */ 
/* 37 */     return findByCriteria(criteria);
/*    */   }
/*    */ 
/*    */   public List<BoGoods> getList(SearchCriteriaGoods searchCriteriaUser, Page page)
/*    */   {
/* 43 */     DetachedCriteria criteria = DetachedCriteria.forClass(BoGoods.class);
/* 44 */     if (!GlobalMethod.isNullorEmpty(searchCriteriaUser))
/*    */     {
/* 46 */       if (StringUtils.isNotEmpty(searchCriteriaUser.getGoodsTitle())) {
/* 47 */         criteria.add(Restrictions.like("goodsTitle", searchCriteriaUser.getGoodsTitle(), MatchMode.ANYWHERE));
/*    */       }
/*    */ 
/*    */     }
/*    */ 
/* 56 */     return findByCriteria(criteria, page);
/*    */   }
/*    */ }

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.dao.impl.bo.BoGoodsDaoImpl
 * JD-Core Version:    0.6.2
 */