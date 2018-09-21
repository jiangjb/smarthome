/*     */ package com.smarthome.imcp.quartz;
/*     */ 
/*     */ import com.smarthome.dock.server.helper.PacketProcessHelper;
		  import com.smarthome.dock.server.support.PacketProcessor;
/*     */ import com.smarthome.dock.server.util.SensorUtil;
/*     */ import com.smarthome.dock.server.util.StaticUtil;
		  import com.smarthome.imcp.action.xing.MqttReceive;
		  import com.smarthome.imcp.action.xing.MqttReceiveData;
		  import com.smarthome.imcp.action.xing.MsgSend;
		  import com.smarthome.imcp.dao.model.bo.AirconditionSleep;
/*     */ import com.smarthome.imcp.dao.model.bo.BoDevice;
/*     */ import com.smarthome.imcp.dao.model.bo.BoHostDevice;
/*     */ import com.smarthome.imcp.dao.model.bo.BoLockPasswordManage;
/*     */ import com.smarthome.imcp.dao.model.bo.BoLockVerdict;
/*     */ import com.smarthome.imcp.dao.model.bo.BoModel;
		  import com.smarthome.imcp.dao.model.bo.InfraredTimer;
		  import com.smarthome.imcp.dao.model.bo.MiniBlack;
		  import com.smarthome.imcp.dao.model.bo.RemoteControl;
		  import com.smarthome.imcp.service.bo.AirconditionSleepServiceIface;
/*     */ import com.smarthome.imcp.service.bo.BoDeviceServiceIface;
/*     */ import com.smarthome.imcp.service.bo.BoHostDeviceServiceIface;
/*     */ import com.smarthome.imcp.service.bo.BoLockPasswordManageServiceIface;
/*     */ import com.smarthome.imcp.service.bo.BoLockVerdictServiceIface;
/*     */ import com.smarthome.imcp.service.bo.BoModelServiceIface;
		  import com.smarthome.imcp.service.bo.InfraredTimerServiceIface;
		  import com.smarthome.imcp.service.bo.MiniBlackServiceIface;
		  import com.smarthome.imcp.service.bo.RemoteControlServiceIface;
/*     */ import com.smarthome.imcp.util.AES;
/*     */ import com.smarthome.imcp.util.SimulateHTTPRequestUtil;
/*     */ import com.smarthome.imcp.util.StaticUtils;
		  import java.io.BufferedReader;
		  import java.io.File;
		  import java.io.FileReader;
/*     */ import java.io.Serializable;
		  import java.text.DateFormat;
/*     */ import java.text.ParseException;
/*     */ import java.text.SimpleDateFormat;
/*     */ import java.util.Date;
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import org.apache.commons.lang3.StringUtils;
		  import org.slf4j.Logger;
		  import org.slf4j.LoggerFactory;
