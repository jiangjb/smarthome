package com.smarthome.imcp.service.bo;

import com.smarthome.imcp.common.Page;
import com.smarthome.imcp.dao.model.bo.BoUnlockingPushRecord;
import com.smarthome.imcp.service.BasicServiceIface;
import java.io.Serializable;
import java.util.List;

public abstract interface BoUnlockingPushRecordServiceIface<T, PK extends Serializable> extends BasicServiceIface<T, PK>
{
  public abstract void insertPush(String paramString1, String paramString2, String paramString3, String paramString4, String paramString5);

  public abstract List<BoUnlockingPushRecord> getRecord(String paramString, Page paramPage);

  public abstract boolean chkDeleteValid(T paramT);

  public abstract BoUnlockingPushRecord delete(T paramT);
}

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.service.bo.BoUnlockingPushRecordServiceIface
 * JD-Core Version:    0.6.2
 */