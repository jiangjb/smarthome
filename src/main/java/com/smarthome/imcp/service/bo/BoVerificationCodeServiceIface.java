package com.smarthome.imcp.service.bo;

import com.smarthome.imcp.dao.model.bo.BoVerificationCode;
import com.smarthome.imcp.service.BasicServiceIface;
import java.io.Serializable;

public abstract interface BoVerificationCodeServiceIface<T, PK extends Serializable> extends BasicServiceIface<T, PK>
{
  public abstract BoVerificationCode saveOrUpdate(BoVerificationCode paramBoVerificationCode);

  public abstract BoVerificationCode findByKey(String paramString);

  public abstract void executeVerificationRecycle(int paramInt);
}

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.service.bo.BoVerificationCodeServiceIface
 * JD-Core Version:    0.6.2
 */