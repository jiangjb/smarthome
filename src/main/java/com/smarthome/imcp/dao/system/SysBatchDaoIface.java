package com.smarthome.imcp.dao.system;

import com.smarthome.imcp.dao.model.cy.DailyDeduction;
import java.util.List;

public abstract interface SysBatchDaoIface
{
  public abstract List<DailyDeduction> getDevicesDailyDeduction(int paramInt1, int paramInt2);

  public abstract void executePlanRefill();
}

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.dao.system.SysBatchDaoIface
 * JD-Core Version:    0.6.2
 */