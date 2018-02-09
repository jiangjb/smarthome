/*      */ package com.smarthome.dock.server.helper;
/*      */ 
/*      */ import com.smarthome.dock.server.bean.DockUser;
/*      */ import com.smarthome.dock.server.packets.ErrorPacket;
/*      */ import com.smarthome.dock.server.packets.InPacket;
/*      */ import com.smarthome.dock.server.packets.OutPacket;
/*      */ import com.smarthome.dock.server.packets.in.CDataPacket;
/*      */ import com.smarthome.dock.server.packets.in.DDataPacket;
/*      */ import com.smarthome.dock.server.packets.in.LoginPacket;
/*      */ import com.smarthome.dock.server.packets.in.QueryPacket;
/*      */ import com.smarthome.dock.server.packets.in.SendDDataReplyPacket;
/*      */ import com.smarthome.dock.server.packets.in.alarm.Alarm0140Packet;
/*      */ import com.smarthome.dock.server.packets.in.alarm.Alarm0150Packet;
/*      */ import com.smarthome.dock.server.packets.in.alarm.Alarm0240Packet;
/*      */ import com.smarthome.dock.server.packets.in.report.Report0140Packet;
/*      */ import com.smarthome.dock.server.packets.in.report.Report0150Packet;
/*      */ import com.smarthome.dock.server.packets.in.report.Report0240Packet;
/*      */ import com.smarthome.dock.server.packets.out.DataReplyPacket;
/*      */ import com.smarthome.dock.server.packets.out.LoginReplyPacket;
/*      */ import com.smarthome.dock.server.packets.out.QueryReplyPacket;
/*      */ import com.smarthome.dock.server.packets.out.SendDDataPacket;
/*      */ import com.smarthome.dock.server.support.KeepAliveTrigger;
/*      */ import com.smarthome.dock.server.support.PacketProcessor;
/*      */ import com.smarthome.dock.server.util.StaticUtil;
		   import com.smarthome.dock.server.util.Util;
/*      */ import com.smarthome.imcp.common.GlobalMethod;
/*      */ import com.smarthome.imcp.controller.RequestJson;
/*      */ import com.smarthome.imcp.dao.model.bo.BoAirBindingPanel;
/*      */ import com.smarthome.imcp.dao.model.bo.BoAlarm;
/*      */ import com.smarthome.imcp.dao.model.bo.BoChannel;
/*      */ import com.smarthome.imcp.dao.model.bo.BoControlEnclosure;
/*      */ import com.smarthome.imcp.dao.model.bo.BoDevice;
/*      */ import com.smarthome.imcp.dao.model.bo.BoDeviceState;
/*      */ import com.smarthome.imcp.dao.model.bo.BoFingerprintMembers;
/*      */ import com.smarthome.imcp.dao.model.bo.BoHostDevice;
/*      */ import com.smarthome.imcp.dao.model.bo.BoLockPasswordManage;
/*      */ import com.smarthome.imcp.dao.model.bo.BoLockVerdict;
/*      */ import com.smarthome.imcp.dao.model.bo.BoModel;
/*      */ import com.smarthome.imcp.dao.model.bo.BoNetworkNumber;
/*      */ import com.smarthome.imcp.dao.model.bo.BoReport;
/*      */ import com.smarthome.imcp.dao.model.bo.BoResendVerification;
/*      */ import com.smarthome.imcp.dao.model.bo.BoSensor;
/*      */ import com.smarthome.imcp.dao.model.bo.BoUnlockingPushRecord;
/*      */ import com.smarthome.imcp.dao.model.bo.BoUserDevice;
/*      */ import com.smarthome.imcp.dao.model.bo.BoUserDevices;
/*      */ import com.smarthome.imcp.dao.model.bo.BoUsers;
           import com.smarthome.imcp.dao.model.system.SysOperate;
/*      */ import com.smarthome.imcp.service.bo.BoAirBindingPanelServiceIface;
/*      */ import com.smarthome.imcp.service.bo.BoChannelServiceIface;
/*      */ import com.smarthome.imcp.service.bo.BoControlEnclosureServiceIface;
/*      */ import com.smarthome.imcp.service.bo.BoDeviceServiceIface;
/*      */ import com.smarthome.imcp.service.bo.BoDeviceStateServiceIface;
/*      */ import com.smarthome.imcp.service.bo.BoFingerprintMembersServiceIface;
/*      */ import com.smarthome.imcp.service.bo.BoHostDeviceServiceIface;
/*      */ import com.smarthome.imcp.service.bo.BoLockPasswordManageServiceIface;
/*      */ import com.smarthome.imcp.service.bo.BoLockVerdictServiceIface;
/*      */ import com.smarthome.imcp.service.bo.BoNetworkNumberServiceIface;
/*      */ import com.smarthome.imcp.service.bo.BoProcessServiceIface;
/*      */ import com.smarthome.imcp.service.bo.BoResendVerificationServiceIface;
/*      */ import com.smarthome.imcp.service.bo.BoSensorServiceIface;
/*      */ import com.smarthome.imcp.service.bo.BoUnlockingPushRecordServiceIface;
/*      */ import com.smarthome.imcp.service.bo.BoUserDeviceServiceIface;
/*      */ import com.smarthome.imcp.service.bo.BoUserDevicesServiceIface;
/*      */ import com.smarthome.imcp.service.bo.BoUserssServiceIface;
/*      */ import com.smarthome.imcp.util.LockPushUtil;
/*      */ import java.io.BufferedReader;
/*      */ import java.io.InputStreamReader;
/*      */ import java.io.PrintStream;
/*      */ import java.io.Serializable;
/*      */ import java.net.URL;
/*      */ import java.net.URLConnection;
/*      */ import java.text.SimpleDateFormat;
		   import java.util.ArrayList;
/*      */ import java.util.Date;
/*      */ import java.util.HashMap;
/*      */ import java.util.List;
/*      */ import java.util.Map;
		   import java.util.Set;