/*     */ import org.springframework.beans.factory.annotation.Autowired;
/*     */ import org.springframework.context.annotation.Lazy;
/*     */ import org.springframework.scheduling.annotation.Scheduled;
/*     */ import org.springframework.stereotype.Component;
/*     */ 
/*     */ @Component
/*     */ @Lazy(false)
/*     */ public class QuartzJobs
/*     */ {
/*     */ 
/*     */   @Autowired
/*     */   private BoHostDeviceServiceIface<BoHostDevice, Serializable> boHostDeviceService;
/*     */ 
/*     */   @Autowired
/*     */   private BoModelServiceIface<BoModel, Serializable> boModelService;
/*     */ 
/*     */   @Autowired
/*     */   private BoLockPasswordManageServiceIface<BoLockPasswordManage, Serializable> boLockPasswordManageService;
/*     */ 
/*     */   @Autowired
/*     */   private BoLockVerdictServiceIface<BoLockVerdict, Serializable> boLockVerdictService;
/*     */ 
/*     */   @Autowired
/*     */   private BoDeviceServiceIface<BoDevice, Serializable> boDeviceService;
/*     */ 
/*     */   @Autowired
/*     */   private PacketProcessHelper packetProcessHelper;

			@Autowired
			private PacketProcessor packetProcessor;//new
			
			@Autowired
			private MiniBlackServiceIface<MiniBlack, Serializable> miniBlackService;//2018-8-22
			
			@Autowired
			private InfraredTimerServiceIface<InfraredTimer, Serializable> infraredTimerService;//9-10
			
			@Autowired
			private RemoteControlServiceIface<RemoteControl, Serializable> remoteControlService;//9-10
			
			@Autowired
			private AirconditionSleepServiceIface<AirconditionSleep, Serializable> airconditionSleepService;//2018-9-14

			private static Logger logger = LoggerFactory.getLogger(QuartzJobs.class);
/*  84 */   private static Map<String, Integer> user_num = new HashMap();
/*     */ 
/*     */   public PacketProcessHelper getPacketProcessHelper()
/*     */   {
/*  77 */     return this.packetProcessHelper;
/*     */   }
/*     */   public void setPacketProcessHelper(PacketProcessHelper packetProcessHelper) {
/*  80 */     this.packetProcessHelper = packetProcessHelper;
/*     */   }

/*     */   public void packNum(String userCode)
/*     */   {
	          logger.info("定时器 packNum 方法");
/*  90 */     if (user_num.get(userCode) == null)
/*  91 */       user_num.put(userCode, Integer.valueOf(0));
/*     */     else
/*  93 */       user_num.put(userCode, Integer.valueOf(((Integer)user_num.get(userCode)).intValue() == 255 ? 0 : ((Integer)user_num.get(userCode)).intValue() + 1));
/*     */   }

			/*
			 * 每2秒执行一次
//			 */
//			@Scheduled(cron="0/2 * * * * ?")
			public void getBackMsg() {
				MqttReceiveData msg=MqttReceiveData.getInstance();//9-19 MqttReceiveData设置成单例类
				List<MiniBlack> list=this.miniBlackService.findAllMac();
				for(MiniBlack mb:list) {
					msg.msgReceive1(mb.getMacAddr());
				}
			}
			
			/*
			 * 每1分钟发送心跳请求
			 */
			@Scheduled(cron="0 0/1 * * * ?")
			public void getMsg() {
				System.gc();//9-21 强制GC
				boolean check=false;
				//从MQTT上获取消息
//				MqttReceive mr=new MqttReceive();
				MqttReceive mr=MqttReceive.getInstance();//9-19 MqttReceive设置成单例类
				List<MiniBlack> list=this.miniBlackService.findAllMac();
				for(MiniBlack mb:list) {
					mr.msgReceive(mb.getMacAddr());
					
					//找到mac地址对应的文件名
					String macNew = mb.getMacAddr().replace(":", "");
					String fileName=macNew+".txt";//mac地址对应的文件名称
					
					String dir="/home/onoff";
					File directory = new File(dir);
					if (directory.exists()) {
						File file = new File(dir);
						File[] fs = file.listFiles();
						for(File f:fs){	
							if(!f.isDirectory()) {
								String fileName01=f.getName().toString();
								if(fileName.equals(fileName01)) {
									check=true;
									break;
								}
							}
						}
					}
					//如果没有找到相应文件，就设为离线
					if(!check) {
						mb.setStatus("离线");
						this.miniBlackService.update(mb);
					}
				}
			}
			
			/*
			 * 每20秒 触发一次，这个定时器用于改变小黑的在线、离线状态
			 */
			@Scheduled(cron="0/20 * * * * ?")   
			public void changeMiniStatus()
			{
				String dir="/home/onoff";
				//判断路径是否存在
				File directory = new File(dir);
		        if (directory.exists()) {//如果存在该路径,取出文件夹下的文件名，再转化为mac地址
					File file = new File(dir);		//获取其file对象
					File[] fs = file.listFiles();	//遍历path下的文件和目录，放在File数组中
					for(File f:fs){					//遍历File[]数组
						if(!f.isDirectory()) {		//若非目录(即文件)，则打印
							//获取mac地址
							String fileName=f.getName().toString();
							String macNew = fileName.substring(0, fileName.length()-4);//去掉 “.txt”
							StringBuffer mac01=new StringBuffer(macNew);
							for(int i=2;i<mac01.length();i+=3) {
								if(i != mac01.length()-1) {
									mac01.insert(i,":");
								}
							}
							String mac=new String(mac01);
//							System.out.println("mac:"+mac);
							//找到mac地址对应的小黑
							MiniBlack mb = this.miniBlackService.findByMac(mac);
							if(mb != null) {
								DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
								Date now = new Date( );
								Date d1,d2;
								//获取文件内容
								FileReader file01;
								try {
									d1=df.parse(df.format(now));//当前时间
									file01 = new FileReader(f.getAbsolutePath());
									BufferedReader br = new BufferedReader(file01); 
									String dateStr = br.readLine();
									d2=df.parse(dateStr);//文件中保存的时间
									long diff = (d1.getTime() - d2.getTime())/1000;
									if(diff <= 20) {
										mb.setStatus("在线");
									}else {
										mb.setStatus("离线");
									}
									this.miniBlackService.update(mb);
									br.close();//9-19
									file01.close();
								} catch (Exception e) {
									e.printStackTrace();
								}   
							}
						}
					}
		        }
			}
			
			@Scheduled(cron="0 0/1 * * * ?")
			public void airConditioningTiming() {
				List<RemoteControl> rcList=this.remoteControlService.findAll();
				if(rcList.size() > 0) {
					for(RemoteControl rc:rcList) {
						//定时功能
						if("on".equals(rc.getState())) {
//							logger.info("state:"+rc.getState());
							List<InfraredTimer> list=this.infraredTimerService.findByRCId(rc.getId());
//							logger.info("on list:"+list);
							if(list.size() > 0) {
								String weekOfDate = SensorUtil.getWeekOfDate(new Date());
								String week;
								for(InfraredTimer it:list) {
									week=it.getWeek();
									boolean indexFromArr = SensorUtil.getIndexFromArr(weekOfDate, week);
									if (indexFromArr) {
										SimpleDateFormat dateFormater = new SimpleDateFormat("HH:mm"); 
										String format = dateFormater.format(new Date());
										String format1=it.getTime();
										if(format1.equals(format)) {
											MsgSend.msgSend(it.getInfraredCode(),it.getMac());
										}
									}
								}
							}
						}else {
							//off状态    关闭定时
						}
						
						//睡眠功能
						if("on".equals(rc.getSleepState())) {
							List<AirconditionSleep> asList=this.airconditionSleepService.findByrcid(rc.getId());
							if(asList != null){//存在睡眠功能，定时执行
								SimpleDateFormat dateFormater = new SimpleDateFormat("HH:mm"); 
								String format = dateFormater.format(new Date());
								for(AirconditionSleep as:asList) {
									String format1=as.getTime();
									boolean bool=format1.equals(format);
									String times=as.getTimes();
									String status=as.getStatus();
									if(status.equals("on")) {//当睡眠开启时
										if(times.equals("1")) {
											if(bool) {
												as.setStatus("off");
												AirconditionSleep update=this.airconditionSleepService.update(as);
												if(update != null) {
													MsgSend.msgSend(as.getInfraredCode(),as.getMac());
												}
											}
										}else if(times.equals("n")) {
											if(bool) {
												MsgSend.msgSend(as.getInfraredCode(),as.getMac());
											}
										}else {
											//为0时不执行      只执行一次
										}
									}
								}
							}
						}else {
							//off状态    关闭睡眠
						}
					}
				}
			}
			
/*     */   @Scheduled(cron="0 0/1 * * * ?")
/*     */   public void setUserMode()
/*     */   {
			  logger.debug("情景模式-定时器");
/* 101 */     List by = this.boModelService.getBy();
/* 102 */     for (int i = 0; i < by.size(); i++) {
/* 103 */       BoModel boModel = (BoModel)by.get(i);
/*     */ 
/* 105 */       String weekOfDate = SensorUtil.getWeekOfDate(new Date());
/*     */       String week;

/* 107 */       if ((boModel.getWeek() == null) || (boModel.getWeek().equals("")))
/* 108 */         week = "星期日,";
/*     */       else {
/* 110 */         week = boModel.getWeek();
/*     */       }
/* 112 */       boolean indexFromArr = SensorUtil.getIndexFromArr(weekOfDate, week);
/* 113 */       if (indexFromArr) {
/* 114 */         SimpleDateFormat dateFormater = new SimpleDateFormat("HH:mm");                     
/* 115 */         String format = dateFormater.format(new Date());
/* 116 */         System.err.println(format);
/* 117 */         List bys = this.boModelService.getBys(boModel.getWeek(), format);
/* 118 */         if (bys.size() <= 0)
/* 119 */           System.err.println("没有");
/*     */         else
/* 121 */           for (int j = 0; j < bys.size(); j++) {
/* 122 */             BoModel boModel2 = (BoModel)bys.get(j);
/* 125 */             SimulateHTTPRequestUtil s = new SimulateHTTPRequestUtil();
///* 126 */             s.sendGet("http://127.0.0.1:8080/smarthome.IMCPlatform/xingUser/commandmodel.action?modelId=" + boModel2.getModelId(), boModel2.getBoUsers().getUserCode());
					  s.sendGet("http://120.77.250.17:8080/smarthome.IMCPlatform/xingUser/commandmodel.action?modelId=" + boModel2.getModelId(), boModel2.getBoUsers().getUserCode());
/*     */           }
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   @Scheduled(cron="0 0/1 * * * ?")
/*     */   public void LockPassword()
/*     */     throws ParseException
/*     */   {
			  logger.debug("定时器 LockPassword 方法");
/* 140 */     List lock = this.boLockPasswordManageService.getLock(Integer.valueOf(65535));
			  logger.info("lock.size() :"+lock.size()+" 如果不是0则不执行processSendDData方法");
/* 141 */     Date currentTime = new Date();
/* 142 */     SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
/* 143 */     String dateString = formatter.format(currentTime);
/* 144 */     System.err.println(dateString);
/* 145 */     for (int i = 0; i < lock.size(); i++) {
/* 146 */       BoLockPasswordManage lockPassManage = (BoLockPasswordManage)lock.get(i);
/* 147 */       if (dateString.equals(lockPassManage.getStartTime())) {
/* 148 */         packNum(lockPassManage.getBoUsers().getUserCode());
/* 149 */         Date datesEndTime = formatter.parse(lockPassManage.getStartTime());
/* 150 */         long appEndTime = datesEndTime.getTime();
/* 151 */         long interval = (appEndTime - currentTime.getTime()) / 1000L;
/* 152 */         String SetString = "ZIGBEE_LOCK-SET-" + user_num.get(lockPassManage.getBoUsers().getUserCode()) + "," + lockPassManage.getLockAddress() + "," + lockPassManage.getLockType() + "," + lockPassManage.getLockOfTimes() + "," + AES.decrypt(lockPassManage.getAdminPwd()) + "," + AES.decrypt(lockPassManage.getLockPwd()) + "," + interval;
/* 153 */         byte[] Set = SetString.getBytes();
///* 154 */         System.err.println("Set :"+new String(Set));
				  logger.info("Set :"+new String(Set));
/* 155 */         this.packetProcessHelper.processSendDData(lockPassManage.getBoDevice().getDeviceCode(), Set);
					
/*     */       } else {
/* 157 */         System.err.println("设置不是当前");
/*     */       }
/*     */ 
/* 160 */       if (dateString.equals(lockPassManage.getEndTime())) {
/* 161 */         packNum(lockPassManage.getBoUsers().getUserCode());
/* 162 */         String DeleteString = "ZIGBEE_LOCK-DELETE-" + user_num.get(lockPassManage.getBoUsers().getUserCode()) + "," + lockPassManage.getLockAddress() + "," + lockPassManage.getLockType() + "," + AES.decrypt(lockPassManage.getAdminPwd());
/* 163 */         byte[] Delete = DeleteString.getBytes();
/* 164 */         System.err.println(new String(Delete));
/* 165 */         this.packetProcessHelper.processSendDData(lockPassManage.getBoDevice().getDeviceCode(), Delete);
/* 166 */         StaticUtil.PWDDELETE.put(lockPassManage.getBoDevice().getDeviceCode() + "_" + lockPassManage.getLockAddress(), lockPassManage.getBoUsers().getUserCode());
/* 167 */         BoLockPasswordManage passwordManage = this.boLockPasswordManageService.findBylockAddress(lockPassManage.getBoUsers().getUserCode(), lockPassManage.getLockAddress(), Integer.valueOf(65535));
/* 168 */         if (passwordManage != null)
/* 169 */           this.boLockPasswordManageService.delete(passwordManage);
/*     */       }
/*     */       else {
/* 172 */         System.err.println("删除不是当前");
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   @Scheduled(cron="0/1 * * * * ?")
/*     */   public void lock() {
			  logger.debug("定时器 lock 方法");
/* 179 */     List<BoLockVerdict> all = this.boLockVerdictService.getAll();
/* 180 */     Long lockTime = Long.valueOf((int)(System.currentTimeMillis() / 1000L));
/* 181 */     for (BoLockVerdict boLockVerdict : all)
/* 182 */       if (lockTime.longValue() > Long.valueOf(boLockVerdict.getUnlockTimes()).longValue()) {
/* 183 */         StaticUtils.locks.put(boLockVerdict.getLockAddress(), Integer.valueOf(0));
/* 184 */         boLockVerdict.setStatus("0");
/* 185 */         boLockVerdict.setLockTimes("");
/* 186 */         boLockVerdict.setUnlockTimes("");
/* 187 */         this.boLockVerdictService.update(boLockVerdict);
/*     */       }
/*     */   }
/*     */ 
/*     */   @Scheduled(cron="0/1 * * * * ?")
/*     */   public void mobileCode()
/*     */   {
//			  logger.debug("定时器 mobileCode 方法");
/* 200 */     Iterator iterator = StaticUtil.msg_code.keySet().iterator();
/* 201 */     long timeout = 60000L;
/* 202 */     while (iterator.hasNext()) {
/* 203 */       String key = (String)iterator.next();
/* 204 */       String val = StaticUtil.msg_code.get(key).toString();
/* 205 */       if (!StringUtils.isEmpty(val))
/*     */       {
/* 207 */         String[] value = val.split(",");
/* 208 */         if (new Date().getTime() - Long.parseLong(value[1]) > timeout)
/* 209 */           iterator.remove();
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   @Scheduled(cron="0/1 * * * * ?")
/*     */   public void emailCode()
/*     */   {
//			  logger.debug("定时器 emailCode 方法");
/* 220 */     Iterator iterator = StaticUtil.msg_email_code.keySet().iterator();
/* 221 */     long timeout = 600000L;
/* 222 */     while (iterator.hasNext()) {
/* 223 */       String key = (String)iterator.next();
/* 224 */       String val = StaticUtil.msg_email_code.get(key).toString();
/* 225 */       if (!StringUtils.isEmpty(val))
/*     */       {
/* 227 */         String[] value = val.split(",");
/* 228 */         if (new Date().getTime() - Long.parseLong(value[1]) > timeout)
/* 229 */           iterator.remove();
/*     */       }
/*     */     }
/*     */   }

/*     */   @Scheduled(cron="0/4 * * * * ?")    //每隔4秒钟触发
/*     */   public void deviceS()
/*     */     throws ParseException
/*     */   {
				//4-17 4s扫描一次主机
//				XingUserAction xing=new XingUserAction();
//				xing.verifyWithSweepHost();
				//END
//				System.out.println("tomcat加载完后，每隔4秒钟触发更新设备hostStatus状态");
				//new add 更新设备的hostStatus状态
				List<BoDevice> devicesList = this.boDeviceService.getAllHostDevices();
//				this.boDeviceService.updateStatus(0);
				for(BoDevice boDevice:devicesList) {
					if(boDevice.getStatus() == 1) {
						String deviceCode=boDevice.getDeviceCode();
						this.boDeviceService.updateStatus(deviceCode,1);
					}
				}
/*     */   }
/*     */ 
/*     */   @Scheduled(cron="0/1 * * * * ?")
/*     */   public void sds()
/*     */   {
/* 248 */     List<BoHostDevice> allList = this.boHostDeviceService.getAllList();
/* 249 */     for (BoHostDevice boHostDevice : allList) {
//				logger.info("isAuthorized="+boHostDevice.getIsAuthorized());
				if(boHostDevice.getIsAuthorized() == null) {//3-19
					boHostDevice.setIsAuthorized(true);
					this.boHostDeviceService.update(boHostDevice);
				}
/* 250 */       if (boHostDevice.getBoDevice() == null)
	/* 251 */         this.boHostDeviceService.delete(boHostDevice);
			  }
/*     */   }
/*     */ }

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.quartz.QuartzJobs
 * JD-Core Version:    0.6.2
 * new smarthomeMavenWebProject
 */