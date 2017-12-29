package com.smarthome.imcp.common;

public class RegConstant
{
  public static final String PHONE_REG = "^(13[0-9]{9})|(17(0|[6-8])[0-9]{8})|(18[0-9]{9})|(15[0-35-9][0-9]{8})$";
  public static final String EMAIL_REG = "\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*";
  public static final String FIFTEEN_IDENTITY_CARD_REG = "^[1-9]\\d{7}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{3}$";
  public static final String EIGHTEEN_IDENTITY_CARD_REG = "^[1-9]\\d{5}[1-9]\\d{3}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{3}([0-9]|X)$";
  public static final String REALNAME_REG = "^[一-龥]{2,5}$";
  public static final String BACKSTAGE_PASSWORD_REG = "^(?=.*\\d.*)(?=.*[a-zA-Z].*)(?=.*[-`~!@#$%^&*()_+\\|\\\\=,./?><\\{\\}\\[\\]].*).*$";
  public static final String URL_REG = "http(s)?://([\\w-]+\\.)+[\\w-]+(/[\\w- ./?%&=]*)?";
  public static final String NUMBER_REG = "[^0-9]";
}

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.common.RegConstant
 * JD-Core Version:    0.6.2
 */