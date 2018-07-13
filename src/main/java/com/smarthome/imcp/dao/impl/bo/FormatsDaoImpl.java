/*    */ package com.smarthome.imcp.dao.impl.bo;

/*    */ import com.smarthome.imcp.dao.CommonsDaoImpl;
		 import com.smarthome.imcp.dao.bo.FormatsDaoIface;
		 import com.smarthome.imcp.dao.model.bo.Formats;
/*    */ import java.io.Serializable;
		 import java.util.List;
		 import org.hibernate.Query;
		 import org.hibernate.type.StandardBasicTypes;
		 import org.slf4j.Logger;
		 import org.slf4j.LoggerFactory;
/*    */ import org.springframework.stereotype.Service;

/*    */ @Service("formatsDao")
/*    */ public class FormatsDaoImpl extends CommonsDaoImpl<Formats, Serializable>
/*    */   implements FormatsDaoIface<Formats, Serializable>
/*    */ {
/*    */   public FormatsDaoImpl()
/*    */   {
/* 20 */     super(Formats.class);
/*    */   }
		private static Logger logger = LoggerFactory.getLogger(FormatsDaoImpl.class);
//		@Override
//		public List<String> findByModelid(String modelid) {
//			String sql="select d.dat from dat d where d.modelid = ?";
//			Query sqlQuery = getCurrentSession().createSQLQuery(sql)  //;// SQLQuery是hibernate用于支持原生sql的接口类
//					.addScalar("dat",StandardBasicTypes.STRING);
//	        sqlQuery.setParameter(0, modelid);
//	        List list = sqlQuery.list();
//	        return list;
//		}
		
	}

