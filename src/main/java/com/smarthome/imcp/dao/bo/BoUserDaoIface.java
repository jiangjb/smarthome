package com.smarthome.imcp.dao.bo;

import com.smarthome.imcp.common.Page;
import com.smarthome.imcp.dao.CommonsDaoIface;
import com.smarthome.imcp.dao.criteria.bo.SearchCriteriaUser;
import com.smarthome.imcp.dao.model.bo.BoUser;
import com.smarthome.imcp.dao.model.bo.BoUsers;
import com.smarthome.imcp.dao.model.bo.BoUsersValidation;

import java.io.Serializable;
import java.util.List;

public abstract interface BoUserDaoIface<T, PK extends Serializable> extends CommonsDaoIface<T, PK>
{
  public abstract List<BoUser> getList(SearchCriteriaUser paramSearchCriteriaUser);

  public abstract List<BoUser> getList(SearchCriteriaUser paramSearchCriteriaUser, Page paramPage);

  public abstract List<BoUser> getAllList();
}

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.dao.bo.BoUserDaoIface
 * JD-Core Version:    0.6.2
 */