/*      */ import org.jboss.netty.channel.Channel;
/*      */ import org.slf4j.Logger;
/*      */ import org.slf4j.LoggerFactory;
/*      */ import org.springframework.beans.factory.annotation.Autowired;
/*      */ 
/*      */ public class PacketProcessHelper
/*      */ {
/*      */   private PacketProcessHelper packetProcessHelper;
/*   98 */   private Map<String, Integer> user_num = new HashMap();
/*   99 */   private RequestJson requestJson = new RequestJson();
/*      */   private String message;
/*      */   private byte[] data;
/*      */   private byte[] dataClone;
/*      */   private String s;
/*      */   private int count;
/*      */   private BoResendVerification resendVerification;
/*      */ 
/*      */   @Autowired
/*      */   private BoDeviceServiceIface<BoDevice, Serializable> wdDeviceService;
/*      */ 
/*      */   @Autowired
/*      */   private BoUserssServiceIface<BoUsers, Serializable> boUserServicess;
/*      */ 
/*      */   @Autowired
/*      */   private BoNetworkNumberServiceIface<BoNetworkNumber, Serializable> boNetworkNumberService;
/*      */ 
/*      */   @Autowired
/*      */   private BoChannelServiceIface<BoChannel, Serializable> boChannelberService;
/*      */ 
/*      */   @Autowired
/*      */   private BoUserDevicesServiceIface<BoUserDevices, Serializable> boUserDevicesServicess;
/*      */ 
/*      */   @Autowired
/*      */   private BoAirBindingPanelServiceIface<BoAirBindingPanel, Serializable> boAirBindingPanelService;
/*      */ 
/*      */   @Autowired
/*      */   private BoHostDeviceServiceIface<BoHostDevice, Serializable> boHostDeviceService;
/*      */ 
/*      */   @Autowired
/*      */   private BoLockPasswordManageServiceIface<BoLockPasswordManage, Serializable> boLockPasswordManageService;
/*      */ 
/*      */   @Autowired
/*      */   private BoUnlockingPushRecordServiceIface<BoUnlockingPushRecord, Serializable> boUnlockingPushRecordServicess;
/*      */ 
/*      */   @Autowired
/*      */   private BoLockVerdictServiceIface<BoLockVerdict, Serializable> boLockVerdictService;
/*      */ 
/*      */   @Autowired
/*      */   private BoControlEnclosureServiceIface<BoControlEnclosure, Serializable> boControlEnclosureService;
/*      */ 
/*      */   @Autowired
/*      */   private BoDeviceStateServiceIface<BoDeviceState, Serializable> boDeviceStateService;
/*      */ 
/*      */   @Autowired
/*      */   private BoResendVerificationServiceIface<BoResendVerification, Serializable> boResendVerificationService;
/*      */ 
/*      */   @Autowired
/*      */   private BoFingerprintMembersServiceIface<BoFingerprintMembers, Serializable> boFingerprintMembersService;
/*      */ 
/*      */   @Autowired
/*      */   private BoSensorServiceIface<BoSensor, Serializable> boSensorService;
/*      */   private Map<String, Object> map;
/*      */   private String prompt;
/*  231 */   private static Logger logger = LoggerFactory.getLogger(PacketProcessHelper.class);
/*      */   private PacketProcessor packetProcessor;
/*      */   private UserManager userManager;
/*      */ 
/*      */   @Autowired
/*      */   private BoProcessServiceIface boProcessService;
/*      */ 
/*      */   @Autowired
/*      */   private BoDeviceServiceIface boDeviceService;
/*      */ 
/*      */   @Autowired
/*      */   private BoUserDeviceServiceIface boUserDeviceService;

/*      */   private String userCode;
/*      */   private String deviceCode;
/*      */ 
/*      */   public String getMessage()
/*      */   {
/*  156 */     return this.message;
/*      */   }
/*      */ 
/*      */   public void setMessage(String message) {
/*  160 */     this.message = message;
/*      */   }
/*      */ 
/*      */   public String getS() {
/*  164 */     return this.s;
/*      */   }
/*      */ 
/*      */   public void setS(String s) {
/*  168 */     this.s = s;
/*      */   }
/*      */ 
/*      */   public byte[] getData() {
/*  172 */     return this.data;
/*      */   }
/*      */ 
/*      */   public void setData(byte[] data) {
/*  176 */     this.data = data;
/*      */   }
/*      */ 
/*      */   public byte[] getDataClone() {
/*  180 */     return this.dataClone;
/*      */   }
/*      */ 
/*      */   public void setDataClone(byte[] dataClone) {
/*  184 */     this.dataClone = dataClone;
/*      */   }
/*      */ 
/*      */   public String getPrompt() {
/*  188 */     return this.prompt;
/*      */   }
/*      */ 
/*      */   public void setPrompt(String prompt) {
/*  192 */     this.prompt = prompt;
/*      */   }
/*      */ 
/*      */   public PacketProcessHelper getPacketProcessHelper() {
/*  196 */     return this.packetProcessHelper;
/*      */   }
/*      */ 
/*      */   public void setPacketProcessHelper(PacketProcessHelper packetProcessHelper) {
/*  200 */     this.packetProcessHelper = packetProcessHelper;
/*      */   }
/*      */ 
/*      */   public String getDeviceCode() {
/*  204 */     return this.deviceCode;
/*      */   }
/*      */ 
/*      */   public void setDeviceCode(String deviceCode) {
/*  208 */     this.deviceCode = deviceCode;
/*      */   }
/*      */ 
/*      */   public RequestJson getRequestJson() {
/*  212 */     return this.requestJson;
/*      */   }
/*      */ 
/*      */   public void setRequestJson(RequestJson requestJson) {
/*  216 */     this.requestJson = requestJson;
/*      */   }
/*      */ 
/*      */   public Map<String, Object> getMap()
/*      */   {
/*  222 */     return this.map;
/*      */   }
/*      */ 
/*      */   public void setMap(Map<String, Object> map) {
/*  226 */     this.map = map;
/*      */   }
/*      */ 
/*      */   public PacketProcessHelper()
/*      */   {
/*      */   }
/*      */ 
/*      */   public PacketProcessHelper(PacketProcessor packetProcessor)
/*      */   {
/*  256 */     this.packetProcessor = packetProcessor;
/*  257 */     this.userManager = packetProcessor.getUserManager();
/*      */   }
/*      */ 
/*      */   public void processLoginSuccess(InPacket in)
/*      */   {
/*  269 */     logger.info("开始处理设备登录");
/*      */     System.out.println("开始处理设备登录 processLoginSuccess");
/*  271 */     LoginPacket packet = (LoginPacket)in;
/*  272 */     packet.getDevData();
/*      */ 
/*  274 */     int ret = 0;
/*      */ 
/*  276 */     String hostName = packet.getHostName();
/*  277 */     int port = packet.getPort();
/*      */ 
/*  279 */     String deviceCode = in.getDevId();
/*      */ 
/*  283 */     DockUser user = this.userManager.getUser(deviceCode);
/*  285 */     if (user == null) {
				 logger.info("user == null");//常出现？？？
/*  286 */       boolean isExist = this.boProcessService.doLogin(deviceCode, hostName);
/*      */ 
/*  288 */       if (isExist)
/*      */       {
/*  290 */         this.userManager.addUser(deviceCode, new Date(), hostName, port);
/*  291 */         this.packetProcessor.addSocketAddress(deviceCode, new String[] { 
/*  292 */           hostName, port+"" });
/*  293 */         ret = 1;
/*      */       } else {
/*  295 */         ret = -1;
/*      */       }
/*      */     } else {
/*  298 */       ret = 1;
/*  299 */       user.setStatus(1);
/*      */ 
/*  301 */       this.boProcessService.doLogin(deviceCode, hostName);
/*  302 */       this.userManager.addUser(deviceCode, new Date(), hostName, port);
/*  303 */       this.packetProcessor.addSocketAddress(deviceCode, new String[] { 
/*  304 */         hostName, port+"" });
/*      */     }
/*      */ 
/*  307 */     logger.info("注册设备序列号:" + deviceCode + " 设备注册! ip " + hostName + " 状态 " + 
/*  308 */       ret);
/*      */ 
/*  310 */     LoginReplyPacket reply = new LoginReplyPacket(deviceCode, ret);
/*  311 */     reply.setHostName(hostName);
/*  312 */     reply.setPort(port);
/*  313 */     this.packetProcessor.sendStrategy(reply);
/*      */   }
/*      */ 
/*      */   public void processLogout(String userId, Channel channel)
/*      */   {
/*  324 */     logger.info(userId + " 设备注册! 离线");
/*  325 */     this.userManager.logoutUser(userId, new Date(), "");
/*      */   }
/*      */ 
/*      */   public void processQuerySuccess(InPacket in) {
/*  329 */     logger.info("开始处理设备查询");
               System.out.println("processQuerySuccess");
/*  330 */     QueryPacket packet = (QueryPacket)in;
/*      */ 
/*  332 */     String deviceCode = packet.getDevId();
/*      */ 
/*  334 */     String hostName = packet.getHostName();
/*  335 */     int port = packet.getPort();
/*      */ 
/*  337 */     int ret = 1;
/*      */ 
/*  339 */     DockUser user = this.userManager.getUser(deviceCode);
/*      */ 
/*  341 */     if (user != null) {
/*  342 */       ret = user.getStatus();
/*      */     }
/*      */     else {
/*  345 */       BoDevice device = this.boProcessService.findDevice(deviceCode);
/*  346 */       if (device == null) {
/*  347 */         ret = 1;
/*      */       }
/*      */       else
/*      */       {
/*  355 */         ret = 1;
/*      */       }
/*      */     }
/*      */ 
/*  359 */     logger.info(" 查询设备状态 devId " + deviceCode + " 状态 " + ret);
/*      */ 
/*  361 */     QueryReplyPacket reply = new QueryReplyPacket(packet.getDevId(), ret);
/*  362 */     reply.setHostName(hostName);
/*  363 */     reply.setPort(port);
/*  364 */     this.packetProcessor.sendStrategy(reply);
/*      */   }
/*      */ 
/*      */   public void processSendDDatas(String devId, byte[] frameBody)
/*      */   {
/*  379 */     logger.info("发送 " + devId + " processSendDData");
/*  380 */     SendDDataPacket packet = new SendDDataPacket(devId);
/*      */ 
/*  385 */     packet.setFrameBody(frameBody);
/*  386 */     packet.setFrameLen(frameBody.length);
/*      */ 
/*  388 */     String[] address = this.packetProcessor.getSocketAddress(devId);
/*      */ 
/*  390 */     if (address == null) {
/*  391 */       logger.error(devId + " 设备IP 不存在");
/*  392 */       this.message = (devId + " 设备IP 不存在");
/*  393 */       return;
/*      */     }
/*      */ 
/*  396 */     packet.setHostName(address[0]);
/*  397 */     packet.setPort(Integer.valueOf(address[1]).intValue());
/*      */ 
/*  399 */     this.packetProcessor.sends(packet);
/*      */   }
/*      */ 
/*      */   public void processSendDDatas(String devId, byte[] frameBody, String userCode)
/*      */   {
/*  416 */     logger.info("发送 " + devId + " processSendDData");
/*  417 */     SendDDataPacket packet = new SendDDataPacket(devId);
/*      */ 
/*  422 */     packet.setFrameBody(frameBody);
/*  423 */     packet.setFrameLen(frameBody.length);
/*      */ 
/*  425 */     String[] address = this.packetProcessor.getSocketAddress(devId);
/*      */ 
/*  427 */     if (address == null) {
/*  428 */       logger.error(devId + " 设备IP 不存在");
/*  429 */       this.message = (devId + " 设备IP 不存在");
/*  430 */       return;
/*      */     }
/*      */ 
/*  433 */     packet.setHostName(userCode);
/*  434 */     packet.setPort(Integer.valueOf(address[1]).intValue());
/*      */ 
/*  436 */     this.packetProcessor.sends(packet);
/*      */   }
/*      */ 
/*      */   public void processSendDData(String devId, byte[] frameBody)
/*      */   {//设备对码的时候进入的方法
/*  452 */     logger.info("发送 " + devId + " processSendDData");
/*  453 */     SendDDataPacket packet = new SendDDataPacket(devId);//门锁的command有问题啊
			   System.out.println("processSendDData packet:"+packet);
			   System.out.println("processSendDData command:"+Util.getCommandString(packet.getCommand()));//门锁--MSG_C2D_DATA
/*      */ 
/*  458 */     packet.setFrameBody(frameBody);
/*  459 */     packet.setFrameLen(frameBody.length);
/*      */ 
			   System.out.println("devId:"+devId);
/*  461 */     String[] address = this.packetProcessor.getSocketAddress(devId);
/*      */ 
/*  463 */     if (address == null) {
/*  464 */       logger.error(devId + " 设备不在线");
/*  465 */       this.message = (devId + " 设备不在线");
/*  466 */       return;
/*      */     }
/*      */     System.out.println("address[0]="+address[0]);
/*  469 */     packet.setHostName(address[0]);
/*  470 */     packet.setPort(Integer.valueOf(address[1]).intValue());
/*      */ 
/*  472 */     this.packetProcessor.send(packet);//发送包  commandMode>>processSendDData
/*      */   }
/*      */ 
/*      */   public void processSend0Packet(String devId, char devType, byte frameType)
/*      */   {
/*  488 */     SendDDataPacket packet = new SendDDataPacket(devId);
/*  489 */     int req = OutPacket.getNextSeq();
/*  490 */     logger.info("发送清0命令 " + devId + " rand=" + req);
/*      */ 
/*  492 */     packet.setFrameType(frameType);
/*  493 */     packet.setDevType(devType);
/*  494 */     byte[] b = { (byte)req };
/*  495 */     packet.setFrameBody(b);
/*  496 */     packet.setFrameLen(1);
/*      */ 
/*  498 */     String[] address = this.packetProcessor.getSocketAddress(devId);
/*      */ 
/*  500 */     if (address == null) {
/*  501 */       logger.error(devId + " 设备IP 不存在");
/*  502 */       return;
/*      */     }
/*      */ 
/*  505 */     packet.setHostName(address[0]);
/*  506 */     packet.setPort(Integer.valueOf(address[1]).intValue());
/*      */ 
/*  508 */     this.packetProcessor.send(packet);
/*      */   }
/*      */ 
/*      */   public void processSendDDataSuccess(InPacket in) {
/*  512 */     SendDDataReplyPacket packet = (SendDDataReplyPacket)in;
/*      */ 
/*  514 */     String devId = packet.getDevId();
/*      */ 
/*  516 */     switch (packet.getFrameType())
/*      */     {
/*      */     case 2:
/*  519 */       break;
/*      */     case 16:
/*  521 */       logger.info("设备" + devId + "清0！");
/*  522 */       this.boProcessService.doUpdateDeviceWater(devId, 0);
/*      */     }
/*      */   }
/*      */ 
/*      */   public void processCDataSuccess(InPacket in)
/*      */   {
/*  533 */     logger.info("开始处理processCDataSuccess");
/*  534 */     CDataPacket packet = (CDataPacket)in;
/*  535 */     String devId = packet.getDevId();
/*  536 */     String clientId = packet.getClientId();
/*  537 */     byte[] data = packet.getDevData();
/*      */ 
/*  539 */     String hostName = packet.getHostName();
/*  540 */     int port = packet.getPort();
/*      */ 
/*  542 */     this.packetProcessor.addSocketAddress(clientId, new String[] { hostName, port+"" });
/*      */ 
/*  544 */     logger.info(clientId + " 客户 转发 " + "devId " + devId + " data " + data);
/*      */ 
/*  546 */     String[] address = this.packetProcessor.getSocketAddress(devId);
/*      */ 
/*  548 */     if (address == null) {
/*  549 */       logger.error(devId + " 设备IP 不存在");
/*  550 */       return;
/*      */     }
/*      */ 
/*  553 */     DataReplyPacket reply = new DataReplyPacket(packet.getCommand(), packet.getDevId());//这里又有和command相关的实体类
/*  554 */     reply.setDevData(data);
/*  555 */     reply.setHostName(address[0]);
/*  556 */     reply.setPort(Integer.valueOf(address[1]).intValue());
/*  557 */     logger.info(clientId + " 客户 转发给 " + devId + " 设备 ip " + address[0] + " port " + address[1]);
/*      */   }
/*      */ 
/*      */   public static boolean isInDate(Date date, String strDateBegin, String strDateEnd, String s)
/*      */   {
/*  592 */     SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
/*  593 */     String strDate = sdf.format(date);
/*      */ 
/*  595 */     int strDateH = Integer.parseInt(strDate.substring(11, 13));
/*      */ 
/*  598 */     int strDateBeginH = Integer.parseInt(strDateBegin);
/*      */ 
/*  601 */     int strDateEndH = Integer.parseInt(strDateEnd);
/*      */ 
/*  604 */     int ds = Integer.parseInt(s);
/*  605 */     if (ds == 1) {
/*  606 */       if ((strDateH >= strDateBeginH) && (strDateH <= strDateEndH))
/*      */       {
/*  608 */         return true;
/*      */       }
/*  610 */       return false;
/*      */     }
/*      */ 
/*  613 */     return false;
/*      */   }
/*      */ 
/*      */   public static String sendGet(String url, String userCode)
/*      */   {//安防会经过这里
/*  619 */     String result = "";
/*  620 */     BufferedReader in = null;
/*      */     try {
/*  622 */       String urlNameString = url;
/*  623 */       URL realUrl = new URL(urlNameString);
///*      */ 		 logger.info("realUrl>>>"+realUrl);
/*  625 */       URLConnection connection = realUrl.openConnection();
//                 logger.info("connection>>>"+connection);
/*      */ 
/*  628 */       connection.setRequestProperty("userCode", userCode);
/*  629 */       connection.setRequestProperty("timestamp", new Date().getTime()+"");
/*  631 */       connection.connect();//这里没问题，但是如果URL错的话就会连不上

/*  633 */       Map map = connection.getHeaderFields();
				 List<String> keys = new ArrayList<String>(map.keySet());
				 System.out.println( "----------------------------1>" );
/*  635 */       for (String key : keys) {
/*  636 */         System.out.println(key + "--->" + map.get(key));
/*      */       }
/*      */ 
/*  639 */       in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
/*      */       String line;
/*  641 */       while ((line = in.readLine()) != null)
/*      */       {
/*  642 */         result = result + line;
/*      */       }
/*  644 */       System.err.println(result);
/*      */     } catch (Exception e) {
/*  646 */       System.out.println("发送GET请求出现异常！" + e);
/*  647 */       e.printStackTrace();
/*      */       try
/*      */       {
/*  652 */         if (in != null)
/*  653 */           in.close();
/*      */       }
/*      */       catch (Exception e2) {
/*  656 */         e2.printStackTrace();
/*      */       }
/*      */     }
/*      */     finally
/*      */     {
/*      */       try
/*      */       {
/*  652 */         if (in != null)
/*  653 */           in.close();
/*      */       }
/*      */       catch (Exception e2) {
/*  656 */         e2.printStackTrace();
/*      */       }
/*      */     }
/*  659 */     return result;
/*      */   }
/*      */ 
/*      */   public static String sendGet2(String url) {
/*  663 */     String result = "";
/*  664 */     BufferedReader in = null;
/*      */     try {
/*  666 */       String urlNameString = url;
/*  667 */       URL realUrl = new URL(urlNameString);
/*      */ 		 logger.info("realUrl>>>"+realUrl);
/*  669 */       URLConnection connection = realUrl.openConnection();
				 logger.info("connection>>>"+connection);
/*      */ 
/*  674 */       connection.connect();
/*      */ 

/*  633 */       Map map = connection.getHeaderFields();
				 List<String> keys = new ArrayList<String>(map.keySet());
				 System.out.println( "----------------------------2>" );
/*  678 */       for (String key : keys) {
/*  679 */         System.out.println(key + "--->" + map.get(key));
/*      */       }
/*      */ 
/*  682 */       in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
/*      */       String line;
/*  684 */       while ((line = in.readLine()) != null)
/*      */       {
/*  685 */         result = result + line;
/*      */       }
/*  687 */       System.err.println(result);
/*      */     } catch (Exception e) {
/*  689 */       System.out.println("发送GET请求出现异常！" + e);
/*  690 */       e.printStackTrace();
/*      */       try
/*      */       {
/*  695 */         if (in != null)
/*  696 */           in.close();
/*      */       }
/*      */       catch (Exception e2) {
/*  699 */         e2.printStackTrace();
/*      */       }
/*      */     }
/*      */     finally
/*      */     {
/*      */       try
/*      */       {
/*  695 */         if (in != null)
/*  696 */           in.close();
/*      */       }
/*      */       catch (Exception e2) {
/*  699 */         e2.printStackTrace();
/*      */       }
/*      */     }
/*  702 */     return result;
/*      */   }
/*      */ 
/*      */   public void processDDataSuccess(InPacket in)
/*      */   {
/*  711 */     logger.info("开始处理processDDataSuccess");
/*  712 */     DDataPacket packet = (DDataPacket)in;
/*  713 */     String devId = packet.getDevId();
/*  714 */     String clientId = packet.getClientId();
			   System.out.println("processDDataSuccess clientId:"+clientId);
/*      */ 
/*  716 */     this.data = packet.getDevData();
/*      */ 
/*  718 */     this.dataClone = new byte[this.data.length];
/*      */ 
/*  720 */     System.arraycopy(this.data, 0, this.dataClone, 0, this.data.length);
/*  721 */     String as = new String(this.dataClone);
/*      */ 
/*  723 */     setS(as);
/*  724 */     System.err.println("this.dataClone:"+new String(this.dataClone));
/*  725 */     System.out.println("**************************");
/*      */ 
/*  727 */     if (this.user_num.get(getUserCode()) == null)
/*  728 */       this.user_num.put(getUserCode(), Integer.valueOf(0));
/*      */     else {
/*  730 */       this.user_num.put(getUserCode(), Integer.valueOf(((Integer)this.user_num.get(getUserCode())).intValue() == 255 ? 0 : ((Integer)this.user_num.get(getUserCode())).intValue() + 1));
/*      */     }
/*  732 */     String pm25 = new String(this.dataClone);
/*  733 */     String[] pm25Split = pm25.split("-");
/*  734 */     if ((pm25Split[0].trim().toString().equals("ZIGBEE_PM25")) && (pm25Split[1].trim().toString().equals("READ"))) {
/*  735 */       String pm25_2 = pm25Split[2].trim().toString();
/*  736 */       if (pm25_2.contains("OK")) {
/*  737 */         String[] pm25Split2 = pm25_2.split(",");
/*  738 */         StaticUtil.PM25.put(devId + "_" + pm25Split2[1].trim().toString(), pm25Split2[2].trim().toString());
/*      */       }
/*      */ 
/*      */     }
/*      */ 
/*  745 */     String airSend = new String(this.dataClone);
/*  746 */     String[] airSendSplit = airSend.split("-");
/*  747 */     if ((airSendSplit[0].trim().toString().equals("AIR")) && (airSendSplit[1].trim().toString().equals("SEND"))) {
/*  748 */       String airSend2 = airSendSplit[2].trim().toString();
/*  749 */       String[] airSendSplit2 = airSend2.split(",");
/*  750 */       if (airSendSplit2[7].trim().toString().equals("OK")) {
/*  751 */         StaticUtil.AIRSEND.put(devId + "_" + airSendSplit2[1].trim().toString() + "," + airSendSplit2[2].trim().toString(), "OK");
/*      */       }
/*      */ 
/*      */     }
/*      */ 
/*  758 */     String airOff = new String(this.dataClone);
/*  759 */     String[] airOffSplit = airOff.split("-");
/*  760 */     if ((airOffSplit[0].trim().toString().equals("AIR")) && (airOffSplit[1].trim().toString().equals("POWER_OFF"))) {
/*  761 */       String airOff2 = airOffSplit[2].trim().toString();
/*  762 */       String[] airOffSplit2 = airOff2.split(",");
/*  763 */       if (airOffSplit2[4].trim().toString().equals("OK")) {
/*  764 */         StaticUtil.AIROFF.put(devId + "_" + airOffSplit2[1].trim().toString() + "," + airOffSplit2[2].trim().toString(), "OK");
/*      */       }
/*      */ 
/*      */     }
/*      */ 
/*  769 */     String lockReset = new String(this.dataClone);
/*  770 */     String[] lockResetSplit = lockReset.split("-");
/*  771 */     if ((lockResetSplit[0].trim().toString().equals("ZIGBEE_LOCK")) && (lockResetSplit[1].trim().toString().equals("RESET"))) {
/*  772 */       String lockReset2 = lockResetSplit[2].trim().toString();
/*  773 */       String ResetString = "ZIGBEE_LOCK-RESET-" + lockReset2 + "," + "OK";
/*  774 */       byte[] Reset = ResetString.getBytes();
/*  775 */       System.err.println(new String(Reset));
/*  776 */       this.packetProcessHelper.processSendDData(devId, Reset);
/*  777 */       List<BoFingerprintMembers> fingerprintMembers = this.boFingerprintMembersService.get(lockReset2);
/*  778 */       for (BoFingerprintMembers boFingerprintMembers : fingerprintMembers) {
/*  779 */         this.boFingerprintMembersService.delete(boFingerprintMembers);
/*      */       }
/*  781 */       List<BoLockPasswordManage> passwordManage = this.boLockPasswordManageService.get(lockReset2);
/*  782 */       for (BoLockPasswordManage boLockPasswordManage : passwordManage) {
/*  783 */         this.boLockPasswordManageService.delete(boLockPasswordManage);
/*      */       }
/*      */     }
/*      */ 
/*  787 */     String lockPowr = new String(this.dataClone);
/*  788 */     String[] lockPowrSplit = lockPowr.split("-");
/*      */     Integer phoneType;
/*  789 */     if ((lockPowrSplit[0].trim().toString().equals("ZIGBEE_LOCK")) && (lockPowrSplit[1].trim().toString().equals("POWER"))) {
/*  790 */       String formatDate = GlobalMethod.formatDate(new Date(), "yyyy-MM-dd HH:mm:ss");
/*      */ 
/*  792 */       String lockPowr2 = lockPowrSplit[2].trim().toString();
/*  793 */       String[] lockPowrSplit2 = lockPowr2.split(",");
/*  794 */       String PowrString = "ZIGBEE_LOCK-POWER-" + lockPowrSplit2[0].trim().toString() + "," + lockPowrSplit2[1].trim().toString() + "," + "OK";
/*  795 */       byte[] Powr = PowrString.getBytes();
/*  796 */       System.err.println(new String(Powr));
/*  797 */       this.packetProcessHelper.processSendDData(devId, Powr);
/*  798 */       BoHostDevice hostDevice = this.boHostDeviceService.lock(devId, lockPowrSplit2[0].trim().toString());
/*  799 */       BoUsers users = this.boUserServicess.findByUserUserCode(hostDevice.getBoUsers().getUserCode());
/*  800 */       if (lockPowrSplit2[1].trim().toString().equals("0")) {
/*  801 */         Long timestamp = Long.valueOf(172800L);
/*  802 */         Long timestamps = Long.valueOf((int)(System.currentTimeMillis() / 1000L) + timestamp.longValue());
/*  803 */         BoUnlockingPushRecord unlockingPushRecord = new BoUnlockingPushRecord();
/*  804 */         unlockingPushRecord.setUserCode(hostDevice.getBoUsers().getUserCode());
/*  805 */         unlockingPushRecord.setLockAddress(lockPowrSplit2[0].trim().toString());
/*  806 */         unlockingPushRecord.setMethodsStatus(hostDevice.getNickName() + " 锁 " + "电量低");
/*  807 */         phoneType = hostDevice.getBoUsers().getPhoneType();
/*      */         String alarmPhoneType;
/*  809 */         if (phoneType.intValue() == 0)
/*  810 */           alarmPhoneType = "安卓";
/*      */         else {
/*  812 */           alarmPhoneType = "苹果";
/*      */         }
/*  814 */         unlockingPushRecord.setAlarmPhoneType(alarmPhoneType);
/*  815 */         unlockingPushRecord.setReportDate(formatDate);
/*  816 */         unlockingPushRecord.setReportTimestamp(timestamps+"");
/*  817 */         this.boUnlockingPushRecordServicess.save(unlockingPushRecord);
/*  818 */         LockPushUtil.lockPowr(users.getVersionType(), users.getCid(), users.getPhoneType().toString(), "电量低", formatDate);
/*      */       }
/*      */     }
/*      */ 
/*  822 */     String lockPwdDelete = new String(this.dataClone);
/*  823 */     String[] lockPwdDeleteSplit = lockPwdDelete.split("-");
/*  824 */     if ((lockPwdDeleteSplit[0].trim().toString().equals("ZIGBEE_LOCK")) && (lockPwdDeleteSplit[1].trim().toString().equals("DELETE"))) {
/*  825 */       String lockPwdDelete2 = lockPwdDeleteSplit[2].trim().toString();
/*  826 */       String[] lockPwdDeleteSplit2 = lockPwdDelete2.split(",");
/*  827 */       if (lockPwdDeleteSplit2[4].trim().toString().equals("OK")) {
/*  828 */         StaticUtil.PWDDELETES.put(devId + "_" + lockPwdDeleteSplit2[1].trim().toString(), "OK");
/*  829 */       } else if (lockPwdDeleteSplit2[4].trim().toString().equals("ERROR")) {
/*  830 */         StaticUtil.PWDDELETES.put(devId + "_" + lockPwdDeleteSplit2[1].trim().toString(), "ERROR");
/*  831 */         System.err.println("删除失败");
/*      */       } else {
/*  833 */         System.err.println("主机到锁通讯失败");
/*      */       }
/*      */     }
/*      */ 
/*  837 */     String lockFingerprintAddDelete = new String(this.dataClone);
/*  838 */     String[] lockFingerprintAddDeleteSplit = lockFingerprintAddDelete.split("-");
/*  839 */     if ((lockFingerprintAddDeleteSplit[0].trim().toString().equals("ZIGBEE_LOCK")) && (lockFingerprintAddDeleteSplit[1].trim().toString().equals("FINGERPRINT"))) {
/*  840 */       String lockFingerprintAddDelete2 = lockFingerprintAddDeleteSplit[2].trim().toString();
/*  841 */       String[] lockFingerprintAddDeleteSplit2 = lockFingerprintAddDelete2.split(",");
/*  842 */       String NumString = "ZIGBEE_LOCK-FINGERPRINT-" + lockFingerprintAddDeleteSplit2[0].trim().toString() + "," + lockFingerprintAddDeleteSplit2[1].trim().toString() + "," + lockFingerprintAddDeleteSplit2[2].trim().toString() + "," + "OK";
/*  843 */       byte[] Num = NumString.getBytes();
/*  844 */       System.err.println(new String(Num));
/*  845 */       this.packetProcessHelper.processSendDData(devId, Num);
/*  846 */       if (lockFingerprintAddDeleteSplit2[1].trim().toString().equals("0")) {
/*  847 */         BoFingerprintMembers members = this.boFingerprintMembersService.findFingerprint(lockFingerprintAddDeleteSplit2[0].trim().toString(), lockFingerprintAddDeleteSplit2[2].trim().toString());
/*  848 */         if (members == null) {
/*  849 */           BoFingerprintMembers s = new BoFingerprintMembers();
/*  850 */           s.setLockAddress(lockFingerprintAddDeleteSplit2[0].trim().toString());
/*  851 */           s.setFingerprintSubscript(lockFingerprintAddDeleteSplit2[2].trim().toString());
/*  852 */           s.setMembersHeadpic("uploads/fingerprintHeadpic/head.jpg");
/*  853 */           s.setMembersName("");
/*  854 */           this.boFingerprintMembersService.save(s);
/*      */         } else {
/*  856 */           members.setLockAddress(lockFingerprintAddDeleteSplit2[0].trim().toString());
/*  857 */           members.setMembersHeadpic("uploads/fingerprintHeadpic/head.jpg");
/*  858 */           members.setMembersName("");
/*  859 */           this.boFingerprintMembersService.update(members);
/*      */         }
/*  861 */       } else if (lockFingerprintAddDeleteSplit2[1].trim().toString().equals("1")) {
/*  862 */         if (lockFingerprintAddDeleteSplit2[2].trim().toString().equals("65534")) {
/*  863 */           List<BoFingerprintMembers> list = this.boFingerprintMembersService.get(lockFingerprintAddDeleteSplit2[0].trim().toString());
/*  864 */           for (BoFingerprintMembers boFingerprintMembers : list)
/*  865 */             this.boFingerprintMembersService.delete(boFingerprintMembers);
/*      */         }
/*      */         else {
/*  868 */           BoFingerprintMembers members = this.boFingerprintMembersService.findFingerprint(lockFingerprintAddDeleteSplit2[0].trim().toString(), lockFingerprintAddDeleteSplit2[2].trim().toString());
/*  869 */           if (members != null) {
/*  870 */             this.boFingerprintMembersService.delete(members);
/*      */           }
/*      */         }
/*      */       }
/*      */ 
/*      */     }
/*      */ 
/*  877 */     String lockFingerprint = new String(this.dataClone);
/*  878 */     String[] lockFingerprintSplit = lockFingerprint.split("-");
/*  879 */     if ((lockFingerprintSplit[0].trim().toString().equals("ZIGBEE_LOCK")) && (lockFingerprintSplit[1].trim().toString().equals("NUM"))) {
/*  880 */       String lockFingerprint2 = lockFingerprintSplit[2].trim().toString();
/*  881 */       String[] lockFingerprintSplit2 = lockFingerprint2.split(",");
/*  882 */       if (!lockFingerprintSplit2[0].trim().toString().equals("OK")) {
/*  883 */         for (int i = 2; i < lockFingerprintSplit2.length - 1; i++) {
/*  884 */           BoFingerprintMembers fingerprintMembers = this.boFingerprintMembersService.findFingerprint(lockFingerprintSplit2[1], lockFingerprintSplit2[i]);
/*  885 */           if (fingerprintMembers == null) {
/*  886 */             BoFingerprintMembers s = new BoFingerprintMembers();
/*  887 */             s.setLockAddress(lockFingerprintSplit2[1]);
/*  888 */             s.setFingerprintSubscript(lockFingerprintSplit2[i]);
/*  889 */             s.setMembersHeadpic("uploads/fingerprintHeadpic/head.jpg");
/*  890 */             s.setMembersName("");
/*  891 */             this.boFingerprintMembersService.save(s);
/*      */           }
/*      */ 
/*      */         }
/*      */ 
/*      */       }
/*      */ 
/*      */     }
/*      */ 
/*  900 */     String lockControl = new String(this.dataClone);
/*  901 */     String[] lockControlSplit = lockControl.split("-");
/*  902 */     if ((lockControlSplit[0].trim().toString().equals("ZIGBEE_LOCK")) && (lockControlSplit[1].trim().toString().equals("SEND"))) {
/*  903 */       String lockControl2 = lockControlSplit[2].trim().toString();
/*  904 */       String[] lockControlSplit2 = lockControl2.split(",");
/*  905 */       if (lockControlSplit2[3].trim().toString().equals("OK"))
/*      */       {
/*  907 */         StaticUtil.LOCKCONTROL.put(devId + "_" + lockControlSplit2[1].trim().toString(), "OK");
/*  908 */       } else if (lockControlSplit2[3].trim().toString().equals("ERROR"))
/*      */       {
/*  910 */         StaticUtil.LOCKCONTROL.put(devId + "_" + lockControlSplit2[1].trim().toString(), "ERROR");
/*  911 */       } else if (lockControlSplit2[3].trim().toString().equals("BACKLOCK"))
/*      */       {
/*  913 */         StaticUtil.LOCKCONTROL.put(devId + "_" + lockControlSplit2[1].trim().toString(), "BACKLOCK");
/*  914 */       } else if (lockControlSplit2[3].trim().toString().equals("ZIGBEE_ERR")) {
/*  915 */         System.err.println("通讯失败");
/*      */       }
/*      */ 
/*      */     }
/*      */ 
/*  920 */     String lockElectricQuantity = new String(this.dataClone);
/*  921 */     String[] lockElectricQuantitySplit = lockElectricQuantity.split("-");
/*  922 */     if ((lockElectricQuantitySplit[0].trim().toString().equals("ZIGBEE_LOCK")) && (lockElectricQuantitySplit[1].trim().toString().equals("CHECK"))) {
/*  923 */       String lockElectricQuantity2 = lockElectricQuantitySplit[2].trim().toString();
/*  924 */       String[] lockElectricQuantitySplit2 = lockElectricQuantity2.split(",");
/*  925 */       for (int i = 0; i < lockElectricQuantitySplit2.length; i++) {
/*  926 */         System.err.println("截取 " + lockElectricQuantitySplit2[i]);
/*      */       }
/*  928 */       if (lockElectricQuantitySplit2[4].trim().toString().equals("OK"))
/*  929 */         StaticUtil.ELECTRIC.put(devId + "_" + lockElectricQuantitySplit2[1].trim().toString(), new String[] { "OK", lockElectricQuantitySplit2[2].trim().toString(), lockElectricQuantitySplit2[3].trim().toString() });
/*  930 */       else if (lockElectricQuantitySplit2[3].trim().toString().equals("ERROR"))
/*  931 */         StaticUtil.ELECTRIC.put(devId + "_" + lockElectricQuantitySplit2[1].trim().toString(), new String[] { "ERROR" });
/*  932 */       else if (lockElectricQuantitySplit2[3].trim().toString().equals("ZIGBEE_ERR")) {
/*  933 */         System.err.println("通讯失败");
/*      */       }
/*      */ 
/*      */     }
/*      */ 
/*  938 */     String lockOpen = new String(this.dataClone);
/*  939 */     String[] lockOpenSplit = lockOpen.split("-");
/*  940 */     if ((lockOpenSplit[0].trim().toString().equals("ZIGBEE_LOCK")) && (lockOpenSplit[1].trim().toString().equals("OPEN"))) {
/*  941 */       String lockOpen2 = lockOpenSplit[2].trim().toString();
/*  942 */       String[] lockOpenSplit2 = lockOpen2.split(",");
/*  943 */       String NumString = "ZIGBEE_LOCK-OPEN-" + lockOpenSplit2[0].trim().toString() + "," + lockOpenSplit2[1].trim().toString() + "," + lockOpenSplit2[2].trim().toString() + "," + lockOpenSplit2[3].trim().toString() + "," + "OK";
/*  944 */       byte[] Num = NumString.getBytes();
/*  945 */       System.err.println(new String(Num));
/*  946 */       this.packetProcessHelper.processSendDData(devId, Num);
/*  947 */       BoHostDevice hostDevice = this.boHostDeviceService.lock(devId, lockOpenSplit2[0].trim().toString());
/*  948 */       BoUsers users = this.boUserServicess.findByUserUserCode(hostDevice.getBoUsers().getUserCode());
/*  949 */       String unlockMethods = "";
/*  950 */       String membersName = "";
/*  951 */       String fingerprintSubscript = "";
/*  952 */       if (lockOpenSplit2[1].trim().toString().equals("1")) {
/*  953 */         unlockMethods = "指纹";
/*  954 */         if (!lockOpenSplit2[2].trim().toString().equals("0")) {
/*  955 */           System.err.println(lockOpenSplit2[2].trim().toString());
/*  956 */           BoFingerprintMembers findFingerprint = this.boFingerprintMembersService.findFingerprint(lockOpenSplit2[0].trim().toString(), lockOpenSplit2[2].trim().toString());
/*  957 */           if (findFingerprint != null) {
/*  958 */             membersName = findFingerprint.getMembersName();
/*  959 */             fingerprintSubscript = findFingerprint.getFingerprintSubscript();
/*      */           }
/*      */         }
/*  962 */       } else if (lockOpenSplit2[1].trim().toString().equals("2")) {
/*  963 */         unlockMethods = "密码";
/*  964 */       } else if (lockOpenSplit2[1].trim().toString().equals("3")) {
/*  965 */         unlockMethods = "卡";
/*      */       } else {
/*  967 */         unlockMethods = "远程密码";
/*      */       }
/*  969 */       String unlockStatus = "";
/*  970 */       if (lockOpenSplit2[3].trim().toString().equals("0"))
/*  971 */         unlockStatus = "开锁成功";
/*  972 */       else if (lockOpenSplit2[3].trim().toString().equals("255"))
/*  973 */         unlockStatus = "开锁失败";
/*  974 */       else if (lockOpenSplit2[3].trim().toString().equals("253")) {
/*  975 */         unlockStatus = "门返锁";
/*      */       }
/*      */       try
/*      */       {
/*  979 */         String formatDate = GlobalMethod.formatDate(new Date(), "yyyy-MM-dd HH:mm:ss");
/*  980 */         Long timestamp = Long.valueOf(172800L);
/*  981 */         Long timestamps = Long.valueOf((int)(System.currentTimeMillis() / 1000L) + timestamp.longValue());
/*  982 */         BoUnlockingPushRecord unlockingPushRecord = new BoUnlockingPushRecord();
/*  983 */         unlockingPushRecord.setUserCode(hostDevice.getBoUsers().getUserCode());
/*  984 */         unlockingPushRecord.setLockAddress(lockOpenSplit2[0].trim().toString());
/*  985 */         unlockingPushRecord.setMethodsStatus(hostDevice.getNickName() + " 锁 " + membersName + " " + unlockMethods + " " + unlockStatus);
/*  986 */         if (unlockMethods.equals("指纹")) {
/*  987 */           if (membersName.equals(""))
/*  988 */             unlockingPushRecord.setMethodsStatus(hostDevice.getNickName() + " 锁 " + "用户" + fingerprintSubscript + " " + unlockMethods + " " + unlockStatus);
/*      */           else
/*  990 */             unlockingPushRecord.setMethodsStatus(hostDevice.getNickName() + " 锁 " + membersName + " " + unlockMethods + " " + unlockStatus);
/*      */         }
/*      */         else {
/*  993 */           unlockingPushRecord.setMethodsStatus(hostDevice.getNickName() + " 锁 " + membersName + " " + unlockMethods + " " + unlockStatus);
/*      */         }
/*  995 */         phoneType = hostDevice.getBoUsers().getPhoneType();
/*      */         String alarmPhoneType;
///*      */         String alarmPhoneType;
/*  997 */         if (phoneType.intValue() == 0)
/*  998 */           alarmPhoneType = "安卓";
/*      */         else {
/* 1000 */           alarmPhoneType = "苹果";
/*      */         }
/* 1002 */         unlockingPushRecord.setAlarmPhoneType(alarmPhoneType);
/* 1003 */         unlockingPushRecord.setReportDate(formatDate);
/* 1004 */         unlockingPushRecord.setReportTimestamp(timestamps+"");
/* 1005 */         this.boUnlockingPushRecordServicess.save(unlockingPushRecord);
/* 1006 */         if (hostDevice.getPushSet().equals("0")) {
/* 1007 */           LockPushUtil.lockPush(users.getVersionType(), users.getCid(), users.getPhoneType().toString(), unlockMethods, unlockStatus, formatDate, hostDevice.getNickName(), membersName);
/*      */         }
/*      */         else
/*      */         {
/* 1011 */           logger.info(users.getUserPhone() + "的锁里设置了不推送");
/*      */         }
/*      */       }
/*      */       catch (Exception e) {
/* 1015 */         logger.error(e.getMessage());
/*      */       }
/*      */ 
/*      */     }
/*      */ 
/* 1021 */     String lockDelete = new String(this.dataClone);
/* 1022 */     String[] lockDeleteSplit = lockDelete.split("-");
/* 1023 */     if ((lockDeleteSplit[0].trim().toString().equals("ZIGBEE_LOCK")) && (lockDeleteSplit[1].trim().toString().equals("DELETE")))
/*      */     {
/* 1025 */       String lockDelete2 = lockDeleteSplit[2].trim().toString();
/* 1026 */       String[] lockDeleteSplit2 = lockDelete2.split(",");
/* 1027 */       if (lockDeleteSplit2[4].trim().toString().equals("OK"))
/* 1028 */         StaticUtil.LOCKDELETE.put(devId + "_" + lockDeleteSplit2[1].trim().toString(), "OK");
/* 1029 */       else if (lockDeleteSplit2[4].trim().toString().equals("ERROR"))
/* 1030 */         StaticUtil.LOCKDELETE.put(devId + "_" + lockDeleteSplit2[1].trim().toString(), "ERROR");
/* 1031 */       else if (lockDeleteSplit2[4].trim().toString().equals("ZIGBEE_ERR")) {
/* 1032 */         System.err.println("通讯失败");
/*      */       }
/*      */ 
/*      */     }
/*      */ 
/* 1037 */     String lockSet = new String(this.dataClone);
/* 1038 */     String[] lockSetSplit = lockSet.split("-");
/*      */ 
/* 1040 */     if ((lockSetSplit[0].trim().toString().equals("ZIGBEE_LOCK")) && 
/* 1041 */       (lockSetSplit[1].trim().toString().equals("SET"))) {
/* 1042 */       String lockSet2 = lockSetSplit[2].trim().toString();
/* 1043 */       String[] lockSetSplit2 = lockSet2.split(",");
/* 1044 */       if (lockSetSplit2[5].trim().toString().equals("OK"))
/* 1045 */         StaticUtil.LOCKSET.put(devId + "_" + lockSetSplit2[1].trim().toString(), "OK");
/* 1046 */       else if (lockSetSplit2[5].trim().toString().equals("ERROR"))
/* 1047 */         StaticUtil.LOCKSET.put(devId + "_" + lockSetSplit2[1].trim().toString(), "ERROR");
/* 1048 */       else if (lockSetSplit2[5].trim().toString().equals("ZIGBEE_ERR")) {
/* 1049 */         System.err.println("通讯失败");
/*      */       }
/*      */ 
/*      */     }
/*      */ 
/* 1054 */     String air_Params = new String(this.dataClone);
/* 1055 */     String[] air_ParamsSplit = air_Params.split("-");
/* 1056 */     if ((air_ParamsSplit[0].trim().toString().equals("AIR")) && 
/* 1057 */       (air_ParamsSplit[1].trim().toString().equals("READ"))) {
/* 1058 */       String air_Params2 = air_ParamsSplit[2].trim().toString();
/* 1059 */       String[] air_ParamsSplit2 = air_Params2.split(",");
/*      */ 
/* 1063 */       if (air_ParamsSplit2[8].trim().toString().equals("OK")) {
/* 1064 */         System.err.println("&*()))**(8888888888888");
/* 1065 */         StaticUtil.AIR_Params.put(devId + "_" + air_ParamsSplit2[2].trim().toString(), new String[] { 
/* 1066 */           air_ParamsSplit2[4].trim().toString(), air_ParamsSplit2[5].trim().toString(), air_ParamsSplit2[6].trim().toString(), air_ParamsSplit2[7].trim().toString() });
/*      */       }
/*      */ 
/*      */     }
/*      */ 
/* 1073 */     String air_Alarms = new String(this.dataClone);
/* 1074 */     String[] air_AlarmsSplit = air_Alarms.split("-");
/* 1075 */     if ((air_AlarmsSplit[0].trim().toString().equals("AIR")) && 
/* 1076 */       (air_AlarmsSplit[1].trim().toString().equals("APP_ALARMS"))) {
/* 1077 */       String air_Alarms2 = air_AlarmsSplit[2].trim().toString();
/* 1078 */       String[] air_AlarmsSplit2 = air_Alarms2.split(",");
/* 1079 */       if (air_AlarmsSplit2[5].trim().toString().equals("OK"))
/* 1080 */         StaticUtil.AIR_ALARMS.put(devId + "_" + "A", new String[] { 
/* 1081 */           "OK", new Date().getTime()+"" });
/* 1082 */       else if (air_AlarmsSplit2[5].trim().toString().equals("ZIGBEE_ERR")) {
/* 1083 */         StaticUtil.AIR_ALARMS.put(devId + "_" + "A", new String[] { 
/* 1084 */           "ZIGBEE_ERR", new Date().getTime()+"" });
/*      */       }
/*      */ 
/*      */     }
/*      */ 
/* 1092 */     String control_controlEnclosure = new String(this.dataClone);
/* 1093 */     String[] control_controlEnclosureSplit = control_controlEnclosure.split("-");
/*      */ 
/* 1095 */     if ((control_controlEnclosureSplit[0].trim().toString().equals("RELAY")) && 
/* 1096 */       (control_controlEnclosureSplit[1].trim().toString().equals("SET"))) {
/* 1097 */       String control_controlEnclosure2 = control_controlEnclosureSplit[2].trim().toString();
/* 1098 */       String[] control_controlEnclosureSplit2 = control_controlEnclosure2.split(",");
/* 1099 */       for (int i = 0; i < control_controlEnclosureSplit2.length; i++) {
/* 1100 */         System.err.println("截取 " + control_controlEnclosureSplit2[i]);
/*      */       }
/* 1102 */       if (control_controlEnclosureSplit2[3].trim().toString().equals("OK")) {
/* 1103 */         this.resendVerification = this.boResendVerificationService.find(devId, devId, 
/* 1104 */           control_controlEnclosureSplit2[2].trim().toString());
/* 1105 */         if (this.resendVerification != null) {
/* 1106 */           this.resendVerification.setAcceptState("OK");
/* 1107 */           this.boResendVerificationService.update(this.resendVerification);
/*      */         }
/*      */ 
/*      */       }
/*      */ 
/*      */     }
/*      */ 
/* 1115 */     String alarmEnclosure = new String(this.dataClone);
/* 1116 */     String[] alarmEnclosureSplit = alarmEnclosure.split("-");
/* 1117 */     if ((alarmEnclosureSplit[0].trim().toString().equals("RELAY")) && 
/* 1118 */       (alarmEnclosureSplit[1].trim().toString().equals("ALARM"))) {
/* 1119 */       String alarmEnclosure2 = alarmEnclosureSplit[2].trim().toString();
/* 1120 */       String[] alarmEnclosureSplit2 = alarmEnclosure2.split(",");
/* 1121 */       String str = "RELAY-ALARM-" + alarmEnclosureSplit2[0].trim().toString() + ",OK";
/* 1122 */       byte[] bs = str.getBytes();
/* 1123 */       this.packetProcessHelper.processSendDDatas(devId, bs);
/*      */ 
/* 1125 */       BoSensor ssss = this.boSensorService.find(devId, alarmEnclosureSplit2[0].trim().toString());
/* 1126 */       Integer type = Integer.valueOf(ssss.getType());
/*      */ 
/* 1128 */       switch (type.intValue()) {
/*      */       case 1:
/*      */         try {
/* 1131 */           Thread.sleep(500L);
/* 1132 */           if (ssss.getSecurityType().equals("1")) {
	                   logger.info("getSecurityType===1");
/* 1133 */             boolean inDateOno = isInDate(new Date(), ssss.getStartTimeOne(), ssss.getEndTimeOne(), ssss.getSecurityOneType());
/* 1134 */             boolean inDateTwo = isInDate(new Date(), ssss.getStartTimeTwo(), ssss.getEndTimeTwo(), ssss.getSecurityTwoType());
/* 1135 */             boolean inDateThree = isInDate(new Date(), ssss.getStartTimeThree(), ssss.getEndTimeThree(), ssss.getSecurityThreeType());
/* 1136 */             if (inDateOno) {
/*      */               try {
/* 1138 */                 Thread.sleep(600L);
/* 1139 */                 sendGet2("http://120.77.250.17:8080/smarthome.IMCPlatform/xingUser/push.action?deviceCode=" + devId + "&" + "deviceAddress=" + alarmEnclosureSplit2[0].trim().toString());
/*      */               }
/*      */               catch (InterruptedException e) {
/* 1142 */                 e.printStackTrace();
/*      */               }
/*      */               try {
/* 1145 */                 Thread.sleep(500L);
/* 1146 */                 sendGet("http://120.77.250.17:8080/smarthome.IMCPlatform/xingUser/commandmodel.action?modelId=" + ssss.getBoModel().getModelId(), ssss.getBoUsers().getUserCode());
/*      */               }
/*      */               catch (InterruptedException e) {
/* 1149 */                 e.printStackTrace();
/*      */               }
/* 1151 */             } else if (inDateTwo) {
/*      */               try {
/* 1153 */                 Thread.sleep(600L);
/* 1154 */                 sendGet2("http://120.77.250.17:8080/smarthome.IMCPlatform/xingUser/push.action?deviceCode=" + devId + "&" + "deviceAddress=" + alarmEnclosureSplit2[0].trim().toString());
/*      */               }
/*      */               catch (InterruptedException e) {
/* 1157 */                 e.printStackTrace();
/*      */               }
/*      */               try {
/* 1160 */                 Thread.sleep(500L);
/* 1161 */                 sendGet("http://120.77.250.17:8080/smarthome.IMCPlatform/xingUser/commandmodel.action?modelId=" + ssss.getBoModel().getModelId(), ssss.getBoUsers().getUserCode());
/*      */               }
/*      */               catch (InterruptedException e) {
/* 1164 */                 e.printStackTrace();
/*      */               }
/* 1166 */             } else if (inDateThree) {
/*      */               try {
/* 1168 */                 Thread.sleep(600L);
/* 1169 */                 sendGet2("http://120.77.250.17:8080/smarthome.IMCPlatform/xingUser/push.action?deviceCode=" + devId + "&" + "deviceAddress=" + alarmEnclosureSplit2[0].trim().toString());
/*      */               }
/*      */               catch (InterruptedException e) {
/* 1172 */                 e.printStackTrace();
/*      */               }
/*      */               try {
/* 1175 */                 Thread.sleep(500L);
/* 1176 */                 sendGet("http://120.77.250.17:8080/smarthome.IMCPlatform/xingUser/commandmodel.action?modelId=" + ssss.getBoModel().getModelId(), ssss.getBoUsers().getUserCode());
/*      */               }
/*      */               catch (InterruptedException e) {
/* 1179 */                 e.printStackTrace();
/*      */               }
/*      */             }
/*      */           } else {
/* 1183 */             logger.info("< " + ssss.getBoUsers().getUserPhone() + " 用户的 " + ssss.getNickName() + " > " + " 控制的传感器 总开关是 关的");
/* 1184 */             System.err.println("< " + ssss.getBoUsers().getUserPhone() + " 用户的 " + ssss.getNickName() + " > " + " 控制的传感器 总开关是 关的");
/*      */           }
/*      */         }
/*      */         catch (InterruptedException e) {
/* 1188 */           e.printStackTrace();
/*      */         }
/*      */ 
/*      */       }
/*      */ 
/*      */     }
/*      */ 
/* 1199 */     String readEnclosure = new String(this.dataClone);
/* 1200 */     String[] readEnclosureSplit = readEnclosure.split("-");
/* 1201 */     if ((readEnclosureSplit[0].trim().toString().equals("RELAY")) && 
/* 1202 */       (readEnclosureSplit[1].trim().toString().equals("READ"))) {
/* 1203 */       String[] array = (String[])StaticUtil.READCONTROLENCLOSURE.get(devId + "_A");
/* 1204 */       String userCode = "";
/* 1205 */       if (array != null) {
/* 1206 */         userCode = array[0];
/*      */       }
/* 1208 */       String readEnclosure2 = readEnclosureSplit[2].trim().toString();
/* 1209 */       String[] readEnclosureSplit2 = readEnclosure2.split(",");
/* 1210 */       BoControlEnclosure controlEnclosures = this.boControlEnclosureService.controlEnclosures(devId, userCode, readEnclosureSplit2[1].trim().toString());
/* 1211 */       if (controlEnclosures != null) {
/* 1212 */         controlEnclosures.setState(readEnclosureSplit2[2].trim().toString());
/* 1213 */         this.boControlEnclosureService.update(controlEnclosures);
/*      */       }
/*      */ 
/*      */     }
/*      */ 
/* 1222 */     String controlEnclosure = new String(this.dataClone);
/* 1223 */     String[] controlEnclosureSplit = controlEnclosure.split("_");
/* 1224 */     if ((controlEnclosureSplit[0].trim().toString().equals("RELAY-SCAN")) && 
/* 1225 */       (controlEnclosureSplit[1].trim().toString().equals("DEVICE-CHANNEL"))) {
/* 1226 */       String[] array = (String[])StaticUtil.CONTROLENCLOSURE.get(devId + "_A");
/* 1227 */       String userCode = "";
/* 1228 */       if (array != null) {
/* 1229 */         userCode = array[0];
/*      */       }
/* 1231 */       BoUsers boUsers = this.boUserServicess.findByUserUserCode(userCode);
/* 1232 */       BoDevice boDevice = this.wdDeviceService.findByCode(devId);
/*      */ 
/* 1234 */       String controlEnclosure2 = controlEnclosureSplit[2].trim().toString();
/* 1235 */       String[] controlEnclosureSplit2 = controlEnclosure2.split(",");
/* 1236 */       if (controlEnclosureSplit2[1].trim().equals("OK")) {
/* 1237 */         BoHostDevice hostDevice = this.boHostDeviceService.controlEnclosure(devId, userCode, controlEnclosureSplit2[0].trim().toString());
/* 1238 */         if (hostDevice == null) {
/* 1239 */           BoHostDevice boHostDevice = new BoHostDevice();
/* 1240 */           boHostDevice.setDeviceAddress(devId);
/* 1241 */           boHostDevice.setPushSet("");
/* 1242 */           boHostDevice.setBoDevice(boDevice);
/* 1243 */           boHostDevice.setDeviceNum(Integer.valueOf(1));
/* 1244 */           boHostDevice.setDeviceType(controlEnclosureSplit2[0].trim().toString());
/* 1245 */           boHostDevice.setDeviceNum(Integer.valueOf(1));
/* 1246 */           boHostDevice.setWhetherQueryStateSign("");
/* 1247 */           boHostDevice.setBoUsers(boUsers);
/* 1248 */           boHostDevice.setValidationCode("");
/* 1249 */           boHostDevice.setDeviceClassify(Boolean.valueOf(false));
/* 1250 */           boHostDevice.setNickName("");
/* 1251 */           boHostDevice.setIco("控制盒");
/* 1252 */           boHostDevice.setMntDelete("N");
/* 1253 */           BoHostDevice save = (BoHostDevice)this.boHostDeviceService.save(boHostDevice);
/*      */           try
/*      */           {
/* 1256 */             Thread.sleep(1500L);
/* 1257 */             List list = this.boControlEnclosureService.controlEnclosure(devId, userCode, save.getId());
/*      */             String SENDs;
///*      */             String SENDs;
/* 1259 */             if (controlEnclosureSplit2[0].trim().toString().equals("8"))
/* 1260 */               SENDs = controlEnclosureSplit2[0].trim().toString();
/*      */             else {
/* 1262 */               SENDs = "6";
/*      */             }
/* 1264 */             if (list.size() <= 0) {
/* 1265 */               Integer Num = Integer.valueOf(controlEnclosureSplit2[0].trim().toString());
/* 1266 */               int count = 0;
/* 1267 */               for (int j = 0; j < Num.intValue(); j++) {
/* 1268 */                 count++;
/* 1269 */                 int c = j + 1;
/* 1270 */                 BoControlEnclosure s = new BoControlEnclosure();
/* 1271 */                 s.setBoDevice(boDevice);
/* 1272 */                 s.setBoHostDevice(save);
/* 1273 */                 s.setState("0");
/* 1274 */                 s.setBoUsers(boUsers);
/* 1275 */                 s.setDeviceAddress(c+"");
/* 1276 */                 s.setNickName("第" + c + "组");
/* 1277 */                 this.boControlEnclosureService.save(s);
/*      */               }
/*      */             }
/* 1280 */             List list2 = this.boSensorService.gets(userCode, devId);
/* 1281 */             System.err.println(list2.size());
/*      */ 
/* 1283 */             if (list2.size() <= 0) {
/* 1284 */               Integer Nums = Integer.valueOf(SENDs);
/* 1285 */               int count = 0;
/* 1286 */               for (int j = 0; j < Nums.intValue(); j++) {
/* 1287 */                 count++;
/* 1288 */                 int c = j + 1;
/* 1289 */                 BoSensor s = new BoSensor();
/* 1290 */                 s.setBoDevice(boDevice);
/* 1291 */                 s.setBoUsers(boUsers);
/* 1292 */                 s.setBoModel(null);
/* 1293 */                 s.setDeviceClassify(Boolean.valueOf(false));
/* 1294 */                 s.setDeviceType("315");
/* 1295 */                 s.setType("1");
/* 1296 */                 s.setStartTimeOne("0");
/* 1297 */                 s.setEndTimeOne("0");
/* 1298 */                 s.setPushContent("");
/* 1299 */                 s.setSecurityOneType("0");
/* 1300 */                 s.setStartTimeTwo("0");
/* 1301 */                 s.setEndTimeTwo("0");
/* 1302 */                 s.setSecurityTwoType("0");
/* 1303 */                 s.setStartTimeThree("0");
/* 1304 */                 s.setEndTimeThree("0");
/* 1305 */                 s.setSecurityThreeType("0");
/* 1306 */                 s.setSecurityType("0");
/* 1307 */                 s.setIco("8|32");
/* 1308 */                 s.setNickName("第" + c + "个");
/* 1309 */                 s.setDeviceAddress(c+"");
/* 1310 */                 this.boSensorService.save(s);
/*      */               }
/*      */             }
/*      */           }
/*      */           catch (InterruptedException e) {
/* 1315 */             e.printStackTrace();
/*      */           }
/*      */ 
/*      */         }
/*      */ 
/*      */       }
/*      */ 
/*      */     }
/*      */ 
/* 1326 */     String scanHost = new String(this.dataClone);
/* 1327 */     String[] scanHostSplit = scanHost.split("-");
/*      */ 
/* 1329 */     if (("ZIGBEE_SCAN".equals(scanHostSplit[0])) && 
/* 1330 */       ("DEVEICE".equals(scanHostSplit[1]))) {
/* 1331 */       String[] array = (String[])StaticUtil.drik.get(devId + "_A");
/*      */ 
/* 1333 */       String userCode = "";
/* 1334 */       if (array != null) {
/* 1335 */         userCode = array[0];
/* 1336 */         BoDevice boDevice = this.wdDeviceService.findByCode(devId);
/* 1337 */         BoUsers boUsers = this.boUserServicess.findByUserUserCode(userCode);
/* 1338 */         String scanHost2 = scanHostSplit[2];
/* 1339 */         System.err.println(scanHost2);
/* 1340 */         if (scanHost2.contains(";")) {
/* 1341 */           String[] scanHostSplit2 = scanHost2.split(";");
/* 1342 */           String scanHost3 = null;
/* 1343 */           for (int i = 0; i < scanHostSplit2.length; i++) {
/* 1344 */             scanHost3 = scanHostSplit2[i];
/* 1345 */             String[] split = scanHost3.split(",");
/* 1346 */             System.err.println("截取" + split[0]);
/* 1347 */             if (split[0].trim().toString().equals("1003")) {
/* 1348 */               System.err.println("pm25");
/* 1349 */               BoHostDevice boHostDevice = this.boHostDeviceService.findBydeviceAddress(userCode, split[1]);
/* 1350 */               if (boHostDevice == null) {
/* 1351 */                 BoHostDevice s = new BoHostDevice();
/* 1352 */                 s.setDeviceAddress(split[1]);
/* 1353 */                 s.setBoDevice(boDevice);
/* 1354 */                 s.setPushSet("");
/* 1355 */                 s.setDeviceNum(Integer.valueOf(split[2]));
/* 1356 */                 s.setDeviceType("6");
/* 1357 */                 s.setDeviceNum(Integer.valueOf(1));
/* 1358 */                 s.setWhetherQueryStateSign("Y");
/* 1359 */                 s.setBoUsers(boUsers);
/* 1360 */                 s.setState("0");
/* 1361 */                 s.setValidationCode("");
/* 1362 */                 s.setDeviceClassify(Boolean.valueOf(false));
/* 1363 */                 s.setNickName("");
/* 1364 */                 s.setIco("pm25");
/* 1365 */                 s.setMntDelete("N");
/* 1366 */                 this.boHostDeviceService.save(s);
/*      */               }
/* 1368 */             } else if (split[0].trim().toString().equals("3001")) {
/* 1369 */               System.err.println("-----");
/* 1370 */               System.err.println("split[0] " + split[0].trim().toString());
/* 1371 */               System.err.println("split[1] " + split[1].trim().toString());
/* 1372 */               if (split[2].trim().toString().equals("1")) {
/* 1373 */                 BoAirBindingPanel boAirBindingPanel = this.boAirBindingPanelService.findBydeviceAddress(userCode, split[1].trim().toString());
/* 1374 */                 if (boAirBindingPanel == null) {
/* 1375 */                   System.err.println("LKSALDKAS");
/* 1376 */                   BoAirBindingPanel s = new BoAirBindingPanel();
/* 1377 */                   s.setBoUsers(boUsers);
/* 1378 */                   s.setBoDevice(boDevice);
/* 1379 */                   s.setPanelAddress(split[1]);
/* 1380 */                   s.setDeviceAddress("");
/* 1381 */                   s.setLogoWhetherIsBound("N");
/* 1382 */                   s.setPatternType("1");
/*      */ 
/* 1384 */                   this.boAirBindingPanelService.save(s);
/*      */                 }
/* 1386 */               } else if (split[2].trim().toString().equals("2")) {//空调  --DeviceType:501
/* 1387 */                 BoHostDevice boHostDevice = this.boHostDeviceService.findBydeviceAddress(userCode, split[1].trim().toString());
/* 1388 */                 if (boHostDevice == null) {
/* 1389 */                   System.err.println("LKSALDKAS");
/* 1390 */                   BoHostDevice s = new BoHostDevice();
/* 1391 */                   s.setDeviceAddress(split[1]);
/* 1392 */                   s.setBoDevice(boDevice);
/* 1393 */                   s.setDeviceNum(Integer.valueOf(split[2]));
/* 1394 */                   s.setDeviceType("501");
/* 1395 */                   s.setDeviceNum(Integer.valueOf(1));
/* 1396 */                   s.setPushSet("");
/* 1397 */                   s.setWhetherQueryStateSign("");
/* 1398 */                   s.setBoUsers(boUsers);
/* 1399 */                   s.setValidationCode("");
/* 1400 */                   s.setDeviceClassify(Boolean.valueOf(false));
/* 1401 */                   s.setNickName("");
/* 1402 */                   s.setIco("空调");
/* 1403 */                   s.setState("0");
/* 1404 */                   s.setMntDelete("N");
/* 1405 */                   this.boHostDeviceService.save(s);
/*      */                 }
/*      */               }
/* 1408 */             } else if (split[0].trim().toString().equals("1")) {
/* 1409 */               Integer Num = Integer.valueOf(split[2].trim().toString());
/* 1410 */               if (Num.intValue() == 1) {
/* 1411 */                 int count = 0;
/* 1412 */                 for (int j = 0; j < Num.intValue(); j++) {
/* 1413 */                   count++;
/* 1414 */                   int c = j + 1;
/*      */ 
/* 1416 */                   BoHostDevice boHostDevice = this.boHostDeviceService.findBydeviceAddress(userCode, split[1] + c);
/* 1417 */                   if (boHostDevice == null) {
/* 1418 */                     BoHostDevice s = new BoHostDevice();
/* 1419 */                     System.err.println(count++);
/* 1420 */                     s.setDeviceAddress(split[1] + c);
/* 1421 */                     s.setBoDevice(boDevice);
/* 1422 */                     s.setPushSet("");
/* 1423 */                     s.setDeviceNum(Integer.valueOf(split[2]));
/* 1424 */                     s.setDeviceType(split[0].trim().toString());
/* 1425 */                     s.setDeviceNum(Integer.valueOf(1));
/* 1426 */                     s.setBoUsers(boUsers);
/* 1427 */                     s.setValidationCode("");
/* 1428 */                     s.setDeviceClassify(Boolean.valueOf(false));
/* 1429 */                     s.setWhetherQueryStateSign("Y");
/* 1430 */                     s.setNickName("");
/* 1431 */                     s.setState("0");
/* 1432 */                     s.setIco("双向灯");
/* 1433 */                     s.setMntDelete("N");
/* 1434 */                     this.boHostDeviceService.save(s);
/*      */                   }
/*      */                 }
/*      */               }
/* 1438 */               else if (Num.intValue() == 2) {
/* 1439 */                 int count = 0;
/* 1440 */                 for (int j = 0; j < Num.intValue(); j++) {
/* 1441 */                   count++;
/* 1442 */                   int c = j + 1;
/*      */ 
/* 1444 */                   BoHostDevice boHostDevice = this.boHostDeviceService.findBydeviceAddress(userCode, split[1] + c);
/* 1445 */                   if (boHostDevice == null) {
/* 1446 */                     BoHostDevice s = new BoHostDevice();
/* 1447 */                     System.err.println(count++);
/* 1448 */                     s.setDeviceAddress(split[1] + c);
/* 1449 */                     s.setBoDevice(boDevice);
/* 1450 */                     s.setPushSet("");
/* 1451 */                     s.setDeviceNum(Integer.valueOf(split[2]));
/* 1452 */                     s.setDeviceType(split[0].trim().toString());
/* 1453 */                     s.setDeviceNum(Integer.valueOf(1));
/* 1454 */                     s.setWhetherQueryStateSign("Y");
/* 1455 */                     s.setBoUsers(boUsers);
/* 1456 */                     s.setValidationCode("");
/* 1457 */                     s.setDeviceClassify(Boolean.valueOf(false));
/* 1458 */                     s.setState("0");
/* 1459 */                     s.setNickName("");
/* 1460 */                     s.setIco("双向灯");
/* 1461 */                     s.setMntDelete("N");
/* 1462 */                     this.boHostDeviceService.save(s);
/*      */                   }
/*      */                 }
/*      */               }
/* 1466 */               else if (Num.intValue() == 3) {
/* 1467 */                 int count = 0;
/* 1468 */                 for (int j = 0; j < Num.intValue(); j++) {
/* 1469 */                   count++;
/* 1470 */                   int c = j + 1;
/*      */ 
/* 1472 */                   BoHostDevice boHostDevice = this.boHostDeviceService.findBydeviceAddress(userCode, split[1] + c);
/* 1473 */                   if (boHostDevice == null) {
/* 1474 */                     BoHostDevice s = new BoHostDevice();
/* 1475 */                     System.err.println(count++);
/* 1476 */                     s.setDeviceAddress(split[1] + c);
/* 1477 */                     s.setBoDevice(boDevice);
/* 1478 */                     s.setPushSet("");
/* 1479 */                     s.setDeviceNum(Integer.valueOf(split[2]));
/* 1480 */                     s.setDeviceType(split[0].trim().toString());
/* 1481 */                     s.setDeviceNum(Integer.valueOf(1));
/* 1482 */                     s.setWhetherQueryStateSign("Y");
/* 1483 */                     s.setBoUsers(boUsers);
/* 1484 */                     s.setValidationCode("");
/* 1485 */                     s.setState("0");
/* 1486 */                     s.setDeviceClassify(Boolean.valueOf(false));
/* 1487 */                     s.setNickName("");
/* 1488 */                     s.setIco("双向灯");
/* 1489 */                     s.setMntDelete("N");
/* 1490 */                     this.boHostDeviceService.save(s);
/*      */                   }
/*      */                 }
/*      */               }
/*      */             }
/* 1495 */             else if (split[0].trim().toString().equals("2")) {
/* 1496 */               BoHostDevice boHostDevice = this.boHostDeviceService.findBydeviceAddress(userCode, split[1]);
/* 1497 */               if (boHostDevice == null) {
/* 1498 */                 BoHostDevice s = new BoHostDevice();
/* 1499 */                 s.setDeviceAddress(split[1]);
/* 1500 */                 s.setBoDevice(boDevice);
/* 1501 */                 s.setPushSet("");
/* 1502 */                 s.setDeviceNum(Integer.valueOf(split[2]));
/* 1503 */                 s.setDeviceType("2");
/* 1504 */                 s.setDeviceNum(Integer.valueOf(1));
/* 1505 */                 s.setWhetherQueryStateSign("Y");
/* 1506 */                 s.setBoUsers(boUsers);
/* 1507 */                 s.setState("0");
/* 1508 */                 s.setValidationCode("");
/* 1509 */                 s.setDeviceClassify(Boolean.valueOf(false));
/* 1510 */                 s.setNickName("");
/* 1511 */                 s.setIco("窗帘");
/* 1512 */                 s.setMntDelete("N");
/* 1513 */                 this.boHostDeviceService.save(s);
/*      */               }
/*      */             }
/* 1516 */             else if (split[0].trim().toString().equals("4")) {
/* 1517 */               BoHostDevice boHostDevice = this.boHostDeviceService.findBydeviceAddress(userCode, split[1]);
/* 1518 */               if (boHostDevice == null) {
/* 1519 */                 BoHostDevice s = new BoHostDevice();
/* 1520 */                 s.setDeviceAddress(split[1]);
/* 1521 */                 s.setBoDevice(boDevice);
/* 1522 */                 s.setDeviceNum(Integer.valueOf(split[2]));
/* 1523 */                 s.setDeviceType("4");
/* 1524 */                 s.setPushSet("");
/* 1525 */                 s.setDeviceNum(Integer.valueOf(1));
/* 1526 */                 s.setWhetherQueryStateSign("Y");
/* 1527 */                 s.setBoUsers(boUsers);
/* 1528 */                 s.setValidationCode("");
/* 1529 */                 s.setState("0");
/* 1530 */                 s.setDeviceClassify(Boolean.valueOf(false));
/* 1531 */                 s.setNickName("");
/* 1532 */                 s.setIco("调光灯泡");
/* 1533 */                 s.setMntDelete("N");
/* 1534 */                 this.boHostDeviceService.save(s);
/*      */               }
/*      */             }
/* 1537 */             else if (split[0].trim().toString().equals("5")) {//智能门锁
	                     logger.info("split[1].trim().toString() ="+split[1].trim().toString());
	                     System.out.println("split[1] 1419L:"+split[1]);
/* 1538 */               BoHostDevice boHostDevice = this.boHostDeviceService.findBydeviceAddress(userCode, split[1].trim().toString());
                         System.out.println("boHostDevice 1422L:"+boHostDevice);
/* 1539 */               BoLockVerdict boLockVerdict = this.boLockVerdictService.findLock(split[1].trim().toString());
						 System.out.println("boLockVerdict 1423L:"+boHostDevice);
/* 1540 */               if (boHostDevice == null) {
/* 1541 */                 System.err.println("智能门锁 --进了");
/* 1542 */                 BoHostDevice s = new BoHostDevice();
/* 1543 */                 s.setDeviceAddress(split[1]);
/* 1544 */                 s.setBoDevice(boDevice);
/* 1545 */                 s.setDeviceNum(Integer.valueOf(split[2]));
/* 1546 */                 s.setDeviceType("5");//5？ 5314？
/* 1547 */                 s.setPushSet("0");
//						   s.setPushSet("");
/* 1548 */                 s.setDeviceNum(Integer.valueOf(1));
/* 1549 */                 s.setWhetherQueryStateSign("");
//						   s.setWhetherQueryStateSign("Y");
/* 1550 */                 s.setBoUsers(boUsers);
/* 1551 */                 s.setState("");
//						   s.setState("0");
/* 1552 */                 s.setValidationCode("");
/* 1553 */                 s.setDeviceClassify(Boolean.valueOf(false));
/* 1554 */                 s.setNickName("");
/* 1555 */                 s.setIco("智能门锁");
/* 1556 */                 s.setMntDelete("N");
/* 1557 */                 this.boHostDeviceService.save(s);
/*      */               }
/*      */ 
/* 1560 */               if (boLockVerdict == null) {
/* 1561 */                 BoLockVerdict lockVerdict = new BoLockVerdict();
/* 1562 */                 lockVerdict.setBoDevice(boDevice);
/* 1563 */                 lockVerdict.setLockAddress(split[1]);
/* 1564 */                 lockVerdict.setStatus("0");
/* 1565 */                 lockVerdict.setLockTimes("");
/* 1566 */                 lockVerdict.setUnlockTimes("");
/* 1567 */                 this.boLockVerdictService.save(lockVerdict);
/*      */               }
/* 1569 */             } else if (split[0].trim().toString().equals("8")) {
/* 1570 */               BoHostDevice boHostDevice = this.boHostDeviceService.findBydeviceAddress(userCode, split[1]);
/* 1571 */               if (boHostDevice == null) {
/* 1572 */                 BoHostDevice s = new BoHostDevice();
/* 1573 */                 s.setDeviceAddress(split[1]);
/* 1574 */                 s.setBoDevice(boDevice);
/* 1575 */                 s.setDeviceNum(Integer.valueOf(split[2]));
/* 1576 */                 s.setDeviceType("99");
/* 1577 */                 s.setPushSet("");
/* 1578 */                 s.setDeviceNum(Integer.valueOf(1));
/* 1579 */                 s.setState("");
/* 1580 */                 s.setWhetherQueryStateSign("");
/* 1581 */                 s.setBoUsers(boUsers);
/* 1582 */                 s.setValidationCode("");
/* 1583 */                 s.setDeviceClassify(Boolean.valueOf(false));
/* 1584 */                 s.setNickName("");
/* 1585 */                 s.setIco("红外");
/* 1586 */                 s.setMntDelete("N");
/* 1587 */                 this.boHostDeviceService.save(s);
/*      */               }
/*      */             }
/*      */           }
/*      */         } else {
/* 1592 */           String[] split = scanHost2.split(",");
/* 1593 */           if (split[0].trim().toString().equals("1003")) {
/* 1594 */             System.err.println("pm25");
/* 1595 */             BoHostDevice boHostDevice = this.boHostDeviceService.findBydeviceAddress(userCode, split[1]);
/* 1596 */             if (boHostDevice == null) {
/* 1597 */               BoHostDevice s = new BoHostDevice();
/* 1598 */               s.setDeviceAddress(split[1]);
/* 1599 */               s.setBoDevice(boDevice);
/* 1600 */               s.setPushSet("");
/* 1601 */               s.setDeviceNum(Integer.valueOf(split[2]));
/* 1602 */               s.setDeviceType("6");
/* 1603 */               s.setDeviceNum(Integer.valueOf(1));
/* 1604 */               s.setWhetherQueryStateSign("Y");
/* 1605 */               s.setBoUsers(boUsers);
/* 1606 */               s.setState("0");
/* 1607 */               s.setValidationCode("");
/* 1608 */               s.setDeviceClassify(Boolean.valueOf(false));
/* 1609 */               s.setNickName("");
/* 1610 */               s.setIco("pm25");
/* 1611 */               s.setMntDelete("N");
/* 1612 */               this.boHostDeviceService.save(s);
/*      */             }
/*      */ 
/*      */           }
/*      */ 
/*      */         }
/*      */ 
/*      */       }
/*      */ 
/*      */     }
/*      */ 
/* 1648 */     String hostNetworkNumber = new String(this.dataClone);
/* 1649 */     String[] hostNetworkNumberSplit = hostNetworkNumber.split("-");
/* 1650 */     if (("ZIGBEE_CONFIG2".equals(hostNetworkNumberSplit[0].trim().toString())) && 
/* 1651 */       ("READ".equals(hostNetworkNumberSplit[1].trim().toString())))
/*      */     {
/* 1653 */       String hostNetworkNumber2 = hostNetworkNumberSplit[2].trim().toString();
/* 1654 */       String[] hostNetworkNumberSplit2 = hostNetworkNumber2.split(",");
/* 1655 */       if ("OI".equals(hostNetworkNumberSplit2[0].trim().toString())) {
/* 1656 */         for (int i = 0; i < hostNetworkNumberSplit2.length; i++) {
/* 1657 */           System.err.println("截取 " + hostNetworkNumberSplit2[i]);
/*      */         }
/* 1659 */         if ("OK".equals(hostNetworkNumberSplit2[2].trim().toString())) {
/* 1660 */           System.err.println("走了");
/* 1661 */           StaticUtil.NETWORK.put(devId, hostNetworkNumberSplit2[1].trim().toString());
/*      */         }
/*      */       }
/*      */ 
/*      */     }
/*      */ 
/* 1667 */     String channel = new String(this.dataClone);
/* 1668 */     String[] channelSplit = channel.split("-");
/* 1669 */     if (("ZIGBEE_CONFIG2".equals(channelSplit[0].trim().toString())) && 
/* 1670 */       ("READ".equals(channelSplit[1].trim().toString())))
/*      */     {
/* 1672 */       String channel2 = channelSplit[2].trim().toString();
/* 1673 */       String[] channelSplit2 = channel2.split(",");
/* 1674 */       if (("CH".equals(channelSplit2[0].trim().toString())) && 
/* 1675 */         ("OK".equals(channelSplit2[2].trim().toString()))) {
/* 1676 */         System.err.println("走了");
/* 1677 */         StaticUtil.CHANNEL.put(devId, channelSplit2[1].trim().toString());
/*      */       }
/*      */ 
/*      */     }
/*      */ 
/* 1683 */     String deviceChannel = new String(this.dataClone);
/* 1684 */     String[] deviceChannelSplit = deviceChannel.split("-");
/* 1685 */     if (("ZIGBEE_CONFIG2".equals(deviceChannelSplit[0].trim().toString())) && 
/* 1686 */       ("READ".equals(deviceChannelSplit[1].trim().toString())))
/*      */     {
/* 1688 */       String deviceChannel2 = deviceChannelSplit[2].trim().toString();
/* 1689 */       String[] deviceChannelSplit2 = deviceChannel2.split(",");
/* 1690 */       if (("CN".equals(deviceChannelSplit2[0].trim().toString())) && 
/* 1691 */         ("OK".equals(deviceChannelSplit2[2].trim().toString()))) {
/* 1692 */         System.err.println("走了");
/* 1693 */         BoDevice boDevice = this.boDeviceService.findByCode(devId);
/* 1694 */         BoUsers boUsers = this.boUserServicess.findByUserUserCode(getUserCode());
/*      */ 
/* 1696 */         BoChannel boChannel = new BoChannel();
/* 1697 */         boChannel.setBoDevice(boDevice);
/* 1698 */         boChannel.setBoUsers(boUsers);
/* 1699 */         boChannel.setChannel(deviceChannelSplit2[1].trim().toString());
/* 1700 */         boChannel.setDeviceOrHost("2");
/* 1701 */         this.boChannelberService.save(boChannel);
/*      */       }
/*      */ 
/*      */     }
/*      */ 
/* 1707 */     String sensorAlarm = new String(this.dataClone);
/* 1708 */     String[] sensorAlarmSplit = sensorAlarm.split("-");
/* 1709 */     if (("RFSTUY_315M".equals(sensorAlarmSplit[0].trim().toString())) && 
/* 1710 */       ("ALARM".equals(sensorAlarmSplit[1].trim().toString()))) {
/* 1711 */       System.err.println("走了");
/* 1712 */       String sensorAlarm2 = sensorAlarmSplit[2].trim().toString();
/* 1713 */       String[] sensorAlarmSplit2 = sensorAlarm2.split(",");
/* 1714 */       String str = "RFSTUY_315M-ALARM-" + sensorAlarmSplit2[0].trim().toString() + ",OK";
/* 1715 */       byte[] bs = str.getBytes();
/* 1716 */       this.packetProcessHelper.processSendDDatas(devId, bs);
/* 1717 */       BoSensor ssss = this.boSensorService.find(devId, sensorAlarmSplit2[0]);
/* 1718 */       Integer type = Integer.valueOf(ssss.getType());
				 logger.info("ssss.getSecurityType()>>"+ssss.getSecurityType());
/* 1719 */       switch (type.intValue()) {
/*      */       case 1:
/* 1721 */         if (ssss.getSecurityType().equals("1")) {
/* 1722 */           boolean inDateOno = isInDate(new Date(), ssss.getStartTimeOne(), ssss.getEndTimeOne(), ssss.getSecurityOneType());
/* 1723 */           boolean inDateTwo = isInDate(new Date(), ssss.getStartTimeTwo(), ssss.getEndTimeTwo(), ssss.getSecurityTwoType());
/* 1724 */           boolean inDateThree = isInDate(new Date(), ssss.getStartTimeThree(), ssss.getEndTimeThree(), ssss.getSecurityThreeType());
/* 1725 */           if (inDateOno) {
/*      */             try {
/* 1727 */               Thread.sleep(600L);
/* 1728 */               sendGet2("http://120.77.250.17:8080/smarthome.IMCPlatform/xingUser/push.action?deviceCode=" + devId + "&" + "deviceAddress=" + sensorAlarmSplit2[0]);
/*      */             }
/*      */             catch (InterruptedException e) {
/* 1731 */               e.printStackTrace();
/*      */             }
/*      */             try {
/* 1734 */               Thread.sleep(500L);
/* 1735 */               sendGet("http://120.77.250.17:8080/smarthome.IMCPlatform/xingUser/commandmodel.action?modelId=" + ssss.getBoModel().getModelId(), ssss.getBoUsers().getUserCode());//安防
/*      */             }
/*      */             catch (InterruptedException e) {
/* 1738 */               e.printStackTrace();
/*      */             }
/*      */           }
/* 1741 */           else if (inDateTwo) {
/*      */             try {
/* 1743 */               Thread.sleep(600L);
/* 1744 */               sendGet2("http://120.77.250.17:8080/smarthome.IMCPlatform/xingUser/push.action?deviceCode=" + devId + "&" + "deviceAddress=" + sensorAlarmSplit2[0]);
/*      */             }
/*      */             catch (InterruptedException e) {
/* 1747 */               e.printStackTrace();
/*      */             }
/*      */             try {
/* 1750 */               Thread.sleep(500L);
						 System.out.println("sendGet http://120.77.250.17:8080/smarthome.IMCPlatform/xingUser/commandmodel.action?modelId="+ssss.getBoModel().getModelId());//sendGet
/* 1751 */               sendGet("http://120.77.250.17:8080/smarthome.IMCPlatform/xingUser/commandmodel.action?modelId=" + ssss.getBoModel().getModelId(), ssss.getBoUsers().getUserCode());
/*      */             }
/*      */             catch (InterruptedException e) {
/* 1754 */               e.printStackTrace();
/*      */             }
/*      */           }
/* 1757 */           else if (inDateThree) {
/*      */             try {
/* 1759 */               Thread.sleep(600L);
                         System.out.println("sendGet2 http://120.77.250.17:8080/smarthome.IMCPlatform/xingUser/push.action?deviceCode="+devId + "&" + "deviceAddress=" + sensorAlarmSplit2[0]);
/* 1760 */               sendGet2("http://120.77.250.17:8080/smarthome.IMCPlatform/xingUser/push.action?deviceCode=" + devId + "&" + "deviceAddress=" + sensorAlarmSplit2[0]);
/*      */             }
/*      */             catch (InterruptedException e) {
/* 1763 */               e.printStackTrace();
/*      */             }
/*      */             try {
/* 1766 */               Thread.sleep(500L);
                         System.out.println("sendGet http://120.77.250.17:8080/smarthome.IMCPlatform/xingUser/commandmodel.action?modelId="+ssss.getBoModel().getModelId());//sendGet
/* 1767 */               sendGet("http://120.77.250.17:8080/smarthome.IMCPlatform/xingUser/commandmodel.action?modelId=" + ssss.getBoModel().getModelId(), ssss.getBoUsers().getUserCode());
/*      */             }
/*      */             catch (InterruptedException e) {
/* 1770 */               e.printStackTrace();
/*      */             }
/*      */           } else {
/* 1773 */             System.err.println("< " + ssss.getBoUsers().getUserPhone() + " 用户的 " + ssss.getNickName() + " > " + " 传感器第一个时间第二个时间第三个时间都是撤防或不再当前时间里");
/*      */           }
/*      */         } else {
/* 1776 */           logger.info("< " + ssss.getBoUsers().getUserPhone() + " 用户的 " + ssss.getNickName() + " > " + " 传感器 总开关是 关的");
/* 1777 */           System.err.println("< " + ssss.getBoUsers().getUserPhone() + " 用户的 " + ssss.getNickName() + " > " + " 传感器 总开关是 关的");
/*      */         }
/* 1779 */         break;
/*      */       default:
///* 1781 */         System.err.println("sendGet http://120.77.250.17:8080/smarthome.IMCPlatform/xingUser/commandmodel.action?modelId="+ssss.getBoModel().getModelId());
/* 1782 */         sendGet("http://120.77.250.17:8080/smarthome.IMCPlatform/xingUser/commandmodel.action?modelId=" + ssss.getBoModel().getModelId(), ssss.getBoUsers().getUserCode());//sendGet
				   break;
/*      */       }
/*      */ 
/*      */     }
/*      */ 
/* 1796 */     String infraredLearn = new String(this.dataClone);
/* 1797 */     String[] infraredLearnSplit = infraredLearn.split("-");
/*      */ 
/* 1799 */     if (("ZIGBEE_INFRARED".equals(infraredLearnSplit[0])) && 
/* 1800 */       ("STUDY".equals(infraredLearnSplit[1]))) {
/* 1801 */       String infraredLearn2 = infraredLearnSplit[2];
/* 1802 */       String[] infraredLearnSplit2 = infraredLearn2.split(",");
/* 1803 */       if ("OK".equals(infraredLearnSplit2[4].trim().toString())) {
/* 1804 */         for (int i = 0; i < infraredLearnSplit2.length; i++) {
/* 1805 */           System.err.println("截取" + infraredLearnSplit2[i]);
/*      */         }
/* 1807 */         this.resendVerification = this.boResendVerificationService.find(devId, infraredLearnSplit2[1].trim(), 
/* 1808 */           infraredLearnSplit2[2].trim());
/* 1809 */         if (this.resendVerification != null) {
/* 1810 */           this.resendVerification.setAcceptState("OK");
/* 1811 */           this.boResendVerificationService.update(this.resendVerification);
/*      */         }
/*      */ 
/*      */       }
/*      */ 
/*      */     }
/*      */ 
/* 1820 */     String radioCommand = new String(this.dataClone);
               logger.info("············radioCommand:"+radioCommand);//PT1527_315M-SEND-0,33,788925101,OK
/* 1821 */     String[] radioCommandSplit = radioCommand.split("-");//PT1527_315M SEND 0,33,788925101,OK 
 
/*      */ 
/* 1823 */     if ("PT2262_315M".equals(radioCommandSplit[0])) {
/* 1824 */       if ("SEND".equals(radioCommandSplit[1])) {
/* 1825 */         String radioCommand2 = radioCommandSplit[2];
/* 1826 */         String[] radioCommandSplit2 = radioCommand2.split(",");//0 33 788925101 OK
/* 1827 */         for (int i = 0; i < radioCommandSplit2.length; i++) {
/* 1828 */           System.err.println("截取" + radioCommandSplit2[i]);
/*      */         }
/* 1830 */         if ("OK".equals(radioCommandSplit2[3].trim().toString()))
/*      */         {
/* 1832 */           this.resendVerification = this.boResendVerificationService.find(devId, radioCommandSplit2[2].trim(), "0");
/* 1833 */           if (this.resendVerification != null) {
///* 1834 */             System.err.println("2121212");
					   logger.info("PT2262_315");
/* 1835 */             this.resendVerification.setAcceptState("OK");
/* 1836 */             this.boResendVerificationService.update(this.resendVerification);
/*      */           } else {
/* 1838 */             System.err.println(devId);
/* 1839 */             System.err.println(radioCommandSplit2[2].trim());
///* 1840 */             System.err.println("meij2262");
						 logger.info("meiJ2262-315");
/*      */           }
/*      */         }
/*      */       }
/* 1844 */     } else if ("PT2262_433M".equals(radioCommandSplit[0])) {
/* 1845 */       if ("SEND".equals(radioCommandSplit[1])) {
				   logger.info("PT2262_433");
/* 1846 */         String radioCommand2 = radioCommandSplit[2];
/* 1847 */         String[] radioCommandSplit2 = radioCommand2.split(",");
/* 1848 */         for (int i = 0; i < radioCommandSplit2.length; i++) {
/* 1849 */           System.err.println("截取" + radioCommandSplit2[i]);
/*      */         }
/* 1851 */         if ("OK".equals(radioCommandSplit2[3].trim().toString()))
/*      */         {
/* 1853 */           this.resendVerification = this.boResendVerificationService.find(devId, radioCommandSplit2[2].trim(), "0");
/* 1854 */           if (this.resendVerification != null) {
					   logger.info("meiJ2262-433");
/* 1855 */             this.resendVerification.setAcceptState("OK");
/* 1856 */             this.boResendVerificationService.update(this.resendVerification);
/*      */           }
/*      */         }
/*      */       }
/* 1860 */     } else if ("PT1527_315M".equals(radioCommandSplit[0])) {//门锁 进入了这里  有没有可能是#造成的    ??PTZNMS_315M
/* 1861 */       if ("SEND".equals(radioCommandSplit[1])) {
/* 1862 */         String radioCommand2 = radioCommandSplit[2];
/* 1863 */         String[] radioCommandSplit2 = radioCommand2.split(",");//0 33 788925101 OK
/* 1864 */         for (int i = 0; i < radioCommandSplit2.length; i++) {
/* 1865 */           System.err.println("截取" + radioCommandSplit2[i]);
/*      */         }
/* 1867 */         if ("OK".equals(radioCommandSplit2[3].trim().toString()))
/*      */         {
					 logger.info("·······devId:"+devId);
					 logger.info("·······radioCommandSplit2[2]:"+radioCommandSplit2[2]);
					 logger.info("·······radioCommandSplit2[0]:"+radioCommandSplit2[0]);//暂时将下面的"0"替换掉
/* 1869 */           this.resendVerification = this.boResendVerificationService.find(devId, radioCommandSplit2[2].trim(), "0");//deviceAddress=radioCommandSplit2[2].trim();radioCommandSplit2[2].trim().toString()代替devId;   command="0"  radioCommandSplit2[0].trim()
                     logger.info("···PacketProcessHelper this.resendVerification:"+this.resendVerification);//null
/* 1870 */           if (this.resendVerification != null) {
//					   logger.info("1527_315");
/* 1871 */             this.resendVerification.setAcceptState("OK");
/* 1872 */             this.boResendVerificationService.update(this.resendVerification);//这之后无法跳入commad方法
/*      */           }else {
	/* 1838 */             System.err.println(devId);
	/* 1839 */             System.err.println(radioCommandSplit2[2].trim());
//						   logger.info("meij1527_315");
	/*      */        }
/*      */         }
/*      */       }
/* 1876 */     } else if (("PT1527_433M".equals(radioCommandSplit[0])) && 
/* 1877 */       ("SEND".equals(radioCommandSplit[1]))) {
/* 1878 */       String radioCommand2 = radioCommandSplit[2];
/* 1879 */       String[] radioCommandSplit2 = radioCommand2.split(",");
/* 1880 */       for (int i = 0; i < radioCommandSplit2.length; i++) {
/* 1881 */         System.err.println("截取" + radioCommandSplit2[i]);
/*      */       }
/* 1883 */       if ("OK".equals(radioCommandSplit2[3].trim().toString()))
/*      */       {
/* 1885 */         this.resendVerification = this.boResendVerificationService.find(devId, radioCommandSplit2[2].trim(), "0");
/* 1886 */         if (this.resendVerification != null) {
//					 logger.info("1527_433");
/* 1887 */           this.resendVerification.setAcceptState("OK");
/* 1888 */           this.boResendVerificationService.update(this.resendVerification);
/*      */         }
/*      */       }
/*      */ 
/*      */     }

/*      */ 
/* 1894 */     String infraredCommand = new String(this.dataClone);
/* 1895 */     String[] infraredCommandSplit = infraredCommand.split("-");
/* 1896 */     if (("ZIGBEE_INFRARED".equals(infraredCommandSplit[0])) && 
/* 1897 */       ("SEND".equals(infraredCommandSplit[1]))) {
/* 1898 */       String infraredCommand2 = infraredCommandSplit[2];
/* 1899 */       String[] infraredCommandSplit2 = infraredCommand2.split(",");
/* 1900 */       if ("OK".equals(infraredCommandSplit2[4].trim().toString())) {
/* 1901 */         for (int i = 0; i < infraredCommandSplit2.length; i++) {
/* 1902 */           System.err.println("截取" + infraredCommandSplit2[i]);
/*      */         }
/* 1904 */         this.resendVerification = this.boResendVerificationService.find(devId, infraredCommandSplit2[1].trim(), 
/* 1905 */           infraredCommandSplit2[2].trim());
/* 1906 */         if (this.resendVerification != null) {
/* 1907 */           this.resendVerification.setAcceptState("OK");
/* 1908 */           this.boResendVerificationService.update(this.resendVerification);
/*      */         }
/* 1910 */       } else if ("ZIGBEE_ERR".equals(infraredCommandSplit2[4].trim().toString())) {
/* 1911 */         this.resendVerification = this.boResendVerificationService.find(devId, infraredCommandSplit2[1].trim(), 
/* 1912 */           infraredCommandSplit2[2].trim());
/* 1913 */         System.err.println("lll");
/* 1914 */         System.err.println("〉〉 " + this.resendVerification.getAcceptState());
/* 1915 */         if (this.resendVerification.getAcceptState().equals("wait")) {
/* 1916 */           this.resendVerification.setAcceptState("1");
/* 1917 */           this.boResendVerificationService.update(this.resendVerification);
/* 1918 */           System.err.println("走了");
/* 1919 */         } else if (this.resendVerification.getAcceptState().equals("1")) {
/* 1920 */           this.resendVerification.setAcceptState("2");
/* 1921 */           this.boResendVerificationService.update(this.resendVerification);
/* 1922 */           System.err.println("走了1");
/* 1923 */         } else if (this.resendVerification.getAcceptState().equals("2")) {
/* 1924 */           this.resendVerification.setAcceptState("ERR");
/* 1925 */           this.boResendVerificationService.update(this.resendVerification);
/* 1926 */           System.err.println("走了2");
/*      */         }
/* 1928 */       } else if ("ERROR".equals(infraredCommandSplit2[4].trim().toString()))
/*      */       {
/* 1930 */         System.err.println(devId + " <> " + infraredCommandSplit2[1].trim() + " <> " + 
/* 1931 */           infraredCommandSplit2[2].trim());
/* 1932 */         this.resendVerification = this.boResendVerificationService.find(devId, infraredCommandSplit2[1].trim(), 
/* 1933 */           infraredCommandSplit2[2].trim());
/* 1934 */         System.err.println("lll");
/* 1935 */         System.err.println("〉〉 " + this.resendVerification.getAcceptState());
/* 1936 */         if (this.resendVerification.getAcceptState().equals("wait")) {
/* 1937 */           this.resendVerification.setAcceptState("1");
/* 1938 */           this.boResendVerificationService.update(this.resendVerification);
/* 1939 */           System.err.println("走了");
/* 1940 */         } else if (this.resendVerification.getAcceptState().equals("1")) {
/* 1941 */           this.resendVerification.setAcceptState("2");
/* 1942 */           this.boResendVerificationService.update(this.resendVerification);
/* 1943 */           System.err.println("走了1");
/* 1944 */         } else if (this.resendVerification.getAcceptState().equals("2")) {
/* 1945 */           this.resendVerification.setAcceptState("ERR");
/* 1946 */           this.boResendVerificationService.update(this.resendVerification);
/* 1947 */           System.err.println("走了2");
/*      */         }
/*      */ 
/*      */       }
/*      */ 
/*      */     }
/*      */ 
/* 1956 */     String curtainCommand = new String(this.dataClone);
/* 1957 */     String[] curtainCommandSplit = curtainCommand.split("-");
/* 1958 */     if (("ZIGBEE_CURTAIN".equals(curtainCommandSplit[0])) && 
/* 1959 */       ("SEND".equals(curtainCommandSplit[1]))) {
/* 1960 */       String curtainCommand2 = curtainCommandSplit[2];
/* 1961 */       String[] curtainCommandSplit2 = curtainCommand2.split(",");
/*      */ 
/* 1963 */       if ("OK".equals(curtainCommandSplit2[4].trim().toString())) {
/* 1964 */         this.resendVerification = this.boResendVerificationService.find(devId, curtainCommandSplit2[1], 
/* 1965 */           curtainCommandSplit2[3].trim().toString());
/* 1966 */         if (this.resendVerification != null) {
/* 1967 */           this.resendVerification.setAcceptState("OK");
/* 1968 */           this.boResendVerificationService.update(this.resendVerification);
/*      */         }
/*      */ 
/* 1971 */         List<BoHostDevice> list = this.boHostDeviceService.getDeviceByAddress(curtainCommandSplit2[1]);
/* 1972 */         for (BoHostDevice boHostDevice : list) {
/* 1973 */           boHostDevice.setState(curtainCommandSplit2[3].trim().toString());
/* 1974 */           this.boHostDeviceService.update(boHostDevice);
/*      */         }
/*      */       }
/* 1977 */       else if ("ZIGBEE_ERR".equals(curtainCommandSplit2[4].trim().toString())) {
/* 1978 */         System.err.println(devId + " <>" + curtainCommandSplit2[1] + " <>" + 
/* 1979 */           curtainCommandSplit2[3].trim().toString());
/* 1980 */         this.resendVerification = this.boResendVerificationService.find(devId, curtainCommandSplit2[1], 
/* 1981 */           curtainCommandSplit2[3].trim().toString());
/* 1982 */         System.err.println("lll");
/* 1983 */         System.err.println("〉〉 " + this.resendVerification.getAcceptState());
/* 1984 */         if (this.resendVerification.getAcceptState().equals("wait")) {
/* 1985 */           this.resendVerification.setAcceptState("1");
/* 1986 */           this.boResendVerificationService.update(this.resendVerification);
/* 1987 */           System.err.println("走了");
/* 1988 */         } else if (this.resendVerification.getAcceptState().equals("1")) {
/* 1989 */           this.resendVerification.setAcceptState("2");
/* 1990 */           this.boResendVerificationService.update(this.resendVerification);
/* 1991 */           System.err.println("走了1");
/* 1992 */         } else if (this.resendVerification.getAcceptState().equals("2")) {
/* 1993 */           this.resendVerification.setAcceptState("ERR");
/* 1994 */           this.boResendVerificationService.update(this.resendVerification);
/* 1995 */           System.err.println("走了2");
/*      */         }
/*      */ 
/*      */       }
/*      */ 
/*      */     }
/*      */ 
/* 2003 */     String dimmingLightCommand = new String(this.dataClone);
/* 2004 */     String[] dimmingLightCommandsplit = dimmingLightCommand.split("-");
/* 2005 */     if (("ZIGBEE_DIMMER".equals(dimmingLightCommandsplit[0])) && 
/* 2006 */       ("SEND".equals(dimmingLightCommandsplit[1]))) {
/* 2007 */       String dimmingLightCommand2 = dimmingLightCommandsplit[2];
/* 2008 */       String[] dimmingLightCommandSplit2 = dimmingLightCommand2.split(",");
/* 2009 */       List<BoHostDevice> list = this.boHostDeviceService.getDeviceByAddress(dimmingLightCommandSplit2[1].trim().toString());
/* 2010 */       for (BoHostDevice boHostDevice : list) {
/* 2011 */         boHostDevice.setState(dimmingLightCommandSplit2[2].trim().toString());
/* 2012 */         this.boHostDeviceService.update(boHostDevice);
/*      */       }
/* 2014 */       if ("OK".equals(dimmingLightCommandSplit2[3].trim().toString())) {
/* 2015 */         this.resendVerification = this.boResendVerificationService.find(devId, dimmingLightCommandSplit2[1], 
/* 2016 */           dimmingLightCommandSplit2[3].trim().toString());
/* 2017 */         if (this.resendVerification != null) {
/* 2018 */           this.resendVerification.setAcceptState("OK");
/* 2019 */           this.boResendVerificationService.update(this.resendVerification);
/*      */         }
/* 2021 */       } else if ("ZIGBEE_ERR".equals(dimmingLightCommandSplit2[4].trim().toString())) {
/* 2022 */         this.resendVerification = this.boResendVerificationService.find(devId, dimmingLightCommandSplit2[1].trim(), 
/* 2023 */           dimmingLightCommandSplit2[2].trim());
/* 2024 */         System.err.println("lll");
/* 2025 */         System.err.println("〉〉 " + this.resendVerification.getAcceptState());
/* 2026 */         if (this.resendVerification.getAcceptState().equals("wait")) {
/* 2027 */           this.resendVerification.setAcceptState("1");
/* 2028 */           this.boResendVerificationService.update(this.resendVerification);
/* 2029 */           System.err.println("走了");
/* 2030 */         } else if (this.resendVerification.getAcceptState().equals("1")) {
/* 2031 */           this.resendVerification.setAcceptState("2");
/* 2032 */           this.boResendVerificationService.update(this.resendVerification);
/* 2033 */           System.err.println("走了1");
/* 2034 */         } else if (this.resendVerification.getAcceptState().equals("2")) {
/* 2035 */           this.resendVerification.setAcceptState("ERR");
/* 2036 */           this.boResendVerificationService.update(this.resendVerification);
/* 2037 */           System.err.println("走了2");
/*      */         }
/* 2039 */       } else if ("ERROR".equals(dimmingLightCommandSplit2[4].trim().toString()))
/*      */       {
/* 2041 */         System.err.println(devId + " <> " + dimmingLightCommandSplit2[1].trim() + " <> " + 
/* 2042 */           dimmingLightCommandSplit2[2].trim());
/* 2043 */         this.resendVerification = this.boResendVerificationService.find(devId, dimmingLightCommandSplit2[1].trim(), 
/* 2044 */           dimmingLightCommandSplit2[2].trim());
/* 2045 */         System.err.println("lll");
/* 2046 */         System.err.println("〉〉 " + this.resendVerification.getAcceptState());
/* 2047 */         if (this.resendVerification.getAcceptState().equals("wait")) {
/* 2048 */           this.resendVerification.setAcceptState("1");
/* 2049 */           this.boResendVerificationService.update(this.resendVerification);
/* 2050 */           System.err.println("走了");
/* 2051 */         } else if (this.resendVerification.getAcceptState().equals("1")) {
/* 2052 */           this.resendVerification.setAcceptState("2");
/* 2053 */           this.boResendVerificationService.update(this.resendVerification);
/* 2054 */           System.err.println("走了1");
/* 2055 */         } else if (this.resendVerification.getAcceptState().equals("2")) {
/* 2056 */           this.resendVerification.setAcceptState("ERR");
/* 2057 */           this.boResendVerificationService.update(this.resendVerification);
/* 2058 */           System.err.println("走了2");
/*      */         }
/*      */ 
/*      */       }
/*      */ 
/*      */     }
/*      */ 
/* 2066 */     String on_offLamp = new String(this.dataClone);
/* 2067 */     String[] on_offLampSplit = on_offLamp.split("-");
/* 2068 */     if (("ZIGBEE_LIGHT".equals(on_offLampSplit[0])) && 
/* 2069 */       ("SEND".equals(on_offLampSplit[1]))) {
/* 2070 */       String on_offLamp2 = on_offLampSplit[2];
/* 2071 */       String[] on_offLampSplit2 = on_offLamp2.split(",");
/* 2072 */       if ("OK".equals(on_offLampSplit2[4].trim().toString()))
/*      */       {
/* 2074 */         String ds = null;
/* 2075 */         if (on_offLampSplit2[2].equals("1"))
/* 2076 */           ds = on_offLampSplit2[1] + 1;
/* 2077 */         else if (on_offLampSplit2[2].equals("2"))
/* 2078 */           ds = on_offLampSplit2[1] + 2;
/* 2079 */         else if (on_offLampSplit2[2].equals("3")) {
/* 2080 */           ds = on_offLampSplit2[1] + 3;
/*      */         }
/* 2082 */         this.resendVerification = this.boResendVerificationService.find(devId, ds, on_offLampSplit2[3]);
/* 2083 */         if (this.resendVerification != null) {
/* 2084 */           this.resendVerification.setAcceptState("OK");
/* 2085 */           this.boResendVerificationService.update(this.resendVerification);
/*      */         }
/*      */ 
/* 2088 */         if (Integer.valueOf(on_offLampSplit2[2]).intValue() == 1) {
/* 2089 */           List<BoHostDevice> list = this.boHostDeviceService.getDeviceByAddress(on_offLampSplit2[1] + 1);
/* 2090 */           for (BoHostDevice boHostDevice : list) {
/* 2091 */             boHostDevice.setState(on_offLampSplit2[3].trim().toString());
/* 2092 */             this.boHostDeviceService.update(boHostDevice);
/*      */           }
/*      */ 
/*      */         }
/*      */ 
/* 2097 */         if (Integer.valueOf(on_offLampSplit2[2]).intValue() == 2) {
/* 2098 */           List<BoHostDevice> list = this.boHostDeviceService.getDeviceByAddress(on_offLampSplit2[1] + 2);
/* 2099 */           for (BoHostDevice boHostDevice : list) {
/* 2100 */             boHostDevice.setState(on_offLampSplit2[3].trim().toString());
/* 2101 */             this.boHostDeviceService.update(boHostDevice);
/*      */           }
/*      */ 
/*      */         }
/*      */ 
/* 2107 */         if (Integer.valueOf(on_offLampSplit2[2]).intValue() == 3) {
/* 2108 */           List<BoHostDevice> list = this.boHostDeviceService.getDeviceByAddress(on_offLampSplit2[1] + 3);
/* 2109 */           for (BoHostDevice boHostDevice : list) {
/* 2110 */             boHostDevice.setState(on_offLampSplit2[3].trim().toString());
/* 2111 */             this.boHostDeviceService.update(boHostDevice);
/*      */           }
/*      */         }
/*      */       }
/* 2115 */       else if (("ZIGBEE_ERR".equals(on_offLampSplit2[4].trim().toString())) || ("ERR".equals(on_offLampSplit2[4].trim().toString()))) {
/* 2116 */         String ds = null;
/* 2117 */         if (on_offLampSplit2[2].equals("1"))
/* 2118 */           ds = on_offLampSplit2[1] + 1;
/* 2119 */         else if (on_offLampSplit2[2].equals("2"))
/* 2120 */           ds = on_offLampSplit2[1] + 2;
/* 2121 */         else if (on_offLampSplit2[2].equals("3")) {
/* 2122 */           ds = on_offLampSplit2[1] + 3;
/*      */         }
/*      */ 
/* 2126 */         this.resendVerification = this.boResendVerificationService.find(devId, ds, on_offLampSplit2[3]);
/* 2127 */         System.err.println();
/* 2128 */         if (this.resendVerification.getAcceptState().equals("wait")) {
/* 2129 */           this.resendVerification.setAcceptState("1");
/* 2130 */           this.boResendVerificationService.update(this.resendVerification);
/* 2131 */           System.err.println("走了");
/* 2132 */         } else if (this.resendVerification.getAcceptState().equals("1")) {
/* 2133 */           this.resendVerification.setAcceptState("2");
/* 2134 */           this.boResendVerificationService.update(this.resendVerification);
/* 2135 */           System.err.println("走了1");
/* 2136 */         } else if (this.resendVerification.getAcceptState().equals("2")) {
/* 2137 */           this.resendVerification.setAcceptState("ERR");
/* 2138 */           this.boResendVerificationService.update(this.resendVerification);
/* 2139 */           System.err.println("走了2");
/*      */         }
/*      */ 
/*      */       }
/*      */ 
/*      */     }
/*      */ 
/* 2149 */     String curtain = new String(this.dataClone);
/* 2150 */     String[] curtainSplit1 = curtain.split("-");
/* 2151 */     if (("ZIGBEE_CURTAIN".equals(curtainSplit1[0])) && 
/* 2152 */       ("READ".equals(curtainSplit1[1]))) {
/* 2153 */       String[] array = (String[])StaticUtil.CURTAIN.get(devId + "_A");
/* 2154 */       String GUEST_ROOM = "";
/* 2155 */       if (array != null) {
/* 2156 */         GUEST_ROOM = array[0];
/*      */       }
/* 2158 */       String curtain2 = curtainSplit1[2];
/* 2159 */       String[] curtainSplit2 = curtain2.split(",");
/* 2160 */       for (int i = 0; i < curtainSplit2.length; i++) {
/* 2161 */         System.err.println("截取 " + curtainSplit2[i]);
/*      */       }
/* 2163 */       System.err.println("curtainSplit2 数组长度 " + curtainSplit2.length);
/* 2164 */       String string = curtainSplit2[(curtainSplit2.length - 1)];
/* 2165 */       System.err.println(string);
/* 2166 */       if (string.trim().toString().equals("OK")) {
/* 2167 */         BoHostDevice hostDevice = this.boHostDeviceService.findBydeviceAddress(GUEST_ROOM, curtainSplit2[1].trim().toString());
/* 2168 */         if (hostDevice != null) {
/* 2169 */           StaticUtil.STATE.put(devId + "_" + curtainSplit2[1].trim().toString(), curtainSplit2[3]);
/* 2170 */           hostDevice.setState(curtainSplit2[3].trim().toString());
/* 2171 */           this.boHostDeviceService.update(hostDevice);
/*      */         }
/*      */ 
/*      */       }
/*      */ 
/*      */     }
/*      */ 
/* 2179 */     String dimmingLight = new String(this.dataClone);
/* 2180 */     String[] dimmingLightSplit1 = dimmingLight.split("-");
/* 2181 */     if (("ZIGBEE_DIMMER".equals(dimmingLightSplit1[0])) && 
/* 2182 */       ("READ".equals(dimmingLightSplit1[1]))) {
/* 2183 */       String[] array = (String[])StaticUtil.DIMMINGLIGHT.get(devId + "_A");
/* 2184 */       String GUEST_ROOM = "";
/* 2185 */       if (array != null) {
/* 2186 */         GUEST_ROOM = array[0];
/*      */       }
/* 2188 */       String dimmingLight2 = dimmingLightSplit1[2];
/* 2189 */       String[] dimmingLightSplit2 = dimmingLight2.split(",");
/* 2190 */       for (int i = 0; i < dimmingLightSplit2.length; i++) {
/* 2191 */         System.err.println("截取 " + dimmingLightSplit2[i]);
/*      */       }
/* 2193 */       System.err.println("dimmingLightSplit2 数组长度 " + dimmingLightSplit2.length);
/* 2194 */       String string = dimmingLightSplit2[(dimmingLightSplit2.length - 1)];
/* 2195 */       System.err.println(string);
/* 2196 */       if (string.trim().toString().equals("OK")) {
/* 2197 */         BoHostDevice hostDevice = this.boHostDeviceService.findBydeviceAddress(GUEST_ROOM, dimmingLightSplit2[1].trim().toString());
/* 2198 */         if (hostDevice != null) {
/* 2199 */           StaticUtil.STATE.put(devId + "_" + dimmingLightSplit2[1].trim().toString(), dimmingLightSplit2[2]);
/* 2200 */           hostDevice.setState(dimmingLightSplit2[2].trim().toString());
/* 2201 */           this.boHostDeviceService.update(hostDevice);
/*      */         }
/*      */ 
/*      */       }
/*      */ 
/*      */     }
/*      */ 
/* 2209 */     String lightRead = new String(this.dataClone);
/* 2210 */     String[] lightReadSplit = lightRead.split("-");
/* 2211 */     if ((lightReadSplit[0].trim().equals("ZIGBEE_LIGHT")) && (lightReadSplit[1].trim().equals("READ"))) {
/* 2212 */       String[] array = (String[])StaticUtil.QUERYSTATE.get(devId + "_A");
/* 2213 */       String GUEST_ROOM = "";
/* 2214 */       if (array != null) {
/* 2215 */         GUEST_ROOM = array[0];
/*      */       }
/* 2217 */       System.err.println("GUEST_ROOM  " + GUEST_ROOM);
/* 2218 */       String lightRead2 = lightReadSplit[2].trim().toString();
/* 2219 */       String[] lightReadSplit2 = lightRead2.split(",");
/*      */ 
/* 2221 */       Integer lightCount = Integer.valueOf(lightReadSplit2[2]);
/*      */ 
/* 2223 */       BoDevice boDevice = this.boDeviceService.findByCode(devId);
/* 2224 */       BoUsers boUsers = this.boUserServicess.findByUserUserCode(GUEST_ROOM);
/* 2225 */       for (int i = 0; i < lightCount.intValue(); i++) {
/* 2226 */         int c = i + 1;
/*      */         try
/*      */         {
/* 2229 */           BoHostDevice hostDevice = this.boHostDeviceService.findBydeviceAddress(GUEST_ROOM, lightReadSplit2[1].trim().toString() + c);
/*      */ 
/* 2231 */           if (hostDevice != null) {
/* 2232 */             if (c == 1) {
/* 2233 */               System.err.println("on_off2split2[3]" + lightReadSplit2[3]);
/* 2234 */               hostDevice.setState(lightReadSplit2[3].trim().toString());
/* 2235 */               StaticUtil.STATE.put(devId + "_" + lightReadSplit2[1].trim().toString() + c, lightReadSplit2[3]);
/* 2236 */             } else if (c == 2) {
/* 2237 */               System.err.println("on_off2split2[4]" + lightReadSplit2[4]);
/* 2238 */               StaticUtil.STATE.put(devId + "_" + lightReadSplit2[1].trim().toString() + c, lightReadSplit2[4]);
/* 2239 */               hostDevice.setState(lightReadSplit2[4].trim().toString());
/*      */             }
/* 2241 */             else if (c == 3) {
/* 2242 */               System.err.println("on_off2split2[5]" + lightReadSplit2[5]);
/* 2243 */               StaticUtil.STATE.put(devId + "_" + lightReadSplit2[1].trim().toString() + c, lightReadSplit2[5]);
/* 2244 */               hostDevice.setState(lightReadSplit2[5].trim().toString());
/*      */             }
/* 2246 */             this.boHostDeviceService.update(hostDevice);
/*      */           }
/*      */         }
/*      */         catch (Exception e)
/*      */         {
/* 2251 */           e.printStackTrace();
/*      */         }
/*      */ 
/* 2254 */         List<BoDeviceState> list = this.boDeviceStateService.getBydeviceAddress(GUEST_ROOM, 
/* 2255 */           lightReadSplit2[1] + c);
/* 2256 */         if (list.size() <= 0)
/*      */         {
/* 2258 */           BoDeviceState state = new BoDeviceState();
/* 2259 */           state.setBoDevice(boDevice);
/* 2260 */           state.setBoUsers(boUsers);
/* 2261 */           state.setDeviceAddress(lightReadSplit2[1] + c);
/* 2262 */           state.setKeyValue(Integer.valueOf(1));
/* 2263 */           if (c == 1) {
/* 2264 */             System.err.println("on_off2split2[3]" + lightReadSplit2[3]);
/* 2265 */             state.setKey1(Integer.valueOf(lightReadSplit2[3]));
/* 2266 */           } else if (c == 2) {
/* 2267 */             System.err.println("on_off2split2[4]" + lightReadSplit2[4]);
/* 2268 */             state.setKey1(Integer.valueOf(lightReadSplit2[4]));
/*      */           } else {
/* 2270 */             System.err.println("on_off2split2[5]" + lightReadSplit2[5]);
/* 2271 */             state.setKey1(Integer.valueOf(lightReadSplit2[5]));
/*      */           }
/* 2273 */           this.boDeviceStateService.save(state);
/*      */         }
/*      */         else {
/* 2276 */           for (BoDeviceState boDeviceState : list)
/*      */           {
/* 2278 */             System.err.println("countss" + c);
/* 2279 */             if (c == 1) {
/* 2280 */               System.err.println("on_off2split2[3]" + lightReadSplit2[3]);
/* 2281 */               boDeviceState.setKey1(Integer.valueOf(lightReadSplit2[3]));
/* 2282 */             } else if (c == 2) {
/* 2283 */               System.err.println("on_off2split2[4]" + lightReadSplit2[4]);
/* 2284 */               boDeviceState.setKey1(Integer.valueOf(lightReadSplit2[4]));
/*      */             } else {
/* 2286 */               System.err.println("on_off2split2[5]" + lightReadSplit2[5]);
/* 2287 */               boDeviceState.setKey1(Integer.valueOf(lightReadSplit2[5]));
/*      */             }
/*      */ 
/* 2290 */             this.boDeviceStateService.update(boDeviceState);
/*      */           }
/*      */         }
/*      */ 
/*      */       }
/*      */ 
/*      */     }
/*      */ 
/* 2298 */     String[] address = this.packetProcessor.getSocketAddress(clientId);
/*      */   }
/*      */ 
/*      */   public void processReportSuccess(InPacket in)
/*      */   {
/* 2322 */     logger.info("开始处理processReportSuccess");
/* 2323 */     BoReport report = new BoReport();
/* 2324 */     if ((in instanceof Report0140Packet)) {
/* 2325 */       Report0140Packet packet = (Report0140Packet)in;
/*      */ 
/* 2327 */       logger.info("processReportSuccess deviceCode=" + packet.getDevId() + " deviceType=" + packet.getDevType());
/*      */ 
/* 2329 */       report.setDeviceCode(packet.getDevId());
/* 2330 */       report.setDeviceType(Integer.valueOf(packet.getDevType()));
/* 2331 */       report.setFrameType(Integer.valueOf(packet.getFrameType()));
/* 2332 */       report.setLife1(Integer.valueOf(packet.getLife1()));
/* 2333 */       report.setLife2(Integer.valueOf(packet.getLife2()));
/* 2334 */       report.setLife3(Integer.valueOf(packet.getLife3()));
/* 2335 */       report.setLife4(Integer.valueOf(packet.getLife4()));
/* 2336 */       report.setLife5(Integer.valueOf(packet.getLife5()));
/*      */ 
/* 2338 */       report.setStatus(Integer.valueOf(packet.getFrameStatus()));
/* 2339 */       report.setTds(Integer.valueOf(packet.getTDS()));
/* 2340 */       report.setWork(Integer.valueOf(packet.getFrameWork()));
/*      */ 
/* 2342 */       report.setTime(new Date());
/*      */ 
/* 2344 */       report.setIp(packet.getHostName());
/* 2345 */       report.setPort(Integer.valueOf(packet.getPort()));
/*      */     }
/* 2347 */     else if ((in instanceof Report0150Packet)) {
/* 2348 */       Report0150Packet packet = (Report0150Packet)in;
/*      */ 
/* 2350 */       logger.info("processReportSuccess deviceCode=" + packet.getDevId() + " deviceType=" + packet.getDevType());
/*      */ 
/* 2352 */       report.setDeviceCode(packet.getDevId());
/* 2353 */       report.setDeviceType(Integer.valueOf(packet.getDevType()));
/* 2354 */       report.setFrameType(Integer.valueOf(packet.getFrameType()));
/*      */ 
/* 2356 */       report.setStatus(Integer.valueOf(packet.getFrameStatus()));
/* 2357 */       report.setHumidity(Integer.valueOf(packet.getHumidity()));
/* 2358 */       report.setTemperature(Integer.valueOf(packet.getTemperature()));
/* 2359 */       report.setElectric(Integer.valueOf(packet.getElectric()));
/* 2360 */       report.setImagePath(packet.getImgPath());
/*      */ 
/* 2362 */       report.setTime(new Date());
/*      */ 
/* 2364 */       report.setIp(packet.getHostName());
/* 2365 */       report.setPort(Integer.valueOf(packet.getPort()));
/* 2366 */     } else if ((in instanceof Report0240Packet)) {
/* 2367 */       Report0240Packet packet = (Report0240Packet)in;
/*      */ 
/* 2369 */       logger.info("processReportSuccess deviceCode=" + packet.getDevId() + " deviceType=" + packet.getDevType());
/*      */ 
/* 2371 */       BoUserDevice userDevice = this.boUserDeviceService.getMasterBinding(packet.getDevId());
/*      */ 
/* 2373 */       report.setDeviceCode(packet.getDevId());
/* 2374 */       report.setDeviceType(Integer.valueOf(packet.getDevType()));
/* 2375 */       report.setDeviceId(userDevice.getDeviceId());
/* 2376 */       report.setUserId(userDevice.getUserId());
/* 2377 */       report.setFrameType(Integer.valueOf(packet.getFrameType()));
/* 2378 */       report.setLife1(Integer.valueOf(packet.getLife1()));
/* 2379 */       report.setLife2(Integer.valueOf(packet.getLife2()));
/* 2380 */       report.setLife3(Integer.valueOf(packet.getLife3()));
/* 2381 */       report.setLife4(Integer.valueOf(packet.getLife4()));
/* 2382 */       report.setLife5(Integer.valueOf(packet.getLife5()));
/*      */ 
/* 2384 */       report.setStatus(Integer.valueOf(packet.getFrameStatus()));
/* 2385 */       report.setWork(Integer.valueOf(packet.getFrameWork()));
/* 2386 */       report.setInTds(Integer.valueOf(packet.getInTDS()));
/* 2387 */       report.setOutTds(Integer.valueOf(packet.getOutTDS()));
/* 2388 */       report.setTotalWater(Integer.valueOf(packet.getTotalWater()));
/*      */ 
/* 2390 */       report.setTime(new Date());
/*      */ 
/* 2392 */       report.setIp(packet.getHostName());
/* 2393 */       report.setPort(Integer.valueOf(packet.getPort()));
/*      */     }
/* 2395 */     this.boProcessService.doSaveReport(report);
/*      */   }
/*      */ 
/*      */   public void processAlarmSuccess(InPacket in)
/*      */   {
/* 2404 */     logger.info("开始处理processAlarmSuccess");
/* 2405 */     BoAlarm alarm = new BoAlarm();
/*      */ 
/* 2407 */     if ((in instanceof Alarm0140Packet)) {
				 logger.debug("Alarm0140Packet");
/* 2408 */       Alarm0140Packet packet = (Alarm0140Packet)in;
/*      */ 
/* 2410 */       alarm.setDeviceCode(packet.getDevId());
/* 2411 */       alarm.setDeviceType(Integer.valueOf(packet.getDevType()));
/* 2412 */       alarm.setFrameType(Integer.valueOf(packet.getFrameType()));
/* 2413 */       alarm.setStatus(Integer.valueOf(packet.getFrameStatus()));
/* 2414 */       alarm.setTime(new Date());
/*      */ 
/* 2416 */       alarm.setIp(packet.getHostName());
/* 2417 */       alarm.setPort(Integer.valueOf(packet.getPort()));
/*      */     }
/* 2419 */     else if ((in instanceof Alarm0150Packet)) {
			     logger.debug("Alarm0150Packet");
/* 2420 */       Alarm0150Packet packet = (Alarm0150Packet)in;
/*      */ 
/* 2422 */       alarm.setDeviceCode(packet.getDevId());
/* 2423 */       alarm.setDeviceType(Integer.valueOf(packet.getDevType()));
/* 2424 */       alarm.setFrameType(Integer.valueOf(packet.getFrameType()));
/* 2425 */       alarm.setStatus(Integer.valueOf(packet.getFrameStatus()));
/* 2426 */       alarm.setTime(new Date());
/*      */ 
/* 2428 */       alarm.setIp(packet.getHostName());
/* 2429 */       alarm.setPort(Integer.valueOf(packet.getPort()));
/*      */ 
/* 2431 */       alarm.setUrl("");
/*      */     }
/* 2433 */     else if ((in instanceof Alarm0240Packet)) {
				 logger.debug("Alarm0240Packet");
/* 2434 */       Alarm0240Packet packet = (Alarm0240Packet)in;
/*      */ 
/* 2436 */       alarm.setDeviceCode(packet.getDevId());
/* 2437 */       alarm.setDeviceType(Integer.valueOf(packet.getDevType()));
/* 2438 */       alarm.setFrameType(Integer.valueOf(packet.getFrameType()));
/* 2439 */       alarm.setStatus(Integer.valueOf(packet.getFrameStatus()));
/* 2440 */       alarm.setTime(new Date());
/*      */ 
/* 2442 */       alarm.setIp(packet.getHostName());
/* 2443 */       alarm.setPort(Integer.valueOf(packet.getPort()));
/*      */     }
/*      */ 
/* 2447 */     this.boProcessService.doSaveAlarm(alarm);//？
/*      */   }
/*      */ 
/*      */   public void procesKeepAliveSuccess(InPacket in)
/*      */   {
/* 2456 */     logger.info("开始处理procesKeepAliveSuccess");
/* 2457 */     com.smarthome.dock.server.packets.in.KeepAlivePacket packet = (com.smarthome.dock.server.packets.in.KeepAlivePacket)in;
/* 2458 */     String deviceCode = packet.getDevId();
			   System.out.println("deviceCode:"+deviceCode);//这句输出语句本身存在
/*      */ 
/* 2460 */     String hostName = packet.getHostName();
			   System.out.println("hostName:"+hostName);
/* 2461 */     int port = packet.getPort();
			   System.out.println("port:"+port);
/*      */ 
/* 2463 */     int ret = 1;
/* 2464 */     DockUser dockUser = this.userManager.getUser(deviceCode);//这里为空，设备状态为0
			   System.out.println("dockUser :"+dockUser);
/* 2465 */     if (dockUser == null) {
/* 2466 */       ret = 0;
/*      */     } else {
/* 2468 */       ret = dockUser.getStatus();
/*      */ 
/* 2470 */       if ((ret == 1) && (
/* 2471 */         (!hostName.equals(dockUser.getIp())) || (port != dockUser.getPort()))) {
/* 2472 */         this.packetProcessor.addSocketAddress(deviceCode, new String[] { hostName, port+"" });
/* 2473 */         this.userManager.keepAliveUser(deviceCode, new Date(), hostName, port);
				   //this.userManager.logoutUser(deviceCode,keepAliveTime, hostName);
/*      */ 
/* 2475 */         BoDevice boDevice = this.wdDeviceService.findByCode(deviceCode);
/* 2476 */         if (boDevice != null) {
/* 2477 */           boDevice.setStatus(Integer.valueOf(1));
					 boDevice.setHostStatus("在线");
/* 2478 */           BoDevice update = (BoDevice)this.wdDeviceService.update(boDevice);
/* 2479 */           System.err.println(deviceCode + " ---- " + update.getStatus());
/*      */         }
/*      */ 
/*      */       }
/*      */ 
/*      */     }

/* 2487 */     BoDevice boDevice = this.wdDeviceService.findByCode(deviceCode);
/* 2488 */     if (boDevice != null) {
/* 2489 */       boDevice.setStatus(Integer.valueOf(1));
/* 2490 */       BoDevice update = (BoDevice)this.wdDeviceService.update(boDevice);
/* 2491 */       System.err.println(deviceCode + " ---- " + update.getStatus());
/*      */     }
/*      */ 
/* 2494 */     logger.info(deviceCode + " KeepAlive " + hostName + " " + port + " 状态 " + ret);//ret 代表设备的状态
/*      */ 
/* 2496 */     com.smarthome.dock.server.packets.out.KeepAlivePacket reply = new com.smarthome.dock.server.packets.out.KeepAlivePacket(packet.getDevId(), packet.getSequence(), ret);
/* 2497 */     reply.setHostName(hostName);
/* 2498 */     reply.setPort(port);
/* 2499 */     this.packetProcessor.sendStrategy(reply);//发送包
/*      */ 
/* 2501 */     this.packetProcessor.getKeepAliveTrigger().add(packet);//通过则新增至KeepAliveTrigger
/*      */   }
/*      */ 
/*      */   public void procesKeepAliveLost(com.smarthome.dock.server.packets.in.KeepAlivePacket packet)
/*      */   {
/* 2511 */     logger.info(packet.getDevId() + " 开始处理离线procesKeepAliveLost");
/* 2512 */     String hostName = packet.getHostName();
/* 2513 */     int port = packet.getPort();
/*      */ 
/* 2515 */     this.userManager.logoutUser(packet.getDevId(), new Date(), hostName);
/*      */ 
/* 2517 */     this.boProcessService.doKeepAliveLost(packet.getDevId(), hostName);//判断离线
/*      */ 
/* 2519 */     this.packetProcessor.removeSocketAddress(packet.getDevId());
/*      */ 
/* 2521 */     logger.info(packet.getDevId() + " procesKeepAliveLost " + hostName);
/*      */   }
/*      */ 
/*      */   public void processUnknown(InPacket in)
/*      */   {
/*      */     ErrorPacket error;
/* 2530 */     if ((in instanceof ErrorPacket))
/* 2531 */       error = (ErrorPacket)in;
/*      */   }
/*      */ 
/*      */   public String getUserCode()
/*      */   {
/* 2538 */     return this.userCode;
/*      */   }
/*      */ 
/*      */   public void setUserCode(String userCode) {
/* 2542 */     this.userCode = userCode;
/*      */   }
/*      */ }

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.dock.server.helper.PacketProcessHelper
 * JD-Core Version:    0.6.2
 */