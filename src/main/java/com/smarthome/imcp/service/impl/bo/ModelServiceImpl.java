/*    */ package com.smarthome.imcp.service.impl.bo;
 
		 import com.smarthome.imcp.dao.bo.ModelDaoIface;
import com.smarthome.imcp.dao.model.bo.MiniBlack;
import com.smarthome.imcp.dao.model.bo.Model;
/*    */ import com.smarthome.imcp.service.AbstractBasicService;
		 import com.smarthome.imcp.service.bo.ModelServiceIface;
/*    */ import java.io.Serializable;
/*    */ import java.util.List;

import org.hibernate.Query;
/*    */ import org.hibernate.criterion.DetachedCriteria;
/*    */ import org.hibernate.criterion.Restrictions;
/*    */ import org.springframework.beans.factory.annotation.Autowired;
/*    */ import org.springframework.stereotype.Service;
/*    */ 
/*    */ @Service("modelService")
/*    */ public class ModelServiceImpl extends AbstractBasicService<Model, Serializable>
/*    */   implements ModelServiceIface<Model, Serializable>
/*    */ {
			@Autowired
	        private ModelDaoIface<Model, Serializable> modelDao;

			@Override
			public List findAllType(int deviceId) {
				return this.modelDao.findAllType(deviceId);
			}

			@Override
			public List findByType(String label) {
				return this.modelDao.findByType(label);
			}

			@Override
			public List findTestCode(int id,int device_id) {
				return this.modelDao.findTestCode(id,device_id);
			}

			@Override
			public int findByLabel(String label) {
				return this.modelDao.findByLabel(label);
			}

			@Override
			public String findModelidByLabel(String label) {
				return this.modelDao.findModelidByLabel(label);
			}

			@Override
			public List findFormatIdByMcode(int device_id , String m_code) {
				return this.modelDao.findFormatIdByMcode(device_id , m_code);
			}

			@Override
			public int findKSByfid(int fid) {
				return this.modelDao.findKSByfid(fid);
			}

			@Override
			public List findModelidByid(int labelId) {
				return this.modelDao.findModelidByid(labelId);
			}

			@Override
			public List findModelInfo(int device_id) {
				return this.modelDao.findModelInfo(device_id);
			}
			public Model findByKey(Serializable id)
			{
				Model model = (Model)this.modelDao.findById(id);
				return model;
			}

		}
