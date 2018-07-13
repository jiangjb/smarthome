/*    */ package com.smarthome.imcp.dao.impl.bo;

/*    */ import com.smarthome.imcp.dao.CommonsDaoImpl;
		 import com.smarthome.imcp.dao.bo.BrandsDaoIface;
		 import com.smarthome.imcp.dao.model.bo.Brands;
/*    */ import java.io.Serializable;
/*    */ import org.springframework.stereotype.Service;

/*    */ @Service("brandsDao")
/*    */ public class BrandsDaoImpl extends CommonsDaoImpl<Brands, Serializable>
/*    */   implements BrandsDaoIface<Brands, Serializable>
/*    */ {
/*    */   public BrandsDaoImpl()
/*    */   {
/* 20 */     super(Brands.class);
/*    */   }


		}

