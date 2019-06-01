package com.Snoopy.SmartIndoorNavigation.MQTT;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Snoopy.SmartIndoorNavigation.Model.Entity.Pi;
import com.Snoopy.SmartIndoorNavigation.Model.Repository.PiRepository;

@Service
public class SubscribePi implements MqttCallback {
	
	//MQTT Broker
    String topic        = "/nav/piReady/";
    
    int qos             = 2;
    String broker       = "tcp://mqtt.iot-embedded.de:1883";
    String clientId     = "JavaSample";
    String username     = "indoor-nav";
    String password		= "(IndoorNav)";
    MemoryPersistence persistence = new MemoryPersistence();
    
    MqttClient sampleClient;
    
    @Autowired
    PiRepository repoPi;
    
	public SubscribePi(){
		
	}
	
	public void subscribe() {
		 try {
	            sampleClient = new MqttClient(broker, clientId, persistence);
	            MqttConnectOptions connOpts = new MqttConnectOptions();
	            connOpts.setCleanSession(true);
	            connOpts.setUserName(username);
	            connOpts.setPassword(password.toCharArray());
	            System.out.println("Connecting to broker: "+broker);
	            
	            
	            sampleClient.connect(connOpts);
	            
	            System.out.println("Connected");
	            
	            sampleClient.setCallback(this);
	            sampleClient.subscribe(topic);
	            
	            
	        } catch(MqttException me) {
	            System.out.println("reason "+me.getReasonCode());
	            System.out.println("msg "+me.getMessage());
	            System.out.println("loc "+me.getLocalizedMessage());
	            System.out.println("cause "+me.getCause());
	            System.out.println("excep "+me);
	            me.printStackTrace();
	        }
	}

	@Override
	public void connectionLost(Throwable arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deliveryComplete(IMqttDeliveryToken arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void messageArrived(String topic, MqttMessage message) throws Exception {
		System.out.println(message);
		
		String messageStr = message.toString();
		
		messageStr = messageStr.replace("{", "");
		messageStr = messageStr.replace("}", "");
		messageStr = messageStr.replace(" ", "");
		
		System.out.println(messageStr);
		String[] messageArr = messageStr.split(",");
		
		
		System.out.println(messageArr[0]);
		System.out.println(messageArr[1]);
		Pi pi = repoPi.findByMacAdress(messageArr[0].split(":")[1]);
		pi.setStatus(Boolean.parseBoolean(messageArr[1].split(":")[1]));
		
		repoPi.save(pi);
		
	}
}
