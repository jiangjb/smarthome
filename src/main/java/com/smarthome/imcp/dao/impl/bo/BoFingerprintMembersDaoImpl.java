/*    */ package com.smarthome.imcp.dao.impl.bo;
/*    */ 
/*    */ import com.smarthome.imcp.dao.CommonsDaoImpl;
/*    */ import com.smarthome.imcp.dao.bo.BoFingerprintMembersDaoIface;
/*    */ import com.smarthome.imcp.dao.model.bo.BoFingerprintMembers;
/*    */ import java.io.Serializable;
/*    */ import org.springframework.stereotype.Service;
/*    */ 
/*    */ @Service("boFingerprintMembersDao")
/*    */ public class BoFingerprintMembersDaoImpl extends CommonsDaoImpl<BoFingerprintMembers, Serializable>
/*    */   implements BoFingerprintMembersDaoIface<BoFingerprintMembers, Serializable>
/*    */ {
/*    */   public BoFingerprintMembersDaoImpl()
/*    */   {
/* 24 */     super(BoFingerprintMembers.class);
/*    */   }
/*    */ }

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.dao.impl.bo.BoFingerprintMembersDaoImpl
 * JD-Core Version:    0.6.2
 */