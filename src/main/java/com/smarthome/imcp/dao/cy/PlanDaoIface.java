package com.smarthome.imcp.dao.cy;

import com.smarthome.imcp.common.Page;
import com.smarthome.imcp.dao.CommonsDaoIface;
import com.smarthome.imcp.dao.model.cy.Plan;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

public abstract interface PlanDaoIface<T, PK extends Serializable> extends CommonsDaoIface<T, PK>
{
  public abstract List<Plan> getList(Page paramPage);

  public abstract void bindPlan(String paramString1, String paramString2);

  public abstract void bindPlan(String paramString1, String paramString2, Date paramDate);

  public abstract List<String> deductDailyFee();

  public abstract Plan getCurrentPlanByDevice(String paramString);
}

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.dao.cy.PlanDaoIface
 * JD-Core Version:    0.6.2
 */