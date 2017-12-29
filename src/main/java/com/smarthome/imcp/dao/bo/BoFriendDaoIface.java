package com.smarthome.imcp.dao.bo;

import com.smarthome.imcp.common.Page;
import com.smarthome.imcp.dao.CommonsDaoIface;
import com.smarthome.imcp.dao.criteria.bo.SearchCriteriaFriend;
import com.smarthome.imcp.dao.model.bo.BoFriend;
import java.io.Serializable;
import java.util.List;

public abstract interface BoFriendDaoIface<T, PK extends Serializable> extends CommonsDaoIface<T, PK>
{
  public abstract BoFriend findByUserFriendId(int paramInt1, int paramInt2);

  public abstract List<BoFriend> getList(SearchCriteriaFriend paramSearchCriteriaFriend, Page paramPage);

  public abstract List<BoFriend> getListByUserId(int paramInt);
}

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.dao.bo.BoFriendDaoIface
 * JD-Core Version:    0.6.2
 */