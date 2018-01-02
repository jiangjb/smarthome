package com.smarthome.imcp.service.bo;

import com.smarthome.imcp.dao.model.bo.BoInfraredPart;
import com.smarthome.imcp.service.BasicServiceIface;
import java.io.Serializable;
import java.util.List;

public abstract interface BoInfraredPartServiceIface<T, PK extends Serializable> extends BasicServiceIface<T, PK>
{
  public abstract BoInfraredPart find(String paramString);

  public abstract boolean chkDeleteValid(T paramT);

  public abstract BoInfraredPart delete(T paramT);

  public abstract List<BoInfraredPart> getAllInfraredPart();

  public abstract String saveExcel(int choiceTo, String filepath);

  public abstract BoInfraredPart save(BoInfraredPart bohost);

  public abstract BoInfraredPart update(BoInfraredPart bohost);

}

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.service.bo.BoInfraredPartServiceIface
 * JD-Core Version:    0.6.2
 */