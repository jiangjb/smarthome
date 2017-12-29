package com.smarthome.imcp.service.bo;

import com.smarthome.imcp.dao.model.bo.BoDevicePlan;
import com.smarthome.imcp.service.BasicServiceIface;
import java.io.Serializable;
import java.util.List;

public abstract interface BoDevicePlanServiceIface<T, PK extends Serializable> extends BasicServiceIface<T, PK>
{
  public abstract BoDevicePlan getInitPlan(String paramString);

  public abstract List<T> getCurrentPlanByDevice(String paramString);
}

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.service.bo.BoDevicePlanServiceIface
 * JD-Core Version:    0.6.2
 */