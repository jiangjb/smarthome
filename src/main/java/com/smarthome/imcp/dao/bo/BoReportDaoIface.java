package com.smarthome.imcp.dao.bo;

import com.smarthome.imcp.common.Page;
import com.smarthome.imcp.dao.CommonsDaoIface;
import com.smarthome.imcp.dao.criteria.bo.SearchCriteriaReport;
import java.io.Serializable;
import java.util.List;

public abstract interface BoReportDaoIface<T, PK extends Serializable> extends CommonsDaoIface<T, PK>
{
  public abstract List<T> getList(SearchCriteriaReport paramSearchCriteriaReport, Page paramPage);
}

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.dao.bo.BoReportDaoIface
 * JD-Core Version:    0.6.2
 */