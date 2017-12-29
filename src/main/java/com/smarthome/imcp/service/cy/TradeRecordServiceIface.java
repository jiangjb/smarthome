package com.smarthome.imcp.service.cy;

import com.smarthome.imcp.common.Page;
import com.smarthome.imcp.dao.criteria.SearchCriteria;
import com.smarthome.imcp.dao.model.cy.TradeRecord;
import java.io.Serializable;
import java.util.List;

public abstract interface TradeRecordServiceIface
{
  public abstract List<TradeRecord> getList(SearchCriteria paramSearchCriteria, Page paramPage);

  public abstract boolean chkSaveValid(TradeRecord paramTradeRecord);

  public abstract TradeRecord save(TradeRecord paramTradeRecord);

  public abstract TradeRecord findByKey(Serializable paramSerializable);

  public abstract boolean chkUpdateValid(TradeRecord paramTradeRecord);

  public abstract TradeRecord update(TradeRecord paramTradeRecord);

  public abstract boolean chkDeleteValid(String paramString);

  public abstract void deleteByKey(String paramString);

  public abstract void deleteByKeys(String paramString);

  public abstract List<TradeRecord> getListByUserId(int paramInt, Page paramPage);

  public abstract TradeRecord findByOrderNo(String paramString);
}

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.service.cy.TradeRecordServiceIface
 * JD-Core Version:    0.6.2
 */