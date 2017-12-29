package com.smarthome.imcp.dao.bo;

import com.smarthome.imcp.common.Page;
import com.smarthome.imcp.dao.CommonsDaoIface;
import com.smarthome.imcp.dao.criteria.bo.SearchCriteriaAlarm;
import java.io.Serializable;
import java.util.List;

public abstract interface BoAlarmDaoIface<T, PK extends Serializable> extends CommonsDaoIface<T, PK>
{
  public abstract List<T> getList(SearchCriteriaAlarm paramSearchCriteriaAlarm, Page paramPage);
}

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.dao.bo.BoAlarmDaoIface
 * JD-Core Version:    0.6.2
 */