/*    */ package com.smarthome.imcp.dao.impl.bo;
/*    */ 
/*    */ import com.smarthome.imcp.dao.CommonsDaoImpl;
/*    */ import com.smarthome.imcp.dao.bo.BoMetadataDaoIface;
/*    */ import com.smarthome.imcp.dao.model.bo.BoMetadata;
/*    */ import java.io.Serializable;
/*    */ import org.springframework.stereotype.Service;
/*    */ 
/*    */ @Service("boMetadataDao")
/*    */ public class BoMetadataDaoImpl extends CommonsDaoImpl<BoMetadata, Serializable>
/*    */   implements BoMetadataDaoIface<BoMetadata, Serializable>
/*    */ {
/*    */   public BoMetadataDaoImpl()
/*    */   {
/* 16 */     super(BoMetadata.class);
/*    */   }
/*    */ }

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.dao.impl.bo.BoMetadataDaoImpl
 * JD-Core Version:    0.6.2
 */