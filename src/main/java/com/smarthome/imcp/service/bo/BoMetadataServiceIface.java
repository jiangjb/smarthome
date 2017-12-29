package com.smarthome.imcp.service.bo;

import com.smarthome.imcp.service.BasicServiceIface;
import java.io.Serializable;

public abstract interface BoMetadataServiceIface<T, PK extends Serializable> extends BasicServiceIface<T, PK>
{
  public abstract T getMetadataByKeyName(String paramString);
}

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.service.bo.BoMetadataServiceIface
 * JD-Core Version:    0.6.2
 */