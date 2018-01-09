package com.smarthome.imcp.dao.system;

import com.smarthome.imcp.common.Page;
import com.smarthome.imcp.dao.CommonsDaoIface;
import com.smarthome.imcp.dao.criteria.system.SearchCriteriaUser;
import com.smarthome.imcp.dao.model.bo.BoUser;
import com.smarthome.imcp.dao.model.bo.BoUsersValidation;
import com.smarthome.imcp.dao.model.system.SysUser;
import java.io.Serializable;
import java.util.List;


public abstract interface SysUserDaoIface<T, PK extends Serializable> extends CommonsDaoIface<T, PK>
{
  public abstract List<SysUser> getList(SearchCriteriaUser paramSearchCriteriaUser, Page paramPage);

  public abstract boolean isExistsByUserIdLoginName(Integer paramInteger, String paramString);

  public abstract SysUser checkUser(String paramString1, String paramString2);

  public abstract void changePassword(Integer paramInteger, String paramString);

  public abstract int findByPhone(String userPhone);

  public abstract int findByEmail(String email);
  //用户管理-全部用户
  public abstract List<BoUser> findBoUsers();
  //用户管理-登录验证码
  public abstract List<BoUsersValidation> findValidations();
  public abstract String addValidation(String userPhone,String verificationCode);
  public abstract String toExcel();
}

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.dao.system.SysUserDaoIface
 * JD-Core Version:    0.6.2
 */