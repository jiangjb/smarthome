/*    */ package com.smarthome.imcp.dao.impl.bo;

/*    */ import com.smarthome.imcp.dao.CommonsDaoImpl;
		 import com.smarthome.imcp.dao.bo.ModelDaoIface;
		 import com.smarthome.imcp.dao.model.bo.Model;
/*    */ import java.io.Serializable;
		 import java.util.List;
		 import org.hibernate.Query;
		 import org.hibernate.type.StandardBasicTypes;
		 import org.slf4j.Logger;
		 import org.slf4j.LoggerFactory;
/*    */ import org.springframework.stereotype.Service;

/*    */ @Service("modelDao")
/*    */ public class ModelDaoImpl extends CommonsDaoImpl<Model, Serializable>
/*    */   implements ModelDaoIface<Model, Serializable>
/*    */ {
/*    */   public ModelDaoImpl()
/*    */   {
/* 20 */     super(Model.class);
/*    */   }
			private static Logger logger = LoggerFactory.getLogger(ModelDaoImpl.class);
			@Override
			public List findAllType(int deviceId) {
				String sql="select id,m_label from model where device_id = ?";
				Query sqlQuery = getCurrentSession().createSQLQuery(sql)  //;// SQLQuery是hibernate用于支持原生sql的接口类
						.addScalar("id",StandardBasicTypes.INTEGER)
						.addScalar("m_label",StandardBasicTypes.STRING);
		        sqlQuery.setParameter(0, deviceId);
		        List result = sqlQuery.list();
		        logger.info("sqlQuery result:"+result);
		        return result;
			}
			@Override
			public List findByType(String label) {
				String sql="select id,m_label from model where m_label = ?";
				Query sqlQuery = getCurrentSession().createSQLQuery(sql)  //;// SQLQuery是hibernate用于支持原生sql的接口类
						.addScalar("id",StandardBasicTypes.INTEGER)
						.addScalar("m_label",StandardBasicTypes.STRING);
		        sqlQuery.setParameter(0, label);
		        List result = sqlQuery.list();
		        logger.info("findByType result:"+result);
		        return result;
			}
			@Override
			public String findTestCode(int id) {
				String sql="select f.format_string from model m,formats f where m.id = ?";
				Query sqlQuery = getCurrentSession().createSQLQuery(sql)  //;// SQLQuery是hibernate用于支持原生sql的接口类
						.addScalar("format_string",StandardBasicTypes.STRING);
		        sqlQuery.setParameter(0, id);
		        List<String> result = sqlQuery.list();
		        logger.info("findTestCode result:"+result);
		        return result.get(0);
			}
			@Override
			public String findByLabel(String label) {
				String sql="select m_format_id from model where m_label = ?";
				Query sqlQuery = getCurrentSession().createSQLQuery(sql)  //;// SQLQuery是hibernate用于支持原生sql的接口类
						.addScalar("m_format_id",StandardBasicTypes.INTEGER);
		        sqlQuery.setParameter(0, label);
		        List<String> result = sqlQuery.list();
		        logger.info("findByLabel result:"+result);
		        return result.get(0);
			}
			@Override
			public String findModelidByLabel(String label) {
				String sql="select device_id,m_keyfile from model where m_label = ?";
				Query sqlQuery = getCurrentSession().createSQLQuery(sql)  //;// SQLQuery是hibernate用于支持原生sql的接口类
						.addScalar("device_id",StandardBasicTypes.INTEGER)
						.addScalar("m_keyfile",StandardBasicTypes.STRING);
		        sqlQuery.setParameter(0, label);
		        List list = sqlQuery.list();
		        int deviceid=(int) list.get(0);
		        String result="";
		        if(deviceid < 10) {
		        	result="0"+deviceid+list.get(1);
		        }else {
		        	result=deviceid+""+list.get(1);
		        }
		        logger.info("findModelidByLabel result:"+result);
		        return result;
			}
	}

