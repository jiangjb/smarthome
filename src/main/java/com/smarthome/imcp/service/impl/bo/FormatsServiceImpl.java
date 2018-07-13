/*    */ package com.smarthome.imcp.service.impl.bo;
 
		 import com.smarthome.imcp.dao.bo.FormatsDaoIface;
		 import com.smarthome.imcp.dao.model.bo.Formats;
/*    */ import com.smarthome.imcp.service.AbstractBasicService;
		 import com.smarthome.imcp.service.bo.FormatsServiceIface;
/*    */ import java.io.Serializable;
/*    */ import java.util.List;
		 import org.hibernate.Query;
/*    */ import org.hibernate.criterion.DetachedCriteria;
/*    */ import org.hibernate.criterion.Restrictions;
/*    */ import org.springframework.beans.factory.annotation.Autowired;
/*    */ import org.springframework.stereotype.Service;
/*    */ 
/*    */ @Service("formatsService")
/*    */ public class FormatsServiceImpl extends AbstractBasicService<Formats, Serializable>
/*    */   implements FormatsServiceIface<Formats, Serializable>
/*    */ {
			@Autowired
	        private FormatsDaoIface<Formats, Serializable> formatsDao;

		}
