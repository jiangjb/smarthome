package com.smarthome.imcp.service.bo;

import com.smarthome.imcp.dao.model.bo.BoUsers;
import com.smarthome.imcp.service.BasicServiceIface;
import java.io.Serializable;
import java.util.List;

public abstract interface BoUserssServiceIface<T, PK extends Serializable> extends BasicServiceIface<T, PK>
{
  public abstract List<BoUsers> getAllList();

  public abstract List<BoUsers> getByAuthorizeUserCode(String paramString);

  public abstract BoUsers findByUserPhone(String paramString);

  public abstract BoUsers findByUserEmail(String paramString);

  public abstract BoUsers findByUserPhonePwd(String paramString1, String paramString2);

  public abstract BoUsers findByUserUserCode(String paramString);

  public abstract List<BoUsers> findAllBoUsers();
  public abstract BoUsers delete(T paramT);//4-26
}

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.service.bo.BoUserssServiceIface
 * JD-Core Version:    0.6.2
 */