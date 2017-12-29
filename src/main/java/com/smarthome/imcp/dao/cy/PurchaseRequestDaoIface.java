package com.smarthome.imcp.dao.cy;

import com.smarthome.imcp.common.Page;
import com.smarthome.imcp.dao.CommonsDaoIface;
import com.smarthome.imcp.dao.criteria.cy.SearchCriteriaPurchaseRequest;
import com.smarthome.imcp.dao.model.cy.PurchaseRequest;
import java.io.Serializable;
import java.util.List;

public abstract interface PurchaseRequestDaoIface<T, PK extends Serializable> extends CommonsDaoIface<T, PK>
{
  public abstract List<PurchaseRequest> getList(SearchCriteriaPurchaseRequest paramSearchCriteriaPurchaseRequest, Page paramPage);
}

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.dao.cy.PurchaseRequestDaoIface
 * JD-Core Version:    0.6.2
 */