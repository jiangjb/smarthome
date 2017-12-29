package com.smarthome.imcp.service.cy;

import com.smarthome.imcp.common.Page;
import com.smarthome.imcp.dao.model.cy.Plan;
import java.io.Serializable;
import java.util.List;

public abstract interface PlanServiceIface
{
  public abstract List<Plan> getList(Page paramPage);

  public abstract List<Plan> getAllList();

  public abstract void bindPlan(String paramString1, String paramString2);

  public abstract Plan save(Plan paramPlan);

  public abstract List<String> chargeDailyFee();

  public abstract Plan findByKey(Serializable paramSerializable);

  public abstract boolean chkDeleteValid(String paramString);

  public abstract void deleteByKey(String paramString);

  public abstract void deleteByKeys(String paramString);

  public abstract Plan update(Plan paramPlan);

  public abstract boolean chkUpdateValid(Plan paramPlan);

  public abstract Plan findByCode(String paramString);
}

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.service.cy.PlanServiceIface
 * JD-Core Version:    0.6.2
 */