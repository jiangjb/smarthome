package com.smarthome.imcp.dao.cy;

import com.smarthome.imcp.common.Page;
import com.smarthome.imcp.dao.CommonsDaoIface;
import com.smarthome.imcp.dao.criteria.cy.SearchCriteriaProduct;
import com.smarthome.imcp.dao.model.cy.Product;
import java.io.Serializable;
import java.util.List;

public abstract interface ProductDaoIface<T, PK extends Serializable> extends CommonsDaoIface<T, PK>
{
  public abstract List<Product> getList(SearchCriteriaProduct paramSearchCriteriaProduct, Page paramPage);
}

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.dao.cy.ProductDaoIface
 * JD-Core Version:    0.6.2
 */