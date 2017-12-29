package com.smarthome.imcp.service.bo;

import com.smarthome.imcp.dao.model.bo.BoUser;
import com.smarthome.imcp.dao.model.bo.BoUsers;
import com.smarthome.imcp.dao.model.bo.BoUsersValidation;
import com.smarthome.imcp.service.BasicServiceIface;
import java.io.Serializable;
import java.util.List;

public abstract interface BoUserServiceIface<T, PK extends Serializable> extends BasicServiceIface<T, PK>
{
  public abstract List<BoUser> getAllList();

  public abstract BoUser findByUserCode(String paramString);

  public abstract BoUser findByUserPhone(Integer paramInteger, String paramString);

  public abstract BoUser findByUserPhonePwd(String paramString1, String paramString2);

  public abstract BoUser findByUserPhonePwd(Integer paramInteger, String paramString1, String paramString2);

  public abstract void updateUserIP(Integer paramInteger, String paramString1, String paramString2, String paramString3);

  public abstract boolean validateUserPassword(String paramString1, String paramString2);

}

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.service.bo.BoUserServiceIface
 * JD-Core Version:    0.6.2
 */