package com.smarthome.imcp.service.bo;

import com.smarthome.imcp.dao.model.bo.BoFingerprintMembers;
import com.smarthome.imcp.service.BasicServiceIface;
import java.io.Serializable;
import java.util.List;

public abstract interface BoFingerprintMembersServiceIface<T, PK extends Serializable> extends BasicServiceIface<T, PK>
{
  public abstract List<BoFingerprintMembers> get(String paramString);

  public abstract List gets(String paramString);

  public abstract List<BoFingerprintMembers> get(String paramString1, String paramString2);

  public abstract BoFingerprintMembers findFingerprint(String paramString1, String paramString2);

  public abstract BoFingerprintMembers findFingerprint(String paramString);

  public abstract boolean chkDeleteValid(T paramT);

  public abstract BoFingerprintMembers delete(T paramT);
}

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.service.bo.BoFingerprintMembersServiceIface
 * JD-Core Version:    0.6.2
 */