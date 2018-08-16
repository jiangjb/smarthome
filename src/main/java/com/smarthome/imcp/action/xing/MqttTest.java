		package com.smarthome.imcp.action.xing;

		import org.eclipse.paho.client.mqttv3.MqttClient;
		import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
		import org.eclipse.paho.client.mqttv3.MqttDeliveryToken;
		import org.eclipse.paho.client.mqttv3.MqttMessage;
		import org.eclipse.paho.client.mqttv3.MqttTopic;
		import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

		public class MqttTest{
			/**
		     * 代理服务器ip地址
		     */
		    public static final String MQTT_BROKER_HOST = "tcp://120.77.250.17:1883";

		    /**
		     * 订阅标识
		     */
		    public static final String MQTT_TOPIC = "/xuhong/LED/out";

		    private static String userName = "admin";
		    private static String password = "public";

		    /**
		     * 客户端唯一标识
		     */
		    public static final String MQTT_CLIENT_ID = "mqtt_Msg";//在这里是本机与服务器不是同一台主机，所以MQTT_CLIENT_ID要不一样
		    private static MqttTopic topic;
		    private static MqttClient client;
		    
			public static void main(String[] args){
				// 推送消息
		        MqttMessage message = new MqttMessage();
		        try {
		            client = new MqttClient(MQTT_BROKER_HOST, MQTT_CLIENT_ID, new MemoryPersistence());
		            MqttConnectOptions options = new MqttConnectOptions();
		            options.setCleanSession(true);
		            options.setUserName(userName);
		            options.setPassword(password.toCharArray());
		            options.setConnectionTimeout(20);
		            options.setKeepAliveInterval(60);

		            topic = client.getTopic(MQTT_TOPIC);

		            message.setQos(0);
		            message.setRetained(false);
		            message.setPayload("01011".getBytes());
		            client.connect(options);

		            while (true) {
	            		MqttDeliveryToken token = topic.publish(message);
	            		token.waitForCompletion();
	            		System.out.println("已经发送");
	            		Thread.sleep(10000);
		            }

		        } catch (Exception e) {
		            e.printStackTrace();
		        }
			}
		}

