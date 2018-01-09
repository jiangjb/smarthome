package com.smarthome.imcp.service.system;

import com.smarthome.imcp.dao.model.bo.BoUser;
import com.smarthome.imcp.dao.model.bo.BoUsersValidation;
import com.smarthome.imcp.dao.model.system.SysUser;
import com.smarthome.imcp.dao.vo.system.UserChangePassword;
import com.smarthome.imcp.service.BasicServiceIface;
import java.io.Serializable;
import java.util.List;
import java.util.Set;

public abstract interface SysUserServiceIface<T, PK extends Serializable> extends BasicServiceIface<T, PK>
{
  public abstract T checkUser(String paramString1, String paramString2);

  public abstract void doChangePassword(UserChangePassword paramUserChangePassword);

  public abstract void doChangePasswordNoOld(UserChangePassword paramUserChangePassword);

  public abstract int findByUserPhone(String userPhone);//返回Userid

  public abstract int findByUserEmail(String email);

//  public abstract Set<String> getRoles(String userName);

//  public abstract Set<String> getPermissions(String userName);
//
  public abstract SysUser getSysUsersByName(String userName);

}

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.service.system.SysUserServiceIface
 * JD-Core Version:    0.6.2
 */