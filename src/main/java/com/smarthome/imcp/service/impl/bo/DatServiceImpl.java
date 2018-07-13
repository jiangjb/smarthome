/*    */ package com.smarthome.imcp.service.impl.bo;
 
		 import com.smarthome.imcp.dao.bo.DatDaoIface;
		 import com.smarthome.imcp.dao.model.bo.Dat;
/*    */ import com.smarthome.imcp.service.AbstractBasicService;
	 	 import com.smarthome.imcp.service.bo.DatServiceIface;
/*    */ import java.io.Serializable;
/*    */ import java.util.List;
		 import org.hibernate.Query;
/*    */ import org.hibernate.criterion.DetachedCriteria;
/*    */ import org.hibernate.criterion.Restrictions;
/*    */ import org.springframework.beans.factory.annotation.Autowired;
/*    */ import org.springframework.stereotype.Service;
/*    */ 
/*    */ @Service("datService")
/*    */ public class DatServiceImpl extends AbstractBasicService<Dat, Serializable>
/*    */   implements DatServiceIface<Dat, Serializable>
/*    */ {
			@Autowired
	        private DatDaoIface<Dat, Serializable> datDao;

			@Override
			public List<String> findByModelid(String modelid) {
				return this.datDao.findByModelid(modelid);
			}


		}
