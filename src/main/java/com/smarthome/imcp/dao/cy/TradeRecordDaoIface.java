package com.smarthome.imcp.dao.cy;

import com.smarthome.imcp.common.Page;
import com.smarthome.imcp.dao.CommonsDaoIface;
import com.smarthome.imcp.dao.criteria.cy.SearchCriteriaRecord;
import com.smarthome.imcp.dao.model.cy.TradeRecord;
import java.io.Serializable;
import java.util.List;

public abstract interface TradeRecordDaoIface<T, PK extends Serializable> extends CommonsDaoIface<T, PK>
{
  public abstract List<TradeRecord> getList(SearchCriteriaRecord paramSearchCriteriaRecord, Page paramPage);

  public abstract List<TradeRecord> getListByUserId(int paramInt, Page paramPage);

  public abstract TradeRecord findByOrderNo(String paramString);
}

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.dao.cy.TradeRecordDaoIface
 * JD-Core Version:    0.6.2
 */