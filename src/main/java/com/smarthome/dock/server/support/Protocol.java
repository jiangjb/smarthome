package com.smarthome.dock.server.support;

public abstract interface Protocol
{
  public static final String MK = "cityclud";
  public static final int SERVER_INIT_SEQUENCE = 1000;
  public static final int CLIENT_INIT_SEQUENCE = 10000;
  public static final long TIMEOUT_SEND = 350L;
  public static final long TIMEOUT_ALIVE = 60000L;
  public static final int MAX_RESEND = 2;
  public static final String CHARSET_DEFAULT = "UTF-8";
  public static final int MAX_PACKET_SIZE = 65535;
  public static final int LENGTH_FIELD_OFFSET = 0;
  public static final int LENGTH_FIELD_LENGTH = 4;
  public static final int PACKET_HEADER_SIZE = 44;
  public static final int PACKET_BODY_SIZE = 980;
  public static final int DEVID_LENGTH = 28;
  public static final int LENGTH_KEY = 8;
  public static final byte MARK = 94;
  public static final char MSG_DEV_REG = 'ꀀ';
  public static final char MSG_DEV_REG_RE = 'ꀁ';
  public static final char MSG_DEV_HEART_BEAT = 'ꀂ';
  public static final char MSG_DEV_HEART_BEAT_RE = 'ꀃ';
  public static final char MSG_CLI_QUERY = '뀀';
  public static final char MSG_CLI_QUERY_RE = '뀁';
  public static final char MSG_C2D_DATA_START = '쀀';
  public static final char MSG_C2D_DATA_END = '쿿';
  public static final char MSG_D2C_DATA_START = '퀀';
  public static final char MSG_D2C_DATA_END = '?';
  public static final char MSG_D2S_DATA_START = '';
  public static final char MSG_D2S_DATA_END = '';
  public static final char MSG_D2S_ALARM_START = '';
  public static final char MSG_D2S_ALARM_END = '￿';
  public static final char CMD_UNKNOWN = '\000';
  public static final char MSG_TYPE_DONGCHE = 'ŀ';
  public static final char MSG_TYPE_HEBEI = 'ɀ';
  public static final char MSG_TYPE_BAOXIAN = 'Ő';
}