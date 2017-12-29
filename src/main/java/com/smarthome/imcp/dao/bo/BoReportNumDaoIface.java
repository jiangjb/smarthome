package com.smarthome.imcp.dao.bo;

import com.smarthome.imcp.common.Page;
import com.smarthome.imcp.dao.CommonsDaoIface;
import com.smarthome.imcp.dao.criteria.bo.SearchCriteriaNum;
import com.smarthome.imcp.dao.model.bo.BoReportNum;
import java.io.Serializable;
import java.util.List;

public abstract interface BoReportNumDaoIface<T, PK extends Serializable> extends CommonsDaoIface<T, PK>
{
  public abstract int update(int paramInt1, String paramString, int paramInt2, int paramInt3);

  public abstract BoReportNum findByDeviceCode(String paramString, int paramInt1, int paramInt2);

  public abstract List<BoReportNum> getList(SearchCriteriaNum paramSearchCriteriaNum, Page paramPage);
}

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.dao.bo.BoReportNumDaoIface
 * JD-Core Version:    0.6.2
 */