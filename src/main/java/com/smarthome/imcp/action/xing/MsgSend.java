		package com.smarthome.imcp.action.xing;

		import org.eclipse.paho.client.mqttv3.MqttClient;
		import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
		import org.eclipse.paho.client.mqttv3.MqttDeliveryToken;
		import org.eclipse.paho.client.mqttv3.MqttMessage;
		import org.eclipse.paho.client.mqttv3.MqttTopic;
		import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

		public class MsgSend{
			/**
		     * 代理服务器ip地址
		     */
		    public static final String MQTT_BROKER_HOST = "tcp://120.77.250.17:1883";

		    /**
		     * 订阅标识
		     */
//		    public static final String MQTT_TOPIC = "/infraredCode";//这是你放|发布 红外码的话题，红外转发器从这个话题订阅消息    这个之后也弄成动态的，根据传进来的mac地址构造
		    public static  String MQTT_TOPIC = "";
		    
		    private static String userName = "admin";
		    private static String password = "public";

		    /**
		     * 客户端唯一标识
		     */
		    public static  String MQTT_CLIENT_ID = "mqtt_Msg";//在这里是本机与服务器不是同一台主机，所以MQTT_CLIENT_ID要不一样  

		    private static MqttTopic topic;
		    private static MqttClient client;
		    
		    public static Boolean msgSend(String str,String mac) {
		    	boolean result=true;
		    	//client_ID
//		    	MQTT_CLIENT_ID=mac; //服务端拒绝重复的 ClientID 连接 ,利用mac地址区分
		    	//发布消息对应的Topic（MQTT对应的Topic）
//		    	MQTT_TOPIC="/84:f3:eb:8f:da:f4/in";//发布到   /mac地址/in  话题里面
		    	String mac01 = mac.replace(":", "");//去掉冒号
		    	MQTT_TOPIC = "/"+mac01+"/in";
		    	
				// 推送消息
		        MqttMessage message = new MqttMessage();
		        try {
		            client = new MqttClient(MQTT_BROKER_HOST, MQTT_CLIENT_ID, new MemoryPersistence());
		            MqttConnectOptions options = new MqttConnectOptions();
		            options.setCleanSession(true);
		            options.setUserName(userName);
		            options.setPassword(password.toCharArray());
		            // 设置超时时间
		            options.setConnectionTimeout(20);
		            // 设置会话心跳时间
		            options.setKeepAliveInterval(60);

		            topic = client.getTopic(MQTT_TOPIC);

		            message.setQos(0);
		            message.setRetained(true);//实时   
		            message.setPayload(str.getBytes());
		            client.connect(options);
		            
		            int times=0;
		            while (true) {
	            		MqttDeliveryToken token = topic.publish(message);
	            		token.waitForCompletion();
	            		System.out.println("已经发送");
	            		Thread.sleep(100);
	            		times++;
	            		if(times == 3) {
	            			break;
	            		}
		            }

		        } catch (Exception e) {
		            e.printStackTrace();
		        }
		        return true;
			}
		    
		    public static void main(String[] args) {
		    	msgSend("hello,mr.jiang!","2f:3c:6b");
		    }
		}

