package com.smarthome.imcp.dao.cy;

import com.smarthome.imcp.dao.CommonsDaoIface;
import com.smarthome.imcp.dao.model.cy.CrystalRate;
import java.io.Serializable;
import java.util.List;

public abstract interface CrystalRateDaoIface<T, PK extends Serializable> extends CommonsDaoIface<T, PK>
{
  public abstract List<CrystalRate> getList();
}

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.dao.cy.CrystalRateDaoIface
 * JD-Core Version:    0.6.2
 */