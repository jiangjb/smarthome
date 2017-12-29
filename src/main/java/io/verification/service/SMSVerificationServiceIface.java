package io.verification.service;

public abstract interface SMSVerificationServiceIface
{
  public abstract String sendVerificationCode(String paramString1, String paramString2)
    throws Exception;

  public abstract String generateVerificationCode();
}

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     io.verification.service.SMSVerificationServiceIface
 * JD-Core Version:    0.6.2
 */