package com.smarthome.imcp.dao.bo;

import com.smarthome.imcp.common.Page;
import com.smarthome.imcp.dao.CommonsDaoIface;
import com.smarthome.imcp.dao.criteria.smarthome.SearchCriteriaGoods;
import com.smarthome.imcp.dao.model.bo.BoGoods;
import java.io.Serializable;
import java.util.List;

public abstract interface BoGoodsDaoIface<T, PK extends Serializable> extends CommonsDaoIface<T, PK>
{
  public abstract List<BoGoods> getList(SearchCriteriaGoods paramSearchCriteriaGoods);

  public abstract List<BoGoods> getList(SearchCriteriaGoods paramSearchCriteriaGoods, Page paramPage);
}

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.dao.bo.BoGoodsDaoIface
 * JD-Core Version:    0.6.2
 */