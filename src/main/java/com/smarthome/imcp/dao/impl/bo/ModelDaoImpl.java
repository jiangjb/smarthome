/*    */ package com.smarthome.imcp.dao.impl.bo;

/*    */ import com.smarthome.imcp.dao.CommonsDaoImpl;
		 import com.smarthome.imcp.dao.bo.ModelDaoIface;
		 import com.smarthome.imcp.dao.model.bo.Model;
/*    */ import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
				String sql="select id,m_label,m_search_string,m_key_squency,m_code from model where device_id = ?";
				Query sqlQuery = getCurrentSession().createSQLQuery(sql)  //;// SQLQuery是hibernate用于支持原生sql的接口类
						.addScalar("id",StandardBasicTypes.INTEGER)
						.addScalar("m_label",StandardBasicTypes.STRING)
						.addScalar("m_search_string",StandardBasicTypes.STRING)
						.addScalar("m_key_squency",StandardBasicTypes.STRING)
						.addScalar("m_code",StandardBasicTypes.STRING);
		        sqlQuery.setParameter(0, deviceId);
		        List result = sqlQuery.list();//取的是[[1,"1234"],[2,"2345"],.......] 集合里面还有一个集合
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
			public List findTestCode(int id) {
				String sql="select f.fid,f.format_string from model m,formats f where m.m_format_id = f.fid and m.id = ?";
				Query sqlQuery = getCurrentSession().createSQLQuery(sql)  //;// SQLQuery是hibernate用于支持原生sql的接口类
						.addScalar("fid",StandardBasicTypes.INTEGER)
						.addScalar("format_string",StandardBasicTypes.STRING);
		        sqlQuery.setParameter(0, id);
		        List list = sqlQuery.list();
		        return list;
			}
			
			@Override
			public int findByLabel(String label) {
				String sql="select m_format_id from model where m_label = ?";
				Query sqlQuery = getCurrentSession().createSQLQuery(sql)  //;// SQLQuery是hibernate用于支持原生sql的接口类
						.addScalar("m_format_id",StandardBasicTypes.INTEGER);
		        sqlQuery.setParameter(0, label);
		        List result = sqlQuery.list();
		        logger.info("findByLabel result:"+result);
		        return (int) result.get(0);
			}
			
			@Override
			public String findModelidByLabel(String label) {
				String result="";
				int device_id=0;
				String m_keyfile="";
				String sql="select device_id,m_keyfile from model where m_label = ?";
				Query sqlQuery = getCurrentSession().createSQLQuery(sql)  //;// SQLQuery是hibernate用于支持原生sql的接口类
						.addScalar("device_id",StandardBasicTypes.INTEGER)
						.addScalar("m_keyfile",StandardBasicTypes.STRING);
		        sqlQuery.setParameter(0, label);
		        List<Object[]> list = sqlQuery.list();
		        for(Object[] obj:list){
		        	device_id=(int) obj[0];
		        	logger.info("device_id:"+device_id);
		        	m_keyfile=(String) obj[1];
		        	logger.info("m_keyfile:"+m_keyfile);
		        	break;
				 }
		        //这个取决于modelid字段
		        if(device_id < 10) {
		        	String devId="0"+device_id;
		        	if(m_keyfile.length() == 1) {
		        		result=devId+"000"+m_keyfile;
		        	}else if(m_keyfile.length() == 2) {
		        		result=devId+"00"+m_keyfile;
		        	}else if(m_keyfile.length() == 3) {
		        		result=devId+"0"+m_keyfile;
		        	}else {
		        		result=devId+m_keyfile;
		        	}
		        }else {
		        	if(m_keyfile.length() == 1) {
		        		result=device_id+"000"+m_keyfile;
		        	}else if(m_keyfile.length() == 2) {
		        		result=device_id+"00"+m_keyfile;
		        	}else if(m_keyfile.length() == 3) {
		        		result=device_id+"0"+m_keyfile;
		        	}else {
		        		result=device_id+m_keyfile;
		        	}
		        }
		        logger.info("findModelidByLabel result:"+result);
		        return result;
			}
			
			@Override
			public List findFormatIdByMcode(int device_id , String m_code) {
				String sql="select m_label,m_format_id,m_key_squency from model where  device_id = ? and m_code = ?";
				Query sqlQuery = getCurrentSession().createSQLQuery(sql)  //;// SQLQuery是hibernate用于支持原生sql的接口类
						.addScalar("m_label",StandardBasicTypes.STRING)
						.addScalar("m_format_id",StandardBasicTypes.INTEGER)
						.addScalar("m_key_squency",StandardBasicTypes.INTEGER);
				sqlQuery.setParameter(0, device_id);
		        sqlQuery.setParameter(1, m_code);
		        List list = sqlQuery.list();
				return list;
			}

			@Override
			public int findKSByfid(int fid) {
				String sql="select m_key_squency from model where  m_format_id = ?";
				Query sqlQuery = getCurrentSession().createSQLQuery(sql)  //;// SQLQuery是hibernate用于支持原生sql的接口类
						.addScalar("m_format_id",StandardBasicTypes.INTEGER);
				sqlQuery.setParameter(0, fid);
		        List list = sqlQuery.list();
				return (int) list.get(0);
			}

			@Override
			public List findModelidByid(int labelId) {
				String sql="select device_id,m_keyfile,m_label from model where  id = ?";
				Query sqlQuery = getCurrentSession().createSQLQuery(sql)  //;// SQLQuery是hibernate用于支持原生sql的接口类
						.addScalar("device_id",StandardBasicTypes.INTEGER)
						.addScalar("m_keyfile",StandardBasicTypes.STRING)
						.addScalar("m_label",StandardBasicTypes.STRING);
				sqlQuery.setParameter(0, labelId);
		        List list = sqlQuery.list();
				return list;
			}

			@Override
			public List findModelInfo(int device_id) {
				String sql="select id,m_format_id,m_rank from model where device_id = ?";
				Query sqlQuery = getCurrentSession().createSQLQuery(sql)  //;// SQLQuery是hibernate用于支持原生sql的接口类
						.addScalar("id",StandardBasicTypes.INTEGER)
						.addScalar("m_format_id",StandardBasicTypes.INTEGER)
						.addScalar("m_rank",StandardBasicTypes.STRING);
				sqlQuery.setParameter(0, device_id);
		        List list = sqlQuery.list();
				return list;
			}
	}

