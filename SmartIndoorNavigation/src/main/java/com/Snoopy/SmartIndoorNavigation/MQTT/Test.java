package com.Snoopy.SmartIndoorNavigation.MQTT;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

public class Test {


	    public static void main(String[] args) {

	        String topic        = "/nav/";
	        String content      = "Message from Larry";
	        int qos             = 2;
	        String broker       = "tcp://mqtt.iot-embedded.de:1883";
	        String clientId     = "JavaSample";
	        String username     = "indoor-nav";
	        String password		= "(IndoorNav)";
	        MemoryPersistence persistence = new MemoryPersistence();

	        try {
	            MqttClient sampleClient = new MqttClient(broker, clientId, persistence);
	            MqttConnectOptions connOpts = new MqttConnectOptions();
	            connOpts.setCleanSession(true);
	            connOpts.setUserName(username);
	            connOpts.setPassword(password.toCharArray());
	            System.out.println("Connecting to broker: "+broker);
	            
	            
	            sampleClient.connect(connOpts);
	            System.out.println("Connected");
	            System.out.println("Publishing message: "+content);
	            MqttMessage message = new MqttMessage(content.getBytes());
	            message.setQos(qos);
	            sampleClient.publish(topic, message);
	            System.out.println("Message published");
	            sampleClient.disconnect();
	            System.out.println("Disconnected");
	            System.exit(0);
	        } catch(MqttException me) {
	            System.out.println("reason "+me.getReasonCode());
	            System.out.println("msg "+me.getMessage());
	            System.out.println("loc "+me.getLocalizedMessage());
	            System.out.println("cause "+me.getCause());
	            System.out.println("excep "+me);
	            me.printStackTrace();
	        }
	    }
	}

