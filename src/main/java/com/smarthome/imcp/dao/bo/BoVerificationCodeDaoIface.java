package com.smarthome.imcp.dao.bo;

import com.smarthome.imcp.dao.CommonsDaoIface;
import java.io.Serializable;

public abstract interface BoVerificationCodeDaoIface<T, PK extends Serializable> extends CommonsDaoIface<T, PK>
{
  public abstract void executeVerificationCodeRecycle(int paramInt);
}

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.dao.bo.BoVerificationCodeDaoIface
 * JD-Core Version:    0.6.2
 */