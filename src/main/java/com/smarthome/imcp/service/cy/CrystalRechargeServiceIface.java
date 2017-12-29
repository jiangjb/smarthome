package com.smarthome.imcp.service.cy;

import com.smarthome.imcp.common.Page;
import com.smarthome.imcp.dao.criteria.SearchCriteria;
import com.smarthome.imcp.dao.model.cy.CrystalRecharge;
import java.io.Serializable;
import java.util.List;

public abstract interface CrystalRechargeServiceIface
{
  public abstract CrystalRecharge findByOrderNo(String paramString);

  public abstract List<CrystalRecharge> getList(SearchCriteria paramSearchCriteria, Page paramPage);

  public abstract boolean chkSaveValid(CrystalRecharge paramCrystalRecharge);

  public abstract CrystalRecharge save(CrystalRecharge paramCrystalRecharge);

  public abstract CrystalRecharge findByKey(Serializable paramSerializable);

  public abstract boolean chkUpdateValid(CrystalRecharge paramCrystalRecharge);

  public abstract CrystalRecharge update(CrystalRecharge paramCrystalRecharge);

  public abstract boolean chkDeleteValid(String paramString);

  public abstract void deleteByKey(String paramString);

  public abstract void deleteByKeys(String paramString);
}

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.service.cy.CrystalRechargeServiceIface
 * JD-Core Version:    0.6.2
 */