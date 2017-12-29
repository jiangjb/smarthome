package com.smarthome.imcp.service.bo;

import com.smarthome.imcp.common.Page;
import com.smarthome.imcp.dao.criteria.SearchCriteria;
import com.smarthome.imcp.dao.model.bo.BoReportNum;
import java.io.Serializable;
import java.util.List;

public abstract interface BoReportNumServiceIface
{
  public abstract List<BoReportNum> getList(SearchCriteria paramSearchCriteria, Page paramPage);

  public abstract int doPlusNum(String paramString, int paramInt1, int paramInt2);

  public abstract void doInitNum(String paramString, int paramInt1, int paramInt2);

  public abstract BoReportNum doFindByDeviceCode(String paramString, int paramInt1, int paramInt2);

  public abstract boolean chkSaveValid(BoReportNum paramBoReportNum);

  public abstract BoReportNum save(BoReportNum paramBoReportNum);

  public abstract BoReportNum findByKey(Serializable paramSerializable);

  public abstract boolean chkUpdateValid(BoReportNum paramBoReportNum);

  public abstract BoReportNum update(BoReportNum paramBoReportNum);

  public abstract boolean chkDeleteValid(String paramString);

  public abstract void deleteByKey(String paramString);

  public abstract void deleteByKeys(String paramString);
}

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.service.bo.BoReportNumServiceIface
 * JD-Core Version:    0.6.2
 */