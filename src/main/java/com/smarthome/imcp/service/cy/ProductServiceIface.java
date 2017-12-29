package com.smarthome.imcp.service.cy;

import com.smarthome.imcp.common.Page;
import com.smarthome.imcp.dao.criteria.SearchCriteria;
import com.smarthome.imcp.dao.model.cy.Product;
import java.io.Serializable;
import java.util.List;

public abstract interface ProductServiceIface
{
  public abstract List<Product> getList(SearchCriteria paramSearchCriteria, Page paramPage);

  public abstract boolean chkSaveValid(Product paramProduct);

  public abstract Product save(Product paramProduct);

  public abstract Product findByKey(Serializable paramSerializable);

  public abstract boolean chkUpdateValid(Product paramProduct);

  public abstract Product update(Product paramProduct);

  public abstract boolean chkDeleteValid(String paramString);

  public abstract void deleteByKey(String paramString);

  public abstract void deleteByKeys(String paramString);
}

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.service.cy.ProductServiceIface
 * JD-Core Version:    0.6.2
 */