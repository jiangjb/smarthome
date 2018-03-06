/*       */ package com.smarthome.imcp.action.xing;
/*       */ 
/*       */ import com.alibaba.fastjson.JSON;
/*       */ import com.pingplusplus.exception.APIConnectionException;//pingplusplus 支付模块  （Ping++）
/*       */ import com.pingplusplus.exception.APIException;
/*       */ import com.pingplusplus.exception.AuthenticationException;
/*       */ import com.pingplusplus.exception.ChannelException;
/*       */ import com.pingplusplus.exception.InvalidRequestException;
/*       */ import com.pingplusplus.exception.PingppException;
/*       */ import com.pingplusplus.model.App;
/*       */ import com.pingplusplus.model.Charge;
/*       */ import com.pingplusplus.model.Event;
/*       */ import com.pingplusplus.model.Refund;
/*       */ import com.pingplusplus.model.Summary;
/*       */ import com.pingplusplus.model.Webhooks;
/*       */ import com.smarthome.dock.server.helper.PacketProcessHelper;
/*       */ import com.smarthome.dock.server.util.StaticUtil;
/*       */ import com.smarthome.imcp.action.AbstractAction;
/*       */ import com.smarthome.imcp.common.GlobalMethod;
/*       */ import com.smarthome.imcp.common.Md5;
/*       */ import com.smarthome.imcp.common.Page;
/*       */ import com.smarthome.imcp.controller.RequestJson;
/*       */ import com.smarthome.imcp.dao.model.bo.BoAirBindingPanel;
/*       */ import com.smarthome.imcp.dao.model.bo.BoAlarmRecord;
/*       */ import com.smarthome.imcp.dao.model.bo.BoAndroidVersion;
/*       */ import com.smarthome.imcp.dao.model.bo.BoChannel;
/*       */ import com.smarthome.imcp.dao.model.bo.BoControlEnclosure;
/*       */ import com.smarthome.imcp.dao.model.bo.BoDevice;
/*       */ import com.smarthome.imcp.dao.model.bo.BoDeviceState;
/*       */ import com.smarthome.imcp.dao.model.bo.BoDevicehostinfo;
/*       */ import com.smarthome.imcp.dao.model.bo.BoFeedBack;
/*       */ import com.smarthome.imcp.dao.model.bo.BoFloor;
/*       */ import com.smarthome.imcp.dao.model.bo.BoGoods;
/*       */ import com.smarthome.imcp.dao.model.bo.BoHost;
/*       */ import com.smarthome.imcp.dao.model.bo.BoHostDevice;
/*       */ import com.smarthome.imcp.dao.model.bo.BoInfraredButtons;
/*       */ import com.smarthome.imcp.dao.model.bo.BoInfraredDevice;
/*       */ import com.smarthome.imcp.dao.model.bo.BoInfraredLearnControlMap;
/*       */ import com.smarthome.imcp.dao.model.bo.BoInfraredPart;
/*       */ import com.smarthome.imcp.dao.model.bo.BoIosVersion;
/*       */ import com.smarthome.imcp.dao.model.bo.BoLockVerdict;
/*       */ import com.smarthome.imcp.dao.model.bo.BoModel;
/*       */ import com.smarthome.imcp.dao.model.bo.BoModelInfo;
/*       */ import com.smarthome.imcp.dao.model.bo.BoNetworkNumber;
/*       */ import com.smarthome.imcp.dao.model.bo.BoOrder;
/*       */ import com.smarthome.imcp.dao.model.bo.BoRepairs;
/*       */ import com.smarthome.imcp.dao.model.bo.BoResendVerification;
/*       */ import com.smarthome.imcp.dao.model.bo.BoRoom;
/*       */ import com.smarthome.imcp.dao.model.bo.BoSensor;
/*       */ import com.smarthome.imcp.dao.model.bo.BoShoppingCart;
/*       */ import com.smarthome.imcp.dao.model.bo.BoUserDevices;
/*       */ import com.smarthome.imcp.dao.model.bo.BoUsers;
/*       */ import com.smarthome.imcp.dao.model.bo.BoUsersValidation;
/*       */ import com.smarthome.imcp.example.ChargeExample;
/*       */ import com.smarthome.imcp.service.bo.BoAirBindingPanelServiceIface;
/*       */ import com.smarthome.imcp.service.bo.BoAlarmRecordServiceIface;
/*       */ import com.smarthome.imcp.service.bo.BoAndroidVersionServiceIface;
/*       */ import com.smarthome.imcp.service.bo.BoChannelServiceIface;
/*       */ import com.smarthome.imcp.service.bo.BoControlEnclosureServiceIface;
/*       */ import com.smarthome.imcp.service.bo.BoDeviceServiceIface;
/*       */ import com.smarthome.imcp.service.bo.BoDeviceStateServiceIface;
/*       */ import com.smarthome.imcp.service.bo.BoDevicehostinfoServiceIface;
/*       */ import com.smarthome.imcp.service.bo.BoFeedBackServiceIface;
/*       */ import com.smarthome.imcp.service.bo.BoFloorServiceIface;
/*       */ import com.smarthome.imcp.service.bo.BoGoodsServiceIface;
/*       */ import com.smarthome.imcp.service.bo.BoHostDeviceServiceIface;
/*       */ import com.smarthome.imcp.service.bo.BoHostServiceIface;
/*       */ import com.smarthome.imcp.service.bo.BoInfraredButtonsServiceIface;
/*       */ import com.smarthome.imcp.service.bo.BoInfraredDeviceServiceIface;
/*       */ import com.smarthome.imcp.service.bo.BoInfraredLearnControlMapServiceIface;
/*       */ import com.smarthome.imcp.service.bo.BoInfraredPartServiceIface;
/*       */ import com.smarthome.imcp.service.bo.BoIosVersionServiceIface;
/*       */ import com.smarthome.imcp.service.bo.BoLockVerdictServiceIface;
/*       */ import com.smarthome.imcp.service.bo.BoModelInfoServiceIface;
/*       */ import com.smarthome.imcp.service.bo.BoModelServiceIface;
/*       */ import com.smarthome.imcp.service.bo.BoNetworkNumberServiceIface;
/*       */ import com.smarthome.imcp.service.bo.BoOrderServiceIface;
/*       */ import com.smarthome.imcp.service.bo.BoRepairsServiceIface;
/*       */ import com.smarthome.imcp.service.bo.BoResendVerificationServiceIface;
/*       */ import com.smarthome.imcp.service.bo.BoRoomServiceIface;
/*       */ import com.smarthome.imcp.service.bo.BoSensorServiceIface;
/*       */ import com.smarthome.imcp.service.bo.BoShoppingCartServiceIface;
/*       */ import com.smarthome.imcp.service.bo.BoUserDevicesServiceIface;
/*       */ import com.smarthome.imcp.service.bo.BoUsersValidationServiceIface;
/*       */ import com.smarthome.imcp.service.bo.BoUserssServiceIface;
/*       */ import com.smarthome.imcp.service.impl.push.PushService;
/*       */ import com.smarthome.imcp.service.system.FileServiceIface;
/*       */ import com.smarthome.imcp.util.MySecureProtocolSocketFactory;
/*       */ import com.smarthome.imcp.util.NumComparator;
/*       */ import com.smarthome.imcp.util.SendMsgUtil;
			import com.smarthome.imcp.util.SimulateHTTPRequestUtil;
/*       */ import com.smarthome.imcp.util.StaticUtils;
/*       */ import com.smarthome.imcp.util.TokeUtil;
/*       */ import com.smarthome.imcp.util.UuidUtil;
/*       */ import com.smarthome.imcp.util.YZUitl;
			import com.smarthome.imcp.util.androidAndIOS.Demo;
/*       */ import java.io.BufferedReader;
/*       */ import java.io.File;
/*       */ import java.io.IOException;
/*       */ import java.io.InputStream;
/*       */ import java.io.Serializable;
/*       */ import java.io.UnsupportedEncodingException;
/*       */ import java.math.BigDecimal;
/*       */ import java.text.SimpleDateFormat;
/*       */ import java.util.ArrayList;
/*       */ import java.util.Collections;
/*       */ import java.util.Comparator;
/*       */ import java.util.Date;
/*       */ import java.util.Enumeration;
/*       */ import java.util.HashMap;
/*       */ import java.util.HashSet;
/*       */ import java.util.Iterator;
/*       */ import java.util.List;
/*       */ import java.util.Map;
/*       */ import java.util.Set;
/*       */ import java.util.Timer;
/*       */ import java.util.TimerTask;
/*       */ import javax.servlet.http.HttpServletRequest;
/*       */ import javax.servlet.http.HttpServletResponse;
/*       */ import net.sf.json.JSONArray;
/*       */ import net.sf.json.JSONObject;
/*       */ import org.apache.commons.httpclient.HttpClient;
/*       */ import org.apache.commons.httpclient.methods.PostMethod;
/*       */ import org.apache.commons.httpclient.methods.RequestEntity;
/*       */ import org.apache.commons.httpclient.methods.StringRequestEntity;
/*       */ import org.apache.commons.httpclient.protocol.Protocol;
/*       */ import org.apache.commons.httpclient.protocol.ProtocolSocketFactory;
/*       */ import org.apache.commons.io.IOUtils;
/*       */ import org.apache.commons.lang3.StringUtils;
/*       */ import org.apache.struts2.ServletActionContext;
/*       */ import org.apache.struts2.convention.annotation.Action;
/*       */ import org.apache.struts2.convention.annotation.Namespace;
/*       */ import org.apache.struts2.convention.annotation.ParentPackage;
/*       */ import org.apache.struts2.json.JSONUtil;
/*       */ import org.apkinfo.api.GetApkInfo;
/*       */ import org.apkinfo.api.domain.ApkInfo;
/*       */ import org.slf4j.Logger;
/*       */ import org.slf4j.LoggerFactory;
/*       */ import org.springframework.beans.factory.annotation.Autowired;
			import org.apache.commons.codec.binary.Base64;//2-9

/*       */ @ParentPackage("auth-check-package")
/*       */ @Namespace("/xingUser")
/*       */ public class XingUserAction extends AbstractAction
/*       */ {
/*   188 */   private static Logger logger = LoggerFactory.getLogger(XingUserAction.class);
/*   189 */   private static Map<String, Integer> user_num = new HashMap();
/*       */ 
/*   204 */   public static String apiKey = "sk_live_KirHuHS8KKiL484Ke1Kq5uL8";
/*   208 */   public static String appId = "app_DOGKG8mDG0OSSyrz";

/*       */   public static final String CURRENCY_FEN_REGEX = "\\-?[0-9]+";
/*   212 */   SendMsgUtil s = new SendMsgUtil();
/*       */   public static final String content1 = "尊敬的用户，您的验证码为 : ";
/*       */   public static final String content2 = "【爱波瑞科技】尊敬的用户，您的验证码为 : ";
/*       */   public static final String siChuangcontent = "【思创智能】尊敬的用户，您的验证码为 :";
/*       */   public static final String siChuangcontent2 = "【思创智能】尊敬的用户，您的授权验证码为 :";
/*       */   public static final String fengTingcontent = "【峰庭智能】尊敬的用户，您的验证码为 :";
/*       */   public static final String fengTingcontent2 = "【峰庭智能】尊敬的用户，您的授权验证码为 :";
/*       */   public static final String content4 = "尊敬的用户，您的授权验证码为 : ";
/*       */   public static final String content5 = "【爱波瑞科技】尊敬的用户，您的授权验证码为 : ";
/*       */   public static final String lv = "12345";
/*   224 */   private static final Object HashMap = null;
/*   225 */   private static final String BoUsersValidation = null;
/*       */ 
/*   227 */   private char[] Array1 = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J' };
/*       */ 
/*       */   @Autowired
/*       */   private BoUserssServiceIface<BoUsers, Serializable> boUserServicess;
/*       */ 
/*       */   @Autowired
/*       */   private BoAndroidVersionServiceIface<BoAndroidVersion, Serializable> BoAndroidVersionService;
/*       */ 
/*       */   @Autowired
/*       */   private BoIosVersionServiceIface<BoIosVersion, Serializable> boIosVersionService;
/*       */ 
/*       */   @Autowired
/*       */   private BoControlEnclosureServiceIface<BoControlEnclosure, Serializable> boControlEnclosureService;
/*       */ 
/*       */   @Autowired
/*       */   private BoAlarmRecordServiceIface<BoAlarmRecord, Serializable> boAlarmRecordService;
/*       */ 
/*       */   @Autowired
/*       */   private BoUsersValidationServiceIface<BoUsersValidation, Serializable> boUsersValidationServicess;
/*       */ 
/*       */   @Autowired
/*       */   private BoDeviceServiceIface<BoDevice, Serializable> wdDeviceService;
/*       */ 
/*       */   @Autowired
/*       */   private BoModelInfoServiceIface<BoModelInfo, Serializable> boModelInfoServicess;
/*       */ 
/*       */   @Autowired
/*       */   private BoInfraredLearnControlMapServiceIface<BoInfraredLearnControlMap, Serializable> boInfraredLearnControlMapService;
/*       */ 
/*       */   @Autowired
/*       */   private BoRepairsServiceIface<BoRepairs, Serializable> boRepairsService;
/*       */ 
/*       */   @Autowired
/*       */   private BoOrderServiceIface<BoOrder, Serializable> boOrderService;
/*       */ 
/*       */   @Autowired
/*       */   private BoRoomServiceIface<BoRoom, Serializable> boRoomService;
/*       */ 
/*       */   @Autowired
/*       */   private BoFloorServiceIface<BoFloor, Serializable> boFloorService;
/*       */ 
/*       */   @Autowired
/*       */   private BoDevicehostinfoServiceIface<BoDevicehostinfo, Serializable> boDevicehostinfoService;
/*       */ 
/*       */   @Autowired
/*       */   private BoDeviceServiceIface<BoDevice, Serializable> boDeviceService;
/*       */ 
/*       */   @Autowired
/*       */   private BoUserDevicesServiceIface<BoUserDevices, Serializable> boUserDevicesServicess;
/*       */ 
/*       */   @Autowired
/*       */   private BoAirBindingPanelServiceIface<BoAirBindingPanel, Serializable> boAirBindingPanelService;
/*       */ 
/*       */   @Autowired
/*       */   private BoFeedBackServiceIface<BoFeedBack, Serializable> boFeedBackService;
/*       */ 
/*       */   @Autowired
/*       */   private BoHostServiceIface<BoHost, Serializable> boHostService;
/*       */ 
/*       */   @Autowired
/*       */   private BoLockVerdictServiceIface<BoLockVerdict, Serializable> boLockVerdictService;
/*       */ 
/*       */   @Autowired
/*       */   private BoHostDeviceServiceIface<BoHostDevice, Serializable> boHostDeviceService;
/*       */ 
/*       */   @Autowired
/*       */   private BoResendVerificationServiceIface<BoResendVerification, Serializable> boResendVerificationService;
/*       */ 
/*       */   @Autowired
/*       */   private BoSensorServiceIface<BoSensor, Serializable> boSensorService;
/*       */ 
/*       */   @Autowired
/*       */   private BoInfraredPartServiceIface<BoInfraredPart, Serializable> boInfraredPartService;
/*       */ 
/*       */   @Autowired
/*       */   private BoInfraredButtonsServiceIface<BoInfraredButtons, Serializable> boInfraredButtonsService;
/*       */ 
/*       */   @Autowired
/*       */   private BoNetworkNumberServiceIface<BoNetworkNumber, Serializable> boNetworkNumberService;
/*       */ 
/*       */   @Autowired
/*       */   private BoChannelServiceIface<BoChannel, Serializable> boChannelberService;
/*       */ 
/*       */   @Autowired
/*       */   private BoInfraredDeviceServiceIface<BoInfraredDevice, Serializable> boInfraredDeviceService;
/*       */ 
/*       */   @Autowired
/*       */   private BoGoodsServiceIface<BoGoods, Serializable> boGoodsService;
/*       */ 
/*       */   @Autowired
/*       */   private BoModelServiceIface<BoModel, Serializable> boModelService;
/*       */ 
/*       */   @Autowired
/*       */   private BoShoppingCartServiceIface<BoShoppingCart, Serializable> boShoppingCartService;
/*       */ 
/*       */   @Autowired
/*       */   private BoDeviceStateServiceIface<BoDeviceState, Serializable> boDeviceStateService;
/*       */ 
/*       */   @Autowired
/*       */   private FileServiceIface<Object, Serializable> fileService;
/*       */ 
/*       */   @Autowired
/*       */   private PacketProcessHelper packetProcessHelper;

/*       */   private File fileupload;
/*       */   private String fileuploadFileName;
/*       */   private String fileuploadContentType;
/* 16448 */   private RequestJson requestJson = new RequestJson();
/* 16449 */   private String contents = "";
/*       */   private Integer isStudy;
/* 16451 */   private String userCode = "";
/*       */ 
/* 16453 */   private String deviceOrHost = "";
/* 16454 */   private String modelId = "";
/* 16455 */   private String modelWeek = "";
/* 16456 */   private String modelTime = "";
/*       */   private Integer controlCommand;
/* 16458 */   private String modelInfo = "";
/* 16459 */   private String modelName = "";
/*       */   private String deviceAddress;
/* 16461 */   private String controlAction = "";
/* 16462 */   private String originalValue = "";
/*       */   private String infraredButtonsName;
/*       */   private String infraredButtonsValuess;
/*       */   private String validationCode;
/*       */   private Integer orderId;
/*       */   private String deviceType;
/*       */   private Integer version;
/* 16469 */   private String roomCode = "";
/* 16470 */   private String hostCode = "";
/* 16471 */   private String floorCode = "";
/* 16472 */   private String deviceCode = "";
/* 16473 */   private String userAge = "";
/* 16474 */   private String userSex = "";
/* 16475 */   private String signature = "";
/* 16476 */   private String userName = "";
/* 16477 */   private String city = "";
/* 16478 */   private String nickName = "";
/* 16479 */   private String pushContent = "";
/* 16480 */   private String sensorName = "";
/*       */   private Integer num;
/* 16482 */   private String numName = "";
/* 16483 */   private String ico = "";
/* 16484 */   private String startTime = "";
/* 16485 */   private String endTime = "";
/* 16486 */   private String security = "";
/* 16487 */   private String securityType = "";
/* 16488 */   private String howMany = "";
/* 16489 */   private String iconUrl = "";
/* 16490 */   private String networkNumber = "";
/* 16491 */   private String channel = "";
/*       */   private String commandType;
/* 16493 */   private String ln = null;
/*       */ 
/* 16495 */   private String currentPwd = "";
/* 16496 */   private String userPwd = "";
/* 16497 */   private String template = "";
/* 16498 */   private String accountOperationType = "";
/* 16499 */   private String userPhone = "";
/* 16500 */   private String oldUserPwd = "";
/* 16501 */   private String refreshToken = "";
/* 16502 */   private String accessToken = "";
/* 16503 */   private String userAddr = "";
/* 16504 */   private String floorName = "";
/* 16505 */   private String roomInfo = "";
/* 16506 */   private String floorInfo = "";
/* 16507 */   private String infraredButtonsInfo = "";
/* 16508 */   private String classesInfo = "";
/* 16509 */   private String sensorInfo = "";
/*       */ 
/* 16511 */   private String roomName = "";
/*       */   private Integer id;
/*       */   private Integer purchaseQuantity;
/* 16514 */   private String msg = "";
/*       */   private Integer satisfaction;
/*       */   private String code;
/* 16517 */   private Integer type = Integer.valueOf(0);
/* 16518 */   private String patternType = "";
/*       */   private Integer command;//a key variable 这个是怎么给定的？
/* 16520 */   private String CID = "";
/*       */   private Integer keyvalue;
/*       */   private Integer value;
/*       */   private String verifyCode;
/* 16524 */   private String readBackStatus = "wait";
/*       */   private BoResendVerification resendVerification;
/* 16526 */   private BoResendVerification save = null;
/* 16527 */   private String add = null;
/*       */   private String phoneType;
/* 16529 */   private String provider = "";
/* 16530 */   private String appVersion = "";
/* 16531 */   private String versionType = "1";
/*       */ 
/* 17036 */   private Boolean fid = Boolean.valueOf(false);
/*       */ 
/* 17038 */   private Boolean fid1 = Boolean.valueOf(true);
/*       */ 
/* 17057 */   private Integer pageNum = Integer.valueOf(1); private Integer pageSize = Integer.valueOf(50);
/*       */   private String orderField;
/*       */   private String orderDirection;

/*       */   public void packNum(String userCode)
/*       */   {
	            //1-29 map的put,get操作
//	            logger.info("packNum userCode:"+((Integer)user_num.get(userCode)).intValue());
/*   195 */     if (user_num.get(userCode) == null)
/*   196 */       user_num.put(userCode, Integer.valueOf(0));
/*       */     else
/*   198 */       user_num.put(userCode, Integer.valueOf(((Integer)user_num.get(userCode)).intValue() == 255 ? 0 : ((Integer)user_num.get(userCode)).intValue() + 1));
/*       */   }
/*       */ 
/*       */   public Boolean isRal(String timestamp, String nonce, String sign, String access_Token, String userCode, String interfaceName)
/*       */   {
/*   239 */     HttpServletRequest request = ServletActionContext.getRequest();
/*   240 */     String ip = request.getRemoteAddr();
/*   241 */     String str = "";
/*   242 */     Md5 md5 = new Md5();
/*   243 */     boolean isRel = true;
/*       */ 
/*   245 */     str = str + "access_token=";
/*   246 */     str = str + access_Token;
/*   247 */     logger.info(access_Token);
/*   248 */     System.err.println("access_Token：" + access_Token);
/*   249 */     str = str + "&nonce=";
/*   250 */     str = str + nonce;
/*   251 */     System.err.println("nonce：" + nonce);
/*   252 */     str = str + "&timestamp=";
/*   253 */     str = str + timestamp;
/*   254 */     System.err.println("timestamp：" + timestamp);
/*   255 */     str = str + "&userCode=";
/*   256 */     str = str + userCode;
/*   257 */     System.err.println("userCode：" + userCode);
/*   258 */     str = str + "12345";
/*   259 */     String service_sign = md5.getMD5ofStr(str).toLowerCase();
/*   260 */     System.err.println("客户端：" + sign);
/*   261 */     System.err.println("服务器：" + service_sign);
/*   262 */     System.err.println(userCode + " " + interfaceName);
/*   263 */     if (service_sign.equals(sign)) {
/*   264 */       return Boolean.valueOf(isRel);
/*       */     }
/*   266 */     logger.info(ip + interfaceName + "验证不通过");
/*   267 */     return Boolean.valueOf(false);
/*       */   }
/*       */ 
/*       */   public Boolean commandMode(String usereCode, String modelId)
/*       */   {//这里面的怎么都不打印
	            logger.info("in commandMode Method");
/*   276 */     System.err.println(usereCode + modelId);
/*   277 */     Map map = new HashMap();
/*       */ 
/*   279 */     Boolean isR = Boolean.valueOf(true);
/*       */     try
/*       */     {
/*   282 */       List list = this.boModelInfoServicess.getBy(usereCode, modelId);
/*   283 */       System.err.println(list.size());
/*   284 */       if (list.size() <= 0)
/*       */       {
/*   286 */         isR = Boolean.valueOf(false);
/*       */       }
/*       */       else
/*       */       {
/*   295 */         for (int indexs = 0; indexs < list.size(); indexs++)
/*       */         {//下面的代码 差不多都在循环里
/*   297 */           if (user_num.get(usereCode) == null)
/*   298 */             user_num.put(usereCode, Integer.valueOf(0));
/*       */           else {
/*   300 */             user_num.put(usereCode, Integer.valueOf(((Integer)user_num.get(usereCode)).intValue() == 255 ? 0 : ((Integer)user_num.get(usereCode)).intValue() + 1));
/*       */           }
/*   302 */           final BoModelInfo obj = (BoModelInfo)list.get(indexs);//to  split
/*       */ 
/*   304 */           Integer delayValues = obj.getDelayValues();
/*   305 */           Integer sss = delayValues;
/*   306 */           if (obj.getDeviceType().equals("32")) {
/*   307 */             if (obj.getDelayValues().intValue() == 300)
/*   308 */               Thread.sleep(100L);
/*       */             else
/*   310 */               Thread.sleep(sss.intValue());
/*       */           }
/*       */           else {
/*   313 */             Thread.sleep(sss.intValue());
/*       */           }
/*       */ 
/*   317 */           String controlCommand2 = obj.getControlCommand();//决定command的值？
//                      logger.info("······controlCommand2:"+controlCommand2);
/*   318 */           String[] split = controlCommand2.split(",");//a key variable
//					  logger.info("······split[0]:"+split[0]);//
/*   319 */           System.err.println("时间 " + sss);
/*       */ 
/*   321 */           System.err.println("device.getDeviceType() " + obj.getDeviceType());
/*   322 */           if (obj.getDeviceType().equals("1"))
/*       */           {
/*   324 */             String deviceAddress2 = obj.getDeviceAddress();
/*   325 */             String substring = deviceAddress2.substring(0, deviceAddress2.length() - 1);
/*   326 */             String substring2 = deviceAddress2.substring(deviceAddress2.length() - 1, deviceAddress2.length());

/*       */             final String s;
/*   328 */             if (split[0].equals("100")) {
/*   329 */               s = "1";
/*       */             }
/*       */             else {
/*   332 */               s = split[0];
/*       */             }
/*       */             try
/*       */             {
/*   336 */               this.resendVerification = this.boResendVerificationService
/*   337 */                 .find(obj.getBoDevice().getDeviceCode(), obj.getDeviceAddress(), s);
/*       */ 
/*   339 */               if (this.resendVerification == null)
/*       */               {
/*   341 */                 BoResendVerification resend = new BoResendVerification();
/*   342 */                 resend.setBoDevice(obj.getBoDevice());
/*   343 */                 resend.setDeviceAddress(obj.getDeviceAddress());
/*   344 */                 resend.setDeviceType(obj.getDeviceType());
/*   345 */                 resend.setCommand(s);
/*   346 */                 resend.setAcceptState("wait");
/*   347 */                 this.save = ((BoResendVerification)this.boResendVerificationService.save(resend));
/*       */               }
/*   349 */               String str = "ZIGBEE_LIGHT-SEND-" + user_num.get(usereCode) + "," + substring + "," + 
/*   350 */                 substring2 + "," + s;
/*   351 */               byte[] bs = str.getBytes();
/*   352 */               System.err.println(new String(bs));
/*       */ 
/*   354 */               System.err.println(obj.getBoDevice().getDeviceCode());
/*       */ 
/*   356 */               this.packetProcessHelper.processSendDData(obj.getBoDevice().getDeviceCode(), bs);
/*       */ 
/*   359 */               Timer timer = new Timer();
/*       */               try
/*       */               {
/*   362 */                 timer.schedule(new TimerTask() {
/*       */                   public void run() {
/*   364 */                     BoResendVerification resendVerificationss = XingUserAction.this.boResendVerificationService
/*   365 */                       .find(obj.getBoDevice().getDeviceCode(), obj.getDeviceAddress(), s);
/*   366 */                     XingUserAction.this.readBackStatus = resendVerificationss.getAcceptState();
/*   367 */                     System.err.println(XingUserAction.this.readBackStatus);
/*       */                   }
/*       */                 }
/*       */                 , 300L, 300L);
/*       */               } catch (NullPointerException e) {
/*   371 */                 logger.info(e.getMessage());
/*       */               }
/*       */ 
/*   374 */               String flag1 = "";
/*   375 */               this.readBackStatus = "wait";
/*   376 */               int index = 0;
/*   377 */               while (index < 40)
/*       */               {
/*   379 */                 if (this.readBackStatus.equals("OK")) {
/*       */                   break;
/*       */                 }
/*   382 */                 if (this.readBackStatus.equals("ERR"))
/*       */                   break;
/*   384 */                 if ((this.readBackStatus.equals("1")) && (flag1 == "")) {
/*   385 */                   String stsr = "ZIGBEE_LIGHT-SEND-" + user_num.get(usereCode) + "," + substring + 
/*   386 */                     "," + substring2 + "," + s;
/*   387 */                   byte[] stsrs = stsr.getBytes();
/*   388 */                   System.err.println(new String(bs));
/*       */ 
/*   390 */                   this.packetProcessHelper.processSendDData(obj.getBoDevice().getDeviceCode(), 
/*   391 */                     stsrs);
/*   392 */                   this.packetProcessHelper.setUserCode(usereCode);
/*   393 */                   flag1 = "1";
/*   394 */                 } else if ((this.readBackStatus.equals("2")) && (flag1 == "1")) {
/*   395 */                   String stsr = "ZIGBEE_LIGHT-SEND-" + user_num.get(usereCode) + "," + substring + 
/*   396 */                     "," + substring2 + "," + s;
/*   397 */                   byte[] stsrs = stsr.getBytes();
/*   398 */                   System.err.println(new String(bs));
/*   399 */                   this.packetProcessHelper.processSendDData(obj.getBoDevice().getDeviceCode(), 
/*   400 */                     stsrs);
/*   401 */                   this.packetProcessHelper.setUserCode(usereCode);
/*   402 */                   flag1 = "2";
/*   403 */                 } else if (((this.readBackStatus.equals("wait")) && (index == 3)) || (
/*   404 */                   (this.readBackStatus.equals("wait")) && (index == 5))) {
/*   405 */                   String stsr = "ZIGBEE_LIGHT-SEND-" + user_num.get(usereCode) + "," + substring + 
/*   406 */                     "," + substring2 + "," + s;
/*   407 */                   byte[] stsrs = stsr.getBytes();
/*   408 */                   System.err.println(new String(bs));
/*   409 */                   this.packetProcessHelper.processSendDData(obj.getBoDevice().getDeviceCode(), 
/*   410 */                     stsrs);
/*   411 */                   this.packetProcessHelper.setUserCode(usereCode);
/*       */                 }
/*       */ 
/*   414 */                 Thread.sleep(300L);
/*       */ 
/*   416 */                 index++;
/*       */               }
/*   418 */               timer.cancel();
/*       */               try
/*       */               {
/*   421 */                 this.boResendVerificationService.delete(this.save);
/*       */               }
/*       */               catch (Exception e) {
/*   424 */                 logger.info(e.getMessage());
/*   425 */                 System.err.println(e.getMessage());
/*       */               }
/*       */             }
/*       */             catch (InterruptedException localInterruptedException) {
/*       */             }
/*       */           }
/*   431 */           else if (obj.getDeviceType().equals("2")) {
/*   432 */             final String tg = split[0];
/*   433 */             System.err.println("split[0] " + split[0]);
/*   434 */             Integer commands = null;
/*   435 */             if (Integer.valueOf(split[0]).intValue() == 0) {
/*   436 */               commands = Integer.valueOf(19);
/*       */             }
/*       */ 
/*   439 */             if (Integer.valueOf(split[0]).intValue() == 100)
/*   440 */               commands = Integer.valueOf(17);
/*       */             else
/*   442 */               commands = Integer.valueOf(20);
/*       */             int commandss;

/*   445 */             if (Integer.valueOf(split[0]).intValue() >= 95)
/*   446 */               commandss = 95;
/*       */             else
/*   448 */               commandss = Integer.valueOf(split[0]).intValue();
/*       */             try
/*       */             {
/*   451 */               this.resendVerification = this.boResendVerificationService
/*   452 */                 .find(obj.getBoDevice().getDeviceCode(), obj.getDeviceAddress(), tg);
/*       */ 
/*   454 */               if (this.resendVerification == null)
/*       */               {
/*   456 */                 BoResendVerification resend = new BoResendVerification();
/*   457 */                 resend.setBoDevice(obj.getBoDevice());
/*   458 */                 resend.setDeviceAddress(obj.getDeviceAddress());
/*   459 */                 resend.setDeviceType(obj.getDeviceType());
/*   460 */                 resend.setCommand(tg);
/*   461 */                 resend.setAcceptState("wait");
/*   462 */                 this.save = ((BoResendVerification)this.boResendVerificationService.save(resend));
/*       */               }
/*   464 */               String str = "ZIGBEE_CURTAIN-SEND-" + user_num.get(usereCode) + "," + 
/*   465 */                 obj.getDeviceAddress() + "," + commands + "," + commandss;
/*   466 */               byte[] bs = str.getBytes();
/*   467 */               System.err.println(new String(bs));
/*       */ 
/*   469 */               this.packetProcessHelper.processSendDDatas(obj.getBoDevice().getDeviceCode(), bs);
/*       */ 
/*   472 */               Timer timer = new Timer();
/*       */               try
/*       */               {
/*   475 */                 timer.schedule(new TimerTask() {
/*       */                   public void run() {
/*   477 */                     BoResendVerification resendVerificationss = XingUserAction.this.boResendVerificationService
/*   478 */                       .find(obj.getBoDevice().getDeviceCode(), obj.getDeviceAddress(), tg);
/*   479 */                     XingUserAction.this.readBackStatus = resendVerificationss.getAcceptState();
/*   480 */                     System.err.println(XingUserAction.this.readBackStatus);
/*       */                   }
/*       */                 }
/*       */                 , 300L, 300L);
/*       */               } catch (NullPointerException e) {
/*   484 */                 logger.info(e.getMessage());
/*       */               }
/*       */ 
/*   487 */               String flag1 = "";
/*   488 */               this.readBackStatus = "wait";
/*   489 */               int index = 0;
/*   490 */               while (index < 25)
/*       */               {
/*   492 */                 if (this.readBackStatus.equals("OK")) {
/*       */                   break;
/*       */                 }
/*   495 */                 if (this.readBackStatus.equals("ERR"))
/*       */                   break;
/*   497 */                 if ((this.readBackStatus.equals("1")) && (flag1 == ""))
/*       */                 {
/*   500 */                   String tgstr = "ZIGBEE_CURTAIN-SEND-" + user_num.get(usereCode) + "," + 
/*   501 */                     obj.getDeviceAddress() + "," + commands + "," + commandss;
/*   502 */                   byte[] tgbs = tgstr.getBytes();
/*   503 */                   System.err.println(new String(tgbs));
/*   504 */                   this.packetProcessHelper.processSendDDatas(obj.getBoDevice().getDeviceCode(), 
/*   505 */                     tgbs);
/*   506 */                   this.packetProcessHelper.setUserCode(usereCode);
/*   507 */                   flag1 = "1";
/*   508 */                 } else if ((this.readBackStatus.equals("2")) && (flag1 == "1")) {
/*   509 */                   String tgstr = "ZIGBEE_CURTAIN-SEND-" + user_num.get(usereCode) + "," + 
/*   510 */                     obj.getDeviceAddress() + "," + commands + "," + commandss;
/*   511 */                   byte[] tgbs = tgstr.getBytes();
/*   512 */                   System.err.println(new String(tgbs));
/*   513 */                   this.packetProcessHelper.processSendDDatas(obj.getBoDevice().getDeviceCode(), 
/*   514 */                     tgbs);
/*       */ 
/*   516 */                   flag1 = "2";
/*   517 */                 } else if (((this.readBackStatus.equals("wait")) && (index == 2)) || (
/*   518 */                   (this.readBackStatus.equals("wait")) && (index == 4))) {
/*   519 */                   String tgstr = "ZIGBEE_CURTAIN-SEND-" + user_num.get(usereCode) + "," + 
/*   520 */                     obj.getDeviceAddress() + "," + commands + "," + commandss;
/*   521 */                   byte[] tgbs = tgstr.getBytes();
/*   522 */                   System.err.println(new String(tgbs));
/*   523 */                   this.packetProcessHelper.processSendDDatas(obj.getBoDevice().getDeviceCode(), 
/*   524 */                     tgbs);
/*       */                 }
/*       */ 
/*   528 */                 Thread.sleep(150L);
/*       */ 
/*   530 */                 index++;
/*       */               }
/*   532 */               timer.cancel();
/*       */               try
/*       */               {
/*   535 */                 this.boResendVerificationService.delete(this.save);
/*       */               }
/*       */               catch (Exception e) {
/*   538 */                 logger.info(e.getMessage());
/*       */               }
/*       */             }
/*       */             catch (InterruptedException localInterruptedException1) {
/*       */             }
/*       */           }
/*   544 */           else if (obj.getDeviceType().equals("4"))
/*       */           {
/*   546 */             final String tg = split[0];
/*       */             try {
/*   548 */               this.resendVerification = this.boResendVerificationService
/*   549 */                 .find(obj.getBoDevice().getDeviceCode(), obj.getDeviceAddress(), tg);
/*       */ 
/*   551 */               if (this.resendVerification == null)
/*       */               {
/*   553 */                 BoResendVerification resend = new BoResendVerification();
/*   554 */                 resend.setBoDevice(obj.getBoDevice());
/*   555 */                 resend.setDeviceAddress(obj.getDeviceAddress());
/*   556 */                 resend.setDeviceType(obj.getDeviceType());
/*   557 */                 resend.setCommand(tg);
/*   558 */                 resend.setAcceptState("wait");
/*   559 */                 this.save = ((BoResendVerification)this.boResendVerificationService.save(resend));
/*       */               }
/*   561 */               String str = "ZIGBEE_DIMMER-SEND-" + user_num.get(usereCode) + "," + 
/*   562 */                 obj.getDeviceAddress() + "," + split[0];
/*   563 */               byte[] bs = str.getBytes();
/*   564 */               System.err.println(new String(bs));
/*       */ 
/*   566 */               this.packetProcessHelper.processSendDDatas(obj.getBoDevice().getDeviceCode(), bs);
/*       */ 
/*   568 */               Timer timer = new Timer();
/*       */               try
/*       */               {
/*   571 */                 timer.schedule(new TimerTask() {
/*       */                   public void run() {
/*   573 */                     BoResendVerification resendVerificationss = XingUserAction.this.boResendVerificationService
/*   574 */                       .find(obj.getBoDevice().getDeviceCode(), obj.getDeviceAddress(), tg);
/*   575 */                     XingUserAction.this.readBackStatus = resendVerificationss.getAcceptState();
/*   576 */                     System.err.println(XingUserAction.this.readBackStatus);
/*       */                   }
/*       */                 }
/*       */                 , 200L, 200L);
/*       */               } catch (NullPointerException e) {
/*   580 */                 logger.info(e.getMessage());
/*       */               }
/*       */ 
/*   583 */               String flag1 = "";
/*   584 */               this.readBackStatus = "wait";
/*   585 */               int index = 0;
/*   586 */               while (index < 25)
/*       */               {
/*   588 */                 if (this.readBackStatus.equals("OK")) {
/*       */                   break;
/*       */                 }
/*   591 */                 if (this.readBackStatus.equals("ERR"))
/*       */                   break;
/*   593 */                 if ((this.readBackStatus.equals("1")) && (flag1 == "")) {
/*   594 */                   String tgstr = "ZIGBEE_DIMMER-SEND-" + user_num.get(usereCode) + "," + 
/*   595 */                     obj.getDeviceAddress() + "," + split[0];
/*   596 */                   byte[] tgbs = tgstr.getBytes();
/*   597 */                   System.err.println(new String(tgbs));
/*   598 */                   this.packetProcessHelper.processSendDDatas(obj.getBoDevice().getDeviceCode(), 
/*   599 */                     tgbs);
/*   600 */                   this.packetProcessHelper.setUserCode(usereCode);
/*   601 */                   flag1 = "1";
/*   602 */                 } else if ((this.readBackStatus.equals("2")) && (flag1 == "1")) {
/*   603 */                   String tgstr = "ZIGBEE_DIMMER-SEND-" + user_num.get(usereCode) + "," + 
/*   604 */                     obj.getDeviceAddress() + "," + split[0];
/*   605 */                   byte[] tgbs = tgstr.getBytes();
/*   606 */                   System.err.println(new String(tgbs));
/*   607 */                   this.packetProcessHelper.processSendDDatas(obj.getBoDevice().getDeviceCode(), 
/*   608 */                     tgbs);
/*   609 */                   this.packetProcessHelper.setUserCode(usereCode);
/*   610 */                   flag1 = "2";
/*   611 */                 } else if (((this.readBackStatus.equals("wait")) && (index == 2)) || (
/*   612 */                   (this.readBackStatus.equals("wait")) && (index == 4))) {
/*   613 */                   String tgstr = "ZIGBEE_DIMMER-SEND-" + user_num.get(usereCode) + "," + 
/*   614 */                     obj.getDeviceAddress() + "," + split[0];
/*   615 */                   byte[] tgbs = tgstr.getBytes();
/*   616 */                   System.err.println(new String(tgbs));
/*   617 */                   this.packetProcessHelper.processSendDDatas(obj.getBoDevice().getDeviceCode(), 
/*   618 */                     tgbs);
/*       */                 }
/*       */ 
/*   622 */                 Thread.sleep(150L);
/*       */ 
/*   624 */                 index++;
/*       */               }
/*   626 */               timer.cancel();
/*       */               try
/*       */               {
/*   629 */                 this.boResendVerificationService.delete(this.save);
/*       */               }
/*       */               catch (Exception e) {
/*   632 */                 logger.info(e.getMessage());
/*       */               }
/*       */             } catch (InterruptedException localInterruptedException2) {
/*       */             }
/*       */           }
/*   637 */           else if (obj.getDeviceType().equals("99")) {
/*   638 */             if (split.length == 3) {
/*   639 */               if (split[2].trim().toString().equals("A")) {
/*   640 */                 BoInfraredLearnControlMap controlMap = this.boInfraredLearnControlMapService.findBy(usereCode, obj.getDeviceAddress(), split[0]);
/*   641 */                 if (controlMap == null)
/*       */                 {
/*   643 */                   System.err.println("您还没学习过该按键");
/*       */                 } else {
/*   645 */                   String zgbzf = split[0];
/*   646 */                   BoInfraredPart findss = this.boInfraredPartService.find(obj.getDeviceAddress());
/*       */                   String s;

/*   648 */                   if (findss == null)
/*   649 */                     s = "0000000000";
/*       */                   else {
/*   651 */                     s = findss.getValidationCode();
/*       */                   }
/*   653 */                   String str = "ZIGBEE_INFRARED-SEND-" + user_num.get(usereCode) + "," + 
/*   654 */                     obj.getDeviceAddress() + "," + controlMap.getChangeValue() + "," + s;
/*   655 */                   byte[] bs = str.getBytes();
/*   656 */                   System.err.println(new String(bs));
/*       */ 
/*   658 */                   this.packetProcessHelper.processSendDDatas(obj.getBoDevice().getDeviceCode(), bs);
/*       */                 }
/*       */               }
/*       */               else
/*       */               {
/*   663 */                 BoInfraredLearnControlMap controlMap = this.boInfraredLearnControlMapService.findBy(usereCode, obj.getDeviceAddress(), split[0]);
/*   664 */                 if (controlMap == null) {
/*   665 */                   System.err.println("您还没学习过改按键");
/*       */                 }
/*       */                 else {
/*   668 */                   BoInfraredPart findss = this.boInfraredPartService.find(obj.getDeviceAddress());
/*       */                   String s;

/*   670 */                   if (findss == null)
/*   671 */                     s = "0000000000";
/*       */                   else {
/*   673 */                     s = findss.getValidationCode();
/*       */                   }
/*   675 */                   String str = "ZIGBEE_INFRARED-SEND-" + user_num.get(usereCode) + "," + 
/*   676 */                     obj.getDeviceAddress() + "," + controlMap.getChangeValue() + "," + s;//INFRARED 红外
/*   677 */                   byte[] bs = str.getBytes();
/*   678 */                   System.err.println(new String(bs));
/*       */ 
/*   680 */                   this.packetProcessHelper.processSendDDatas(obj.getBoDevice().getDeviceCode(), bs);
/*       */                 }
/*       */               }
/*       */             }
/*       */             else {
/*   685 */               BoInfraredLearnControlMap controlMap = this.boInfraredLearnControlMapService.findBy(usereCode, obj.getDeviceAddress(), split[0]);
/*   686 */               if (controlMap == null)
/*       */               {
/*   688 */                 System.err.println("您还没学习过该按键");
/*       */               }
/*       */               else {
/*   691 */                 BoInfraredPart findss = this.boInfraredPartService.find(obj.getDeviceAddress());
/*       */                 String s;

/*   693 */                 if (findss == null)
/*   694 */                   s = "0000000000";
/*       */                 else {
/*   696 */                   s = findss.getValidationCode();
/*       */                 }
/*   698 */                 String str = "ZIGBEE_INFRARED-SEND-" + user_num.get(usereCode) + "," + 
/*   699 */                   obj.getDeviceAddress() + "," + controlMap.getChangeValue() + "," + s;
/*   700 */                 byte[] bs = str.getBytes();
/*   701 */                 System.err.println(new String(bs));
/*       */ 
/*   703 */                 this.packetProcessHelper.processSendDDatas(obj.getBoDevice().getDeviceCode(), bs);
/*       */               }
/*       */             }
/*       */ 
/*       */           }
/*   708 */           else if (obj.getDeviceType().equals("98")) {
/*   709 */             if (split.length == 3) {
/*   710 */               if (split[2].trim().toString().equals("A")) {
/*   711 */                 BoInfraredLearnControlMap controlMap = this.boInfraredLearnControlMapService.findBy(usereCode, obj.getDeviceAddress(), split[0]);
/*   712 */                 if (controlMap == null)
/*       */                 {
/*   714 */                   System.err.println("您还没学习过该按键");
/*       */                 } else {
/*   716 */                   String zgbzf = split[0];
/*   717 */                   BoInfraredPart findss = this.boInfraredPartService.find(obj.getDeviceAddress());
/*       */                   String s;

/*   719 */                   if (findss == null)
/*   720 */                     s = "0000000000";
/*       */                   else {
/*   722 */                     s = findss.getValidationCode();
/*       */                   }
/*   724 */                   String[] split2 = obj.getDeviceAddress().split(",");
/*   725 */                   String str = "ZIGBEE_INFRARED-SEND-" + user_num.get(usereCode) + "," + 
/*   726 */                     split2[0] + "," + controlMap.getChangeValue() + "," + s;
/*   727 */                   byte[] bs = str.getBytes();
/*   728 */                   System.err.println(new String(bs));
/*       */ 
/*   730 */                   this.packetProcessHelper.processSendDDatas(obj.getBoDevice().getDeviceCode(), bs);
/*       */                 }
/*       */               }
/*       */               else
/*       */               {
/*   735 */                 BoInfraredLearnControlMap controlMap = this.boInfraredLearnControlMapService.findBy(usereCode, obj.getDeviceAddress(), split[0]);
/*   736 */                 if (controlMap == null)
/*       */                 {
/*   738 */                   System.err.println("您还没学习过该按键");
/*       */                 } else {
/*   740 */                   BoInfraredPart findss = this.boInfraredPartService.find(obj.getDeviceAddress());
/*       */                   String s;

/*   742 */                   if (findss == null)
/*   743 */                     s = "0000000000";
/*       */                   else {
/*   745 */                     s = findss.getValidationCode();
/*       */                   }
/*   747 */                   String[] split2 = obj.getDeviceAddress().split(",");
/*   748 */                   String str = "ZIGBEE_INFRARED-SEND-" + user_num.get(usereCode) + "," + 
/*   749 */                     split2[0] + "," + controlMap.getChangeValue() + "," + s;
/*   750 */                   byte[] bs = str.getBytes();
/*   751 */                   System.err.println(new String(bs));
/*       */ 
/*   753 */                   this.packetProcessHelper.processSendDDatas(obj.getBoDevice().getDeviceCode(), bs);
/*       */                 }
/*       */               }
/*       */             }
/*       */             else {
/*   758 */               BoInfraredLearnControlMap controlMap = this.boInfraredLearnControlMapService.findBy(usereCode, obj.getDeviceAddress(), split[0]);
/*   759 */               if (controlMap == null)
/*       */               {
/*   761 */                 System.err.println("您还没学习过该按键");
/*       */               } else {
/*   763 */                 BoInfraredPart findss = this.boInfraredPartService.find(obj.getDeviceAddress());
/*       */                 String s;

/*   765 */                 if (findss == null)
/*   766 */                   s = "0000000000";
/*       */                 else {
/*   768 */                   s = findss.getValidationCode();
/*       */                 }
/*   770 */                 String[] split2 = obj.getDeviceAddress().split(",");
/*   771 */                 String str = "ZIGBEE_INFRARED-SEND-" + user_num.get(usereCode) + "," + 
/*   772 */                   split2[0] + "," + controlMap.getChangeValue() + "," + s;
/*   773 */                 byte[] bs = str.getBytes();
/*   774 */                 System.err.println(new String(bs));
/*       */ 
/*   776 */                 this.packetProcessHelper.processSendDDatas(obj.getBoDevice().getDeviceCode(), bs);
/*       */               }
/*       */ 
/*       */             }
/*       */ 
/*       */           }
						else if (obj.getDeviceType().equals("201")) {
						    System.out.println("model 201 wifi split[0]:" + split[0]);
						    byte open_close = 0;
						    if (split[0].equals("100")) {
						      open_close = 1;
						    }
						
						    byte[] byte_data = { 2, open_close };
						    String str_hex = Base64.encodeBase64String(byte_data);
						    JSONObject jsonSend = new JSONObject();
						    jsonSend.put("token", "9f93b08e45104e2780136a292aa121a4");
						    jsonSend.put("device_id", obj.getDeviceAddress());
						    jsonSend.put("data", str_hex);
						
						    String str_content = Base64.encodeBase64String(jsonSend.toString().getBytes());
						
						    String sendURL = "http://relay.hificat.com:8000/send_data?content=" + str_content;
						    SimulateHTTPRequestUtil simulateHTTPRequestUtil = new SimulateHTTPRequestUtil();
						    String http_result = simulateHTTPRequestUtil.sendGet2(sendURL);
						    System.out.println("sendURL:" + sendURL + " http_result:" + http_result);
					  }
/*   783 */           else if (obj.getDeviceAddress().toString().length() == 8) {//2-9 modify
					    String deviceType = obj.getDeviceType().toString();
					    String substring = deviceType.substring(1, 2);
					    Integer valueOf = Integer.valueOf(substring);
					    String s_code = null;
					    if (valueOf.intValue() == 1) {
					      s_code = "2262";
					    } else if (valueOf.intValue() == 3) {
					      int i_deviceType = 0;
					      try { i_deviceType = Integer.valueOf(deviceType).intValue(); } catch (Exception localException1) {
					      }
					      if ((i_deviceType >= 4000) && (i_deviceType <= 4999))
					      {
					        s_code = "DYDJ";
					      } else if ((i_deviceType >= 5000) && (i_deviceType <= 5999))
					      {
					        s_code = "ZNMS";
					      }
					      System.out.println("------i_deviceType:" + i_deviceType + " s_code:" + s_code);
					    } else {
					      s_code = "1527";
					    }
					
					    String substring2 = deviceType.substring(2, 3);
					    Integer valueOf2 = Integer.valueOf(substring2);
					    Integer b = null;
					    if (valueOf2.intValue() == 1)
					      b = Integer.valueOf(315);
					    else {
					      b = Integer.valueOf(433);
					    }
					
					    String substring3 = deviceType.substring(3, 4);
					    Integer valueOf3 = Integer.valueOf(substring3);
					    Integer c = null;
					    if (valueOf3.intValue() == 1) {
					      c = Integer.valueOf(12);
					    }
					    if (valueOf3.intValue() == 2) {
					      c = Integer.valueOf(15);
					    }
					    if (valueOf3.intValue() == 3) {
					      c = Integer.valueOf(22);
					    }
					
					    if (valueOf3.intValue() == 4) {
					      c = Integer.valueOf(33);
					    }
					    if (valueOf3.intValue() == 5)
					      c = Integer.valueOf(47);
					    else if (valueOf3.intValue() == 6)
					      c = Integer.valueOf(330);
					    else if (valueOf3.intValue() == 7)
					      c = Integer.valueOf(390);
					    else if (valueOf3.intValue() == 8) {
					      c = Integer.valueOf(200);
					    }
					
					    if (split[0].equals("0"))
					    {
					      String str = "PT" + s_code + "_" + b + "M-SEND-" + user_num.get(usereCode) + "," + c + 
					        "," + obj.getDeviceAddress() + 2;
					      byte[] bs = str.getBytes();
					      System.err.println(new String(bs));
					
					      this.packetProcessHelper.processSendDData(obj.getBoDevice().getDeviceCode(), bs);
					    }
					
					    if (split[0].equals("50"))
					    {
					      String str = "PT" + s_code + "_" + b + "M-SEND-" + user_num.get(usereCode) + "," + c + 
					        "," + obj.getDeviceAddress() + 3;
					      byte[] bs = str.getBytes();
					      System.err.println(new String(bs));
					      this.packetProcessHelper.processSendDData(obj.getBoDevice().getDeviceCode(), bs);
					    }
					
					    if (split[0].equals("100"))
					    {
					      String str = "PT" + s_code + "_" + b + "M-SEND-" + user_num.get(usereCode) + "," + c + 
					        "," + obj.getDeviceAddress() + 1;
					      byte[] bs = str.getBytes();
					      System.err.println(new String(bs));
					
					      this.packetProcessHelper.processSendDData(obj.getBoDevice().getDeviceCode(), bs);
					    }
					
					    if (split[0].equals("150"))
					    {
					      String str = "PT" + s_code + "_" + b + "M-SEND-" + user_num.get(usereCode) + "," + c + 
					        "," + obj.getDeviceAddress() + 4;
					      byte[] bs = str.getBytes();
					      System.err.println(new String(bs));
					      this.packetProcessHelper.processSendDData(obj.getBoDevice().getDeviceCode(), bs);
					    }
					  } else if (obj.getDeviceType().equals("8")) {
/*       */             try {
/*   960 */               this.resendVerification = this.boResendVerificationService
/*   961 */                 .find(obj.getBoDevice().getDeviceCode(), obj.getDeviceAddress(), split[2]);
/*       */ 
/*   963 */               if (this.resendVerification == null)
/*       */               {
/*   965 */                 BoResendVerification resend = new BoResendVerification();
/*   966 */                 resend.setBoDevice(obj.getBoDevice());
/*   967 */                 resend.setDeviceAddress(obj.getDeviceAddress());
/*   968 */                 resend.setDeviceType(obj.getDeviceType());
/*   969 */                 resend.setCommand(split[2]);
/*   970 */                 resend.setAcceptState("wait");
/*   971 */                 this.save = ((BoResendVerification)this.boResendVerificationService.save(resend));
/*       */               }
/*   973 */               String strss = "RELAY-SET-" + user_num.get(usereCode) + "," + split[0] + "," + split[2];
/*   974 */               byte[] bss = strss.getBytes();
/*   975 */               System.err.println(new String(bss));
/*   976 */               this.packetProcessHelper.processSendDDatas(obj.getBoDevice().getDeviceCode(), bss);
/*       */ 
/*   978 */               Timer timer = new Timer();
/*   979 */               final String SD = split[2];
/*       */               try {
/*   981 */                 timer.schedule(new TimerTask() {
/*       */                   public void run() {
/*   983 */                     BoResendVerification resendVerificationss = XingUserAction.this.boResendVerificationService
/*   984 */                       .find(obj.getBoDevice().getDeviceCode(), obj.getDeviceAddress(), SD);
/*   985 */                     XingUserAction.this.readBackStatus = resendVerificationss.getAcceptState();
/*   986 */                     System.err.println(XingUserAction.this.readBackStatus);
/*       */                   }
/*       */                 }
/*       */                 , 100L, 100L);
/*       */               } catch (NullPointerException e) {
/*   990 */                 logger.info(e.getMessage());
/*       */               }
/*       */ 
/*   993 */               String flag1 = "";
/*   994 */               this.readBackStatus = "wait";
/*   995 */               int index = 0;
/*   996 */               while (index < 10)
/*       */               {
/*   998 */                 if (this.readBackStatus.equals("OK")) {
/*       */                   break;
/*       */                 }
/*  1001 */                 if (this.readBackStatus.equals("ERR"))
/*       */                   break;
/*  1003 */                 if ((this.readBackStatus.equals("1")) && (flag1 == "")) {
/*  1004 */                   String strs = "RELAY-SET-" + user_num.get(usereCode) + "," + split[0] + "," + split[2];
/*  1005 */                   byte[] bsss = strs.getBytes();
/*  1006 */                   System.err.println(new String(bss));
/*  1007 */                   this.packetProcessHelper.processSendDDatas(obj.getBoDevice().getDeviceCode(), bsss);
/*  1008 */                   flag1 = "1";
/*  1009 */                 } else if ((this.readBackStatus.equals("2")) && (flag1 == "1")) {
/*  1010 */                   String strs = "RELAY-SET-" + user_num.get(usereCode) + "," + split[0] + "," + split[2];
/*  1011 */                   byte[] bsss = strs.getBytes();
/*  1012 */                   System.err.println(new String(bss));
/*  1013 */                   this.packetProcessHelper.processSendDDatas(obj.getBoDevice().getDeviceCode(), bsss);
/*  1014 */                   flag1 = "2";
/*  1015 */                 } else if (((this.readBackStatus.equals("wait")) && (index == 1)) || (
/*  1016 */                   (this.readBackStatus.equals("wait")) && (index == 2))) {
/*  1017 */                   String strs = "RELAY-SET-" + user_num.get(usereCode) + "," + split[0] + "," + split[2];
/*  1018 */                   byte[] bsss = strs.getBytes();
/*  1019 */                   System.err.println(new String(bss));
/*  1020 */                   this.packetProcessHelper.processSendDDatas(obj.getBoDevice().getDeviceCode(), bsss);
/*       */                 }
/*       */ 
/*  1023 */                 Thread.sleep(30L);
/*       */ 
/*  1025 */                 index++;
/*       */               }
/*  1027 */               timer.cancel();
/*       */               try
/*       */               {
/*  1030 */                 this.boResendVerificationService.delete(this.save);
/*       */               }
/*       */               catch (Exception e) {
/*  1033 */                 logger.info(e.getMessage());
/*  1034 */                 System.err.println(e.getMessage());
/*       */               }
/*       */             }
/*       */             catch (InterruptedException localInterruptedException3)
/*       */             {
/*       */             }
/*       */           }
/*  1041 */           else if (obj.getDeviceType().equals("32")) {
/*       */             try {
/*  1043 */               this.resendVerification = this.boResendVerificationService
/*  1044 */                 .find(obj.getBoDevice().getDeviceCode(), obj.getDeviceAddress(), split[2]);
/*       */ 
/*  1046 */               if (this.resendVerification == null)
/*       */               {
/*  1048 */                 BoResendVerification resend = new BoResendVerification();
/*  1049 */                 resend.setBoDevice(obj.getBoDevice());
/*  1050 */                 resend.setDeviceAddress(obj.getDeviceAddress());
/*  1051 */                 resend.setDeviceType(obj.getDeviceType());
/*  1052 */                 resend.setCommand(split[2]);
/*  1053 */                 resend.setAcceptState("wait");
/*  1054 */                 this.save = ((BoResendVerification)this.boResendVerificationService.save(resend));
/*       */               }
/*  1056 */               String strss = "RELAY-SET-" + user_num.get(usereCode) + "," + split[0] + "," + split[2];
/*  1057 */               byte[] bss = strss.getBytes();
/*  1058 */               System.err.println(new String(bss));
/*  1059 */               this.packetProcessHelper.processSendDDatas(obj.getBoDevice().getDeviceCode(), bss);
/*       */ 
/*  1061 */               Timer timer = new Timer();
/*  1062 */               final String SD = split[2];
/*       */               try {
/*  1064 */                 timer.schedule(new TimerTask() {
/*       */                   public void run() {
/*  1066 */                     BoResendVerification resendVerificationss = XingUserAction.this.boResendVerificationService
/*  1067 */                       .find(obj.getBoDevice().getDeviceCode(), obj.getDeviceAddress(), SD);
/*  1068 */                     XingUserAction.this.readBackStatus = resendVerificationss.getAcceptState();
/*  1069 */                     System.err.println(XingUserAction.this.readBackStatus);
/*       */                   }
/*       */                 }
/*       */                 , 100L, 100L);
/*       */               } catch (NullPointerException e) {
/*  1073 */                 logger.info(e.getMessage());
/*       */               }
/*       */ 
/*  1076 */               String flag1 = "";
/*  1077 */               this.readBackStatus = "wait";
/*  1078 */               int index = 0;
/*  1079 */               while (index < 10)
/*       */               {
/*  1081 */                 if (this.readBackStatus.equals("OK")) {
/*       */                   break;
/*       */                 }
/*  1084 */                 if (this.readBackStatus.equals("ERR"))
/*       */                   break;
/*  1086 */                 if ((this.readBackStatus.equals("1")) && (flag1 == "")) {
/*  1087 */                   String strs = "RELAY-SET-" + user_num.get(usereCode) + "," + split[0] + "," + split[2];
/*  1088 */                   byte[] bsss = strs.getBytes();
/*  1089 */                   System.err.println(new String(bss));
/*  1090 */                   this.packetProcessHelper.processSendDDatas(obj.getBoDevice().getDeviceCode(), bsss);
/*  1091 */                   flag1 = "1";
/*  1092 */                 } else if ((this.readBackStatus.equals("2")) && (flag1 == "1")) {
/*  1093 */                   String strs = "RELAY-SET-" + user_num.get(usereCode) + "," + split[0] + "," + split[2];
/*  1094 */                   byte[] bsss = strs.getBytes();
/*  1095 */                   System.err.println(new String(bss));
/*  1096 */                   this.packetProcessHelper.processSendDDatas(obj.getBoDevice().getDeviceCode(), bsss);
/*  1097 */                   flag1 = "2";
/*  1098 */                 } else if (((this.readBackStatus.equals("wait")) && (index == 1)) || (
/*  1099 */                   (this.readBackStatus.equals("wait")) && (index == 2))) {
/*  1100 */                   String strs = "RELAY-SET-" + user_num.get(usereCode) + "," + split[0] + "," + split[2];
/*  1101 */                   byte[] bsss = strs.getBytes();
/*  1102 */                   System.err.println(new String(bss));
/*  1103 */                   this.packetProcessHelper.processSendDDatas(obj.getBoDevice().getDeviceCode(), bsss);
/*       */                 }
/*       */ 
/*  1106 */                 Thread.sleep(30L);
/*       */ 
/*  1108 */                 index++;
/*       */               }
/*  1110 */               timer.cancel();
/*       */               try
/*       */               {
/*  1113 */                 this.boResendVerificationService.delete(this.save);
/*       */               }
/*       */               catch (Exception e) {
/*  1116 */                 logger.info(e.getMessage());
/*  1117 */                 System.err.println(e.getMessage());
/*       */               }
/*       */             }
/*       */             catch (InterruptedException localInterruptedException4)
/*       */             {
/*       */             }
/*       */           }
/*       */         }
/*       */ 
/*       */       }
/*       */ 
/*       */     }
/*       */     catch (InterruptedException localInterruptedException5)
/*       */     {
/*       */     }
/*       */ 
/*  1133 */     return isR;
/*       */   }
/*       */ 
/*       */   @Action(value="authorize", results={@org.apache.struts2.convention.annotation.Result(type="json", params={"root", "requestJson"})})
/*       */   public String authorize()
/*       */   {
/*  1144 */     this.requestJson = new RequestJson();
/*  1145 */     Map map = new HashMap();
/*  1146 */     HttpServletRequest request = ServletActionContext.getRequest();
/*  1147 */     String ip = request.getRemoteAddr();
/*  1148 */     String header = request.getHeader("timestamp");
/*  1149 */     String header2 = request.getHeader("nonce");
/*  1150 */     String header3 = request.getHeader("sign");
/*  1151 */     String header4 = request.getHeader("access_token");
/*  1152 */     String userCode = request.getHeader("userCode");
                logger.info("-------authorize-------");
                //遍历出request的所有参数
                Enumeration pNames=request.getParameterNames();
                while(pNames.hasMoreElements()){
                    String name=(String)pNames.nextElement();
                    String value=request.getParameter(name);
                    logger.info(name + " == " + value);
                }
                logger.info("signature>>"+this.signature);
//                logger.info("accountOperationType>>"+this.accountOperationType); //this == BoUsers对象     ;  request.getHeader("accountOperationType")=null;
/*  1153 */     if (userCode.contains(",")) {
/*  1154 */       String[] userCode2 = userCode.split(",");
/*  1155 */       BoUsers boUsers = this.boUserServicess.findByUserUserCode(userCode2[0].trim().toString());
/*  1156 */       BoUsers phone = this.boUserServicess.findByUserPhone(userCode2[1].trim().toString());
/*  1157 */       Boolean ral = isRal(header, header2, header3, header4, userCode, "授权用户");
/*  1158 */       if (ral.booleanValue()) {
/*  1159 */         System.err.println("验证通过");
/*  1160 */         if ((phone == null) || (boUsers == null)) {
/*  1161 */           this.requestJson.setData(map);
/*  1162 */           this.requestJson.setMessage("Invalid_User");
/*  1163 */           this.requestJson.setSuccess(true);
/*       */         } else {
/*  1165 */           List list = this.boUserServicess.getByAuthorizeUserCode(boUsers.getUserCode());
/*  1166 */           if (list.size() > 8) {
/*  1167 */             this.requestJson.setData(map);
/*  1168 */             System.err.println("当前版本暂时只能授权用户7个");
/*  1169 */             this.requestJson.setMessage("当前版本暂时只能授权用户7个");
/*  1170 */             this.requestJson.setSuccess(false);
/*       */           } else {
/*  1172 */             BoUsers users = this.boUserServicess.findByUserPhone(this.userPhone);
/*  1173 */             if (users == null) {
/*  1174 */               this.requestJson.setData(map);
/*  1175 */               this.requestJson.setMessage("该账户尚未注册");
/*  1176 */               this.requestJson.setSuccess(false);
/*       */             }
/*  1178 */             else if (!users.getAuthorizationUserCode().equals("")) {
/*  1179 */               map.put("result", "账户已被其他账户授权");
/*  1180 */               this.requestJson.setData(map);
/*  1181 */               this.requestJson.setSuccess(true);
/*       */             } else {
/*  1183 */               String generateTokeCode = TokeUtil.generateTokeCode();
/*  1184 */               String generateTokeCodes = TokeUtil.generateTokeCodes();
/*  1185 */               users.setAuthorizationUserCode(boUsers.getUserCode());
/*  1186 */               users.setLogoAccountType("S");
/*       */               String accountOperation;
///*       */               String accountOperation;
/*  1188 */               if ((this.accountOperationType == null) || (this.accountOperationType.equals("")))
/*  1189 */                 accountOperation = "1";
/*       */               else {
/*  1191 */                 accountOperation = this.accountOperationType;
							//在这里把楼层、房间和设备信息 填入 city（不被使用的字段）中   +登录的时候把  被授权者的 userCode也加在后面(userInfoMap.put("userCode", users.getAuthorizationUserCode() + "," + users.getUserPhone()+"," + users.getUserCode()) )
/*       */               }
/*  1193 */               users.setAccountOperationType(accountOperation);
/*  1194 */               users.setAccessToken(generateTokeCode);
/*  1195 */               users.setRefreshToken(generateTokeCodes);
/*  1196 */               users.setAccessTokenTime("940923880");
/*  1197 */               users.setRefreshTokenTime("940923880");
/*  1198 */               BoUsers update = (BoUsers)this.boUserServicess.update(users);
/*  1199 */               if (update == null) {
/*  1200 */                 this.requestJson.setData(map);
/*  1201 */                 this.requestJson.setMessage("授权失败");
/*  1202 */                 this.requestJson.setSuccess(false);
/*       */               } else {
/*  1204 */                 PushService pushService = new PushService();
/*  1205 */                 if (users.getVersionType().equals("1")) {
/*  1206 */                   System.err.println("易联智家KEY");
/*  1207 */                   pushService.setAppId("C2rT1RdCoB7BaZ83l2AJM7");
/*  1208 */                   pushService.setAppkey("ApgJ2YFdI573k57hLt9Mz9");
/*  1209 */                   pushService.setMaster("JBWO7E3WyW75zgd2Bdr4NA");
/*  1210 */                 } else if (users.getVersionType().equals("2")) {
/*  1211 */                   System.err.println("爱博瑞KEY");
/*  1212 */                   pushService.setAppId("qy0HMfNc8o6fiLdtUGRfo1");
/*  1213 */                   pushService.setAppkey("cu1LHpYRwnAwDtTjq8XaQ7");
/*  1214 */                   pushService.setMaster("UpAQnXf7S47Snh00l5P5E8");
/*  1215 */                 } else if (!users.getVersionType().equals("3"))
/*       */                 {
/*  1217 */                   if (users.getVersionType().equals("4")) {
/*  1218 */                     System.err.println("思创智能KEY");
/*  1219 */                     pushService.setAppId("m5H4RPxliZAVXBlG1jka32");
/*  1220 */                     pushService.setAppkey("xizBIaTLNY5g7HxCi6kP05");
/*  1221 */                     pushService.setMaster("TRuul8ZWoO8rfFexEbvN09");
/*  1222 */                   } else if (users.getVersionType().equals("5")) {
/*  1223 */                     System.err.println("峰庭智能KEY");
/*  1224 */                     pushService.setAppId("8qv7s4OhGEAjDjEaC5bBw4");
/*  1225 */                     pushService.setAppkey("DH3NlHfEOG6YtySTspB4LA");
/*  1226 */                     pushService.setMaster("utgpb3GAGN9KsVKVun7W32");
/*  1227 */                   } else if (users.getVersionType().equals("6")) {
/*  1228 */                     System.err.println("麦宝KEY");
/*  1229 */                     pushService.setAppId("OAzON9h86e8Y2XREgjU0R9");
/*  1230 */                     pushService.setAppkey("SAE1hTMJcW8ZkRfZPRDja6");
/*  1231 */                     pushService.setMaster("y5zkumXwYPACBLJ59BZnr6");
/*  1232 */                   } else if (users.getVersionType().equals("7")) {
/*  1233 */                     System.err.println("乐沃KEY");
/*  1234 */                     pushService.setAppId("pHU6NuXh789r2ZXEYzj7z1");
/*  1235 */                     pushService.setAppkey("COGyB0sKyQ8nGHmnuNUK41");
/*  1236 */                     pushService.setMaster("qSTfJpPIQc7OkaswRI1YH7");
/*       */                   }
/*       */                 }
/*  1238 */                 String title = "";
/*  1239 */                 String CID = update.getCid();
/*       */ 
/*  1241 */                 if ((CID == null) || (CID.equals(""))) {
/*  1242 */                   System.err.println("CID为空推送不到信息");
/*       */                 } else {
/*  1244 */                   StringBuffer text = new StringBuffer();
/*       */ 
/*  1246 */                   System.err.println("*****<< " + update.getVersionType());
/*  1247 */                   if (users.getVersionType().equals("1")) {
/*  1248 */                     System.err.println("易联智家推送内容");
/*  1249 */                     title = "易家智联";
/*  1250 */                     text.append("您的账户已被人授权\n");
/*  1251 */                     text.append("打开软件将会进入登录界面请重新登录\n");
/*  1252 */                   } else if (users.getVersionType().equals("2")) {
/*  1253 */                     System.err.println("爱博瑞推送内容");
/*  1254 */                     title = "爱波瑞科技";
/*  1255 */                     text.append("您的账户已被人授权\n");
/*  1256 */                     text.append("打开软件将会进入登录界面请重新登录\n");
/*  1257 */                   } else if (!users.getVersionType().equals("3"))
/*       */                   {
/*  1259 */                     if (users.getVersionType().equals("4")) {
/*  1260 */                       System.err.println("思创智能推送内容");
/*  1261 */                       title = "思创智能";
/*  1262 */                       text.append("您的账户已被人授权\n");
/*  1263 */                       text.append("打开软件将会进入登录界面请重新登录\n");
/*  1264 */                     } else if (users.getVersionType().equals("5")) {
/*  1265 */                       System.err.println("峰庭智能推送内容");
/*  1266 */                       title = "峰庭智能";
/*  1267 */                       text.append("您的账户已被人授权\n");
/*  1268 */                       text.append("打开软件将会进入登录界面请重新登录\n");
/*  1269 */                     } else if (users.getVersionType().equals("6")) {
/*  1270 */                       System.err.println("麦宝推送内容");
/*  1271 */                       title = "麦宝";
/*  1272 */                       text.append("您的账户已被人授权\n");
/*  1273 */                       text.append("打开软件将会进入登录界面请重新登录\n");
/*  1274 */                     } else if (users.getVersionType().equals("7")) {
/*  1275 */                       System.err.println("乐沃推送内容");
/*  1276 */                       title = "乐沃";
/*  1277 */                       text.append("您的账户已被人授权\n");
/*  1278 */                       text.append("打开软件将会进入登录界面请重新登录\n");
/*       */                     }
/*       */                   }
/*  1281 */                   Integer type = users.getPhoneType();
/*       */ 
/*  1283 */                   if ((type == null) || (type.intValue() == 0)) {
/*  1284 */                     pushService.pushToSingle(CID, title, text.toString(), text.toString());//推送？
/*       */                   }
/*       */                   else {
/*  1287 */                     pushService.apnPush(CID, title, text.toString(), text.toString());
/*       */                   }
/*       */                 }
/*       */ 
/*  1291 */                 map.put("result", "授权成功");
/*  1292 */                 this.requestJson.setData(map);
/*  1293 */                 this.requestJson.setSuccess(true);
/*       */               }
/*       */             }
/*       */           }
/*       */ 
/*       */         }
/*       */ 
/*       */       }
/*       */       else
/*       */       {
/*  1303 */         System.err.println("验证不通过");
/*       */ 
/*  1305 */         this.requestJson.setData(map);
/*  1306 */         this.requestJson.setMessage("验证不通过");
/*  1307 */         this.requestJson.setSuccess(false);
/*       */       }
/*       */     } else {
/*  1310 */       Boolean ral = isRal(header, header2, header3, header4, userCode, "授权用户");
/*  1311 */       if (ral.booleanValue()) {
/*  1312 */         System.err.println("验证通过");
/*  1313 */         BoUsers boUsers = this.boUserServicess.findByUserUserCode(userCode);
/*  1314 */         if (boUsers == null) {
/*  1315 */           this.requestJson.setData(map);
/*  1316 */           this.requestJson.setMessage("Invalid_User");
/*  1317 */           this.requestJson.setSuccess(true);
/*       */         } else {
/*  1319 */           List list = this.boUserServicess.getByAuthorizeUserCode(boUsers.getUserCode());
/*  1320 */           if (list.size() > 8) {
/*  1321 */             this.requestJson.setData(map);
/*  1322 */             System.err.println("当前版本暂时只能授权用户7个");
/*  1323 */             this.requestJson.setMessage("当前版本暂时只能授权用户7个");
/*  1324 */             this.requestJson.setSuccess(false);
/*       */           } else {
/*  1326 */             BoUsers users = this.boUserServicess.findByUserPhone(this.userPhone);
/*  1327 */             if (users == null) {
/*  1328 */               this.requestJson.setData(map);
/*  1329 */               this.requestJson.setMessage("该账号尚未注册");
/*  1330 */               this.requestJson.setSuccess(true);
/*       */             }
/*  1332 */             else if (!users.getAuthorizationUserCode().equals("")) {
/*  1333 */               map.put("result", "账户已被其他账户授权");
/*  1334 */               this.requestJson.setData(map);
/*  1335 */               this.requestJson.setSuccess(true);
/*       */             } else {
/*  1337 */               String generateTokeCode = TokeUtil.generateTokeCode();
/*  1338 */               String generateTokeCodes = TokeUtil.generateTokeCodes();
/*  1339 */               users.setAuthorizationUserCode(boUsers.getUserCode());
/*  1340 */               users.setLogoAccountType("S");
/*       */               String accountOperation;
///*       */               String accountOperation;
/*  1342 */               if ((this.accountOperationType == null) || (this.accountOperationType.equals("")))
/*  1343 */                 accountOperation = "1";
/*       */               else {
/*  1345 */                 accountOperation = this.accountOperationType;
/*       */               }
/*  1347 */               users.setAccountOperationType(accountOperation);
/*  1348 */               users.setAccessToken(generateTokeCode);
/*  1349 */               users.setRefreshToken(generateTokeCodes);
/*  1350 */               users.setAccessTokenTime("940923880");
/*  1351 */               users.setRefreshTokenTime("940923880");
/*  1352 */               BoUsers update = (BoUsers)this.boUserServicess.update(users);
/*  1353 */               if (update == null) {
/*  1354 */                 this.requestJson.setData(map);
/*  1355 */                 this.requestJson.setMessage("授权失败");
/*  1356 */                 this.requestJson.setSuccess(false);
/*       */               } else {
/*  1358 */                 PushService pushService = new PushService();
/*  1359 */                 if (boUsers.getVersionType().equals("1")) {
/*  1360 */                   System.err.println("易联智家KEY");
/*  1361 */                   pushService.setAppId("C2rT1RdCoB7BaZ83l2AJM7");
/*  1362 */                   pushService.setAppkey("ApgJ2YFdI573k57hLt9Mz9");
/*  1363 */                   pushService.setMaster("JBWO7E3WyW75zgd2Bdr4NA");
/*  1364 */                 } else if (boUsers.getVersionType().equals("2")) {
/*  1365 */                   System.err.println("爱博瑞KEY");
/*  1366 */                   pushService.setAppId("qy0HMfNc8o6fiLdtUGRfo1");
/*  1367 */                   pushService.setAppkey("cu1LHpYRwnAwDtTjq8XaQ7");
/*  1368 */                   pushService.setMaster("UpAQnXf7S47Snh00l5P5E8");
/*  1369 */                 } else if (!boUsers.getVersionType().equals("3"))
/*       */                 {
/*  1371 */                   if (boUsers.getVersionType().equals("4")) {
/*  1372 */                     System.err.println("思创智能KEY");
/*  1373 */                     pushService.setAppId("m5H4RPxliZAVXBlG1jka32");
/*  1374 */                     pushService.setAppkey("xizBIaTLNY5g7HxCi6kP05");
/*  1375 */                     pushService.setMaster("TRuul8ZWoO8rfFexEbvN09");
/*  1376 */                   } else if (boUsers.getVersionType().equals("5")) {
/*  1377 */                     System.err.println("峰庭智能KEY");
/*  1378 */                     pushService.setAppId("8qv7s4OhGEAjDjEaC5bBw4");
/*  1379 */                     pushService.setAppkey("DH3NlHfEOG6YtySTspB4LA");
/*  1380 */                     pushService.setMaster("utgpb3GAGN9KsVKVun7W32");
/*  1381 */                   } else if (boUsers.getVersionType().equals("6")) {
/*  1382 */                     System.err.println("麦宝KEY");
/*  1383 */                     pushService.setAppId("OAzON9h86e8Y2XREgjU0R9");
/*  1384 */                     pushService.setAppkey("SAE1hTMJcW8ZkRfZPRDja6");
/*  1385 */                     pushService.setMaster("y5zkumXwYPACBLJ59BZnr6");
/*  1386 */                   } else if (boUsers.getVersionType().equals("7")) {
/*  1387 */                     System.err.println("乐沃KEY");
/*  1388 */                     pushService.setAppId("pHU6NuXh789r2ZXEYzj7z1");
/*  1389 */                     pushService.setAppkey("COGyB0sKyQ8nGHmnuNUK41");
/*  1390 */                     pushService.setMaster("qSTfJpPIQc7OkaswRI1YH7");
/*       */                   }
/*       */                 }
/*  1392 */                 String title = "";
/*  1393 */                 String CID = update.getCid();
/*       */ 
/*  1395 */                 if ((CID == null) || (CID.equals(""))) {
/*  1396 */                   System.err.println("CID为空推送不到信息");
/*       */                 } else {
/*  1398 */                   StringBuffer text = new StringBuffer();
/*       */ 
/*  1400 */                   System.err.println("*****<< " + update.getVersionType());
/*  1401 */                   if (boUsers.getVersionType().equals("1")) {
/*  1402 */                     System.err.println("易联智家推送内容");
/*  1403 */                     title = "易家智联";
/*  1404 */                     text.append("您的账户已被人授权\n");
/*  1405 */                     text.append("打开软件将会进入登录界面请重新登录\n");
/*  1406 */                   } else if (boUsers.getVersionType().equals("2")) {
/*  1407 */                     System.err.println("爱博瑞推送内容");
/*  1408 */                     title = "爱波瑞科技";
/*  1409 */                     text.append("您的账户已被人授权\n");
/*  1410 */                     text.append("打开软件将会进入登录界面请重新登录\n");
/*  1411 */                   } else if (!boUsers.getVersionType().equals("3"))
/*       */                   {
/*  1413 */                     if (boUsers.getVersionType().equals("4")) {
/*  1414 */                       System.err.println("思创智能推送内容");
/*  1415 */                       title = "思创智能";
/*  1416 */                       text.append("您的账户已被人授权\n");
/*  1417 */                       text.append("打开软件将会进入登录界面请重新登录\n");
/*  1418 */                     } else if (boUsers.getVersionType().equals("5")) {
/*  1419 */                       System.err.println("峰庭智能推送内容");
/*  1420 */                       title = "峰庭智能";
/*  1421 */                       text.append("您的账户已被人授权\n");
/*  1422 */                       text.append("打开软件将会进入登录界面请重新登录\n");
/*  1423 */                     } else if (boUsers.getVersionType().equals("6")) {
/*  1424 */                       System.err.println("麦宝推送内容");
/*  1425 */                       title = "麦宝";
/*  1426 */                       text.append("您的账户已被人授权\n");
/*  1427 */                       text.append("打开软件将会进入登录界面请重新登录\n");
/*  1428 */                     } else if (boUsers.getVersionType().equals("7")) {
/*  1429 */                       System.err.println("乐沃推送内容");
/*  1430 */                       title = "乐沃";
/*  1431 */                       text.append("您的账户已被人授权\n");
/*  1432 */                       text.append("打开软件将会进入登录界面请重新登录\n");
/*       */                     }
/*       */                   }
/*  1435 */                   Integer type = update.getPhoneType();
/*       */ 
/*  1437 */                   if ((type == null) || (type.intValue() == 0)) {
/*  1438 */                     pushService.pushToSingle(CID, title, text.toString(), text.toString());
/*       */                   }
/*       */                   else {
/*  1441 */                     pushService.apnPush(CID, title, text.toString(), text.toString());
/*       */                   }
/*       */                 }
/*       */ 
/*  1445 */                 map.put("result", "授权成功");
/*  1446 */                 this.requestJson.setData(map);
/*  1447 */                 this.requestJson.setSuccess(true);
/*       */               }
/*       */             }
/*       */ 
/*       */           }
/*       */ 
/*       */         }
/*       */ 
/*       */       }
/*       */       else
/*       */       {
/*  1458 */         System.err.println("验证不通过");
/*       */ 
/*  1460 */         this.requestJson.setData(map);
/*  1461 */         this.requestJson.setMessage("验证不通过");
/*  1462 */         this.requestJson.setSuccess(false);
/*       */       }
/*       */     }
/*  1465 */     return "success";
/*       */   }
/*       */ 
/*       */   @Action(value="setPwd", results={@org.apache.struts2.convention.annotation.Result(type="json", params={"root", "requestJson"})})
/*       */   public String setPwd() throws InterruptedException
/*       */   {
/*  1477 */     this.requestJson = new RequestJson();
/*  1478 */     Md5 md5 = new Md5();
/*  1479 */     Map map = new HashMap();
/*  1480 */     HttpServletRequest request = ServletActionContext.getRequest();
/*  1481 */     String header = request.getHeader("timestamp");
/*  1482 */     String header2 = request.getHeader("nonce");
/*  1483 */     String header3 = request.getHeader("sign");
/*  1484 */     String header4 = request.getHeader("access_token");
/*  1485 */     String userCode = request.getHeader("userCode");
				
/*  1486 */     Boolean ral = isRal(header, header2, header3, header4, userCode, "设置登录密码");
/*  1487 */     if (ral.booleanValue()) {
/*  1488 */       System.err.println("验证通过");
/*       */       try {
/*  1490 */         BoUsers phone = this.boUserServicess.findByUserPhone(this.userPhone);
/*  1491 */         if (phone == null) 
//						break label432;
						return "phone null";
/*  1492 */         System.err.println(this.userPhone);
/*  1493 */         System.err.println(this.oldUserPwd);
/*  1494 */         System.err.println(phone.getUserPwd());
/*  1495 */         System.err.println(md5.getMD5ofStr(this.oldUserPwd));
/*  1496 */         if (phone.getUserPwd().equals(md5.getMD5ofStr(this.oldUserPwd)))
/*       */         {
/*  1498 */           phone.setUserPwd(md5.getMD5ofStr(this.userPwd));
/*  1499 */           BoUsers update = (BoUsers)this.boUserServicess.update(phone);
/*  1500 */           if (update != null) {
/*  1501 */             this.requestJson.setData(map);
/*  1502 */             this.requestJson.setMessage("设置密码成功");
/*  1503 */             this.requestJson.setSuccess(true);
/*       */           } else {
/*  1505 */             this.requestJson.setData(map);
/*  1506 */             this.requestJson.setMessage("设置密码失败");
/*  1507 */             this.requestJson.setSuccess(false);
/*       */           }
/*       */         } else {
/*  1510 */           this.requestJson.setData(map);
/*  1511 */           System.err.println(this.oldUserPwd);
/*  1512 */           this.requestJson.setMessage("旧密码不正确");
/*  1513 */           this.requestJson.setSuccess(false);
/*       */         }
/*       */       }
/*       */       catch (Exception e)
/*       */       {
/*  1518 */         logger.info("error" + e.getMessage());
/*  1519 */         this.requestJson.setData(map);
/*  1520 */         this.requestJson.setMessage("服务器发生异常");
/*  1521 */         this.requestJson.setSuccess(false);
/*       */       }
/*       */     }
/*       */     else {
/*  1525 */       System.err.println("验证不通过");
/*  1526 */       this.requestJson.setData(map);
/*  1527 */       this.requestJson.setMessage("验证不通过");
/*  1528 */       this.requestJson.setSuccess(false);
/*       */     }
/*       */ 
///*  1531 */     label432: return "success";
			return "success";
/*       */   }
/*       */ 
/*       */   @Action(value="resetPwd", results={@org.apache.struts2.convention.annotation.Result(type="json", params={"root", "requestJson"})})
/*       */   public String resetPwd()
/*       */     throws InterruptedException
/*       */   {
/*  1543 */     this.requestJson = new RequestJson();
/*  1544 */     HttpServletRequest request = ServletActionContext.getRequest();
/*  1545 */     Map resetPwdMap = new HashMap();
/*  1546 */     String serviceSign = "";
/*  1547 */     Md5 md5 = new Md5();
/*  1548 */     String header = request.getHeader("timestamp");
/*  1549 */     String header2 = request.getHeader("nonce");
/*  1550 */     String appSign = request.getHeader("sign");
/*       */ 
/*  1552 */     serviceSign = serviceSign + "userPhone=";
/*  1553 */     serviceSign = serviceSign + this.userPhone;
/*  1554 */     serviceSign = serviceSign + "&userPwd=";
/*  1555 */     serviceSign = serviceSign + this.userPwd;
/*  1556 */     serviceSign = serviceSign + "&verifyCode=";
/*  1557 */     serviceSign = serviceSign + this.verifyCode;
/*  1558 */     serviceSign = serviceSign + "12345";
/*  1559 */     String sign = md5.getMD5ofStr(serviceSign).toLowerCase();
/*  1560 */     if (appSign.equals(sign))
/*       */     {
/*  1562 */       String[] array = (String[])StaticUtils.VERIFICATIONCODE.get(this.userPhone + "_A");
/*  1563 */       String verificationCode = "";
/*       */ 
/*  1565 */       if (array != null) {
/*  1566 */         if (array[0].equals(""))
/*  1567 */           verificationCode = "***^&*&*&^&*&^$$^&*()&";
/*       */         else {
/*  1569 */           verificationCode = array[0];
/*       */         }
/*  1571 */         System.err.println("----" + verificationCode);
/*  1572 */         System.err.println("====" + this.verifyCode);
/*  1573 */         if (verificationCode.equals(this.verifyCode)) {
/*  1574 */           BoUsers phone = this.boUserServicess.findByUserPhone(this.userPhone);
/*  1575 */           if (phone != null) {
/*  1576 */             phone.setUserPwd(md5.getMD5ofStr(this.userPwd));
/*  1577 */             BoUsers update = (BoUsers)this.boUserServicess.update(phone);
/*  1578 */             if (update != null) {
/*  1579 */               this.requestJson.setData(resetPwdMap);
/*  1580 */               this.requestJson.setMessage("重置密码成功");
/*  1581 */               this.requestJson.setSuccess(true);
/*       */             } else {
/*  1583 */               this.requestJson.setData(resetPwdMap);
/*  1584 */               this.requestJson.setMessage("重置密码失败");
/*  1585 */               this.requestJson.setSuccess(false);
/*       */             }
/*       */           } else {
/*  1588 */             this.requestJson.setData(resetPwdMap);
/*  1589 */             this.requestJson.setMessage("用户尚未注册");
/*  1590 */             this.requestJson.setSuccess(false);
/*       */           }
/*       */         } else {
/*  1593 */           this.requestJson.setData(resetPwdMap);
/*  1594 */           this.requestJson.setMessage("验证码错误");
/*  1595 */           this.requestJson.setSuccess(false);
/*       */         }
/*       */       } else {
/*  1598 */         this.requestJson.setData(resetPwdMap);
/*  1599 */         this.requestJson.setMessage("验证码错误");
/*  1600 */         this.requestJson.setSuccess(false);
/*       */       }
/*       */     } else {
/*  1603 */       System.err.println("验证不通过");
/*  1604 */       this.requestJson.setData(resetPwdMap);
/*  1605 */       this.requestJson.setMessage("验证不通过");
/*  1606 */       this.requestJson.setSuccess(false);
/*       */     }
/*  1608 */     return "success";
/*       */   }
/*       */ 
/*       */   @Action(value="gainSecurityTotalSwitch", results={@org.apache.struts2.convention.annotation.Result(type="json", params={"root", "requestJson"})})
/*       */   public String gainSecurityTotalSwitch()
/*       */   {
/*  1619 */     this.requestJson = new RequestJson();
/*  1620 */     Map map = new HashMap();
/*  1621 */     HttpServletRequest request = ServletActionContext.getRequest();
/*  1622 */     String ip = request.getRemoteAddr();
/*  1623 */     String header = request.getHeader("timestamp");
/*  1624 */     String header2 = request.getHeader("nonce");
/*  1625 */     String header3 = request.getHeader("sign");
/*  1626 */     String header4 = request.getHeader("access_token");
/*  1627 */     String userCode = request.getHeader("userCode");
/*  1628 */     if (userCode.contains(",")) {
/*  1629 */       String[] userCode2 = userCode.split(",");
/*  1630 */       BoUsers boUsers = this.boUserServicess.findByUserUserCode(userCode2[0].trim().toString());
/*  1631 */       BoUsers phone = this.boUserServicess.findByUserPhone(userCode2[1].trim().toString());
/*  1632 */       Boolean ral = isRal(header, header2, header3, header4, userCode, "获取首界面安防总开关");
/*  1633 */       if (ral.booleanValue()) {
/*  1634 */         System.err.println("验证通过");
/*  1635 */         Long accessToken = Long.valueOf(header);
/*       */ 
/*  1637 */         if ((phone == null) || (boUsers == null)) {
/*  1638 */           this.requestJson.setData(map);
/*  1639 */           this.requestJson.setMessage("Invalid_User");
/*  1640 */           this.requestJson.setSuccess(true);
/*       */         }
/*  1642 */         else if (header4.equals(phone.getAccessToken())) {
/*  1643 */           if (accessToken.longValue() < Long.valueOf(phone.getAccessTokenTime()).longValue()) {
/*  1644 */             map.put("securityTotalSwitch", boUsers.getSecurityTotalSwitch());
/*  1645 */             this.requestJson.setData(map);
/*  1646 */             this.requestJson.setMessage("");
/*  1647 */             this.requestJson.setSuccess(true);
/*       */           } else {
/*  1649 */             System.err.println("AToken时间戳超时了");
/*  1650 */             this.requestJson.setData(map);
/*  1651 */             this.requestJson.setMessage("超时了");
/*  1652 */             this.requestJson.setSuccess(false);
/*       */           }
/*       */         } else {
/*  1655 */           System.err.println("AToken超时了");
/*  1656 */           this.requestJson.setData(map);
/*  1657 */           this.requestJson.setMessage("超时了");
/*  1658 */           this.requestJson.setSuccess(false);
/*       */         }
/*       */       }
/*       */       else {
/*  1662 */         System.err.println("验证不通过");
/*  1663 */         this.requestJson.setData(map);
/*  1664 */         this.requestJson.setMessage("验证不通过");
/*  1665 */         this.requestJson.setSuccess(false);
/*       */       }
/*       */     } else {
/*  1668 */       Boolean ral = isRal(header, header2, header3, header4, userCode, "获取首界面安防总开关");
/*  1669 */       if (ral.booleanValue()) {
/*  1670 */         System.err.println("验证通过");
/*  1671 */         Long accessToken = Long.valueOf(header);
/*  1672 */         BoUsers boUsers = this.boUserServicess.findByUserUserCode(userCode);
/*  1673 */         if (boUsers == null) {
/*  1674 */           this.requestJson.setData(map);
/*  1675 */           this.requestJson.setMessage("Invalid_User");
/*  1676 */           this.requestJson.setSuccess(true);
/*       */         }
/*  1678 */         else if (accessToken.longValue() < Long.valueOf(boUsers.getAccessTokenTime()).longValue()) {
/*  1679 */           map.put("securityTotalSwitch", boUsers.getSecurityTotalSwitch());
/*  1680 */           this.requestJson.setData(map);
/*  1681 */           this.requestJson.setMessage("");
/*  1682 */           this.requestJson.setSuccess(true);
/*       */         } else {
/*  1684 */           this.requestJson.setData(map);
/*  1685 */           this.requestJson.setMessage("超时了");
/*  1686 */           this.requestJson.setSuccess(false);
/*       */         }
/*       */       }
/*       */       else
/*       */       {
/*  1691 */         System.err.println("验证不通过");
/*  1692 */         this.requestJson.setData(map);
/*  1693 */         this.requestJson.setMessage("验证不通过");
/*  1694 */         this.requestJson.setSuccess(false);
/*       */       }
/*       */     }
/*  1697 */     return "success";
/*       */   }
/*       */ 
/*       */   @Action(value="securityTotalSwitch", results={@org.apache.struts2.convention.annotation.Result(type="json", params={"root", "requestJson"})})
/*       */   public String securityTotalSwitch()
/*       */   {
/*  1705 */     this.requestJson = new RequestJson();
/*  1706 */     Map map = new HashMap();
/*  1707 */     HttpServletRequest request = ServletActionContext.getRequest();
/*  1708 */     String ip = request.getRemoteAddr();
/*  1709 */     String header = request.getHeader("timestamp");
/*  1710 */     String header2 = request.getHeader("nonce");
/*  1711 */     String header3 = request.getHeader("sign");
/*  1712 */     String header4 = request.getHeader("access_token");
/*  1713 */     String userCode = request.getHeader("userCode");
/*       */     List<BoSensor> security;
/*  1714 */     if (userCode.contains(",")) {
/*  1715 */       String[] userCode2 = userCode.split(",");
/*  1716 */       BoUsers boUsers = this.boUserServicess.findByUserUserCode(userCode2[0].trim().toString());
/*  1717 */       BoUsers phone = this.boUserServicess.findByUserPhone(userCode2[1].trim().toString());
/*  1718 */       Boolean ral = isRal(header, header2, header3, header4, userCode, "设置首界面安防总开关");
/*  1719 */       if (ral.booleanValue()) {
/*  1720 */         System.err.println("验证通过");
/*  1721 */         Long accessToken = Long.valueOf(header);
/*       */ 
/*  1723 */         if ((phone == null) || (boUsers == null)) {
/*  1724 */           this.requestJson.setData(map);
/*  1725 */           this.requestJson.setMessage("Invalid_User");
/*  1726 */           this.requestJson.setSuccess(true);
/*       */         }
/*  1728 */         else if (header4.equals(phone.getAccessToken())) {
/*  1729 */           if (accessToken.longValue() < Long.valueOf(phone.getAccessTokenTime()).longValue()) {
/*  1730 */             if (this.securityType.equals("0")) {
/*  1731 */               security = this.boSensorService.getSecurity(userCode2[0].trim().toString(), "1");
/*  1732 */               for (BoSensor boSensor : security) {
/*  1733 */                 boSensor.setSecurityType("0");
/*  1734 */                 this.boSensorService.update(boSensor);
/*       */               }
/*  1736 */               boUsers.setSecurityTotalSwitch(this.securityType);
/*  1737 */               BoUsers update = (BoUsers)this.boUserServicess.update(boUsers);
/*  1738 */               if (update != null) {
/*  1739 */                 this.requestJson.setData(map);
/*  1740 */                 this.requestJson.setMessage("修改成功");
/*  1741 */                 this.requestJson.setSuccess(true);
/*       */               } else {
/*  1743 */                 this.requestJson.setData(map);
/*  1744 */                 this.requestJson.setMessage("修改失败");
/*  1745 */                 this.requestJson.setSuccess(false);
/*       */               }
/*       */             } else {
/*  1748 */               security = this.boSensorService.getSecurity(userCode2[0].trim().toString(), "1");
/*  1749 */               for (BoSensor boSensor : security) {
/*  1750 */                 boSensor.setSecurityType("1");
/*  1751 */                 this.boSensorService.update(boSensor);
/*       */               }
/*  1753 */               boUsers.setSecurityTotalSwitch(this.securityType);
/*  1754 */               BoUsers update = (BoUsers)this.boUserServicess.update(boUsers);
/*  1755 */               if (update != null) {
/*  1756 */                 this.requestJson.setData(map);
/*  1757 */                 this.requestJson.setMessage("修改成功");
/*  1758 */                 this.requestJson.setSuccess(true);
/*       */               } else {
/*  1760 */                 this.requestJson.setData(map);
/*  1761 */                 this.requestJson.setMessage("修改失败");
/*  1762 */                 this.requestJson.setSuccess(false);
/*       */               }
/*       */             }
/*       */           } else {
/*  1766 */             System.err.println("AToken时间戳超时了");
/*  1767 */             this.requestJson.setData(map);
/*  1768 */             this.requestJson.setMessage("超时了");
/*  1769 */             this.requestJson.setSuccess(false);
/*       */           }
/*       */         } else {
/*  1772 */           System.err.println("AToken超时了");
/*  1773 */           this.requestJson.setData(map);
/*  1774 */           this.requestJson.setMessage("超时了");
/*  1775 */           this.requestJson.setSuccess(false);
/*       */         }
/*       */       }
/*       */       else {
/*  1779 */         System.err.println("验证不通过");
/*  1780 */         this.requestJson.setData(map);
/*  1781 */         this.requestJson.setMessage("验证不通过");
/*  1782 */         this.requestJson.setSuccess(false);
/*       */       }
/*       */     } else {
/*  1785 */       Boolean ral = isRal(header, header2, header3, header4, userCode, "设置首界面安防总开关");
/*  1786 */       if (ral.booleanValue()) {
/*  1787 */         System.err.println("验证通过");
/*  1788 */         Long accessToken = Long.valueOf(header);
/*  1789 */         BoUsers boUsers = this.boUserServicess.findByUserUserCode(userCode);
/*  1790 */         if (boUsers == null) {
/*  1791 */           this.requestJson.setData(map);
/*  1792 */           this.requestJson.setMessage("Invalid_User");
/*  1793 */           this.requestJson.setSuccess(true);
/*       */         }
/*  1795 */         else if (accessToken.longValue() < Long.valueOf(boUsers.getAccessTokenTime()).longValue()) {
/*  1796 */           if (this.securityType.equals("0")) {
/*  1797 */             security = this.boSensorService.getSecurity(userCode, "1");
/*  1798 */             for (BoSensor boSensor : security) {
/*  1799 */               boSensor.setSecurityType("0");
/*  1800 */               this.boSensorService.update(boSensor);
/*       */             }
/*  1802 */             boUsers.setSecurityTotalSwitch(this.securityType);
/*  1803 */             BoUsers update = (BoUsers)this.boUserServicess.update(boUsers);
/*  1804 */             if (update != null) {
/*  1805 */               this.requestJson.setData(map);
/*  1806 */               this.requestJson.setMessage("修改成功");
/*  1807 */               this.requestJson.setSuccess(true);
/*       */             } else {
/*  1809 */               this.requestJson.setData(map);
/*  1810 */               this.requestJson.setMessage("修改失败");
/*  1811 */               this.requestJson.setSuccess(false);
/*       */             }
/*       */           } else {
/*  1814 */             security = this.boSensorService.getSecurity(userCode, "1");
/*  1815 */             for (BoSensor boSensor : security) {
/*  1816 */               boSensor.setSecurityType("1");
/*  1817 */               this.boSensorService.update(boSensor);
/*       */             }
/*  1819 */             boUsers.setSecurityTotalSwitch(this.securityType);
/*  1820 */             BoUsers update = (BoUsers)this.boUserServicess.update(boUsers);
/*  1821 */             if (update != null) {
/*  1822 */               this.requestJson.setData(map);
/*  1823 */               this.requestJson.setMessage("修改成功");
/*  1824 */               this.requestJson.setSuccess(true);
/*       */             } else {
/*  1826 */               this.requestJson.setData(map);
/*  1827 */               this.requestJson.setMessage("修改失败");
/*  1828 */               this.requestJson.setSuccess(false);
/*       */             }
/*       */           }
/*       */         } else {
/*  1832 */           this.requestJson.setData(map);
/*  1833 */           this.requestJson.setMessage("超时了");
/*  1834 */           this.requestJson.setSuccess(false);
/*       */         }
/*       */       }
/*       */       else
/*       */       {
/*  1839 */         System.err.println("验证不通过");
/*  1840 */         this.requestJson.setData(map);
/*  1841 */         this.requestJson.setMessage("验证不通过");
/*  1842 */         this.requestJson.setSuccess(false);
/*       */       }
/*       */     }
/*  1845 */     return "success";
/*       */   }
/*       */ 
/*       */   @Action(value="subordinateAccountRemoveAuthorize", results={@org.apache.struts2.convention.annotation.Result(type="json", params={"root", "requestJson"})})
/*       */   public String subordinateAccountRemoveAuthorize()
/*       */   {
/*  1853 */     this.requestJson = new RequestJson();
/*  1854 */     Map map = new HashMap();
/*  1855 */     HttpServletRequest request = ServletActionContext.getRequest();
/*  1856 */     String ip = request.getRemoteAddr();
/*  1857 */     String header = request.getHeader("timestamp");
/*  1858 */     String header2 = request.getHeader("nonce");
/*  1859 */     String header3 = request.getHeader("sign");
/*  1860 */     String header4 = request.getHeader("access_token");
/*  1861 */     String userCode = request.getHeader("userCode");
/*  1862 */     if (userCode.contains(",")) {
/*  1863 */       String[] userCode2 = userCode.split(",");
/*  1864 */       BoUsers boUsers = this.boUserServicess.findByUserUserCode(userCode2[0].trim().toString());
/*  1865 */       BoUsers phone = this.boUserServicess.findByUserPhone(userCode2[1].trim().toString());
/*  1866 */       Boolean ral = isRal(header, header2, header3, header4, userCode, "次账户解除授权");
/*  1867 */       if (ral.booleanValue()) {
/*  1868 */         System.err.println("验证通过");
/*  1869 */         Long accessToken = Long.valueOf(header);
/*       */ 
/*  1871 */         if (((phone == null ? 1 : 0) | (boUsers == null ? 1 : 0)) != 0) {
/*  1872 */           this.requestJson.setData(map);
/*  1873 */           this.requestJson.setMessage("Invalid_User");
/*  1874 */           this.requestJson.setSuccess(true);
/*       */         }
/*  1876 */         else if (header4.equals(phone.getAccessToken())) {
/*  1877 */           if (accessToken.longValue() < Long.valueOf(phone.getAccessTokenTime()).longValue()) {
/*  1878 */             BoUsers users = this.boUserServicess.findByUserPhone(this.userPhone);
/*  1879 */             users.setAuthorizationUserCode("");
/*  1880 */             users.setLogoAccountType("M");
/*  1881 */             users.setAccountOperationType("1");
/*  1882 */             this.boUserServicess.update(boUsers);
/*  1883 */             BoUsers remove = (BoUsers)this.boUserServicess.update(users);
/*       */ 
/*  1885 */             if (remove != null) {
/*  1886 */               this.requestJson.setData(map);
/*  1887 */               this.requestJson.setMessage("解绑成功");
/*  1888 */               this.requestJson.setSuccess(true);
/*       */             } else {
/*  1890 */               this.requestJson.setData(map);
/*  1891 */               this.requestJson.setMessage("解绑失败,请重新解绑");
/*  1892 */               this.requestJson.setSuccess(false);
/*       */             }
/*       */           } else {
/*  1895 */             System.err.println("AToken时间戳超时了");
/*  1896 */             this.requestJson.setData(map);
/*  1897 */             this.requestJson.setMessage("超时了");
/*  1898 */             this.requestJson.setSuccess(false);
/*       */           }
/*       */         } else {
/*  1901 */           System.err.println("AToken超时了");
/*  1902 */           this.requestJson.setData(map);
/*  1903 */           this.requestJson.setMessage("超时了");
/*  1904 */           this.requestJson.setSuccess(false);
/*       */         }
/*       */       }
/*       */       else
/*       */       {
/*  1909 */         System.err.println("验证不通过");
/*  1910 */         this.requestJson.setData(map);
/*  1911 */         this.requestJson.setMessage("验证不通过");
/*  1912 */         this.requestJson.setSuccess(false);
/*       */       }
/*       */     } else {
/*  1915 */       Boolean ral = isRal(header, header2, header3, header4, userCode, "次账户解除授权");
/*  1916 */       if (ral.booleanValue()) {
/*  1917 */         System.err.println("验证通过");
/*  1918 */         Long accessToken = Long.valueOf(header);
/*  1919 */         BoUsers boUsers = this.boUserServicess.findByUserUserCode(userCode);
/*  1920 */         if (boUsers == null) {
/*  1921 */           this.requestJson.setData(map);
/*  1922 */           this.requestJson.setMessage("Invalid_User");
/*  1923 */           this.requestJson.setSuccess(true);
/*       */         }
/*  1925 */         else if (accessToken.longValue() < Long.valueOf(boUsers.getAccessTokenTime()).longValue()) {
/*  1926 */           BoUsers users = this.boUserServicess.findByUserPhone(this.userPhone);
/*  1927 */           users.setAuthorizationUserCode("");
/*  1928 */           users.setLogoAccountType("M");
/*  1929 */           users.setAccountOperationType("1");
/*  1930 */           this.boUserServicess.update(boUsers);
/*  1931 */           BoUsers remove = (BoUsers)this.boUserServicess.update(users);
/*       */ 
/*  1933 */           if (remove != null) {
/*  1934 */             this.requestJson.setData(map);
/*  1935 */             this.requestJson.setMessage("解绑成功");
/*  1936 */             this.requestJson.setSuccess(true);
/*       */           } else {
/*  1938 */             this.requestJson.setData(map);
/*  1939 */             this.requestJson.setMessage("解绑失败,请重新解绑");
/*  1940 */             this.requestJson.setSuccess(false);
/*       */           }
/*       */         } else {
/*  1943 */           this.requestJson.setData(map);
/*  1944 */           this.requestJson.setMessage("超时了");
/*  1945 */           this.requestJson.setSuccess(false);
/*       */         }
/*       */       }
/*       */       else
/*       */       {
/*  1950 */         System.err.println("验证不通过");
/*  1951 */         this.requestJson.setData(map);
/*  1952 */         this.requestJson.setMessage("验证不通过");
/*  1953 */         this.requestJson.setSuccess(false);
/*       */       }
/*       */     }
/*  1956 */     return "success";
/*       */   }
/*       */ 
/*       */   @Action(value="primaryAccountRemoveBelowAccount", results={@org.apache.struts2.convention.annotation.Result(type="json", params={"root", "requestJson"})})
/*       */   public String primaryAccountRemoveBelowAccount()
/*       */   {
/*  1965 */     this.requestJson = new RequestJson();
/*  1966 */     Map map = new HashMap();
/*  1967 */     HttpServletRequest request = ServletActionContext.getRequest();
/*  1968 */     String ip = request.getRemoteAddr();
/*  1969 */     String header = request.getHeader("timestamp");
/*  1970 */     String header2 = request.getHeader("nonce");
/*  1971 */     String header3 = request.getHeader("sign");
/*  1972 */     String header4 = request.getHeader("access_token");
/*  1973 */     String userCode = request.getHeader("userCode");
/*  1974 */     if (userCode.contains(",")) {
/*  1975 */       String[] userCode2 = userCode.split(",");
/*  1976 */       BoUsers boUsers = this.boUserServicess.findByUserUserCode(userCode2[0].trim().toString());
/*  1977 */       BoUsers phone = this.boUserServicess.findByUserPhone(userCode2[1].trim().toString());
/*  1978 */       Boolean ral = isRal(header, header2, header3, header4, userCode, "主账户解除其下次授权账户");
/*  1979 */       if (ral.booleanValue()) {
/*  1980 */         System.err.println("验证通过");
/*  1981 */         Long accessToken = Long.valueOf(header);
/*       */ 
/*  1983 */         if ((phone == null) || (boUsers == null)) {
/*  1984 */           this.requestJson.setData(map);
/*  1985 */           this.requestJson.setMessage("Invalid_User");
/*  1986 */           this.requestJson.setSuccess(true);
/*       */         }
/*  1988 */         else if (header4.equals(phone.getAccessToken())) {
/*  1989 */           if (accessToken.longValue() < Long.valueOf(phone.getAccessTokenTime()).longValue()) {
/*  1990 */             String generateTokeCode = TokeUtil.generateTokeCode();
/*  1991 */             String generateTokeCodes = TokeUtil.generateTokeCodes();
/*  1992 */             BoUsers users = this.boUserServicess.findByUserPhone(this.userPhone);
/*  1993 */             users.setAuthorizationUserCode("");
/*  1994 */             users.setLogoAccountType("M");
/*  1995 */             users.setAccountOperationType("1");
/*  1996 */             users.setAccessToken(generateTokeCode);
/*  1997 */             users.setRefreshToken(generateTokeCodes);
/*  1998 */             users.setAccessTokenTime("940923880");
/*  1999 */             users.setRefreshTokenTime("940923880");
/*  2000 */             this.boUserServicess.update(boUsers);
/*  2001 */             BoUsers remove = (BoUsers)this.boUserServicess.update(users);
/*  2002 */             if (remove != null) {
/*  2003 */               PushService pushService = new PushService();
/*  2004 */               if (users.getVersionType().equals("1")) {
/*  2005 */                 System.err.println("易联智家KEY");
/*  2006 */                 pushService.setAppId("C2rT1RdCoB7BaZ83l2AJM7");
/*  2007 */                 pushService.setAppkey("ApgJ2YFdI573k57hLt9Mz9");
/*  2008 */                 pushService.setMaster("JBWO7E3WyW75zgd2Bdr4NA");
/*  2009 */               } else if (users.getVersionType().equals("2")) {
/*  2010 */                 System.err.println("爱博瑞KEY");
/*  2011 */                 pushService.setAppId("qy0HMfNc8o6fiLdtUGRfo1");
/*  2012 */                 pushService.setAppkey("cu1LHpYRwnAwDtTjq8XaQ7");
/*  2013 */                 pushService.setMaster("UpAQnXf7S47Snh00l5P5E8");
/*  2014 */               } else if (!users.getVersionType().equals("3"))
/*       */               {
/*  2016 */                 if (users.getVersionType().equals("4")) {
/*  2017 */                   System.err.println("思创智能KEY");
/*  2018 */                   pushService.setAppId("m5H4RPxliZAVXBlG1jka32");
/*  2019 */                   pushService.setAppkey("xizBIaTLNY5g7HxCi6kP05");
/*  2020 */                   pushService.setMaster("TRuul8ZWoO8rfFexEbvN09");
/*  2021 */                 } else if (users.getVersionType().equals("5")) {
/*  2022 */                   System.err.println("峰庭智能KEY");
/*  2023 */                   pushService.setAppId("8qv7s4OhGEAjDjEaC5bBw4");
/*  2024 */                   pushService.setAppkey("DH3NlHfEOG6YtySTspB4LA");
/*  2025 */                   pushService.setMaster("utgpb3GAGN9KsVKVun7W32");
/*  2026 */                 } else if (users.getVersionType().equals("6")) {
/*  2027 */                   System.err.println("麦宝KEY");
/*  2028 */                   pushService.setAppId("OAzON9h86e8Y2XREgjU0R9");
/*  2029 */                   pushService.setAppkey("SAE1hTMJcW8ZkRfZPRDja6");
/*  2030 */                   pushService.setMaster("y5zkumXwYPACBLJ59BZnr6");
/*  2031 */                 } else if (users.getVersionType().equals("7")) {
/*  2032 */                   System.err.println("乐沃KEY");
/*  2033 */                   pushService.setAppId("pHU6NuXh789r2ZXEYzj7z1");
/*  2034 */                   pushService.setAppkey("COGyB0sKyQ8nGHmnuNUK41");
/*  2035 */                   pushService.setMaster("qSTfJpPIQc7OkaswRI1YH7");
/*       */                 }
/*       */               }
/*  2037 */               String title = "";
/*  2038 */               String CID = users.getCid();
/*       */ 
/*  2040 */               if ((CID == null) || (CID.equals(""))) {
/*  2041 */                 System.err.println("CID为空推送不到信息");
/*       */               } else {
/*  2043 */                 StringBuffer text = new StringBuffer();
/*  2044 */                 System.err.println("*****<< " + users.getVersionType());
/*  2045 */                 if (users.getVersionType().equals("1")) {
/*  2046 */                   System.err.println("易联智家推送内容");
/*  2047 */                   title = "易家智联";
/*  2048 */                   text.append("账户已被主账户取消了授权\n");
/*  2049 */                   text.append("打开软件将会进入登录界面请重新登录\n");
/*  2050 */                 } else if (users.getVersionType().equals("2")) {
/*  2051 */                   System.err.println("爱博瑞推送内容");
/*  2052 */                   title = "爱波瑞科技";
/*  2053 */                   text.append("账户已被主账户取消了授权\n");
/*  2054 */                   text.append("打开软件将会进入登录界面请重新登录\n");
/*  2055 */                 } else if (!users.getVersionType().equals("3"))
/*       */                 {
/*  2057 */                   if (users.getVersionType().equals("4")) {
/*  2058 */                     System.err.println("思创智能推送内容");
/*  2059 */                     title = "思创智能";
/*  2060 */                     text.append("账户已被主账户取消了授权\n");
/*  2061 */                     text.append("打开软件将会进入登录界面请重新登录\n");
/*  2062 */                   } else if (users.getVersionType().equals("5")) {
/*  2063 */                     System.err.println("峰庭智能推送内容");
/*  2064 */                     title = "峰庭智能";
/*  2065 */                     text.append("账户已被主账户取消了授权\n");
/*  2066 */                     text.append("打开软件将会进入登录界面请重新登录\n");
/*  2067 */                   } else if (users.getVersionType().equals("6")) {
/*  2068 */                     System.err.println("麦宝推送内容");
/*  2069 */                     title = "麦宝";
/*  2070 */                     text.append("账户已被主账户取消了授权\n");
/*  2071 */                     text.append("打开软件将会进入登录界面请重新登录\n");
/*       */                   }
/*       */                 }
/*  2073 */                 Integer type = users.getPhoneType();
/*  2074 */                 if ((type == null) || (type.intValue() == 0))
/*  2075 */                   pushService.pushToSingle(CID, title, text.toString(), text.toString());
/*       */                 else {
/*  2077 */                   pushService.apnPush(CID, title, text.toString(), text.toString());
/*       */                 }
/*       */               }
/*  2080 */               this.requestJson.setData(map);
/*  2081 */               this.requestJson.setMessage("解绑成功");
/*  2082 */               this.requestJson.setSuccess(true);
/*       */             } else {
/*  2084 */               this.requestJson.setData(map);
/*  2085 */               this.requestJson.setMessage("解绑失败,请重新解绑");
/*  2086 */               this.requestJson.setSuccess(false);
/*       */             }
/*       */           } else {
/*  2089 */             System.err.println("AToken时间戳超时了");
/*  2090 */             this.requestJson.setData(map);
/*  2091 */             this.requestJson.setMessage("超时了");
/*  2092 */             this.requestJson.setSuccess(false);
/*       */           }
/*       */         } else {
/*  2095 */           System.err.println("AToken超时了");
/*  2096 */           this.requestJson.setData(map);
/*  2097 */           this.requestJson.setMessage("超时了");
/*  2098 */           this.requestJson.setSuccess(false);
/*       */         }
/*       */       }
/*       */       else {
/*  2102 */         System.err.println("验证不通过");
/*  2103 */         this.requestJson.setData(map);
/*  2104 */         this.requestJson.setMessage("验证不通过");
/*  2105 */         this.requestJson.setSuccess(false);
/*       */       }
/*       */     } else {
/*  2108 */       Boolean ral = isRal(header, header2, header3, header4, userCode, "主账户解除其下次授权账户");
/*  2109 */       if (ral.booleanValue()) {
/*  2110 */         System.err.println("验证通过");
/*  2111 */         Long accessToken = Long.valueOf(header);
/*  2112 */         BoUsers boUsers = this.boUserServicess.findByUserUserCode(userCode);
/*  2113 */         if (boUsers == null) {
/*  2114 */           this.requestJson.setData(map);
/*  2115 */           this.requestJson.setMessage("Invalid_User");
/*  2116 */           this.requestJson.setSuccess(true);
/*       */         }
/*  2118 */         else if (accessToken.longValue() < Long.valueOf(boUsers.getAccessTokenTime()).longValue())
/*       */         {
/*  2120 */           BoUsers users = this.boUserServicess.findByUserPhone(this.userPhone);
/*  2121 */           users.setAuthorizationUserCode("");
/*  2122 */           users.setLogoAccountType("M");
/*  2123 */           users.setAccountOperationType("1");
/*  2124 */           boUsers.setAccessTokenTime("940923880");
/*  2125 */           boUsers.setRefreshTokenTime("940923880");
/*  2126 */           this.boUserServicess.update(boUsers);
/*  2127 */           BoUsers remove = (BoUsers)this.boUserServicess.update(users);
/*       */ 
/*  2129 */           if (remove != null) {
/*  2130 */             PushService pushService = new PushService();
/*  2131 */             if (users.getVersionType().equals("1")) {
/*  2132 */               System.err.println("易联智家KEY");
/*  2133 */               pushService.setAppId("C2rT1RdCoB7BaZ83l2AJM7");
/*  2134 */               pushService.setAppkey("ApgJ2YFdI573k57hLt9Mz9");
/*  2135 */               pushService.setMaster("JBWO7E3WyW75zgd2Bdr4NA");
/*  2136 */             } else if (users.getVersionType().equals("2")) {
/*  2137 */               System.err.println("爱博瑞KEY");
/*  2138 */               pushService.setAppId("qy0HMfNc8o6fiLdtUGRfo1");
/*  2139 */               pushService.setAppkey("cu1LHpYRwnAwDtTjq8XaQ7");
/*  2140 */               pushService.setMaster("UpAQnXf7S47Snh00l5P5E8");
/*  2141 */             } else if (!users.getVersionType().equals("3"))
/*       */             {
/*  2143 */               if (users.getVersionType().equals("4")) {
/*  2144 */                 System.err.println("思创智能KEY");
/*  2145 */                 pushService.setAppId("m5H4RPxliZAVXBlG1jka32");
/*  2146 */                 pushService.setAppkey("xizBIaTLNY5g7HxCi6kP05");
/*  2147 */                 pushService.setMaster("TRuul8ZWoO8rfFexEbvN09");
/*  2148 */               } else if (users.getVersionType().equals("5")) {
/*  2149 */                 System.err.println("峰庭智能KEY");
/*  2150 */                 pushService.setAppId("8qv7s4OhGEAjDjEaC5bBw4");
/*  2151 */                 pushService.setAppkey("DH3NlHfEOG6YtySTspB4LA");
/*  2152 */                 pushService.setMaster("utgpb3GAGN9KsVKVun7W32");
/*  2153 */               } else if (users.getVersionType().equals("6")) {
/*  2154 */                 System.err.println("麦宝KEY");
/*  2155 */                 pushService.setAppId("OAzON9h86e8Y2XREgjU0R9");
/*  2156 */                 pushService.setAppkey("SAE1hTMJcW8ZkRfZPRDja6");
/*  2157 */                 pushService.setMaster("y5zkumXwYPACBLJ59BZnr6");
/*       */               }
/*       */             }
/*  2159 */             String title = "";
/*  2160 */             String CID = users.getCid();
/*       */ 
/*  2162 */             if ((CID == null) || (CID.equals(""))) {
/*  2163 */               System.err.println("CID为空推送不到信息");//消息推送
/*       */             } else {
/*  2165 */               StringBuffer text = new StringBuffer();
/*  2166 */               System.err.println("*****<< " + users.getVersionType());
/*  2167 */               if (users.getVersionType().equals("1")) {
/*  2168 */                 System.err.println("易联智家推送内容");
/*  2169 */                 title = "易家智联";
/*  2170 */                 text.append("账户已被主账户取消了授权\n");
/*  2171 */                 text.append("打开软件将会进入登录界面请重新登录\n");
/*  2172 */               } else if (users.getVersionType().equals("2")) {
/*  2173 */                 System.err.println("爱博瑞推送内容");
/*  2174 */                 title = "爱波瑞科技";
/*  2175 */                 text.append("账户已被主账户取消了授权\n");
/*  2176 */                 text.append("打开软件将会进入登录界面请重新登录\n");
/*  2177 */               } else if (!users.getVersionType().equals("3"))
/*       */               {
/*  2179 */                 if (users.getVersionType().equals("4")) {
/*  2180 */                   System.err.println("思创智能推送内容");
/*  2181 */                   title = "思创智能";
/*  2182 */                   text.append("账户已被主账户取消了授权\n");
/*  2183 */                   text.append("打开软件将会进入登录界面请重新登录\n");
/*  2184 */                 } else if (users.getVersionType().equals("5")) {
/*  2185 */                   System.err.println("峰庭智能推送内容");
/*  2186 */                   title = "峰庭智能";
/*  2187 */                   text.append("账户已被主账户取消了授权\n");
/*  2188 */                   text.append("打开软件将会进入登录界面请重新登录\n");
/*  2189 */                 } else if (users.getVersionType().equals("6")) {
/*  2190 */                   System.err.println("麦宝推送内容");
/*  2191 */                   title = "麦宝";
/*  2192 */                   text.append("账户已被主账户取消了授权\n");
/*  2193 */                   text.append("打开软件将会进入登录界面请重新登录\n");
/*       */                 }
/*       */               }
/*  2196 */               Integer type = users.getPhoneType();
/*       */ 
/*  2198 */               if ((type == null) || (type.intValue() == 0))
/*  2199 */                 pushService.pushToSingle(CID, title, text.toString(), text.toString());
/*       */               else {
/*  2201 */                 pushService.apnPush(CID, title, text.toString(), text.toString());
/*       */               }
/*       */             }
/*  2204 */             this.requestJson.setData(map);
/*  2205 */             this.requestJson.setMessage("解绑成功");
/*  2206 */             this.requestJson.setSuccess(true);
/*       */           } else {
/*  2208 */             this.requestJson.setData(map);
/*  2209 */             this.requestJson.setMessage("解绑失败,请重新解绑");
/*  2210 */             this.requestJson.setSuccess(false);
/*       */           }
/*       */         } else {
/*  2213 */           this.requestJson.setData(map);
/*  2214 */           this.requestJson.setMessage("超时了");
/*  2215 */           this.requestJson.setSuccess(false);
/*       */         }
/*       */       }
/*       */       else
/*       */       {
/*  2220 */         System.err.println("验证不通过");
/*  2221 */         this.requestJson.setData(map);
/*  2222 */         this.requestJson.setMessage("验证不通过");
/*  2223 */         this.requestJson.setSuccess(false);
/*       */       }
/*       */     }
/*  2226 */     return "success";
/*       */   }
/*       */ 
/*       */   @Action(value="gainAuthorizeList", results={@org.apache.struts2.convention.annotation.Result(type="json", params={"root", "requestJson"})})
/*       */   public String gainAuthorizeList()          //授权有关
/*       */   {
/*  2234 */     this.requestJson = new RequestJson();
/*  2235 */     Map map = new HashMap();
/*  2236 */     HttpServletRequest request = ServletActionContext.getRequest();
/*  2237 */     String ip = request.getRemoteAddr();
/*  2238 */     String header = request.getHeader("timestamp");
/*  2239 */     String header2 = request.getHeader("nonce");
/*  2240 */     String header3 = request.getHeader("sign");
/*  2241 */     String header4 = request.getHeader("access_token");
/*  2242 */     String userCode = request.getHeader("userCode");
/*       */     List voList;
                logger.info("-----------gainAuthorizeList-------------");
/*  2243 */     if (userCode.contains(",")) {
/*  2244 */       String[] userCode2 = userCode.split(",");
/*  2245 */       BoUsers boUsers = this.boUserServicess.findByUserUserCode(userCode2[0].trim().toString());
/*  2246 */       BoUsers phone = this.boUserServicess.findByUserPhone(userCode2[1].trim().toString());
/*  2247 */       Boolean ral = isRal(header, header2, header3, header4, userCode, "获取授权列表");
/*  2248 */       if (ral.booleanValue()) {
/*  2249 */         System.err.println("验证通过");
/*  2250 */         Long accessToken = Long.valueOf(header);
/*       */ 
/*  2252 */         if ((phone == null) || (boUsers == null)) {
/*  2253 */           this.requestJson.setData(map);
/*  2254 */           this.requestJson.setMessage("Invalid_User");
/*  2255 */           this.requestJson.setSuccess(true);
/*       */         }
/*  2257 */         else if (header4.equals(phone.getAccessToken())) {
/*  2258 */           if (accessToken.longValue() < Long.valueOf(phone.getAccessTokenTime()).longValue()) {
/*  2259 */             List<BoUsers> list = this.boUserServicess.getByAuthorizeUserCode(userCode2[0].trim().toString());
/*  2260 */             if (list.size() <= 0)
/*       */             {
/*  2262 */               this.requestJson.setMessage("没有授权列表");
/*  2263 */               this.requestJson.setSuccess(false);
/*       */             }
/*       */             else {
/*  2266 */               voList = new ArrayList();
/*  2267 */               for (BoUsers boUsers2 : list) {
/*  2268 */                 Map maps = new HashMap();
/*  2269 */                 maps.put("userPhone", boUsers2.getUserPhone());
/*  2270 */                 voList.add(maps);
/*       */               }
/*  2272 */               this.requestJson.setData(voList);
/*  2273 */               this.requestJson.setSuccess(true);
/*       */             }
/*       */           } else {
/*  2276 */             System.err.println("AToken时间戳超时了");
/*  2277 */             this.requestJson.setData(map);
/*  2278 */             this.requestJson.setMessage("超时了");
/*  2279 */             this.requestJson.setSuccess(false);
/*       */           }
/*       */         } else {
/*  2282 */           System.err.println("AToken超时了");
/*  2283 */           this.requestJson.setData(map);
/*  2284 */           this.requestJson.setMessage("超时了");
/*  2285 */           this.requestJson.setSuccess(false);
/*       */         }
/*       */       }
/*       */       else {
/*  2289 */         System.err.println("验证不通过");
/*  2290 */         this.requestJson.setData(map);
/*  2291 */         this.requestJson.setMessage("验证不通过");
/*  2292 */         this.requestJson.setSuccess(false);
/*       */       }
/*       */     } else {
/*  2295 */       Boolean ral = isRal(header, header2, header3, header4, userCode, "获取授权列表");
/*  2296 */       if (ral.booleanValue()) {
/*  2297 */         System.err.println("验证通过");
/*  2298 */         Long accessToken = Long.valueOf(header);
/*  2299 */         BoUsers boUsers = this.boUserServicess.findByUserUserCode(userCode);
/*  2300 */         if (boUsers == null) {
/*  2301 */           this.requestJson.setData(map);
/*  2302 */           this.requestJson.setMessage("Invalid_User");
/*  2303 */           this.requestJson.setSuccess(true);
/*       */         }
/*  2305 */         else if (accessToken.longValue() < Long.valueOf(boUsers.getAccessTokenTime()).longValue()) {
/*  2306 */           List<BoUsers> list = this.boUserServicess.getByAuthorizeUserCode(userCode);
/*  2307 */           if (list.size() <= 0) {
/*  2308 */             this.requestJson.setMessage("没有授权列表");
/*  2309 */             this.requestJson.setSuccess(true);
/*       */           }
/*       */           else {
/*  2312 */             voList = new ArrayList();
/*  2313 */             for (BoUsers boUsers2 : list) {
/*  2314 */               Map maps = new HashMap();
/*  2315 */               maps.put("userPhone", boUsers2.getUserPhone());
/*  2316 */               voList.add(maps);
/*       */             }
/*  2318 */             this.requestJson.setData(voList);
/*  2319 */             this.requestJson.setSuccess(true);
/*       */           }
/*       */         } else {
/*  2322 */           this.requestJson.setData(map);
/*  2323 */           this.requestJson.setMessage("超时了");
/*  2324 */           this.requestJson.setSuccess(false);
/*       */         }
/*       */       }
/*       */       else
/*       */       {
/*  2329 */         System.err.println("验证不通过");
/*  2330 */         this.requestJson.setData(map);
/*  2331 */         this.requestJson.setMessage("验证不通过");
/*  2332 */         this.requestJson.setSuccess(false);
/*       */       }
/*       */ 
/*       */     }
/*       */ 
/*  2337 */     return "success";
/*       */   }
/*       */ 
/*       */   @Action(value="startAuthorize", results={@org.apache.struts2.convention.annotation.Result(type="json", params={"root", "requestJson"})})
/*       */   public String startAuthorize()
/*       */   {
/*  2346 */     this.requestJson = new RequestJson();
/*  2347 */     Map map = new HashMap();
/*  2348 */     HttpServletRequest request = ServletActionContext.getRequest();
/*  2349 */     String ip = request.getRemoteAddr();
/*  2350 */     String header = request.getHeader("timestamp");
/*  2351 */     String header2 = request.getHeader("nonce");
/*  2352 */     String header3 = request.getHeader("sign");
/*  2353 */     String header4 = request.getHeader("access_token");
/*  2354 */     String userCode = request.getHeader("userCode");
				logger.info("request>>"+request.getQueryString());//获取授权时请求中的实体内容数据
				logger.info("授权函数userCode>>"+userCode);
/*  2355 */     if (userCode.contains(",")) {
/*  2356 */       String[] userCode2 = userCode.split(",");
/*  2357 */       BoUsers boUsers = this.boUserServicess.findByUserUserCode(userCode2[0].trim().toString());
/*  2358 */       BoUsers phone = this.boUserServicess.findByUserPhone(userCode2[1].trim().toString());
/*  2359 */       Boolean ral = isRal(header, header2, header3, header4, userCode, "开始授权");
/*  2360 */       if (ral.booleanValue()) {
/*  2361 */         System.err.println("验证通过");
/*  2362 */         Long accessToken = Long.valueOf(header);
/*       */ 
/*  2364 */         if ((phone == null) || (boUsers == null)) {
/*  2365 */           this.requestJson.setData(map);
/*  2366 */           this.requestJson.setMessage("Invalid_User");
/*  2367 */           this.requestJson.setSuccess(true);
/*       */         } else {
/*  2369 */           String[] array = (String[])StaticUtils.AUTHORIZATIONSENDMSG.get(this.userPhone + "_A");
/*  2370 */           String verificationCode = "";
/*  2371 */           String verificationCodes = "";
/*       */ 
/*  2373 */           if (array != null) {
/*  2374 */             verificationCode = array[0];
/*       */           }
/*       */ 
/*  2377 */           if (verificationCode.equals(""))
/*  2378 */             verificationCodes = "***^&*&*&^&*&^$$^&*()&";
/*       */           else {
/*  2380 */             verificationCodes = verificationCode;
/*       */           }
/*  2382 */           System.err.println("----" + verificationCode);
/*  2383 */           System.err.println("====" + this.verifyCode);
/*  2384 */           if (verificationCodes.equals(this.verifyCode)) {
/*  2385 */             List list = this.boUserServicess.getByAuthorizeUserCode(boUsers.getUserCode());
/*  2386 */             if (list.size() > 8) {
/*  2387 */               this.requestJson.setData(map);
/*  2388 */               System.err.println("当前版本暂时只能授权用户7个");
/*  2389 */               this.requestJson.setMessage("当前版本暂时只能授权用户7个");
/*  2390 */               this.requestJson.setSuccess(false);
/*       */             } else {
/*  2392 */               BoUsers users = this.boUserServicess.findByUserPhone(this.userPhone);
/*  2393 */               if (!users.getAuthorizationUserCode().equals("")) {//AUTHORIZATION_USER_CODE不为空时 说明被其他账户授权
/*  2394 */                 map.put("result", "账户已被其他账户授权");
/*  2395 */                 this.requestJson.setData(map);
/*  2396 */                 this.requestJson.setSuccess(true);
/*       */               } else {
/*  2398 */                 String generateTokeCode = TokeUtil.generateTokeCode();
/*  2399 */                 String generateTokeCodes = TokeUtil.generateTokeCodes();
/*  2400 */                 users.setAuthorizationUserCode(boUsers.getUserCode());//将被授权的该字段(authorizationUserCode) 设置成 授权者的userCode
/*  2401 */                 users.setLogoAccountType("S");
/*       */                 String accountOperation;
///*       */                 String accountOperation;
/*  2403 */                 if ((this.accountOperationType == null) || (this.accountOperationType.equals("")))
/*  2404 */                   accountOperation = "1";
/*       */                 else {
/*  2406 */                   accountOperation = this.accountOperationType;
/*       */                 }
/*  2408 */                 users.setAccountOperationType(accountOperation);
/*  2409 */                 users.setAccessToken(generateTokeCode);
/*  2410 */                 users.setRefreshToken(generateTokeCodes);
/*  2411 */                 users.setAccessTokenTime("940923880");
/*  2412 */                 users.setRefreshTokenTime("940923880");
/*  2413 */                 BoUsers update = (BoUsers)this.boUserServicess.update(users);
/*  2414 */                 if (update == null) {
/*  2415 */                   this.requestJson.setData(map);
/*  2416 */                   this.requestJson.setMessage("授权失败");
/*  2417 */                   this.requestJson.setSuccess(false);
/*       */                 } else {
/*  2419 */                   PushService pushService = new PushService();
/*  2420 */                   if (users.getVersionType().equals("1")) {
/*  2421 */                     System.err.println("易联智家KEY");
/*  2422 */                     pushService.setAppId("C2rT1RdCoB7BaZ83l2AJM7");
/*  2423 */                     pushService.setAppkey("ApgJ2YFdI573k57hLt9Mz9");
/*  2424 */                     pushService.setMaster("JBWO7E3WyW75zgd2Bdr4NA");
/*  2425 */                   } else if (users.getVersionType().equals("2")) {
/*  2426 */                     System.err.println("爱博瑞KEY");
/*  2427 */                     pushService.setAppId("qy0HMfNc8o6fiLdtUGRfo1");
/*  2428 */                     pushService.setAppkey("cu1LHpYRwnAwDtTjq8XaQ7");
/*  2429 */                     pushService.setMaster("UpAQnXf7S47Snh00l5P5E8");
/*  2430 */                   } else if (!users.getVersionType().equals("3"))
/*       */                   {
/*  2432 */                     if (users.getVersionType().equals("4")) {
/*  2433 */                       System.err.println("思创智能KEY");
/*  2434 */                       pushService.setAppId("m5H4RPxliZAVXBlG1jka32");
/*  2435 */                       pushService.setAppkey("xizBIaTLNY5g7HxCi6kP05");
/*  2436 */                       pushService.setMaster("TRuul8ZWoO8rfFexEbvN09");
/*  2437 */                     } else if (users.getVersionType().equals("5")) {
/*  2438 */                       System.err.println("峰庭智能KEY");
/*  2439 */                       pushService.setAppId("8qv7s4OhGEAjDjEaC5bBw4");
/*  2440 */                       pushService.setAppkey("DH3NlHfEOG6YtySTspB4LA");
/*  2441 */                       pushService.setMaster("utgpb3GAGN9KsVKVun7W32");
/*  2442 */                     } else if (users.getVersionType().equals("6")) {
/*  2443 */                       System.err.println("麦宝KEY");
/*  2444 */                       pushService.setAppId("OAzON9h86e8Y2XREgjU0R9");
/*  2445 */                       pushService.setAppkey("SAE1hTMJcW8ZkRfZPRDja6");
/*  2446 */                       pushService.setMaster("y5zkumXwYPACBLJ59BZnr6");
/*  2447 */                     } else if (users.getVersionType().equals("7")) {
/*  2448 */                       System.err.println("乐沃KEY");
/*  2449 */                       pushService.setAppId("pHU6NuXh789r2ZXEYzj7z1");
/*  2450 */                       pushService.setAppkey("COGyB0sKyQ8nGHmnuNUK41");
/*  2451 */                       pushService.setMaster("qSTfJpPIQc7OkaswRI1YH7");
/*       */                     }
/*       */                   }
/*  2453 */                   String title = "";
/*  2454 */                   String CID = update.getCid();
/*       */ 
/*  2456 */                   if ((CID == null) || (CID.equals(""))) {
/*  2457 */                     System.err.println("CID为空推送不到信息");
/*       */                   } else {
/*  2459 */                     StringBuffer text = new StringBuffer();
/*       */ 
/*  2461 */                     System.err.println("*****<< " + update.getVersionType());
/*  2462 */                     if (users.getVersionType().equals("1")) {
/*  2463 */                       System.err.println("易联智家推送内容");
/*  2464 */                       title = "易家智联";
/*  2465 */                       text.append("您的账户已被人授权\n");
/*  2466 */                       text.append("打开软件将会进入登录界面请重新登录\n");
/*  2467 */                     } else if (users.getVersionType().equals("2")) {
/*  2468 */                       System.err.println("爱博瑞推送内容");
/*  2469 */                       title = "爱波瑞科技";
/*  2470 */                       text.append("您的账户已被人授权\n");
/*  2471 */                       text.append("打开软件将会进入登录界面请重新登录\n");
/*  2472 */                     } else if (!users.getVersionType().equals("3"))
/*       */                     {
/*  2474 */                       if (users.getVersionType().equals("4")) {
/*  2475 */                         System.err.println("思创智能推送内容");
/*  2476 */                         title = "思创智能";
/*  2477 */                         text.append("您的账户已被人授权\n");
/*  2478 */                         text.append("打开软件将会进入登录界面请重新登录\n");
/*  2479 */                       } else if (users.getVersionType().equals("5")) {
/*  2480 */                         System.err.println("峰庭智能推送内容");
/*  2481 */                         title = "峰庭智能";
/*  2482 */                         text.append("您的账户已被人授权\n");
/*  2483 */                         text.append("打开软件将会进入登录界面请重新登录\n");
/*  2484 */                       } else if (users.getVersionType().equals("6")) {
/*  2485 */                         System.err.println("麦宝推送内容");
/*  2486 */                         title = "麦宝";
/*  2487 */                         text.append("您的账户已被人授权\n");
/*  2488 */                         text.append("打开软件将会进入登录界面请重新登录\n");
/*  2489 */                       } else if (users.getVersionType().equals("7")) {
/*  2490 */                         System.err.println("乐沃推送内容");
/*  2491 */                         title = "乐沃";
/*  2492 */                         text.append("您的账户已被人授权\n");
/*  2493 */                         text.append("打开软件将会进入登录界面请重新登录\n");
/*       */                       }
/*       */                     }
/*  2496 */                     Integer type = users.getPhoneType();
/*       */ 
/*  2498 */                     if ((type == null) || (type.intValue() == 0)) {
/*  2499 */                       pushService.pushToSingle(CID, title, text.toString(), text.toString());
/*       */                     }
/*       */                     else {
/*  2502 */                       pushService.apnPush(CID, title, text.toString(), text.toString());
/*       */                     }
/*       */                   }
/*       */ 
/*  2506 */                   map.put("result", "授权成功");
/*  2507 */                   this.requestJson.setData(map);
/*  2508 */                   this.requestJson.setSuccess(true);
/*       */                 }
/*       */               }
/*       */             }
/*       */ 
/*       */           }
/*       */           else
/*       */           {
/*  2516 */             map.put("result", "验证码错误");
/*  2517 */             this.requestJson.setData(map);
/*       */ 
/*  2519 */             this.requestJson.setSuccess(true);
/*       */           }
/*       */         }
/*       */       }
/*       */       else {
/*  2524 */         System.err.println("验证不通过");
/*       */ 
/*  2526 */         this.requestJson.setData(map);
/*  2527 */         this.requestJson.setMessage("验证不通过");
/*  2528 */         this.requestJson.setSuccess(false);
/*       */       }
/*       */     } else {
/*  2531 */       Boolean ral = isRal(header, header2, header3, header4, userCode, "开始授权");
/*  2532 */       if (ral.booleanValue()) {
/*  2533 */         System.err.println("验证通过");
/*  2534 */         Long accessToken = Long.valueOf(header);
/*  2535 */         BoUsers boUsers = this.boUserServicess.findByUserUserCode(userCode);
/*  2536 */         if (boUsers == null) {
/*  2537 */           this.requestJson.setData(map);
/*  2538 */           this.requestJson.setMessage("Invalid_User");
/*  2539 */           this.requestJson.setSuccess(true);
/*       */         } else {
/*  2541 */           String[] array = (String[])StaticUtils.AUTHORIZATIONSENDMSG.get(this.userPhone + "_A");
/*  2542 */           String verificationCode = "";
/*  2543 */           String verificationCodes = "";
/*       */ 
/*  2545 */           if (array != null) {
/*  2546 */             verificationCode = array[0];
/*       */           }
/*       */ 
/*  2549 */           if (verificationCode.equals(""))
/*  2550 */             verificationCodes = "***^&*&*&^&*&^$$^&*()&";
/*       */           else {
/*  2552 */             verificationCodes = verificationCode;
/*       */           }
/*  2554 */           System.err.println("----" + verificationCode);
/*  2555 */           System.err.println("====" + this.verifyCode);
/*  2556 */           if (verificationCodes.equals(this.verifyCode)) {
/*  2557 */             List list = this.boUserServicess.getByAuthorizeUserCode(boUsers.getUserCode());
/*  2558 */             if (list.size() > 8) {
/*  2559 */               this.requestJson.setData(map);
/*  2560 */               System.err.println("当前版本暂时只能授权用户7个");
/*  2561 */               this.requestJson.setMessage("当前版本暂时只能授权用户7个");
/*  2562 */               this.requestJson.setSuccess(false);
/*       */             } else {
/*  2564 */               BoUsers users = this.boUserServicess.findByUserPhone(this.userPhone);
/*  2565 */               if (!users.getAuthorizationUserCode().equals("")) {
/*  2566 */                 map.put("result", "账户已被其他账户授权");
/*  2567 */                 this.requestJson.setData(map);
/*  2568 */                 this.requestJson.setSuccess(true);
/*       */               } else {
/*  2570 */                 String generateTokeCode = TokeUtil.generateTokeCode();
/*  2571 */                 String generateTokeCodes = TokeUtil.generateTokeCodes();
/*  2572 */                 users.setAuthorizationUserCode(boUsers.getUserCode());
/*  2573 */                 users.setLogoAccountType("S");
/*       */                 String accountOperation;
///*       */                 String accountOperation;
/*  2575 */                 if ((this.accountOperationType == null) || (this.accountOperationType.equals("")))
/*  2576 */                   accountOperation = "1";
/*       */                 else {
/*  2578 */                   accountOperation = this.accountOperationType;
/*       */                 }
/*  2580 */                 users.setAccountOperationType(accountOperation);
/*  2581 */                 users.setAccessToken(generateTokeCode);
/*  2582 */                 users.setRefreshToken(generateTokeCodes);
/*  2583 */                 users.setAccessTokenTime("940923880");
/*  2584 */                 users.setRefreshTokenTime("940923880");
/*  2585 */                 BoUsers update = (BoUsers)this.boUserServicess.update(users);
/*  2586 */                 if (update == null) {
/*  2587 */                   this.requestJson.setData(map);
/*  2588 */                   this.requestJson.setMessage("授权失败");
/*  2589 */                   this.requestJson.setSuccess(false);
/*       */                 } else {
/*  2591 */                   PushService pushService = new PushService();
/*  2592 */                   if (boUsers.getVersionType().equals("1")) {
/*  2593 */                     System.err.println("易联智家KEY");
/*  2594 */                     pushService.setAppId("C2rT1RdCoB7BaZ83l2AJM7");
/*  2595 */                     pushService.setAppkey("ApgJ2YFdI573k57hLt9Mz9");
/*  2596 */                     pushService.setMaster("JBWO7E3WyW75zgd2Bdr4NA");
/*  2597 */                   } else if (boUsers.getVersionType().equals("2")) {
/*  2598 */                     System.err.println("爱博瑞KEY");
/*  2599 */                     pushService.setAppId("qy0HMfNc8o6fiLdtUGRfo1");
/*  2600 */                     pushService.setAppkey("cu1LHpYRwnAwDtTjq8XaQ7");
/*  2601 */                     pushService.setMaster("UpAQnXf7S47Snh00l5P5E8");
/*  2602 */                   } else if (!boUsers.getVersionType().equals("3"))
/*       */                   {
/*  2604 */                     if (boUsers.getVersionType().equals("4")) {
/*  2605 */                       System.err.println("思创智能KEY");
/*  2606 */                       pushService.setAppId("m5H4RPxliZAVXBlG1jka32");
/*  2607 */                       pushService.setAppkey("xizBIaTLNY5g7HxCi6kP05");
/*  2608 */                       pushService.setMaster("TRuul8ZWoO8rfFexEbvN09");
/*  2609 */                     } else if (boUsers.getVersionType().equals("5")) {
/*  2610 */                       System.err.println("峰庭智能KEY");
/*  2611 */                       pushService.setAppId("8qv7s4OhGEAjDjEaC5bBw4");
/*  2612 */                       pushService.setAppkey("DH3NlHfEOG6YtySTspB4LA");
/*  2613 */                       pushService.setMaster("utgpb3GAGN9KsVKVun7W32");
/*  2614 */                     } else if (users.getVersionType().equals("6")) {
/*  2615 */                       System.err.println("麦宝KEY");
/*  2616 */                       pushService.setAppId("OAzON9h86e8Y2XREgjU0R9");
/*  2617 */                       pushService.setAppkey("SAE1hTMJcW8ZkRfZPRDja6");
/*  2618 */                       pushService.setMaster("y5zkumXwYPACBLJ59BZnr6");
/*  2619 */                     } else if (users.getVersionType().equals("7")) {
/*  2620 */                       System.err.println("乐沃KEY");
/*  2621 */                       pushService.setAppId("pHU6NuXh789r2ZXEYzj7z1");
/*  2622 */                       pushService.setAppkey("COGyB0sKyQ8nGHmnuNUK41");
/*  2623 */                       pushService.setMaster("qSTfJpPIQc7OkaswRI1YH7");
/*       */                     }
/*       */                   }
/*  2625 */                   String title = "";
/*  2626 */                   String CID = update.getCid();
/*       */ 
/*  2628 */                   if ((CID == null) || (CID.equals(""))) {
/*  2629 */                     System.err.println("CID为空推送不到信息");
/*       */                   } else {
/*  2631 */                     StringBuffer text = new StringBuffer();
/*       */ 
/*  2633 */                     System.err.println("*****<< " + update.getVersionType());
/*  2634 */                     if (boUsers.getVersionType().equals("1")) {
/*  2635 */                       System.err.println("易联智家推送内容");
/*  2636 */                       title = "易家智联";
/*  2637 */                       text.append("您的账户已被人授权\n");
/*  2638 */                       text.append("打开软件将会进入登录界面请重新登录\n");
/*  2639 */                     } else if (boUsers.getVersionType().equals("2")) {
/*  2640 */                       System.err.println("爱博瑞推送内容");
/*  2641 */                       title = "爱波瑞科技";
/*  2642 */                       text.append("您的账户已被人授权\n");
/*  2643 */                       text.append("打开软件将会进入登录界面请重新登录\n");
/*  2644 */                     } else if (!boUsers.getVersionType().equals("3"))
/*       */                     {
/*  2646 */                       if (boUsers.getVersionType().equals("4")) {
/*  2647 */                         System.err.println("思创智能推送内容");
/*  2648 */                         title = "思创智能";
/*  2649 */                         text.append("您的账户已被人授权\n");
/*  2650 */                         text.append("打开软件将会进入登录界面请重新登录\n");
/*  2651 */                       } else if (boUsers.getVersionType().equals("5")) {
/*  2652 */                         System.err.println("峰庭智能推送内容");
/*  2653 */                         title = "峰庭智能";
/*  2654 */                         text.append("您的账户已被人授权\n");
/*  2655 */                         text.append("打开软件将会进入登录界面请重新登录\n");
/*  2656 */                       } else if (users.getVersionType().equals("6")) {
/*  2657 */                         System.err.println("麦宝推送内容");
/*  2658 */                         title = "麦宝";
/*  2659 */                         text.append("您的账户已被人授权\n");
/*  2660 */                         text.append("打开软件将会进入登录界面请重新登录\n");
/*  2661 */                       } else if (users.getVersionType().equals("7")) {
/*  2662 */                         System.err.println("乐沃推送内容");
/*  2663 */                         title = "乐沃";
/*  2664 */                         text.append("您的账户已被人授权\n");
/*  2665 */                         text.append("打开软件将会进入登录界面请重新登录\n");
/*       */                       }
/*       */                     }
/*  2668 */                     Integer type = update.getPhoneType();
/*       */ 
/*  2670 */                     if ((type == null) || (type.intValue() == 0)) {
/*  2671 */                       pushService.pushToSingle(CID, title, text.toString(), text.toString());
/*       */                     }
/*       */                     else {
/*  2674 */                       pushService.apnPush(CID, title, text.toString(), text.toString());
/*       */                     }
/*       */                   }
/*       */ 
/*  2678 */                   map.put("result", "授权成功");
/*  2679 */                   this.requestJson.setData(map);
/*  2680 */                   this.requestJson.setSuccess(true);
/*       */                 }
/*       */               }
/*       */             }
/*       */ 
/*       */           }
/*       */           else
/*       */           {
/*  2688 */             map.put("result", "验证码错误");
/*  2689 */             this.requestJson.setData(map);
/*       */ 
/*  2691 */             this.requestJson.setSuccess(true);
/*       */           }
/*       */         }
/*       */       }
/*       */       else {
/*  2696 */         System.err.println("验证不通过");
/*       */ 
/*  2698 */         this.requestJson.setData(map);
/*  2699 */         this.requestJson.setMessage("验证不通过");
/*  2700 */         this.requestJson.setSuccess(false);
/*       */       }
/*       */     }
/*  2703 */     return "success";
/*       */   }
/*       */ 
/*       */   @Action(value="authorizeSendMsg", results={@org.apache.struts2.convention.annotation.Result(type="json", params={"root", "requestJson"})})
/*       */   public String authorizeSendMsg()
/*       */     throws UnsupportedEncodingException
/*       */   {
/*  2714 */     this.requestJson = new RequestJson();
/*       */ 
/*  2716 */     Map maps = new HashMap();
/*  2717 */     String createRandomVcode = this.s.createRandomVcode();
/*  2718 */     String sendMsg = null;
/*  2719 */     BoUsers boUsers = this.boUserServicess.findByUserPhone(this.userPhone);
/*  2720 */     if (boUsers == null) {
/*  2721 */       this.requestJson.setData(maps);
/*  2722 */       System.err.println("该用户尚未注册");
/*  2723 */       this.requestJson.setMessage("该用户尚未注册");
/*  2724 */       this.requestJson.setSuccess(false);
/*       */     } else {
/*  2726 */       List lists = this.boUserServicess.getByAuthorizeUserCode(boUsers.getUserCode());
/*  2727 */       if (lists.size() > 0) {
/*  2728 */         this.requestJson.setData(maps);
/*  2729 */         System.err.println("该账户已有授权用户暂不能授权,待将旗下授权用户解除,方才能授权该账户");
/*  2730 */         this.requestJson.setMessage("该账户已有授权用户暂不能授权,待将旗下授权用户解除,方才能授权该账户");
/*  2731 */         this.requestJson.setSuccess(false);
/*       */       }
/*  2733 */       else if (!boUsers.getAuthorizationUserCode().equals("")) {
/*  2734 */         System.err.println("账户已被其他账户授权");
/*  2735 */         this.requestJson.setData(maps);
/*  2736 */         this.requestJson.setMessage("账户已被其他账户授权");
/*  2737 */         this.requestJson.setSuccess(false);
/*       */       }
/*  2739 */       else if (this.versionType.equals("1")) {
/*  2740 */         sendMsg = SendMsgUtil.sendMsg(this.userPhone, "尊敬的用户，您的授权验证码为 :  " + createRandomVcode + "。如果被授权，您将显示主账号的房间设备信息。");
/*  2741 */         System.err.println(sendMsg);
/*  2742 */         String[] split = sendMsg.split(",");
/*  2743 */         if (split[1].equals("0")) {
/*  2744 */           Map map = new HashMap();
/*  2745 */           map.put("sendMsg", sendMsg);
/*  2746 */           StaticUtils.AUTHORIZATIONSENDMSG.put(this.userPhone + "_" + "A", new String[] { 
/*  2747 */             createRandomVcode, new Date().getTime()+"" });
/*  2748 */           this.requestJson.setData(map);
/*  2749 */           String userPhones = this.userPhone;
/*       */ 
/*  2751 */           System.out.println(userPhones);
/*  2752 */           System.err.println(createRandomVcode);
/*  2753 */           this.requestJson.setMessage("发送成功");
/*  2754 */           this.requestJson.setSuccess(true);
/*  2755 */         } else if (split[1].equals("101")) {
/*  2756 */           this.requestJson.setMessage("无此用户");
/*  2757 */           this.requestJson.setSuccess(false);
/*  2758 */         } else if (split[1].equals("103")) {
/*  2759 */           this.requestJson.setMessage("提交过快");
/*  2760 */           this.requestJson.setSuccess(false);
/*  2761 */         } else if (split[1].equals("108")) {
/*  2762 */           this.requestJson.setMessage("手机号码格式错误");
/*  2763 */           this.requestJson.setSuccess(false);
/*  2764 */         } else if (split[1].equals("109")) {
/*  2765 */           this.requestJson.setMessage("该号码发送数量已使用完");
/*  2766 */           this.requestJson.setSuccess(false);
/*       */         }
/*  2768 */       } else if (this.versionType.equals("2")) {
/*  2769 */         sendMsg = SendMsgUtil.aiBoRuisendMsg(this.userPhone, "【爱波瑞科技】尊敬的用户，您的授权验证码为 :  " + createRandomVcode + "。如果被授权，您将显示主账号的房间设备信息。");
/*  2770 */         Map map = new HashMap();
/*  2771 */         map.put("sendMsg", sendMsg);
/*  2772 */         StaticUtils.AUTHORIZATIONSENDMSG.put(this.userPhone + "_" + "A", new String[] { 
/*  2773 */           createRandomVcode, new Date().getTime()+"" });
/*  2774 */         this.requestJson.setData(map);
/*  2775 */         this.requestJson.setMessage("发送成功");
/*  2776 */         String userPhones = this.userPhone;
/*       */ 
/*  2778 */         System.out.println(userPhones);
/*  2779 */         System.err.println(createRandomVcode);
/*  2780 */         this.requestJson.setSuccess(true);
/*  2781 */       } else if (!this.versionType.equals("3"))
/*       */       {
/*  2783 */         if (this.versionType.equals("4")) {
/*  2784 */           Map map = new HashMap();
/*  2785 */           sendMsg = SendMsgUtil.siChuangSendMsg(this.userPhone, "【思创智能】尊敬的用户，您的授权验证码为 : " + createRandomVcode + "。如果被授权，您将显示主账号的房间设备信息。");
/*  2786 */           System.err.println(sendMsg);
/*  2787 */           map.put("sendMsg", sendMsg);
/*  2788 */           StaticUtils.AUTHORIZATIONSENDMSG.put(this.userPhone + "_" + "A", new String[] { 
/*  2789 */             createRandomVcode, new Date().getTime()+"" });
/*  2790 */           this.requestJson.setData(map);
/*  2791 */           this.requestJson.setMessage("发送成功");
/*  2792 */         } else if (this.versionType.equals("5")) {
/*  2793 */           Map map = new HashMap();
/*  2794 */           sendMsg = SendMsgUtil.fengTingSendMsg(this.userPhone, "【峰庭智能】尊敬的用户，您的授权验证码为 : " + createRandomVcode + "。如果被授权，您将显示主账号的房间设备信息。");
/*  2795 */           System.err.println(sendMsg);
/*  2796 */           map.put("sendMsg", sendMsg);
/*  2797 */           StaticUtils.AUTHORIZATIONSENDMSG.put(this.userPhone + "_" + "A", new String[] { 
/*  2798 */             createRandomVcode, new Date().getTime()+"" });
/*  2799 */           this.requestJson.setData(map);
/*  2800 */           this.requestJson.setMessage("发送成功");
/*       */         }
/*       */ 
/*       */       }
/*       */ 
/*       */     }
/*       */ 
/*  2807 */     return "success";
/*       */   }
/*       */     //2018-2-2注释  该方法无用
///*       */   @Action(value="appVersionManager", results={@org.apache.struts2.convention.annotation.Result(type="json", params={"root", "requestJson"})})
///*       */   public String appVersionManager()
///*       */   {//自动登录会进入该方法 --有问题    ==》自动登录不该进入该方法  
///*  2818 */     Map map = new HashMap();
///*  2819 */     HttpServletRequest request = ServletActionContext.getRequest();
///*  2820 */     String header = request.getHeader("user-agent");
///*  2821 */     System.err.println(header);
///*  2822 */     String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + "/smarthome.IMCPlatform" + "/";
//                logger.info("basePath:"+basePath);
///*       */     try {
///*  2824 */       if (this.phoneType.equals("0")) {//安卓手机
///*  2825 */         if (Integer.valueOf(this.provider).intValue() == 1) {
///*  2826 */           String root = System.getProperty("webapp.root");
///*  2827 */           String tempDir = root + "/" + "apk";
///*  2828 */           ApkInfo apkInfo = GetApkInfo.getApkInfoByFilePath(tempDir + "\\smartHome.apk");
///*  2829 */           String s1 = this.appVersion.substring(2, 5);
///*  2830 */           String s2 = apkInfo.getVersionName().substring(2, 5);
///*  2831 */           System.err.println(s2);
///*  2832 */           if (Double.valueOf(s1).doubleValue() < Double.valueOf(s2).doubleValue()) {
///*  2833 */             BoAndroidVersion boAndroidVersion = this.BoAndroidVersionService.findAndroidVersionById(Integer.valueOf(1));
///*  2834 */             if (boAndroidVersion != null) {
///*  2835 */               System.err.println(basePath + boAndroidVersion.getApkUrl().toString());
///*  2836 */               map.put("url", basePath + boAndroidVersion.getApkUrl().toString());
///*  2837 */               this.requestJson.setData(map);
///*  2838 */               this.requestJson.setMessage("当前版本较低,请即使更新");
///*  2839 */               this.requestJson.setSuccess(false);
///*       */             }
///*       */           } else {
///*  2842 */             this.requestJson.setData(map);
///*  2843 */             this.requestJson.setMessage("当前已是最新版本");
///*  2844 */             this.requestJson.setSuccess(true);
///*       */           }
///*  2846 */         } else if (Integer.valueOf(this.provider).intValue() == 2) {
///*  2847 */           String root = System.getProperty("webapp.root");
///*  2848 */           String tempDir = root + "/" + "apk";
///*  2849 */           ApkInfo apkInfo = GetApkInfo.getApkInfoByFilePath(tempDir + "\\aiborui.apk");
///*  2850 */           String s1 = this.appVersion.substring(2, 5);
///*  2851 */           String s2 = apkInfo.getVersionName().substring(2, 5);
///*  2852 */           System.err.println(apkInfo.getVersionName());
///*  2853 */           if (Double.valueOf(s1).doubleValue() < Double.valueOf(s2).doubleValue()) {
///*  2854 */             BoAndroidVersion boAndroidVersion = this.BoAndroidVersionService.findAndroidVersionById(Integer.valueOf(2));
///*  2855 */             if (boAndroidVersion != null) {
///*  2856 */               map.put("url", basePath + boAndroidVersion.getApkUrl().toString());
///*  2857 */               this.requestJson.setData(map);
///*  2858 */               this.requestJson.setMessage("当前版本较低,请即使更新");
///*  2859 */               this.requestJson.setSuccess(false);
///*       */             }
///*       */           } else {
///*  2862 */             this.requestJson.setData(map);
///*  2863 */             this.requestJson.setMessage("当前已是最新版本");
///*  2864 */             this.requestJson.setSuccess(true);
///*       */           }
///*  2866 */         } else if (Integer.valueOf(this.provider).intValue() != 3)
///*       */         {
///*  2868 */           if (Integer.valueOf(this.provider).intValue() == 4) {
///*  2869 */             String root = System.getProperty("webapp.root");
///*  2870 */             String tempDir = root + "/" + "apk";
///*  2871 */             ApkInfo apkInfo = GetApkInfo.getApkInfoByFilePath(tempDir + "\\sichuang.apk");
///*  2872 */             String s1 = this.appVersion.substring(2, 5);
///*  2873 */             String s2 = apkInfo.getVersionName().substring(2, 5);
///*  2874 */             System.err.println(apkInfo.getVersionName());
///*  2875 */             if (Double.valueOf(s1).doubleValue() < Double.valueOf(s2).doubleValue()) {
///*  2876 */               BoAndroidVersion boAndroidVersion = this.BoAndroidVersionService.findAndroidVersionById(Integer.valueOf(4));
///*  2877 */               if (boAndroidVersion != null) {
///*  2878 */                 map.put("url", basePath + boAndroidVersion.getApkUrl().toString());
///*  2879 */                 this.requestJson.setData(map);
///*  2880 */                 this.requestJson.setMessage("当前版本较低,请即使更新");
///*  2881 */                 this.requestJson.setSuccess(false);
///*       */               }
///*       */             } else {
///*  2884 */               this.requestJson.setData(map);
///*  2885 */               this.requestJson.setMessage("当前已是最新版本");
///*  2886 */               this.requestJson.setSuccess(true);
///*       */             }
///*  2888 */           } else if (Integer.valueOf(this.provider).intValue() == 5) {
///*  2889 */             String root = System.getProperty("webapp.root");
///*  2890 */             String tempDir = root + "/" + "apk";
///*  2891 */             ApkInfo apkInfo = GetApkInfo.getApkInfoByFilePath(tempDir + "\\fengting.apk");
///*  2892 */             String s1 = this.appVersion.substring(2, 5);
///*  2893 */             String s2 = apkInfo.getVersionName().substring(2, 5);
///*  2894 */             System.err.println(apkInfo.getVersionName());
///*  2895 */             if (Double.valueOf(s1).doubleValue() < Double.valueOf(s2).doubleValue()) {
///*  2896 */               BoAndroidVersion boAndroidVersion = this.BoAndroidVersionService.findAndroidVersionById(Integer.valueOf(5));
///*  2897 */               if (boAndroidVersion != null) {
///*  2898 */                 map.put("url", basePath + boAndroidVersion.getApkUrl().toString());
///*  2899 */                 this.requestJson.setData(map);
///*  2900 */                 this.requestJson.setMessage("当前版本较低,请即使更新");
///*  2901 */                 this.requestJson.setSuccess(false);
///*       */               }
///*       */             } else {
///*  2904 */               this.requestJson.setData(map);
///*  2905 */               this.requestJson.setMessage("当前已是最新版本");
///*  2906 */               this.requestJson.setSuccess(true);
///*       */             }
///*  2908 */           } else if (Integer.valueOf(this.provider).intValue() == 6) {
///*  2909 */             String root = System.getProperty("webapp.root");
///*  2910 */             String tempDir = root + "/" + "apk";
///*  2911 */             ApkInfo apkInfo = GetApkInfo.getApkInfoByFilePath(tempDir + "\\maibao.apk");
///*  2912 */             String s1 = this.appVersion.substring(2, 5);
///*  2913 */             String s2 = apkInfo.getVersionName().substring(2, 5);
///*  2914 */             System.err.println(apkInfo.getVersionName());
///*  2915 */             if (Double.valueOf(s1).doubleValue() < Double.valueOf(s2).doubleValue()) {
///*  2916 */               BoAndroidVersion boAndroidVersion = this.BoAndroidVersionService.findAndroidVersionById(Integer.valueOf(5));
///*  2917 */               if (boAndroidVersion != null) {
///*  2918 */                 map.put("url", basePath + boAndroidVersion.getApkUrl().toString());
///*  2919 */                 this.requestJson.setData(map);
///*  2920 */                 this.requestJson.setMessage("当前版本较低,请即使更新");
///*  2921 */                 this.requestJson.setSuccess(false);
///*       */               }
///*       */             } else {
///*  2924 */               this.requestJson.setData(map);
///*  2925 */               this.requestJson.setMessage("当前已是最新版本");
///*  2926 */               this.requestJson.setSuccess(true);
///*       */             }
///*  2928 */           } else if (Integer.valueOf(this.provider).intValue() == 7) {
///*  2929 */             this.requestJson.setData(map);
///*  2930 */             this.requestJson.setMessage("当前已是最新版本");
///*  2931 */             this.requestJson.setSuccess(true);
///*       */           }
///*       */         }
///*       */       } else { BoIosVersion boIosVersion = this.boIosVersionService.findAndroidVersionById(Integer.valueOf(this.provider));
///*  2935 */         if (boIosVersion != null) {
///*  2936 */           if (Double.valueOf(this.appVersion).doubleValue() < Double.valueOf(boIosVersion.getVersion()).doubleValue()) {
///*  2937 */             map.put("url", boIosVersion.getApkUrl().toString());
///*  2938 */             this.requestJson.setData(map);
///*  2939 */             this.requestJson.setMessage("当前版本较低,请即使更新");
///*  2940 */             this.requestJson.setSuccess(false);
///*       */           } else {
///*  2942 */             this.requestJson.setData(map);
///*  2943 */             this.requestJson.setMessage("当前已是最新版本");
///*  2944 */             this.requestJson.setSuccess(true);
///*       */           }
///*       */         }
///*       */       }
///*       */     }
///*       */     catch (Exception e)
///*       */     {
///*  2951 */       logger.info("error_" + e.getMessage());
///*  2952 */       this.requestJson.setData(map);
///*  2953 */       this.requestJson.setMessage("服务器发生异常");
///*  2954 */       this.requestJson.setSuccess(false);
///*       */     }
///*  2956 */     return "success";
///*       */   }
/*       */ 
/*       */   @Action(value="dropDownControlEnclosureRelayStatus", results={@org.apache.struts2.convention.annotation.Result(type="json", params={"root", "requestJson"})})
/*       */   public String dropDownControlEnclosureRelayStatus()
/*       */   {
/*  2966 */     this.requestJson = new RequestJson();
/*  2967 */     Map map = new HashMap();
/*  2968 */     HttpServletRequest request = ServletActionContext.getRequest();
/*  2969 */     String ip = request.getRemoteAddr();
/*  2970 */     String timestamp = request.getHeader("timestamp");
/*  2971 */     String nonce = request.getHeader("nonce");
/*  2972 */     String header3 = request.getHeader("sign");
/*  2973 */     String header4 = request.getHeader("access_token");
/*  2974 */     String userCode = request.getHeader("userCode");
/*  2975 */     if (userCode.contains(",")) {
/*  2976 */       String[] userCode2 = userCode.split(",");
/*  2977 */       BoUsers boUsers = this.boUserServicess.findByUserUserCode(userCode2[0].trim().toString());
/*  2978 */       BoUsers phone = this.boUserServicess.findByUserPhone(userCode2[1].trim().toString());
/*  2979 */       Boolean ral = isRal(timestamp, nonce, header3, header4, userCode, "下拉刷新控制盒查询继电器");
/*  2980 */       if (ral.booleanValue()) {
/*  2981 */         System.err.println("验证通过");
/*  2982 */         Long accessToken = Long.valueOf(timestamp);
/*       */ 
/*  2984 */         if ((phone == null) || (boUsers == null)) {
/*  2985 */           this.requestJson.setData(map);
/*  2986 */           this.requestJson.setMessage("Invalid_User");
/*  2987 */           this.requestJson.setSuccess(true);
/*       */         }
/*  2989 */         else if (header4.equals(phone.getAccessToken())) {
/*  2990 */           if (accessToken.longValue() < Long.valueOf(phone.getAccessTokenTime()).longValue()) {
/*       */             try {
/*  2992 */               List<BoControlEnclosure> list = this.boControlEnclosureService.controlEnclosure(this.deviceAddress, userCode2[0].trim().toString(), this.deviceAddress);
/*       */               String strss;
/*  2993 */               for (int i = 0; i < list.size(); i++) {
/*  2994 */                 Thread.sleep(100L);
/*  2995 */                 packNum(userCode2[0].trim().toString());
/*       */ 
/*  2997 */                 BoControlEnclosure obj = (BoControlEnclosure)list.get(i);
/*  2998 */                 strss = "RELAY-READ-" + user_num.get(userCode2[0].trim().toString()) + "," + obj.getDeviceAddress();
/*  2999 */                 byte[] bss = strss.getBytes();
/*  3000 */                 System.err.println(new String(bss));
/*  3001 */                 this.packetProcessHelper.processSendDDatas(this.deviceAddress, bss);
/*  3002 */                 StaticUtil.READCONTROLENCLOSURE.put(this.deviceAddress + "_" + "A", new String[] { 
/*  3003 */                   userCode2[0].trim().toString(), new Date().getTime()+"" });
/*       */               }
/*       */ 
/*  3006 */               if (list.size() <= 0) 
//							break label1516;
							return "list.size()<=0";
/*  3007 */               List voList = new ArrayList();
/*  3008 */               for (BoControlEnclosure boControlEnclosure : list) {
/*  3009 */                 Map maps = new HashMap();
/*  3010 */                 maps.put("deviceCode", boControlEnclosure.getBoDevice().getDeviceCode());
/*  3011 */                 maps.put("deviceAddress", boControlEnclosure.getDeviceAddress());
/*  3012 */                 maps.put("nickName", boControlEnclosure.getNickName());
/*  3013 */                 maps.put("state", boControlEnclosure.getState());
/*  3014 */                 voList.add(maps);
/*       */               }
/*  3016 */               this.requestJson.setData(voList);
/*       */             }
/*       */             catch (Exception e)
/*       */             {
/*  3023 */               logger.info("error_" + e.getMessage());
/*  3024 */               this.requestJson.setData(map);
/*  3025 */               this.requestJson.setMessage("服务器发生异常");
/*  3026 */               this.requestJson.setSuccess(false);
/*       */             }
/*       */           } else {
/*  3029 */             System.err.println("AToken时间戳超时了");
/*  3030 */             this.requestJson.setMessage("超时了");
/*  3031 */             this.requestJson.setSuccess(false);
/*       */           }
/*       */         } else {
/*  3034 */           System.err.println("AToken超时了");
/*  3035 */           this.requestJson.setData(map);
/*  3036 */           this.requestJson.setMessage("超时了");
/*  3037 */           this.requestJson.setSuccess(false);
/*       */         }
/*       */       }
/*       */       else {
/*  3041 */         System.err.println("验证不通过");
/*       */ 
/*  3043 */         this.requestJson.setData(map);
/*  3044 */         this.requestJson.setMessage("验证不通过");
/*  3045 */         this.requestJson.setSuccess(false);
/*       */       }
/*       */     } else {
/*  3048 */       Boolean ral = isRal(timestamp, nonce, header3, header4, userCode, "下拉刷新控制盒查询继电器");
/*  3049 */       if (ral.booleanValue()) {
/*  3050 */         System.err.println("验证通过");
/*  3051 */         Long accessToken = Long.valueOf(timestamp);
/*  3052 */         BoUsers boUsers = this.boUserServicess.findByUserUserCode(userCode);
/*  3053 */         if (boUsers == null) {
/*  3054 */           this.requestJson.setData(map);
/*  3055 */           this.requestJson.setMessage("Invalid_User");
/*  3056 */           this.requestJson.setSuccess(true);
/*       */         }
/*  3058 */         else if (accessToken.longValue() < Long.valueOf(boUsers.getAccessTokenTime()).longValue()) {
/*       */           try {
/*  3060 */             List<BoControlEnclosure> list = this.boControlEnclosureService.controlEnclosure(this.deviceAddress, userCode, this.deviceAddress);
/*       */             String strss;
/*  3061 */             for (int i = 0; i < list.size(); i++) {
/*  3062 */               Thread.sleep(100L);
/*  3063 */               if (user_num.get(userCode) == null)
/*  3064 */                 user_num.put(userCode, Integer.valueOf(0));
/*       */               else {
/*  3066 */                 user_num.put(userCode, Integer.valueOf(((Integer)user_num.get(userCode)).intValue() == 255 ? 0 : ((Integer)user_num.get(userCode)).intValue() + 1));
/*       */               }
/*       */ 
/*  3069 */               BoControlEnclosure obj = (BoControlEnclosure)list.get(i);
/*  3070 */               strss = "RELAY-READ-" + user_num.get(userCode) + "," + obj.getDeviceAddress();
/*  3071 */               byte[] bss = strss.getBytes();
/*  3072 */               System.err.println(new String(bss));
/*  3073 */               this.packetProcessHelper.processSendDDatas(this.deviceAddress, bss);
/*  3074 */               StaticUtil.READCONTROLENCLOSURE.put(this.deviceAddress + "_" + "A", new String[] { 
/*  3075 */                 userCode, new Date().getTime()+"" });
/*       */             }
/*       */ 
/*  3078 */             if (list.size() <= 0) 
//							break label1516;
							return "list.size()<=0";
/*  3079 */             List voList = new ArrayList();
/*  3080 */             for (BoControlEnclosure boControlEnclosure : list) {
/*  3081 */               Map maps = new HashMap();
/*  3082 */               maps.put("deviceCode", boControlEnclosure.getBoDevice().getDeviceCode());
/*  3083 */               maps.put("deviceAddress", boControlEnclosure.getDeviceAddress());
/*  3084 */               maps.put("nickName", boControlEnclosure.getNickName());
/*  3085 */               maps.put("state", boControlEnclosure.getState());
/*  3086 */               voList.add(maps);
/*       */             }
/*  3088 */             this.requestJson.setData(voList);
/*       */           }
/*       */           catch (Exception e)
/*       */           {
/*  3095 */             logger.info("error_" + e.getMessage());
/*  3096 */             this.requestJson.setData(map);
/*  3097 */             this.requestJson.setMessage("服务器发生异常");
/*  3098 */             this.requestJson.setSuccess(false);
/*       */           }
/*       */         } else {
/*  3101 */           this.requestJson.setData(map);
/*  3102 */           this.requestJson.setMessage("超时了");
/*  3103 */           this.requestJson.setSuccess(false);
/*       */         }
/*       */       }
/*       */       else
/*       */       {
/*  3108 */         System.err.println("验证不通过");
/*       */ 
/*  3110 */         this.requestJson.setData(map);
/*  3111 */         this.requestJson.setMessage("验证不通过");
/*  3112 */         this.requestJson.setSuccess(false);
/*       */       }
/*       */     }
/*  3115 */     label1516: return "success";
/*       */   }
/*       */ 
/*       */   @Action(value="updateControlEnclosureName", results={@org.apache.struts2.convention.annotation.Result(type="json", params={"root", "requestJson"})})
/*       */   public String updateControlEnclosureName()
/*       */   {
/*  3125 */     this.requestJson = new RequestJson();
/*  3126 */     Map map = new HashMap();
/*  3127 */     HttpServletRequest request = ServletActionContext.getRequest();
/*  3128 */     String ip = request.getRemoteAddr();
/*  3129 */     String header = request.getHeader("timestamp");
/*  3130 */     String header2 = request.getHeader("nonce");
/*  3131 */     String header3 = request.getHeader("sign");
/*  3132 */     String header4 = request.getHeader("access_token");
/*  3133 */     String userCode = request.getHeader("userCode");
/*  3134 */     if (userCode.contains(",")) {
/*  3135 */       String[] userCode2 = userCode.split(",");
/*  3136 */       BoUsers boUsers = this.boUserServicess.findByUserUserCode(userCode2[0].trim().toString());
/*  3137 */       BoUsers phone = this.boUserServicess.findByUserPhone(userCode2[1].trim().toString());
/*  3138 */       Boolean ral = isRal(header, header2, header3, header4, userCode, "修改控制盒列表里的昵称");
/*  3139 */       if (ral.booleanValue()) {
/*  3140 */         System.err.println("验证通过");
/*  3141 */         Long accessToken = Long.valueOf(header);
/*       */ 
/*  3143 */         if ((phone == null) || (boUsers == null)) {
/*  3144 */           this.requestJson.setData(map);
/*  3145 */           this.requestJson.setMessage("Invalid_User");
/*  3146 */           this.requestJson.setSuccess(true);
/*       */         }
/*  3148 */         else if (header4.equals(phone.getAccessToken())) {
/*  3149 */           if (accessToken.longValue() < Long.valueOf(phone.getAccessTokenTime()).longValue()) {
/*       */             try {
/*  3151 */               BoControlEnclosure controlEnclosures = this.boControlEnclosureService.controlEnclosures(this.deviceCode, userCode2[0].trim().toString(), this.deviceAddress);
/*  3152 */               if (controlEnclosures == null) {
/*  3153 */                 this.requestJson.setData(map);
/*  3154 */                 this.requestJson.setMessage("没有找到");
/*  3155 */                 this.requestJson.setSuccess(true);
/*       */               } else {
/*  3157 */                 controlEnclosures.setNickName(this.nickName);
/*  3158 */                 this.boControlEnclosureService.update(controlEnclosures);
/*       */               }
/*       */             }
/*       */             catch (Exception e) {
/*  3162 */               logger.info("error_" + e.getMessage());
/*  3163 */               this.requestJson.setData(map);
/*  3164 */               this.requestJson.setMessage("服务器发生异常");
/*  3165 */               this.requestJson.setSuccess(false);
/*       */             }
/*       */           } else {
/*  3168 */             System.err.println("AToken时间戳超时了");
/*  3169 */             this.requestJson.setData(map);
/*  3170 */             this.requestJson.setMessage("超时了");
/*  3171 */             this.requestJson.setSuccess(false);
/*       */           }
/*       */         } else {
/*  3174 */           System.err.println("AToken超时了");
/*  3175 */           this.requestJson.setData(map);
/*  3176 */           this.requestJson.setMessage("超时了");
/*  3177 */           this.requestJson.setSuccess(false);
/*       */         }
/*       */       }
/*       */       else {
/*  3181 */         System.err.println("验证不通过");
/*       */ 
/*  3183 */         this.requestJson.setData(map);
/*  3184 */         this.requestJson.setMessage("验证不通过");
/*  3185 */         this.requestJson.setSuccess(false);
/*       */       }
/*       */     } else {
/*  3188 */       Boolean ral = isRal(header, header2, header3, header4, userCode, "修改控制盒列表里的昵称");
/*  3189 */       if (ral.booleanValue()) {
/*  3190 */         System.err.println("验证通过");
/*  3191 */         Long accessToken = Long.valueOf(header);
/*  3192 */         BoUsers boUsers = this.boUserServicess.findByUserUserCode(userCode);
/*  3193 */         if (boUsers == null) {
/*  3194 */           this.requestJson.setData(map);
/*  3195 */           this.requestJson.setMessage("Invalid_User");
/*  3196 */           this.requestJson.setSuccess(true);
/*       */         }
/*  3198 */         else if (accessToken.longValue() < Long.valueOf(boUsers.getAccessTokenTime()).longValue()) {
/*       */           try {
/*  3200 */             BoControlEnclosure controlEnclosures = this.boControlEnclosureService.controlEnclosures(this.deviceCode, userCode, this.deviceAddress);
/*  3201 */             if (controlEnclosures == null) {
/*  3202 */               this.requestJson.setData(map);
/*  3203 */               this.requestJson.setMessage("没有找到");
/*  3204 */               this.requestJson.setSuccess(true);
/*       */             } else {
/*  3206 */               controlEnclosures.setNickName(this.nickName);
/*  3207 */               this.boControlEnclosureService.update(controlEnclosures);
/*       */             }
/*       */           }
/*       */           catch (Exception e) {
/*  3211 */             logger.info("error_" + e.getMessage());
/*  3212 */             this.requestJson.setData(map);
/*  3213 */             this.requestJson.setMessage("服务器发生异常");
/*  3214 */             this.requestJson.setSuccess(false);
/*       */           }
/*       */         } else {
/*  3217 */           this.requestJson.setData(map);
/*  3218 */           this.requestJson.setMessage("超时了");
/*  3219 */           this.requestJson.setSuccess(false);
/*       */         }
/*       */       }
/*       */       else
/*       */       {
/*  3224 */         System.err.println("验证不通过");
/*       */ 
/*  3226 */         this.requestJson.setData(map);
/*  3227 */         this.requestJson.setMessage("验证不通过");
/*  3228 */         this.requestJson.setSuccess(false);
/*       */       }
/*       */     }
/*  3231 */     return "success";
/*       */   }
/*       */ 
/*       */   @Action(value="control_ControlEnclosure", results={@org.apache.struts2.convention.annotation.Result(type="json", params={"root", "requestJson"})})
/*       */   public String control_ControlEnclosure()
/*       */   {
/*  3245 */     this.requestJson = new RequestJson();
/*  3246 */     Map map = new HashMap();
/*  3247 */     HttpServletRequest request = ServletActionContext.getRequest();
/*  3248 */     String ip = request.getRemoteAddr();
/*  3249 */     String header = request.getHeader("timestamp");
/*  3250 */     String header2 = request.getHeader("nonce");
/*  3251 */     String header3 = request.getHeader("sign");
/*  3252 */     String header4 = request.getHeader("access_token");
/*  3253 */     String userCode = request.getHeader("userCode");
/*  3254 */     if (userCode.contains(",")) {
/*  3255 */       String[] userCode2 = userCode.split(",");
/*  3256 */       BoUsers boUsers = this.boUserServicess.findByUserUserCode(userCode2[0].trim().toString());
/*  3257 */       BoUsers phone = this.boUserServicess.findByUserPhone(userCode2[1].trim().toString());
/*  3258 */       Boolean ral = isRal(header, header2, header3, header4, userCode, "控制 控制盒");
/*  3259 */       if (ral.booleanValue()) {
/*  3260 */         System.err.println("验证通过");
/*  3261 */         Long accessToken = Long.valueOf(header);
/*       */ 
/*  3263 */         if ((phone == null) || (boUsers == null)) {
/*  3264 */           this.requestJson.setData(map);
/*  3265 */           this.requestJson.setMessage("Invalid_User");
/*  3266 */           this.requestJson.setSuccess(true);
/*       */         }
/*  3268 */         else if (header4.equals(phone.getAccessToken())) {
/*  3269 */           if (accessToken.longValue() < Long.valueOf(phone.getAccessTokenTime()).longValue()) {
/*       */             try {
/*  3271 */               packNum(userCode2[0].trim().toString());
/*  3272 */               String strss = "RELAY-SET-" + user_num.get(userCode2[0].trim().toString()) + "," + this.deviceAddress + "," + this.controlAction;
/*  3273 */               byte[] bss = strss.getBytes();
/*  3274 */               System.err.println(new String(bss));
/*  3275 */               this.packetProcessHelper.processSendDDatas(this.deviceCode, bss);
/*       */             }
/*       */             catch (Exception e) {
/*  3278 */               logger.info("error_" + e.getMessage());
/*  3279 */               this.requestJson.setData(map);
/*  3280 */               this.requestJson.setMessage("服务器发生异常");
/*  3281 */               this.requestJson.setSuccess(false);
/*       */             }
/*       */           } else {
/*  3284 */             System.err.println("AToken时间戳超时了");
/*  3285 */             this.requestJson.setData(map);
/*  3286 */             this.requestJson.setMessage("超时了");
/*  3287 */             this.requestJson.setSuccess(false);
/*       */           }
/*       */         } else {
/*  3290 */           System.err.println("AToken超时了");
/*  3291 */           this.requestJson.setData(map);
/*  3292 */           this.requestJson.setMessage("超时了");
/*  3293 */           this.requestJson.setSuccess(false);
/*       */         }
/*       */       }
/*       */       else {
/*  3297 */         System.err.println("验证不通过");
/*       */ 
/*  3299 */         this.requestJson.setData(map);
/*  3300 */         this.requestJson.setMessage("验证不通过");
/*  3301 */         this.requestJson.setSuccess(false);
/*       */       }
/*       */     } else {
/*  3304 */       Boolean ral = isRal(header, header2, header3, header4, userCode, "控制 控制盒");
/*  3305 */       if (ral.booleanValue()) {
/*  3306 */         System.err.println("验证通过");
/*  3307 */         Long accessToken = Long.valueOf(header);
/*  3308 */         BoUsers boUsers = this.boUserServicess.findByUserUserCode(userCode);
/*  3309 */         if (boUsers == null) {
/*  3310 */           this.requestJson.setData(map);
/*  3311 */           this.requestJson.setMessage("Invalid_User");
/*  3312 */           this.requestJson.setSuccess(true);
/*       */         }
/*  3314 */         else if (accessToken.longValue() < Long.valueOf(boUsers.getAccessTokenTime()).longValue()) {
/*       */           try {
/*  3316 */             if (user_num.get(userCode) == null)
/*  3317 */               user_num.put(userCode, Integer.valueOf(0));
/*       */             else {
/*  3319 */               user_num.put(userCode, Integer.valueOf(((Integer)user_num.get(userCode)).intValue() == 255 ? 0 : ((Integer)user_num.get(userCode)).intValue() + 1));
/*       */             }
/*  3321 */             String strss = "RELAY-SET-" + user_num.get(userCode) + "," + this.deviceAddress + "," + this.controlAction;
/*  3322 */             byte[] bss = strss.getBytes();
/*  3323 */             System.err.println(new String(bss));
/*  3324 */             this.packetProcessHelper.processSendDDatas(this.deviceCode, bss);
/*       */           }
/*       */           catch (Exception e) {
/*  3327 */             logger.info("error_" + e.getMessage());
/*  3328 */             this.requestJson.setData(map);
/*  3329 */             this.requestJson.setMessage("服务器发生异常");
/*  3330 */             this.requestJson.setSuccess(false);
/*       */           }
/*       */         } else {
/*  3333 */           this.requestJson.setData(map);
/*  3334 */           this.requestJson.setMessage("超时了");
/*  3335 */           this.requestJson.setSuccess(false);
/*       */         }
/*       */       }
/*       */       else
/*       */       {
/*  3340 */         System.err.println("验证不通过");
/*       */ 
/*  3342 */         this.requestJson.setData(map);
/*  3343 */         this.requestJson.setMessage("验证不通过");
/*  3344 */         this.requestJson.setSuccess(false);
/*       */       }
/*       */     }
/*  3347 */     return "success";
/*       */   }
/*       */ 
/*       */   @Action(value="gainControlEnclosure", results={@org.apache.struts2.convention.annotation.Result(type="json", params={"root", "requestJson"})})
/*       */   public String gainControlEnclosure()
/*       */   {
/*  3356 */     this.requestJson = new RequestJson();
/*  3357 */     Map map = new HashMap();
/*  3358 */     HttpServletRequest request = ServletActionContext.getRequest();
/*  3359 */     String ip = request.getRemoteAddr();
/*  3360 */     String header = request.getHeader("timestamp");
/*  3361 */     String header2 = request.getHeader("nonce");
/*  3362 */     String header3 = request.getHeader("sign");
/*  3363 */     String header4 = request.getHeader("access_token");
/*  3364 */     String userCode = request.getHeader("userCode");
/*  3365 */     if (userCode.contains(",")) {
/*  3366 */       String[] userCode2 = userCode.split(",");
/*  3367 */       BoUsers boUsers = this.boUserServicess.findByUserUserCode(userCode2[0].trim().toString());
/*  3368 */       BoUsers phone = this.boUserServicess.findByUserPhone(userCode2[1].trim().toString());
/*  3369 */       Boolean ral = isRal(header, header2, header3, header4, userCode, "获取控制盒列表");
/*  3370 */       if (ral.booleanValue()) {
/*  3371 */         System.err.println("验证通过");
/*  3372 */         Long accessToken = Long.valueOf(header);
/*       */ 
/*  3374 */         if ((phone == null) || (boUsers == null)) {
/*  3375 */           this.requestJson.setData(map);
/*  3376 */           this.requestJson.setMessage("Invalid_User");
/*  3377 */           this.requestJson.setSuccess(true);
/*       */         }
/*  3379 */         else if (header4.equals(phone.getAccessToken())) {
/*  3380 */           if (accessToken.longValue() < Long.valueOf(phone.getAccessTokenTime()).longValue()) {
/*       */             try {
/*  3382 */               List<BoControlEnclosure> list = this.boControlEnclosureService.controlEnclosure(this.deviceAddress, userCode2[0].trim().toString(), this.deviceAddress);
/*  3383 */               if (list.size() <= 0) {
/*  3384 */                 this.requestJson.setData(map);
/*  3385 */                 this.requestJson.setMessage("没有找到");
/*  3386 */                 this.requestJson.setSuccess(true);
/*       */               }
/*       */               else
/*       */               {
/*       */                 String strss;
/*  3388 */                 for (int i = 0; i < list.size(); i++) {
/*  3389 */                   Thread.sleep(100L);
/*  3390 */                   packNum(userCode2[0].trim().toString());
/*  3391 */                   BoControlEnclosure obj = (BoControlEnclosure)list.get(i);
/*  3392 */                   strss = "RELAY-READ-" + user_num.get(userCode2[0].trim().toString()) + "," + obj.getDeviceAddress();
/*  3393 */                   byte[] bss = strss.getBytes();
/*  3394 */                   System.err.println(new String(bss));
/*  3395 */                   this.packetProcessHelper.processSendDDatas(this.deviceAddress, bss);
/*  3396 */                   StaticUtil.READCONTROLENCLOSURE.put(this.deviceAddress + "_" + "A", new String[] { 
/*  3397 */                     userCode2[0].trim().toString(), new Date().getTime()+"" });
/*       */                 }
/*  3399 */                 if (list.size() > 0) {
/*  3400 */                   List voList = new ArrayList();
/*  3401 */                   for (BoControlEnclosure boControlEnclosure : list) {
/*  3402 */                     Map maps = new HashMap();
/*  3403 */                     maps.put("deviceCode", boControlEnclosure.getBoDevice().getDeviceCode());
/*  3404 */                     maps.put("deviceAddress", boControlEnclosure.getDeviceAddress());
/*  3405 */                     maps.put("nickName", boControlEnclosure.getNickName());
/*  3406 */                     maps.put("state", boControlEnclosure.getState());
/*  3407 */                     voList.add(maps);
/*       */                   }
/*  3409 */                   this.requestJson.setData(voList);
/*       */                 }
/*       */               }
/*       */ 
/*       */             }
/*       */             catch (Exception e)
/*       */             {
/*  3416 */               logger.info("error_" + e.getMessage());
/*  3417 */               this.requestJson.setData(map);
/*  3418 */               this.requestJson.setMessage("服务器发生异常");
/*  3419 */               this.requestJson.setSuccess(false);
/*       */             }
/*       */           } else {
/*  3422 */             System.err.println("AToken时间戳超时了");
/*  3423 */             this.requestJson.setData(map);
/*  3424 */             this.requestJson.setMessage("超时了");
/*  3425 */             this.requestJson.setSuccess(false);
/*       */           }
/*       */         } else {
/*  3428 */           System.err.println("AToken超时了");
/*  3429 */           this.requestJson.setData(map);
/*  3430 */           this.requestJson.setMessage("超时了");
/*  3431 */           this.requestJson.setSuccess(false);
/*       */         }
/*       */       }
/*       */       else {
/*  3435 */         System.err.println("验证不通过");
/*       */ 
/*  3437 */         this.requestJson.setData(map);
/*  3438 */         this.requestJson.setMessage("验证不通过");
/*  3439 */         this.requestJson.setSuccess(false);
/*       */       }
/*       */     } else {
/*  3442 */       Boolean ral = isRal(header, header2, header3, header4, userCode, "获取控制盒列表");
/*  3443 */       if (ral.booleanValue()) {
/*  3444 */         System.err.println("验证通过");
/*  3445 */         Long accessToken = Long.valueOf(header);
/*  3446 */         BoUsers boUsers = this.boUserServicess.findByUserUserCode(userCode);
/*  3447 */         if (boUsers == null) {
/*  3448 */           this.requestJson.setData(map);
/*  3449 */           this.requestJson.setMessage("Invalid_User");
/*  3450 */           this.requestJson.setSuccess(true);
/*       */         }
/*  3452 */         else if (accessToken.longValue() < Long.valueOf(boUsers.getAccessTokenTime()).longValue()) {
/*       */           try {
/*  3454 */             List<BoControlEnclosure> list = this.boControlEnclosureService.controlEnclosure(this.deviceAddress, userCode, this.deviceAddress);
/*  3455 */             if (list.size() <= 0) {
/*  3456 */               this.requestJson.setData(map);
/*  3457 */               this.requestJson.setMessage("没有找到");
/*  3458 */               this.requestJson.setSuccess(true);
/*       */             }
/*       */             else
/*       */             {
/*       */               String strss;
/*  3460 */               for (int i = 0; i < list.size(); i++) {
/*  3461 */                 Thread.sleep(100L);
/*  3462 */                 if (user_num.get(userCode) == null)
/*  3463 */                   user_num.put(userCode, Integer.valueOf(0));
/*       */                 else {
/*  3465 */                   user_num.put(userCode, Integer.valueOf(((Integer)user_num.get(userCode)).intValue() == 255 ? 0 : ((Integer)user_num.get(userCode)).intValue() + 1));
/*       */                 }
/*  3467 */                 BoControlEnclosure obj = (BoControlEnclosure)list.get(i);
/*  3468 */                 strss = "RELAY-READ-" + user_num.get(userCode) + "," + obj.getDeviceAddress();
/*  3469 */                 byte[] bss = strss.getBytes();
/*  3470 */                 System.err.println(new String(bss));
/*  3471 */                 this.packetProcessHelper.processSendDDatas(this.deviceAddress, bss);
/*  3472 */                 StaticUtil.READCONTROLENCLOSURE.put(this.deviceAddress + "_" + "A", new String[] { 
/*  3473 */                   userCode, new Date().getTime()+"" });
/*       */               }
/*  3475 */               if (list.size() > 0) {
/*  3476 */                 List voList = new ArrayList();
/*  3477 */                 for (BoControlEnclosure boControlEnclosure : list) {
/*  3478 */                   Map maps = new HashMap();
/*  3479 */                   maps.put("deviceCode", boControlEnclosure.getBoDevice().getDeviceCode());
/*  3480 */                   maps.put("deviceAddress", boControlEnclosure.getDeviceAddress());
/*  3481 */                   maps.put("nickName", boControlEnclosure.getNickName());
/*  3482 */                   maps.put("state", boControlEnclosure.getState());
/*  3483 */                   voList.add(maps);
/*       */                 }
/*  3485 */                 this.requestJson.setData(voList);
/*       */               }
/*       */             }
/*       */ 
/*       */           }
/*       */           catch (Exception e)
/*       */           {
/*  3492 */             logger.info("error_" + e.getMessage());
/*  3493 */             this.requestJson.setData(map);
/*  3494 */             this.requestJson.setMessage("服务器发生异常");
/*  3495 */             this.requestJson.setSuccess(false);
/*       */           }
/*       */         } else {
/*  3498 */           this.requestJson.setData(map);
/*  3499 */           this.requestJson.setMessage("超时了");
/*  3500 */           this.requestJson.setSuccess(false);
/*       */         }
/*       */       }
/*       */       else
/*       */       {
/*  3505 */         System.err.println("验证不通过");
/*       */ 
/*  3507 */         this.requestJson.setData(map);
/*  3508 */         this.requestJson.setMessage("验证不通过");
/*  3509 */         this.requestJson.setSuccess(false);
/*       */       }
/*       */     }
/*  3512 */     return "success";
/*       */   }
/*       */ 
/*       */   @Action(value="gainAlarmRecord", results={@org.apache.struts2.convention.annotation.Result(type="json", params={"root", "requestJson"})})
/*       */   public String gainAlarmRecord()   //推送记录 进的该方法
/*       */   {
/*  3522 */     this.requestJson = new RequestJson();
/*  3523 */     Map map = new HashMap();
/*  3524 */     HttpServletRequest request = ServletActionContext.getRequest();
/*  3525 */     String ip = request.getRemoteAddr();
/*  3526 */     String header = request.getHeader("timestamp");
/*  3527 */     String header2 = request.getHeader("nonce");
/*  3528 */     String header3 = request.getHeader("sign");
/*  3529 */     String header4 = request.getHeader("access_token");
/*  3530 */     String userCode = request.getHeader("userCode");
				logger.info("gainAlarmRecord userCode>>"+userCode);
/*       */     List voList;
/*  3531 */     if (userCode.contains(",")) {
/*  3532 */       String[] userCode2 = userCode.split(",");
/*  3533 */       BoUsers boUsers = this.boUserServicess.findByUserUserCode(userCode2[0].trim().toString());
/*  3534 */       BoUsers phone = this.boUserServicess.findByUserPhone(userCode2[1].trim().toString());
/*  3535 */       Page page = new Page();
/*  3536 */       page.setPageSize(this.pageSize.intValue());
/*  3537 */       page.setPageNum(this.pageNum.intValue());
/*       */ 
/*  3539 */       if (StringUtils.isNotEmpty(this.orderField)) {
/*  3540 */         page.setOrderField(this.orderField);
/*       */       }
/*       */ 
/*  3543 */       if (StringUtils.isNotEmpty(this.orderDirection)) {
/*  3544 */         page.setOrderDirection(this.orderDirection);
/*       */       }
/*  3546 */       Boolean ral = isRal(header, header2, header3, header4, userCode, "获取传感器报警记录");
/*  3547 */       if (ral.booleanValue()) {
/*  3548 */         System.err.println("验证通过");
/*  3549 */         Long accessToken = Long.valueOf(header);
/*       */ 
/*  3551 */         if ((phone == null) || (boUsers == null)) {
/*  3552 */           this.requestJson.setData(map);
/*  3553 */           this.requestJson.setMessage("Invalid_User");
/*  3554 */           this.requestJson.setSuccess(true);
/*       */         }
/*  3556 */         else if (header4.equals(phone.getAccessToken())) {
/*  3557 */           if (accessToken.longValue() < Long.valueOf(phone.getAccessTokenTime()).longValue()) {
/*       */             try {
/*  3559 */               List<BoAlarmRecord> list = this.boAlarmRecordService.getAlarmRecordByUserCode(userCode2[0].trim().toString(), page);
                          logger.info("gainAlarmRecord list>>"+list);//非空  -因为给17779605699加了几条条记录
/*  3560 */               if (list.size() <= 0) {
/*  3561 */                 this.requestJson.setData(map);
/*  3562 */                 this.requestJson.setMessage("没有找到报警记录");
/*  3563 */                 this.requestJson.setSuccess(false);
/*       */               } else {
/*  3565 */                 voList = new ArrayList();
/*  3566 */                 Map alarmRecordMap = null;
/*       */                 try
/*       */                 {
/*  3569 */                   for (BoAlarmRecord boAlarmRecord : list) {
/*  3570 */                     alarmRecordMap = new HashMap();
/*  3571 */                     SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
/*  3572 */                     String dateString = formatter.format(boAlarmRecord.getReportDate());
/*  3573 */                     alarmRecordMap.put("sebsorName", boAlarmRecord.getSebsorName());
/*  3574 */                     alarmRecordMap.put("reportDate", dateString);
/*  3575 */                     alarmRecordMap.put("alarmPhoneType", boAlarmRecord.getAlarmPhoneType());
/*  3576 */                     voList.add(alarmRecordMap);
/*       */                   }
/*  3578 */                   this.requestJson.setData(voList);
/*       */                 } finally {
//							  voList.remove(alarmRecordMap);//去掉后 消息推送中的普通记录无法显示   可是默认是有该操作的，为什么？
/*  3580 */                   voList.remove(map);
/*       */                 }
/*       */               }
/*       */             }
/*       */             catch (Exception e) {
/*  3585 */               logger.info("error_" + e.getMessage());
/*  3586 */               this.requestJson.setData(map);
/*  3587 */               this.requestJson.setMessage("服务器发生异常");
/*  3588 */               this.requestJson.setSuccess(false);
/*       */             }
/*       */           } else {
/*  3591 */             System.err.println("AToken时间戳超时了");
/*  3592 */             this.requestJson.setData(map);
/*  3593 */             this.requestJson.setMessage("超时了");
/*  3594 */             this.requestJson.setSuccess(false);
/*       */           }
/*       */         } else {
/*  3597 */           System.err.println("AToken超时了");
/*  3598 */           this.requestJson.setData(map);
/*  3599 */           this.requestJson.setMessage("超时了");
/*  3600 */           this.requestJson.setSuccess(false);
/*       */         }
/*       */       }
/*       */       else {
/*  3604 */         System.err.println("验证不通过");
/*       */ 
/*  3606 */         this.requestJson.setData(map);
/*  3607 */         this.requestJson.setMessage("验证不通过");
/*  3608 */         this.requestJson.setSuccess(false);
/*       */       }
/*       */     } else {
/*  3611 */       Boolean ral = isRal(header, header2, header3, header4, userCode, "获取传感器报警记录");
/*  3612 */       if (ral.booleanValue()) {
/*  3613 */         System.err.println("验证通过");
/*  3614 */         Long accessToken = Long.valueOf(header);
/*  3615 */         BoUsers boUsers = this.boUserServicess.findByUserUserCode(userCode);
/*  3616 */         if (boUsers == null) {
/*  3617 */           this.requestJson.setData(map);
/*  3618 */           this.requestJson.setMessage("Invalid_User");
/*  3619 */           this.requestJson.setSuccess(true);
/*       */         }
/*  3621 */         else if (accessToken.longValue() < Long.valueOf(boUsers.getAccessTokenTime()).longValue()) {
/*       */           try {
/*  3623 */             List<BoAlarmRecord> list = this.boAlarmRecordService.getAlarmRecordByUserCode(userCode, this.page);
						logger.info("gainAlarmRecord list 01>>"+list);
/*  3624 */             if (list.size() <= 0) {
/*  3625 */               this.requestJson.setData(map);
/*  3626 */               this.requestJson.setMessage("没有找到报警记录");
/*  3627 */               this.requestJson.setSuccess(false);
/*       */             } else {
/*  3629 */               voList = new ArrayList();
/*  3630 */               Map alarmRecordMap = null;
/*       */               try
/*       */               {
/*  3633 */                 for (BoAlarmRecord boAlarmRecord : list) {
/*  3634 */                   alarmRecordMap = new HashMap();
/*  3635 */                   SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
/*  3636 */                   String dateString = formatter.format(boAlarmRecord.getReportDate());
/*  3637 */                   alarmRecordMap.put("sebsorName", boAlarmRecord.getSebsorName());
/*  3638 */                   alarmRecordMap.put("reportDate", dateString);
/*  3639 */                   alarmRecordMap.put("alarmPhoneType", boAlarmRecord.getAlarmPhoneType());
/*  3640 */                   voList.add(alarmRecordMap);
/*       */                 }
/*  3642 */                 this.requestJson.setData(voList);
/*       */               } finally {
///*  3644 */                 voList.remove(alarmRecordMap);//去掉后 消息推送中的普通记录无法显示   可是默认是有该操作的，为什么？
							  voList.remove(map);
/*       */               }
/*       */             }
/*       */           }
/*       */           catch (Exception e) {
/*  3649 */             logger.info("error_" + e.getMessage());
/*  3650 */             this.requestJson.setData(map);
/*  3651 */             this.requestJson.setMessage("服务器发生异常");
/*  3652 */             this.requestJson.setSuccess(false);
/*       */           }
/*       */         } else {
/*  3655 */           this.requestJson.setData(map);
/*  3656 */           this.requestJson.setMessage("超时了");
/*  3657 */           this.requestJson.setSuccess(false);
/*       */         }
/*       */       }
/*       */       else
/*       */       {
/*  3662 */         System.err.println("验证不通过");
/*       */ 
/*  3664 */         this.requestJson.setData(map);
/*  3665 */         this.requestJson.setMessage("验证不通过");
/*  3666 */         this.requestJson.setSuccess(false);
/*       */       }
/*       */     }
/*  3669 */     return "success";
/*       */   }
/*       */ 
/*       */   @Action(value="modifyHostNickname", results={@org.apache.struts2.convention.annotation.Result(type="json", params={"root", "requestJson"})})
/*       */   public String modifyHostNickname()
/*       */   {
/*  3678 */     this.requestJson = new RequestJson();
/*  3679 */     Map map = new HashMap();
/*  3680 */     HttpServletRequest request = ServletActionContext.getRequest();
/*  3681 */     String ip = request.getRemoteAddr();
/*  3682 */     String header = request.getHeader("timestamp");
/*  3683 */     String header2 = request.getHeader("nonce");
/*  3684 */     String header3 = request.getHeader("sign");
/*  3685 */     String header4 = request.getHeader("access_token");
/*  3686 */     String userCode = request.getHeader("userCode");
/*  3687 */     if (userCode.contains(",")) {
/*  3688 */       String[] userCode2 = userCode.split(",");
/*  3689 */       BoUsers boUsers = this.boUserServicess.findByUserUserCode(userCode2[0].trim().toString());
/*  3690 */       BoUsers phone = this.boUserServicess.findByUserPhone(userCode2[1].trim().toString());
/*  3691 */       Boolean ral = isRal(header, header2, header3, header4, userCode, "修改主机昵称");
/*  3692 */       if (ral.booleanValue()) {
/*  3693 */         System.err.println("验证通过");
/*  3694 */         Long accessToken = Long.valueOf(header);
/*       */ 
/*  3696 */         if ((phone == null) || (boUsers == null)) {
/*  3697 */           this.requestJson.setData(map);
/*  3698 */           this.requestJson.setMessage("Invalid_User");
/*  3699 */           this.requestJson.setSuccess(true);
/*       */         }
/*  3701 */         else if (header4.equals(phone.getAccessToken())) {
/*  3702 */           if (accessToken.longValue() < Long.valueOf(phone.getAccessTokenTime()).longValue()) {
/*  3703 */             BoUserDevices userDevices = this.boUserDevicesServicess.findBy(userCode2[0].trim().toString(), this.deviceCode);
/*  3704 */             if (userDevices == null) {
/*  3705 */               this.requestJson.setData(map);
/*  3706 */               this.requestJson.setMessage("没有找到该绑定的主机");
/*  3707 */               this.requestJson.setSuccess(true);
/*       */             } else {
/*  3709 */               userDevices.setNickName(this.nickName);
/*  3710 */               BoUserDevices update = (BoUserDevices)this.boUserDevicesServicess.update(userDevices);
/*  3711 */               if (update != null) {
/*  3712 */                 this.requestJson.setData(map);
/*  3713 */                 this.requestJson.setMessage("修改成功");
/*  3714 */                 this.requestJson.setSuccess(true);
/*       */               } else {
/*  3716 */                 this.requestJson.setData(map);
/*  3717 */                 this.requestJson.setMessage("修改失败");
/*  3718 */                 this.requestJson.setSuccess(true);
/*       */               }
/*       */             }
/*       */           } else {
/*  3722 */             System.err.println("AToken时间戳超时了");
/*  3723 */             this.requestJson.setData(map);
/*  3724 */             this.requestJson.setMessage("超时了");
/*  3725 */             this.requestJson.setSuccess(false);
/*       */           }
/*       */         } else {
/*  3728 */           System.err.println("AToken超时了");
/*  3729 */           this.requestJson.setData(map);
/*  3730 */           this.requestJson.setMessage("超时了");
/*  3731 */           this.requestJson.setSuccess(false);
/*       */         }
/*       */       }
/*       */       else {
/*  3735 */         System.err.println("AToken超时了");
/*  3736 */         this.requestJson.setData(map);
/*  3737 */         this.requestJson.setMessage("验证不通过");
/*  3738 */         this.requestJson.setSuccess(false);
/*       */       }
/*       */     } else {
/*  3741 */       Boolean ral = isRal(header, header2, header3, header4, userCode, "修改主机昵称");
/*  3742 */       if (ral.booleanValue()) {
/*  3743 */         System.err.println("验证通过");
/*  3744 */         Long accessToken = Long.valueOf(header);
/*  3745 */         BoUsers boUsers = this.boUserServicess.findByUserUserCode(userCode);
/*  3746 */         if (boUsers == null) {
/*  3747 */           this.requestJson.setData(map);
/*  3748 */           this.requestJson.setMessage("Invalid_User");
/*  3749 */           this.requestJson.setSuccess(true);
/*       */         }
/*  3751 */         else if (accessToken.longValue() < Long.valueOf(boUsers.getAccessTokenTime()).longValue()) {
/*  3752 */           BoUserDevices userDevices = this.boUserDevicesServicess.findBy(userCode, this.deviceCode);
/*  3753 */           if (userDevices == null) {
/*  3754 */             this.requestJson.setData(map);
/*  3755 */             this.requestJson.setMessage("没有找到该绑定的主机");
/*  3756 */             this.requestJson.setSuccess(true);
/*       */           } else {
/*  3758 */             userDevices.setNickName(this.nickName);
/*  3759 */             BoUserDevices update = (BoUserDevices)this.boUserDevicesServicess.update(userDevices);
/*  3760 */             if (update != null) {
/*  3761 */               this.requestJson.setData(map);
/*  3762 */               this.requestJson.setMessage("修改成功");
/*  3763 */               this.requestJson.setSuccess(true);
/*       */             } else {
/*  3765 */               this.requestJson.setData(map);
/*  3766 */               this.requestJson.setMessage("修改失败");
/*  3767 */               this.requestJson.setSuccess(true);
/*       */             }
/*       */           }
/*       */         } else {
/*  3771 */           this.requestJson.setData(map);
/*  3772 */           this.requestJson.setMessage("超时了");
/*  3773 */           this.requestJson.setSuccess(false);
/*       */         }
/*       */       }
/*       */       else {
/*  3777 */         System.err.println("验证不通过");
/*       */ 
/*  3779 */         this.requestJson.setData(map);
/*  3780 */         this.requestJson.setMessage("验证不通过");
/*  3781 */         this.requestJson.setSuccess(false);
/*       */       }
/*       */     }
/*  3784 */     return "success";
/*       */   }
/*       */ 
/*       */   @Action(value="queryNetworkNumber", results={@org.apache.struts2.convention.annotation.Result(type="json", params={"root", "requestJson"})})
/*       */   public String queryNetworkNumber()
/*       */   {
/*  3793 */     this.requestJson = new RequestJson();
/*  3794 */     Map map = new HashMap();
/*  3795 */     HttpServletRequest request = ServletActionContext.getRequest();
/*  3796 */     String header = request.getHeader("timestamp");
/*  3797 */     String header2 = request.getHeader("nonce");
/*  3798 */     String header3 = request.getHeader("sign");
/*  3799 */     String header4 = request.getHeader("access_token");
/*  3800 */     String userCode = request.getHeader("userCode");
/*  3801 */     if (userCode.contains(",")) {
/*  3802 */       String[] userCode2 = userCode.split(",");
/*  3803 */       BoUsers boUsers = this.boUserServicess.findByUserUserCode(userCode2[0].trim().toString());
/*  3804 */       BoUsers phone = this.boUserServicess.findByUserPhone(userCode2[1].trim().toString());
/*  3805 */       Boolean ral = isRal(header, header2, header3, header4, userCode, "  查询主机网络号和信道接口");
/*  3806 */       if (ral.booleanValue()) {
/*  3807 */         System.err.println("验证通过");
/*  3808 */         Long accessToken = Long.valueOf(header);
/*       */ 
/*  3810 */         if ((phone == null) || (boUsers == null)) {
/*  3811 */           this.requestJson.setData(map);
/*  3812 */           this.requestJson.setMessage("Invalid_User");
/*  3813 */           this.requestJson.setSuccess(true);
/*       */         }
/*  3815 */         else if (header4.equals(phone.getAccessToken())) {
/*  3816 */           if (accessToken.longValue() < Long.valueOf(phone.getAccessTokenTime()).longValue()) {
/*  3817 */             System.err.println("没有超时了");
/*       */             try {
/*  3819 */               if (this.deviceOrHost.equals("1"))
/*       */               {
/*  3821 */                 String networkStr = "ZIGBEE_CONFIG2-READ-OI";
/*  3822 */                 byte[] networkBs = networkStr.getBytes();
/*  3823 */                 System.err.println(new String(networkBs));
/*  3824 */                 this.packetProcessHelper.processSendDDatas(this.deviceCode, networkBs);
/*       */                 try
/*       */                 {
/*  3827 */                   Thread.sleep(1000L);
/*  3828 */                   String channelStr = "ZIGBEE_CONFIG2-READ-CH";
/*  3829 */                   byte[] channelBs = channelStr.getBytes();
/*  3830 */                   System.err.println(new String(channelBs));
/*  3831 */                   this.packetProcessHelper.processSendDDatas(this.deviceCode, channelBs);
/*  3832 */                   Thread.sleep(1000L);
/*  3833 */                   Object network = StaticUtil.NETWORK.get(this.deviceCode);
/*  3834 */                   Object channel = StaticUtil.CHANNEL.get(this.deviceCode);
/*  3835 */                   Object networkNum = "";
/*  3836 */                   String channelNum = "";
/*  3837 */                   if ((network != null) && (channel != null)) {
/*  3838 */                     networkNum = network;
/*  3839 */                     channelNum = (String) channel;
/*  3840 */                     map.put("networkNumber", networkNum);
/*  3841 */                     map.put("channel", channelNum);
/*  3842 */                     this.requestJson.setData(map);
/*  3843 */                     this.requestJson.setSuccess(true);
/*       */                   } else {
/*  3845 */                     this.requestJson.setData(map);
/*  3846 */                     this.requestJson.setMessage("获取失败");
/*  3847 */                     this.requestJson.setSuccess(true);
/*       */                   }
/*       */                 }
/*       */                 catch (InterruptedException e) {
/*  3851 */                   e.printStackTrace();
/*       */                 }
/*       */               }
/*       */             }
/*       */             finally {
/*  3856 */               StaticUtil.NETWORK.remove(this.deviceCode);
/*  3857 */               StaticUtil.CHANNEL.remove(this.deviceCode);
/*       */             }
/*       */           } else {
/*  3860 */             System.err.println("AToken时间戳超时了");
/*  3861 */             this.requestJson.setMessage("超时了");
/*  3862 */             this.requestJson.setSuccess(false);
/*       */           }
/*       */         } else {
/*  3865 */           System.err.println("AToken超时了");
/*  3866 */           this.requestJson.setMessage("超时了");
/*  3867 */           this.requestJson.setSuccess(false);
/*       */         }
/*       */       }
/*       */       else
/*       */       {
/*  3872 */         System.err.println("验证不通过");
/*  3873 */         this.requestJson.setData(map);
/*  3874 */         this.requestJson.setMessage("验证不通过");
/*  3875 */         this.requestJson.setSuccess(false);
/*       */       }
/*       */     } else {
/*  3878 */       Boolean ral = isRal(header, header2, header3, header4, userCode, "  查询主机网络号和信道接口");
/*  3879 */       if (ral.booleanValue()) {
/*  3880 */         System.err.println("验证通过");
/*  3881 */         Long accessToken = Long.valueOf(header);
/*  3882 */         BoUsers boUsers = this.boUserServicess.findByUserUserCode(userCode);
/*  3883 */         if (boUsers == null) {
/*  3884 */           this.requestJson.setData(map);
/*  3885 */           this.requestJson.setMessage("Invalid_User");
/*  3886 */           this.requestJson.setSuccess(true);
/*       */         }
/*  3888 */         else if (accessToken.longValue() < Long.valueOf(boUsers.getAccessTokenTime()).longValue()) {
/*       */           try {
/*  3890 */             if (this.deviceOrHost.equals("1"))
/*       */             {
/*  3892 */               String networkStr = "ZIGBEE_CONFIG2-READ-OI";
/*  3893 */               byte[] networkBs = networkStr.getBytes();
/*  3894 */               System.err.println(new String(networkBs));
/*  3895 */               this.packetProcessHelper.processSendDDatas(this.deviceCode, networkBs);
/*  3896 */               this.packetProcessHelper.setUserCode(userCode);
/*       */               try
/*       */               {
/*  3899 */                 Thread.sleep(1000L);
/*  3900 */                 String channelStr = "ZIGBEE_CONFIG2-READ-CH";
/*  3901 */                 byte[] channelBs = channelStr.getBytes();
/*  3902 */                 System.err.println(new String(channelBs));
/*  3903 */                 this.packetProcessHelper.processSendDDatas(this.deviceCode, channelBs);
/*  3904 */                 this.packetProcessHelper.setUserCode(userCode);
/*  3905 */                 Thread.sleep(1000L);
/*  3906 */                 Object network = StaticUtil.NETWORK.get(this.deviceCode);
/*  3907 */                 Object channel = StaticUtil.CHANNEL.get(this.deviceCode);
/*  3908 */                 String networkNum = "";
/*  3909 */                 String channelNum = "";
/*  3910 */                 if ((network != null) && (channel != null)) {
/*  3911 */                   networkNum = (String) network;
/*  3912 */                   channelNum = (String) channel;
/*  3913 */                   map.put("networkNumber", networkNum);
/*  3914 */                   map.put("channel", channelNum);
/*  3915 */                   this.requestJson.setData(map);
/*  3916 */                   this.requestJson.setSuccess(true);
/*       */                 } else {
/*  3918 */                   this.requestJson.setData(map);
/*  3919 */                   this.requestJson.setMessage("获取失败");
/*  3920 */                   this.requestJson.setSuccess(true);
/*       */                 }
/*       */               }
/*       */               catch (InterruptedException e) {
/*  3924 */                 e.printStackTrace();
/*       */               }
/*       */             }
/*       */           }
/*       */           finally {
/*  3929 */             StaticUtil.NETWORK.remove(this.deviceCode);
/*  3930 */             StaticUtil.CHANNEL.remove(this.deviceCode);
/*       */           }
/*       */         } else {
/*  3933 */           this.requestJson.setMessage("超时了");
/*  3934 */           this.requestJson.setSuccess(false);
/*       */         }
/*       */       }
/*       */       else
/*       */       {
/*  3939 */         System.err.println("验证不通过");
/*  3940 */         this.requestJson.setData(map);
/*  3941 */         this.requestJson.setMessage("验证不通过");
/*  3942 */         this.requestJson.setSuccess(false);
/*       */       }
/*       */     }
/*  3945 */     return "success";
/*       */   }
/*       */ 
/*       */   @Action(value="config", results={@org.apache.struts2.convention.annotation.Result(type="json", params={"root", "requestJson"})})
/*       */   public String config()
/*       */   {
/*  3954 */     this.requestJson = new RequestJson();
/*  3955 */     Map map = new HashMap();
/*  3956 */     HttpServletRequest request = ServletActionContext.getRequest();
/*  3957 */     String header = request.getHeader("timestamp");
/*  3958 */     String header2 = request.getHeader("nonce");
/*  3959 */     String header3 = request.getHeader("sign");
/*  3960 */     String header4 = request.getHeader("access_token");
/*  3961 */     String userCode = request.getHeader("userCode");
/*  3962 */     if (userCode.contains(",")) {
/*  3963 */       String[] userCode2 = userCode.split(",");
/*  3964 */       BoUsers boUsers = this.boUserServicess.findByUserUserCode(userCode2[0].trim().toString());
/*  3965 */       BoUsers phone = this.boUserServicess.findByUserPhone(userCode2[1].trim().toString());
/*  3966 */       Boolean ral = isRal(header, header2, header3, header4, userCode, "  终端配置");
/*  3967 */       if (ral.booleanValue()) {
/*  3968 */         System.err.println("验证通过");
/*  3969 */         Long accessToken = Long.valueOf(header);
/*       */ 
/*  3971 */         if ((phone == null) || (boUsers == null)) {
/*  3972 */           this.requestJson.setData(map);
/*  3973 */           this.requestJson.setMessage("Invalid_User");
/*  3974 */           this.requestJson.setSuccess(true);
/*       */         }
/*  3976 */         else if (header4.equals(phone.getAccessToken())) {
/*  3977 */           if (accessToken.longValue() < Long.valueOf(phone.getAccessTokenTime()).longValue()) {
/*  3978 */             String networkStr = "ZIGBEE_CONFIG2-SEND-OI,4096";
/*  3979 */             byte[] networkBs = networkStr.getBytes();
/*  3980 */             System.err.println(new String(networkBs));
/*  3981 */             this.packetProcessHelper.processSendDDatas(this.deviceCode, networkBs);
/*       */             try
/*       */             {
/*  3985 */               Thread.sleep(1000L);
/*  3986 */               String channelStr = "ZIGBEE_CONFIG2-SEND-CH,25";
/*  3987 */               byte[] channelBs = channelStr.getBytes();
/*  3988 */               System.err.println(new String(channelBs));
/*  3989 */               this.packetProcessHelper.processSendDDatas(this.deviceCode, channelBs);
/*       */             }
/*       */             catch (InterruptedException e) {
/*  3992 */               e.printStackTrace();
/*       */             }
/*       */           }
/*       */           else {
/*  3996 */             System.err.println("AToken时间戳超时了");
/*  3997 */             this.requestJson.setData(map);
/*  3998 */             this.requestJson.setMessage("超时了");
/*  3999 */             this.requestJson.setSuccess(false);
/*       */           }
/*       */         } else {
/*  4002 */           System.err.println("AToken超时了");
/*  4003 */           this.requestJson.setData(map);
/*  4004 */           this.requestJson.setMessage("超时了");
/*  4005 */           this.requestJson.setSuccess(false);
/*       */         }
/*       */       }
/*       */       else
/*       */       {
/*  4010 */         this.requestJson.setData(map);
/*  4011 */         this.requestJson.setMessage("验证不通过");
/*  4012 */         this.requestJson.setSuccess(false);
/*       */       }
/*       */     } else {
/*  4015 */       Boolean ral = isRal(header, header2, header3, header4, userCode, "  终端配置");
/*  4016 */       if (ral.booleanValue()) {
/*  4017 */         System.err.println("验证通过");
/*  4018 */         Long accessToken = Long.valueOf(header);
/*  4019 */         BoUsers boUsers = this.boUserServicess.findByUserUserCode(userCode);
/*  4020 */         if (boUsers == null) {
/*  4021 */           this.requestJson.setData(map);
/*  4022 */           this.requestJson.setMessage("Invalid_User");
/*  4023 */           this.requestJson.setSuccess(true);
/*       */         }
/*  4025 */         else if (accessToken.longValue() < Long.valueOf(boUsers.getAccessTokenTime()).longValue()) {
/*  4026 */           String networkStr = "ZIGBEE_CONFIG2-SEND-OI,4096";
/*  4027 */           byte[] networkBs = networkStr.getBytes();
/*  4028 */           System.err.println(new String(networkBs));
/*  4029 */           this.packetProcessHelper.processSendDDatas(this.deviceCode, networkBs);
/*       */ 
/*  4031 */           this.packetProcessHelper.setUserCode(userCode);
/*       */           try
/*       */           {
/*  4035 */             Thread.sleep(1000L);
/*  4036 */             String channelStr = "ZIGBEE_CONFIG2-SEND-CH,25";
/*  4037 */             byte[] channelBs = channelStr.getBytes();
/*  4038 */             System.err.println(new String(channelBs));
/*  4039 */             this.packetProcessHelper.processSendDDatas(this.deviceCode, channelBs);
/*  4040 */             this.packetProcessHelper.setUserCode(userCode);
/*       */           }
/*       */           catch (InterruptedException e) {
/*  4043 */             e.printStackTrace();
/*       */           }
/*       */         }
/*       */         else {
/*  4047 */           this.requestJson.setData(map);
/*  4048 */           this.requestJson.setMessage("超时了");
/*  4049 */           this.requestJson.setSuccess(false);
/*       */         }
/*       */       }
/*       */       else
/*       */       {
/*  4054 */         this.requestJson.setData(map);
/*  4055 */         this.requestJson.setMessage("验证不通过");
/*  4056 */         this.requestJson.setSuccess(false);
/*       */       }
/*       */     }
/*  4059 */     return "success";
/*       */   }
/*       */ 
/*       */   @Action(value="setNetworkNumber", results={@org.apache.struts2.convention.annotation.Result(type="json", params={"root", "requestJson"})})
/*       */   public String setHostNetworkNumber()
/*       */   {
/*  4068 */     this.requestJson = new RequestJson();
/*  4069 */     HttpServletRequest request = ServletActionContext.getRequest();
/*  4070 */     Map map = new HashMap();
/*  4071 */     String header = request.getHeader("timestamp");
/*  4072 */     String header2 = request.getHeader("nonce");
/*  4073 */     String header3 = request.getHeader("sign");
/*  4074 */     String header4 = request.getHeader("access_token");
/*  4075 */     String userCode = request.getHeader("userCode");
/*  4076 */     if (userCode.contains(",")) {
/*  4077 */       String[] userCode2 = userCode.split(",");
/*  4078 */       BoUsers boUsers = this.boUserServicess.findByUserUserCode(userCode2[0].trim().toString());
/*  4079 */       BoUsers phone = this.boUserServicess.findByUserPhone(userCode2[1].trim().toString());
/*  4080 */       Boolean ral = isRal(header, header2, header3, header4, userCode, " 写入网络号和信道");
/*  4081 */       if (ral.booleanValue()) {
/*  4082 */         System.err.println("验证通过");
/*  4083 */         Long accessToken = Long.valueOf(header);
/*       */ 
/*  4085 */         if ((phone == null) || (boUsers == null)) {
/*  4086 */           this.requestJson.setData(map);
/*  4087 */           this.requestJson.setMessage("Invalid_User");
/*  4088 */           this.requestJson.setSuccess(true);
/*       */         }
/*  4090 */         else if (header4.equals(phone.getAccessToken())) {
/*  4091 */           if (accessToken.longValue() < Long.valueOf(phone.getAccessTokenTime()).longValue()) {
/*  4092 */             if (this.deviceOrHost.equals("1")) {
/*  4093 */               String networkStr = "ZIGBEE_CONFIG2-SEND-OI," + this.networkNumber;
/*  4094 */               byte[] networkBs = networkStr.getBytes();
/*  4095 */               System.err.println(new String(networkBs));
/*  4096 */               this.packetProcessHelper.processSendDDatas(this.deviceCode, networkBs);
/*       */               try
/*       */               {
/*  4099 */                 Thread.sleep(200L);
/*  4100 */                 String channelStr = "ZIGBEE_CONFIG2-SEND-CH," + this.channel;
/*  4101 */                 byte[] channelBs = channelStr.getBytes();
/*  4102 */                 System.err.println(new String(channelBs));
/*  4103 */                 this.packetProcessHelper.processSendDDatas(this.deviceCode, channelBs);
/*       */               }
/*       */               catch (InterruptedException e) {
/*  4106 */                 e.printStackTrace();
/*       */               }
/*       */             }
/*       */             else {
/*  4110 */               String networkStr = "ZIGBEE_CONFIG2-SEND-CF," + this.networkNumber;
/*  4111 */               byte[] networkBs = networkStr.getBytes();
/*  4112 */               System.err.println(new String(networkBs));
/*  4113 */               this.packetProcessHelper.processSendDDatas(this.deviceCode, networkBs);
/*       */               try
/*       */               {
/*  4116 */                 Thread.sleep(200L);
/*  4117 */                 String channelStr = "ZIGBEE_CONFIG2-SEND-CN," + this.channel;
/*  4118 */                 byte[] channelBs = channelStr.getBytes();
/*  4119 */                 System.err.println(new String(channelBs));
/*  4120 */                 this.packetProcessHelper.processSendDDatas(this.deviceCode, channelBs);
/*       */               }
/*       */               catch (InterruptedException e) {
/*  4123 */                 e.printStackTrace();
/*       */               }
/*       */             }
/*       */           } else {
/*  4127 */             System.err.println("AToken时间戳超时了");
/*  4128 */             this.requestJson.setData(map);
/*  4129 */             this.requestJson.setMessage("超时了");
/*  4130 */             this.requestJson.setSuccess(false);
/*       */           }
/*       */         } else {
/*  4133 */           System.err.println("AToken超时了");
/*  4134 */           this.requestJson.setData(map);
/*  4135 */           this.requestJson.setMessage("超时了");
/*  4136 */           this.requestJson.setSuccess(false);
/*       */         }
/*       */       }
/*       */       else
/*       */       {
/*  4141 */         System.err.println("验证不通过");
/*  4142 */         this.requestJson.setData(map);
/*  4143 */         this.requestJson.setMessage("验证不通过");
/*  4144 */         this.requestJson.setSuccess(false);
/*       */       }
/*       */     } else {
/*  4147 */       Boolean ral = isRal(header, header2, header3, header4, userCode, " 写入网络号和信道");
/*  4148 */       if (ral.booleanValue()) {
/*  4149 */         System.err.println("验证通过");
/*  4150 */         Long accessToken = Long.valueOf(header);
/*  4151 */         BoUsers boUsers = this.boUserServicess.findByUserUserCode(userCode);
/*  4152 */         if (boUsers == null) {
/*  4153 */           this.requestJson.setData(map);
/*  4154 */           this.requestJson.setMessage("Invalid_User");
/*  4155 */           this.requestJson.setSuccess(true);
/*       */         }
/*  4157 */         else if (accessToken.longValue() < Long.valueOf(boUsers.getAccessTokenTime()).longValue()) {
/*  4158 */           if (this.deviceOrHost.equals("1")) {
/*  4159 */             String networkStr = "ZIGBEE_CONFIG2-SEND-OI," + this.networkNumber;
/*  4160 */             byte[] networkBs = networkStr.getBytes();
/*  4161 */             System.err.println(new String(networkBs));
/*  4162 */             this.packetProcessHelper.processSendDDatas(this.deviceCode, networkBs);
/*  4163 */             this.packetProcessHelper.setUserCode(userCode);
/*       */             try
/*       */             {
/*  4166 */               Thread.sleep(200L);
/*  4167 */               String channelStr = "ZIGBEE_CONFIG2-SEND-CH," + this.channel;
/*  4168 */               byte[] channelBs = channelStr.getBytes();
/*  4169 */               System.err.println(new String(channelBs));
/*  4170 */               this.packetProcessHelper.processSendDDatas(this.deviceCode, channelBs);
/*       */             }
/*       */             catch (InterruptedException e) {
/*  4173 */               e.printStackTrace();
/*       */             }
/*       */           }
/*       */           else {
/*  4177 */             String networkStr = "ZIGBEE_CONFIG2-SEND-CF," + this.networkNumber;
/*  4178 */             byte[] networkBs = networkStr.getBytes();
/*  4179 */             System.err.println(new String(networkBs));
/*  4180 */             this.packetProcessHelper.processSendDDatas(this.deviceCode, networkBs);
/*       */             try
/*       */             {
/*  4183 */               Thread.sleep(200L);
/*  4184 */               String channelStr = "ZIGBEE_CONFIG2-SEND-CN," + this.channel;
/*  4185 */               byte[] channelBs = channelStr.getBytes();
/*  4186 */               System.err.println(new String(channelBs));
/*  4187 */               this.packetProcessHelper.processSendDDatas(this.deviceCode, channelBs);
/*       */             }
/*       */             catch (InterruptedException e) {
/*  4190 */               e.printStackTrace();
/*       */             }
/*       */           }
/*       */         } else {
/*  4194 */           this.requestJson.setData(map);
/*  4195 */           this.requestJson.setMessage("超时了");
/*  4196 */           this.requestJson.setSuccess(false);
/*       */         }
/*       */       }
/*       */       else
/*       */       {
/*  4201 */         System.err.println("验证不通过");
/*  4202 */         this.requestJson.setData(map);
/*  4203 */         this.requestJson.setMessage("验证不通过");
/*  4204 */         this.requestJson.setSuccess(false);
/*       */       }
/*       */     }
/*  4207 */     return "success";
/*       */   }
/*       */ 
/*       */   @Action(value="adjustInfraredLocation", results={@org.apache.struts2.convention.annotation.Result(type="json", params={"root", "requestJson"})})
/*       */   public String adjustInfraredLocation()
/*       */   {
/*  4216 */     this.requestJson = new RequestJson();
/*  4217 */     Map map = new HashMap();
/*  4218 */     HttpServletRequest request = ServletActionContext.getRequest();
/*  4219 */     String header = request.getHeader("timestamp");
/*  4220 */     String header2 = request.getHeader("nonce");
/*  4221 */     String header3 = request.getHeader("sign");
/*  4222 */     String header4 = request.getHeader("access_token");
/*  4223 */     String userCode = request.getHeader("userCode");
/*       */     List<BoInfraredButtons> list;
/*  4224 */     if (userCode.contains(",")) {
/*  4225 */       String[] userCode2 = userCode.split(",");
/*  4226 */       BoUsers boUsers = this.boUserServicess.findByUserUserCode(userCode2[0].trim().toString());
/*  4227 */       BoUsers phone = this.boUserServicess.findByUserPhone(userCode2[1].trim().toString());
/*  4228 */       Boolean ral = isRal(header, header2, header3, header4, userCode, "调整红外模板位置");
/*  4229 */       if (ral.booleanValue()) {
/*  4230 */         System.err.println("验证通过");
/*  4231 */         Long accessToken = Long.valueOf(header);
/*       */ 
/*  4233 */         if (boUsers == null) {
/*  4234 */           this.requestJson.setData(map);
/*  4235 */           this.requestJson.setMessage("Invalid_User");
/*  4236 */           this.requestJson.setSuccess(true);
/*       */         }
/*  4238 */         else if (header4.equals(phone.getAccessToken())) {
/*  4239 */           if (accessToken.longValue() < Long.valueOf(phone.getAccessTokenTime()).longValue()) {
/*  4240 */             BoHostDevice hostDevice = this.boHostDeviceService.findBydeviceAddress(userCode2[0].trim().toString(), this.deviceCode, this.deviceAddress);
/*  4241 */             list = this.boInfraredButtonsService.getListBy(userCode2[0].trim().toString(), hostDevice.getBoDevice().getDeviceCode(), this.deviceAddress);
/*  4242 */             for (BoInfraredButtons boInfraredButtons : list) {
/*  4243 */               boInfraredButtons.setTemplate(this.classesInfo);
/*  4244 */               BoInfraredButtons update = (BoInfraredButtons)this.boInfraredButtonsService.update(boInfraredButtons);
/*  4245 */               if (update != null) {
/*  4246 */                 this.requestJson.setData(map);
/*  4247 */                 this.requestJson.setMessage("调整成功");
/*  4248 */                 this.requestJson.setSuccess(true);
/*       */               } else {
/*  4250 */                 this.requestJson.setData(map);
/*  4251 */                 this.requestJson.setMessage("调整失败");
/*  4252 */                 this.requestJson.setSuccess(true);
/*       */               }
/*       */             }
/*       */           }
/*       */           else {
/*  4257 */             System.err.println("AToken时间戳超时了");
/*  4258 */             this.requestJson.setData(map);
/*  4259 */             this.requestJson.setMessage("超时了");
/*  4260 */             this.requestJson.setSuccess(false);
/*       */           }
/*       */         } else {
/*  4263 */           System.err.println("AToken超时了");
/*  4264 */           this.requestJson.setData(map);
/*  4265 */           this.requestJson.setMessage("超时了");
/*  4266 */           this.requestJson.setSuccess(false);
/*       */         }
/*       */       }
/*       */       else {
/*  4270 */         System.err.println("验证不通过");
/*  4271 */         this.requestJson.setData(map);
/*  4272 */         this.requestJson.setMessage("验证不通过");
/*  4273 */         this.requestJson.setSuccess(false);
/*       */       }
/*       */     } else {
/*  4276 */       Boolean ral = isRal(header, header2, header3, header4, userCode, "调整红外模板位置");
/*  4277 */       if (ral.booleanValue()) {
/*  4278 */         System.err.println("验证通过");
/*  4279 */         Long accessToken = Long.valueOf(header);
/*  4280 */         BoUsers boUsers = this.boUserServicess.findByUserUserCode(userCode);
/*  4281 */         if (boUsers == null) {
/*  4282 */           this.requestJson.setData(map);
/*  4283 */           this.requestJson.setMessage("Invalid_User");
/*  4284 */           this.requestJson.setSuccess(true);
/*       */         }
/*  4286 */         else if (accessToken.longValue() < Long.valueOf(boUsers.getAccessTokenTime()).longValue()) {
/*  4287 */           BoHostDevice hostDevice = this.boHostDeviceService.findBydeviceAddress(userCode, this.deviceCode, this.deviceAddress);
/*  4288 */           list = this.boInfraredButtonsService.getListBy(userCode, hostDevice.getBoDevice().getDeviceCode(), this.deviceAddress);
/*  4289 */           for (BoInfraredButtons boInfraredButtons : list) {
/*  4290 */             boInfraredButtons.setTemplate(this.classesInfo);
/*  4291 */             BoInfraredButtons update = (BoInfraredButtons)this.boInfraredButtonsService.update(boInfraredButtons);
/*  4292 */             if (update != null) {
/*  4293 */               this.requestJson.setData(map);
/*  4294 */               this.requestJson.setMessage("调整成功");
/*  4295 */               this.requestJson.setSuccess(true);
/*       */             } else {
/*  4297 */               this.requestJson.setData(map);
/*  4298 */               this.requestJson.setMessage("调整失败");
/*  4299 */               this.requestJson.setSuccess(true);
/*       */             }
/*       */           }
/*       */         }
/*       */         else {
/*  4304 */           this.requestJson.setData(map);
/*  4305 */           this.requestJson.setMessage("超时了");
/*  4306 */           this.requestJson.setSuccess(false);
/*       */         }
/*       */       }
/*       */       else {
/*  4310 */         System.err.println("验证不通过");
/*  4311 */         this.requestJson.setData(map);
/*  4312 */         this.requestJson.setMessage("验证不通过");
/*  4313 */         this.requestJson.setSuccess(false);
/*       */       }
/*       */     }
/*  4316 */     return "success";
/*       */   }
/*       */ 
/*       */   @Action(value="gaininfraredvalue", results={@org.apache.struts2.convention.annotation.Result(type="json", params={"root", "requestJson"})})
/*       */   public String gainInfraredValue()
/*       */   { //红外模块
/*  4326 */     this.requestJson = new RequestJson();
/*  4327 */     Map maps = new HashMap();
/*  4328 */     HttpServletRequest request = ServletActionContext.getRequest();
/*  4329 */     String header = request.getHeader("timestamp");
/*  4330 */     String header2 = request.getHeader("nonce");
/*  4331 */     String header3 = request.getHeader("sign");
/*  4332 */     String header4 = request.getHeader("access_token");
/*  4333 */     String userCode = request.getHeader("userCode");
/*       */     Set set;
                logger.info("gain红外参数 userCode>"+userCode);
/*  4334 */     if (userCode.contains(",")) {
/*  4335 */       String[] userCode2 = userCode.split(",");
/*  4336 */       BoUsers boUsers = this.boUserServicess.findByUserUserCode(userCode2[0].trim().toString());
/*  4337 */       BoUsers phone = this.boUserServicess.findByUserPhone(userCode2[1].trim().toString());
/*  4338 */       Boolean ral = isRal(header, header2, header3, header4, userCode, "获取红外模板按键");
/*  4339 */       if (ral.booleanValue()) {
/*  4340 */         System.err.println("验证通过");
/*  4341 */         Long accessToken = Long.valueOf(header);
/*       */ 
/*  4343 */         if ((phone == null) || (boUsers == null)) {
/*  4344 */           this.requestJson.setData(maps);
/*  4345 */           this.requestJson.setMessage("Invalid_User");
/*  4346 */           this.requestJson.setSuccess(true);
/*       */         }
/*  4348 */         else if (header4.equals(phone.getAccessToken())) {
/*  4349 */           if (accessToken.longValue() < Long.valueOf(phone.getAccessTokenTime()).longValue())
/*       */           {
/*  4351 */             List<BoInfraredButtons> list = this.boInfraredButtonsService.getListBy(userCode2[0].trim().toString(), this.deviceCode, this.deviceAddress);
/*  4352 */             Map Vmaps = new HashMap();
/*  4353 */             if (list.size() <= 0) {
/*  4354 */               this.requestJson.setData(maps);
/*  4355 */               this.requestJson.setMessage("没有");
/*  4356 */               this.requestJson.setSuccess(true);
/*       */             } else {
/*  4358 */               Map map = new HashMap();
/*  4359 */               set = new HashSet();
/*  4360 */               for (BoInfraredButtons boInfraredButtons : list)
/*       */               {
/*  4363 */                 set.add(boInfraredButtons.getTemplate());
/*  4364 */                 map.put(boInfraredButtons.getInfraredButtonsValuess().toString(), boInfraredButtons.getInfraredButtonsName());
/*  4365 */                 Vmaps.put("shunxu", set);
/*       */               }
/*       */ 
/*  4369 */               Vmaps.put("button-value", map);
/*       */ 
/*  4372 */               this.requestJson.setData(Vmaps);
/*       */             }
/*       */           } else {
/*  4375 */             System.err.println("AToken时间戳超时了");
/*  4376 */             this.requestJson.setMessage("超时了");
/*  4377 */             this.requestJson.setSuccess(false);
/*       */           }
/*       */         } else {
/*  4380 */           System.err.println("AToken超时了");
/*  4381 */           this.requestJson.setMessage("超时了");
/*  4382 */           this.requestJson.setSuccess(false);
/*       */         }
/*       */       }
/*       */       else {
/*  4386 */         System.err.println("验证不通过");
/*  4387 */         this.requestJson.setMessage("验证不通过");
/*  4388 */         this.requestJson.setSuccess(false);
/*       */       }
/*       */     } else {
/*  4391 */       Boolean ral = isRal(header, header2, header3, header4, userCode, "获取红外模板按键");
/*  4392 */       if (ral.booleanValue()) {
/*  4393 */         System.err.println("验证通过");
/*  4394 */         Long accessToken = Long.valueOf(header);
/*  4395 */         BoUsers boUsers = this.boUserServicess.findByUserUserCode(userCode);
/*  4396 */         if (boUsers == null) {
/*  4397 */           this.requestJson.setData(maps);
/*  4398 */           this.requestJson.setMessage("Invalid_User");
/*  4399 */           this.requestJson.setSuccess(true);
/*       */         }
/*  4401 */         else if (accessToken.longValue() < Long.valueOf(boUsers.getAccessTokenTime()).longValue())
/*       */         {
/*  4403 */           List<BoInfraredButtons> list = this.boInfraredButtonsService.getListBy(userCode, this.deviceCode, this.deviceAddress);
/*  4404 */           Map Vmaps = new HashMap();
/*  4405 */           if (list.size() <= 0) {
/*  4406 */             this.requestJson.setData(maps);
/*  4407 */             this.requestJson.setMessage("没有");
/*  4408 */             this.requestJson.setSuccess(true);
/*       */           } else {
/*  4410 */             Map map = new HashMap();
/*  4411 */             set = new HashSet();
/*  4412 */             for (BoInfraredButtons boInfraredButtons : list)
/*       */             {
/*  4415 */               set.add(boInfraredButtons.getTemplate());
/*  4416 */               map.put(boInfraredButtons.getInfraredButtonsValuess().toString(), boInfraredButtons.getInfraredButtonsName());
/*  4417 */               Vmaps.put("shunxu", set);
/*       */             }
/*       */ 
/*  4421 */             Vmaps.put("button-value", map);
/*       */ 
/*  4424 */             this.requestJson.setData(Vmaps);
/*       */           }
/*       */         }
/*       */         else {
/*  4428 */           this.requestJson.setMessage("超时了");
/*  4429 */           this.requestJson.setSuccess(false);
/*       */         }
/*       */       }
/*       */       else
/*       */       {
/*  4434 */         System.err.println("验证不通过");
/*  4435 */         this.requestJson.setMessage("验证不通过");
/*  4436 */         this.requestJson.setSuccess(false);
/*       */       }
/*       */     }
/*  4439 */     return "success";
/*       */   }
/*       */ 
/*       */   @Action(value="deleteButton", results={@org.apache.struts2.convention.annotation.Result(type="json", params={"root", "requestJson"})})
/*       */   public String deleteButton()
/*       */   {
/*  4448 */     this.requestJson = new RequestJson();
/*  4449 */     Map map = new HashMap();
/*  4450 */     HttpServletRequest request = ServletActionContext.getRequest();
/*  4451 */     String header = request.getHeader("timestamp");
/*  4452 */     String header2 = request.getHeader("nonce");
/*  4453 */     String header3 = request.getHeader("sign");
/*  4454 */     String header4 = request.getHeader("access_token");
/*  4455 */     String userCode = request.getHeader("userCode");
/*       */     int indexFromArr;
/*       */     List<BoInfraredButtons> lists;
/*  4456 */     if (userCode.contains(",")) {
/*  4457 */       String[] userCode2 = userCode.split(",");
/*  4458 */       BoUsers boUsers = this.boUserServicess.findByUserUserCode(userCode2[0].trim().toString());
/*  4459 */       BoUsers phone = this.boUserServicess.findByUserPhone(userCode2[1].trim().toString());
/*  4460 */       Boolean ral = isRal(header, header2, header3, header4, userCode, "删除红外模板");
/*  4461 */       if (ral.booleanValue()) {
/*  4462 */         System.err.println("验证通过");
/*  4463 */         Long accessToken = Long.valueOf(header);
/*       */ 
/*  4465 */         if ((phone == null) || (boUsers == null)) {
/*  4466 */           this.requestJson.setData(map);
/*  4467 */           this.requestJson.setMessage("Invalid_User");
/*  4468 */           this.requestJson.setSuccess(true);
/*       */         }
/*  4470 */         else if (header4.equals(phone.getAccessToken())) {
/*  4471 */           if (accessToken.longValue() < Long.valueOf(phone.getAccessTokenTime()).longValue()) {
/*  4472 */             String classesInfo1 = this.classesInfo.substring(0, 1);
/*  4473 */             System.err.println("classesInfo>>"+this.classesInfo);//E2,C1,B0
/*  4474 */             String info = this.classesInfo.substring(1, this.classesInfo.length());//
/*  4475 */             System.err.println(info);
/*  4476 */             int index = getIndexFromArr(classesInfo1.charAt(0));
/*  4477 */             int base = index * 50 + Integer.valueOf(info).intValue() * 500;//注意
/*  4478 */             int s = base + 50;
/*  4479 */             BoHostDevice hostDevice = this.boHostDeviceService.findBydeviceAddress(userCode2[0].trim().toString(), this.deviceCode, this.deviceAddress);
/*       */ 
/*  4481 */             System.err.println(this.deviceCode);
/*  4482 */             System.err.println(this.deviceAddress);
/*  4483 */             System.err.println(hostDevice.getBoDevice().getDeviceCode());
/*  4484 */             List<BoInfraredButtons> list = this.boInfraredButtonsService.getListBy(userCode2[0].trim().toString(), this.deviceCode, this.deviceAddress, base, s);
/*  4485 */             if (list.size() <= 0) {
/*  4486 */               System.err.println("没有");
/*       */             } else {
/*  4488 */               BoInfraredButtons buttons = this.boInfraredButtonsService.findBydeviceAddress(userCode2[0].trim().toString(), this.deviceCode, this.deviceAddress);
/*  4489 */               indexFromArr = 0;
/*       */ 
/*  4492 */               for (BoInfraredButtons boInfraredButtons : list) {
/*  4493 */                 this.boInfraredButtonsService.delete(boInfraredButtons);
/*  4494 */                 indexFromArr = getIndexFromArr(this.classesInfo, buttons.getTemplate());
/*       */               }
/*       */ 
/*  4498 */               String[] arrayStrs = buttons.getTemplate().split(",");
/*       */ 
/*  4500 */               ArrayList al = new ArrayList();
/*  4501 */               for (int i = 0; i < arrayStrs.length; i++) {
/*  4502 */                 al.add(arrayStrs[i]);
/*       */               }
/*       */ 
/*  4506 */               List list1 = new ArrayList();
/*  4507 */               for (int i = 0; i < al.size(); i++) {
/*  4508 */                 System.err.println((String)al.get(i));
/*  4509 */                 if (((String)al.get(i)).equals(this.classesInfo))
/*  4510 */                   al.remove(al.get(i));
/*       */                 else {
/*  4512 */                   list1.add((String)al.get(i));
/*       */                 }
/*       */ 
/*       */               }
/*       */ 
/*  4517 */               StringBuilder sb = new StringBuilder();
/*  4518 */               for (int i = 0; i < al.size(); i++) {
/*  4519 */                 sb.append((String)al.get(i));
/*  4520 */                 sb.append(",");
/*       */               }
/*       */ 
/*  4523 */               String str = sb.toString();
/*  4524 */               lists = this.boInfraredButtonsService.getListBy(userCode2[0].trim().toString(), hostDevice.getBoDevice().getDeviceCode(), this.deviceAddress);
/*  4525 */               for (BoInfraredButtons boInfraredButtons : lists) {
/*  4526 */                 boInfraredButtons.setTemplate(str);
/*  4527 */                 this.boInfraredButtonsService.update(boInfraredButtons);
/*       */               }
/*       */             }
/*  4530 */             System.out.println(base + " " + s + "<--->" + index + "-->" + "this is index of array");
/*       */           } else {
/*  4532 */             System.err.println("AToken时间戳超时了");
/*  4533 */             this.requestJson.setMessage("超时了");
/*  4534 */             this.requestJson.setSuccess(false);
/*       */           }
/*       */         } else {
/*  4537 */           System.err.println("AToken超时了");
/*  4538 */           this.requestJson.setMessage("超时了");
/*  4539 */           this.requestJson.setSuccess(false);
/*       */         }
/*       */       }
/*       */       else
/*       */       {
/*  4544 */         System.err.println("验证不通过");
/*  4545 */         this.requestJson.setMessage("验证不通过");
/*  4546 */         this.requestJson.setSuccess(false);
/*       */       }
/*       */     } else {
/*  4549 */       Boolean ral = isRal(header, header2, header3, header4, userCode, "删除红外模板");
/*  4550 */       if (ral.booleanValue()) {
/*  4551 */         System.err.println("验证通过");
/*  4552 */         Long accessToken = Long.valueOf(header);
/*  4553 */         BoUsers boUsers = this.boUserServicess.findByUserUserCode(userCode);
/*  4554 */         if (boUsers == null) {
/*  4555 */           this.requestJson.setData(map);
/*  4556 */           this.requestJson.setMessage("Invalid_User");
/*  4557 */           this.requestJson.setSuccess(true);
/*       */         }
/*  4559 */         else if (accessToken.longValue() < Long.valueOf(boUsers.getAccessTokenTime()).longValue()) {
/*  4560 */           String classesInfo1 = this.classesInfo.substring(0, 1);
/*  4561 */           System.err.println(classesInfo1);
/*  4562 */           String info = this.classesInfo.substring(1, this.classesInfo.length());
/*  4563 */           System.err.println(info);
/*  4564 */           int index = getIndexFromArr(classesInfo1.charAt(0));
/*  4565 */           int base = index * 50 + Integer.valueOf(info).intValue() * 500;
/*  4566 */           int s = base + 50;
/*  4567 */           BoHostDevice hostDevice = this.boHostDeviceService.findBydeviceAddress(userCode, this.deviceCode, this.deviceAddress);
/*       */ 
/*  4569 */           System.err.println(this.deviceCode);
/*  4570 */           System.err.println(this.deviceAddress);
/*  4571 */           System.err.println(hostDevice.getBoDevice().getDeviceCode());
/*  4572 */           List<BoInfraredButtons> list = this.boInfraredButtonsService.getListBy(userCode, this.deviceCode, this.deviceAddress, base, s);
/*  4573 */           if (list.size() <= 0) {
/*  4574 */             System.err.println("没有");
/*       */           } else {
/*  4576 */             BoInfraredButtons buttons = this.boInfraredButtonsService.findBydeviceAddress(userCode, this.deviceCode, this.deviceAddress);
/*  4577 */             indexFromArr = 0;
/*       */ 
/*  4580 */             for (BoInfraredButtons boInfraredButtons : list) {
/*  4581 */               this.boInfraredButtonsService.delete(boInfraredButtons);
/*  4582 */               indexFromArr = getIndexFromArr(this.classesInfo, buttons.getTemplate());
/*       */             }
/*       */ 
/*  4586 */             String[] arrayStrs = buttons.getTemplate().split(",");
/*       */ 
/*  4588 */             ArrayList al = new ArrayList();
/*  4589 */             for (int i = 0; i < arrayStrs.length; i++) {
/*  4590 */               al.add(arrayStrs[i]);
/*       */             }
/*       */ 
/*  4594 */             List list1 = new ArrayList();
/*  4595 */             for (int i = 0; i < al.size(); i++) {
/*  4596 */               System.err.println((String)al.get(i));
/*  4597 */               if (((String)al.get(i)).equals(this.classesInfo))
/*  4598 */                 al.remove(al.get(i));
/*       */               else {
/*  4600 */                 list1.add((String)al.get(i));
/*       */               }
/*       */ 
/*       */             }
/*       */ 
/*  4605 */             StringBuilder sb = new StringBuilder();
/*  4606 */             for (int i = 0; i < al.size(); i++) {
/*  4607 */               sb.append((String)al.get(i));
/*  4608 */               sb.append(",");
/*       */             }
/*       */ 
/*  4611 */             String str = sb.toString();
/*  4612 */             lists = this.boInfraredButtonsService.getListBy(userCode, hostDevice.getBoDevice().getDeviceCode(), this.deviceAddress);
/*  4613 */             for (BoInfraredButtons boInfraredButtons : lists) {
/*  4614 */               boInfraredButtons.setTemplate(str);
/*  4615 */               this.boInfraredButtonsService.update(boInfraredButtons);
/*       */             }
/*       */           }
/*  4618 */           System.out.println(base + " " + s + "<--->" + index + "-->" + "this is index of array");
/*       */         } else {
/*  4620 */           this.requestJson.setMessage("超时了");
/*  4621 */           this.requestJson.setSuccess(false);
/*       */         }
/*       */       }
/*       */       else
/*       */       {
/*  4626 */         System.err.println("验证不通过");
/*  4627 */         this.requestJson.setMessage("验证不通过");
/*  4628 */         this.requestJson.setSuccess(false);
/*       */       }
/*       */     }
/*  4631 */     return "success";
/*       */   }
/*       */ 
/*       */   public int getIndexFromArr(char s)
/*       */   {
				int i;
/*  4636 */     for (i = 0; i < this.Array1.length; i++)
/*  4637 */       
/*  4638 */       if (s == this.Array1[i])
/*       */         break;
/*  4640 */     return i;
/*       */   }
/*       */ 
/*       */   public int getIndexFromArr(String ss, String ss1)
/*       */   {
/*  4645 */     String[] arrayStr = new String[0];
/*  4646 */     arrayStr = ss1.split(",");
				int i;
/*  4647 */     for (i = 0;i < arrayStr.length; i++)
/*  4649 */       if (ss.equals(arrayStr[i]))
/*       */         break;
/*  4651 */     return i;
/*       */   }
/*       */ 
/*       */   @Action(value="createButton", results={@org.apache.struts2.convention.annotation.Result(type="json", params={"root", "requestJson"})})
/*       */   public String createButton()
/*       */   {
/*  4660 */     this.requestJson = new RequestJson();
/*  4661 */     Map map = new HashMap();
/*  4662 */     HttpServletRequest request = ServletActionContext.getRequest();
/*  4663 */     String header = request.getHeader("timestamp");
/*  4664 */     String header2 = request.getHeader("nonce");
/*  4665 */     String header3 = request.getHeader("sign");
/*  4666 */     String header4 = request.getHeader("access_token");
/*  4667 */     String userCode = request.getHeader("userCode");
///*       */     List<BoInfraredButtons> list;
/*  4668 */     if (userCode.contains(",")) {
/*  4669 */       String[] userCode2 = userCode.split(",");
/*  4670 */       BoUsers boUsers = this.boUserServicess.findByUserUserCode(userCode2[0].trim().toString());
/*  4671 */       BoUsers phone = this.boUserServicess.findByUserPhone(userCode2[1].trim().toString());
/*  4672 */       Boolean ral = isRal(header, header2, header3, header4, userCode, "创建红外按键");
/*  4673 */       if (ral.booleanValue()) {
/*  4674 */         System.err.println("验证通过");
					logger.info("验证通过 0");
/*  4675 */         Long accessToken = Long.valueOf(header);
/*  4676 */         if ((phone == null) || (boUsers == null)) {
/*  4677 */           this.requestJson.setData(map);
/*  4678 */           this.requestJson.setMessage("Invalid_User");
/*  4679 */           this.requestJson.setSuccess(true);
/*       */         }
/*  4681 */         else if (header4.equals(phone.getAccessToken())) {
/*  4682 */           if (accessToken.longValue() < Long.valueOf(phone.getAccessTokenTime()).longValue()) {
/*  4683 */             BoHostDevice hostDevice = this.boHostDeviceService.findBydeviceAddress(userCode2[0].trim().toString(), this.deviceCode, this.deviceAddress);
/*       */             logger.info("hostDevice 0>"+hostDevice);
						logger.info("classesInfo 0>"+this.classesInfo);
						logger.info("infraredButtonsInfo 0>"+this.infraredButtonsInfo);
/*  4685 */             System.err.println(this.classesInfo);
/*  4686 */             System.err.println(this.infraredButtonsInfo);


/*  4763 */             Map data = new HashMap();
						List<String> keys = new ArrayList<String>(data.keySet());
///*       */ 			List<String> keys=(List<String>) data.keySet();
						//Set 转   List
//						Set<String> keys0 = data.keySet();
//						List<String> keys = new ArrayList<>(keys0);
//						System.out.println("keys:"+keys);
						
/*  4689 */             JSONObject jsonObject = JSONObject.fromObject(this.infraredButtonsInfo);
/*  4690 */             Iterator it = jsonObject.keys();
/*       */ 
/*  4692 */             while (it.hasNext())
/*       */             {
/*  4694 */               String key = String.valueOf(it.next());
/*  4695 */               String value = (String)jsonObject.get(key);
/*  4696 */               data.put(key, value);
/*       */             }
/*       */ 
/*  4700 */             String s = null;
/*  4701 */             List<BoInfraredButtons> list = this.boInfraredButtonsService.getListBy(userCode2[0].trim().toString(), this.deviceCode, this.deviceAddress);
/*       */ 
/*  4703 */             if (list.size() > 0) {
/*  4704 */               for (BoInfraredButtons boInfraredButtons : list) {
/*  4705 */                 s = boInfraredButtons.getTemplate();
/*  4706 */                 boInfraredButtons.setTemplate(boInfraredButtons.getTemplate() + this.classesInfo + ",");
/*  4707 */                 this.boInfraredButtonsService.update(boInfraredButtons);
/*       */               }
/*       */ 
/*       */             }
/*       */ 
/*  4712 */             for (String key : keys)
/*       */             {
/*  4714 */               BoInfraredButtons bo = new BoInfraredButtons();
/*  4715 */               bo.setBoDevice(hostDevice.getBoDevice());
/*  4716 */               bo.setBoUsers(boUsers);
/*  4717 */               bo.setDeviceAddress(this.deviceAddress);
/*  4718 */               bo.setInfraredButtonsValuess(Integer.valueOf(key));
/*  4719 */               bo.setInfraredButtonsName((String)data.get(key));
/*  4720 */               if ((s == null) || (s.equals("")))
/*  4721 */                 bo.setTemplate(this.classesInfo + ",");
/*       */               else {
/*  4723 */                 bo.setTemplate(s + this.classesInfo + ",");
/*       */               }
/*       */ 
/*  4726 */               this.boInfraredButtonsService.save(bo);
/*       */             }
/*       */           } else {
/*  4729 */             System.err.println("AToken时间戳超时了");
/*  4730 */             this.requestJson.setMessage("超时了");
/*  4731 */             this.requestJson.setSuccess(false);
/*       */           }
/*       */         } else {
/*  4734 */           System.err.println("AToken超时了");
/*  4735 */           this.requestJson.setMessage("超时了");
/*  4736 */           this.requestJson.setSuccess(false);
/*       */         }
/*       */ 
/*       */       }
/*       */       else
/*       */       {
/*  4743 */         System.err.println("验证不通过");
/*  4744 */         this.requestJson.setMessage("验证不通过");
/*  4745 */         this.requestJson.setSuccess(false);
/*       */       }
/*       */     } else {
/*  4748 */       Boolean ral = isRal(header, header2, header3, header4, userCode, "创建红外按键");
/*  4749 */       if (ral.booleanValue()) {
/*  4750 */         System.err.println("验证通过");
					logger.info("验证通过 1");
/*  4751 */         Long accessToken = Long.valueOf(header);
/*  4752 */         BoUsers boUsers = this.boUserServicess.findByUserUserCode(userCode);
/*  4753 */         if (boUsers == null) {
/*  4754 */           this.requestJson.setData(map);
/*  4755 */           this.requestJson.setMessage("Invalid_User");
/*  4756 */           this.requestJson.setSuccess(true);
/*       */         }
/*  4758 */         else if (accessToken.longValue() < Long.valueOf(boUsers.getAccessTokenTime()).longValue()) {
/*  4759 */           BoHostDevice hostDevice = this.boHostDeviceService.findBydeviceAddress(userCode, this.deviceCode, this.deviceAddress);
/*       */ 
/*  4761 */           System.err.println(this.classesInfo);
/*  4762 */           System.err.println(this.infraredButtonsInfo);
					  

/*  4763 */           Map data = new HashMap();
				      List<String> keys = new ArrayList<String>(data.keySet());//map key 转为  list  List<String> mapKeyList = new ArrayList<String>(map.keySet());
//					  List<String> keys=(List<String>) data.keySet();
					  //Set 转   List
//					  Set<String> keys0 = data.keySet();
//					  List<String> keys = new ArrayList<>(keys0);
//					  System.out.println("keys:"+keys);
/*       */ 
/*  4765 */           JSONObject jsonObject = JSONObject.fromObject(this.infraredButtonsInfo);
/*  4766 */           Iterator it = jsonObject.keys();
/*       */ 
/*  4768 */           while (it.hasNext())
/*       */           {
/*  4770 */             String key = String.valueOf(it.next());
/*  4771 */             String value = (String)jsonObject.get(key);
/*  4772 */             data.put(key, value);
/*       */           }
/*       */ 
/*  4776 */           String s = null;
/*  4777 */           List<BoInfraredButtons> list = this.boInfraredButtonsService.getListBy(userCode, this.deviceCode, this.deviceAddress);
/*       */ 
/*  4779 */           if (list.size() > 0) {
/*  4780 */             for (BoInfraredButtons boInfraredButtons : list) {
/*  4781 */               s = boInfraredButtons.getTemplate();
/*  4782 */               boInfraredButtons.setTemplate(boInfraredButtons.getTemplate() + this.classesInfo + ",");
/*  4783 */               this.boInfraredButtonsService.update(boInfraredButtons);
/*       */             }
/*       */ 
/*       */           }
/*       */ 
/*  4788 */           for (String key : keys)
/*       */           {
/*  4790 */             BoInfraredButtons bo = new BoInfraredButtons();
/*  4791 */             bo.setBoDevice(hostDevice.getBoDevice());
/*  4792 */             bo.setBoUsers(boUsers);
/*  4793 */             bo.setDeviceAddress(this.deviceAddress);
/*  4794 */             bo.setInfraredButtonsValuess(Integer.valueOf(key));
/*  4795 */             bo.setInfraredButtonsName((String)data.get(key));
/*  4796 */             if ((s == null) || (s.equals("")))
/*  4797 */               bo.setTemplate(this.classesInfo + ",");
/*       */             else {
/*  4799 */               bo.setTemplate(s + this.classesInfo + ",");
/*       */             }
/*       */ 
/*  4802 */             this.boInfraredButtonsService.save(bo);
/*       */           }
/*       */         } else {
/*  4805 */           this.requestJson.setMessage("超时了");
/*  4806 */           this.requestJson.setSuccess(false);
/*       */         }
/*       */ 
/*       */       }
/*       */       else
/*       */       {
/*  4813 */         System.err.println("验证不通过");
/*  4814 */         this.requestJson.setMessage("验证不通过");
/*  4815 */         this.requestJson.setSuccess(false);
/*       */       }
/*       */     }
/*  4818 */     return "success";
/*       */   }
/*       */ 
/*       */   @Action(value="quickscanhost", results={@org.apache.struts2.convention.annotation.Result(type="json", params={"root", "requestJson"})})
/*       */   public String quickScanHost()
/*       */   {
/*  4830 */     this.requestJson = new RequestJson();
/*  4831 */     Map map = new HashMap();
/*  4832 */     HttpServletRequest request = ServletActionContext.getRequest();
/*  4833 */     String header = request.getHeader("timestamp");
/*  4834 */     String header2 = request.getHeader("nonce");
/*  4835 */     String header3 = request.getHeader("sign");
/*  4836 */     String header4 = request.getHeader("access_token");
/*  4837 */     String userCode = request.getHeader("userCode");
/*  4838 */     if (userCode.contains(",")) {
/*  4839 */       String[] userCode2 = userCode.split(",");
/*  4840 */       BoUsers boUsers = this.boUserServicess.findByUserUserCode(userCode2[0].trim().toString());
/*  4841 */       BoUsers phone = this.boUserServicess.findByUserPhone(userCode2[1].trim().toString());
/*  4842 */       Boolean ral = isRal(header, header2, header3, header4, userCode, "刷新扫描添加的主机");
/*  4843 */       if (ral.booleanValue()) {
/*  4844 */         System.err.println("验证通过");
/*  4845 */         Long accessToken = Long.valueOf(header);
/*       */ 
/*  4847 */         if ((phone == null) || (boUsers == null)) {
/*  4848 */           this.requestJson.setData(map);
/*  4849 */           this.requestJson.setMessage("Invalid_User");
/*  4850 */           this.requestJson.setSuccess(true);
/*       */         }
/*  4852 */         else if (header4.equals(phone.getAccessToken())) {
/*  4853 */           if (accessToken.longValue() < Long.valueOf(phone.getAccessTokenTime()).longValue()) {
/*       */             try {
/*  4855 */               List byDeviceCode = this.boUserDevicesServicess.getListByDeviceCodes(userCode2[0].trim().toString());
/*  4856 */               for (int i = 0; i < byDeviceCode.size(); i++) {
/*  4857 */                 BoUserDevices obj = (BoUserDevices)byDeviceCode.get(i);
/*  4858 */                 String str = "ZIGBEE_SCAN-DEVEICE-NOW";
/*  4859 */                 byte[] bs = str.getBytes();
/*  4860 */                 System.err.println(new String(bs));
/*  4861 */                 this.packetProcessHelper.processSendDDatas(obj.getBoDevice().getDeviceCode(), bs);
/*  4862 */                 StaticUtil.drik.put(obj.getBoDevice().getDeviceCode() + "_" + "A", new String[] { 
/*  4863 */                   userCode2[0].trim().toString(), new Date().getTime()+"" });
/*  4864 */                 this.requestJson.setSuccess(true);
/*  4865 */                 BoDevice boDevice = this.boDeviceService.findByCode(obj.getBoDevice().getDeviceCode());
/*  4866 */                 BoHostDevice hostDevice = this.boHostDeviceService.findBydeviceAddress(userCode2[0].trim().toString(), obj.getBoDevice().getDeviceCode(), "65535," + obj.getBoDevice().getDeviceCode());
/*  4867 */                 if ((obj.getBoDevice().getType().equals("G")) && 
/*  4868 */                   (hostDevice == null)) {
/*  4869 */                   BoHostDevice boHostDevice = new BoHostDevice();
/*  4870 */                   boHostDevice.setBoDevice(boDevice);
/*  4871 */                   boHostDevice.setBoUsers(boUsers);
/*  4872 */                   boHostDevice.setDeviceType("98");
/*  4873 */                   boHostDevice.setDeviceAddress("65535," + obj.getBoDevice().getDeviceCode());
/*  4874 */                   boHostDevice.setNickName("");
/*  4875 */                   boHostDevice.setWhetherQueryStateSign("");
/*  4876 */                   boHostDevice.setIco("WIFI红外");
/*  4877 */                   boHostDevice.setDeviceNum(Integer.valueOf(0));
/*  4878 */                   boHostDevice.setPushSet("");
/*  4879 */                   boHostDevice.setState("");
/*  4880 */                   boHostDevice.setBoRoom(null);
/*  4881 */                   boHostDevice.setDeviceClassify(this.fid);
/*  4882 */                   boHostDevice.setMntDelete("N");
/*  4883 */                   boHostDevice.setValidationCode("");
/*  4884 */                   this.boHostDeviceService.save(boHostDevice);
/*       */                 }
/*       */ 
/*  4887 */                 Thread.sleep(5000L);
/*       */               }
/*       */             }
/*       */             catch (InterruptedException localInterruptedException) {
/*       */             }
/*       */           }
/*       */           else {
/*  4894 */             System.err.println("AToken时间戳超时了");
/*  4895 */             this.requestJson.setMessage("超时了");
/*  4896 */             this.requestJson.setSuccess(false);
/*       */           }
/*       */         } else {
/*  4899 */           System.err.println("AToken超时了");
/*  4900 */           this.requestJson.setMessage("超时了");
/*  4901 */           this.requestJson.setSuccess(false);
/*       */         }
/*       */       }
/*       */       else {
/*  4905 */         System.err.println("验证不通过");
/*  4906 */         this.requestJson.setMessage("验证不通过");
/*  4907 */         this.requestJson.setSuccess(false);
/*       */       }
/*       */     } else {
/*  4910 */       Boolean ral = isRal(header, header2, header3, header4, userCode, "刷新扫描添加的主机");
/*  4911 */       if (ral.booleanValue()) {
/*  4912 */         System.err.println("验证通过");
/*  4913 */         Long accessToken = Long.valueOf(header);
/*  4914 */         BoUsers boUsers = this.boUserServicess.findByUserUserCode(userCode);
/*  4915 */         if (boUsers == null) {
/*  4916 */           this.requestJson.setData(map);
/*  4917 */           this.requestJson.setMessage("Invalid_User");
/*  4918 */           this.requestJson.setSuccess(true);
/*       */         }
/*  4920 */         else if (accessToken.longValue() < Long.valueOf(boUsers.getAccessTokenTime()).longValue()) {
/*       */           try {
/*  4922 */             List byDeviceCode = this.boUserDevicesServicess.getListByDeviceCodes(userCode);
/*  4923 */             for (int i = 0; i < byDeviceCode.size(); i++) {
/*  4924 */               BoUserDevices obj = (BoUserDevices)byDeviceCode.get(i);
/*  4925 */               String str = "ZIGBEE_SCAN-DEVEICE-NOW";
/*  4926 */               byte[] bs = str.getBytes();
/*  4927 */               System.err.println(new String(bs));
/*  4928 */               this.packetProcessHelper.processSendDDatas(obj.getBoDevice().getDeviceCode(), bs);
/*  4929 */               StaticUtil.drik.put(obj.getBoDevice().getDeviceCode() + "_" + "A", new String[] { 
/*  4930 */                 userCode, new Date().getTime()+"" });
/*  4931 */               this.requestJson.setSuccess(true);
/*  4932 */               BoDevice boDevice = this.boDeviceService.findByCode(obj.getBoDevice().getDeviceCode());
/*  4933 */               BoHostDevice hostDevice = this.boHostDeviceService.findBydeviceAddress(userCode, obj.getBoDevice().getDeviceCode(), "65535," + obj.getBoDevice().getDeviceCode());
/*  4934 */               if ((obj.getBoDevice().getType().equals("G")) && 
/*  4935 */                 (hostDevice == null)) {
/*  4936 */                 BoHostDevice boHostDevice = new BoHostDevice();
/*  4937 */                 boHostDevice.setBoDevice(boDevice);
/*  4938 */                 boHostDevice.setBoUsers(boUsers);
/*  4939 */                 boHostDevice.setDeviceType("98");
/*  4940 */                 boHostDevice.setDeviceAddress("65535," + obj.getBoDevice().getDeviceCode());
/*  4941 */                 boHostDevice.setNickName("");
/*  4942 */                 boHostDevice.setWhetherQueryStateSign("");
/*  4943 */                 boHostDevice.setIco("WIFI红外");
/*  4944 */                 boHostDevice.setDeviceNum(Integer.valueOf(0));
/*  4945 */                 boHostDevice.setPushSet("");
/*  4946 */                 boHostDevice.setState("");
/*  4947 */                 boHostDevice.setBoRoom(null);
/*  4948 */                 boHostDevice.setDeviceClassify(this.fid);
/*  4949 */                 boHostDevice.setMntDelete("N");
/*  4950 */                 boHostDevice.setValidationCode("");
/*  4951 */                 this.boHostDeviceService.save(boHostDevice);
/*       */               }
/*       */ 
/*  4954 */               Thread.sleep(5000L);
/*       */             }
/*       */           }
/*       */           catch (InterruptedException localInterruptedException1) {
/*       */           }
/*       */         }
/*       */         else {
/*  4961 */           this.requestJson.setMessage("超时了");
/*  4962 */           this.requestJson.setSuccess(false);
/*       */         }
/*       */ 
/*       */       }
/*       */       else
/*       */       {
/*  4969 */         System.err.println("验证不通过");
/*  4970 */         this.requestJson.setMessage("验证不通过");
/*  4971 */         this.requestJson.setSuccess(false);
/*       */       }
/*       */     }
/*  4974 */     return "success";
/*       */   }
/*       */ 
/*       */   @Action(value="loginforpassword", results={@org.apache.struts2.convention.annotation.Result(type="json", params={"root", "requestJson"})})
/*       */   public String LoginForPassword()
/*       */   {
/*  4984 */     this.requestJson = new RequestJson();
/*  4985 */     HttpServletRequest request = ServletActionContext.getRequest();
/*  4986 */     String str = "";
/*  4987 */     Md5 md5 = new Md5();
/*  4988 */     String header3 = request.getHeader("sign");
/*       */ 
/*  5019 */     Map map = new HashMap();
/*  5020 */     BoUsers users = this.boUserServicess.findByUserPhonePwd(this.userPhone, md5.getMD5ofStr(this.userPwd));
/*  5021 */     String generateTokeCode = TokeUtil.generateTokeCode();
/*  5022 */     String generateTokeCodes = TokeUtil.generateTokeCodes();
/*  5023 */     Long accessTokenTime = Long.valueOf(1800L);
/*  5024 */     Long refreshTokenTime = Long.valueOf(2592000L);
/*  5025 */     Long accessTokenTime_o = Long.valueOf((int)(System.currentTimeMillis() / 1000L) + accessTokenTime.longValue());
/*  5026 */     Long refreshTokenTime_o = Long.valueOf((int)(System.currentTimeMillis() / 1000L) + refreshTokenTime.longValue());
/*  5027 */     if (users != null) {
/*  5028 */       users.setAccessToken(generateTokeCode);
/*  5029 */       users.setRefreshToken(generateTokeCodes);
/*  5030 */       users.setAccessTokenTime(accessTokenTime_o+"");
/*  5031 */       users.setRefreshTokenTime(refreshTokenTime_o+"");
/*  5032 */       users.setCid(this.CID);
/*       */       String s;
///*       */       String s;
/*  5034 */       if (this.phoneType == null)
/*  5035 */         s = "1";
/*       */       else {
/*  5037 */         s = this.phoneType;
/*       */       }
/*  5039 */       users.setPhoneType(Integer.valueOf(s));
/*  5040 */       users.setVersionType(this.versionType);
/*  5041 */       BoUsers update = (BoUsers)this.boUserServicess.update(users);
/*  5042 */       map.put("accessToken", update.getAccessToken());
/*  5043 */       map.put("refreshToken", update.getRefreshToken());
/*  5044 */       if (users.getLogoAccountType().equals("M"))
/*  5045 */         map.put("userCode", users.getUserCode() + "," + users.getUserPhone());
/*       */       else {
/*  5047 */         map.put("userCode", users.getAuthorizationUserCode() + "," + users.getUserPhone());
/*       */       }
/*  5049 */       map.put("logoAccountType", users.getLogoAccountType());
/*  5050 */       map.put("accountOperationType", users.getAccountOperationType());
/*  5051 */       map.put("userPhone", users.getUserPhone());
/*  5052 */       map.put("isFirst", users.getIsFirst());
/*  5053 */       map.put("whetherSetPwd", users.getWhetherSetPwd());
/*  5054 */       String fluoriteAccessToken = users.getFluoriteAccessToken();
/*       */       String EZTOKEN;
///*       */       String EZTOKEN;
/*  5056 */       if (fluoriteAccessToken.equals(""))
/*  5057 */         EZTOKEN = "NO_BUNDING";
/*       */       else {
/*  5059 */         EZTOKEN = fluoriteAccessToken;
/*       */       }
/*  5061 */       map.put("Eztoken", EZTOKEN);
/*  5062 */       map.put("ez_token", EZTOKEN);
/*  5063 */       String city2 = users.getCity();
/*       */       String city3;
///*       */       String city3;
/*  5065 */       if (city2.equals("")) {
/*  5066 */         city3 = "杭州市";
/*       */       } else {
/*  5068 */         String[] split = city2.split(",");
/*  5069 */         city3 = split[1];
/*       */       }
/*  5071 */       map.put("city", city3);
/*  5072 */       this.requestJson.setData(map);
/*  5073 */       this.requestJson.setSuccess(true);
/*       */     } else {
/*  5075 */       this.requestJson.setData(map);
/*  5076 */       this.requestJson.setMessage("手机号或密码不正确");
/*  5077 */       this.requestJson.setSuccess(false);
/*       */     }
/*  5079 */     return "success";
/*       */   }
/*       */ 
/*       */   @Action(value="setloginpassword", results={@org.apache.struts2.convention.annotation.Result(type="json", params={"root", "requestJson"})})
/*       */   public String setLoginPassword()
/*       */   {
/*  5093 */     this.requestJson = new RequestJson();
/*  5094 */     HttpServletRequest request = ServletActionContext.getRequest();
/*  5095 */     Map map = new HashMap();
/*  5096 */     Md5 md5 = new Md5();
/*  5097 */     String header = request.getHeader("timestamp");
/*  5098 */     String header2 = request.getHeader("nonce");
/*  5099 */     String header3 = request.getHeader("sign");
/*  5100 */     String header4 = request.getHeader("access_token");
/*  5101 */     String userCode = request.getHeader("userCode");
/*  5102 */     if (userCode.contains(",")) {
/*  5103 */       String[] userCode2 = userCode.split(",");
/*  5104 */       BoUsers boUsers = this.boUserServicess.findByUserUserCode(userCode2[0].trim().toString());
/*  5105 */       BoUsers phone = this.boUserServicess.findByUserPhone(userCode2[1].trim().toString());
/*  5106 */       Boolean ral = isRal(header, header2, header3, header4, userCode, "用户设置密码");
/*  5107 */       if (ral.booleanValue()) {
/*  5108 */         System.err.println("验证通过");
/*  5109 */         Long accessToken = Long.valueOf(header);
/*       */ 
/*  5111 */         if ((phone == null) || (boUsers == null)) {
/*  5112 */           this.requestJson.setData(map);
/*  5113 */           this.requestJson.setMessage("Invalid_User");
/*  5114 */           this.requestJson.setSuccess(true);
/*       */         }
/*  5116 */         else if (header4.equals(phone.getAccessToken())) {
/*  5117 */           if (accessToken.longValue() < Long.valueOf(phone.getAccessTokenTime()).longValue()) {
/*  5118 */             BoUsers user = this.boUserServicess.findByUserPhone(this.userPhone);
/*  5119 */             user.setUserPwd(md5.getMD5ofStr(this.userPwd));
/*  5120 */             user.setWhetherSetPwd(this.fid1);
/*  5121 */             BoUsers update = (BoUsers)this.boUserServicess.update(user);
/*  5122 */             if (update != null) {
/*  5123 */               map.put("whetherSetPwd", update.getWhetherSetPwd());
/*  5124 */               this.requestJson.setData(map);
/*  5125 */               this.requestJson.setMessage("设置成功");
/*  5126 */               this.requestJson.setSuccess(true);
/*       */             } else {
/*  5128 */               this.requestJson.setData(map);
/*  5129 */               this.requestJson.setMessage("设置失败");
/*  5130 */               this.requestJson.setSuccess(false);
/*       */             }
/*       */           } else {
/*  5133 */             System.err.println("AToken时间戳超时了");
/*  5134 */             this.requestJson.setMessage("超时了");
/*  5135 */             this.requestJson.setSuccess(false);
/*       */           }
/*       */         } else {
/*  5138 */           System.err.println("AToken超时了");
/*  5139 */           this.requestJson.setMessage("超时了");
/*  5140 */           this.requestJson.setSuccess(false);
/*       */         }
/*       */       }
/*       */       else {
/*  5144 */         System.err.println("验证不通过");
/*  5145 */         this.requestJson.setMessage("验证不通过");
/*  5146 */         this.requestJson.setSuccess(false);
/*       */       }
/*       */     } else {
/*  5149 */       Boolean ral = isRal(header, header2, header3, header4, userCode, "用户设置密码");
/*  5150 */       if (ral.booleanValue()) {
/*  5151 */         System.err.println("验证通过");
/*  5152 */         Long accessToken = Long.valueOf(header);
/*  5153 */         BoUsers boUsers = this.boUserServicess.findByUserUserCode(userCode);
/*  5154 */         if (boUsers == null) {
/*  5155 */           this.requestJson.setData(map);
/*  5156 */           this.requestJson.setMessage("Invalid_User");
/*  5157 */           this.requestJson.setSuccess(true);
/*       */         }
/*  5159 */         else if (accessToken.longValue() < Long.valueOf(boUsers.getAccessTokenTime()).longValue()) {
/*  5160 */           BoUsers user = this.boUserServicess.findByUserPhone(this.userPhone);
/*  5161 */           user.setUserPwd(md5.getMD5ofStr(this.userPwd));
/*  5162 */           user.setWhetherSetPwd(this.fid1);
/*  5163 */           BoUsers update = (BoUsers)this.boUserServicess.update(user);
/*  5164 */           if (update != null) {
/*  5165 */             map.put("whetherSetPwd", update.getWhetherSetPwd());
/*  5166 */             this.requestJson.setData(map);
/*  5167 */             this.requestJson.setMessage("设置成功");
/*  5168 */             this.requestJson.setSuccess(true);
/*       */           } else {
/*  5170 */             this.requestJson.setData(map);
/*  5171 */             this.requestJson.setMessage("设置失败");
/*  5172 */             this.requestJson.setSuccess(false);
/*       */           }
/*       */         } else {
/*  5175 */           this.requestJson.setMessage("超时了");
/*  5176 */           this.requestJson.setSuccess(false);
/*       */         }
/*       */       }
/*       */       else {
/*  5180 */         System.err.println("验证不通过");
/*  5181 */         this.requestJson.setMessage("验证不通过");
/*  5182 */         this.requestJson.setSuccess(false);
/*       */       }
/*       */     }
/*  5185 */     return "success";
/*       */   }
/*       */ 
/*       */   @Action(value="ezToken", results={@org.apache.struts2.convention.annotation.Result(type="json", params={"root", "requestJson"})})
/*       */   public String ezToken()
/*       */   {
/*  5192 */     this.requestJson = new RequestJson();
/*  5193 */     HttpServletRequest request = ServletActionContext.getRequest();
/*  5194 */     Map map = new HashMap();
/*  5195 */     String header = request.getHeader("timestamp");
/*  5196 */     String header2 = request.getHeader("nonce");
/*  5197 */     String header3 = request.getHeader("sign");
/*  5198 */     String header4 = request.getHeader("access_token");
/*  5199 */     String userCode = request.getHeader("userCode");
/*  5200 */     if (userCode.contains(",")) {
/*  5201 */       String[] userCode2 = userCode.split(",");
/*  5202 */       BoUsers boUsers = this.boUserServicess.findByUserUserCode(userCode2[0].trim().toString());
/*  5203 */       BoUsers phone = this.boUserServicess.findByUserPhone(userCode2[1].trim().toString());
/*  5204 */       Boolean ral = isRal(header, header2, header3, header4, userCode, "修改主机昵称");
/*  5205 */       if (ral.booleanValue()) {
/*  5206 */         System.err.println("验证通过");
/*  5207 */         Long accessToken = Long.valueOf(header);
/*  5208 */         System.err.println(userCode2[0].trim().toString());
/*       */ 
/*  5210 */         if ((phone == null) || (boUsers == null)) {
/*  5211 */           this.requestJson.setData(map);
/*  5212 */           this.requestJson.setMessage("Invalid_User");
/*  5213 */           this.requestJson.setSuccess(true);
/*       */         }
/*  5215 */         else if (header4.equals(phone.getAccessToken())) {
/*  5216 */           if (accessToken.longValue() < Long.valueOf(phone.getAccessTokenTime()).longValue()) {
/*  5217 */             Long fluoriteAccessTokenTime = Long.valueOf(518400L);
/*  5218 */             Long fluoriteAccessTokenTime_o = Long.valueOf((int)(System.currentTimeMillis() / 1000L) + fluoriteAccessTokenTime.longValue());
/*  5219 */             String o4 = null;
/*  5220 */             Map paramsMap = new HashMap();
/*  5221 */             paramsMap.put("phone", boUsers.getUserPhone());
/*  5222 */             Map maps = YZUitl.paramsInit("token/getAccessToken", paramsMap);
/*  5223 */             String json = JSON.toJSONString(maps);
/*  5224 */             ProtocolSocketFactory fcty = new MySecureProtocolSocketFactory();
/*  5225 */             Protocol.registerProtocol("https", new Protocol("https", fcty, 443));
/*  5226 */             HttpClient client = new HttpClient();
/*       */ 
/*  5228 */             PostMethod method = new PostMethod("https://open.ys7.com:443/api/method");
/*       */ 
/*  5231 */             label659: 
/*       */             try { RequestEntity entity = new StringRequestEntity(json, "application/json", "UTF-8");
/*  5232 */               method.setRequestEntity(entity);
/*  5233 */               client.executeMethod(method);
/*       */ 
/*  5235 */               InputStream inputStream = method.getResponseBodyAsStream();
/*  5236 */               String restult = IOUtils.toString(inputStream);
/*       */ 
/*  5238 */               Object o = JSONUtil.deserialize(restult);
/*  5239 */               Map o1 = (HashMap)o;
/*  5240 */               Map o2 = (HashMap)o1.get("result");
/*  5241 */               String code = (String)o2.get("code");
/*       */ 
/*  5243 */               if (code.equals("200")) {
/*  5244 */                 Map o3 = (HashMap)o2.get("data");
/*  5245 */                 o4 = (String)o3.get("accessToken");
/*  5246 */                 boUsers.setFluoriteAccessToken(o4);
/*  5247 */                 boUsers.setFluoriteAccessTokenExpireTime(fluoriteAccessTokenTime_o.toString());
/*  5248 */                 BoUsers update = (BoUsers)this.boUserServicess.update(boUsers);
/*  5249 */                 if (update != null) {
/*  5250 */                   Thread.sleep(3000L);
/*  5251 */                   map.put("Eztoken", update.getFluoriteAccessToken());
/*  5252 */                   this.requestJson.setData(map);
/*  5253 */                   this.requestJson.setSuccess(true);
/*       */ 
/*  5255 */                   break label659;
/*       */                 } } else { map.put("Eztoken", "NO_BUNDING");
/*  5257 */                 this.requestJson.setData(map);
/*  5258 */                 this.requestJson.setSuccess(true);
/*       */               }
/*       */             }
/*       */             catch (Exception e)
/*       */             {
/*  5263 */               e.printStackTrace();
/*       */             }
/*       */             finally
/*       */             {
/*  5267 */               method.releaseConnection();
/*       */             }
/*       */ 
/*       */           }
/*       */           else
/*       */           {
/*  5273 */             System.err.println("AToken时间戳超时了");
/*  5274 */             this.requestJson.setData(map);
/*  5275 */             this.requestJson.setMessage("超时了");
/*  5276 */             this.requestJson.setSuccess(false);
/*       */           }
/*       */         } else {
/*  5279 */           System.err.println("AToken超时了");
/*  5280 */           this.requestJson.setData(map);
/*  5281 */           this.requestJson.setMessage("超时了");
/*  5282 */           this.requestJson.setSuccess(false);
/*       */         }
/*       */       }
/*       */       else {
/*  5286 */         System.err.println("验证不通过");
/*  5287 */         this.requestJson.setData(map);
/*  5288 */         this.requestJson.setMessage("验证不通过");
/*  5289 */         this.requestJson.setSuccess(false);
/*       */       }
/*       */     } else {
/*  5292 */       Boolean ral = isRal(header, header2, header3, header4, userCode, "修改主机昵称");
/*  5293 */       if (ral.booleanValue()) {
/*  5294 */         System.err.println("验证通过");
/*  5295 */         Long accessToken = Long.valueOf(header);
/*  5296 */         System.err.println(userCode);
/*  5297 */         BoUsers boUsers = this.boUserServicess.findByUserUserCode(userCode);
/*  5298 */         if (boUsers == null) {
/*  5299 */           this.requestJson.setData(map);
/*  5300 */           this.requestJson.setMessage("Invalid_User");
/*  5301 */           this.requestJson.setSuccess(true);
/*       */         }
/*  5303 */         else if (accessToken.longValue() < Long.valueOf(boUsers.getAccessTokenTime()).longValue()) {
/*  5304 */           Long fluoriteAccessTokenTime = Long.valueOf(518400L);
/*  5305 */           Long fluoriteAccessTokenTime_o = Long.valueOf((int)(System.currentTimeMillis() / 1000L) + fluoriteAccessTokenTime.longValue());
/*  5306 */           String o4 = null;
/*  5307 */           Map paramsMap = new HashMap();
/*  5308 */           paramsMap.put("phone", boUsers.getUserPhone());
/*  5309 */           Map maps = YZUitl.paramsInit("token/getAccessToken", paramsMap);
/*  5310 */           String json = JSON.toJSONString(maps);
/*  5311 */           ProtocolSocketFactory fcty = new MySecureProtocolSocketFactory();
/*  5312 */           Protocol.registerProtocol("https", new Protocol("https", fcty, 443));
/*  5313 */           HttpClient client = new HttpClient();
/*       */ 
/*  5315 */           PostMethod method = new PostMethod("https://open.ys7.com:443/api/method");
/*       */ 
/*  5318 */           label1287: 
/*       */           try { RequestEntity entity = new StringRequestEntity(json, "application/json", "UTF-8");
/*  5319 */             method.setRequestEntity(entity);
/*  5320 */             client.executeMethod(method);
/*       */ 
/*  5322 */             InputStream inputStream = method.getResponseBodyAsStream();
/*  5323 */             String restult = IOUtils.toString(inputStream);
/*       */ 
/*  5325 */             Object o = JSONUtil.deserialize(restult);
/*  5326 */             Map o1 = (HashMap)o;
/*  5327 */             Map o2 = (HashMap)o1.get("result");
/*  5328 */             String code = (String)o2.get("code");
/*       */ 
/*  5330 */             if (code.equals("200")) {
/*  5331 */               Map o3 = (HashMap)o2.get("data");
/*  5332 */               o4 = (String)o3.get("accessToken");
/*  5333 */               boUsers.setFluoriteAccessToken(o4);
/*  5334 */               boUsers.setFluoriteAccessTokenExpireTime(fluoriteAccessTokenTime_o.toString());
/*  5335 */               BoUsers update = (BoUsers)this.boUserServicess.update(boUsers);
/*  5336 */               if (update != null) {
/*  5337 */                 Thread.sleep(3000L);
/*  5338 */                 map.put("Eztoken", update.getFluoriteAccessToken());
/*  5339 */                 this.requestJson.setData(map);
/*  5340 */                 this.requestJson.setSuccess(true);
/*       */ 
/*  5342 */                 break label1287;
/*       */               } } else { map.put("Eztoken", "NO_BUNDING");
/*  5344 */               this.requestJson.setData(map);
/*  5345 */               this.requestJson.setSuccess(true);
/*       */             }
/*       */           }
/*       */           catch (Exception e)
/*       */           {
/*  5350 */             e.printStackTrace();
/*       */           }
/*       */           finally
/*       */           {
/*  5354 */             method.releaseConnection();
/*       */           }
/*       */ 
/*       */         }
/*       */         else
/*       */         {
/*  5360 */           System.err.println("超时了");
/*  5361 */           this.requestJson.setData(map);
/*  5362 */           this.requestJson.setMessage("超时了");
/*  5363 */           this.requestJson.setSuccess(false);
/*       */         }
/*       */       }
/*       */       else {
/*  5367 */         System.err.println("验证不通过");
/*  5368 */         this.requestJson.setData(map);
/*  5369 */         this.requestJson.setMessage("验证不通过");
/*  5370 */         this.requestJson.setSuccess(false);
/*       */       }
/*       */     }
/*  5373 */     return "success";
/*       */   }
/*       */ 
/*       */   @Action(value="gainEzTokens", results={@org.apache.struts2.convention.annotation.Result(type="json", params={"root", "requestJson"})})
/*       */   public String gainEzTokens()
/*       */   {
/*  5383 */     this.requestJson = new RequestJson();
/*  5384 */     HttpServletRequest request = ServletActionContext.getRequest();
/*  5385 */     Map map = new HashMap();
/*  5386 */     String header = request.getHeader("timestamp");
/*  5387 */     String header2 = request.getHeader("nonce");
/*  5388 */     String header3 = request.getHeader("sign");
/*  5389 */     String header4 = request.getHeader("access_token");
/*  5390 */     String userCode = request.getHeader("userCode");
/*  5391 */     if (userCode.contains(",")) {
/*  5392 */       String[] userCode2 = userCode.split(",");
/*  5393 */       BoUsers boUsers = this.boUserServicess.findByUserUserCode(userCode2[0].trim().toString());
/*  5394 */       BoUsers phone = this.boUserServicess.findByUserPhone(userCode2[1].trim().toString());
/*  5395 */       Boolean ral = isRal(header, header2, header3, header4, userCode, "获取萤石Token");
/*  5396 */       if (ral.booleanValue()) {
/*  5397 */         System.err.println("验证通过");
/*       */ 
/*  5399 */         System.err.println("userCode" + userCode2[0].trim().toString());
/*       */ 
/*  5401 */         if ((phone == null) || (boUsers == null)) {
/*  5402 */           this.requestJson.setData(map);
/*  5403 */           this.requestJson.setMessage("Invalid_User");
/*  5404 */           this.requestJson.setSuccess(true);
/*       */         } else {
/*  5406 */           System.err.println("userCode" + userCode2[0].trim().toString());
/*  5407 */           Long fluoriteAccessTokenTime = Long.valueOf(518400L);
/*  5408 */           Long fluoriteAccessTokenTime_o = Long.valueOf((int)(System.currentTimeMillis() / 1000L) + fluoriteAccessTokenTime.longValue());
/*  5409 */           String o4 = null;
/*  5410 */           Map paramsMap = new HashMap();
/*  5411 */           paramsMap.put("phone", boUsers.getUserPhone());
/*  5412 */           Map maps = YZUitl.paramsInit("token/getAccessToken", paramsMap);
/*  5413 */           String json = JSON.toJSONString(maps);
/*  5414 */           ProtocolSocketFactory fcty = new MySecureProtocolSocketFactory();
/*  5415 */           Protocol.registerProtocol("https", new Protocol("https", fcty, 443));
/*  5416 */           HttpClient client = new HttpClient();
/*       */ 
/*  5418 */           PostMethod method = new PostMethod("https://open.ys7.com:443/api/method");
/*       */ 
/*  5420 */           label662: 
/*       */           try { RequestEntity entity = new StringRequestEntity(json, "application/json", "UTF-8");
/*  5421 */             method.setRequestEntity(entity);
/*  5422 */             client.executeMethod(method);
/*       */ 
/*  5424 */             InputStream inputStream = method.getResponseBodyAsStream();
/*  5425 */             String restult = IOUtils.toString(inputStream);
/*       */ 
/*  5427 */             Object o = JSONUtil.deserialize(restult);
/*  5428 */             Map o1 = (HashMap)o;
/*  5429 */             Map o2 = (HashMap)o1.get("result");
/*  5430 */             String code = (String)o2.get("code");
/*       */ 
/*  5432 */             if (code.equals("200")) {
/*  5433 */               Map o3 = (HashMap)o2.get("data");
/*  5434 */               o4 = (String)o3.get("accessToken");
/*  5435 */               boUsers.setFluoriteAccessToken(o4);
/*  5436 */               boUsers.setFluoriteAccessTokenExpireTime(fluoriteAccessTokenTime_o.toString());
/*  5437 */               BoUsers update = (BoUsers)this.boUserServicess.update(boUsers);
/*  5438 */               if (update != null) {
/*  5439 */                 map.put("Eztoken", update.getFluoriteAccessToken());
/*  5440 */                 this.requestJson.setData(map);
/*  5441 */                 this.requestJson.setSuccess(true);
/*       */ 
/*  5443 */                 break label662;
/*       */               } } else { map.put("Eztoken", "NO_BUNDING");
/*  5445 */               this.requestJson.setData(map);
/*       */ 
/*  5447 */               this.requestJson.setSuccess(true);
/*       */             }
/*       */           }
/*       */           catch (Exception e)
/*       */           {
/*  5452 */             e.printStackTrace();
/*       */           }
/*       */           finally
/*       */           {
/*  5456 */             method.releaseConnection();
/*       */           }
/*       */         }
/*       */       } else {
/*  5460 */         System.err.println("验证不通过");
/*  5461 */         this.requestJson.setData(map);
/*  5462 */         this.requestJson.setMessage("验证不通过");
/*  5463 */         this.requestJson.setSuccess(false);
/*       */       }
/*       */     } else {
/*  5466 */       Boolean ral = isRal(header, header2, header3, header4, userCode, "修改主机昵称");
/*  5467 */       if (ral.booleanValue()) {
/*  5468 */         System.err.println("验证通过");
/*       */ 
/*  5470 */         System.err.println("userCode" + userCode);
/*  5471 */         BoUsers boUsers = this.boUserServicess.findByUserUserCode(userCode);
/*  5472 */         if (boUsers == null) {
/*  5473 */           this.requestJson.setData(map);
/*  5474 */           this.requestJson.setMessage("Invalid_User");
/*  5475 */           this.requestJson.setSuccess(true);
/*       */         } else {
/*  5477 */           System.err.println("userCode" + userCode);
/*  5478 */           Long fluoriteAccessTokenTime = Long.valueOf(518400L);
/*  5479 */           Long fluoriteAccessTokenTime_o = Long.valueOf((int)(System.currentTimeMillis() / 1000L) + fluoriteAccessTokenTime.longValue());
/*  5480 */           String o4 = null;
/*  5481 */           Map paramsMap = new HashMap();
/*  5482 */           paramsMap.put("phone", boUsers.getUserPhone());
/*  5483 */           Map maps = YZUitl.paramsInit("token/getAccessToken", paramsMap);
/*  5484 */           String json = JSON.toJSONString(maps);
/*  5485 */           ProtocolSocketFactory fcty = new MySecureProtocolSocketFactory();
/*  5486 */           Protocol.registerProtocol("https", new Protocol("https", fcty, 443));
/*  5487 */           HttpClient client = new HttpClient();
/*       */ 
/*  5489 */           PostMethod method = new PostMethod("https://open.ys7.com:443/api/method");
/*       */ 
/*  5491 */           label1222: 
/*       */           try { RequestEntity entity = new StringRequestEntity(json, "application/json", "UTF-8");
/*  5492 */             method.setRequestEntity(entity);
/*  5493 */             client.executeMethod(method);
/*       */ 
/*  5495 */             InputStream inputStream = method.getResponseBodyAsStream();
/*  5496 */             String restult = IOUtils.toString(inputStream);
/*       */ 
/*  5498 */             Object o = JSONUtil.deserialize(restult);
/*  5499 */             Map o1 = (HashMap)o;
/*  5500 */             Map o2 = (HashMap)o1.get("result");
/*  5501 */             String code = (String)o2.get("code");
/*       */ 
/*  5503 */             if (code.equals("200")) {
/*  5504 */               Map o3 = (HashMap)o2.get("data");
/*  5505 */               o4 = (String)o3.get("accessToken");
/*  5506 */               boUsers.setFluoriteAccessToken(o4);
/*  5507 */               boUsers.setFluoriteAccessTokenExpireTime(fluoriteAccessTokenTime_o.toString());
/*  5508 */               BoUsers update = (BoUsers)this.boUserServicess.update(boUsers);
/*  5509 */               if (update != null) {
/*  5510 */                 map.put("Eztoken", update.getFluoriteAccessToken());
/*  5511 */                 this.requestJson.setData(map);
/*  5512 */                 this.requestJson.setSuccess(true);
/*       */ 
/*  5514 */                 break label1222;
/*       */               } } else { map.put("Eztoken", "NO_BUNDING");
/*  5516 */               this.requestJson.setData(map);
/*       */ 
/*  5518 */               this.requestJson.setSuccess(true);
/*       */             }
/*       */           }
/*       */           catch (Exception e)
/*       */           {
/*  5523 */             e.printStackTrace();
/*       */           }
/*       */           finally
/*       */           {
/*  5527 */             method.releaseConnection();
/*       */           }
/*       */         }
/*       */       } else {
/*  5531 */         System.err.println("验证不通过");
/*  5532 */         this.requestJson.setData(map);
/*  5533 */         this.requestJson.setMessage("验证不通过");
/*  5534 */         this.requestJson.setSuccess(false);
/*       */       }
/*       */     }
/*  5537 */     return "success";
/*       */   }
/*       */ 
/*       */   @Action(value="gaineztoken", results={@org.apache.struts2.convention.annotation.Result(type="json", params={"root", "requestJson"})})
/*       */   public String gainEzToken()
/*       */   {
/*  5546 */     this.requestJson = new RequestJson();
/*  5547 */     HttpServletRequest request = ServletActionContext.getRequest();
/*  5548 */     Map map = new HashMap();
/*  5549 */     String header = request.getHeader("timestamp");
/*  5550 */     String header2 = request.getHeader("nonce");
/*  5551 */     String header3 = request.getHeader("sign");
/*  5552 */     String header4 = request.getHeader("access_token");
/*  5553 */     String userCode = request.getHeader("userCode");
/*  5554 */     if (userCode.contains(",")) {
/*  5555 */       String[] userCode2 = userCode.split(",");
/*  5556 */       BoUsers boUsers = this.boUserServicess.findByUserUserCode(userCode2[0].trim().toString());
/*  5557 */       BoUsers phone = this.boUserServicess.findByUserPhone(userCode2[1].trim().toString());
/*  5558 */       Boolean ral = isRal(header, header2, header3, header4, userCode, "修改主机昵称");
/*  5559 */       if (ral.booleanValue()) {
/*  5560 */         System.err.println("验证通过");
/*  5561 */         Long accessToken = Long.valueOf(header);
/*  5562 */         System.err.println(userCode2[0].trim().toString());
/*       */ 
/*  5564 */         if ((phone == null) || (boUsers == null)) {
/*  5565 */           this.requestJson.setData(map);
/*  5566 */           this.requestJson.setMessage("Invalid_User");
/*  5567 */           this.requestJson.setSuccess(true);
/*       */         }
/*  5569 */         else if (header4.equals(phone.getAccessToken())) {
/*  5570 */           if (accessToken.longValue() < Long.valueOf(phone.getAccessTokenTime()).longValue()) {
/*  5571 */             String fluoriteAccessTokenExpireTime = boUsers.getFluoriteAccessTokenExpireTime();
/*       */             String EZ;
///*       */             String EZ;
/*  5573 */             if (fluoriteAccessTokenExpireTime.equals(""))
/*  5574 */               EZ = System.currentTimeMillis() / 1000L+"";
/*       */             else {
/*  5576 */               EZ = fluoriteAccessTokenExpireTime;
/*       */             }
/*       */ 
/*  5579 */             if ((int)(System.currentTimeMillis() / 1000L) < Long.valueOf(EZ).longValue()) {
/*  5580 */               map.put("Eztoken", boUsers.getFluoriteAccessToken());
/*  5581 */               this.requestJson.setData(map);
/*  5582 */               this.requestJson.setSuccess(true);
/*       */             } else {
/*  5584 */               Long fluoriteAccessTokenTime = Long.valueOf(518400L);
/*  5585 */               Long fluoriteAccessTokenTime_o = Long.valueOf((int)(System.currentTimeMillis() / 1000L) + fluoriteAccessTokenTime.longValue());
/*  5586 */               String o4 = null;
/*  5587 */               Map paramsMap = new HashMap();
/*  5588 */               paramsMap.put("phone", boUsers.getUserPhone());
/*  5589 */               Map maps = YZUitl.paramsInit("token/getAccessToken", paramsMap);
/*  5590 */               String json = JSON.toJSONString(maps);
/*  5591 */               ProtocolSocketFactory fcty = new MySecureProtocolSocketFactory();
/*  5592 */               Protocol.registerProtocol("https", new Protocol("https", fcty, 443));
/*  5593 */               HttpClient client = new HttpClient();
/*       */ 
/*  5595 */               PostMethod method = new PostMethod("https://open.ys7.com:443/api/method");
/*       */ 
/*  5598 */               label756: 
/*       */               try { RequestEntity entity = new StringRequestEntity(json, "application/json", "UTF-8");
/*  5599 */                 method.setRequestEntity(entity);
/*  5600 */                 client.executeMethod(method);
/*       */ 
/*  5602 */                 InputStream inputStream = method.getResponseBodyAsStream();
/*  5603 */                 String restult = IOUtils.toString(inputStream);
/*       */ 
/*  5605 */                 Object o = JSONUtil.deserialize(restult);
/*  5606 */                 Map o1 = (HashMap)o;
/*  5607 */                 Map o2 = (HashMap)o1.get("result");
/*  5608 */                 String code = (String)o2.get("code");
/*       */ 
/*  5610 */                 if (code.equals("200")) {
/*  5611 */                   Map o3 = (HashMap)o2.get("data");
/*  5612 */                   o4 = (String)o3.get("accessToken");
/*  5613 */                   boUsers.setFluoriteAccessToken(o4);
/*  5614 */                   boUsers.setFluoriteAccessTokenExpireTime(fluoriteAccessTokenTime_o.toString());
/*  5615 */                   BoUsers update = (BoUsers)this.boUserServicess.update(boUsers);
/*  5616 */                   if (update != null) {
/*  5617 */                     map.put("Eztoken", update.getFluoriteAccessToken());
/*  5618 */                     this.requestJson.setData(map);
/*  5619 */                     this.requestJson.setSuccess(true);
/*       */ 
/*  5621 */                     break label756;
/*       */                   } } else { map.put("Eztoken", "NO_BUNDING");
/*  5623 */                   this.requestJson.setData(map);
/*       */ 
/*  5625 */                   this.requestJson.setSuccess(true);
/*       */                 }
/*       */               }
/*       */               catch (Exception e)
/*       */               {
/*  5630 */                 e.printStackTrace();
/*       */               }
/*       */               finally
/*       */               {
/*  5634 */                 method.releaseConnection();
/*       */               }
/*       */             }
/*       */ 
/*       */           }
/*       */           else
/*       */           {
/*  5641 */             System.err.println("AToken时间戳超时了");
/*  5642 */             this.requestJson.setData(map);
/*  5643 */             this.requestJson.setMessage("超时了");
/*  5644 */             this.requestJson.setSuccess(false);
/*       */           }
/*       */         } else {
/*  5647 */           System.err.println("AToken超时了");
/*  5648 */           this.requestJson.setData(map);
/*  5649 */           this.requestJson.setMessage("超时了");
/*  5650 */           this.requestJson.setSuccess(false);
/*       */         }
/*       */       }
/*       */       else {
/*  5654 */         System.err.println("验证不通过");
/*  5655 */         this.requestJson.setData(map);
/*  5656 */         this.requestJson.setMessage("验证不通过");
/*  5657 */         this.requestJson.setSuccess(false);
/*       */       }
/*       */     } else {
/*  5660 */       Boolean ral = isRal(header, header2, header3, header4, userCode, "修改主机昵称");
/*  5661 */       if (ral.booleanValue()) {
/*  5662 */         System.err.println("验证通过");
/*  5663 */         Long accessToken = Long.valueOf(header);
/*  5664 */         System.err.println(userCode);
/*  5665 */         BoUsers boUsers = this.boUserServicess.findByUserUserCode(userCode);
/*  5666 */         if (boUsers == null) {
/*  5667 */           this.requestJson.setData(map);
/*  5668 */           this.requestJson.setMessage("Invalid_User");
/*  5669 */           this.requestJson.setSuccess(true);
/*       */         }
/*  5671 */         else if (accessToken.longValue() < Long.valueOf(boUsers.getAccessTokenTime()).longValue()) {
/*  5672 */           String fluoriteAccessTokenExpireTime = boUsers.getFluoriteAccessTokenExpireTime();
/*       */           String EZ;
///*       */           String EZ;
/*  5674 */           if (fluoriteAccessTokenExpireTime.equals(""))
/*  5675 */             EZ = System.currentTimeMillis() / 1000L+"";
/*       */           else {
/*  5677 */             EZ = fluoriteAccessTokenExpireTime;
/*       */           }
/*       */ 
/*  5680 */           if ((int)(System.currentTimeMillis() / 1000L) < Long.valueOf(EZ).longValue()) {
/*  5681 */             map.put("Eztoken", boUsers.getFluoriteAccessToken());
/*  5682 */             this.requestJson.setData(map);
/*  5683 */             this.requestJson.setSuccess(true);
/*       */           } else {
/*  5685 */             Long fluoriteAccessTokenTime = Long.valueOf(518400L);
/*  5686 */             Long fluoriteAccessTokenTime_o = Long.valueOf((int)(System.currentTimeMillis() / 1000L) + fluoriteAccessTokenTime.longValue());
/*  5687 */             String o4 = null;
/*  5688 */             Map paramsMap = new HashMap();
/*  5689 */             paramsMap.put("phone", boUsers.getUserPhone());
/*  5690 */             Map maps = YZUitl.paramsInit("token/getAccessToken", paramsMap);
/*  5691 */             String json = JSON.toJSONString(maps);
/*  5692 */             ProtocolSocketFactory fcty = new MySecureProtocolSocketFactory();
/*  5693 */             Protocol.registerProtocol("https", new Protocol("https", fcty, 443));
/*  5694 */             HttpClient client = new HttpClient();
/*       */ 
/*  5696 */             PostMethod method = new PostMethod("https://open.ys7.com:443/api/method");
/*       */ 
/*  5699 */             label1481: 
/*       */             try { RequestEntity entity = new StringRequestEntity(json, "application/json", "UTF-8");
/*  5700 */               method.setRequestEntity(entity);
/*  5701 */               client.executeMethod(method);
/*       */ 
/*  5703 */               InputStream inputStream = method.getResponseBodyAsStream();
/*  5704 */               String restult = IOUtils.toString(inputStream);
/*       */ 
/*  5706 */               Object o = JSONUtil.deserialize(restult);
/*  5707 */               Map o1 = (HashMap)o;
/*  5708 */               Map o2 = (HashMap)o1.get("result");
/*  5709 */               String code = (String)o2.get("code");
/*       */ 
/*  5711 */               if (code.equals("200")) {
/*  5712 */                 Map o3 = (HashMap)o2.get("data");
/*  5713 */                 o4 = (String)o3.get("accessToken");
/*  5714 */                 boUsers.setFluoriteAccessToken(o4);
/*  5715 */                 boUsers.setFluoriteAccessTokenExpireTime(fluoriteAccessTokenTime_o.toString());
/*  5716 */                 BoUsers update = (BoUsers)this.boUserServicess.update(boUsers);
/*  5717 */                 if (update != null) {
/*  5718 */                   map.put("Eztoken", update.getFluoriteAccessToken());
/*  5719 */                   this.requestJson.setData(map);
/*  5720 */                   this.requestJson.setSuccess(true);
/*       */ 
/*  5722 */                   break label1481;
/*       */                 } } else { map.put("Eztoken", "NO_BUNDING");
/*  5724 */                 this.requestJson.setData(map);
/*       */ 
/*  5726 */                 this.requestJson.setSuccess(true);
/*       */               }
/*       */             }
/*       */             catch (Exception e)
/*       */             {
/*  5731 */               e.printStackTrace();
/*       */             }
/*       */             finally
/*       */             {
/*  5735 */               method.releaseConnection();
/*       */             }
/*       */           }
/*       */ 
/*       */         }
/*       */         else
/*       */         {
/*  5742 */           System.err.println("超时了");
/*  5743 */           this.requestJson.setData(map);
/*  5744 */           this.requestJson.setMessage("超时了");
/*  5745 */           this.requestJson.setSuccess(false);
/*       */         }
/*       */       }
/*       */       else {
/*  5749 */         System.err.println("验证不通过");
/*  5750 */         this.requestJson.setData(map);
/*  5751 */         this.requestJson.setMessage("验证不通过");
/*  5752 */         this.requestJson.setSuccess(false);
/*       */       }
/*       */     }
/*  5755 */     return "success";
/*       */   }
/*       */ 
/*       */   @Action(value="updateSensorsPram", results={@org.apache.struts2.convention.annotation.Result(type="json", params={"root", "requestJson"})})
/*       */   public String updateSensorsPram()
/*       */   {
/*  5764 */     this.requestJson = new RequestJson();
/*  5765 */     Map map = new HashMap();
/*  5766 */     HttpServletRequest request = ServletActionContext.getRequest();
/*  5767 */     String header = request.getHeader("timestamp");
/*  5768 */     String header2 = request.getHeader("nonce");
/*  5769 */     String header3 = request.getHeader("sign");
/*  5770 */     String header4 = request.getHeader("access_token");
/*  5771 */     String userCode = request.getHeader("userCode");
/*  5772 */     if (userCode.contains(",")) {
/*  5773 */       String[] userCode2 = userCode.split(",");
/*  5774 */       BoUsers boUsers = this.boUserServicess.findByUserUserCode(userCode2[0].trim().toString());
/*  5775 */       BoUsers phone = this.boUserServicess.findByUserPhone(userCode2[1].trim().toString());
/*       */ 
/*  5777 */       Boolean ral = isRal(header, header2, header3, header4, userCode, "修改传感器或情景面板配置参数");
/*  5778 */       if (ral.booleanValue()) {
/*  5779 */         System.err.println("验证通过");
/*  5780 */         Long accessToken = Long.valueOf(header);
/*       */ 
/*  5782 */         if ((phone == null) || (boUsers == null)) {
/*  5783 */           this.requestJson.setData(map);
/*  5784 */           this.requestJson.setMessage("Invalid_User");
/*  5785 */           this.requestJson.setSuccess(true);
/*       */         }
/*  5787 */         else if (header4.equals(phone.getAccessToken())) {
/*  5788 */           if (accessToken.longValue() < Long.valueOf(phone.getAccessTokenTime()).longValue()) {
/*  5789 */             BoModel boModel = this.boModelService.find(userCode2[0].trim().toString(), this.modelId);
/*  5790 */             BoSensor boSensor = this.boSensorService.findBydeviceAddress(userCode2[0].trim().toString(), this.deviceCode, this.deviceAddress, this.patternType);
/*  5791 */             if (boSensor == null) {
/*  5792 */               this.requestJson.setData(map);
/*  5793 */               this.requestJson.setMessage("没有找到");
/*  5794 */               this.requestJson.setSuccess(true);
/*       */             } else {
/*  5796 */               boSensor.setBoModel(boModel);
/*  5797 */               boSensor.setDeviceType(this.deviceType);
/*  5798 */               boSensor.setPushContent(this.pushContent);
/*  5799 */               boSensor.setNickName(this.nickName);
/*  5800 */               BoSensor update = (BoSensor)this.boSensorService.update(boSensor);
/*  5801 */               if (update == null) {
/*  5802 */                 this.requestJson.setData(map);
/*  5803 */                 this.requestJson.setMessage("修改成功");
/*  5804 */                 this.requestJson.setSuccess(true);
/*       */               } else {
/*  5806 */                 this.requestJson.setData(map);
/*  5807 */                 this.requestJson.setMessage("修改失败");
/*  5808 */                 this.requestJson.setSuccess(true);
/*       */               }
/*       */             }
/*       */           } else {
/*  5812 */             System.err.println("AToken时间戳超时了");
/*  5813 */             this.requestJson.setData(map);
/*  5814 */             this.requestJson.setMessage("超时了");
/*  5815 */             this.requestJson.setSuccess(false);
/*       */           }
/*       */         } else {
/*  5818 */           System.err.println("AToken超时了");
/*  5819 */           this.requestJson.setData(map);
/*  5820 */           this.requestJson.setMessage("超时了");
/*  5821 */           this.requestJson.setSuccess(false);
/*       */         }
/*       */       }
/*       */       else {
/*  5825 */         System.err.println("验证不通过");
/*  5826 */         this.requestJson.setData(map);
/*  5827 */         this.requestJson.setMessage("验证不通过");
/*  5828 */         this.requestJson.setSuccess(false);
/*       */       }
/*       */     } else {
/*  5831 */       Boolean ral = isRal(header, header2, header3, header4, userCode, "修改主机昵称");
/*  5832 */       if (ral.booleanValue()) {
/*  5833 */         System.err.println("验证通过");
/*  5834 */         Long accessToken = Long.valueOf(header);
/*  5835 */         BoUsers boUsers = this.boUserServicess.findByUserUserCode(userCode);
/*  5836 */         if (boUsers == null) {
/*  5837 */           this.requestJson.setData(map);
/*  5838 */           this.requestJson.setMessage("Invalid_User");
/*  5839 */           this.requestJson.setSuccess(true);
/*       */         }
/*  5841 */         else if (accessToken.longValue() < Long.valueOf(boUsers.getAccessTokenTime()).longValue()) {
/*  5842 */           BoModel boModel = this.boModelService.find(userCode, this.modelId);
/*  5843 */           BoSensor boSensor = this.boSensorService.findBydeviceAddress(userCode, this.deviceCode, this.deviceAddress, this.patternType);
/*  5844 */           if (boSensor == null) {
/*  5845 */             this.requestJson.setData(map);
/*  5846 */             this.requestJson.setMessage("没有找到");
/*  5847 */             this.requestJson.setSuccess(true);
/*       */           } else {
/*  5849 */             boSensor.setBoModel(boModel);
/*  5850 */             boSensor.setDeviceType(this.deviceType);
/*  5851 */             boSensor.setPushContent(this.pushContent);
/*  5852 */             boSensor.setNickName(this.nickName);
/*  5853 */             BoSensor update = (BoSensor)this.boSensorService.update(boSensor);
/*  5854 */             if (update == null) {
/*  5855 */               this.requestJson.setData(map);
/*  5856 */               this.requestJson.setMessage("修改成功");
/*  5857 */               this.requestJson.setSuccess(true);
/*       */             } else {
/*  5859 */               this.requestJson.setData(map);
/*  5860 */               this.requestJson.setMessage("修改失败");
/*  5861 */               this.requestJson.setSuccess(true);
/*       */             }
/*       */           }
/*       */         } else {
/*  5865 */           this.requestJson.setData(map);
/*  5866 */           this.requestJson.setMessage("超时了");
/*  5867 */           this.requestJson.setSuccess(false);
/*       */         }
/*       */       }
/*       */       else {
/*  5871 */         System.err.println("验证不通过");
/*  5872 */         this.requestJson.setData(map);
/*  5873 */         this.requestJson.setMessage("验证不通过");
/*  5874 */         this.requestJson.setSuccess(false);
/*       */       }
/*       */     }
/*  5877 */     return "success";
/*       */   }
/*       */ 
/*       */   @Action(value="gainAloneSensors", results={@org.apache.struts2.convention.annotation.Result(type="json", params={"root", "requestJson"})})
/*       */   public String gainAloneSensors()
/*       */   {
/*  5885 */     this.requestJson = new RequestJson();
/*  5886 */     Map map = new HashMap();
/*  5887 */     HttpServletRequest request = ServletActionContext.getRequest();
/*  5888 */     String header = request.getHeader("timestamp");
/*  5889 */     String header2 = request.getHeader("nonce");
/*  5890 */     String header3 = request.getHeader("sign");
/*  5891 */     String header4 = request.getHeader("access_token");
/*  5892 */     String userCode = request.getHeader("userCode");
/*  5893 */     if (userCode.contains(",")) {
/*  5894 */       String[] userCode2 = userCode.split(",");
/*  5895 */       BoUsers boUsers = this.boUserServicess.findByUserUserCode(userCode2[0].trim().toString());
/*  5896 */       BoUsers phone = this.boUserServicess.findByUserPhone(userCode2[1].trim().toString());
/*  5897 */       Boolean ral = isRal(header, header2, header3, header4, userCode, "获取单独传感器或情景面板");
/*  5898 */       if (ral.booleanValue()) {
/*  5899 */         System.err.println("验证通过");
/*  5900 */         Long accessToken = Long.valueOf(header);
/*       */ 
/*  5902 */         if ((phone == null) || (boUsers == null)) {
/*  5903 */           this.requestJson.setData(map);
/*  5904 */           this.requestJson.setMessage("Invalid_User");
/*  5905 */           this.requestJson.setSuccess(true);
/*       */         }
/*  5907 */         else if (header4.equals(phone.getAccessToken())) {
/*  5908 */           if (accessToken.longValue() < Long.valueOf(phone.getAccessTokenTime()).longValue()) {
/*       */             try {
/*  5910 */               BoUserDevices userDevices = this.boUserDevicesServicess.findBy(userCode2[0].trim().toString(), this.deviceCode);
/*  5911 */               BoSensor boSensor = this.boSensorService.findBydeviceAddress(userCode2[0].trim().toString(), this.deviceCode, this.deviceAddress, this.patternType);
/*  5912 */               if (boSensor == null) {
/*  5913 */                 this.requestJson.setData(map);
/*  5914 */                 this.requestJson.setMessage("没有找到");
/*  5915 */                 this.requestJson.setSuccess(true);
/*       */               } else {
/*  5917 */                 map.put("deviceCode", boSensor.getBoDevice().getDeviceCode().toString());
/*       */                 String modelName;
/*       */                 String modelId;
///*       */                 String modelName;
/*  5920 */                 if (boSensor.getBoModel() == null) {
/*  5921 */                   modelId = "";
/*  5922 */                   modelName = "";
/*       */                 } else {
/*  5924 */                   modelId = boSensor.getBoModel().getModelId().toString();
/*  5925 */                   modelName = boSensor.getBoModel().getName().toString();
/*       */                 }
/*  5927 */                 map.put("modelId", modelId);
/*  5928 */                 map.put("modelName", modelName);
/*  5929 */                 map.put("pushContent", boSensor.getPushContent());
/*  5930 */                 map.put("deviceAddress", boSensor.getDeviceAddress().toString());
/*  5931 */                 map.put("deviceCode", boSensor.getBoDevice().getDeviceCode().toString());
/*  5932 */                 map.put("status", boSensor.getBoDevice().getStatus().toString());
/*  5933 */                 map.put("deviceNickName", userDevices.getNickName().toString());
/*  5934 */                 map.put("nickName", boSensor.getNickName().toString());
/*  5935 */                 map.put("deviceType", boSensor.getDeviceType().toString());
/*  5936 */                 this.requestJson.setData(map);
/*       */               }
/*       */             }
/*       */             catch (Exception e) {
/*  5940 */               logger.info("errsds错误" + e.getMessage());
/*  5941 */               e.printStackTrace();
/*  5942 */               this.requestJson.setData(map);
/*  5943 */               this.requestJson.setMessage("服务器发生异常");
/*  5944 */               this.requestJson.setSuccess(false);
/*       */             }
/*       */           } else {
/*  5947 */             System.err.println("AToken时间戳超时了");
/*  5948 */             this.requestJson.setMessage("超时了");
/*  5949 */             this.requestJson.setSuccess(false);
/*       */           }
/*       */         } else {
/*  5952 */           System.err.println("AToken超时了");
/*  5953 */           this.requestJson.setMessage("超时了");
/*  5954 */           this.requestJson.setSuccess(false);
/*       */         }
/*       */       }
/*       */       else {
/*  5958 */         System.err.println("验证不通过");
/*  5959 */         this.requestJson.setMessage("验证不通过");
/*  5960 */         this.requestJson.setSuccess(false);
/*       */       }
/*       */     } else {
/*  5963 */       Boolean ral = isRal(header, header2, header3, header4, userCode, "修改主机昵称");
/*  5964 */       if (ral.booleanValue()) {
/*  5965 */         System.err.println("验证通过");
/*  5966 */         Long accessToken = Long.valueOf(header);
/*  5967 */         BoUsers boUsers = this.boUserServicess.findByUserUserCode(userCode);
/*  5968 */         if (boUsers == null) {
/*  5969 */           this.requestJson.setData(map);
/*  5970 */           this.requestJson.setMessage("Invalid_User");
/*  5971 */           this.requestJson.setSuccess(true);
/*       */         }
/*  5973 */         else if (accessToken.longValue() < Long.valueOf(boUsers.getAccessTokenTime()).longValue()) {
/*       */           try {
/*  5975 */             BoUserDevices userDevices = this.boUserDevicesServicess.findBy(userCode, this.deviceCode);
/*  5976 */             BoSensor boSensor = this.boSensorService.findBydeviceAddress(userCode, this.deviceCode, this.deviceAddress, this.patternType);
/*  5977 */             if (boSensor == null) {
/*  5978 */               this.requestJson.setData(map);
/*  5979 */               this.requestJson.setMessage("没有找到");
/*  5980 */               this.requestJson.setSuccess(true);
/*       */             } else {
/*  5982 */               map.put("deviceCode", boSensor.getBoDevice().getDeviceCode().toString());
/*       */               String modelName;
/*       */               String modelId;
///*       */               String modelName;
/*  5985 */               if (boSensor.getBoModel() == null) {
/*  5986 */                 modelId = "";
/*  5987 */                 modelName = "";
/*       */               } else {
/*  5989 */                 modelId = boSensor.getBoModel().getModelId().toString();
/*  5990 */                 modelName = boSensor.getBoModel().getName().toString();
/*       */               }
/*  5992 */               map.put("modelId", modelId);
/*  5993 */               map.put("modelName", modelName);
/*  5994 */               map.put("pushContent", boSensor.getPushContent());
/*  5995 */               map.put("deviceAddress", boSensor.getDeviceAddress().toString());
/*  5996 */               map.put("deviceCode", boSensor.getBoDevice().getDeviceCode().toString());
/*  5997 */               map.put("status", boSensor.getBoDevice().getStatus().toString());
/*  5998 */               map.put("deviceNickName", userDevices.getNickName().toString());
/*  5999 */               map.put("nickName", boSensor.getNickName().toString());
/*  6000 */               map.put("deviceType", boSensor.getDeviceType().toString());
/*  6001 */               this.requestJson.setData(map);
/*       */             }
/*       */           }
/*       */           catch (Exception e) {
/*  6005 */             logger.info("errsds错误" + e.getMessage());
/*  6006 */             this.requestJson.setData(map);
/*  6007 */             this.requestJson.setMessage("服务器发生异常");
/*  6008 */             this.requestJson.setSuccess(false);
/*       */           }
/*       */         } else {
/*  6011 */           this.requestJson.setData(map);
/*  6012 */           this.requestJson.setMessage("超时了");
/*  6013 */           this.requestJson.setSuccess(false);
/*       */         }
/*       */ 
/*       */       }
/*       */       else
/*       */       {
/*  6020 */         System.err.println("验证不通过");
/*  6021 */         this.requestJson.setMessage("验证不通过");
/*  6022 */         this.requestJson.setSuccess(false);
/*       */       }
/*       */     }
/*  6025 */     return "success";
/*       */   }
/*       */ 
/*       */   @Action(value="gainSensor", results={@org.apache.struts2.convention.annotation.Result(type="json", params={"root", "requestJson"})})
/*       */   public String gainSensor()
/*       */   {
/*  6034 */     this.requestJson = new RequestJson();
/*  6035 */     Map map = new HashMap();
/*  6036 */     HttpServletRequest request = ServletActionContext.getRequest();
/*  6037 */     String header = request.getHeader("timestamp");
/*  6038 */     String header2 = request.getHeader("nonce");
/*  6039 */     String header3 = request.getHeader("sign");
/*  6040 */     String header4 = request.getHeader("access_token");
/*  6041 */     String userCode = request.getHeader("userCode");
/*       */     List voList;
/*  6042 */     if (userCode.contains(",")) {
/*  6043 */       String[] userCode2 = userCode.split(",");
/*  6044 */       BoUsers boUsers = this.boUserServicess.findByUserUserCode(userCode2[0].trim().toString());
/*  6045 */       BoUsers phone = this.boUserServicess.findByUserPhone(userCode2[1].trim().toString());
/*  6046 */       Boolean ral = isRal(header, header2, header3, header4, userCode, "修改主机昵称");
/*  6047 */       if (ral.booleanValue()) {
/*  6048 */         System.err.println("验证通过");
/*  6049 */         Long accessToken = Long.valueOf(header);
/*       */ 
/*  6051 */         if ((phone == null) || (boUsers == null)) {
/*  6052 */           this.requestJson.setData(map);
/*  6053 */           this.requestJson.setMessage("Invalid_User");
/*  6054 */           this.requestJson.setSuccess(true);
/*       */         }
/*  6056 */         else if (header4.equals(phone.getAccessToken())) {
/*  6057 */           if (accessToken.longValue() < Long.valueOf(phone.getAccessTokenTime()).longValue()) {
/*  6058 */             List<BoSensor> list = this.boSensorService.getListByUserCodes(userCode2[0].trim().toString(), this.patternType);
/*  6059 */             if (list.size() <= 0) {
/*  6060 */               this.requestJson.setData(map);
/*  6061 */               this.requestJson.setMessage("没有传感器");
/*  6062 */               this.requestJson.setSuccess(true);
/*       */             } else {
/*  6064 */               voList = new ArrayList();
/*  6065 */               for (BoSensor boSensor : list) {
/*  6066 */                 Map boSensorMap = new HashMap();
/*  6067 */                 boSensorMap.put("patternType", boSensor.getType().toString());
/*  6068 */                 boSensorMap.put("pushContent", boSensor.getPushContent());
/*  6069 */                 boSensorMap.put("deviceType", boSensor.getDeviceType().toString());
/*  6070 */                 boSensorMap.put("deviceAddress", boSensor.getDeviceAddress().toString());
/*  6071 */                 boSensorMap.put("whetherControlEnclosure", boSensor.getIco().toString());
/*  6072 */                 String nickName2 = boSensor.getNickName();
/*       */                 String nickNames;

/*  6074 */                 if (nickName2 == null)
/*  6075 */                   nickNames = "";
/*       */                 else {
/*  6077 */                   nickNames = boSensor.getNickName();
/*       */                 }
/*  6079 */                 boSensor.getStartTimeOne().toString();
/*  6080 */                 boSensorMap.put("deviceCode", boSensor.getBoDevice().getDeviceCode().toString());
/*  6081 */                 boSensorMap.put("nickName", nickNames);
/*       */ 
/*  6083 */                 String StartTimeOne = null;
/*  6084 */                 String EndTimeOne = null;
/*  6085 */                 String StartTimeTwo = null;
/*  6086 */                 String EndTimeTwo = null;
/*  6087 */                 String StartTimeThree = null;
/*  6088 */                 String EndTimeThree = null;
/*  6089 */                 if (boSensor.getStartTimeOne().toString().length() == 1)
/*  6090 */                   StartTimeOne = "0" + boSensor.getStartTimeOne().toString();
/*       */                 else {
/*  6092 */                   StartTimeOne = boSensor.getStartTimeOne().toString();
/*       */                 }
/*       */ 
/*  6095 */                 if (boSensor.getEndTimeOne().toString().length() == 1)
/*  6096 */                   EndTimeOne = "0" + boSensor.getEndTimeOne().toString();
/*       */                 else {
/*  6098 */                   EndTimeOne = boSensor.getEndTimeOne().toString();
/*       */                 }
/*       */ 
/*  6101 */                 if (boSensor.getStartTimeTwo().toString().length() == 1)
/*  6102 */                   StartTimeTwo = "0" + boSensor.getStartTimeTwo().toString();
/*       */                 else {
/*  6104 */                   StartTimeTwo = boSensor.getStartTimeTwo().toString();
/*       */                 }
/*       */ 
/*  6107 */                 if (boSensor.getEndTimeTwo().toString().length() == 1)
/*  6108 */                   EndTimeTwo = "0" + boSensor.getEndTimeTwo().toString();
/*       */                 else {
/*  6110 */                   EndTimeTwo = boSensor.getEndTimeTwo().toString();
/*       */                 }
/*       */ 
/*  6113 */                 if (boSensor.getStartTimeThree().toString().length() == 1)
/*  6114 */                   StartTimeThree = "0" + boSensor.getStartTimeThree().toString();
/*       */                 else {
/*  6116 */                   StartTimeThree = boSensor.getStartTimeThree().toString();
/*       */                 }
/*       */ 
/*  6119 */                 if (boSensor.getEndTimeThree().toString().length() == 1)
/*  6120 */                   EndTimeThree = "0" + boSensor.getEndTimeThree().toString();
/*       */                 else {
/*  6122 */                   EndTimeThree = boSensor.getEndTimeThree().toString();
/*       */                 }
/*  6124 */                 boSensorMap.put("No.1", StartTimeOne + "," + EndTimeOne + "," + boSensor.getSecurityOneType().toString());
/*  6125 */                 boSensorMap.put("No.2", StartTimeTwo + "," + EndTimeTwo + "," + boSensor.getSecurityTwoType().toString());
/*  6126 */                 boSensorMap.put("No.3", StartTimeThree + "," + EndTimeThree + "," + boSensor.getSecurityThreeType().toString());
/*  6127 */                 boSensorMap.put("securityType", boSensor.getSecurityType().toString());
/*  6128 */                 voList.add(boSensorMap);
/*       */               }
/*  6130 */               this.requestJson.setData(voList);
/*  6131 */               this.requestJson.setSuccess(true);
/*       */             }
/*       */           }
/*       */           else {
/*  6135 */             System.err.println("AToken时间戳超时了");
/*  6136 */             this.requestJson.setMessage("超时了");
/*  6137 */             this.requestJson.setSuccess(false);
/*       */           }
/*       */         } else {
/*  6140 */           System.err.println("AToken超时了");
/*  6141 */           this.requestJson.setMessage("超时了");
/*  6142 */           this.requestJson.setSuccess(false);
/*       */         }
/*       */       } else {
/*  6145 */         System.err.println("验证不通过");
/*  6146 */         this.requestJson.setMessage("验证不通过");
/*  6147 */         this.requestJson.setSuccess(false);
/*       */       }
/*       */     } else {
/*  6150 */       Boolean ral = isRal(header, header2, header3, header4, userCode, "修改主机昵称");
/*  6151 */       if (ral.booleanValue()) {
/*  6152 */         System.err.println("验证通过");
/*  6153 */         Long accessToken = Long.valueOf(header);
/*  6154 */         BoUsers boUsers = this.boUserServicess.findByUserUserCode(userCode);
/*  6155 */         if (boUsers == null) {
/*  6156 */           this.requestJson.setData(map);
/*  6157 */           this.requestJson.setMessage("Invalid_User");
/*  6158 */           this.requestJson.setSuccess(true);
/*       */         }
/*  6160 */         else if (accessToken.longValue() < Long.valueOf(boUsers.getAccessTokenTime()).longValue()) {
/*  6161 */           List<BoSensor> list = this.boSensorService.getListByUserCodes(userCode, this.patternType);
/*  6162 */           if (list.size() <= 0) {
/*  6163 */             this.requestJson.setData(map);
/*  6164 */             this.requestJson.setMessage("没有传感器");
/*  6165 */             this.requestJson.setSuccess(true);
/*       */           } else {
/*  6167 */             voList = new ArrayList();
/*  6168 */             for (BoSensor boSensor : list) {
/*  6169 */               Map boSensorMap = new HashMap();
/*  6170 */               boSensorMap.put("patternType", boSensor.getType().toString());
/*  6171 */               boSensorMap.put("pushContent", boSensor.getPushContent());
/*  6172 */               boSensorMap.put("deviceType", boSensor.getDeviceType().toString());
/*  6173 */               boSensorMap.put("deviceAddress", boSensor.getDeviceAddress().toString());
/*  6174 */               boSensorMap.put("whetherControlEnclosure", boSensor.getIco().toString());
/*  6175 */               String nickName2 = boSensor.getNickName();
/*       */               String nickNames;

/*  6177 */               if (nickName2 == null)
/*  6178 */                 nickNames = "";
/*       */               else {
/*  6180 */                 nickNames = boSensor.getNickName();
/*       */               }
/*  6182 */               boSensor.getStartTimeOne().toString();
/*  6183 */               boSensorMap.put("deviceCode", boSensor.getBoDevice().getDeviceCode().toString());
/*  6184 */               boSensorMap.put("nickName", nickNames);
/*       */ 
/*  6186 */               String StartTimeOne = null;
/*  6187 */               String EndTimeOne = null;
/*  6188 */               String StartTimeTwo = null;
/*  6189 */               String EndTimeTwo = null;
/*  6190 */               String StartTimeThree = null;
/*  6191 */               String EndTimeThree = null;
/*  6192 */               if (boSensor.getStartTimeOne().toString().length() == 1)
/*  6193 */                 StartTimeOne = "0" + boSensor.getStartTimeOne().toString();
/*       */               else {
/*  6195 */                 StartTimeOne = boSensor.getStartTimeOne().toString();
/*       */               }
/*       */ 
/*  6198 */               if (boSensor.getEndTimeOne().toString().length() == 1)
/*  6199 */                 EndTimeOne = "0" + boSensor.getEndTimeOne().toString();
/*       */               else {
/*  6201 */                 EndTimeOne = boSensor.getEndTimeOne().toString();
/*       */               }
/*       */ 
/*  6204 */               if (boSensor.getStartTimeTwo().toString().length() == 1)
/*  6205 */                 StartTimeTwo = "0" + boSensor.getStartTimeTwo().toString();
/*       */               else {
/*  6207 */                 StartTimeTwo = boSensor.getStartTimeTwo().toString();
/*       */               }
/*       */ 
/*  6210 */               if (boSensor.getEndTimeTwo().toString().length() == 1)
/*  6211 */                 EndTimeTwo = "0" + boSensor.getEndTimeTwo().toString();
/*       */               else {
/*  6213 */                 EndTimeTwo = boSensor.getEndTimeTwo().toString();
/*       */               }
/*       */ 
/*  6216 */               if (boSensor.getStartTimeThree().toString().length() == 1)
/*  6217 */                 StartTimeThree = "0" + boSensor.getStartTimeThree().toString();
/*       */               else {
/*  6219 */                 StartTimeThree = boSensor.getStartTimeThree().toString();
/*       */               }
/*       */ 
/*  6222 */               if (boSensor.getEndTimeThree().toString().length() == 1)
/*  6223 */                 EndTimeThree = "0" + boSensor.getEndTimeThree().toString();
/*       */               else {
/*  6225 */                 EndTimeThree = boSensor.getEndTimeThree().toString();
/*       */               }
/*  6227 */               boSensorMap.put("No.1", StartTimeOne + "," + EndTimeOne + "," + boSensor.getSecurityOneType().toString());
/*  6228 */               boSensorMap.put("No.2", StartTimeTwo + "," + EndTimeTwo + "," + boSensor.getSecurityTwoType().toString());
/*  6229 */               boSensorMap.put("No.3", StartTimeThree + "," + EndTimeThree + "," + boSensor.getSecurityThreeType().toString());
/*  6230 */               boSensorMap.put("securityType", boSensor.getSecurityType().toString());
/*  6231 */               voList.add(boSensorMap);
/*       */             }
/*  6233 */             this.requestJson.setData(voList);
/*  6234 */             this.requestJson.setSuccess(true);
/*       */           }
/*       */         }
/*       */         else {
/*  6238 */           this.requestJson.setMessage("超时了");
/*  6239 */           this.requestJson.setSuccess(false);
/*       */         }
/*       */       } else {
/*  6242 */         System.err.println("验证不通过");
/*  6243 */         this.requestJson.setMessage("验证不通过");
/*  6244 */         this.requestJson.setSuccess(false);
/*       */       }
/*       */     }
/*  6247 */     return "success";
/*       */   }
/*       */ 
/*       */   @Action(value="deleteSensor", results={@org.apache.struts2.convention.annotation.Result(type="json", params={"root", "requestJson"})})
/*       */   public String deleteSensor()
/*       */   {
/*  6256 */     this.requestJson = new RequestJson();
/*  6257 */     Map map = new HashMap();
/*  6258 */     HttpServletRequest request = ServletActionContext.getRequest();
/*  6259 */     String header = request.getHeader("timestamp");
/*  6260 */     String header2 = request.getHeader("nonce");
/*  6261 */     String header3 = request.getHeader("sign");
/*  6262 */     String header4 = request.getHeader("access_token");
/*  6263 */     String userCode = request.getHeader("userCode");
/*  6264 */     if (userCode.contains(",")) {
/*  6265 */       String[] userCode2 = userCode.split(",");
/*  6266 */       BoUsers boUsers = this.boUserServicess.findByUserUserCode(userCode2[0].trim().toString());
/*  6267 */       BoUsers phone = this.boUserServicess.findByUserPhone(userCode2[1].trim().toString());
/*  6268 */       Boolean ral = isRal(header, header2, header3, header4, userCode, "删除传感器");
/*  6269 */       if (ral.booleanValue())
/*       */       {
/*  6271 */         Long accessToken = Long.valueOf(header);
/*       */ 
/*  6273 */         if ((phone == null) || (boUsers == null)) {
/*  6274 */           this.requestJson.setData(map);
/*  6275 */           this.requestJson.setMessage("Invalid_User");
/*  6276 */           this.requestJson.setSuccess(true);
/*       */         }
/*  6278 */         else if (header4.equals(phone.getAccessToken())) {
/*  6279 */           if (accessToken.longValue() < Long.valueOf(phone.getAccessTokenTime()).longValue()) {
/*  6280 */             BoSensor boSensor = this.boSensorService.findBydeviceAddress(userCode2[0].trim().toString(), this.deviceCode, this.deviceAddress, this.patternType);
/*  6281 */             if (boSensor == null) {
/*  6282 */               this.requestJson.setData(map);
/*  6283 */               this.requestJson.setMessage("没有找到该传感器");
/*  6284 */               this.requestJson.setSuccess(true);
/*       */             } else {
/*  6286 */               String str = "RFSTUY_315M-DELETE-" + this.deviceAddress;
/*  6287 */               byte[] bs = str.getBytes();
/*  6288 */               System.err.println(new String(bs));
/*  6289 */               this.packetProcessHelper.processSendDDatas(this.deviceCode, bs);
/*       */               try
/*       */               {
/*  6292 */                 Thread.sleep(1000L);
/*  6293 */                 BoSensor delete = this.boSensorService.delete(boSensor);
/*  6294 */                 if (delete == null) {
/*  6295 */                   this.requestJson.setData(map);
/*  6296 */                   this.requestJson.setMessage("删除成功");
/*  6297 */                   this.requestJson.setSuccess(true);
/*       */                 } else {
/*  6299 */                   this.requestJson.setData(map);
/*  6300 */                   this.requestJson.setMessage("删除失败");
/*  6301 */                   this.requestJson.setSuccess(true);
/*       */                 }
/*       */               }
/*       */               catch (InterruptedException e) {
/*  6305 */                 e.printStackTrace();
/*       */               }
/*       */             }
/*       */           } else {
/*  6309 */             System.err.println("AToken时间戳超时了");
/*  6310 */             this.requestJson.setMessage("超时了");
/*  6311 */             this.requestJson.setSuccess(false);
/*       */           }
/*       */         } else {
/*  6314 */           System.err.println("AToken超时了");
/*  6315 */           this.requestJson.setMessage("超时了");
/*  6316 */           this.requestJson.setSuccess(false);
/*       */         }
/*       */       }
/*       */       else
/*       */       {
/*  6321 */         System.err.println("验证不通过");
/*  6322 */         this.requestJson.setMessage("验证不通过");
/*  6323 */         this.requestJson.setSuccess(false);
/*       */       }
/*       */     } else {
/*  6326 */       Boolean ral = isRal(header, header2, header3, header4, userCode, "删除传感器");
/*  6327 */       if (ral.booleanValue())
/*       */       {
/*  6329 */         Long accessToken = Long.valueOf(header);
/*  6330 */         BoUsers boUsers = this.boUserServicess.findByUserUserCode(userCode);
/*  6331 */         if (boUsers == null) {
/*  6332 */           this.requestJson.setData(map);
/*  6333 */           this.requestJson.setMessage("Invalid_User");
/*  6334 */           this.requestJson.setSuccess(true);
/*       */         }
/*  6336 */         else if (accessToken.longValue() < Long.valueOf(boUsers.getAccessTokenTime()).longValue()) {
/*  6337 */           BoSensor boSensor = this.boSensorService.findBydeviceAddress(userCode, this.deviceCode, this.deviceAddress, this.patternType);
/*  6338 */           if (boSensor == null) {
/*  6339 */             this.requestJson.setData(map);
/*  6340 */             this.requestJson.setMessage("没有找到该传感器");
/*  6341 */             this.requestJson.setSuccess(true);
/*       */           } else {
/*  6343 */             String str = "RFSTUY_315M-DELETE-" + this.deviceAddress;
/*  6344 */             byte[] bs = str.getBytes();
/*  6345 */             System.err.println(new String(bs));
/*  6346 */             this.packetProcessHelper.processSendDDatas(this.deviceCode, bs);
/*       */             try
/*       */             {
/*  6349 */               Thread.sleep(1000L);
/*  6350 */               BoSensor delete = this.boSensorService.delete(boSensor);
/*  6351 */               if (delete == null) {
/*  6352 */                 this.requestJson.setData(map);
/*  6353 */                 this.requestJson.setMessage("删除成功");
/*  6354 */                 this.requestJson.setSuccess(true);
/*       */               } else {
/*  6356 */                 this.requestJson.setData(map);
/*  6357 */                 this.requestJson.setMessage("删除失败");
/*  6358 */                 this.requestJson.setSuccess(true);
/*       */               }
/*       */             }
/*       */             catch (InterruptedException e) {
/*  6362 */               e.printStackTrace();
/*       */             }
/*       */           }
/*       */         } else {
/*  6366 */           this.requestJson.setMessage("超时了");
/*  6367 */           this.requestJson.setSuccess(false);
/*       */         }
/*       */       }
/*       */       else
/*       */       {
/*  6372 */         System.err.println("验证不通过");
/*  6373 */         this.requestJson.setMessage("验证不通过");
/*  6374 */         this.requestJson.setSuccess(false);
/*       */       }
/*       */     }
/*  6377 */     return "success";
/*       */   }
/*       */ 
/*       */   @Action(value="setTotalSecuritySwitch", results={@org.apache.struts2.convention.annotation.Result(type="json", params={"root", "requestJson"})})
/*       */   public String setTotalSecuritySwitch()
/*       */   {
/*  6388 */     this.requestJson = new RequestJson();
/*  6389 */     Map map = new HashMap();
/*  6390 */     HttpServletRequest request = ServletActionContext.getRequest();
/*  6391 */     String header = request.getHeader("timestamp");
/*  6392 */     String header2 = request.getHeader("nonce");
/*  6393 */     String header3 = request.getHeader("sign");
/*  6394 */     String header4 = request.getHeader("access_token");
/*  6395 */     String userCode = request.getHeader("userCode");
/*  6396 */     if (userCode.contains(",")) {
/*  6397 */       String[] userCode2 = userCode.split(",");
/*  6398 */       BoUsers boUsers = this.boUserServicess.findByUserUserCode(userCode2[0].trim().toString());
/*  6399 */       BoUsers phone = this.boUserServicess.findByUserPhone(userCode2[1].trim().toString());
/*  6400 */       Boolean ral = isRal(header, header2, header3, header4, userCode, "设置传感器总安防开关");
/*  6401 */       if (ral.booleanValue()) {
/*  6402 */         System.err.println("验证通过");
/*  6403 */         Long accessToken = Long.valueOf(header);
/*       */ 
/*  6405 */         if ((phone == null) || (boUsers == null)) {
/*  6406 */           this.requestJson.setData(map);
/*  6407 */           this.requestJson.setMessage("Invalid_User");
/*  6408 */           this.requestJson.setSuccess(true);
/*       */         }
/*  6410 */         else if (header4.equals(phone.getAccessToken())) {
/*  6411 */           if (accessToken.longValue() < Long.valueOf(phone.getAccessTokenTime()).longValue()) {
/*  6412 */             BoSensor boSensor = this.boSensorService.findBydeviceAddress(userCode2[0].trim().toString(), this.deviceCode, this.deviceAddress, this.patternType);
/*  6413 */             if (boSensor == null) {
/*  6414 */               this.requestJson.setData(map);
/*  6415 */               this.requestJson.setMessage("没有找到该传感器");
/*  6416 */               this.requestJson.setSuccess(true);
/*       */             } else {
/*  6418 */               boSensor.setSecurityType(this.securityType);
/*  6419 */               BoSensor update = (BoSensor)this.boSensorService.update(boSensor);
/*  6420 */               if (update != null) {
/*  6421 */                 this.requestJson.setData(map);
/*  6422 */                 this.requestJson.setMessage("修改成功");
/*  6423 */                 this.requestJson.setSuccess(true);
/*       */               } else {
/*  6425 */                 this.requestJson.setData(map);
/*  6426 */                 this.requestJson.setMessage("修改失败");
/*  6427 */                 this.requestJson.setSuccess(true);
/*       */               }
/*       */             }
/*       */           } else {
/*  6431 */             System.err.println("AToken时间戳超时了");
/*  6432 */             this.requestJson.setMessage("超时了");
/*  6433 */             this.requestJson.setSuccess(false);
/*       */           }
/*       */         } else {
/*  6436 */           System.err.println("AToken超时了");
/*  6437 */           this.requestJson.setMessage("超时了");
/*  6438 */           this.requestJson.setSuccess(false);
/*       */         }
/*       */       }
/*       */       else
/*       */       {
/*  6443 */         System.err.println("验证不通过");
/*  6444 */         this.requestJson.setMessage("验证不通过");
/*  6445 */         this.requestJson.setSuccess(false);
/*       */       }
/*       */     } else {
/*  6448 */       Boolean ral = isRal(header, header2, header3, header4, userCode, "设置传感器总安防开关");
/*  6449 */       if (ral.booleanValue()) {
/*  6450 */         System.err.println("验证通过");
/*  6451 */         Long accessToken = Long.valueOf(header);
/*  6452 */         BoUsers boUsers = this.boUserServicess.findByUserUserCode(userCode);
/*  6453 */         if (boUsers == null) {
/*  6454 */           this.requestJson.setData(map);
/*  6455 */           this.requestJson.setMessage("Invalid_User");
/*  6456 */           this.requestJson.setSuccess(true);
/*       */         }
/*  6458 */         else if (accessToken.longValue() < Long.valueOf(boUsers.getAccessTokenTime()).longValue()) {
/*  6459 */           BoSensor boSensor = this.boSensorService.findBydeviceAddress(userCode, this.deviceCode, this.deviceAddress, this.patternType);
/*  6460 */           if (boSensor == null) {
/*  6461 */             this.requestJson.setData(map);
/*  6462 */             this.requestJson.setMessage("没有找到该传感器");
/*  6463 */             this.requestJson.setSuccess(true);
/*       */           } else {
/*  6465 */             boSensor.setSecurityType(this.securityType);
/*  6466 */             BoSensor update = (BoSensor)this.boSensorService.update(boSensor);
/*  6467 */             if (update != null) {
/*  6468 */               this.requestJson.setData(map);
/*  6469 */               this.requestJson.setMessage("修改成功");
/*  6470 */               this.requestJson.setSuccess(true);
/*       */             } else {
/*  6472 */               this.requestJson.setData(map);
/*  6473 */               this.requestJson.setMessage("修改失败");
/*  6474 */               this.requestJson.setSuccess(true);
/*       */             }
/*       */           }
/*       */         } else {
/*  6478 */           this.requestJson.setMessage("超时了");
/*  6479 */           this.requestJson.setSuccess(false);
/*       */         }
/*       */       }
/*       */       else
/*       */       {
/*  6484 */         System.err.println("验证不通过");
/*  6485 */         this.requestJson.setMessage("验证不通过");
/*  6486 */         this.requestJson.setSuccess(false);
/*       */       }
/*       */     }
/*  6489 */     return "success";
/*       */   }
/*       */ 
/*       */   @Action(value="setSensorSecurityTime", results={@org.apache.struts2.convention.annotation.Result(type="json", params={"root", "requestJson"})})
/*       */   public String setSensorSecurityTime()
/*       */   {
/*  6498 */     this.requestJson = new RequestJson();
/*  6499 */     Map map = new HashMap();
/*  6500 */     HttpServletRequest request = ServletActionContext.getRequest();
/*  6501 */     String header = request.getHeader("timestamp");
/*  6502 */     String header2 = request.getHeader("nonce");
/*  6503 */     String header3 = request.getHeader("sign");
/*  6504 */     String header4 = request.getHeader("access_token");
/*  6505 */     String userCode = request.getHeader("userCode");
/*  6506 */     if (userCode.contains(",")) {
/*  6507 */       String[] userCode2 = userCode.split(",");
/*  6508 */       BoUsers boUsers = this.boUserServicess.findByUserUserCode(userCode2[0].trim().toString());
/*  6509 */       BoUsers phone = this.boUserServicess.findByUserPhone(userCode2[1].trim().toString());
/*  6510 */       Boolean ral = isRal(header, header2, header3, header4, userCode, "设置传感器安防时间");
/*  6511 */       if (ral.booleanValue()) {
/*  6512 */         System.err.println("验证通过");
/*  6513 */         Long accessToken = Long.valueOf(header);
/*       */ 
/*  6515 */         if ((phone == null) || (boUsers == null)) {
/*  6516 */           this.requestJson.setData(map);
/*  6517 */           this.requestJson.setMessage("Invalid_User");
/*  6518 */           this.requestJson.setSuccess(true);
/*       */         }
/*  6520 */         else if (header4.equals(phone.getAccessToken())) {
/*  6521 */           if (accessToken.longValue() < Long.valueOf(phone.getAccessTokenTime()).longValue()) {
/*  6522 */             BoSensor boSensor = this.boSensorService.findBydeviceAddress(userCode2[0].trim().toString(), this.deviceCode, this.deviceAddress, this.patternType);
/*  6523 */             if (boSensor == null) {
/*  6524 */               this.requestJson.setData(map);
/*  6525 */               this.requestJson.setMessage("没有找到该传感器");
/*  6526 */               this.requestJson.setSuccess(true);
/*       */             } else {
/*  6528 */               String substring = this.startTime.substring(0, 1);
/*       */               String time;
///*       */               String time;
/*  6530 */               if (substring.equals("0"))
/*  6531 */                 time = this.startTime.substring(1, 2);
/*       */               else {
/*  6533 */                 time = this.startTime;
/*       */               }
/*       */ 
/*  6536 */               String substring1 = this.endTime.substring(0, 1);
/*       */               String time2;

/*  6538 */               if (substring1.equals("0"))
/*  6539 */                 time2 = this.endTime.substring(1, 2);
/*       */               else {
/*  6541 */                 time2 = this.endTime;
/*       */               }
/*  6543 */               if (this.howMany.equals("1"))
/*       */               {
/*  6545 */                 boSensor.setStartTimeOne(time);
/*  6546 */                 boSensor.setEndTimeOne(time2);
/*  6547 */                 boSensor.setSecurityOneType(this.security);
/*  6548 */               } else if (this.howMany.equals("2")) {
/*  6549 */                 boSensor.setStartTimeTwo(time);
/*  6550 */                 boSensor.setEndTimeTwo(time2);
/*  6551 */                 boSensor.setSecurityTwoType(this.security);
/*       */               } else {
/*  6553 */                 boSensor.setStartTimeThree(time);
/*  6554 */                 boSensor.setEndTimeThree(time2);
/*  6555 */                 boSensor.setSecurityThreeType(this.security);
/*       */               }
/*       */ 
/*  6558 */               BoSensor update = (BoSensor)this.boSensorService.update(boSensor);
/*  6559 */               if (update != null) {
/*  6560 */                 this.requestJson.setData(map);
/*  6561 */                 this.requestJson.setMessage("修改成功");
/*  6562 */                 this.requestJson.setSuccess(true);
/*       */               } else {
/*  6564 */                 this.requestJson.setData(map);
/*  6565 */                 this.requestJson.setMessage("修改失败");
/*  6566 */                 this.requestJson.setSuccess(true);
/*       */               }
/*       */             }
/*       */           } else {
/*  6570 */             System.err.println("AToken时间戳超时了");
/*  6571 */             this.requestJson.setMessage("超时了");
/*  6572 */             this.requestJson.setSuccess(false);
/*       */           }
/*       */         } else {
/*  6575 */           System.err.println("AToken超时了");
/*  6576 */           this.requestJson.setMessage("超时了");
/*  6577 */           this.requestJson.setSuccess(false);
/*       */         }
/*       */       } else {
/*  6580 */         System.err.println("验证不通过");
/*  6581 */         this.requestJson.setMessage("验证不通过");
/*  6582 */         this.requestJson.setSuccess(false);
/*       */       }
/*       */     } else {
/*  6585 */       Boolean ral = isRal(header, header2, header3, header4, userCode, "设置传感器安防时间");
/*  6586 */       if (ral.booleanValue()) {
/*  6587 */         System.err.println("验证通过");
/*  6588 */         Long accessToken = Long.valueOf(header);
/*  6589 */         BoUsers boUsers = this.boUserServicess.findByUserUserCode(userCode);
/*  6590 */         if (boUsers == null) {
/*  6591 */           this.requestJson.setData(map);
/*  6592 */           this.requestJson.setMessage("Invalid_User");
/*  6593 */           this.requestJson.setSuccess(true);
/*       */         }
/*  6595 */         else if (accessToken.longValue() < Long.valueOf(boUsers.getAccessTokenTime()).longValue()) {
/*  6596 */           BoSensor boSensor = this.boSensorService.findBydeviceAddress(userCode, this.deviceCode, this.deviceAddress, this.patternType);
/*  6597 */           if (boSensor == null) {
/*  6598 */             this.requestJson.setData(map);
/*  6599 */             this.requestJson.setMessage("没有找到该传感器");
/*  6600 */             this.requestJson.setSuccess(true);
/*       */           } else {
/*  6602 */             String substring = this.startTime.substring(0, 1);
/*       */             String time;
///*       */             String time;
/*  6604 */             if (substring.equals("0"))
/*  6605 */               time = this.startTime.substring(1, 2);
/*       */             else {
/*  6607 */               time = this.startTime;
/*       */             }
/*       */ 
/*  6610 */             String substring1 = this.endTime.substring(0, 1);
/*       */             String time2;

/*  6612 */             if (substring1.equals("0"))
/*  6613 */               time2 = this.endTime.substring(1, 2);
/*       */             else {
/*  6615 */               time2 = this.endTime;
/*       */             }
/*  6617 */             if (this.howMany.equals("1"))
/*       */             {
/*  6619 */               boSensor.setStartTimeOne(time);
/*  6620 */               boSensor.setEndTimeOne(time2);
/*  6621 */               boSensor.setSecurityOneType(this.security);
/*  6622 */             } else if (this.howMany.equals("2")) {
/*  6623 */               boSensor.setStartTimeTwo(time);
/*  6624 */               boSensor.setEndTimeTwo(time2);
/*  6625 */               boSensor.setSecurityTwoType(this.security);
/*       */             } else {
/*  6627 */               boSensor.setStartTimeThree(time);
/*  6628 */               boSensor.setEndTimeThree(time2);
/*  6629 */               boSensor.setSecurityThreeType(this.security);
/*       */             }
/*       */ 
/*  6632 */             BoSensor update = (BoSensor)this.boSensorService.update(boSensor);
/*  6633 */             if (update != null) {
/*  6634 */               this.requestJson.setData(map);
/*  6635 */               this.requestJson.setMessage("修改成功");
/*  6636 */               this.requestJson.setSuccess(true);
/*       */             } else {
/*  6638 */               this.requestJson.setData(map);
/*  6639 */               this.requestJson.setMessage("修改失败");
/*  6640 */               this.requestJson.setSuccess(true);
/*       */             }
/*       */           }
/*       */         } else {
/*  6644 */           this.requestJson.setMessage("超时了");
/*  6645 */           this.requestJson.setSuccess(false);
/*       */         }
/*       */       } else {
/*  6648 */         System.err.println("验证不通过");
/*  6649 */         this.requestJson.setMessage("验证不通过");
/*  6650 */         this.requestJson.setSuccess(false);
/*       */       }
/*       */     }
/*  6653 */     return "success";
/*       */   }
/*       */ 
/*       */   @Action(value="addsensor", results={@org.apache.struts2.convention.annotation.Result(type="json", params={"root", "requestJson"})})
/*       */   public String addSensor()
/*       */   {
/*  6664 */     this.requestJson = new RequestJson();
/*  6665 */     Map map = new HashMap();
/*  6666 */     HttpServletRequest request = ServletActionContext.getRequest();
/*  6667 */     String header = request.getHeader("timestamp");
/*  6668 */     String header2 = request.getHeader("nonce");
/*  6669 */     String header3 = request.getHeader("sign");
/*  6670 */     String header4 = request.getHeader("access_token");
/*  6671 */     String userCode = request.getHeader("userCode");
/*  6672 */     String[] userCode2 = userCode.split(",");
/*  6673 */     BoUsers boUsers = this.boUserServicess.findByUserUserCode(userCode2[0].trim().toString());
/*  6674 */     BoUsers phone = this.boUserServicess.findByUserPhone(userCode2[1].trim().toString());
/*  6675 */     Boolean ral = isRal(header, header2, header3, header4, userCode, "添加传感器或情景面板");
/*  6676 */     if (ral.booleanValue()) {
/*  6677 */       System.err.println("验证通过");
/*  6678 */       Long accessToken = Long.valueOf(header);
/*  6679 */       if ((phone == null) || (boUsers == null)) {
/*  6680 */         this.requestJson.setData(map);
/*  6681 */         this.requestJson.setMessage("Invalid_User");
/*  6682 */         this.requestJson.setSuccess(true);
/*       */       }
/*  6684 */       else if (header4.equals(phone.getAccessToken())) {
/*  6685 */         if (accessToken.longValue() < Long.valueOf(phone.getAccessTokenTime()).longValue()) {
/*  6686 */           BoDevice boDevice = this.boDeviceService.findByCode(this.deviceCode);
/*  6687 */           if (boDevice.getType().equals("8")) {
/*  6688 */             System.err.println("控制盒不能添加传感器");
/*  6689 */             this.requestJson.setData(map);
/*  6690 */             this.requestJson.setMessage("控制盒不能添加传感器");
/*  6691 */             this.requestJson.setSuccess(false);
/*  6692 */           } else if (boDevice.getType().equals("32")) {
/*  6693 */             System.err.println("控制盒不能添加传感器");
/*  6694 */             this.requestJson.setData(map);
/*  6695 */             this.requestJson.setMessage("控制盒不能添加传感器");
/*  6696 */             this.requestJson.setSuccess(false);
/*       */           } else {
/*  6698 */             BoModel boModel = this.boModelService.find(userCode2[0].trim().toString(), this.modelId);
/*  6699 */             if (boModel == null) {
/*  6700 */               this.requestJson.setMessage("没有该情景模式");
/*  6701 */               this.requestJson.setSuccess(true);
/*       */             }
/*  6703 */             List list = this.boSensorService.get(this.deviceCode);
/*  6704 */             List nums = new ArrayList();
/*       */             Integer valueOfs;

/*  6708 */             if (list.size() <= 0) {
/*  6709 */               valueOfs = Integer.valueOf(1);
/*       */             }
/*       */             else {
/*  6712 */               for (int i = 0; i < list.size(); i++) {
/*  6713 */                 BoSensor boSensor = (BoSensor)list.get(i);
/*  6714 */                 nums.add(Integer.valueOf(boSensor.getDeviceAddress()));
/*       */               }
/*       */ 
/*  6718 */               Comparator ascComparator = new NumComparator();
/*  6719 */               Comparator descComparator = Collections.reverseOrder(ascComparator);
/*  6720 */               Collections.sort(nums, descComparator);
/*  6721 */               valueOfs = Integer.valueOf(((Integer)nums.get(0)).intValue() + 1);
/*  6722 */               System.err.println("最后一个 " + valueOfs);
/*       */             }
/*       */ 
/*  6727 */             if (list.size() > 100) {
/*  6728 */               this.requestJson.setData(map);
/*  6729 */               this.requestJson.setMessage("该主机已超过100路传感器");
/*  6730 */               this.requestJson.setSuccess(false);
/*       */             } else {
/*  6732 */               BoSensor s = new BoSensor();
/*  6733 */               s.setBoDevice(boDevice);
/*  6734 */               s.setBoUsers(boUsers);
/*  6735 */               s.setBoModel(boModel);
/*  6736 */               s.setDeviceClassify(this.fid1);
/*  6737 */               s.setDeviceType(this.deviceType);
/*  6738 */               s.setType(this.patternType);
/*  6739 */               s.setStartTimeOne("0");
/*  6740 */               s.setEndTimeOne("0");
/*  6741 */               s.setSecurityOneType("0");
/*  6742 */               s.setStartTimeTwo("0");
/*  6743 */               s.setEndTimeTwo("0");
/*  6744 */               s.setSecurityTwoType("0");
/*  6745 */               s.setStartTimeThree("0");
/*  6746 */               s.setEndTimeThree("0");
/*  6747 */               s.setSecurityThreeType("0");
/*  6748 */               s.setSecurityType("0");
/*  6749 */               s.setIco("");
/*  6750 */               s.setPushContent(this.pushContent);
/*  6751 */               s.setNickName(this.nickName);
/*  6752 */               s.setDeviceAddress(valueOfs+"");
/*  6753 */               BoSensor save2 = (BoSensor)this.boSensorService.save(s);
/*  6754 */               map.put("line", save2.getDeviceAddress());
/*  6755 */               this.requestJson.setData(map);
/*  6756 */               this.requestJson.setSuccess(true);
/*       */             }
/*       */           }
/*       */         }
/*       */         else {
/*  6761 */           this.requestJson.setData(map);
/*  6762 */           this.requestJson.setMessage("超时了");
/*  6763 */           this.requestJson.setSuccess(false);
/*       */         }
/*       */       } else {
/*  6766 */         System.err.println("超时了");
/*  6767 */         this.requestJson.setData(map);
/*  6768 */         this.requestJson.setMessage("超时了");
/*  6769 */         this.requestJson.setSuccess(false);
/*       */       }
/*       */ 
/*       */     }
/*       */     else
/*       */     {
/*  6775 */       this.requestJson.setMessage("验证不通过");
/*  6776 */       this.requestJson.setSuccess(false);
/*       */     }
/*       */ 
/*  6779 */     return "success";
/*       */   }
/*       */ 
/*       */   @Action(value="push", results={@org.apache.struts2.convention.annotation.Result(type="json", params={"root", "requestJson"})})
/*       */   public String push()
/*       */     throws InterruptedException
/*       */   {
/*  6790 */     String formatDate = GlobalMethod.formatDate(new Date(), "yy/MM/dd HH:mm:ss");
/*  6791 */     BoSensor ssss = this.boSensorService.find(this.deviceCode, this.deviceAddress);
/*  6792 */     BoUsers boUsers = this.boUserServicess.findByUserUserCode(ssss.getBoUsers().getUserCode());
/*  6793 */     if (boUsers != null) {
/*  6794 */       List<BoUsers> list = this.boUserServicess.getByAuthorizeUserCode(ssss.getBoUsers().getUserCode());
/*  6795 */       PushService pushService = new PushService();
/*  6796 */       for (BoUsers boUsers2 : list) {
/*  6797 */         if (boUsers2.getVersionType().equals("1")) {
/*  6798 */           System.err.println("易联智家KEY");
/*  6799 */           pushService.setAppId("C2rT1RdCoB7BaZ83l2AJM7");
/*  6800 */           pushService.setAppkey("ApgJ2YFdI573k57hLt9Mz9");
/*  6801 */           pushService.setMaster("JBWO7E3WyW75zgd2Bdr4NA");
/*  6802 */         } else if (boUsers2.getVersionType().equals("2")) {
/*  6803 */           System.err.println("爱博瑞KEY");
/*  6804 */           pushService.setAppId("qy0HMfNc8o6fiLdtUGRfo1");
/*  6805 */           pushService.setAppkey("cu1LHpYRwnAwDtTjq8XaQ7");
/*  6806 */           pushService.setMaster("UpAQnXf7S47Snh00l5P5E8");
/*  6807 */         } else if (!boUsers2.getVersionType().equals("3"))
/*       */         {
/*  6809 */           if (boUsers2.getVersionType().equals("4")) {
/*  6810 */             System.err.println("思创智能KEY");
/*  6811 */             pushService.setAppId("m5H4RPxliZAVXBlG1jka32");
/*  6812 */             pushService.setAppkey("xizBIaTLNY5g7HxCi6kP05");
/*  6813 */             pushService.setMaster("TRuul8ZWoO8rfFexEbvN09");
/*  6814 */           } else if (boUsers2.getVersionType().equals("5")) {
/*  6815 */             System.err.println("峰庭智能KEY");
/*  6816 */             pushService.setAppId("8qv7s4OhGEAjDjEaC5bBw4");
/*  6817 */             pushService.setAppkey("DH3NlHfEOG6YtySTspB4LA");
/*  6818 */             pushService.setMaster("utgpb3GAGN9KsVKVun7W32");
/*  6819 */           } else if (boUsers2.getVersionType().equals("6")) {
/*  6820 */             System.err.println("麦宝KEY");
/*  6821 */             pushService.setAppId("OAzON9h86e8Y2XREgjU0R9");
/*  6822 */             pushService.setAppkey("SAE1hTMJcW8ZkRfZPRDja6");
/*  6823 */             pushService.setMaster("y5zkumXwYPACBLJ59BZnr6");
/*  6824 */           } else if (boUsers2.getVersionType().equals("7")) {
/*  6825 */             System.err.println("乐沃KEY");
/*  6826 */             pushService.setAppId("pHU6NuXh789r2ZXEYzj7z1");
/*  6827 */             pushService.setAppkey("COGyB0sKyQ8nGHmnuNUK41");
/*  6828 */             pushService.setMaster("qSTfJpPIQc7OkaswRI1YH7");
/*       */           }
/*       */         }
/*  6831 */         Thread.sleep(500L);
/*  6832 */         System.err.println(boUsers2.getUserPhone());
/*  6833 */         String title = "";
/*  6834 */         String CID = boUsers2.getCid();
/*  6835 */         System.err.println("() " + CID + " ()");
/*  6836 */         if ((CID == null) || (CID.equals(""))) {
/*  6837 */           System.err.println("CID为空推送不到信息");
/*       */         } else {
/*  6839 */           StringBuffer text = new StringBuffer();
/*  6840 */           System.err.println(ssss.getType());
/*  6841 */           Integer valueOf = Integer.valueOf(ssss.getType());
/*  6842 */           switch (valueOf.intValue()) {
/*       */           case 1:
/*  6844 */             System.err.println("*****>> " + ssss.getBoUsers().getUserCode());
/*  6845 */             System.err.println("*****<< " + boUsers.getVersionType());
/*       */ 
/*  6847 */             if (boUsers2.getVersionType().equals("1")) {
/*  6848 */               System.err.println("易联智家推送内容");
/*  6849 */               title = "掌上智家";
/*  6850 */               if (!ssss.getPushContent().equals("")) {
/*  6851 */                 text.append(ssss.getPushContent());
/*       */               } else {
/*  6853 */                 text.append("尊敬的客户");
/*  6854 */                 text.append("您的 < " + ssss.getNickName() + " > " + " 传感器正在被触发 ");
/*  6855 */                 text.append(formatDate);
/*  6856 */                 text.append(" 请务必留意。");
/*       */               }
/*  6858 */             } else if (boUsers2.getVersionType().equals("2")) {
/*  6859 */               System.err.println("爱博瑞推送内容");
/*  6860 */               title = "爱波瑞科技";
/*  6861 */               if (!ssss.getPushContent().equals("")) {
/*  6862 */                 text.append(ssss.getPushContent());
/*       */               } else {
/*  6864 */                 text.append("尊敬的客户");
/*  6865 */                 text.append("您的 < " + ssss.getNickName() + " > " + " 传感器正在被触发 ");
/*  6866 */                 text.append(formatDate);
/*  6867 */                 text.append(" 请务必留意。");
/*       */               }
/*  6869 */             } else if (!boUsers2.getVersionType().equals("3"))
/*       */             {
/*  6871 */               if (boUsers2.getVersionType().equals("4")) {
/*  6872 */                 System.err.println("思创智能推送内容");
/*  6873 */                 title = "思创智能";
/*  6874 */                 if (!ssss.getPushContent().equals("")) {
/*  6875 */                   text.append(ssss.getPushContent());
/*       */                 } else {
/*  6877 */                   text.append("尊敬的客户");
/*  6878 */                   text.append("您的 < " + ssss.getNickName() + " > " + " 传感器正在被触发 ");
/*  6879 */                   text.append(formatDate);
/*  6880 */                   text.append(" 请务必留意。");
/*       */                 }
/*  6882 */               } else if (boUsers2.getVersionType().equals("5")) {
/*  6883 */                 System.err.println("峰庭智能推送内容");
/*  6884 */                 title = "峰庭智能";
/*  6885 */                 if (!ssss.getPushContent().equals("")) {
/*  6886 */                   text.append(ssss.getPushContent());
/*       */                 } else {
/*  6888 */                   text.append("尊敬的客户");
/*  6889 */                   text.append("您的 < " + ssss.getNickName() + " > " + " 传感器正在被触发 ");
/*  6890 */                   text.append(formatDate);
/*  6891 */                   text.append(" 请务必留意。");
/*       */                 }
/*  6893 */               } else if (boUsers2.getVersionType().equals("6")) {
/*  6894 */                 System.err.println("麦宝推送内容");
/*  6895 */                 title = "麦宝";
/*  6896 */                 if (!ssss.getPushContent().equals("")) {
/*  6897 */                   text.append(ssss.getPushContent());
/*       */                 } else {
/*  6899 */                   text.append("尊敬的客户");
/*  6900 */                   text.append("您的 < " + ssss.getNickName() + " > " + " 传感器正在被触发 ");
/*  6901 */                   text.append(formatDate);
/*  6902 */                   text.append(" 请务必留意。");
/*       */                 }
/*  6904 */               } else if (boUsers2.getVersionType().equals("7")) {
/*  6905 */                 System.err.println("乐沃推送内容");
/*  6906 */                 title = "乐沃";
/*  6907 */                 if (!ssss.getPushContent().equals("")) {
/*  6908 */                   text.append(ssss.getPushContent());
/*       */                 } else {
/*  6910 */                   text.append("尊敬的客户");
/*  6911 */                   text.append("您的 < " + ssss.getNickName() + " > " + " 传感器正在被触发 ");
/*  6912 */                   text.append(formatDate);
/*  6913 */                   text.append(" 请务必留意。");
/*       */                 }
/*       */               }
/*       */             }
/*  6917 */             Integer type = boUsers2.getPhoneType();
/*       */ 
/*  6919 */             if ((type == null) || (type.intValue() == 0)) {
/*  6920 */               pushService.pushToSingle(CID, title, text.toString(), text.toString());
/*  6921 */               this.requestJson.setSuccess(true);
/*  6922 */               System.err.println(text.toString());
/*       */             } else {
/*  6924 */               pushService.apnPush(CID, title, text.toString(), text.toString());
/*  6925 */               this.requestJson.setSuccess(true);
/*  6926 */               System.err.println(text.toString());
/*       */             }
/*       */ 
/*       */             break;
/*       */           }
/*       */ 
/*       */         }
/*       */ 
/*       */       }
/*       */ 
/*  6940 */       if (boUsers.getVersionType().equals("1")) {
/*  6941 */         System.err.println("易联智家KEY");
/*  6942 */         pushService.setAppId("C2rT1RdCoB7BaZ83l2AJM7");
/*  6943 */         pushService.setAppkey("ApgJ2YFdI573k57hLt9Mz9");
/*  6944 */         pushService.setMaster("JBWO7E3WyW75zgd2Bdr4NA");
/*  6945 */       } else if (boUsers.getVersionType().equals("2")) {
/*  6946 */         System.err.println("爱博瑞KEY");
/*  6947 */         pushService.setAppId("qy0HMfNc8o6fiLdtUGRfo1");
/*  6948 */         pushService.setAppkey("cu1LHpYRwnAwDtTjq8XaQ7");
/*  6949 */         pushService.setMaster("UpAQnXf7S47Snh00l5P5E8");
/*  6950 */       } else if (!boUsers.getVersionType().equals("3"))
/*       */       {
/*  6952 */         if (boUsers.getVersionType().equals("4")) {
/*  6953 */           System.err.println("思创智能KEY");
/*  6954 */           pushService.setAppId("m5H4RPxliZAVXBlG1jka32");
/*  6955 */           pushService.setAppkey("xizBIaTLNY5g7HxCi6kP05");
/*  6956 */           pushService.setMaster("TRuul8ZWoO8rfFexEbvN09");
/*  6957 */         } else if (boUsers.getVersionType().equals("5")) {
/*  6958 */           System.err.println("峰庭智能KEY");
/*  6959 */           pushService.setAppId("8qv7s4OhGEAjDjEaC5bBw4");
/*  6960 */           pushService.setAppkey("DH3NlHfEOG6YtySTspB4LA");
/*  6961 */           pushService.setMaster("utgpb3GAGN9KsVKVun7W32");
/*  6962 */         } else if (boUsers.getVersionType().equals("6")) {
/*  6963 */           System.err.println("麦宝KEY");
/*  6964 */           pushService.setAppId("OAzON9h86e8Y2XREgjU0R9");
/*  6965 */           pushService.setAppkey("SAE1hTMJcW8ZkRfZPRDja6");
/*  6966 */           pushService.setMaster("y5zkumXwYPACBLJ59BZnr6");
/*  6967 */         } else if (boUsers.getVersionType().equals("7")) {
/*  6968 */           System.err.println("乐沃KEY");
/*  6969 */           pushService.setAppId("pHU6NuXh789r2ZXEYzj7z1");
/*  6970 */           pushService.setAppkey("COGyB0sKyQ8nGHmnuNUK41");
/*  6971 */           pushService.setMaster("qSTfJpPIQc7OkaswRI1YH7");
/*       */         }
/*       */       }
/*  6974 */       String title = "";
/*  6975 */       String CID = boUsers.getCid();
/*  6976 */       System.err.println("() " + CID + " ()");
/*  6977 */       if ((CID == null) || (CID.equals(""))) {
/*  6978 */         System.err.println("CID为空推送不到信息");
/*       */       } else {
/*  6980 */         StringBuffer text = new StringBuffer();
/*  6981 */         System.err.println(ssss.getType());
/*  6982 */         Integer valueOf = Integer.valueOf(ssss.getType());
/*  6983 */         switch (valueOf.intValue())
/*       */         {
/*       */         case 1:
/*  6987 */           if (boUsers.getVersionType().equals("1")) {
/*  6988 */             System.err.println("易联智家推送内容");
/*  6989 */             title = "掌上智家";
/*  6990 */             if (!ssss.getPushContent().equals("")) {
/*  6991 */               text.append(ssss.getPushContent());
/*       */             } else {
/*  6993 */               text.append("尊敬的客户");
/*  6994 */               text.append("您的 < " + ssss.getNickName() + " > " + " 传感器正在被触发 ");
/*  6995 */               text.append(formatDate);
/*  6996 */               text.append(" 请务必留意。");
/*       */             }
/*  6998 */           } else if (boUsers.getVersionType().equals("2")) {
/*  6999 */             System.err.println("爱博瑞推送内容");
/*  7000 */             title = "爱波瑞科技";
/*  7001 */             if (!ssss.getPushContent().equals("")) {
/*  7002 */               text.append(ssss.getPushContent());
/*       */             } else {
/*  7004 */               text.append("尊敬的客户");
/*  7005 */               text.append("您的 < " + ssss.getNickName() + " > " + " 传感器正在被触发 ");
/*  7006 */               text.append(formatDate);
/*  7007 */               text.append(" 请务必留意。");
/*       */             }
/*  7009 */           } else if (!boUsers.getVersionType().equals("3"))
/*       */           {
/*  7011 */             if (boUsers.getVersionType().equals("4")) {
/*  7012 */               System.err.println("思创智能推送内容");
/*  7013 */               title = "思创智能";
/*  7014 */               if (!ssss.getPushContent().equals("")) {
/*  7015 */                 text.append(ssss.getPushContent());
/*       */               } else {
/*  7017 */                 text.append("尊敬的客户");
/*  7018 */                 text.append("您的 < " + ssss.getNickName() + " > " + " 传感器正在被触发 ");
/*  7019 */                 text.append(formatDate);
/*  7020 */                 text.append(" 请务必留意。");
/*       */               }
/*  7022 */             } else if (boUsers.getVersionType().equals("5")) {
/*  7023 */               System.err.println("峰庭智能推送内容");
/*  7024 */               title = "峰庭智能";
/*  7025 */               if (!ssss.getPushContent().equals("")) {
/*  7026 */                 text.append(ssss.getPushContent());
/*       */               } else {
/*  7028 */                 text.append("尊敬的客户");
/*  7029 */                 text.append("您的 < " + ssss.getNickName() + " > " + " 传感器正在被触发 ");
/*  7030 */                 text.append(formatDate);
/*  7031 */                 text.append(" 请务必留意。");
/*       */               }
/*  7033 */             } else if (boUsers.getVersionType().equals("6")) {
/*  7034 */               System.err.println("麦宝推送内容");
/*  7035 */               title = "麦宝";
/*  7036 */               if (!ssss.getPushContent().equals("")) {
/*  7037 */                 text.append(ssss.getPushContent());
/*       */               } else {
/*  7039 */                 text.append("尊敬的客户");
/*  7040 */                 text.append("您的 < " + ssss.getNickName() + " > " + " 传感器正在被触发 ");
/*  7041 */                 text.append(formatDate);
/*  7042 */                 text.append(" 请务必留意。");
/*       */               }
/*  7044 */             } else if (boUsers.getVersionType().equals("7")) {
/*  7045 */               System.err.println("乐沃推送内容");
/*  7046 */               title = "乐沃";
/*  7047 */               if (!ssss.getPushContent().equals("")) {
/*  7048 */                 text.append(ssss.getPushContent());
/*       */               } else {
/*  7050 */                 text.append("尊敬的客户");
/*  7051 */                 text.append("您的 < " + ssss.getNickName() + " > " + " 传感器正在被触发 ");
/*  7052 */                 text.append(formatDate);
/*  7053 */                 text.append(" 请务必留意。");
/*       */               }
/*       */             }
/*       */           }
/*  7057 */           Integer type = boUsers.getPhoneType();
/*       */ 
/*  7059 */           if ((type == null) || (type.intValue() == 0)) {
/*  7060 */             pushService.pushToSingle(CID, title, text.toString(), text.toString());
/*  7061 */             this.requestJson.setSuccess(true);
/*  7062 */             System.err.println(text.toString());
/*       */           } else {
/*  7064 */             pushService.apnPush(CID, title, text.toString(), text.toString());
/*  7065 */             this.requestJson.setSuccess(true);
/*  7066 */             System.err.println(text.toString());
/*       */           }
/*  7068 */           BoAlarmRecord boAlarmRecord = new BoAlarmRecord();
/*  7069 */           boAlarmRecord.setDeviceCode(this.deviceCode);
/*  7070 */           boAlarmRecord.setUserCode(ssss.getBoUsers().getUserCode());
/*  7071 */           boAlarmRecord.setSebsorName(ssss.getNickName());
/*  7072 */           Integer phoneType = ssss.getBoUsers().getPhoneType();
/*       */           String alarmPhoneType;

/*  7074 */           if (phoneType.intValue() == 0)
/*  7075 */             alarmPhoneType = "安卓";
/*       */           else {
/*  7077 */             alarmPhoneType = "苹果";
/*       */           }
/*  7079 */           boAlarmRecord.setAlarmPhoneType(alarmPhoneType);
/*       */ 
/*  7081 */           boAlarmRecord.setReportTime(formatDate);
/*  7082 */           Long timestamp = Long.valueOf(172800L);
/*  7083 */           Long timestamps = Long.valueOf((int)(System.currentTimeMillis() / 1000L) + timestamp.longValue());
/*  7084 */           boAlarmRecord.setReportTimestamp(timestamps+"");
/*  7085 */           boAlarmRecord.setReportDate(new Date());
/*  7086 */           this.boAlarmRecordService.save(boAlarmRecord);
/*  7087 */           break;
/*       */         }
/*       */ 
/*       */       }
/*       */ 
/*       */     }
/*       */ 
/*  7099 */     return "success";
/*       */   }
/*       */ 
/*       */   @Action(value="storagepushcid", results={@org.apache.struts2.convention.annotation.Result(type="json", params={"root", "requestJson"})})
/*       */   public String StoragePushCID()
/*       */   {
/*  7107 */     this.requestJson = new RequestJson();
/*  7108 */     Map map = new HashMap();
/*  7109 */     HttpServletRequest request = ServletActionContext.getRequest();
/*  7110 */     String header = request.getHeader("timestamp");
/*  7111 */     String header2 = request.getHeader("nonce");
/*  7112 */     String header3 = request.getHeader("sign");
/*  7113 */     String header4 = request.getHeader("access_token");
/*  7114 */     String userCode = request.getHeader("userCode");
/*  7115 */     if (userCode.contains(",")) {
/*  7116 */       String[] userCode2 = userCode.split(",");
/*  7117 */       BoUsers boUsers = this.boUserServicess.findByUserUserCode(userCode2[0].trim().toString());
/*  7118 */       BoUsers phone = this.boUserServicess.findByUserPhone(userCode2[1].trim().toString());
/*  7119 */       Boolean ral = isRal(header, header2, header3, header4, userCode, "修改主机昵称");
/*  7120 */       if (ral.booleanValue()) {
/*  7121 */         System.err.println("验证通过");
/*  7122 */         Long accessToken = Long.valueOf(header);
/*       */ 
/*  7124 */         if ((phone == null) || (boUsers == null)) {
/*  7125 */           this.requestJson.setData(map);
/*  7126 */           this.requestJson.setMessage("Invalid_User");
/*  7127 */           this.requestJson.setSuccess(true);
/*       */         }
/*  7129 */         else if (header4.equals(phone.getAccessToken())) {
/*  7130 */           if (accessToken.longValue() < Long.valueOf(phone.getAccessTokenTime()).longValue())
/*       */           {
/*  7132 */             if ((!this.CID.equals(boUsers.getCid())) || (this.type != boUsers.getPhoneType())) {
/*  7133 */               phone.setCid(this.CID);
/*  7134 */               phone.setPhoneType(this.type);
/*  7135 */               phone.setVersionType(this.versionType);
/*  7136 */               this.boUserServicess.update(phone);
/*  7137 */               this.requestJson.setData(map);
/*       */             }
/*       */           } else {
/*  7140 */             System.err.println("超时了");
/*  7141 */             this.requestJson.setData(map);
/*  7142 */             this.requestJson.setMessage("超时了");
/*  7143 */             this.requestJson.setSuccess(false);
/*       */           }
/*       */         } else {
/*  7146 */           this.requestJson.setData(map);
/*  7147 */           this.requestJson.setMessage("超时了");
/*  7148 */           this.requestJson.setSuccess(false);
/*       */         }
/*       */ 
/*       */       }
/*       */       else
/*       */       {
/*  7155 */         System.err.println("验证不通过");
/*  7156 */         this.requestJson.setMessage("验证不通过");
/*  7157 */         this.requestJson.setSuccess(false);
/*       */       }
/*       */     } else {
/*  7160 */       Boolean ral = isRal(header, header2, header3, header4, userCode, "修改主机昵称");
/*  7161 */       if (ral.booleanValue()) {
/*  7162 */         System.err.println("验证通过");
/*  7163 */         Long accessToken = Long.valueOf(header);
/*  7164 */         BoUsers boUsers = this.boUserServicess.findByUserUserCode(userCode);
/*  7165 */         if (boUsers == null) {
/*  7166 */           this.requestJson.setData(map);
/*  7167 */           this.requestJson.setMessage("Invalid_User");
/*  7168 */           this.requestJson.setSuccess(true);
/*       */         }
/*  7170 */         else if (header4.equals(boUsers.getAccessToken())) {
/*  7171 */           if (accessToken.longValue() < Long.valueOf(boUsers.getAccessTokenTime()).longValue()) {
/*  7172 */             BoUsers user = this.boUserServicess.findByUserPhone(this.userPhone);
/*       */ 
/*  7174 */             if ((!this.CID.equals(boUsers.getCid())) || (this.type != boUsers.getPhoneType())) {
/*  7175 */               user.setCid(this.CID);
/*  7176 */               user.setPhoneType(this.type);
/*  7177 */               user.setVersionType(this.versionType);
/*  7178 */               this.boUserServicess.update(user);
/*  7179 */               this.requestJson.setData(map);
/*       */             }
/*       */           } else {
/*  7182 */             System.err.println("超时了");
/*  7183 */             this.requestJson.setData(map);
/*  7184 */             this.requestJson.setMessage("超时了");
/*  7185 */             this.requestJson.setSuccess(false);
/*       */           }
/*       */         } else {
/*  7188 */           this.requestJson.setData(map);
/*  7189 */           this.requestJson.setMessage("超时了");
/*  7190 */           this.requestJson.setSuccess(false);
/*       */         }
/*       */ 
/*       */       }
/*       */       else
/*       */       {
/*  7197 */         System.err.println("验证不通过");
/*  7198 */         this.requestJson.setMessage("验证不通过");
/*  7199 */         this.requestJson.setSuccess(false);
/*       */       }
/*       */     }
/*  7202 */     return "success";
/*       */   }
/*       */ 
/*       */   @Action(value="commandmodel", results={@org.apache.struts2.convention.annotation.Result(type="json", params={"root", "requestJson"})})
/*       */   public String commandModel()
/*       */   {
	            logger.info("in commandmodel Method");
/*  7208 */     this.requestJson = new RequestJson();
/*  7209 */     HttpServletRequest request = ServletActionContext.getRequest();
/*  7210 */     Map map = new HashMap();
/*  7211 */     String ip = request.getRemoteAddr();
/*  7212 */     System.out.println("ip :"+ip);
                System.out.println("modelId"+this.modelId);//new
/*  7213 */     String header = request.getHeader("timestamp");
/*  7214 */     String header2 = request.getHeader("nonce");
/*  7215 */     String header3 = request.getHeader("sign");
/*  7216 */     String header4 = request.getHeader("access_token");
/*  7217 */     String userCode = request.getHeader("userCode");
/*  7218 */     if (userCode.contains(",")) {
/*  7219 */       String[] userCode2 = userCode.split(",");
/*  7220 */       BoUsers boUsers = this.boUserServicess.findByUserUserCode(userCode2[0].trim().toString());
/*  7221 */       BoUsers phone = this.boUserServicess.findByUserPhone(userCode2[1].trim().toString());
///*  7222 */       if ((ip.equals("127.0.0.1")) || (ip.equals("0:0:0:0:0:0:0:1")) || (isRal(header, header2, header3, header4, userCode, "").booleanValue())) {
				 if ((ip.equals("120.77.250.17")) || (ip.equals("0:0:0:0:0:0:0:1")) || (isRal(header, header2, header3, header4, userCode, "").booleanValue())) {
/*  7223 */         System.err.println("验证通过");
/*       */ 
/*  7225 */         Long accessToken = Long.valueOf(header);
/*  7226 */         if ((phone == null) || (boUsers == null)) {
/*  7227 */           this.requestJson.setData(map);
/*  7228 */           this.requestJson.setMessage("Invalid_User");
/*  7229 */           this.requestJson.setSuccess(true);
/*       */         }
///*  7231 */         else if ((ip.equals("127.0.0.1")) || (ip.equals("0:0:0:0:0:0:0:1")) || (header4.equals(phone.getAccessToken())) || (accessToken.longValue() < Long.valueOf(phone.getAccessTokenTime()).longValue())) {
					else if ((ip.equals("120.77.250.17")) || (ip.equals("0:0:0:0:0:0:0:1")) || (header4.equals(phone.getAccessToken())) || (accessToken.longValue() < Long.valueOf(phone.getAccessTokenTime()).longValue())) {
/*  7232 */           System.err.println(header4);
/*  7233 */           System.err.println(phone.getAccessToken());
///*  7234 */           if ((ip.equals("127.0.0.1")) || (ip.equals("0:0:0:0:0:0:0:1"))) {
					  if ((ip.equals("120.77.250.17")) || (ip.equals("0:0:0:0:0:0:0:1"))) {
/*  7235 */             Boolean commandMode = commandMode(userCode2[0].trim().toString(), this.modelId);
/*  7236 */             if (!commandMode.booleanValue()) {
/*  7237 */               this.requestJson.setData(map);
/*  7238 */               this.requestJson.setMessage("该情景模式没有情景信息");
/*  7239 */               this.requestJson.setSuccess(true);
/*       */             } else {
/*  7241 */               this.requestJson.setData(map);
/*  7242 */               this.requestJson.setMessage("情景执行完成");
/*       */ 
/*  7244 */               this.requestJson.setSuccess(true);
/*       */             }
/*       */ 
/*       */           }
/*       */           else
/*       */           {
/*  7252 */             System.err.println(" userCode-- " + userCode2[0].trim().toString());
/*  7253 */             System.err.println(" modelId-- " + this.modelId);
/*  7254 */             Boolean commandMode = commandMode(userCode2[0].trim().toString(), this.modelId);
/*  7255 */             if (!commandMode.booleanValue()) {
/*  7256 */               this.requestJson.setData(map);
/*  7257 */               this.requestJson.setMessage("该情景模式没有情景信息");
/*  7258 */               this.requestJson.setSuccess(true);
/*       */             } else {
/*  7260 */               this.requestJson.setData(map);
/*  7261 */               this.requestJson.setMessage("情景执行完成");
/*       */ 
/*  7263 */               this.requestJson.setSuccess(true);
/*       */             }
/*       */ 
/*       */           }
/*       */ 
/*       */         }
/*       */         else
/*       */         {
/*  7272 */           System.err.println(header4);
/*  7273 */           System.err.println(phone.getAccessToken());
/*       */ 
/*  7275 */           this.requestJson.setMessage("超时了");
/*  7276 */           this.requestJson.setSuccess(false);
/*       */         }
/*       */ 
/*       */       }
/*       */       else
/*       */       {
/*  7282 */         System.err.println("验证不通过");
/*  7283 */         this.requestJson.setMessage("验证不通过");
/*  7284 */         this.requestJson.setSuccess(false);
/*       */       }
/*  7286 */       System.err.println(userCode2[0].trim().toString());
/*  7287 */       System.err.println(this.modelId);
/*       */     } else {
///*  7289 */       if ((ip.equals("127.0.0.1")) || (ip.equals("0:0:0:0:0:0:0:1")) || (isRal(header, header2, header3, header4, userCode, "").booleanValue())) {
				if ((ip.equals("120.77.250.17")) || (ip.equals("0:0:0:0:0:0:0:1")) || (isRal(header, header2, header3, header4, userCode, "").booleanValue())) {
/*  7290 */         System.err.println("验证通过");
/*       */ 
/*  7292 */         Long accessToken = Long.valueOf(header);
/*  7293 */         BoUsers boUsers = this.boUserServicess.findByUserUserCode(userCode);
///*  7294 */         if ((ip.equals("127.0.0.1")) || (ip.equals("0:0:0:0:0:0:0:1")) || (accessToken.longValue() < Long.valueOf(boUsers.getAccessTokenTime()).longValue())) {
					if ((ip.equals("120.77.250.17")) || (ip.equals("0:0:0:0:0:0:0:1")) || (accessToken.longValue() < Long.valueOf(boUsers.getAccessTokenTime()).longValue())) {
/*  7295 */           if (boUsers == null) {
/*  7296 */             this.requestJson.setMessage("Invalid_User");
/*  7297 */             this.requestJson.setSuccess(true);
/*       */           }
///*  7299 */           else if ((ip.equals("127.0.0.1")) || (ip.equals("0:0:0:0:0:0:0:1"))) {
					 else if ((ip.equals("120.77.250.17")) || (ip.equals("0:0:0:0:0:0:0:1"))) {
/*  7300 */             Boolean commandMode = commandMode(userCode, this.modelId);
/*  7301 */             if (!commandMode.booleanValue()) {
/*  7302 */               this.requestJson.setData(map);
/*  7303 */               this.requestJson.setMessage("该情景模式没有情景信息");
/*  7304 */               this.requestJson.setSuccess(true);
/*       */             } else {
/*  7306 */               this.requestJson.setData(map);
/*  7307 */               this.requestJson.setMessage("情景执行完成");
/*       */ 
/*  7309 */               this.requestJson.setSuccess(true);
/*       */             }
/*       */ 
/*       */           }
/*       */           else
/*       */           {
/*  7317 */             System.err.println(" userCode-- " + userCode);
/*  7318 */             System.err.println(" modelId-- " + this.modelId);
/*  7319 */             Boolean commandMode = commandMode(userCode, this.modelId);
/*  7320 */             if (!commandMode.booleanValue()) {
/*  7321 */               this.requestJson.setData(map);
/*  7322 */               this.requestJson.setMessage("该情景模式没有情景信息");
/*  7323 */               this.requestJson.setSuccess(true);
/*       */             } else {
/*  7325 */               this.requestJson.setData(map);
/*  7326 */               this.requestJson.setMessage("情景执行完成");
/*       */ 
/*  7328 */               this.requestJson.setSuccess(true);
/*       */             }
/*       */ 
/*       */           }
/*       */ 
/*       */         }
/*       */         else
/*       */         {
/*  7342 */           this.requestJson.setMessage("超时了");
/*  7343 */           this.requestJson.setSuccess(false);
/*       */         }
/*       */       }
/*       */       else {
/*  7347 */         System.err.println("验证不通过");
/*  7348 */         this.requestJson.setMessage("验证不通过");
/*  7349 */         this.requestJson.setSuccess(false);
/*       */       }
/*  7351 */       System.err.println(userCode);
/*  7352 */       System.err.println(this.modelId);
/*       */     }
/*  7354 */     return "success";
/*       */   }
/*       */ 
/*       */   @Action(value="addzigbeedevice", results={@org.apache.struts2.convention.annotation.Result(type="json", params={"root", "requestJson"})})
/*       */   public String addZigBeeDevice()
/*       */   {
/*  7360 */     this.requestJson = new RequestJson();
/*  7361 */     Map map = new HashMap();
/*  7362 */     HttpServletRequest request = ServletActionContext.getRequest();
/*  7363 */     String header = request.getHeader("timestamp");
/*  7364 */     String header2 = request.getHeader("nonce");
/*  7365 */     String header3 = request.getHeader("sign");
/*  7366 */     String header4 = request.getHeader("access_token");
/*  7367 */     String userCode = request.getHeader("userCode");
/*  7368 */     if (userCode.contains(",")) {
/*  7369 */       String[] userCode2 = userCode.split(",");
/*  7370 */       BoUsers boUsers = this.boUserServicess.findByUserUserCode(userCode2[0].trim().toString());
/*  7371 */       BoUsers phone = this.boUserServicess.findByUserPhone(userCode2[1].trim().toString());
/*  7372 */       Boolean ral = isRal(header, header2, header3, header4, userCode, "修改主机昵称");
/*  7373 */       if (ral.booleanValue()) {
/*  7374 */         System.err.println("验证通过");
/*  7375 */         Long accessToken = Long.valueOf(header);
/*       */ 
/*  7377 */         if ((phone == null) || (boUsers == null)) {
/*  7378 */           this.requestJson.setData(map);
/*  7379 */           this.requestJson.setMessage("Invalid_User");
/*  7380 */           this.requestJson.setSuccess(true);
/*       */         }
/*  7382 */         else if (header4.equals(phone.getAccessToken())) {
/*  7383 */           if (accessToken.longValue() < Long.valueOf(phone.getAccessTokenTime()).longValue()) {
/*  7384 */             BoUserDevices userDevices = this.boUserDevicesServicess.findBy(userCode2[0].trim().toString(), this.deviceCode);
/*  7385 */             BoRoom boRoom = this.boRoomService.findByCode(this.roomCode);
/*  7386 */             BoHostDevice hostDevice = this.boHostDeviceService.findBydeviceAddress(userCode2[0].trim().toString(), this.deviceAddress);
/*  7387 */             if (boRoom != null)
/*  7388 */               if (hostDevice == null) {
/*  7389 */                 BoHostDevice boHostDevice = new BoHostDevice();
/*  7390 */                 boHostDevice.setDeviceType(this.deviceType);
/*  7391 */                 boHostDevice.setBoDevice(userDevices.getBoDevice());
/*  7392 */                 boHostDevice.setNickName(this.nickName);
/*  7393 */                 boHostDevice.setBoRoom(boRoom);
/*  7394 */                 boHostDevice.setDeviceClassify(this.fid1);
/*  7395 */                 boHostDevice.setBoUsers(boUsers);
/*  7396 */                 boHostDevice.setIco(this.ico);
/*  7397 */                 this.boHostDeviceService.save(boHostDevice);
/*       */               } else {
/*  7399 */                 hostDevice.setBoDevice(userDevices.getBoDevice());
/*  7400 */                 hostDevice.setDeviceType(this.deviceType);
/*  7401 */                 hostDevice.setNickName(this.nickName);
/*  7402 */                 hostDevice.setBoRoom(boRoom);
/*  7403 */                 hostDevice.setDeviceClassify(this.fid1);
/*  7404 */                 hostDevice.setBoUsers(boUsers);
/*  7405 */                 hostDevice.setIco(this.ico);
/*  7406 */                 this.boHostDeviceService.update(hostDevice);
/*       */               }
/*       */           }
/*       */           else {
/*  7410 */             System.err.println("AToken时间戳超时了");
/*  7411 */             this.requestJson.setMessage("超时了");
/*  7412 */             this.requestJson.setSuccess(false);
/*       */           }
/*       */         } else {
/*  7415 */           System.err.println("AToken超时了");
/*  7416 */           this.requestJson.setMessage("超时了");
/*  7417 */           this.requestJson.setSuccess(false);
/*       */         }
/*       */       }
/*       */       else {
/*  7421 */         System.err.println("验证不通过");
/*  7422 */         this.requestJson.setMessage("验证不通过");
/*  7423 */         this.requestJson.setSuccess(false);
/*       */       }
/*       */     } else {
/*  7426 */       Boolean ral = isRal(header, header2, header3, header4, userCode, "修改主机昵称");
/*  7427 */       if (ral.booleanValue()) {
/*  7428 */         System.err.println("验证通过");
/*  7429 */         Long accessToken = Long.valueOf(header);
/*  7430 */         BoUsers boUsers = this.boUserServicess.findByUserUserCode(userCode);
/*  7431 */         if (boUsers == null) {
/*  7432 */           this.requestJson.setData(map);
/*  7433 */           this.requestJson.setMessage("Invalid_User");
/*  7434 */           this.requestJson.setSuccess(true);
/*       */         }
/*  7436 */         else if (accessToken.longValue() < Long.valueOf(boUsers.getAccessTokenTime()).longValue()) {
/*  7437 */           BoUserDevices userDevices = this.boUserDevicesServicess.findBy(userCode, this.deviceCode);
/*  7438 */           BoRoom boRoom = this.boRoomService.findByCode(this.roomCode);
/*  7439 */           BoHostDevice hostDevice = this.boHostDeviceService.findBydeviceAddress(userCode, this.deviceAddress);
/*  7440 */           if (boRoom != null)
/*  7441 */             if (hostDevice == null) {
/*  7442 */               BoHostDevice boHostDevice = new BoHostDevice();
/*  7443 */               boHostDevice.setDeviceType(this.deviceType);
/*  7444 */               boHostDevice.setBoDevice(userDevices.getBoDevice());
/*  7445 */               boHostDevice.setNickName(this.nickName);
/*  7446 */               boHostDevice.setBoRoom(boRoom);
/*  7447 */               boHostDevice.setDeviceClassify(this.fid1);
/*  7448 */               boHostDevice.setBoUsers(boUsers);
/*  7449 */               boHostDevice.setIco(this.ico);
/*  7450 */               this.boHostDeviceService.save(boHostDevice);
/*       */             } else {
/*  7452 */               hostDevice.setBoDevice(userDevices.getBoDevice());
/*  7453 */               hostDevice.setDeviceType(this.deviceType);
/*  7454 */               hostDevice.setNickName(this.nickName);
/*  7455 */               hostDevice.setBoRoom(boRoom);
/*  7456 */               hostDevice.setDeviceClassify(this.fid1);
/*  7457 */               hostDevice.setBoUsers(boUsers);
/*  7458 */               hostDevice.setIco(this.ico);
/*  7459 */               this.boHostDeviceService.update(hostDevice);
/*       */             }
/*       */         }
/*       */         else {
/*  7463 */           this.requestJson.setMessage("超时了");
/*  7464 */           this.requestJson.setSuccess(false);
/*       */         }
/*       */       }
/*       */       else {
/*  7468 */         System.err.println("验证不通过");
/*  7469 */         this.requestJson.setMessage("验证不通过");
/*  7470 */         this.requestJson.setSuccess(false);
/*       */       }
/*       */     }
/*  7473 */     return "success";
/*       */   }
/*       */ 
/*       */   @Action(value="gainmodelinfo", results={@org.apache.struts2.convention.annotation.Result(type="json", params={"root", "requestJson"})})
/*       */   public String gainModelInfo()
/*       */   {
/*  7484 */     this.requestJson = new RequestJson();
/*  7485 */     Map is = new HashMap();
/*  7486 */     HttpServletRequest request = ServletActionContext.getRequest();
/*  7487 */     String header = request.getHeader("timestamp");
/*  7488 */     String header2 = request.getHeader("nonce");
/*  7489 */     String header3 = request.getHeader("sign");
/*  7490 */     String header4 = request.getHeader("access_token");
/*  7491 */     String userCode = request.getHeader("userCode");
/*       */     List voList;
/*  7492 */     if (userCode.contains(",")) {
/*  7493 */       String[] userCode2 = userCode.split(",");
/*  7494 */       BoUsers boUsers = this.boUserServicess.findByUserUserCode(userCode2[0].trim().toString());
/*  7495 */       BoUsers phone = this.boUserServicess.findByUserPhone(userCode2[1].trim().toString());
/*  7496 */       Boolean ral = isRal(header, header2, header3, header4, userCode, "获取情景模式信息列表");
/*  7497 */       if (ral.booleanValue()) {
/*  7498 */         System.err.println("验证通过");
/*  7499 */         Long accessToken = Long.valueOf(header);
/*  7500 */         if ((phone == null) || (boUsers == null)) {
/*  7501 */           this.requestJson.setData(is);
/*  7502 */           this.requestJson.setMessage("Invalid_User");
/*  7503 */           this.requestJson.setSuccess(true);
/*       */         }
/*  7505 */         else if (header4.equals(phone.getAccessToken())) {
/*  7506 */           if (accessToken.longValue() < Long.valueOf(phone.getAccessTokenTime()).longValue()) {
/*  7507 */             logger.info("获取情景模式信息列表 ]-userCode " + userCode2[0].trim().toString() + "<------>" + "modelId " + this.modelId);
/*  7508 */             List<BoModelInfo> list = this.boModelInfoServicess.getBy(userCode2[0].trim().toString(), this.modelId);
/*  7509 */             if (list.size() <= 0) {
/*  7510 */               this.requestJson.setData(is);
/*  7511 */               this.requestJson.setMessage("没有找到该情景模式信息");
/*  7512 */               this.requestJson.setSuccess(true);
/*       */             } else {
/*  7514 */               voList = new ArrayList();
/*  7515 */               for (BoModelInfo boModelInfo : list) {
/*  7516 */                 Map map = new HashMap();
/*  7517 */                 map.put("modelId", boModelInfo.getBoModel().getModelId());
/*  7518 */                 map.put("deviceAddress", boModelInfo.getDeviceAddress());
/*  7519 */                 map.put("deviceCode", boModelInfo.getBoDevice().getDeviceCode());
/*  7520 */                 map.put("deviceType", boModelInfo.getDeviceType());
/*  7521 */                 map.put("controlCommand", boModelInfo.getControlCommand());
/*  7522 */                 map.put("delayValues", boModelInfo.getDelayValues());
/*       */ 
/*  7524 */                 voList.add(map);
/*       */               }
/*  7526 */               this.requestJson.setData(voList);
/*       */             }
/*       */           } else {
/*  7529 */             System.err.println("AToken时间戳超时了");
/*  7530 */             this.requestJson.setData(is);
/*  7531 */             this.requestJson.setMessage("超时了");
/*  7532 */             this.requestJson.setSuccess(false);
/*       */           }
/*       */         } else {
/*  7535 */           System.err.println("AToken超时了");
/*  7536 */           this.requestJson.setData(is);
/*  7537 */           this.requestJson.setMessage("超时了");
/*  7538 */           this.requestJson.setSuccess(false);
/*       */         }
/*       */ 
/*       */       }
/*       */       else
/*       */       {
/*  7544 */         System.err.println("验证不通过");
/*  7545 */         this.requestJson.setMessage("验证不通过");
/*  7546 */         this.requestJson.setSuccess(false);
/*       */       }
/*       */     } else {
/*  7549 */       Boolean ral = isRal(header, header2, header3, header4, userCode, "修改主机昵称");
/*  7550 */       if (ral.booleanValue()) {
/*  7551 */         System.err.println("验证通过");
/*  7552 */         Long accessToken = Long.valueOf(header);
/*  7553 */         BoUsers boUsers = this.boUserServicess.findByUserUserCode(userCode);
/*  7554 */         if (accessToken.longValue() < Long.valueOf(boUsers.getAccessTokenTime()).longValue()) {
/*  7555 */           if (boUsers == null) {
/*  7556 */             this.requestJson.setMessage("Invalid_User");
/*  7557 */             this.requestJson.setSuccess(true);
/*       */           } else {
/*  7559 */             logger.info("获取情景模式信息列表 ]-userCode " + userCode + "<------>" + "modelId " + this.modelId);
/*  7560 */             List<BoModelInfo> list = this.boModelInfoServicess.getBy(userCode, this.modelId);
/*  7561 */             if (list.size() <= 0) {
/*  7562 */               this.requestJson.setData(is);
/*  7563 */               this.requestJson.setMessage("没有找到该情景模式信息");
/*  7564 */               this.requestJson.setSuccess(true);
/*       */             } else {
/*  7566 */               voList = new ArrayList();
/*  7567 */               for (BoModelInfo boModelInfo : list) {
/*  7568 */                 Map map = new HashMap();
/*  7569 */                 map.put("modelId", boModelInfo.getBoModel().getModelId());
/*  7570 */                 map.put("deviceAddress", boModelInfo.getDeviceAddress());
/*  7571 */                 map.put("deviceCode", boModelInfo.getBoDevice().getDeviceCode());
/*  7572 */                 map.put("deviceType", boModelInfo.getDeviceType());
/*  7573 */                 map.put("controlCommand", boModelInfo.getControlCommand());
/*  7574 */                 map.put("delayValues", boModelInfo.getDelayValues());
/*       */ 
/*  7576 */                 voList.add(map);
/*       */               }
/*  7578 */               this.requestJson.setData(voList);
/*       */             }
/*       */           }
/*       */         } else {
/*  7582 */           this.requestJson.setData(is);
/*  7583 */           this.requestJson.setMessage("超时了");
/*  7584 */           this.requestJson.setSuccess(false);
/*       */         }
/*       */       }
/*       */       else {
/*  7588 */         System.err.println("验证不通过");
/*  7589 */         this.requestJson.setMessage("验证不通过");
/*  7590 */         this.requestJson.setSuccess(false);
/*       */       }
/*       */     }
/*  7593 */     return "success";
/*       */   }
/*       */ 
/*       */   @Action(value="addmodelinfo", results={@org.apache.struts2.convention.annotation.Result(type="json", params={"root", "requestJson"})})
/*       */   public String addModelInfo()
/*       */   {
/*  7607 */     this.requestJson = new RequestJson();
/*  7608 */     Map map = new HashMap();
/*  7609 */     HttpServletRequest request = ServletActionContext.getRequest();
/*  7610 */     String header = request.getHeader("timestamp");
/*  7611 */     String header2 = request.getHeader("nonce");
/*  7612 */     String header3 = request.getHeader("sign");
/*  7613 */     String header4 = request.getHeader("access_token");
/*  7614 */     String userCode = request.getHeader("userCode");
/*       */     List by;
///*       */     Object persons;
/*  7615 */     if (userCode.contains(",")) {
/*  7616 */       String[] userCode2 = userCode.split(",");
/*  7617 */       BoUsers boUsers = this.boUserServicess.findByUserUserCode(userCode2[0].trim().toString());
/*  7618 */       BoUsers phone = this.boUserServicess.findByUserPhone(userCode2[1].trim().toString());
/*  7619 */       Boolean ral = isRal(header, header2, header3, header4, userCode, "添加情景模式信息");
/*  7620 */       if (ral.booleanValue()) {
/*  7621 */         System.err.println("验证通过");
/*  7622 */         Long accessToken = Long.valueOf(header);
/*  7623 */         if ((phone == null) || (boUsers == null)) {
/*  7624 */           this.requestJson.setData(map);
/*  7625 */           this.requestJson.setMessage("Invalid_User");
/*  7626 */           this.requestJson.setSuccess(true);
/*       */         }
/*  7628 */         else if (header4.equals(phone.getAccessToken())) {
/*  7629 */           if (accessToken.longValue() < Long.valueOf(phone.getAccessTokenTime()).longValue()) {
/*  7630 */             System.err.println(">>>-- " + userCode2[0].trim().toString());
/*  7631 */             System.err.println(">>>-- " + this.modelId);
/*  7632 */             List<BoModelInfo> list = this.boModelInfoServicess.getBy(userCode2[0].trim().toString(), this.modelId);
/*  7633 */             by = this.boModelService.getBy(userCode2[0].trim().toString(), this.modelId);
/*  7634 */             if (by.size() <= 0) {
/*  7635 */               this.requestJson.setData(map);
/*  7636 */               this.requestJson.setMessage("没有该情景模式");
/*  7637 */               this.requestJson.setSuccess(true);
/*       */             } else {
/*  7639 */               for (BoModelInfo boModelInfo : list) {
/*  7640 */                 this.boModelInfoServicess.delete(boModelInfo);
/*       */               }
/*  7642 */               JSONArray json = JSONArray.fromObject(this.modelInfo);
/*  7643 */               System.err.println("modelInfo " + this.modelInfo);
/*  7644 */               System.err.println("userCode " + userCode2[0].trim().toString());
/*  7645 */               List<BoModelInfo> persons = (List)JSONArray.toCollection(json, BoModelInfo.class);
/*  7646 */               for (BoModelInfo boModelInfo : persons) {
/*  7647 */                 BoHostDevice hostDevice = this.boHostDeviceService
/*  7648 */                   .findBydeviceAddress(userCode2[0].trim().toString(), boModelInfo.getDeviceAddress());
/*  7649 */                 BoModel boModel = this.boModelService.find(userCode2[0].trim().toString(), this.modelId);
/*  7650 */                 boModelInfo.setBoModel(boModel);
/*  7651 */                 boModelInfo.setBoDevice(hostDevice.getBoDevice());
/*  7652 */                 boModelInfo.setBoUsers(boUsers);
/*  7653 */                 boModelInfo.setDeviceAddress(boModelInfo.getDeviceAddress());
/*  7654 */                 boModelInfo.setDeviceType(boModelInfo.getDeviceType());
/*  7655 */                 boModelInfo.setControlCommand(boModelInfo.getControlCommand());
/*  7656 */                 boModelInfo.setDelayValues(boModelInfo.getDelayValues());
/*  7657 */                 BoModelInfo save = (BoModelInfo)this.boModelInfoServicess.save(boModelInfo);
/*  7658 */                 if (save != null) {
/*  7659 */                   this.requestJson.setData(map);
/*  7660 */                   this.requestJson.setMessage("添加成功");
/*  7661 */                   this.requestJson.setSuccess(true);
/*       */                 } else {
/*  7663 */                   this.requestJson.setData(map);
/*  7664 */                   this.requestJson.setMessage("添加失败");
/*  7665 */                   this.requestJson.setSuccess(false);
/*       */                 }
/*       */               }
/*       */             }
/*       */           } else {
/*  7670 */             System.err.println("AToken时间戳超时了");
/*  7671 */             this.requestJson.setData(map);
/*  7672 */             this.requestJson.setMessage("超时了");
/*  7673 */             this.requestJson.setSuccess(false);
/*       */           }
/*       */         } else {
/*  7676 */           System.err.println("AToken超时了");
/*  7677 */           this.requestJson.setData(map);
/*  7678 */           this.requestJson.setMessage("超时了");
/*  7679 */           this.requestJson.setSuccess(false);
/*       */         }
/*       */ 
/*       */       }
/*       */       else
/*       */       {
/*  7685 */         System.err.println("验证不通过");
/*  7686 */         this.requestJson.setData(map);
/*  7687 */         this.requestJson.setMessage("验证不通过");
/*  7688 */         this.requestJson.setSuccess(false);
/*       */       }
/*       */     } else {
/*  7691 */       Boolean ral = isRal(header, header2, header3, header4, userCode, "修改主机昵称");
/*  7692 */       if (ral.booleanValue()) {
/*  7693 */         System.err.println("验证通过");
/*  7694 */         Long accessToken = Long.valueOf(header);
/*  7695 */         BoUsers boUsers = this.boUserServicess.findByUserUserCode(userCode);
/*  7696 */         if (accessToken.longValue() < Long.valueOf(boUsers.getAccessTokenTime()).longValue()) {
/*  7697 */           if (boUsers == null) {
/*  7698 */             this.requestJson.setMessage("Invalid_User");
/*  7699 */             this.requestJson.setSuccess(true);
/*       */           } else {
/*  7701 */             System.err.println(">>>-- " + userCode);
/*  7702 */             System.err.println(">>>-- " + this.modelId);
/*  7703 */             List<BoModelInfo> list = this.boModelInfoServicess.getBy(userCode, this.modelId);
/*  7704 */             by = this.boModelService.getBy(userCode, this.modelId);
/*  7705 */             if (by.size() <= 0) {
/*  7706 */               this.requestJson.setData(map);
/*  7707 */               this.requestJson.setMessage("没有该情景模式");
/*  7708 */               this.requestJson.setSuccess(true);
/*       */             } else {
/*  7710 */               for (BoModelInfo boModelInfo : list) {
/*  7711 */                 this.boModelInfoServicess.delete(boModelInfo);
/*       */               }
/*  7713 */               JSONArray json = JSONArray.fromObject(this.modelInfo);
/*  7714 */               System.err.println("modelInfo " + this.modelInfo);
/*  7715 */               System.err.println("userCode " + userCode);
/*  7716 */               List persons = (List)JSONArray.toCollection(json, BoModelInfo.class);
/*  7717 */               for (persons = (List) persons.iterator(); ((Iterator)persons).hasNext(); ) { BoModelInfo boModelInfo = (BoModelInfo)((Iterator)persons).next();
/*  7718 */                 BoHostDevice hostDevice = this.boHostDeviceService
/*  7719 */                   .findBydeviceAddress(userCode, boModelInfo.getDeviceAddress());
/*  7720 */                 BoModel boModel = this.boModelService.find(userCode, this.modelId);
/*  7721 */                 boModelInfo.setBoModel(boModel);
/*  7722 */                 boModelInfo.setBoDevice(hostDevice.getBoDevice());
/*  7723 */                 boModelInfo.setBoUsers(boUsers);
/*  7724 */                 boModelInfo.setDeviceAddress(boModelInfo.getDeviceAddress());
/*  7725 */                 boModelInfo.setDeviceType(boModelInfo.getDeviceType());
/*  7726 */                 boModelInfo.setControlCommand(boModelInfo.getControlCommand());
/*  7727 */                 boModelInfo.setDelayValues(boModelInfo.getDelayValues());
/*  7728 */                 BoModelInfo save = (BoModelInfo)this.boModelInfoServicess.save(boModelInfo);
/*  7729 */                 if (save != null) {
/*  7730 */                   this.requestJson.setData(map);
/*  7731 */                   this.requestJson.setMessage("添加成功");
/*  7732 */                   this.requestJson.setSuccess(true);
/*       */                 } else {
/*  7734 */                   this.requestJson.setData(map);
/*  7735 */                   this.requestJson.setMessage("添加失败");
/*  7736 */                   this.requestJson.setSuccess(false);
/*       */                 } }
/*       */             }
/*       */           }
/*       */         }
/*       */         else
/*       */         {
/*  7743 */           this.requestJson.setData(map);
/*  7744 */           this.requestJson.setMessage("超时了");
/*  7745 */           this.requestJson.setSuccess(false);
/*       */         }
/*       */       }
/*       */       else {
/*  7749 */         System.err.println("验证不通过");
/*  7750 */         this.requestJson.setData(map);
/*  7751 */         this.requestJson.setMessage("验证不通过");
/*  7752 */         this.requestJson.setSuccess(false);
/*       */       }
/*       */     }
/*       */ 
/*  7756 */     return "success";
/*       */   }
/*       */ 
/*       */   @Action(value="gainmodel", results={@org.apache.struts2.convention.annotation.Result(type="json", params={"root", "requestJson"})})
/*       */   public String gainModel()
/*       */   {
/*  7767 */     this.requestJson = new RequestJson();
/*  7768 */     Map maps = new HashMap();
/*  7769 */     HttpServletRequest request = ServletActionContext.getRequest();
/*  7770 */     String header = request.getHeader("timestamp");
/*  7771 */     String header2 = request.getHeader("nonce");
/*  7772 */     String header3 = request.getHeader("sign");
/*  7773 */     String header4 = request.getHeader("access_token");
/*  7774 */     String userCode = request.getHeader("userCode");
/*       */     List voList;
/*  7775 */     if (userCode.contains(",")) {
/*  7776 */       String[] userCode2 = userCode.split(",");
/*  7777 */       BoUsers boUsers = this.boUserServicess.findByUserUserCode(userCode2[0].trim().toString());
/*  7778 */       BoUsers phone = this.boUserServicess.findByUserPhone(userCode2[1].trim().toString());
/*  7779 */       Boolean ral = isRal(header, header2, header3, header4, userCode, "获取用户下的情景模式");
/*  7780 */       if (ral.booleanValue()) {
/*  7781 */         System.err.println("验证通过");
/*  7782 */         Long accessToken = Long.valueOf(header);
/*  7783 */         if ((phone == null) || (boUsers == null)) {
/*  7784 */           this.requestJson.setData(maps);
/*  7785 */           this.requestJson.setMessage("Invalid_User");
/*  7786 */           this.requestJson.setSuccess(true);
/*       */         }
/*  7788 */         else if (header4.equals(phone.getAccessToken())) {
/*  7789 */           if (accessToken.longValue() < Long.valueOf(phone.getAccessTokenTime()).longValue()) {
/*  7790 */             List<BoModel> list = this.boModelService.getListBy(userCode2[0].trim().toString());
/*  7791 */             if (list.size() <= 0) {
/*  7792 */               this.requestJson.setData(maps);
/*  7793 */               this.requestJson.setMessage("没有找到");
/*  7794 */               this.requestJson.setSuccess(true);
/*       */             } else {
/*  7796 */               voList = new ArrayList();
/*  7797 */               for (BoModel boModel : list) {
/*  7798 */                 Map map = new HashMap();
/*  7799 */                 map.put("modelId", boModel.getModelId());
/*  7800 */                 map.put("ico", boModel.getIco().toString());
/*  7801 */                 map.put("modelName", boModel.getName().toString());
/*  7802 */                 voList.add(map);
/*       */               }
/*       */ 
/*  7805 */               this.requestJson.setData(voList);
/*       */             }
/*       */           }
/*       */           else {
/*  7809 */             System.err.println("AToken时间戳超时了");
/*  7810 */             this.requestJson.setData(maps);
/*  7811 */             this.requestJson.setMessage("超时了");
/*  7812 */             this.requestJson.setSuccess(false);
/*       */           }
/*       */         } else {
/*  7815 */           System.err.println("AToken超时了");
/*  7816 */           this.requestJson.setData(maps);
/*  7817 */           this.requestJson.setMessage("超时了");
/*  7818 */           this.requestJson.setSuccess(false);
/*       */         }
/*       */       }
/*       */       else {
/*  7822 */         System.err.println("验证不通过");
/*  7823 */         this.requestJson.setData(maps);
/*  7824 */         this.requestJson.setMessage("验证不通过");
/*  7825 */         this.requestJson.setSuccess(false);
/*       */       }
/*       */     } else {
/*  7828 */       Boolean ral = isRal(header, header2, header3, header4, userCode, "修改主机昵称");
/*  7829 */       if (ral.booleanValue()) {
/*  7830 */         System.err.println("验证通过");
/*  7831 */         Long accessToken = Long.valueOf(header);
/*  7832 */         BoUsers boUsers = this.boUserServicess.findByUserUserCode(userCode);
/*  7833 */         if (boUsers == null) {
/*  7834 */           this.requestJson.setData(maps);
/*  7835 */           this.requestJson.setMessage("Invalid_User");
/*  7836 */           this.requestJson.setSuccess(true);
/*       */         }
/*  7838 */         else if (accessToken.longValue() < Long.valueOf(boUsers.getAccessTokenTime()).longValue())
/*       */         {
/*  7840 */           List<BoModel> list = this.boModelService.getListBy(userCode);
/*  7841 */           if (list.size() <= 0) {
/*  7842 */             this.requestJson.setData(maps);
/*  7843 */             this.requestJson.setMessage("没有找到");
/*  7844 */             this.requestJson.setSuccess(true);
/*       */           } else {
/*  7846 */             voList = new ArrayList();
/*  7847 */             for (BoModel boModel : list) {
/*  7848 */               Map map = new HashMap();
/*  7849 */               map.put("modelId", boModel.getModelId());
/*  7850 */               map.put("ico", boModel.getIco().toString());
/*  7851 */               map.put("modelName", boModel.getName().toString());
/*  7852 */               voList.add(map);
/*       */             }
/*       */ 
/*  7855 */             this.requestJson.setData(voList);
/*       */           }
/*       */         }
/*       */         else {
/*  7859 */           this.requestJson.setData(maps);
/*  7860 */           this.requestJson.setMessage("超时了");
/*  7861 */           this.requestJson.setSuccess(false);
/*       */         }
/*       */ 
/*       */       }
/*       */       else
/*       */       {
/*  7867 */         System.err.println("验证不通过");
/*  7868 */         this.requestJson.setData(maps);
/*  7869 */         this.requestJson.setMessage("验证不通过");
/*  7870 */         this.requestJson.setSuccess(false);
/*       */       }
/*       */     }
/*  7873 */     return "success";
/*       */   }
/*       */ 
/*       */   @Action(value="gainModelTiming", results={@org.apache.struts2.convention.annotation.Result(type="json", params={"root", "requestJson"})})
/*       */   public String gainModelTiming() {
/*  7878 */     this.requestJson = new RequestJson();
/*  7879 */     Map map = new HashMap();
/*  7880 */     HttpServletRequest request = ServletActionContext.getRequest();
/*  7881 */     String header = request.getHeader("timestamp");
/*  7882 */     String header2 = request.getHeader("nonce");
/*  7883 */     String header3 = request.getHeader("sign");
/*  7884 */     String header4 = request.getHeader("access_token");
/*  7885 */     String userCode = request.getHeader("userCode");
/*  7886 */     if (userCode.contains(",")) {
/*  7887 */       String[] userCode2 = userCode.split(",");
/*  7888 */       BoUsers boUsers = this.boUserServicess.findByUserUserCode(userCode2[0].trim().toString());
/*  7889 */       BoUsers phone = this.boUserServicess.findByUserPhone(userCode2[1].trim().toString());
/*  7890 */       Boolean ral = isRal(header, header2, header3, header4, userCode, "修改主机昵称");
/*  7891 */       if (ral.booleanValue()) {
/*  7892 */         System.err.println("验证通过");
/*  7893 */         Long accessToken = Long.valueOf(header);
/*       */ 
/*  7895 */         if ((phone == null) || (boUsers == null)) {
/*  7896 */           this.requestJson.setData(map);
/*  7897 */           this.requestJson.setMessage("Invalid_User");
/*  7898 */           this.requestJson.setSuccess(true);
/*       */         }
/*  7900 */         else if (header4.equals(phone.getAccessToken())) {
/*  7901 */           if (accessToken.longValue() < Long.valueOf(phone.getAccessTokenTime()).longValue()) {
/*  7902 */             BoModel boModel = this.boModelService.find(userCode2[0].trim().toString(), this.modelId);
/*  7903 */             if (boModel == null) {
/*  7904 */               this.requestJson.setData(map);
/*  7905 */               this.requestJson.setMessage("没有找到该情景模式");
/*  7906 */               this.requestJson.setSuccess(true);
/*       */             } else {
/*  7908 */               map.put("modelWeek", boModel.getWeek());
/*  7909 */               map.put("modelTime", boModel.getTime());
/*  7910 */               this.requestJson.setData(map);
/*       */             }
/*       */           } else {
/*  7913 */             System.err.println("AToken时间戳超时了");
/*  7914 */             this.requestJson.setData(map);
/*  7915 */             this.requestJson.setMessage("超时了");
/*  7916 */             this.requestJson.setSuccess(false);
/*       */           }
/*       */         } else {
/*  7919 */           System.err.println("AToken超时了");
/*  7920 */           this.requestJson.setData(map);
/*  7921 */           this.requestJson.setMessage("超时了");
/*  7922 */           this.requestJson.setSuccess(false);
/*       */         }
/*       */       }
/*       */       else {
/*  7926 */         System.err.println("验证不通过");
/*  7927 */         this.requestJson.setData(map);
/*  7928 */         this.requestJson.setMessage("验证不通过");
/*  7929 */         this.requestJson.setSuccess(false);
/*       */       }
/*       */     } else {
/*  7932 */       Boolean ral = isRal(header, header2, header3, header4, userCode, "修改主机昵称");
/*  7933 */       if (ral.booleanValue()) {
/*  7934 */         System.err.println("验证通过");
/*  7935 */         Long accessToken = Long.valueOf(header);
/*  7936 */         BoUsers boUsers = this.boUserServicess.findByUserUserCode(userCode);
/*  7937 */         if (boUsers == null) {
/*  7938 */           this.requestJson.setData(map);
/*  7939 */           this.requestJson.setMessage("Invalid_User");
/*  7940 */           this.requestJson.setSuccess(true);
/*       */         }
/*  7942 */         else if (accessToken.longValue() < Long.valueOf(boUsers.getAccessTokenTime()).longValue()) {
/*  7943 */           BoModel boModel = this.boModelService.find(userCode, this.modelId);
/*  7944 */           if (boModel == null) {
/*  7945 */             this.requestJson.setData(map);
/*  7946 */             this.requestJson.setMessage("没有找到该情景模式");
/*  7947 */             this.requestJson.setSuccess(true);
/*       */           } else {
/*  7949 */             map.put("modelWeek", boModel.getWeek());
/*  7950 */             map.put("modelTime", boModel.getTime());
/*  7951 */             this.requestJson.setData(map);
/*       */           }
/*       */         } else {
/*  7954 */           this.requestJson.setData(map);
/*  7955 */           this.requestJson.setMessage("超时了");
/*  7956 */           this.requestJson.setSuccess(false);
/*       */         }
/*       */       }
/*       */       else {
/*  7960 */         System.err.println("验证不通过");
/*  7961 */         this.requestJson.setData(map);
/*  7962 */         this.requestJson.setMessage("验证不通过");
/*  7963 */         this.requestJson.setSuccess(false);
/*       */       }
/*       */     }
/*  7966 */     return "success";
/*       */   }
/*       */ 
/*       */   @Action(value="setModelTiming", results={@org.apache.struts2.convention.annotation.Result(type="json", params={"root", "requestJson"})})
/*       */   public String setModelTiming() {
/*  7971 */     this.requestJson = new RequestJson();
/*  7972 */     Map map = new HashMap();
/*  7973 */     HttpServletRequest request = ServletActionContext.getRequest();
/*  7974 */     String header = request.getHeader("timestamp");
/*  7975 */     String header2 = request.getHeader("nonce");
/*  7976 */     String header3 = request.getHeader("sign");
/*  7977 */     String header4 = request.getHeader("access_token");
/*  7978 */     String userCode = request.getHeader("userCode");
/*  7979 */     if (userCode.contains(",")) {
/*  7980 */       String[] userCode2 = userCode.split(",");
/*  7981 */       BoUsers boUsers = this.boUserServicess.findByUserUserCode(userCode2[0].trim().toString());
/*  7982 */       BoUsers phone = this.boUserServicess.findByUserPhone(userCode2[1].trim().toString());
/*  7983 */       Boolean ral = isRal(header, header2, header3, header4, userCode, "修改主机昵称");
/*  7984 */       if (ral.booleanValue()) {
/*  7985 */         System.err.println("验证通过");
/*  7986 */         Long accessToken = Long.valueOf(header);
/*       */ 
/*  7988 */         if ((phone == null) || (boUsers == null)) {
/*  7989 */           this.requestJson.setData(map);
/*  7990 */           this.requestJson.setMessage("Invalid_User");
/*  7991 */           this.requestJson.setSuccess(true);
/*       */         }
/*  7993 */         else if (header4.equals(phone.getAccessToken())) {
/*  7994 */           if (accessToken.longValue() < Long.valueOf(phone.getAccessTokenTime()).longValue()) {
/*  7995 */             BoModel boModel = this.boModelService.find(userCode2[0].trim().toString(), this.modelId);
/*  7996 */             if (boModel == null) {
/*  7997 */               this.requestJson.setData(map);
/*  7998 */               this.requestJson.setMessage("没有找到该情景模式");
/*  7999 */               this.requestJson.setSuccess(true);
/*       */             } else {
/*  8001 */               boModel.setWeek(this.modelWeek);
/*  8002 */               boModel.setTime(this.modelTime);
/*  8003 */               BoModel update = (BoModel)this.boModelService.update(boModel);
/*  8004 */               if (update != null) {
/*  8005 */                 this.requestJson.setData(map);
/*  8006 */                 this.requestJson.setMessage("设置成功");
/*  8007 */                 this.requestJson.setSuccess(true);
/*       */               } else {
/*  8009 */                 this.requestJson.setData(map);
/*  8010 */                 this.requestJson.setMessage("设置失败");
/*  8011 */                 this.requestJson.setSuccess(true);
/*       */               }
/*       */             }
/*       */           } else {
/*  8015 */             System.err.println("AToken时间戳超时了");
/*  8016 */             this.requestJson.setData(map);
/*  8017 */             this.requestJson.setMessage("超时了");
/*  8018 */             this.requestJson.setSuccess(false);
/*       */           }
/*       */         } else {
/*  8021 */           System.err.println("AToken超时了");
/*  8022 */           this.requestJson.setData(map);
/*  8023 */           this.requestJson.setMessage("超时了");
/*  8024 */           this.requestJson.setSuccess(false);
/*       */         }
/*       */       }
/*       */       else {
/*  8028 */         System.err.println("验证不通过");
/*  8029 */         this.requestJson.setData(map);
/*  8030 */         this.requestJson.setMessage("验证不通过");
/*  8031 */         this.requestJson.setSuccess(false);
/*       */       }
/*       */     } else {
/*  8034 */       Boolean ral = isRal(header, header2, header3, header4, userCode, "修改主机昵称");
/*  8035 */       if (ral.booleanValue()) {
/*  8036 */         System.err.println("验证通过");
/*  8037 */         Long accessToken = Long.valueOf(header);
/*  8038 */         BoUsers boUsers = this.boUserServicess.findByUserUserCode(userCode);
/*  8039 */         if (boUsers == null) {
/*  8040 */           this.requestJson.setData(map);
/*  8041 */           this.requestJson.setMessage("Invalid_User");
/*  8042 */           this.requestJson.setSuccess(true);
/*       */         }
/*  8044 */         else if (accessToken.longValue() < Long.valueOf(boUsers.getAccessTokenTime()).longValue()) {
/*  8045 */           BoModel boModel = this.boModelService.find(userCode, this.modelId);
/*  8046 */           if (boModel == null) {
/*  8047 */             this.requestJson.setData(map);
/*  8048 */             this.requestJson.setMessage("没有找到该情景模式");
/*  8049 */             this.requestJson.setSuccess(true);
/*       */           } else {
/*  8051 */             boModel.setWeek(this.modelWeek);
/*  8052 */             boModel.setTime(this.modelTime);
/*  8053 */             BoModel update = (BoModel)this.boModelService.update(boModel);
/*  8054 */             if (update != null) {
/*  8055 */               this.requestJson.setData(map);
/*  8056 */               this.requestJson.setMessage("设置成功");
/*  8057 */               this.requestJson.setSuccess(true);
/*       */             } else {
/*  8059 */               this.requestJson.setData(map);
/*  8060 */               this.requestJson.setMessage("设置失败");
/*  8061 */               this.requestJson.setSuccess(true);
/*       */             }
/*       */           }
/*       */         }
/*       */         else {
/*  8066 */           this.requestJson.setData(map);
/*  8067 */           this.requestJson.setMessage("超时了");
/*  8068 */           this.requestJson.setSuccess(false);
/*       */         }
/*       */       }
/*       */       else {
/*  8072 */         System.err.println("验证不通过");
/*  8073 */         this.requestJson.setData(map);
/*  8074 */         this.requestJson.setMessage("验证不通过");
/*  8075 */         this.requestJson.setSuccess(false);
/*       */       }
/*       */     }
/*  8078 */     return "success";
/*       */   }
/*       */ 
/*       */   @Action(value="deletemodel", results={@org.apache.struts2.convention.annotation.Result(type="json", params={"root", "requestJson"})})
/*       */   public String deleteModel()
/*       */   {
/*  8089 */     this.requestJson = new RequestJson();
/*  8090 */     Map map = new HashMap();
/*  8091 */     HttpServletRequest request = ServletActionContext.getRequest();
/*       */ 
/*  8093 */     String header = request.getHeader("timestamp");
/*  8094 */     String header2 = request.getHeader("nonce");
/*  8095 */     String header3 = request.getHeader("sign");
/*  8096 */     String header4 = request.getHeader("access_token");
/*  8097 */     String userCode = request.getHeader("userCode");
/*       */     List<BoModelInfo> list;
/*       */     List<BoSensor> list2;
/*  8098 */     if (userCode.contains(",")) {
/*  8099 */       String[] userCode2 = userCode.split(",");
/*  8100 */       BoUsers boUsers = this.boUserServicess.findByUserUserCode(userCode2[0].trim().toString());
/*  8101 */       BoUsers phone = this.boUserServicess.findByUserPhone(userCode2[1].trim().toString());
/*  8102 */       Boolean ral = isRal(header, header2, header3, header4, userCode, "删除情景模式");
/*  8103 */       if (ral.booleanValue()) {
/*  8104 */         System.err.println("验证通过");
/*  8105 */         Long accessToken = Long.valueOf(header);
/*  8106 */         if ((phone == null) || (boUsers == null)) {
/*  8107 */           this.requestJson.setData(map);
/*  8108 */           this.requestJson.setMessage("Invalid_User");
/*  8109 */           this.requestJson.setSuccess(true);
/*       */         }
/*  8111 */         else if (header4.equals(phone.getAccessToken())) {
/*  8112 */           if (accessToken.longValue() < Long.valueOf(phone.getAccessTokenTime()).longValue()) {
/*  8113 */             BoModel boModel = this.boModelService.find(userCode2[0].trim().toString(), this.modelId);
/*  8114 */             if (boModel == null) {
/*  8115 */               this.requestJson.setData(map);
/*  8116 */               this.requestJson.setMessage("没有找到该情景模式");
/*  8117 */               this.requestJson.setSuccess(true);
/*       */             } else {
/*  8119 */               list = this.boModelInfoServicess.getBy(userCode2[0].trim().toString(), this.modelId);
/*  8120 */               for (BoModelInfo boModelInfo : list) {
/*  8121 */                 this.boModelInfoServicess.delete(boModelInfo);
/*       */               }
/*  8123 */               list2 = this.boSensorService.getByModelId(this.modelId);
/*  8124 */               for (BoSensor boSensor : list2) {
/*  8125 */                 boSensor.setBoModel(null);
/*  8126 */                 this.boSensorService.update(boSensor);
/*       */               }
/*  8128 */               BoModel delete = this.boModelService.delete(boModel);
/*  8129 */               if (delete != null) {
/*  8130 */                 this.requestJson.setData(map);
/*  8131 */                 this.requestJson.setMessage("删除成功");
/*  8132 */                 this.requestJson.setSuccess(true);
/*       */               } else {
/*  8134 */                 this.requestJson.setData(map);
/*  8135 */                 this.requestJson.setMessage("删除失败");
/*  8136 */                 this.requestJson.setSuccess(true);
/*       */               }
/*       */             }
/*       */           } else {
/*  8140 */             System.err.println("AToken时间戳超时了");
/*  8141 */             this.requestJson.setData(map);
/*  8142 */             this.requestJson.setMessage("超时了");
/*  8143 */             this.requestJson.setSuccess(false);
/*       */           }
/*       */         } else {
/*  8146 */           System.err.println("AToken超时了");
/*  8147 */           this.requestJson.setData(map);
/*  8148 */           this.requestJson.setMessage("超时了");
/*  8149 */           this.requestJson.setSuccess(false);
/*       */         }
/*       */       }
/*       */       else {
/*  8153 */         System.err.println("验证不通过");
/*  8154 */         this.requestJson.setData(map);
/*  8155 */         this.requestJson.setMessage("验证不通过");
/*  8156 */         this.requestJson.setSuccess(false);
/*       */       }
/*       */     } else {
/*  8159 */       Boolean ral = isRal(header, header2, header3, header4, userCode, "修改主机昵称");
/*  8160 */       if (ral.booleanValue()) {
/*  8161 */         System.err.println("验证通过");
/*  8162 */         Long accessToken = Long.valueOf(header);
/*  8163 */         BoUsers boUsers = this.boUserServicess.findByUserUserCode(userCode);
/*  8164 */         if (accessToken.longValue() < Long.valueOf(boUsers.getAccessTokenTime()).longValue()) {
/*  8165 */           if (boUsers == null) {
/*  8166 */             this.requestJson.setData(map);
/*  8167 */             this.requestJson.setMessage("Invalid_User");
/*  8168 */             this.requestJson.setSuccess(true);
/*       */           }
/*       */           else {
/*  8171 */             BoModel boModel = this.boModelService.find(userCode, this.modelId);
/*  8172 */             if (boModel == null) {
/*  8173 */               this.requestJson.setData(map);
/*  8174 */               this.requestJson.setMessage("没有找到该情景模式");
/*  8175 */               this.requestJson.setSuccess(true);
/*       */             } else {
/*  8177 */               list = this.boModelInfoServicess.getBy(userCode, this.modelId);
/*  8178 */               for (BoModelInfo boModelInfo : list) {
/*  8179 */                 this.boModelInfoServicess.delete(boModelInfo);
/*       */               }
/*  8181 */               list2 = this.boSensorService.getByModelId(this.modelId);
/*  8182 */               for (BoSensor boSensor : list2) {
/*  8183 */                 boSensor.setBoModel(null);
/*  8184 */                 this.boSensorService.update(boSensor);
/*       */               }
/*  8186 */               BoModel delete = this.boModelService.delete(boModel);
/*  8187 */               if (delete != null) {
/*  8188 */                 this.requestJson.setData(map);
/*  8189 */                 this.requestJson.setMessage("删除成功");
/*  8190 */                 this.requestJson.setSuccess(true);
/*       */               } else {
/*  8192 */                 this.requestJson.setData(map);
/*  8193 */                 this.requestJson.setMessage("删除失败");
/*  8194 */                 this.requestJson.setSuccess(true);
/*       */               }
/*       */             }
/*       */           }
/*       */         } else {
/*  8199 */           this.requestJson.setData(map);
/*  8200 */           this.requestJson.setMessage("超时了");
/*  8201 */           this.requestJson.setSuccess(false);
/*       */         }
/*       */       }
/*       */       else {
/*  8205 */         System.err.println("验证不通过");
/*  8206 */         this.requestJson.setData(map);
/*  8207 */         this.requestJson.setMessage("验证不通过");
/*  8208 */         this.requestJson.setSuccess(false);
/*       */       }
/*       */     }
/*  8211 */     return "success";
/*       */   }
/*       */ 
/*       */   @Action(value="addmodel", results={@org.apache.struts2.convention.annotation.Result(type="json", params={"root", "requestJson"})})
/*       */   public String addModel()
/*       */   {
/*  8222 */     this.requestJson = new RequestJson();
/*  8223 */     Map map = new HashMap();
/*  8224 */     HttpServletRequest request = ServletActionContext.getRequest();
/*  8225 */     String header = request.getHeader("timestamp");
/*  8226 */     String header2 = request.getHeader("nonce");
/*  8227 */     String header3 = request.getHeader("sign");
/*  8228 */     String header4 = request.getHeader("access_token");
/*  8229 */     String userCode = request.getHeader("userCode");
/*  8230 */     if (userCode.contains(",")) {
/*  8231 */       String[] userCode2 = userCode.split(",");
/*  8232 */       BoUsers boUsers = this.boUserServicess.findByUserUserCode(userCode2[0].trim().toString());
/*  8233 */       BoUsers phone = this.boUserServicess.findByUserPhone(userCode2[1].trim().toString());
/*  8234 */       Boolean ral = isRal(header, header2, header3, header4, userCode, "添加情景模式");
/*  8235 */       if (ral.booleanValue()) {
/*  8236 */         System.err.println("验证通过");
/*  8237 */         Long accessToken = Long.valueOf(header);
/*  8238 */         if ((phone == null) || (boUsers == null)) {
/*  8239 */           this.requestJson.setData(map);
/*  8240 */           this.requestJson.setMessage("Invalid_User");
/*  8241 */           this.requestJson.setSuccess(true);
/*       */         }
/*  8243 */         else if (header4.equals(phone.getAccessToken())) {
/*  8244 */           if (accessToken.longValue() < Long.valueOf(phone.getAccessTokenTime()).longValue()) {
/*       */             try {
/*  8246 */               BoModel boModel = this.boModelService.find(userCode2[0].trim().toString(), this.modelId);
/*  8247 */               if (boModel == null) {
/*  8248 */                 System.err.println("没有");
/*  8249 */                 BoModel model = this.boModelService.findby(userCode2[0].trim().toString(), this.modelName);
/*  8250 */                 if (model == null) {
/*  8251 */                   BoModel boModels = new BoModel();
/*  8252 */                   boModels.setBoUsers(boUsers);
/*  8253 */                   boModels.setModelId(this.modelId);
/*  8254 */                   boModels.setName(this.modelName);
/*  8255 */                   boModels.setIco(this.ico);
/*  8256 */                   boModels.setFlag(this.fid);
/*  8257 */                   boModels.setWeek("");
/*  8258 */                   boModels.setTime("");
/*  8259 */                   BoModel save = (BoModel)this.boModelService.save(boModels);
/*  8260 */                   if (save != null) {
/*  8261 */                     this.requestJson.setData(map);
/*  8262 */                     this.requestJson.setMessage("添加成功");
/*  8263 */                     this.requestJson.setSuccess(true);
/*       */                   } else {
/*  8265 */                     this.requestJson.setData(map);
/*  8266 */                     this.requestJson.setMessage("添加失败");
/*  8267 */                     this.requestJson.setSuccess(false);
/*       */                   }
/*       */                 } else {
/*  8270 */                   System.err.println("您已使用过该昵称");
/*  8271 */                   this.requestJson.setData(map);
/*  8272 */                   this.requestJson.setMessage("您已使用过该昵称");
/*  8273 */                   this.requestJson.setSuccess(false);
/*       */                 }
/*       */               }
/*       */               else {
/*  8277 */                 System.err.println("有");
/*  8278 */                 boModel.setModelId(this.modelId);
/*  8279 */                 boModel.setIco(this.ico);
/*  8280 */                 boModel.setName(this.modelName);
/*  8281 */                 BoModel update = (BoModel)this.boModelService.update(boModel);
/*  8282 */                 if (update != null) {
/*  8283 */                   this.requestJson.setData(map);
/*  8284 */                   this.requestJson.setMessage("修改成功");
/*  8285 */                   this.requestJson.setSuccess(true);
/*       */                 } else {
/*  8287 */                   this.requestJson.setData(map);
/*  8288 */                   this.requestJson.setMessage("修改失败");
/*  8289 */                   this.requestJson.setSuccess(false);
/*       */                 }
/*       */               }
/*       */             }
/*       */             catch (Exception e) {
/*  8294 */               logger.info("error_" + e.getMessage());
/*  8295 */               this.requestJson.setData(map);
/*  8296 */               this.requestJson.setMessage("服务器发生异常");
/*  8297 */               this.requestJson.setSuccess(false);
/*       */             }
/*       */           } else {
/*  8300 */             System.err.println("AToken时间戳超时了");
/*  8301 */             this.requestJson.setData(map);
/*  8302 */             this.requestJson.setMessage("超时了");
/*  8303 */             this.requestJson.setSuccess(false);
/*       */           }
/*       */         } else {
/*  8306 */           System.err.println("AToken超时了");
/*  8307 */           this.requestJson.setData(map);
/*  8308 */           this.requestJson.setMessage("超时了");
/*  8309 */           this.requestJson.setSuccess(false);
/*       */         }
/*       */ 
/*       */       }
/*       */       else
/*       */       {
/*  8315 */         System.err.println("验证不通过");
/*  8316 */         this.requestJson.setData(map);
/*  8317 */         this.requestJson.setMessage("验证不通过");
/*  8318 */         this.requestJson.setSuccess(false);
/*       */       }
/*       */     } else {
/*  8321 */       Boolean ral = isRal(header, header2, header3, header4, userCode, "添加情景模式");
/*  8322 */       if (ral.booleanValue()) {
/*  8323 */         System.err.println("验证通过");
/*  8324 */         Long accessToken = Long.valueOf(header);
/*  8325 */         BoUsers boUsers = this.boUserServicess.findByUserUserCode(userCode);
/*  8326 */         if (accessToken.longValue() < Long.valueOf(boUsers.getAccessTokenTime()).longValue()) {
/*  8327 */           if (boUsers == null) {
/*  8328 */             this.requestJson.setData(map);
/*  8329 */             this.requestJson.setMessage("Invalid_User");
/*  8330 */             this.requestJson.setSuccess(true);
/*       */           } else {
/*       */             try {
/*  8333 */               BoModel boModel = this.boModelService.find(userCode, this.modelId);
/*  8334 */               if (boModel == null) {
/*  8335 */                 System.err.println("没有");
/*  8336 */                 BoModel model = this.boModelService.findby(userCode, this.modelName);
/*  8337 */                 if (model == null) {
/*  8338 */                   BoModel boModels = new BoModel();
/*  8339 */                   boModels.setBoUsers(boUsers);
/*  8340 */                   boModels.setModelId(this.modelId);
/*  8341 */                   boModels.setName(this.modelName);
/*  8342 */                   boModels.setIco(this.ico);
/*  8343 */                   boModels.setFlag(this.fid);
/*  8344 */                   boModels.setWeek("");
/*  8345 */                   boModels.setTime("");
/*  8346 */                   BoModel save = (BoModel)this.boModelService.save(boModels);
/*  8347 */                   if (save != null) {
/*  8348 */                     this.requestJson.setData(map);
/*  8349 */                     this.requestJson.setMessage("添加成功");
/*  8350 */                     this.requestJson.setSuccess(true);
/*       */                   } else {
/*  8352 */                     this.requestJson.setData(map);
/*  8353 */                     this.requestJson.setMessage("添加失败");
/*  8354 */                     this.requestJson.setSuccess(false);
/*       */                   }
/*       */                 } else {
/*  8357 */                   System.err.println("您已使用过该昵称");
/*  8358 */                   this.requestJson.setData(map);
/*  8359 */                   this.requestJson.setMessage("您已使用过该昵称");
/*  8360 */                   this.requestJson.setSuccess(true);
/*       */                 }
/*       */               }
/*       */               else {
/*  8364 */                 System.err.println("有");
/*  8365 */                 boModel.setModelId(this.modelId);
/*  8366 */                 boModel.setIco(this.ico);
/*  8367 */                 boModel.setName(this.modelName);
/*  8368 */                 BoModel update = (BoModel)this.boModelService.update(boModel);
/*  8369 */                 if (update != null) {
/*  8370 */                   this.requestJson.setData(map);
/*  8371 */                   this.requestJson.setMessage("修改成功");
/*  8372 */                   this.requestJson.setSuccess(true);
/*       */                 } else {
/*  8374 */                   this.requestJson.setData(map);
/*  8375 */                   this.requestJson.setMessage("修改失败");
/*  8376 */                   this.requestJson.setSuccess(false);
/*       */                 }
/*       */               }
/*       */             }
/*       */             catch (Exception e) {
/*  8381 */               logger.info("error_" + e.getMessage());
/*  8382 */               this.requestJson.setData(map);
/*  8383 */               this.requestJson.setMessage("服务器发生异常");
/*  8384 */               this.requestJson.setSuccess(false);
/*       */             }
/*       */           }
/*       */         } else {
/*  8388 */           this.requestJson.setData(map);
/*  8389 */           this.requestJson.setMessage("超时了");
/*  8390 */           this.requestJson.setSuccess(false);
/*       */         }
/*       */       }
/*       */       else {
/*  8394 */         System.err.println("验证不通过");
/*  8395 */         this.requestJson.setData(map);
/*  8396 */         this.requestJson.setMessage("验证不通过");
/*  8397 */         this.requestJson.setSuccess(false);
/*       */       }
/*       */     }
/*  8400 */     return "success";
/*       */   }
/*       */ 
/*       */   @Action(value="studysensor", results={@org.apache.struts2.convention.annotation.Result(type="json", params={"root", "requestJson"})})
/*       */   public String studySensors()
/*       */   {
/*  8414 */     this.requestJson = new RequestJson();
/*  8415 */     HttpServletRequest request = ServletActionContext.getRequest();
/*  8416 */     Map map = new HashMap();
/*  8417 */     String header = request.getHeader("timestamp");
/*  8418 */     String header2 = request.getHeader("nonce");
/*  8419 */     String header3 = request.getHeader("sign");
/*  8420 */     String header4 = request.getHeader("access_token");
/*  8421 */     String userCode = request.getHeader("userCode");
/*  8422 */     if (userCode.contains(",")) {
/*  8423 */       String[] userCode2 = userCode.split(",");
/*  8424 */       BoUsers boUsers = this.boUserServicess.findByUserUserCode(userCode2[0].trim().toString());
/*  8425 */       BoUsers phone = this.boUserServicess.findByUserPhone(userCode2[1].trim().toString());
/*  8426 */       Boolean ral = isRal(header, header2, header3, header4, userCode, "传感器学习");
/*  8427 */       if (ral.booleanValue()) {
/*  8428 */         System.err.println("验证通过");
/*  8429 */         Long accessToken = Long.valueOf(header);
/*  8430 */         if ((phone == null) || (boUsers == null)) {
/*  8431 */           this.requestJson.setData(map);
/*  8432 */           this.requestJson.setMessage("Invalid_User");
/*  8433 */           this.requestJson.setSuccess(true);
/*       */         }
/*  8435 */         else if (header4.equals(phone.getAccessToken())) {
/*  8436 */           if (accessToken.longValue() < Long.valueOf(phone.getAccessTokenTime()).longValue()) {
/*  8437 */             BoSensor boSensor = this.boSensorService.find(this.deviceCode, this.deviceAddress);
/*  8438 */             if (boSensor == null) {
/*  8439 */               this.requestJson.setData(map);
/*  8440 */               this.requestJson.setMessage("没有找到该传感器");
/*  8441 */               this.requestJson.setSuccess(true);
/*       */             } else {
/*  8443 */               String str = "RFSTUY_315M-STUDY-" + boSensor.getDeviceAddress();
/*  8444 */               byte[] bs = str.getBytes();
/*  8445 */               System.err.println(new String(bs));
/*  8446 */               this.packetProcessHelper.processSendDDatas(boSensor.getBoDevice().getDeviceCode(), bs);
/*       */             }
/*       */           }
/*       */           else {
/*  8450 */             System.err.println("AToken时间戳超时了");
/*  8451 */             this.requestJson.setData(map);
/*  8452 */             this.requestJson.setMessage("超时了");
/*  8453 */             this.requestJson.setSuccess(false);
/*       */           }
/*       */         } else {
/*  8456 */           System.err.println("AToken超时了");
/*  8457 */           this.requestJson.setData(map);
/*  8458 */           this.requestJson.setMessage("超时了");
/*  8459 */           this.requestJson.setSuccess(false);
/*       */         }
/*       */ 
/*       */       }
/*       */       else
/*       */       {
/*  8465 */         System.err.println("验证不通过");
/*  8466 */         this.requestJson.setData(map);
/*  8467 */         this.requestJson.setMessage("验证不通过");
/*  8468 */         this.requestJson.setSuccess(false);
/*       */       }
/*       */     } else {
/*  8471 */       Boolean ral = isRal(header, header2, header3, header4, userCode, "修改主机昵称");
/*  8472 */       if (ral.booleanValue()) {
/*  8473 */         System.err.println("验证通过");
/*  8474 */         Long accessToken = Long.valueOf(header);
/*  8475 */         BoUsers boUsers = this.boUserServicess.findByUserUserCode(userCode);
/*  8476 */         if (accessToken.longValue() < Long.valueOf(boUsers.getAccessTokenTime()).longValue()) {
/*  8477 */           if (boUsers == null) {
/*  8478 */             this.requestJson.setData(map);
/*  8479 */             this.requestJson.setMessage("Invalid_User");
/*  8480 */             this.requestJson.setSuccess(true);
/*       */           } else {
/*  8482 */             BoSensor boSensor = this.boSensorService.find(this.deviceCode, this.deviceAddress);
/*  8483 */             if (boSensor == null) {
/*  8484 */               this.requestJson.setData(map);
/*  8485 */               this.requestJson.setMessage("没有找到该传感器");
/*  8486 */               this.requestJson.setSuccess(true);
/*       */             } else {
/*  8488 */               String str = "RFSTUY_315M-STUDY-" + boSensor.getDeviceAddress();
/*  8489 */               byte[] bs = str.getBytes();
/*  8490 */               System.err.println(new String(bs));
/*  8491 */               this.packetProcessHelper.processSendDDatas(boSensor.getBoDevice().getDeviceCode(), bs);
/*  8492 */               this.packetProcessHelper.setUserCode(userCode);
/*       */             }
/*       */           }
/*       */         }
/*       */         else {
/*  8497 */           this.requestJson.setData(map);
/*  8498 */           this.requestJson.setMessage("超时了");
/*  8499 */           this.requestJson.setSuccess(false);
/*       */         }
/*       */       }
/*       */       else {
/*  8503 */         System.err.println("验证不通过");
/*  8504 */         this.requestJson.setData(map);
/*  8505 */         this.requestJson.setMessage("验证不通过");
/*  8506 */         this.requestJson.setSuccess(false);
/*       */       }
/*       */     }
/*  8509 */     return "success";
/*       */   }
/*       */ 
/*       */   @Action(value="studyandcommand", results={@org.apache.struts2.convention.annotation.Result(type="json", params={"root", "requestJson"})})
/*       */   public String studyAndCommand()
/*       */   {
/*  8523 */     this.requestJson = new RequestJson();
/*  8524 */     Map is = new HashMap();
/*  8525 */     HttpServletRequest request = ServletActionContext.getRequest();
/*       */ 
/*  8527 */     String header = request.getHeader("timestamp");
/*  8528 */     String header2 = request.getHeader("nonce");
/*  8529 */     String header3 = request.getHeader("sign");
/*  8530 */     String header4 = request.getHeader("access_token");
/*  8531 */     String userCode = request.getHeader("userCode");
/*  8532 */     System.err.println(this.deviceAddress);
/*  8533 */     System.err.println(this.isStudy);
/*  8534 */     System.err.println(this.infraredButtonsValuess);
/*  8535 */     System.err.println(userCode);
/*  8536 */     if (userCode.contains(",")) {
/*  8537 */       String[] userCode2 = userCode.split(",");
/*  8538 */       BoUsers boUsers = this.boUserServicess.findByUserUserCode(userCode2[0].trim().toString());
/*  8539 */       BoUsers phone = this.boUserServicess.findByUserPhone(userCode2[1].trim().toString());
/*  8540 */       Boolean ral = isRal(header, header2, header3, header4, userCode, "修改主机昵称");
/*  8541 */       packNum(userCode2[0].trim().toString());
/*  8542 */       if (ral.booleanValue()) {
/*  8543 */         System.err.println("验证通过");
/*  8544 */         Long accessToken = Long.valueOf(header);
/*       */ 
/*  8546 */         if ((phone == null) || (boUsers == null)) {
/*  8547 */           this.requestJson.setData(is);
/*  8548 */           this.requestJson.setMessage("Invalid_User");
/*  8549 */           this.requestJson.setSuccess(true);
/*       */         }
/*  8551 */         else if (header4.equals(phone.getAccessToken())) {
/*  8552 */           if (accessToken.longValue() < Long.valueOf(phone.getAccessTokenTime()).longValue()) {
/*  8553 */             BoHostDevice device = this.boHostDeviceService.findBydeviceAddress(userCode2[0].trim().toString(), this.deviceAddress);
/*  8554 */             if (device == null) {
/*  8555 */               this.requestJson.setData(is);
/*  8556 */               this.requestJson.setMessage("没有找到红外设备");
/*  8557 */               this.requestJson.setSuccess(true);
/*       */             }
/*  8559 */             else if (device.getDeviceType().equals("99")) {
/*  8560 */               int countin = 0;
/*  8561 */               System.err.println("可热一");
/*  8562 */               System.err.println("infraredButtonsValuess " + this.infraredButtonsValuess);
/*  8563 */               if (this.isStudy.intValue() == 0) {
/*  8564 */                 List list = this.boInfraredLearnControlMapService.getListBy(userCode2[0].trim().toString(), device.getBoDevice().getDeviceCode(), this.deviceAddress);
/*  8565 */                 BoInfraredLearnControlMap controlMapss = this.boInfraredLearnControlMapService.findBy(userCode2[0].trim().toString(), device.getBoDevice().getDeviceCode(), this.deviceAddress, this.infraredButtonsValuess);
/*       */ 
/*  8567 */                 if (controlMapss == null)
/*       */                 {
/*  8569 */                   BoInfraredLearnControlMap boInfraredLearnControlMap = new BoInfraredLearnControlMap();
/*  8570 */                   boInfraredLearnControlMap.setBoUsers(boUsers);
/*  8571 */                   boInfraredLearnControlMap.setBoDevice(device.getBoDevice());
/*  8572 */                   boInfraredLearnControlMap.setDeviceAddress(this.deviceAddress);
/*  8573 */                   boInfraredLearnControlMap.setOriginalValue(this.infraredButtonsValuess);
/*  8574 */                   boInfraredLearnControlMap.setChangeValue((list.size() + 1)+"");
/*  8575 */                   boInfraredLearnControlMap.setMntDelete("N");
/*  8576 */                   this.boInfraredLearnControlMapService.save(boInfraredLearnControlMap);
/*       */                 } else {
/*  8578 */                   controlMapss.setOriginalValue(this.infraredButtonsValuess);
/*  8579 */                   controlMapss.setChangeValue(controlMapss.getChangeValue());
/*  8580 */                   this.boInfraredLearnControlMapService.update(controlMapss);
/*       */                 }
/*       */ 
/*  8583 */                 System.err.println("是学习");
/*  8584 */                 BoInfraredPart find = this.boInfraredPartService.find(this.deviceAddress);
/*       */                 String s;

/*  8587 */                 if (find == null)
/*  8588 */                   s = "0000000000";
/*       */                 else
/*  8590 */                   s = find.getValidationCode();
/*       */                 String ad;

/*  8594 */                 if (device.getDeviceAddress().equals("65535"))
/*  8595 */                   ad = "65535";
/*       */                 else {
/*  8597 */                   ad = device.getDeviceAddress();
/*       */                 }
/*  8599 */                 BoInfraredLearnControlMap controlMap = this.boInfraredLearnControlMapService.findBy(userCode2[0].trim().toString(), this.deviceAddress, this.infraredButtonsValuess);
/*  8600 */                 String str = "ZIGBEE_INFRARED-STUDY-" + user_num.get(userCode2[0].trim().toString()) + "," + ad + "," + 
/*  8601 */                   controlMap.getChangeValue() + "," + s;
/*  8602 */                 byte[] bs = str.getBytes();
/*  8603 */                 System.err.println(new String(bs));
/*  8604 */                 this.packetProcessHelper.processSendDDatas(device.getBoDevice().getDeviceCode(), bs);
/*       */               } else {
/*  8606 */                 System.err.println("是控制");
/*  8607 */                 String[] infraredButtonsValuessSplit = this.infraredButtonsValuess.split(",");
/*  8608 */                 for (int i = 0; i < infraredButtonsValuessSplit.length; i++) {
/*  8609 */                   System.err.println("截取 " + infraredButtonsValuessSplit[i]);
/*       */                 }
/*       */ 
/*  8612 */                 if (infraredButtonsValuessSplit[0].trim().toString().equals("0")) {
/*  8613 */                   BoInfraredPart find = this.boInfraredPartService.find(this.deviceAddress);
/*       */                   String s;

/*  8615 */                   if (find == null)
/*  8616 */                     s = "0000000000";
/*       */                   else
/*  8618 */                     s = find.getValidationCode();
/*       */                   String ad;

/*  8622 */                   if (device.getDeviceAddress().equals("65535"))
/*  8623 */                     ad = "65535";
/*       */                   else {
/*  8625 */                     ad = device.getDeviceAddress();
/*       */                   }
/*       */ 
/*  8628 */                   String str = "ZIGBEE_INFRARED-SEND-" + user_num.get(userCode2[0].trim().toString()) + "," + ad + "," + 
/*  8629 */                     0 + "," + s;
/*  8630 */                   byte[] bs = str.getBytes();
/*  8631 */                   System.err.println(new String(bs));
/*  8632 */                   this.packetProcessHelper.processSendDDatas(device.getBoDevice().getDeviceCode(), bs);
/*       */                 } else {
/*  8634 */                   String Valuess = infraredButtonsValuessSplit[0];
/*  8635 */                   if (infraredButtonsValuessSplit[1].trim().toString().equals("A")) {
/*  8636 */                     BoInfraredLearnControlMap controlMap = this.boInfraredLearnControlMapService.findBy(userCode2[0].trim().toString(), this.deviceAddress, Valuess);
/*  8637 */                     if (controlMap == null) {
/*  8638 */                       this.requestJson.setData(is);
/*  8639 */                       this.requestJson.setMessage("您还没学习过改按键");
/*  8640 */                       this.requestJson.setSuccess(true);
/*  8641 */                       System.err.println("您还没学习过改按键");
/*       */                     } else {
/*  8643 */                       BoInfraredPart find = this.boInfraredPartService.find(this.deviceAddress);
/*       */                       String s;

/*  8645 */                       if (find == null)
/*  8646 */                         s = "0000000000";
/*       */                       else
/*  8648 */                         s = find.getValidationCode();
/*       */                       String ad;

/*  8652 */                       if (device.getDeviceAddress().equals("65535"))
/*  8653 */                         ad = "65535";
/*       */                       else {
/*  8655 */                         ad = device.getDeviceAddress();
/*       */                       }
/*       */ 
/*  8658 */                       String str = "ZIGBEE_INFRARED-SEND-" + user_num.get(userCode2[0].trim().toString()) + "," + ad + "," + 
/*  8659 */                         controlMap.getChangeValue() + "," + s;
/*  8660 */                       byte[] bs = str.getBytes();
/*  8661 */                       System.err.println(new String(bs));
/*  8662 */                       this.packetProcessHelper.processSendDDatas(device.getBoDevice().getDeviceCode(), bs);
/*       */                     }
/*       */                   } else {
/*  8665 */                     BoInfraredLearnControlMap controlMap = this.boInfraredLearnControlMapService.findBy(userCode2[0].trim().toString(), this.deviceAddress, Valuess);
/*  8666 */                     if (controlMap == null) {
/*  8667 */                       this.requestJson.setData(is);
/*  8668 */                       this.requestJson.setMessage("您还没学习过该按键");
/*  8669 */                       this.requestJson.setSuccess(true);
/*  8670 */                       System.err.println("您还没学习过改按键");
/*       */                     } else {
/*  8672 */                       BoInfraredPart find = this.boInfraredPartService.find(this.deviceAddress);
/*       */                       String s;

/*  8674 */                       if (find == null)
/*  8675 */                         s = "0000000000";
/*       */                       else
/*  8677 */                         s = find.getValidationCode();
/*       */                       String ad;

/*  8681 */                       if (device.getDeviceAddress().equals("65535"))
/*  8682 */                         ad = "65535";
/*       */                       else {
/*  8684 */                         ad = device.getDeviceAddress();
/*       */                       }
/*       */ 
/*  8687 */                       String str = "ZIGBEE_INFRARED-SEND-" + user_num.get(userCode2[0].trim().toString()) + "," + ad + "," + 
/*  8688 */                         controlMap.getChangeValue() + "," + s;
/*  8689 */                       byte[] bs = str.getBytes();
/*  8690 */                       System.err.println(new String(bs));
/*  8691 */                       this.packetProcessHelper.processSendDDatas(device.getBoDevice().getDeviceCode(), bs);
/*       */                     }
/*       */                   }
/*       */                 }
/*       */               }
/*       */             }
/*  8697 */             else if (device.getDeviceType().equals("98")) {
/*  8698 */               int countin = 0;
/*  8699 */               System.err.println("可热一");
/*  8700 */               if (this.isStudy.intValue() == 0) {
/*  8701 */                 String[] split = this.deviceAddress.split(",");
/*  8702 */                 BoHostDevice hostDevice = this.boHostDeviceService.findBydeviceAddress(userCode2[0].trim().toString(), this.deviceAddress);
/*  8703 */                 List list = this.boInfraredLearnControlMapService.getListBy(userCode2[0].trim().toString(), hostDevice.getBoDevice().getDeviceCode(), this.deviceAddress);
/*  8704 */                 BoInfraredLearnControlMap controlMapss = this.boInfraredLearnControlMapService.findBy(userCode2[0].trim().toString(), hostDevice.getBoDevice().getDeviceCode(), this.deviceAddress, this.infraredButtonsValuess);
/*  8705 */                 System.err.println("infraredButtonsValuess " + this.infraredButtonsValuess);
/*  8706 */                 if (controlMapss == null)
/*       */                 {
/*  8708 */                   BoInfraredLearnControlMap boInfraredLearnControlMap = new BoInfraredLearnControlMap();
/*  8709 */                   boInfraredLearnControlMap.setBoUsers(boUsers);
/*  8710 */                   boInfraredLearnControlMap.setBoDevice(hostDevice.getBoDevice());
/*  8711 */                   boInfraredLearnControlMap.setDeviceAddress(this.deviceAddress);
/*  8712 */                   boInfraredLearnControlMap.setOriginalValue(this.infraredButtonsValuess);
/*  8713 */                   boInfraredLearnControlMap.setChangeValue((list.size() + 1)+"");
/*  8714 */                   boInfraredLearnControlMap.setMntDelete("N");
/*  8715 */                   this.boInfraredLearnControlMapService.save(boInfraredLearnControlMap);
/*       */                 } else {
/*  8717 */                   controlMapss.setOriginalValue(this.infraredButtonsValuess);
/*  8718 */                   controlMapss.setChangeValue(controlMapss.getChangeValue());
/*  8719 */                   this.boInfraredLearnControlMapService.update(controlMapss);
/*       */                 }
/*       */ 
/*  8722 */                 System.err.println("是学习");
/*  8723 */                 BoInfraredPart find = this.boInfraredPartService.find(this.deviceAddress);
/*       */                 String s;

/*  8726 */                 if (find == null)
/*  8727 */                   s = "0000000000";
/*       */                 else
/*  8729 */                   s = find.getValidationCode();
/*       */                 String ad;

/*  8733 */                 if (split[0].equals("65535"))
/*  8734 */                   ad = "65535";
/*       */                 else {
/*  8736 */                   ad = device.getDeviceAddress();
/*       */                 }
/*  8738 */                 BoInfraredLearnControlMap controlMap = this.boInfraredLearnControlMapService.findBy(userCode2[0].trim().toString(), this.deviceAddress, this.infraredButtonsValuess);
/*  8739 */                 String str = "ZIGBEE_INFRARED-STUDY-" + user_num.get(userCode2[0].trim().toString()) + "," + ad + "," + 
/*  8740 */                   controlMap.getChangeValue() + "," + s;
/*  8741 */                 byte[] bs = str.getBytes();
/*  8742 */                 System.err.println(new String(bs));
/*  8743 */                 this.packetProcessHelper.processSendDDatas(device.getBoDevice().getDeviceCode(), bs);
/*       */               }
/*       */               else {
/*  8746 */                 System.err.println("是控制");
/*  8747 */                 String[] infraredButtonsValuessSplit = this.infraredButtonsValuess.split(",");
/*  8748 */                 for (int i = 0; i < infraredButtonsValuessSplit.length; i++) {
/*  8749 */                   System.err.println("截取 " + infraredButtonsValuessSplit[i]);
/*       */                 }
/*       */ 
/*  8752 */                 if (infraredButtonsValuessSplit[0].trim().toString().equals("0")) {
/*  8753 */                   BoInfraredPart find = this.boInfraredPartService.find(this.deviceAddress);
/*       */                   String s;

/*  8755 */                   if (find == null)
/*  8756 */                     s = "0000000000";
/*       */                   else {
/*  8758 */                     s = find.getValidationCode();
/*       */                   }
/*  8760 */                   String[] split = device.getDeviceAddress().split(",");
/*  8761 */                   String str = "ZIGBEE_INFRARED-SEND-" + user_num.get(userCode2[0].trim().toString()) + "," + split[0] + "," + 
/*  8762 */                     0 + "," + s;
/*  8763 */                   byte[] bs = str.getBytes();
/*  8764 */                   System.err.println(new String(bs));
/*  8765 */                   this.packetProcessHelper.processSendDDatas(device.getBoDevice().getDeviceCode(), bs);
/*       */                 } else {
/*  8767 */                   String Valuess = infraredButtonsValuessSplit[0];
/*  8768 */                   if (infraredButtonsValuessSplit[1].trim().toString().equals("A")) {
/*  8769 */                     BoInfraredLearnControlMap controlMap = this.boInfraredLearnControlMapService.findBy(userCode2[0].trim().toString(), this.deviceAddress, Valuess);
/*  8770 */                     if (controlMap == null) {
/*  8771 */                       this.requestJson.setData(is);
/*  8772 */                       this.requestJson.setMessage("您还没学习过改按键");
/*  8773 */                       this.requestJson.setSuccess(true);
/*  8774 */                       System.err.println("您还没学习过改按键");
/*       */                     } else {
/*  8776 */                       BoInfraredPart find = this.boInfraredPartService.find(this.deviceAddress);
/*       */                       String s;

/*  8778 */                       if (find == null)
/*  8779 */                         s = "0000000000";
/*       */                       else {
/*  8781 */                         s = find.getValidationCode();
/*       */                       }
/*  8783 */                       String[] split = device.getDeviceAddress().split(",");
/*  8784 */                       String str = "ZIGBEE_INFRARED-SEND-" + user_num.get(userCode2[0].trim().toString()) + "," + split[0] + "," + 
/*  8785 */                         controlMap.getChangeValue() + "," + s;
/*  8786 */                       byte[] bs = str.getBytes();
/*  8787 */                       System.err.println(new String(bs));
/*  8788 */                       this.packetProcessHelper.processSendDDatas(device.getBoDevice().getDeviceCode(), bs);
/*       */                     }
/*       */                   } else {
/*  8791 */                     BoInfraredLearnControlMap controlMap = this.boInfraredLearnControlMapService.findBy(userCode2[0].trim().toString(), this.deviceAddress, Valuess);
/*  8792 */                     if (controlMap == null) {
/*  8793 */                       this.requestJson.setData(is);
/*  8794 */                       this.requestJson.setMessage("您还没学习过改按键");
/*  8795 */                       this.requestJson.setSuccess(true);
/*  8796 */                       System.err.println("您还没学习过改按键");
/*       */                     } else {
/*  8798 */                       BoInfraredPart find = this.boInfraredPartService.find(this.deviceAddress);
/*       */                       String s;

/*  8800 */                       if (find == null)
/*  8801 */                         s = "0000000000";
/*       */                       else {
/*  8803 */                         s = find.getValidationCode();
/*       */                       }
/*  8805 */                       String[] split = device.getDeviceAddress().split(",");
/*  8806 */                       String str = "ZIGBEE_INFRARED-SEND-" + user_num.get(userCode2[0].trim().toString()) + "," + split[0] + "," + 
/*  8807 */                         controlMap.getChangeValue() + "," + s;
/*  8808 */                       byte[] bs = str.getBytes();
/*  8809 */                       System.err.println(new String(bs));
/*  8810 */                       this.packetProcessHelper.processSendDDatas(device.getBoDevice().getDeviceCode(), bs);
/*       */                     }
/*       */                   }
/*       */                 }
/*       */               }
/*       */             }
/*       */           }
/*       */           else
/*       */           {
/*  8819 */             System.err.println("AToken时间戳超时了");
/*  8820 */             this.requestJson.setData(is);
/*  8821 */             this.requestJson.setMessage("超时了");
/*  8822 */             this.requestJson.setSuccess(false);
/*       */           }
/*       */         } else {
/*  8825 */           System.err.println("AToken超时了");
/*  8826 */           this.requestJson.setData(is);
/*  8827 */           this.requestJson.setMessage("超时了");
/*  8828 */           this.requestJson.setSuccess(false);
/*       */         }
/*       */       }
/*       */       else {
/*  8832 */         System.err.println("验证不通过");
/*  8833 */         this.requestJson.setData(is);
/*  8834 */         this.requestJson.setMessage("验证不通过");
/*  8835 */         this.requestJson.setSuccess(false);
/*       */       }
/*       */     } else {
/*  8838 */       Boolean ral = isRal(header, header2, header3, header4, userCode, "修改主机昵称");
/*  8839 */       if (user_num.get(userCode) == null)
/*  8840 */         user_num.put(userCode, Integer.valueOf(0));
/*       */       else {
/*  8842 */         user_num.put(userCode, Integer.valueOf(((Integer)user_num.get(userCode)).intValue() == 255 ? 0 : ((Integer)user_num.get(userCode)).intValue() + 1));
/*       */       }
/*  8844 */       if (ral.booleanValue()) {
/*  8845 */         System.err.println("验证通过");
/*  8846 */         Long accessToken = Long.valueOf(header);
/*  8847 */         BoUsers boUsers = this.boUserServicess.findByUserUserCode(userCode);
/*  8848 */         if (boUsers == null) {
/*  8849 */           this.requestJson.setData(is);
/*  8850 */           this.requestJson.setMessage("Invalid_User");
/*  8851 */           this.requestJson.setSuccess(true);
/*       */         }
/*  8853 */         else if (accessToken.longValue() < Long.valueOf(boUsers.getAccessTokenTime()).longValue()) {
/*  8854 */           BoHostDevice device = this.boHostDeviceService.findBydeviceAddress(userCode, this.deviceAddress);
/*  8855 */           if (device == null) {
/*  8856 */             this.requestJson.setData(is);
/*  8857 */             this.requestJson.setMessage("没有找到红外设备");
/*  8858 */             this.requestJson.setSuccess(true);
/*       */           }
/*  8860 */           else if (device.getDeviceType().equals("99")) {
/*  8861 */             int countin = 0;
/*  8862 */             System.err.println("可热一");
/*  8863 */             System.err.println("infraredButtonsValuess " + this.infraredButtonsValuess);
/*  8864 */             if (this.isStudy.intValue() == 0) {
/*  8865 */               List list = this.boInfraredLearnControlMapService.getListBy(userCode, device.getBoDevice().getDeviceCode(), this.deviceAddress);
/*  8866 */               BoInfraredLearnControlMap controlMapss = this.boInfraredLearnControlMapService.findBy(userCode, device.getBoDevice().getDeviceCode(), this.deviceAddress, this.infraredButtonsValuess);
/*       */ 
/*  8868 */               if (controlMapss == null)
/*       */               {
/*  8870 */                 BoInfraredLearnControlMap boInfraredLearnControlMap = new BoInfraredLearnControlMap();
/*  8871 */                 boInfraredLearnControlMap.setBoUsers(boUsers);
/*  8872 */                 boInfraredLearnControlMap.setBoDevice(device.getBoDevice());
/*  8873 */                 boInfraredLearnControlMap.setDeviceAddress(this.deviceAddress);
/*  8874 */                 boInfraredLearnControlMap.setOriginalValue(this.infraredButtonsValuess);
/*  8875 */                 boInfraredLearnControlMap.setChangeValue((list.size() + 1)+"");
/*  8876 */                 boInfraredLearnControlMap.setMntDelete("N");
/*  8877 */                 this.boInfraredLearnControlMapService.save(boInfraredLearnControlMap);
/*       */               } else {
/*  8879 */                 controlMapss.setOriginalValue(this.infraredButtonsValuess);
/*  8880 */                 controlMapss.setChangeValue(controlMapss.getChangeValue());
/*  8881 */                 this.boInfraredLearnControlMapService.update(controlMapss);
/*       */               }
/*       */ 
/*  8884 */               System.err.println("是学习");
/*  8885 */               BoInfraredPart find = this.boInfraredPartService.find(this.deviceAddress);
/*       */               String s;

/*  8888 */               if (find == null)
/*  8889 */                 s = "0000000000";
/*       */               else
/*  8891 */                 s = find.getValidationCode();
/*       */               String ad;

/*  8895 */               if (device.getDeviceAddress().equals("65535"))
/*  8896 */                 ad = "65535";
/*       */               else {
/*  8898 */                 ad = device.getDeviceAddress();
/*       */               }
/*  8900 */               BoInfraredLearnControlMap controlMap = this.boInfraredLearnControlMapService.findBy(userCode, this.deviceAddress, this.infraredButtonsValuess);
/*  8901 */               String str = "ZIGBEE_INFRARED-STUDY-" + user_num.get(userCode) + "," + ad + "," + 
/*  8902 */                 controlMap.getChangeValue() + "," + s;
/*  8903 */               byte[] bs = str.getBytes();
/*  8904 */               System.err.println(new String(bs));
/*  8905 */               this.packetProcessHelper.processSendDDatas(device.getBoDevice().getDeviceCode(), bs);
/*  8906 */               this.packetProcessHelper.setUserCode(userCode);
/*       */             }
/*       */             else {
/*  8909 */               System.err.println("是控制");
/*  8910 */               String[] infraredButtonsValuessSplit = this.infraredButtonsValuess.split(",");
/*  8911 */               for (int i = 0; i < infraredButtonsValuessSplit.length; i++) {
/*  8912 */                 System.err.println("截取 " + infraredButtonsValuessSplit[i]);
/*       */               }
/*       */ 
/*  8915 */               if (infraredButtonsValuessSplit[0].trim().toString().equals("0")) {
/*  8916 */                 BoInfraredPart find = this.boInfraredPartService.find(this.deviceAddress);
/*       */                 String s;

/*  8918 */                 if (find == null)
/*  8919 */                   s = "0000000000";
/*       */                 else
/*  8921 */                   s = find.getValidationCode();
/*       */                 String ad;

/*  8925 */                 if (device.getDeviceAddress().equals("65535"))
/*  8926 */                   ad = "65535";
/*       */                 else {
/*  8928 */                   ad = device.getDeviceAddress();
/*       */                 }
/*       */ 
/*  8931 */                 String str = "ZIGBEE_INFRARED-SEND-" + user_num.get(userCode) + "," + ad + "," + 
/*  8932 */                   0 + "," + s;
/*  8933 */                 byte[] bs = str.getBytes();
/*  8934 */                 System.err.println(new String(bs));
/*  8935 */                 this.packetProcessHelper.processSendDDatas(device.getBoDevice().getDeviceCode(), bs);
/*  8936 */                 this.packetProcessHelper.setUserCode(userCode);
/*       */               } else {
/*  8938 */                 String Valuess = infraredButtonsValuessSplit[0];
/*  8939 */                 if (infraredButtonsValuessSplit[1].trim().toString().equals("A")) {
/*  8940 */                   BoInfraredLearnControlMap controlMap = this.boInfraredLearnControlMapService.findBy(userCode, this.deviceAddress, Valuess);
/*  8941 */                   if (controlMap == null) {
/*  8942 */                     this.requestJson.setData(is);
/*  8943 */                     this.requestJson.setMessage("您还没学习过改按键");
/*  8944 */                     this.requestJson.setSuccess(true);
/*  8945 */                     System.err.println("您还没学习过该按键");
/*       */                   } else {
/*  8947 */                     BoInfraredPart find = this.boInfraredPartService.find(this.deviceAddress);
/*       */                     String s;

/*  8949 */                     if (find == null)
/*  8950 */                       s = "0000000000";
/*       */                     else
/*  8952 */                       s = find.getValidationCode();
/*       */                     String ad;

/*  8956 */                     if (device.getDeviceAddress().equals("65535"))
/*  8957 */                       ad = "65535";
/*       */                     else {
/*  8959 */                       ad = device.getDeviceAddress();
/*       */                     }
/*       */ 
/*  8962 */                     String str = "ZIGBEE_INFRARED-SEND-" + user_num.get(userCode) + "," + ad + "," + 
/*  8963 */                       controlMap.getChangeValue() + "," + s;
/*  8964 */                     byte[] bs = str.getBytes();
/*  8965 */                     System.err.println(new String(bs));
/*  8966 */                     this.packetProcessHelper.processSendDDatas(device.getBoDevice().getDeviceCode(), bs);
/*  8967 */                     this.packetProcessHelper.setUserCode(userCode);
/*       */                   }
/*       */                 } else {
/*  8970 */                   BoInfraredLearnControlMap controlMap = this.boInfraredLearnControlMapService.findBy(userCode, this.deviceAddress, Valuess);
/*  8971 */                   if (controlMap == null) {
/*  8972 */                     this.requestJson.setData(is);
/*  8973 */                     this.requestJson.setMessage("您还没学习过该按键");
/*  8974 */                     this.requestJson.setSuccess(true);
/*  8975 */                     System.err.println("您还没学习过该按键");
/*       */                   } else {
/*  8977 */                     BoInfraredPart find = this.boInfraredPartService.find(this.deviceAddress);
/*       */                     String s;

/*  8979 */                     if (find == null)
/*  8980 */                       s = "0000000000";
/*       */                     else
/*  8982 */                       s = find.getValidationCode();
/*       */                     String ad;

/*  8986 */                     if (device.getDeviceAddress().equals("65535"))
/*  8987 */                       ad = "65535";
/*       */                     else {
/*  8989 */                       ad = device.getDeviceAddress();
/*       */                     }
/*       */ 
/*  8992 */                     String str = "ZIGBEE_INFRARED-SEND-" + user_num.get(userCode) + "," + ad + "," + 
/*  8993 */                       controlMap.getChangeValue() + "," + s;
/*  8994 */                     byte[] bs = str.getBytes();
/*  8995 */                     System.err.println(new String(bs));
/*  8996 */                     this.packetProcessHelper.processSendDDatas(device.getBoDevice().getDeviceCode(), bs);
/*  8997 */                     this.packetProcessHelper.setUserCode(userCode);
/*       */                   }
/*       */                 }
/*       */               }
/*       */             }
/*       */           }
/*  9003 */           else if (device.getDeviceType().equals("98")) {
/*  9004 */             int countin = 0;
/*  9005 */             System.err.println("可热一");
/*  9006 */             if (this.isStudy.intValue() == 0) {
/*  9007 */               String[] split = this.deviceAddress.split(",");
/*  9008 */               BoHostDevice hostDevice = this.boHostDeviceService.findBydeviceAddress(userCode, this.deviceAddress);
/*  9009 */               List list = this.boInfraredLearnControlMapService.getListBy(userCode, hostDevice.getBoDevice().getDeviceCode(), this.deviceAddress);
/*  9010 */               BoInfraredLearnControlMap controlMapss = this.boInfraredLearnControlMapService.findBy(userCode, hostDevice.getBoDevice().getDeviceCode(), this.deviceAddress, this.infraredButtonsValuess);
/*  9011 */               System.err.println("infraredButtonsValuess " + this.infraredButtonsValuess);
/*  9012 */               if (controlMapss == null)
/*       */               {
/*  9014 */                 BoInfraredLearnControlMap boInfraredLearnControlMap = new BoInfraredLearnControlMap();
/*  9015 */                 boInfraredLearnControlMap.setBoUsers(boUsers);
/*  9016 */                 boInfraredLearnControlMap.setBoDevice(hostDevice.getBoDevice());
/*  9017 */                 boInfraredLearnControlMap.setDeviceAddress(this.deviceAddress);
/*  9018 */                 boInfraredLearnControlMap.setOriginalValue(this.infraredButtonsValuess);
/*  9019 */                 boInfraredLearnControlMap.setChangeValue((list.size() + 1)+"");
/*  9020 */                 boInfraredLearnControlMap.setMntDelete("N");
/*  9021 */                 this.boInfraredLearnControlMapService.save(boInfraredLearnControlMap);
/*       */               } else {
/*  9023 */                 controlMapss.setOriginalValue(this.infraredButtonsValuess);
/*  9024 */                 controlMapss.setChangeValue(controlMapss.getChangeValue());
/*  9025 */                 this.boInfraredLearnControlMapService.update(controlMapss);
/*       */               }
/*       */ 
/*  9028 */               System.err.println("是学习");
/*  9029 */               BoInfraredPart find = this.boInfraredPartService.find(this.deviceAddress);
/*       */               String s;

/*  9032 */               if (find == null)
/*  9033 */                 s = "0000000000";
/*       */               else
/*  9035 */                 s = find.getValidationCode();
/*       */               String ad;

/*  9039 */               if (split[0].equals("65535"))
/*  9040 */                 ad = "65535";
/*       */               else {
/*  9042 */                 ad = device.getDeviceAddress();
/*       */               }
/*  9044 */               BoInfraredLearnControlMap controlMap = this.boInfraredLearnControlMapService.findBy(userCode, this.deviceAddress, this.infraredButtonsValuess);
/*  9045 */               String str = "ZIGBEE_INFRARED-STUDY-" + user_num.get(userCode) + "," + ad + "," + 
/*  9046 */                 controlMap.getChangeValue() + "," + s;
/*  9047 */               byte[] bs = str.getBytes();
/*  9048 */               System.err.println(new String(bs));
/*  9049 */               this.packetProcessHelper.processSendDDatas(device.getBoDevice().getDeviceCode(), bs);
/*  9050 */               this.packetProcessHelper.setUserCode(userCode);
/*       */             }
/*       */             else {
/*  9053 */               System.err.println("是控制");
/*  9054 */               String[] infraredButtonsValuessSplit = this.infraredButtonsValuess.split(",");
/*  9055 */               for (int i = 0; i < infraredButtonsValuessSplit.length; i++) {
/*  9056 */                 System.err.println("截取 " + infraredButtonsValuessSplit[i]);
/*       */               }
/*       */ 
/*  9059 */               if (infraredButtonsValuessSplit[0].trim().toString().equals("0")) {
/*  9060 */                 BoInfraredPart find = this.boInfraredPartService.find(this.deviceAddress);
/*       */                 String s;

/*  9062 */                 if (find == null)
/*  9063 */                   s = "0000000000";
/*       */                 else {
/*  9065 */                   s = find.getValidationCode();
/*       */                 }
/*  9067 */                 String[] split = device.getDeviceAddress().split(",");
/*  9068 */                 String str = "ZIGBEE_INFRARED-SEND-" + user_num.get(userCode) + "," + split[0] + "," + 
/*  9069 */                   0 + "," + s;
/*  9070 */                 byte[] bs = str.getBytes();
/*  9071 */                 System.err.println(new String(bs));
/*  9072 */                 this.packetProcessHelper.processSendDDatas(device.getBoDevice().getDeviceCode(), bs);
/*  9073 */                 this.packetProcessHelper.setUserCode(userCode);
/*       */               } else {
/*  9075 */                 String Valuess = infraredButtonsValuessSplit[0];
/*  9076 */                 if (infraredButtonsValuessSplit[1].trim().toString().equals("A")) {
/*  9077 */                   BoInfraredLearnControlMap controlMap = this.boInfraredLearnControlMapService.findBy(userCode, this.deviceAddress, Valuess);
/*  9078 */                   if (controlMap == null) {
/*  9079 */                     this.requestJson.setData(is);
/*  9080 */                     this.requestJson.setMessage("您还没学习过该按键");
/*  9081 */                     this.requestJson.setSuccess(true);
/*  9082 */                     System.err.println("您还没学习过该按键");
/*       */                   } else {
/*  9084 */                     BoInfraredPart find = this.boInfraredPartService.find(this.deviceAddress);
/*       */                     String s;

/*  9086 */                     if (find == null)
/*  9087 */                       s = "0000000000";
/*       */                     else {
/*  9089 */                       s = find.getValidationCode();
/*       */                     }
/*  9091 */                     String[] split = device.getDeviceAddress().split(",");
/*  9092 */                     String str = "ZIGBEE_INFRARED-SEND-" + user_num.get(userCode) + "," + split[0] + "," + 
/*  9093 */                       controlMap.getChangeValue() + "," + s;
/*  9094 */                     byte[] bs = str.getBytes();
/*  9095 */                     System.err.println(new String(bs));
/*  9096 */                     this.packetProcessHelper.processSendDDatas(device.getBoDevice().getDeviceCode(), bs);
/*  9097 */                     this.packetProcessHelper.setUserCode(userCode);
/*       */                   }
/*       */                 } else {
/*  9100 */                   BoInfraredLearnControlMap controlMap = this.boInfraredLearnControlMapService.findBy(userCode, this.deviceAddress, Valuess);
/*  9101 */                   if (controlMap == null) {
/*  9102 */                     this.requestJson.setData(is);
/*  9103 */                     this.requestJson.setMessage("您还没学习过该按键");
/*  9104 */                     this.requestJson.setSuccess(true);
/*  9105 */                     System.err.println("您还没学习过该按键");
/*       */                   } else {
/*  9107 */                     BoInfraredPart find = this.boInfraredPartService.find(this.deviceAddress);
/*       */                     String s;

/*  9109 */                     if (find == null)
/*  9110 */                       s = "0000000000";
/*       */                     else {
/*  9112 */                       s = find.getValidationCode();
/*       */                     }
/*  9114 */                     String[] split = device.getDeviceAddress().split(",");
/*  9115 */                     String str = "ZIGBEE_INFRARED-SEND-" + user_num.get(userCode) + "," + split[0] + "," + 
/*  9116 */                       controlMap.getChangeValue() + "," + s;
/*  9117 */                     byte[] bs = str.getBytes();
/*  9118 */                     System.err.println(new String(bs));
/*  9119 */                     this.packetProcessHelper.processSendDDatas(device.getBoDevice().getDeviceCode(), bs);
/*  9120 */                     this.packetProcessHelper.setUserCode(userCode);
/*       */                   }
/*       */                 }
/*       */               }
/*       */             }
/*       */           }
/*       */         }
/*       */         else
/*       */         {
/*  9129 */           this.requestJson.setData(is);
/*  9130 */           this.requestJson.setMessage("超时了");
/*  9131 */           this.requestJson.setSuccess(false);
/*       */         }
/*       */       }
/*       */       else
/*       */       {
/*  9136 */         System.err.println("验证不通过");
/*  9137 */         this.requestJson.setData(is);
/*  9138 */         this.requestJson.setMessage("验证不通过");
/*  9139 */         this.requestJson.setSuccess(false);
/*       */       }
/*       */     }
/*  9142 */     return "success";
/*       */   }
/*       */ 
/*       */   @Action(value="updatebutten", results={@org.apache.struts2.convention.annotation.Result(type="json", params={"root", "requestJson"})})
/*       */   public String updateinfraredbuttonses()
/*       */   {
/*  9151 */     this.requestJson = new RequestJson();
/*  9152 */     Map map = new HashMap();
/*  9153 */     HttpServletRequest request = ServletActionContext.getRequest();
/*  9154 */     String header = request.getHeader("timestamp");
/*  9155 */     String header2 = request.getHeader("nonce");
/*  9156 */     String header3 = request.getHeader("sign");
/*  9157 */     String header4 = request.getHeader("access_token");
/*  9158 */     String userCode = request.getHeader("userCode");
/*  9159 */     if (userCode.contains(",")) {
/*  9160 */       String[] userCode2 = userCode.split(",");
/*  9161 */       BoUsers boUsers = this.boUserServicess.findByUserUserCode(userCode2[0].trim().toString());
/*  9162 */       BoUsers phone = this.boUserServicess.findByUserPhone(userCode2[1].trim().toString());
/*  9163 */       Boolean ral = isRal(header, header2, header3, header4, userCode, "修改红外按键");
/*  9164 */       if (ral.booleanValue()) {
/*  9165 */         System.err.println("验证通过");
/*  9166 */         Long accessToken = Long.valueOf(header);
/*       */ 
/*  9168 */         if ((phone == null) || (boUsers == null)) {
/*  9169 */           this.requestJson.setData(map);
/*  9170 */           this.requestJson.setMessage("Invalid_User");
/*  9171 */           this.requestJson.setSuccess(true);
/*       */         }
/*  9173 */         else if (header4.equals(phone.getAccessToken())) {
/*  9174 */           if (accessToken.longValue() < Long.valueOf(phone.getAccessTokenTime()).longValue()) {
/*  9175 */             BoInfraredButtons boInfraredButtons = this.boInfraredButtonsService.findBydeviceAddress(userCode2[0].trim().toString(), this.deviceCode, this.deviceAddress, Integer.valueOf(this.infraredButtonsValuess));
/*  9176 */             if (boInfraredButtons == null) {
/*  9177 */               this.requestJson.setData(map);
/*  9178 */               this.requestJson.setMessage("没有找到该按键");
/*  9179 */               this.requestJson.setSuccess(true);
/*       */             } else {
/*  9181 */               boInfraredButtons.setInfraredButtonsName(this.infraredButtonsName);
/*  9182 */               BoInfraredButtons update = (BoInfraredButtons)this.boInfraredButtonsService.update(boInfraredButtons);
/*  9183 */               if (update != null) {
/*  9184 */                 this.requestJson.setData(map);
/*  9185 */                 this.requestJson.setMessage("修改成功");
/*  9186 */                 this.requestJson.setSuccess(true);
/*       */               } else {
/*  9188 */                 this.requestJson.setData(map);
/*  9189 */                 this.requestJson.setMessage("修改失败");
/*  9190 */                 this.requestJson.setSuccess(true);
/*       */               }
/*       */             }
/*       */           } else {
/*  9194 */             System.err.println("AToken时间戳超时了");
/*  9195 */             this.requestJson.setData(map);
/*  9196 */             this.requestJson.setMessage("超时了");
/*  9197 */             this.requestJson.setSuccess(false);
/*       */           }
/*       */         } else {
/*  9200 */           System.err.println("AToken超时了");
/*  9201 */           this.requestJson.setData(map);
/*  9202 */           this.requestJson.setMessage("超时了");
/*  9203 */           this.requestJson.setSuccess(false);
/*       */         }
/*       */       }
/*       */       else {
/*  9207 */         System.err.println("验证不通过");
/*  9208 */         this.requestJson.setData(map);
/*  9209 */         this.requestJson.setMessage("验证不通过");
/*  9210 */         this.requestJson.setSuccess(false);
/*       */       }
/*       */     } else {
/*  9213 */       Boolean ral = isRal(header, header2, header3, header4, userCode, "修改红外按键");
/*  9214 */       if (ral.booleanValue()) {
/*  9215 */         System.err.println("验证通过");
/*  9216 */         Long accessToken = Long.valueOf(header);
/*  9217 */         BoUsers boUsers = this.boUserServicess.findByUserUserCode(userCode);
/*  9218 */         if (boUsers == null) {
/*  9219 */           this.requestJson.setData(map);
/*  9220 */           this.requestJson.setMessage("Invalid_User");
/*  9221 */           this.requestJson.setSuccess(true);
/*       */         }
/*  9223 */         else if (accessToken.longValue() < Long.valueOf(boUsers.getAccessTokenTime()).longValue()) {
/*  9224 */           BoInfraredButtons boInfraredButtons = this.boInfraredButtonsService.findBydeviceAddress(userCode, this.deviceCode, this.deviceAddress, Integer.valueOf(this.infraredButtonsValuess));
/*  9225 */           if (boInfraredButtons == null) {
/*  9226 */             this.requestJson.setData(map);
/*  9227 */             this.requestJson.setMessage("没有找到该按键");
/*  9228 */             this.requestJson.setSuccess(true);
/*       */           } else {
/*  9230 */             boInfraredButtons.setInfraredButtonsName(this.infraredButtonsName);
/*  9231 */             BoInfraredButtons update = (BoInfraredButtons)this.boInfraredButtonsService.update(boInfraredButtons);
/*  9232 */             if (update != null) {
/*  9233 */               this.requestJson.setData(map);
/*  9234 */               this.requestJson.setMessage("修改成功");
/*  9235 */               this.requestJson.setSuccess(true);
/*       */             } else {
/*  9237 */               this.requestJson.setData(map);
/*  9238 */               this.requestJson.setMessage("修改失败");
/*  9239 */               this.requestJson.setSuccess(true);
/*       */             }
/*       */           }
/*       */         } else {
/*  9243 */           this.requestJson.setData(map);
/*  9244 */           this.requestJson.setMessage("超时了");
/*  9245 */           this.requestJson.setSuccess(false);
/*       */         }
/*       */       }
/*       */       else {
/*  9249 */         System.err.println("验证不通过");
/*  9250 */         this.requestJson.setData(map);
/*  9251 */         this.requestJson.setMessage("验证不通过");
/*  9252 */         this.requestJson.setSuccess(false);
/*       */       }
/*       */     }
/*  9255 */     return "success";
/*       */   }
/*       */ 
/*       */   @Action(value="deleteinfraredbuttonses", results={@org.apache.struts2.convention.annotation.Result(type="json", params={"root", "requestJson"})})
/*       */   public String deleteInfraredButtonses()
/*       */   {
/*  9267 */     this.requestJson = new RequestJson();
/*  9268 */     Map map = new HashMap();
/*  9269 */     HttpServletRequest request = ServletActionContext.getRequest();
/*  9270 */     String header = request.getHeader("timestamp");
/*  9271 */     String header2 = request.getHeader("nonce");
/*  9272 */     String header3 = request.getHeader("sign");
/*  9273 */     String header4 = request.getHeader("access_token");
/*  9274 */     String userCode = request.getHeader("userCode");
/*       */     List<BoInfraredButtons> list;
/*  9275 */     if (userCode.contains(",")) {
/*  9276 */       String[] userCode2 = userCode.split(",");
/*  9277 */       BoUsers boUsers = this.boUserServicess.findByUserUserCode(userCode2[0].trim().toString());
/*  9278 */       BoUsers phone = this.boUserServicess.findByUserPhone(userCode2[1].trim().toString());
/*  9279 */       Boolean ral = isRal(header, header2, header3, header4, userCode, "删除红外模板");
/*  9280 */       if (ral.booleanValue())
/*       */       {
/*  9282 */         Long accessToken = Long.valueOf(header);
/*       */ 
/*  9284 */         if ((phone == null) || (boUsers == null)) {
/*  9285 */           this.requestJson.setData(map);
/*  9286 */           this.requestJson.setMessage("Invalid_User");
/*  9287 */           this.requestJson.setSuccess(true);
/*       */         }
/*  9289 */         else if (header4.equals(phone.getAccessToken())) {
/*  9290 */           if (accessToken.longValue() < Long.valueOf(phone.getAccessTokenTime()).longValue()) {
/*  9291 */             list = this.boInfraredButtonsService.getListBy(userCode2[0].trim().toString(), this.deviceCode, this.deviceAddress);
/*  9292 */             for (BoInfraredButtons boInfraredButtons : list) {
/*  9293 */               BoInfraredButtons delete = this.boInfraredButtonsService.delete(boInfraredButtons);
/*  9294 */               if (delete != null) {
/*  9295 */                 this.requestJson.setData(map);
/*  9296 */                 this.requestJson.setMessage("删除成功");
/*  9297 */                 this.requestJson.setSuccess(true);
/*       */               } else {
/*  9299 */                 this.requestJson.setData(map);
/*  9300 */                 this.requestJson.setMessage("删除失败");
/*  9301 */                 this.requestJson.setSuccess(true);
/*       */               }
/*       */             }
/*       */           } else {
/*  9305 */             System.err.println("AToken时间戳超时了");
/*  9306 */             this.requestJson.setData(map);
/*  9307 */             this.requestJson.setMessage("超时了");
/*  9308 */             this.requestJson.setSuccess(false);
/*       */           }
/*       */         } else {
/*  9311 */           System.err.println("AToken超时了");
/*  9312 */           this.requestJson.setData(map);
/*  9313 */           this.requestJson.setMessage("超时了");
/*  9314 */           this.requestJson.setSuccess(false);
/*       */         }
/*       */ 
/*       */       }
/*       */       else
/*       */       {
/*  9321 */         this.requestJson.setData(map);
/*  9322 */         this.requestJson.setMessage("验证不通过");
/*  9323 */         this.requestJson.setSuccess(false);
/*       */       }
/*       */     } else {
/*  9326 */       Boolean ral = isRal(header, header2, header3, header4, userCode, "修改主机昵称");
/*  9327 */       if (ral.booleanValue())
/*       */       {
/*  9329 */         Long accessToken = Long.valueOf(header);
/*  9330 */         BoUsers boUsers = this.boUserServicess.findByUserUserCode(userCode);
/*  9331 */         if (boUsers == null) {
/*  9332 */           this.requestJson.setData(map);
/*  9333 */           this.requestJson.setMessage("Invalid_User");
/*  9334 */           this.requestJson.setSuccess(true);
/*       */         }
/*  9336 */         else if (accessToken.longValue() < Long.valueOf(boUsers.getAccessTokenTime()).longValue()) {
/*  9337 */           list = this.boInfraredButtonsService.getListBy(userCode, this.deviceCode, this.deviceAddress);
/*  9338 */           for (BoInfraredButtons boInfraredButtons : list) {
/*  9339 */             BoInfraredButtons delete = this.boInfraredButtonsService.delete(boInfraredButtons);
/*  9340 */             if (delete != null) {
/*  9341 */               this.requestJson.setData(map);
/*  9342 */               this.requestJson.setMessage("删除成功");
/*  9343 */               this.requestJson.setSuccess(true);
/*       */             } else {
/*  9345 */               this.requestJson.setData(map);
/*  9346 */               this.requestJson.setMessage("删除失败");
/*  9347 */               this.requestJson.setSuccess(true);
/*       */             }
/*       */           }
/*       */         } else {
/*  9351 */           this.requestJson.setData(map);
/*  9352 */           this.requestJson.setMessage("超时了");
/*  9353 */           this.requestJson.setSuccess(false);
/*       */         }
/*       */       }
/*       */       else
/*       */       {
/*  9358 */         this.requestJson.setData(map);
/*  9359 */         this.requestJson.setMessage("验证不通过");
/*  9360 */         this.requestJson.setSuccess(false);
/*       */       }
/*       */     }
/*  9363 */     return "success";
/*       */   }
/*       */ 
/*       */   @Action(value="gaininfraredbuttonses", results={@org.apache.struts2.convention.annotation.Result(type="json", params={"root", "requestJson"})})
/*       */   public String gainInfraredButtonses()
/*       */   {
/*  9371 */     this.requestJson = new RequestJson();
/*  9372 */     Map is = new HashMap();
/*  9373 */     HttpServletRequest request = ServletActionContext.getRequest();
/*  9374 */     String header = request.getHeader("timestamp");
/*  9375 */     String header2 = request.getHeader("nonce");
/*  9376 */     String header3 = request.getHeader("sign");
/*  9377 */     String header4 = request.getHeader("access_token");
/*  9378 */     String userCode = request.getHeader("userCode");
/*       */     List voList;
/*  9379 */     if (userCode.contains(",")) {
/*  9380 */       String[] userCode2 = userCode.split(",");
/*  9381 */       BoUsers boUsers = this.boUserServicess.findByUserUserCode(userCode2[0].trim().toString());
/*  9382 */       BoUsers phone = this.boUserServicess.findByUserPhone(userCode2[1].trim().toString());
/*  9383 */       Boolean ral = isRal(header, header2, header3, header4, userCode, "修改主机昵称");
/*  9384 */       if (ral.booleanValue()) {
/*  9385 */         System.err.println("验证通过");
/*  9386 */         Long accessToken = Long.valueOf(header);
/*  9387 */         if ((phone == null) || (boUsers == null)) {
/*  9388 */           this.requestJson.setData(is);
/*  9389 */           this.requestJson.setMessage("Invalid_User");
/*  9390 */           this.requestJson.setSuccess(true);
/*       */         }
/*  9392 */         else if (header4.equals(phone.getAccessToken())) {
/*  9393 */           if (accessToken.longValue() < Long.valueOf(phone.getAccessTokenTime()).longValue()) {
/*  9394 */             BoHostDevice hostDevice = this.boHostDeviceService.findBydeviceAddress(this.deviceAddress);
/*  9395 */             List<BoInfraredButtons> list = this.boInfraredButtonsService.getListBy(userCode2[0].trim().toString(), 
/*  9396 */               hostDevice.getBoDevice().getDeviceCode(), this.deviceAddress);
/*  9397 */             if (list.size() <= 0) {
/*  9398 */               this.requestJson.setData(is);
/*  9399 */               this.requestJson.setMessage("没有红外按键");
/*  9400 */               this.requestJson.setSuccess(true);
/*       */             } else {
/*  9402 */               voList = new ArrayList();
/*  9403 */               for (BoInfraredButtons boInfraredButtons : list) {
/*  9404 */                 Map map = new HashMap();
/*  9405 */                 map.put("deviceAddress", boInfraredButtons.getDeviceAddress().toString());
/*  9406 */                 map.put("infraredButtonsValues", boInfraredButtons.getInfraredButtonsValuess().toString());
/*  9407 */                 map.put("infraredButtonsName", boInfraredButtons.getInfraredButtonsName().toString());
/*  9408 */                 voList.add(map);
/*       */               }
/*       */ 
/*  9411 */               this.requestJson.setData(voList);
/*       */             }
/*       */           } else {
/*  9414 */             System.err.println("AToken时间戳超时了");
/*  9415 */             this.requestJson.setMessage("超时了");
/*  9416 */             this.requestJson.setSuccess(false);
/*       */           }
/*       */         } else {
/*  9419 */           System.err.println("AToken超时了");
/*  9420 */           this.requestJson.setMessage("超时了");
/*  9421 */           this.requestJson.setSuccess(false);
/*       */         }
/*       */ 
/*       */       }
/*       */       else
/*       */       {
/*  9427 */         System.err.println("验证不通过");
/*  9428 */         this.requestJson.setMessage("验证不通过");
/*  9429 */         this.requestJson.setSuccess(false);
/*       */       }
/*       */     } else {
/*  9432 */       Boolean ral = isRal(header, header2, header3, header4, userCode, "修改主机昵称");
/*  9433 */       if (ral.booleanValue()) {
/*  9434 */         System.err.println("验证通过");
/*  9435 */         Long accessToken = Long.valueOf(header);
/*  9436 */         BoUsers boUsers = this.boUserServicess.findByUserUserCode(userCode);
/*  9437 */         if (accessToken.longValue() < Long.valueOf(boUsers.getAccessTokenTime()).longValue()) {
/*  9438 */           if (boUsers == null) {
/*  9439 */             this.requestJson.setData(is);
/*  9440 */             this.requestJson.setMessage("Invalid_User");
/*  9441 */             this.requestJson.setSuccess(true);
/*       */           } else {
/*  9443 */             BoHostDevice hostDevice = this.boHostDeviceService.findBydeviceAddress(this.deviceAddress);
/*  9444 */             List<BoInfraredButtons> list = this.boInfraredButtonsService.getListBy(userCode, 
/*  9445 */               hostDevice.getBoDevice().getDeviceCode(), this.deviceAddress);
/*  9446 */             if (list.size() <= 0) {
/*  9447 */               this.requestJson.setData(is);
/*  9448 */               this.requestJson.setMessage("没有红外按键");
/*  9449 */               this.requestJson.setSuccess(true);
/*       */             } else {
/*  9451 */               voList = new ArrayList();
/*  9452 */               for (BoInfraredButtons boInfraredButtons : list) {
/*  9453 */                 Map map = new HashMap();
/*  9454 */                 map.put("deviceAddress", boInfraredButtons.getDeviceAddress().toString());
/*  9455 */                 map.put("infraredButtonsValues", boInfraredButtons.getInfraredButtonsValuess().toString());
/*  9456 */                 map.put("infraredButtonsName", boInfraredButtons.getInfraredButtonsName().toString());
/*  9457 */                 voList.add(map);
/*       */               }
/*       */ 
/*  9460 */               this.requestJson.setData(voList);
/*       */             }
/*       */           }
/*       */         } else {
/*  9464 */           this.requestJson.setMessage("超时了");
/*  9465 */           this.requestJson.setSuccess(false);
/*       */         }
/*       */       }
/*       */       else {
/*  9469 */         System.err.println("验证不通过");
/*  9470 */         this.requestJson.setMessage("验证不通过");
/*  9471 */         this.requestJson.setSuccess(false);
/*       */       }
/*       */     }
/*  9474 */     return "success";
/*       */   }
/*       */ 
/*       */   @Action(value="setinfrareddeviceinfo", results={@org.apache.struts2.convention.annotation.Result(type="json", params={"root", "requestJson"})})
/*       */   public String setInfraredDeviceInfo()
/*       */   {
/*  9480 */     this.requestJson = new RequestJson();
/*  9481 */     Map is = new HashMap();
/*  9482 */     HttpServletRequest request = ServletActionContext.getRequest();
/*  9483 */     String str = "";
/*  9484 */     Md5 md5 = new Md5();
/*  9485 */     String header = request.getHeader("timestamp");
/*  9486 */     String header2 = request.getHeader("nonce");
/*  9487 */     String header3 = request.getHeader("sign");
/*  9488 */     String header4 = request.getHeader("access_token");
/*  9489 */     String userCode = request.getHeader("userCode");
/*  9490 */     if (userCode.contains(",")) {
/*  9491 */       String[] userCode2 = userCode.split(",");
/*  9492 */       BoUsers boUsers = this.boUserServicess.findByUserUserCode(userCode2[0].trim().toString());
/*  9493 */       BoUsers phone = this.boUserServicess.findByUserPhone(userCode2[1].trim().toString());
/*  9494 */       Boolean ral = isRal(header, header2, header3, header4, userCode, "修改主机昵称");
/*  9495 */       if (ral.booleanValue()) {
/*  9496 */         System.err.println("验证通过");
/*  9497 */         Long accessToken = Long.valueOf(header);
/*  9498 */         if ((phone == null) || (boUsers == null)) {
/*  9499 */           this.requestJson.setData(is);
/*  9500 */           this.requestJson.setMessage("Invalid_User");
/*  9501 */           this.requestJson.setSuccess(true);
/*       */         }
/*  9503 */         else if (header4.equals(phone.getAccessToken())) {
/*  9504 */           if (accessToken.longValue() < Long.valueOf(phone.getAccessTokenTime()).longValue()) {
/*  9505 */             BoHostDevice hostDevice = this.boHostDeviceService.findBydeviceAddress(this.deviceAddress);
/*  9506 */             BoRoom boRoom = this.boRoomService.findByCode(this.roomCode);
/*  9507 */             if (hostDevice == null) {
/*  9508 */               this.requestJson.setData(is);
/*  9509 */               System.err.println("没有该设备地址");
/*  9510 */               this.requestJson.setMessage("没有该设备地址");
/*  9511 */               this.requestJson.setSuccess(true);
/*       */             }
/*  9513 */             else if (boRoom == null) {
/*  9514 */               this.requestJson.setData(is);
/*  9515 */               System.err.println("没有该房间");
/*  9516 */               this.requestJson.setMessage("没有该房间");
/*  9517 */               this.requestJson.setSuccess(true);
/*       */             }
/*       */             else {
/*  9520 */               hostDevice.setDeviceAddress(this.deviceAddress);
/*  9521 */               hostDevice.setValidationCode(this.validationCode);
/*  9522 */               hostDevice.setNickName(this.nickName);
/*  9523 */               hostDevice.setBoRoom(boRoom);
/*  9524 */               hostDevice.setDeviceClassify(this.fid1);
/*  9525 */               hostDevice.setIco(this.ico);
/*  9526 */               this.boHostDeviceService.update(hostDevice);
/*       */             }
/*       */           }
/*       */           else {
/*  9530 */             System.err.println("AToken时间戳超时了");
/*  9531 */             this.requestJson.setData(is);
/*  9532 */             this.requestJson.setMessage("超时了");
/*  9533 */             this.requestJson.setSuccess(false);
/*       */           }
/*       */         } else {
/*  9536 */           System.err.println("AToken超时了");
/*  9537 */           this.requestJson.setData(is);
/*  9538 */           this.requestJson.setMessage("超时了");
/*  9539 */           this.requestJson.setSuccess(false);
/*       */         }
/*       */ 
/*       */       }
/*       */       else
/*       */       {
/*  9547 */         System.err.println("验证不通过");
/*  9548 */         this.requestJson.setData(is);
/*  9549 */         this.requestJson.setMessage("验证不通过");
/*  9550 */         this.requestJson.setSuccess(false);
/*       */       }
/*       */     } else {
/*  9553 */       Boolean ral = isRal(header, header2, header3, header4, userCode, "修改主机昵称");
/*  9554 */       if (ral.booleanValue()) {
/*  9555 */         System.err.println("验证通过");
/*  9556 */         Long accessToken = Long.valueOf(header);
/*  9557 */         BoUsers boUsers = this.boUserServicess.findByUserUserCode(userCode);
/*  9558 */         if (accessToken.longValue() < Long.valueOf(boUsers.getAccessTokenTime()).longValue()) {
/*  9559 */           if (boUsers == null) {
/*  9560 */             this.requestJson.setData(is);
/*  9561 */             this.requestJson.setMessage("Invalid_User");
/*  9562 */             this.requestJson.setSuccess(true);
/*       */           } else {
/*  9564 */             BoHostDevice hostDevice = this.boHostDeviceService.findBydeviceAddress(this.deviceAddress);
/*  9565 */             BoRoom boRoom = this.boRoomService.findByCode(this.roomCode);
/*  9566 */             if (hostDevice == null) {
/*  9567 */               this.requestJson.setData(is);
/*  9568 */               System.err.println("没有该设备地址");
/*  9569 */               this.requestJson.setMessage("没有该设备地址");
/*  9570 */               this.requestJson.setSuccess(true);
/*       */             }
/*  9572 */             else if (boRoom == null) {
/*  9573 */               this.requestJson.setData(is);
/*  9574 */               System.err.println("没有该房间");
/*  9575 */               this.requestJson.setMessage("没有该房间");
/*  9576 */               this.requestJson.setSuccess(true);
/*       */             }
/*       */             else {
/*  9579 */               hostDevice.setDeviceAddress(this.deviceAddress);
/*  9580 */               hostDevice.setValidationCode(this.validationCode);
/*  9581 */               hostDevice.setNickName(this.nickName);
/*  9582 */               hostDevice.setBoRoom(boRoom);
/*  9583 */               hostDevice.setDeviceClassify(this.fid1);
/*  9584 */               hostDevice.setIco(this.ico);
/*  9585 */               this.boHostDeviceService.update(hostDevice);
/*       */             }
/*       */           }
/*       */         }
/*       */         else {
/*  9590 */           this.requestJson.setData(is);
/*  9591 */           this.requestJson.setMessage("超时了");
/*  9592 */           this.requestJson.setSuccess(false);
/*       */         }
/*       */       }
/*       */       else {
/*  9596 */         System.err.println("验证不通过");
/*  9597 */         this.requestJson.setData(is);
/*  9598 */         this.requestJson.setMessage("验证不通过");
/*  9599 */         this.requestJson.setSuccess(false);
/*       */       }
/*       */     }
/*  9602 */     return "success";
/*       */   }
/*       */ 
/*       */   @Action(value="ss", results={@org.apache.struts2.convention.annotation.Result(type="json", params={"root", "requestJson"})})
/*       */   public String ss()
/*       */     throws AuthenticationException, InvalidRequestException, APIConnectionException, APIException, ChannelException, IOException
/*       */   {
/*  9621 */     HttpServletRequest request = getRequest();
/*  9622 */     HttpServletResponse response = getResponse();
/*       */     try {
/*  9624 */       request.setCharacterEncoding("UTF8");
/*       */     }
/*       */     catch (UnsupportedEncodingException e) {
/*  9627 */       e.printStackTrace();
/*       */     }
/*       */ 
/*  9630 */     Enumeration headerNames = request.getHeaderNames();
/*  9631 */     while (headerNames.hasMoreElements()) {
/*  9632 */       String key = (String)headerNames.nextElement();
/*  9633 */       String value = request.getHeader(key);
/*  9634 */       System.out.println(key + " " + value);
/*       */     }
/*       */ 
/*  9637 */     BufferedReader reader = request.getReader();
/*  9638 */     StringBuffer buffer = new StringBuffer();
/*       */     String string;
/*  9640 */     while ((string = reader.readLine()) != null)
/*       */     {
///*       */       String string;
/*  9641 */       buffer.append(string);
/*       */     }
/*  9643 */     reader.close();
/*       */ 
/*  9645 */     Event event = Webhooks.eventParse(buffer.toString());
/*       */ 
/*  9648 */     if ("charge.succeeded".equals(event.getType()))
/*  9649 */       response.setStatus(200);
/*  9650 */     else if ("refund.succeeded".equals(event.getType()))
/*  9651 */       response.setStatus(200);
/*       */     else {
/*  9653 */       response.setStatus(500);
/*       */     }
/*  9655 */     retrieves(event.getId());
/*  9656 */     return "success";
/*       */   }
/*       */ 
/*       */   public static String changeF2Y(String amount)
/*       */     throws Exception
/*       */   {
/*  9667 */     if (!amount.matches("\\-?[0-9]+")) {
/*  9668 */       throw new Exception("金额格式有误");
/*       */     }
/*  9670 */     return BigDecimal.valueOf(Long.valueOf(amount).longValue()).divide(new BigDecimal(100)).toString();
/*       */   }
/*       */ 
/*       */   public void retrieves(String id)
/*       */   {
/*       */     try
/*       */     {
/*  9683 */       Event event = Event.retrieve(id);
/*       */ 
/*  9685 */       System.err.println(event);
/*       */ 
/*  9687 */       Object obj = Webhooks.parseEvnet(event.toString());
/*  9688 */       if ((obj instanceof Charge))
/*       */       {
/*  9690 */         System.out.println("webhooks 发送了 Charge");
/*  9691 */         if (((Charge)obj).getPaid().booleanValue()) {
/*  9692 */           System.err.println("支付成功");
/*       */ 
/*  9694 */           String string = ((Charge)obj).getDescription().toString();
/*  9695 */           String[] split2 = string.split(",");
/*  9696 */           for (int i = 0; i < split2.length; i++) {
/*  9697 */             List<BoGoods> listbyId = this.boGoodsService.getListbyId(Integer.valueOf(split2[i]));
/*  9698 */             for (BoGoods boGoods : listbyId)
/*       */             {
/*  9700 */               BoOrder boOrder = new BoOrder();
/*       */ 
/*  9702 */               boOrder.setOrderNo(((Charge)obj).getOrderNo());
/*  9703 */               boOrder.setTradeNo(((Charge)obj).getTransactionNo());
/*  9704 */               SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
/*  9705 */               String paymentTime = formatter.format(new Date());
/*  9706 */               boOrder.setPaymentTime(paymentTime);
/*       */ 
/*  9711 */               boOrder.setTradeMoney(boGoods.getGoodsPrice().toString());
/*  9712 */               boOrder.setOrderState(this.fid);
/*  9713 */               String body = ((Charge)obj).getBody().toString();
/*  9714 */               String[] split = body.split(",");
/*  9715 */               BoUsers boUsers = this.boUserServicess.findByUserPhone(split[0]);
/*  9716 */               boOrder.setBoUsers(boUsers);
/*  9717 */               boOrder.setBoGoods(boGoods);
/*  9718 */               boOrder.setRecipentName(split[1]);
/*  9719 */               boOrder.setRecipentPhone(split[2]);
/*  9720 */               boOrder.setRecipentAddress(split[3]);
/*  9721 */               Integer channel = null;
/*  9722 */               if (((Charge)obj).getChannel().trim().equals("alipay")) {
/*  9723 */                 channel = Integer.valueOf(0);
/*       */               }
/*  9725 */               boOrder.setPayChannel(channel);
/*  9726 */               this.boOrderService.save(boOrder);
/*       */             }
/*       */           }
/*       */         }
/*       */         else {
/*  9731 */           System.err.println("支付失败");
/*       */         }
/*  9733 */       } else if ((obj instanceof Refund)) {
/*  9734 */         System.out.println("webhooks 发送了 Refund");
/*  9735 */       } else if ((obj instanceof Summary)) {
/*  9736 */         System.out.println("webhooks 发送了 Summary");
/*       */       }
/*       */     } catch (AuthenticationException e) {
/*  9739 */       e.printStackTrace();
/*       */     } catch (InvalidRequestException e) {
/*  9741 */       e.printStackTrace();
/*       */     } catch (APIConnectionException e) {
/*  9743 */       e.printStackTrace();
/*       */     } catch (APIException e) {
/*  9745 */       e.printStackTrace();
/*       */     }
/*       */     catch (ChannelException e) {
/*  9748 */       e.printStackTrace();
/*       */     } catch (Exception e) {
/*  9750 */       e.printStackTrace();
/*       */     }
/*       */   }
/*       */ 
/*       */   @Action(value="notifypay", results={@org.apache.struts2.convention.annotation.Result(type="json", params={"root", "requestJson"})})
/*       */   public String notifyPay()
/*       */     throws IOException, AuthenticationException, InvalidRequestException, APIConnectionException, APIException, ChannelException
/*       */   {
/*  9770 */     this.requestJson = new RequestJson();
/*  9771 */     Map map = new HashMap();
/*  9772 */     HttpServletRequest request = ServletActionContext.getRequest();
/*  9773 */     String str = "";
/*  9774 */     Md5 md5 = new Md5();
/*  9775 */     String header = request.getHeader("timestamp");
/*  9776 */     String header2 = request.getHeader("nonce");
/*  9777 */     String header3 = request.getHeader("sign");
/*  9778 */     String header4 = request.getHeader("access_token");
/*  9779 */     String userCode = request.getHeader("userCode");
/*  9780 */     if (userCode.contains(",")) {
/*  9781 */       String[] userCode2 = userCode.split(",");
/*  9782 */       BoUsers boUsers = this.boUserServicess.findByUserUserCode(userCode2[0].trim().toString());
/*  9783 */       BoUsers phone = this.boUserServicess.findByUserPhone(userCode2[1].trim().toString());
/*  9784 */       Boolean ral = isRal(header, header2, header3, header4, userCode, "修改主机昵称");
/*  9785 */       if (ral.booleanValue()) {
/*  9786 */         System.err.println("验证通过");
/*  9787 */         Long accessToken = Long.valueOf(header);
/*  9788 */         if ((phone == null) || (boUsers == null)) {
/*  9789 */           this.requestJson.setMessage("Invalid_User");
/*  9790 */           this.requestJson.setSuccess(true);
/*       */         }
/*  9792 */         else if (header4.equals(phone.getAccessToken())) {
/*  9793 */           if (accessToken.longValue() < Long.valueOf(phone.getAccessTokenTime()).longValue())
/*       */           {
/*  9795 */             HttpServletResponse response = getResponse();
/*       */ 
/*  9797 */             response.setHeader("Content-Type", "application/x-www-form-urlencoded; charset=utf-8");
/*       */ 
/*  9802 */             com.pingplusplus.Pingpp.apiKey = apiKey;
/*  9803 */             ChargeExample ce = new ChargeExample();
/*       */ 
/*  9809 */             String order_no = getRequest().getParameter("order_no");
/*       */ 
/*  9812 */             String amount = getRequest().getParameter("amount");
/*       */ 
/*  9815 */             String channel = getRequest().getParameter("channel");
/*       */ 
/*  9821 */             String description = getRequest().getParameter("description");
/*       */ 
/*  9823 */             String goodsid = getRequest().getParameter("goodsid");
/*       */ 
/*  9825 */             Charge charge = null;
/*  9826 */             Map chargeParams = new HashMap();
/*  9827 */             SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
/*  9828 */             String dateString = formatter.format(new Date());
/*  9829 */             chargeParams.put("order_no", dateString);
/*  9830 */             chargeParams.put("amount", amount);
/*  9831 */             Map app = new HashMap();
/*  9832 */             app.put("id", "app_DOGKG8mDG0OSSyrz");
/*  9833 */             chargeParams.put("app", app);
/*  9834 */             chargeParams.put("channel", channel);
/*  9835 */             chargeParams.put("currency", "cny");
/*  9836 */             chargeParams.put("client_ip", "120.77.250.17");
/*  9837 */             String s = " 掌上智家商品付款";
/*  9838 */             s = new String(s.getBytes("UTF-8"));
/*       */ 
/*  9840 */             chargeParams.put("subject", s);
/*  9841 */             chargeParams.put("body", this.userPhone + "," + description);
/*  9842 */             chargeParams.put("description", goodsid);
/*  9843 */             System.err.println(goodsid);
/*  9844 */             Charge.create(chargeParams);
/*       */             try
/*       */             {
/*  9847 */               charge = Charge.create(chargeParams);
/*       */ 
/*  9849 */               map.put("charge", charge);
/*  9850 */               retrieve(charge.getId());
/*  9851 */               this.requestJson.setData(map);
/*  9852 */               this.requestJson.setSuccess(true);
/*       */             }
/*       */             catch (PingppException e) {
/*  9855 */               e.printStackTrace();
/*       */             }
/*       */           } else {
/*  9858 */             System.err.println("AToken时间戳超时了");
/*  9859 */             this.requestJson.setMessage("超时了");
/*  9860 */             this.requestJson.setSuccess(false);
/*       */           }
/*       */         } else {
/*  9863 */           System.err.println("AToken超时了");
/*  9864 */           this.requestJson.setMessage("超时了");
/*  9865 */           this.requestJson.setSuccess(false);
/*       */         }
/*       */ 
/*       */       }
/*       */       else
/*       */       {
/*  9871 */         System.err.println("验证不通过");
/*  9872 */         this.requestJson.setMessage("验证不通过");
/*  9873 */         this.requestJson.setSuccess(false);
/*       */       }
/*       */     } else {
/*  9876 */       Boolean ral = isRal(header, header2, header3, header4, userCode, "修改主机昵称");
/*  9877 */       if (ral.booleanValue()) {
/*  9878 */         System.err.println("验证通过");
/*  9879 */         Long accessToken = Long.valueOf(header);
/*  9880 */         BoUsers boUsers = this.boUserServicess.findByUserUserCode(userCode);
/*  9881 */         if (accessToken.longValue() < Long.valueOf(boUsers.getAccessTokenTime()).longValue()) {
/*  9882 */           if (boUsers == null) {
/*  9883 */             this.requestJson.setMessage("Invalid_User");
/*  9884 */             this.requestJson.setSuccess(true);
/*       */           }
/*       */           else {
/*  9887 */             HttpServletResponse response = getResponse();
/*       */ 
/*  9889 */             response.setHeader("Content-Type", "application/x-www-form-urlencoded; charset=utf-8");
/*       */ 
/*  9893 */             com.pingplusplus.Pingpp.apiKey = apiKey;
/*  9894 */             ChargeExample ce = new ChargeExample();
/*       */ 
/*  9900 */             String order_no = getRequest().getParameter("order_no");
/*       */ 
/*  9903 */             String amount = getRequest().getParameter("amount");
/*       */ 
/*  9906 */             String channel = getRequest().getParameter("channel");
/*       */ 
/*  9912 */             String description = getRequest().getParameter("description");
/*       */ 
/*  9914 */             String goodsid = getRequest().getParameter("goodsid");
/*       */ 
/*  9916 */             Charge charge = null;
/*  9917 */             Map chargeParams = new HashMap();
/*  9918 */             SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
/*  9919 */             String dateString = formatter.format(new Date());
/*  9920 */             chargeParams.put("order_no", dateString);
/*  9921 */             chargeParams.put("amount", amount);
/*  9922 */             Map app = new HashMap();
/*  9923 */             app.put("id", "app_DOGKG8mDG0OSSyrz");
/*  9924 */             chargeParams.put("app", app);
/*  9925 */             chargeParams.put("channel", channel);
/*  9926 */             chargeParams.put("currency", "cny");
/*  9927 */             chargeParams.put("client_ip", "120.77.250.17");
/*  9928 */             String s = " 掌上智家商品付款";
/*  9929 */             s = new String(s.getBytes("UTF-8"));
/*       */ 
/*  9931 */             chargeParams.put("subject", s);
/*  9932 */             chargeParams.put("body", this.userPhone + "," + description);
/*  9933 */             chargeParams.put("description", goodsid);
/*  9934 */             System.err.println(goodsid);
/*  9935 */             Charge.create(chargeParams);
/*       */             try
/*       */             {
/*  9938 */               charge = Charge.create(chargeParams);
/*       */ 
/*  9940 */               map.put("charge", charge);
/*  9941 */               retrieve(charge.getId());
/*  9942 */               this.requestJson.setData(map);
/*  9943 */               this.requestJson.setSuccess(true);
/*       */             }
/*       */             catch (PingppException e) {
/*  9946 */               e.printStackTrace();
/*       */             }
/*       */           }
/*       */         } else {
/*  9950 */           this.requestJson.setMessage("超时了");
/*  9951 */           this.requestJson.setSuccess(false);
/*       */         }
/*       */       }
/*       */       else {
/*  9955 */         System.err.println("验证不通过");
/*  9956 */         this.requestJson.setMessage("验证不通过");
/*  9957 */         this.requestJson.setSuccess(false);
/*       */       }
/*       */     }
/*  9960 */     return "success";
/*       */   }
/*       */ 
/*       */   public void retrieve(String id)
/*       */   {
/*       */     try
/*       */     {
/*  9977 */       Map param = new HashMap();
/*  9978 */       List expande = new ArrayList();
/*  9979 */       expande.add("app");
/*  9980 */       param.put("expand", expande);
/*       */ 
/*  9983 */       Charge charge = Charge.retrieve(id, param);
/*  9984 */       if(charge.getApp() instanceof App);
/*       */     }
/*       */     catch (PingppException e)
/*       */     {
/*  9992 */       e.printStackTrace();
/*       */     } catch (Exception e) {
/*  9994 */       e.printStackTrace();
/*       */     }
/*       */   }
/*       */ 
/*       */   @Action(value="gainuserorderInfo", results={@org.apache.struts2.convention.annotation.Result(type="json", params={"root", "requestJson"})})
/*       */   public String gainUserOrderInfo()
/*       */   {
/* 10007 */     this.requestJson = new RequestJson();
/* 10008 */     HttpServletRequest request = ServletActionContext.getRequest();
/* 10009 */     Map is = new HashMap();
/* 10010 */     String str = "";
/* 10011 */     Md5 md5 = new Md5();
/* 10012 */     String timestamp = request.getHeader("timestamp");
/* 10013 */     String nonce = request.getHeader("nonce");
/* 10014 */     String sign = request.getHeader("sign");
/* 10015 */     String access_token = request.getHeader("access_token");
/* 10016 */     String userCode = request.getHeader("userCode");
/* 10017 */     if (userCode.contains(",")) {
/* 10018 */       String[] userCode2 = userCode.split(",");
/* 10019 */       BoUsers boUsers = this.boUserServicess.findByUserUserCode(userCode2[0].trim().toString());
/* 10020 */       BoUsers phone = this.boUserServicess.findByUserPhone(userCode2[1].trim().toString());
/* 10021 */       Boolean ral = isRal(timestamp, nonce, sign, access_token, userCode, "已购单个商品详情");
/* 10022 */       if (ral.booleanValue()) {
/* 10023 */         System.err.println("验证通过");
/* 10024 */         Long accessToken = Long.valueOf(timestamp);
/*       */ 
/* 10026 */         if ((phone == null) || (boUsers == null)) {
/* 10027 */           this.requestJson.setData(is);
/* 10028 */           this.requestJson.setMessage("Invalid_User");
/* 10029 */           this.requestJson.setSuccess(true);
/*       */         } else {
/*       */           try {
/* 10032 */             if (access_token.equals(phone.getAccessToken())) {
/* 10033 */               if (accessToken.longValue() < Long.valueOf(phone.getAccessTokenTime()).longValue()) {
/* 10034 */                 Map map = new HashMap();
/* 10035 */                 BoOrder boOrder = this.boOrderService.findByOrderId(this.userPhone, this.orderId);
/* 10036 */                 if (boOrder != null) {
/* 10037 */                   map.put("picturesShow", boOrder.getBoGoods().getPicturesShow());
/* 10038 */                   map.put("tradeMoney", boOrder.getTradeMoney());
/* 10039 */                   map.put("goodsIntroduce", boOrder.getBoGoods().getGoodsIntroduce());
/* 10040 */                   map.put("goodsTitle", boOrder.getBoGoods().getGoodsTitle());
/* 10041 */                   map.put("tradeMoney", boOrder.getTradeMoney());
/* 10042 */                   map.put("recipentName", boOrder.getRecipentName());
/* 10043 */                   map.put("recipentPhone", boOrder.getRecipentPhone());
/* 10044 */                   map.put("recipentAddress", boOrder.getRecipentAddress());
/* 10045 */                   map.put("tradeNo", boOrder.getTradeNo().toString());
/* 10046 */                   map.put("paymentTime", boOrder.getPaymentTime());
/* 10047 */                   map.put("orderState", boOrder.getOrderState());
/* 10048 */                   this.requestJson.setData(map);
/* 10049 */                   this.requestJson.setSuccess(true);
/*       */                 }
/*       */               } else {
/* 10052 */                 this.requestJson.setData(is);
/* 10053 */                 this.requestJson.setMessage("超时了");
/* 10054 */                 this.requestJson.setSuccess(false);
/*       */               }
/*       */             } else {
/* 10057 */               System.err.println("超时了");
/* 10058 */               this.requestJson.setData(is);
/* 10059 */               this.requestJson.setMessage("超时了");
/* 10060 */               this.requestJson.setSuccess(false);
/*       */             }
/*       */           }
/*       */           catch (Exception e)
/*       */           {
/* 10065 */             logger.info("error_" + e.getMessage());
/* 10066 */             this.requestJson.setData(is);
/* 10067 */             this.requestJson.setMessage("服务器发生异常");
/* 10068 */             this.requestJson.setSuccess(false);
/*       */           }
/*       */ 
/*       */         }
/*       */ 
/*       */       }
/*       */       else
/*       */       {
/* 10077 */         System.err.println("验证不通过");
/* 10078 */         this.requestJson.setMessage("验证不通过");
/* 10079 */         this.requestJson.setSuccess(false);
/*       */       }
/*       */     } else {
/* 10082 */       Boolean ral = isRal(timestamp, nonce, sign, access_token, userCode, "已购单个商品详情");
/* 10083 */       if (ral.booleanValue()) {
/* 10084 */         System.err.println("验证通过");
/* 10085 */         Long accessToken = Long.valueOf(timestamp);
/* 10086 */         BoUsers boUsers = this.boUserServicess.findByUserUserCode(userCode);
/* 10087 */         if (boUsers == null) {
/* 10088 */           this.requestJson.setData(is);
/* 10089 */           this.requestJson.setMessage("Invalid_User");
/* 10090 */           this.requestJson.setSuccess(true);
/*       */         } else {
/*       */           try {
/* 10093 */             if (accessToken.longValue() < Long.valueOf(boUsers.getAccessTokenTime()).longValue()) {
/* 10094 */               Map map = new HashMap();
/* 10095 */               BoOrder boOrder = this.boOrderService.findByOrderId(this.userPhone, this.orderId);
/* 10096 */               if (boOrder != null) {
/* 10097 */                 map.put("picturesShow", boOrder.getBoGoods().getPicturesShow());
/* 10098 */                 map.put("tradeMoney", boOrder.getTradeMoney());
/* 10099 */                 map.put("goodsIntroduce", boOrder.getBoGoods().getGoodsIntroduce());
/* 10100 */                 map.put("goodsTitle", boOrder.getBoGoods().getGoodsTitle());
/* 10101 */                 map.put("tradeMoney", boOrder.getTradeMoney());
/* 10102 */                 map.put("recipentName", boOrder.getRecipentName());
/* 10103 */                 map.put("recipentPhone", boOrder.getRecipentPhone());
/* 10104 */                 map.put("recipentAddress", boOrder.getRecipentAddress());
/* 10105 */                 map.put("tradeNo", boOrder.getTradeNo().toString());
/* 10106 */                 map.put("paymentTime", boOrder.getPaymentTime());
/* 10107 */                 map.put("orderState", boOrder.getOrderState());
/* 10108 */                 this.requestJson.setData(map);
/* 10109 */                 this.requestJson.setSuccess(true);
/*       */               }
/*       */             } else {
/* 10112 */               this.requestJson.setData(is);
/* 10113 */               this.requestJson.setMessage("超时了");
/* 10114 */               this.requestJson.setSuccess(false);
/*       */             }
/*       */           }
/*       */           catch (Exception e) {
/* 10118 */             logger.info("error_" + e.getMessage());
/* 10119 */             this.requestJson.setData(is);
/* 10120 */             this.requestJson.setMessage("服务器发生异常");
/* 10121 */             this.requestJson.setSuccess(false);
/*       */           }
/*       */ 
/*       */         }
/*       */ 
/*       */       }
/*       */       else
/*       */       {
/* 10130 */         System.err.println("验证不通过");
/* 10131 */         this.requestJson.setMessage("验证不通过");
/* 10132 */         this.requestJson.setSuccess(false);
/*       */       }
/*       */     }
/* 10135 */     return "success";
/*       */   }
/*       */ 
/*       */   @Action(value="gainuserorder", results={@org.apache.struts2.convention.annotation.Result(type="json", params={"root", "requestJson"})})
/*       */   public String gainUserOrder()
/*       */   {
/* 10146 */     this.requestJson = new RequestJson();
/* 10147 */     Map is = new HashMap();
/* 10148 */     HttpServletRequest request = ServletActionContext.getRequest();
/* 10149 */     String timestamp = request.getHeader("timestamp");
/* 10150 */     String nonce = request.getHeader("nonce");
/* 10151 */     String sign = request.getHeader("sign");
/* 10152 */     String access_token = request.getHeader("access_token");
/* 10153 */     String userCode = request.getHeader("userCode");
/*       */     List voList;
/* 10154 */     if (userCode.contains(",")) {
/* 10155 */       String[] userCode2 = userCode.split(",");
/* 10156 */       BoUsers boUsers = this.boUserServicess.findByUserUserCode(userCode2[0].trim().toString());
/* 10157 */       BoUsers phone = this.boUserServicess.findByUserPhone(userCode2[1].trim().toString());
/* 10158 */       Boolean ral = isRal(timestamp, nonce, sign, access_token, userCode, "已购模块");
/* 10159 */       if (ral.booleanValue()) {
/* 10160 */         System.err.println("验证通过");
/* 10161 */         Long accessToken = Long.valueOf(timestamp);
/*       */ 
/* 10163 */         if ((phone == null) || (boUsers == null)) {
/* 10164 */           this.requestJson.setData(is);
/* 10165 */           this.requestJson.setMessage("Invalid_User");
/* 10166 */           this.requestJson.setSuccess(true);
/*       */         }
/* 10168 */         else if (access_token.equals(phone.getAccessToken())) {
/* 10169 */           if (accessToken.longValue() < Long.valueOf(phone.getAccessTokenTime()).longValue()) {
/*       */             try {
/* 10171 */               List<BoOrder> list = this.boOrderService.getByuserPhone(this.userPhone);
/* 10172 */               if (list.size() <= 0) {
/* 10173 */                 this.requestJson.setData(is);
/* 10174 */                 this.requestJson.setMessage("没有购买过商品");
/* 10175 */                 this.requestJson.setSuccess(true);
/*       */               } else {
/* 10177 */                 voList = new ArrayList();
/* 10178 */                 for (BoOrder boOrder : list) {
/* 10179 */                   Map map = new HashMap();
/* 10180 */                   map.put("orderId", boOrder.getOrderId());
/* 10181 */                   map.put("goodsIntroduce", boOrder.getBoGoods().getGoodsIntroduce());
/* 10182 */                   map.put("goodsTitle", boOrder.getBoGoods().getGoodsTitle());
/* 10183 */                   map.put("tradeMoney", boOrder.getTradeMoney());
/* 10184 */                   map.put("picturesShow", boOrder.getBoGoods().getPicturesShow());
/* 10185 */                   voList.add(map);
/*       */                 }
/* 10187 */                 this.requestJson.setData(voList);
/*       */               }
/*       */             }
/*       */             catch (Exception e) {
/* 10191 */               logger.info("error_" + e.getMessage());
/* 10192 */               this.requestJson.setData(is);
/* 10193 */               this.requestJson.setMessage("服务器发生异常");
/* 10194 */               this.requestJson.setSuccess(false);
/*       */             }
/*       */           }
/*       */           else
/*       */           {
/* 10199 */             System.err.println("AToken时间戳超时了");
/* 10200 */             this.requestJson.setMessage("超时了");
/* 10201 */             this.requestJson.setSuccess(false);
/*       */           }
/*       */         } else {
/* 10204 */           System.err.println("AToken超时了");
/* 10205 */           this.requestJson.setMessage("超时了");
/* 10206 */           this.requestJson.setSuccess(false);
/*       */         }
/*       */       }
/*       */       else {
/* 10210 */         System.err.println("验证不通过");
/* 10211 */         this.requestJson.setMessage("验证不通过");
/* 10212 */         this.requestJson.setSuccess(false);
/*       */       }
/*       */     }
/*       */     else {
/* 10216 */       Boolean ral = isRal(timestamp, nonce, sign, access_token, userCode, "已购模块");
/* 10217 */       if (ral.booleanValue()) {
/* 10218 */         System.err.println("验证通过");
/* 10219 */         Long accessToken = Long.valueOf(timestamp);
/* 10220 */         BoUsers boUsers = this.boUserServicess.findByUserUserCode(userCode);
/* 10221 */         if (boUsers == null) {
/* 10222 */           this.requestJson.setData(is);
/* 10223 */           this.requestJson.setMessage("Invalid_User");
/* 10224 */           this.requestJson.setSuccess(true);
/*       */         }
/* 10226 */         else if (accessToken.longValue() < Long.valueOf(boUsers.getAccessTokenTime()).longValue()) {
/*       */           try {
/* 10228 */             List<BoOrder> list = this.boOrderService.getByuserPhone(this.userPhone);
/* 10229 */             if (list.size() <= 0) {
/* 10230 */               this.requestJson.setData(is);
/* 10231 */               this.requestJson.setMessage("没有购买过商品");
/* 10232 */               this.requestJson.setSuccess(true);
/*       */             } else {
/* 10234 */               voList = new ArrayList();
/* 10235 */               for (BoOrder boOrder : list) {
/* 10236 */                 Map map = new HashMap();
/* 10237 */                 map.put("orderId", boOrder.getOrderId());
/* 10238 */                 map.put("goodsIntroduce", boOrder.getBoGoods().getGoodsIntroduce());
/* 10239 */                 map.put("goodsTitle", boOrder.getBoGoods().getGoodsTitle());
/* 10240 */                 map.put("tradeMoney", boOrder.getTradeMoney());
/* 10241 */                 map.put("picturesShow", boOrder.getBoGoods().getPicturesShow());
/* 10242 */                 voList.add(map);
/*       */               }
/* 10244 */               this.requestJson.setData(voList);
/*       */             }
/*       */           }
/*       */           catch (Exception e) {
/* 10248 */             logger.info("error_" + e.getMessage());
/* 10249 */             this.requestJson.setData(is);
/* 10250 */             this.requestJson.setMessage("服务器发生异常");
/* 10251 */             this.requestJson.setSuccess(false);
/*       */           }
/*       */         }
/*       */         else
/*       */         {
/* 10256 */           this.requestJson.setMessage("超时了");
/* 10257 */           this.requestJson.setSuccess(false);
/*       */         }
/*       */       }
/*       */       else {
/* 10261 */         System.err.println("验证不通过");
/* 10262 */         this.requestJson.setMessage("验证不通过");
/* 10263 */         this.requestJson.setSuccess(false);
/*       */       }
/*       */     }
/* 10266 */     return "success";
/*       */   }
/*       */ 
/*       */   @Action(value="unbundlinghost", results={@org.apache.struts2.convention.annotation.Result(type="json", params={"root", "requestJson"})})
/*       */   public String unbundlingHost()
/*       */   {
/* 10277 */     this.requestJson = new RequestJson();
/* 10278 */     Map map = new HashMap();
/* 10279 */     HttpServletRequest request = ServletActionContext.getRequest();
/* 10280 */     String header = request.getHeader("timestamp");
/* 10281 */     String header2 = request.getHeader("nonce");
/* 10282 */     String header3 = request.getHeader("sign");
/* 10283 */     String header4 = request.getHeader("access_token");
/* 10284 */     String userCode = request.getHeader("userCode");
/*       */     List<BoControlEnclosure> controlEnclosure;
/*       */     List<BoInfraredButtons> infraredButtonsList;
/*       */     Object infraredLearnControlMapList;
/*       */     Object airBindingPanelList;
/*       */     Object modelInfoList;
/*       */     Object sensorList;
/*       */     Object lockVerdictList;
/* 10285 */     if (userCode.contains(",")) {
/* 10286 */       String[] userCode2 = userCode.split(",");
/* 10287 */       BoUsers boUsers = this.boUserServicess.findByUserUserCode(userCode2[0].trim().toString());
/* 10288 */       BoUsers phone = this.boUserServicess.findByUserPhone(userCode2[1].trim().toString());
/* 10289 */       Boolean ral = isRal(header, header2, header3, header4, userCode, "主机解绑");
/* 10290 */       if (ral.booleanValue()) {
/* 10291 */         System.err.println("验证通过");
/* 10292 */         Long accessToken = Long.valueOf(header);
/* 10293 */         if ((phone == null) || (boUsers == null)) {
/* 10294 */           this.requestJson.setData(map);
/* 10295 */           this.requestJson.setMessage("Invalid_User");
/* 10296 */           this.requestJson.setSuccess(true);
/*       */         }
/* 10298 */         else if (header4.equals(phone.getAccessToken())) {
/* 10299 */           if (accessToken.longValue() < Long.valueOf(phone.getAccessTokenTime()).longValue()) {
/* 10300 */             BoDevice boDevice = this.boDeviceService.findByCode(this.deviceCode);
/* 10301 */             BoUserDevices userDevices = this.boUserDevicesServicess.findBy(userCode2[0].trim().toString(), this.deviceCode);
/* 10302 */             if (userDevices != null) {
/* 10303 */               this.boUserDevicesServicess.delete(userDevices);
/*       */ 
/* 10305 */               controlEnclosure = this.boControlEnclosureService.controlEnclosure(this.deviceCode, userCode2[0].trim().toString());
/* 10306 */               if (controlEnclosure.size() > 0) {
/* 10307 */                 for (BoControlEnclosure boControlEnclosure : controlEnclosure) {
/* 10308 */                   this.boControlEnclosureService.delete(boControlEnclosure);
/*       */                 }
/*       */ 
/*       */               }
/*       */ 
/* 10313 */               infraredButtonsList = this.boInfraredButtonsService.getListBys(userCode2[0].trim().toString(), this.deviceCode);
/* 10314 */               if (infraredButtonsList.size() > 0) {
/* 10315 */                 for (BoInfraredButtons boInfraredButtons : infraredButtonsList) {
/* 10316 */                   this.boInfraredButtonsService.delete(boInfraredButtons);
/*       */                 }
/*       */               }
/*       */ 
/* 10320 */               infraredLearnControlMapList = this.boInfraredLearnControlMapService.infraredLearnControlMapList(userCode2[0].trim().toString(), this.deviceCode);
/* 10321 */               if (((List)infraredLearnControlMapList).size() > 0) {
/* 10322 */                 for (BoInfraredLearnControlMap boInfraredLearnControlMap : (List<BoInfraredLearnControlMap>)infraredLearnControlMapList) {
/* 10323 */                   this.boInfraredLearnControlMapService.delete(boInfraredLearnControlMap);
/*       */                 }
/*       */               }
/*       */ 
/* 10327 */               airBindingPanelList = this.boAirBindingPanelService.airBindingPanelList(userCode2[0].trim().toString(), this.deviceCode);
/* 10328 */               if (((List)airBindingPanelList).size() > 0) {
/* 10329 */                 for (BoAirBindingPanel boAirBindingPanel : (List<BoAirBindingPanel>)airBindingPanelList) {
/* 10330 */                   this.boAirBindingPanelService.delete(boAirBindingPanel);
/*       */                 }
/*       */               }
/* 10333 */               modelInfoList = this.boModelInfoServicess.getBys(userCode2[0].trim().toString(), this.deviceCode);
/* 10334 */               if (((List)modelInfoList).size() > 0) {
/* 10335 */                 for (BoModelInfo boModelInfo : (List<BoModelInfo>)modelInfoList) {
/* 10336 */                   this.boModelInfoServicess.delete(boModelInfo);
/*       */                 }
/*       */               }
/* 10339 */               sensorList = this.boSensorService.gets(userCode2[0].trim().toString(), this.deviceCode);
/* 10340 */               if (((List)sensorList).size() > 0) {
/* 10341 */                 for (BoSensor boSensor : (List<BoSensor>)sensorList) {
/* 10342 */                   this.boSensorService.delete(boSensor);
/*       */                 }
/*       */               }
/*       */ 
/* 10346 */               lockVerdictList = this.boLockVerdictService.getByDeviceCode(this.deviceCode);
/* 10347 */               if (((List)lockVerdictList).size() > 0) {
/* 10348 */                 for (BoLockVerdict boLockVerdict : (List<BoLockVerdict>)lockVerdictList)
/* 10349 */                   this.boLockVerdictService.delete(boLockVerdict);
/*       */               }
/*       */               try
/*       */               {
/* 10353 */                 Thread.sleep(1000L);
/* 10354 */                 List<BoHostDevice> hostDeviceList = this.boHostDeviceService.get(userCode2[0].trim().toString(), this.deviceCode);
/* 10355 */                 if (hostDeviceList.size() > 0) {
/* 10356 */                   for (BoHostDevice boHostDevice : hostDeviceList)
/* 10357 */                     this.boHostDeviceService.delete(boHostDevice);
/*       */                 }
/*       */               }
/*       */               catch (InterruptedException e)
/*       */               {
/* 10362 */                 e.printStackTrace();
/*       */               }
/* 10364 */               this.requestJson.setData(map);
/* 10365 */               this.requestJson.setMessage("解绑成功");
/* 10366 */               this.requestJson.setSuccess(true);
/*       */             }
/*       */           } else {
/* 10369 */             System.err.println("AToken时间戳超时了");
/* 10370 */             this.requestJson.setData(map);
/* 10371 */             this.requestJson.setMessage("超时了");
/* 10372 */             this.requestJson.setSuccess(false);
/*       */           }
/*       */         } else {
/* 10375 */           System.err.println("AToken超时了");
/* 10376 */           this.requestJson.setData(map);
/* 10377 */           this.requestJson.setMessage("超时了");
/* 10378 */           this.requestJson.setSuccess(false);
/*       */         }
/*       */ 
/*       */       }
/*       */       else
/*       */       {
/* 10384 */         System.err.println("验证不通过");
/* 10385 */         this.requestJson.setData(map);
/* 10386 */         this.requestJson.setMessage("验证不通过");
/* 10387 */         this.requestJson.setSuccess(false);
/*       */       }
/*       */     } else {
/* 10390 */       Boolean ral = isRal(header, header2, header3, header4, userCode, "修改主机昵称");
/* 10391 */       if (ral.booleanValue()) {
/* 10392 */         System.err.println("验证通过");
/* 10393 */         Long accessToken = Long.valueOf(header);
/* 10394 */         BoUsers boUsers = this.boUserServicess.findByUserUserCode(userCode);
/* 10395 */         if (accessToken.longValue() < Long.valueOf(boUsers.getAccessTokenTime()).longValue()) {
/* 10396 */           if (boUsers == null) {
/* 10397 */             this.requestJson.setData(map);
/* 10398 */             this.requestJson.setMessage("Invalid_User");
/* 10399 */             this.requestJson.setSuccess(true);
/*       */           } else {
/* 10401 */             BoDevice boDevice = this.boDeviceService.findByCode(this.deviceCode);
/* 10402 */             BoUserDevices userDevices = this.boUserDevicesServicess.findBy(userCode, this.deviceCode);
/* 10403 */             if (userDevices != null) {
/* 10404 */               this.boUserDevicesServicess.delete(userDevices);
/*       */ 
/* 10406 */               controlEnclosure = this.boControlEnclosureService.controlEnclosure(this.deviceCode, userCode);
/* 10407 */               if (controlEnclosure.size() > 0) {
/* 10408 */                 for (BoControlEnclosure boControlEnclosure : controlEnclosure) {
/* 10409 */                   this.boControlEnclosureService.delete(boControlEnclosure);
/*       */                 }
/*       */ 
/*       */               }
/*       */ 
/* 10414 */               infraredButtonsList = this.boInfraredButtonsService.getListBys(userCode, this.deviceCode);
/* 10415 */               if (infraredButtonsList.size() > 0) {
/* 10416 */                 for (BoInfraredButtons boInfraredButtons : infraredButtonsList) {
/* 10417 */                   this.boInfraredButtonsService.delete(boInfraredButtons);
/*       */                 }
/*       */               }
/*       */ 
/* 10421 */               infraredLearnControlMapList = this.boInfraredLearnControlMapService.infraredLearnControlMapList(userCode, this.deviceCode);
/* 10422 */               if (((JSONArray) infraredLearnControlMapList).size() > 0) {
/* 10423 */                 for (infraredLearnControlMapList = ((JSONArray) infraredLearnControlMapList).iterator(); ((Iterator)infraredLearnControlMapList).hasNext(); ) { BoInfraredLearnControlMap boInfraredLearnControlMap = (BoInfraredLearnControlMap)((Iterator)infraredLearnControlMapList).next();
/* 10424 */                   this.boInfraredLearnControlMapService.delete(boInfraredLearnControlMap);
/*       */                 }
/*       */               }
/*       */ 
/* 10428 */               airBindingPanelList = this.boAirBindingPanelService.airBindingPanelList(userCode, this.deviceCode);
/* 10429 */               if (((JSONArray) airBindingPanelList).size() > 0) {
/* 10430 */                 for (airBindingPanelList = ((JSONArray) airBindingPanelList).iterator(); ((Iterator)airBindingPanelList).hasNext(); ) { BoAirBindingPanel boAirBindingPanel = (BoAirBindingPanel)((Iterator)airBindingPanelList).next();
/* 10431 */                   this.boAirBindingPanelService.delete(boAirBindingPanel);
/*       */                 }
/*       */               }
/* 10434 */               modelInfoList = this.boModelInfoServicess.getBys(userCode, this.deviceCode);
/* 10435 */               if (((JSONArray) modelInfoList).size() > 0) {
/* 10436 */                 for (modelInfoList = ((JSONArray) modelInfoList).iterator(); ((Iterator)modelInfoList).hasNext(); ) { BoModelInfo boModelInfo = (BoModelInfo)((Iterator)modelInfoList).next();
/* 10437 */                   this.boModelInfoServicess.delete(boModelInfo);
/*       */                 }
/*       */               }
/* 10440 */               sensorList = this.boSensorService.gets(userCode, this.deviceCode);
/* 10441 */               if (((JSONArray) sensorList).size() > 0) {
/* 10442 */                 for (sensorList = ((JSONArray) sensorList).iterator(); ((Iterator)sensorList).hasNext(); ) { BoSensor boSensor = (BoSensor)((Iterator)sensorList).next();
/* 10443 */                   this.boSensorService.delete(boSensor);
/*       */                 }
/*       */               }
/*       */ 
/*       */               try
/*       */               {
/* 10449 */                 Thread.sleep(1000L);
/* 10450 */                 List hostDeviceList = this.boHostDeviceService.get(userCode, this.deviceCode);
/* 10451 */                 if (hostDeviceList.size() > 0)
/* 10452 */                   for (lockVerdictList = hostDeviceList.iterator(); ((Iterator)lockVerdictList).hasNext(); ) { BoHostDevice boHostDevice = (BoHostDevice)((Iterator)lockVerdictList).next();
/* 10453 */                     this.boHostDeviceService.delete(boHostDevice);
/*       */                   }
/*       */               }
/*       */               catch (InterruptedException e)
/*       */               {
/* 10458 */                 e.printStackTrace();
/*       */               }
/* 10460 */               this.requestJson.setData(map);
/* 10461 */               this.requestJson.setMessage("解绑成功");
/* 10462 */               this.requestJson.setSuccess(true);
/*       */             }
/*       */           }
/*       */         } else {
/* 10466 */           this.requestJson.setData(map);
/* 10467 */           this.requestJson.setMessage("超时了");
/* 10468 */           this.requestJson.setSuccess(false);
/*       */         }
/*       */       }
/*       */       else {
/* 10472 */         System.err.println("验证不通过");
/* 10473 */         this.requestJson.setData(map);
/* 10474 */         this.requestJson.setMessage("验证不通过");
/* 10475 */         this.requestJson.setSuccess(false);
/*       */       }
/*       */     }
/* 10478 */     return "success";
/*       */   }
/*       */ 
/*       */   @Action(value="delectshoppingcart", results={@org.apache.struts2.convention.annotation.Result(type="json", params={"root", "requestJson"})})
/*       */   public String delectShoppingCart()
/*       */   {
/* 10490 */     this.requestJson = new RequestJson();
/* 10491 */     Map maps = new HashMap();
/* 10492 */     HttpServletRequest request = ServletActionContext.getRequest();
/* 10493 */     String header = request.getHeader("timestamp");
/* 10494 */     String header2 = request.getHeader("nonce");
/* 10495 */     String header3 = request.getHeader("sign");
/* 10496 */     String header4 = request.getHeader("access_token");
/* 10497 */     String userCode = request.getHeader("userCode");
/* 10498 */     if (userCode.contains(",")) {
/* 10499 */       String[] userCode2 = userCode.split(",");
/* 10500 */       BoUsers boUsers = this.boUserServicess.findByUserUserCode(userCode2[0].trim().toString());
/* 10501 */       BoUsers phone = this.boUserServicess.findByUserPhone(userCode2[1].trim().toString());
/* 10502 */       Boolean ral = isRal(header, header2, header3, header4, userCode, " 删除购物车商品");
/* 10503 */       if (ral.booleanValue()) {
/* 10504 */         System.err.println("验证通过");
/* 10505 */         Long accessToken = Long.valueOf(header);
/*       */ 
/* 10507 */         if ((phone == null) || (boUsers == null)) {
/* 10508 */           this.requestJson.setData(maps);
/* 10509 */           this.requestJson.setMessage("Invalid_User");
/* 10510 */           this.requestJson.setSuccess(true);
/*       */         }
/* 10512 */         else if (header4.equals(phone.getAccessToken())) {
/* 10513 */           if (accessToken.longValue() < Long.valueOf(phone.getAccessTokenTime()).longValue()) {
/* 10514 */             BoShoppingCart shoppingCart = this.boShoppingCartService.findById(this.id, this.userPhone);
/* 10515 */             if (shoppingCart == null) {
/* 10516 */               this.requestJson.setData(maps);
/* 10517 */               this.requestJson.setMessage("购物车里没有该商品");
/* 10518 */               this.requestJson.setSuccess(true);
/*       */             } else {
/* 10520 */               this.requestJson.setData(maps);
/* 10521 */               this.boShoppingCartService.delete(shoppingCart);
/* 10522 */               this.requestJson.setMessage("删除成功");
/* 10523 */               this.requestJson.setSuccess(true);
/*       */             }
/*       */           } else {
/* 10526 */             System.err.println("AToken时间戳超时了");
/* 10527 */             this.requestJson.setMessage("超时了");
/* 10528 */             this.requestJson.setSuccess(false);
/*       */           }
/*       */         } else {
/* 10531 */           System.err.println("AToken超时了");
/* 10532 */           this.requestJson.setMessage("超时了");
/* 10533 */           this.requestJson.setSuccess(false);
/*       */         }
/*       */ 
/*       */       }
/*       */       else
/*       */       {
/* 10539 */         System.err.println("验证不通过");
/* 10540 */         this.requestJson.setMessage("验证不通过");
/* 10541 */         this.requestJson.setSuccess(false);
/*       */       }
/*       */     } else {
/* 10544 */       Boolean ral = isRal(header, header2, header3, header4, userCode, "修改主机昵称");
/* 10545 */       if (ral.booleanValue()) {
/* 10546 */         System.err.println("验证通过");
/* 10547 */         Long accessToken = Long.valueOf(header);
/* 10548 */         BoUsers boUsers = this.boUserServicess.findByUserUserCode(userCode);
/* 10549 */         if (boUsers == null) {
/* 10550 */           this.requestJson.setMessage("Invalid_User");
/* 10551 */           this.requestJson.setSuccess(true);
/*       */         }
/* 10553 */         else if (accessToken.longValue() < Long.valueOf(boUsers.getAccessTokenTime()).longValue())
/*       */         {
/* 10555 */           BoShoppingCart shoppingCart = this.boShoppingCartService.findById(this.id, this.userPhone);
/* 10556 */           if (shoppingCart == null) {
/* 10557 */             this.requestJson.setData(maps);
/* 10558 */             this.requestJson.setMessage("购物车里没有该商品");
/* 10559 */             this.requestJson.setSuccess(true);
/*       */           } else {
/* 10561 */             this.requestJson.setData(maps);
/* 10562 */             this.boShoppingCartService.delete(shoppingCart);
/* 10563 */             this.requestJson.setMessage("删除成功");
/* 10564 */             this.requestJson.setSuccess(true);
/*       */           }
/*       */         } else {
/* 10567 */           this.requestJson.setMessage("超时了");
/* 10568 */           this.requestJson.setSuccess(false);
/*       */         }
/*       */ 
/*       */       }
/*       */       else
/*       */       {
/* 10574 */         System.err.println("验证不通过");
/* 10575 */         this.requestJson.setMessage("验证不通过");
/* 10576 */         this.requestJson.setSuccess(false);
/*       */       }
/*       */     }
/* 10579 */     return "success";
/*       */   }
/*       */ 
/*       */   @Action(value="queryshoppingcart", results={@org.apache.struts2.convention.annotation.Result(type="json", params={"root", "requestJson"})})
/*       */   public String queryUserShoppingCart()
/*       */   {
/* 10590 */     this.requestJson = new RequestJson();
/* 10591 */     Map maps = new HashMap();
/* 10592 */     HttpServletRequest request = ServletActionContext.getRequest();
/* 10593 */     String header = request.getHeader("timestamp");
/* 10594 */     String header2 = request.getHeader("nonce");
/* 10595 */     String header3 = request.getHeader("sign");
/* 10596 */     String header4 = request.getHeader("access_token");
/* 10597 */     String userCode = request.getHeader("userCode");
/*       */     List voList;
/* 10598 */     if (userCode.contains(",")) {
/* 10599 */       String[] userCode2 = userCode.split(",");
/* 10600 */       BoUsers boUsers = this.boUserServicess.findByUserUserCode(userCode2[0].trim().toString());
/* 10601 */       BoUsers phone = this.boUserServicess.findByUserPhone(userCode2[1].trim().toString());
/* 10602 */       Boolean ral = isRal(header, header2, header3, header4, userCode, "查询购物车里的商品接口");
/* 10603 */       if (ral.booleanValue()) {
/* 10604 */         System.err.println("验证通过");
/* 10605 */         Long accessToken = Long.valueOf(header);
/*       */ 
/* 10607 */         if ((phone == null) || (boUsers == null)) {
/* 10608 */           this.requestJson.setData(maps);
/* 10609 */           this.requestJson.setMessage("Invalid_User");
/* 10610 */           this.requestJson.setSuccess(true);
/*       */         }
/* 10612 */         else if (header4.equals(phone.getAccessToken())) {
/* 10613 */           if (accessToken.longValue() < Long.valueOf(phone.getAccessTokenTime()).longValue()) {
/* 10614 */             List<BoShoppingCart> list = this.boShoppingCartService.getListByUserCode(this.userPhone);
/* 10615 */             if (list.size() > 0) {
/* 10616 */               voList = new ArrayList();
/*       */ 
/* 10618 */               for (BoShoppingCart boShoppingCart : list) {
/* 10619 */                 Map map = new HashMap();
/* 10620 */                 map.put("id", boShoppingCart.getId());
/* 10621 */                 map.put("ids", boShoppingCart.getBoGoods().getId());
/* 10622 */                 map.put("goodsTitle", boShoppingCart.getBoGoods().getGoodsTitle().toString());
/* 10623 */                 map.put("goodsIntroduce", boShoppingCart.getBoGoods().getGoodsIntroduce().toString());
/* 10624 */                 map.put("goodsPrice", boShoppingCart.getBoGoods().getGoodsPrice().toString());
/* 10625 */                 map.put("salesVolumeDegree", 
/* 10626 */                   boShoppingCart.getBoGoods().getSalesVolumeDegree().toString());
/* 10627 */                 map.put("picturesShow", boShoppingCart.getBoGoods().getPicturesShow().toString());
/*       */ 
/* 10629 */                 voList.add(map);
/*       */               }
/* 10631 */               this.requestJson.setData(voList);
/*       */             } else {
/* 10633 */               this.requestJson.setData(maps);
/* 10634 */               this.requestJson.setMessage("购物车没有商品");
/* 10635 */               this.requestJson.setSuccess(true);
/*       */             }
/*       */           } else {
/* 10638 */             System.err.println("AToken时间戳超时了");
/* 10639 */             this.requestJson.setMessage("超时了");
/* 10640 */             this.requestJson.setSuccess(false);
/*       */           }
/*       */         } else {
/* 10643 */           System.err.println("AToken超时了");
/* 10644 */           this.requestJson.setMessage("超时了");
/* 10645 */           this.requestJson.setSuccess(false);
/*       */         }
/*       */       }
/*       */       else
/*       */       {
/* 10650 */         System.err.println("验证不通过");
/* 10651 */         this.requestJson.setMessage("验证不通过");
/* 10652 */         this.requestJson.setSuccess(false);
/*       */       }
/*       */     } else {
/* 10655 */       Boolean ral = isRal(header, header2, header3, header4, userCode, "修改主机昵称");
/* 10656 */       if (ral.booleanValue()) {
/* 10657 */         System.err.println("验证通过");
/* 10658 */         Long accessToken = Long.valueOf(header);
/* 10659 */         BoUsers boUsers = this.boUserServicess.findByUserUserCode(userCode);
/* 10660 */         if (boUsers == null) {
/* 10661 */           this.requestJson.setMessage("Invalid_User");
/* 10662 */           this.requestJson.setSuccess(true);
/*       */         }
/* 10664 */         else if (accessToken.longValue() < Long.valueOf(boUsers.getAccessTokenTime()).longValue())
/*       */         {
/* 10666 */           if (boUsers == null) {
/* 10667 */             this.requestJson.setData(maps);
/* 10668 */             this.requestJson.setMessage("Invalid_User");
/* 10669 */             this.requestJson.setSuccess(true);
/*       */           } else {
/* 10671 */             List<BoShoppingCart> list = this.boShoppingCartService.getListByUserCode(this.userPhone);
/* 10672 */             if (list.size() > 0) {
/* 10673 */               voList = new ArrayList();
/*       */ 
/* 10675 */               for (BoShoppingCart boShoppingCart : list) {
/* 10676 */                 Map map = new HashMap();
/* 10677 */                 map.put("id", boShoppingCart.getId());
/* 10678 */                 map.put("ids", boShoppingCart.getBoGoods().getId());
/* 10679 */                 map.put("goodsTitle", boShoppingCart.getBoGoods().getGoodsTitle().toString());
/* 10680 */                 map.put("goodsIntroduce", boShoppingCart.getBoGoods().getGoodsIntroduce().toString());
/* 10681 */                 map.put("goodsPrice", boShoppingCart.getBoGoods().getGoodsPrice().toString());
/* 10682 */                 map.put("salesVolumeDegree", 
/* 10683 */                   boShoppingCart.getBoGoods().getSalesVolumeDegree().toString());
/* 10684 */                 map.put("picturesShow", boShoppingCart.getBoGoods().getPicturesShow().toString());
/*       */ 
/* 10686 */                 voList.add(map);
/*       */               }
/* 10688 */               this.requestJson.setData(voList);
/*       */             } else {
/* 10690 */               this.requestJson.setData(maps);
/* 10691 */               this.requestJson.setMessage("购物车没有商品");
/* 10692 */               this.requestJson.setSuccess(true);
/*       */             }
/*       */           }
/*       */         } else {
/* 10696 */           this.requestJson.setMessage("超时了");
/* 10697 */           this.requestJson.setSuccess(false);
/*       */         }
/*       */       }
/*       */       else
/*       */       {
/* 10702 */         System.err.println("验证不通过");
/* 10703 */         this.requestJson.setMessage("验证不通过");
/* 10704 */         this.requestJson.setSuccess(false);
/*       */       }
/*       */     }
/* 10707 */     return "success";
/*       */   }
/*       */ 
/*       */   @Action(value="addshoppingcart", results={@org.apache.struts2.convention.annotation.Result(type="json", params={"root", "requestJson"})})
/*       */   public String addShoppingCart()
/*       */   {
/* 10718 */     this.requestJson = new RequestJson();
/* 10719 */     Map maps = new HashMap();
/* 10720 */     HttpServletRequest request = ServletActionContext.getRequest();
/* 10721 */     String str = "";
/* 10722 */     Md5 md5 = new Md5();
/* 10723 */     String header = request.getHeader("timestamp");
/* 10724 */     String header2 = request.getHeader("nonce");
/* 10725 */     String header3 = request.getHeader("sign");
/* 10726 */     String header4 = request.getHeader("access_token");
/* 10727 */     String userCode = request.getHeader("userCode");
/* 10728 */     if (userCode.contains(",")) {
/* 10729 */       String[] userCode2 = userCode.split(",");
/* 10730 */       BoUsers boUsers = this.boUserServicess.findByUserUserCode(userCode2[0].trim().toString());
/* 10731 */       BoUsers phone = this.boUserServicess.findByUserPhone(userCode2[1].trim().toString());
/* 10732 */       Boolean ral = isRal(header, header2, header3, header4, userCode, "修改主机昵称");
/* 10733 */       if (ral.booleanValue()) {
/* 10734 */         System.err.println("验证通过");
/* 10735 */         Long accessToken = Long.valueOf(header);
/*       */ 
/* 10737 */         if ((phone == null) || (boUsers == null)) {
/* 10738 */           this.requestJson.setData(maps);
/* 10739 */           this.requestJson.setMessage("Invalid_User");
/* 10740 */           this.requestJson.setSuccess(true);
/*       */         }
/* 10742 */         else if (header4.equals(phone.getAccessToken())) {
/* 10743 */           if (accessToken.longValue() < Long.valueOf(phone.getAccessTokenTime()).longValue()) {
/* 10744 */             BoGoods goodsid = this.boGoodsService.findById(this.id);
/* 10745 */             BoShoppingCart boShoppingCart = new BoShoppingCart();
/* 10746 */             boShoppingCart.setBoGoods(goodsid);
/* 10747 */             boShoppingCart.setBoUsers(boUsers);
/* 10748 */             boShoppingCart.setCreateTime(new Date());
/* 10749 */             this.boShoppingCartService.save(boShoppingCart);
/*       */           } else {
/* 10751 */             System.err.println("AToken时间戳超时了");
/* 10752 */             this.requestJson.setData(maps);
/* 10753 */             this.requestJson.setMessage("超时了");
/* 10754 */             this.requestJson.setSuccess(false);
/*       */           }
/*       */         } else {
/* 10757 */           System.err.println("AToken超时了");
/* 10758 */           this.requestJson.setData(maps);
/* 10759 */           this.requestJson.setMessage("超时了");
/* 10760 */           this.requestJson.setSuccess(false);
/*       */         }
/*       */       }
/*       */       else
/*       */       {
/* 10765 */         System.err.println("验证不通过");
/* 10766 */         this.requestJson.setMessage("验证不通过");
/* 10767 */         this.requestJson.setSuccess(false);
/*       */       }
/*       */     } else {
/* 10770 */       Boolean ral = isRal(header, header2, header3, header4, userCode, "修改主机昵称");
/* 10771 */       if (ral.booleanValue()) {
/* 10772 */         System.err.println("验证通过");
/* 10773 */         Long accessToken = Long.valueOf(header);
/* 10774 */         BoUsers boUsers = this.boUserServicess.findByUserPhone(this.userPhone);
/* 10775 */         if (boUsers == null) {
/* 10776 */           this.requestJson.setData(maps);
/* 10777 */           this.requestJson.setMessage("Invalid_User");
/* 10778 */           this.requestJson.setSuccess(true);
/*       */         }
/* 10780 */         else if (accessToken.longValue() < Long.valueOf(boUsers.getAccessTokenTime()).longValue()) {
/* 10781 */           BoGoods goodsid = this.boGoodsService.findById(this.id);
/* 10782 */           BoShoppingCart boShoppingCart = new BoShoppingCart();
/* 10783 */           boShoppingCart.setBoGoods(goodsid);
/* 10784 */           boShoppingCart.setBoUsers(boUsers);
/* 10785 */           boShoppingCart.setCreateTime(new Date());
/* 10786 */           this.boShoppingCartService.save(boShoppingCart);
/*       */         } else {
/* 10788 */           this.requestJson.setData(maps);
/* 10789 */           this.requestJson.setMessage("超时了");
/* 10790 */           this.requestJson.setSuccess(false);
/*       */         }
/*       */       }
/*       */       else {
/* 10794 */         System.err.println("验证不通过");
/* 10795 */         this.requestJson.setMessage("验证不通过");
/* 10796 */         this.requestJson.setSuccess(false);
/*       */       }
/*       */     }
/* 10799 */     return "success";
/*       */   }
/*       */ 
/*       */   @Action(value="deleteunclassifieddevice", results={@org.apache.struts2.convention.annotation.Result(type="json", params={"root", "requestJson"})})
/*       */   public String deleteUnclassifiedDevice()
/*       */   {
/* 10811 */     this.requestJson = new RequestJson();
/* 10812 */     Map maps = new HashMap();
/* 10813 */     HttpServletRequest request = ServletActionContext.getRequest();
/* 10814 */     String str = "";
/* 10815 */     Md5 md5 = new Md5();
/* 10816 */     String header = request.getHeader("timestamp");
/* 10817 */     String header2 = request.getHeader("nonce");
/* 10818 */     String header3 = request.getHeader("sign");
/* 10819 */     String header4 = request.getHeader("access_token");
/* 10820 */     String userCode = request.getHeader("userCode");
/*       */     Long accessToken;
/* 10821 */     if (userCode.contains(",")) {
/* 10822 */       String[] userCode2 = userCode.split(",");
/* 10823 */       BoUsers boUsers = this.boUserServicess.findByUserUserCode(userCode2[0].trim().toString());
/* 10824 */       BoUsers phone = this.boUserServicess.findByUserPhone(userCode2[1].trim().toString());
/* 10825 */       Boolean ral = isRal(header, header2, header3, header4, userCode, "未分类删除设备接口");
/* 10826 */       if (ral.booleanValue()) {
/* 10827 */         System.err.println("验证通过");
/* 10828 */         accessToken = Long.valueOf(header);
/* 10829 */         if ((phone == null) || (boUsers == null)) {
/* 10830 */           this.requestJson.setData(maps);
/* 10831 */           this.requestJson.setMessage("Invalid_User");
/* 10832 */           this.requestJson.setSuccess(true);
/*       */         }
/* 10834 */         else if (header4.equals(phone.getAccessToken())) {
/* 10835 */           if (accessToken.longValue() < Long.valueOf(phone.getAccessTokenTime()).longValue()) {
/* 10836 */             BoDeviceState boDeviceState = this.boDeviceStateService.findBy(userCode2[0].trim().toString(), this.deviceAddress);
/* 10837 */             if (boDeviceState != null) {
/* 10838 */               Long localBoDeviceState1 = 1L;//暂时用1代替       this.boDeviceStateService.delete(boDeviceState);
/*       */             }
/* 10840 */             BoHostDevice device = this.boHostDeviceService.findBydeviceAddress(userCode2[0].trim().toString(), this.deviceAddress);
/* 10841 */             if (device != null) {
/* 10842 */               BoHostDevice delete = this.boHostDeviceService.delete(device);
/* 10843 */               if (delete != null) {
/* 10844 */                 this.requestJson.setData(maps);
/* 10845 */                 this.requestJson.setMessage("删除成功");
/* 10846 */                 this.requestJson.setSuccess(true);
/*       */               } else {
/* 10848 */                 this.requestJson.setData(maps);
/* 10849 */                 this.requestJson.setMessage("删除失败");
/* 10850 */                 this.requestJson.setSuccess(false);
/*       */               }
/*       */             }
/*       */           }
/*       */           else {
/* 10855 */             System.err.println("AToken时间戳超时了");
/* 10856 */             this.requestJson.setData(maps);
/* 10857 */             this.requestJson.setMessage("超时了");
/* 10858 */             this.requestJson.setSuccess(false);
/*       */           }
/*       */         } else {
/* 10861 */           System.err.println("AToken超时了");
/* 10862 */           this.requestJson.setData(maps);
/* 10863 */           this.requestJson.setMessage("超时了");
/* 10864 */           this.requestJson.setSuccess(false);
/*       */         }
/*       */ 
/*       */       }
/*       */       else
/*       */       {
/* 10870 */         this.requestJson.setData(maps);
/* 10871 */         System.err.println("验证不通过");
/* 10872 */         this.requestJson.setMessage("验证不通过");
/* 10873 */         this.requestJson.setSuccess(false);
/*       */       }
/*       */     } else {
/* 10876 */       Boolean ral = isRal(header, header2, header3, header4, userCode, "修改主机昵称");
/* 10877 */       if (ral.booleanValue()) {
/* 10878 */         System.err.println("验证通过");
/* 10879 */         accessToken = Long.valueOf(header);
/* 10880 */         BoUsers boUsers = this.boUserServicess.findByUserUserCode(userCode);
/* 10881 */         if (accessToken.longValue() < Long.valueOf(boUsers.getAccessTokenTime()).longValue()) {
/* 10882 */           if (boUsers == null) {
/* 10883 */             this.requestJson.setData(maps);
/* 10884 */             this.requestJson.setMessage("Invalid_User");
/* 10885 */             this.requestJson.setSuccess(true);
/*       */           } else {
/* 10887 */             BoDeviceState boDeviceState = this.boDeviceStateService.findBy(userCode, this.deviceAddress);
/* 10888 */             if (boDeviceState != null) {
///* 10889 */               accessToken = this.boDeviceStateService.delete(boDeviceState);
							accessToken = 1L;//暂时用1代替
/*       */             }
/* 10891 */             BoHostDevice device = this.boHostDeviceService.findBydeviceAddress(userCode, this.deviceAddress);
/* 10892 */             if (device != null) {
/* 10893 */               BoHostDevice delete = this.boHostDeviceService.delete(device);
/* 10894 */               if (delete != null) {
/* 10895 */                 this.requestJson.setMessage("删除成功");
/* 10896 */                 this.requestJson.setSuccess(true);
/*       */               } else {
/* 10898 */                 this.requestJson.setMessage("删除失败");
/* 10899 */                 this.requestJson.setSuccess(false);
/*       */               }
/*       */             }
/*       */           }
/*       */         }
/*       */         else {
/* 10905 */           this.requestJson.setData(maps);
/* 10906 */           this.requestJson.setMessage("超时了");
/* 10907 */           this.requestJson.setSuccess(false);
/*       */         }
/*       */       }
/*       */       else {
/* 10911 */         this.requestJson.setData(maps);
/* 10912 */         System.err.println("验证不通过");
/* 10913 */         this.requestJson.setMessage("验证不通过");
/* 10914 */         this.requestJson.setSuccess(false);
/*       */       }
/*       */     }
/* 10917 */     return "success";
/*       */   }
/*       */ 
/*       */   @Action(value="deletedevice", results={@org.apache.struts2.convention.annotation.Result(type="json", params={"root", "requestJson"})})
/*       */   public String deleteDevice()
/*       */   {
/* 10928 */     this.requestJson = new RequestJson();
/* 10929 */     Map maps = new HashMap();
/* 10930 */     HttpServletRequest request = ServletActionContext.getRequest();
/* 10931 */     String str = "";
/* 10932 */     Md5 md5 = new Md5();
/* 10933 */     String header = request.getHeader("timestamp");
/* 10934 */     String header2 = request.getHeader("nonce");
/* 10935 */     String header3 = request.getHeader("sign");
/* 10936 */     String header4 = request.getHeader("access_token");
/* 10937 */     String userCode = request.getHeader("userCode");
/* 10938 */     if (userCode.contains(",")) {
/* 10939 */       String[] userCode2 = userCode.split(",");
/* 10940 */       BoUsers boUsers = this.boUserServicess.findByUserUserCode(userCode2[0].trim().toString());
/* 10941 */       BoUsers phone = this.boUserServicess.findByUserPhone(userCode2[1].trim().toString());
/* 10942 */       Boolean ral = isRal(header, header2, header3, header4, userCode, "删除设备接口");
/* 10943 */       if (ral.booleanValue()) {
/* 10944 */         System.err.println("验证通过");
/* 10945 */         Long accessToken = Long.valueOf(header);
/* 10946 */         if ((phone == null) || (boUsers == null)) {
/* 10947 */           this.requestJson.setData(maps);
/* 10948 */           this.requestJson.setMessage("Invalid_User");
/* 10949 */           this.requestJson.setSuccess(true);
/*       */         }
/* 10951 */         else if (header4.equals(phone.getAccessToken())) {
/* 10952 */           if (accessToken.longValue() < Long.valueOf(phone.getAccessTokenTime()).longValue()) {
/* 10953 */             BoHostDevice device = this.boHostDeviceService.findBydeviceAddress(userCode2[0].trim().toString(), this.deviceAddress);
/* 10954 */             BoSensor boSensor = this.boSensorService.findBydeviceAddress(userCode2[0].trim().toString(), this.deviceAddress);
/* 10955 */             if (device != null)
/*       */             {
/* 10957 */               if (Integer.valueOf(device.getDeviceType()).intValue() <= 99) {
/* 10958 */                 this.requestJson.setData(maps);
/* 10959 */                 device.setBoRoom(null);
/* 10960 */                 device.setNickName("");
/* 10961 */                 device.setDeviceClassify(this.fid);
/* 10962 */                 BoHostDevice update = (BoHostDevice)this.boHostDeviceService.update(device);
/* 10963 */                 if (update != null) {
/* 10964 */                   this.requestJson.setMessage("删除成功");
/* 10965 */                   this.requestJson.setSuccess(true);
/*       */                 } else {
/* 10967 */                   this.requestJson.setMessage("删除失败");
/* 10968 */                   this.requestJson.setSuccess(false);
/*       */                 }
/*       */               } else {
/* 10971 */                 BoHostDevice delete = this.boHostDeviceService.delete(device);
/* 10972 */                 if (delete != null) {
/* 10973 */                   this.requestJson.setData(maps);
/* 10974 */                   this.requestJson.setMessage("删除成功");
/* 10975 */                   this.requestJson.setSuccess(true);
/*       */                 } else {
/* 10977 */                   this.requestJson.setData(maps);
/* 10978 */                   this.requestJson.setMessage("删除失败");
/* 10979 */                   this.requestJson.setSuccess(false);
/*       */                 }
/*       */               }
/*       */ 
/*       */             }
/*       */ 
/* 10985 */             if (boSensor != null) {
/* 10986 */               BoSensor delete = this.boSensorService.delete(boSensor);
/* 10987 */               if (delete != null) {
/* 10988 */                 this.requestJson.setData(maps);
/* 10989 */                 this.requestJson.setMessage("删除成功");
/* 10990 */                 this.requestJson.setSuccess(true);
/*       */               } else {
/* 10992 */                 this.requestJson.setData(maps);
/* 10993 */                 this.requestJson.setMessage("删除失败");
/* 10994 */                 this.requestJson.setSuccess(false);
/*       */               }
/*       */             }
/*       */           } else {
/* 10998 */             System.err.println("AToken时间戳超时了");
/* 10999 */             this.requestJson.setData(maps);
/* 11000 */             this.requestJson.setMessage("超时了");
/* 11001 */             this.requestJson.setSuccess(false);
/*       */           }
/*       */         } else {
/* 11004 */           System.err.println("AToken超时了");
/* 11005 */           this.requestJson.setData(maps);
/* 11006 */           this.requestJson.setMessage("超时了");
/* 11007 */           this.requestJson.setSuccess(false);
/*       */         }
/*       */ 
/*       */       }
/*       */       else
/*       */       {
/* 11013 */         this.requestJson.setData(maps);
/* 11014 */         System.err.println("验证不通过");
/* 11015 */         this.requestJson.setMessage("验证不通过");
/* 11016 */         this.requestJson.setSuccess(false);
/*       */       }
/*       */     } else {
/* 11019 */       Boolean ral = isRal(header, header2, header3, header4, userCode, "修改主机昵称");
/* 11020 */       if (ral.booleanValue()) {
/* 11021 */         System.err.println("验证通过");
/* 11022 */         Long accessToken = Long.valueOf(header);
/* 11023 */         BoUsers boUsers = this.boUserServicess.findByUserUserCode(userCode);
/* 11024 */         if (accessToken.longValue() < Long.valueOf(boUsers.getAccessTokenTime()).longValue()) {
/* 11025 */           if (boUsers == null) {
/* 11026 */             this.requestJson.setData(maps);
/* 11027 */             this.requestJson.setMessage("Invalid_User");
/* 11028 */             this.requestJson.setSuccess(true);
/*       */           } else {
/* 11030 */             BoHostDevice device = this.boHostDeviceService.findBydeviceAddress(userCode, this.deviceAddress);
/* 11031 */             BoSensor boSensor = this.boSensorService.findBydeviceAddress(userCode, this.deviceAddress);
/* 11032 */             if (device != null)
/*       */             {
/* 11034 */               if (Integer.valueOf(device.getDeviceType()).intValue() <= 99) {
/* 11035 */                 this.requestJson.setData(maps);
/* 11036 */                 device.setBoRoom(null);
/* 11037 */                 device.setNickName("");
/* 11038 */                 device.setDeviceClassify(this.fid);
/* 11039 */                 BoHostDevice update = (BoHostDevice)this.boHostDeviceService.update(device);
/* 11040 */                 if (update != null) {
/* 11041 */                   this.requestJson.setMessage("删除成功");
/* 11042 */                   this.requestJson.setSuccess(true);
/*       */                 } else {
/* 11044 */                   this.requestJson.setMessage("删除失败");
/* 11045 */                   this.requestJson.setSuccess(false);
/*       */                 }
/*       */               } else {
/* 11048 */                 BoHostDevice delete = this.boHostDeviceService.delete(device);
/* 11049 */                 if (delete != null) {
/* 11050 */                   this.requestJson.setMessage("删除成功");
/* 11051 */                   this.requestJson.setSuccess(true);
/*       */                 } else {
/* 11053 */                   this.requestJson.setMessage("删除失败");
/* 11054 */                   this.requestJson.setSuccess(false);
/*       */                 }
/*       */               }
/*       */ 
/*       */             }
/*       */ 
/* 11060 */             if (boSensor != null) {
/* 11061 */               BoSensor delete = this.boSensorService.delete(boSensor);
/* 11062 */               if (delete != null) {
/* 11063 */                 this.requestJson.setMessage("删除成功");
/* 11064 */                 this.requestJson.setSuccess(true);
/*       */               } else {
/* 11066 */                 this.requestJson.setMessage("删除失败");
/* 11067 */                 this.requestJson.setSuccess(false);
/*       */               }
/*       */             }
/*       */           }
/*       */         } else {
/* 11072 */           this.requestJson.setData(maps);
/* 11073 */           this.requestJson.setMessage("超时了");
/* 11074 */           this.requestJson.setSuccess(false);
/*       */         }
/*       */       }
/*       */       else {
/* 11078 */         this.requestJson.setData(maps);
/* 11079 */         System.err.println("验证不通过");
/* 11080 */         this.requestJson.setMessage("验证不通过");
/* 11081 */         this.requestJson.setSuccess(false);
/*       */       }
/*       */     }
/* 11084 */     return "success";
/*       */   }

/*       */   //删除房间接口
/*       */   @Action(value="deleteroom", results={@org.apache.struts2.convention.annotation.Result(type="json", params={"root", "requestJson"})})
/*       */   public String deleteRooms()
/*       */   {
/* 11095 */     this.requestJson = new RequestJson();
/* 11096 */     Map map = new HashMap();
/* 11097 */     HttpServletRequest request = ServletActionContext.getRequest();
/* 11098 */     String str = "";
/* 11099 */     Md5 md5 = new Md5();
/* 11100 */     String header = request.getHeader("timestamp");
/* 11101 */     String header2 = request.getHeader("nonce");
/* 11102 */     String header3 = request.getHeader("sign");
/* 11103 */     String header4 = request.getHeader("access_token");
/* 11104 */     String userCode = request.getHeader("userCode");
/*       */     List<BoHostDevice> list;
/* 11105 */     if (userCode.contains(",")) {
/* 11106 */       String[] userCode2 = userCode.split(",");
/* 11107 */       BoUsers boUsers = this.boUserServicess.findByUserUserCode(userCode2[0].trim().toString());
/* 11108 */       BoUsers phone = this.boUserServicess.findByUserPhone(userCode2[1].trim().toString());
/* 11109 */       Boolean ral = isRal(header, header2, header3, header4, userCode, "删除房间接口");
/* 11110 */       if (ral.booleanValue()) {
/* 11111 */         System.err.println("验证通过");
/* 11112 */         Long accessToken = Long.valueOf(header);
/* 11113 */         if ((phone == null) || (boUsers == null)) {
/* 11114 */           this.requestJson.setData(map);
/* 11115 */           this.requestJson.setMessage("Invalid_User");
/* 11116 */           this.requestJson.setSuccess(true);
/*       */         }
/* 11118 */         else if (header4.equals(phone.getAccessToken())) {
/* 11119 */           if (accessToken.longValue() < Long.valueOf(phone.getAccessTokenTime()).longValue()) {
/* 11120 */             list = this.boHostDeviceService.getByroomCode(this.roomCode);
/* 11121 */             for (BoHostDevice boHostDevice : list)
/* 11122 */               if (Integer.valueOf(boHostDevice.getDeviceType()).intValue() <= 99) {
/* 11123 */                 boHostDevice.setBoRoom(null);
/* 11124 */                 boHostDevice.setNickName("");
/* 11125 */                 boHostDevice.setDeviceClassify(this.fid);
/* 11126 */                 this.boHostDeviceService.update(boHostDevice);
/*       */               } else {
/* 11128 */                 this.boHostDeviceService.delete(boHostDevice);
/*       */               }
/*       */             try
/*       */             {
/* 11132 */               Thread.sleep(2000L);
/* 11133 */               BoRoom room = this.boRoomService.findByRommCode(this.roomCode);
/* 11134 */               if (room == null) {
/* 11135 */                 this.requestJson.setSuccess(false);
/*       */               } else {
/* 11137 */                 BoRoom delete = this.boRoomService.delete(room);
/* 11138 */                 if (delete != null) {
/* 11139 */                   this.requestJson.setData(map);
/* 11140 */                   this.requestJson.setMessage("删除成功");
/* 11141 */                   this.requestJson.setSuccess(true);
/*       */                 } else {
/* 11143 */                   this.requestJson.setData(map);
/* 11144 */                   this.requestJson.setMessage("删除失败");
/* 11145 */                   this.requestJson.setSuccess(false);
/*       */                 }
/*       */               }
/*       */             }
/*       */             catch (InterruptedException e) {
/* 11150 */               e.printStackTrace();
/*       */             }
/*       */           } else {
/* 11153 */             System.err.println("AToken时间戳超时了");
/* 11154 */             this.requestJson.setData(map);
/* 11155 */             this.requestJson.setMessage("超时了");
/* 11156 */             this.requestJson.setSuccess(false);
/*       */           }
/*       */         } else {
/* 11159 */           System.err.println("AToken超时了");
/* 11160 */           this.requestJson.setData(map);
/* 11161 */           this.requestJson.setMessage("超时了");
/* 11162 */           this.requestJson.setSuccess(false);
/*       */         }
/*       */       }
/*       */       else
/*       */       {
/* 11167 */         System.err.println("验证不通过");
/* 11168 */         this.requestJson.setData(map);
/* 11169 */         this.requestJson.setMessage("验证不通过");
/* 11170 */         this.requestJson.setSuccess(false);
/*       */       }
/*       */     } else {
/* 11173 */       Boolean ral = isRal(header, header2, header3, header4, userCode, "修改主机昵称");
/* 11174 */       if (ral.booleanValue()) {
/* 11175 */         System.err.println("验证通过");
/* 11176 */         Long accessToken = Long.valueOf(header);
/* 11177 */         BoUsers boUsers = this.boUserServicess.findByUserUserCode(userCode);
/* 11178 */         if (accessToken.longValue() < Long.valueOf(boUsers.getAccessTokenTime()).longValue()) {
/* 11179 */           if (boUsers == null) {
/* 11180 */             this.requestJson.setData(map);
/* 11181 */             this.requestJson.setMessage("Invalid_User");
/* 11182 */             this.requestJson.setSuccess(true);
/*       */           } else {
/* 11184 */             list = this.boHostDeviceService.getByroomCode(this.roomCode);
/* 11185 */             for (BoHostDevice boHostDevice : list)
/* 11186 */               if (Integer.valueOf(boHostDevice.getDeviceType()).intValue() <= 99) {
/* 11187 */                 boHostDevice.setBoRoom(null);
/* 11188 */                 boHostDevice.setNickName("");
/* 11189 */                 boHostDevice.setDeviceClassify(this.fid);
/* 11190 */                 this.boHostDeviceService.update(boHostDevice);
/*       */               } else {
/* 11192 */                 this.boHostDeviceService.delete(boHostDevice);
/*       */               }
/*       */             try
/*       */             {
/* 11196 */               Thread.sleep(2000L);
/* 11197 */               BoRoom room = this.boRoomService.findByRommCode(this.roomCode);
/* 11198 */               if (room == null) {
/* 11199 */                 this.requestJson.setSuccess(false);
/*       */               } else {
/* 11201 */                 BoRoom delete = this.boRoomService.delete(room);
/* 11202 */                 if (delete != null) {
/* 11203 */                   this.requestJson.setData(map);
/* 11204 */                   this.requestJson.setMessage("删除成功");
/* 11205 */                   this.requestJson.setSuccess(true);
/*       */                 } else {
/* 11207 */                   this.requestJson.setData(map);
/* 11208 */                   this.requestJson.setMessage("删除失败");
/* 11209 */                   this.requestJson.setSuccess(false);
/*       */                 }
/*       */               }
/*       */             }
/*       */             catch (InterruptedException e) {
/* 11214 */               e.printStackTrace();
/*       */             }
/*       */           }
/*       */         }
/*       */         else {
/* 11219 */           this.requestJson.setData(map);
/* 11220 */           this.requestJson.setMessage("超时了");
/* 11221 */           this.requestJson.setSuccess(false);
/*       */         }
/*       */       } else {
/* 11224 */         System.err.println("验证不通过");
/* 11225 */         this.requestJson.setData(map);
/* 11226 */         this.requestJson.setMessage("验证不通过");
/* 11227 */         this.requestJson.setSuccess(false);
/*       */       }
/*       */     }
/* 11230 */     return "success";
/*       */   }
/*       */ 
/*       */   @Action(value="deletefloor", results={@org.apache.struts2.convention.annotation.Result(type="json", params={"root", "requestJson"})})
/*       */   public String deleteFloor()
/*       */   {
/* 11241 */     this.requestJson = new RequestJson();
/* 11242 */     Map map = new HashMap();
/* 11243 */     HttpServletRequest request = ServletActionContext.getRequest();
/* 11244 */     String header = request.getHeader("timestamp");
/* 11245 */     String header2 = request.getHeader("nonce");
/* 11246 */     String header3 = request.getHeader("sign");
/* 11247 */     String header4 = request.getHeader("access_token");
/* 11248 */     String userCode = request.getHeader("userCode");
/*       */     List<BoRoom> list;
/*       */     List<BoHostDevice> list2;
/* 11249 */     if (userCode.contains(",")) {
/* 11250 */       String[] userCode2 = userCode.split(",");
/* 11251 */       BoUsers boUsers = this.boUserServicess.findByUserUserCode(userCode2[0].trim().toString());
/* 11252 */       BoUsers phone = this.boUserServicess.findByUserPhone(userCode2[1].trim().toString());
/* 11253 */       Boolean ral = isRal(header, header2, header3, header4, userCode, "删除楼层接口");
/* 11254 */       if (ral.booleanValue()) {
/* 11255 */         System.err.println("验证通过");
/* 11256 */         Long accessToken = Long.valueOf(header);
/* 11257 */         if ((phone == null) || (boUsers == null)) {
/* 11258 */           this.requestJson.setData(map);
/* 11259 */           this.requestJson.setMessage("Invalid_User");
/* 11260 */           this.requestJson.setSuccess(true);
/*       */         }
/* 11262 */         else if (header4.equals(phone.getAccessToken())) {
/* 11263 */           if (accessToken.longValue() < Long.valueOf(phone.getAccessTokenTime()).longValue()) {
/* 11264 */             BoFloor boFloor = this.boFloorService.findByFloorCode(this.floorCode);
/* 11265 */             if (boFloor == null) {
/* 11266 */               this.requestJson.setData(map);
/* 11267 */               this.requestJson.setMessage("没有找到该楼层");
/* 11268 */               this.requestJson.setSuccess(true);
/*       */             } else {
/* 11270 */               this.boFloorService.delete(boFloor);
/* 11271 */               list = this.boRoomService.getAllListByFloorCode(this.floorCode);
/* 11272 */               if (list.size() > 0)
/* 11273 */                 for (BoRoom boRoom : list) {
/* 11274 */                   list2 = this.boHostDeviceService.getByroomCode(boRoom.getRoomCode());
/* 11275 */                   if (list2.size() > 0) {
/* 11276 */                     for (BoHostDevice boHostDevice : list2)
/* 11277 */                       if (Integer.valueOf(boHostDevice.getDeviceType()).intValue() <= 99) {
/* 11278 */                         boHostDevice.setBoRoom(null);
/* 11279 */                         boHostDevice.setNickName("");
/* 11280 */                         boHostDevice.setDeviceClassify(this.fid);
/* 11281 */                         this.boHostDeviceService.update(boHostDevice);
/*       */                       } else {
/* 11283 */                         this.boHostDeviceService.delete(boHostDevice);
/*       */                       }
/*       */                   }
/*       */                   try
/*       */                   {
/* 11288 */                     Thread.sleep(2000L);
/* 11289 */                     BoRoom delete = this.boRoomService.delete(boRoom);
/* 11290 */                     if (delete != null) {
/* 11291 */                       this.requestJson.setData(map);
/* 11292 */                       this.requestJson.setMessage("删除成功");
/* 11293 */                       this.requestJson.setSuccess(true);
/*       */                     } else {
/* 11295 */                       this.requestJson.setData(map);
/* 11296 */                       this.requestJson.setMessage("删除失败");
/* 11297 */                       this.requestJson.setSuccess(true);
/*       */                     }
/*       */                   }
/*       */                   catch (InterruptedException e) {
/* 11301 */                     e.printStackTrace();
/*       */                   }
/*       */                 }
/*       */             }
/*       */           }
/*       */           else
/*       */           {
/* 11308 */             System.err.println("AToken时间戳超时了");
/* 11309 */             this.requestJson.setData(map);
/* 11310 */             this.requestJson.setMessage("超时了");
/* 11311 */             this.requestJson.setSuccess(false);
/*       */           }
/*       */         } else {
/* 11314 */           System.err.println("AToken超时了");
/* 11315 */           this.requestJson.setData(map);
/* 11316 */           this.requestJson.setMessage("超时了");
/* 11317 */           this.requestJson.setSuccess(false);
/*       */         }
/*       */       }
/*       */       else
/*       */       {
/* 11322 */         System.err.println("验证不通过");
/* 11323 */         this.requestJson.setData(map);
/* 11324 */         this.requestJson.setMessage("验证不通过");
/* 11325 */         this.requestJson.setSuccess(false);
/*       */       }
/*       */     } else {
/* 11328 */       Boolean ral = isRal(header, header2, header3, header4, userCode, "修改主机昵称");
/* 11329 */       if (ral.booleanValue()) {
/* 11330 */         System.err.println("验证通过");
/* 11331 */         Long accessToken = Long.valueOf(header);
/* 11332 */         BoUsers boUsers = this.boUserServicess.findByUserUserCode(userCode);
/* 11333 */         if (accessToken.longValue() < Long.valueOf(boUsers.getAccessTokenTime()).longValue()) {
/* 11334 */           if (boUsers == null) {
/* 11335 */             this.requestJson.setData(map);
/* 11336 */             this.requestJson.setMessage("Invalid_User");
/* 11337 */             this.requestJson.setSuccess(true);
/*       */           } else {
/* 11339 */             BoFloor boFloor = this.boFloorService.findByFloorCode(this.floorCode);
/* 11340 */             if (boFloor == null) {
/* 11341 */               this.requestJson.setData(map);
/* 11342 */               this.requestJson.setMessage("没有找到该楼层");
/* 11343 */               this.requestJson.setSuccess(true);
/*       */             } else {
/* 11345 */               this.boFloorService.delete(boFloor);
/* 11346 */               list = this.boRoomService.getAllListByFloorCode(this.floorCode);
/* 11347 */               if (list.size() > 0)
/* 11348 */                 for (BoRoom boRoom : list) {
/* 11349 */                   list2 = this.boHostDeviceService.getByroomCode(boRoom.getRoomCode());
/* 11350 */                   if (list2.size() > 0) {
/* 11351 */                     for (BoHostDevice boHostDevice : list2)
/* 11352 */                       if (Integer.valueOf(boHostDevice.getDeviceType()).intValue() <= 99) {
/* 11353 */                         boHostDevice.setBoRoom(null);
/* 11354 */                         boHostDevice.setNickName("");
/* 11355 */                         boHostDevice.setDeviceClassify(this.fid);
/* 11356 */                         this.boHostDeviceService.update(boHostDevice);
/*       */                       } else {
/* 11358 */                         this.boHostDeviceService.delete(boHostDevice);
/*       */                       }
/*       */                   }
/*       */                   try
/*       */                   {
/* 11363 */                     Thread.sleep(2000L);
/* 11364 */                     BoRoom delete = this.boRoomService.delete(boRoom);
/* 11365 */                     if (delete != null) {
/* 11366 */                       this.requestJson.setData(map);
/* 11367 */                       this.requestJson.setMessage("删除成功");
/* 11368 */                       this.requestJson.setSuccess(true);
/*       */                     } else {
/* 11370 */                       this.requestJson.setData(map);
/* 11371 */                       this.requestJson.setMessage("删除失败");
/* 11372 */                       this.requestJson.setSuccess(true);
/*       */                     }
/*       */                   }
/*       */                   catch (InterruptedException e) {
/* 11376 */                     e.printStackTrace();
/*       */                   }
/*       */                 }
/*       */             }
/*       */           }
/*       */         }
/*       */         else
/*       */         {
/* 11384 */           this.requestJson.setData(map);
/* 11385 */           this.requestJson.setMessage("超时了");
/* 11386 */           this.requestJson.setSuccess(false);
/*       */         }
/*       */       } else {
/* 11389 */         System.err.println("验证不通过");
/* 11390 */         this.requestJson.setData(map);
/* 11391 */         this.requestJson.setMessage("验证不通过");
/* 11392 */         this.requestJson.setSuccess(false);
/*       */       }
/*       */     }
/* 11395 */     return "success";
/*       */   }
/*       */ 
/*       */   @Action(value="getallhost", results={@org.apache.struts2.convention.annotation.Result(type="json", params={"root", "requestJson"})})
/*       */   public String getAllHost()
/*       */   {
	 			
/* 11408 */     this.requestJson = new RequestJson();
/* 11409 */     Map maps = new HashMap();
/* 11410 */     HttpServletRequest request = ServletActionContext.getRequest();
/* 11411 */     String str = "";
/* 11412 */     Md5 md5 = new Md5();
/* 11413 */     String header = request.getHeader("timestamp");
/* 11414 */     String header2 = request.getHeader("nonce");
/* 11415 */     String header3 = request.getHeader("sign");
/* 11416 */     String header4 = request.getHeader("access_token");
/* 11417 */     String userCode = request.getHeader("userCode");
/*       */     Set voSet;
/* 11418 */     if (userCode.contains(",")) {
/* 11419 */       String[] userCode2 = userCode.split(",");
/* 11420 */       BoUsers boUsers = this.boUserServicess.findByUserUserCode(userCode2[0].trim().toString());
/* 11421 */       BoUsers phone = this.boUserServicess.findByUserPhone(userCode2[1].trim().toString());
/* 11422 */       Boolean ral = isRal(header, header2, header3, header4, userCode, "获取用户所有的主机接口");
/* 11423 */       if (ral.booleanValue()) {
/* 11424 */         System.err.println("验证通过");
/* 11425 */         Long accessToken = Long.valueOf(header);
/* 11426 */         if ((phone == null) || (boUsers == null)) {
/* 11427 */           this.requestJson.setData(maps);
/* 11428 */           this.requestJson.setMessage("Invalid_User");
/* 11429 */           this.requestJson.setSuccess(true);
/*       */         }
/* 11431 */         else if (header4.equals(phone.getAccessToken())) {
/* 11432 */           if (accessToken.longValue() < Long.valueOf(phone.getAccessTokenTime()).longValue()) {
/* 11433 */             List<BoUserDevices> list = this.boUserDevicesServicess.getListByDeviceCodes(userCode2[0].trim().toString());
/* 11434 */             if (list.size() > 0) {
/* 11435 */               List voList = new ArrayList();
/* 11436 */               voSet = new HashSet();
/* 11437 */               for (BoUserDevices boUserDevices : list) {
/* 11438 */                 Map map = new HashMap();
/* 11439 */                 Integer status = boUserDevices.getBoDevice().getStatus();
/* 11440 */                 map.put("deviceCode", boUserDevices.getBoDevice().getDeviceCode());
/* 11441 */                 map.put("nickName", boUserDevices.getNickName().toString());
/* 11442 */                 map.put("status", status.toString());
/* 11443 */                 voSet.add(map);
/*       */               }
/* 11445 */               this.requestJson.setData(voSet);
/*       */             } else {
/* 11447 */               this.requestJson.setData(maps);
/* 11448 */               this.requestJson.setMessage("没有找到主机");
/* 11449 */               this.requestJson.setSuccess(true);
/*       */             }
/*       */           } else {
/* 11452 */             System.err.println("AToken时间戳超时了");
/* 11453 */             this.requestJson.setMessage("超时了");
/* 11454 */             this.requestJson.setData(maps);
/* 11455 */             this.requestJson.setSuccess(false);
/*       */           }
/*       */         } else {
/* 11458 */           System.err.println("AToken超时了");
/* 11459 */           this.requestJson.setData(maps);
/* 11460 */           this.requestJson.setMessage("超时了");
/* 11461 */           this.requestJson.setSuccess(false);
/*       */         }
/*       */ 
/*       */       }
/*       */       else
/*       */       {
/* 11467 */         System.err.println("验证不通过");
/* 11468 */         this.requestJson.setData(maps);
/* 11469 */         this.requestJson.setMessage("验证不通过");
/* 11470 */         this.requestJson.setSuccess(false);
/*       */       }
/*       */     } else {
/* 11473 */       Boolean ral = isRal(header, header2, header3, header4, userCode, "修改主机昵称");
/* 11474 */       if (ral.booleanValue()) {
/* 11475 */         System.err.println("验证通过");
/* 11476 */         Long accessToken = Long.valueOf(header);
/* 11477 */         BoUsers boUsers = this.boUserServicess.findByUserUserCode(userCode);
/* 11478 */         if (accessToken.longValue() < Long.valueOf(boUsers.getAccessTokenTime()).longValue()) {
/* 11479 */           if (boUsers == null) {
/* 11480 */             this.requestJson.setData(maps);
/* 11481 */             this.requestJson.setMessage("Invalid_User");
/* 11482 */             this.requestJson.setSuccess(true);
/*       */           }
/* 11484 */           else if (boUsers == null) {
/* 11485 */             this.requestJson.setData(maps);
/* 11486 */             this.requestJson.setMessage("Invalid_User");
/* 11487 */             this.requestJson.setSuccess(true);
/*       */           } else {
/* 11489 */             List<BoUserDevices> list = this.boUserDevicesServicess.getListByDeviceCodes(userCode);
/* 11490 */             if (list.size() > 0) {
/* 11491 */               List voList = new ArrayList();
/* 11492 */               voSet = new HashSet();
/* 11493 */               for (BoUserDevices boUserDevices : list) {
/* 11494 */                 Map map = new HashMap();
/* 11495 */                 Integer status = boUserDevices.getBoDevice().getStatus();
/* 11496 */                 map.put("deviceCode", boUserDevices.getBoDevice().getDeviceCode());
/* 11497 */                 map.put("nickName", boUserDevices.getNickName().toString());
/* 11498 */                 map.put("status", status.toString());
/* 11499 */                 voSet.add(map);
/*       */               }
/* 11501 */               this.requestJson.setData(voSet);
/*       */             } else {
/* 11503 */               this.requestJson.setData(maps);
/* 11504 */               this.requestJson.setMessage("没有找到主机");
/* 11505 */               this.requestJson.setSuccess(true);
/*       */             }
/*       */           }
/*       */         }
/*       */         else {
/* 11510 */           this.requestJson.setMessage("超时了");
/* 11511 */           this.requestJson.setData(maps);
/* 11512 */           this.requestJson.setSuccess(false);
/*       */         }
/*       */       }
/*       */       else {
/* 11516 */         System.err.println("验证不通过");
/* 11517 */         this.requestJson.setData(maps);
/* 11518 */         this.requestJson.setMessage("验证不通过");
/* 11519 */         this.requestJson.setSuccess(false);
/*       */       }
/*       */     }
/* 11522 */     return "success";
/*       */   }
/*       */ 
/*       */   @Action(value="gaingoodsdetailedInfo", results={@org.apache.struts2.convention.annotation.Result(type="json", params={"root", "requestJson"})})
/*       */   public String gainGoodsDetailedInfo()
/*       */   {
/* 11532 */     this.requestJson = new RequestJson();
/* 11533 */     HttpServletRequest request = ServletActionContext.getRequest();
/* 11534 */     Map map = new HashMap();
/* 11535 */     String str = "";
/* 11536 */     Md5 md5 = new Md5();
/* 11537 */     String header = request.getHeader("timestamp");
/* 11538 */     String header2 = request.getHeader("nonce");
/* 11539 */     String header3 = request.getHeader("sign");
/* 11540 */     String header4 = request.getHeader("access_token");
/* 11541 */     String userCode = request.getHeader("userCode");
/*       */ 
/* 11543 */     System.err.println(">-- " + header2);
/*       */ 
/* 11546 */     str = str + "access_token=";
/* 11547 */     str = str + header4;
/*       */ 
/* 11549 */     str = str + "&nonce=";
/* 11550 */     str = str + header2;
/*       */ 
/* 11552 */     str = str + "&timestamp=";
/* 11553 */     str = str + header;
/*       */ 
/* 11555 */     str = str + "&userCode=";
/* 11556 */     str = str + userCode;
/*       */ 
/* 11558 */     str = str + "12345";
/* 11559 */     String sign = md5.getMD5ofStr(str).toLowerCase();
/* 11560 */     System.err.println("sign>-- " + sign);
/* 11561 */     String[] userCode2 = userCode.split(",");
/* 11562 */     BoUsers boUsers = this.boUserServicess.findByUserUserCode(userCode2[0].trim().toString());
/* 11563 */     BoUsers phone = this.boUserServicess.findByUserPhone(userCode2[1].trim().toString());
/* 11564 */     if (header3.equals(sign)) {
/* 11565 */       Long accessToken = Long.valueOf(header);
/*       */ 
/* 11567 */       if ((phone == null) || (boUsers == null)) {
/* 11568 */         this.requestJson.setData(map);
/* 11569 */         this.requestJson.setMessage("Invalid_User");
/* 11570 */         this.requestJson.setSuccess(true);
/*       */       }
/* 11572 */       else if (header4.equals(phone.getAccessToken())) {
/* 11573 */         if (accessToken.longValue() < Long.valueOf(phone.getAccessTokenTime()).longValue())
/*       */         {
/* 11575 */           BoGoods findById = this.boGoodsService.findById(this.id);
/* 11576 */           if (findById == null)
/*       */           {
/* 11578 */             this.requestJson.setData(map);
/* 11579 */             this.requestJson.setMessage("没有找到");
/* 11580 */             this.requestJson.setSuccess(false);
/*       */           }
/*       */           else {
/* 11583 */             map.put("id", findById.getId());
/* 11584 */             map.put("goodsColor", findById.getGoodsColor().toString());
/* 11585 */             map.put("goodsSize", findById.getGoodsSize().toString());
/* 11586 */             map.put("workingVoltage", findById.getWorkingVoltage().toString());
/* 11587 */             map.put("powerConsumption", findById.getPowerConsumption().toString());
/* 11588 */             map.put("materialGoods", findById.getMaterialGoods().toString());
/* 11589 */             map.put("communicatuinMode", findById.getCommunicatuinMode().toString());
/* 11590 */             map.put("workingTemperature", findById.getWorkingTemperature().toString());
/* 11591 */             map.put("workingHumidity", findById.getWorkingHumidity().toString());
/* 11592 */             map.put("goodsPrice", findById.getGoodsPrice().toString());
/* 11593 */             map.put("goodsTitle", findById.getGoodsTitle().toString());
/* 11594 */             this.requestJson.setData(map);
/* 11595 */             this.requestJson.setSuccess(true);
/*       */           }
/*       */         }
/*       */         else {
/* 11599 */           this.requestJson.setData(map);
/* 11600 */           this.requestJson.setMessage("超时了");
/* 11601 */           this.requestJson.setSuccess(false);
/*       */         }
/*       */       } else {
/* 11604 */         System.err.println("超时了");
/* 11605 */         this.requestJson.setData(map);
/* 11606 */         this.requestJson.setMessage("超时了");
/* 11607 */         this.requestJson.setSuccess(false);
/*       */       }
/*       */     }
/*       */     else {
/* 11611 */       this.requestJson.setData(map);
/* 11612 */       this.requestJson.setMessage("验证不通过");
/* 11613 */       this.requestJson.setSuccess(false);
/*       */     }
/* 11615 */     return "success";
/*       */   }
/*       */ 
/*       */   @Action(value="gaingoodslist", results={@org.apache.struts2.convention.annotation.Result(type="json", params={"root", "requestJson"})})
/*       */   public String gainGoodsList()
/*       */   {
/* 11623 */     this.requestJson = new RequestJson();
/* 11624 */     HttpServletRequest request = ServletActionContext.getRequest();
/* 11625 */     Map maps = new HashMap();
/* 11626 */     String str = "";
/* 11627 */     Md5 md5 = new Md5();
/* 11628 */     String header = request.getHeader("timestamp");
/* 11629 */     String header2 = request.getHeader("nonce");
/* 11630 */     String header3 = request.getHeader("sign");
/* 11631 */     String header4 = request.getHeader("access_token");
/* 11632 */     String userCode = request.getHeader("userCode");
/*       */ 
/* 11634 */     System.err.println(">-- " + header2);
/*       */ 
/* 11637 */     str = str + "access_token=";
/* 11638 */     str = str + header4;
/*       */ 
/* 11640 */     str = str + "&nonce=";
/* 11641 */     str = str + header2;
/*       */ 
/* 11643 */     str = str + "&timestamp=";
/* 11644 */     str = str + header;
/*       */ 
/* 11646 */     str = str + "&userCode=";
/* 11647 */     str = str + userCode;
/*       */ 
/* 11649 */     str = str + "12345";//有问题吗？
/* 11650 */     String sign = md5.getMD5ofStr(str).toLowerCase();
/* 11651 */     System.err.println("sign>-- " + sign);
/*       */     List<BoGoods> list;
/* 11652 */     if (userCode.contains(",")) {
/* 11653 */       String[] userCode2 = userCode.split(",");
/* 11654 */       BoUsers boUsers = this.boUserServicess.findByUserUserCode(userCode2[0].trim().toString());
/* 11655 */       BoUsers phone = this.boUserServicess.findByUserPhone(userCode2[1].trim().toString());
/* 11656 */       Boolean ral = isRal(header, header2, header3, header4, userCode, "商品展示");
/* 11657 */       if (header3.equals(sign)) {
/* 11658 */         System.out.println("验证通过");
/* 11659 */         Long accessToken = Long.valueOf(header);
/*       */ 
/* 11661 */         if ((phone == null) || (boUsers == null)) {
/* 11662 */           this.requestJson.setData(maps);
/* 11663 */           this.requestJson.setMessage("Invalid_User");
/* 11664 */           this.requestJson.setSuccess(true);
/*       */         } else {
/* 11666 */           System.err.println("手机 " + accessToken);
/* 11667 */           System.err.println("服务器 " + phone.getAccessTokenTime());
/* 11668 */           if (header4.equals(phone.getAccessToken())) {
/* 11669 */             if (accessToken.longValue() < Long.valueOf(phone.getAccessTokenTime()).longValue()) {
/* 11670 */               System.err.println("没有超时");
/* 11671 */               list = this.boGoodsService.getList();
/* 11672 */               if (list != null) {
/* 11673 */                 List voList = new ArrayList();
/* 11674 */                 for (BoGoods boGoods : list) {
/* 11675 */                   Map map = new HashMap();
/* 11676 */                   map.put("id", boGoods.getId());
/* 11677 */                   map.put("goodsTitle", boGoods.getGoodsTitle().toString());
/* 11678 */                   map.put("goodsIntroduce", boGoods.getGoodsIntroduce().toString());
/* 11679 */                   map.put("salesVolumeDegree", boGoods.getSalesVolumeDegree().toString());
/* 11680 */                   map.put("goodsPrice", boGoods.getGoodsPrice().toString());
/* 11681 */                   map.put("picturesShow", boGoods.getPicturesShow().toString());
/* 11682 */                   voList.add(map);
/*       */                 }
/* 11684 */                 this.requestJson.setData(voList);
/* 11685 */                 this.requestJson.setSuccess(true);
/*       */               }
/*       */             }
/*       */             else {
/* 11689 */               System.err.println("AToken时间戳超时了");
/* 11690 */               this.requestJson.setMessage("超时了");
/* 11691 */               this.requestJson.setSuccess(false);
/*       */             }
/*       */           } else {
/* 11694 */             System.err.println("AToken超时了");
/* 11695 */             this.requestJson.setMessage("超时了");
/* 11696 */             this.requestJson.setSuccess(false);
/*       */           }
/*       */         }
/*       */       } else {
/* 11700 */         this.requestJson.setMessage("验证不通过");
/* 11701 */         this.requestJson.setSuccess(false);
/*       */       }
/*       */     }
/* 11704 */     else if (header3.equals(sign)) {
/* 11705 */       System.out.println("验证通过");
/* 11706 */       Long accessToken = Long.valueOf(header);
/* 11707 */       BoUsers boUsers = this.boUserServicess.findByUserUserCode(userCode);
/* 11708 */       if (boUsers == null) {
/* 11709 */         this.requestJson.setData(maps);
/* 11710 */         this.requestJson.setMessage("Invalid_User");
/* 11711 */         this.requestJson.setSuccess(true);
/*       */       } else {
/* 11713 */         System.err.println("手机 " + accessToken);
/* 11714 */         System.err.println("服务器 " + boUsers.getAccessTokenTime());
/* 11715 */         if (accessToken.longValue() < Long.valueOf(boUsers.getAccessTokenTime()).longValue()) {
/* 11716 */           System.err.println("没有超时");
/* 11717 */           list = this.boGoodsService.getList();
/* 11718 */           if (list != null) {
/* 11719 */             List voList = new ArrayList();
/* 11720 */             for (BoGoods boGoods : list) {
/* 11721 */               Map map = new HashMap();
/* 11722 */               map.put("id", boGoods.getId());
/* 11723 */               map.put("goodsTitle", boGoods.getGoodsTitle().toString());
/* 11724 */               map.put("goodsIntroduce", boGoods.getGoodsIntroduce().toString());
/* 11725 */               map.put("salesVolumeDegree", boGoods.getSalesVolumeDegree().toString());
/* 11726 */               map.put("goodsPrice", boGoods.getGoodsPrice().toString());
/* 11727 */               map.put("picturesShow", boGoods.getPicturesShow().toString());
/* 11728 */               voList.add(map);
/*       */             }
/* 11730 */             this.requestJson.setData(voList);
/* 11731 */             this.requestJson.setSuccess(true);
/*       */           }
/*       */         }
/*       */         else {
/* 11735 */           this.requestJson.setMessage("超时了");
/* 11736 */           this.requestJson.setSuccess(false);
/*       */         }
/*       */       }
/*       */     } else {
/* 11740 */       this.requestJson.setMessage("验证不通过");
/* 11741 */       this.requestJson.setSuccess(false);
/*       */     }
/*       */ 
/* 11744 */     return "success";
/*       */   }
/*       */ 
/*       */   @Action(value="queryDbDeviceState", results={@org.apache.struts2.convention.annotation.Result(type="json", params={"root", "requestJson"})})
/*       */   public String queryDbDeviceState()
/*       */     throws InterruptedException
/*       */   {
/* 11757 */     this.requestJson = new RequestJson();
/* 11758 */     Map map1 = new HashMap();
/* 11759 */     HttpServletRequest request = ServletActionContext.getRequest();
/* 11760 */     String header = request.getHeader("timestamp");
/* 11761 */     String header2 = request.getHeader("nonce");
/* 11762 */     String header3 = request.getHeader("sign");
/* 11763 */     String header4 = request.getHeader("access_token");
/* 11764 */     String userCode = request.getHeader("userCode");
/*       */     List voList;
/* 11765 */     if (userCode.contains(",")) {
/* 11766 */       String[] userCode2 = userCode.split(",");
/* 11767 */       BoUsers boUsers = this.boUserServicess.findByUserUserCode(userCode2[0].trim().toString());
/* 11768 */       BoUsers phone = this.boUserServicess.findByUserPhone(userCode2[1].trim().toString());
/* 11769 */       Boolean ral = isRal(header, header2, header3, header4, userCode, "查询房间设备");
/* 11770 */       if (ral.booleanValue()) {
/* 11771 */         System.err.println("验证通过");
/* 11772 */         Long accessToken = Long.valueOf(header);
/* 11773 */         if ((phone == null) || (boUsers == null)) {
/* 11774 */           this.requestJson.setData(map1);
/* 11775 */           this.requestJson.setMessage("Invalid_User");
/* 11776 */           this.requestJson.setSuccess(true);
/*       */         } else {
/*       */           try {
/* 11779 */             List<BoHostDevice> getroomCode = this.boHostDeviceService.getroomCode(this.roomCode);
/* 11780 */             if (getroomCode.size() > 0) {
/* 11781 */               voList = new ArrayList();
/*       */ 
/* 11783 */               for (BoHostDevice boHostDevice : getroomCode) {
/* 11784 */                 System.err.println("boHostDevice.getDeviceAddress() " + boHostDevice.getDeviceAddress());
/* 11785 */                 System.err.println("boHostDevice.getState() " + boHostDevice.getState());
/* 11786 */                 Map map = new HashMap();
/* 11787 */                 if (boHostDevice.getDeviceType().equals("1")) {
/* 11788 */                   map.put("deviceAddress", boHostDevice.getDeviceAddress());
/* 11789 */                   String state = "";
/* 11790 */                   if (boHostDevice.getState().equals("1"))
/* 11791 */                     state = "100";
/*       */                   else {
/* 11793 */                     state = boHostDevice.getState().toString();
/*       */                   }
/* 11795 */                   map.put("state", state);
/*       */                 } else {
/* 11797 */                   map.put("deviceAddress", boHostDevice.getDeviceAddress());
/* 11798 */                   map.put("state", boHostDevice.getState().toString());
/*       */                 }
/* 11800 */                 voList.add(map);
/*       */               }
/*       */ 
/* 11804 */               this.requestJson = new RequestJson(true, "", voList);
/*       */             } else {
/* 11806 */               this.requestJson.setData(map1);
/* 11807 */               this.requestJson.setMessage("该房间没有设备");
/* 11808 */               this.requestJson.setSuccess(true);
/*       */             }
/*       */           }
/*       */           catch (Exception e) {
/* 11812 */             logger.info("error" + e.getMessage());
/* 11813 */             this.requestJson.setData(map1);
/* 11814 */             this.requestJson.setMessage("服务器发生异常");
/* 11815 */             this.requestJson.setSuccess(false);
/*       */           }
/*       */         }
/*       */       } else {
/* 11819 */         System.err.println("验证不通过");
/* 11820 */         this.requestJson.setData(map1);
/* 11821 */         this.requestJson.setMessage("验证不通过");
/* 11822 */         this.requestJson.setSuccess(false);
/*       */       }
/*       */     } else {
/* 11825 */       Boolean ral = isRal(header, header2, header3, header4, userCode, "查询房间设备");
/* 11826 */       if (ral.booleanValue()) {
/* 11827 */         System.err.println("验证通过");
/* 11828 */         Long accessToken = Long.valueOf(header);
/* 11829 */         BoUsers boUsers = this.boUserServicess.findByUserUserCode(userCode);
/* 11830 */         if (boUsers == null) {
/* 11831 */           this.requestJson.setData(map1);
/* 11832 */           this.requestJson.setMessage("Invalid_User");
/* 11833 */           this.requestJson.setSuccess(true);
/*       */         } else {
/*       */           try {
/* 11836 */             List<BoHostDevice> getroomCode = this.boHostDeviceService.getroomCode(this.roomCode);
/* 11837 */             if (getroomCode.size() > 0) {
/* 11838 */               voList = new ArrayList();
/*       */ 
/* 11840 */               for (BoHostDevice boHostDevice : getroomCode) {
/* 11841 */                 System.err.println("boHostDevice.getDeviceAddress() " + boHostDevice.getDeviceAddress());
/* 11842 */                 System.err.println("boHostDevice.getState() " + boHostDevice.getState());
/* 11843 */                 Map map = new HashMap();
/* 11844 */                 if (boHostDevice.getDeviceType().equals("1")) {
/* 11845 */                   map.put("deviceAddress", boHostDevice.getDeviceAddress());
/* 11846 */                   String state = "";
/* 11847 */                   if (boHostDevice.getState().equals("1"))
/* 11848 */                     state = "100";
/*       */                   else {
/* 11850 */                     state = boHostDevice.getState().toString();
/*       */                   }
/* 11852 */                   map.put("state", state);
/*       */                 } else {
/* 11854 */                   map.put("deviceAddress", boHostDevice.getDeviceAddress());
/* 11855 */                   map.put("state", boHostDevice.getState().toString());
/*       */                 }
/* 11857 */                 voList.add(map);
/*       */               }
/*       */ 
/* 11861 */               this.requestJson = new RequestJson(true, "", voList);
/*       */             } else {
/* 11863 */               this.requestJson.setData(map1);
/* 11864 */               this.requestJson.setMessage("该房间没有设备");
/* 11865 */               this.requestJson.setSuccess(true);
/*       */             }
/*       */           }
/*       */           catch (Exception e) {
/* 11869 */             logger.info("error" + e.getMessage());
/* 11870 */             this.requestJson.setData(map1);
/* 11871 */             this.requestJson.setMessage("服务器发生异常");
/* 11872 */             this.requestJson.setSuccess(false);
/*       */           }
/*       */         }
/*       */       } else {
/* 11876 */         System.err.println("验证不通过");
/* 11877 */         this.requestJson.setData(map1);
/* 11878 */         this.requestJson.setMessage("验证不通过");
/* 11879 */         this.requestJson.setSuccess(false);
/*       */       }
/*       */     }
/* 11882 */     return "success";
/*       */   }
/*       */ 
/*       */   @Action(value="queryDeviceState", results={@org.apache.struts2.convention.annotation.Result(type="json", params={"root", "requestJson"})})
/*       */   public String queryDeviceState()
/*       */     throws InterruptedException
/*       */   {
/* 11894 */     Map map1 = new HashMap();
/* 11895 */     HttpServletRequest request = ServletActionContext.getRequest();
/* 11896 */     String header = request.getHeader("timestamp");
/* 11897 */     String header2 = request.getHeader("nonce");
/* 11898 */     String header3 = request.getHeader("sign");
/* 11899 */     String header4 = request.getHeader("access_token");
/* 11900 */     String userCode = request.getHeader("userCode");
/* 11901 */     if (userCode.contains(",")) {
/* 11902 */       String[] userCode2 = userCode.split(",");
/* 11903 */       BoUsers boUsers = this.boUserServicess.findByUserUserCode(userCode2[0].trim().toString());
/* 11904 */       BoUsers phone = this.boUserServicess.findByUserPhone(userCode2[1].trim().toString());
/* 11905 */       Boolean ral = isRal(header, header2, header3, header4, userCode, "查询房间设备 向底层查询存数据库");
/* 11906 */       if (ral.booleanValue()) {
/* 11907 */         System.err.println("验证通过");
/* 11908 */         Long accessToken = Long.valueOf(header);
/* 11909 */         if ((phone == null) || (boUsers == null)) {
/* 11910 */           this.requestJson.setData(map1);
/* 11911 */           this.requestJson.setMessage("Invalid_User");
/* 11912 */           this.requestJson.setSuccess(true);
/*       */         } else {
/*       */           try {

/* 11915 */             Map deviceAddressMap = new HashMap();
//						List<String> keys=(List<String>) deviceAddressMap.keySet();//Set can't cast to List 
					    List<String> keys = new ArrayList<String>(deviceAddressMap.keySet());
						//Set 转    List
//						Set<String> keys0 = deviceAddressMap.keySet();
//						List<String> keys = new ArrayList<>(keys0);
//						System.out.println("keys11580:"+keys);   //报错：java.util.HashMap$KeySet cannot be cast to java.util.List
						
/* 11916 */             List getroomCode = this.boHostDeviceService.getroomCode(this.roomCode);
/*       */             BoHostDevice boHostDevice;
/* 11917 */             for (int i = 0; i < getroomCode.size(); i++) {
/* 11918 */               boHostDevice = (BoHostDevice)getroomCode.get(i);
/* 11919 */               if (boHostDevice.getDeviceType().equals("1")) {
/* 11920 */                 String deviceAddress = boHostDevice.getDeviceAddress().substring(0, boHostDevice.getDeviceAddress().length() - 1);
/* 11921 */                 deviceAddressMap.put(deviceAddress, deviceAddress + "," + boHostDevice.getDeviceType() + "," + boHostDevice.getBoDevice().getDeviceCode());
/*       */               } else {
/* 11923 */                 deviceAddressMap.put(boHostDevice.getDeviceAddress(), boHostDevice.getDeviceAddress() + "," + boHostDevice.getDeviceType() + "," + boHostDevice.getBoDevice().getDeviceCode());
/*       */               }
/*       */             }
/*       */ 
/* 11927 */             for (String key : keys) {
/* 11928 */               packNum(userCode2[0].trim().toString());
/* 11929 */               Thread.sleep(400L);
/*       */ 
/* 11931 */               String[] split = ((String)deviceAddressMap.get(key)).split(",");
/* 11932 */               if (split[1].equals("1")) {
/* 11933 */                 System.err.println("双向灯");//门锁 有经过？
/* 11934 */                 String str = "ZIGBEE_LIGHT-READ-" + user_num.get(userCode2[0].trim().toString()) + "," + split[0];
/* 11935 */                 byte[] bs = str.getBytes();
/* 11936 */                 System.err.println(new String(bs));
/* 11937 */                 this.packetProcessHelper.processSendDData(split[2], bs);
/* 11938 */                 StaticUtil.QUERYSTATE.put(split[2] + "_" + "A", new String[] { 
/* 11939 */                   userCode2[0].trim().toString(), new Date().getTime()+"" });
/* 11940 */               } else if (split[1].equals("2")) {
/* 11941 */                 System.err.println("窗帘");
/* 11942 */                 String str = "ZIGBEE_CURTAIN-READ-" + user_num.get(userCode2[0].trim().toString()) + "," + 
/* 11943 */                   split[0] + "," + "21";
/* 11944 */                 byte[] bs = str.getBytes();
/* 11945 */                 System.err.println(new String(bs));
/* 11946 */                 this.packetProcessHelper.processSendDData(split[2], bs);
/* 11947 */                 StaticUtil.CURTAIN.put(split[2] + "_" + "A", new String[] { 
/* 11948 */                   userCode2[0].trim().toString(), new Date().getTime()+"" });
/* 11949 */               } else if (split[1].equals("4")) {
/* 11950 */                 System.err.println("调关灯");
/* 11951 */                 String str = "ZIGBEE_DIMMER-READ-" + user_num.get(userCode2[0].trim().toString()) + "," + split[0];
/* 11952 */                 byte[] bs = str.getBytes();
/* 11953 */                 System.err.println(new String(bs));
/* 11954 */                 this.packetProcessHelper.processSendDData(split[2], bs);
/* 11955 */                 StaticUtil.DIMMINGLIGHT.put(split[2] + "_" + "A", new String[] { 
/* 11956 */                   userCode2[0].trim().toString(), new Date().getTime()+"" });
/*       */               }
/* 11958 */               System.err.println(key + "=" + (String)deviceAddressMap.get(key));
/*       */             }
/* 11960 */             Thread.sleep(800L);
/* 11961 */             this.requestJson.setData(map1);
/* 11962 */             this.requestJson.setMessage("OK");
/* 11963 */             this.requestJson.setSuccess(true);
/*       */           }
/*       */           catch (Exception e) {
/* 11966 */             logger.info("error" + e.getMessage());
/* 11967 */             this.requestJson.setData(map1);
/* 11968 */             this.requestJson.setMessage("服务器发生异常");
/* 11969 */             this.requestJson.setSuccess(false);
/*       */           }
/*       */         }
/*       */       }
/*       */       else {
/* 11974 */         System.err.println("验证不通过");
/* 11975 */         this.requestJson.setData(map1);
/* 11976 */         this.requestJson.setMessage("验证不通过");
/* 11977 */         this.requestJson.setSuccess(false);
/*       */       }
/*       */     } else {
/* 11980 */       Boolean ral = isRal(header, header2, header3, header4, userCode, "查询房间设备");
/* 11981 */       if (ral.booleanValue()) {
/* 11982 */         System.err.println("验证通过");
/* 11983 */         Long accessToken = Long.valueOf(header);
/* 11984 */         BoUsers boUsers = this.boUserServicess.findByUserUserCode(userCode);
/* 11985 */         if (boUsers == null) {
/* 11986 */           this.requestJson.setData(map1);
/* 11987 */           this.requestJson.setMessage("Invalid_User");
/* 11988 */           this.requestJson.setSuccess(true);
/*       */         } else {
/*       */           try {

/* 11915 */             Map deviceAddressMap = new HashMap();
						//List<String> keys=(List<String>) deviceAddressMap.keySet();//Set can't cast to List 
						List<String> keys = new ArrayList<String>(deviceAddressMap.keySet());
						//Set 转    List
//						Set<String> keys0 = deviceAddressMap.keySet();
//						List<String> keys = new ArrayList<>(keys0);
						
/* 11992 */             List getroomCode = this.boHostDeviceService.getroomCode(this.roomCode);
/*       */             BoHostDevice boHostDevice;
/* 11993 */             for (int i = 0; i < getroomCode.size(); i++) {
/* 11994 */               boHostDevice = (BoHostDevice)getroomCode.get(i);
/* 11995 */               if (boHostDevice.getDeviceType().equals("1")) {
/* 11996 */                 String deviceAddress = boHostDevice.getDeviceAddress().substring(0, boHostDevice.getDeviceAddress().length() - 1);
/* 11997 */                 deviceAddressMap.put(deviceAddress, deviceAddress + "," + boHostDevice.getDeviceType() + "," + boHostDevice.getBoDevice().getDeviceCode());
/*       */               } else {
/* 11999 */                 deviceAddressMap.put(boHostDevice.getDeviceAddress(), boHostDevice.getDeviceAddress() + "," + boHostDevice.getDeviceType() + "," + boHostDevice.getBoDevice().getDeviceCode());
/*       */               }
/*       */             }
/*       */ 
/* 12003 */             for (String key : keys) {
/* 12004 */               packNum(userCode);
/* 12005 */               Thread.sleep(400L);
/*       */ 
/* 12007 */               String[] split = ((String)deviceAddressMap.get(key)).split(",");
/* 12008 */               if (split[1].equals("1")) {
/* 12009 */                 System.err.println("双向灯");
/* 12010 */                 String str = "ZIGBEE_LIGHT-READ-" + user_num.get(userCode) + "," + split[0];
/* 12011 */                 byte[] bs = str.getBytes();
/* 12012 */                 System.err.println(new String(bs));
/* 12013 */                 this.packetProcessHelper.processSendDData(split[2], bs);
/* 12014 */                 StaticUtil.QUERYSTATE.put(split[2] + "_" + "A", new String[] { 
/* 12015 */                   userCode, new Date().getTime()+"" });
/* 12016 */               } else if (split[1].equals("2")) {
/* 12017 */                 System.err.println("窗帘");
/* 12018 */                 String str = "ZIGBEE_CURTAIN-READ-" + user_num.get(userCode) + "," + 
/* 12019 */                   split[0] + "," + "21";
/* 12020 */                 byte[] bs = str.getBytes();
/* 12021 */                 System.err.println(new String(bs));
/* 12022 */                 this.packetProcessHelper.processSendDData(split[2], bs);
/* 12023 */                 StaticUtil.CURTAIN.put(split[2] + "_" + "A", new String[] { 
/* 12024 */                   userCode, new Date().getTime()+"" });
/* 12025 */               } else if (split[1].equals("4")) {
/* 12026 */                 System.err.println("调关灯");
/* 12027 */                 String str = "ZIGBEE_DIMMER-READ-" + user_num.get(userCode) + "," + split[0];
/* 12028 */                 byte[] bs = str.getBytes();
/* 12029 */                 System.err.println(new String(bs));
/* 12030 */                 this.packetProcessHelper.processSendDData(split[2], bs);
/* 12031 */                 StaticUtil.DIMMINGLIGHT.put(split[2] + "_" + "A", new String[] { 
/* 12032 */                   userCode, new Date().getTime()+"" });
/*       */               }
/* 12034 */               System.err.println(key + "=" + (String)deviceAddressMap.get(key));
/*       */             }
/* 12036 */             Thread.sleep(800L);
/* 12037 */             this.requestJson.setData(map1);
/* 12038 */             this.requestJson.setMessage("OK");
/* 12039 */             this.requestJson.setSuccess(true);
/*       */           }
/*       */           catch (Exception e) {
/* 12042 */             logger.info("error" + e.getMessage());
/* 12043 */             this.requestJson.setData(map1);
/* 12044 */             this.requestJson.setMessage("服务器发生异常");
/* 12045 */             this.requestJson.setSuccess(false);
/*       */           }
/*       */         }
/*       */       }
/*       */       else {
/* 12050 */         System.err.println("验证不通过");
/* 12051 */         this.requestJson.setData(map1);
/* 12052 */         this.requestJson.setMessage("验证不通过");
/* 12053 */         this.requestJson.setSuccess(false);
/*       */       }
/*       */     }
/* 12056 */     return "success";
/*       */   }
/*       */ 
/*       */   @Action(value="queryroomdevicestate", results={@org.apache.struts2.convention.annotation.Result(type="json", params={"root", "requestJson"})})
/*       */   public String queryRoomDeviceState()
/*       */   {
/* 12069 */     this.requestJson = new RequestJson();
/* 12070 */     Map map1 = new HashMap();
/* 12071 */     HttpServletRequest request = ServletActionContext.getRequest();
/* 12072 */     String header = request.getHeader("timestamp");
/* 12073 */     String header2 = request.getHeader("nonce");
/* 12074 */     String header3 = request.getHeader("sign");
/* 12075 */     String header4 = request.getHeader("access_token");
/* 12076 */     String userCode = request.getHeader("userCode");
/*       */     List voList;
/* 12077 */     if (userCode.contains(",")) {
/* 12078 */       String[] userCode2 = userCode.split(",");
/* 12079 */       BoUsers boUsers = this.boUserServicess.findByUserUserCode(userCode2[0].trim().toString());
/* 12080 */       BoUsers phone = this.boUserServicess.findByUserPhone(userCode2[1].trim().toString());
/* 12081 */       Boolean ral = isRal(header, header2, header3, header4, userCode, "查询房间设备状态");
/* 12082 */       if (ral.booleanValue()) {
/* 12083 */         System.err.println("验证通过");
/* 12084 */         Long accessToken = Long.valueOf(header);
/* 12085 */         if ((phone == null) || (boUsers == null)) {
/* 12086 */           this.requestJson.setData(map1);
/* 12087 */           this.requestJson.setMessage("没有找到该编号");
/* 12088 */           this.requestJson.setSuccess(true);
/*       */         }
/* 12090 */         else if (header4.equals(phone.getAccessToken())) {
/* 12091 */           if (accessToken.longValue() < Long.valueOf(phone.getAccessTokenTime()).longValue()) {
/*       */             try {
/* 12093 */               List<BoHostDevice> getroomCode = this.boHostDeviceService.getroomCode(this.roomCode);
/* 12094 */               System.err.println("roomCode" + this.roomCode);
/*       */ 
/* 12096 */               if (getroomCode.size() > 0) {
/* 12097 */                 voList = new ArrayList();
/* 12098 */                 for (BoHostDevice boHostDevice : getroomCode) { 
	                            List<BoDeviceState> list = this.boDeviceStateService
/* 12100 */                     .getBydeviceAddress(userCode2[0].trim().toString(), boHostDevice.getDeviceAddress());
/*       */ 
/* 12102 */                   packNum(userCode2[0].trim().toString());
/*       */                   BoHostDevice obj;
/*       */                   try { List list2 = this.boHostDeviceService
/* 12106 */                       .getListBy(userCode2[0].trim().toString(), boHostDevice.getDeviceAddress());
/* 12107 */                     for (int i = 0; i < list2.size(); i++) {
/* 12108 */                       Thread.sleep(200L);
/* 12109 */                       obj = (BoHostDevice)list2.get(i);
/* 12110 */                       packNum(userCode2[0].trim().toString());
/* 12111 */                       BoHostDevice device = this.boHostDeviceService
/* 12112 */                         .findBydeviceAddress(obj.getBoUsers().getUserCode(), obj.getDeviceAddress());
/*       */ 
/* 12114 */                       if (device.getDeviceType().equals("1")) {
/* 12115 */                         String substring = obj.getDeviceAddress().substring(0, obj.getDeviceAddress().length() - 1);
/* 12116 */                         String str = "ZIGBEE_LIGHT-READ-" + user_num.get(userCode2[0].trim().toString()) + "," + substring;
/* 12117 */                         byte[] bs = str.getBytes();
/* 12118 */                         System.err.println(new String(bs));
/* 12119 */                         this.packetProcessHelper.processSendDData(device.getBoDevice().getDeviceCode(), bs);
/* 12120 */                         StaticUtil.QUERYSTATE.put(device.getBoDevice().getDeviceCode() + "_" + "A", new String[] { 
/* 12121 */                           userCode2[0].trim().toString(), new Date().getTime()+"" });
/*       */                       }
/*       */ 
/* 12124 */                       if (device.getDeviceType().equals("2")) {
/* 12125 */                         String str = "ZIGBEE_CURTAIN-READ-" + user_num.get(userCode2[0].trim().toString()) + "," + 
/* 12126 */                           obj.getDeviceAddress() + "," + "21";
/* 12127 */                         byte[] bs = str.getBytes();
/* 12128 */                         System.err.println(new String(bs));
/* 12129 */                         this.packetProcessHelper.processSendDData(device.getBoDevice().getDeviceCode(), bs);
/* 12130 */                         StaticUtil.CURTAIN.put(device.getBoDevice().getDeviceCode() + "_" + "A", new String[] { 
/* 12131 */                           userCode2[0].trim().toString(), new Date().getTime()+"" });
/*       */                       }
/*       */ 
/* 12134 */                       if (device.getDeviceType().equals("4")) {
/* 12135 */                         String str = "ZIGBEE_DIMMER-READ-" + user_num.get(userCode2[0].trim().toString()) + "," + 
/* 12136 */                           obj.getDeviceAddress();
/* 12137 */                         byte[] bs = str.getBytes();
/* 12138 */                         System.err.println(new String(bs));
/* 12139 */                         this.packetProcessHelper.processSendDData(device.getBoDevice().getDeviceCode(), bs);
/* 12140 */                         StaticUtil.DIMMINGLIGHT.put(device.getBoDevice().getDeviceCode() + "_" + "A", new String[] { 
/* 12141 */                           userCode2[0].trim().toString(), new Date().getTime()+"" });
/*       */                       }
/*       */                     }
/*       */                   }
/*       */                   catch (InterruptedException localInterruptedException)
/*       */                   {
/*       */                   }
/*       */ 
/* 12149 */                   if (list.size() > 0) {
/* 12150 */                     Map map = new HashMap();
/* 12151 */                     for (BoDeviceState boDeviceState : list) {
/* 12152 */                       BoHostDevice hostDevice = this.boHostDeviceService.findBydeviceAddress(boDeviceState.getBoUsers().getUserCode(), boDeviceState.getDeviceAddress().toString());
/* 12153 */                       map.put("deviceAddress", boDeviceState.getDeviceAddress().toString());
/* 12154 */                       String string = boDeviceState.getKey1().toString();
/* 12155 */                       String state = "";
/* 12156 */                       if (hostDevice.getDeviceType().equals("1")) {
/* 12157 */                         if (string.equals("1"))
/* 12158 */                           state = "100";
/*       */                         else
/* 12160 */                           state = boDeviceState.getKey1().toString();
/*       */                       }
/*       */                       else {
/* 12163 */                         state = boDeviceState.getKey1().toString();
/*       */                       }
/*       */ 
/* 12166 */                       map.put("state", state);
/* 12167 */                       voList.add(map);
/*       */                     }
/*       */ 
/*       */                   }
/*       */ 
/*       */                 }
/*       */ 
/* 12174 */                 this.requestJson.setData(voList);
/*       */               } else {
/* 12176 */                 this.requestJson.setData(map1);
/* 12177 */                 this.requestJson.setMessage("该房间没有设备状态");
/* 12178 */                 this.requestJson.setSuccess(true);
/*       */               }
/*       */             }
/*       */             catch (Exception e) {
/* 12182 */               logger.info("error" + e.getMessage());
/* 12183 */               this.requestJson.setData(map1);
/* 12184 */               this.requestJson.setMessage("服务器发生异常");
/* 12185 */               this.requestJson.setSuccess(false);
/*       */             }
/*       */           } else {
/* 12188 */             System.err.println("AToken时间戳超时了");
/* 12189 */             this.requestJson.setMessage("超时了");
/* 12190 */             this.requestJson.setSuccess(false);
/*       */           }
/*       */         } else {
/* 12193 */           System.err.println("AToken超时了");
/* 12194 */           this.requestJson.setMessage("超时了");
/* 12195 */           this.requestJson.setSuccess(false);
/*       */         }
/*       */       }
/*       */       else
/*       */       {
/* 12200 */         System.err.println("验证不通过");
/* 12201 */         this.requestJson.setMessage("验证不通过");
/* 12202 */         this.requestJson.setSuccess(false);
/*       */       }
/*       */     } else {
/* 12205 */       Boolean ral = isRal(header, header2, header3, header4, userCode, "查询房间设备状态");
/* 12206 */       if (ral.booleanValue()) {
/* 12207 */         System.err.println("验证通过");
/* 12208 */         Long accessToken = Long.valueOf(header);
/* 12209 */         BoUsers boUsers = this.boUserServicess.findByUserUserCode(userCode);
/* 12210 */         if (accessToken.longValue() < Long.valueOf(boUsers.getAccessTokenTime()).longValue()) {
/* 12211 */           if (boUsers == null) {
/* 12212 */             this.requestJson.setMessage("没有找到该编号");
/* 12213 */             this.requestJson.setSuccess(true);
/*       */           } else {
/*       */             try {
/* 12216 */               List<BoHostDevice> getroomCode = this.boHostDeviceService.getroomCode(this.roomCode);
/* 12217 */               System.err.println("roomCode" + this.roomCode);
/*       */ 
/* 12219 */               if (getroomCode.size() > 0) {
/* 12220 */                 voList = new ArrayList();
/* 12221 */                 for (BoHostDevice boHostDevice : getroomCode) {
/* 12222 */                   List<BoDeviceState> list = this.boDeviceStateService
/* 12223 */                     .getBydeviceAddress(userCode, boHostDevice.getDeviceAddress());
/*       */ 
/* 12226 */                   if (user_num.get(userCode) == null)
/* 12227 */                     user_num.put(userCode, Integer.valueOf(0));
/*       */                   else {
/* 12229 */                     user_num.put(userCode, Integer.valueOf(((Integer)user_num.get(userCode)).intValue() == 255 ? 0 : ((Integer)user_num.get(userCode)).intValue() + 1));
/*       */                   }
/*       */ 
/*       */                   BoHostDevice obj;
/*       */                   try
/*       */                   {
/* 12237 */                     List list2 = this.boHostDeviceService
/* 12238 */                       .getListBy(userCode, boHostDevice.getDeviceAddress());
/* 12239 */                     for (int i = 0; i < list2.size(); i++) {
/* 12240 */                       Thread.sleep(200L);
/* 12241 */                       obj = (BoHostDevice)list2.get(i);
/* 12242 */                       if (user_num.get(userCode) == null)
/* 12243 */                         user_num.put(userCode, Integer.valueOf(0));
/*       */                       else {
/* 12245 */                         user_num.put(userCode, 
/* 12246 */                           Integer.valueOf(((Integer)user_num.get(userCode)).intValue() == 255 ? 0 : ((Integer)user_num.get(userCode)).intValue() + 1));
/*       */                       }
/* 12248 */                       BoHostDevice device = this.boHostDeviceService
/* 12249 */                         .findBydeviceAddress(obj.getBoUsers().getUserCode(), obj.getDeviceAddress());
/*       */ 
/* 12251 */                       if (device.getDeviceType().equals("1")) {
/* 12252 */                         String substring = obj.getDeviceAddress().substring(0, obj.getDeviceAddress().length() - 1);
/* 12253 */                         String str = "ZIGBEE_LIGHT-READ-" + user_num.get(userCode) + "," + substring;
/* 12254 */                         byte[] bs = str.getBytes();
/* 12255 */                         System.err.println(new String(bs));
/* 12256 */                         this.packetProcessHelper.processSendDData(device.getBoDevice().getDeviceCode(), bs);
/* 12257 */                         StaticUtil.QUERYSTATE.put(device.getBoDevice().getDeviceCode() + "_" + "A", new String[] { 
/* 12258 */                           userCode, new Date().getTime()+"" });
/*       */                       }
/*       */ 
/* 12261 */                       if (device.getDeviceType().equals("2")) {
/* 12262 */                         String str = "ZIGBEE_CURTAIN-READ-" + user_num.get(userCode) + "," + 
/* 12263 */                           obj.getDeviceAddress() + "," + "21";
/* 12264 */                         byte[] bs = str.getBytes();
/* 12265 */                         System.err.println(new String(bs));
/* 12266 */                         this.packetProcessHelper.processSendDData(device.getBoDevice().getDeviceCode(), bs);
/* 12267 */                         StaticUtil.CURTAIN.put(device.getBoDevice().getDeviceCode() + "_" + "A", new String[] { 
/* 12268 */                           userCode, new Date().getTime()+"" });
/*       */                       }
/*       */ 
/* 12271 */                       if (device.getDeviceType().equals("4")) {
/* 12272 */                         String str = "ZIGBEE_DIMMER-READ-" + user_num.get(userCode) + "," + 
/* 12273 */                           obj.getDeviceAddress();
/* 12274 */                         byte[] bs = str.getBytes();
/* 12275 */                         System.err.println(new String(bs));
/* 12276 */                         this.packetProcessHelper.processSendDData(device.getBoDevice().getDeviceCode(), bs);
/* 12277 */                         StaticUtil.DIMMINGLIGHT.put(device.getBoDevice().getDeviceCode() + "_" + "A", new String[] { 
/* 12278 */                           userCode, new Date().getTime()+"" });
/*       */                       }
/*       */                     }
/*       */                   }
/*       */                   catch (InterruptedException localInterruptedException2)
/*       */                   {
/*       */                   }
/*       */ 
/* 12286 */                   if (list.size() > 0) {
/* 12287 */                     Object map = new HashMap();
/* 12288 */                     for (BoDeviceState boDeviceState : list) {
/* 12289 */                       BoHostDevice hostDevice = this.boHostDeviceService.findBydeviceAddress(boDeviceState.getBoUsers().getUserCode(), boDeviceState.getDeviceAddress().toString());
/* 12290 */                       ((Map)map).put("deviceAddress", boDeviceState.getDeviceAddress().toString());
/* 12291 */                       String string = boDeviceState.getKey1().toString();
/* 12292 */                       String state = "";
/* 12293 */                       if (hostDevice.getDeviceType().equals("1")) {
/* 12294 */                         if (string.equals("1"))
/* 12295 */                           state = "100";
/*       */                         else
/* 12297 */                           state = boDeviceState.getKey1().toString();
/*       */                       }
/*       */                       else {
/* 12300 */                         state = boDeviceState.getKey1().toString();
/*       */                       }
/*       */ 
/* 12303 */                       ((Map)map).put("state", state);
/* 12304 */                       voList.add(map);
/*       */                     }
/*       */ 
/*       */                   }
/*       */ 
/*       */                 }
/*       */ 
/* 12311 */                 this.requestJson.setData(voList);
/*       */               } else {
/* 12313 */                 this.requestJson.setData(map1);
/* 12314 */                 this.requestJson.setMessage("该房间没有设备状态");
/* 12315 */                 this.requestJson.setSuccess(true);
/*       */               }
/*       */             }
/*       */             catch (Exception e) {
/* 12319 */               logger.info("error" + e.getMessage());
/* 12320 */               this.requestJson.setData(map1);
/* 12321 */               this.requestJson.setMessage("服务器发生异常");
/*       */             }
/*       */           }
/*       */         } else {
/* 12325 */           this.requestJson.setMessage("超时了");
/* 12326 */           this.requestJson.setSuccess(false);
/*       */         }
/*       */       }
/*       */       else {
/* 12330 */         System.err.println("验证不通过");
/* 12331 */         this.requestJson.setMessage("验证不通过");
/* 12332 */         this.requestJson.setSuccess(false);
/*       */       }
/*       */     }
/* 12335 */     return "success";
/*       */   }
/*       */ 
/*       */   @Action(value="setDeviceInfo", results={@org.apache.struts2.convention.annotation.Result(type="json", params={"root", "requestJson"})})
/*       */   public String setDeviceInfo()
/*       */   {//deviceCode==100（摄像头）出错  已改
/* 12347 */     this.requestJson = new RequestJson();
/* 12348 */     System.err.println("setDeviceInfo");
/* 12349 */     HttpServletRequest request = ServletActionContext.getRequest();
/* 12350 */     Map map = new HashMap();
/* 12351 */     String header = request.getHeader("timestamp");
/* 12352 */     String header2 = request.getHeader("nonce");
/* 12353 */     String header3 = request.getHeader("sign");
/* 12354 */     String header4 = request.getHeader("access_token");
/* 12355 */     String userCode = request.getHeader("userCode");
/* 12356 */     if (userCode.contains(",")) {
/* 12357 */       String[] userCode2 = userCode.split(",");
/* 12358 */       BoUsers boUsers = this.boUserServicess.findByUserUserCode(userCode2[0].trim().toString());
/* 12359 */       BoUsers phone = this.boUserServicess.findByUserPhone(userCode2[1].trim().toString());
/* 12360 */       Boolean ral = isRal(header, header2, header3, header4, userCode, "设置设备");
/* 12361 */       if (ral.booleanValue()) {
/* 12362 */         System.err.println("验证通过");
/* 12363 */         Long accessToken = Long.valueOf(header);
/*       */ 
/* 12365 */         if ((phone == null) || (boUsers == null)) {
/* 12366 */           this.requestJson.setData(map);
/* 12367 */           this.requestJson.setMessage("Invalid_User");
/* 12368 */           this.requestJson.setSuccess(true);
/*       */         }
/* 12370 */         else if (header4.equals(phone.getAccessToken())) {
/* 12371 */           if (accessToken.longValue() < Long.valueOf(phone.getAccessTokenTime()).longValue()) {
/* 12372 */             System.err.println("deviceCode> " + this.deviceCode);
/* 12373 */             System.err.println("roomCode> " + this.roomCode);
/* 12374 */             System.err.println("deviceAddress> " + this.deviceAddress);
/* 12375 */             System.err.println("userCode >" + userCode2[0].trim().toString());
/* 12377 */             System.err.println("ico >" + this.ico);
                        //输出到日志
                        logger.info("userCode2[0] >" + userCode2[0].trim().toString());//一串字符串
						//测试门锁 打印ico
//						System.out.println("测试打印ico >" + this.ico);//是门锁
/* 12378 */             BoDevice findByCode = this.boDeviceService.findByCode(this.deviceCode);
/* 12379 */             if (StringUtils.isEmpty(this.deviceCode)) {
/* 12380 */               System.err.println("主机地址不能为空,请选择主机");
/* 12381 */               this.requestJson.setData(map);
/* 12382 */               this.requestJson.setMessage("主机地址不能为空,请选择主机");
/* 12383 */               this.requestJson.setSuccess(false);
/*       */             } else {
/* 12385 */               BoRoom boRoom = this.boRoomService.findByCode(this.roomCode);
/* 12386 */               System.err.println(boRoom);
/* 12387 */               BoUserDevices userDevices = this.boUserDevicesServicess.findBy(userCode2[0].trim().toString());//null
/* 12388 */               if (boRoom != null) {
/* 12389 */                 BoHostDevice hostDevice = this.boHostDeviceService.findBydeviceAddress(userCode2[0].trim().toString(), this.deviceAddress);
							logger.info("hostDevice >" + hostDevice);
/* 12390 */                 if (hostDevice != null) {
/* 12391 */                   if ((hostDevice.getDeviceType().equals("1")) || (hostDevice.getDeviceType().equals("2")) || (hostDevice.getDeviceType().equals("4"))) {
/* 12392 */                     hostDevice.setWhetherQueryStateSign("Y");
/* 12393 */                     hostDevice.setPushSet("");
/* 12394 */                     hostDevice.setState("0");
/* 12395 */                   } else if (hostDevice.getDeviceType().equals("5")) {
/* 12396 */                     hostDevice.setWhetherQueryStateSign("");
/* 12397 */                     hostDevice.setPushSet("0");
/* 12398 */                     hostDevice.setState("");
//								hostDevice.setWhetherQueryStateSign("Y");
//								hostDevice.setPushSet("");
//								hostDevice.setState("0");
/*       */                   } else {
/* 12400 */                     hostDevice.setWhetherQueryStateSign("");
/* 12401 */                     hostDevice.setPushSet("");
/* 12402 */                     hostDevice.setState("");
/*       */                   }
/* 12404 */                   hostDevice.setBoDevice(findByCode);
/* 12405 */                   hostDevice.setNickName(this.nickName);
/* 12406 */                   hostDevice.setValidationCode(this.validationCode);//摄像头不能设置为空？
/* 12407 */                   hostDevice.setBoRoom(boRoom);
/* 12408 */                   hostDevice.setBoUsers(boUsers);
///* 12409 */                   hostDevice.setValidationCode("");
/* 12410 */                   hostDevice.setIco(this.ico);
/* 12411 */                   hostDevice.setDeviceClassify(this.fid1);
/* 12412 */                   BoHostDevice update = (BoHostDevice)this.boHostDeviceService.update(hostDevice);
/* 12413 */                   if (update != null) {
/* 12414 */                     this.requestJson.setData(map);
/* 12415 */                     this.requestJson.setMessage("");
/* 12416 */                     this.requestJson.setSuccess(true);
/*       */                   } else {
/* 12418 */                     this.requestJson.setData(map);
/* 12419 */                     this.requestJson.setMessage("");
/* 12420 */                     this.requestJson.setSuccess(false);
/*       */                   }
/*       */                 } else {//门锁 有关
/* 12423 */                   BoDevice findByCodes = this.boDeviceService.findByCode(this.deviceCode);
/* 12424 */                   BoHostDevice b = new BoHostDevice();
/* 12425 */                   System.err.println("deviceCode：" + this.deviceCode);
/* 12426 */                   System.err.println("deviceAddress：" + this.deviceAddress);
/* 12427 */                   System.err.println("*deviceType：" + this.deviceType);//门锁 -5314
/* 12428 */                   System.err.println("validationCode：" + this.validationCode);
							  //测试门锁   打印deviceType
//							  System.out.println("测试打印deviceType " + this.deviceType);//是 5314
/* 12429 */                   b.setDeviceAddress(this.deviceAddress);
/*       */ 
/* 12431 */                   b.setDeviceType(this.deviceType);
/* 12432 */                   if ((this.deviceType.equals("1")) || (this.deviceType.equals("2")) || (this.deviceType.equals("4"))) {
/* 12433 */                     b.setWhetherQueryStateSign("Y");
/* 12434 */                     b.setPushSet("");
/* 12435 */                     b.setState("0");
/* 12436 */                   } else if (this.deviceType.equals("5")) {
/* 12437 */                     b.setWhetherQueryStateSign("");
/* 12438 */                     b.setPushSet("0");
/* 12439 */                     b.setState("");
/*       */                   } else {
/* 12441 */                     b.setWhetherQueryStateSign("");
/* 12442 */                     b.setPushSet("");
/* 12443 */                     b.setState("");
/*       */                   }
/*       */ 
/* 12446 */                   if (this.deviceType.equals("100")) {
/* 12447 */                     if (StringUtils.isEmpty(this.validationCode)) {
/* 12448 */                       this.requestJson.setSuccess(false);
/* 12449 */                       this.requestJson.setMessage("摄像头密码不能为空");
/* 12450 */                       return "success";
/*       */                     }
/* 12452 */                   } else if ((this.deviceType.equals("101")) && 
/* 12453 */                     (StringUtils.isEmpty(this.validationCode))) {
/* 12454 */                     this.requestJson.setSuccess(false);
/* 12455 */                     this.requestJson.setMessage("摄像头密码不能为空");
/* 12456 */                     return "success";
/*       */                   }
/*       */ 
/* 12459 */                   b.setDeviceNum(Integer.valueOf(1));
/* 12460 */                   b.setNickName(this.nickName);
/* 12461 */                   b.setBoRoom(boRoom);
/* 12462 */                   b.setIco(this.ico);
/* 12463 */                   if ((this.validationCode == null) || (this.validationCode.equals("")))
/* 12464 */                     b.setValidationCode("");
/*       */                   else {
/* 12466 */                     b.setValidationCode(this.validationCode);
/*       */                   }
/* 12468 */                   b.setBoUsers(boUsers);
/* 12469 */                   b.setBoDevice(findByCodes);
/* 12470 */                   b.setDeviceClassify(this.fid1);
/* 12471 */                   System.err.println("deviceType >>"+this.deviceType);//门锁-5314
/* 12472 */                   if (this.deviceType.equals("100"))
/* 12473 */                     b.setMntDelete("Y");
/* 12474 */                   else if (this.deviceType.equals("101"))
/* 12475 */                     b.setMntDelete("Y");
/*       */                   else {
/* 12477 */                     b.setMntDelete("N");
/*       */                   }
/* 12479 */                   this.boHostDeviceService.save(b);
/*       */                 }
/*       */               }
/*       */               else
/*       */               {
/* 12484 */                 this.requestJson.setData(map);
/* 12485 */                 this.requestJson.setMessage("没有找到该房间");
/* 12486 */                 this.requestJson.setSuccess(true);
/*       */               }
/*       */             }
/*       */           } else {
/* 12490 */             System.err.println("AToken时间戳超时了");
/* 12491 */             this.requestJson.setMessage("超时了");
/* 12492 */             this.requestJson.setSuccess(false);
/*       */           }
/*       */         } else {
/* 12495 */           System.err.println("AToken超时了");
/* 12496 */           this.requestJson.setMessage("超时了");
/* 12497 */           this.requestJson.setSuccess(false);
/*       */         }
/*       */ 
/*       */       }
/*       */       else
/*       */       {
/* 12503 */         System.err.println("验证不通过");
/* 12504 */         this.requestJson.setMessage("验证不通过");
/* 12505 */         this.requestJson.setSuccess(false);
/*       */       }
/*       */     } else {
/* 12508 */       Boolean ral = isRal(header, header2, header3, header4, userCode, "修改主机昵称");
/* 12509 */       if (ral.booleanValue()) {
/* 12510 */         System.err.println("验证通过");
/* 12511 */         Long accessToken = Long.valueOf(header);
/* 12512 */         BoUsers boUsers = this.boUserServicess.findByUserUserCode(userCode);
/* 12513 */         if (boUsers == null) {
/* 12514 */           this.requestJson.setData(map);
/* 12515 */           this.requestJson.setMessage("Invalid_User");
/* 12516 */           this.requestJson.setSuccess(true);
/*       */         }
/* 12518 */         else if (accessToken.longValue() < Long.valueOf(boUsers.getAccessTokenTime()).longValue())
/*       */         {
	                  //here
/* 12520 */           System.err.println("deviceCode> " + this.deviceCode);
/* 12521 */           System.err.println("roomCode> " + this.roomCode);
/* 12522 */           System.err.println("deviceAddress> " + this.deviceAddress);
/* 12523 */           System.err.println("userCode >" + userCode);//
/* 12525 */           System.err.println("ico >" + this.ico);
					  logger.error("deviceCode> " + this.deviceCode);
					  logger.error("deviceAddress> " + this.deviceAddress);
					  logger.info("userCode >" + userCode);
					  

/* 12526 */           BoDevice findByCode = this.boDeviceService.findByCode(this.deviceCode);
/* 12527 */           if (StringUtils.isEmpty(this.deviceCode)) {
/* 12528 */             System.err.println("主机地址不能为空,请选择主机");
/* 12529 */             this.requestJson.setData(map);
/* 12530 */             this.requestJson.setMessage("主机地址不能为空,请选择主机");
/* 12531 */             this.requestJson.setSuccess(false);
/*       */           } else {
/* 12533 */             BoRoom boRoom = this.boRoomService.findByCode(this.roomCode);
/* 12534 */             System.err.println(boRoom);
/* 12535 */             BoUserDevices userDevices = this.boUserDevicesServicess.findBy(userCode);
/* 12536 */             if (boRoom != null) {
/* 12537 */               BoHostDevice hostDevice = this.boHostDeviceService.findBydeviceAddress(userCode, this.deviceAddress);
/* 12538 */               if (hostDevice != null) {
/* 12539 */                 hostDevice.setBoDevice(findByCode);
/* 12540 */                 hostDevice.setNickName(this.nickName);
/* 12541 */                 hostDevice.setValidationCode("");
/* 12542 */                 hostDevice.setBoRoom(boRoom);
/* 12543 */                 hostDevice.setBoUsers(boUsers);
/* 12544 */                 hostDevice.setValidationCode("");
/* 12545 */                 hostDevice.setIco(this.ico);
/* 12546 */                 hostDevice.setDeviceClassify(this.fid1);
/* 12547 */                 BoHostDevice update = (BoHostDevice)this.boHostDeviceService.update(hostDevice);
/* 12548 */                 if (update != null) {
/* 12549 */                   this.requestJson.setData(map);
/* 12550 */                   this.requestJson.setMessage("");
/* 12551 */                   this.requestJson.setSuccess(true);
/*       */                 } else {
/* 12553 */                   this.requestJson.setData(map);
/* 12554 */                   this.requestJson.setMessage("");
/* 12555 */                   this.requestJson.setSuccess(false);
/*       */                 }
/*       */               } else {
/* 12558 */                 BoDevice findByCodes = this.boDeviceService.findByCode(this.deviceCode);
/* 12559 */                 BoHostDevice b = new BoHostDevice();
							//here
/* 12560 */                 System.err.println("deviceCode " + this.deviceCode);
/* 12561 */                 System.err.println("deviceAddress " + this.deviceAddress);
/* 12562 */                 System.err.println("deviceType " + this.deviceType);//门锁-5314
/* 12563 */                 System.err.println("validationCode " + this.validationCode);
/* 12564 */                 b.setDeviceAddress(this.deviceAddress);
/*       */ 
/* 12566 */                 b.setDeviceType(this.deviceType);
/* 12567 */                 if ((this.deviceType.equals("1")) || (this.deviceType.equals("2")) || (this.deviceType.equals("4"))) {
/* 12568 */                   b.setWhetherQueryStateSign("Y");
/* 12569 */                   b.setPushSet("");
/* 12570 */                   b.setState("0");
/* 12571 */                 } else if (this.deviceType.equals("5")) {//门锁
/* 12572 */                   b.setWhetherQueryStateSign("");
/* 12573 */                   b.setPushSet("0");
/* 12574 */                   b.setState("");
/*       */                 } else {
/* 12576 */                   b.setWhetherQueryStateSign("");
/* 12577 */                   b.setPushSet("");
/* 12578 */                   b.setState("");
/*       */                 }
/*       */ 
/* 12581 */                 if (this.deviceType.equals("100")) {
/* 12582 */                   if (StringUtils.isEmpty(this.validationCode)) {
/* 12583 */                     this.requestJson.setSuccess(false);
/* 12584 */                     this.requestJson.setMessage("摄像头密码不能为空");
/* 12585 */                     return "success";
/*       */                   }
/* 12587 */                 } else if ((this.deviceType.equals("101")) && 
/* 12588 */                   (StringUtils.isEmpty(this.validationCode))) {
/* 12589 */                   this.requestJson.setSuccess(false);
/* 12590 */                   this.requestJson.setMessage("摄像头密码不能为空");
/* 12591 */                   return "success";
/*       */                 }
/*       */ 
/* 12594 */                 b.setDeviceNum(Integer.valueOf(1));
/* 12595 */                 b.setNickName(this.nickName);
/* 12596 */                 b.setBoRoom(boRoom);
/* 12597 */                 b.setIco(this.ico);
/* 12598 */                 if ((this.validationCode == null) || (this.validationCode.equals("")))
/* 12599 */                   b.setValidationCode("");
/*       */                 else {
/* 12601 */                   b.setValidationCode(this.validationCode);
/*       */                 }
/* 12603 */                 b.setBoUsers(boUsers);
/* 12604 */                 b.setBoDevice(findByCodes);
/* 12605 */                 b.setDeviceClassify(this.fid1);
///* 12606 */                 System.err.println("deviceType 12291L:"+this.deviceType);//门锁-5314
/* 12607 */                 if (this.deviceType.equals("100"))
/* 12608 */                   b.setMntDelete("Y");
/* 12609 */                 else if (this.deviceType.equals("101"))
/* 12610 */                   b.setMntDelete("Y");
/*       */                 else {
/* 12612 */                   b.setMntDelete("N");
/*       */                 }
/* 12614 */                 this.boHostDeviceService.save(b);
/* 12615 */                 if ((this.deviceType.equals("1")) || (this.deviceType.equals("2")) || (this.deviceType.equals("4"))) {
/* 12616 */                   BoDeviceState boDeviceState = new BoDeviceState();
/* 12617 */                   boDeviceState.setBoDevice(findByCodes);
/* 12618 */                   boDeviceState.setBoUsers(boUsers);
/* 12619 */                   boDeviceState.setDeviceAddress(this.deviceAddress);
/* 12620 */                   boDeviceState.setKeyValue(Integer.valueOf(1));
/* 12621 */                   boDeviceState.setKey1(Integer.valueOf(0));
/* 12622 */                   this.boDeviceStateService.save(boDeviceState);
/*       */                 }
/*       */               }
/*       */             }
/*       */             else {
/* 12627 */               this.requestJson.setData(map);
/* 12628 */               this.requestJson.setMessage("没有找到该房间");
/* 12629 */               this.requestJson.setSuccess(true);
/*       */             }
/*       */           }
/*       */         }
/*       */         else
/*       */         {
/* 12635 */           this.requestJson.setMessage("超时了");
/* 12636 */           this.requestJson.setSuccess(false);
/*       */         }
/*       */ 
/*       */       }
/*       */       else
/*       */       {
/* 12642 */         System.err.println("验证不通过");
/* 12643 */         this.requestJson.setMessage("验证不通过");
/* 12644 */         this.requestJson.setSuccess(false);
/*       */       }
/*       */     }
/* 12647 */     return "success";
/*       */   }
/*       */ 
/*       */   @Action(value="classifyqueryequipment", results={@org.apache.struts2.convention.annotation.Result(type="json", params={"root", "requestJson"})})
/*       */   public String classifyQueryeQuipment()
/*       */   {
/* 12658 */     this.requestJson = new RequestJson();
/* 12659 */     Map maps = new HashMap();
/* 12660 */     HttpServletRequest request = ServletActionContext.getRequest();
/* 12661 */     String header = request.getHeader("timestamp");
/* 12662 */     String header2 = request.getHeader("nonce");
/* 12663 */     String header3 = request.getHeader("sign");
/* 12664 */     String header4 = request.getHeader("access_token");
/* 12665 */     String userCode = request.getHeader("userCode");
/*       */     List voList;
/* 12666 */     if (userCode.contains(",")) {
/* 12667 */       String[] userCode2 = userCode.split(",");
				  logger.info("userCode2>>"+userCode);
/* 12668 */       BoUsers boUsers = this.boUserServicess.findByUserUserCode(userCode2[0].trim().toString());
/* 12669 */       BoUsers phone = this.boUserServicess.findByUserPhone(userCode2[1].trim().toString());
/* 12670 */       Boolean ral = isRal(header, header2, header3, header4, userCode, "已分类房间设备");
/* 12671 */       if (ral.booleanValue()) {
/* 12672 */         System.err.println("验证通过");
/* 12673 */         Long accessToken = Long.valueOf(header);
/*       */ 
/* 12675 */         if ((phone == null) || (boUsers == null)) {
/* 12676 */           this.requestJson.setData(maps);
/* 12677 */           this.requestJson.setMessage("Invalid_User");
/* 12678 */           this.requestJson.setSuccess(true);
/*       */         }
/* 12680 */         else if (header4.equals(phone.getAccessToken())) {
/* 12681 */           if (accessToken.longValue() < Long.valueOf(phone.getAccessTokenTime()).longValue()) {
/* 12682 */             List by = this.boUserDevicesServicess.getBy(userCode2[0].trim().toString());
/* 12683 */             List<BoHostDevice> lists = this.boHostDeviceService.getListByUserCodes(userCode2[0].trim().toString(), "commonsxt", 
/* 12684 */               this.fid1);
/* 12685 */             voList = new ArrayList();
/*       */             Map map;
/* 12686 */             for (BoHostDevice boHostDevice : lists) {
/* 12687 */               map = new HashMap();
/* 12688 */               map.put("deviceType", boHostDevice.getDeviceType().toString());
/* 12689 */               map.put("deviceAddress", boHostDevice.getDeviceAddress().toString());
/* 12690 */               map.put("deviceNum", boHostDevice.getDeviceNum().toString());
/* 12691 */               String validationCodes = boHostDevice.getValidationCode().toString();
/*       */               String validationCodess;

/* 12693 */               if (validationCodes == null)
/* 12694 */                 validationCodess = "";
/*       */               else {
/* 12696 */                 validationCodess = boHostDevice.getValidationCode().toString();
/*       */               }
/* 12698 */               System.err.println("摄像头" + boHostDevice.toString());
/* 12699 */               map.put("validationCode", validationCodess);
/* 12700 */               map.put("userCode", boHostDevice.getBoUsers().getUserCode());//没有逗号的情况
/* 12701 */               String nickName2 = boHostDevice.getNickName();
/*       */               String nickNames;

/* 12703 */               if (nickName2 == null)
/* 12704 */                 nickNames = "";
/*       */               else {
/* 12706 */                 nickNames = boHostDevice.getNickName();
/*       */               }
/* 12708 */               map.put("nickName", nickNames);
						  logger.info("nickName>>"+nickNames);//设备名称
/* 12709 */               map.put("roomCode", boHostDevice.getBoRoom().getRoomCode().toString());
/* 12710 */               map.put("roomName", boHostDevice.getBoRoom().getRoomName().toString());
						  logger.info("roomName>>"+boHostDevice.getBoRoom().getRoomName().toString());//房间名称
/*       */ 
/* 12712 */               map.put("icon", boHostDevice.getIco());
/* 12713 */               voList.add(map);
/*       */             }
/*       */ 
/* 12716 */             if (by.size() <= 0)
/*       */             {
/* 12718 */               this.requestJson.setMessage("没有");
/* 12719 */               this.requestJson.setSuccess(true);
/*       */             }
/*       */             else {
/* 12722 */               List<BoHostDevice> list = this.boHostDeviceService.getListByUserCode(userCode2[0].trim().toString(), this.fid1);
/*       */ 
/* 12724 */               for (BoHostDevice boHostDevice : list) {
/* 12725 */                 map = new HashMap();
						    //new 2018-3-1
							String deviceName="";
							String roomName=boHostDevice.getBoRoom().getRoomName().toString();
							if(boHostDevice.getNickName() !=null) {
								deviceName=boHostDevice.getNickName();
							}
//							logger.info("隐藏客厅>>>"+!roomName.equals("客厅"));
//							if(!roomName.equals("客厅")) {//客厅 没被赋予权限  被隐藏了
//								if(!deviceName.equals("灯光433")) {  //灯光433 没赋予权限就无法显示了                        	
		/* 12726 */                 map.put("deviceCode", boHostDevice.getBoDevice().getDeviceCode());
		/* 12727 */                 map.put("deviceType", boHostDevice.getDeviceType().toString());
		/* 12728 */                 map.put("deviceAddress", boHostDevice.getDeviceAddress().toString());
		/* 12729 */                 map.put("deviceNum", boHostDevice.getDeviceNum().toString());
		/* 12730 */                 map.put("userCode", boHostDevice.getBoUsers().getUserCode());//userCode没有逗号的情况
		/* 12731 */                 String nickName2 = boHostDevice.getNickName();
		/*       */                 String nickNames;
		
		/* 12733 */                 if (nickName2 == null)
			/* 12734 */                   nickNames = "";
		/*       */                 else {
			/* 12736 */                   nickNames = boHostDevice.getNickName();
		/*       */                 }
		/* 12738 */                 map.put("nickName", nickNames);
									logger.info("nickName 01>>"+nickNames);//设备名称
		/* 12739 */                 map.put("roomCode", boHostDevice.getBoRoom().getRoomCode().toString());
		/* 12740 */                 map.put("roomName", boHostDevice.getBoRoom().getRoomName().toString());
									logger.info("roomName 01>>"+boHostDevice.getBoRoom().getRoomName().toString());//房间名称
		/* 12741 */                 map.put("icon", boHostDevice.getIco());
		/* 12742 */                 voList.add(map);
//								}
								
//							}
                            //前面没有 /* 行数 */的是新添加的
/*       */               }
/*       */             }
/* 12745 */             this.requestJson.setData(voList);
/*       */           } else {
/* 12747 */             this.requestJson.setMessage("超时了");
/* 12748 */             this.requestJson.setSuccess(false);
/*       */           }
/*       */         } else {
/* 12751 */           this.requestJson.setMessage("超时了");
/* 12752 */           this.requestJson.setSuccess(false);
/*       */         }
/*       */ 
/*       */       }
/*       */       else
/*       */       {
/* 12760 */         System.err.println("验证不通过");
/* 12761 */         this.requestJson.setMessage("验证不通过");
/* 12762 */         this.requestJson.setSuccess(false);
/*       */       }
/*       */     } else {
/* 12765 */       Boolean ral = isRal(header, header2, header3, header4, userCode, "修改主机昵称");
/* 12766 */       if (ral.booleanValue()) {
/* 12767 */         System.err.println("验证通过");
/* 12768 */         Long accessToken = Long.valueOf(header);
/* 12769 */         BoUsers boUsers = this.boUserServicess.findByUserUserCode(userCode);
/* 12770 */         if (boUsers == null) {
/* 12771 */           this.requestJson.setMessage("Invalid_User");
/* 12772 */           this.requestJson.setSuccess(true);
/*       */         }
/* 12774 */         else if (header4.equals(boUsers.getAccessToken())) {
/* 12775 */           if (accessToken.longValue() < Long.valueOf(boUsers.getAccessTokenTime()).longValue()) {
/* 12776 */             List by = this.boUserDevicesServicess.getBy(userCode);
/* 12777 */             List<BoHostDevice> lists = this.boHostDeviceService.getListByUserCodes(userCode, "commonsxt", 
/* 12778 */               this.fid1);
/* 12779 */             voList = new ArrayList();
/*       */             Map map;
/* 12780 */             for (BoHostDevice boHostDevice : lists) {
/* 12781 */               map = new HashMap();
/* 12782 */               map.put("deviceType", boHostDevice.getDeviceType().toString());
/* 12783 */               map.put("deviceAddress", boHostDevice.getDeviceAddress().toString());
/* 12784 */               map.put("deviceNum", boHostDevice.getDeviceNum().toString());
/* 12785 */               String validationCodes = boHostDevice.getValidationCode().toString();
/*       */               String validationCodess;

/* 12787 */               if (validationCodes == null)
/* 12788 */                 validationCodess = "";
/*       */               else {
/* 12790 */                 validationCodess = boHostDevice.getValidationCode().toString();
/*       */               }
/* 12792 */               System.err.println("摄像头" + boHostDevice.toString());
/* 12793 */               map.put("validationCode", validationCodess);
/* 12794 */               map.put("userCode", boHostDevice.getBoUsers().getUserCode());
/* 12795 */               String nickName2 = boHostDevice.getNickName();
/*       */               String nickNames;

/* 12797 */               if (nickName2 == null)
/* 12798 */                 nickNames = "";
/*       */               else {
/* 12800 */                 nickNames = boHostDevice.getNickName();
/*       */               }
/* 12802 */               map.put("nickName", nickNames);
/* 12803 */               map.put("roomCode", boHostDevice.getBoRoom().getRoomCode().toString());
/* 12804 */               map.put("roomName", boHostDevice.getBoRoom().getRoomName().toString());
/*       */ 
/* 12806 */               map.put("icon", boHostDevice.getIco());
/* 12807 */               voList.add(map);
/*       */             }
/*       */ 
/* 12810 */             if (by.size() <= 0) {
/* 12811 */               this.requestJson.setData(maps);
/* 12812 */               this.requestJson.setMessage("没有");
/* 12813 */               this.requestJson.setSuccess(true);
/*       */             }
/*       */             else {
/* 12816 */               List<BoHostDevice> list = this.boHostDeviceService.getListByUserCode(userCode, this.fid1);
/*       */ 
/* 12818 */               for (BoHostDevice boHostDevice : list) {
/* 12819 */                 map = new HashMap();
/* 12820 */                 ((Map)map).put("deviceCode", boHostDevice.getBoDevice().getDeviceCode());
/* 12821 */                 ((Map)map).put("deviceType", boHostDevice.getDeviceType().toString());
/* 12822 */                 ((Map)map).put("deviceAddress", boHostDevice.getDeviceAddress().toString());
/* 12823 */                 ((Map)map).put("deviceNum", boHostDevice.getDeviceNum().toString());
/* 12824 */                 ((Map)map).put("userCode", boHostDevice.getBoUsers().getUserCode());
/* 12825 */                 String nickName2 = boHostDevice.getNickName();
/*       */                 String nickNames;

/* 12827 */                 if (nickName2 == null)
/* 12828 */                   nickNames = "";
/*       */                 else {
/* 12830 */                   nickNames = boHostDevice.getNickName();
/*       */                 }
/* 12832 */                 ((Map)map).put("nickName", nickNames);
/* 12833 */                 ((Map)map).put("roomCode", boHostDevice.getBoRoom().getRoomCode().toString());
/* 12834 */                 ((Map)map).put("roomName", boHostDevice.getBoRoom().getRoomName().toString());
/* 12835 */                 ((Map)map).put("icon", boHostDevice.getIco());
/* 12836 */                 voList.add(map);
/*       */               }
/*       */             }
/* 12839 */             this.requestJson.setData(voList);
/*       */           } else {
/* 12841 */             this.requestJson.setData(maps);
/* 12842 */             this.requestJson.setMessage("超时了");
/* 12843 */             this.requestJson.setSuccess(false);
/*       */           }
/*       */         } else {
/* 12846 */           this.requestJson.setData(maps);
/* 12847 */           this.requestJson.setMessage("超时了");
/* 12848 */           this.requestJson.setSuccess(false);
/*       */         }
/*       */ 
/*       */       }
/*       */       else
/*       */       {
/* 12856 */         System.err.println("验证不通过");
/* 12857 */         this.requestJson.setMessage("验证不通过");
/* 12858 */         this.requestJson.setSuccess(false);
/*       */       }
/*       */     }
/* 12861 */     return "success";
/*       */   }
/*       */ 
/*       */   @Action(value="unclassifyqueryequipment", results={@org.apache.struts2.convention.annotation.Result(type="json", params={"root", "requestJson"})})
/*       */   public String unClassiFied()
/*       */     throws InterruptedException
/*       */   {
/* 12874 */     Map maps = new HashMap();
/* 12875 */     HttpServletRequest request = ServletActionContext.getRequest();
/* 12876 */     String header = request.getHeader("timestamp");
/* 12877 */     String header2 = request.getHeader("nonce");
/* 12878 */     String header3 = request.getHeader("sign");
/* 12879 */     String header4 = request.getHeader("access_token");
/* 12880 */     String userCode = request.getHeader("userCode");
/*       */     List<BoUserDevices> list;
/*       */     List voList;
/* 12881 */     if (userCode.contains(",")) {
/* 12882 */       String[] userCode2 = userCode.split(",");
/* 12883 */       BoUsers boUsers = this.boUserServicess.findByUserUserCode(userCode2[0].trim().toString());
/* 12884 */       BoUsers phone = this.boUserServicess.findByUserPhone(userCode2[1].trim().toString());
/* 12885 */       Boolean ral = isRal(header, header2, header3, header4, userCode, "未分类房间设备");
/* 12886 */       if (ral.booleanValue()) {
/* 12887 */         if (header4.equals(phone.getAccessToken())) {
/* 12888 */           Long accessToken = Long.valueOf(header);
/*       */ 
/* 12890 */           if (accessToken.longValue() < Long.valueOf(phone.getAccessTokenTime()).longValue()) {
/* 12891 */             System.err.println("没有超时");
/* 12892 */             BoUserDevices findBy = this.boUserDevicesServicess.findBy(userCode2[0].trim().toString());
/* 12893 */             if (findBy == null) {
/* 12894 */               System.err.println("没有主机");
/* 12895 */               this.requestJson.setData(maps);
/* 12896 */               this.requestJson.setMessage("您没有绑定主机");
/* 12897 */               this.requestJson.setSuccess(true);
/*       */             } else {
/* 12899 */               Thread.sleep(1500L);
/* 12900 */               list = this.boUserDevicesServicess.getBy(userCode2[0].trim().toString());
/* 12901 */               for (BoUserDevices boUserDevices : list) {
/* 12902 */                 List<BoHostDevice> lists = this.boHostDeviceService.getListByUserCode(userCode2[0].trim().toString(), this.fid);
/* 12903 */                 if (list.size() <= 0) {
/* 12904 */                   this.requestJson.setData(maps);
/* 12905 */                   this.requestJson.setMessage("没有找到");
/* 12906 */                   this.requestJson.setSuccess(true);
/* 12907 */                   return "success";
/*       */                 }
/* 12909 */                 voList = new ArrayList();
/* 12910 */                 for (BoHostDevice boHostDevice : lists) {
/* 12911 */                   System.err.println("地址码 " + boHostDevice.getDeviceAddress().toString());
/* 12912 */                   Map map = new HashMap();
/* 12913 */                   map.put("deviceType", boHostDevice.getDeviceType().toString());
/* 12914 */                   map.put("deviceAddress", boHostDevice.getDeviceAddress().toString());
/* 12915 */                   map.put("deviceNum", boHostDevice.getDeviceNum().toString());
/* 12916 */                   map.put("userCode", boUserDevices.getBoUsers().getUserCode());
/* 12917 */                   map.put("deviceCode", boHostDevice.getBoDevice().getDeviceCode());
/* 12918 */                   map.put("icon", boHostDevice.getIco());
/*       */ 
/* 12920 */                   voList.add(map);
/*       */                 }
/*       */ 
/* 12923 */                 this.requestJson.setData(voList);
/*       */               }
/*       */             }
/*       */           }
/*       */           else {
/* 12928 */             System.err.println("AToken时间戳超时了");
/* 12929 */             this.requestJson.setMessage("超时了");
/* 12930 */             this.requestJson.setSuccess(false);
/*       */           }
/*       */         } else {
/* 12933 */           System.err.println("AToken超时了");
/* 12934 */           this.requestJson.setMessage("超时了");
/* 12935 */           this.requestJson.setSuccess(false);
/*       */         }
/*       */       } else {
/* 12938 */         System.err.println("验证不通过");
/* 12939 */         this.requestJson.setMessage("验证不通过");
/* 12940 */         this.requestJson.setSuccess(false);
/*       */       }
/*       */     } else {
/* 12943 */       Boolean ral = isRal(header, header2, header3, header4, userCode, "修改主机昵称");
/* 12944 */       if (ral.booleanValue()) {
/* 12945 */         Long accessToken = Long.valueOf(header);
/* 12946 */         BoUsers boUsers = this.boUserServicess.findByUserUserCode(userCode);
/* 12947 */         if (accessToken.longValue() < Long.valueOf(boUsers.getAccessTokenTime()).longValue()) {
/* 12948 */           System.err.println("没有超时");
/* 12949 */           BoUserDevices findBy = this.boUserDevicesServicess.findBy(userCode);
/* 12950 */           if (findBy == null) {
/* 12951 */             System.err.println("没有主机");
/* 12952 */             this.requestJson.setData(maps);
/* 12953 */             this.requestJson.setMessage("您没有绑定主机");
/* 12954 */             this.requestJson.setSuccess(true);
/*       */           } else {
/* 12956 */             list = this.boUserDevicesServicess.getBy(userCode);
/* 12957 */             for (BoUserDevices boUserDevices : list) {
/* 12958 */               List<BoHostDevice> lists = this.boHostDeviceService.getListByUserCode(userCode, this.fid);
/* 12959 */               if (list.size() <= 0) {
/* 12960 */                 this.requestJson.setData(maps);
/* 12961 */                 this.requestJson.setMessage("没有找到");
/* 12962 */                 this.requestJson.setSuccess(true);
/* 12963 */                 return "success";
/*       */               }
/* 12965 */               voList = new ArrayList();
/* 12966 */               for (BoHostDevice boHostDevice : lists) {
/* 12967 */                 System.err.println(boHostDevice.getDeviceAddress().toString());
/* 12968 */                 Map map = new HashMap();
/* 12969 */                 map.put("deviceType", boHostDevice.getDeviceType().toString());
/* 12970 */                 map.put("deviceAddress", boHostDevice.getDeviceAddress().toString());
/* 12971 */                 map.put("deviceNum", boHostDevice.getDeviceNum().toString());
/* 12972 */                 map.put("userCode", boUserDevices.getBoUsers().getUserCode());
/* 12973 */                 map.put("deviceCode", boHostDevice.getBoDevice().getDeviceCode());
/* 12974 */                 map.put("icon", boHostDevice.getIco());
/*       */ 
/* 12976 */                 ((List)voList).add(map);
/*       */               }
/*       */ 
/* 12979 */               this.requestJson.setData(voList);
/*       */             }
/*       */           }
/*       */         }
/*       */         else {
/* 12984 */           this.requestJson.setMessage("超时了");
/* 12985 */           this.requestJson.setSuccess(false);
/*       */         }
/*       */       } else {
/* 12988 */         System.err.println("验证不通过");
/* 12989 */         this.requestJson.setMessage("验证不通过");
/* 12990 */         this.requestJson.setSuccess(false);
/*       */       }
/*       */     }
/* 12993 */     return "success";
/*       */   }
/*       */ 
/*       */   @Action(value="updatinfo", results={@org.apache.struts2.convention.annotation.Result(type="json", params={"root", "requestJson"})})
/*       */   public String updateInfo()
/*       */   {
/* 13004 */     this.requestJson = new RequestJson();
/* 13005 */     Map map = new HashMap();
/* 13006 */     HttpServletRequest request = ServletActionContext.getRequest();
/* 13007 */     String str = "";
/* 13008 */     Md5 md5 = new Md5();
/* 13009 */     String header = request.getHeader("timestamp");
/* 13010 */     String header2 = request.getHeader("nonce");
/* 13011 */     String header3 = request.getHeader("sign");
/* 13012 */     String header4 = request.getHeader("access_token");
/* 13013 */     String userCode = request.getHeader("userCode");
/*       */     List<BoFloor> persons;
/*       */     JSONArray json1;
/*       */     Object lists;
/*       */     Object isFirst;
/* 13014 */     if (userCode.contains(",")) {
/* 13015 */       String[] userCode2 = userCode.split(",");
/* 13016 */       BoUsers boUsers = this.boUserServicess.findByUserUserCode(userCode2[0].trim().toString());
/* 13017 */       BoUsers phone = this.boUserServicess.findByUserPhone(userCode2[1].trim().toString());
/* 13018 */       Boolean ral = isRal(header, header2, header3, header4, userCode, "设置楼层房间信息");
/* 13019 */       if (ral.booleanValue()) {
/* 13020 */         System.err.println("验证通过");
/* 13021 */         Long accessToken = Long.valueOf(header);
/* 13022 */         if ((phone == null) || (boUsers == null)) {
/* 13023 */           this.requestJson.setData(map);
/* 13024 */           this.requestJson.setMessage("Invalid_User");
/* 13025 */           this.requestJson.setSuccess(true);
/*       */         }
/* 13027 */         else if (header4.equals(phone.getAccessToken())) {
/* 13028 */           if (accessToken.longValue() < Long.valueOf(phone.getAccessTokenTime()).longValue()) {
/* 13029 */             JSONArray json = JSONArray.fromObject(this.floorInfo);
/* 13030 */             System.err.println(this.floorInfo);
/* 13031 */             persons = (List)JSONArray.toCollection(json, BoFloor.class);
/*       */             BoFloor boFloor2;
/* 13033 */             for (BoFloor boFloor : persons) {
/* 13034 */               if (boFloor.getFloorCode().equals(""))
/*       */               {
/* 13036 */                 boFloor.setFloorName(boFloor.getFloorName());
/* 13037 */                 boFloor.setUserCode(userCode2[0].trim().toString());
/* 13038 */                 BoFloor localBoFloor1 = (BoFloor)this.boFloorService.save(boFloor);
/*       */               } else {
/* 13040 */                 List list = this.boFloorService.get(boFloor.getFloorCode());
/* 13041 */                 for (Iterator localIterator2 = list.iterator(); localIterator2.hasNext(); ) { boFloor2 = (BoFloor)localIterator2.next();
/* 13042 */                   boFloor2.setFloorName(boFloor.getFloorName());
/* 13043 */                   this.boFloorService.update(boFloor2);
/*       */                 }
/*       */ 
/*       */               }
/*       */ 
/*       */             }
/*       */ 
/* 13054 */             json1 = JSONArray.fromObject(this.roomInfo);
/* 13055 */             System.err.println(this.roomInfo);
/* 13056 */             List<BoRoom> personss = (List)JSONArray.toCollection(json1, BoRoom.class);
/*       */ 
/* 13063 */             for (BoRoom boRoom : personss) {
/* 13064 */               if (boRoom.getRoomCode().equals("")) {
/* 13065 */                 BoFloor floor2 = this.boFloorService.findByFloorName(userCode2[0].trim().toString(), boRoom.getFloorName());
/* 13066 */                 if (floor2 == null) {
/* 13067 */                   this.requestJson.setData(map);
/* 13068 */                   this.requestJson.setMessage("楼层创建失败");
/* 13069 */                   this.requestJson.setSuccess(false);
/*       */                 } else {
/* 13071 */                   boRoom.setRoomName(boRoom.getRoomName());
/* 13072 */                   boRoom.setFloorCode(floor2.getFloorCode());
/* 13073 */                   boRoom.setUserCode(userCode2[0].trim().toString());
/* 13074 */                   this.boRoomService.save(boRoom);
/*       */                 }
/*       */               }
/*       */               else {
/* 13078 */                 lists = this.boRoomService.get(boRoom.getRoomCode());
/* 13079 */                 System.err.println(((List)lists).size());
/* 13080 */                 System.err.println("// " + boRoom.getRoomCode());
/* 13081 */                 if (((List)lists).size() > 0) {
/* 13082 */                   for (BoRoom boRoom2 : (List<BoRoom>)lists) {
/* 13083 */                     boRoom2.setRoomName(boRoom.getRoomName());
/* 13084 */                     this.boRoomService.update(boRoom2);
/*       */                   }
/*       */                 }
/*       */               }
/*       */ 
/*       */             }
/*       */ 
/* 13091 */             isFirst = boUsers.getIsFirst();
/* 13092 */             if (isFirst == this.fid) {
/* 13093 */               boUsers.setIsFirst(this.fid1);
/* 13094 */               this.boUserServicess.update(boUsers);
/*       */             }
/* 13096 */             this.requestJson.setData(map);
/*       */ 
/* 13098 */             return "success";
/*       */           }
/* 13100 */           System.err.println("ATOKEN时间戳超时了");
/* 13101 */           this.requestJson.setMessage("超时了");
/* 13102 */           this.requestJson.setSuccess(false);
/*       */         }
/*       */         else {
/* 13105 */           System.err.println("ATOKEN超时了");
/* 13106 */           this.requestJson.setMessage("超时了");
/* 13107 */           this.requestJson.setSuccess(false);
/*       */         }
/*       */       }
/*       */       else {
/* 13111 */         System.err.println("验证不通过");
/* 13112 */         this.requestJson.setMessage("验证不通过");
/* 13113 */         this.requestJson.setSuccess(false);
/*       */       }
/*       */     } else {
/* 13116 */       Boolean ral = isRal(header, header2, header3, header4, userCode, "设置楼层房间信息");
/* 13117 */       if (ral.booleanValue()) {
/* 13118 */         System.err.println("验证通过");
/* 13119 */         Long accessToken = Long.valueOf(header);
/* 13120 */         BoUsers boUsers = this.boUserServicess.findByUserUserCode(userCode);
/* 13121 */         if (accessToken.longValue() < Long.valueOf(boUsers.getAccessTokenTime()).longValue()) {
/* 13122 */           if (boUsers == null) {
/* 13123 */             this.requestJson.setMessage("Invalid_User");
/* 13124 */             this.requestJson.setSuccess(true);
/*       */           } else {
/* 13126 */             JSONArray json = JSONArray.fromObject(this.floorInfo);
/* 13127 */             System.err.println(this.floorInfo);
/* 13128 */             persons = (List)JSONArray.toCollection(json, BoFloor.class);
/*       */ 
/* 13130 */             for (BoFloor boFloor : persons) {
/* 13131 */               if (boFloor.getFloorCode().equals(""))
/*       */               {
/* 13133 */                 boFloor.setFloorName(boFloor.getFloorName());
/* 13134 */                 boFloor.setUserCode(userCode);
///* 13135 */                 json1 = (BoFloor)this.boFloorService.save(boFloor);
/*       */               } else {
/* 13137 */                 List list = this.boFloorService.get(boFloor.getFloorCode());
/* 13138 */                 for (isFirst = list.iterator(); ((Iterator)isFirst).hasNext(); ) { BoFloor boFloor2 = (BoFloor)((Iterator)isFirst).next();
/* 13139 */                   ((BoFloor)boFloor2).setFloorName(boFloor.getFloorName());
/* 13140 */                   this.boFloorService.update(boFloor2);
/*       */                 }
/*       */ 
/*       */               }
/*       */ 
/*       */             }
/*       */ 
/* 13151 */             json1 = JSONArray.fromObject(this.roomInfo);
/* 13152 */             System.err.println(this.roomInfo);
/* 13153 */             List personss = (List)JSONArray.toCollection(json1, BoRoom.class);
/*       */ 
/* 13160 */             for (Object boFloor2 = personss.iterator(); ((Iterator)boFloor2).hasNext(); ) { BoRoom boRoom = (BoRoom)((Iterator)boFloor2).next();
/* 13161 */               if (boRoom.getRoomCode().equals("")) {
/* 13162 */                 BoFloor floor2 = this.boFloorService.findByFloorName(userCode, boRoom.getFloorName());
/* 13163 */                 boRoom.setRoomName(boRoom.getRoomName());
/* 13164 */                 boRoom.setFloorCode(floor2.getFloorCode());
/* 13165 */                 boRoom.setUserCode(userCode);
/* 13166 */                 this.boRoomService.save(boRoom);
/*       */               } else {
/* 13168 */                 lists = this.boRoomService.get(boRoom.getRoomCode());
/* 13169 */                 System.err.println(((JSONArray) lists).size());
/* 13170 */                 System.err.println("// " + boRoom.getRoomCode());
/* 13171 */                 if (((JSONArray) lists).size() > 0) {
/* 13172 */                   for (lists = ((List) lists).iterator(); ((Iterator)lists).hasNext(); ) { BoRoom boRoom2 = (BoRoom)((Iterator)lists).next();
/* 13173 */                     boRoom2.setRoomName(boRoom.getRoomName());
/* 13174 */                     this.boRoomService.update(boRoom2);
/*       */                   }
/*       */                 }
/*       */               }
/*       */ 
/*       */             }
/*       */ 
/* 13181 */             isFirst = boUsers.getIsFirst();
/* 13182 */             if (isFirst == this.fid) {
/* 13183 */               boUsers.setIsFirst(this.fid1);
/* 13184 */               this.boUserServicess.update(boUsers);
/*       */             }
/*       */ 
/* 13192 */             this.requestJson.setData(map);
/*       */ 
/* 13194 */             return "success";
/*       */           }
/*       */         } else {
/* 13197 */           this.requestJson.setMessage("超时了");
/* 13198 */           this.requestJson.setSuccess(false);
/*       */         }
/*       */       }
/*       */       else {
/* 13202 */         System.err.println("验证不通过");
/* 13203 */         this.requestJson.setMessage("验证不通过");
/* 13204 */         this.requestJson.setSuccess(false);
/*       */       }
/*       */     }
/*       */ 
/* 13208 */     return "success";
/*       */   }
/*       */
/*       */   @Action(value="commad", results={@org.apache.struts2.convention.annotation.Result(type="json", params={"root", "requestJson"})})
/*       */   public String commad()
/*       */   {//主机在线、离线
	            System.out.println("····进入commad方法····");
/* 13223 */     this.requestJson = new RequestJson();
/* 13224 */     HttpServletRequest request = ServletActionContext.getRequest();
/* 13225 */     Map map = new HashMap();
/* 13226 */     String strs = "";
/* 13227 */     Md5 md5 = new Md5();
/* 13228 */     String header = request.getHeader("timestamp");
/* 13229 */     String header2 = request.getHeader("nonce");
/* 13230 */     String header3 = request.getHeader("sign");
/* 13231 */     String header4 = request.getHeader("access_token");
/* 13232 */     String userCode = request.getHeader("userCode");
			    System.err.println("userCode>--> " + userCode);
/*       */ 
/* 13234 */     System.err.println(">-- " + header2);
/*       */ 
/* 13237 */     strs = strs + "access_token=";
/* 13238 */     strs = strs + header4;
/*       */ 
/* 13240 */     strs = strs + "&nonce=";
/* 13241 */     strs = strs + header2;
/*       */ 
/* 13243 */     strs = strs + "&timestamp=";
/* 13244 */     strs = strs + header;
/*       */ 
/* 13246 */     strs = strs + "&userCode=";
/* 13247 */     strs = strs + userCode;
/*       */ 
/* 13249 */     if (userCode.contains(",")) {//userCode有逗号的情况 >-- c24ef3aebb0c49f99642e62eb029a412,18038035290
/* 13250 */       String[] userCode2 = userCode.split(",");
/* 13251 */       BoUsers boUsers = this.boUserServicess.findByUserUserCode(userCode2[0].trim().toString());
/* 13252 */       BoUsers phone = this.boUserServicess.findByUserPhone(userCode2[1].trim().toString());
/* 13253 */       packNum(userCode2[0].trim().toString());
/* 13254 */       strs = strs + "12345";
/* 13255 */       String sign = md5.getMD5ofStr(strs).toLowerCase();
/* 13256 */       System.err.println("sign>--> " + sign);
//				  System.err.println("userCode2[0]>--> " + userCode2[0]);
//				  System.err.println("this.command " + this.command);//100
/* 13257 */       if (header3.equals(sign)) {
/* 13258 */         if (header4.equals(phone.getAccessToken()))
/*       */         {
/* 13261 */           Long accessToken = Long.valueOf(header);
/*       */ 
/* 13263 */           if (accessToken.longValue() < Long.valueOf(phone.getAccessTokenTime()).longValue())
/*       */           {
/* 13266 */             final BoHostDevice device = this.boHostDeviceService.findBydeviceAddress(userCode2[0].trim().toString(), this.deviceAddress);
/*       */ 
/* 13268 */             if (device != null) {
/* 13269 */               int i = 0;
/* 13270 */               if (device.getDeviceType().equals("1"))
/*       */               {
/* 13272 */                 Integer commands = null;
/* 13273 */                 if (this.command.intValue() == 0) {
/* 13274 */                   commands = this.command;
/*       */                 }
/*       */ 
/* 13277 */                 if (this.command.intValue() == 100) {
/* 13278 */                   commands = Integer.valueOf(1);
/*       */                 }
/* 13280 */                 String deviceAddress2 = device.getDeviceAddress();
/* 13281 */                 String substring = deviceAddress2.substring(0, deviceAddress2.length() - 1);
/* 13282 */                 String substring2 = deviceAddress2.substring(deviceAddress2.length() - 1, deviceAddress2.length());
/* 13283 */                 final String string = commands.toString();
/*       */ 
/* 13285 */                 if (substring2.equals("1"))
/* 13286 */                   this.add = (substring + 1);
/* 13287 */                 else if (substring2.equals("2"))
/* 13288 */                   this.add = (substring + 2);
/* 13289 */                 else if (substring2.equals("3")) {
/* 13290 */                   this.add = (substring + 3);
/*       */                 }
/*       */                 try
/*       */                 {
/* 13294 */                   this.resendVerification = this.boResendVerificationService.find(device.getBoDevice().getDeviceCode(), 
/* 13295 */                     this.add, string);
/*       */ 
/* 13297 */                   if (this.resendVerification == null)
/*       */                   {
/* 13299 */                     BoResendVerification resend = new BoResendVerification();
/* 13300 */                     resend.setBoDevice(device.getBoDevice());
/* 13301 */                     resend.setDeviceAddress(this.add);
/* 13302 */                     resend.setDeviceType(device.getDeviceType());
/* 13303 */                     resend.setCommand(string);
/* 13304 */                     resend.setAcceptState("wait");
/* 13305 */                     this.save = ((BoResendVerification)this.boResendVerificationService.save(resend));
/*       */                   }
/*       */ 
/* 13308 */                   String strss = "ZIGBEE_LIGHT-SEND-" + user_num.get(userCode2[0].trim().toString()) + "," + substring + "," + 
/* 13309 */                     substring2 + "," + commands;
/* 13310 */                   byte[] bss = strss.getBytes();
/* 13311 */                   System.err.println(new String(bss));
/* 13312 */                   this.packetProcessHelper.processSendDDatas(device.getBoDevice().getDeviceCode(), bss);
/* 13313 */                   String flag = "";
/* 13314 */                   Timer timer = new Timer();
/*       */ 
/* 13316 */                   timer.schedule(new TimerTask() {
/*       */                     public void run() {
/* 13318 */                       BoResendVerification resendVerificationss = XingUserAction.this.boResendVerificationService
/* 13319 */                         .find(device.getBoDevice().getDeviceCode(), XingUserAction.this.add, string);
/* 13320 */                       XingUserAction.this.readBackStatus = resendVerificationss.getAcceptState();
/* 13321 */                       System.err.println(XingUserAction.this.readBackStatus);
/*       */                     }
/*       */                   }
/*       */                   , 300L, 300L);
/* 13324 */                   while (i < 40)
/*       */                   {
/* 13326 */                     if (this.readBackStatus.equals("OK"))
/*       */                       break;
/* 13328 */                     if (this.readBackStatus.equals("ERR"))
/*       */                       break;
/* 13330 */                     if ((this.readBackStatus.equals("1")) && (flag == "")) {
/* 13331 */                       String str = "ZIGBEE_LIGHT-SEND-" + user_num.get(userCode2[0].trim().toString()) + "," + substring + "," + 
/* 13332 */                         substring2 + "," + commands;
/* 13333 */                       byte[] bs = str.getBytes();
/*       */ 
/* 13335 */                       this.packetProcessHelper.processSendDDatas(device.getBoDevice().getDeviceCode(), bs);
/* 13336 */                       flag = "1";
/* 13337 */                     } else if ((this.readBackStatus.equals("2")) && (flag == "1")) {
/* 13338 */                       String str = "ZIGBEE_LIGHT-SEND-" + user_num.get(userCode2[0].trim().toString()) + "," + substring + "," + 
/* 13339 */                         substring2 + "," + commands;
/* 13340 */                       byte[] bs = str.getBytes();
/*       */ 
/* 13342 */                       this.packetProcessHelper.processSendDDatas(device.getBoDevice().getDeviceCode(), bs);
/* 13343 */                       flag = "2";
/* 13344 */                     } else if (((this.readBackStatus.equals("wait")) && (i == 3)) || (
/* 13345 */                       (this.readBackStatus.equals("wait")) && (i == 7))) {
/* 13346 */                       String str = "ZIGBEE_LIGHT-SEND-" + user_num.get(userCode2[0].trim().toString()) + "," + substring + "," + 
/* 13347 */                         substring2 + "," + commands;
/* 13348 */                       byte[] bs = str.getBytes();
/*       */ 
/* 13350 */                       this.packetProcessHelper.processSendDDatas(device.getBoDevice().getDeviceCode(), bs);
/*       */                     }
/*       */ 
/* 13353 */                     Thread.sleep(300L);
/*       */ 
/* 13355 */                     i++;
/*       */                   }
/*       */ 
/* 13358 */                   timer.cancel();
/* 13359 */                   this.requestJson.setSuccess(true);
/*       */ 
/* 13361 */                   this.boResendVerificationService.delete(this.save);
/*       */                 }
/*       */                 catch (InterruptedException localInterruptedException)
/*       */                 {
/*       */                 }
/*       */ 
/*       */               }
/*       */ 
/* 13369 */               if (device.getDeviceType().equals("999")) {
/* 13370 */                 String str = "DOOR_LOCK-SEND-" + user_num.get(userCode2[0].trim().toString()) + "," + 1;
/* 13371 */                 byte[] bs = str.getBytes();
/* 13372 */                 System.err.println(new String(bs));
/* 13373 */                 this.packetProcessHelper.processSendDData(device.getBoDevice().getDeviceCode(), bs);
/* 13374 */                 return "success";
/*       */               }
/*       */ 
/* 13377 */               if (device.getDeviceType().equals("2")) {
/* 13378 */                 System.err.println("sss");
/* 13379 */                 Integer commands = null;
/* 13380 */                 if (this.command.intValue() == 0) {
/* 13381 */                   commands = Integer.valueOf(19);
/*       */                 }
/*       */ 
/* 13384 */                 if (this.command.intValue() == 100)
/* 13385 */                   commands = Integer.valueOf(17);
/*       */                 else
/* 13387 */                   commands = Integer.valueOf(20);

/*       */                 final int commandss;
/* 13390 */                 if (this.command.intValue() >= 95)
/* 13391 */                   commandss = 95;
/*       */                 else {
/* 13393 */                   commandss = this.command.intValue();
/*       */                 }
/*       */                 try
/*       */                 {
/* 13397 */                   this.resendVerification = this.boResendVerificationService.find(device.getBoDevice().getDeviceCode(), 
/* 13398 */                     device.getDeviceAddress(), commandss+"");
/*       */ 
/* 13400 */                   if (this.resendVerification == null)
/*       */                   {
/* 13402 */                     BoResendVerification resend = new BoResendVerification();
/* 13403 */                     resend.setBoDevice(device.getBoDevice());
/* 13404 */                     resend.setDeviceAddress(device.getDeviceAddress());
/* 13405 */                     resend.setDeviceType(device.getDeviceType());
/* 13406 */                     resend.setCommand(commandss+"");
/* 13407 */                     resend.setAcceptState("wait");
/* 13408 */                     this.save = ((BoResendVerification)this.boResendVerificationService.save(resend));
/*       */                   }
/*       */ 
/* 13412 */                   String str = "ZIGBEE_CURTAIN-SEND-" + user_num.get(userCode2[0].trim().toString()) + "," + 
/* 13413 */                     device.getDeviceAddress() + "," + commands + "," + commandss;
/* 13414 */                   System.err.println(commands + ">-- " + this.command);
/* 13415 */                   byte[] bs = str.getBytes();
/* 13416 */                   System.err.println(new String(bs));
/* 13417 */                   this.packetProcessHelper.processSendDData(device.getBoDevice().getDeviceCode(), bs);
/* 13418 */                   String flag = "";
/* 13419 */                   Timer timer = new Timer();
/*       */ 
/* 13421 */                   timer.schedule(new TimerTask() {
/*       */                     public void run() {
/* 13423 */                       BoResendVerification resendVerificationss = XingUserAction.this.boResendVerificationService.find(
/* 13424 */                         device.getBoDevice().getDeviceCode(), device.getDeviceAddress(), 
/* 13425 */                         commandss+"");
/* 13426 */                       XingUserAction.this.readBackStatus = resendVerificationss.getAcceptState();
/*       */                     }
/*       */                   }
/*       */                   , 300L, 300L);
/*       */ 
/* 13431 */                   while (i < 40) {
/* 13432 */                     System.err.println("readBackStatus" + this.readBackStatus);
/* 13433 */                     if (this.readBackStatus.equals("OK")) {
/*       */                       break;
/*       */                     }
/* 13436 */                     if (this.readBackStatus.equals("ERR"))
/*       */                       break;
/* 13438 */                     if ((this.readBackStatus.equals("1")) && (flag == "")) {
/* 13439 */                       String strss = "ZIGBEE_CURTAIN-SEND-" + user_num.get(userCode2[0].trim().toString()) + "," + 
/* 13440 */                         device.getDeviceAddress() + "," + commands + "," + commandss;
/* 13441 */                       System.err.println(commands + ">-- " + this.command);
/* 13442 */                       byte[] bss = strss.getBytes();
/* 13443 */                       System.err.println(new String(bss));
/* 13444 */                       this.packetProcessHelper.processSendDData(device.getBoDevice().getDeviceCode(), bss);
/* 13445 */                       flag = "1";
/* 13446 */                     } else if ((this.readBackStatus.equals("2")) && (flag == "1")) {
/* 13447 */                       String strss = "ZIGBEE_CURTAIN-SEND-" + user_num.get(userCode2[0].trim().toString()) + "," + 
/* 13448 */                         device.getDeviceAddress() + "," + commands + "," + commandss;
/* 13449 */                       System.err.println(commands + ">-- " + this.command);
/* 13450 */                       byte[] bss = strss.getBytes();
/* 13451 */                       System.err.println(new String(bss));
/* 13452 */                       this.packetProcessHelper.processSendDData(device.getBoDevice().getDeviceCode(), bss);
/* 13453 */                       flag = "2";
/* 13454 */                     } else if (((this.readBackStatus.equals("wait")) && (i == 3)) || (
/* 13455 */                       (this.readBackStatus.equals("wait")) && (i == 7))) {
/* 13456 */                       String strss = "ZIGBEE_CURTAIN-SEND-" + user_num.get(userCode2[0].trim().toString()) + "," + 
/* 13457 */                         device.getDeviceAddress() + "," + commands + "," + commandss;
/* 13458 */                       System.err.println(commands + ">-- " + this.command);
/* 13459 */                       byte[] bss = strss.getBytes();
/* 13460 */                       System.err.println(new String(bs));
/* 13461 */                       this.packetProcessHelper.processSendDData(device.getBoDevice().getDeviceCode(), bss);
/*       */                     }
/*       */ 
/* 13464 */                     Thread.sleep(300L);
/*       */ 
/* 13466 */                     i++;
/*       */                   }
/* 13468 */                   timer.cancel();
/* 13469 */                   this.requestJson.setSuccess(true);
/*       */ 
/* 13471 */                   this.boResendVerificationService.delete(this.save);
/*       */                 }
/*       */                 catch (InterruptedException localInterruptedException1)
/*       */                 {
/*       */                 }
/*       */ 
/*       */               }
/*       */ 
/* 13479 */               if (device.getDeviceType().equals("4")) {
/* 13480 */                 Integer commands = null;
/* 13481 */                 if (this.command.intValue() == 0) {
/* 13482 */                   commands = Integer.valueOf(19);
/*       */                 }
/*       */ 
/* 13485 */                 if (this.command.intValue() == 100)
/* 13486 */                   commands = Integer.valueOf(17);
/*       */                 else {
/* 13488 */                   commands = Integer.valueOf(20);
/*       */                 }
/* 13490 */                 String str = "ZIGBEE_DIMMER-SEND-" + user_num.get(userCode2[0].trim().toString()) + "," + device.getDeviceAddress() + 
/* 13491 */                   "," + this.command;
/* 13492 */                 System.err.println(commands + ">-- " + this.command);
/* 13493 */                 byte[] bs = str.getBytes();
/* 13494 */                 System.err.println(new String(bs));
/* 13495 */                 this.packetProcessHelper.processSendDData(device.getBoDevice().getDeviceCode(), bs);
/*       */               }
/*       */ 				
						//2-8
						if (device.getDeviceType().equals("7")) {
						    int command_type = 1;
						    Integer commands = null;
						    if (this.command.intValue() == 0) {
						      command_type = 1;
						      commands = Integer.valueOf(0);
						    }
						
						    if (this.command.intValue() == 1) {
						      commands = Integer.valueOf(1);
						      command_type = 1;
						    }
						
						    if ((this.command.intValue() >= 100) && (this.command.intValue() <= 200))
						    {
						      commands = Integer.valueOf(this.command.intValue() - 100);
						      command_type = 2;
						    }
						
						    if ((this.command.intValue() >= 300) && (this.command.intValue() <= 400))
						    {
						      commands = Integer.valueOf(this.command.intValue() - 300);
						      command_type = 3;
						    }
						
						    String str = "ZIGBEE_COLOR-SEND-" + user_num.get(userCode2[0].trim().toString()) + "," + device.getDeviceAddress() + 
						      "," + command_type + "," + commands;
						    System.err.println(commands + ">-- " + this.command + ">--" + command_type);
						    byte[] bs = str.getBytes();
						    System.err.println(new String(bs));
						    this.packetProcessHelper.processSendDData(device.getBoDevice().getDeviceCode(), bs);
						  }
						//end

						//2-1
						if (device.getDeviceAddress().toString().length() == 8) {
						    String deviceType = device.getDeviceType().toString();
						    String substring = deviceType.substring(1, 2);
						    Integer valueOf = Integer.valueOf(substring);
						    String s_code = null;
						    if (valueOf.intValue() == 1) {
						      s_code = "2262";
						    } else if (valueOf.intValue() == 3) {
						      int i_deviceType = 0;
						      try { 
						    	  i_deviceType = Integer.valueOf(deviceType).intValue(); 
						      } catch (Exception localException) 
						      		{
						      		}
						      if ((i_deviceType >= 4000) && (i_deviceType <= 4999))
						      {
						        s_code = "DYDJ";
						      } else if ((i_deviceType >= 5000) && (i_deviceType <= 5999))
						      {
						        s_code = "ZNMS";
						      }
//						      System.out.println("------i_deviceType:" + i_deviceType + " s_code:" + s_code);
						      logger.info("------i_deviceType:" + i_deviceType + " s_code:" + s_code);
						    } else {
						      s_code = "1527";
						    }
						
						    String substring2 = deviceType.substring(2, 3);
						    Integer valueOf2 = Integer.valueOf(substring2);
						    Integer b = null;
						    if (valueOf2.intValue() == 1)
						      b = Integer.valueOf(315);
						    else {
						      b = Integer.valueOf(433);
						    }
						
						    String substring3 = deviceType.substring(3, 4);
						    Integer valueOf3 = Integer.valueOf(substring3);
						    Integer c = null;
						    if (valueOf3.intValue() == 1)
						      c = Integer.valueOf(12);
						    else if (valueOf3.intValue() == 2)
						      c = Integer.valueOf(15);
						    else if (valueOf3.intValue() == 3)
						      c = Integer.valueOf(22);
						    else if (valueOf3.intValue() == 4)
						      c = Integer.valueOf(33);
						    else if (valueOf3.intValue() == 5)
						      c = Integer.valueOf(47);
						    else if (valueOf3.intValue() == 6)
						      c = Integer.valueOf(330);
						    else if (valueOf3.intValue() == 7)
						      c = Integer.valueOf(390);
						    else if (valueOf3.intValue() == 8) {
						      c = Integer.valueOf(200);
						    }
						
						    if (this.command.intValue() == 0) {
						      logger.info("this.command == 0");
						      System.err.println(device.getDeviceAddress());
						      String string = device.getDeviceAddress().substring(0, 1);
						      String string2 = device.getDeviceAddress().substring(1, 8);
						
						      if (string.equals("0"))
						        this.ln = string2;
						      else {
						        this.ln = device.getDeviceAddress();
						      }
						
						      String str = "PT" + s_code + "_" + b + "M-SEND-" + user_num.get(userCode2[0].trim().toString()) + "," + c + "," + 
						        this.ln + 2;
						      byte[] bs = str.getBytes();
//						      System.err.println("<?> " + new String(bs));
						      logger.debug("<?> " + new String(bs));
						      this.packetProcessHelper.processSendDData(device.getBoDevice().getDeviceCode(), bs);
						    }
						
						    if (this.command.intValue() == 50)
						    {
						    	logger.info("this.command == 50");
						      String str = "PT" + s_code + "_" + b + "M-SEND-" + user_num.get(userCode2[0].trim().toString()) + "," + c + "," + 
						        device.getDeviceAddress() + 3;
						      byte[] bs = str.getBytes();
//						      System.err.println(new String(bs));
						      logger.info("<?> " + new String(bs));
						      this.packetProcessHelper.processSendDData(device.getBoDevice().getDeviceCode(), bs);
						    }
						
						    if (this.command.intValue() == 100) {
						      logger.info("this.command == 100");
						      String string = device.getDeviceAddress().substring(0, 1);
						      String string2 = device.getDeviceAddress().substring(1, 8);
						
						      if (string.equals("0"))
						        this.ln = string2;
						      else {
						        this.ln = device.getDeviceAddress();
						      }
						
						      String str = "PT" + s_code + "_" + b + "M-SEND-" + user_num.get(userCode2[0].trim().toString()) + "," + c + "," + 
						        this.ln + 1;
						      byte[] bs = str.getBytes();
//						      System.err.println(new String(bs));
						      logger.info("<?> " + new String(bs));
						      this.packetProcessHelper.processSendDData(device.getBoDevice().getDeviceCode(), bs);
						    }
						
						    if (this.command.intValue() == 150)
						    {
						      logger.info("this.command == 150");
						      String str = "PT" + s_code + "_" + b + "M-SEND-" + user_num.get(userCode2[0].trim().toString()) + "," + c + "," + 
						        device.getDeviceAddress() + 4;
						      byte[] bs = str.getBytes();
//						      System.err.println(new String(bs));
						      logger.info("<?> " + new String(bs));
						      this.packetProcessHelper.processSendDData(device.getBoDevice().getDeviceCode(), bs);
						    }
						  }
						//2018-3-6 setSuccess(true)时 设置Mseeage="主机昵称（状态）"
						String deviceCode=device.getBoDevice().getDeviceCode();
						int status=device.getBoDevice().getStatus();
						String host_status="离线";
						if(status == 1) {
							host_status="在线";
						}
						List<BoUserDevices> boUs=this.boUserDevicesServicess.getListByDeviceCode(deviceCode);
						String nickName=boUs.get(0).getNickName();
						logger.info("nickName=="+nickName);
						String msg=nickName+","+host_status;
						logger.info("msg=="+msg);
						this.requestJson.setMessage(msg);
						//END
/*       */             }
/*       */             else
/*       */             {
/* 13590 */               this.requestJson.setMessage("没有找到你要找的设备地址");
/* 13591 */               this.requestJson.setData(map);
/* 13592 */               this.requestJson.setSuccess(false);
/*       */             }
/*       */           } else {
/* 13595 */             this.requestJson.setData(map);
/* 13596 */             this.requestJson.setMessage("超时了");
/* 13597 */             this.requestJson.setSuccess(false);
/*       */           }
/*       */         } else {
/* 13600 */           System.err.println("超时了");
/* 13601 */           this.requestJson.setData(map);
/* 13602 */           this.requestJson.setMessage("超时了");
/* 13603 */           this.requestJson.setSuccess(false);
/*       */         }
/*       */       }
/*       */       else {
/* 13607 */         this.requestJson.setMessage("验证不通过");
/* 13608 */         this.requestJson.setSuccess(false);
/*       */       }
/*       */     } else {
/* 13611 */       if (user_num.get(userCode) == null)
/* 13612 */         user_num.put(userCode, Integer.valueOf(0));
/*       */       else {
/* 13614 */         user_num.put(userCode, Integer.valueOf(((Integer)user_num.get(userCode)).intValue() == 255 ? 0 : ((Integer)user_num.get(userCode)).intValue() + 1));
/*       */       }
/* 13616 */       strs = strs + "12345";
/* 13617 */       String sign = md5.getMD5ofStr(strs).toLowerCase();
/* 13618 */       System.err.println("sign>--> " + sign);
/* 13619 */       if (header3.equals(sign))
/*       */       {
/* 13623 */         Long accessToken = Long.valueOf(header);
/* 13624 */         BoUsers boUsers = this.boUserServicess.findByUserUserCode(userCode);
/* 13625 */         if (accessToken.longValue() < Long.valueOf(boUsers.getAccessTokenTime()).longValue())
/*       */         {
/* 13628 */           final BoHostDevice device = this.boHostDeviceService.findBydeviceAddress(userCode, this.deviceAddress);
/*       */ 
/* 13630 */           if (device != null) {
/* 13631 */             int i = 0;
/* 13632 */             if (device.getDeviceType().equals("1"))
/*       */             {
/* 13634 */               Integer commands = null;
/* 13635 */               if (this.command.intValue() == 0) {
/* 13636 */                 commands = this.command;
/*       */               }
/*       */ 
/* 13639 */               if (this.command.intValue() == 100) {
/* 13640 */                 commands = Integer.valueOf(1);
/*       */               }
/* 13642 */               String deviceAddress2 = device.getDeviceAddress();
/* 13643 */               String substring = deviceAddress2.substring(0, deviceAddress2.length() - 1);
/* 13644 */               String substring2 = deviceAddress2.substring(deviceAddress2.length() - 1, deviceAddress2.length());
/* 13645 */               final String string = commands.toString();
/*       */ 
/* 13647 */               if (substring2.equals("1"))
/* 13648 */                 this.add = (substring + 1);
/* 13649 */               else if (substring2.equals("2"))
/* 13650 */                 this.add = (substring + 2);
/* 13651 */               else if (substring2.equals("3")) {
/* 13652 */                 this.add = (substring + 3);
/*       */               }
/*       */               try
/*       */               {
/* 13656 */                 this.resendVerification = this.boResendVerificationService.find(device.getBoDevice().getDeviceCode(), 
/* 13657 */                   this.add, string);
/*       */ 
/* 13659 */                 if (this.resendVerification == null)
/*       */                 {
/* 13661 */                   BoResendVerification resend = new BoResendVerification();
/* 13662 */                   resend.setBoDevice(device.getBoDevice());
/* 13663 */                   resend.setDeviceAddress(this.add);
/* 13664 */                   resend.setDeviceType(device.getDeviceType());
/* 13665 */                   resend.setCommand(string);
/* 13666 */                   resend.setAcceptState("wait");
/* 13667 */                   this.save = ((BoResendVerification)this.boResendVerificationService.save(resend));
/*       */                 }
/*       */ 
/* 13670 */                 String strss = "ZIGBEE_LIGHT-SEND-" + user_num.get(userCode) + "," + substring + "," + 
/* 13671 */                   substring2 + "," + commands;
/* 13672 */                 byte[] bss = strss.getBytes();
/* 13673 */                 System.err.println(new String(bss));
/* 13674 */                 this.packetProcessHelper.processSendDDatas(device.getBoDevice().getDeviceCode(), bss);
/* 13675 */                 this.packetProcessHelper.setUserCode(userCode);
/* 13676 */                 String flag = "";
/* 13677 */                 Timer timer = new Timer();
/*       */ 
/* 13679 */                 timer.schedule(new TimerTask() {
/*       */                   public void run() {
/* 13681 */                     BoResendVerification resendVerificationss = XingUserAction.this.boResendVerificationService
/* 13682 */                       .find(device.getBoDevice().getDeviceCode(), XingUserAction.this.add, string);
/* 13683 */                     XingUserAction.this.readBackStatus = resendVerificationss.getAcceptState();
/* 13684 */                     System.err.println(XingUserAction.this.readBackStatus);
/*       */                   }
/*       */                 }
/*       */                 , 300L, 300L);
/* 13687 */                 while (i < 40)
/*       */                 {
/* 13689 */                   if (this.readBackStatus.equals("OK"))
/*       */                     break;
/* 13691 */                   if (this.readBackStatus.equals("ERR"))
/*       */                     break;
/* 13693 */                   if ((this.readBackStatus.equals("1")) && (flag == "")) {
/* 13694 */                     String str = "ZIGBEE_LIGHT-SEND-" + user_num.get(userCode) + "," + substring + "," + 
/* 13695 */                       substring2 + "," + commands;
/* 13696 */                     byte[] bs = str.getBytes();
/*       */ 
/* 13698 */                     this.packetProcessHelper.processSendDDatas(device.getBoDevice().getDeviceCode(), bs);
/* 13699 */                     this.packetProcessHelper.setUserCode(userCode);
/* 13700 */                     flag = "1";
/* 13701 */                   } else if ((this.readBackStatus.equals("2")) && (flag == "1")) {
/* 13702 */                     String str = "ZIGBEE_LIGHT-SEND-" + user_num.get(userCode) + "," + substring + "," + 
/* 13703 */                       substring2 + "," + commands;
/* 13704 */                     byte[] bs = str.getBytes();
/*       */ 
/* 13706 */                     this.packetProcessHelper.processSendDDatas(device.getBoDevice().getDeviceCode(), bs);
/* 13707 */                     this.packetProcessHelper.setUserCode(userCode);
/* 13708 */                     flag = "2";
/* 13709 */                   } else if (((this.readBackStatus.equals("wait")) && (i == 3)) || (
/* 13710 */                     (this.readBackStatus.equals("wait")) && (i == 7))) {
/* 13711 */                     String str = "ZIGBEE_LIGHT-SEND-" + user_num.get(userCode) + "," + substring + "," + 
/* 13712 */                       substring2 + "," + commands;
/* 13713 */                     byte[] bs = str.getBytes();
/*       */ 
/* 13715 */                     this.packetProcessHelper.processSendDDatas(device.getBoDevice().getDeviceCode(), bs);
/* 13716 */                     this.packetProcessHelper.setUserCode(userCode);
/*       */                   }
/*       */ 
/* 13719 */                   Thread.sleep(300L);
/*       */ 
/* 13721 */                   i++;
/*       */                 }
/*       */ 
/* 13724 */                 timer.cancel();
/* 13725 */                 this.requestJson.setSuccess(true);
/*       */ 
/* 13727 */                 this.boResendVerificationService.delete(this.save);
/*       */               }
/*       */               catch (InterruptedException localInterruptedException2)
/*       */               {
/*       */               }
/*       */ 
/*       */             }
/*       */ 
/* 13735 */             if (device.getDeviceType().equals("999")) {
/* 13736 */               String str = "DOOR_LOCK-SEND-" + user_num.get(userCode) + "," + 1;
/* 13737 */               byte[] bs = str.getBytes();
/* 13738 */               System.err.println(new String(bs));
/* 13739 */               this.packetProcessHelper.processSendDData(device.getBoDevice().getDeviceCode(), bs);
/* 13740 */               this.packetProcessHelper.setUserCode(userCode);
/* 13741 */               return "success";
/*       */             }
/*       */ 
/* 13744 */             if (device.getDeviceType().equals("2")) {
/* 13745 */               System.err.println("sss");
/* 13746 */               Integer commands = null;
/* 13747 */               if (this.command.intValue() == 0) {
/* 13748 */                 commands = Integer.valueOf(19);
/*       */               }
/*       */ 
/* 13751 */               if (this.command.intValue() == 100)
/* 13752 */                 commands = Integer.valueOf(17);
/*       */               else
/* 13754 */                 commands = Integer.valueOf(20);

/*       */               final int commandss;
/* 13757 */               if (this.command.intValue() >= 95)
/* 13758 */                 commandss = 95;
/*       */               else {
/* 13760 */                 commandss = this.command.intValue();
/*       */               }
/*       */               try
/*       */               {
/* 13764 */                 this.resendVerification = this.boResendVerificationService.find(device.getBoDevice().getDeviceCode(), 
/* 13765 */                   device.getDeviceAddress(), commandss+"");
/*       */ 
/* 13767 */                 if (this.resendVerification == null)
/*       */                 {
/* 13769 */                   BoResendVerification resend = new BoResendVerification();
/* 13770 */                   resend.setBoDevice(device.getBoDevice());
/* 13771 */                   resend.setDeviceAddress(device.getDeviceAddress());
/* 13772 */                   resend.setDeviceType(device.getDeviceType());
/* 13773 */                   resend.setCommand(commandss+"");
/* 13774 */                   resend.setAcceptState("wait");
/* 13775 */                   this.save = ((BoResendVerification)this.boResendVerificationService.save(resend));
/*       */                 }
/*       */ 
/* 13779 */                 String str = "ZIGBEE_CURTAIN-SEND-" + user_num.get(userCode) + "," + 
/* 13780 */                   device.getDeviceAddress() + "," + commands + "," + commandss;
/* 13781 */                 System.err.println(commands + ">-- " + this.command);
/* 13782 */                 byte[] bs = str.getBytes();
/* 13783 */                 System.err.println(new String(bs));
/* 13784 */                 this.packetProcessHelper.processSendDData(device.getBoDevice().getDeviceCode(), bs);
/* 13785 */                 this.packetProcessHelper.setUserCode(userCode);
/* 13786 */                 String flag = "";
/* 13787 */                 Timer timer = new Timer();
/*       */ 
/* 13789 */                 timer.schedule(new TimerTask() {
/*       */                   public void run() {
/* 13791 */                     BoResendVerification resendVerificationss = XingUserAction.this.boResendVerificationService.find(
/* 13792 */                       device.getBoDevice().getDeviceCode(), device.getDeviceAddress(), 
/* 13793 */                       commandss+"");
/* 13794 */                     XingUserAction.this.readBackStatus = resendVerificationss.getAcceptState();
/*       */                   }
/*       */                 }
/*       */                 , 300L, 300L);
/*       */ 
/* 13799 */                 while (i < 40) {
/* 13800 */                   System.err.println("readBackStatus" + this.readBackStatus);
/* 13801 */                   if (this.readBackStatus.equals("OK")) {
/*       */                     break;
/*       */                   }
/* 13804 */                   if (this.readBackStatus.equals("ERR"))
/*       */                     break;
/* 13806 */                   if ((this.readBackStatus.equals("1")) && (flag == "")) {
/* 13807 */                     String strss = "ZIGBEE_CURTAIN-SEND-" + user_num.get(userCode) + "," + 
/* 13808 */                       device.getDeviceAddress() + "," + commands + "," + commandss;
/* 13809 */                     System.err.println(commands + ">-- " + this.command);
/* 13810 */                     byte[] bss = strss.getBytes();
/* 13811 */                     System.err.println(new String(bss));
/* 13812 */                     this.packetProcessHelper.processSendDData(device.getBoDevice().getDeviceCode(), bss);
/* 13813 */                     this.packetProcessHelper.setUserCode(userCode);
/* 13814 */                     flag = "1";
/* 13815 */                   } else if ((this.readBackStatus.equals("2")) && (flag == "1")) {
/* 13816 */                     String strss = "ZIGBEE_CURTAIN-SEND-" + user_num.get(userCode) + "," + 
/* 13817 */                       device.getDeviceAddress() + "," + commands + "," + commandss;
/* 13818 */                     System.err.println(commands + ">-- " + this.command);
/* 13819 */                     byte[] bss = strss.getBytes();
/* 13820 */                     System.err.println(new String(bss));
/* 13821 */                     this.packetProcessHelper.processSendDData(device.getBoDevice().getDeviceCode(), bss);
/* 13822 */                     this.packetProcessHelper.setUserCode(userCode);
/* 13823 */                     flag = "2";
/* 13824 */                   } else if (((this.readBackStatus.equals("wait")) && (i == 3)) || (
/* 13825 */                     (this.readBackStatus.equals("wait")) && (i == 7))) {
/* 13826 */                     String strss = "ZIGBEE_CURTAIN-SEND-" + user_num.get(userCode) + "," + 
/* 13827 */                       device.getDeviceAddress() + "," + commands + "," + commandss;
/* 13828 */                     System.err.println(commands + ">-- " + this.command);
/* 13829 */                     byte[] bss = strss.getBytes();
/* 13830 */                     System.err.println(new String(bs));
/* 13831 */                     this.packetProcessHelper.processSendDData(device.getBoDevice().getDeviceCode(), bss);
/* 13832 */                     this.packetProcessHelper.setUserCode(userCode);
/*       */                   }
/*       */ 
/* 13835 */                   Thread.sleep(300L);
/*       */ 
/* 13837 */                   i++;
/*       */                 }
/* 13839 */                 timer.cancel();
/* 13840 */                 this.requestJson.setSuccess(true);
/*       */ 
/* 13842 */                 this.boResendVerificationService.delete(this.save);
/*       */               }
/*       */               catch (InterruptedException localInterruptedException3)
/*       */               {
/*       */               }
/*       */ 
/*       */             }
/*       */ 
/* 13850 */             if (device.getDeviceType().equals("4")) {
/* 13851 */               Integer commands = null;
/* 13852 */               if (this.command.intValue() == 0) {
/* 13853 */                 commands = Integer.valueOf(19);
/*       */               }
/*       */ 
/* 13856 */               if (this.command.intValue() == 100)
/* 13857 */                 commands = Integer.valueOf(17);
/*       */               else {
/* 13859 */                 commands = Integer.valueOf(20);
/*       */               }
/* 13861 */               String str = "ZIGBEE_DIMMER-SEND-" + user_num.get(userCode) + "," + device.getDeviceAddress() + 
/* 13862 */                 "," + this.command;//DIMMER 调光开关
/* 13863 */               System.err.println(commands + ">-- " + this.command);
/* 13864 */               byte[] bs = str.getBytes();
/* 13865 */               System.err.println(new String(bs));
/* 13866 */               this.packetProcessHelper.processSendDData(device.getBoDevice().getDeviceCode(), bs);
/* 13867 */               this.packetProcessHelper.setUserCode(userCode);
/*       */             }
/*       */ 
/* 13870 */             if (device.getDeviceAddress().toString().length() == 8) {//门锁相关
/* 13871 */               String deviceType = device.getDeviceType().toString();
/* 13872 */               String substring = deviceType.substring(1, 2);
/* 13873 */               Integer valueOf = Integer.valueOf(substring);
/* 13874 */               Integer s = null;
/* 13875 */               if (valueOf.intValue() == 1)
/* 13876 */                 s = Integer.valueOf(2262);
/*       */               else {
/* 13878 */                 s = Integer.valueOf(1527);
/*       */               }
/*       */ 
/* 13881 */               String substring2 = deviceType.substring(2, 3);
/* 13882 */               Integer valueOf2 = Integer.valueOf(substring2);
/* 13883 */               Integer b = null;
/* 13884 */               if (valueOf2.intValue() == 1)
/* 13885 */                 b = Integer.valueOf(315);
/*       */               else {
/* 13887 */                 b = Integer.valueOf(433);
/*       */               }
/*       */ 
/* 13890 */               String substring3 = deviceType.substring(3, 4);
/* 13891 */               Integer valueOf3 = Integer.valueOf(substring3);
/* 13892 */               Integer c = null;
/* 13893 */               if (valueOf3.intValue() == 1)
/* 13894 */                 c = Integer.valueOf(12);
/* 13895 */               else if (valueOf3.intValue() == 2)
/* 13896 */                 c = Integer.valueOf(15);
/* 13897 */               else if (valueOf3.intValue() == 3)
/* 13898 */                 c = Integer.valueOf(22);
/* 13899 */               else if (valueOf3.intValue() == 4)
/* 13900 */                 c = Integer.valueOf(33);
/* 13901 */               else if (valueOf3.intValue() == 5)
/* 13902 */                 c = Integer.valueOf(47);
/* 13903 */               else if (valueOf3.intValue() == 6)
/* 13904 */                 c = Integer.valueOf(330);
/* 13905 */               else if (valueOf3.intValue() == 7)
/* 13906 */                 c = Integer.valueOf(390);
/* 13907 */               else if (valueOf3.intValue() == 8) {
/* 13908 */                 c = Integer.valueOf(200);
/*       */               }
///*       */               logger.info("~~~~~13684~~~~~this.command:"+this.command);//100
/* 13912 */               if (this.command.intValue() == 0) {
/* 13913 */                 System.err.println(device.getDeviceAddress());
/* 13914 */                 String string = device.getDeviceAddress().substring(0, 1);
/* 13915 */                 String string2 = device.getDeviceAddress().substring(1, 8);
/*       */ 
/* 13917 */                 if (string.equals("0"))
/* 13918 */                   this.ln = string2;
/*       */                 else {
/* 13920 */                   this.ln = device.getDeviceAddress();
/*       */                 }
/*       */ 
/* 13925 */                 String str = "PT" + s + "_" + b + "M-SEND-" + user_num.get(userCode) + "," + c + "," + 
/* 13926 */                   this.ln+2;//门锁地址+'2'
/* 13927 */                 byte[] bs = str.getBytes();
/* 13928 */                 System.err.println("<?> " + new String(bs));
/* 13929 */                 this.packetProcessHelper.processSendDData(device.getBoDevice().getDeviceCode(), bs);
/* 13930 */                 this.packetProcessHelper.setUserCode(userCode);
/*       */               }
/*       */ 
/* 13934 */               if (this.command.intValue() == 50)
/*       */               {
/* 13938 */                 String str = "PT" + s + "_" + b + "M-SEND-" + user_num.get(userCode.trim().toString()) + "," + c + "," + 
/* 13939 */                   device.getDeviceAddress() + 3;//门锁？？
/* 13940 */                 byte[] bs = str.getBytes();
							System.err.println("<?> " +new String(bs));//PT1527_315M SEND '0',33,78892510'1',OK
/* 13942 */                 this.packetProcessHelper.processSendDData(device.getBoDevice().getDeviceCode(), bs);
/* 13943 */                 this.packetProcessHelper.setUserCode(userCode);
/*       */               }
/*       */ 
/* 13947 */               if (this.command.intValue() == 100) {
/* 13948 */                 String string = device.getDeviceAddress().substring(0, 1);
/* 13949 */                 String string2 = device.getDeviceAddress().substring(1, 8);
/*       */ 
/* 13951 */                 if (string.equals("0"))
/* 13952 */                   this.ln = string2;
/*       */                 else {
/* 13954 */                   this.ln = device.getDeviceAddress();
/*       */                 }
/*       */ 
/* 13957 */                 String str = "PT" + s + "_" + b + "M-SEND-" + user_num.get(userCode) + "," + c + "," + 
/* 13958 */                   this.ln+1;//门锁 新地址  this.ln+'1'
/* 13959 */                 byte[] bs = str.getBytes();
							System.err.println("<?> " +new String(bs));//PT1527_315M SEND 0,33,788925101,OK
/* 13961 */                 this.packetProcessHelper.processSendDData(device.getBoDevice().getDeviceCode(), bs);
/* 13962 */                 this.packetProcessHelper.setUserCode(userCode);
/*       */               }
/*       */             }


/*       */ 
/*       */           }
/*       */           else
/*       */           {
/* 13969 */             this.requestJson.setMessage("没有找到你要找的设备地址");
/* 13970 */             this.requestJson.setData(map);
/* 13971 */             this.requestJson.setSuccess(false);
/*       */           }
/*       */         } else {
/* 13974 */           this.requestJson.setMessage("超时了");
/* 13975 */           this.requestJson.setSuccess(false);
/*       */         }
/*       */       } else {
/* 13978 */         this.requestJson.setMessage("验证不通过");
/* 13979 */         this.requestJson.setSuccess(false);
/*       */       }
/*       */     }
/* 13982 */     return "success";
/*       */   }
/*       */ 
/*       */   @Action(value="gainfluoriteaccesstoken", results={@org.apache.struts2.convention.annotation.Result(type="json", params={"root", "requestJson"})})
/*       */   public String gainFluoriteAccessToken()
/*       */   {
/* 13989 */     this.requestJson = new RequestJson();
/* 13990 */     Map map = new HashMap();
/* 13991 */     HttpServletRequest request = ServletActionContext.getRequest();
/* 13992 */     String str = "";
/* 13993 */     Md5 md5 = new Md5();
/* 13994 */     String header = request.getHeader("timestamp");
/* 13995 */     String header2 = request.getHeader("nonce");
/* 13996 */     String header3 = request.getHeader("sign");
/* 13997 */     String header4 = request.getHeader("access_token");
/* 13998 */     String userCode = request.getHeader("userCode");
/* 13999 */     if (userCode.contains(",")) {
/* 14000 */       String[] userCode2 = userCode.split(",");
/* 14001 */       BoUsers boUsers = this.boUserServicess.findByUserUserCode(userCode2[0].trim().toString());
/* 14002 */       BoUsers phone = this.boUserServicess.findByUserPhone(userCode2[1].trim().toString());
/* 14003 */       Boolean ral = isRal(header, header2, header3, header4, userCode, "修改主机昵称");
/* 14004 */       if (ral.booleanValue()) {
/* 14005 */         System.err.println("验证通过");
/* 14006 */         Long accessToken = Long.valueOf(header);
/* 14007 */         if ((phone == null) || (boUsers == null)) {
/* 14008 */           this.requestJson.setData(map);
/* 14009 */           this.requestJson.setMessage("Invalid_User");
/* 14010 */           this.requestJson.setSuccess(true);
/*       */         }
/* 14012 */         else if (header4.equals(phone.getAccessToken())) {
/* 14013 */           if (accessToken.longValue() < Long.valueOf(phone.getAccessTokenTime()).longValue())
/*       */           {
/* 14015 */             if (Long.valueOf(boUsers.getFluoriteAccessTokenCreateTime()).longValue() < 
/* 14015 */               Long.valueOf(boUsers.getFluoriteAccessTokenExpireTime()).longValue()) {
/* 14016 */               this.requestJson.setData(map);
/* 14017 */               this.requestJson.setMessage("fluoriteAccessToken没有过期");
/* 14018 */               this.requestJson.setSuccess(true);
/*       */             } else {
/* 14020 */               String o4 = null;
/* 14021 */               Map paramsMap = new HashMap();
/* 14022 */               paramsMap.put("phone", boUsers.getUserPhone());
/* 14023 */               Map maps = YZUitl.paramsInit("token/getAccessToken", paramsMap);
/* 14024 */               String json = JSON.toJSONString(maps);
/* 14025 */               ProtocolSocketFactory fcty = new MySecureProtocolSocketFactory();
/* 14026 */               Protocol.registerProtocol("https", new Protocol("https", fcty, 443));
/* 14027 */               HttpClient client = new HttpClient();
/*       */ 
/* 14029 */               PostMethod method = new PostMethod("https://open.ys7.com:443/api/method");
/*       */               try
/*       */               {
/* 14032 */                 RequestEntity entity = new StringRequestEntity(json, "application/json", "UTF-8");
/* 14033 */                 method.setRequestEntity(entity);
/* 14034 */                 client.executeMethod(method);
/*       */ 
/* 14036 */                 InputStream inputStream = method.getResponseBodyAsStream();
/* 14037 */                 String restult = IOUtils.toString(inputStream);
/*       */ 
/* 14039 */                 Object o = JSONUtil.deserialize(restult);
/* 14040 */                 Map o1 = (HashMap)o;
/* 14041 */                 Map o2 = (HashMap)o1.get("result");
/* 14042 */                 code = (String)o2.get("code");
/*       */ 
/* 14044 */                 if (code.equals("200")) {
/* 14045 */                   Map o3 = (HashMap)o2.get("data");
/* 14046 */                   o4 = (String)o3.get("accessToken");
/*       */                 } else {
/* 14048 */                   o4 = "NO_BUNDING";
/*       */                 }
/* 14050 */                 map.put("ez_token", o4);
/*       */ 
/* 14052 */                 this.requestJson.setData(map);
/* 14053 */                 this.requestJson.setSuccess(true);
/*       */               } catch (Exception e) {
/* 14055 */                 e.printStackTrace();
/*       */               }
/*       */               finally
/*       */               {
/* 14059 */                 method.releaseConnection();
/*       */               }
/*       */             }
/*       */           }
/*       */           else {
/* 14064 */             this.requestJson.setData(map);
/* 14065 */             this.requestJson.setMessage("超时了");
/* 14066 */             this.requestJson.setSuccess(false);
/*       */           }
/*       */         } else {
/* 14069 */           System.err.println("超时了");
/* 14070 */           this.requestJson.setData(map);
/* 14071 */           this.requestJson.setMessage("超时了");
/* 14072 */           this.requestJson.setSuccess(false);
/*       */         }
/*       */ 
/*       */       }
/*       */       else
/*       */       {
/* 14078 */         System.err.println("验证不通过");
/* 14079 */         this.requestJson.setData(map);
/* 14080 */         this.requestJson.setMessage("验证不通过");
/* 14081 */         this.requestJson.setSuccess(false);
/*       */       }
/*       */     } else {
/* 14084 */       Boolean ral = isRal(header, header2, header3, header4, userCode, "修改主机昵称");
/* 14085 */       if (ral.booleanValue()) {
/* 14086 */         System.err.println("验证通过");
/* 14087 */         Long accessToken = Long.valueOf(header);
/* 14088 */         BoUsers boUsers = this.boUserServicess.findByUserUserCode(userCode);
/* 14089 */         if (accessToken.longValue() < Long.valueOf(boUsers.getAccessTokenTime()).longValue()) {
/* 14090 */           if (boUsers == null) {
/* 14091 */             this.requestJson.setMessage("Invalid_User");
/* 14092 */             this.requestJson.setSuccess(true);
/*       */           }
/* 14095 */           else if (Long.valueOf(boUsers.getFluoriteAccessTokenCreateTime()).longValue() < 
/* 14095 */             Long.valueOf(boUsers.getFluoriteAccessTokenExpireTime()).longValue()) {
/* 14096 */             this.requestJson.setMessage("fluoriteAccessToken没有过期");
/* 14097 */             this.requestJson.setSuccess(true);
/*       */           } else {
/* 14099 */             String o4 = null;
/* 14100 */             Map paramsMap = new HashMap();
/* 14101 */             paramsMap.put("phone", boUsers.getUserPhone());
/* 14102 */             Map maps = YZUitl.paramsInit("token/getAccessToken", paramsMap);
/* 14103 */             String json = JSON.toJSONString(maps);
/* 14104 */             ProtocolSocketFactory fcty = new MySecureProtocolSocketFactory();
/* 14105 */             Protocol.registerProtocol("https", new Protocol("https", fcty, 443));
/* 14106 */             HttpClient client = new HttpClient();
/*       */ 
/* 14108 */             PostMethod method = new PostMethod("https://open.ys7.com:443/api/method");
/*       */             try
/*       */             {
/* 14111 */               RequestEntity entity = new StringRequestEntity(json, "application/json", "UTF-8");
/* 14112 */               method.setRequestEntity(entity);
/* 14113 */               client.executeMethod(method);
/*       */ 
/* 14115 */               InputStream inputStream = method.getResponseBodyAsStream();
/* 14116 */               String restult = IOUtils.toString(inputStream);
/*       */ 
/* 14118 */               Object o = JSONUtil.deserialize(restult);
/* 14119 */               Map o1 = (HashMap)o;
/* 14120 */               Map o2 = (HashMap)o1.get("result");
/* 14121 */               String code = (String)o2.get("code");
/*       */ 
/* 14123 */               if (code.equals("200")) {
/* 14124 */                 Map o3 = (HashMap)o2.get("data");
/* 14125 */                 o4 = (String)o3.get("accessToken");
/*       */               } else {
/* 14127 */                 o4 = "NO_BUNDING";
/*       */               }
/* 14129 */               map.put("ez_token", o4);
/*       */ 
/* 14131 */               this.requestJson.setData(map);
/* 14132 */               this.requestJson.setSuccess(true);
/*       */             } catch (Exception e) {
/* 14134 */               e.printStackTrace();
/*       */             }
/*       */             finally
/*       */             {
/* 14138 */               method.releaseConnection();
/*       */             }
/*       */           }
/*       */         }
/*       */         else
/*       */         {
/* 14144 */           this.requestJson.setMessage("超时了");
/* 14145 */           this.requestJson.setSuccess(false);
/*       */         }
/*       */       }
/*       */       else {
/* 14149 */         System.err.println("验证不通过");
/* 14150 */         this.requestJson.setMessage("验证不通过");
/* 14151 */         this.requestJson.setSuccess(false);
/*       */       }
/*       */     }
/* 14154 */     return "success";
/*       */   }
/*       */ 
/*       */   @Action(value="verify_with_sweep_host", results={@org.apache.struts2.convention.annotation.Result(type="json", params={"root", "requestJson"})})
/*       */   public String verifyWithSweepHost()
/*       */   {
/* 14167 */     this.requestJson = new RequestJson();
/* 14168 */     Map map = new HashMap();
/* 14169 */     HttpServletRequest request = ServletActionContext.getRequest();
/* 14170 */     String header = request.getHeader("timestamp");
/* 14171 */     String header2 = request.getHeader("nonce");
/* 14172 */     String header3 = request.getHeader("sign");
/* 14173 */     String header4 = request.getHeader("access_token");
/* 14174 */     String userCode = request.getHeader("userCode");
/* 14175 */     if (userCode.contains(",")) {
/* 14176 */       String[] userCode2 = userCode.split(",");
/* 14177 */       BoUsers boUsers = this.boUserServicess.findByUserUserCode(userCode2[0].trim().toString());
/* 14178 */       BoUsers phone = this.boUserServicess.findByUserPhone(userCode2[1].trim().toString());
/* 14179 */       Boolean ral = isRal(header, header2, header3, header4, userCode, "验证与扫描主机");
/* 14180 */       if (ral.booleanValue())
/*       */       {
/* 14182 */         Long accessToken = Long.valueOf(header);
/*       */ 
/* 14184 */         if ((phone == null) || (boUsers == null)) {
/* 14185 */           this.requestJson.setData(map);
/* 14186 */           this.requestJson.setMessage("Invalid_User");
/* 14187 */           this.requestJson.setSuccess(false);
/*       */         }
/* 14189 */         else if (header4.equals(phone.getAccessToken())) {
/* 14190 */           if (accessToken.longValue() < Long.valueOf(phone.getAccessTokenTime()).longValue()) {
/* 14191 */             System.err.println("走了");
/* 14192 */             System.err.println(this.deviceCode);
/* 14193 */             BoDevice boDevice = this.boDeviceService.findByCode(this.deviceCode.trim().toString());
/* 14194 */             System.err.println(this.deviceCode);
/* 14195 */             if (boDevice == null) {
/* 14196 */               System.err.println("服务器尚未添设该主机,请联系管理员处理");
/* 14197 */               this.requestJson.setData(map);
/* 14198 */               this.requestJson.setMessage("服务器尚未添设该主机,请联系管理员处理");
/* 14199 */               this.requestJson.setSuccess(false);
/*       */             } else {
/* 14201 */               List listByDeviceCode = this.boUserDevicesServicess.getListByDeviceCode(this.deviceCode);
/* 14202 */               if (!GlobalMethod.isNullorEmpty(listByDeviceCode)) {
/* 14203 */                 if (listByDeviceCode.size() >= 1) {
/* 14204 */                   System.err.println("主机已被绑定");
/* 14205 */                   this.requestJson.setData(map);
/* 14206 */                   this.requestJson.setMessage("主机已被绑定");
/* 14207 */                   this.requestJson.setSuccess(false);
/* 14208 */                   return "success";
/*       */                 }
/*       */               } else {
/* 14211 */                 BoUserDevices u = new BoUserDevices();
/* 14212 */                 u.setBoDevice(boDevice);
/* 14213 */                 u.setBoUsers(boUsers);
/* 14214 */                 u.setNickName(this.nickName);
/* 14215 */                 this.boUserDevicesServicess.save(u);
/* 14216 */                 if (boDevice.getType().equals("8")) {
/* 14217 */                   String str = "RELAY-SCAN_DEVICE-NOW";
/* 14218 */                   byte[] bs = str.getBytes();
/* 14219 */                   System.err.println(new String(bs));
/* 14220 */                   this.packetProcessHelper.processSendDDatas(this.deviceCode, bs);
/* 14221 */                   StaticUtil.CONTROLENCLOSURE.put(this.deviceCode + "_" + "A", new String[] { userCode2[0].trim().toString(), new Date().getTime()+"" });
/* 14222 */                   this.requestJson.setData(map);
/* 14223 */                 } else if (boDevice.getType().equals("32")) {
/* 14224 */                   String str = "RELAY-SCAN_DEVICE-NOW";
/* 14225 */                   byte[] bs = str.getBytes();
/* 14226 */                   System.err.println(new String(bs));
/* 14227 */                   this.packetProcessHelper.processSendDDatas(this.deviceCode, bs);
/* 14228 */                   StaticUtil.CONTROLENCLOSURE.put(this.deviceCode + "_" + "A", new String[] { 
/* 14229 */                     userCode2[0].trim().toString(), new Date().getTime()+"" });
/* 14230 */                   this.requestJson.setData(map);
/*       */                 } else {
/* 14232 */                   BoHostDevice hostDevice = this.boHostDeviceService.findBydeviceAddress(userCode2[0].trim().toString(), this.deviceCode, "65535," + boDevice.getDeviceCode());
/* 14233 */                   if ((boDevice.getType().equals("G")) && 
/* 14234 */                     (hostDevice == null)) {
/* 14235 */                     BoHostDevice boHostDevice = new BoHostDevice();
/* 14236 */                     boHostDevice.setBoDevice(boDevice);
/* 14237 */                     boHostDevice.setBoUsers(boUsers);
/* 14238 */                     boHostDevice.setDeviceType("98");
/* 14239 */                     boHostDevice.setDeviceAddress("65535," + boDevice.getDeviceCode());
/* 14240 */                     boHostDevice.setIco("WIFI红外");
/* 14241 */                     boHostDevice.setNickName("");
/* 14242 */                     boHostDevice.setWhetherQueryStateSign("");
/* 14243 */                     boHostDevice.setDeviceNum(Integer.valueOf(0));
/* 14244 */                     boHostDevice.setPushSet("");
/* 14245 */                     boHostDevice.setState("");
/* 14246 */                     boHostDevice.setBoRoom(null);
/* 14247 */                     boHostDevice.setDeviceClassify(this.fid);
/* 14248 */                     boHostDevice.setMntDelete("N");
/* 14249 */                     boHostDevice.setValidationCode("");
/* 14250 */                     this.boHostDeviceService.save(boHostDevice);
/*       */                   }
/*       */ 
/* 14253 */                   String str = "ZIGBEE_SCAN-DEVEICE-NOW";//门锁 有经过？
/* 14254 */                   byte[] bs = str.getBytes();
/* 14255 */                   System.err.println(new String(bs));
/* 14256 */                   this.packetProcessHelper.processSendDDatas(this.deviceCode, bs);
/* 14257 */                   StaticUtil.drik.put(this.deviceCode + "_" + "A", new String[] { 
/* 14258 */                     userCode2[0].trim().toString(), new Date().getTime()+"" });
/* 14259 */                   System.err.println(new String(bs));
/* 14260 */                   this.requestJson.setData(map);
/* 14261 */                   this.requestJson.setMessage("正在扫描设备请稍等......");
/* 14262 */                   System.err.println("正在扫描设备请稍等......");
/* 14263 */                   this.requestJson.setSuccess(true);
/*       */                 }
/*       */               }
/*       */             }
/*       */ 
/*       */           }
/*       */           else
/*       */           {
/* 14271 */             this.requestJson.setMessage("超时了");
/* 14272 */             this.requestJson.setSuccess(false);
/*       */           }
/*       */         } else {
/* 14275 */           System.err.println("超时了");
/* 14276 */           this.requestJson.setMessage("超时了");
/* 14277 */           this.requestJson.setSuccess(false);
/*       */         }
/*       */       }
/*       */       else {
/* 14281 */         this.requestJson.setMessage("验证不通过");
/* 14282 */         this.requestJson.setSuccess(false);
/*       */       }
/*       */     } else {
/* 14285 */       Boolean ral = isRal(header, header2, header3, header4, userCode, "验证与扫描主机");
/* 14286 */       if (ral.booleanValue())
/*       */       {
/* 14288 */         Long accessToken = Long.valueOf(header);
/*       */ 
/* 14290 */         BoUsers boUsers = this.boUserServicess.findByUserUserCode(userCode);
/* 14291 */         if (boUsers == null) {
/* 14292 */           this.requestJson.setMessage("Invalid_User");
/* 14293 */           this.requestJson.setSuccess(false);
/*       */         }
/* 14295 */         else if (accessToken.longValue() < Long.valueOf(boUsers.getAccessTokenTime()).longValue()) {
/* 14296 */           System.err.println("走了");
/* 14297 */           System.err.println(this.deviceCode);
/* 14298 */           BoDevice boDevice = this.boDeviceService.findByCode(this.deviceCode.trim().toString());
/* 14299 */           System.err.println(this.deviceCode);
/* 14300 */           if (boDevice == null) {
/* 14301 */             System.err.println("服务器尚未添设该主机,请联系管理员处理");
/* 14302 */             this.requestJson.setData(map);
/* 14303 */             this.requestJson.setMessage("服务器尚未添设该主机,请联系管理员处理");
/* 14304 */             this.requestJson.setSuccess(false);
/*       */           } else {
/* 14306 */             List listByDeviceCode = this.boUserDevicesServicess.getListByDeviceCode(this.deviceCode);
/* 14307 */             if (!GlobalMethod.isNullorEmpty(listByDeviceCode)) {
/* 14308 */               if (listByDeviceCode.size() >= 1) {
/* 14309 */                 System.err.println("主机已被绑定");
/* 14310 */                 this.requestJson.setData(map);
/* 14311 */                 this.requestJson.setMessage("主机已被绑定");
/* 14312 */                 this.requestJson.setSuccess(false);
/* 14313 */                 return "success";
/*       */               }
/*       */             } else {
/* 14316 */               BoUserDevices u = new BoUserDevices();
/* 14317 */               u.setBoDevice(boDevice);
/* 14318 */               u.setBoUsers(boUsers);
/* 14319 */               u.setNickName(this.nickName);
/* 14320 */               this.boUserDevicesServicess.save(u);
/* 14321 */               if (boDevice.getType().equals("8")) {
/* 14322 */                 String str = "RELAY-SCAN_DEVICE-NOW";
/* 14323 */                 byte[] bs = str.getBytes();
/* 14324 */                 System.err.println(new String(bs));
/* 14325 */                 this.packetProcessHelper.processSendDDatas(this.deviceCode, bs);
/* 14326 */                 StaticUtil.CONTROLENCLOSURE.put(this.deviceCode + "_" + "A", new String[] { userCode, new Date().getTime()+"" });
/* 14327 */                 this.requestJson.setData(map);
/* 14328 */               } else if (boDevice.getType().equals("32")) {
/* 14329 */                 String str = "RELAY-SCAN_DEVICE-NOW";
/* 14330 */                 byte[] bs = str.getBytes();
/* 14331 */                 System.err.println(new String(bs));
/* 14332 */                 this.packetProcessHelper.processSendDDatas(this.deviceCode, bs);
/* 14333 */                 StaticUtil.CONTROLENCLOSURE.put(this.deviceCode + "_" + "A", new String[] { 
/* 14334 */                   userCode, new Date().getTime()+"" });
/* 14335 */                 this.requestJson.setData(map);
/*       */               } else {
/* 14337 */                 BoHostDevice hostDevice = this.boHostDeviceService.findBydeviceAddress(userCode, this.deviceCode, "65535," + boDevice.getDeviceCode());
/* 14338 */                 if ((boDevice.getType().equals("G")) && 
/* 14339 */                   (hostDevice == null)) {
/* 14340 */                   BoHostDevice boHostDevice = new BoHostDevice();
/* 14341 */                   boHostDevice.setBoDevice(boDevice);
/* 14342 */                   boHostDevice.setBoUsers(boUsers);
/* 14343 */                   boHostDevice.setDeviceType("98");
/* 14344 */                   boHostDevice.setDeviceAddress("65535," + boDevice.getDeviceCode());
/* 14345 */                   boHostDevice.setIco("WIFI红外");
/* 14346 */                   boHostDevice.setNickName("");
/* 14347 */                   boHostDevice.setWhetherQueryStateSign("");
/* 14348 */                   boHostDevice.setDeviceNum(Integer.valueOf(0));
/* 14349 */                   boHostDevice.setPushSet("");
/* 14350 */                   boHostDevice.setState("");
/* 14351 */                   boHostDevice.setBoRoom(null);
/* 14352 */                   boHostDevice.setDeviceClassify(this.fid);
/* 14353 */                   boHostDevice.setMntDelete("N");
/* 14354 */                   boHostDevice.setValidationCode("");
/* 14355 */                   this.boHostDeviceService.save(boHostDevice);
/*       */                 }
/*       */ 
/* 14358 */                 String str = "ZIGBEE_SCAN-DEVEICE-NOW";
/* 14359 */                 byte[] bs = str.getBytes();
/* 14360 */                 System.err.println(new String(bs));
/* 14361 */                 this.packetProcessHelper.processSendDDatas(this.deviceCode, bs);
/* 14362 */                 StaticUtil.drik.put(this.deviceCode + "_" + "A", new String[] { 
/* 14363 */                   userCode, new Date().getTime()+"" });
/* 14364 */                 System.err.println(new String(bs));
/* 14365 */                 this.requestJson.setData(map);
/* 14366 */                 this.requestJson.setMessage("正在扫描设备请稍等......");
/* 14367 */                 System.err.println("正在扫描设备请稍等......");
/* 14368 */                 this.requestJson.setSuccess(true);
/*       */               }
/*       */             }
/*       */           }
/*       */ 
/*       */         }
/*       */         else
/*       */         {
/* 14376 */           this.requestJson.setMessage("超时了");
/* 14377 */           this.requestJson.setSuccess(false);
/*       */         }
/*       */       }
/*       */       else {
/* 14381 */         this.requestJson.setMessage("验证不通过");
/* 14382 */         this.requestJson.setSuccess(false);
/*       */       }
/*       */     }
/* 14385 */     return "success";
/*       */   }
/*       */ 
/*       */   @Action(value="addRepair", results={@org.apache.struts2.convention.annotation.Result(type="json", params={"root", "requestJson"})})
/*       */   public String addRepair()
/*       */   {
/* 14399 */     Map map = new HashMap();
/* 14400 */     this.requestJson = new RequestJson();
/* 14401 */     HttpServletRequest request = ServletActionContext.getRequest();
/* 14402 */     String str = "";
/* 14403 */     Md5 md5 = new Md5();
/* 14404 */     String header = request.getHeader("timestamp");
/* 14405 */     String header2 = request.getHeader("nonce");
/* 14406 */     String header3 = request.getHeader("sign");
/* 14407 */     String header4 = request.getHeader("access_token");
/* 14408 */     String userCode = request.getHeader("userCode");
/* 14409 */     if (userCode.contains(",")) {
/* 14410 */       String[] userCode2 = userCode.split(",");
/* 14411 */       BoUsers boUsers = this.boUserServicess.findByUserUserCode(userCode2[0].trim().toString());
/* 14412 */       BoUsers phone = this.boUserServicess.findByUserPhone(userCode2[1].trim().toString());
/* 14413 */       Boolean ral = isRal(header, header2, header3, header4, userCode, "一键报修");
/* 14414 */       if (ral.booleanValue()) {
/* 14415 */         System.err.println("验证通过");
/* 14416 */         Long accessToken = Long.valueOf(header);
/* 14417 */         if ((phone == null) || (boUsers == null)) {
/* 14418 */           this.requestJson.setData(map);
/* 14419 */           this.requestJson.setMessage("Invalid_User");
/* 14420 */           this.requestJson.setSuccess(true);
/*       */         }
/* 14422 */         else if (header4.equals(phone.getAccessToken())) {
/* 14423 */           if (accessToken.longValue() < Long.valueOf(phone.getAccessTokenTime()).longValue()) {
/* 14424 */             if (StringUtils.isEmpty(this.userPhone)) {
/* 14425 */               this.userPhone = boUsers.getUserPhone();
/*       */             }
/*       */ 
/* 14428 */             BoRepairs repair = new BoRepairs();
/*       */ 
/* 14430 */             repair.setBoUsers(boUsers);
/* 14431 */             repair.setRepairDate(new Date());
/* 14432 */             repair.setUserPhone(this.userPhone);
/* 14433 */             repair.setUserAddr(this.userAddr);
/* 14434 */             repair.setUserTxt(this.msg);
/*       */ 
/* 14436 */             this.boRepairsService.save(repair);
/* 14437 */             this.requestJson.setData(map);
/* 14438 */             this.requestJson.setMessage("提交成功");
/* 14439 */             this.requestJson.setSuccess(true);
/*       */           } else {
/* 14441 */             this.requestJson.setData(map);
/* 14442 */             this.requestJson.setMessage("超时了");
/* 14443 */             this.requestJson.setSuccess(false);
/*       */           }
/*       */         } else {
/* 14446 */           System.err.println("超时了");
/* 14447 */           this.requestJson.setData(map);
/* 14448 */           this.requestJson.setMessage("超时了");
/* 14449 */           this.requestJson.setSuccess(false);
/*       */         }
/*       */ 
/*       */       }
/*       */       else
/*       */       {
/* 14455 */         System.err.println("验证不通过");
/* 14456 */         this.requestJson.setData(map);
/* 14457 */         this.requestJson.setMessage("验证不通过");
/* 14458 */         this.requestJson.setSuccess(false);
/*       */       }
/*       */     } else {
/* 14461 */       Boolean ral = isRal(header, header2, header3, header4, userCode, "修改主机昵称");
/* 14462 */       if (ral.booleanValue()) {
/* 14463 */         System.err.println("验证通过");
/* 14464 */         Long accessToken = Long.valueOf(header);
/* 14465 */         BoUsers boUsers = this.boUserServicess.findByUserUserCode(userCode);
/* 14466 */         if (accessToken.longValue() < Long.valueOf(boUsers.getAccessTokenTime()).longValue()) {
/* 14467 */           if (boUsers == null) {
/* 14468 */             this.requestJson.setMessage("Invalid_User");
/* 14469 */             this.requestJson.setSuccess(true);
/*       */           } else {
/* 14471 */             if (StringUtils.isEmpty(this.userPhone)) {
/* 14472 */               this.userPhone = boUsers.getUserPhone();
/*       */             }
/*       */ 
/* 14475 */             BoRepairs repair = new BoRepairs();
/*       */ 
/* 14477 */             repair.setBoUsers(boUsers);
/* 14478 */             repair.setRepairDate(new Date());
/* 14479 */             repair.setUserPhone(this.userPhone);
/* 14480 */             repair.setUserAddr(this.userAddr);
/* 14481 */             repair.setUserTxt(this.msg);
/*       */ 
/* 14483 */             this.boRepairsService.save(repair);
/* 14484 */             this.requestJson.setData(map);
/* 14485 */             this.requestJson.setMessage("提交成功");
/* 14486 */             this.requestJson.setSuccess(true);
/*       */           }
/*       */         } else {
/* 14489 */           this.requestJson.setMessage("超时了");
/* 14490 */           this.requestJson.setSuccess(false);
/*       */         }
/*       */       }
/*       */       else {
/* 14494 */         System.err.println("验证不通过");
/* 14495 */         this.requestJson.setMessage("验证不通过");
/* 14496 */         this.requestJson.setSuccess(false);
/*       */       }
/*       */ 
/*       */     }
/*       */ 
/* 14501 */     return "success";
/*       */   }
/*       */ 
/*       */   @Action(value="feedback", results={@org.apache.struts2.convention.annotation.Result(type="json", params={"root", "requestJson"})})
/*       */   public String feedBack()
/*       */   {
/* 14512 */     this.requestJson = new RequestJson();
/* 14513 */     Map map = new HashMap();
/*       */ 
/* 14515 */     HttpServletRequest request = ServletActionContext.getRequest();
/* 14516 */     String str = "";
/* 14517 */     Md5 md5 = new Md5();
/* 14518 */     String header = request.getHeader("timestamp");
/* 14519 */     String header2 = request.getHeader("nonce");
/* 14520 */     String header3 = request.getHeader("sign");
/* 14521 */     String header4 = request.getHeader("access_token");
/* 14522 */     String userCode = request.getHeader("userCode");
/* 14523 */     if (userCode.contains(",")) {
/* 14524 */       String[] userCode2 = userCode.split(",");
/* 14525 */       BoUsers boUsers = this.boUserServicess.findByUserUserCode(userCode2[0].trim().toString());
/* 14526 */       BoUsers phone = this.boUserServicess.findByUserPhone(userCode2[1].trim().toString());
/* 14527 */       Boolean ral = isRal(header, header2, header3, header4, userCode, "意见反馈接口");
/* 14528 */       if (ral.booleanValue()) {
/* 14529 */         System.err.println("验证通过");
/* 14530 */         Long accessToken = Long.valueOf(header);
/* 14531 */         if ((phone == null) || (boUsers == null)) {
/* 14532 */           this.requestJson.setData(map);
/* 14533 */           this.requestJson.setMessage("Invalid_User");
/* 14534 */           this.requestJson.setSuccess(true);
/*       */         }
/* 14536 */         else if (header4.equals(phone.getAccessToken())) {
/* 14537 */           if (accessToken.longValue() < Long.valueOf(phone.getAccessTokenTime()).longValue()) {
/* 14538 */             BoFeedBack boFeedBack = new BoFeedBack();
/* 14539 */             boFeedBack.setBoUsers(phone);
/* 14540 */             boFeedBack.setUserPhone(boUsers.getUserPhone());
/*       */ 
/* 14542 */             boFeedBack.setContent(this.contents);
/* 14543 */             boFeedBack.setTime(new Date());
/* 14544 */             this.boFeedBackService.save(boFeedBack);
/* 14545 */             this.requestJson.setData(map);
/* 14546 */             this.requestJson.setMessage("提交成功");
/* 14547 */             this.requestJson.setSuccess(true);
/*       */           } else {
/* 14549 */             this.requestJson.setData(map);
/* 14550 */             this.requestJson.setMessage("超时了");
/* 14551 */             this.requestJson.setSuccess(false);
/*       */           }
/*       */         } else {
/* 14554 */           System.err.println("超时了");
/* 14555 */           this.requestJson.setData(map);
/* 14556 */           this.requestJson.setMessage("超时了");
/* 14557 */           this.requestJson.setSuccess(false);
/*       */         }
/*       */       }
/*       */       else {
/* 14561 */         System.err.println("验证不通过");
/* 14562 */         this.requestJson.setData(map);
/* 14563 */         this.requestJson.setMessage("验证不通过");
/* 14564 */         this.requestJson.setSuccess(false);
/*       */       }
/*       */     } else {
/* 14567 */       Boolean ral = isRal(header, header2, header3, header4, userCode, "修改主机昵称");
/* 14568 */       if (ral.booleanValue()) {
/* 14569 */         System.err.println("验证通过");
/* 14570 */         Long accessToken = Long.valueOf(header);
/* 14571 */         BoUsers boUsers = this.boUserServicess.findByUserUserCode(userCode);
/* 14572 */         if (boUsers == null) {
/* 14573 */           this.requestJson.setMessage("Invalid_User");
/* 14574 */           this.requestJson.setSuccess(true);
/*       */         }
/* 14576 */         else if (accessToken.longValue() < Long.valueOf(boUsers.getAccessTokenTime()).longValue())
/*       */         {
/* 14578 */           BoFeedBack boFeedBack = new BoFeedBack();
/* 14579 */           boFeedBack.setBoUsers(boUsers);
/*       */ 
/* 14581 */           boFeedBack.setUserPhone(boUsers.getUserPhone());
/*       */ 
/* 14583 */           boFeedBack.setContent(this.contents);
/* 14584 */           boFeedBack.setTime(new Date());
/* 14585 */           this.boFeedBackService.save(boFeedBack);
/* 14586 */           this.requestJson.setMessage("提交成功");
/* 14587 */           this.requestJson.setSuccess(true);
/*       */         } else {
/* 14589 */           this.requestJson.setMessage("超时了");
/* 14590 */           this.requestJson.setSuccess(false);
/*       */         }
/*       */       }
/*       */       else {
/* 14594 */         System.err.println("验证不通过");
/* 14595 */         this.requestJson.setMessage("验证不通过");
/* 14596 */         this.requestJson.setSuccess(false);
/*       */       }
/*       */     }
/* 14599 */     return "success";
/*       */   }
/*       */ 
/*       */   @Action(value="fileupload", results={@org.apache.struts2.convention.annotation.Result(type="json", params={"root", "requestJson"})})
/*       */   public String fileUploads()
/*       */   {
/* 14612 */     this.requestJson = new RequestJson();
/* 14613 */     Map map = new HashMap();
/* 14614 */     HttpServletRequest request = ServletActionContext.getRequest();
/* 14615 */     String str = "";
/* 14616 */     Md5 md5 = new Md5();
/* 14617 */     String header = request.getHeader("timestamp");
/* 14618 */     String header2 = request.getHeader("nonce");
/* 14619 */     String header3 = request.getHeader("sign");
/* 14620 */     String header4 = request.getHeader("access_token");
/* 14621 */     String userCode = request.getHeader("userCode");
/* 14622 */     String userPhone = request.getHeader("userPhone");
				
/* 14623 */     if (userCode.contains(",")) {
/* 14624 */       String[] userCode2 = userCode.split(",");
/* 14625 */       BoUsers boUsers = this.boUserServicess.findByUserUserCode(userCode2[0].trim().toString());
/* 14626 */       BoUsers phone = this.boUserServicess.findByUserPhone(userCode2[1].trim().toString());
/* 14627 */       Boolean ral = isRal(header, header2, header3, header4, userCode, "头像上传");

/* 14628 */       if (ral.booleanValue()) {
/* 14629 */         System.err.println("验证通过");
/* 14630 */         Long accessToken = Long.valueOf(header);
/* 14631 */         if ((phone == null) || (boUsers == null)) {
/* 14632 */           this.requestJson.setData(map);
/* 14633 */           this.requestJson.setMessage("Invalid_User");
/* 14634 */           this.requestJson.setSuccess(true);
/*       */         }
/* 14636 */         else if (header4.equals(phone.getAccessToken())) {
/* 14637 */           if (accessToken.longValue() < Long.valueOf(phone.getAccessTokenTime()).longValue()) {
/*       */             try {
/* 14639 */               if ((userPhone == null) || (userPhone.equals(""))) {
/* 14640 */                 String dir = "uploads/headpic";//不能放在项目目录下，不然每次重新部署都会消失
/* 14641 */                 System.err.println("<>>>>>>>>fileupload " + this.fileupload);
///* 14642 */                 String filePath = this.fileService.saveToDir(this.fileupload, 
///* 14643 */                   userCode + "head" + createRandomVcodesss() + ".jpg", dir);
							String filePath = this.fileService.saveToDir(this.fileupload, 
									userCode + "head" + ".jpg", dir);
//							logger.info("头像的地址1 filePath>>"+filePath);
/* 14644 */                 boUsers.setHeadPic(filePath);
/* 14645 */                 BoUsers update = (BoUsers)this.boUserServicess.update(boUsers);
/* 14646 */                 this.requestJson.setMessage("头像上传成功");
/* 14647 */                 map.put("headPic", update.getHeadPic());
//							logger.info("`headPic>>"+update.getHeadPic());
///* 14648 */                 break label1208;
							return "userPhone == null";
/* 14649 */               }
						  String dir = "uploads/headpic";//不能放在项目目录下，不然每次重新部署都会消失
/* 14650 */               System.err.println("<>>>>>>>>fileupload " + this.fileupload);
///* 14651 */               String filePath = this.fileService.saveToDir(this.fileupload, 
///* 14652 */                 userCode + "head" + createRandomVcodesss() + ".jpg", dir);//这样命名图片会越来越多
						  String filePath = this.fileService.saveToDir(this.fileupload, 
								  userCode + "head" + ".jpg", dir);
//						  logger.info("头像的地址2 filePath>>"+filePath);
/* 14653 */               phone.setHeadPic(filePath);
/* 14654 */               BoUsers update = (BoUsers)this.boUserServicess.update(phone);
/* 14655 */               this.requestJson.setMessage("头像上传成功");
/* 14656 */               map.put("headPic", update.getHeadPic());
//						  logger.info("``headPic>>"+update.getHeadPic());
/* 14657 */               return "success";
/*       */             }
/*       */             catch (Exception e)
/*       */             {
/* 14661 */               this.requestJson.setData(map);
/* 14662 */               this.requestJson.setMessage("头像上传失败");
/* 14663 */               this.requestJson.setSuccess(false);
/* 14664 */               e.printStackTrace();
/* 14665 */               return "success";
/*       */             }
/*       */           } else {
/* 14668 */             this.requestJson.setData(map);
/* 14669 */             this.requestJson.setMessage("超时了");
/* 14670 */             this.requestJson.setSuccess(false);
/*       */           }
/*       */         } else {
/* 14673 */           System.err.println("超时了");
/* 14674 */           this.requestJson.setData(map);
/* 14675 */           this.requestJson.setMessage("超时了");
/* 14676 */           this.requestJson.setSuccess(false);
/*       */         }
/*       */       }
/*       */       else {
/* 14680 */         System.err.println("验证不通过");
/* 14681 */         this.requestJson.setData(map);
/* 14682 */         this.requestJson.setMessage("验证不通过");
/* 14683 */         this.requestJson.setSuccess(false);
/*       */       }
/*       */     } else {
/* 14686 */       Boolean ral = isRal(header, header2, header3, header4, userCode, "修改主机昵称");
/* 14687 */       if (ral.booleanValue()) {
/* 14688 */         System.err.println("验证通过");
/* 14689 */         Long accessToken = Long.valueOf(header);
/* 14690 */         BoUsers boUsers = this.boUserServicess.findByUserUserCode(userCode);
/* 14691 */         if (boUsers == null) {
/* 14692 */           this.requestJson.setData(map);
/* 14693 */           this.requestJson.setMessage("Invalid_User");
/* 14694 */           this.requestJson.setSuccess(true);
/*       */         }
/* 14696 */         else if (accessToken.longValue() < Long.valueOf(boUsers.getAccessTokenTime()).longValue()) {
/*       */           try {
/* 14698 */             if ((userPhone == null) || (userPhone.equals(""))) {
/* 14699 */               String dir = "uploads/headpic";
/* 14700 */               System.err.println("<>>>>>>>>fileupload " + this.fileupload);
///* 14701 */               String filePath = this.fileService.saveToDir(this.fileupload, 
///* 14702 */                 userCode + "head" + createRandomVcodesss() + ".jpg", dir);
						  String filePath = this.fileService.saveToDir(this.fileupload, 
								  userCode + "head" + ".jpg", dir);
/* 14703 */               boUsers.setHeadPic(filePath);
/* 14704 */               BoUsers update = (BoUsers)this.boUserServicess.update(boUsers);
/* 14705 */               this.requestJson.setMessage("头像上传成功");
/* 14706 */               map.put("headPic", update.getHeadPic());
//						  logger.info("······headPic:"+update.getHeadPic());
///* 14707 */               break label1208;
						  return "success";
/* 14708 */             }BoUsers boUser = this.boUserServicess.findByUserPhone(userPhone);
/* 14709 */             String dir = "uploads/headpic";
/* 14710 */             System.err.println("<>>>>>>>>fileupload " + this.fileupload);
///* 14711 */             String filePath = this.fileService.saveToDir(this.fileupload, 
///* 14712 */               userCode + "head" + createRandomVcodesss() + ".jpg", dir);
						String filePath = this.fileService.saveToDir(this.fileupload, 
								userCode + "head" + ".jpg", dir);
//						logger.info("头像的地址 filePath>>"+filePath);
/* 14713 */             boUser.setHeadPic(filePath);
/* 14714 */             BoUsers update = (BoUsers)this.boUserServicess.update(boUser);
/* 14715 */             this.requestJson.setMessage("头像上传成功");
/* 14716 */             map.put("headPic", update.getHeadPic());
//					    logger.info("·····headPic:"+update.getHeadPic());
/* 14717 */             return "success";
/*       */           }
/*       */           catch (Exception e)
/*       */           {
/* 14721 */             this.requestJson.setData(map);
/* 14722 */             this.requestJson.setMessage("头像上传失败");
/* 14723 */             this.requestJson.setSuccess(false);
/* 14724 */             e.printStackTrace();
/* 14725 */             return "success";
/*       */           }
/*       */         } else {
/* 14728 */           this.requestJson.setData(map);
/* 14729 */           this.requestJson.setMessage("超时了");
/* 14730 */           this.requestJson.setSuccess(false);
/*       */         }
/*       */       }
/*       */       else {
/* 14734 */         System.err.println("验证不通过");
/* 14735 */         this.requestJson.setData(map);
/* 14736 */         this.requestJson.setMessage("验证不通过");
/* 14737 */         this.requestJson.setSuccess(false);
/*       */       }
				
/*       */     }
///* 14740 */     label1208: return "success";
				return "success";
		
/*       */   }
/*       */ 
/*       */   @Action(value="getroom", results={@org.apache.struts2.convention.annotation.Result(type="json", params={"root", "requestJson"})})
/*       */   public String getRoom()
/*       */   {
/* 14750 */     this.requestJson = new RequestJson();
/* 14751 */     Map mapss = new HashMap();
/* 14752 */     HttpServletRequest request = ServletActionContext.getRequest();
/* 14753 */     String header = request.getHeader("timestamp");
/* 14754 */     String header2 = request.getHeader("nonce");
/* 14755 */     String header3 = request.getHeader("sign");
/* 14756 */     String header4 = request.getHeader("access_token");
/* 14757 */     String userCode = request.getHeader("userCode");//被授权者应该只有部门房间或设备使用权  这里取的应该时AUTHORIZATION_USER_CODE字段，此字段为空的话就是取USER_CODE字段
/*       */     List list_floor;
				logger.info("-------getroom-------");
				Enumeration pNames=request.getParameterNames();
				while(pNames.hasMoreElements()){
				    String name=(String)pNames.nextElement();
				    String value=request.getParameter(name);
				    logger.info(name + " == " + value);
				}
				logger.info("获取房间信息的userCode>>"+userCode);//若是被授权者：授权者userCode,userPhone,被授权者userCode ； 若是授权者：本身userCode,userPhone 有可能不带userPhone
/* 14758 */     if (userCode.contains(",")) {//有逗号的情况----
/* 14759 */       String[] userCode2 = userCode.split(",");
//				  logger.info("userCode2[0]>>"+userCode2[0].trim().toString());// 2/28测试被授权的用户的userCode  ==授权者的userCode
/* 14760 */       BoUsers boUsers = this.boUserServicess.findByUserUserCode(userCode2[0].trim().toString());
//				  logger.info("boUsers>>"+boUsers);// 2/28 打印  授权者的信息（没有授权者才显示自己的用户信息）
/* 14761 */       BoUsers phone = this.boUserServicess.findByUserPhone(userCode2[1].trim().toString());
/* 14762 */       Boolean ral = isRal(header, header2, header3, header4, userCode, "查找用户的楼层房间列表接口");
/* 14763 */       if (ral.booleanValue()) {
/* 14764 */         System.err.println("验证通过");
/* 14765 */         if ((phone == null) || (boUsers == null)) {
/* 14766 */           this.requestJson.setData(mapss);
/* 14767 */           this.requestJson.setMessage("Invalid_User");
/* 14768 */           this.requestJson.setSuccess(true);
/*       */         }
/* 14770 */         else if (header4.equals(phone.getAccessToken())) {
/* 14771 */           Long accessToken = Long.valueOf(header);
/* 14772 */           if (accessToken.longValue() < Long.valueOf(phone.getAccessTokenTime()).longValue()) {
/* 14773 */             List<BoRoom> list = this.boRoomService.getAllListByUserCode(userCode2[0].trim().toString());
/* 14774 */             List<BoFloor> list2 = this.boFloorService.getAllListByUserCode(userCode2[0].trim().toString());
/* 14775 */             if ((list.size() <= 0) && (list2.size() <= 0)) {
/* 14776 */               Map maps = new HashMap();
/* 14777 */               this.requestJson.setMessage("没有找到");
/* 14778 */               this.requestJson.setData(maps);
/* 14779 */               this.requestJson.setSuccess(true);
/* 14780 */               return "success";
/*       */             }
/* 14782 */             List voList = new ArrayList();
/* 14783 */             List list_room = new ArrayList();
/* 14784 */             Map map_room = new HashMap();
/*       */ 
/* 14786 */             list_floor = new ArrayList();
/*       */ 
/* 14788 */             for (BoFloor boFloor : list2) {
							String floorName=boFloor.getFloorName().toString();
							logger.info("floorName test>"+floorName);
	                    	if(!floorName .equals("xxx")) {
	/* 14789 */               Map map = new HashMap();
	/* 14791 */               map.put("floorCode", boFloor.getFloorCode().toString());
//						  System.out.println("floor floorCode:"+boFloor.getFloorCode().toString());
	/* 14792 */               map.put("floorName", boFloor.getFloorName().toString());
							  logger.info("floorName :"+boFloor.getFloorName().toString());
	/* 14793 */               list_floor.add(map);                    		
	                    	}
/*       */             }
/* 14795 */             map_room.put("floorInfo", list_floor);//楼层 信息
/*       */ 
/* 14797 */             for (BoRoom boRoom : list) {
							String roomName=boRoom.getRoomName().toString();
//							logger.info("不允许存在 客厅>"+!roomName .equals("客厅"));
//							if(!roomName.equals("客厅")) {
	/* 14798 */               Map map = new HashMap();
	/* 14799 */               BoFloor findByFloorCode = this.boFloorService.findByFloorCode(boRoom.getFloorCode());
	/*       */ 
	/* 14801 */               map.put("roomCode", boRoom.getRoomCode().toString());
//						  System.out.println("Room roomCode:"+boRoom.getRoomCode().toString());
	/* 14802 */               map.put("roomName", boRoom.getRoomName().toString());
							  logger.info("roomName:"+boRoom.getRoomName().toString());
	/* 14803 */               map.put("floorCode", boRoom.getFloorCode().toString());
//						  System.out.println("floorCode:"+boRoom.getFloorCode().toString());
	/* 14804 */               list_room.add(map);								
//							}
/*       */             }
/*       */ 
/* 14808 */             map_room.put("roomInfo", list_room);//房间  信息
/*       */ 
/* 14811 */             voList.add(map_room);
/* 14812 */             this.requestJson.setData(voList);
/*       */ 
/* 14814 */             this.requestJson.setSuccess(true);
/*       */           }
/*       */           else {
/* 14817 */             this.requestJson.setData(mapss);
/* 14818 */             this.requestJson.setMessage("超时了");
/* 14819 */             this.requestJson.setSuccess(false);
/*       */           }
/*       */         } else {
/* 14822 */           System.err.println("超时了");
/* 14823 */           this.requestJson.setData(mapss);
/* 14824 */           this.requestJson.setMessage("超时了");
/* 14825 */           this.requestJson.setSuccess(false);
/*       */         }
/*       */ 
/*       */       }
/*       */       else
/*       */       {
/* 14831 */         System.err.println("验证不通过");
/* 14832 */         this.requestJson.setData(mapss);
/* 14833 */         this.requestJson.setMessage("验证不通过");
/* 14834 */         this.requestJson.setSuccess(false);
/*       */       }
/*       */     } else {//没有逗号的情况----
/* 14837 */       Boolean ral = isRal(header, header2, header3, header4, userCode, "修改主机昵称");
/* 14838 */       if (ral.booleanValue()) {
/* 14839 */         System.err.println("验证通过");
/* 14840 */         BoUsers boUsers = this.boUserServicess.findByUserUserCode(userCode);
/* 14841 */         if (boUsers == null) {
/* 14842 */           this.requestJson.setData(mapss);
/* 14843 */           this.requestJson.setMessage("Invalid_User");
/* 14844 */           this.requestJson.setSuccess(true);
/*       */         } else {
/* 14846 */           Long accessToken = Long.valueOf(header);
/* 14847 */           if (accessToken.longValue() < Long.valueOf(boUsers.getAccessTokenTime()).longValue()) {
/* 14848 */             List<BoRoom> list = this.boRoomService.getAllListByUserCode(userCode);
/* 14849 */             List<BoFloor> list2 = this.boFloorService.getAllListByUserCode(userCode);
                        logger.info("list2>>>>>>"+list2);
/* 14850 */             if ((list.size() <= 0) && (list2.size() <= 0)) {
/* 14851 */               Map maps = new HashMap();
/* 14852 */               this.requestJson.setMessage("没有找到");
/* 14853 */               this.requestJson.setData(maps);
/* 14854 */               this.requestJson.setSuccess(true);
/* 14855 */               return "success";
/*       */             }
/* 14857 */             List voList = new ArrayList();
/* 14858 */             List list_room = new ArrayList();
/* 14859 */             Map map_room = new HashMap();
/*       */ 
/* 14861 */             list_floor = new ArrayList();
/*       */ 
/* 14863 */             for (BoFloor boFloor : list2)
/*       */             {
							String floorName=boFloor.getFloorName().toString();
							logger.info("floorName test01>"+floorName);
							if(!floorName .equals("xxx")) {
	/* 14865 */                 Map map = new HashMap();
	/*       */ 
	/* 14867 */                 map.put("floorCode", boFloor.getFloorCode().toString());
	/* 14868 */                 map.put("floorName", boFloor.getFloorName().toString());
								logger.info("floorName 01:"+boFloor.getFloorName().toString());
	/* 14869 */                 list_floor.add(map);							
							}
/*       */             }
/* 14871 */             map_room.put("floorInfo", list_floor);//楼层 信息
/*       */ 
/* 14873 */             for (BoRoom boRoom : list) {
							String roomName=boRoom.getRoomName().toString();
							logger.info("允许存在 客厅 01?>"+!roomName .equals("客厅"));
//                            if(!roomName .equals("客厅")) {                	  
  /* 14874 */               	Map map = new HashMap();
  /* 14875 */               	BoFloor findByFloorCode = this.boFloorService.findByFloorCode(boRoom.getFloorCode());
  /* 14877 */               	map.put("roomCode", boRoom.getRoomCode().toString());
  /* 14878 */               	map.put("roomName", boRoom.getRoomName().toString());
  								logger.info("roomName 01:"+boRoom.getRoomName().toString());
  /* 14879 */               	map.put("floorCode", boRoom.getFloorCode().toString());
  /* 14880 */               	list_room.add(map);
//                            }
/*       */             }
/*       */ 
/* 14884 */             map_room.put("roomInfo", list_room);//房间  信息
/*       */ 
/* 14887 */             voList.add(map_room);
/* 14888 */             this.requestJson.setData(voList);
/*       */ 
/* 14890 */             this.requestJson.setSuccess(true);
/*       */           }
/*       */           else {
/* 14893 */             this.requestJson.setMessage("超时了");
/* 14894 */             this.requestJson.setSuccess(false);
/*       */           }
/*       */         }
/*       */       } else {
/* 14898 */         System.err.println("验证不通过");
/* 14899 */         this.requestJson.setMessage("验证不通过");
/* 14900 */         this.requestJson.setSuccess(false);
/*       */       }
/*       */     }
/* 14903 */     return "success";
/*       */   }
/*       */ 
/*       */   @Action(value="getuser", results={@org.apache.struts2.convention.annotation.Result(type="json", params={"root", "requestJson"})})
/*       */   public String getUser()
/*       */   {
/* 14917 */     this.requestJson = new RequestJson();
/* 14918 */     HttpServletRequest request = ServletActionContext.getRequest();
/* 14919 */     Map map = new HashMap();
/* 14920 */     String header = request.getHeader("timestamp");
/* 14921 */     String header2 = request.getHeader("nonce");
/* 14922 */     String header3 = request.getHeader("sign");
/* 14923 */     String header4 = request.getHeader("access_token");
/* 14924 */     String userCode = request.getHeader("userCode");
/* 14925 */     if (userCode.contains(",")) {
/* 14926 */       String[] userCode2 = userCode.split(",");
/* 14927 */       BoUsers boUsers = this.boUserServicess.findByUserUserCode(userCode2[0].trim().toString());
/* 14928 */       BoUsers phone = this.boUserServicess.findByUserPhone(userCode2[1].trim().toString());
/* 14929 */       Boolean ral = isRal(header, header2, header3, header4, userCode, "修改主机昵称");
/* 14930 */       if (ral.booleanValue()) {
/* 14931 */         System.err.println("验证通过");
/* 14932 */         Long accessToken = Long.valueOf(header);
/*       */ 
/* 14934 */         if ((phone == null) || (boUsers == null)) {
/* 14935 */           this.requestJson.setData(map);
/* 14936 */           this.requestJson.setMessage("Invalid_User");
/* 14937 */           this.requestJson.setSuccess(true);
/*       */         }
/* 14939 */         else if (header4.equals(phone.getAccessToken())) {
/* 14940 */           if (accessToken.longValue() < Long.valueOf(phone.getAccessTokenTime()).longValue()) {
/* 14941 */             if ((this.userPhone == null) || (this.userPhone.equals(""))) {
/* 14942 */               map.put("userName", boUsers.getUserName().toString());
/* 14943 */               map.put("userSex", boUsers.getUserSex().toString());
/* 14944 */               String city = boUsers.getCity().toString();
/*       */ 
/* 14946 */               String[] citySplit = city.split(",");
/*       */               String c;
///*       */               String c;
/* 14947 */               if (city.isEmpty())
/* 14948 */                 c = "";
/*       */               else {
/* 14950 */                 c = citySplit[1] + "-" + citySplit[2];
/*       */               }
/*       */ 
/* 14953 */               map.put("city", c);
/* 14954 */               map.put("province_city_area", boUsers.getCity().toString());
/* 14955 */               map.put("signature", boUsers.getSignature().toString());
/* 14956 */               map.put("headPic", boUsers.getHeadPic().toString());
//						  logger.info("·headPic>>"+boUsers.getHeadPic().toString());
/* 14957 */               this.requestJson.setPage(Integer.valueOf(1));
/* 14958 */               this.requestJson.setTotalPages(Integer.valueOf(1));
/* 14959 */               this.requestJson.setTotal(Integer.valueOf(1));
/* 14960 */               this.requestJson.setData(map);
/*       */             } else {
/* 14962 */               BoUsers users = this.boUserServicess.findByUserPhone(this.userPhone);
/*       */ 
/* 14964 */               map.put("userName", users.getUserName().toString());
/* 14965 */               map.put("userSex", users.getUserSex().toString());
/* 14966 */               String city = users.getCity().toString();
/*       */ 
/* 14968 */               String[] citySplit = city.split(",");
/*       */               String c;
///*       */               String c;
/* 14969 */               if (city.isEmpty())
/* 14970 */                 c = "";
/*       */               else {
/* 14972 */                 c = citySplit[1] + "-" + citySplit[2];
/*       */               }
/*       */ 
/* 14975 */               map.put("city", c);
/* 14976 */               map.put("province_city_area", users.getCity().toString());
/* 14977 */               map.put("signature", users.getSignature().toString());
/* 14978 */               map.put("headPic", users.getHeadPic().toString());
//						  logger.info("··headPic>>"+boUsers.getHeadPic().toString());
/* 14979 */               this.requestJson.setPage(Integer.valueOf(1));
/* 14980 */               this.requestJson.setTotalPages(Integer.valueOf(1));
/* 14981 */               this.requestJson.setTotal(Integer.valueOf(1));
/* 14982 */               this.requestJson.setData(map);
/*       */             }
/*       */           }
/*       */           else {
/* 14986 */             System.err.println();
/* 14987 */             this.requestJson.setData(map);
/* 14988 */             this.requestJson.setMessage("超时了");
/* 14989 */             this.requestJson.setSuccess(false);
/*       */           }
/*       */         } else {
/* 14992 */           this.requestJson.setData(map);
/* 14993 */           this.requestJson.setMessage("超时了");
/* 14994 */           this.requestJson.setSuccess(false);
/*       */         }
/*       */       }
/*       */       else
/*       */       {
/* 14999 */         System.err.println("验证不通过");
/* 15000 */         this.requestJson.setMessage("验证不通过");
/* 15001 */         this.requestJson.setSuccess(false);
/*       */       }
/*       */     } else {
/* 15004 */       Boolean ral = isRal(header, header2, header3, header4, userCode, "修改主机昵称");
/* 15005 */       if (ral.booleanValue()) {
/* 15006 */         System.err.println("验证通过");
/* 15007 */         Long accessToken = Long.valueOf(header);
/* 15008 */         BoUsers boUsers = this.boUserServicess.findByUserUserCode(userCode);
/* 15009 */         if (boUsers == null) {
/* 15010 */           this.requestJson.setData(map);
/* 15011 */           this.requestJson.setMessage("Invalid_User");
/* 15012 */           this.requestJson.setSuccess(true);
/*       */         }
/* 15014 */         else if (header4.equals(boUsers.getAccessToken())) {
/* 15015 */           if (accessToken.longValue() < Long.valueOf(boUsers.getAccessTokenTime()).longValue()) {
/* 15016 */             if ((this.userPhone == null) || (this.userPhone.equals(""))) {
/* 15017 */               map.put("userName", boUsers.getUserName().toString());
/* 15018 */               map.put("userSex", boUsers.getUserSex().toString());
/* 15019 */               String city = boUsers.getCity().toString();
/*       */ 
/* 15021 */               String[] citySplit = city.split(",");
/*       */               String c;
///*       */               String c;
/* 15022 */               if (city.isEmpty())
/* 15023 */                 c = "";
/*       */               else {
/* 15025 */                 c = citySplit[1] + "-" + citySplit[2];
/*       */               }
/*       */ 
/* 15028 */               map.put("city", c);
/* 15029 */               map.put("province_city_area", boUsers.getCity().toString());
/* 15030 */               map.put("signature", boUsers.getSignature().toString());
/* 15031 */               map.put("headPic", boUsers.getHeadPic().toString());
//						  logger.info("···headPic>>"+boUsers.getHeadPic().toString());
/* 15032 */               this.requestJson.setPage(Integer.valueOf(1));
/* 15033 */               this.requestJson.setTotalPages(Integer.valueOf(1));
/* 15034 */               this.requestJson.setTotal(Integer.valueOf(1));
/* 15035 */               this.requestJson.setData(map);
/*       */             } else {
/* 15037 */               BoUsers users = this.boUserServicess.findByUserPhone(this.userPhone);
/*       */ 
/* 15039 */               map.put("userName", users.getUserName().toString());
/* 15040 */               map.put("userSex", users.getUserSex().toString());
/* 15041 */               String city = users.getCity().toString();
/*       */ 
/* 15043 */               String[] citySplit = city.split(",");
/*       */               String c;
///*       */               String c;
/* 15044 */               if (city.isEmpty())
/* 15045 */                 c = "";
/*       */               else {
/* 15047 */                 c = citySplit[1] + "-" + citySplit[2];
/*       */               }
/*       */ 
/* 15050 */               map.put("city", c);
/* 15051 */               map.put("province_city_area", users.getCity().toString());
/* 15052 */               map.put("signature", users.getSignature().toString());
/* 15053 */               map.put("headPic", users.getHeadPic().toString());
//						  logger.info("····headPic>>"+boUsers.getHeadPic().toString());
/* 15054 */               this.requestJson.setPage(Integer.valueOf(1));
/* 15055 */               this.requestJson.setTotalPages(Integer.valueOf(1));
/* 15056 */               this.requestJson.setTotal(Integer.valueOf(1));
/* 15057 */               this.requestJson.setData(map);
/*       */             }
/*       */           }
/*       */           else {
/* 15061 */             this.requestJson.setData(map);
/* 15062 */             this.requestJson.setMessage("超时了");
/* 15063 */             this.requestJson.setSuccess(false);
/*       */           }
/*       */         } else {
/* 15066 */           this.requestJson.setData(map);
/* 15067 */           this.requestJson.setMessage("超时了");
/* 15068 */           this.requestJson.setSuccess(false);
/*       */         }
/*       */       }
/*       */       else
/*       */       {
/* 15073 */         System.err.println("验证不通过");
/* 15074 */         this.requestJson.setMessage("验证不通过");
/* 15075 */         this.requestJson.setSuccess(false);
/*       */       }
/*       */     }
/* 15078 */     return "success";
/*       */   }
/*       */ 
/*       */   @Action(value="setcity", results={@org.apache.struts2.convention.annotation.Result(type="json", params={"root", "requestJson"})})
/*       */   public String setCity()
/*       */   {
/* 15088 */     this.requestJson = new RequestJson();
/* 15089 */     HttpServletRequest request = ServletActionContext.getRequest();
/* 15090 */     Map map = new HashMap();
/* 15091 */     String header = request.getHeader("timestamp");
/* 15092 */     String header2 = request.getHeader("nonce");
/* 15093 */     String header3 = request.getHeader("sign");
/* 15094 */     String header4 = request.getHeader("access_token");
/* 15095 */     String userCode = request.getHeader("userCode");
/*       */ 
/* 15097 */     if (userCode.contains(",")) {
/* 15098 */       String[] userCode2 = userCode.split(",");
/* 15099 */       BoUsers boUsers = this.boUserServicess.findByUserUserCode(userCode2[0].trim().toString());
/* 15100 */       BoUsers phone = this.boUserServicess.findByUserPhone(userCode2[1].trim().toString());
/* 15101 */       Boolean ral = isRal(header, header2, header3, header4, userCode, "修改主机昵称");
/* 15102 */       if (ral.booleanValue()) {
/* 15103 */         System.err.println("验证通过");
/* 15104 */         Long accessToken = Long.valueOf(header);
/*       */ 
/* 15106 */         if ((phone == null) || (boUsers == null)) {
/* 15107 */           this.requestJson.setData(map);
/* 15108 */           this.requestJson.setMessage("Invalid_User");
/* 15109 */           this.requestJson.setSuccess(true);
/*       */         }
/* 15111 */         else if (header4.equals(phone.getAccessToken())) {
/* 15112 */           if (accessToken.longValue() < Long.valueOf(phone.getAccessTokenTime()).longValue()) {
/* 15113 */             if ((this.userPhone == null) || (this.userPhone.equals(""))) {
/* 15114 */               boUsers.setCity(this.city);
/* 15115 */               BoUsers update = (BoUsers)this.boUserServicess.update(boUsers);
/* 15116 */               if (update != null) {
/* 15117 */                 this.requestJson.setData(map);
/* 15118 */                 this.requestJson.setMessage("修改成功");
/* 15119 */                 this.requestJson.setSuccess(true);
/*       */               } else {
/* 15121 */                 this.requestJson.setData(map);
/* 15122 */                 this.requestJson.setMessage("修改失败");
/* 15123 */                 this.requestJson.setSuccess(false);
/*       */               }
/*       */             }
/*       */             else {
/* 15127 */               phone.setCity(this.city);
/* 15128 */               BoUsers update = (BoUsers)this.boUserServicess.update(phone);
/* 15129 */               if (update != null) {
/* 15130 */                 this.requestJson.setData(map);
/* 15131 */                 this.requestJson.setMessage("修改成功");
/* 15132 */                 this.requestJson.setSuccess(true);
/*       */               } else {
/* 15134 */                 this.requestJson.setData(map);
/* 15135 */                 this.requestJson.setMessage("修改失败");
/* 15136 */                 this.requestJson.setSuccess(false);
/*       */               }
/*       */             }
/*       */           }
/*       */           else
/*       */           {
/* 15142 */             this.requestJson.setData(map);
/* 15143 */             this.requestJson.setMessage("超时了");
/* 15144 */             this.requestJson.setSuccess(false);
/*       */           }
/*       */         } else {
/* 15147 */           System.err.println("超时了");
/* 15148 */           this.requestJson.setData(map);
/* 15149 */           this.requestJson.setMessage("超时了");
/* 15150 */           this.requestJson.setSuccess(false);
/*       */         }
/*       */       }
/*       */       else {
/* 15154 */         System.err.println("验证不通过");
/* 15155 */         this.requestJson.setData(map);
/* 15156 */         this.requestJson.setMessage("验证不通过");
/* 15157 */         this.requestJson.setSuccess(false);
/*       */       }
/*       */     } else {
/* 15160 */       Boolean ral = isRal(header, header2, header3, header4, userCode, "修改主机昵称");
/* 15161 */       if (ral.booleanValue()) {
/* 15162 */         System.err.println("验证通过");
/* 15163 */         Long accessToken = Long.valueOf(header);
/* 15164 */         BoUsers boUsers = this.boUserServicess.findByUserUserCode(userCode);
/* 15165 */         if (boUsers == null) {
/* 15166 */           this.requestJson.setData(map);
/* 15167 */           this.requestJson.setMessage("Invalid_User");
/* 15168 */           this.requestJson.setSuccess(true);
/*       */         }
/* 15170 */         else if (accessToken.longValue() < Long.valueOf(boUsers.getAccessTokenTime()).longValue()) {
/* 15171 */           if ((this.userPhone == null) || (this.userPhone.equals(""))) {
/* 15172 */             boUsers.setCity(this.city);
/* 15173 */             BoUsers update = (BoUsers)this.boUserServicess.update(boUsers);
/* 15174 */             if (update != null) {
/* 15175 */               this.requestJson.setData(map);
/* 15176 */               this.requestJson.setMessage("修改成功");
/* 15177 */               this.requestJson.setSuccess(true);
/*       */             } else {
/* 15179 */               this.requestJson.setData(map);
/* 15180 */               this.requestJson.setMessage("修改失败");
/* 15181 */               this.requestJson.setSuccess(false);
/*       */             }
/*       */           } else {
/* 15184 */             BoUsers user = this.boUserServicess.findByUserPhone(this.userPhone);
/* 15185 */             user.setCity(this.city);
/* 15186 */             BoUsers update = (BoUsers)this.boUserServicess.update(user);
/* 15187 */             if (update != null) {
/* 15188 */               this.requestJson.setData(map);
/* 15189 */               this.requestJson.setMessage("修改成功");
/* 15190 */               this.requestJson.setSuccess(true);
/*       */             } else {
/* 15192 */               this.requestJson.setData(map);
/* 15193 */               this.requestJson.setMessage("修改失败");
/* 15194 */               this.requestJson.setSuccess(false);
/*       */             }
/*       */           }
/*       */         }
/*       */         else
/*       */         {
/* 15200 */           this.requestJson.setData(map);
/* 15201 */           this.requestJson.setMessage("超时了");
/* 15202 */           this.requestJson.setSuccess(false);
/*       */         }
/*       */       }
/*       */       else {
/* 15206 */         System.err.println("验证不通过");
/* 15207 */         this.requestJson.setData(map);
/* 15208 */         this.requestJson.setMessage("验证不通过");
/* 15209 */         this.requestJson.setSuccess(false);
/*       */       }
/*       */     }
/* 15212 */     return "success";
/*       */   }
/*       */ 
/*       */   @Action(value="setusername", results={@org.apache.struts2.convention.annotation.Result(type="json", params={"root", "requestJson"})})
/*       */   public String setuserName()
/*       */   {
/* 15223 */     this.requestJson = new RequestJson();
/* 15224 */     Map map = new HashMap();
/* 15225 */     HttpServletRequest request = ServletActionContext.getRequest();
/*       */ 
/* 15227 */     String header = request.getHeader("timestamp");
/* 15228 */     String header2 = request.getHeader("nonce");
/* 15229 */     String header3 = request.getHeader("sign");
/* 15230 */     String header4 = request.getHeader("access_token");
/* 15231 */     String userCode = request.getHeader("userCode");
/* 15232 */     if (userCode.contains(",")) {
/* 15233 */       String[] userCode2 = userCode.split(",");
/* 15234 */       BoUsers boUsers = this.boUserServicess.findByUserUserCode(userCode2[0].trim().toString());
/* 15235 */       BoUsers phone = this.boUserServicess.findByUserPhone(userCode2[1].trim().toString());
/* 15236 */       Boolean ral = isRal(header, header2, header3, header4, userCode, "设置用户昵称");
/* 15237 */       if (ral.booleanValue()) {
/* 15238 */         Long accessToken = Long.valueOf(header);
/* 15239 */         if ((phone == null) || (boUsers == null)) {
/* 15240 */           this.requestJson.setData(map);
/* 15241 */           this.requestJson.setMessage("Invalid_User");
/* 15242 */           this.requestJson.setSuccess(true);
/*       */         }
/* 15244 */         else if (header4.equals(phone.getAccessToken())) {
/* 15245 */           if (accessToken.longValue() < Long.valueOf(phone.getAccessTokenTime()).longValue()) {
/* 15246 */             if ((this.userPhone == null) || (this.userPhone.equals(""))) {
/* 15247 */               boUsers.setUserName(this.userName);
/* 15248 */               BoUsers update = (BoUsers)this.boUserServicess.update(boUsers);
/* 15249 */               if (update != null) {
/* 15250 */                 this.requestJson.setData(map);
/* 15251 */                 this.requestJson.setMessage("修改成功");
/* 15252 */                 this.requestJson.setSuccess(true);
/*       */               } else {
/* 15254 */                 this.requestJson.setData(map);
/* 15255 */                 this.requestJson.setMessage("修改失败");
/* 15256 */                 this.requestJson.setSuccess(false);
/*       */               }
/*       */             } else {
/* 15259 */               phone.setUserName(this.userName);
/* 15260 */               BoUsers update = (BoUsers)this.boUserServicess.update(phone);
/* 15261 */               if (update != null) {
/* 15262 */                 this.requestJson.setData(map);
/* 15263 */                 this.requestJson.setMessage("修改成功");
/* 15264 */                 this.requestJson.setSuccess(true);
/*       */               } else {
/* 15266 */                 this.requestJson.setData(map);
/* 15267 */                 this.requestJson.setMessage("修改失败");
/* 15268 */                 this.requestJson.setSuccess(false);
/*       */               }
/*       */             }
/*       */           } else {
/* 15272 */             this.requestJson.setMessage("超时了");
/* 15273 */             this.requestJson.setSuccess(false);
/*       */           }
/*       */         } else {
/* 15276 */           System.err.println("超时了");
/* 15277 */           this.requestJson.setData(map);
/* 15278 */           this.requestJson.setMessage("超时了");
/* 15279 */           this.requestJson.setSuccess(false);
/*       */         }
/*       */ 
/*       */       }
/*       */       else
/*       */       {
/* 15285 */         System.err.println("验证不通过");
/* 15286 */         this.requestJson.setMessage("验证不通过");
/* 15287 */         this.requestJson.setSuccess(false);
/*       */       }
/*       */     } else {
/* 15290 */       Boolean ral = isRal(header, header2, header3, header4, userCode, "修改主机昵称");
/* 15291 */       if (ral.booleanValue()) {
/* 15292 */         Long accessToken = Long.valueOf(header);
/* 15293 */         BoUsers boUsers = this.boUserServicess.findByUserUserCode(userCode);
/* 15294 */         if (boUsers == null) {
/* 15295 */           this.requestJson.setData(map);
/* 15296 */           this.requestJson.setMessage("Invalid_User");
/* 15297 */           this.requestJson.setSuccess(true);
/*       */         }
/* 15299 */         else if (accessToken.longValue() < Long.valueOf(boUsers.getAccessTokenTime()).longValue()) {
/* 15300 */           if ((this.userPhone == null) || (this.userPhone.equals(""))) {
/* 15301 */             boUsers.setUserName(this.userName);
/* 15302 */             BoUsers update = (BoUsers)this.boUserServicess.update(boUsers);
/* 15303 */             if (update != null) {
/* 15304 */               this.requestJson.setData(map);
/* 15305 */               this.requestJson.setMessage("修改成功");
/* 15306 */               this.requestJson.setSuccess(true);
/*       */             } else {
/* 15308 */               this.requestJson.setData(map);
/* 15309 */               this.requestJson.setMessage("修改失败");
/* 15310 */               this.requestJson.setSuccess(false);
/*       */             }
/*       */           } else {
/* 15313 */             BoUsers user = this.boUserServicess.findByUserPhone(this.userPhone);
/* 15314 */             user.setUserName(this.userName);
/* 15315 */             BoUsers update = (BoUsers)this.boUserServicess.update(user);
/* 15316 */             if (update != null) {
/* 15317 */               this.requestJson.setData(map);
/* 15318 */               this.requestJson.setMessage("修改成功");
/* 15319 */               this.requestJson.setSuccess(true);
/*       */             } else {
/* 15321 */               this.requestJson.setData(map);
/* 15322 */               this.requestJson.setMessage("修改失败");
/* 15323 */               this.requestJson.setSuccess(false);
/*       */             }
/*       */           }
/*       */         }
/*       */         else
/*       */         {
/* 15329 */           this.requestJson.setMessage("超时了");
/* 15330 */           this.requestJson.setSuccess(false);
/*       */         }
/*       */       }
/*       */       else {
/* 15334 */         System.err.println("验证不通过");
/* 15335 */         this.requestJson.setMessage("验证不通过");
/* 15336 */         this.requestJson.setSuccess(false);
/*       */       }
/*       */     }
/* 15339 */     return "success";
/*       */   }
/*       */ 
/*       */   @Action(value="setusersex", results={@org.apache.struts2.convention.annotation.Result(type="json", params={"root", "requestJson"})})
/*       */   public String setuserSex()
/*       */   {
/* 15350 */     this.requestJson = new RequestJson();
/* 15351 */     HttpServletRequest request = ServletActionContext.getRequest();
/* 15352 */     Map map = new HashMap();
/* 15353 */     String header = request.getHeader("timestamp");
/* 15354 */     String header2 = request.getHeader("nonce");
/* 15355 */     String header3 = request.getHeader("sign");
/* 15356 */     String header4 = request.getHeader("access_token");
/* 15357 */     String userCode = request.getHeader("userCode");
/* 15358 */     if (userCode.contains(",")) {
/* 15359 */       String[] userCode2 = userCode.split(",");
/* 15360 */       BoUsers boUsers = this.boUserServicess.findByUserUserCode(userCode2[0].trim().toString());
/* 15361 */       BoUsers phone = this.boUserServicess.findByUserPhone(userCode2[1].trim().toString());
/* 15362 */       Boolean ral = isRal(header, header2, header3, header4, userCode, "设置用户性别");
/* 15363 */       if (ral.booleanValue()) {
/* 15364 */         System.err.println("验证通过");
/* 15365 */         Long accessToken = Long.valueOf(header);
/* 15366 */         if ((phone == null) || (boUsers == null)) {
/* 15367 */           this.requestJson.setData(map);
/* 15368 */           this.requestJson.setMessage("Invalid_User");
/* 15369 */           this.requestJson.setSuccess(true);
/*       */         }
/* 15371 */         else if (header4.equals(phone.getAccessToken())) {
/* 15372 */           if (accessToken.longValue() < Long.valueOf(phone.getAccessTokenTime()).longValue()) {
/* 15373 */             if ((this.userPhone == null) || (this.userPhone.equals(""))) {
/* 15374 */               boUsers.setUserSex(this.userSex);
/* 15375 */               BoUsers update = (BoUsers)this.boUserServicess.update(boUsers);
/* 15376 */               if (update != null) {
/* 15377 */                 this.requestJson.setData(map);
/* 15378 */                 this.requestJson.setMessage("修改成功");
/* 15379 */                 this.requestJson.setSuccess(true);
/*       */               } else {
/* 15381 */                 this.requestJson.setData(map);
/* 15382 */                 this.requestJson.setMessage("修改失败");
/* 15383 */                 this.requestJson.setSuccess(false);
/*       */               }
/*       */             } else {
/* 15386 */               phone.setUserSex(this.userSex);
/* 15387 */               BoUsers update = (BoUsers)this.boUserServicess.update(phone);
/* 15388 */               if (update != null) {
/* 15389 */                 this.requestJson.setData(map);
/* 15390 */                 this.requestJson.setMessage("修改成功");
/* 15391 */                 this.requestJson.setSuccess(true);
/*       */               } else {
/* 15393 */                 this.requestJson.setData(map);
/* 15394 */                 this.requestJson.setMessage("修改失败");
/* 15395 */                 this.requestJson.setSuccess(false);
/*       */               }
/*       */             }
/*       */           }
/*       */           else {
/* 15400 */             this.requestJson.setData(map);
/* 15401 */             this.requestJson.setMessage("超时了");
/* 15402 */             this.requestJson.setSuccess(false);
/*       */           }
/*       */         } else {
/* 15405 */           System.err.println("超时了");
/* 15406 */           this.requestJson.setData(map);
/* 15407 */           this.requestJson.setMessage("超时了");
/* 15408 */           this.requestJson.setSuccess(false);
/*       */         }
/*       */       }
/*       */       else
/*       */       {
/* 15413 */         System.err.println("验证不通过");
/* 15414 */         this.requestJson.setMessage("验证不通过");
/* 15415 */         this.requestJson.setSuccess(false);
/*       */       }
/*       */     } else {
/* 15418 */       Boolean ral = isRal(header, header2, header3, header4, userCode, "设置用户性别");
/* 15419 */       if (ral.booleanValue()) {
/* 15420 */         System.err.println("验证通过");
/* 15421 */         Long accessToken = Long.valueOf(header);
/* 15422 */         BoUsers boUsers = this.boUserServicess.findByUserUserCode(userCode);
/* 15423 */         if (boUsers == null) {
/* 15424 */           this.requestJson.setData(map);
/* 15425 */           this.requestJson.setMessage("Invalid_User");
/* 15426 */           this.requestJson.setSuccess(true);
/*       */         }
/* 15428 */         else if (accessToken.longValue() < Long.valueOf(boUsers.getAccessTokenTime()).longValue()) {
/* 15429 */           if ((this.userPhone == null) || (this.userPhone.equals(""))) {
/* 15430 */             boUsers.setUserSex(this.userSex);
/* 15431 */             BoUsers update = (BoUsers)this.boUserServicess.update(boUsers);
/* 15432 */             if (update != null) {
/* 15433 */               this.requestJson.setData(map);
/* 15434 */               this.requestJson.setMessage("修改成功");
/* 15435 */               this.requestJson.setSuccess(true);
/*       */             } else {
/* 15437 */               this.requestJson.setData(map);
/* 15438 */               this.requestJson.setMessage("修改失败");
/* 15439 */               this.requestJson.setSuccess(false);
/*       */             }
/*       */           } else {
/* 15442 */             BoUsers user = this.boUserServicess.findByUserPhone(this.userPhone);
/* 15443 */             user.setUserSex(this.userSex);
/* 15444 */             BoUsers update = (BoUsers)this.boUserServicess.update(user);
/* 15445 */             if (update != null) {
/* 15446 */               this.requestJson.setData(map);
/* 15447 */               this.requestJson.setMessage("修改成功");
/* 15448 */               this.requestJson.setSuccess(true);
/*       */             } else {
/* 15450 */               this.requestJson.setData(map);
/* 15451 */               this.requestJson.setMessage("修改失败");
/* 15452 */               this.requestJson.setSuccess(false);
/*       */             }
/*       */           }
/*       */         }
/*       */         else {
/* 15457 */           this.requestJson.setMessage("超时了");
/* 15458 */           this.requestJson.setSuccess(false);
/*       */         }
/*       */       }
/*       */       else
/*       */       {
/* 15463 */         System.err.println("验证不通过");
/* 15464 */         this.requestJson.setMessage("验证不通过");
/* 15465 */         this.requestJson.setSuccess(false);
/*       */       }
/*       */     }
/* 15468 */     return "success";
/*       */   }
/*       */ 
/*       */   @Action(value="setsignature", results={@org.apache.struts2.convention.annotation.Result(type="json", params={"root", "requestJson"})})
/*       */   public String setSignature()
/*       */   {
/* 15479 */     this.requestJson = new RequestJson();
/* 15480 */     Map map = new HashMap();
/*       */ 
/* 15482 */     HttpServletRequest request = ServletActionContext.getRequest();
/* 15483 */     String str = "";
/* 15484 */     Md5 md5 = new Md5();
/* 15485 */     String header = request.getHeader("timestamp");
/* 15486 */     String header2 = request.getHeader("nonce");
/* 15487 */     String header3 = request.getHeader("sign");
/* 15488 */     String header4 = request.getHeader("access_token");
/* 15489 */     String userCode = request.getHeader("userCode");
/* 15490 */     if (userCode.contains(",")) {
/* 15491 */       String[] userCode2 = userCode.split(",");
/*       */ 
/* 15493 */       System.err.println(">-- " + header2);
/*       */ 
/* 15496 */       str = str + "access_token=";
/* 15497 */       str = str + header4;
/*       */ 
/* 15499 */       str = str + "&nonce=";
/* 15500 */       str = str + header2;
/*       */ 
/* 15502 */       str = str + "&timestamp=";
/* 15503 */       str = str + header;
/*       */ 
/* 15505 */       str = str + "&userCode=";
/* 15506 */       str = str + userCode;
/*       */ 
/* 15508 */       str = str + "12345";
/* 15509 */       String sign = md5.getMD5ofStr(str).toLowerCase();
/* 15510 */       System.err.println("sign>-- " + sign);
/* 15511 */       BoUsers boUsers = this.boUserServicess.findByUserUserCode(userCode2[0].trim().toString());
/* 15512 */       BoUsers phone = this.boUserServicess.findByUserPhone(userCode2[1].trim().toString());
/* 15513 */       if (header3.equals(sign)) {
/* 15514 */         Long accessToken = Long.valueOf(header);
/* 15515 */         if ((phone == null) || (boUsers == null)) {
/* 15516 */           this.requestJson.setData(map);
/* 15517 */           this.requestJson.setMessage("Invalid_User");
/* 15518 */           this.requestJson.setSuccess(true);
/*       */         }
/* 15520 */         else if (header4.equals(phone.getAccessToken())) {
/* 15521 */           if (accessToken.longValue() < Long.valueOf(phone.getAccessTokenTime()).longValue()) {
/* 15522 */             if ((this.userPhone == null) || (this.userPhone.equals(""))) {
/* 15523 */               boUsers.setSignature(this.signature);
/* 15524 */               this.boUserServicess.update(boUsers);
/* 15525 */               this.requestJson.setMessage("修改成功");
/* 15526 */               this.requestJson.setSuccess(true);
/*       */             } else {
/* 15528 */               phone.setSignature(this.signature);
/* 15529 */               this.boUserServicess.update(phone);
/* 15530 */               this.requestJson.setMessage("修改成功");
/* 15531 */               this.requestJson.setSuccess(true);
/*       */             }
/*       */           } else {
/* 15534 */             this.requestJson.setData(map);
/* 15535 */             this.requestJson.setMessage("超时了");
/* 15536 */             this.requestJson.setSuccess(false);
/*       */           }
/*       */         } else {
/* 15539 */           System.err.println("超时了");
/* 15540 */           this.requestJson.setData(map);
/* 15541 */           this.requestJson.setMessage("超时了");
/* 15542 */           this.requestJson.setSuccess(false);
/*       */         }
/*       */ 
/*       */       }
/*       */       else
/*       */       {
/* 15548 */         this.requestJson.setData(map);
/* 15549 */         this.requestJson.setMessage("验证不通过");
/* 15550 */         this.requestJson.setSuccess(false);
/*       */       }
/*       */     } else {
/* 15553 */       System.err.println(">-- " + header2);
/*       */ 
/* 15556 */       str = str + "access_token=";
/* 15557 */       str = str + header4;
/*       */ 
/* 15559 */       str = str + "&nonce=";
/* 15560 */       str = str + header2;
/*       */ 
/* 15562 */       str = str + "&timestamp=";
/* 15563 */       str = str + header;
/*       */ 
/* 15565 */       str = str + "&userCode=";
/* 15566 */       str = str + userCode;
/*       */ 
/* 15568 */       str = str + "12345";
/* 15569 */       String sign = md5.getMD5ofStr(str).toLowerCase();
/* 15570 */       System.err.println("sign>-- " + sign);
/* 15571 */       if (header3.equals(sign)) {
/* 15572 */         Long accessToken = Long.valueOf(header);
/* 15573 */         BoUsers boUsers = this.boUserServicess.findByUserUserCode(userCode);
/* 15574 */         if (accessToken.longValue() < Long.valueOf(boUsers.getAccessTokenTime()).longValue()) {
/* 15575 */           if ((this.userPhone == null) || (this.userPhone.equals(""))) {
/* 15576 */             boUsers.setSignature(this.signature);
/* 15577 */             this.boUserServicess.update(boUsers);
/* 15578 */             this.requestJson.setMessage("修改成功");
/* 15579 */             this.requestJson.setSuccess(true);
/*       */           } else {
/* 15581 */             BoUsers user = this.boUserServicess.findByUserPhone(this.userPhone);
/* 15582 */             user.setSignature(this.signature);
/* 15583 */             this.boUserServicess.update(user);
/* 15584 */             this.requestJson.setMessage("修改成功");
/* 15585 */             this.requestJson.setSuccess(true);
/*       */           }
/*       */         }
/*       */         else {
/* 15589 */           this.requestJson.setMessage("超时了");
/* 15590 */           this.requestJson.setSuccess(false);
/*       */         }
/*       */       } else {
/* 15593 */         this.requestJson.setMessage("验证不通过");
/* 15594 */         this.requestJson.setSuccess(false);
/*       */       }
/*       */     }
/* 15597 */     return "success";
/*       */   }
/*       */ 
/*       */   public static String createRandomVcodess()
/*       */   {
/* 15610 */     String vcode = "";
/* 15611 */     for (int i = 0; i < 16; i++) {
/* 15612 */       vcode = vcode + (int)(Math.random() * 9.0D);
/*       */     }
/* 15614 */     return vcode;
/*       */   }
/*       */ 
/*       */   public static String createRandomVcodesss()
/*       */   {
/* 15627 */     String vcode = "";
/* 15628 */     for (int i = 0; i < 3; i++) {
/* 15629 */       vcode = vcode + (int)(Math.random() * 9.0D);
/*       */     }
/* 15631 */     return vcode;
/*       */   }
/*       */   //登录一次后进入这个方法
/*       */   @Action(value="refresh_accessToken", results={@org.apache.struts2.convention.annotation.Result(type="json", params={"root", "requestJson"})})
/*       */   public String refreshToken()
/*       */   {
/* 15644 */     this.requestJson = new RequestJson();
/*       */ 
/* 15646 */     if (StringUtils.isEmpty(this.refreshToken)) {
/* 15647 */       this.requestJson.setSuccess(false);
/* 15648 */       this.requestJson.setMessage("refreshToken不能为空");
/* 15649 */       return "success";
/*       */     }
/*       */ 
/* 15652 */     Map map = new HashMap();
/* 15653 */     HttpServletRequest request = ServletActionContext.getRequest();
/* 15654 */     String str = "";
/* 15655 */     Md5 md5 = new Md5();
/* 15656 */     String header = request.getHeader("timestamp");
/* 15657 */     String header2 = request.getHeader("nonce");
/* 15658 */     String header3 = request.getHeader("sign");
/*       */ 
/* 15660 */     str = str + "refreshToken=";
/* 15661 */     str = str + this.refreshToken;//c24ef3aebb0c49f99642e62eb029a412
/* 15662 */     System.err.println("refreshToken >-- " + this.refreshToken);
/* 15663 */     str = str + "&userCode=";
/* 15664 */     str = str + this.userCode;//c24ef3aebb0c49f99642e62eb029a412,18038035290
/* 15665 */     System.err.println("userCode >-- " + this.userCode);//c24ef3aebb0c49f99642e62eb029a412,18038035290
/* 15666 */     str = str + "12345";
/* 15667 */     System.err.println("str " + str);
/*       */ 
/* 15669 */     Long current_time = Long.valueOf(header);
/* 15670 */     String sign = md5.getMD5ofStr(str).toLowerCase();
/* 15671 */     System.err.println("sign " + sign);
/* 15672 */     System.err.println("header_sign" + header3);
/* 15673 */     System.err.println(this.userCode);
/* 15674 */     if (this.userCode.contains(",")) {
/* 15675 */       String[] split = this.userCode.split(",");//c24ef3aebb0c49f99642e62eb029a412 18038035290
/* 15676 */       BoUsers users = this.boUserServicess.findByUserPhone(split[1].trim().toString());
/* 15677 */       Long accessTokenTime = Long.valueOf(1800L);
/* 15678 */       Long refreshTokenTime = Long.valueOf(2592000L);
/* 15679 */       Long accessTokenTime_o = Long.valueOf(current_time.longValue() + accessTokenTime.longValue());
/* 15680 */       Long refreshTokenTime_o = Long.valueOf(current_time.longValue() + refreshTokenTime.longValue());
/*       */ 
/* 15682 */       if (header3.equals(sign)) {
/* 15683 */         System.err.println("sign验证通过");
/* 15684 */         System.err.println("手机 》》" + this.refreshToken);//???
/*       */ 
/* 15686 */         if (users == null) {
/* 15687 */           this.requestJson.setData(map);
/* 15688 */           this.requestJson.setMessage("Invalid_User");
/* 15689 */           this.requestJson.setSuccess(true);
/*       */         }
/* 15691 */         else if (this.refreshToken.equals(users.getRefreshToken())) {//新的refreshToken 和 从数据库取出来的一样时
/* 15692 */           System.err.println(users.getRefreshTokenTime());
//                      logger.info("新的refreshToken"+this.refreshToken);
//                      logger.info("旧的refreshToken"+users.getRefreshToken());
/* 15693 */           if (current_time.longValue() < Long.valueOf(users.getRefreshTokenTime()).longValue())
/*       */           {
/* 15695 */             String generateTokeCode = TokeUtil.generateTokeCode();
/* 15696 */             String generateTokeCodes = TokeUtil.generateTokeCodes();
/*       */ 
/* 15698 */             users.setAccessTokenTime(accessTokenTime_o.toString());
/*       */ 
/* 15700 */             users.setRefreshTokenTime(refreshTokenTime_o.toString());
/* 15701 */             BoUsers update = (BoUsers)this.boUserServicess.update(users);
/* 15702 */             map.put("accessToken", update.getAccessToken());
/* 15703 */             map.put("refreshToken", update.getRefreshToken());
/* 15704 */             this.requestJson.setData(map);
/* 15705 */             this.requestJson.setSuccess(true);
/* 15706 */             return "success";
/*       */           }
/* 15708 */           System.err.println("超时了");
/* 15709 */           this.requestJson.setMessage("超时了");
/* 15710 */           this.requestJson.setSuccess(false);
/*       */         }
/*       */         else {//新的refreshToken 和 从数据库取出来的 不同时
					//2-6  友盟推送 
				     String device_token=users.getUserDevicetoken();//新设备token
//				     logger.info("新设备的token>>>"+device_token);
				     String oldDevice_token=users.getUserAddr();//旧设备token
//				     logger.info("旧设备的token>>>"+oldDevice_token);
				     //new 2018-3-3  添加手机类型判断
				     Demo ymPush = new Demo();
				     
				     Date currentTime = new Date();
				     SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				     String dateString = formatter.format(currentTime);
					 try {
						 if(oldDevice_token !=""  || oldDevice_token != device_token) {
							 if(users.getPhoneType() == 1) {//new 2018-3-3
								 Map<String,String> map1=new HashMap<String,String>();
								 map1.put("title", "离线通知");
								 map1.put("subtitle", "");
								 map1.put("body", "另一台设备正在登录,您在"+dateString+"被迫下线");
								 ymPush.sendIOSUnicast(oldDevice_token,map,"offline");	
							 }else {
								 ymPush.sendAndroidUnicast(oldDevice_token,"离线通知","另一台设备正在登录,您在"+dateString+"被迫下线");		 
							 }
							 users.setUserAddr("");
							 BoUsers update = (BoUsers)this.boUserServicess.update(users);
							 if(update !=null) {
								 logger.info("更新操作成功执行");
							 } 
						 }
		
	                    //end
					 } catch (Exception e) {
						e.printStackTrace();
					 }
/* 15713 */           System.err.println("refreshToken令牌失效");
/* 15714 */           this.requestJson.setData(map);
/* 15715 */           this.requestJson.setMessage("refreshToken令牌失效");
/* 15716 */           this.requestJson.setSuccess(false);
/*       */         }
/*       */       }
/*       */       else
/*       */       {
/* 15721 */         System.err.println("验证不通过");
/* 15722 */         this.requestJson.setData(map);
/* 15723 */         this.requestJson.setMessage("验证不通过");
/* 15724 */         this.requestJson.setSuccess(false);
/*       */       }
/*       */     } else {
/* 15727 */       BoUsers users = this.boUserServicess.findByUserUserCode(this.userCode);
/* 15728 */       Long accessTokenTime = Long.valueOf(1800L);
/* 15729 */       Long refreshTokenTime = Long.valueOf(2592000L);
/* 15730 */       Long accessTokenTime_o = Long.valueOf(current_time.longValue() + accessTokenTime.longValue());
/* 15731 */       Long refreshTokenTime_o = Long.valueOf(current_time.longValue() + refreshTokenTime.longValue());
/*       */ 
/* 15733 */       if (header3.equals(sign)) {
/* 15734 */         System.err.println("sign验证通过");
/* 15735 */         System.err.println("手机 》》" + this.refreshToken);
/* 15736 */         System.err.println("数据库 》》" + users.getRefreshToken());
/* 15737 */         if (this.refreshToken.equals(users.getRefreshToken())) {
/* 15738 */           System.err.println(users.getRefreshTokenTime());
/* 15739 */           if (current_time.longValue() < Long.valueOf(users.getRefreshTokenTime()).longValue())
/*       */           {
/* 15741 */             String generateTokeCode = TokeUtil.generateTokeCode();
/* 15742 */             String generateTokeCodes = TokeUtil.generateTokeCodes();
/*       */ 
/* 15744 */             users.setAccessTokenTime(accessTokenTime_o.toString());
/*       */ 
/* 15746 */             users.setRefreshTokenTime(refreshTokenTime_o.toString());
/* 15747 */             BoUsers update = (BoUsers)this.boUserServicess.update(users);
/* 15748 */             map.put("accessToken", update.getAccessToken());
/* 15749 */             map.put("refreshToken", update.getRefreshToken());
/* 15750 */             this.requestJson.setData(map);
/* 15751 */             this.requestJson.setSuccess(true);
/* 15752 */             return "success";
/*       */           }
/* 15754 */           System.err.println("超时了");
/* 15755 */           this.requestJson.setMessage("超时了");
/* 15756 */           this.requestJson.setSuccess(false);
/*       */         }
/*       */         else {
/* 15759 */           System.err.println("refreshToken令牌失效");
/* 15760 */           this.requestJson.setMessage("refreshToken令牌失效");
/* 15761 */           this.requestJson.setSuccess(false);
/*       */         }
/*       */       }
/*       */       else {
/* 15765 */         System.err.println("验证不通过");
/* 15766 */         this.requestJson.setMessage("验证不通过");
/* 15767 */         this.requestJson.setSuccess(false);
/*       */       }
/*       */ 
/*       */     }
/*       */ 
/* 15773 */     return "success";
/*       */   }
/*       */ 
/*       */   @Action(value="login", results={@org.apache.struts2.convention.annotation.Result(type="json", params={"root", "requestJson"})})
/*       */   public String login()
/*       */   {
/* 15785 */     this.requestJson = new RequestJson();
/*       */ 
/* 15824 */     Map map = new HashMap();
				//2-6  两台设备登录账号   登录时会根据下面两个参数做出判断，当不一样时会强制前面的设备退出
/* 15825 */     String generateTokeCode = TokeUtil.generateTokeCode();
                logger.info("AccessToken>>>"+generateTokeCode);
/* 15826 */     String generateTokeCodes = TokeUtil.generateTokeCodes();
				logger.info("RefreshToken>>>"+generateTokeCodes);
                //END
/*       */ 
/* 15828 */     BoUsersValidation findByUserPhone = this.boUsersValidationServicess.findByUserPhone(this.userPhone);
/* 15829 */     if (findByUserPhone != null) {
/* 15830 */       if (this.verifyCode.equals(findByUserPhone.getVerificationCode())) {
/* 15831 */         BoUsers phone2 = this.boUserServicess.findByUserPhone(this.userPhone);
/* 15832 */         Long accessToken = Long.valueOf((int)(System.currentTimeMillis() / 1000L));
/*       */ 
/* 15834 */         Long accessTokenTime = Long.valueOf(1800L);
/* 15835 */         Long refreshTokenTime = Long.valueOf(2592000L);
/* 15836 */         Long fluoriteAccessTokenTime = Long.valueOf(518400L);
/* 15837 */         Long accessTokenTime_o = Long.valueOf(accessToken.longValue() + accessTokenTime.longValue());
/* 15838 */         Long refreshTokenTime_o = Long.valueOf(accessToken.longValue() + refreshTokenTime.longValue());
/*       */ 
/* 15840 */         Long fluoriteAccessTokenTime_o = Long.valueOf((int)(System.currentTimeMillis() / 1000L) + 
/* 15841 */           fluoriteAccessTokenTime.longValue());
/* 15842 */         if (phone2 != null) {
/* 15843 */           if (phone2.getLogoAccountType().equals("M")) {
/* 15844 */             phone2.setAccessToken(generateTokeCode);//2-6
/* 15845 */             phone2.setRefreshToken(generateTokeCodes);//2-6
/* 15846 */             phone2.setAccessTokenTime(accessTokenTime_o+"");
/* 15847 */             phone2.setRefreshTokenTime(refreshTokenTime_o+"");
/* 15848 */             phone2.setCid(this.CID);
/*       */             String s;
///*       */             String s;
/* 15850 */             if (this.phoneType == null)
/* 15851 */               s = "1";
/*       */             else {
/* 15853 */               s = this.phoneType;
/*       */             }
/* 15855 */             phone2.setPhoneType(Integer.valueOf(s));
/* 15856 */             phone2.setVersionType(this.versionType);
/* 15857 */             BoUsers update = (BoUsers)this.boUserServicess.update(phone2);
/* 15858 */             map.put("accessToken", update.getAccessToken());
/* 15859 */             map.put("refreshToken", update.getRefreshToken());
/* 15860 */             map.put("userCode", phone2.getUserCode() + "," + phone2.getUserPhone());
/*       */ 
/* 15862 */             map.put("logoAccountType", phone2.getLogoAccountType());
/* 15863 */             map.put("userPhone", phone2.getUserPhone());
/* 15864 */             map.put("accountOperationType", phone2.getAccountOperationType());
/* 15865 */             map.put("isFirst", phone2.getIsFirst());
/* 15866 */             map.put("whetherSetPwd", phone2.getWhetherSetPwd());
/* 15867 */             String fluoriteAccessToken = phone2.getFluoriteAccessToken();
/*       */             String EZTOKEN;
///*       */             String EZTOKEN;
/* 15869 */             if (fluoriteAccessToken.equals(""))
/* 15870 */               EZTOKEN = "NO_BUNDING";
/*       */             else {
/* 15872 */               EZTOKEN = fluoriteAccessToken;
/*       */             }
/* 15874 */             map.put("Eztoken", EZTOKEN);
/* 15875 */             map.put("ez_token", EZTOKEN);
/* 15876 */             String city2 = phone2.getCity();
/*       */             String city3;
///*       */             String city3;
/* 15878 */             if (city2.equals("")) {
/* 15879 */               city3 = "杭州市";
/*       */             } else {
/* 15881 */               String[] split = city2.split(",");
/* 15882 */               city3 = split[1];
/*       */             }
/* 15884 */             map.put("city", city3);
/*       */           } else {
/* 15886 */             BoUsers boUsers = this.boUserServicess.findByUserUserCode(phone2.getAuthorizationUserCode());
/* 15887 */             if (boUsers != null) {
/* 15888 */               phone2.setAccessToken(generateTokeCode);
/* 15889 */               phone2.setRefreshToken(generateTokeCodes);
/* 15890 */               phone2.setAccessTokenTime(accessTokenTime_o+"");
/* 15891 */               phone2.setRefreshTokenTime(refreshTokenTime_o+"");
/*       */ 
/* 15893 */               phone2.setCid(this.CID);
/*       */               String s;
///*       */               String s;
/* 15895 */               if (this.phoneType == null)
/* 15896 */                 s = "1";
/*       */               else {
/* 15898 */                 s = this.phoneType;
/*       */               }
/* 15900 */               phone2.setPhoneType(Integer.valueOf(s));
/* 15901 */               phone2.setVersionType(this.versionType);
/* 15902 */               this.boUserServicess.update(phone2);
/* 15903 */               map.put("accessToken", phone2.getAccessToken());//2-6
/*       */ 
/* 15905 */               map.put("refreshToken", phone2.getRefreshToken());//2-6
/*       */ 
/* 15907 */               map.put("userCode", boUsers.getUserCode() + "," + phone2.getUserPhone());
/* 15908 */               map.put("userPhone", phone2.getUserPhone());
/* 15909 */               map.put("accountOperationType", phone2.getAccountOperationType());
/* 15910 */               map.put("logoAccountType", phone2.getLogoAccountType());
/* 15911 */               map.put("isFirst", boUsers.getIsFirst());
/* 15912 */               map.put("whetherSetPwd", phone2.getWhetherSetPwd());
/* 15913 */               String fluoriteAccessToken = boUsers.getFluoriteAccessToken();
/*       */               String EZTOKEN;
///*       */               String EZTOKEN;
/* 15915 */               if (fluoriteAccessToken.equals(""))
/* 15916 */                 EZTOKEN = "NO_BUNDING";
/*       */               else {
/* 15918 */                 EZTOKEN = fluoriteAccessToken;
/*       */               }
/* 15920 */               map.put("Eztoken", EZTOKEN);
/* 15921 */               map.put("ez_token", EZTOKEN);
/* 15922 */               String city2 = phone2.getCity();
/*       */               String city3;
///*       */               String city3;
/* 15924 */               if (city2.equals("")) {
/* 15925 */                 city3 = "杭州市";
/*       */               } else {
/* 15927 */                 String[] split = city2.split(",");
/* 15928 */                 city3 = split[1];
/*       */               }
/* 15930 */               map.put("city", city3);
/*       */             }
/*       */ 
/*       */           }
/*       */ 
/* 15935 */           this.requestJson.setData(map);
/* 15936 */           this.requestJson.setSuccess(true);
/*       */         }
/*       */         else {
/* 15939 */           BoUsers user = new BoUsers();
/* 15940 */           user.setUserPhone(this.userPhone);
/* 15941 */           user.setUserCode(UuidUtil.get32UUID());
/* 15942 */           user.setAccessToken(generateTokeCode);
/* 15943 */           user.setRefreshToken(generateTokeCodes);
/* 15944 */           user.setAccessTokenTime(accessTokenTime_o+"");
/* 15945 */           user.setRefreshTokenTime(refreshTokenTime_o+"");
/* 15946 */           user.setFluoriteAccessToken("");
/* 15947 */           user.setFluoriteAccessTokenCreateTime("");
/* 15948 */           user.setFluoriteAccessTokenExpireTime("");
/* 15949 */           user.setCid(this.CID);
/* 15950 */           user.setPhoneType(Integer.valueOf(1));
/* 15951 */           user.setFluoriteAccessTokenExpireTime("");
/* 15952 */           user.setUserAddr("");
/* 15953 */           user.setVersionType(this.versionType);
/* 15954 */           user.setHeadPic("");
/* 15955 */           user.setUserName("");
/* 15956 */           user.setSecurityTotalSwitch("0");
/* 15957 */           user.setVersion(Integer.valueOf(0));
/* 15958 */           user.setUserSex("");
/* 15959 */           user.setUserAge("");
/* 15960 */           user.setCity("");
/* 15961 */           user.setAuthorizationUserCode("");
/* 15962 */           user.setLogoAccountType("M");
/* 15963 */           user.setAccountOperationType("1");
/* 15964 */           user.setSignature("");
/* 15965 */           user.setIsFirst(this.fid);
/* 15966 */           user.setWhetherSetPwd(this.fid);
/* 15967 */           BoUsers save = (BoUsers)this.boUserServicess.save(user);
/* 15968 */           String city2 = save.getCity();
/*       */           String city3;
///*       */           String city3;
/* 15970 */           if (city2.equals("")) {
/* 15971 */             city3 = "杭州市";
/*       */           } else {
/* 15973 */             String[] split = city2.split(",");
/* 15974 */             city3 = split[1];
/*       */           }
/* 15976 */           map.put("whetherSetPwd", save.getWhetherSetPwd());
/* 15977 */           map.put("accessToken", save.getAccessToken());//2-6
/* 15978 */           map.put("refreshToken", save.getRefreshToken());//2-6
/* 15979 */           if (save.getLogoAccountType().equals("M"))
/* 15980 */             map.put("userCode", save.getUserCode() + "," + save.getUserPhone());
/*       */           else {
/* 15982 */             map.put("userCode", save.getAuthorizationUserCode() + "," + save.getUserPhone());
/*       */           }
/* 15984 */           map.put("userPhone", save.getUserPhone());
/* 15985 */           map.put("logoAccountType", save.getLogoAccountType());
/* 15986 */           map.put("accountOperationType", save.getAccountOperationType());
/* 15987 */           map.put("city", city3);
/* 15988 */           map.put("isFirst", save.getIsFirst());
/* 15989 */           String fluoriteAccessToken = save.getFluoriteAccessToken();
/*       */           String EZTOKEN;
///*       */           String EZTOKEN;
/* 15991 */           if (fluoriteAccessToken.equals(""))
/* 15992 */             EZTOKEN = "NO_BUNDING";
/*       */           else {
/* 15994 */             EZTOKEN = fluoriteAccessToken;
/*       */           }
/* 15996 */           map.put("Eztoken", EZTOKEN);
/* 15997 */           map.put("ez_token", EZTOKEN);
/* 15998 */           this.requestJson.setData(map);
/* 15999 */           this.requestJson.setSuccess(true);
/*       */ 
/* 16001 */           String root = System.getProperty("webapp.root");
/* 16002 */           String tempDir = root + "/" + "photo_library" + "/" + this.userPhone;
/* 16003 */           File f = new File(tempDir);
/* 16004 */           System.err.println(f.exists());
/*       */ 
/* 16006 */           if (!f.exists()) {
/* 16007 */             f.mkdirs();
/*       */           }
/*       */         }
/*       */       }
/*       */       else
/*       */       {
/* 16013 */         this.requestJson.setMessage("验证码错误");
/* 16014 */         this.requestJson.setSuccess(false);
/*       */       }
/*       */     } else {
/* 16017 */       String[] array = (String[])StaticUtils.VERIFICATIONCODE.get(this.userPhone + "_A");
/* 16018 */       String verificationCode = "";
/* 16019 */       String verificationCodes = "";
/*       */ 
/* 16021 */       if (array != null) {
/* 16022 */         verificationCode = array[0];
/*       */       }
/*       */ 
/* 16025 */       if (verificationCode.equals(""))
/* 16026 */         verificationCodes = "***^&*&*&^&*&^$$^&*()&";
/*       */       else {
/* 16028 */         verificationCodes = verificationCode;
/*       */       }
/* 16030 */       System.err.println("----" + verificationCode);
/* 16031 */       System.err.println("====" + this.verifyCode);
/* 16032 */       if (verificationCodes.equals(this.verifyCode)) {
/* 16033 */         BoUsers phone = this.boUserServicess.findByUserPhone(this.userPhone);
/*       */ 
/* 16036 */         Long accessToken = Long.valueOf((int)(System.currentTimeMillis() / 1000L));
/*       */ 
/* 16038 */         Long accessTokenTime = Long.valueOf(1800L);
/* 16039 */         Long refreshTokenTime = Long.valueOf(2592000L);
/* 16040 */         Long fluoriteAccessTokenTime = Long.valueOf(518400L);
/* 16041 */         Long accessTokenTime_o = Long.valueOf(accessToken.longValue() + accessTokenTime.longValue());
/* 16042 */         Long refreshTokenTime_o = Long.valueOf(accessToken.longValue() + refreshTokenTime.longValue());
/*       */ 
/* 16044 */         Long fluoriteAccessTokenTime_o = Long.valueOf((int)(System.currentTimeMillis() / 1000L) + 
/* 16045 */           fluoriteAccessTokenTime.longValue());
/* 16046 */         if (phone != null) {
/* 16047 */           if (phone.getLogoAccountType().equals("M")) {
/* 16048 */             phone.setAccessToken(generateTokeCode);
/* 16049 */             phone.setRefreshToken(generateTokeCodes);
/* 16050 */             phone.setAccessTokenTime(accessTokenTime_o+"");
/* 16051 */             phone.setRefreshTokenTime(refreshTokenTime_o+"");
/* 16052 */             phone.setCid(this.CID);
/*       */             String s;
///*       */             String s;
/* 16054 */             if (this.phoneType == null)
/* 16055 */               s = "1";
/*       */             else {
/* 16057 */               s = this.phoneType;
/*       */             }
/* 16059 */             phone.setPhoneType(Integer.valueOf(s));
/* 16060 */             phone.setVersionType(this.versionType);
/* 16061 */             BoUsers update = (BoUsers)this.boUserServicess.update(phone);
/* 16062 */             map.put("accessToken", update.getAccessToken());
/* 16063 */             map.put("refreshToken", update.getRefreshToken());
/* 16064 */             if (phone.getLogoAccountType().equals("M"))
/* 16065 */               map.put("userCode", phone.getUserCode() + "," + phone.getUserPhone());
/*       */             else {
/* 16067 */               map.put("userCode", phone.getAuthorizationUserCode());
/*       */             }
/* 16069 */             map.put("logoAccountType", phone.getLogoAccountType());
/* 16070 */             map.put("userPhone", phone.getUserPhone());
/* 16071 */             map.put("accountOperationType", phone.getAccountOperationType());
/* 16072 */             map.put("isFirst", phone.getIsFirst());
/* 16073 */             map.put("whetherSetPwd", phone.getWhetherSetPwd());
/* 16074 */             String fluoriteAccessToken = phone.getFluoriteAccessToken();
/*       */             String EZTOKEN;
///*       */             String EZTOKEN;
/* 16076 */             if (fluoriteAccessToken.equals(""))
/* 16077 */               EZTOKEN = "NO_BUNDING";
/*       */             else {
/* 16079 */               EZTOKEN = fluoriteAccessToken;
/*       */             }
/* 16081 */             map.put("Eztoken", EZTOKEN);
/* 16082 */             map.put("ez_token", EZTOKEN);
/* 16083 */             String city2 = phone.getCity();
/*       */             String city3;
///*       */             String city3;
/* 16085 */             if (city2.equals("")) {
/* 16086 */               city3 = "杭州市";
/*       */             } else {
/* 16088 */               String[] split = city2.split(",");
/* 16089 */               city3 = split[1];
/*       */             }
/* 16091 */             map.put("city", city3);
/*       */           } else {
/* 16093 */             BoUsers boUsers = this.boUserServicess.findByUserUserCode(phone.getAuthorizationUserCode());
/* 16094 */             if (boUsers != null) {
/* 16095 */               phone.setAccessToken(generateTokeCode);
/* 16096 */               phone.setRefreshToken(generateTokeCodes);
/* 16097 */               phone.setAccessTokenTime(accessTokenTime_o+"");
/* 16098 */               phone.setRefreshTokenTime(refreshTokenTime_o+"");
/* 16099 */               phone.setCid(this.CID);
/*       */               String s;
///*       */               String s;
/* 16101 */               if (this.phoneType == null)
/* 16102 */                 s = "1";
/*       */               else {
/* 16104 */                 s = this.phoneType;
/*       */               }
/* 16106 */               phone.setPhoneType(Integer.valueOf(s));
/* 16107 */               phone.setVersionType(this.versionType);
/* 16108 */               this.boUserServicess.update(phone);
/* 16109 */               map.put("accessToken", phone.getAccessToken());
/* 16110 */               map.put("refreshToken", phone.getRefreshToken());
/* 16111 */               map.put("userCode", boUsers.getUserCode() + "," + phone.getUserPhone());
/* 16112 */               map.put("userPhone", phone.getUserPhone());
/* 16113 */               map.put("logoAccountType", phone.getLogoAccountType());
/* 16114 */               map.put("accountOperationType", phone.getAccountOperationType());
/* 16115 */               map.put("isFirst", boUsers.getIsFirst());
/* 16116 */               map.put("whetherSetPwd", phone.getWhetherSetPwd());
/* 16117 */               String fluoriteAccessToken = boUsers.getFluoriteAccessToken();
/*       */               String EZTOKEN;
///*       */               String EZTOKEN;
/* 16119 */               if (fluoriteAccessToken.equals(""))
/* 16120 */                 EZTOKEN = "NO_BUNDING";
/*       */               else {
/* 16122 */                 EZTOKEN = fluoriteAccessToken;
/*       */               }
/* 16124 */               map.put("Eztoken", EZTOKEN);
/* 16125 */               map.put("ez_token", EZTOKEN);
/* 16126 */               String city2 = phone.getCity();
/*       */               String city3;
///*       */               String city3;
/* 16128 */               if (city2.equals("")) {
/* 16129 */                 city3 = "杭州市";
/*       */               } else {
/* 16131 */                 String[] split = city2.split(",");
/* 16132 */                 city3 = split[1];
/*       */               }
/* 16134 */               map.put("city", city3);
/*       */             }
/*       */ 
/*       */           }
/*       */ 
/* 16139 */           this.requestJson.setData(map);
/* 16140 */           this.requestJson.setSuccess(true);
/*       */         }
/*       */         else {
/* 16143 */           BoUsers user = new BoUsers();
/* 16144 */           user.setUserPhone(this.userPhone);
/* 16145 */           user.setUserCode(UuidUtil.get32UUID());
/* 16146 */           user.setAccessToken(generateTokeCode);
/* 16147 */           user.setRefreshToken(generateTokeCodes);
/* 16148 */           user.setAccessTokenTime(accessTokenTime_o+"");
/* 16149 */           user.setRefreshTokenTime(refreshTokenTime_o+"");
/* 16150 */           user.setFluoriteAccessToken("");
/* 16151 */           user.setFluoriteAccessTokenCreateTime("");
/* 16152 */           user.setFluoriteAccessTokenExpireTime("");
/* 16153 */           user.setCid(this.CID);
/* 16154 */           user.setPhoneType(Integer.valueOf(1));
/* 16155 */           user.setFluoriteAccessTokenExpireTime("");
/* 16156 */           user.setUserAddr("");
/* 16157 */           user.setVersionType(this.versionType);
/* 16158 */           user.setHeadPic("");
/* 16159 */           user.setUserName("");
/* 16160 */           user.setVersion(Integer.valueOf(0));
/* 16161 */           user.setUserSex("");
/* 16162 */           user.setSecurityTotalSwitch("0");
/* 16163 */           user.setUserAge("");
/* 16164 */           user.setCity("");
/* 16165 */           user.setAuthorizationUserCode("");
/* 16166 */           user.setLogoAccountType("M");
/* 16167 */           user.setAccountOperationType("1");
/* 16168 */           user.setSignature("");
/* 16169 */           user.setIsFirst(this.fid);
/* 16170 */           user.setWhetherSetPwd(this.fid);
/* 16171 */           BoUsers save = (BoUsers)this.boUserServicess.save(user);
/* 16172 */           String city2 = save.getCity();
/*       */           String city3;
///*       */           String city3;
/* 16174 */           if (city2.equals("")) {
/* 16175 */             city3 = "杭州市";
/*       */           } else {
/* 16177 */             String[] split = city2.split(",");
/* 16178 */             city3 = split[1];
/*       */           }
/* 16180 */           map.put("whetherSetPwd", save.getWhetherSetPwd());
/* 16181 */           map.put("accessToken", save.getAccessToken());
/* 16182 */           map.put("refreshToken", save.getRefreshToken());
/* 16183 */           if (save.getLogoAccountType().equals("M"))
/* 16184 */             map.put("userCode", save.getUserCode() + "," + save.getUserPhone());
/*       */           else {
/* 16186 */             map.put("userCode", save.getAuthorizationUserCode() + "," + save.getUserPhone());
/*       */           }
/* 16188 */           map.put("userPhone", save.getUserPhone());
/* 16189 */           map.put("logoAccountType", save.getLogoAccountType());
/* 16190 */           map.put("accountOperationType", save.getAccountOperationType());
/* 16191 */           map.put("city", city3);
/* 16192 */           map.put("isFirst", save.getIsFirst());
/* 16193 */           String fluoriteAccessToken = save.getFluoriteAccessToken();
/*       */           String EZTOKEN;
///*       */           String EZTOKEN;
/* 16195 */           if (fluoriteAccessToken.equals(""))
/* 16196 */             EZTOKEN = "NO_BUNDING";
/*       */           else {
/* 16198 */             EZTOKEN = fluoriteAccessToken;
/*       */           }
/* 16200 */           map.put("Eztoken", EZTOKEN);
/* 16201 */           map.put("ez_token", "NO_BUNDING");
/*       */ 
/* 16203 */           this.requestJson.setData(map);
/* 16204 */           this.requestJson.setSuccess(true);
/*       */ 
/* 16206 */           String root = System.getProperty("webapp.root");
/* 16207 */           String tempDir = root + "/" + "photo_library" + "/" + this.userPhone;
/* 16208 */           File f = new File(tempDir);
/* 16209 */           System.err.println(f.exists());
/*       */ 
/* 16211 */           if (!f.exists())
/* 16212 */             f.mkdirs();
/*       */         }
/*       */       }
/*       */       else
/*       */       {
/* 16217 */         this.requestJson.setMessage("验证码错误");
/* 16218 */         this.requestJson.setSuccess(false);
/*       */       }
/*       */     }
/*       */ 
/* 16222 */     return "success";
/*       */   }
/*       */ 
/*       */   @Action(value="send", results={@org.apache.struts2.convention.annotation.Result(type="json", params={"root", "requestJson"})})
/*       */   public String sendMsgs()
/*       */     throws UnsupportedEncodingException
/*       */   {
/* 16234 */     this.requestJson = new RequestJson();
/* 16235 */     if (StringUtils.isEmpty(this.userPhone)) {
/* 16236 */       this.requestJson.setSuccess(false);
/* 16237 */       this.requestJson.setMessage("手机号不能为空！");
/* 16238 */       return "success";
/*       */     }
/* 16240 */     String createRandomVcode = this.s.createRandomVcode();
/* 16241 */     String sendMsg = null;
/* 16242 */     Map map = new HashMap();
//                logger.info("···versionType····"+this.versionType);
/* 16243 */     if (this.versionType.equals("1")) {
/* 16244 */       sendMsg = SendMsgUtil.sendMsg(this.userPhone, "尊敬的用户，您的验证码为 :  " + createRandomVcode);
///* 16245 */       System.err.println(sendMsg);
//				  logger.info("sendMsg>>"+sendMsg);
/* 16246 */       String[] split = sendMsg.split(",");
/* 16247 */       if (split[1].equals("0"))
/*       */       {
/* 16249 */         map.put("sendMsg", sendMsg);
/* 16250 */         StaticUtils.VERIFICATIONCODE.put(this.userPhone + "_" + "A", new String[] { 
/* 16251 */           createRandomVcode, new Date().getTime()+"" });
/* 16252 */         this.requestJson.setData(map);
/* 16253 */         this.requestJson.setMessage("发送成功");
/* 16254 */         this.requestJson.setSuccess(true);
/* 16255 */       } else if (split[1].equals("101")) {
/* 16256 */         this.requestJson.setData(map);
/* 16257 */         this.requestJson.setMessage("无此用户");
/* 16258 */         this.requestJson.setSuccess(false);
/* 16259 */       } else if (split[1].equals("103")) {
/* 16260 */         this.requestJson.setData(map);
/* 16261 */         this.requestJson.setMessage("提交过快");
/* 16262 */         this.requestJson.setSuccess(false);
/* 16263 */       } else if (split[1].equals("108")) {
/* 16264 */         this.requestJson.setData(map);
/* 16265 */         this.requestJson.setMessage("手机号码格式错误");
/* 16266 */         this.requestJson.setSuccess(false);
/* 16267 */       } else if (split[1].equals("109")) {
/* 16268 */         this.requestJson.setData(map);
/* 16269 */         this.requestJson.setMessage("该号码发送数量已使用完");
/* 16270 */         this.requestJson.setSuccess(false);
/*       */       }
/* 16272 */     } else if (this.versionType.equals("2")) {
/* 16273 */       sendMsg = SendMsgUtil.aiBoRuisendMsg(this.userPhone, "【爱波瑞科技】尊敬的用户，您的验证码为 :  " + createRandomVcode);
/* 16274 */       map.put("sendMsg", sendMsg);
/* 16275 */       StaticUtils.VERIFICATIONCODE.put(this.userPhone + "_" + "A", new String[] { 
/* 16276 */         createRandomVcode, new Date().getTime()+"" });
/* 16277 */       this.requestJson.setData(map);
/* 16278 */       this.requestJson.setMessage("发送成功");
/* 16279 */       this.requestJson.setSuccess(true);
/* 16280 */     } else if (!this.versionType.equals("3"))
/*       */     {
/* 16282 */       if (this.versionType.equals("4")) {
/* 16283 */         sendMsg = SendMsgUtil.siChuangSendMsg(this.userPhone, "【思创智能】尊敬的用户，您的验证码为 : " + createRandomVcode);
/* 16284 */         System.err.println(sendMsg);
/* 16285 */         map.put("sendMsg", sendMsg);
/* 16286 */         StaticUtils.VERIFICATIONCODE.put(this.userPhone + "_" + "A", new String[] { 
/* 16287 */           createRandomVcode, new Date().getTime()+"" });
/* 16288 */         this.requestJson.setData(map);
/* 16289 */         this.requestJson.setMessage("发送成功");
/* 16290 */       } else if (this.versionType.equals("5")) {
/* 16291 */         sendMsg = SendMsgUtil.fengTingSendMsg(this.userPhone, "【峰庭智能】尊敬的用户，您的验证码为 : " + createRandomVcode);
/* 16292 */         System.err.println(sendMsg);
/* 16293 */         map.put("sendMsg", sendMsg);
/* 16294 */         StaticUtils.VERIFICATIONCODE.put(this.userPhone + "_" + "A", new String[] { 
/* 16295 */           createRandomVcode, new Date().getTime()+"" });
/* 16296 */         this.requestJson.setData(map);
/* 16297 */         this.requestJson.setMessage("发送成功");
/*       */       }
/*       */     }
/* 16299 */     String userPhones = this.userPhone;
/* 16300 */     System.out.println(userPhones);
/* 16301 */     System.err.println(createRandomVcode);
/* 16302 */     return "success";
/*       */   }
/*       */ 
/*       */   @Action(value="test", results={@org.apache.struts2.convention.annotation.Result(type="json", params={"root", "requestJson"})})
/*       */   public String test()
/*       */   {
/*       */     try
/*       */     {
/* 16325 */       String root = System.getProperty("webapp.root");
/* 16326 */       String tempDir = root + "/" + "apk";
/* 16327 */       ApkInfo apkInfo = GetApkInfo.getApkInfoByFilePath(tempDir + "\\smartHome.apk");
/*       */ 
/* 16329 */       System.out.println(apkInfo.getVersionName());
/* 16330 */       this.requestJson.setMessage(tempDir);
/*       */     } catch (IOException e) {
/* 16332 */       e.printStackTrace();
/*       */     }
/*       */ 
/* 16337 */     return "success";
/*       */   }
/*       */ 
/*       */   public String getOldUserPwd()
/*       */   {
/* 16547 */     return this.oldUserPwd;
/*       */   }
/*       */ 
/*       */   public void setOldUserPwd(String oldUserPwd) {
/* 16551 */     this.oldUserPwd = oldUserPwd;
/*       */   }
/*       */ 
/*       */   public String getPushContent() {
/* 16555 */     return this.pushContent;
/*       */   }
/*       */   public void setPushContent(String pushContent) {
/* 16558 */     this.pushContent = pushContent;
/*       */   }
/*       */   public String getAccountOperationType() {
/* 16561 */     return this.accountOperationType;
/*       */   }
/*       */ 
/*       */   public void setAccountOperationType(String accountOperationType) {
/* 16565 */     this.accountOperationType = accountOperationType;
/*       */   }
/*       */ 
/*       */   public String getVersionType() {
/* 16569 */     return this.versionType;
/*       */   }
/*       */ 
/*       */   public void setVersionType(String versionType) {
/* 16573 */     this.versionType = versionType;
/*       */   }
/*       */ 
/*       */   public String getCommandType() {
/* 16577 */     return this.commandType;
/*       */   }
/*       */   public void setCommandType(String commandType) {
/* 16580 */     this.commandType = commandType;
/*       */   }
/*       */ 
/*       */   public String getAppVersion() {
/* 16584 */     return this.appVersion;
/*       */   }
/*       */ 
/*       */   public void setAppVersion(String appVersion) {
/* 16588 */     this.appVersion = appVersion;
/*       */   }
/*       */ 
/*       */   public String getProvider() {
/* 16592 */     return this.provider;
/*       */   }
/*       */ 
/*       */   public void setProvider(String provider) {
/* 16596 */     this.provider = provider;
/*       */   }
/*       */ 
/*       */   public String getControlAction() {
/* 16600 */     return this.controlAction;
/*       */   }
/*       */ 
/*       */   public void setControlAction(String controlAction) {
/* 16604 */     this.controlAction = controlAction;
/*       */   }
/*       */ 
/*       */   public String getPhoneType() {
/* 16608 */     return this.phoneType;
/*       */   }
/*       */ 
/*       */   public void setPhoneType(String phoneType)
/*       */   {
/* 16613 */     this.phoneType = phoneType;
/*       */   }
/*       */ 
/*       */   public String getPatternType()
/*       */   {
/* 16619 */     return this.patternType;
/*       */   }
/*       */ 
/*       */   public void setPatternType(String patternType) {
/* 16623 */     this.patternType = patternType;
/*       */   }
/*       */ 
/*       */   public String getModelTime() {
/* 16627 */     return this.modelTime;
/*       */   }
/*       */ 
/*       */   public void setModelTime(String modelTime) {
/* 16631 */     this.modelTime = modelTime;
/*       */   }
/*       */ 
/*       */   public String getModelWeek() {
/* 16635 */     return this.modelWeek;
/*       */   }
/*       */ 
/*       */   public void setModelWeek(String modelWeek) {
/* 16639 */     this.modelWeek = modelWeek;
/*       */   }
/*       */ 
/*       */   public String getChannel() {
/* 16643 */     return this.channel;
/*       */   }
/*       */ 
/*       */   public void setChannel(String channel) {
/* 16647 */     this.channel = channel;
/*       */   }
/*       */ 
/*       */   public String getNetworkNumber() {
/* 16651 */     return this.networkNumber;
/*       */   }
/*       */ 
/*       */   public void setNetworkNumber(String networkNumber) {
/* 16655 */     this.networkNumber = networkNumber;
/*       */   }
/*       */ 
/*       */   public String getDeviceOrHost()
/*       */   {
/* 16663 */     return this.deviceOrHost;
/*       */   }
/*       */ 
/*       */   public void setDeviceOrHost(String deviceOrHost) {
/* 16667 */     this.deviceOrHost = deviceOrHost;
/*       */   }
/*       */ 
/*       */   public String getOriginalValue()
/*       */   {
/* 16675 */     return this.originalValue;
/*       */   }
/*       */ 
/*       */   public void setOriginalValue(String originalValue)
/*       */   {
/* 16683 */     this.originalValue = originalValue;
/*       */   }
/*       */ 
/*       */   public String getClassesInfo()
/*       */   {
/* 16691 */     return this.classesInfo;
/*       */   }
/*       */ 
/*       */   public void setClassesInfo(String classesInfo)
/*       */   {
/* 16696 */     this.classesInfo = classesInfo;
/*       */   }
/*       */ 
/*       */   public String getInfraredButtonsInfo()
/*       */   {
/* 16701 */     return this.infraredButtonsInfo;
/*       */   }
/*       */ 
/*       */   public void setInfraredButtonsInfo(String infraredButtonsInfo)
/*       */   {
/* 16706 */     this.infraredButtonsInfo = infraredButtonsInfo;
/*       */   }
/*       */ 
/*       */   public String getSensorName()
/*       */   {
/* 16711 */     return this.sensorName;
/*       */   }
/*       */ 
/*       */   public void setSensorName(String sensorName)
/*       */   {
/* 16716 */     this.sensorName = sensorName;
/*       */   }
/*       */ 
/*       */   public String getSecurity()
/*       */   {
/* 16721 */     return this.security;
/*       */   }
/*       */ 
/*       */   public void setSecurity(String security)
/*       */   {
/* 16726 */     this.security = security;
/*       */   }
/*       */ 
/*       */   public String getHowMany()
/*       */   {
/* 16731 */     return this.howMany;
/*       */   }
/*       */ 
/*       */   public void setHowMany(String howMany)
/*       */   {
/* 16736 */     this.howMany = howMany;
/*       */   }
/*       */ 
/*       */   public String getSecurityType()
/*       */   {
/* 16741 */     return this.securityType;
/*       */   }
/*       */ 
/*       */   public void setSecurityType(String securityType)
/*       */   {
/* 16746 */     this.securityType = securityType;
/*       */   }
/*       */ 
/*       */   public String getTemplate()
/*       */   {
/* 16751 */     return this.template;
/*       */   }
/*       */ 
/*       */   public void setTemplate(String template)
/*       */   {
/* 16756 */     this.template = template;
/*       */   }
/*       */ 
/*       */   public Integer getControlCommand()
/*       */   {
/* 16761 */     return this.controlCommand;
/*       */   }
/*       */ 
/*       */   public void setControlCommand(Integer controlCommand) {
/* 16765 */     this.controlCommand = controlCommand;
/*       */   }
/*       */ 
/*       */   public String getModelInfo() {
/* 16769 */     return this.modelInfo;
/*       */   }
/*       */ 
/*       */   public void setModelInfo(String modelInfo) {
/* 16773 */     this.modelInfo = modelInfo;
/*       */   }
/*       */ 
/*       */   public String getStartTime()
/*       */   {
/* 16779 */     return this.startTime;
/*       */   }
/*       */ 
/*       */   public void setStartTime(String startTime) {
/* 16783 */     this.startTime = startTime;
/*       */   }
/*       */ 
/*       */   public String getModelName() {
/* 16787 */     return this.modelName;
/*       */   }
/*       */ 
/*       */   public String getEndTime()
/*       */   {
/* 16792 */     return this.endTime;
/*       */   }
/*       */ 
/*       */   public void setEndTime(String endTime) {
/* 16796 */     this.endTime = endTime;
/*       */   }
/*       */ 
/*       */   public String getSensorInfo()
/*       */   {
/* 16802 */     return this.sensorInfo;
/*       */   }
/*       */ 
/*       */   public void setSensorInfo(String sensorInfo)
/*       */   {
/* 16807 */     this.sensorInfo = sensorInfo;
/*       */   }
/*       */ 
/*       */   public void setModelName(String modelName)
/*       */   {
/* 16812 */     this.modelName = modelName;
/*       */   }
/*       */ 
/*       */   public String getModelId() {
/* 16816 */     return this.modelId;
/*       */   }
/*       */ 
/*       */   public void setModelId(String modelId) {
/* 16820 */     this.modelId = modelId;
/*       */   }
/*       */ 
/*       */   public String getInfraredButtonsValuess() {
/* 16824 */     return this.infraredButtonsValuess;
/*       */   }
/*       */ 
/*       */   public void setInfraredButtonsValuess(String infraredButtonsValuess) {
/* 16828 */     this.infraredButtonsValuess = infraredButtonsValuess;
/*       */   }
/*       */ 
/*       */   public String getInfraredButtonsName() {
/* 16832 */     return this.infraredButtonsName;
/*       */   }
/*       */ 
/*       */   public void setInfraredButtonsName(String infraredButtonsName) {
/* 16836 */     this.infraredButtonsName = infraredButtonsName;
/*       */   }
/*       */ 
/*       */   public String getValidationCode() {
/* 16840 */     return this.validationCode;
/*       */   }
/*       */ 
/*       */   public void setValidationCode(String validationCode) {
/* 16844 */     this.validationCode = validationCode;
/*       */   }
/*       */ 
/*       */   public Integer getIsStudy() {
/* 16848 */     return this.isStudy;
/*       */   }
/*       */ 
/*       */   public void setIsStudy(Integer isStudy) {
/* 16852 */     this.isStudy = isStudy;
/*       */   }
/*       */ 
/*       */   public String getNumName() {
/* 16856 */     return this.numName;
/*       */   }
/*       */ 
/*       */   public void setNumName(String numName) {
/* 16860 */     this.numName = numName;
/*       */   }
/*       */ 
/*       */   public Integer getNum() {
/* 16864 */     return this.num;
/*       */   }
/*       */ 
/*       */   public void setNum(Integer num) {
/* 16868 */     this.num = num;
/*       */   }
/*       */ 
/*       */   public Integer getPurchaseQuantity() {
/* 16872 */     return this.purchaseQuantity;
/*       */   }
/*       */ 
/*       */   public void setPurchaseQuantity(Integer purchaseQuantity) {
/* 16876 */     this.purchaseQuantity = purchaseQuantity;
/*       */   }
/*       */ 
/*       */   public Integer getOrderId() {
/* 16880 */     return this.orderId;
/*       */   }
/*       */ 
/*       */   public void setOrderId(Integer orderId) {
/* 16884 */     this.orderId = orderId;
/*       */   }
/*       */ 
/*       */   public String getCity() {
/* 16888 */     return this.city;
/*       */   }
/*       */ 
/*       */   public void setCity(String city) {
/* 16892 */     this.city = city;
/*       */   }
/*       */ 
/*       */   public String getRefreshToken() {
/* 16896 */     return this.refreshToken;
/*       */   }
/*       */ 
/*       */   public void setRefreshToken(String refreshToken) {
/* 16900 */     this.refreshToken = refreshToken;
/*       */   }
/*       */ 
/*       */   public String getIco() {
/* 16904 */     return this.ico;
/*       */   }
/*       */ 
/*       */   public void setIco(String ico) {
/* 16908 */     this.ico = ico;
/*       */   }
/*       */ 
/*       */   public String getSignature() {
/* 16912 */     return this.signature;
/*       */   }
/*       */ 
/*       */   public void setSignature(String signature) {
/* 16916 */     this.signature = signature;
/*       */   }
/*       */ 
/*       */   public Integer getValue() {
/* 16920 */     return this.value;
/*       */   }
/*       */ 
/*       */   public void setValue(Integer value) {
/* 16924 */     this.value = value;
/*       */   }
/*       */ 
/*       */   public Integer getKeyvalue() {
/* 16928 */     return this.keyvalue;
/*       */   }
/*       */ 
/*       */   public void setKeyvalue(Integer keyvalue) {
/* 16932 */     this.keyvalue = keyvalue;
/*       */   }
/*       */ 
/*       */   public String getDeviceType() {
/* 16936 */     return this.deviceType;
/*       */   }
/*       */ 
/*       */   public void setDeviceType(String deviceType) {
/* 16940 */     this.deviceType = deviceType;
/*       */   }
/*       */ 
/*       */   public Integer getCommand() {
/* 16944 */     return this.command;
/*       */   }
/*       */ 
/*       */   public void setCommand(Integer command) {
/* 16948 */     this.command = command;
/*       */   }
/*       */ 
/*       */   public String getRoomInfo() {
/* 16952 */     return this.roomInfo;
/*       */   }
/*       */ 
/*       */   public void setRoomInfo(String roomInfo) {
/* 16956 */     this.roomInfo = roomInfo;
/*       */   }
/*       */ 
/*       */   public String getDeviceAddress() {
/* 16960 */     return this.deviceAddress;
/*       */   }
/*       */ 
/*       */   public void setDeviceAddress(String deviceAddress) {
/* 16964 */     this.deviceAddress = deviceAddress;
/*       */   }
/*       */ 
/*       */   public String getIconUrl() {
/* 16968 */     return this.iconUrl;
/*       */   }
/*       */ 
/*       */   public void setIconUrl(String iconUrl) {
/* 16972 */     this.iconUrl = iconUrl;
/*       */   }
/*       */ 
/*       */   public String getHostCode() {
/* 16976 */     return this.hostCode;
/*       */   }
/*       */ 
/*       */   public void setHostCode(String hostCode) {
/* 16980 */     this.hostCode = hostCode;
/*       */   }
/*       */ 
/*       */   public String getContents() {
/* 16984 */     return this.contents;
/*       */   }
/*       */ 
/*       */   public void setVersion(Integer version) {
/* 16988 */     this.version = version;
/*       */   }
/*       */ 
/*       */   public void setContents(String contents) {
/* 16992 */     this.contents = contents;
/*       */   }
/*       */ 
/*       */   public String getFloorInfo() {
/* 16996 */     return this.floorInfo;
/*       */   }
/*       */ 
/*       */   public void setFloorInfo(String floorInfo) {
/* 17000 */     this.floorInfo = floorInfo;
/*       */   }
/*       */ 
/*       */   public String getCurrentPwd() {
/* 17004 */     return this.currentPwd;
/*       */   }
/*       */ 
/*       */   public void setCurrentPwd(String currentPwd) {
/* 17008 */     this.currentPwd = currentPwd;
/*       */   }
/*       */ 
/*       */   public String getVerifyCode() {
/* 17012 */     return this.verifyCode;
/*       */   }
/*       */ 
/*       */   public void setVerifyCode(String verifyCode) {
/* 17016 */     this.verifyCode = verifyCode;
/*       */   }
/*       */ 
/*       */   public Integer getType() {
/* 17020 */     return this.type;
/*       */   }
/*       */ 
/*       */   public void setType(Integer type) {
/* 17024 */     this.type = type;
/*       */   }
/*       */ 
/*       */   public String getCID() {
/* 17028 */     return this.CID;
/*       */   }
/*       */ 
/*       */   public void setCID(String cID) {
/* 17032 */     this.CID = cID;
/*       */   }
/*       */ 
/*       */   public Boolean getFid()
/*       */   {
/* 17041 */     return this.fid;
/*       */   }
/*       */ 
/*       */   public void setFid(Boolean fid) {
/* 17045 */     this.fid = fid;
/*       */   }
/*       */ 
/*       */   public Boolean getFid1() {
/* 17049 */     return this.fid1;
/*       */   }
/*       */ 
/*       */   public void setFid1(Boolean fid1) {
/* 17053 */     this.fid1 = fid1;
/*       */   }
/*       */ 
/*       */   public RequestJson getRequestJson()
/*       */   {
/* 17062 */     return this.requestJson;
/*       */   }
/*       */ 
/*       */   public void setRequestJson(RequestJson requestJson) {
/* 17066 */     this.requestJson = requestJson;
/*       */   }
/*       */ 
/*       */   public String getUserCode() {
/* 17070 */     return this.userCode;
/*       */   }
/*       */ 
/*       */   public void setUserCode(String userCode) {
/* 17074 */     this.userCode = userCode;
/*       */   }
/*       */ 
/*       */   public String getRoomCode() {
/* 17078 */     return this.roomCode;
/*       */   }
/*       */ 
/*       */   public String getAccessToken() {
/* 17082 */     return this.accessToken;
/*       */   }
/*       */ 
/*       */   public void setAccessToken(String accessToken) {
/* 17086 */     this.accessToken = accessToken;
/*       */   }
/*       */ 
/*       */   public void setRoomCode(String roomCode) {
/* 17090 */     this.roomCode = roomCode;
/*       */   }
/*       */ 
/*       */   public String getFloorCode() {
/* 17094 */     return this.floorCode;
/*       */   }
/*       */ 
/*       */   public void setFloorCode(String floorCode) {
/* 17098 */     this.floorCode = floorCode;
/*       */   }
/*       */ 
/*       */   public String getDeviceCode() {
/* 17102 */     return this.deviceCode;
/*       */   }
/*       */ 
/*       */   public void setDeviceCode(String deviceCode) {
/* 17106 */     this.deviceCode = deviceCode;
/*       */   }
/*       */ 
/*       */   public String getUserName() {
/* 17110 */     return this.userName;
/*       */   }
/*       */ 
/*       */   public void setUserName(String userName) {
/* 17114 */     this.userName = userName;
/*       */   }
/*       */ 
/*       */   public String getNickName() {
/* 17118 */     return this.nickName;
/*       */   }
/*       */ 
/*       */   public void setNickName(String nickName) {
/* 17122 */     this.nickName = nickName;
/*       */   }
/*       */ 
/*       */   public String getUserPwd() {
/* 17126 */     return this.userPwd;
/*       */   }
/*       */ 
/*       */   public void setUserPwd(String userPwd) {
/* 17130 */     this.userPwd = userPwd;
/*       */   }
/*       */ 
/*       */   public String getUserPhone() {
/* 17134 */     return this.userPhone;
/*       */   }
/*       */ 
/*       */   public void setUserPhone(String userPhone) {
/* 17138 */     this.userPhone = userPhone;
/*       */   }
/*       */ 
/*       */   public String getUserAddr() {
/* 17142 */     return this.userAddr;
/*       */   }
/*       */ 
/*       */   public String getUserAge() {
/* 17146 */     return this.userAge;
/*       */   }
/*       */ 
/*       */   public void setUserAge(String userAge) {
/* 17150 */     this.userAge = userAge;
/*       */   }
/*       */ 
/*       */   public String getUserSex() {
/* 17154 */     return this.userSex;
/*       */   }
/*       */ 
/*       */   public void setUserSex(String userSex) {
/* 17158 */     this.userSex = userSex;
/*       */   }
/*       */ 
/*       */   public void setUserAddr(String userAddr) {
/* 17162 */     this.userAddr = userAddr;
/*       */   }
/*       */ 
/*       */   public String getFloorName() {
/* 17166 */     return this.floorName;
/*       */   }
/*       */ 
/*       */   public void setFloorName(String floorName) {
/* 17170 */     this.floorName = floorName;
/*       */   }
/*       */ 
/*       */   public Integer getId()
/*       */   {
/* 17180 */     return this.id;
/*       */   }
/*       */ 
/*       */   public void setPageNum(Integer pageNum) {
/* 17184 */     this.pageNum = pageNum;
/*       */   }
/*       */ 
/*       */   public void setPageSize(Integer pageSize) {
/* 17188 */     this.pageSize = pageSize;
/*       */   }
/*       */ 
/*       */   public String getRoomName() {
/* 17192 */     return this.roomName;
/*       */   }
/*       */ 
/*       */   public void setRoomName(String roomName) {
/* 17196 */     this.roomName = roomName;
/*       */   }
/*       */ 
/*       */   public void setId(Integer id) {
/* 17200 */     this.id = id;
/*       */   }
/*       */ 
/*       */   public String getMsg() {
/* 17204 */     return this.msg;
/*       */   }
/*       */ 
/*       */   public void setMsg(String msg) {
/* 17208 */     this.msg = msg;
/*       */   }
/*       */ 
/*       */   public Integer getSatisfaction() {
/* 17212 */     return this.satisfaction;
/*       */   }
/*       */ 
/*       */   public void setSatisfaction(Integer satisfaction) {
/* 17216 */     this.satisfaction = satisfaction;
/*       */   }
/*       */ 
/*       */   public int getPageNum() {
/* 17220 */     return this.pageNum.intValue();
/*       */   }
/*       */ 
/*       */   public void setPageNum(int pageNum) {
/* 17224 */     this.pageNum = Integer.valueOf(pageNum);
/*       */   }
/*       */ 
/*       */   public int getPageSize() {
/* 17228 */     return this.pageSize.intValue();
/*       */   }
/*       */ 
/*       */   public void setPageSize(int pageSize) {
/* 17232 */     this.pageSize = Integer.valueOf(pageSize);
/*       */   }
/*       */ 
/*       */   public String getOrderField() {
/* 17236 */     return this.orderField;
/*       */   }
/*       */ 
/*       */   public void setOrderField(String orderField) {
/* 17240 */     this.orderField = orderField;
/*       */   }
/*       */ 
/*       */   public String getOrderDirection() {
/* 17244 */     return this.orderDirection;
/*       */   }
/*       */ 
/*       */   public void setOrderDirection(String orderDirection) {
/* 17248 */     this.orderDirection = orderDirection;
/*       */   }
/*       */ 
/*       */   public String getCode() {
/* 17252 */     return this.code;
/*       */   }
/*       */ 
/*       */   public void setCode(String code) {
/* 17256 */     this.code = code;
/*       */   }
/*       */ 
/*       */   public File getFileupload() {
/* 17260 */     return this.fileupload;
/*       */   }
/*       */ 
/*       */   public void setFileupload(File fileupload) {
/* 17264 */     this.fileupload = fileupload;
/*       */   }
/*       */ 
/*       */   public String getFileuploadFileName() {
/* 17268 */     return this.fileuploadFileName;
/*       */   }
/*       */ 
/*       */   public void setFileuploadFileName(String fileuploadFileName) {
/* 17272 */     this.fileuploadFileName = fileuploadFileName;
/*       */   }
/*       */ 
/*       */   public String getFileuploadContentType() {
/* 17276 */     return this.fileuploadContentType;
/*       */   }
/*       */ 
/*       */   public void setFileuploadContentType(String fileuploadContentType) {
/* 17280 */     this.fileuploadContentType = fileuploadContentType;
/*       */   }
/*       */ 
/*       */   public PacketProcessHelper getPacketProcessHelper() {
/* 17284 */     return this.packetProcessHelper;
/*       */   }
/*       */ 
/*       */   public void setPacketProcessHelper(PacketProcessHelper packetProcessHelper) {
/* 17288 */     this.packetProcessHelper = packetProcessHelper;
/*       */   }
/*       */ }

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.action.xing.XingUserAction
 * old name:           smarthomeMavenWebProject
 * JD-Core Version:    0.6.2
 */