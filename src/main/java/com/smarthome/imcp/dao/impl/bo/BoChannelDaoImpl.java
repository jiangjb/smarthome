/*    */ package com.smarthome.imcp.dao.impl.bo;
/*    */ 
/*    */ import com.smarthome.imcp.dao.CommonsDaoImpl;
/*    */ import com.smarthome.imcp.dao.bo.BoChannelDaoIface;
/*    */ import com.smarthome.imcp.dao.model.bo.BoChannel;
/*    */ import java.io.Serializable;
/*    */ import org.springframework.stereotype.Service;
/*    */ 
/*    */ @Service("boChannelDao")
/*    */ public class BoChannelDaoImpl extends CommonsDaoImpl<BoChannel, Serializable>
/*    */   implements BoChannelDaoIface<BoChannel, Serializable>
/*    */ {
/*    */   public BoChannelDaoImpl()
/*    */   {
/* 20 */     super(BoChannel.class);
/*    */   }
/*    */ }

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.dao.impl.bo.BoChannelDaoImpl
 * JD-Core Version:    0.6.2
 */