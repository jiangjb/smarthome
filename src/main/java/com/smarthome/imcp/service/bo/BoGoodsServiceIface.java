package com.smarthome.imcp.service.bo;

import com.smarthome.imcp.dao.model.bo.BoGoods;
import com.smarthome.imcp.service.BasicServiceIface;
import java.io.Serializable;
import java.util.List;

public abstract interface BoGoodsServiceIface<T, PK extends Serializable> extends BasicServiceIface<T, PK>
{
  public abstract BoGoods findById(Integer paramInteger);

  public abstract List<BoGoods> getList();

  public abstract List<BoGoods> getListbyId(Integer paramInteger);

  public abstract boolean chkDeleteValid(T paramT);

  public abstract BoGoods delete(T paramT);
}

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.service.bo.BoGoodsServiceIface
 * JD-Core Version:    0.6.2
 */