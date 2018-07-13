/*    */ package com.smarthome.imcp.service.impl.bo;
 
		 import com.smarthome.imcp.dao.bo.BrandsDaoIface;
		 import com.smarthome.imcp.dao.model.bo.Brands;
/*    */ import com.smarthome.imcp.service.AbstractBasicService;
		 import com.smarthome.imcp.service.bo.BrandsServiceIface;
/*    */ import java.io.Serializable;
/*    */ import java.util.List;
/*    */ import org.hibernate.criterion.DetachedCriteria;
/*    */ import org.hibernate.criterion.Restrictions;
/*    */ import org.springframework.beans.factory.annotation.Autowired;
/*    */ import org.springframework.stereotype.Service;
/*    */ 
/*    */ @Service("brandsService")
/*    */ public class BrandsServiceImpl extends AbstractBasicService<Brands, Serializable>
/*    */   implements BrandsServiceIface<Brands, Serializable>
/*    */ {
			@Autowired
	        private BrandsDaoIface<Brands, Serializable> brandsDao;

			@Override
			public List<Brands> findAll(int deviceId) {
				DetachedCriteria criteria = DetachedCriteria.forClass(Brands.class);
		        criteria.add(Restrictions.eq("device_id", deviceId));
				 
				List<Brands> list = this.brandsDao.findByCriteria(criteria);
			    if ((list == null) || (list.isEmpty())) {
			       return null;
			    }
				return list;
			}

			
   	
		}
