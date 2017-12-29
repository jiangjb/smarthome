package com.smarthome.imcp.service.bo;

import com.smarthome.imcp.common.Page;
import com.smarthome.imcp.dao.model.bo.BoAlarmRecord;
import com.smarthome.imcp.service.BasicServiceIface;
import java.io.Serializable;
import java.util.List;

public abstract interface BoAlarmRecordServiceIface<T, PK extends Serializable> extends BasicServiceIface<T, PK>
{
  public abstract List<BoAlarmRecord> getAlarmRecordByUserCode(String paramString, Page paramPage);

  public abstract boolean chkDeleteValid(T paramT);

  public abstract BoAlarmRecord delete(T paramT);
}

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.service.bo.BoAlarmRecordServiceIface
 * JD-Core Version:    0.6.2
 */