package com.smarthome.imcp.service.bo;

import com.smarthome.imcp.common.Page;
import com.smarthome.imcp.dao.model.bo.BoReport;
import com.smarthome.imcp.service.BasicServiceIface;
import java.io.Serializable;
import java.util.List;

public abstract interface BoReportServiceIface<T, PK extends Serializable> extends BasicServiceIface<T, PK>
{
  public abstract List<T> getListByDeviceCode(String paramString, int paramInt1, int paramInt2, Page paramPage);

  public abstract BoReport findByDeviceCode(String paramString, int paramInt1, int paramInt2);
}

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.service.bo.BoReportServiceIface
 * JD-Core Version:    0.6.2
 */