/*    */ package com.smarthome.imcp.dao.impl.bo;

/*    */ import com.smarthome.imcp.dao.CommonsDaoImpl;
		 import com.smarthome.imcp.dao.bo.BrandsDaoIface;
		 import com.smarthome.imcp.dao.model.bo.Brands;
/*    */ import java.io.Serializable;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.type.StandardBasicTypes;
/*    */ import org.springframework.stereotype.Service;

/*    */ @Service("brandsDao")
/*    */ public class BrandsDaoImpl extends CommonsDaoImpl<Brands, Serializable>
/*    */   implements BrandsDaoIface<Brands, Serializable>
/*    */ {
/*    */   public BrandsDaoImpl()
/*    */   {
/* 20 */     super(Brands.class);
/*    */   }

			@Override
			public String findModelByid(int id) {
				String sql="select model_list from brands where id = ?";
				Query sqlQuery = getCurrentSession().createSQLQuery(sql)  // SQLQuery是hibernate用于支持原生sql的接口类
						.addScalar("model_list",StandardBasicTypes.STRING);
		        sqlQuery.setParameter(0, id);
		        List list = sqlQuery.list();
				return (String) list.get(0);
			}

			@Override
			public List findAllBrands(int deviceId) {
				String sql="select id,brandname,ebrandname from brands where device_id = ? order by brandname";
				Query sqlQuery = getCurrentSession().createSQLQuery(sql)  //SQLQuery是hibernate用于支持原生sql的接口类
						.addScalar("id",StandardBasicTypes.INTEGER)
						.addScalar("brandname",StandardBasicTypes.STRING)
						.addScalar("ebrandname",StandardBasicTypes.STRING);
		        sqlQuery.setParameter(0, deviceId);
		        List list = sqlQuery.list();
				return list;
			}

		}

