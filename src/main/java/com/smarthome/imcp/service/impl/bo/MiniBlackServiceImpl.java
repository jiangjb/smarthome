/*    */ package com.smarthome.imcp.service.impl.bo;
 
		 import com.smarthome.imcp.dao.bo.MiniBlackDaoIface;
		 import com.smarthome.imcp.dao.model.bo.MiniBlack;
/*    */ import com.smarthome.imcp.service.AbstractBasicService;
		 import com.smarthome.imcp.service.bo.MiniBlackServiceIface;
/*    */ import java.io.Serializable;
		 import java.util.List;
		 import org.hibernate.criterion.DetachedCriteria;
		 import org.hibernate.criterion.Restrictions;
/*    */ import org.springframework.beans.factory.annotation.Autowired;
/*    */ import org.springframework.stereotype.Service;
/*    */ 
/*    */ @Service("miniBlackService")
/*    */ public class MiniBlackServiceImpl extends AbstractBasicService<MiniBlack, Serializable>
/*    */   implements MiniBlackServiceIface<MiniBlack, Serializable>
/*    */ {
			@Autowired
	        private MiniBlackDaoIface<MiniBlack, Serializable> miniBlackDao;

			public MiniBlack save(MiniBlack model)
			{
				if (chkSaveValid(model))
				{
					this.miniBlackDao.save(model);
				}
				return model;
			}
			public MiniBlack update(MiniBlack model)
			{
				if (chkUpdateValid(model)) {
					this.miniBlackDao.update(model);
				}
				return model;
			}
			
			public MiniBlack findByKey(Serializable id)
			{
				MiniBlack model = (MiniBlack)this.miniBlackDao.findById(id);
				return model;
			}
			
			public void deleteByKey(String id)
			{
				if ((id == null) || ("".equals(id)))
					return;
				if (chkDeleteValid(id))
					this.miniBlackDao.deleteByKey(Integer.valueOf(id));
			}
			
			public MiniBlack delete(MiniBlack model)
			{
				if (chkUpdateValid(model)) {
					this.miniBlackDao.delete(model);
				}
				return model;
			}
			
			@Override
			public List<MiniBlack> findByUserId(int userid) {
				DetachedCriteria criteria = DetachedCriteria.forClass(MiniBlack.class);
				criteria.add(Restrictions.eq("userid", userid));
				List<MiniBlack> list = this.miniBlackDao.findByCriteria(criteria);
				if ((list == null) || (list.isEmpty())) {
					return null;
				}
				return list;
			}

			@Override
			public MiniBlack findByMac(String mac) {
				DetachedCriteria criteria = DetachedCriteria.forClass(MiniBlack.class);
				criteria.add(Restrictions.eq("macAddr", mac));
				List<MiniBlack> list = this.miniBlackDao.findByCriteria(criteria);
				if ((list == null) || (list.isEmpty())) {
					return null;
				}
				return list.get(0);
			}
			@Override
			public List<MiniBlack> findAllMac() {
				DetachedCriteria criteria = DetachedCriteria.forClass(MiniBlack.class);
				List<MiniBlack> list = this.miniBlackDao.findByCriteria(criteria);
				return list;
			}

		}
