package com.smarthome.imcp.service.bo;

import com.smarthome.imcp.common.Page;
import com.smarthome.imcp.dao.criteria.SearchCriteria;
import com.smarthome.imcp.dao.model.bo.BoFriend;
import java.io.Serializable;
import java.util.List;

public abstract interface BoFriendServiceIface
{
  public abstract BoFriend findByUserFriendId(int paramInt1, int paramInt2);

  public abstract List<BoFriend> getList(SearchCriteria paramSearchCriteria, Page paramPage);

  public abstract BoFriend save(BoFriend paramBoFriend);

  public abstract List<BoFriend> getListByUserId(int paramInt);

  public abstract BoFriend findByKey(Serializable paramSerializable);

  public abstract BoFriend update(BoFriend paramBoFriend);

  public abstract void deleteByKey(String paramString);

  public abstract void deleteByKeys(String paramString);

  public abstract boolean chkSaveValid(BoFriend paramBoFriend);

  public abstract boolean chkUpdateValid(BoFriend paramBoFriend);

  public abstract boolean chkDeleteValid(String paramString);
}

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.service.bo.BoFriendServiceIface
 * JD-Core Version:    0.6.2
 */