/*    */ package com.smarthome.imcp.dao.impl.bo;
/*    */ 
/*    */ import com.smarthome.imcp.dao.CommonsDaoImpl;
/*    */ import com.smarthome.imcp.dao.bo.BoInfraredPartDaoIface;
/*    */ import com.smarthome.imcp.dao.model.bo.BoInfraredPart;
import com.smarthome.imcp.dao.model.bo.BoUsers;

/*    */ import java.io.Serializable;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
/*    */ import org.springframework.stereotype.Service;
/*    */ 
/*    */ @Service("boInfraredPartDao")
/*    */ public class BoInfraredPartDaoImpl extends CommonsDaoImpl<BoInfraredPart, Serializable>
/*    */   implements BoInfraredPartDaoIface<BoInfraredPart, Serializable>
/*    */ {
/*    */   public BoInfraredPartDaoImpl()
/*    */   {
/* 18 */     super(BoInfraredPart.class);
/*    */   }
/*    */

		@Override
		public List<BoInfraredPart> getAllInfraredPart() {
			DetachedCriteria criteria = DetachedCriteria.forClass(BoInfraredPart.class);
			return findByCriteria(criteria);
		}
	}

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.dao.impl.bo.BoInfraredPartDaoImpl
 * JD-Core Version:    0.6.2
 */