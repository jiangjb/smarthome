	package com.smarthome.imcp.action.xing;

	import java.io.File;
	import java.io.FileWriter;
	import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.eclipse.paho.client.mqttv3.*;
	import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

	public class MqttReceive{
		
		private static MqttReceive instance;  
	    private MqttReceive (){}  
	  
	    public static synchronized MqttReceive getInstance() {  
		    if (instance == null) {  
		        instance = new MqttReceive();  
		    }  
	    	return instance;  
	    } 
		////////////////////////////////9-19 设置成单例模式，避免多个引用///////////////////////////////////////////
		
		
	    /**
	     * 代理服务器ip地址
	     */
	    public static final String MQTT_BROKER_HOST = "tcp://120.77.250.17:1883";
	
	    /**
	     * 客户端唯一标识
	     */
	    public static  String MQTT_CLIENT_ID = "";//应用的Client_ID
	
	    /**
	     * 订阅标识
	     */
	    //    public static final String MQTT_TOPIC = "/returnValue";//从MQTT订阅的话题       这个话题存放的是红外转发器放返回值的话题
	    public static String MQTT_TOPIC = "";
	
	    public static final String USERNAME = "admin";
	    public static final String PASSWORD = "public";
	
	    private volatile static MqttClient mqttClient;
	    private static MqttConnectOptions options;
	    private static String result="success";
	    
	    public  String msgReceive(String mac) {
	    	//client_ID 客户端唯一标识
	    	String macNew = mac.replace(":", "");//去掉冒号    84:f3:eb:8f:da:f8  >  84f3eb8fdaf8
	    	//订阅消息对应的 Topic   （MQTT里面已经存在的话题）
	    	MQTT_CLIENT_ID=macNew+"_jiang";
	    	MQTT_TOPIC = "/"+macNew+"/out";
//	    	MQTT_TOPIC ="/2a2b3c4d5e6f/out";
	        try {
	            // host为主机名，clientid即连接MQTT的客户端ID，一般以客户端唯一标识符表示，
	            // MemoryPersistence设置clientid的保存形式，默认为以内存保存
	            mqttClient = new MqttClient(MQTT_BROKER_HOST, MQTT_CLIENT_ID, new MemoryPersistence());
	            // 配置参数信息
	            options = new MqttConnectOptions();
	            // 设置是否清空session,这里如果设置为false表示服务器会保留客户端的连接记录，
	            // 这里设置为true表示每次连接到服务器都以新的身份连接
	            options.setCleanSession(false);
	            // 设置用户名
	            options.setUserName(USERNAME);
	            // 设置密码
	            options.setPassword(PASSWORD.toCharArray());
	            // 设置超时时间 单位为秒
	            options.setConnectionTimeout(5);
	            // 设置会话心跳时间 单位为秒 服务器会每隔1.5*20秒的时间向客户端发送个消息判断客户端是否在线，但这个方法并没有重连的机制
	            options.setKeepAliveInterval(10);
	            //设置自动重连  8-20
//	            options.setAutomaticReconnect(true);
	            // 连接
	            mqttClient.connect(options);
	            // 订阅
	            mqttClient.subscribe(MQTT_TOPIC);
	            // 设置回调
	            mqttClient.setCallback(new MqttCallback() {
	            	
	                @Override
	                public void connectionLost(Throwable throwable) {
	                    System.out.println("connectionLost");
	                    try {
							Thread.sleep(30*24*60*60*1000);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
	                }
	
	                @Override
	                public void messageArrived(String topic, MqttMessage mqttMessage) throws Exception {
	                    System.out.println("Topic: " + topic + " ,Message: " + mqttMessage.toString());
	                    //当前时间
	                    Date now = new Date( );
	                    DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	                    String date=df.format(now);
	                    fileOut(macNew,date); 
	                    Thread.sleep(30*24*60*60*1000);
	                }
	
	                @Override
	                public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {
	                	System.err.println("complete");
	                	try {
							Thread.sleep(30*24*60*60*1000);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
	                }
	            });
	        } catch (Exception e) {
	            e.printStackTrace();
	            result="fail";
	        }
	        return result;
	    }
	    public void fileOut(String macNew,String time) {
	    	String dir="/home/onoff";
	    	//判断路径是否存在
			File directory = new File(dir);
	        if (!directory.exists()) {
	            directory.mkdirs();
	        }
	        String fileName=dir+'/'+macNew+".txt";
	      //写入
			FileWriter fw;
			try {
				fw = new FileWriter(fileName);
				fw.write(time);
				fw.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
	    }
	    
	    public static void main(String[] args) {
	    	MqttReceive mr=MqttReceive.getInstance();
//	    	MqttReceive mr2=MqttReceive.getInstance();
	    	mr.msgReceive("2a:2b:3c:4d:5e:6f");
//	    	System.out.println("对象1："+mr);
//	    	System.out.println("对象2："+mr2);
//	    	System.out.println(mr.equals(mr2));
	    }
	
	}